package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.CommonUIA
import cn.edu.cup.dictionary.CommonUIAController
import cn.edu.cup.dictionary.CommonUIAService
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.OK

class Operation4CommonUIAController extends CommonUIAController{

    CommonUIAService commonUIAService

    /*
    * 更新
    * */

    def update(CommonUIA commonUIA) {
        if (commonUIA == null) {
            notFound()
            return
        }

        try {
            commonUIAService.save(commonUIA)
        } catch (ValidationException e) {
            respond commonUIA.errors, view: 'edit'
            return
        }

        def action = "index"
        def controller = params.controller

        if (params.newController) {
            controller = params.newController
        }

        if (params.newAction) {
            action = params.newAction
        }

        redirect(controller: controller, action: action)
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def commonUIA = commonUIAService.get(id)
        def result = [commonUIA: commonUIA]
        //处理视图
        def view = "editCommonUIA"
        if (params.view) {
            view = params.view
        }
        if (request.xhr) {
            render(template: view, model: result)
        } else {
            result
        }
    }

    /*
    * 删除
    * */

    def delete(Long id) {
        if (id) {
            commonUIAService.delete(id);
        }

        def action = "index"
        def controller = params.controller

        if (params.newController) {
            controller = params.newController
        }

        if (params.newAction) {
            action = params.newAction
        }

        redirect(controller: controller, action: action)
    }

    /*
    * 显示记录
    * */

    def show(CommonUIA commonUIA) {
        def result = [commonUIA: commonUIA]
        //处理视图
        def view = "showCommonUIA"
        if (params.view) {
            view = "${params.view}"
        }
        //返回结果
        if (request.xhr) {
            render(template: view, model: result)
        } else {
            result
        }
    }

    /*
    * 通用数据项的用户界面
    * */

    def displayDataKeyUI() {
        if (params.commonUIA) {
            def c = CommonUIA.get(params.commonUIA)
            session.commonUIA = c
        } else {
            session.removeAttribute("commonUIA")
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = CommonUIA.count()   //这是必须调整的
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 数据列表
    * */

    def list() {
        def commonUIAList = CommonUIA.list(params)
        def result = [commonUIAList: commonUIAList]
        //处理视图
        def view = "listCommonUIA"
        if (params.view) {
            view = "${params.view}"
        }
        //返回结果
        if (request.xhr) {
            render(template: view, model: result)
        } else {
            result
        }
    }

    /*
    * 常规的域类管理
    * */

    def index() {}

}
