package com.amazon.alexa.drivemode.api;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.amazon.alexa.drivemode.api.DriveModeCard;
/* loaded from: classes7.dex */
public abstract class DoubleIconsCard implements DriveModeCard {
    private String mHint;
    private Drawable mLeftIcon;
    private Drawable mRightIcon;
    private String mRightIconContentDescription;
    private String mSubtitle;
    private String mSubtitleAddendum;
    private Integer mSubtitleAddendumColor;
    private Integer mSubtitleColor;
    private String mTitle;

    public DoubleIconsCard(String str, String str2, Drawable drawable, Drawable drawable2) {
        this.mTitle = str;
        this.mSubtitle = str2;
        this.mLeftIcon = drawable;
        this.mRightIcon = drawable2;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public boolean allowIconTinting() {
        return true;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardType getCardType() {
        return DriveModeCard.CardType.TWO_ICONS_CARD;
    }

    @Nullable
    public String getHint() {
        return this.mHint;
    }

    public Drawable getLeftIcon() {
        return this.mLeftIcon;
    }

    public Drawable getRightIcon() {
        return this.mRightIcon;
    }

    public String getRightIconContentDescription() {
        return this.mRightIconContentDescription;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public String getSubtitleAddendum() {
        return this.mSubtitleAddendum;
    }

    @Nullable
    public Integer getSubtitleAddendumColor() {
        return this.mSubtitleAddendumColor;
    }

    @Nullable
    public Integer getSubtitleColor() {
        return this.mSubtitleColor;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public abstract void onCardClicked();

    public abstract void onHintClicked();

    public abstract void onRightIconClicked();

    public void setHint(String str) {
        this.mHint = str;
    }

    public void setLeftIcon(Drawable drawable) {
        this.mLeftIcon = drawable;
    }

    public void setRightIcon(Drawable drawable) {
        this.mRightIcon = drawable;
    }

    public void setRightIconContentDescription(String str) {
        this.mRightIconContentDescription = str;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public void setSubtitleAddendum(String str) {
        this.mSubtitleAddendum = str;
    }

    public void setSubtitleAddendumColor(Integer num) {
        this.mSubtitleAddendumColor = num;
    }

    public void setSubtitleColor(Integer num) {
        this.mSubtitleColor = num;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
