package cn.edu.cup.cai

import grails.gorm.services.Service

@Service(QuestionDocument)
interface QuestionDocumentService {

    QuestionDocument get(Serializable id)

    List<QuestionDocument> list(Map args)

    Long count()

    void delete(Serializable id)

    QuestionDocument save(QuestionDocument questionDocument)

}