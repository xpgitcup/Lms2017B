package cn.edu.cup.operation4Cai

import cn.edu.cup.cai.Question
import grails.converters.JSON
import grails.validation.ValidationException
import org.apache.poi.hwpf.HWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.extractor.XWPFWordExtractor

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4QuestionController {

    def commonService
    def questionService

    /*
    * 导入试卷
    * */

    def showQuestionFromFile() {
        println("${params}")
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        def data = loadFromFile(sf)
        redirect(controller: 'operation4QuestionType', action: 'index', model: [data: data])
    }

    /*
    * 从文档中读入数据
    * */

    def loadFromFile(file) {
        def data
        def word
        word = openWordDocument(file)

        if (word.document) {
            switch (word.type) {
                case 'doc':
                    def range = word.document.getRange()
                    println("结果：")
                    println("${range}")
                    break;
                case 'docx':
                    def extractor = new XWPFWordExtractor(word.document);
                    String doc1 = extractor.getText();
                    println(doc1);
                    break
            }
        }
        return data
    }

    private def openWordDocument(file) {

        def word = [type: '', document: null]
        def fileName = file.name
        def inputStream = new FileInputStream(file)
        if (fileName.endsWith('.doc')) {
            println("一般word文档.")
            word.type = 'doc'
            word.document = new HWPFDocument(inputStream)

        } else {
            if (fileName.endsWith('.docx')) {
                println("高级word文档.")
                word.type = 'docx'
                word.document = new XWPFDocument(inputStream)
            } else {
                println("不是word文档.")
            }
        }
        return word
    }

    /*
    * 准备导入
    * */

    def prepareToImport() {
        def course = null

        def view = 'prepare2Import'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [course: course])
        } else {
            respond course
        }
    }

    /*
    * 更新
    * */

    def update(Question question) {
        if (question == null) {
            notFound()
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                //redirect question
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { respond question, [status: OK] }
        }
    }

    /*
    * 编辑
    * */

    def edit(Long id) {
        def question = questionService.get(id)

        def view = 'edit'
        if (params.view) {
            view = "${params.view}"
        }

        if (request.xhr) {
            render(template: view, model: [question: question])
        } else {
            respond question
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

        questionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
                //redirect action:"index", method:"GET"
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /*
    * 显示
    * */

    def show(Long id) {

        def question = questionService.get(id)
        def view = 'show'

        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [question: question])
        } else {
            respond question
        }
    }

    /*
    * 保存
    * */

    def save(Question question) {
        if (question == null) {
            notFound()
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                //redirect question
                redirect(action: "index", controller: "operation4QuestionType")
            }
            '*' { respond question, [status: CREATED] }
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
        def question = new Question(params)
        if (request.xhr) {
            render(template: view, model: [question: question])
        } else {
            respond question
        }
    }

    /*
    * 列表
    * */

    def list() {
        def questionList = Question.list(params)

        def view = "list"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [questionList: questionList])
        } else {
            respond questionList
        }
    }

    /*
    * 统计
    * */

    def count() {
        def count = Question.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
