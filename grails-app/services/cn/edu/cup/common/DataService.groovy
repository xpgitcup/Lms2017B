package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataItemAService
import cn.edu.cup.dictionary.DataKeyA
import grails.gorm.transactions.Transactional
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

@Transactional
class DataService {

    /*
    * 构建数据的索引
    * */

    def createIndex(DataItemA dataItemA) {
        def temp = ""
        def m = [:]
        switch (dataItemA.dataKeyA.id) {
            case 33:
                dataItemA.subDataItems.each { e ->
                    if (e.dataKeyA.indexed) {
                        //m.put(e.dataKeyA.dataTag, e.dataValue)
                        temp = e.dataValue
                    }
                }
                break
            default:
                dataItemA.subDataItems.each { e ->
                    if (e.dataKeyA.indexed) {
                        m.put(e.dataKeyA.dataTag, e.dataValue)
                    }
                }
                temp = "${m}"
        }
        return temp
    }

    /*
    * 生成模板文件
    * */

    def createTemplate(DataKeyA dataKeyA, String path) {

        def fileName = "${path}/${dataKeyA.dataTag}_${dataKeyA.id}.xls"

        try {
            //  打开文件
            File file = new File(fileName)

            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${dataKeyA.dataTag}", 0);

            exportHeads(dataKeyA, sheet)

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

    /*
    * 直接将关键字的Heads顺序输出到Excel文件中
    * */

    private void exportHeads(DataKeyA dataKeyA, sheet) {
        int r = 0;
        int c = 0;
        dataKeyA.heads().each { e ->
            r = 0
            e.each { ee ->
                def label = new Label(c, r, ee)
                sheet.addCell(label)
                r++
            }
            c++
        }
    }

    /*
    * 导出数据文件
    * */

    def exportToExcelFile(DataItemA dataItemA, String path) {
        def fileName = "${path}/${dataItemA.dataKeyA.dataTag}_${dataItemA.dataKeyA.id}_${dataItemA.id}_data.xls"

        try {
            //  打开文件
            File file = new File(fileName)

            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${dataItemA.dataKeyA.dataTag}", 0);

            exportHeads(dataItemA.dataKeyA, sheet)

            int r = 3
            int c = 0
            dataItemA.subDataItems.each { e ->
                def label = new Label(c, r, e.dataValue)
                sheet.addCell(label)
                c++
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

    /*
    * 导入数据文件
    * */

    @Transactional
    def importDataFromExcelFile(DataKeyA dataKeyA, excelFile) {
        def message = []

        Workbook book = Workbook.getWorkbook(excelFile)
        Sheet sheet = book.getSheet(dataKeyA.dataTag)

        if (!sheet) {
            message.add("找不到数据：${dataKeyA.dataTag}")
            return message
        }

        checkHeads(dataKeyA, sheet, message)

        if (message.size() < 1) {
            def dataItemA = new DataItemA(dataKeyA: dataKeyA)
            def newItems = []
            int r = 3
            dataKeyA.realSubDataKeys().eachWithIndex { DataKeyA entry, int i ->
                def cell = sheet.getCell(i, r)
                def item = new DataItemA(upDataItem: dataItemA, dataKeyA: entry, dataValue: cell.contents)
                newItems.add(item)
            }
            dataItemA.subDataItems = newItems
            dataItemA.save(flush: true)
            message.add("成功导入文件 ${excelFile}.")
        } else {
            return message
        }
    }

    /*
    * 导入数据文件检查
    * */

    private void checkHeads(DataKeyA dataKeyA, sheet, message) {
        int r = 0
        int c = 0
        dataKeyA.heads().each { e ->
            r = 0
            e.each { ee ->
                def cell = sheet.getCell(c, r)
                if (ee != cell.contents) {
                    message.add("(${c},${r})应该是:${ee}, 不应该是：${cell.contents}")
                }
                r++
            }
            c++
        }
    }

}
