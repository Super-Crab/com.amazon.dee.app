package com.amazon.alexa.voice.tta.metrics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEventWithType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class TtaUiEventWithTypeProcessor extends TypedEventProcessor<TtaUiEventWithType> {
    private static final Map<TtaUiEvent, UiEventName> EVENT_MAP;
    @NonNull
    private final UiEventReporter sdkEventReporter;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(TtaUiEvent.VOX_TTA_IN_CHAT_LINK_GENERATED, UiEventName.VOX_TTA_IN_CHAT_LINK_GENERATED);
        hashMap.put(TtaUiEvent.VOX_TTA_IN_CHAT_LINK_TAPPED, UiEventName.VOX_TTA_IN_CHAT_LINK_TAPPED);
        EVENT_MAP = Collections.unmodifiableMap(hashMap);
    }

    public TtaUiEventWithTypeProcessor(@NonNull UiEventReporter uiEventReporter) {
        super(TtaUiEventWithType.class);
        this.sdkEventReporter = uiEventReporter;
    }

    @Override // com.amazon.alexa.voice.tta.metrics.TypedEventProcessor
    public boolean processTypedEvent(@NonNull TtaUiEventWithType ttaUiEventWithType, @NonNull EventTime eventTime) {
        UiEventName uiEventName = EVENT_MAP.get(ttaUiEventWithType.getEvent());
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), ttaUiEventWithType.getType());
        if (uiEventName == null) {
            return false;
        }
        this.sdkEventReporter.sendEvent(uiEventName, bundle);
        return true;
    }
}
