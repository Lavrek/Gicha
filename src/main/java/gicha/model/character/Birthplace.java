package gicha.model.character;

/**
 * Enum class for character birthplace.
 */
public enum Birthplace {
    MONDSTADT,
    LIYUE,
    INAZUMA,
    SUMERU,
    FONTAINE,
    NATLAN,
    SNEZHNAYA,
    UNIVERSE,
    UNKNOWN;

    public static Birthplace birthplaceToBirthplaceDto(gicha.Character.BirthplaceEnum newBirthplace) {
        Birthplace updatedBirthplace;
        return updatedBirthplace = valueOf(newBirthplace.getValue());
    }
}

