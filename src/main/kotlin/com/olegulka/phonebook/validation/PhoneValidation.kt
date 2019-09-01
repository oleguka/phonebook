package com.olegulka.phonebook.validation

import com.olegulka.phonebook.domain.Phone

fun Phone.validation(): Boolean {
    val validReg = Regex("""\+\d-\d{10}""").find(number)

    when {
        validReg != null -> return true
        else -> throw IllegalArgumentException("Invalid number format. Please use format +X-XXXXXXXXXX")
    }
}
