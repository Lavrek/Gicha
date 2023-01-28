package Gicha.controller;

import Gicha.Character;
import Gicha.*;
import Gicha.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class CharacterController implements CharacterApi, CharacteridApi, CharactersApi, CharactersbirthplaceApi, CharacterselementApi {
    private final CharacterService characterService;

    @Override
    public ResponseEntity<Void> characterPost(Character character) {
        characterService.save(character);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> characteridDelete(BigDecimal id) {
        characterService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Character> characteridGet(BigDecimal id) {
        return ResponseEntity.ok(characterService.findById(id));

    }

    @Override
    public ResponseEntity<Void> characteridPut(BigDecimal id, Character character) {
        characterService.update(id, character);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<Character>> charactersGet() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @Override
    public ResponseEntity<List<Character>> charactersbirthplaceGet(String birthplace) {
        return ResponseEntity.ok(characterService.getAllCharactersByLocation(Character.BirthplaceEnum.fromValue(birthplace)));
    }

    @Override
    public ResponseEntity<List<Character>> characterselementGet(String element) {
        return ResponseEntity.ok(characterService.getAllCharactersByElement(Character.ElementEnum.fromValue(element)));
    }
}

