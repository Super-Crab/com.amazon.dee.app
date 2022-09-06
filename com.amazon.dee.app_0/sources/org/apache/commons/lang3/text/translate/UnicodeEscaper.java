package org.apache.commons.lang3.text.translate;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Writer;
/* loaded from: classes4.dex */
public class UnicodeEscaper extends CodePointTranslator {
    private final int above;
    private final int below;
    private final boolean between;

    public UnicodeEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }

    public static UnicodeEscaper above(int i) {
        return outsideOf(0, i);
    }

    public static UnicodeEscaper below(int i) {
        return outsideOf(i, Integer.MAX_VALUE);
    }

    public static UnicodeEscaper between(int i, int i2) {
        return new UnicodeEscaper(i, i2, true);
    }

    public static UnicodeEscaper outsideOf(int i, int i2) {
        return new UnicodeEscaper(i, i2, false);
    }

    protected String toUtf16Escape(int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\\u");
        outline107.append(CharSequenceTranslator.hex(i));
        return outline107.toString();
    }

    @Override // org.apache.commons.lang3.text.translate.CodePointTranslator
    public boolean translate(int i, Writer writer) throws IOException {
        if (this.between) {
            if (i < this.below || i > this.above) {
                return false;
            }
        } else if (i >= this.below && i <= this.above) {
            return false;
        }
        if (i > 65535) {
            writer.write(toUtf16Escape(i));
            return true;
        }
        writer.write("\\u");
        writer.write(CharSequenceTranslator.HEX_DIGITS[(i >> 12) & 15]);
        writer.write(CharSequenceTranslator.HEX_DIGITS[(i >> 8) & 15]);
        writer.write(CharSequenceTranslator.HEX_DIGITS[(i >> 4) & 15]);
        writer.write(CharSequenceTranslator.HEX_DIGITS[i & 15]);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UnicodeEscaper(int i, int i2, boolean z) {
        this.below = i;
        this.above = i2;
        this.between = z;
    }
}
