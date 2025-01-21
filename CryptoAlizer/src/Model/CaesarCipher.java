package Model;

import View.ConsoleView;

import java.util.*;

public class CaesarCipher {

    private final char[] ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш',
            'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш',
            'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
            '.', ',', '«', '»', '"', '\\', ':', '!', '?', ' '
    };


    private char[] shiftAlphabet(int shift){
        List<Character> shiftAlphabet = new ArrayList<>();

        for (char c : ALPHABET) {
            shiftAlphabet.add(c);
        }
        Collections.rotate(shiftAlphabet, -shift);

        char[] newAlphabet = new char[shiftAlphabet.size()];
        for (int i = 0; i < shiftAlphabet.size(); i++) {
            newAlphabet[i] = shiftAlphabet.get(i);
        }
        return newAlphabet;
    }


    public String encrypt(String text, int shift) {
        /* шифрование текста */
        return convertText(text, shift);
    }

    public String decrypt(String text, int shift) {
        /* расшифровка текста */
        return convertText(text, shift);
    }

    private void mapingSlovar(Set<String> hDictionaryWords, List<Integer> list, String partText){
        for (int i = 0; i < ALPHABET.length; i++) {
            list.add(i,0);
            String[] splitText = decrypt(partText, -i).replaceAll("[^A-Za-zА-Яа-я0-9]", " ").split(" ");

            int matchCount = 0; // Количество совпадений с словарём
            for (String word : splitText) {
                if (hDictionaryWords.contains(word))
                    matchCount++;
            }
            list.set(i, matchCount);
        }
    }

    public String decryptBruteForce(String text) {
        /* расшифровка текста Взлом (Brute Force) */
        Set<String> hDictionaryWords = loadDictionaryWords(); // Грузим Словарь
        List<Integer> maxMatchesShift = new ArrayList<>(); // Список количества совпадений после мапинга по словарю

        String partText = text.substring(1,300).toLowerCase();  // Только часть текста для скорости подбора
        mapingSlovar(hDictionaryWords, maxMatchesShift, partText);

        int maxCountMatches = Collections.max(maxMatchesShift);
        validateMaxCountMatches(maxCountMatches);

        int shift = searhShiftOfList(maxMatchesShift, maxCountMatches);

        ConsoleView.displayNumberShift(shift);
        return convertText(text, -shift);
    }

    // Чтение словаря
    private String dictionaryWords (){
        return FileManager.readDictionaryOfWords();
    }

    private HashSet<String> loadDictionaryWords(){
        HashSet<String> hDictionaryWords = new HashSet<String>();
        String[] words = dictionaryWords().split(",");
        for (String word : words) {
            word = word.trim();
            if (!word.isEmpty())
                hDictionaryWords.add(word);
        }
        return hDictionaryWords;
    }

    private String convertText (String text, int shift){
        HashMap<Character, Integer > mapAlphabet = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            mapAlphabet.put(ALPHABET[i], i);
        }

        char[] shiftAlphabet = shiftAlphabet(shift);
        char[] chText =  text.toCharArray();
        char[] chNewText = new char[chText.length];

        for (int i = 0; i < chText.length; i++) {
            if (mapAlphabet.containsKey(chText[i])){
                chNewText[i] = shiftAlphabet[mapAlphabet.get(chText[i])];

            } else chNewText[i]=chText[i];
        }

        return new String(chNewText);
    }

    private int searhShiftOfList(List<Integer> list, int max){
        // Поиск ключа
        int shift = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == max)
                shift = i;
        }
        return shift;
    }

    private void validateMaxCountMatches(int maxCountMatches) {
        if (maxCountMatches == 0) {
            throw new IllegalArgumentException("Не удалось подобрать ключ");
        }
    }

}
