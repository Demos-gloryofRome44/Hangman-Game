package backend.academy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import lombok.Getter;

public class OpenFile {
    @Getter private String word = null;
    @Getter private String clue = null;
    private Map<String, List<String>> wordMap = new HashMap<>(); //пара секция список секции
    private Map<String, List<String>> clueMap = new HashMap<>(); // пара секция подсказки к ней
    private static final String FILE_PATH = "/Users/Egor/Desktop/Java/"
        + "backend_academy_2024_project_1-java-Demos-gloryofRome44"
        + "/src/main/java/backend/academy"
        + "/dictionary.txt";
    private static final Logger LOG = Logger.getLogger(OpenFile.class.getName());

    public OpenFile(int topic, int level) {
        try {
            this.word = chooseWord(topic, System.out, level);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при выборе слова: " + e.getMessage());
        }
    }

    private String chooseWord(int type, PrintStream out, int level) {
        String chooseword = null;
        String filepath = FILE_PATH;

        Topic topic = Topic.fromValue(type);
        switch (topic) {
            case ANIMAL:
                chooseword = readfile(filepath, topic.getName(), level);
                break;
            case TOWN:
                chooseword = readfile(filepath, topic.getName(), level);
                break;
            case PERSON:
                chooseword = readfile(filepath, topic.getName(), level);
                break;
            case MIX:
                Topic[] topics = {Topic.ANIMAL, Topic.TOWN, Topic.PERSON};
                Topic random = topics[(int) (Math.random() * topics.length)];
                chooseword = readfile(filepath, random.getName(), level);
                break;
            default:
                out.println("Неверный ввод");
                return null;
        }
        return chooseword;
    }

    private String readfile(String filename, String section, int level) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String fullsection = null;
            String currentsection = null;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("[") && line.endsWith("]")) {
                    fullsection = line.substring(1, line.length() - 1);
                    String[] sectionMap = fullsection.split(":");
                    if (sectionMap.length == 2 && Integer.parseInt(sectionMap[1]) == level) {
                        currentsection = sectionMap[0];
                        wordMap.put(currentsection, new ArrayList<>()); // Инициализация списка слов
                        clueMap.put(currentsection, new ArrayList<>()); // Инициализация списка подсказок
                    } else {
                        currentsection = null;
                    }
                } else if (currentsection != null) {
                    if (line.contains("=")) {
                        String[] wordclue = line.split("=", 2);
                        String newWord = wordclue[0].trim(); // слово
                        String newClue = wordclue[1].trim(); // подсказка

                        wordMap.get(currentsection).add(newWord);
                        clueMap.get(currentsection).add(newClue); // Сохранение подсказки в clue_map

                    }
                }
            }

            selectRandomWord(section);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("IO error: " + e.getMessage(), e);
        }

        return this.word; // Возвращаем загаданное слово
    }


}
