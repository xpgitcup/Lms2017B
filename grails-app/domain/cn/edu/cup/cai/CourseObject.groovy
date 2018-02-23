package cn.edu.cup.cai

class CourseObject {

    String context

    static belongsTo = [course: Course]

    static constraints = {
        context(unique: true)
    }

    String toString() {
        return "${context}/${course}"
    }
}
