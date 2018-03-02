package cn.edu.cup.cai

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class QuestionDocumentServiceSpec extends Specification {

    QuestionDocumentService questionDocumentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new QuestionDocument(...).save(flush: true, failOnError: true)
        //new QuestionDocument(...).save(flush: true, failOnError: true)
        //QuestionDocument questionDocument = new QuestionDocument(...).save(flush: true, failOnError: true)
        //new QuestionDocument(...).save(flush: true, failOnError: true)
        //new QuestionDocument(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //questionDocument.id
    }

    void "test get"() {
        setupData()

        expect:
        questionDocumentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<QuestionDocument> questionDocumentList = questionDocumentService.list(max: 2, offset: 2)

        then:
        questionDocumentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        questionDocumentService.count() == 5
    }

    void "test delete"() {
        Long questionDocumentId = setupData()

        expect:
        questionDocumentService.count() == 5

        when:
        questionDocumentService.delete(questionDocumentId)
        sessionFactory.currentSession.flush()

        then:
        questionDocumentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        QuestionDocument questionDocument = new QuestionDocument()
        questionDocumentService.save(questionDocument)

        then:
        questionDocument.id != null
    }
}
