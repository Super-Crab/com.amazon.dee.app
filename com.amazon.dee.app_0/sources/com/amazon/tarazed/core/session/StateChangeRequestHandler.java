package com.amazon.tarazed.core.session;

import android.app.Activity;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.session.sessionEvents.SessionEvents;
import com.amazon.tarazed.core.session.sessionEvents.StateChangeRequest;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionActivity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateChangeRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0016R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/core/session/StateChangeRequestHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/session/sessionEvents/StateChangeRequest;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "session", "Lcom/amazon/tarazed/core/session/TarazedSession;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/session/TarazedSession;)V", "allowedRequests", "", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType;", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "payloadSerializer", "Lkotlinx/serialization/DeserializationStrategy;", "getPayloadSerializer", "()Lkotlinx/serialization/DeserializationStrategy;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class StateChangeRequestHandler implements TarazedEventHandler<StateChangeRequest> {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String METRIC_INVALID_REQUEST_RECEIVED = "InvalidRequestReceived";
    @NotNull
    public static final String TAG = "StateChangeReqHandler";
    private final Set<TarazedSessionStateChangeType> allowedRequests;
    @NotNull
    private final List<String> handledEventTypes;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final TarazedSession session;

    /* compiled from: StateChangeRequestHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/session/StateChangeRequestHandler$Companion;", "", "()V", "METRIC_INVALID_REQUEST_RECEIVED", "", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedSessionStateChangeType.values().length];

        static {
            $EnumSwitchMapping$0[TarazedSessionStateChangeType.RESUME_SESSION.ordinal()] = 1;
        }
    }

    public StateChangeRequestHandler(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSession session) {
        Set<TarazedSessionStateChangeType> of;
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(session, "session");
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.session = session;
        of = SetsKt__SetsKt.setOf((Object[]) new TarazedSessionStateChangeType[]{TarazedSessionStateChangeType.REQUEST_START_SESSION, TarazedSessionStateChangeType.START_SESSION, TarazedSessionStateChangeType.PAUSE_SESSION, TarazedSessionStateChangeType.RESUME_SESSION, TarazedSessionStateChangeType.REQUEST_RESUME_SESSION, TarazedSessionStateChangeType.SUSPEND_SESSION, TarazedSessionStateChangeType.END_SESSION});
        this.allowedRequests = of;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(SessionEvents.STATE_CHANGE_REQUEST);
        this.handledEventTypes = listOf;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super StateChangeRequest> getPayloadSerializer() {
        return StateChangeRequest.Companion.serializer();
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends StateChangeRequest> e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        this.logger.i(TAG, "State change request received.");
        try {
            TarazedSessionStateChangeType lookupStateChange = TarazedSessionStateChangeType.Companion.lookupStateChange(e.getPayload().getStateChange());
            if (lookupStateChange != null && this.allowedRequests.contains(lookupStateChange)) {
                TarazedSessionLogger tarazedSessionLogger = this.logger;
                tarazedSessionLogger.d(TAG, "processing stateChange: " + lookupStateChange);
                if (WhenMappings.$EnumSwitchMapping$0[lookupStateChange.ordinal()] == 1) {
                    lookupStateChange = TarazedSessionStateChangeType.REQUEST_RESUME_SESSION;
                }
                if (lookupStateChange == TarazedSessionStateChangeType.END_SESSION && TarazedMediaProjectionActivity.Companion.isWaitingForMediaProjectionResult$TarazedMobileCore_release()) {
                    this.logger.i(TAG, "Killing mediaProjection activity as the browser session has ended");
                    Activity self$TarazedMobileCore_release = TarazedMediaProjectionActivity.Companion.getSelf$TarazedMobileCore_release();
                    if (self$TarazedMobileCore_release != null) {
                        self$TarazedMobileCore_release.finishActivity(0);
                    }
                }
                this.session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(lookupStateChange, TarazedSessionStateChangeSource.SOURCE_AGENT));
                return;
            }
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            tarazedSessionLogger2.w(TAG, "Invalid state change request was made: " + e.getPayload().getStateChange());
        } catch (IllegalArgumentException e2) {
            TarazedSessionLogger tarazedSessionLogger3 = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid request received: ");
            outline107.append(e.getPayload().getStateChange());
            tarazedSessionLogger3.w(TAG, outline107.toString(), e2);
            this.metricsHelper.addCount(TAG, METRIC_INVALID_REQUEST_RECEIVED, 1.0d);
        }
    }
}
