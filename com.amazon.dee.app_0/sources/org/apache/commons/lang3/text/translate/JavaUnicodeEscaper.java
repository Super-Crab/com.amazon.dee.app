package org.apache.commons.lang3.text.translate;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class JavaUnicodeEscaper extends UnicodeEscaper {
    public JavaUnicodeEscaper(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    public static JavaUnicodeEscaper above(int i) {
        return outsideOf(0, i);
    }

    public static JavaUnicodeEscaper below(int i) {
        return outsideOf(i, Integer.MAX_VALUE);
    }

    public static JavaUnicodeEscaper between(int i, int i2) {
        return new JavaUnicodeEscaper(i, i2, true);
    }

    public static JavaUnicodeEscaper outsideOf(int i, int i2) {
        return new JavaUnicodeEscaper(i, i2, false);
    }

    @Override // org.apache.commons.lang3.text.translate.UnicodeEscaper
    protected String toUtf16Escape(int i) {
        char[] chars = Character.toChars(i);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\\u");
        outline107.append(CharSequenceTranslator.hex(chars[0]));
        outline107.append("\\u");
        outline107.append(CharSequenceTranslator.hex(chars[1]));
        return outline107.toString();
    }
}
