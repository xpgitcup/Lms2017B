package cn.edu.cup.dictionary

class DataDictionary {

    String name

    static hasMany = [datakeys: DataKeyA]

    static constraints = {
        name(unique: true)
    }

    String toString() {
        return "${name}/(${datakeys?.size()})"
    }

    def modelCount() {
        datakeys.countBy { e->
            e.subDataKeys.size() > 0
        }
    }
}
