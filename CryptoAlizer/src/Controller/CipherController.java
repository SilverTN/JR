package Controller;

import Model.CaesarCipher;
import Model.FileManager;
import Model.FileSuffix;
import View.ConsoleView;

public class CipherController {
    private CaesarCipher caesarCipher;
    private FileManager fileManager;
    private ConsoleView consoleView;
    private boolean isSaveCryptNewFile;  // Сохранять новый файл рядом
    private int shift; // Сдвиг

    public CipherController() {
        caesarCipher = new CaesarCipher();
        fileManager = new FileManager();
        consoleView = new ConsoleView();
        this.isSaveCryptNewFile = true;
        this.shift = 3;
    }

    public CipherController(int shift) {
        // Инициализация объектов
        caesarCipher = new CaesarCipher();
        fileManager = new FileManager();
        consoleView = new ConsoleView();
        this.isSaveCryptNewFile = true;
        this.shift = shift;
    }

    public CipherController(boolean isSaveCryptNewFile) {
        // Инициализация объектов
        caesarCipher = new CaesarCipher();
        fileManager = new FileManager();
        consoleView = new ConsoleView();
        this.isSaveCryptNewFile = isSaveCryptNewFile;
        this.shift = 3;
    }

    public CipherController(int shift, boolean isSaveCryptNewFile) {
        // Инициализация объектов
        caesarCipher = new CaesarCipher();
        fileManager = new FileManager();
        consoleView = new ConsoleView();
        this.isSaveCryptNewFile = isSaveCryptNewFile;
        this.shift = shift;
    }

    public void run() {

        boolean running = true;

        while (running) {
            consoleView.displayMenu();
            String choice = consoleView.getTextInput();
            switch (choice) {
                case "1" -> encryptText();
                case "2" -> decryptText();
                case "3" -> running = false; // выход
                default ->  consoleView.displayError("Неверный выбор, попробуйте снова.");
            }
            System.out.println("Завершение работы программы");
        }
    }

    private void encryptText() {
        /* обработка запроса на шифрование текста. */
        String filePath = consoleView.getFilePathInput(); // Получаем путь к файлу от пользователя
        String content = fileManager.readFile(filePath); // Читаем текст из файла
        if (content != null) {
            String encrypted = caesarCipher.encrypt(content, shift);  // Шифруем текст и выводим результат используем сдвиг, например 3
            if(isSaveCryptNewFile)
                fileManager.saveFileWithSuffix(filePath, encrypted, FileSuffix.ENCRYPTED); // Сохранение зашифрованного текста рядом с оригинальным файлом
            consoleView.displayResult(encrypted);
        } else {
            consoleView.displayError("Не удалось прочитать файл.");
        }
    }

    private void decryptText() {
        /* обработка запроса на расшифровку текста */
        String filePath = consoleView.getFilePathInput(); // Получаем путь к файлу от пользователя
        String content = fileManager.readFile(filePath);
        if (content != null) {
            // Расшифровка текста
            String decrypted = caesarCipher.decrypt(content, -shift); // Используем тот же сдвиг
            if(isSaveCryptNewFile)
                fileManager.saveFileWithSuffix(filePath, decrypted, FileSuffix.DECRYPTED); // Сохранение зашифрованного текста рядом с оригинальным файлом
            consoleView.displayResult(decrypted);
        } else {
            consoleView.displayError("Не удалось прочитать файл.");
        }
    }


}
