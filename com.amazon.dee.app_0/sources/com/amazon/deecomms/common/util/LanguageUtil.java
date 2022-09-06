package com.amazon.deecomms.common.util;

import androidx.annotation.NonNull;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public final class LanguageUtil {
    private static List<Character.UnicodeBlock> emojiCharSet = new ArrayList();
    private static List<Character.UnicodeBlock> latinCharSet = new ArrayList();
    private static List<Character.UnicodeBlock> punctuationCharSet = new ArrayList();
    private static List<Character.UnicodeBlock> symbolCharSet = new ArrayList();
    private static List<Character.UnicodeBlock> hiraganaCharSet = new ArrayList();
    private static List<Character.UnicodeBlock> fullWidthKatakanaCharSet = new ArrayList();

    static {
        latinCharSet.add(Character.UnicodeBlock.BASIC_LATIN);
        punctuationCharSet.add(Character.UnicodeBlock.GENERAL_PUNCTUATION);
        punctuationCharSet.add(Character.UnicodeBlock.SUPPLEMENTAL_PUNCTUATION);
        punctuationCharSet.add(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
        punctuationCharSet.add(Character.UnicodeBlock.CUNEIFORM_NUMBERS_AND_PUNCTUATION);
        symbolCharSet.add(Character.UnicodeBlock.ALCHEMICAL_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.ANCIENT_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.CURRENCY_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
        symbolCharSet.add(Character.UnicodeBlock.KHMER_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.LETTERLIKE_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.MUSICAL_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.RUMI_NUMERAL_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS);
        symbolCharSet.add(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A);
        symbolCharSet.add(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B);
        symbolCharSet.add(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS);
        symbolCharSet.add(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS);
        emojiCharSet.add(Character.UnicodeBlock.EMOTICONS);
        hiraganaCharSet.add(Character.UnicodeBlock.HIRAGANA);
        fullWidthKatakanaCharSet.add(Character.UnicodeBlock.KATAKANA);
    }

    private LanguageUtil() {
    }

    public static boolean containUnsupportedCharForJPNames(@NonNull String str) {
        Character.UnicodeBlock of;
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (isInExceptionCharSet(charAt)) {
                i++;
            } else {
                if (Character.isHighSurrogate(charAt)) {
                    of = Character.UnicodeBlock.of(Character.toCodePoint(charAt, str.charAt(i + 1)));
                    i += 2;
                } else {
                    of = Character.UnicodeBlock.of(charAt);
                    i++;
                }
                if (punctuationCharSet.contains(of) || symbolCharSet.contains(of) || emojiCharSet.contains(of)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containUnsupportedCharForJPPhoneticNames(@NonNull String str) {
        char[] charArray;
        for (char c : str.toCharArray()) {
            if (Character.isSurrogate(c)) {
                return true;
            }
            if (!isInExceptionCharSet(c)) {
                Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
                if (!isHiragana(c) && !isKatakana(c) && !latinCharSet.contains(of)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsUnsupportedCharSet(@NonNull String str) {
        return !str.matches("[\\x20-\\x7E\\xA0-\\xFF]+");
    }

    private static List<Character.UnicodeBlock> emojiCharSet() {
        return emojiCharSet;
    }

    private static List<Character.UnicodeBlock> fullWidthKatakanaCharSet() {
        return fullWidthKatakanaCharSet;
    }

    private static List<Character.UnicodeBlock> hiraganaCharSet() {
        return hiraganaCharSet;
    }

    public static boolean isFullWidthKatakana(char c) {
        return fullWidthKatakanaCharSet.contains(Character.UnicodeBlock.of(c));
    }

    public static boolean isHalfWidthKatakana(char c) {
        return 65381 <= c && c <= 65439;
    }

    public static boolean isHiragana(char c) {
        return hiraganaCharSet.contains(Character.UnicodeBlock.of(c));
    }

    private static boolean isInExceptionCharSet(char c) {
        return "'`‘’".indexOf(c) != -1;
    }

    public static boolean isKatakana(char c) {
        return isHalfWidthKatakana(c) || isFullWidthKatakana(c);
    }

    private static List<Character.UnicodeBlock> latinCharSet() {
        return latinCharSet;
    }

    private static List<Character.UnicodeBlock> punctuationCharSet() {
        return punctuationCharSet;
    }

    private static List<Character.UnicodeBlock> symbolCharSet() {
        return symbolCharSet;
    }

    public static char toHiragana(char c) {
        int i;
        if (isFullWidthKatakana(c)) {
            i = c - '`';
        } else if (!isHalfWidthKatakana(c)) {
            return c;
        } else {
            i = c - 53029;
        }
        return (char) i;
    }

    public static String toHiragana(@NonNull String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(toHiragana(str.charAt(i)));
        }
        return sb.toString();
    }
}
