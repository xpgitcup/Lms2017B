package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.CourseObject
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4CourseObjectController {

    def courseObjectService
    /*
    * 更新
    * */

    def update(CourseObject courseObject) {
        if (courseObject == null) {
            notFound()
            return
        }

        try {
            courseObjectService.save(courseObject)
        } catch (ValidationException e) {
            respond courseObject.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), courseObject.id])
                //redirect courseObject
                redirect(action: "index", controller: "operation4Course")
            }
            '*' { respond courseObject, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def courseObject = courseObjectService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [courseObject: courseObject])
        } else {
            respond courseObject
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

        courseObjectService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), id])
                //redirect action:"index", method:"GET"
                redirect(action: "index", controller: "operation4Course")
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /*
    * 显示
    * */

    def show(Long id) {

        def courseObject = courseObjectService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [courseObject: courseObject])
        } else {
            respond courseObject
        }
    }

    /*
    * 保存
    * */

    def save(CourseObject courseObject) {
        if (courseObject == null) {
            notFound()
            return
        }

        try {
            courseObjectService.save(courseObject)
        } catch (ValidationException e) {
            respond courseObject.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), courseObject.id])
                //redirect courseObject
                redirect(action: "index", controller: "operation4Course")
            }
            '*' { respond courseObject, [status: CREATED] }
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
        def courseObject = new CourseObject(params)
        if (request.xhr) {
            render(template: view, model: [courseObject: courseObject])
        } else {
            respond courseObject
        }
    }

    /*
    * 列表
    * */

    def list() {
        def courseList = CourseObject.list(params)

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
        return CourseObject.count()
    }

    def index() { }
}
