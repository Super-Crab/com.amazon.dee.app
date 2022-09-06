package com.amazon.alexa.voice.ui.tta;

import java.util.List;
/* loaded from: classes11.dex */
public interface TtaConversationHistoryListener {
    void onClear(String str);

    void onMessageHistory(List<TtaMessage> list);
}
