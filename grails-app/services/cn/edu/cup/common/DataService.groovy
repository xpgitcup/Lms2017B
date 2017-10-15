package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import grails.gorm.transactions.Transactional
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

@Transactional
class DataService {

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

            int r = 2
            int c = 0
            dataItemA.subDataItems.each {e ->
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
    * 导入数据文件检查
    * */

    /*
    * 导入数据文件
    * */

}
