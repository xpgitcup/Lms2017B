package cn.edu.cup.cai

class QuestionDocument {

    Course course
    QuestionType questionType
    String fileName
    boolean hasImproved

    static constraints = {
        course()
        questionType()
        fileName(nullable: true)
    }

    String toString() {
        return "${course}.${questionType}.${fileName}"
    }
}
