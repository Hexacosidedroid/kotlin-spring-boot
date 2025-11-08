package ru.cib.kotlinspringboot.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduleService (
    private val loadFromXmlFile: LoadFromXmlFile,
    private val saveToDatabase: SaveToDatabase
){

    val logger = LoggerFactory.getLogger(ScheduleService::class.java)

    @Scheduled(fixedDelay = 60000)
    fun saveNewXml() {
        logger.info("Start loading xml files from new")
        val students = loadFromXmlFile.loadFromXmlFile()
        students.student.forEach { student ->
            logger.debug("Start saving students to database {}", student)
            saveToDatabase.saveStudent(student)
        }
    }
}