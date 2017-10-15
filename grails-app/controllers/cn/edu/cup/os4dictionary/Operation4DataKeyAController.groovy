package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.DataKeyA
import cn.edu.cup.dictionary.DataKeyAController
import cn.edu.cup.dictionary.JsFrame
import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

@Transactional(readOnly = true)
class Operation4DataKeyAController extends DataKeyAController{

    def treeViewService
    def commonService
    def excelService
    def dataService

    /*
    * 显示数据关键字的表头
    * */
    def showHeads(DataKeyA dataKeyA) {
        def model = [heads: dataKeyA.heads()]
        println("${dataKeyA.heads()}")
        if (request.xhr) {
            render(template: "showHeads", model: model)
        } else {
            model
        }
    }

    /*
    * 选择当前关键字
    * */
    def selectCurrentDataKeyA(DataKeyA dataKeyA) {
        println("选择${dataKeyA}")
        selectDataKeyA(dataKeyA)
        redirect(controller: 'operation4DataA', action: 'index')
    }

    private void selectDataKeyA(DataKeyA dataKeyA) {
        session.currentDataKeyA = dataKeyA
        session.currentDataDictionary = dataKeyA.dictionary
    }

    def clearCurrentDataKeyA() {
        session.removeAttribute("currentDataKeyA")
        redirect(controller: 'operation4DataA', action: 'index')
    }

    /*
    * 导入数据
    * */
    @Transactional
    def importFromExcelFile(DataKeyA dataKeyA) {
        //selectDataKeyA(dataKeyA)      //不能操作session
        println("${params}")
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        //def message = dataKeyA.importFromExcelFile(sf)
        def message = excelService.importDataFromExcelFile4DataKeyA(dataKeyA, sf)
        flash.message = message
        println("${flash}")
        redirect(controller: 'operation4DataA', action: 'index', model: [flush: flash])
    }

    /*
    * 下载模板
    * */
    def downloadTemplate(DataKeyA dataKeyA) {
        def path = servletContext.getRealPath("/") + "dictionary/templates"
        //def fileName = dataKeyA.createTemplate(path)
        //def fileName = excelService.createTemplate(dataKeyA, path)
        def fileName = dataService.createTemplate(dataKeyA, path)
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }

    /*
    * 统计数据模型的数量
    * */
    def countDataKeyA4DataModel() {
        def count = 0
        if (session.currentDataDictionary) {
            count = DataKeyA.countByDictionaryAndSubDataKeysIsNotEmpty(session.currentDataDictionary)
        } else {
            count = DataKeyA.countBySubDataKeysIsNotEmpty()
        }
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 对象列表--数据模型
    * */

    def listDataKeyA4DataModel() {
        def dataKeyAList// = DataKeyA.findAllBySubDataKeysIsNotEmpty(params)

        params.sort = 'orderNumber'

        if (session.currentDataDictionary) {
            //println("筛选...")
            dataKeyAList = DataKeyA.findAllBySubDataKeysIsNotEmptyAndDictionary(session.currentDataDictionary, params)
            //println("${session.currentDataDictionary}")
            //println("${dataKeyAList}")
        } else {
            dataKeyAList = DataKeyA.findAllBySubDataKeysIsNotEmpty(params)
        }

        if (request.xhr) {
            render(template: 'listDataKeyA', model: [dataKeyAList: dataKeyAList])
        } else {
            respond dataKeyAList
        }
    }

    /*
    * 删除对象
    * */
    @Transactional
    def deleteDataKeyA(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataKeyA.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect action:"index", method:"GET", controller: "operation4Dictionary"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    /*
    * 创建对象
    * */
    def createDataKeyA(DataKeyA dataKeyA) {
        def newDataKeyA = new DataKeyA(upDataKey: dataKeyA)
        //处理所属字典
        if (session.currentDataDictionary) {
            newDataKeyA.dictionary = session.currentDataDictionary
        }

        if (request.xhr) {
            render(template: 'createDataKeyA', model: [dataKeyA: newDataKeyA])
        } else {
            respond newDataKeyA
        }
    }

    /*
    * 保存对象
    * */
    @Transactional
    def saveDataKeyA(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyA.errors, view:'create'
            //下面的都不能用，上面的也不对
            //println("有错误...")
            //flash.message = "有错了..."
            //render(template: 'createDataKeyA', model: [dataKeyA: dataKeyA])
        }

        dataKeyA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect(controller: 'operation4Dictionary', action: 'index', model: [dataKeyA: dataKeyA])
            }
            '*' { respond dataKeyA, [status: CREATED] }
        }
    }

    @Transactional
    def updateDataKeyA(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyA.errors, view:'edit'
            return
        }

        dataKeyA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect(controller: 'operation4Dictionary', action: 'index', model: [dataKeyA: dataKeyA])
            }
            '*'{ respond dataKeyA, [status: OK] }
        }
    }

    /*
    * 编辑对象
    * */
    def editDataKeyA(DataKeyA dataKeyA) {
        if (request.xhr) {
            render(template: 'editDataKeyA', model: [dataKeyA: dataKeyA])
        } else {
            respond dataKeyA
        }
    }

    /*
    * 统计根属性
    * */
    def countDataKeyA() {
        def count = DataKeyA.countByUpDataKeyIsNull()    //这是必须调整的
        if (session.currentDataDictionary) {
            count = DataKeyA.countByDictionaryAndUpDataKeyIsNull(session.currentDataDictionary)
        }
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
        //return count //就是不行
    }

    /*
    * 获取当前id对应的对象
    * */
    def getDataKeyA(DataKeyA dataKeyA) {
        def theModel = [dataKeyA: dataKeyA]
        if (request.xhr) {
            render(template: "showDataKeyA", model:theModel)
        } else {
            theModel
        }
    }

    /*
    * 获取json格式的树形结构数据
    * 获取某个节点下的所有子节点
    * */
    def getDataKeyATree(DataKeyA dataKeyA) {
        def data = dataKeyA.subDataKeys
        params.context = "dataTag"
        params.subItems = "subDataKeys"
        params.attributes = "id"    //
        params.useMethod = true
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 获取json格式的树形结构数据
    * 获取根节点
    * */
    def getTreeDataKeyA() {
        def data
        def dataDictionary
        params.sort = 'orderNumber'
        if (session.currentDataDictionary) {
            dataDictionary = session.currentDataDictionary
            data = DataKeyA.findAllByDictionaryAndUpDataKeyIsNull(dataDictionary, params)
        } else {
            data = DataKeyA.findAllByUpDataKeyIsNull(params)     //这是必须调整的
        }
        //println("${data} ${dataDictionary}")
        params.context = "dataTag"
        params.subItems = "subDataKeys"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() { }
}
