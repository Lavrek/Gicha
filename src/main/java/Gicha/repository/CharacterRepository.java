package Gicha.repository;

import Gicha.model.character.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for Character domain
 * General purpose is to hold type information
 * <p>
 * Contains all JpaRepository methods and methods inherited from PagingAndSortingRepository and QueryByExampleExecutor interfaces
 *
 * @author Lavrek
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
