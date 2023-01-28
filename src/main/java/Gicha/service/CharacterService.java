package Gicha.service;

import Gicha.Character;
import Gicha.Exceptions.CharacterAlreadyExistsException;
import Gicha.Exceptions.CharacterNotFoundException;
import Gicha.model.character.Birthplace;
import Gicha.model.character.CharacterDtoToCharacter;
import Gicha.model.character.Element;
import Gicha.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for character controller.
 *
 * @author Lavrek
 */
@Service
@AllArgsConstructor
public class CharacterService {
    CharacterRepository characterRepository;
    CharacterDtoToCharacter characterDtoToCharacter;

    /**
     * Save a character in the database
     * The url argument must contain Character object
     *
     * @param character Character object
     * @throws CharacterAlreadyExistsException if there is character with same ID in database
     */
    public void save(Character character) {
        Gicha.model.character.Character existingCharacter = characterRepository
                .findById(character.getId().longValue()).orElse(null);
        if (existingCharacter == null) {
            characterRepository.save(characterDtoToCharacter.CharacterToCharacterDto(character));
        } else
            throw new CharacterAlreadyExistsException();
    }

    /**
     * Update an existing Character object
     * The url argument must specify character ID and Character object with requested changes
     *
     * @param id        the character ID
     * @param character modified Character object
     */
    public void update(BigDecimal id, Character character) {
        Gicha.model.character.Character existingCharacter = characterRepository.findById(id.longValue()).orElse(null);
        if (existingCharacter == null) {
            throw new CharacterNotFoundException();
        } else {
            existingCharacter.setName(character.getName());
            existingCharacter.setElement(Element.valueOf(character.getElement().toString()));
            existingCharacter.setBirthplace(Birthplace.valueOf(character.getBirthplace().toString()));
        }
    }

    /**
     * Returns a Character object
     * The url argument must specify character ID
     *
     * @param id the character ID
     * @return The character at the specified URL
     * @throws CharacterNotFoundException if there is no character with specified ID
     * @see Character
     */

    public Character findById(BigDecimal id) {
        return characterRepository.findById(id.longValue())
                .map(e -> characterDtoToCharacter.CharacterDtoToCharacter(e))
                .orElseThrow(() -> new CharacterNotFoundException());
    }

    /**
     * Returns all characters from database
     *
     * @return List of characters
     * @throws CharacterNotFoundException if there aren't any character in database
     * @see List
     */
    public List<Character> getAllCharacters() {
        List<Character> characterList = characterRepository.findAll().stream()
                .map(e -> characterDtoToCharacter.CharacterDtoToCharacter(e))
                .collect(Collectors.toList());
        if (characterList.size() > 0) {
            return characterList;
        } else throw new CharacterNotFoundException("Your characters list is currently empty!" +
                "\nLet's start with adding your first character!");
    }

    /**
     * Delete a character from database
     * The url argument must specify character ID
     *
     * @param id the character ID
     * @throws CharacterNotFoundException if there is no characters with specified ID
     */
    public void deleteById(BigDecimal id) {
        Gicha.model.character.Character characterToDelete = characterRepository.findById(id.longValue()).orElse(null);
        if (characterToDelete == null) {
            throw new CharacterNotFoundException();
        } else {
            characterRepository.deleteById(id.longValue());
        }
    }

    /**
     * Returns all characters with the same birthplace
     * The url argument must specify character birthplace
     *
     * @param birthplaceEnum the character birthplace
     * @return List of characters at the specified URL
     * @throws CharacterNotFoundException if there is no characters with specified birthplace in database
     * @see List
     */
    public List<Character> getAllCharactersByLocation(Character.BirthplaceEnum birthplaceEnum) {
        List<Character> characterList = characterRepository.findAll().stream()
                .filter(Character -> Character.getBirthplace().name().equals(birthplaceEnum.getValue()))
                .map(e -> characterDtoToCharacter.CharacterDtoToCharacter(e))
                .collect(Collectors.toList());
        if (characterList.size() > 0) {
            return characterList;
        } else throw new CharacterNotFoundException("Characters from this location were not found");
    }

    /**
     * Returns all characters with the same element
     * The url argument must specify character element
     *
     * @param elementEnum the character element
     * @return List of characters at the specified URL
     * @throws CharacterNotFoundException if there is no characters with specified element in database
     * @see List
     */
    public List<Character> getAllCharactersByElement(Character.ElementEnum elementEnum) {
        List<Character> characterList = characterRepository.findAll().stream()
                .filter(Character -> Character.getElement().name().equals(elementEnum.getValue()))
                .map(e -> characterDtoToCharacter.CharacterDtoToCharacter(e))
                .collect(Collectors.toList());
        if (characterList.size() > 0) {
            return characterList;
        } else throw new CharacterNotFoundException("There are no characters with requested element");
    }
}
