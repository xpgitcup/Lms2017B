package cn.edu.cup.cai

class QuestionType {

    String name

    static constraints = {
        name(unique: true)
    }

    String toString() {
        return "${name}"
    }

}
