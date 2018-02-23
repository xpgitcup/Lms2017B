package cn.edu.cup.system

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class InitService {

    def dataSource
    def excelPoiService

    //加载数据库初始化脚本
    def loadScripts(String dir) {
        def File sf = new File(dir)
        println "load scripts ${dir}"
        if (sf.exists()) {
            if (sf) {
                sf.eachFile { f ->
                    if (f.isFile()) {
                        executeScript(f)
                    }
                }
            }
        }
    }

    //执行附加脚本
    def executeScript(File sf) {
        //def File sf = new File(fileName)
        println "init - ${sf}"
        if (sf) {
            def db
            def sql = sf.text
            db = new groovy.sql.Sql(dataSource)
            //println "init - ${sql}"
            def lines = sql.split(";")
            lines.each() { it ->
                //println "line: ${it}"
                it = it.trim()
                if (!it.isEmpty()) {
                    db.executeUpdate(it)
                }
            }
        }
    }

    /*
    * 初始化系统数据
    * */

    def initSystemData(domains, webRootDir) {
        println("初始化系统数据......")
        initSystemUsers()
        initSystemMenuItems(domains, webRootDir)
    }

    /*
    * 初始化系统菜单
    * */

    def initSystemMenuItems(domains, webRootDir) {
        if (SystemMenu.count() < 1) {
            initDomainMenuItems(domains)
            def userConfigFileName = "${webRootDir}/config/system_menu_config.xlsx"
            initUserMenuItems(userConfigFileName)
        }
    }

    /*
    * 初始化用户菜单项
    * */

    private void initUserMenuItems(String userConfigFileName) {
        excelPoiService.openExcelFile(userConfigFileName)
        def targetHead = excelPoiService.getSheetHeadAt(0)
        def head = ["menu_context", "menu_description", "menu_action", "menu_order", "role_attribute", "up_menu_item_id"]
        def dif = excelPoiService.checkHead(head, targetHead)
        if (dif.isEmpty()) {
            println("开始导入菜单...")
            def data = excelPoiService.getData(0)
            data.each { row->
                row.each { cell->
                    print("${cell},")
                }
                println()

                if (SystemMenu.findByMenuContext(row[0])) {
                    println("${row[0]}存在了.")
                } else {
                    def upMenuItem = null
                    if (!row[5].equals("")) {
                        upMenuItem = SystemMenu.findByMenuContext(row[5])
                    }
                    def menuItem = new SystemMenu(
                            menuContext: row[0],
                            menuDescription: row[1],
                            menuAction: row[2],
                            menuOrder: row[3],
                            roleAttribute: row[4],
                            upMenuItem: upMenuItem
                    )
                    menuItem.save(true)
                    println("增加：${row[0]}.")
                }
            }
        } else {
            println("标题不对应...")
            dif.each { e->
                println("${e}")
            }
        }
        excelPoiService.statusOfexcelFile.each { e->
            println(e)
        }
    }

    /*
    * 初始化有关域类的基础菜单项
    * */

    private void initDomainMenuItems(domains) {
        def m0 = new SystemMenu(
                menuContext: "底层管理",
                menuAction: "#",
                menuDescription: "对系统的菜单结构进行底层维护",
                upMenuItem: null,
                roleAttribute: "底层管理",
                menuOrder: 100
        )
        m0.save(true)
        //----------------------------------------------------------------------------------------------------------
        //创建正对各个域类控制器的菜单
        domains.sort()
        domains.each() { e ->
            def m01 = new SystemMenu(
                    menuContext: "${e.name}",
                    menuAction: "${e.name}/index",
                    menuDescription: "对${e.name}属性进行维护",
                    upMenuItem: m0,
                    roleAttribute: "底层管理",
                    menuOrder: 0
            )
            m01.save(true)
        }
        def m011 = new SystemMenu(
                menuContext: "系统状态",
                menuAction: "Operation4SystemStatus",
                menuDescription: "显示当前的系统状态",
                upMenuItem: m0,
                roleAttribute: "底层管理",
                menuOrder: 0
        )
        m011.save(true)
    }

    /**
     * 初始化用户数据
     **/
    def initSystemUsers() {
        def roles = ["底层管理", "系统维护", "公共服务", "教师服务", "学生服务"]
        //def json = JSON.toJSONString(roles)
        if (SystemUser.count() < 1) {
            newUser("李晓平", "3764", roles)
            newUser("宫敬", "2156", roles)
            newUser("吴海浩", "3181", roles)

            newUser("洪炳沅", "527527", roles)
            newUser("闵元", "519519", roles)
            newUser("苏越", "519519", roles)
            newUser("李愚", "519519", roles)
            newUser("周艳红", "519519", roles)
            newUser("万洋洋", "519519", roles)
            newUser("韦宝成", "519519", roles)
            newUser("王茀玺", "519519", roles)
            newUser("金浩", "519519", roles)
        }
    }

    private void newUser(userName, password, attribute) {
        def u5 = new SystemUser(userName: userName, password: password, roleAttribute: attribute)
        u5.save(true)
    }

    /**
     * 填充测试数据
     */
    def fillSamples() {
        println("填充测试数据......")
        //用户
        fillSampleUsers()
        //属性
        fileSampleAttributes()
        //菜单
        fillSampleMenus()
        //对话
        fillSampleChat()
        //数据字典
        //fillSampleDataKey()
        //程序标题
        fillSampleTitle()
    }

    def fillSampleTitle() {
        println("初始化系统标题......")
        if (SystemTitle.count() < 1) {
            def systemTitle = new SystemTitle(
                    applicationTitle: "Lms2017B 实验室管理系统",
                    applicationLogo: "cuplogoA.png",
                    applicationLayout: "mainEasyUI"
            )
            systemTitle.save(true)
            //----------------------------------------------------------------------------------------------------------
            if (SystemSponser.countBySystemTitle(systemTitle) < 1) {
                newSponser(systemTitle, "中国石油大学", "cuplogoA.png")
            }
            //----------------------------------------------------------------------------------------------------------
            if (SystemCarousel.countBySystemTitle(systemTitle) < 1) {
                newCarousel(systemTitle, "课题组", "课题组.jpg")
                newCarousel(systemTitle, "多相流", "多相流.png")
                newCarousel(systemTitle, "抽油机", "u68.jpg")
            }
        }
    }

    private void newSponser(systemTitle, name, logo) {
        def ns = new SystemSponser(systemTitle: systemTitle, name: name, logo: logo)
        ns.save(true)
    }

    private void newCarousel(systemTitle, name, png) {
        def nc = new SystemCarousel(systemTitle: systemTitle, name: name, imageName: png)
        nc.save(true)
    }

    private void fillSampleDataKey() {
        println("测试数据字典的数据...")
        def dw = ["kg", "m", "s", "MPa", "m^3/s", "kg/s"]
    }

    private void fillSampleChat() {
        for (int i = 0; i < 20; i++) {
            def c = new SystemChat(
                    speaker: "李晓平",
                    speakTo: "张${i}李${i + 1}",
                    message: " I speak to 张${i}李${i + 1} that ${i * i}",
                    startTime: new Date()
            )
            c.save(true)
            def d = new SystemChat(
                    speakTo: "李晓平",
                    speaker: "张${i}李${i + 1}",
                    message: "张${i}李${i + 1} tell me ${i * i}",
                    startTime: new Date()
            )
            d.save(true)
        }
    }

    private void fillSampleMenus() {
        if (SystemMenu.count() < 1) {
            println("填充测试性的菜单数据......")

            println("${GrailsApplication.simpleName}")
            println("${GrailsApplication.APPLICATION_ID}")
            println("${Application.properties.name}")
            //println("${Application.metaPropertyValues}")

            for (int i = 0; i < 10; i++) {
                def mm = new SystemMenu(
                        menuContext: "菜单${i}",
                        menuAction: "动作${i}",
                        menuDescription: "描述：${i}",
                        upMenuItem: null,
                        menuOrder: i
                )
                mm.save(true)

                for (int j = 0; j < i; j++) {
                    def mmn = new SystemMenu(
                            menuContext: "菜单${i}.${j}",
                            menuAction: "动作${i}.${j}",
                            menuDescription: "描述：${i}.${j}",
                            upMenuItem: mm,
                            menuOrder: j
                    )
                    mmn.save(true)
                }
            }
        }
    }

    private void fileSampleAttributes() {
        if (SystemAttribute.count() < 1) {
            println("测试性的属性...")
            def attributeA = new SystemAttribute(name: "系统操作权限")
            attributeA.save(true)
            for (int i = 0; i < 10; i++) {
                def aa = new SystemAttribute(name: "权限${i}", upAttribute: attributeA)
                aa.save(true)
            }
            def attributeB = new SystemAttribute(name: "读取权限")
            attributeB.save(true)
            for (int i = 0; i < 10; i++) {
                def aa = new SystemAttribute(name: "读取权限${i}", upAttribute: attributeB)
                aa.save(true)
            }
        }
    }

    private void fillSampleUsers() {
        println("测试性的用户...")
        for (int i = 0; i < 20; i++) {
            def u = new SystemUser(userName: "张${i}李${i + 1}", password: "1")
            u.save(true)
        }
    }

}
