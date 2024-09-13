package backend.academy.samples;

import backend.academy.LogicsPlay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicsPlayTest {
    private LogicsPlay gameLogic;

    @BeforeEach
    void setUp() {
        gameLogic = new LogicsPlay("кот", 6); // Пример: слово "кот", 6 попыток
    }

    // Тест успешного угадывания буквы
    @Test
    void testGuessLetterCorrectly() {
        assertTrue(gameLogic.guessLetter('к'), "Буква 'к' должна быть угадана");
        assertEquals(2, gameLogic.getNowLetter(), "Кол-во букв которые надо угадать должно стать 2");
        assertTrue(gameLogic.guessLetter('О'), "Буква 'к' должна быть угадана");
        assertEquals(1, gameLogic.getNowLetter(), "Кол-во букв которые надо угадать должно стать 2");
    }

    // Тест неправильного угадывания буквы
    @Test
    void testGuessLetterIncorrectly() {
        assertFalse(gameLogic.guessLetter('а'), "Буква 'а' должна быть неверной");
        assertEquals(1, gameLogic.getNowCount(), "Кол-во попыток должно увеличиться на 1");
    }

    // Тест обработки повторного ввода буквы
    @Test
    void testGuessLetterTwice() {
        assertTrue(gameLogic.guessLetter('к'), "Буква 'к' должна быть угадана");
        assertFalse(gameLogic.guessLetter('к'), "Повторный ввод буквы 'к' должен быть неверным");
        assertEquals(2, gameLogic.getNowLetter(), "Кол-во угаданных букв которых надо угадать должно стать 2");
    }

    // Тест обработки ввода неверной буквы
    @Test
    void testGuessInvalidLetter() {
        assertFalse(gameLogic.guessLetter('1'), "Ввод цифры '1' должен быть неверным");
        assertEquals(0, gameLogic.getNowCount(), "Кол-во попыток не должно увеличиться");
    }

    // Тест победы в игре
    @Test
    void testGameWon() {
        gameLogic.guessLetter('к');
        gameLogic.guessLetter('о');
        gameLogic.guessLetter('т');
        assertTrue(gameLogic.gameWon(), "Игра должна быть выиграна после угадывания всех букв");
    }

    // Тест поражения в игре
    @Test
    void testGameLost() {
        gameLogic.guessLetter('г');
        gameLogic.guessLetter('е');
        gameLogic.guessLetter('р');
        gameLogic.guessLetter('а');
        gameLogic.guessLetter('л');
        gameLogic.guessLetter('ь');
        assertTrue(gameLogic.gameLost(), "Игра должна быть проиграна после превышения попыток");
    }
}


