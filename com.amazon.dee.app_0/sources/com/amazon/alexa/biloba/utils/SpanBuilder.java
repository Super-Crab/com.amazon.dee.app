package com.amazon.alexa.biloba.utils;

import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
/* loaded from: classes6.dex */
public class SpanBuilder {
    private SpannableStringBuilder spanStringBuilder = new SpannableStringBuilder();

    public SpanBuilder append(String str, CharacterStyle... characterStyleArr) {
        int length = this.spanStringBuilder.length();
        this.spanStringBuilder.append((CharSequence) str);
        if (characterStyleArr != null && characterStyleArr.length > 0) {
            for (CharacterStyle characterStyle : characterStyleArr) {
                this.spanStringBuilder.setSpan(characterStyle, length, str.length() + length, 17);
            }
        }
        return this;
    }

    public SpannableStringBuilder getValue() {
        return this.spanStringBuilder;
    }

    public String toString() {
        return this.spanStringBuilder.toString();
    }
}
