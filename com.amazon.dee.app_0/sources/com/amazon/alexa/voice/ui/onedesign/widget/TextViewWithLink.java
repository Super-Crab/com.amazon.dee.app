package com.amazon.alexa.voice.ui.onedesign.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TextViewWithLink extends AppCompatTextView {
    private int clickableSubTextColor;

    /* loaded from: classes11.dex */
    public interface OnEmbeddedLinkClickListener {
        void onEmbeddedLinkClick();
    }

    public TextViewWithLink(Context context) {
        super(context);
    }

    public void setTextWithLink(CharSequence charSequence, CharSequence charSequence2, final OnEmbeddedLinkClickListener onEmbeddedLinkClickListener) {
        if (!TextUtils.isEmpty(charSequence2)) {
            if (TextUtils.isEmpty(charSequence)) {
                throw new IllegalArgumentException("the content should not be null when link text is not empty.");
            }
            if (onEmbeddedLinkClickListener != null) {
                SpannableString spannableString = new SpannableString(charSequence);
                ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.amazon.alexa.voice.ui.onedesign.widget.TextViewWithLink.1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        onEmbeddedLinkClickListener.onEmbeddedLinkClick();
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setUnderlineText(false);
                        textPaint.setColor(TextViewWithLink.this.clickableSubTextColor);
                    }
                };
                int indexOf = charSequence.toString().indexOf(charSequence2.toString());
                spannableString.setSpan(clickableSpan, indexOf, charSequence2.length() + indexOf, 33);
                setText(spannableString);
                setVisibility(0);
                setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            throw new IllegalArgumentException("the linkTextClickListener should not be nullwhen link text is not empty.");
        }
        ViewUtils.setTextOrRemove(this, charSequence);
    }

    public TextViewWithLink(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextViewWithLink);
        this.clickableSubTextColor = obtainStyledAttributes.getColor(R.styleable.TextViewWithLink_clickableSubTextColor, 0);
        obtainStyledAttributes.recycle();
    }
}
