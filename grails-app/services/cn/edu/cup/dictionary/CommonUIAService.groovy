package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(CommonUIA)
interface CommonUIAService {

    CommonUIA get(Serializable id)

    List<CommonUIA> list(Map args)

    Long count()

    void delete(Serializable id)

    CommonUIA save(CommonUIA commonUIA)

}