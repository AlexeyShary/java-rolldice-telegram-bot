package sh.alex.rolldicetelegrambot.stats.data;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiceRollMapper {
    DiceRollMapper INSTANCE = Mappers.getMapper(DiceRollMapper.class);

    DiceRollDto toDto(DiceRoll entity);
}