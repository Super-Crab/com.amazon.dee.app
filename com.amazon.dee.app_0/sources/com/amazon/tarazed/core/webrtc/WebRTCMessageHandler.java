package com.amazon.tarazed.core.webrtc;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.core.webrtc.signals.Answer;
import com.amazon.tarazed.core.webrtc.signals.MediaSignal;
import com.amazon.tarazed.core.webrtc.signals.MediaSignalSerializer;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalTypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import org.jetbrains.annotations.NotNull;
/* compiled from: WebRTCMessageHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00172\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001a\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0016H\u0016R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/WebRTCMessageHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/webrtc/signals/MediaSignal;", "webRTCManager", "Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/webrtc/WebRTCManager;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "payloadSerializer", "Lkotlinx/serialization/DeserializationStrategy;", "getPayloadSerializer", "()Lkotlinx/serialization/DeserializationStrategy;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WebRTCMessageHandler implements TarazedEventHandler<MediaSignal<?>> {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String METRIC_UNKNOWN_EVENT_TYPE = "UnknownEventType";
    @NotNull
    public static final String TAG = "WebRTCMessageHandler";
    @NotNull
    private final List<String> handledEventTypes;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    private final DeserializationStrategy<MediaSignal<?>> payloadSerializer;
    private final WebRTCManager webRTCManager;

    /* compiled from: WebRTCMessageHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/WebRTCMessageHandler$Companion;", "", "()V", "METRIC_UNKNOWN_EVENT_TYPE", "", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WebRTCMessageHandler(@NotNull WebRTCManager webRTCManager, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(webRTCManager, "webRTCManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.webRTCManager = webRTCManager;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(WebRTCSignalTypes.EVENT_TYPE_MEDIA_SIGNAL);
        this.handledEventTypes = listOf;
        this.payloadSerializer = MediaSignalSerializer.INSTANCE;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super MediaSignal<?>> getPayloadSerializer() {
        return this.payloadSerializer;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends MediaSignal<?>> e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        MediaSignal<?> payload = e.getPayload();
        String signalType = payload.getSignalType();
        Object signalPayload = payload.getSignalPayload();
        if (signalType.hashCode() == -1412808770 && signalType.equals(WebRTCSignalTypes.ANSWER)) {
            WebRTCManager webRTCManager = this.webRTCManager;
            if (signalPayload == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.tarazed.core.webrtc.signals.Answer");
            }
            webRTCManager.handleAnswer(((Answer) signalPayload).toWebRTCHandshake());
            return;
        }
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown event type: ");
        outline107.append(e.getType());
        tarazedSessionLogger.w(TAG, outline107.toString());
        this.metricsHelper.addCount(TAG, METRIC_UNKNOWN_EVENT_TYPE, 1.0d);
    }
}
