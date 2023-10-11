package sh.alex.rolldicetelegrambot.stats.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiceRollDto {
    private String request;
    private String result;
    private LocalDateTime timestamp;
}