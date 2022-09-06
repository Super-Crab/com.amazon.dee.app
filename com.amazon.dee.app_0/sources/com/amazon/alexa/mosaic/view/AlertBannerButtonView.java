package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.amazon.alexa.biloba.R;
/* loaded from: classes9.dex */
public class AlertBannerButtonView extends RelativeLayout {
    private final String alertText;
    private final boolean alertVisible;
    private final String buttonText;
    private final Drawable iconSrc;

    public AlertBannerButtonView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setIconSrc(this.iconSrc);
        setButtonText(this.buttonText);
        setAlertText(this.alertText);
        setAlertVisible(this.alertVisible);
    }

    public void setAlertText(String str) {
        ViewUtils.updateTextView(this, R.id.alert_text, str);
    }

    public void setAlertVisible(boolean z) {
        ViewUtils.updateVisibility(this, R.id.alert_banner, Boolean.valueOf(z));
    }

    public void setButtonText(String str) {
        ViewUtils.updateTextView(this, R.id.button_text, str);
    }

    public void setIconSrc(Drawable drawable) {
        ViewUtils.updateImageView(this, R.id.button_icon, drawable);
    }

    public AlertBannerButtonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlertBannerButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AlertBannerButtonView, 0, 0);
        try {
            this.iconSrc = obtainStyledAttributes.getDrawable(R.styleable.AlertBannerButtonView_iconSrc);
            this.buttonText = obtainStyledAttributes.getString(R.styleable.AlertBannerButtonView_buttonText);
            this.alertText = obtainStyledAttributes.getString(R.styleable.AlertBannerButtonView_alertText);
            this.alertVisible = obtainStyledAttributes.getBoolean(R.styleable.AlertBannerButtonView_alertVisible, false);
            obtainStyledAttributes.recycle();
            RelativeLayout.inflate(getContext(), R.layout.alert_banner_button, this);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
