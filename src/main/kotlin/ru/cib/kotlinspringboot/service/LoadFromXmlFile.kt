package ru.cib.kotlinspringboot.service

import jakarta.xml.bind.JAXBContext
import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.dto.Students
import java.io.File

@Service
class LoadFromXmlFile {

    fun loadFromXmlFile(): Students {
        var students = Students()
        val newDir = File("/Users/slava_ivanov_saikyo/kotlin-spring-boot/src/main/resources/new")
        newDir.walk().filter { it.extension == "xml" }.forEach { file ->
            if (file.isFile) {
                students = unmarshall(file)
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