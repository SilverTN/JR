package Model;

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

    public String decryptBruteForce(String text) {
        /* расшифровка текста Взлом (Brute Force) */
        HashSet<String> hDictionaryWords = loadDictionaryWords();
        ArrayList<Integer> maxMatchesShift = new ArrayList<>();

        String partText = text.substring(1,300);  // Часть текста
        for (int i = 0; i < ALPHABET.length; i++) {
           maxMatchesShift.add(i,0);
           String[] splitText = convertText(partText, -i).replaceAll("[^A-Za-zА-Яа-я0-9]", " ").toLowerCase().split(" ");
           for (int j = 0; j < splitText.length; j++) {
              if (hDictionaryWords.contains(splitText[j].toLowerCase())){
                     maxMatchesShift.set(i, maxMatchesShift.get(i) + 1);
              }
           }
        }

        int max = Collections.max(maxMatchesShift);
        if (max == 0){
            throw new IllegalArgumentException("Не удалось подобрать ключ");
        }

        int shift = 0;
        for (int i = 0; i < maxMatchesShift.size(); i++) {
          if (maxMatchesShift.get(i) == max)
              shift = i;
        }

        System.out.println("=== КЛЮЧ ["+ shift +"] ====");
        return convertText(text, -shift);
    }

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

}
