package ru.cib.kotlinspringboot.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.cib.kotlinspringboot.dao.Hobby

interface HobbyRepository: JpaRepository<Hobby, Long>