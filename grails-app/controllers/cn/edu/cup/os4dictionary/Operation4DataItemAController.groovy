package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonOutput

import static org.springframework.http.HttpStatus.CREATED

@Transactional(readOnly = true)
class Operation4DataItemAController {

    def commonService
    def dataService

    /*
    * 过滤条件下的统计
    * */

    def countDataItemA4Filter() {
        println("countDataItemA4Filter: ${params}")
        def count
        def currentDataKeyA
        def currentDataValue
        if (params.dataKey) {
            currentDataKeyA = DataKeyA.get(Integer.parseInt(params.dataKey))
            if (params.dataValue) {
                currentDataValue = params.dataValue
                count = DataItemA.countByDataKeyAAndDataValue(currentDataKeyA, currentDataValue)
                println("数据项=${count}--------------")
            } else {
                count = DataItemA.countByDataKeyA(currentDataKeyA)
            }
        } else {
            count = 0//DataItemA.countByUpDataItemIsNull()
        }
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 过滤条件下的数据显示
    * */

    def listDataItemA4FilterWithView() {
        println("listDataItemA4FilterWithView: ${params}")
        def dataItemAList = []
        def currentDataKeyA
        def currentDataValue
        if (params.dataKey) {
            currentDataKeyA = DataKeyA.get(params.dataKey)
            if (params.dataValue) {
                currentDataValue = params.dataValue
                def temp = DataItemA.findAllByDataKeyAAndDataValue(currentDataKeyA, currentDataValue, params)
                temp.each { e ->
                    dataItemAList.add(e.upDataItem)
                }
            } else {
                dataItemAList = DataItemA.findAllByDataKeyAAndUpDataItemIsNull(currentDataKeyA, params)
            }
        } else {
            //dataItemAList = DataItemA.findAllByUpDataItemIsNull(params)
        }
        // 处理视图
        def view = "listDataItemA"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [dataItemAList: dataItemAList])
        } else {
            respond dataItemAList
        }
    }

    /*
    * 统计特定的dataItem
    * */

    def countDataItemA4DataKey() {
        def dataKey = DataKeyA.get(Integer.parseInt(params.dataKey))
        def dataValue = params.searchValue
        def count = DataItemA.countByDataKeyAAndDataValue(dataKey, dataValue)
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 搜索特定的dataItem
    * */

    def searchDataItemA() {
        println("搜索...${params}")
        def dataKey = DataKeyA.get(Integer.parseInt(params.dataKey))
        def dataValue = params.searchValue
        def items = DataItemA.findAllByDataKeyAAndDataValue(dataKey, dataValue, params)
        def dataItemAList = []
        items.each { e ->
            dataItemAList.add(e.upDataItem)
        }
        if (request.xhr) {
            render(template: 'listDataItemA', model: [dataItemAList: dataItemAList])
        } else {
            respond dataItemAList
        }
    }

    /*
    * 数据导出到Excel文件
    * */

    def exportToExcelFile(DataItemA dataItemA) {
        def path = servletContext.getRealPath("/") + "temp"
        def filename = dataService.exportToExcelFile(dataItemA, path)
        params.downLoadFileName = filename
        commonService.downLoadFile(params)
    }

    /*
    * 统计记录个数
    * */

    def countDataItemA() {
        //def count = DataItemA.count()    //这是必须调整的
        def count = DataItemA.countByUpDataItemIsNull()
        println("正常统计结果：${count}")
        if (session.currentDataKeyA) {
            count = DataItemA.countByDataKeyAAndUpDataItemIsNull(session.currentDataKey)
        }

        println("正常统计结果-${session.currentDataKey}--：${count}")
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

        def view = "listDataItemA"
        if (params.view) {
            view = "${params.view}"
        }
        if (request.xhr) {
            render(template: view, model: [dataItemAList: dataItemAList])
        } else {
            respond dataItemAList
        }
    }

    /*
    * 创建对象
    * */

    def createDataItemA(DataKeyA dataKeyA) {
        def newDataItemA = new DataItemA(dataKeyA: dataKeyA)
        def newSubItems = []
        dataKeyA.realSubDataKeys().each { e ->
            def subItem = new DataItemA(dataKeyA: e, upDataItem: newDataItemA)
            newSubItems.add(subItem)
        }
        newDataItemA.subDataItems = newSubItems
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

        //处理关键字索引
        dataItemA.dataValue = dataService.createIndex(dataItemA)

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
