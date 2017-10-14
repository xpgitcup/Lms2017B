package cn.edu.cup.dictionary

import grails.gorm.services.Service

//@Service(DataKeyA)        //在这里是不是就应该去掉这个主食了？
interface IDataKeyAService {

    DataKeyA get(Serializable id)

    List<DataKeyA> list(Map args)

    Long count()

    void delete(Serializable id)

    DataKeyA save(DataKeyA dataKeyA)

}