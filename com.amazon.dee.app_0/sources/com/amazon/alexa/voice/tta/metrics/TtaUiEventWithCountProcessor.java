package com.amazon.alexa.voice.tta.metrics;

import android.os.Bundle;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEventWithCount;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TtaUiEventWithCountProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/TtaUiEventWithCountProcessor;", "Lcom/amazon/alexa/voice/tta/metrics/TypedEventProcessor;", "Lcom/amazon/alexa/voice/ui/onedesign/tta/metrics/TtaUiEventWithCount;", "sdkEventReporter", "Lcom/amazon/alexa/voice/tta/sdk/UiEventReporter;", "(Lcom/amazon/alexa/voice/tta/sdk/UiEventReporter;)V", "eventMap", "", "Lcom/amazon/alexa/voice/ui/onedesign/tta/metrics/TtaUiEvent;", "Lcom/amazon/alexa/api/UiEventName;", "getSdkEventReporter", "()Lcom/amazon/alexa/voice/tta/sdk/UiEventReporter;", "processTypedEvent", "", "event", "time", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class TtaUiEventWithCountProcessor extends TypedEventProcessor<TtaUiEventWithCount> {
    private final Map<TtaUiEvent, UiEventName> eventMap;
    @NotNull
    private final UiEventReporter sdkEventReporter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtaUiEventWithCountProcessor(@NotNull UiEventReporter sdkEventReporter) {
        super(TtaUiEventWithCount.class);
        Map<TtaUiEvent, UiEventName> mapOf;
        Intrinsics.checkParameterIsNotNull(sdkEventReporter, "sdkEventReporter");
        this.sdkEventReporter = sdkEventReporter;
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(TtaUiEvent.VOX_CHARS_BEFORE_SUGGESTIONS, UiEventName.VOX_CHARS_BEFORE_SUGGESTIONS), TuplesKt.to(TtaUiEvent.VOX_SIMBA_SUGGESTION_SELECTED_INDEX, UiEventName.VOX_SIMBA_SUGGESTION_SELECTED_INDEX), TuplesKt.to(TtaUiEvent.VOX_SIMBA_RESULT_SELECTED_INDEX, UiEventName.VOX_SIMBA_RESULT_SELECTED_INDEX));
        this.eventMap = mapOf;
    }

    @NotNull
    public final UiEventReporter getSdkEventReporter() {
        return this.sdkEventReporter;
    }

    @Override // com.amazon.alexa.voice.tta.metrics.TypedEventProcessor
    public boolean processTypedEvent(@NotNull TtaUiEventWithCount event, @NotNull EventTime time) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(time, "time");
        UiEventName uiEventName = this.eventMap.get(event.getEvent());
        if (uiEventName != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(AlexaMetadataBundleKey.SOURCE_CONTEXT.name(), event.getCount());
            this.sdkEventReporter.sendEvent(uiEventName, bundle);
            return true;
        }
        return false;
    }
}
