package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.Course
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4CourseController {

    def courseService

    /*
    * 更新
    * */

    def update(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                //redirect course
                redirect(action: "index")
            }
            '*' { respond course, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def course = courseService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [course: course])
        } else {
            respond course
        }
    }

    /*
    * 删除
    * */

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), id])
                //redirect action:"index", method:"GET"
                redirect(action: "index")
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /*
    * 显示
    * */

    def show(Long id) {

        def course = courseService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [course: course])
        } else {
            respond course
        }
    }

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
            respond course.errors, view: 'create'
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
        def view = 'create'
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

        def view = "list"
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
        count = Course.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
