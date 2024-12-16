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
    private int shift;                   // Сдвиг


    public CipherController(int shift, boolean isSaveCryptNewFile) {
        this.caesarCipher = new CaesarCipher();
        this.fileManager = new FileManager();
        this.consoleView = new ConsoleView();
        this.isSaveCryptNewFile = isSaveCryptNewFile;
        this.shift = shift;
    }

    public CipherController() {
        this(3, true); // значение по умолчанию: shift = 3, isSaveCryptNewFile = true
    }

    public void run() {

        boolean running = true;

        while (running) {
            consoleView.displayMenu();
            String choice = consoleView.getTextInput();
            switch (choice) {
                case "1" -> encryptText();
                case "2" -> decryptText();
                case "3" -> decryptTextBrutForce();
                case "4" -> running = false; // выход
                default ->  consoleView.displayError("Неверный выбор, попробуйте снова.");
            }
        }
        System.out.println("Завершение работы программы");
    }

    private void encryptText() {
        /* Обработка запроса на шифрование текста. */
        String filePath = consoleView.getFilePathInput(); // Получаем путь к файлу от пользователя
        String content = fileManager.readFile(filePath);  // Читаем текст из файла
        if (content != null) {
            consoleView.displayOrig(content);
            String encrypted = caesarCipher.encrypt(content, shift);  // Шифруем текст и указываем сдвиг, например 3
            if(isSaveCryptNewFile)
                fileManager.saveFileWithSuffix(filePath, encrypted, FileSuffix.ENCRYPTED); // Сохранение зашифрованного текста рядом с оригинальным файлом
            consoleView.displayResult(encrypted);
        } else {
            consoleView.displayError("Не удалось прочитать файл.");
        }
    }

    private void decryptText() {
        /* Обработка запроса на расшифровку текста */
        String filePath = consoleView.getFilePathInput(); // Получаем путь к файлу от пользователя
        String content = fileManager.readFile(filePath);  // Читаем текст из файла
        if (content != null) {
            consoleView.displayOrig(content);
            String decrypted = caesarCipher.decrypt(content, -shift); // Расшифровка текста. Сдвиг c минусом
            if(isSaveCryptNewFile)
                fileManager.saveFileWithSuffix(filePath, decrypted, FileSuffix.DECRYPTED); // Сохранение зашифрованного текста рядом с оригинальным файлом
            consoleView.displayResult(decrypted);
        } else {
            consoleView.displayError("Не удалось прочитать файл.");
        }
    }

    private void decryptTextBrutForce() {
        /* Обработка запроса на расшифровку текста методом грубой силы */
        String filePath = consoleView.getFilePathInput(); // Получаем путь к файлу от пользователя
        String content = fileManager.readFile(filePath);  // Читаем текст из файла
        if (content != null) {
            consoleView.displayOrig(content);
            String decrypted = caesarCipher.decryptBruteForce(content); // Расшифровка текста.
            if(isSaveCryptNewFile)
                fileManager.saveFileWithSuffix(filePath, decrypted, FileSuffix.DECRYPTED); // Сохранение зашифрованного текста рядом с оригинальным файлом
            consoleView.displayResult(decrypted);
        } else {
            consoleView.displayError("Не удалось прочитать файл.");
        }
    }

}
