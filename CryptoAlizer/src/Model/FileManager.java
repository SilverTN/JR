package Model;

import java.nio.file.*;

public class FileManager {

    public String readFile(String filePath) {
        /* чтение текста из файла */
        Path path = Paths.get(filePath);
        try {
            return Files.readString(path);
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }
    public void writeFile(String filePath, String content) {
        /* запись текста в файл */
        Path path = Paths.get(filePath);
        try {
            Files.writeString(path, content);
        } catch (Exception e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public void saveFileWithSuffix(String originalFilePath, String encryptedContent, FileSuffix suffix) {
        Path originalPath = Paths.get(originalFilePath);       // Определяем путь к оригинальному файлу
        String directory = originalPath.getParent().toString();// Получаем директорию оригинального файла
        String newFileName = originalPath.getFileName().toString().replace(".", suffix.getSuffix() + ".");
        Path newPath  = Paths.get(directory, newFileName);     // Собираем полный путь к зашифрованному файлу
        writeFile(newPath .toString(), encryptedContent);      // Записываем зашифрованный текст в новый файл
    }

    public static String readDictionaryOfWords(){
        /* чтение словаря из файла */
        Path path = Paths.get("src/resources/DictionaryOfWords.txt");
        try {
            return Files.readString(path);
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }
}
