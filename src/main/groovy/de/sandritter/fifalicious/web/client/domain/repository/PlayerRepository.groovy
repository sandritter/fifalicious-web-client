package de.sandritter.fifalicious.web.client.domain.repository

import de.sandritter.fifalicious.web.client.domain.model.Player

interface PlayerRepository {
    List<Player> getPlayers()
}