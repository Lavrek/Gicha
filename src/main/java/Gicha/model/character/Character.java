package Gicha.model.character;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Birthplace birthplace;
    @Enumerated(EnumType.STRING)
    private Element element;

    public Character() {
    }

}
