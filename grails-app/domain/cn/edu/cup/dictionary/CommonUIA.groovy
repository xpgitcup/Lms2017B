package cn.edu.cup.dictionary

import cn.edu.cup.dictionary.DataKeyA

class CommonUIA {

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
