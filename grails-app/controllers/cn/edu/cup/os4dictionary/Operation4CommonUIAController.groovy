package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.CommonUIA
import grails.converters.JSON

class Operation4CommonUIAController {

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
