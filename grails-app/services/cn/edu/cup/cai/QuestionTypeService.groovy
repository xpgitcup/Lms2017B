package cn.edu.cup.cai

import grails.gorm.services.Service

@Service(QuestionType)
interface QuestionTypeService {

    QuestionType get(Serializable id)

    List<QuestionType> list(Map args)

    Long count()

    void delete(Serializable id)

    QuestionType save(QuestionType questionType)

}