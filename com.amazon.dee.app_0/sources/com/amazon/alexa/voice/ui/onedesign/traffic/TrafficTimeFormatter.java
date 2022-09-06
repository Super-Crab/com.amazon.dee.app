package com.amazon.alexa.voice.ui.onedesign.traffic;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.voice.ui.onedesign.util.text.CustomTypefaceSpan;
/* loaded from: classes11.dex */
public final class TrafficTimeFormatter implements TimeFormatter {
    private final Typeface numberTypeface;
    private final Typeface textTypeface;

    public TrafficTimeFormatter(Context context) {
        this.numberTypeface = FontResolver.getFont(context, Font.AMAZON_EMBER_REGULAR);
        this.textTypeface = FontResolver.getFont(context, Font.AMAZON_EMBER_LIGHT);
    }

    private CharSequence applyTypeface(CharSequence charSequence, boolean z, Typeface typeface) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        int i = 0;
        while (i < charSequence.length()) {
            int findNumber = z ? findNumber(charSequence, i) : findText(charSequence, i);
            if (findNumber == -1) {
                break;
            }
            int findText = z ? findText(charSequence, findNumber) : findNumber(charSequence, findNumber);
            int length = findText == -1 ? charSequence.length() : findText;
            spannableStringBuilder.setSpan(new CustomTypefaceSpan(typeface), findNumber, length, 33);
            i = length;
        }
        return spannableStringBuilder;
    }

    private int findNumber(CharSequence charSequence, int i) {
        int length = charSequence.length();
        while (i < length) {
            if (Character.isDigit(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private int findText(CharSequence charSequence, int i) {
        int length = charSequence.length();
        while (i < length) {
            if (!Character.isDigit(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TimeFormatter
    public CharSequence format(CharSequence charSequence) {
        return applyTypeface(applyTypeface(charSequence, true, this.numberTypeface), false, this.textTypeface);
    }
}
