package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.KnowledgePoint
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4KnowledgePointController {

    def knowledgePointService

    /*
    * 更新
    * */

    def update(KnowledgePoint knowledgePoint) {
        if (knowledgePoint == null) {
            notFound()
            return
        }

        try {
            knowledgePointService.save(knowledgePoint)
        } catch (ValidationException e) {
            respond knowledgePoint.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), knowledgePoint.id])
                //redirect knowledgePoint
                redirect(action: "index", controller: "operation4Course")
            }
            '*' { respond knowledgePoint, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def knowledgePoint = knowledgePointService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [knowledgePoint: knowledgePoint])
        } else {
            respond knowledgePoint
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

        knowledgePointService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), id])
                //redirect action:"index", method:"GET"
                redirect(action: "index", controller: "operation4Course")
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /*
    * 显示
    * */

    def show(Long id) {

        def knowledgePoint = knowledgePointService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [knowledgePoint: knowledgePoint])
        } else {
            respond knowledgePoint
        }
    }

    /*
    * 保存
    * */

    def save(KnowledgePoint knowledgePoint) {
        if (knowledgePoint == null) {
            notFound()
            return
        }

        try {
            knowledgePointService.save(knowledgePoint)
        } catch (ValidationException e) {
            respond knowledgePoint.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'knowledgePoint.label', default: 'KnowledgePoint'), knowledgePoint.id])
                //redirect knowledgePoint
                redirect(action: "index", controller: "operation4Course")
            }
            '*' { respond knowledgePoint, [status: CREATED] }
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
        def knowledgePoint = new KnowledgePoint(params)
        if (request.xhr) {
            render(template: view, model: [knowledgePoint: knowledgePoint])
        } else {
            respond knowledgePoint
        }
    }

    /*
    * 列表
    * */

    def list() {
        def knowledgePointList = KnowledgePoint.list(params)

        def view = "list"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [knowledgePointList: knowledgePointList])
        } else {
            respond knowledgePointList
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = KnowledgePoint.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
