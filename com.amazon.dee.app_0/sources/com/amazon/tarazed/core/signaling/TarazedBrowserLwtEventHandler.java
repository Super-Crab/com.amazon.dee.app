package com.amazon.tarazed.core.signaling;

import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.signaling.events.EmptySerializable;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedBrowserLwtEventHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0016J\r\u0010\u001b\u001a\u00020\rH\u0001¢\u0006\u0002\b\u001cR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/tarazed/core/signaling/TarazedBrowserLwtEventHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/signaling/events/EmptySerializable;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "(Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;)V", "browserLwtPingTimer", "Lkotlinx/coroutines/Job;", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "payloadSerializer", "Lkotlinx/serialization/DeserializationStrategy;", "getPayloadSerializer", "()Lkotlinx/serialization/DeserializationStrategy;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "initBrowserLwtPingTimer", "initBrowserLwtPingTimer$TarazedMobileCore_release", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedBrowserLwtEventHandler implements TarazedEventHandler<EmptySerializable> {
    private static final String EVENT_TYPE_LWT_DISCONNECT = "lwtDisconnect";
    private static final String EVENT_TYPE_PING = "ping";
    private static final String EVENT_TYPE_PONG = "pong";
    private static final String METRIC_LWT_RECONNECT_TIMEOUT = "BrowserLwtReconnectTimeout";
    private static final long PING_FREQUENCY_MS = 5000;
    private static final String TAG = "BrowserLwtEventHandler";
    private Job browserLwtPingTimer;
    @NotNull
    private final List<String> handledEventTypes;
    private final TarazedIoTManager iotManager;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final TarazedSessionNotifier sessionNotifier;
    public static final Companion Companion = new Companion(null);
    private static long MAX_RECONNECT_WAIT_MS = 30000;
    private static double MAX_BROWSER_PING_ATTEMPTS = MAX_RECONNECT_WAIT_MS / 5000;

    /* compiled from: TarazedBrowserLwtEventHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/signaling/TarazedBrowserLwtEventHandler$Companion;", "", "()V", "EVENT_TYPE_LWT_DISCONNECT", "", "EVENT_TYPE_PING", "EVENT_TYPE_PONG", "MAX_BROWSER_PING_ATTEMPTS", "", "MAX_RECONNECT_WAIT_MS", "", "MAX_RECONNECT_WAIT_MS$annotations", "getMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release", "()J", "setMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release", "(J)V", "METRIC_LWT_RECONNECT_TIMEOUT", "PING_FREQUENCY_MS", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void MAX_RECONNECT_WAIT_MS$annotations() {
        }

        public final long getMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release() {
            return TarazedBrowserLwtEventHandler.MAX_RECONNECT_WAIT_MS;
        }

        public final void setMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release(long j) {
            TarazedBrowserLwtEventHandler.MAX_RECONNECT_WAIT_MS = j;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedBrowserLwtEventHandler(@NotNull TarazedIoTManager iotManager, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSessionNotifier sessionNotifier) {
        Job launch$default;
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        this.iotManager = iotManager;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.sessionNotifier = sessionNotifier;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedBrowserLwtEventHandler$browserLwtPingTimer$1(null), 1, null);
        this.browserLwtPingTimer = launch$default;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{EVENT_TYPE_LWT_DISCONNECT, "ping", EVENT_TYPE_PONG});
        this.handledEventTypes = listOf;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super EmptySerializable> getPayloadSerializer() {
        return EmptySerializable.Companion.serializer();
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends EmptySerializable> e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        String type = e.getType();
        int hashCode = type.hashCode();
        if (hashCode == 3441010) {
            if (!type.equals("ping")) {
                return;
            }
            this.logger.i(TAG, "Received ping request from browser, responding");
            TarazedIoTManager.sendEvent$default(this.iotManager, Json.Default.stringify(TarazedEvent.Companion.serializer(EmptySerializable.Companion.serializer()), new TarazedEvent(EVENT_TYPE_PONG, new EmptySerializable())), null, 2, null);
        } else if (hashCode != 3446776) {
            if (hashCode != 1089130949 || !type.equals(EVENT_TYPE_LWT_DISCONNECT) || this.browserLwtPingTimer.isActive()) {
                return;
            }
            this.logger.w(TAG, GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline107("LWT message received, waiting "), MAX_RECONNECT_WAIT_MS, "ms before ending session"));
            this.browserLwtPingTimer = initBrowserLwtPingTimer$TarazedMobileCore_release();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedBrowserLwtEventHandler$handleEvent$1(this, Json.Default.stringify(TarazedEvent.Companion.serializer(EmptySerializable.Companion.serializer()), new TarazedEvent("ping", new EmptySerializable())), null), 3, null);
        } else if (!type.equals(EVENT_TYPE_PONG) || !this.browserLwtPingTimer.isActive()) {
        } else {
            this.logger.i(TAG, "IoT connection re-established after receiving pong from browser");
            Job.DefaultImpls.cancel$default(this.browserLwtPingTimer, (CancellationException) null, 1, (Object) null);
        }
    }

    @VisibleForTesting
    @NotNull
    public final Job initBrowserLwtPingTimer$TarazedMobileCore_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1(this, null), 3, null);
        return launch$default;
    }
}
