package com.amazon.alexa.drive.entertainment;

import android.graphics.drawable.Drawable;
import com.amazon.alexa.drivemode.api.DriveModeCard;
/* loaded from: classes7.dex */
public abstract class EntertainmentCard implements DriveModeCard {
    private Drawable albumArtImage;
    private Drawable nextIcon;
    private Drawable playPauseIcon;
    private Drawable previousIcon;
    private String subTitle;
    private String title;

    public EntertainmentCard(String str, String str2, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.title = str;
        this.subTitle = str2;
        this.albumArtImage = drawable;
        this.playPauseIcon = drawable2;
        this.previousIcon = drawable3;
        this.nextIcon = drawable4;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public boolean allowIconTinting() {
        return true;
    }

    public Drawable getAlbumArtImage() {
        return this.albumArtImage;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardType getCardType() {
        return DriveModeCard.CardType.ENTERTAINMENT_CARD;
    }

    public Drawable getNextIcon() {
        return this.nextIcon;
    }

    public Drawable getPlayPauseIcon() {
        return this.playPauseIcon;
    }

    public Drawable getPreviousIcon() {
        return this.previousIcon;
    }

    public String getSubtitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public abstract void onCardClicked();

    public abstract void onNextButtonPressed();

    public abstract void onPlayPauseButtonPressed();

    public abstract void onPreviousButtonPressed();

    public void setAlbumArtImage(Drawable drawable) {
        this.albumArtImage = drawable;
    }

    public void setNextIcon(Drawable drawable) {
        this.nextIcon = drawable;
    }

    public void setPlayPauseIcon(Drawable drawable) {
        this.playPauseIcon = drawable;
    }

    public void setPreviousIcon(Drawable drawable) {
        this.previousIcon = drawable;
    }

    public void setSubtitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
