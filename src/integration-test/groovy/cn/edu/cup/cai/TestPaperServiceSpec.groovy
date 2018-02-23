package cn.edu.cup.cai

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TestPaperServiceSpec extends Specification {

    TestPaperService testPaperService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TestPaper(...).save(flush: true, failOnError: true)
        //new TestPaper(...).save(flush: true, failOnError: true)
        //TestPaper testPaper = new TestPaper(...).save(flush: true, failOnError: true)
        //new TestPaper(...).save(flush: true, failOnError: true)
        //new TestPaper(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //testPaper.id
    }

    void "test get"() {
        setupData()

        expect:
        testPaperService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TestPaper> testPaperList = testPaperService.list(max: 2, offset: 2)

        then:
        testPaperList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        testPaperService.count() == 5
    }

    void "test delete"() {
        Long testPaperId = setupData()

        expect:
        testPaperService.count() == 5

        when:
        testPaperService.delete(testPaperId)
        sessionFactory.currentSession.flush()

        then:
        testPaperService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TestPaper testPaper = new TestPaper()
        testPaperService.save(testPaper)

        then:
        testPaper.id != null
    }
}
