package com.amazon.alexa.drivemode.api;
/* loaded from: classes7.dex */
public abstract class DefaultDriveModeCardsProvider implements DriveModeCardsProvider {
    private DriveModeCardChangeListener listener;

    @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
    public void addCardChangeListener(DriveModeCardChangeListener driveModeCardChangeListener) {
        this.listener = driveModeCardChangeListener;
    }

    public DriveModeCardChangeListener getCardChangeListener() {
        return this.listener;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
    public void removeCardChangeListener() {
        this.listener = null;
    }
}
