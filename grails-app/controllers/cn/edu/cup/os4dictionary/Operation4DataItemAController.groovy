package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED

class Operation4DataItemAController {

    def commonService

    /*
    * 统计记录个数
    * */

    def countDataItemA() {
        //def count = DataItemA.count()    //这是必须调整的
        def count = DataItemA.countByUpDataItemIsNull()
        //println("统计结果：${count}")
        if (session.currentDataKeyA) {
            count = DataItemA.countByDataKeyAAndUpDataItemIsNull(session.currentDataKey)
        }
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * DataItemA
    * */

    def listDataItemA() {
        //def dataItemAList = DataItemA.list(params)
        def dataItemAList = DataItemA.findAllByUpDataItemIsNull(params)
        if (session.currentDataKeyA) {
            dataItemAList = DataItemA.findAllByDataKeyAAndUpDataItemIsNull(session.currentDataKeyA, params)
        }
        if (request.xhr) {
            render(template: 'listDataItemA', model: [dataItemAList: dataItemAList])
        } else {
            respond dataItemAList
        }
    }

    /*
    * 创建对象
    * */

    def createDataItemA(DataKeyA dataKeyA) {
        def newDataItemA = new DataItemA(dataKeyA: dataKeyA)
        if (dataKeyA.subDataKeys) {
            def newSubItems = []
            dataKeyA.subDataKeys.each { e ->
                def subItem = new DataItemA(dataKeyA: e, upDataItem: newDataItemA)
                newSubItems.add(subItem)
            }
            newDataItemA.subDataItems = newSubItems
        }
        if (request.xhr) {
            render(template: 'createDataItemA', model: [dataItemA: newDataItemA])
        } else {
            respond newDataItemA
        }
    }

    /*
    * 删除对象
    * */

    @Transactional
    def deleteDataItemA(DataItemA dataItemA) {
        dataItemA.delete()
        redirect(controller: 'operation4DataA', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def saveDataItemA(DataItemA dataItemA) {

        println("${params}")

        if (dataItemA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItemA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            //respond dataItemA.errors, view:'create'
            //render(template: 'createDataItemA', model: [dataItemA: dataItemA])

            flash.message = dataItemA.errors
            redirect(controller: 'operation4DataA', action: 'index')
            return
        }

        dataItemA.save(flush: true)

        def destDir = servletContext.getRealPath("/") + "uploads"
        def uploadFileNames = params.uploadFile
        def uploadFileIndex = params.uploadFileIndex
        def uploadFileDataKeyId = params.uploadFileDataKeyId
        def uploadFilePath = params.uploadFilePath
        if (uploadFileNames.getClass().array) {
            println("不止一个文件...")
            uploadFileNames.eachWithIndex { e, i ->
                //def k = uploadFileIndex[i]
                params.destDir = "${destDir}/${uploadFileDataKeyId[i]}/${uploadFilePath[i]}"
                params.uploadedFile = e
                println(destDir)
                def sf = commonService.upload(params)
                println("上传${sf}成功...")
            }
        } else {
            println("不是数组，只有一个文件...")
            params.destDir = "${destDir}/${uploadFileDataKeyId}/${uploadFilePath}"
            println(destDir)
            def sf = commonService.upload(params)
            println("上传${sf}成功...")
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                //redirect dataItemA
                redirect(controller: 'operation4DataA', action: 'index')
            }
            '*' { respond dataItemA, [status: CREATED] }
        }
    }

    @Transactional
    def updateDataItemA(DataItemA dataItemA) {
        println("准备更新：${dataItemA}")
        dataItemA.save flush: true
        redirect(controller: 'operation4DataA', action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editDataItemA(DataItemA dataItemA) {
        if (request.xhr) {
            render(template: 'editDataItemA', model: [dataItemA: dataItemA])
        } else {
            respond dataItemA
        }
    }

    def index() {}
}
