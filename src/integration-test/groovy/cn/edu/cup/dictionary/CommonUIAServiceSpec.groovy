package cn.edu.cup.dictionary

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CommonUIAServiceSpec extends Specification {

    CommonUIAService commonUIAService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CommonUIA(...).save(flush: true, failOnError: true)
        //new CommonUIA(...).save(flush: true, failOnError: true)
        //CommonUIA commonUIA = new CommonUIA(...).save(flush: true, failOnError: true)
        //new CommonUIA(...).save(flush: true, failOnError: true)
        //new CommonUIA(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //commonUIA.id
    }

    void "test get"() {
        setupData()

        expect:
        commonUIAService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CommonUIA> commonUIAList = commonUIAService.list(max: 2, offset: 2)

        then:
        commonUIAList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        commonUIAService.count() == 5
    }

    void "test delete"() {
        Long commonUIAId = setupData()

        expect:
        commonUIAService.count() == 5

        when:
        commonUIAService.delete(commonUIAId)
        sessionFactory.currentSession.flush()

        then:
        commonUIAService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CommonUIA commonUIA = new CommonUIA()
        commonUIAService.save(commonUIA)

        then:
        commonUIA.id != null
    }
}
