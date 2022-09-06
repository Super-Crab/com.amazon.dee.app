package com.amazon.alexa.voice.ui.onedesign.util;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class HeaderUtils {
    private HeaderUtils() {
    }

    public static void applyFontStyles(@NonNull TextView textView, @Nullable TextView textView2) {
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, textView);
        LetterSpacing.apply(R.dimen.voice_ui_od_font_letter_spacing, textView);
        if (textView2 != null) {
            FontUtils.apply(Font.AMAZON_EMBER_REGULAR, textView);
        }
    }

    public static void applyFontStyles(@NonNull TextView textView) {
        applyFontStyles(textView, null);
    }
}
