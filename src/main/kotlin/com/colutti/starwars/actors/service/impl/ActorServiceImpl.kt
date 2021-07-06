package com.colutti.starwars.actors.service.impl

import com.colutti.starwars.actors.dto.actor.ActorDto
import com.colutti.starwars.actors.dto.actor.converts.ActorConverter
import com.colutti.starwars.actors.exception.ActorNotFoundException
import com.colutti.starwars.actors.repository.ActorRepository
import com.colutti.starwars.actors.service.ActorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActorServiceImpl: ActorService {

    @Autowired
    lateinit var repository: ActorRepository

    @Autowired
    lateinit var converter: ActorConverter

    override fun create(actorDto: ActorDto) {
        repository.save(converter.dtoToActor(actorDto))
    }

    override fun update(id: Long, actorDto: ActorDto) {
        if(getById(id) != null){
            actorDto.id = id
            create(actorDto)
        }
    }

    override fun getById(id: Long): ActorDto =
            converter.actorToDto(repository.findById(id).orElseGet { throw ActorNotFoundException("Actor ${id} not found") })

    override fun getAll(): List<ActorDto> =
        converter.actorListToDtoList(repository.findAll().toList())


}