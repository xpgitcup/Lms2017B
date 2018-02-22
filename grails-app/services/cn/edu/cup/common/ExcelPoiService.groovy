package cn.edu.cup.common

import grails.gorm.transactions.Transactional
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

@Transactional
class ExcelPoiService {

    def excelFileName
    def isOk = false
    def statusOfexcelFile = []
    def numberOfSheets = 0
    def workbook = null

    /*
    * 初始化
    * */

    def openExcelFile(String fileName) {
        def sf = new File(fileName)
        excelFileName = fileName
        //确保文件存在
        if (sf.exists()) {
            statusOfexcelFile.add("${fileName} ok.")
            if (fileName.endsWith('.xls')) {
                workbook = new HSSFWorkbook(sf)
                statusOfexcelFile.add("${fileName}是2007以前的excel（文件扩展名为.xls）")
                numberOfSheets = workbook.getNumberOfSheets()
            } else {
                if (fileName.endsWith('xlsx')) {
                    workbook = new XSSFWorkbook(sf);
                    statusOfexcelFile.add("${fileName}是2007以后的excel（文件扩展名为.xlsx）")
                    numberOfSheets = workbook.getNumberOfSheets()
                } else {
                    statusOfexcelFile.add("${fileName}不是excel文件.")
                }
            }
        } else {
            statusOfexcelFile.add("${fileName} no fount.")
        }
        return isOk
    }

    /*
    * 获取表头
    * */

    def getSheetHead(index) {
        def head = []
        def currentSheet = workbook.getSheet(index)
        //获得当前sheet的开始行
        int firstRowNum = currentSheet.getFirstRowNum()
        def firstRow = currentSheet.getRow(firstRowNum)
        def firstCellNum = firstRow.getFirstCellNum()
        int lastCellNum = firstRow.getPhysicalNumberOfCells();
        //循环当前行
        for (int i = firstCellNum; i < lastCellNum; i++) {
            def cell = firstRow.getCell(i);
            head.add(cell.getStringCellValue());
        }
        return head
    }

    /*
    * 比较标题
    * */

    def checkHead(String[] source, String[] target) {
        def different = [:]
        def minNum = Math.min(source.length, target.length)
        def maxNum = Math.max(source.length, target.length)
        int i = 0
        for (i = 0; i < minNum; i++) {
            if (!source[i].equals(target[i])) {
                different.put(source[i], target[i])
            }
        }
        for (i = minNum; i < maxNum; i++) {
            if (i<source.length) {
                different.put(source[i], "null")
            } else {
                different.put("缺少${i}", target[i])
            }
        }
        return different
    }

    /*
    * 
    * */
}
