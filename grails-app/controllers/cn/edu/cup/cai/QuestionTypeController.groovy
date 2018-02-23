package cn.edu.cup.cai

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuestionTypeController {

    QuestionTypeService questionTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond questionTypeService.list(params), model:[questionTypeCount: questionTypeService.count()]
    }

    def show(Long id) {
        respond questionTypeService.get(id)
    }

    def create() {
        respond new QuestionType(params)
    }

    def save(QuestionType questionType) {
        if (questionType == null) {
            notFound()
            return
        }

        try {
            questionTypeService.save(questionType)
        } catch (ValidationException e) {
            respond questionType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionType.label', default: 'QuestionType'), questionType.id])
                redirect questionType
            }
            '*' { respond questionType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questionTypeService.get(id)
    }

    def update(QuestionType questionType) {
        if (questionType == null) {
            notFound()
            return
        }

        try {
            questionTypeService.save(questionType)
        } catch (ValidationException e) {
            respond questionType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionType.label', default: 'QuestionType'), questionType.id])
                redirect questionType
            }
            '*'{ respond questionType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        questionTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionType.label', default: 'QuestionType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionType.label', default: 'QuestionType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
