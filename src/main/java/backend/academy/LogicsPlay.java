package backend.academy;

import lombok.Getter;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class LogicsPlay {
    private Set<Character> letters; // Множество угаданных букв
    private Set<Character> lettersGuess; // Множество букв, которые надо угадать
    private Set<Character> allLetters; // Буквы, которые подал пользователь
    @Getter private String word = null;
    @Getter private int nowCount = 0;
    private final int countError;

    public LogicsPlay(String word, int countError) {
        this.word = word.toLowerCase();
        this.countError = countError;
        this.lettersGuess = new HashSet<>();
        this.allLetters = new HashSet<>();
        this.nowCount = 0;
        this.letters = lettersSet(word);
    }


}
