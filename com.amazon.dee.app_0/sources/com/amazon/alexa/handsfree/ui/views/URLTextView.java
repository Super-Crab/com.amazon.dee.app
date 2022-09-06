package com.amazon.alexa.handsfree.ui.views;

import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.ui.R;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class URLTextView extends TextView {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static final class URLSpanNoUnderline extends URLSpan {
        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(@NonNull TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }

        private URLSpanNoUnderline(@NonNull String str) {
            super(str);
        }
    }

    public URLTextView(@NonNull Context context) {
        super(context);
    }

    private void setupView() {
        URLSpan[] uRLSpanArr;
        setLinkTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicAction10));
        setMovementMethod(LinkMovementMethod.getInstance());
        Spannable spannable = (Spannable) getText();
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            int spanStart = spannable.getSpanStart(uRLSpan);
            int spanEnd = spannable.getSpanEnd(uRLSpan);
            spannable.removeSpan(uRLSpan);
            spannable.setSpan(new URLSpanNoUnderline(uRLSpan.getURL()), spanStart, spanEnd, 0);
        }
        setText(spannable);
    }

    public void setURLText(int i) {
        setText(i);
        setupView();
    }

    public URLTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getText().length() > 0) {
            setupView();
        }
    }
}
