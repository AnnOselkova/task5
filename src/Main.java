import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача 1");
        System.out.println("encrypt(\"Hello\") = " + Arrays.toString(encrypt("Hello")));
        System.out.println("decrypt([ 72, 33, -73, 84, -12, -3, 13, -13, -68 ]) -> " + decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println("encrypt(\"Sunshine\") = " + Arrays.toString(encrypt("Sunshine")));
        System.out.println("\nЗадача 2");
        System.out.println("canMove(\"Rook\", \"A8\", \"H8\") -> " + canMove("Rook", "A8", "H8"));
        System.out.println("canMove(\"Bishop\", \"A7\", \"G1\") -> " + canMove("Bishop", "A7", "G1"));
        System.out.println("canMove(\"Queen\", \"C4\", \"D6\") -> " + canMove("Queen", "C4", "D6"));
        System.out.println("\nЗадача 3");
        System.out.println("canComplete(\"butl\", \"beautiful\") -> " + canComplete("butl", "beautiful"));
        System.out.println("canComplete(\"butlz\", \"beautiful\") -> " + canComplete("butlz", "beautiful"));
        System.out.println("canComplete(\"tulb\", \"beautiful\") -> " + canComplete("tulb", "beautiful"));
        System.out.println("canComplete(\"bbutl\", \"beautiful\") -> " + canComplete("bbutl", "beautiful"));
        System.out.println("\nЗадача 4");
        System.out.println("sumDigProd(16, 28) -> " + sumDigProd(16, 28));
        System.out.println("sumDigProd(0) -> " + sumDigProd(0));
        System.out.println("sumDigProd(1, 2, 3, 4, 5, 6) -> " + sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println("\nЗадача 5");
        System.out.println("sameVowelGroup([\"toe\", \"ocelot\", \"maniac\"]) -> " + Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println("sameVowelGroup([\"many\", \"carriage\", \"emit\", \"apricot\", \"animal\"]) -> " + Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println("sameVowelGroup([\"hoops\", \"chuff\", \"bot\", \"bottom\"]) -> " + Arrays.toString(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})));
        System.out.println("\nЗадача 6");
        System.out.println("validateCard(1234567890123456) -> " + validateCard(1234567890123456L));
        System.out.println("validateCard(1234567890123452) -> " + validateCard(1234567890123452L));
        System.out.println("\nЗадача 7");
        System.out.println("numToEng(18) -> " + numToEng(18));
        System.out.println("numToEng(117) -> " + numToEng(117));
        System.out.println("numToEng(0) -> " + numToEng(0));
        System.out.println("numToRus(18) -> " + numToRus(18));
        System.out.println("numToRus(356) -> " + numToRus(356));
        System.out.println("numToRus(0) -> " + numToRus(0));
        System.out.println("\nЗадача 8");
        System.out.println("getSha256Hash(\"password123\") -> " + getSha256Hash("password123"));
        System.out.println("getSha256Hash(\"Fluffy@home\") -> " + getSha256Hash("Fluffy@home"));
        System.out.println("getSha256Hash(\"Hey dude!\") -> " + getSha256Hash("Hey dude!"));
        System.out.println("\nЗадача 9");
        System.out.println("correctTitle(\"jOn SnoW, kINg IN thE noRth.\") -> " + correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println("correctTitle(\"sansa stark, lady of winterfell.\") -> " + correctTitle("sansa stark, lady of winterfell."));
        System.out.println("correctTitle(\"TYRION LANNISTER, HAND OF THE QUEEN.\") -> " + correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("\nЗадача 10");
        System.out.println("hexLattice(1) -> " + hexLattice(1));
        System.out.println("hexLattice(7) -> " + hexLattice(7));
        System.out.println("hexLattice(19) -> " + hexLattice(19));
        System.out.println("hexLattice(37) -> " + hexLattice(37));

    }

    
    public static int[] encrypt(String word) {
        int[] e = new int[word.length()];
        e[0] = word.charAt(0); // записываем в массив индекс первого символа
        int p = e[0];
        for (int i = 1; i < word.length(); i++) {
            e[i] = word.charAt(i) - p;
            p = word.charAt(i);
        }
        return e;
    }

   
    public static String decrypt(int[] e) {
        String d = "" + (char) e[0];
        int p = e[0];
        for (int i = 1; i < e.length; i++) {
            d += (char) (e[i] + p);
            p = e[i] + p;
        }
        return d;
    }

    
    public static boolean canMove(String figure, String start, String end) {
        int startRow = start.charAt(0) - 'A';
        int startCol = start.charAt(1) - '1';
        int endRow = end.charAt(0) - 'A';
        int endCol = end.charAt(1) - '1';
        if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 || endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7)
            return false;
        switch (figure) {
            case "Pawn": // пешка
                return startRow == endRow && (endCol - startCol == 1 || (startCol == 1 && endCol - startCol == 2));
            case "Rook": // ладья
                return startRow == endRow || startCol == endCol;
            case "Knight": // конь
                return Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1 || Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2;
            case "Bishop": //слон ( на искосок)
                return Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
            case "Queen":
                return startRow == endRow || startCol == endCol || Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
            case "King":
                return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
            default:
                return false;
        }
    }

    
    public static boolean canComplete(String subword, String word) {
        int s = 0;
        for (int i = 0; i < subword.length(); i++) {
            int idx = word.indexOf(subword.charAt(i), s);
            if (idx == -1) return false;
            s = idx + 1;
        }
        return true;
    }

    
    public static int sumDigProd(int... n) { // можем принимать на входе неограничеснное количество int переменных
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
        }
        while (sum > 9) {
            int p = 1;
            while (sum > 0) {
                p *= (sum % 10);
                sum /= 10;
            }
            sum = p;
        }
        return sum;
    }

    // Вспомогательная функция
    public static int countVowels(String word) {
        final String v = "aeiouy";
        StringBuilder a = new StringBuilder (); // находит символ в строке и проверяет на уникальность гласных в слове
        int sum = 0;
        for (char lit : word.toLowerCase().toCharArray()) {
            if (v.indexOf(lit) != -1 && a.indexOf(lit + "")==-1) { // если гласная и если нет в уникальных 
                sum += lit;
                a.append(lit); // записываем уникальные гласные
            }
        }
        return sum;
    }

    
    public static String[] sameVowelGroup(String[] words) {
        String[] result = new String[words.length];
        int n = 0;
        int baseVowels = countVowels(words[0]);
        for (int i = 0; i < words.length; i++) {
            if (baseVowels == countVowels(words[i])) {
                result[n++] = words[i];
            }
        }
        return Arrays.copyOf(result, n);
    }

    
    public static boolean validateCard(long number) {
        String num = number + ""; //делаем из числа строку
        if (num.length() < 14 || num.length() > 19) return false;
        int sum = 0;
        int last = Integer.parseInt(num.charAt(num.length() - 1) + ""); //последняя цифра числа
        for (int i = 0; i < num.length() - 1; i++) {
            int d = Integer.parseInt(num.charAt(i) + ""); //цифра числа
            if (i % 2 == 0) {
                d *= 2;
                if (d > 9) d -= 9;
            }
            sum += d;
        }
        return (10 - sum % 10) == last;
    }

    
    public static String numToEng(int number) {
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String hundred = "hundred";
        StringBuilder answer = new StringBuilder();
        if (number > 99) {
            answer.append(digits[number / 100]).append(" ").append(hundred).append(" ");
            number %= 100;
        }
        if (number > 19) {
            answer.append(tens[number / 10 - 2]).append(" ");
            number %= 10;
            answer.append(digits[number]);
        } else {
            if (number > 9) {
                answer.append(teens[number - 10]);
            } else {
                answer.append(digits[number]);
            }
        }
        return answer.toString();
    }

    // На русском языке 
    public static String numToRus(int number) {
        String[] digits = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        String[] teens = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        String[] tens = {"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[] hundreds = {"сто", "двести", "триста", "четыреста", "пятьсот", "семьсот", "восемьсот", "девятьсот"};
        StringBuilder answer = new StringBuilder();
        if (number > 99) {
            answer.append(hundreds[number / 100 - 1]).append(" ");
            number %= 100;
        }
        if (number > 19) {
            answer.append(tens[number / 10 - 2]).append(" ");
            number %= 10;
            answer.append(digits[number]);
        } else {
            if (number > 9) {
                answer.append(teens[number - 10]);
            } else {
                answer.append(digits[number]);
            }
        }
        return answer.toString();
    }

    
    public static String getSha256Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public static String correctTitle(String input){
        String[] words = input.split(" "); // делим строку на слова и слова записываем в массив
        List<String> s = Arrays.stream(new String[]{"the", "of", "in", "and"}).toList();
        for(int i = 0; i < words.length; i++){
            String word = words[i].toLowerCase();
            if(s.contains(word)){
                words[i] = word;
            }
            else{
                words[i] = word.substring(0, 1).toUpperCase() + word.substring(1);
            }
        }
        return String.join(" ", words);// соединение строк
    }

    
    public static String hexLattice(int n){
        if(n == 1)return " o ";
        int num = 1;
        int count = 0; // размер стороны решетки
        while(num != n){
            num = 3 * count * (count - 1) + 1;
            count++;
        }
        if(num != n)return "Invalid";
        StringBuilder a = new StringBuilder();
        int side  = count - 1; //сторона
        int mid = side * 2 + 1; // середина решетки
        for(int i = 0; i < count - 1; i++){ // определяем количество пробелов от начала строки до о
            for(int j = 0; j < mid - side - 2; j++){
                a.append(" ");
            }
            for(int d = 0; d < side; d++){ // выводим первый символ в строке
                if(d == 0)answer.append("o");
                else a.append(" o");
                }
            for(int j = 0; j < (mid - side - 2); j++){
                a.append(" ");
            }
            a.append("\n");
            side++;
        }
        side = side - 2;
        for(int i = 0; i < counter - 2; i++){
            for(int j = 0; j < (mid - side - 2); j++){
                a.append(" ");
            }
            for(int k = 0; k < side; k++){
                if(k == 0)answer.append("o");
                else a.append(" o");
            }
            for(int j = 0; j < (mid - side - 2); j++){
                a.append(" ");
            }
            a.append("\n");
            side--;
        }
        return a.toString().trim();
    }
}
