package org.threeten.bp.format;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: classes5.dex */
public final class DecimalStyle {
    private final char decimalSeparator;
    private final char negativeSign;
    private final char positiveSign;
    private final char zeroDigit;
    public static final DecimalStyle STANDARD = new DecimalStyle('0', '+', '-', '.');
    private static final ConcurrentMap<Locale, DecimalStyle> CACHE = new ConcurrentHashMap(16, 0.75f, 2);

    private DecimalStyle(char c, char c2, char c3, char c4) {
        this.zeroDigit = c;
        this.positiveSign = c2;
        this.negativeSign = c3;
        this.decimalSeparator = c4;
    }

    private static DecimalStyle create(Locale locale) {
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale);
        char zeroDigit = decimalFormatSymbols.getZeroDigit();
        char minusSign = decimalFormatSymbols.getMinusSign();
        char decimalSeparator = decimalFormatSymbols.getDecimalSeparator();
        if (zeroDigit == '0' && minusSign == '-' && decimalSeparator == '.') {
            return STANDARD;
        }
        return new DecimalStyle(zeroDigit, '+', minusSign, decimalSeparator);
    }

    public static Set<Locale> getAvailableLocales() {
        return new HashSet(Arrays.asList(DecimalFormatSymbols.getAvailableLocales()));
    }

    public static DecimalStyle of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        DecimalStyle decimalStyle = CACHE.get(locale);
        if (decimalStyle == null) {
            CACHE.putIfAbsent(locale, create(locale));
            return CACHE.get(locale);
        }
        return decimalStyle;
    }

    public static DecimalStyle ofDefaultLocale() {
        return of(Locale.getDefault());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String convertNumberToI18N(String str) {
        char c = this.zeroDigit;
        if (c == '0') {
            return str;
        }
        int i = c - '0';
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            charArray[i2] = (char) (charArray[i2] + i);
        }
        return new String(charArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int convertToDigit(char c) {
        int i = c - this.zeroDigit;
        if (i < 0 || i > 9) {
            return -1;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalStyle)) {
            return false;
        }
        DecimalStyle decimalStyle = (DecimalStyle) obj;
        return this.zeroDigit == decimalStyle.zeroDigit && this.positiveSign == decimalStyle.positiveSign && this.negativeSign == decimalStyle.negativeSign && this.decimalSeparator == decimalStyle.decimalSeparator;
    }

    public char getDecimalSeparator() {
        return this.decimalSeparator;
    }

    public char getNegativeSign() {
        return this.negativeSign;
    }

    public char getPositiveSign() {
        return this.positiveSign;
    }

    public char getZeroDigit() {
        return this.zeroDigit;
    }

    public int hashCode() {
        return this.zeroDigit + this.positiveSign + this.negativeSign + this.decimalSeparator;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DecimalStyle[");
        outline107.append(this.zeroDigit);
        outline107.append(this.positiveSign);
        outline107.append(this.negativeSign);
        outline107.append(this.decimalSeparator);
        outline107.append("]");
        return outline107.toString();
    }

    public DecimalStyle withDecimalSeparator(char c) {
        return c == this.decimalSeparator ? this : new DecimalStyle(this.zeroDigit, this.positiveSign, this.negativeSign, c);
    }

    public DecimalStyle withNegativeSign(char c) {
        return c == this.negativeSign ? this : new DecimalStyle(this.zeroDigit, this.positiveSign, c, this.decimalSeparator);
    }

    public DecimalStyle withPositiveSign(char c) {
        return c == this.positiveSign ? this : new DecimalStyle(this.zeroDigit, c, this.negativeSign, this.decimalSeparator);
    }

    public DecimalStyle withZeroDigit(char c) {
        return c == this.zeroDigit ? this : new DecimalStyle(c, this.positiveSign, this.negativeSign, this.decimalSeparator);
    }
}
