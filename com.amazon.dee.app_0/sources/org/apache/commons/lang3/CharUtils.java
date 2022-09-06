package org.apache.commons.lang3;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class CharUtils {
    public static final char CR = '\r';
    public static final char LF = '\n';
    private static final String[] CHAR_STRING_ARRAY = new String[128];
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        char c = 0;
        while (true) {
            String[] strArr = CHAR_STRING_ARRAY;
            if (c < strArr.length) {
                strArr[c] = String.valueOf(c);
                c = (char) (c + 1);
            } else {
                return;
            }
        }
    }

    public static int compare(char c, char c2) {
        return c - c2;
    }

    public static boolean isAscii(char c) {
        return c < 128;
    }

    public static boolean isAsciiAlpha(char c) {
        return isAsciiAlphaUpper(c) || isAsciiAlphaLower(c);
    }

    public static boolean isAsciiAlphaLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isAsciiAlphaUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isAsciiAlphanumeric(char c) {
        return isAsciiAlpha(c) || isAsciiNumeric(c);
    }

    public static boolean isAsciiControl(char c) {
        return c < ' ' || c == 127;
    }

    public static boolean isAsciiNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isAsciiPrintable(char c) {
        return c >= ' ' && c < 127;
    }

    public static char toChar(Character ch) {
        if (ch != null) {
            return ch.charValue();
        }
        throw new IllegalArgumentException("The Character must not be null");
    }

    @Deprecated
    public static Character toCharacterObject(char c) {
        return Character.valueOf(c);
    }

    public static int toIntValue(char c) {
        if (isAsciiNumeric(c)) {
            return c - '0';
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline48("The character ", c, " is not in the range '0' - '9'"));
    }

    public static String toString(char c) {
        if (c < 128) {
            return CHAR_STRING_ARRAY[c];
        }
        return new String(new char[]{c});
    }

    public static String unicodeEscaped(char c) {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(6, "\\u");
        outline105.append(HEX_DIGITS[(c >> '\f') & 15]);
        outline105.append(HEX_DIGITS[(c >> '\b') & 15]);
        outline105.append(HEX_DIGITS[(c >> 4) & 15]);
        outline105.append(HEX_DIGITS[c & 15]);
        return outline105.toString();
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static char toChar(Character ch, char c) {
        return ch == null ? c : ch.charValue();
    }

    public static int toIntValue(char c, int i) {
        return !isAsciiNumeric(c) ? i : c - '0';
    }

    public static String toString(Character ch) {
        if (ch == null) {
            return null;
        }
        return toString(ch.charValue());
    }

    public static char toChar(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.charAt(0);
        }
        throw new IllegalArgumentException("The String must not be empty");
    }

    public static int toIntValue(Character ch) {
        if (ch != null) {
            return toIntValue(ch.charValue());
        }
        throw new IllegalArgumentException("The character must not be null");
    }

    public static int toIntValue(Character ch, int i) {
        return ch == null ? i : toIntValue(ch.charValue(), i);
    }

    public static char toChar(String str, char c) {
        return StringUtils.isEmpty(str) ? c : str.charAt(0);
    }

    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }
}
