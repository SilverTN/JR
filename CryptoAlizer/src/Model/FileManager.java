package Model;

import java.io.IOException;
import java.nio.file.*;

public class FileManager {

    public String readFile(String filePath) {
        /* чтение текста из файла */
        Path path = Paths.get(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null; // Возвращаем null в случае ошибки
        }
    }
    public void writeFile(String filePath, String content) {
        /* запись текста в файл */
        Path path = Paths.get(filePath);
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public void saveFileWithSuffix(String originalFilePath, String encryptedContent, FileSuffix suffix) {
        Path originalPath = Paths.get(originalFilePath);// Определяем путь к оригинальному файлу
        String directory = originalPath.getParent().toString();// Получаем директорию оригинального файла
        String newFileName = originalPath.getFileName().toString().replace(".", suffix.getSuffix() + "."); // Создаем имя нового файла (можно добавить суффикс или изменить расширение) заменяем точку на _encrypted.
        Path newPath  = Paths.get(directory, newFileName); // Собираем полный путь к зашифрованному файлу
        writeFile(newPath .toString(), encryptedContent);// Записываем зашифрованный текст в новый файл
    }
}
