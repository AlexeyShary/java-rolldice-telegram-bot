package sh.alex.rolldicetelegrambot.stats.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.alex.rolldicetelegrambot.stats.data.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {
    private final DiceRollRepository diceRollRepository;

    @Transactional(readOnly = true)
    public List<DiceRollDto> getLasts() {
        return diceRollRepository.findLasts().stream()
                .map(DiceRollMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DiceRollStatsProjection> getMostPopular() {
        return diceRollRepository.findMostPopular();
    }

    @Transactional
    public void storeDiceRoll(String request, String result) {
        DiceRoll diceRoll = new DiceRoll();
        diceRoll.setResult(result);
        diceRoll.setRequest(request.toLowerCase());
        diceRoll.setTimestamp(LocalDateTime.now());

        diceRollRepository.save(diceRoll);
    }
}