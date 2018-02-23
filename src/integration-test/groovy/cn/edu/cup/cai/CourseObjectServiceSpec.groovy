package cn.edu.cup.cai

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseObjectServiceSpec extends Specification {

    CourseObjectService courseObjectService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseObject(...).save(flush: true, failOnError: true)
        //new CourseObject(...).save(flush: true, failOnError: true)
        //CourseObject courseObject = new CourseObject(...).save(flush: true, failOnError: true)
        //new CourseObject(...).save(flush: true, failOnError: true)
        //new CourseObject(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseObject.id
    }

    void "test get"() {
        setupData()

        expect:
        courseObjectService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseObject> courseObjectList = courseObjectService.list(max: 2, offset: 2)

        then:
        courseObjectList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseObjectService.count() == 5
    }

    void "test delete"() {
        Long courseObjectId = setupData()

        expect:
        courseObjectService.count() == 5

        when:
        courseObjectService.delete(courseObjectId)
        sessionFactory.currentSession.flush()

        then:
        courseObjectService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseObject courseObject = new CourseObject()
        courseObjectService.save(courseObject)

        then:
        courseObject.id != null
    }
}
