package gicha.model.character;

import gicha.Character;

/**
 * Enum class for character element.
 */
public enum Element {
    ANEMO,
    GEO,
    ELECTRO,
    DENDRO,
    HYDRO,
    PYRO,
    CRYO,
    ADAPTIVE;

    public static Element elementToElementDto(Character.ElementEnum newElement) {
        Element updatedElement;
        return updatedElement = valueOf(newElement.getValue());
    }
}
