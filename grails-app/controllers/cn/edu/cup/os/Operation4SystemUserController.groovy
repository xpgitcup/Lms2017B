package cn.edu.cup.os

import cn.edu.cup.system.SystemUser
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemUserController
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class Operation4SystemUserController extends SystemUserController {

    /*
    * 更新用户权限
    * */

    @Transactional
    def updateRoleAttribute() {
        def systemUser = SystemUser.get(params.id)
        def role = ""
        params.roleAttribute.each { e ->
            if (role.isEmpty()) {
                role += e
            } else {
                role += " "
                role += e
            }
        }
        systemUser.roleAttribute = role
        systemUser.save(true)
        print("${params}")
        print("${systemUser}")
        redirect(action: "index")
    }

    /*
    * 编辑用户权限
    * */

    def editRoleAttribute(SystemUser systemUser) {
        def view = "editRoleAttribute"
        if (params.view) {
            view = params.view
            println("使用模板：${view}")
        }

        def topMenu = SystemMenu.findAllByUpMenuItemIsNull()
        def roles = [:]
        topMenu.each { e ->
            def ss = e.roleAttribute.split()
            ss.each { s ->
                roles.put(s, false)
            }
        }

        def ss = systemUser.roleAttribute.split()
        ss.each { s ->
            roles.put(s, true)
        }

        def data = [systemUser: systemUser, roles: roles]

        if (request.xhr) {
            render(template: "${view}", model: data)
        } else {
            respond data
        }
    }

    /*
    * 统计带有过滤的统计
    * */

    def countSystemUserWithFilter() {
        def roleAttribute = params.roleAttribute
        def ppp = "%${roleAttribute}%"
        def q = SystemUser.createCriteria()
        def count = q.get {
            projections {
                count("roleAttribute")
                like("roleAttribute", ppp)
            }
        }
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 过滤用户属性
    * */

    def filter4SystemUser() {
        //println("${params}")
        def roleAttribute = params.roleAttribute
        def ppp = "%${roleAttribute}%"
        //println(p)
        def q = SystemUser.createCriteria()
        def systemUserList = q.list(params) {
            like("roleAttribute", ppp)
        }
        println("${systemUserList}")
        if (request.xhr) {
            render(template: 'listSystemUser', model: [systemUserList: systemUserList])
        } else {
            respond systemUserList
        }
    }

    /*
    * 检索用户名
    * */

    def findAllByUserName() {
        def userName = params.userName
        def systemUserList = SystemUser.findAllByUserName(userName)
        if (request.xhr) {
            render(template: 'listSystemUser', model: [systemUserList: systemUserList])
        } else {
            respond systemUserList
        }
    }

    /*
    * 列出对象
    * */

    def listSystemUser() {
        def view = "listSystemUser"
        if (params.view) {
            view = params.view
            println("使用模板：${view}")
        }
        def systemUserList = SystemUser.list(params)
        if (request.xhr) {
            render(template: "${view}", model: [systemUserList: systemUserList])
        } else {
            respond systemUserList
        }
    }

    /*
    * 创建对象
    * */

    def createSystemUser(SystemUser systemUser) {
        def newSystemUser = new SystemUser()
        if (request.xhr) {
            render(template: 'editSystemUser', model: [systemUser: newSystemUser])
        } else {
            respond newSystemUser
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateSystemUser(SystemUser systemUser) {
        println("准备更新：${systemUser}")
        systemUser.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editSystemUser(SystemUser systemUser) {
        if (request.xhr) {
            render(template: 'editSystemUser', model: [systemUser: systemUser])
        } else {
            respond SystemUser
        }
    }

    /*
    * 统计根属性
    * */

    def countSystemUser() {
        def count = SystemUser.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 获取当前id对应的对象
    * */

    def getSystemUser(SystemUser systemUser) {
        def theModel = [systemUser: systemUser]
        println("${systemUser}")
        if (request.xhr) {
            render(template: "showSystemUser", model: theModel)
        } else {
            theModel
        }
    }

    def index() {}
}
