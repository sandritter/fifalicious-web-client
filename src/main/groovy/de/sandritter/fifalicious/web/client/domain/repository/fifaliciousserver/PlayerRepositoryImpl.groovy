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
