package com.amazon.alexa.drivemode.api;

import android.graphics.drawable.Drawable;
import com.amazon.alexa.drivemode.api.DriveModeCard;
/* loaded from: classes7.dex */
public abstract class SecureCard implements DriveModeCard {
    private String mButtonText;
    private String mDescription;
    private Drawable mIcon;
    private boolean mIsSecure;
    private String mLastUserAction;
    private String mTitle;

    public SecureCard(String str, String str2, Drawable drawable, String str3, boolean z) {
        this.mTitle = str;
        this.mDescription = str2;
        this.mIcon = drawable;
        this.mButtonText = str3;
        this.mIsSecure = z;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public boolean allowIconTinting() {
        return true;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardType getCardType() {
        return DriveModeCard.CardType.SECURE_CARD;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public String getLastUserAction() {
        return this.mLastUserAction;
    }

    public abstract int getSecuredButtonBackground();

    public String getTitle() {
        return this.mTitle;
    }

    public abstract int getUnsecuredButtonBackground();

    public boolean isSecure() {
        return this.mIsSecure;
    }

    public abstract void onCardClicked();

    public void setButtonText(String str) {
        this.mButtonText = str;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
    }

    public void setLastUserAction(String str) {
        this.mLastUserAction = str;
    }

    public void setSecure(boolean z) {
        this.mIsSecure = z;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
