package ru.cib.kotlinspringboot.service

import jakarta.xml.bind.JAXBContext
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.xml.Jdbc4SqlXmlHandler
import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.dto.Students
import java.io.File

@Service
class LoadFromXmlFile {

    val logger = LoggerFactory.getLogger(LoadFromXmlFile::class.java)

    fun loadFromXmlFile(): Students {
        var students = Students()
        val newDir = File("/Users/slava_ivanov_saikyo/kotlin-spring-boot/src/main/resources/new")
        newDir.walk().filter { it.extension == "xml" }.forEach { file ->
            if (file.isFile) {
                try {
                    logger.info("Starting to unmarshall xml file ${file.name}")
                    students = unmarshall(file)
                    file
                        .copyTo(File("/Users/slava_ivanov_saikyo/kotlin-spring-boot/src/main/resources/archive/${file.name}"))
                    file.delete()
                } catch (e: Exception) {
                    logger.error("Error unmarshall file ${file.name}", e)
                    file.copyTo(
                        File("/Users/slava_ivanov_saikyo/kotlin-spring-boot/src/main/resources/error/${file.name}")
                    )
                    file.delete()
                }
            }
        }
        return students
    }

    fun unmarshall(file: File): Students {
        val context = JAXBContext.newInstance(Students::class.java)
        val unmarshaller = context.createUnmarshaller()
        return unmarshaller.unmarshal(file) as Students
    }
}