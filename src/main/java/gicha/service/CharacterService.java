package gicha.service;

import gicha.Character;
import gicha.exceptions.CharacterNotFoundException;
import gicha.model.character.Birthplace;
import gicha.model.character.CharacterDtoToCharacter;
import gicha.model.character.Element;
import gicha.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
     * //     * Save a character in the database
     * //     * The url argument must contain Character object
     * //     *
     * //     * @param character Character object
     * //
     */
    public void save(Character character) {
        characterRepository.save(characterDtoToCharacter.characterToCharacterDto(character));
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
    public Character findById(Long id) {
        return characterRepository.findById(id).map(characterDtoToCharacter::characterDtoToCharacter)
                .orElseThrow(CharacterNotFoundException::new);
    }

    /**
     * Delete a character from database
     * The url argument must specify character ID
     *
     * @param id the character ID
     * @throws CharacterNotFoundException if there is no characters with specified ID
     */
    public void deleteById(Long id) {
        gicha.model.character.Character characterToDelete = characterRepository.findById(id).orElseThrow(CharacterNotFoundException::new);
        characterRepository.deleteById(characterToDelete.getId());
    }

    /**
     * Returns all characters from database
     *
     * @return List of characters
     * @throws CharacterNotFoundException if there aren't any character in database
     * @see List
     */
    public List<Character> getAllCharacters() {

        return characterRepository.findAll().stream()
                .map(e -> characterDtoToCharacter.characterDtoToCharacter(e))
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new CharacterNotFoundException();
                    return result;
                }));
    }

    /**
     * Returns a Character object
     * The url argument must specify Character name
     *
     * @param name the Character name
     * @return The character at the specified URL
     * @throws CharacterNotFoundException if there is no character with specified ID
     * @see Character
     */
    public Character getByName(String name) {
        return characterRepository.findByName(name).map(characterDtoToCharacter::characterDtoToCharacter)
                .orElseThrow(CharacterNotFoundException::new);
    }

    /**
     * Returns all characters with the same birthplace
     * The url argument must specify character birthplace
     *
     * @param birthplaceEnum the character birthplace
     * @return List of characters at the specified URL
     * @see List
     */
    public List<Character> getAllCharactersByLocation(Character.BirthplaceEnum birthplaceEnum) {
        return characterRepository.findByBirthplace(Birthplace.valueOf(birthplaceEnum.getValue()))
                .stream().map((characterDtoToCharacter::characterDtoToCharacter))
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new CharacterNotFoundException();
                    return result;
                }));
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
        return characterRepository.findByElement(Element.valueOf(elementEnum.getValue())).stream()
                .map((characterDtoToCharacter::characterDtoToCharacter))
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new CharacterNotFoundException();
                    return result;
                }));
    }

    /**
     * Update an existing Character object
     * The url argument must specify character ID and Character object with requested changes
     *
     * @param id        the character ID
     * @param character modified Character object
     */


    public Character update(Long id, Character character) {
        return characterRepository.findById(id)
            .map(existingCharacter -> {
                gicha.model.character.Character updatedCharacter = characterDtoToCharacter.characterToCharacterDto(character);
                characterRepository.save(updatedCharacter);
                return characterDtoToCharacter.characterDtoToCharacter(updatedCharacter);
            })
            .orElseThrow(CharacterNotFoundException::new);
    }

    /**
     * Update an existing Character name
     * The url argument must specify character ID and new Character name
     *
     * @param id   the character ID
     * @param name modified Character name
     */
    public Character nameUpdate(Long id, String name) {
        gicha.model.character.Character updatedCharacter = characterRepository.findById(id)
                .orElseThrow(CharacterNotFoundException::new);
        updatedCharacter.setName(name);
        return characterDtoToCharacter.characterDtoToCharacter(updatedCharacter);
    }

    /**
     * Update an existing Character birthplace
     * The url argument must specify character ID and new Character birthplace
     *
     * @param id         the character ID
     * @param birthplace modified Character birthplace
     */
    public Character birthplaceUpdate(Long id, String birthplace) {
        gicha.model.character.Character updatedCharacter = characterRepository.findById(id)
                .orElseThrow(CharacterNotFoundException::new);
        updatedCharacter.setBirthplace(Birthplace.valueOf(birthplace));
        return characterDtoToCharacter.characterDtoToCharacter(updatedCharacter);
    }

    /**
     * Update an existing Character element
     * The url argument must specify character ID and new Character element
     *
     * @param id      the character ID
     * @param element modified Character element
     */
    public Character elementUpdate(Long id, String element) {
        gicha.model.character.Character updatedCharacter = characterRepository.findById(id)
                .orElseThrow(CharacterNotFoundException::new);
        updatedCharacter.setElement(Element.valueOf(element));
        return characterDtoToCharacter.characterDtoToCharacter(updatedCharacter);
    }
}