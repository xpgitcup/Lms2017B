package cn.edu.cup.cai

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class KnowledgePointController {

    KnowledgePointService knowledgePointService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond knowledgePointService.list(params), model:[knowledgePointCount: knowledgePointService.count()]
    }

    def show(Long id) {
        respond knowledgePointService.get(id)
    }

    def create() {
        respond new KnowledgePoint(params)
    }

    def save(KnowledgePoint knowledgePoint) {
        if (knowledgePoint == null) {
            notFound()
            return
        }

        try {
            knowledgePointService.save(knowledgePoint)
        } catch (ValidationException e) {
            respond knowledgePoint.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), knowledgePoint.id])
                redirect knowledgePoint
            }
            '*' { respond knowledgePoint, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond knowledgePointService.get(id)
    }

    def update(KnowledgePoint knowledgePoint) {
        if (knowledgePoint == null) {
            notFound()
            return
        }

        try {
            knowledgePointService.save(knowledgePoint)
        } catch (ValidationException e) {
            respond knowledgePoint.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), knowledgePoint.id])
                redirect knowledgePoint
            }
            '*'{ respond knowledgePoint, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        knowledgePointService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
