package com.amazon.deecomms.util;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class LinkSpannable extends ClickableSpan {
    private View.OnClickListener mClickListener;
    private Integer mEnd;
    @ColorInt
    private Integer mLinkColor;
    private Integer mStart;
    private String mText;
    private boolean mUnderlineText;

    /* loaded from: classes12.dex */
    public static class Builder {
        private Integer end;
        private Integer start;
        private String text;
        @ColorInt
        private Integer linkColor = null;
        private boolean underlineText = false;
        private View.OnClickListener listener = null;

        public LinkSpannable create() {
            if (this.start.intValue() < this.end.intValue()) {
                return new LinkSpannable(this.text, this.start.intValue(), this.end.intValue(), this.linkColor, this.underlineText, this.listener);
            }
            throw new IllegalArgumentException("Ending index should be greater than starting index");
        }

        public Builder from(int i) {
            this.start = Integer.valueOf(i);
            return this;
        }

        public Builder setLinkColor(@ColorInt Integer num) {
            this.linkColor = num;
            return this;
        }

        public Builder setListener(View.OnClickListener onClickListener) {
            this.listener = onClickListener;
            return this;
        }

        public Builder to(int i) {
            this.end = Integer.valueOf(i);
            return this;
        }

        public Builder underlineText(boolean z) {
            this.underlineText = z;
            return this;
        }

        public Builder with(String str) {
            this.text = str;
            return this;
        }
    }

    public LinkSpannable(@NonNull String str, int i, int i2, @ColorInt Integer num, boolean z, View.OnClickListener onClickListener) {
        this.mText = str;
        this.mStart = Integer.valueOf(i);
        this.mEnd = Integer.valueOf(i2);
        this.mLinkColor = num;
        this.mUnderlineText = z;
        this.mClickListener = onClickListener;
    }

    public SpannableString getSpannableString() {
        SpannableString spannableString = new SpannableString(this.mText);
        spannableString.setSpan(this, this.mStart.intValue(), this.mEnd.intValue(), 33);
        return spannableString;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(@NonNull View view) {
        View.OnClickListener onClickListener = this.mClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        Integer num = this.mLinkColor;
        if (num != null) {
            textPaint.setColor(num.intValue());
        }
        textPaint.setUnderlineText(this.mUnderlineText);
    }
}
