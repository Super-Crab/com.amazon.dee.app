package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.utils.LogUtils;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class ErrorView extends ConstraintLayout {
    private static final String TAG = ErrorView.class.getSimpleName();
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    private final String bodyText;
    private final String headlineText;
    private final String hintText;
    private final String iconContentDescription;
    private final Drawable iconSrc;
    private final ColorStateList iconTint;
    private final String linkText;
    private final String metricName;

    public ErrorView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setIconContentDescription(this.iconContentDescription);
        setIconSrc(this.iconSrc);
        setIconTint(this.iconTint);
        setHeadlineText(this.headlineText);
        setBodyText(this.bodyText);
        setLinkText(this.linkText);
        setHintText(this.hintText);
        setMetricName(this.metricName);
    }

    public void setBodyText(String str) {
        ViewUtils.updateTextView(this, R.id.bodyText, str);
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        ViewUtils.updateTextClickable(this, R.id.linkText, z);
    }

    public void setHeadlineText(String str) {
        ViewUtils.updateTextView(this, R.id.headlineText, str);
    }

    public void setHintText(String str) {
        ViewUtils.updateTextView(this, R.id.hintText, str);
    }

    public void setIconContentDescription(String str) {
        ViewUtils.updateViewContentDescription(this, R.id.controlIcon, str);
    }

    public void setIconSrc(Drawable drawable) {
        ViewUtils.updateImageView(this, R.id.controlIcon, drawable);
    }

    public void setIconTint(ColorStateList colorStateList) {
        ViewUtils.updateImageColor(this, R.id.controlIcon, colorStateList);
    }

    public void setLinkText(String str) {
        ViewUtils.updateTextView(this, R.id.linkText, str);
    }

    public void setMetricName(String str) {
        if (str != null) {
            String str2 = TAG;
            LogUtils.d(str2, "Error for: " + str);
            this.bilobaMetricsService.mo358get().recordUserView(str, "");
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        TextView textView = (TextView) findViewById(R.id.linkText);
        if (textView != null && onClickListener != null) {
            LogUtils.i(TAG, "Setting OnClickListener for linkText");
            textView.setOnClickListener(onClickListener);
            setClickable(true);
            return;
        }
        setClickable(false);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            LogUtils.i(TAG, "ErrorView is visible");
        } else if (i != 4 && i != 8) {
        } else {
            LogUtils.i(TAG, "ErrorView is gone");
        }
    }

    public ErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        BilobaDependencies.inject(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ErrorView, 0, 0);
        try {
            this.iconContentDescription = obtainStyledAttributes.getString(R.styleable.ErrorView_iconContentDescription);
            this.iconSrc = obtainStyledAttributes.getDrawable(R.styleable.ErrorView_iconSrc);
            this.iconTint = obtainStyledAttributes.getColorStateList(R.styleable.ErrorView_iconTint);
            this.headlineText = obtainStyledAttributes.getString(R.styleable.ErrorView_headlineText);
            this.bodyText = obtainStyledAttributes.getString(R.styleable.ErrorView_bodyText);
            this.linkText = obtainStyledAttributes.getString(R.styleable.ErrorView_linkText);
            this.hintText = obtainStyledAttributes.getString(R.styleable.ErrorView_hintText);
            this.metricName = obtainStyledAttributes.getString(R.styleable.ErrorView_metricName);
            obtainStyledAttributes.recycle();
            ViewGroup.inflate(getContext(), R.layout.mosaic_error, this);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
