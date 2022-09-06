package org.apache.logging.log4j.util;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class Strings {
    public static final String EMPTY = "";
    public static final String LINE_SEPARATOR = PropertiesUtil.getProperties().getStringProperty("line.separator", "\n");

    private Strings() {
    }

    public static String dquote(String str) {
        return '\"' + str + '\"';
    }

    public static boolean isBlank(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static String join(Iterable<?> iterable, char c) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), c);
    }

    public static String left(String str, int i) {
        if (str == null) {
            return null;
        }
        return i < 0 ? "" : str.length() <= i ? str : str.substring(0, i);
    }

    public static String quote(String str) {
        return Chars.QUOTE + str + Chars.QUOTE;
    }

    public static String repeat(String str, int i) {
        Objects.requireNonNull(str, "str");
        if (i >= 0) {
            StringBuilder sb = new StringBuilder(str.length() * i);
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(str);
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("count");
    }

    public static String toRootUpperCase(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static String trimToNull(String str) {
        String trim = str == null ? null : str.trim();
        if (isEmpty(trim)) {
            return null;
        }
        return trim;
    }

    public static String join(Iterator<?> it2, char c) {
        if (it2 == null) {
            return null;
        }
        if (!it2.hasNext()) {
            return "";
        }
        Object next = it2.next();
        if (!it2.hasNext()) {
            return Objects.toString(next, "");
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it2.hasNext()) {
            sb.append(c);
            Object next2 = it2.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }
}
