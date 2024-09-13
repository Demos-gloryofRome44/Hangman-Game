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

    public final Set<Character> lettersSet(String word) {
        Set<Character> newLetters = new HashSet<>(word.length());
        for (char c : word.toCharArray()) {
            char sh = Character.toLowerCase(c);
            newLetters.add(sh);
        }
        return newLetters;
    }

    public boolean guessLetter(char letter) {
        char lowerLetter = Character.toLowerCase(letter);
        if (validLetter(letter)) {
            if (letters.contains(lowerLetter)) {
                lettersGuess.add(lowerLetter);
                allLetters.add(lowerLetter);
                return true; // Угадали букву
            } else {
                nowCount++;
                allLetters.add(lowerLetter);
                return false; // Не угадали букву
            }
        }
        return false; // Неверный ввод
    }

    public boolean isValidStr(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    private boolean validLetter(char letter) {
        return Character.isLetter(letter) && !allLetters.contains(letter);
    }

    public void currentState(PrintStream out) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (lettersGuess.contains(c)) {
                out.print(" " + Character.toUpperCase(c) + " ");
            } else {
                out.print(" __ ");
            }
        }
        out.println();
    }

}
