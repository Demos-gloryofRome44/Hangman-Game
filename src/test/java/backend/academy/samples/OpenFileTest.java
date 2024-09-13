package backend.academy.samples;

import backend.academy.OpenFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OpenFileTest {
    private OpenFile openFile;

    @Test
    void testChooseWordSuccessfully() { // выбрано какое то слово и подсказка
        openFile = new OpenFile(1, 1); // Предположим, что 1 соответствует теме "Животные" и уровню 1
        String word = openFile.word();
        String clue = openFile.clue();

        assertNotNull(word, "Слово не должно быть null");
        assertNotNull(clue, "Подсказка не должна быть null");
        assertFalse(word.isEmpty(), "Слово не должно быть пустым");
        assertFalse(clue.isEmpty(), "Подсказка не должна быть пустой");
    }

    @Test
    void testSelectRandomWordFromSections() {
        openFile = new OpenFile(1, 1); // Предположим, что 1 соответствует теме "Животные" и уровню 1
        String word1 = openFile.word();
        String clue1 = openFile.clue();

        openFile = new OpenFile(2, 1); // Теперь для темы "Города"
        String word2 = openFile.word();
        String clue2 = openFile.clue();

        assertNotEquals(word1, word2, "Слова из разных секций должны быть разными");
    }

    @Test
    void testInvalidLevel() {
        openFile = new OpenFile(1, 99); // Предположим, что 99 - это неверный уровень
        String word = openFile.word();
        assertNull(word, "Слово должно быть null для неверного уровня");
    }
}
