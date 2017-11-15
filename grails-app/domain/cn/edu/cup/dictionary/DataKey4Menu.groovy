package cn.edu.cup.dictionary

import cn.edu.cup.system.SystemMenu

class DataKey4Menu {

    SystemMenu systemMenu
    DataKeyA dataKeyA

    static constraints = {
    }

    String toString() {
        return "${systemMenu?.menuContext}-${dataKeyA?.dataTag}"
    }
}
