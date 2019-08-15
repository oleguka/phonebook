package com.olegulka.phonebook.controller

import com.olegulka.phonebook.repo.PhoneRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class MainController(
        @Autowired
        val phoneRepo: PhoneRepo
) {

    @Value("\${spring.profiles.active}")
    val profile: String? = null

    @GetMapping
    fun main(model: Model): String {

        val data = HashMap<Any, Any?>()

        data["phones"] = phoneRepo.findAll()

        model.addAttribute("frontendData", data)
        model.addAttribute("isDevMode", "dev" == profile)

        return "index"
    }
}