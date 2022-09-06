package com.amazon.deecomms.util;

import android.content.Context;
import android.text.TextPaint;
import androidx.core.content.ContextCompat;
import com.amazon.deecomms.R;
/* loaded from: classes12.dex */
public class URLSpanNoUnderlineAndSetColor extends URLSpanNoUnderline {
    private final boolean isThemedUIEnabled;
    private final Context mContext;

    public URLSpanNoUnderlineAndSetColor(Context context, String str, boolean z) {
        super(str);
        this.mContext = context;
        this.isThemedUIEnabled = z;
    }

    @Override // com.amazon.deecomms.util.URLSpanNoUnderline, android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
        if (this.isThemedUIEnabled) {
            textPaint.setColor(ContextCompat.getColor(this.mContext, R.color.fiesta_color_accent));
        } else {
            textPaint.setColor(ContextCompat.getColor(this.mContext, R.color.alexaBlue));
        }
    }
}
