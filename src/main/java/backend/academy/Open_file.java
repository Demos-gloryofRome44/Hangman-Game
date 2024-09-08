package backend.academy;

import java.io.BufferedReader;
import java.io.FileReader;


public class Open_file{
    private String read_file(String filename) {
        String word = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int count_1 = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                count_1++;
            }

            int number_line = (int) (Math.random() * count_1);

            reader.close();

            try (BufferedReader reader_2 = new BufferedReader(new FileReader(filename))) {
                int count_2 = 0;
                while ((line = reader_2.readLine()) != null) {
                    if (count_2 == number_line) {
                        word = line; // Сохранение нужной строки в word
                        break;
                    }
                    count_2++;
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
        return word;
    }
}
