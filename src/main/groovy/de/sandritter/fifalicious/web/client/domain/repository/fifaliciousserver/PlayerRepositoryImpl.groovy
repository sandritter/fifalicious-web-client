package de.sandritter.fifalicious.web.client.domain.repository.fifaliciousserver

import de.sandritter.fifalicious.web.client.domain.assembler.PlayerAssembler
import de.sandritter.fifalicious.web.client.domain.model.Player
import de.sandritter.fifalicious.web.client.domain.model.Stroke
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.Resources
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

class PlayerRepositoryImpl implements PlayerRepository {


    private final RestTemplate restTemplate
    private final PlayerAssembler playerAssembler

    @Autowired
    PlayerRepositoryImpl(RestTemplate restTemplate, PlayerAssembler playerAssembler) {
        this.restTemplate = restTemplate
        this.playerAssembler = playerAssembler
    }

    @Override
    List<Player> getPlayers() {
        return givenDummyPlayers()
        Resources<Player> playersResponse = null
        try {
            playersResponse = (Resources<Player>) restTemplate.exchange('/players',
                    HttpMethod.GET, null, new ParameterizedTypeReference<Resources<Player>>() {}).body
        } catch (RestClientException e) {

        }
        List<Player> players = playerAssembler.getAssembledPlayers(playersResponse)
        return players.sort {
            it.strokes.size()
        }.reverse()
    }

    @Override
    ResponseEntity<Player> addStrokeToPlayer(long playerReference) {
        Stroke stroke = new Stroke(createDate: new Date(), isActive: true)

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(playerReference, headers)

        ResponseEntity<Player> resp = new ResponseEntity<>(
                new Player(strokes: [new Stroke(isActive: true),new Stroke(isActive: true),new Stroke(isActive: true)]),
                HttpStatus.OK)
        return resp
        restTemplate.exchange('player/add-stroke', HttpMethod.POST, entity, Player)
    }

    @Override
    ResponseEntity<Player> deleteAllStrokesOfPlayer(long longReference) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(longReference, headers)

        ResponseEntity<Player> resp = new ResponseEntity<>(
                new Player(strokes: [new Stroke(isActive: false),new Stroke(isActive: false),new Stroke(isActive: false)]),
                HttpStatus.OK)
        return resp
    }

    private static final List<Player> givenDummyPlayers() {
        return [
                new Player(
                        reference: 1,
                        lastName: 'Hueck',
                        firstName: 'Lukas',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        reference: 2,
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        reference: 3,
                        lastName: 'Hert',
                        firstName: 'Alex',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        reference: 4,
                        lastName: 'Jensen',
                        firstName: 'Sven',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                )
        ].sort {
            it.strokes.size()
        }.reverse()
    }
}
