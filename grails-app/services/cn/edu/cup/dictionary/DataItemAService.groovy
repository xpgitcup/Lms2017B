package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(DataItemA)
interface DataItemAService {

    DataItemA get(Serializable id)

    List<DataItemA> list(Map args)

    Long count()

    void delete(Serializable id)

    DataItemA save(DataItemA dataItemA)

}