package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import cn.edu.cup.dictionary.DataKeyType
import grails.gorm.transactions.Transactional
import groovy.json.JsonBuilder
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

@Transactional
class ExcelService {


    /*
    * 生成模板
    * */
    def createTemplate(DataKeyA dataKeyA, String path) {

        def fileName = "${path}/${dataKeyA.dataTag}_${dataKeyA.id}.xls"

        try {
            //  打开文件
            File file = new File(fileName)

            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${dataKeyA.dataTag}", 0);

            /*
            * 整体的策略：
            *
            * 1.数据按列分布
            * 2.如果遇到数组，数组的标题就是appendParameter,逗号分隔
            * 3.
            * */
            Label label
            Label labelUnit
            def colIndex = 0
            if (dataKeyA.isDataModel()) {
                //先处理继承的问题
                def ms = dataKeyA.superKeys()
                println("处理数据字典的 ${ms}")
                ms.each { me ->
                    me.subDataKeys.each() { it ->
                        if (!it.isDataModel()) {
                            colIndex = createCell4Field(it, colIndex, sheet)
                        }
                    }
                }
                //再处理本身
                dataKeyA.subDataKeys.eachWithIndex() { e, i ->
                    println("${e}")
                    if (!e.isDataModel()) {
                        //只有具体的数据属性才出现在模板中
                        colIndex = createCell4Field(e, colIndex, sheet)
                    }
                }
            } else {
                label = new Label(0, 0, "这不是一个数据模型，无法生成模板")
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

    private int createCell4Field(DataKeyA e, int colIndex, Sheet sheet) {
        Label labelUnit
        Label label
        Label description

        label = new Label(colIndex, 0, e.dataTag)
        labelUnit = new Label(colIndex, 2, e.dataUnit)

        switch (e.dataKeyType) {
            case DataKeyType.dataKeyNormal:
                description = new Label(colIndex, 1, "普通数据")
                break;
            case DataKeyType.dataKeyText:
                description = new Label(colIndex, 1, "多行数据")
                break;
            case DataKeyType.dataKeyDate:
                description = new Label(colIndex, 1, "日期")
                break;
            case DataKeyType.dataKeyDateTime:
                description = new Label(colIndex, 1, "日期+时间")
                break;
            case DataKeyType.dataKeyEnum:
                description = new Label(colIndex, 1, "枚举数据,请选择")
                labelUnit = new Label(colIndex, 2, "${e.dataUnit}/${e.appendParameter}")
                break;
            case DataKeyType.dataKeyFile:
                description = new Label(colIndex, 1, "文件--提供文件名")
                break;
            case DataKeyType.dataKeyRef:
                description = new Label(colIndex, 1, "引用数据--被引用的关键字ID")
                labelUnit = new Label(colIndex, 2, "${e.dataUnit}/${e.appendParameter}")
                break;
        }

        sheet.addCell(description)
        sheet.addCell(label)
        sheet.addCell(labelUnit)

        if (e.columnNumber > 1) {
            //println("处理附加信息：${e.appendParameter}")
            if (e.appendParameter) {
                def ss = e.appendParameter.split(",")
                for (int i=0; i<ss.size(); i++) {
                    labelUnit = new Label(colIndex + i, 2, "{${ss[i]}}")
                    sheet.addCell(labelUnit)
                }
            }
            else {
                labelUnit = new Label(colIndex, 2, "超过一维的数据，需要设置附加属性")
                sheet.addCell(labelUnit)
            }
        }
        colIndex += e.columnNumber
        return colIndex
    }

    /*
    * 导入数据
    * */

    def importDataFromExcelFile4DataKeyA(DataKeyA dataKeyA, File excelFile) {
        def message = []
        try {
            //  打开文件
            Workbook book = Workbook.getWorkbook(excelFile);
            //  首先查找对应的sheet
            Sheet sheet = book.getSheet("${dataKeyA.dataTag}")
            if (sheet) {
                //导入当前关键字
                message.addAll(importDataFromSheet(dataKeyA, sheet))
            } else {
                message.add("找不到对应的[${dataKeyA.dataTag}]sheet.")
            }
            book.close();
        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return message
    }

    def importDataFromSheet(DataKeyA dataKeyA, Sheet sheet) {
        def message = checkDataTag4DataModel(dataKeyA, sheet)
        if (message.isEmpty()) {
            //首先创建关键字的祖先类。。。
            def dataItem = new DataItemA(dataKeyA: dataKeyA)
            dataItem.save(true)
            //导入数据...
            importDataTag4DataModel(dataKeyA, dataItem, sheet)
        }
        return message
    }

    //导入数据
    def importDataTag4DataModel(DataKeyA dataKeyA, DataItemA dataItemA, Sheet sheet) {
        //先处理超类的
        def colIndex = 0
        //首先处理超类所对应的标签
        dataKeyA.superKeys().each { e->
            importDataTagDataModelSelf(colIndex, e, dataItemA, sheet)
            colIndex += e.columnCount()
        }
        //再处理本身的
        importDataTagDataModelSelf(colIndex, dataKeyA, dataItemA, sheet)
    }

    def importDataTagDataModelSelf(int startColIndex, DataKeyA dataKeyA, DataItemA dataItemA, Sheet sheet) {
        dataKeyA.subDataKeys.eachWithIndex { DataKeyA entry, int i ->
            if (!entry.isDataModel()) {
                importDataTad4Cell(startColIndex + i, entry, dataItemA, sheet)
            }
        }
    }

    def importDataTad4Cell(int startColIndex, DataKeyA dataKeyA, DataItemA dataItemA, Sheet sheet) {
        if (dataKeyA.single) {
            //只导入一行
            def cell = sheet.getCell(startColIndex, 2)
            def subItem = new DataItemA(dataKeyA: dataKeyA, upDataItem: dataItemA, dataValue: cell.contents)
            subItem.save(true)
        } else {
            //导入所有行
            def rowCount = sheet.rows
            for (int i=2; i<rowCount; i++) {
                //导入多维数据
                //importVectorB(dataItem, sheet, entry, si + colIndex, i)
                importVectorB(dataKeyA, dataItemA, sheet, startColIndex, i)
            }
        }
    }

    private void importVectorB(DataKeyA entry, DataItemA dataItemA, Sheet sheet, int index, int i) {
        def v = []
        for (int j = 0; j < entry.dimension; j++) {
            def cell = sheet.getCell(index + j, i)
            v.add(cell.contents)
        }

        /*
        JsonOutput jsonOutput = new JsonOutput()
        def vv = jsonOutput.toJson(v)
        */
        JsonBuilder jsonBuilder = new JsonBuilder(v)
        def vv = v.toString()

        def subItem = new DataItemA(dataKeyA: entry, upDataItem: dataItemA, dataValue: vv)
        subItem.save(true)
    }

    //检查数据模型的标签
    def checkDataTag4DataModel(DataKeyA dataKeyA, Sheet sheet) {
        def message = []
        def colIndex = 0
        //首先处理超类所对应的标签
        dataKeyA.superKeys().each { e->
            message.addAll(checkDataTag4DataModelSelf(colIndex, e, sheet))
            colIndex += e.columnCount()
        }
        //处理自身的标签
        println("测试自身${dataKeyA}的标签...")
        message.addAll(checkDataTag4DataModelSelf(colIndex, dataKeyA, sheet))
        return message
    }

    //检查数据模型的标签
    def checkDataTag4DataModelSelf(int startColIndex, DataKeyA dataKeyA, Sheet sheet) {
        def message = []
        dataKeyA.subDataKeys.eachWithIndex{ def entry, int i ->
            println("测试${entry}")
            if (!entry.isDataModel()) {
                println("测试标签：${entry} ${startColIndex + i}")
                message.addAll(checkDataTag4DataKey(startColIndex + i, entry, sheet))
            }
        }
        return message
    }

    //检查数据标签
    private checkDataTag4DataKey(int index, DataKeyA e, Sheet sheet) {
        def message = []
        def cell = sheet.getCell(index, 0)
        if (!cell.contents.equals(e.dataTag)) {
            message.add("${e.dataTag}不匹配.")
        }
        return message
    }

    private void importVectorA(DataKeyA entry, int index, int i) {
        def dataItem
        for (int j = 0; j < entry.dimension; j++) {
            def cell = sheet.getCell(index + j, i)
            def subItem = new DataItemA(dataKeyA: entry, upDataItem: dataItem, dataValue: cell.contents)
            subItem.save(true)
        }
    }

    @Transactional
    def importExcel2DataItem(DataKeyA dataKey, File excelFile) {
        def message = checkExcelFile(dataKey, excelFile)
    }

    /*
    * excel文件验证
    * */

    def checkExcelFile(DataKeyA dataKey, File excelFile) {
        def message = [:]
        return message
    }

    /*
    * DataKey输出到excel
    * */

    def exportDataKey2Excel(DataKeyA dataKey, String fileName) {
        return fileName
    }

    /*
     * 输入参数是params.filename=需要导入的excel文件名
     * */

    def importExcelFile(params) {
        def filename
        def data = []
        if (params.filename) {
            filename = params.filename
            def sheetIndex
            if (params.sheetIndex) {
                sheetIndex = params.sheetIndex as int
            } else {
                sheetIndex = 0
            }
            try {
                //  打开文件
                File file = new File(filename)
                println "importExcelFile ${file}"
                Workbook book = Workbook.getWorkbook(file);
                //  生成名为“第一页”的工作表，参数0表示这是第一页
                Sheet sheet = book.getSheet(sheetIndex);

                def rowCount = sheet.getRows()
                def colCount = sheet.getColumns()

                println "importExcelFile ${rowCount}, ${colCount}"

                Cell cell;
                for (int i = 0; i < rowCount; i++) {
                    def row = []
                    for (int j = 0; j < colCount; j++) {
                        cell = sheet.getCell(j, i)
                        row.add(cell.getContents())
                    }
                    data.add(row)
                }
                //并关闭文件
                book.close();

            } catch (Exception e) {
                println "importExcelFile error: ${e}";
            }
        } else {
            println "usage: importExcelFile([filename, sheetIndex])"
        }
        return data
    }

    def exportExcelFile(String filename, List dataList) {
        try {
            //  打开文件
            File file = new File(filename)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("第一页", 0);

            Label label
            dataList.eachWithIndex() { e, i ->
                println "ExcelService ${e}"
                def tmps = e.collect()
                tmps.eachWithIndex() { f, j ->
                    label = new Label(j, i, f.toString())
                    //  将定义好的单元格添加到工作表中
                    sheet.addCell(label);
                }
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
    }

}
