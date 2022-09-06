package com.drew.metadata.iptc;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
/* loaded from: classes2.dex */
public final class Iso2022Converter {
    private static final int DOT = 14844066;
    private static final byte ESC = 27;
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final byte LATIN_CAPITAL_A = 65;
    private static final byte LATIN_CAPITAL_G = 71;
    private static final byte PERCENT_SIGN = 37;
    private static final String UTF_8 = "UTF-8";

    private Iso2022Converter() {
    }

    @Nullable
    public static String convertISO2022CharsetToJavaCharset(@NotNull byte[] bArr) {
        if (bArr.length > 2 && bArr[0] == 27 && bArr[1] == 37 && bArr[2] == 71) {
            return "UTF-8";
        }
        if (bArr.length <= 3 || bArr[0] != 27 || ((bArr[3] & 255) | ((bArr[2] & 255) << 8) | ((bArr[1] & 255) << 16)) != DOT || bArr[4] != 65) {
            return null;
        }
        return "ISO-8859-1";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Charset guessCharSet(@NotNull byte[] bArr) {
        for (String str : new String[]{"UTF-8", System.getProperty("file.encoding"), "ISO-8859-1"}) {
            Charset forName = Charset.forName(str);
            try {
                forName.newDecoder().decode(ByteBuffer.wrap(bArr));
                return forName;
            } catch (CharacterCodingException unused) {
            }
        }
        return null;
    }
}
