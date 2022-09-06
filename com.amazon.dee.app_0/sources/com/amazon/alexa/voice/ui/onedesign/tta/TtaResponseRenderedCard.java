package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
/* loaded from: classes11.dex */
public class TtaResponseRenderedCard implements TtaResponseCard {
    private final String cardType;
    private final String description;
    private final String id;
    private final String linkUrl;
    private final String msg;

    public TtaResponseRenderedCard(String str, String str2, String str3, String str4, String str5) {
        this.description = str3;
        this.linkUrl = str4;
        this.msg = str2;
        this.cardType = str5;
        this.id = str;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseCard
    public String getCardType() {
        return this.cardType;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseCard
    public String getDescription() {
        return this.description;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseMessage
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseCard
    public String getLinkUrl() {
        return this.linkUrl;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
    public String getMessage() {
        return this.msg;
    }
}
