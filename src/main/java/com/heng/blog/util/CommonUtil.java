package com.heng.blog.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommonUtil {
    private static final DateTimeFormatter englishDateFormatter;
    private static final Map<Long, String> daysLookup;

    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(englishDateFormatter);
    }

    // 将title转换为slug格式：“this is a title”->“this-is-a-title”
    public static String toSlug(String title) {
        return String.join("-", title.toLowerCase()
                .replace("\n", " ")
                .replace("[^a-z\\d\\s]", " ")
                .split(" "))
                .replace("-+", "-");
    }

    static {
        daysLookup = buildDaysLookup();
        englishDateFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .appendLiteral(" ")
                .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
                .appendLiteral(" ")
                .appendPattern("yyyy")
                .toFormatter(Locale.ENGLISH);
    }

    private static Map<Long, String> buildDaysLookup() {
        Map<Long, String> ret = new HashMap<>();
        for (int i = 1; i <= 31; i++) {
            ret.put((long) i, getDayOfMonthSuffix(i));
        }
        return ret;
    }

    // 根据天数的个位获取对应的后缀
    private static String getDayOfMonthSuffix(int n) {
        switch (n % 10) {
            case 1:
                return n + "st";
            case 2:
                return n + "nd";
            case 3:
                return n + "rd";
            default:
                return n + "th";
        }
    }
}
