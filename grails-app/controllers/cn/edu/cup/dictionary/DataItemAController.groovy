package cn.edu.cup.dictionary

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DataItemAController {

    DataItemAService dataItemAService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dataItemAService.list(params), model:[dataItemACount: dataItemAService.count()]
    }

    def show(Long id) {
        respond dataItemAService.get(id)
    }

    def create() {
        respond new DataItemA(params)
    }

    def save(DataItemA dataItemA) {
        if (dataItemA == null) {
            notFound()
            return
        }

        try {
            dataItemAService.save(dataItemA)
        } catch (ValidationException e) {
            respond dataItemA.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                redirect dataItemA
            }
            '*' { respond dataItemA, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dataItemAService.get(id)
    }

    def update(DataItemA dataItemA) {
        if (dataItemA == null) {
            notFound()
            return
        }

        try {
            dataItemAService.save(dataItemA)
        } catch (ValidationException e) {
            respond dataItemA.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                redirect dataItemA
            }
            '*'{ respond dataItemA, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dataItemAService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
