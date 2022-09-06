package com.amazon.alexa.drivemode.api;

import android.graphics.drawable.Drawable;
import com.amazon.alexa.drivemode.api.DriveModeCard;
/* loaded from: classes7.dex */
public abstract class SingleIconCard implements DriveModeCard {
    private String mDescription;
    private Drawable mIcon;
    private String mTitle;

    public SingleIconCard(String str, String str2, Drawable drawable) {
        this.mTitle = str;
        this.mDescription = str2;
        this.mIcon = drawable;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public boolean allowIconTinting() {
        return true;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardType getCardType() {
        return DriveModeCard.CardType.SINGLE_ICON_CARD;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public abstract void onCardClicked();

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
