package sh.alex.rolldicetelegrambot.stats.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.alex.rolldicetelegrambot.stats.data.DiceRollDto;
import sh.alex.rolldicetelegrambot.stats.data.DiceRollStatsProjection;

import java.util.List;

@RestController
@RequestMapping(path = "/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/last")
    public List<DiceRollDto> getLasts() {
        return statsService.getLasts();
    }

    @GetMapping("/popular")
    public List<DiceRollStatsProjection> getMostPopular() {
        return statsService.getMostPopular();
    }
}