package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(DataKeyA)
interface DataKeyAService {

    DataKeyA get(Serializable id)

    List<DataKeyA> list(Map args)

    Long count()

    void delete(Serializable id)

    DataKeyA save(DataKeyA dataKeyA)

}