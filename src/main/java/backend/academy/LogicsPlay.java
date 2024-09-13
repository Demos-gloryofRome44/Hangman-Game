package backend.academy;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

public class LogicsPlay {
    private Set<Character> letters = null; // Множество угаданных букв
    private Set<Character> lettersGuess = null; // Множество букв, которые надо угадать
    private Set<Character> allLetters = null; // Буквы, которые подал пользователь
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

    public int getErrorNow() {
        return countError - nowCount;
    }

    public int getNowLetter() {
        return word.length() - lettersGuess.size();
    }

    public boolean gameWon() {
        return lettersGuess.size() == letters.size();
    }

    public boolean gameLost() {
        return nowCount == countError;
    }

}
