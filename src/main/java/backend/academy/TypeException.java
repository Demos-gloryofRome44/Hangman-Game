package backend.academy;

import java.io.PrintStream;
import java.util.Scanner;

public class TypeException extends Exception {
    private static final String INVALIDMESSAGE = "Некорректный ввод. Введите 1, 2, 3 или 4: ";

    private TypeException() {
        super("Утилитарный класс не может быть инстанцирован");
    }

    public static Topic getTopics(Scanner scanner) throws Exception {
        while (true) {
            System.out.print("Введите номер темы: "); // Запрашиваем ввод у пользователя
            String input = scanner.nextLine().trim(); // Считываем строку и убираем пробелы по краям

            try {
                int type = Integer.parseInt(input); // Пробуем преобразовать строку в целое число
                return Topic.fromValue(type); // Возвращаем соответствующую тему
            } catch (NumberFormatException e) {
                System.out.println(INVALIDMESSAGE);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALIDMESSAGE);
            }
        }
    }

}

