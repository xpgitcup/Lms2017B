package cn.edu.cup.system

import grails.gorm.transactions.Transactional

@Transactional
class SystemLogImpService {

    @Transactional(readOnly = false)
    def deleteBefore(aDate) {
        SystemLog.executeUpdate("delete SystemLog a where a.actionDate < :d", [d: aDate])
    }

    @Transactional(readOnly = false)
    def recordLog(session, request, params) {
        def ps = params
        if (ps.password) {
            ps.password = "********"
        }
        SystemLog log = new SystemLog();
        log.sessionId = session.getId();
        log.actionDate = new Date();
        log.userName = session.systemUser.userName;
        log.hostIP = request.getRemoteAddr();
        def pps = "${ps}"
        if (pps.length()>100) {
            log.doing = pps.substring(0, 100)
        } else {
            log.doing = pps
        }
        log.save();
    }

}
