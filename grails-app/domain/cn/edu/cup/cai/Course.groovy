package cn.edu.cup.cai

class Course {

    String code
    String name

    static hasMany = [courseObjects: CourseObject, knowledgePoints: KnowledgePoint]

    static constraints = {
        code(unique: true)
        name()
    }

    String toString() {
        return "${code}/${name}"
    }

}
