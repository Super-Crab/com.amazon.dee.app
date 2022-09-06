package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.amazon.alexa.biloba.R;
/* loaded from: classes9.dex */
public class NumberedListText extends LinearLayout {
    private static final String TAG = NumberedListText.class.getSimpleName();
    private final String numberText;
    private final String primaryText;

    public NumberedListText(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setPrimaryText(this.primaryText);
        setNumberText(this.numberText);
    }

    public void setNumberText(String str) {
        ViewUtils.updateTextView(this, R.id.numberText, str);
    }

    public void setPrimaryText(String str) {
        ViewUtils.updateTextView(this, R.id.primaryText, str);
    }

    public NumberedListText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setPrimaryText(SpannableStringBuilder spannableStringBuilder) {
        ViewUtils.updateTextView(this, R.id.primaryText, spannableStringBuilder);
    }

    public NumberedListText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NumberedListText, 0, 0);
        try {
            this.primaryText = obtainStyledAttributes.getString(R.styleable.NumberedListText_primaryText);
            this.numberText = obtainStyledAttributes.getString(R.styleable.NumberedListText_numberText);
            obtainStyledAttributes.recycle();
            LinearLayout.inflate(getContext(), R.layout.mosaic_numbered_list_text, this);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
