package gicha.model.character;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterDtoToCharacter {
    gicha.Character characterDtoToCharacter(Character source);

    Character characterToCharacterDto(gicha.Character destination);


}
