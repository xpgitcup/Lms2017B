package cn.edu.cup.dictionary

import cn.edu.cup.system.SystemMenu

class DataKey4Menu {

    DataKeyA dataKeyA
    Integer  dataValue
    String   viewFileName
    String   jsFileName

    static constraints = {
    }

    String toString() {
        return "${dataKeyA?.dataTag}=${dataValue}"
    }
}
