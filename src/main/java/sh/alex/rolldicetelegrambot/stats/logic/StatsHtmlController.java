package sh.alex.rolldicetelegrambot.stats.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/stats")
@RequiredArgsConstructor
public class StatsHtmlController {
    private final StatsService statsService;

    @GetMapping
    public String statsPage(Model model) {
        model.addAttribute("lasts", statsService.getLasts());
        model.addAttribute("popular", statsService.getMostPopular());
        return "stats";
    }
}
