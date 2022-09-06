package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.amazon.alexa.biloba.R;
/* loaded from: classes9.dex */
public class BulletListText extends LinearLayout {
    private static final String TAG = BulletListText.class.getSimpleName();
    private final String primaryText;
    private final Boolean showBullet;

    public BulletListText(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setPrimaryText(this.primaryText);
        setBulletVisibility(this.showBullet);
    }

    public void setBulletVisibility(Boolean bool) {
        ViewUtils.updateVisibility(this, R.id.circleBullet, bool);
    }

    public void setPrimaryText(String str) {
        ViewUtils.updateTextView(this, R.id.primaryText, str);
    }

    public BulletListText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setPrimaryText(SpannableStringBuilder spannableStringBuilder) {
        ViewUtils.updateTextView(this, R.id.primaryText, spannableStringBuilder);
    }

    public BulletListText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BulletListText, 0, 0);
        try {
            this.primaryText = obtainStyledAttributes.getString(R.styleable.BulletListText_primaryText);
            this.showBullet = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.BulletListText_showBullet, Boolean.TRUE.booleanValue()));
            obtainStyledAttributes.recycle();
            LinearLayout.inflate(getContext(), R.layout.mosaic_bullet_list_text, this);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
