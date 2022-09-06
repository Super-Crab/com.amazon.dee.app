package com.amazon.dee.app.databinding;

import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class FontAdapter {
    private static Map<String, Font> fontNameMap = new HashMap();

    static {
        fontNameMap.put("ember-regular", Font.AMAZON_EMBER_REGULAR);
        fontNameMap.put("ember-medium", Font.AMAZON_EMBER_MEDIUM);
        fontNameMap.put("ember-bold", Font.AMAZON_EMBER_BOLD);
    }

    private FontAdapter() {
    }

    @BindingAdapter({"font"})
    public static void setFont(TextView textView, String str) {
        textView.setTypeface(FontResolver.getFont(textView.getContext(), fontNameMap.get(str)));
    }
}
