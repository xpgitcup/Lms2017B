package cn.edu.cup.dictionary

import cn.edu.cup.dictionary.DataKeyA

class CommonUIA {

    DataKeyA dataKeyA
    String   viewList
    String   viewEdit
    String   viewShow
    String   jsFileName

    static constraints = {
    }


    String toString() {
        return "${dataKeyA?.dataTag}"
    }
}
