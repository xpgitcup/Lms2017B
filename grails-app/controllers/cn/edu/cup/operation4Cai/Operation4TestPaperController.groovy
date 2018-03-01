package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.TestPaper
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4TestPaperController {

    def testPaperService

    /*
    * 更新
    * */

    def update(TestPaper testPaper) {
        if (testPaper == null) {
            notFound()
            return
        }

        try {
            testPaperService.save(testPaper)
        } catch (ValidationException e) {
            respond testPaper.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), testPaper.id])
                //redirect testPaper
                redirect(action: "index")
            }
            '*' { respond testPaper, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def testPaper = testPaperService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [testPaper: testPaper])
        } else {
            respond testPaper
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

        testPaperService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), id])
                //redirect action:"index", method:"GET"
                redirect(action: "index")
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /*
    * 显示
    * */

    def show(Long id) {

        def testPaper = testPaperService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [testPaper: testPaper])
        } else {
            respond testPaper
        }
    }

    /*
    * 保存
    * */

    def save(TestPaper testPaper) {
        if (testPaper == null) {
            notFound()
            return
        }

        try {
            testPaperService.save(testPaper)
        } catch (ValidationException e) {
            respond testPaper.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'testPaper.label', default: 'TestPaper'), testPaper.id])
                //redirect testPaper
                redirect(action: "index")
            }
            '*' { respond testPaper, [status: CREATED] }
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
        def testPaper = new TestPaper(params)
        if (request.xhr) {
            render(template: view, model: [testPaper: testPaper])
        } else {
            respond testPaper
        }
    }

    /*
    * 列表
    * */

    def list() {
        def testPaperList = TestPaper.list(params)

        def view = "list"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [testPaperList: testPaperList])
        } else {
            respond testPaperList
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = TestPaper.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
