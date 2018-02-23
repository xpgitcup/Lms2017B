package cn.edu.cup.cai

import grails.gorm.services.Service

@Service(CourseObject)
interface CourseObjectService {

    CourseObject get(Serializable id)

    List<CourseObject> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseObject save(CourseObject courseObject)

}