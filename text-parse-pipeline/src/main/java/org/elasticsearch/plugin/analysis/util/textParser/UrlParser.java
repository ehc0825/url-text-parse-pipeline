package org.elasticsearch.plugin.analysis.util.textParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {

    private final static String URL_REGEX="\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static String[] extractTorUrl(String contents) {
        String tor_tail = ".onion";
        return urlMatcher(contents,tor_tail);
    }
    public static String[] extractUrl(String contents) {
        return urlMatcher(contents,"");
    }

    private static String[] urlMatcher(String contents, String rule) {
        try {
            List<String> urlList = new ArrayList<>();
            Pattern pattern = Pattern.compile(URL_REGEX+rule,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(contents);
            while (matcher.find()) {
                urlList.add(matcher.group());
            }
            return urlList.stream().toArray(String[]::new);
        }
        catch (Exception e)
        {
            return null;
        }
    }


}
