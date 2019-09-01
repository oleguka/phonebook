package com.olegulka.phonebook.controller

import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.ObjectMapper
import com.olegulka.phonebook.domain.Phone
import com.olegulka.phonebook.domain.Views
import com.olegulka.phonebook.repo.PhoneRepo
import com.olegulka.phonebook.validation.validation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/phones")
class PhoneController(
        @Autowired
        private val numberRepo: PhoneRepo
) {

    @GetMapping
    @JsonView(Views.IdName::class)
    fun list(): MutableList<Phone> = numberRepo.findAll()

    @GetMapping("/{id}")
    fun getNumber(@PathVariable("id") phone: Phone) = phone

    @PostMapping
    fun createNumber(@RequestBody phone: Phone): Phone {
        when(phone.validation()) {
            list().any { it.number == phone.number } ->
                throw IllegalArgumentException("Number already exists. Please enter another number")
            else -> return numberRepo.save(phone)
        }
    }

    @PutMapping("/{id}")
    fun updatePhone(@PathVariable("id") phoneFromDb: Phone, @RequestBody phoneFromUser: Phone): Phone {
        when (phoneFromUser.validation()) {
            phoneFromUser.number == phoneFromDb.number || list().any { it.number != phoneFromUser.number } ->
                return numberRepo.save(phoneFromDb.copy(name = phoneFromUser.name, number = phoneFromUser.number))
            else -> throw IllegalArgumentException("Number already exists. Please enter another number")
        }
    }

    @DeleteMapping("/{id}")
    fun deletePhone(@PathVariable("id") phone: Phone) {
        numberRepo.delete(phone)
    }

    @GetMapping("/searchByName")
    fun findByName(@PathVariable @RequestParam name: String): List<Phone> {
        return numberRepo.findByName(name)
    }

    @GetMapping("/searchByNumber")
    fun findByNumber(@PathVariable @RequestParam number: String): List<Phone> {
        return numberRepo.findByNumber(number)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @GetMapping("/saveJson")
    suspend fun backUp() {
        val mapper = ObjectMapper()
        mapper.writeValue(File("backUp.json"), list())
    }
}