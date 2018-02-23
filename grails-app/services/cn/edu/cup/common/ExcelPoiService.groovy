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
    def sheets = []

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
                getSheets()
            } else {
                if (fileName.endsWith('xlsx')) {
                    workbook = new XSSFWorkbook(sf);
                    statusOfexcelFile.add("${fileName}是2007以后的excel（文件扩展名为.xlsx）")
                    numberOfSheets = workbook.getNumberOfSheets()
                    getSheets()
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
    * 获取全部sheet
    * */

    def getSheets() {
        for (int i=0; i<numberOfSheets; i++) {
            sheets.add(workbook.getSheetName(i))
        }
        return sheets
    }

    /*
    * 获取表头
    * */

    def getSheetHead(sheetName) {
        def head = []
        def currentSheet = workbook.getSheet(sheetName)

        return getHeadFromSheet(currentSheet, head)
    }

    def getSheetHeadAt(index) {
        def head = []
        def currentSheet = workbook.getSheetAt(index)

        return getHeadFromSheet(currentSheet, head)
    }

    private List getHeadFromSheet(currentSheet, List head) {
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

    def checkHead(source, target) {
        def different = [:]
        def minNum = Math.min(source.size(), target.size())
        def maxNum = Math.max(source.size(), target.size())
        int i = 0
        for (i = 0; i < minNum; i++) {
            if (!source[i].equals(target[i])) {
                different.put(source[i], target[i])
            }
        }
        for (i = minNum; i < maxNum; i++) {
            if (i < source.size()) {
                different.put(source[i], "null")
            } else {
                different.put("缺少${i}", target[i])
            }
        }
        return different
    }

    /*
    * 获取数据
    * */

    def getData(index, skipFirstRow = true) {
        def data = []
        //获得当前sheet的开始行
        def currentSheet = workbook.getSheetAt(index)
        //获取航范围
        int firstRowNum = currentSheet.getFirstRowNum()
        int lastRowNum = currentSheet.getLastRowNum()
        //获取列范围
        def firstRow = currentSheet.getRow(firstRowNum)
        def firstCellNum = firstRow.getFirstCellNum()
        int lastCellNum = firstRow.getPhysicalNumberOfCells();

        //循环--行循环
        def currentRow
        for (int i = firstRowNum + 1; i < lastRowNum; i++) {
            currentRow = currentSheet.getRow(i)
            def dataRow = []
            for (int j = firstCellNum; j < lastCellNum; j++) {
                def cell = currentRow.getCell(j)
                dataRow.add("${cell}")
            }
            data.add(dataRow)
        }
        return data
    }
}
