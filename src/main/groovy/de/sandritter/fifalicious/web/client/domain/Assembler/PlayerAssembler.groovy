package de.sandritter.fifalicious.web.client.domain.assembler

import de.sandritter.fifalicious.web.client.domain.model.Player
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resources

class PlayerAssembler {

    private final StrokeAssembler strokeAssembler

    @Autowired
    PlayerAssembler(StrokeAssembler strokeAssembler) {
        this.strokeAssembler = strokeAssembler
    }

    List<Player> getAssembledPlayers(Resources<Player> playerResources) {
        List<Player> players = []
        playerResources.content.each {
            Player player = new Player(
                    playerId: it.playerId,
                    firstName: it.firstName,
                    lastName: it.lastName,
                    last_update: it.last_update,
                    strokes: it.strokes
            )
            players.add(player)
        }
        players
    }
}
