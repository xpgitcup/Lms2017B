package cn.edu.cup.cai

import grails.gorm.services.Service

@Service(TestPaper)
interface TestPaperService {

    TestPaper get(Serializable id)

    List<TestPaper> list(Map args)

    Long count()

    void delete(Serializable id)

    TestPaper save(TestPaper testPaper)

}