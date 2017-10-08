package cn.edu.cup.dictionary

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DataKeyAController {

    DataKeyAService dataKeyAService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dataKeyAService.list(params), model:[dataKeyACount: dataKeyAService.count()]
    }

    def show(Long id) {
        respond dataKeyAService.get(id)
    }

    def create() {
        respond new DataKeyA(params)
    }

    def save(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            notFound()
            return
        }

        try {
            dataKeyAService.save(dataKeyA)
        } catch (ValidationException e) {
            respond dataKeyA.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect dataKeyA
            }
            '*' { respond dataKeyA, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dataKeyAService.get(id)
    }

    def update(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            notFound()
            return
        }

        try {
            dataKeyAService.save(dataKeyA)
        } catch (ValidationException e) {
            respond dataKeyA.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect dataKeyA
            }
            '*'{ respond dataKeyA, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dataKeyAService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
