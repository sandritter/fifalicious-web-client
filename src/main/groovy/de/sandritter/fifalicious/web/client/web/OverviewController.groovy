package de.sandritter.fifalicious.web.client.web

import de.sandritter.fifalicious.web.client.domain.model.Player
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@CompileStatic
@Slf4j
@Controller
@RequestMapping('/')
class OverviewController {

    private final PlayerRepository playerRepository

    @Autowired
    OverviewController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository
    }

    @GetMapping('/overview')
    String showOverview(Model model) {
        List<Player> players = playerRepository.getPlayers()
        model.addAttribute('players', players)
        'overview'
    }
}
