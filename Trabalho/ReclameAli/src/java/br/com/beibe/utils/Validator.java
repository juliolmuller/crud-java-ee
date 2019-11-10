package br.com.beibe.utils;

import java.util.regex.Pattern;

public final class Validator {

    private Validator() {}

    public static boolean isEmail(String email) {
        if (email == null)
            return false;
        String regex = "^[\\w\\d\\.]+@[\\w\\d\\.]+\\.[A-Za-z]{2,6}";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public static boolean isName(String name) {
        if (name == null)
            return false;
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+";
        return Pattern.compile(regex).matcher(name).matches();
    }

    public static boolean isLogin(String login) {
        if (login == null)
            return false;
        String regex = "^\\w+";
        return Pattern.compile(regex).matcher(login).matches();
    }

    public static boolean isDate(String date) {
        return (Converter.toDate(date) != null);
    }

    public static boolean isCpf(String cpf) {
        if (cpf == null)
            return false;
        if (cpf.length() != 11 || Converter.removeNonDigits(cpf).length() != 11)
            return false;
        int sum = 0;
        for (int i = 0, div = 10; i < 9; i++, div--) {
            int num = (int) (cpf.charAt(i) - 48);
            sum += (num * div);
        }
        int rem = 11 - (sum % 11);
        char dig1;
        if (rem == 10 || rem == 11)
            dig1 = '0';
        else
            dig1 = (char) (rem + 48);
        if (dig1 != cpf.charAt(9))
            return false;
        sum = 0;
        for (int i = 0, div = 11; i < 10; i++, div--) {
            int num = (int) (cpf.charAt(i) - 48);
            sum += (num * div);
        }
        rem = 11 - (sum % 11);
        char dig2;
        if (rem == 10 || rem == 11)
            dig2 = '0';
        else
            dig2 = (char) (rem + 48);
        if (dig2 != cpf.charAt(10))
            return false;
        return true;
    }
}
