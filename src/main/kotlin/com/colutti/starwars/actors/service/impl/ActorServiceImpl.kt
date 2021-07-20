package com.colutti.starwars.actors.service.impl

import com.colutti.starwars.actors.dto.actor.ActorDto
import com.colutti.starwars.actors.dto.actor.converts.ActorConverter
import com.colutti.starwars.actors.exception.ActorNotFoundException
import com.colutti.starwars.actors.repository.ActorRepository
import com.colutti.starwars.actors.request.PersonageRequest
import com.colutti.starwars.actors.service.ActorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ActorServiceImpl: ActorService {

    @Autowired
    lateinit var repository: ActorRepository
    @Autowired
    lateinit var converter: ActorConverter
    @Autowired
    lateinit var request: PersonageRequest

    @CacheEvict("actors", allEntries = true)
    override fun create(actorDto: ActorDto) {
        repository.save(converter.dtoToActor(actorDto))
    }

    @CacheEvict("actors", allEntries = true)
    override fun update(id: Long, actorDto: ActorDto) {
        if(getById(id) != null){
            actorDto.id = id
            create(actorDto)
        }
    }

    @Cacheable("actors")
    override fun getById(id: Long): ActorDto =
            converter.actorToDto(repository.findById(id).orElseGet { throw ActorNotFoundException("Actor ${id} not found") })

    @Cacheable("actors")
    override fun getAll(): List<ActorDto> =
        converter.actorListToDtoList(repository.findAll().toList())

    @Cacheable("actors")
    override fun getByPersonageId(personage_id: Long): List<ActorDto> {
        var personage = request.getPersonage(personage_id)
        var actors = personage.actors.map { repository.findById(it.id)
                .orElseGet { throw ActorNotFoundException("Actor ${it.id} not found") } }
        return converter.actorListToDtoList(actors)
    }

}