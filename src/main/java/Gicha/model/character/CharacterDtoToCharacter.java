package Gicha.model.character;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterDtoToCharacter {
    Gicha.Character CharacterDtoToCharacter(Character source);

    Character CharacterToCharacterDto(Gicha.Character destination);
}
