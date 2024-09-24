package net.flectone.lab.one;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StringLab {

    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6})$");

    private static boolean isHexString(String string) {
        return HEX_COLOR_PATTERN.matcher(string).find();
    }

    private static String replaceFirstSymbolToLowerCase(String string, String symbol) {
        StringBuilder stringBuilder = new StringBuilder();

        String lowerCaseSymbol = symbol.toLowerCase();

        for (String word : string.split(" ")) {
            if (word.startsWith(symbol)) {
                word = lowerCaseSymbol + word.substring(1);
            }

            stringBuilder.append(word).append(" ");
        }

        return stringBuilder.toString();
    }

    private static int countSymbol(String string, String symbol) {
        int k = 0;

        for (String word : string.split(" ")) {
            if (word.startsWith(symbol)) {
                k++;
            }
        }

        return k;
    }

    public static int maxSymbolsSubsequence(String string) {
        int max = 0;
        int k = 1;
        char lastSymbol = string.charAt(0);

        for (char symbol : string.toCharArray()) {
            if (symbol > lastSymbol) {
                k++;
            } else {
                if (k > max) {
                    max = k;
                }

                k = 1;
            }

            lastSymbol = symbol;
        }

        if (k > max) {
            max = k;
        }

        return max;
    }


    public static void main(String[] args) {
        // Задание:
        // Пользователь вводит текст, состоящий из слов, разделенных пробелами.
        // В словах, начинающихся на «А» заменить эту букву на «а».
        // В полученном текст подсчитать количество слов, начинающихся на «а».
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        String replacedString = replaceFirstSymbolToLowerCase(inputString, "А");

        int count = countSymbol(replacedString, "а");

        System.out.println("Количество слов, начинающихся на \"а\" = " + count);

        // Задание:
        // Написать регулярное выражение, определяющее является ли данная строчка
        // шестнадцатиричным идентификатором цвета в HTML.
        // Где #FFFFFF для белого, #000000 для черного, #FF0000 для красного и т.д.
        // пример правильных выражений: #FFFFFF, #FF3421, #00ff00.
        // пример неправильных выражений: 232323, f#fddee, #fd2.
        String hexString = "#00ff00";
        System.out.println(hexString + " - HEX строка? " + isHexString(hexString));

        // Задание:
        // Возрастающей подпоследовательностью будем называть последовательность символов, расположенных
        // в порядке увеличения их номера в кодовой таблице символов ASCII.
        // Во веденной строке определите длину наибольшей возрастающей подпоследовательности, содержащейся в ней.
        String symbols = "аббвгиабвг";
        System.out.println("Строка \"" + symbols + "\", её максимальная возрастающая последовательность символов = " + maxSymbolsSubsequence(symbols));
    }
}
