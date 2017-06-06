package de.sandritter.fifalicious.web.client.domain.repository.fifaliciousserver

import de.sandritter.fifalicious.web.client.domain.assembler.PlayerAssembler
import de.sandritter.fifalicious.web.client.domain.model.Player
import de.sandritter.fifalicious.web.client.domain.model.Stroke
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.Resources
import org.springframework.http.HttpMethod
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

    public final List<Player> givenDummyPlayers() {
        return [
                new Player(
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                )
        ].sort {
            it.strokes.size()
        }.reverse()
    }
}
