package cn.edu.cup.dictionary

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataKeyAServiceSpec extends Specification {

    DataKeyAService dataKeyAService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataKeyA(...).save(flush: true, failOnError: true)
        //new DataKeyA(...).save(flush: true, failOnError: true)
        //DataKeyA dataKeyA = new DataKeyA(...).save(flush: true, failOnError: true)
        //new DataKeyA(...).save(flush: true, failOnError: true)
        //new DataKeyA(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataKeyA.id
    }

    void "test get"() {
        setupData()

        expect:
        dataKeyAService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataKeyA> dataKeyAList = dataKeyAService.list(max: 2, offset: 2)

        then:
        dataKeyAList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataKeyAService.count() == 5
    }

    void "test delete"() {
        Long dataKeyAId = setupData()

        expect:
        dataKeyAService.count() == 5

        when:
        dataKeyAService.delete(dataKeyAId)
        sessionFactory.currentSession.flush()

        then:
        dataKeyAService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataKeyA dataKeyA = new DataKeyA()
        dataKeyAService.save(dataKeyA)

        then:
        dataKeyA.id != null
    }
}
