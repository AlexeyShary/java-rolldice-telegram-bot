package sh.alex.rolldicetelegrambot.stats.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiceRollRepository extends JpaRepository<DiceRoll, Long> {
    @Query("SELECT d FROM DiceRoll d " +
            "ORDER BY d.timestamp DESC " +
            "LIMIT 5")
    List<DiceRoll> findLasts();

    @Query("SELECT d.request as request, COUNT(d) as count " +
            "FROM DiceRoll d " +
            "GROUP BY d.request " +
            "ORDER BY count DESC " +
            "LIMIT 5")
    List<DiceRollStatsProjection> findMostPopular();
}
