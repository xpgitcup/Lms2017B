package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.QuestionDocument
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4QuestionDocumentController {

    def questionDocumentService

    /*
    * 更新
    * */

    def update(QuestionDocument questionDocument) {
        if (questionDocument == null) {
            notFound()
            return
        }

        try {
            questionDocumentService.save(questionDocument)
        } catch (ValidationException e) {
            respond questionDocument.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), questionDocument.id])
                //redirect questionDocument
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { respond questionDocument, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def questionDocument = questionDocumentService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [questionDocument: questionDocument])
        } else {
            respond questionDocument
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

        questionDocumentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), id])
                //redirect action:"index", method:"GET"
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /*
    * 显示
    * */

    def show(Long id) {

        def questionDocument = questionDocumentService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [questionDocument: questionDocument])
        } else {
            respond questionDocument
        }
    }

    /*
    * 保存
    * */

    def save(QuestionDocument questionDocument) {
        if (questionDocument == null) {
            notFound()
            return
        }

        try {
            questionDocumentService.save(questionDocument)
        } catch (ValidationException e) {
            respond questionDocument.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), questionDocument.id])
                //redirect questionDocument
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { respond questionDocument, [status: CREATED] }
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
        def questionDocument = new QuestionDocument(params)
        if (request.xhr) {
            render(template: view, model: [questionDocument: questionDocument])
        } else {
            respond questionDocument
        }
    }

    /*
    * 列表
    * */

    def list() {
        def questionDocumentList = QuestionDocument.list(params)

        def view = "list"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [questionDocumentList: questionDocumentList])
        } else {
            respond questionDocumentList
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = QuestionDocument.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
