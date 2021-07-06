package com.colutti.starwars.actors.repository

import com.colutti.starwars.actors.model.Actor
import org.springframework.data.repository.PagingAndSortingRepository

interface ActorRepository: PagingAndSortingRepository<Actor, Long> {
}