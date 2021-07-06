package com.colutti.starwars.actors.dto.actor

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class ActorDto(
        var id: Long = 0,
        var name: String = "",
        var url_image: String = "",
        @JsonFormat(pattern="yyyy-MM-dd")
        var birth_date: Date = Date(),
)