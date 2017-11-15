package cn.edu.cup.dictionary

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DataKey4MenuController {

    DataKey4MenuService dataKey4MenuService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dataKey4MenuService.list(params), model:[dataKey4MenuCount: dataKey4MenuService.count()]
    }

    def show(Long id) {
        respond dataKey4MenuService.get(id)
    }

    def create() {
        respond new DataKey4Menu(params)
    }

    def save(DataKey4Menu dataKey4Menu) {
        if (dataKey4Menu == null) {
            notFound()
            return
        }

        try {
            dataKey4MenuService.save(dataKey4Menu)
        } catch (ValidationException e) {
            respond dataKey4Menu.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKey4Menu.label', default: 'DataKey4Menu'), dataKey4Menu.id])
                redirect dataKey4Menu
            }
            '*' { respond dataKey4Menu, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dataKey4MenuService.get(id)
    }

    def update(DataKey4Menu dataKey4Menu) {
        if (dataKey4Menu == null) {
            notFound()
            return
        }

        try {
            dataKey4MenuService.save(dataKey4Menu)
        } catch (ValidationException e) {
            respond dataKey4Menu.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataKey4Menu.label', default: 'DataKey4Menu'), dataKey4Menu.id])
                redirect dataKey4Menu
            }
            '*'{ respond dataKey4Menu, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dataKey4MenuService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKey4Menu.label', default: 'DataKey4Menu'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataKey4Menu.label', default: 'DataKey4Menu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
