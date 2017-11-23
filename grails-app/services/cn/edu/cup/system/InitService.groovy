package cn.edu.cup.system

import com.alibaba.fastjson.JSON
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class InitService {

    def dataSource

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
    def initSystemData(domains) {
        println("初始化系统数据......")
        initSystemUsers()
        initSystemMenuItems(domains)
    }

    /*
    * 初始化系统菜单
    * */
    def initSystemMenuItems(domains) {
        if (SystemMenu.count() < 1) {
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
            //----------------------------------------------------------------------------------------------------------
            def m1 = new SystemMenu(
                    menuContext: "系统维护",
                    menuAction: "#",
                    menuDescription: "对系统的菜单结构进行用户友好的维护",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m1.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m11 = new SystemMenu(
                    menuContext: "属性维护",
                    menuAction: "Operation4SystemAttribute/index",
                    menuDescription: "对系统的用户属性进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m11.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m12 = new SystemMenu(
                    menuContext: "用户维护",
                    menuAction: "Operation4SystemUser/index",
                    menuDescription: "对系统的用户进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m12.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m13 = new SystemMenu(
                    menuContext: "菜单维护",
                    menuAction: "Operation4SystemMenu/index",
                    menuDescription: "对系统的菜单用户进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m13.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m14 = new SystemMenu(
                    menuContext: "日志维护",
                    menuAction: "Operation4SystemLog/index",
                    menuDescription: "对系统的日志进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m14.save(true)
            //----------------------------------------------------------------------------------------------------------
            /*
            def m15 = new SystemMenu(
                    menuContext: "权限管理",
                    menuAction: "Operation4SystemUser/index4Attribute",
                    menuDescription: "对系统的日志进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m15.save(true)
            */
            //----------------------------------------------------------------------------------------------------------
            def m2 = new SystemMenu(
                    menuContext: "公共服务",
                    menuAction: "#",
                    menuDescription: "对所有用户开放的功能",
                    upMenuItem: null,
                    roleAttribute: "公共服务",
                    menuOrder: 0
            )
            m2.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m21 = new SystemMenu(
                    menuContext: "社区沟通",
                    menuAction: "Operation4SystemChat/index",
                    menuDescription: "与系统中的用户对话",
                    upMenuItem: m2,
                    menuOrder: 0
            )
            m21.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m3 = new SystemMenu(
                    menuContext: "基础数据",
                    menuAction: "#",
                    menuDescription: "维护数据字典+物理单位",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m3.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m33 = new SystemMenu(
                    menuContext: "单位维护",
                    menuAction: "Operation4Physical/index",
                    menuDescription: "物理单位维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m33.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m34 = new SystemMenu(
                    menuContext: "用户库维护",
                    menuAction: "operation4UserLibrary/index",
                    menuDescription: "维护用户代码库",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m34.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m35 = new SystemMenu(
                    menuContext: "数据字典A维护",
                    menuAction: "operation4Dictionary/index",
                    menuDescription: "维护数据字典（新）",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m35.save(true)
            //----------------------------------------------------------------------------------------------------------
            /*
            def m36 = new SystemMenu(
                    menuContext: "数据A维护",
                    menuAction: "operation4DataA/index",
                    menuDescription: "维护数据字典（新）",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m36.save(true)
            */
            //----------------------------------------------------------------------------------------------------------
            def m37 = new SystemMenu(
                    menuContext: "数据B维护",
                    menuAction: "operation4DataB/index",
                    menuDescription: "维护数据字典（新）",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m37.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m38 = new SystemMenu(
                    menuContext: "通用数据界面维护",
                    menuAction: "operation4CommonUIA/index",
                    menuDescription: "维护通用数据操作界面",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m38.save(true)
            //----------------------------------------------------------------------------------------------------------

            //----------------------------------------------------------------------------------------------------------
            def m4 = new SystemMenu(
                    menuContext: "教师服务",
                    menuAction: "#",
                    menuDescription: "需要教师操作的功能",
                    upMenuItem: null,
                    roleAttribute: "教师服务",
                    menuOrder: 0
            )
            m4.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m41 = new SystemMenu(
                    menuContext: "项目管理",
                    menuAction: "Operation4Teacher/index",
                    menuDescription: "完成项目相关的各种操作...",
                    upMenuItem: m4,
                    menuOrder: 0
            )
            m41.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m42 = new SystemMenu(
                    menuContext: "人员管理",
                    menuAction: "Operation4Teacher/index4Staff",
                    menuDescription: "完成项目相关的各种操作...",
                    upMenuItem: m4,
                    menuOrder: 0
            )
            m42.save(true)

            //----------------------------------------------------------------------------------------------------------
            def m5 = new SystemMenu(
                    menuContext: "学生服务",
                    menuAction: "#",
                    menuDescription: "需要学生操作的功能",
                    upMenuItem: null,
                    roleAttribute: "学生服务",
                    menuOrder: 0
            )
            m5.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m51 = new SystemMenu(
                    menuContext: "工作日志",
                    menuAction: "Operation4Student/index",
                    menuDescription: "录入工作日志的各种操作...",
                    upMenuItem: m5,
                    menuOrder: 0
            )
            m51.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m52 = new SystemMenu(
                    menuContext: "人员管理",
                    menuAction: "Operation4Teacher/index4Staff",
                    menuDescription: "完成项目相关的各种操作...",
                    upMenuItem: m5,
                    menuOrder: 0
            )
            m52.save(true)
        }
    }

    /**
     * 初始化用户数据
     **/
    def initSystemUsers() {
        def roles = ["底层管理","系统维护","公共服务","教师服务","学生服务"]
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
        if (SystemTitle.count()<1) {
            def systemTitle = new SystemTitle(
                    applicationTitle: "Lms2017B 实验室管理系统",
                    applicationLogo: "cuplogoA.png",
                    applicationLayout: "mainEasyUI"
            )
            systemTitle.save(true)
            //----------------------------------------------------------------------------------------------------------
            if (SystemSponser.countBySystemTitle(systemTitle)<1) {
                newSponser(systemTitle, "中国石油大学", "cuplogoA.png")
            }
            //----------------------------------------------------------------------------------------------------------
            if (SystemCarousel.countBySystemTitle(systemTitle)<1) {
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
