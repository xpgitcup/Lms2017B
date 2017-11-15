package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(DataKey4Menu)
interface DataKey4MenuService {

    DataKey4Menu get(Serializable id)

    List<DataKey4Menu> list(Map args)

    Long count()

    void delete(Serializable id)

    DataKey4Menu save(DataKey4Menu dataKey4Menu)

}