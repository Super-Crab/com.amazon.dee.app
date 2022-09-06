package com.amazon.alexa.voice.ui.tta;

import com.amazon.alexa.voice.ui.util.BooleanProperty;
/* loaded from: classes11.dex */
public interface TtaMessageHandler {
    BooleanProperty isThinking();

    void send(TtaRequestMessage ttaRequestMessage);

    void setConversationHistoryListener(TtaConversationHistoryListener ttaConversationHistoryListener);

    void setRequestListener(TtaMessageRequestListener ttaMessageRequestListener);

    void setResponseListener(TtaMessageResponseListener ttaMessageResponseListener);
}
