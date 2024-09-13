package backend.academy;

import java.io.PrintStream;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
@SuppressWarnings("all")
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final int CountError = 6; // Максимальное количество ошибок
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите любой символ для вывода правил игры \n"
            + "или нажмите Enter для пропуска: ");
        String input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            displayRules(System.out); // Вывод правил, если введен любой символ
        }

        while (playAgain) {
            System.out.println("Выберите тему:");
            System.out.println("1) Животные \n2) Города \n3) Личности\n4) Смешанная\n");

            int type = 0;
            int level;
            Topic topic;
            System.out.print("Введите 1 2 3 или 4: ");

            while (true) {
                try {
                    topic = TypeException.getTopics(scanner, System.out);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            //scanner.nextLine();
            System.out.print("Ввыберите сложность [С]ложно/[Л]егко: ");
            String lev = scanner.nextLine().trim();
            String output = null;
            if (lev.equalsIgnoreCase("С")){
                level = 1;
                output = "Сложно";
            }
            else {
                level = 0;
                output = "Легко";
            }

            OpenFile fileReader = new OpenFile(topic.getValue(), level);
            final String Word = fileReader.word(); // загаданное слово
            if (Word == null) {
                System.out.println("Не удалось выбрать слово. Проверьте наличие файлов.");
                return;
            }

            System.out.println("Ваше слово из категории " + topic.getName() + " Уровень " + output + " :");

            LogicsPlay gameLogic = new LogicsPlay(Word, CountError);
            StateGallows gameState = new StateGallows();

            //scanner.nextLine();
            // Игровой цикл
            while (!gameLogic.gameWon() && !gameLogic.gameLost()) {
                System.out.print("Загаданное слово: ");
                gameLogic.currentState(System.out); // Отображение текущего состояния слова

                System.out.println("Кол-во букв: " + gameLogic.getNowLetter());
                char letter = ' ';
                boolean validInput = true;

                System.out.print("Введите букву загаданного слова или ?: ");
                while(validInput) {
                    String tmp = scanner.nextLine().trim();

                    if (tmp.equals("?")){
                        System.out.println("ПОДСКАЗКА: " + fileReader.clue());
                        System.out.print("Введите букву загаданного слова: ");
                        continue;
                    }
                    if (gameLogic.isValidStr(tmp)) {
                        letter = tmp.charAt(0); // Ввод буквы
                        validInput = false;
                    } else {
                        System.out.print("Ошибка! Введите одну букву загаданного слова или ?: ");
                    }
                }
                if (gameLogic.guessLetter(letter)) {
                    System.out.println("Вы угадали букву - " + letter);
                } else {
                    System.out.println("Ошибка! Нынешнее кол-во попыток : " + gameLogic.getErrorNow());
                    if (gameLogic.nowCount() > 0) {
                        gameState.printState
                            (gameLogic.nowCount() - 1, System.out);
                    }

                    /*if (CountError - gameLogic.getNowCount()  == 1) { //вывод подсказки
                        System.out.println("ПОДСКАЗКА: " + fileReader.clue());
                    }*/
                }

            }
            if (gameLogic.gameWon()) {
                gameLogic.currentState(System.out);
                System.out.println("Вы победили, поздравляю ;)");
            } else {
                System.out.println("Игра окончена, увы :( \nВаше слово было: " + gameLogic.word().toUpperCase());
            }

            System.out.print("Хотите сыграть еще [Д]а/[Н]ет = ");
            String scan2 = scanner.next();
            if (scan2.equalsIgnoreCase("Н")) {
                playAgain = false;
            }
            else scanner.nextLine();
        }
        System.out.println("     ***Спасибо за игру! :)))***      ");
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
