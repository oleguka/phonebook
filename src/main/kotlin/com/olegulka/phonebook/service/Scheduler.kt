package com.olegulka.phonebook.service

import com.olegulka.phonebook.repo.PhoneRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class Scheduler(@Autowired
                private val numberRepo: PhoneRepo) {

    @Transactional
    @Scheduled(cron = "0 0 1 * * ?") //запуск каждый месяц; */5 * * * * ? - запуск каждые 5 секунд
    fun phoneCleanup() {
        val lastYear = LocalDateTime.now().minusYears(1)

        println("Last year in this day $lastYear")
        numberRepo.deleteByCreationDateBefore(lastYear)
    }
}