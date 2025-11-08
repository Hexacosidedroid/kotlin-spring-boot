package ru.cib.kotlinspringboot.xml

import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "students")
data class Students(
    @XmlElement(name = "student")
    var student: List<Student> = mutableListOf(),
)
