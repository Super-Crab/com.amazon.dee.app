package com.amazon.alexa.voice.tta.metrics;

import android.os.Bundle;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SimbaEventProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/SimbaEventProcessor;", "Lcom/amazon/alexa/voice/tta/metrics/MetricEventProcessor;", "sdkEventReporter", "Lcom/amazon/alexa/voice/tta/sdk/UiEventReporter;", "(Lcom/amazon/alexa/voice/tta/sdk/UiEventReporter;)V", "eventMap", "", "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "Lcom/amazon/alexa/voice/tta/metrics/SdkEvent;", "frictivePromptsTimer", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer;", "inner", "Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor;", "searchTimer", "suggestionsTimer", "createTimer", "name", "Lcom/amazon/alexa/api/UiEventName;", "processEvent", "", "event", "time", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "sendSdkEvent", "", "sdkEvent", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SimbaEventProcessor implements MetricEventProcessor {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String SOURCE_CALLED = "CALLED";
    @NotNull
    public static final String SOURCE_EMPTY = "EMPTY";
    @NotNull
    public static final String SOURCE_ERROR = "ERROR";
    @NotNull
    public static final String SOURCE_HAS_RESULTS = "HAS_RESULTS";
    @NotNull
    public static final String SOURCE_INCHAT = "INCHAT";
    @NotNull
    public static final String SOURCE_NO_NAMESPACE = "NO_NAMESPACE";
    @NotNull
    public static final String SOURCE_NO_PROMPT_ID = "NO_PROMPT_ID";
    @NotNull
    public static final String SOURCE_NO_RESULTS = "NO_RESULTS";
    @NotNull
    public static final String SOURCE_PILL = "PILL";
    @NotNull
    public static final String SOURCE_RESULT = "RESULT";
    @NotNull
    public static final String SOURCE_STARTED = "STARTED";
    @NotNull
    public static final String SOURCE_SUGGESTION = "SUGGESTION";
    @NotNull
    public static final String SOURCE_TIMEOUT = "TIMEOUT";
    private final Map<TtaEvent, SdkEvent> eventMap;
    private final LatencyTimer<TtaEvent, TtaEvent> frictivePromptsTimer;
    private final AggregateEventProcessor inner;
    private final UiEventReporter sdkEventReporter;
    private final LatencyTimer<TtaEvent, TtaEvent> searchTimer;
    private final LatencyTimer<TtaEvent, TtaEvent> suggestionsTimer;

    /* compiled from: SimbaEventProcessor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/SimbaEventProcessor$Companion;", "", "()V", "SOURCE_CALLED", "", "SOURCE_EMPTY", "SOURCE_ERROR", "SOURCE_HAS_RESULTS", "SOURCE_INCHAT", "SOURCE_NO_NAMESPACE", "SOURCE_NO_PROMPT_ID", "SOURCE_NO_RESULTS", "SOURCE_PILL", "SOURCE_RESULT", "SOURCE_STARTED", "SOURCE_SUGGESTION", "SOURCE_TIMEOUT", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SimbaEventProcessor(@NotNull UiEventReporter sdkEventReporter) {
        Map<TtaEvent, SdkEvent> mapOf;
        Intrinsics.checkParameterIsNotNull(sdkEventReporter, "sdkEventReporter");
        this.sdkEventReporter = sdkEventReporter;
        this.searchTimer = createTimer(UiEventName.VOX_SIMBA_SEARCH_API_LATENCY);
        this.suggestionsTimer = createTimer(UiEventName.VOX_SIMBA_SUGGESTIONS_API_LATENCY);
        this.frictivePromptsTimer = createTimer(UiEventName.VOX_SIMBA_FRICTIVE_PROMPTS_API_LATENCY);
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(TtaUiEvent.VOX_SIMBA_ITEM_SELECTED, new SdkEvent(UiEventName.VOX_SIMBA_ITEM_SELECTED, null, null, 6, null)), TuplesKt.to(TtaUiEvent.LINK_RESULT_TAPPED, new SdkEvent(UiEventName.VOX_SIMBA_ITEM_SELECTED, SOURCE_INCHAT, null, 4, null)), TuplesKt.to(TtaUiEvent.HINT_RESULT_TAPPED, new SdkEvent(UiEventName.VOX_SIMBA_ITEM_SELECTED, SOURCE_RESULT, null, 4, null)), TuplesKt.to(TtaUiEvent.SUGGESTION_TAPPED, new SdkEvent(UiEventName.VOX_SIMBA_ITEM_SELECTED, SOURCE_SUGGESTION, null, 4, null)), TuplesKt.to(TtaUiEvent.PILL_TAPPED, new SdkEvent(UiEventName.VOX_SIMBA_ITEM_SELECTED, SOURCE_PILL, null, 4, null)), TuplesKt.to(TtaUiEvent.PILL_SHOWN, new SdkEvent(UiEventName.VOX_SIMBA_PILL_SHOWN, null, null, 6, null)), TuplesKt.to(TtaUiEvent.SWITCHED_TO_SCRIM, new SdkEvent(UiEventName.VOX_SIMBA_SWITCH_TO_VOICE, null, null, 6, null)), TuplesKt.to(TtaUiEvent.SHOW_MORE_DISPLAYED, new SdkEvent(UiEventName.VOX_SIMBA_SHOW_MORE_DISPLAYED, null, null, 6, null)), TuplesKt.to(TtaUiEvent.VOX_SIMBA_INCHAT_SHOWN, new SdkEvent(UiEventName.VOX_SIMBA_INCHAT_SHOWN, null, null, 6, null)), TuplesKt.to(TtaUiEvent.VOX_SIMBA_HINT_SHOWN, new SdkEvent(UiEventName.VOX_SIMBA_RESULT_SHOWN, null, null, 6, null)), TuplesKt.to(TtaUiEvent.VOX_SIMBA_SUGGESTION_SHOWN, new SdkEvent(UiEventName.VOX_SIMBA_SUGGESTION_SHOWN, null, null, 6, null)), TuplesKt.to(TtaUiEvent.VOX_SIMBA_SHOW_MORE_TAPPED, new SdkEvent(UiEventName.VOX_SIMBA_SHOW_MORE_TAPPED, null, null, 6, null)), TuplesKt.to(TtaUiEvent.VOX_TTA_LOCAL_PILL_SHOWN, new SdkEvent(UiEventName.VOX_TTA_LOCAL_PILL_SHOWN, null, null, 6, null)), TuplesKt.to(TtaUiEvent.VOX_TTA_LOCAL_PILL_SELECTED, new SdkEvent(UiEventName.VOX_TTA_LOCAL_PILL_SELECTED, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED, new SdkEvent(UiEventName.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED_EMPTY, new SdkEvent(UiEventName.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED, SOURCE_EMPTY, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED_NO_PROMPT_ID, new SdkEvent(UiEventName.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED, SOURCE_NO_PROMPT_ID, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED_NO_NAMESPACE, new SdkEvent(UiEventName.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED, SOURCE_NO_NAMESPACE, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_CALLED, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API, SOURCE_CALLED, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_CALL_COUNT, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API_CALL_COUNT, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_CALLED, new SdkEvent(UiEventName.VOX_SIMBA_FRICTIVE_PROMPTS_API, SOURCE_CALLED, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_CALL_COUNT, new SdkEvent(UiEventName.VOX_SIMBA_SUGGESTIONS_CALL_COUNT, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_SUCCESS_COUNT, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API_SUCCESS_COUNT, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_HAS_RESULTS, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API, SOURCE_HAS_RESULTS, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_NO_RESULTS, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API, SOURCE_NO_RESULTS, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_CANCELLED, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API_CANCELLED, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_SUCCESS_COUNT, new SdkEvent(UiEventName.VOX_SIMBA_SUGGESTIONS_SUCCESS_COUNT, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_HAS_RESULTS, new SdkEvent(UiEventName.VOX_SIMBA_FRICTIVE_PROMPTS_API, SOURCE_HAS_RESULTS, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_NO_RESULTS, new SdkEvent(UiEventName.VOX_SIMBA_FRICTIVE_PROMPTS_API, SOURCE_NO_RESULTS, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_ERROR, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API, "ERROR", null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_ERROR, new SdkEvent(UiEventName.VOX_SIMBA_FRICTIVE_PROMPTS_API, "ERROR", null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_FAIL_COUNT, new SdkEvent(UiEventName.VOX_SIMBA_SUGGESTIONS_FAIL_COUNT, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_STARTED, new SdkEvent(UiEventName.VOX_SIMBA_FRICTIVE_PROMPTS_API, SOURCE_STARTED, null, 4, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_FRICTIVE_UTTERANCE, new SdkEvent(UiEventName.VOX_SIMBA_FRICTIVE_UTTERANCE, null, null, 6, null)), TuplesKt.to(AppSearchEvent.VOX_SIMBA_SEARCH_API_TIMEOUT, new SdkEvent(UiEventName.VOX_SIMBA_SEARCH_API, "TIMEOUT", null, 4, null)));
        this.eventMap = mapOf;
        AggregateEventProcessor.Builder isEqualTo = new AggregateEventProcessor(new MetricEventProcessor[0]).whenEvent().isEqualTo(AppSearchEvent.VOX_SIMBA_SEARCH_API_CALL_COUNT);
        final LatencyTimer<TtaEvent, TtaEvent> latencyTimer = this.searchTimer;
        final Class eventClass = isEqualTo.getEventClass();
        final Function1 predicate = isEqualTo.getPredicate();
        predicate = predicate == null ? AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE : predicate;
        AggregateEventProcessor.Builder isEqualTo2 = isEqualTo.getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$thenDo$1
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(time, "time");
                if (!eventClass.isInstance(event)) {
                    return false;
                }
                Object cast = eventClass.cast(event);
                if (cast == null) {
                    Intrinsics.throwNpe();
                }
                TtaEvent ttaEvent = (TtaEvent) cast;
                if (!((Boolean) predicate.mo12165invoke(ttaEvent)).booleanValue()) {
                    return false;
                }
                latencyTimer.begin(ttaEvent, time);
                return true;
            }
        }).whenEvent().isEqualTo(AppSearchEvent.VOX_SIMBA_SEARCH_API_SUCCESS_COUNT);
        final LatencyTimer<TtaEvent, TtaEvent> latencyTimer2 = this.searchTimer;
        final Class eventClass2 = isEqualTo2.getEventClass();
        final Function1 predicate2 = isEqualTo2.getPredicate();
        predicate2 = predicate2 == null ? AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE : predicate2;
        AggregateEventProcessor.Builder isEqualTo3 = isEqualTo2.getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$thenDo$2
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(time, "time");
                if (!eventClass2.isInstance(event)) {
                    return false;
                }
                Object cast = eventClass2.cast(event);
                if (cast == null) {
                    Intrinsics.throwNpe();
                }
                TtaEvent ttaEvent = (TtaEvent) cast;
                if (!((Boolean) predicate2.mo12165invoke(ttaEvent)).booleanValue()) {
                    return false;
                }
                latencyTimer2.end(ttaEvent, time);
                return true;
            }
        }).whenEvent().isEqualTo(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_CALL_COUNT);
        final LatencyTimer<TtaEvent, TtaEvent> latencyTimer3 = this.suggestionsTimer;
        final Class eventClass3 = isEqualTo3.getEventClass();
        final Function1 predicate3 = isEqualTo3.getPredicate();
        predicate3 = predicate3 == null ? AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE : predicate3;
        AggregateEventProcessor.Builder isEqualTo4 = isEqualTo3.getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$thenDo$3
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(time, "time");
                if (!eventClass3.isInstance(event)) {
                    return false;
                }
                Object cast = eventClass3.cast(event);
                if (cast == null) {
                    Intrinsics.throwNpe();
                }
                TtaEvent ttaEvent = (TtaEvent) cast;
                if (!((Boolean) predicate3.mo12165invoke(ttaEvent)).booleanValue()) {
                    return false;
                }
                latencyTimer3.begin(ttaEvent, time);
                return true;
            }
        }).whenEvent().isEqualTo(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_SUCCESS_COUNT);
        final LatencyTimer<TtaEvent, TtaEvent> latencyTimer4 = this.suggestionsTimer;
        final Class eventClass4 = isEqualTo4.getEventClass();
        final Function1 predicate4 = isEqualTo4.getPredicate();
        predicate4 = predicate4 == null ? AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE : predicate4;
        AggregateEventProcessor.Builder isEqualTo5 = isEqualTo4.getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$thenDo$4
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(time, "time");
                if (!eventClass4.isInstance(event)) {
                    return false;
                }
                Object cast = eventClass4.cast(event);
                if (cast == null) {
                    Intrinsics.throwNpe();
                }
                TtaEvent ttaEvent = (TtaEvent) cast;
                if (!((Boolean) predicate4.mo12165invoke(ttaEvent)).booleanValue()) {
                    return false;
                }
                latencyTimer4.end(ttaEvent, time);
                return true;
            }
        }).whenEvent().isEqualTo(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_STARTED);
        final LatencyTimer<TtaEvent, TtaEvent> latencyTimer5 = this.frictivePromptsTimer;
        final Class eventClass5 = isEqualTo5.getEventClass();
        final Function1 predicate5 = isEqualTo5.getPredicate();
        predicate5 = predicate5 == null ? AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE : predicate5;
        AggregateEventProcessor.Builder isEqualTo6 = isEqualTo5.getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$thenDo$5
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(time, "time");
                if (!eventClass5.isInstance(event)) {
                    return false;
                }
                Object cast = eventClass5.cast(event);
                if (cast == null) {
                    Intrinsics.throwNpe();
                }
                TtaEvent ttaEvent = (TtaEvent) cast;
                if (!((Boolean) predicate5.mo12165invoke(ttaEvent)).booleanValue()) {
                    return false;
                }
                latencyTimer5.begin(ttaEvent, time);
                return true;
            }
        }).whenEvent().isEqualTo(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_CALLED);
        final LatencyTimer<TtaEvent, TtaEvent> latencyTimer6 = this.frictivePromptsTimer;
        final Class eventClass6 = isEqualTo6.getEventClass();
        final Function1 predicate6 = isEqualTo6.getPredicate();
        predicate6 = predicate6 == null ? AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE : predicate6;
        AggregateEventProcessor add = isEqualTo6.getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$thenDo$6
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(time, "time");
                if (!eventClass6.isInstance(event)) {
                    return false;
                }
                Object cast = eventClass6.cast(event);
                if (cast == null) {
                    Intrinsics.throwNpe();
                }
                TtaEvent ttaEvent = (TtaEvent) cast;
                if (!((Boolean) predicate6.mo12165invoke(ttaEvent)).booleanValue()) {
                    return false;
                }
                latencyTimer6.end(ttaEvent, time);
                return true;
            }
        });
        add.add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor$$special$$inlined$apply$lambda$1
            @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
            public final boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime eventTime) {
                Map map;
                Unit unit;
                Intrinsics.checkParameterIsNotNull(event, "event");
                Intrinsics.checkParameterIsNotNull(eventTime, "<anonymous parameter 1>");
                map = SimbaEventProcessor.this.eventMap;
                SdkEvent sdkEvent = (SdkEvent) map.get(event);
                if (sdkEvent != null) {
                    SimbaEventProcessor.this.sendSdkEvent(sdkEvent);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                return unit != null;
            }
        });
        this.inner = add;
    }

    private final LatencyTimer<TtaEvent, TtaEvent> createTimer(UiEventName uiEventName) {
        return new LatencyTimer<>(new SimbaEventProcessor$createTimer$1(this, uiEventName));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSdkEvent(SdkEvent sdkEvent) {
        Bundle bundle = new Bundle();
        String source = sdkEvent.getSource();
        if (source != null) {
            bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), source);
        }
        EventTime.Interval latency = sdkEvent.getLatency();
        if (latency != null) {
            bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), latency.getMilliseconds());
        }
        this.sdkEventReporter.sendEvent(sdkEvent.getName(), bundle);
    }

    @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
    public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(time, "time");
        return this.inner.processEvent(event, time);
    }
}
