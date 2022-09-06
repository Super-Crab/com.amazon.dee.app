package com.amazon.alexa.drivemode.api;
/* loaded from: classes7.dex */
public interface DriveModeCard {

    /* loaded from: classes7.dex */
    public enum CardDomain {
        ENTERTAINMENT,
        COMMS,
        NAVIGATION,
        SMART_HOME,
        UNKNOWN
    }

    /* loaded from: classes7.dex */
    public enum CardType {
        SINGLE_ICON_CARD,
        TWO_ICONS_CARD,
        SECURE_CARD,
        SINGLE_ICON_CARD_V2,
        ENTERTAINMENT_CARD
    }

    boolean allowIconTinting();

    CardDomain getCardDomain();

    int getCardId();

    CardType getCardType();
}
