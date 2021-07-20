package com.colutti.starwars.actors.request

import com.colutti.starwars.actors.dto.client.PersonageClientResponse
import com.colutti.starwars.actors.exception.PersonageNotFoundException
import com.colutti.starwars.actors.exception.RequestClientException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class PersonageRequest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${request.personage.url}")
    val personageUrl: String? = null

    fun getPersonage(personage_id: Long): PersonageClientResponse {
        try {
            val httpHeaders = HttpHeaders()
            httpHeaders.contentType = MediaType.APPLICATION_JSON
            return restTemplate.getForObject(personageUrl+"/"+personage_id,
                    PersonageClientResponse::class.java)!!
        }catch (err: HttpClientErrorException){
            if(err.statusCode == HttpStatus.NOT_FOUND){
                throw PersonageNotFoundException("Personage "+ personage_id +" Not Found")
            }else{
                throw RequestClientException("Personage API unavailable, status Code: "+ err.statusCode)
            }
        }
    }

}