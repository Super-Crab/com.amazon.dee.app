package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class TtaUiEventProcessor implements MetricEventProcessor {
    private static final Map<TtaEvent, UiEventName> EVENT_MAP;
    @NonNull
    private final UiEventReporter sdkEventReporter;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(TtaUiEvent.SWITCHED_TO_SCRIM, UiEventName.VOX_TTA_SWITCHED_TO_SCRIM);
        hashMap.put(TtaConversationEvent.MESSAGE_WELCOME_SHOWN, UiEventName.VOX_TTA_MESSAGE_WELCOME_SHOWN);
        hashMap.put(TtaConversationEvent.MESSAGE_RETURNING_SHOWN, UiEventName.VOX_TTA_MESSAGE_RETURNING_SHOWN);
        hashMap.put(TtaConversationEvent.MESSAGE_HISTORY_SHOWN, UiEventName.VOX_TTA_MESSAGE_HISTORY_SHOWN);
        hashMap.put(TtaConversationEvent.MESSAGE_REFRESH_SHOWN, UiEventName.VOX_TTA_MESSAGE_REFRESH_SHOWN);
        hashMap.put(TtaConversationEvent.DIALOG_SESSION_ENDED, UiEventName.VOX_TTA_DIALOG_SESSION_ENDED);
        hashMap.put(TtaConversationEvent.CLEAR_SCREEN_ON_VOICE, UiEventName.VOX_TTA_CLEAR_SCREEN_ON_VOICE);
        EVENT_MAP = Collections.unmodifiableMap(hashMap);
    }

    public TtaUiEventProcessor(@NonNull UiEventReporter uiEventReporter) {
        this.sdkEventReporter = uiEventReporter;
    }

    @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
    public boolean processEvent(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime) {
        UiEventName uiEventName = EVENT_MAP.get(ttaEvent);
        if (uiEventName == null) {
            return false;
        }
        this.sdkEventReporter.sendEvent(uiEventName, null);
        return true;
    }
}
