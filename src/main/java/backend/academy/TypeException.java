package backend.academy;

import java.io.PrintStream;
import java.util.Scanner;

public class TypeException extends Exception {
    private static final String INVALIDMESSAGE = "Некорректный ввод. Введите 1, 2, 3 или 4: ";

    private TypeException() {
        super("Утилитарный класс не может быть инстанцирован");
    }


}

