package cn.edu.cup.dictionary

import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

class DataKeyA {

    String dataTag                  //数据标签
    String dataUnit = '无量纲'      //数据单位
    String appendParameter = ''     //附加参数
    DataKeyType dataKeyType = DataKeyType.dataKeyNormal     //数据关键字类型
    String columnSeperator = ","   //列分割副
    int columnNumber = 1          //列数

    int orderNumber = 0         //顺序

    DataKeyA upDataKey

    static belongsTo = [dictionary: DataDictionary]

    static hasMany = [subDataKeys: DataKeyA]

    static mapping = {
        subDataKeys sort: 'orderNumber', 'id'
    }

    static constraints = {
        dataTag()
        dataUnit(nullable: true)
        appendParameter(nullable: true)
        dataKeyType()
        columnNumber()
        columnSeperator()
        orderNumber()
        upDataKey(nullable: true)
    }

    String toString() {
        //return "${dictionary}.${dataTag}.${subDataKeys?.size()}"
        if (subDataKeys) {
            return "${dictionary}.${dataTag}/(模型)"
        } else {
            return "${dictionary}.${dataTag}/(数据项)"
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //触发器
    def beforeInsert() {
        if (upDataKey) {
            dictionary = upDataKey.dictionary
            orderNumber = upDataKey.orderNumber
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //返回数据列数
    def enumItems() {
        def enumItems
        if (dataKeyType == DataKeyType.dataKeyEnum) {
            enumItems = this.appendParameter.split(columnSeperator)
        } else {
            enumItems = []
        }
        return enumItems
    }

    /*
    * 数据标题
    * */
    def heads() {
        def h = []
        superKeys().each {e->
            h.add(getHeads(e))
        }
        h.addAll(getHeads(this))
        return h
    }

    private getHeads(DataKeyA dataKeyA) {
        def h = []
        subDataKeys.each {e ->
            def c = []
            c.add(e.dataTag)
            switch (e.dataKeyType) {
                case DataKeyType.dataKeyNormal:
                    c.add("普通数据")
                    break;
                case DataKeyType.dataKeyText:
                    c.add("普通数据")
                    break;
                case DataKeyType.dataKeyDate:
                    c.add("日期")
                    break;
                case DataKeyType.dataKeyDateTime:
                    c.add("日期时间")
                    break;
                case DataKeyType.dataKeyEnum:
                    c.add("枚举数据，请选择：${e.appendParameter}")
                    break;
                case DataKeyType.dataKeyFile:
                    c.add("文件")
                    break;
                case DataKeyType.dataKeyRef:
                    c.add("引用：${e.appendParameter}")
                    break;
            }
            c.add(e.dataUnit)
            h.add(c)
        }
        return h
    }

    def columnCount() {
        def c = 0
        subDataKeys.each { e ->
            if (!e.isDataModel()) {
                c += e.columnNumber
            }
        }
        return c
    }

    //返回关键字的超类列表
    def superKeys() {
        def s = []
        def p = upDataKey
        while (p) {
            s.add(p)
            p = p.upDataKey
        }
        return s.reverse()
    }

    def isDataModel() {
        return this.subDataKeys.size() > 0
    }


    /*
    * 导入数据
    * */

    def importFromExcelFile(File sf) {

        def message = []

        try {
            //  打开文件
            Workbook book = Workbook.getWorkbook(sf);
            //  首先查找对应的sheet
            Sheet sheet = book.getSheet("${this.dataTag}")

            if (sheet) {
                importDataFromSheet(sheet, message)
            } else {
                message.add("找不到对应的[${this.dataTag}]sheet.")
            }
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return message
    }

    /*
    * 从sheet中导入数据
    * */

    def importDataFromSheet(sheet, message) {
        def dataItem = new DataItemA(dataKeyA: this)
        dataItem.save(true)
    }

}
