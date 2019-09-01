package com.olegulka.phonebook.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonView
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "phoneBook")
data class Phone(
        @Id
        @JsonView(Views.IdName::class)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uuid")
        @Column(name = "id", updatable = false, nullable = false, unique = true)
        val id: Long,

        @JsonView(Views.IdName::class)
        var name: String,

        @JsonView(Views.IdName::class)
        var number: String,

        @JsonView(Views.FullPhone::class)
        @Column(updatable = false)
        @CreationTimestamp
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss", timezone = "Brazil/East")
        val creationDate: LocalDateTime = LocalDateTime.now()
)