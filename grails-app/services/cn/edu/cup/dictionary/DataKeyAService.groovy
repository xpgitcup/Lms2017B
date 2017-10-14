package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import org.springframework.stereotype.Service

@Transactional
@grails.gorm.services.Service(DataKeyA)
abstract class DataKeyAService implements IDataKeyAService {

}
