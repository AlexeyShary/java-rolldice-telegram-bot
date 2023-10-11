package sh.alex.rolldicetelegrambot.stats.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "dice_rolls")
public class DiceRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dice_roll_id")
    private Long id;

    @Column(name = "dice_roll_request")
    private String request;

    @Column(name = "dice_roll_result")
    private String result;

    @Column(name = "dice_roll_timestamp")
    private LocalDateTime timestamp;
}