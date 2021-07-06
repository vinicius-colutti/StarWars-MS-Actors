package com.colutti.starwars.actors.service

import com.colutti.starwars.actors.dto.actor.ActorDto

interface ActorService {

    fun create(actorDto: ActorDto)
    fun update(id: Long, actorDto: ActorDto)
    fun getById(id: Long): ActorDto
    fun getAll(): List<ActorDto>

}