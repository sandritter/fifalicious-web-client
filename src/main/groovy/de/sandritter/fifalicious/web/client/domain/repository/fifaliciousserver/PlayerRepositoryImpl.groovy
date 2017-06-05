package de.sandritter.fifalicious.web.client.domain.repository.fifaliciousserver

import de.sandritter.fifalicious.web.client.domain.model.Player
import de.sandritter.fifalicious.web.client.domain.model.Stroke
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.Resources
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

class PlayerRepositoryImpl implements PlayerRepository{

    @Autowired
    private final RestTemplate restTemplate

    @Override
    List<Player> getPlayers() {
        Resources<Player> playersResponse = null
        try {
            playersResponse = (Resources<Player>) restTemplate.exchange('/players',
                    HttpMethod.GET, null, new ParameterizedTypeReference<Resources<Player>>() {}).body
        } catch (RestClientException e) {

        }

        return playersResponse.content.toList().sort{
            it.strokes.size()
        }.reverse()


        return [
                new Player(
                        avatar: '',
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        nickName: 'Michael S.',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        avatar: '',
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        nickName: 'Lukas H.',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        avatar: '',
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        nickName: 'Jan J.',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true),
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                ),
                new Player(
                        avatar: '',
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        nickName: 'Moritz M.',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                )
        ].sort {
            it.strokes.size()
        }.reverse()
    }
}
