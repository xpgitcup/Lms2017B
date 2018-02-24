package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.Course

class Operation4CourseController {

    def courseService

    /*
    * 列表
    * */

    def list() {
        def courseList = Course.list(params)

        def view = "listCourse"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [courseList: courseList])
        } else {
            respond courseList
        }
    }

    /*
    * 统计
    * */

    def count() {
        return Course.count()
    }

    def index() {}
}
