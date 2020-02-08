import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Scanner;

/**
 *
 * Convert number from binary, octal, hexadecimal and decimal numbers to other types
 * @author: Hoang Xuan Vinh Ngo
 *
 */
public class NumberConverter {

    private static final String[] HEXLIST = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * Take user number input in one base and convert to other bases
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a number: ");
        String input = reader.nextLine();
        System.out.println("Enter number base (in number): ");
        int base = Integer.parseInt(reader.nextLine());
        if (base != 2 & base != 8 & base != 10 & base != 16) {
            throw new Exception("Unsupported number base");
        }
        String binary;
        String octa;
        String decimal;
        String hexa;
        switch(base) {
            case 2:
                if (!isNumeric(input)) {
                    throw new NumberFormatException("Incorrect number format");
                }
                binary = input;
                decimal = convertToDecimal(binary, base);
                octa = convertDecimal(Integer.parseInt(decimal), 8);
                hexa = convertDecimal(Integer.parseInt(decimal), 16);
                System.out.println("Octa: " + octa);
                System.out.println("Decimal: " + decimal);
                System.out.println("Hexa: " + hexa);
                break;
            case 8:
                if (!isNumeric(input)) {
                    throw new NumberFormatException("Incorrect number format");
                }
                octa = input;
                decimal = convertToDecimal(octa, base);
                binary = convertDecimal(Integer.parseInt(decimal), 2);
                hexa = convertDecimal(Integer.parseInt(decimal), 16);
                System.out.println("Binary: " + binary);
                System.out.println("Decimal: " + decimal);
                System.out.println("Hexa: " + hexa);
                break;
            case 10:
                if (!isNumeric(input)) {
                    throw new NumberFormatException("Incorrect number format");
                }
                decimal = input;
                binary = convertDecimal(Integer.parseInt(decimal), 2);
                octa = convertDecimal(Integer.parseInt(decimal), 8);
                hexa = convertDecimal(Integer.parseInt(decimal), 16);
                System.out.println("Binary: " + binary);
                System.out.println("Octa: " + octa);
                System.out.println("Hexa: " + hexa);
                break;
            case 16:
                hexa = input;
                decimal = convertToDecimal(hexa, base);
                binary = convertDecimal(Integer.parseInt(decimal), 2);
                octa = convertDecimal(Integer.parseInt(decimal), 8);
                System.out.println("Binary: " + binary);
                System.out.println("Octa: " + octa);
                System.out.println("Decimal: " + decimal);
                break;
        }
    }

    /**
     * Convert number from 2, 8, 16 base to decimal base
     * @param other
     * @param base
     */
    private static String convertToDecimal(String other, int base) {
        int counter = other.length() - 1;
        int result = 0;
        for (int i = 0; i < other.length(); i++) {
            String letter = String.valueOf(other.charAt(i));
            if (!isNumeric(letter)) {
                // hex [A-F]
                for (int k = 10; k < 16; k++) {
                    if (letter.equals(HEXLIST[k])) {
                        result += Math.pow(base, counter) * k;
                        break;
                    }
                }
            } else {
                result += Math.pow(base, counter) * Integer.parseInt(letter);
            }
            counter--;
        }
        return String.valueOf(result);
    }

    /**
     * Convert decimal number to other base (2, 8, 16)
     * @param number
     * @param base
     */
    private static String convertDecimal(int number, int base) {
        int counter = 0;
        if (number >= 0 && number < base) {
            if (base < 10) {
                return String.valueOf(number);
            } else if (base == 16) {
                return HEXLIST[number];
            }
        }
        String result = "";
        while (number >= Math.pow(base, counter)) {
            counter++;
        }
        for (int i = 0; i < counter; i++) {
            if (number % base >= 10) {
                result += HEXLIST[number % base];
            } else {
                result += Integer.toString(number % base);
            }
            number = number / base;
        }
        result = reverseString(result);
        return result;
    }

    /**
     * Helper methods: isNumeric() and reverseString()
     */
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String reverseString(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}
