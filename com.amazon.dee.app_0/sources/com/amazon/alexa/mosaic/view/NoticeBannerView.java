package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
/* loaded from: classes9.dex */
public class NoticeBannerView extends LinearLayout {
    private static final int NOT_SET = -1;
    private static final String TAG = NoticeBannerView.class.getSimpleName();
    private int backgroundColor;
    private String iconAltText;
    private Drawable iconSrc;
    private ColorStateList iconTint;
    private String iconUrl;
    private String linkText;
    private String primaryText;

    public NoticeBannerView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setIconSrc(this.iconSrc);
        setIconUrl(this.iconUrl);
        setIconTint(this.iconTint);
        setIconAltText(this.iconAltText);
        setPrimaryText(this.primaryText);
        setLinkText(this.linkText);
        setBackgroundColor(this.backgroundColor);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (this.backgroundColor != -1) {
            ViewUtils.updateBackgroundColor(this, R.id.notice_banner_root, i);
        }
    }

    public void setIconAltText(String str) {
        if (str != null) {
            ViewUtils.updateViewContentDescription(this, R.id.iconWarning, str);
        }
    }

    public void setIconSrc(Drawable drawable) {
        if (drawable != null) {
            ViewUtils.updateImageView(this, R.id.iconWarning, drawable);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            ViewUtils.updateImageColor(this, R.id.iconWarning, colorStateList);
        }
    }

    public void setIconUrl(String str) {
        if (str != null) {
            ViewUtils.updateImageUrl(this, R.id.iconWarning, str);
        }
    }

    public void setLinkText(String str) {
        ViewUtils.updateTextView(this, R.id.linkText, str);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        TextView textView = (TextView) findViewById(R.id.linkText);
        if (textView == null || onClickListener == null) {
            return;
        }
        LogUtils.i(TAG, "Setting OnClickListener for linkText");
        textView.setOnClickListener(onClickListener);
    }

    public void setPrimaryText(String str) {
        ViewUtils.updateTextView(this, R.id.primaryText, str);
    }

    public NoticeBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoticeBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.backgroundColor = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NoticeBannerView, 0, 0);
        for (int i2 = 0; i2 < obtainStyledAttributes.length(); i2++) {
            try {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.NoticeBannerView_iconSrc) {
                    this.iconSrc = obtainStyledAttributes.getDrawable(index);
                } else if (index == R.styleable.NoticeBannerView_iconTint) {
                    this.iconTint = obtainStyledAttributes.getColorStateList(index);
                } else if (index == R.styleable.NoticeBannerView_primaryText) {
                    this.primaryText = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.NoticeBannerView_linkText) {
                    this.linkText = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.NoticeBannerView_backgroundColor) {
                    this.backgroundColor = obtainStyledAttributes.getColor(index, -1);
                } else if (index == R.styleable.NoticeBannerView_iconAltText) {
                    this.iconAltText = obtainStyledAttributes.getString(R.styleable.NoticeBannerView_iconAltText);
                } else if (index == R.styleable.NoticeBannerView_iconUrl) {
                    this.iconUrl = obtainStyledAttributes.getString(index);
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        obtainStyledAttributes.recycle();
        LinearLayout.inflate(getContext(), R.layout.mosaic_notice_banner, this);
    }
}
