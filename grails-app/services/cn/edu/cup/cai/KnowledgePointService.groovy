package cn.edu.cup.cai

import grails.gorm.services.Service

@Service(KnowledgePoint)
interface KnowledgePointService {

    KnowledgePoint get(Serializable id)

    List<KnowledgePoint> list(Map args)

    Long count()

    void delete(Serializable id)

    KnowledgePoint save(KnowledgePoint knowledgePoint)

}