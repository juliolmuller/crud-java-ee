package br.com.beibe.utils;

import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public final class Converter {

    private Converter() {}

    public static String removeNonDigits(String str) {
        if (str == null)
            return null;
        return nullable(str.replaceAll("\\D", ""));
    }

    public static Date toDate(String strDate) {
        if (strDate == null)
            return null;
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
        if (strDate == null)
            return null;
        try {
            return new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String toCpf(String cpf) {
        if (cpf == null)
            return null;
        return nullable(cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
    }

    public static String nullable(String str) {
        if (str == null)
            return null;
        if (str.trim().equals(""))
            return null;
        return str.trim();
    }
}
