package com.amazon.alexa.voice.ui.onedesign.travel;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.voice.ui.onedesign.util.text.CustomTypefaceSpan;
/* loaded from: classes11.dex */
public class TravelTimeDistanceFormatter {
    private final Typeface textTypeface;

    public TravelTimeDistanceFormatter(Context context) {
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
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(24, true), findNumber, length, 33);
            i = length;
        }
        return spannableStringBuilder;
    }

    private int findNumber(CharSequence charSequence, int i) {
        int length = charSequence.length();
        while (i < length) {
            if (!Character.isLetter(charSequence.charAt(i)) && !Character.isSpaceChar(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private int findText(CharSequence charSequence, int i) {
        int length = charSequence.length();
        while (i < length) {
            if (Character.isLetter(charSequence.charAt(i)) || Character.isSpaceChar(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public CharSequence format(CharSequence charSequence) {
        return applyTypeface(charSequence, false, this.textTypeface);
    }
}
