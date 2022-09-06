package com.amazon.alexa.voice.ui.cards;
/* loaded from: classes11.dex */
public interface CardCreationEventListener {

    /* loaded from: classes11.dex */
    public enum ErrorType {
        UNKNOWN,
        CARD_DATA_NULL,
        INVALID_JSON,
        MISSING_CARD_TYPE
    }

    void cardControllerCreationFinished(String str, boolean z);

    void cardControllerCreationStart();

    void errorCreatingCard(String str, Exception exc);

    void errorCreatingCardController(String str, Exception exc);

    void errorParsingJson(ErrorType errorType, String str);

    void foundCardFactory(String str);

    void missingCardFactory(String str);

    void parsingJsonFinished(boolean z);

    void parsingJsonStart();
}
