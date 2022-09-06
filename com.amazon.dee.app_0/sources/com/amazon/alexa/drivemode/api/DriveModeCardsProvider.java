package com.amazon.alexa.drivemode.api;
/* loaded from: classes7.dex */
public interface DriveModeCardsProvider {
    void addCardChangeListener(DriveModeCardChangeListener driveModeCardChangeListener);

    void provideCards();

    void removeCardChangeListener();
}
