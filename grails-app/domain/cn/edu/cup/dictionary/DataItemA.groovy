package cn.edu.cup.dictionary

class DataItemA {

    DataKeyA    dataKeyA
    String      dataValue

    static belongsTo = [upDataItem: DataItemA]

    static hasMany = [subDataItems: DataItemA]

    static mapping = {
        subDataItems sort: 'dataKeyA'
    }

    static constraints = {
        dataKeyA()
        dataValue(nullable: true)
        upDataItem(nullable: true)
    }

    String toString() {
        if (dataKeyA.dataUnit.equals("无量纲"))
        {
            return  "${dataKeyA}=${dataValue}"
        }
        else
        {
            return "${dataKeyA}=${dataValue}${dataKeyA.dataUnit}"
        }
    }
}
