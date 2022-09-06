package com.amazon.alexa.voice.ui.tta.search;

import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
/* loaded from: classes11.dex */
public interface TtaInChatResultCard extends TtaResponseMessage {
    ItemPosition getItemPosition();

    ItemRoute getItemRoute();

    @Override // com.amazon.alexa.voice.ui.tta.TtaResponseMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
    String getMessage();

    String getSecondMessage();

    int getType();
}
