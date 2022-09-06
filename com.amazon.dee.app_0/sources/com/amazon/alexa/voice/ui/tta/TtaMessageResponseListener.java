package com.amazon.alexa.voice.ui.tta;

import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import java.util.List;
/* loaded from: classes11.dex */
public interface TtaMessageResponseListener {
    void onMessage(TtaResponseCard ttaResponseCard);

    void onMessage(TtaResponseMessage ttaResponseMessage);

    void onMessage(List<TtaInChatResultCard> list);
}
