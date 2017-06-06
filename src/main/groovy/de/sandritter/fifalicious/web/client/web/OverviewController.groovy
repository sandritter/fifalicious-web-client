package de.sandritter.fifalicious.web.client.web

import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@CompileStatic
@Slf4j
@Controller
class OverviewController {

    private final PlayerRepository playerRepository

    @Autowired
    OverviewController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository
    }

    @GetMapping('/overview')
    String showOverview(Model model) {
        model.addAttribute('players', playerRepository.getPlayers())
        'overview'
    }
}
