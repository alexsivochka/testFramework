package at.utils;

import at.exception.RegexpException;
import lombok.extern.log4j.Log4j;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j
public class RegularExpression {

    public String getTextMatch(String strSearch, String patterSearch, int groupNumber) {
        String result = null;
        Pattern pattern = Pattern.compile(patterSearch);
        Matcher matcher = pattern.matcher(strSearch);
        if (matcher.find()) {
            result = matcher.group(groupNumber);
        }
        if (result==null || result.isEmpty()) {
            throw new RegexpException("Нет данных согласно паттерну: " + patterSearch + " Поиск по тексту: " + strSearch);
        }
        return result;
    }

    public static String getTextMatch(String strSearch, String patterSearch) {
        String result = null;
        Pattern pattern = Pattern.compile(patterSearch);
        Matcher matcher = pattern.matcher(strSearch);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    public static String matches(String text, String regex) {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {
            result.append( m.group());
        }
        if (result.toString().isBlank()) {
            throw new RegexpException("Нет данных согласно паттерну: " + regex + " Поиск по тексту: " + text);
        }
        return result.toString();
    }

}
