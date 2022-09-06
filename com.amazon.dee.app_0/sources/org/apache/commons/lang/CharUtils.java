package org.apache.commons.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class CharUtils {
    private static final String CHAR_STRING = "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u007f";
    public static final char CR = '\r';
    public static final char LF = '\n';
    private static final String[] CHAR_STRING_ARRAY = new String[128];
    private static final Character[] CHAR_ARRAY = new Character[128];

    static {
        for (int i = 127; i >= 0; i--) {
            CHAR_STRING_ARRAY[i] = CHAR_STRING.substring(i, i + 1);
            CHAR_ARRAY[i] = new Character((char) i);
        }
    }

    public static boolean isAscii(char c) {
        return c < 128;
    }

    public static boolean isAsciiAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static boolean isAsciiAlphaLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isAsciiAlphaUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isAsciiAlphanumeric(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHighSurrogate(char c) {
        return 55296 <= c && 56319 >= c;
    }

    public static char toChar(Character ch) {
        if (ch != null) {
            return ch.charValue();
        }
        throw new IllegalArgumentException("The Character must not be null");
    }

    public static Character toCharacterObject(char c) {
        Character[] chArr = CHAR_ARRAY;
        if (c < chArr.length) {
            return chArr[c];
        }
        return new Character(c);
    }

    public static int toIntValue(char c) {
        if (isAsciiNumeric(c)) {
            return c - '0';
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The character ");
        stringBuffer.append(c);
        stringBuffer.append(" is not in the range '0' - '9'");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static String toString(char c) {
        if (c < 128) {
            return CHAR_STRING_ARRAY[c];
        }
        return new String(new char[]{c});
    }

    public static String unicodeEscaped(char c) {
        if (c < 16) {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("\\u000");
            outline103.append(Integer.toHexString(c));
            return outline103.toString();
        } else if (c < 256) {
            StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("\\u00");
            outline1032.append(Integer.toHexString(c));
            return outline1032.toString();
        } else if (c < 4096) {
            StringBuffer outline1033 = GeneratedOutlineSupport1.outline103("\\u0");
            outline1033.append(Integer.toHexString(c));
            return outline1033.toString();
        } else {
            StringBuffer outline1034 = GeneratedOutlineSupport1.outline103("\\u");
            outline1034.append(Integer.toHexString(c));
            return outline1034.toString();
        }
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

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return toCharacterObject(str.charAt(0));
    }

    public static int toIntValue(Character ch) {
        if (ch != null) {
            return toIntValue(ch.charValue());
        }
        throw new IllegalArgumentException("The character must not be null");
    }

    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }

    public static int toIntValue(Character ch, int i) {
        return ch == null ? i : toIntValue(ch.charValue(), i);
    }

    public static char toChar(String str, char c) {
        return StringUtils.isEmpty(str) ? c : str.charAt(0);
    }
}
