package cn.edu.cup.cai

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseObjectController {

    CourseObjectService courseObjectService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseObjectService.list(params), model:[courseObjectCount: courseObjectService.count()]
    }

    def show(Long id) {
        respond courseObjectService.get(id)
    }

    def create() {
        respond new CourseObject(params)
    }

    def save(CourseObject courseObject) {
        if (courseObject == null) {
            notFound()
            return
        }

        try {
            courseObjectService.save(courseObject)
        } catch (ValidationException e) {
            respond courseObject.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), courseObject.id])
                redirect courseObject
            }
            '*' { respond courseObject, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseObjectService.get(id)
    }

    def update(CourseObject courseObject) {
        if (courseObject == null) {
            notFound()
            return
        }

        try {
            courseObjectService.save(courseObject)
        } catch (ValidationException e) {
            respond courseObject.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), courseObject.id])
                redirect courseObject
            }
            '*'{ respond courseObject, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseObjectService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseObject.label', default: 'CourseObject'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
