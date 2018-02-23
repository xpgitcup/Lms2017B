package cn.edu.cup.cai

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TestPaperController {

    TestPaperService testPaperService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond testPaperService.list(params), model:[testPaperCount: testPaperService.count()]
    }

    def show(Long id) {
        respond testPaperService.get(id)
    }

    def create() {
        respond new TestPaper(params)
    }

    def save(TestPaper testPaper) {
        if (testPaper == null) {
            notFound()
            return
        }

        try {
            testPaperService.save(testPaper)
        } catch (ValidationException e) {
            respond testPaper.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), testPaper.id])
                redirect testPaper
            }
            '*' { respond testPaper, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond testPaperService.get(id)
    }

    def update(TestPaper testPaper) {
        if (testPaper == null) {
            notFound()
            return
        }

        try {
            testPaperService.save(testPaper)
        } catch (ValidationException e) {
            respond testPaper.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), testPaper.id])
                redirect testPaper
            }
            '*'{ respond testPaper, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        testPaperService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
