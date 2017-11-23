package cn.edu.cup.dictionary

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CommonUIAController {

    CommonUIAService commonUIAService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond commonUIAService.list(params), model:[commonUIACount: commonUIAService.count()]
    }

    def show(Long id) {
        respond commonUIAService.get(id)
    }

    def create() {
        respond new CommonUIA(params)
    }

    def save(CommonUIA commonUIA) {
        if (commonUIA == null) {
            notFound()
            return
        }

        try {
            commonUIAService.save(commonUIA)
        } catch (ValidationException e) {
            respond commonUIA.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'commonUIA.label', default: 'CommonUIA'), commonUIA.id])
                redirect commonUIA
            }
            '*' { respond commonUIA, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond commonUIAService.get(id)
    }

    def update(CommonUIA commonUIA) {
        if (commonUIA == null) {
            notFound()
            return
        }

        try {
            commonUIAService.save(commonUIA)
        } catch (ValidationException e) {
            respond commonUIA.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'commonUIA.label', default: 'CommonUIA'), commonUIA.id])
                redirect commonUIA
            }
            '*'{ respond commonUIA, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        commonUIAService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'commonUIA.label', default: 'CommonUIA'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonUIA.label', default: 'CommonUIA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
