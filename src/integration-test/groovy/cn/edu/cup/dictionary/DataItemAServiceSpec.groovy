package cn.edu.cup.dictionary

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataItemAServiceSpec extends Specification {

    DataItemAService dataItemAService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataItemA(...).save(flush: true, failOnError: true)
        //new DataItemA(...).save(flush: true, failOnError: true)
        //DataItemA dataItemA = new DataItemA(...).save(flush: true, failOnError: true)
        //new DataItemA(...).save(flush: true, failOnError: true)
        //new DataItemA(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataItemA.id
    }

    void "test get"() {
        setupData()

        expect:
        dataItemAService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataItemA> dataItemAList = dataItemAService.list(max: 2, offset: 2)

        then:
        dataItemAList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataItemAService.count() == 5
    }

    void "test delete"() {
        Long dataItemAId = setupData()

        expect:
        dataItemAService.count() == 5

        when:
        dataItemAService.delete(dataItemAId)
        sessionFactory.currentSession.flush()

        then:
        dataItemAService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataItemA dataItemA = new DataItemA()
        dataItemAService.save(dataItemA)

        then:
        dataItemA.id != null
    }
}
