package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.Question
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4QuestionController {

    def questionService

    /*
    * 更新
    * */

    def update(Question question) {
        if (question == null) {
            notFound()
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                //redirect question
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { respond question, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def question = questionService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [question: question])
        } else {
            respond question
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

        questionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
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

        def question = questionService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [question: question])
        } else {
            respond question
        }
    }

    /*
    * 保存
    * */

    def save(Question question) {
        if (question == null) {
            notFound()
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                //redirect question
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { respond question, [status: CREATED] }
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
        def question = new Question(params)
        if (request.xhr) {
            render(template: view, model: [question: question])
        } else {
            respond question
        }
    }

    /*
    * 列表
    * */

    def list() {
        def questionList = Question.list(params)

        def view = "list"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [questionList: questionList])
        } else {
            respond questionList
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = Question.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
