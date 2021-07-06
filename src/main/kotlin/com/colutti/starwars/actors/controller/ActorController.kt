package com.colutti.starwars.actors.controller

import com.colutti.starwars.actors.dto.ResponseDefault
import com.colutti.starwars.actors.dto.actor.ActorDto
import com.colutti.starwars.actors.service.ActorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/starwars/actors"])
class ActorController {

    @Autowired
    lateinit var service: ActorService

    @PostMapping
    fun create(@RequestBody actorDto: ActorDto): ResponseEntity<ResponseDefault> {
        service.create(actorDto)
        val responseJson = ResponseDefault("Created actor!", Date())
        return ResponseEntity(responseJson, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody actorDto: ActorDto): ResponseEntity<ResponseDefault> {
        service.update(id, actorDto)
        val responseJson = ResponseDefault("Updated actor!", Date())
        return ResponseEntity(responseJson, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ActorDto> =
        ResponseEntity(service.getById(id), HttpStatus.OK)

    @GetMapping()
    fun getAll(): ResponseEntity<List<ActorDto>> =
            ResponseEntity(service.getAll(), HttpStatus.OK)
}