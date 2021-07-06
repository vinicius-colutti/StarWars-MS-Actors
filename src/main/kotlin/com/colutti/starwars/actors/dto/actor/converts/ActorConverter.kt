package com.colutti.starwars.actors.dto.actor.converts

import com.colutti.starwars.actors.dto.actor.ActorDto
import com.colutti.starwars.actors.model.Actor
import org.springframework.stereotype.Component

@Component
class ActorConverter {

    fun dtoToActor(actorDto: ActorDto): Actor {
        var actor = Actor(actorDto.id, actorDto.name, actorDto.url_image, actorDto.birth_date)
        return actor
    }

    fun actorToDto(actor: Actor): ActorDto{
        var actorDto = ActorDto(actor.id, actor.name, actor.url_image, actor.birth_date)
        return actorDto
    }

    fun actorListToDtoList(actor: List<Actor>): List<ActorDto>{
        var actorDto: List<ActorDto> = actor.map { actorResp ->
            ActorDto(actorResp.id, actorResp.name, actorResp.url_image, actorResp.birth_date)
        }
        return actorDto
    }

}