package com.colutti.starwars.actors.dto.client

data class PersonageClientResponse (
        var id: Long,
        var name: String,
        var birth: String,
        var death: String,
        var species: String,
        var description: String,
        var url_image: String,
        var actors: List<ActorClientResponse>,
        var planet: PlanetClientResponse
)