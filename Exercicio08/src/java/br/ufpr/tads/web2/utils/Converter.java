package br.ufpr.tads.web2.utils;

import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public final class Converter {

    private Converter() {
    }

    public static String removeNonDigits(String str) {
        return str.replaceAll("\\D", "");
    }

    public static Date toDate(String strDate) {
        Date date;
        String regex = "^(\\d{1,2})/(\\d{1,2})/(\\d{4})";
        if (Pattern.compile(regex).matcher(strDate).matches()) {
            date = toDate(strDate, "dd/MM/yyyy");
            if (date != null)
                return date;
        }
        regex = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})";
        if (Pattern.compile(regex).matcher(strDate).matches()) {
            date = toDate(strDate, "yyyy-MM-dd");
            if (date != null)
                return date;
        }
        return null;
    }

    public static Date toDate(String strDate, String format) {
        try {
            return new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public static String toCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
