package com.colutti.starwars.actors.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name="actor")
data class Actor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var name: String = "",
        var url_image: String = "",

        @JsonFormat(pattern="yyyy-MM-dd")
        @Temporal(TemporalType.TIMESTAMP)
        var birth_date: Date = Date(),
)