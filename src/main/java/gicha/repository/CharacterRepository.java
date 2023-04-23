package gicha.repository;

import gicha.model.character.Birthplace;
import gicha.model.character.Character;
import gicha.model.character.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    /**
     * Returns Characters from the database by element
     *
     * @param element Value to search for
     * @return a List of matching characters (or an empty List if none
     * found)
     */

    @Query("SELECT c FROM Character c WHERE c.element = :element")
    @Transactional(readOnly = true)
    List<Character> findByElement(@Param("element") Element element);

    /**
     * Returns Characters from the database by birthplace
     *
     * @param birthplace Value to search for
     * @return a List of matching characters (or an empty List if none
     * found)
     */
    @Query("SELECT c FROM Character c WHERE c.birthplace = :birthplace")
    @Transactional(readOnly = true)
    List<Character> findByBirthplace(@Param("birthplace") Birthplace birthplace);

    /**
     * Returns Characters from the database by name
     *
     * @param name Value to search for
     * @return a List of matching characters (or an empty List if none
     * found)
     */
    @Query("SELECT c FROM Character c WHERE c.name = :name")
    @Transactional(readOnly = true)
    Optional<Character> findByName(@Param("name") String name);
}
