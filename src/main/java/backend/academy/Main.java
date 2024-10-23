package backend.academy;

import java.io.PrintStream;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final int CountError = 6; // Максимальное количество ошибок
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);
        PrintStream out = System.out;

        out.print("Введите любой символ для вывода правил игры \n"
            + "или нажмите Enter для пропуска: ");
        String input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            displayRules(System.out); // Вывод правил, если введен любой символ
        }

        while (playAgain) {
            out.println("Выберите тему:");
            out.println("1) Животные \n2) Города \n3) Личности\n4) Смешанная\n");

            int type = 0;
            int level;
            Topic topic;
            out.print("Введите 1 2 3 или 4: ");

            while (true) {
                try {
                    topic = TypeException.getTopics(scanner, System.out);
                    break;
                } catch (Exception e) {
                    out.println(e.getMessage());
                }
            }
            out.print("Ввыберите сложность [С]ложно/[Л]егко: ");
            String lev = scanner.nextLine().trim();
            String output = null;
            if (lev.equalsIgnoreCase("С")) {
                level = 1;
                output = "Сложно";
            } else {
                level = 0;
                output = "Легко";
            }

            OpenFile fileReader = new OpenFile(topic.getValue(), level);
            final String Word = fileReader.word(); // загаданное слово
            if (Word == null) {
                out.println("Не удалось выбрать слово. Проверьте наличие файлов.");
                return;
            }

            out.println("Ваше слово из категории " + topic.getName() + " Уровень " + output + " :");

            LogicsPlay gameLogic = new LogicsPlay(Word, CountError);
            StateGallows gameState = new StateGallows();

            // Игровой цикл
            while (!gameLogic.gameWon() && !gameLogic.gameLost()) {
                out.print("Загаданное слово: ");
                gameLogic.currentState(out); // Отображение текущего состояния слова

                out.println("Кол-во букв: " + gameLogic.getNowLetter());
                char letter = ' ';
                boolean validInput = true;

                out.print("Введите букву загаданного слова или ?: ");
                while (validInput) {
                    String tmp = scanner.nextLine().trim();

                    if (tmp.equals("?")) {
                        out.println("ПОДСКАЗКА: " + fileReader.clue());
                        out.print("Введите букву загаданного слова: ");
                        continue;
                    }
                    if (gameLogic.isValidStr(tmp)) {
                        letter = tmp.charAt(0); // Ввод буквы
                        validInput = false;
                    } else {
                        out.print("Ошибка! Введите одну букву загаданного слова или ?: ");
                    }
                }
                if (gameLogic.guessLetter(letter)) {
                    out.println("Вы угадали букву - " + letter);
                } else {
                    out.println("Ошибка! Нынешнее кол-во попыток : " + gameLogic.getErrorNow());
                    if (gameLogic.nowCount() > 0) {
                        gameState.printState(gameLogic.nowCount() - 1, System.out);
                    }
                }

            }
            if (gameLogic.gameWon()) {
                gameLogic.currentState(out);
                out.println("Вы победили, поздравляю ;)");
            } else {
                out.println("Игра окончена, увы :( \nВаше слово было: " + gameLogic.word().toUpperCase());
            }

            out.print("Хотите сыграть еще [Д]а/[Н]ет = ");
            String scan2 = scanner.next();
            if (scan2.equalsIgnoreCase("Н")) {
                playAgain = false;
            } else {
                scanner.nextLine();
            }
        }

        out.println("     ***Спасибо за игру! :)))***      ");
    }

    private void displayRules(PrintStream out) {
        out.println("Правила игры 'Виселица':\n"
            + "1. Игрок выбирает тему для угадывания слов.\n"
            + "2. Доступны два уровня сложности, по умолчанию выбран легкий режим:\n"
            + "   - Легкий: слова, состоящие из 4-7 букв и являющиеся общеизвестными.\n"
            + "   - Сложный: слова, начинающиеся с 8 букв и являющиеся достаточно редкими в употреблении.\n"
            + "3. Игра начинается с загаданного слова, состоящего из нескольких букв.\n"
            + "4. Игроку предоставляется подсказка, которую можно использовать введя <?> .\n"
            + "5. Игрок поочередно предлагает буквы, которые, по его мнению, могут быть в слове.\n"
            + "6. Если буква есть в слове, она открывается. Если буквы нет, игрок теряет одну попытку.\n"
            + "7. Игра продолжается до тех пор, пока игрок не угадает слово или не исчерпает все попытки.\n"
            + "8. Воспроизведение игры по умолчанию - 'да'. Игрок может выбрать 'нет', чтобы завершить игру.\n"
            + "9. Удачи!");
    }
}
