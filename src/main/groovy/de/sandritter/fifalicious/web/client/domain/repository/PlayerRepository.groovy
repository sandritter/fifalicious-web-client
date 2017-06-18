package de.sandritter.fifalicious.web.client.domain.repository

import de.sandritter.fifalicious.web.client.domain.model.Player
import org.springframework.http.ResponseEntity

interface PlayerRepository {
    List<Player> getPlayers()
    ResponseEntity<Player> addStrokeToPlayer(long playerReference)
    ResponseEntity<Player> deleteAllStrokesOfPlayer(long longReference)
}