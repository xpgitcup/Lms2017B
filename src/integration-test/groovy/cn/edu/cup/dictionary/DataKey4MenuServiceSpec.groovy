package cn.edu.cup.dictionary

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataKey4MenuServiceSpec extends Specification {

    DataKey4MenuService dataKey4MenuService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataKey4Menu(...).save(flush: true, failOnError: true)
        //new DataKey4Menu(...).save(flush: true, failOnError: true)
        //DataKey4Menu dataKey4Menu = new DataKey4Menu(...).save(flush: true, failOnError: true)
        //new DataKey4Menu(...).save(flush: true, failOnError: true)
        //new DataKey4Menu(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataKey4Menu.id
    }

    void "test get"() {
        setupData()

        expect:
        dataKey4MenuService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataKey4Menu> dataKey4MenuList = dataKey4MenuService.list(max: 2, offset: 2)

        then:
        dataKey4MenuList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataKey4MenuService.count() == 5
    }

    void "test delete"() {
        Long dataKey4MenuId = setupData()

        expect:
        dataKey4MenuService.count() == 5

        when:
        dataKey4MenuService.delete(dataKey4MenuId)
        sessionFactory.currentSession.flush()

        then:
        dataKey4MenuService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataKey4Menu dataKey4Menu = new DataKey4Menu()
        dataKey4MenuService.save(dataKey4Menu)

        then:
        dataKey4Menu.id != null
    }
}
