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

        System.out.println("Исходный текст:");
        System.out.println(text);
        System.out.println("Результат текст:");
        String newText = new String(chNewText);
        System.out.println(newText);
        return newText;
    }

    public String decrypt(String text, int shift) {
        /* расшифровка текста */
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

        System.out.println("Исходный текст:");
        System.out.println(text);
        System.out.println("Результат текст:");
        String newText = new String(chNewText);
        System.out.println(newText);
        return newText;
    }


}
