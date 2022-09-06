package com.amazon.alexa.drivemode.api;

import android.graphics.drawable.Drawable;
import com.amazon.alexa.drivemode.api.DriveModeCard;
/* loaded from: classes7.dex */
public abstract class SingleIconCardV2 implements DriveModeCard {
    private Drawable mIcon;
    private String mSubtitle;
    private String mSubtitleAddendum;
    private Integer mSubtitleAddendumColor;
    private String mTitle;

    public SingleIconCardV2(String str, String str2, Drawable drawable) {
        this.mTitle = str;
        this.mSubtitle = str2;
        this.mIcon = drawable;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public boolean allowIconTinting() {
        return true;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardType getCardType() {
        return DriveModeCard.CardType.SINGLE_ICON_CARD_V2;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getSubtitleAddendum() {
        return this.mSubtitleAddendum;
    }

    public Integer getSubtitleAddendumColor() {
        return this.mSubtitleAddendumColor;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public abstract void onCardClicked();

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
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

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
