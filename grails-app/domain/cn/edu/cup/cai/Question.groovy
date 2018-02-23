package cn.edu.cup.cai

class Question {

    QuestionType questionType
    String questionBody
    String questionAnswer
    CourseObject courseObject
    KnowledgePoint knowledgePoint

    static belongsTo = [course: Course]

    static constraints = {
        questionType()
        questionBody(unique: true)
        questionAnswer(nullable: true)
        courseObject()
        knowledgePoint(nullable: true)
    }

    String toString() {
        return "${questionType}"
    }

}
