package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.QuestionType
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4QuestionTypeController {

    def questionTypeService

    /*
    * 更新
    * */

    def update(QuestionType questionType) {
        if (questionType == null) {
            notFound()
            return
        }

        try {
            questionTypeService.save(questionType)
        } catch (ValidationException e) {
            respond questionType.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionType.label', default: 'QuestionType'), questionType.id])
                //redirect questionType
                redirect(action: "index")
            }
            '*' { respond questionType, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def questionType = questionTypeService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [questionType: questionType])
        } else {
            respond questionType
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

        questionTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionType.label', default: 'QuestionType'), id])
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

        def questionType = questionTypeService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [questionType: questionType])
        } else {
            respond questionType
        }
    }

    /*
    * 保存
    * */

    def save(QuestionType questionType) {
        if (questionType == null) {
            notFound()
            return
        }

        try {
            questionTypeService.save(questionType)
        } catch (ValidationException e) {
            respond questionType.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionType.label', default: 'QuestionType'), questionType.id])
                //redirect questionType
                redirect(action: "index")
            }
            '*' { respond questionType, [status: CREATED] }
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
        def questionType = new QuestionType(params)
        if (request.xhr) {
            render(template: view, model: [questionType: questionType])
        } else {
            respond questionType
        }
    }

    /*
    * 列表
    * */

    def list() {
        def questionTypeList = QuestionType.list(params)

        def view = "list"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [questionTypeList: questionTypeList])
        } else {
            respond questionTypeList
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = QuestionType.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
