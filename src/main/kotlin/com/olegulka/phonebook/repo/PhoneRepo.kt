package com.olegulka.phonebook.repo

import com.olegulka.phonebook.domain.Phone
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneRepo : JpaRepository<Phone, Long> {

    fun <T> findByName(name: String): List<T>

    fun <T> findByNumber(number: String): List<T>
}