package com.amazon.alexa.voice.ui.cards.util;

import androidx.annotation.Nullable;
import androidx.core.util.PatternsCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class StringUtils {
    public static final char LEFT_DOUBLE_QUOTE = 8220;
    public static final char RIGHT_DOUBLE_QUOTE = 8221;

    private StringUtils() {
    }

    public static boolean isBlank(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() != 0) {
            for (int i = 0; i < charSequence.length(); i++) {
                if (!Character.isWhitespace(charSequence.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidEmail(String str) {
        return !isBlank(str) && PatternsCompat.EMAIL_ADDRESS.matcher(str).matches();
    }

    @Nullable
    public static String quoteText(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104((char) 8220);
        outline104.append(charSequence.toString());
        outline104.append((char) 8221);
        return outline104.toString();
    }
}
