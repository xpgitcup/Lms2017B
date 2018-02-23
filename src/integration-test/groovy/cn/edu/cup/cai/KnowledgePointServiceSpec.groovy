package cn.edu.cup.cai

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class KnowledgePointServiceSpec extends Specification {

    KnowledgePointService knowledgePointService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new KnowledgePoint(...).save(flush: true, failOnError: true)
        //new KnowledgePoint(...).save(flush: true, failOnError: true)
        //KnowledgePoint knowledgePoint = new KnowledgePoint(...).save(flush: true, failOnError: true)
        //new KnowledgePoint(...).save(flush: true, failOnError: true)
        //new KnowledgePoint(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //knowledgePoint.id
    }

    void "test get"() {
        setupData()

        expect:
        knowledgePointService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<KnowledgePoint> knowledgePointList = knowledgePointService.list(max: 2, offset: 2)

        then:
        knowledgePointList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        knowledgePointService.count() == 5
    }

    void "test delete"() {
        Long knowledgePointId = setupData()

        expect:
        knowledgePointService.count() == 5

        when:
        knowledgePointService.delete(knowledgePointId)
        sessionFactory.currentSession.flush()

        then:
        knowledgePointService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        KnowledgePoint knowledgePoint = new KnowledgePoint()
        knowledgePointService.save(knowledgePoint)

        then:
        knowledgePoint.id != null
    }
}
