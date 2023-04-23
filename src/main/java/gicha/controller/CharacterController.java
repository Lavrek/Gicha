package gicha.controller;

import gicha.Character;
import gicha.CharacterApi;
import gicha.exceptions.CharacterNotFoundException;
import gicha.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class CharacterController implements CharacterApi {
    public final CharacterService characterService;

    @Override
    public ResponseEntity<List<Character>> characterAllcharactersGet() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @Override
    public ResponseEntity<List<Character>> characterBirthplaceGet(String birthplace) {
        try {
            return ResponseEntity.ok(characterService.getAllCharactersByLocation(Character.BirthplaceEnum.fromValue(birthplace)));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid location name", e);
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "There are no characters with requested location yet", ex);
        }
    }

    @Override
    public ResponseEntity<Character> characterBirthplaceIdPatch(Long id, String birthplace) {
        try {
            return ResponseEntity.ok(characterService.birthplaceUpdate(id, birthplace));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid location name", e);
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "Provide correct Character Id", ex);
        }
    }

    @Override
    public ResponseEntity<List<Character>> characterElementGet(String element) {
        try {
            return ResponseEntity.ok(characterService.getAllCharactersByElement(Character.ElementEnum.fromValue(element)));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid element name", e);
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "There are no characters with requested element yet", ex);
        }
    }

    @Override
    public ResponseEntity<Character> characterElementIdPatch(Long id, String element) {
        try {
            return ResponseEntity.ok(characterService.elementUpdate(id, element));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid element name", e);
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "Provide correct Character Id", ex);
        }
    }

    @Override
    public ResponseEntity<Character> characterGet(String name) {
        try {
            return ResponseEntity.ok(characterService.getByName(name));
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "There are no characters with requested name yet", ex);
        }
    }

    @Override
    public ResponseEntity<Void> characterIdDelete(Long id) {
        try {
            characterService.deleteById(id);
            return ResponseEntity.accepted().build();
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "Provide correct Character Id", ex);
        }
    }

    @Override
    public ResponseEntity<Character> characterIdGet(Long id) {
        try {
            return ResponseEntity.ok(characterService.findById(id));
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "Character Not Found", ex);
        }

    }

    @Override
    public ResponseEntity<Character> characterIdPut(Long id, Character character) {
        try {
            return ResponseEntity.ok(characterService.update(id, character));
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "Provide correct Character Id", ex);
        }
    }

    @Override
    public ResponseEntity<Character> characterNameIdPatch(Long id, String name) {
        try {
            return ResponseEntity.ok(characterService.nameUpdate(id, name));
        } catch (CharacterNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "Provide correct Character Id", ex);
        }
    }

    @Override
    public ResponseEntity<Void> characterPost(Character character) {
        characterService.save(character);
        return ResponseEntity.accepted().build();
    }
}
