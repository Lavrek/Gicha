package gicha;

import gicha.model.character.Birthplace;
import gicha.model.character.Character;
import gicha.model.character.CharacterDtoToCharacterImpl;
import gicha.model.character.Element;
import gicha.repository.CharacterRepository;
import gicha.service.CharacterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceTest {
    CharacterService testCharacterService;
    CharacterRepository testCharacterRepository;

    @Test
    public void testGetAllCharacters() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character1 = new Character();
        character1.setBirthplace(Birthplace.INAZUMA);
        character1.setName("Elsa");
        character1.setElement(Element.CRYO);
        character1.setId(128_256L);

        when(testCharacterRepository.findAll()).thenReturn(Arrays.asList(character1));

        List<gicha.Character> allCharacters = testCharacterService.getAllCharacters();
        Assert.assertNotNull(allCharacters);
        Assert.assertEquals(1, allCharacters.size());
        gicha.Character character = allCharacters.get(0);
        Assert.assertNotNull(character);
        Assert.assertEquals("Elsa", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.INAZUMA, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.CRYO, character.getElement());
        Assert.assertEquals(128_256L, character.getId().longValue());
    }

    @Test
    public void testFindById() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Elsa");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        when(testCharacterRepository.findById(character2.getId())).thenReturn(Optional.of(character2));

        gicha.Character character = testCharacterService.findById(12L);
        Assert.assertNotNull(character);
        Assert.assertEquals(12L, character.getId().longValue());
        Assert.assertEquals("Elsa", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.INAZUMA, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.CRYO, character.getElement());
    }

    @Test
    public void testGetByName() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Nora");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        when(testCharacterRepository.findByName(character2.getName())).thenReturn(Optional.of(character2));

        gicha.Character character = testCharacterService.getByName("Nora");
        Assert.assertNotNull(character);
        Assert.assertEquals(12L, character.getId().longValue());
        Assert.assertEquals("Nora", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.INAZUMA, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.CRYO, character.getElement());
    }

    @Test
    public void testGetAllCharactersByLocation() {

        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Nora");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        Character character3 = new Character();
        character3.setBirthplace(Birthplace.LIYUE);
        character3.setName("Ded");
        character3.setElement(Element.GEO);
        character3.setId(24L);

        Character character4 = new Character();
        character4.setBirthplace(Birthplace.LIYUE);
        character4.setName("Childe");
        character4.setElement(Element.HYDRO);
        character4.setId(2556L);

        when(testCharacterRepository.findByBirthplace(Birthplace.LIYUE)).thenReturn(Arrays.asList(character3, character4));
        List<gicha.Character> charactersByLocation = testCharacterService.getAllCharactersByLocation(gicha.Character.BirthplaceEnum.LIYUE);

        Assert.assertNotNull(charactersByLocation);
        Assert.assertEquals(2, charactersByLocation.size());
        gicha.Character character = charactersByLocation.get(0);
        Assert.assertNotNull(character);
        Assert.assertEquals("Ded", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.LIYUE, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.GEO, character.getElement());
        Assert.assertEquals(24L, character.getId().longValue());
    }

    @Test
    public void testGetAllCharactersByElement() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Nora");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        Character character3 = new Character();
        character3.setBirthplace(Birthplace.LIYUE);
        character3.setName("Ded");
        character3.setElement(Element.HYDRO);
        character3.setId(24L);

        Character character4 = new Character();
        character4.setBirthplace(Birthplace.LIYUE);
        character4.setName("Childe");
        character4.setElement(Element.HYDRO);
        character4.setId(2556L);

        when(testCharacterRepository.findByElement(Element.HYDRO)).thenReturn(Arrays.asList(character3, character4));
        List<gicha.Character> charactersByElement = testCharacterService.getAllCharactersByElement(gicha.Character.ElementEnum.HYDRO);

        Assert.assertNotNull(charactersByElement);
        Assert.assertEquals(2, charactersByElement.size());
        gicha.Character character = charactersByElement.get(1);
        Assert.assertNotNull(character);
        Assert.assertEquals("Childe", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.LIYUE, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.HYDRO, character.getElement());
        Assert.assertEquals(2556L, character.getId().longValue());

    }

    @Test
    public void testUpdate() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Elsa");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        gicha.Character character2a = new gicha.Character();
        character2a.setBirthplace(gicha.Character.BirthplaceEnum.SUMERU);
        character2a.setName("Elsa");
        character2a.setElement(gicha.Character.ElementEnum.HYDRO);
        character2a.setId(12L);

        when(testCharacterRepository.findById(character2.getId())).thenReturn(Optional.of(character2));

        gicha.Character character = testCharacterService.update(12L, character2a);

        Assert.assertNotNull(character);
        Assert.assertEquals(12L, character.getId().longValue());
        Assert.assertEquals("Elsa", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.SUMERU, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.HYDRO, character.getElement());



    }

    @Test
    public void testNameUpdate() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Elsa");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        when(testCharacterRepository.findById(character2.getId())).thenReturn(Optional.of(character2));

        gicha.Character character = testCharacterService.nameUpdate(12L, "Mariine");
        Assert.assertNotNull(character);
        Assert.assertEquals(12L, character.getId().longValue());
        Assert.assertEquals("Mariine", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.INAZUMA, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.CRYO, character.getElement());
    }

    @Test
    public void testBirthplaceUpdate() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Elsa");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        when(testCharacterRepository.findById(character2.getId())).thenReturn(Optional.of(character2));

        gicha.Character character = testCharacterService.birthplaceUpdate(12L, "SUMERU");
        Assert.assertNotNull(character);
        Assert.assertEquals(12L, character.getId().longValue());
        Assert.assertEquals("Elsa", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.SUMERU, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.CRYO, character.getElement());
    }

    @Test
    public void testElementUpdate() {
        testCharacterRepository = mock(CharacterRepository.class);
        testCharacterService = new CharacterService(testCharacterRepository, new CharacterDtoToCharacterImpl());

        Character character2 = new Character();
        character2.setBirthplace(Birthplace.INAZUMA);
        character2.setName("Elsa");
        character2.setElement(Element.CRYO);
        character2.setId(12L);

        when(testCharacterRepository.findById(character2.getId())).thenReturn(Optional.of(character2));

        gicha.Character character = testCharacterService.elementUpdate(12l, "DENDRO");
        Assert.assertNotNull(character);
        Assert.assertEquals(12L, character.getId().longValue());
        Assert.assertEquals("Elsa", character.getName());
        Assert.assertEquals(gicha.Character.BirthplaceEnum.INAZUMA, character.getBirthplace());
        Assert.assertEquals(gicha.Character.ElementEnum.DENDRO, character.getElement());
    }
}
