package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes9.dex */
public class ListItem extends ConstraintLayout {
    private static final int RESOURCE_NOT_FOUND = -1;
    private static final String TAG = ListItem.class.getSimpleName();
    protected boolean active;
    protected boolean clickable;
    protected int leftIcon;
    protected AppCompatImageView leftIconImageView;
    protected String linkText;
    protected String primaryText;
    protected int primaryTextMaxLines;
    protected TextView primaryTextView;
    protected String secondaryText;
    protected int secondaryTextMaxLines;
    protected TextView secondaryTextView;

    public ListItem(Context context) {
        this(context, null);
    }

    private void adjustTextBoxPadding() {
        this.primaryTextView = (TextView) findViewById(R.id.primaryText);
        this.secondaryTextView = (TextView) findViewById(R.id.secondaryText);
        TextView textView = this.primaryTextView;
        if (textView != null && this.secondaryTextView != null) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.secondaryTextView.getLayoutParams();
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_margin_lr);
            if (this.leftIcon != -1) {
                int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_twoline_margin_between);
                layoutParams.setMargins(0, resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_twoline_margin_top), dimensionPixelSize, 0);
                layoutParams2.setMargins(0, dimensionPixelSize2, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_with_image_twoline_margin_bottom));
                constraintSet.connect(this.primaryTextView.getId(), 3, 0, 3);
                constraintSet.clear(this.primaryTextView.getId(), 4);
                constraintSet.connect(this.secondaryTextView.getId(), 3, this.primaryTextView.getId(), 4);
            } else if (this.secondaryText == null) {
                LogUtils.v(TAG, "Adjusting margins for primary text only");
                layoutParams.setMargins(dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_oneline_margin_top), dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_oneline_margin_bottom));
                constraintSet.connect(this.primaryTextView.getId(), 3, 0, 3);
                constraintSet.connect(this.primaryTextView.getId(), 4, 0, 4);
            } else {
                LogUtils.v(TAG, "Adjusting margins for primary and secondary text");
                int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_twoline_margin_between);
                layoutParams.setMargins(dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_twoline_margin_top), dimensionPixelSize, 0);
                layoutParams2.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.mosaic_list_item_textbox_twoline_margin_bottom));
                constraintSet.connect(this.primaryTextView.getId(), 3, 0, 3);
                constraintSet.clear(this.primaryTextView.getId(), 4);
                constraintSet.connect(this.secondaryTextView.getId(), 3, this.primaryTextView.getId(), 4);
            }
            this.primaryTextView.setLayoutParams(layoutParams);
            this.secondaryTextView.setLayoutParams(layoutParams2);
            constraintSet.applyTo(this);
            invalidate();
            return;
        }
        LogUtils.e(TAG, "failed to adjust margins for null TextViews");
    }

    private void updateLeftIcon() {
        this.leftIconImageView = (AppCompatImageView) findViewById(R.id.leftIcon);
        AppCompatImageView appCompatImageView = this.leftIconImageView;
        if (appCompatImageView == null) {
            LogUtils.e(TAG, "leftIconImageView is null");
        } else if (this.leftIcon != -1 && !appCompatImageView.isShown()) {
            this.leftIconImageView.setImageResource(this.leftIcon);
            this.leftIconImageView.setVisibility(0);
        } else {
            this.leftIconImageView.setVisibility(8);
        }
    }

    void inflateLayout() {
        ViewGroup.inflate(getContext(), R.layout.mosaic_list_item, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setClickable(this.clickable);
        setFocusable(this.clickable);
        updateLeftIcon();
        setPrimaryText(this.primaryText);
        setSecondaryText(this.secondaryText);
        setActive(this.active);
        adjustTextBoxPadding();
    }

    public void setActive(boolean z) {
        if (z) {
            ViewUtils.updateTextColor(this, R.id.primaryText, ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicAction10));
        } else {
            ViewUtils.updateTextColor(this, R.id.primaryText, ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral10));
        }
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
        ViewUtils.updateTextView(this, R.id.primaryText, this.primaryText);
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
        ViewUtils.updateTextView(this, R.id.secondaryText, this.secondaryText);
    }

    public void setViewOnClick(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public ListItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListItem, 0, 0);
        try {
            this.clickable = obtainStyledAttributes.getBoolean(R.styleable.ListItem_clickable, false);
            this.primaryText = obtainStyledAttributes.getString(R.styleable.ListItem_primaryText);
            this.secondaryText = obtainStyledAttributes.getString(R.styleable.ListItem_secondaryText);
            this.linkText = obtainStyledAttributes.getString(R.styleable.ListItem_linkText);
            this.leftIcon = obtainStyledAttributes.getResourceId(R.styleable.ListItem_leftIcon, -1);
            this.active = obtainStyledAttributes.getBoolean(R.styleable.ListItem_active, false);
            this.primaryTextMaxLines = obtainStyledAttributes.getInt(R.styleable.ListItem_primaryTextMaxLines, 2);
            this.secondaryTextMaxLines = obtainStyledAttributes.getInt(R.styleable.ListItem_secondaryTextMaxLines, 1);
            obtainStyledAttributes.recycle();
            inflateLayout();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
