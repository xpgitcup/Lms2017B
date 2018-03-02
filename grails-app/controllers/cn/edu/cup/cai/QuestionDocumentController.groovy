package cn.edu.cup.cai

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuestionDocumentController {

    QuestionDocumentService questionDocumentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond questionDocumentService.list(params), model:[questionDocumentCount: questionDocumentService.count()]
    }

    def show(Long id) {
        respond questionDocumentService.get(id)
    }

    def create() {
        respond new QuestionDocument(params)
    }

    def save(QuestionDocument questionDocument) {
        if (questionDocument == null) {
            notFound()
            return
        }

        try {
            questionDocumentService.save(questionDocument)
        } catch (ValidationException e) {
            respond questionDocument.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), questionDocument.id])
                redirect questionDocument
            }
            '*' { respond questionDocument, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questionDocumentService.get(id)
    }

    def update(QuestionDocument questionDocument) {
        if (questionDocument == null) {
            notFound()
            return
        }

        try {
            questionDocumentService.save(questionDocument)
        } catch (ValidationException e) {
            respond questionDocument.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), questionDocument.id])
                redirect questionDocument
            }
            '*'{ respond questionDocument, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        questionDocumentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionDocument.label', default: 'QuestionDocument'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
