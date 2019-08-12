package com.olegulka.phonebook.controller

import com.fasterxml.jackson.annotation.JsonView
import com.olegulka.phonebook.domain.Phone
import com.olegulka.phonebook.domain.Views
import com.olegulka.phonebook.repo.PhoneRepo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
        return numberRepo.save(phone)
    }

    @PutMapping("/{id}")
    fun updatePhone(@PathVariable("id") phoneFromDb: Phone,
            @RequestBody phoneFromUser: Phone): Phone {
        return numberRepo.save(phoneFromDb.copy(name = phoneFromUser.name, number = phoneFromUser.number))
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

}