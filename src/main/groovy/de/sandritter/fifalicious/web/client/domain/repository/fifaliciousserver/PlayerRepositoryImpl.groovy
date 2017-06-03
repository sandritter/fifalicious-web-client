package de.sandritter.fifalicious.web.client.domain.repository.fifaliciousserver

import de.sandritter.fifalicious.web.client.domain.model.Player
import de.sandritter.fifalicious.web.client.domain.model.Stroke
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository

class PlayerRepositoryImpl implements PlayerRepository{

    @Override
    List<Player> getPlayers() {

        return [
                new Player(
                        avatar: '',
                        lastName: 'Sandritter',
                        firstName: 'Michael',
                        nickName: 'msand001',
                        strokes: [
                                new Stroke(createDate: new Date(), isActive: true)
                        ]
                )
        ]
    }
}
