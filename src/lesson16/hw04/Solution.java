package lesson16.hw04;

public class Solution {
    public static void main(String[] args) {
        String address = "http://gromcode.com";
        System.out.println(validate(address));

        address = "http://grom2code.com";
        System.out.println(validate(address));

        address = "http://grom2code/.com";
        System.out.println(validate(address));

        address = "http://grom.2code.com";
        System.out.println(validate(address));

        address = "https://grom2code.org";
        System.out.println(validate(address));

        address = "https:/grom2code.org";
        System.out.println(validate(address));

        address = "https://grom2code.org.net";
        System.out.println(validate(address));

        address = "https://www.grom2code.org";
        System.out.println(validate(address));
    }

    public static boolean validate(String address) {
        if (address == null)
            return false;
        String[] strings1 = address.split("//");
        if (checkStartAddress(strings1[0])) {
            String[] strings2 = strings1[1].split("\\.");
            if (checkDomen(strings2[strings2.length - 1])) {
                if (checkNameAddress(strings2))
                    return true;
            }
        }
        return false;
    }

    private static boolean checkStartAddress(String string) {
        if (string.equals("http:") || string.equals("https:"))
            return true;
        return false;
    }

    private static boolean checkDomen(String string) {
        if (string.equals("com") || string.equals("org") || string.equals("net"))
            return true;
        return false;
    }

    private static boolean checkNameAddress(String[] strings) {
        if(!strings[0].equals("www")) {
            if (strings.length > 2)
                return false;
        }
        for (String string : strings) {
            if (!checkWord(string))
                return false;
        }
        return true;
    }

    private static boolean checkWord(String word) {
        char[] chars = word.toCharArray();

        for (char ch : chars) {
            if (!((65 <= ch && ch <= 90) || (97 <= ch && ch <= 122) || (1040 <= ch && ch <= 1103) || (48 <= ch && ch <= 57))) {
                return false;
            }
        }
        return true;
    }
}
