package sh.alex.rolldicetelegrambot.stats.data;

public interface DiceRollStatsProjection {
    String getRequest();

    void setRequest(String request);

    Long getCount();

    void setCount(Long count);
}