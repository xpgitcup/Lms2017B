package cn.edu.cup.cai

class TestPaper {

    String testName

    static hasMany = [questions: Question]

    static constraints = {
    }

    String toString() {
        return "${testName}"
    }

}
