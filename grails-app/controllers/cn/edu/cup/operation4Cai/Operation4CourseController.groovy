package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.Course
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED

class Operation4CourseController {

    def courseService

    /*
    * 保存
    * */
    def save(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                //redirect course
                redirect(action: "index")
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    /*
    * 创建对象--指定视图模板
    * */

    def create() {
        def view = 'createCourse'
        if (params.view) {
            view = "${params.view}"
        }
        def course = new Course(params)
        if (request.xhr) {
            render(template: view, model: [course: course])
        } else {
            respond course
        }
    }

    /*
    * 列表
    * */

    def list() {
        def courseList = Course.list(params)

        def view = "listCourse"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [courseList: courseList])
        } else {
            respond courseList
        }
    }

    /*
    * 统计
    * */

    def count() {
        return Course.count()
    }

    def index() {}
}
