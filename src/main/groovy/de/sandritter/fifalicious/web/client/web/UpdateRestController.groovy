package de.sandritter.fifalicious.web.client.web

import de.sandritter.fifalicious.web.client.domain.model.Player
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateRestController {

    private PlayerRepository playerRepository

    @Value('${gameConfig.amountStrokesToShitYourself}')
    private int maxAmountOfStrokes;

    @Autowired
    UpdateRestController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository
    }

    @PostMapping("/player/add-stroke")
    public String addStroke(@RequestBody Map<String, Object> payload) {
        ResponseEntity<Player> response = playerRepository.addStrokeToPlayer((long) payload.get('player-reference'))
        generateJsonOutput(response.body, response.statusCodeValue)
    }

    @PutMapping("/player/delete-all-strokes")
    public String deleteAllStrokes(@RequestBody Map<String, Object> payload) {
        ResponseEntity<Player> response = playerRepository.deleteAllStrokesOfPlayer((long) payload.get('player-reference'))
        generateJsonOutput(response.body, response.statusCodeValue)
    }

    private String generateJsonOutput(Player player, int status) {
        JsonOutput.toJson(
                [
                        status : status,
                        activeAmountOfStrokes: player.getActiveAmountOfStrokes(),
                        strikeRange : player.getStrikeRange(),
                        strokeColor : player.getColorCode(),
                        maxAmountOfStrokes : maxAmountOfStrokes
                ]
        )
    }
}
