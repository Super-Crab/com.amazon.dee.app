package com.amazon.alexa.voice.ui.onedesign.util;

import android.text.SpannableStringBuilder;
/* loaded from: classes11.dex */
public final class CharSequenceUtils {
    private CharSequenceUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static CharSequence capitalize(CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!Character.isLetter(charAt)) {
                spannableStringBuilder.append(charAt);
            } else if (i != 0 && !Character.isSpaceChar(charSequence.charAt(i - 1))) {
                spannableStringBuilder.append(charAt);
            } else {
                spannableStringBuilder.append(Character.toUpperCase(charAt));
            }
        }
        return spannableStringBuilder;
    }
}
