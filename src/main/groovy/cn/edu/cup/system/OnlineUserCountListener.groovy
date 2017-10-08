package cn.edu.cup.system

import javax.servlet.ServletContext
import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSession
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * Created by LiXiaoping on 2017/3/18.
 */
@WebListener
class OnlineUserCountListener  implements HttpSessionListener{
    @Override
    void sessionCreated(HttpSessionEvent se) {
        Date now = new Date()
        System.out.printf("创建session at %s......\n", now.toString());
        //创建唯一的用户登记表
        HttpSession s = se.getSession();
        if (s != null) {
            ServletContext ctx = s.getServletContext();
            if (ctx != null) {
                Map serviceMap = (Map) ctx.getAttribute("systemUserList");
                if (!serviceMap) {
                    ctx["systemUserList"] = new HashMap()
                }
            }
        }
    }

    @Override
    void sessionDestroyed(HttpSessionEvent se) {
        HttpSession s = se.getSession();
        Date now = new Date()
        println("${s} 会话撤销 at ${now.toString()}...")
        ServletContext ctx = s.getServletContext();
        if (ctx != null) {
            Map serviceMap = (Map) ctx.getAttribute("systemUserList");
            if (serviceMap != null) {
                SystemUser user = (SystemUser) s.getAttribute("systemUser");
                if (user != null) {
                    serviceMap.remove(user.userName);
                    String userName = user.getUserName();
                    System.out.printf("remove %s.\n", userName);
                }
            }
        }
    }

}
