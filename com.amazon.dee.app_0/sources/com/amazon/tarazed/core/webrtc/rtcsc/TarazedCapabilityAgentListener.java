package com.amazon.tarazed.core.webrtc.rtcsc;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.rtcsc.android.typedapi.EventInterpreter;
import com.amazon.rtcsc.android.typedapi.constants.Keywords;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.payloads.events.OfferEventPayload;
import com.amazon.rtcsc.capabilityagentclient.RtcscCAEventListener;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.webrtc.WebRTCManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedCapabilityAgentListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgentListener;", "Lcom/amazon/rtcsc/capabilityagentclient/RtcscCAEventListener;", "webRTCManager", "Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/webrtc/WebRTCManager;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "onSendEvent", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", JsonFields.EVENT_NAME, "eventPayload", "rtcSessionContext", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedCapabilityAgentListener extends RtcscCAEventListener {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_RTCSC_FAILED_INITIATE_SESSION = "RtcscInitiateSessionFailed";
    private static final String METRIC_RTCSC_FAILED_UPDATE_PEERCONNECTION = "RtcscUpdatePeerConnectionFailed";
    private static final String METRIC_RTCSC_FAILED_UPDATE_SESSION = "RtcscUpdateSessionFailed";
    private static final String TAG = "TarazedCapabAgntListr";
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final TarazedSessionNotifier notifier;
    private final WebRTCManager webRTCManager;

    /* compiled from: TarazedCapabilityAgentListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgentListener$Companion;", "", "()V", "METRIC_RTCSC_FAILED_INITIATE_SESSION", "", "METRIC_RTCSC_FAILED_UPDATE_PEERCONNECTION", "METRIC_RTCSC_FAILED_UPDATE_SESSION", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedCapabilityAgentListener(@NotNull WebRTCManager webRTCManager, @NotNull TarazedSessionNotifier notifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(webRTCManager, "webRTCManager");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.webRTCManager = webRTCManager;
        this.notifier = notifier;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAEventListener, com.amazon.rtcsc.interfaces.IRtcscEventListener
    public void onSendEvent(@NotNull String sessionId, @NotNull String eventName, @NotNull String eventPayload, @NotNull String rtcSessionContext) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(eventName, "eventName");
        Intrinsics.checkParameterIsNotNull(eventPayload, "eventPayload");
        Intrinsics.checkParameterIsNotNull(rtcSessionContext, "rtcSessionContext");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Received a " + eventName + " Event from RtcscService.");
        Payload payload = EventInterpreter.getPayload(eventName, eventPayload);
        if (payload == null) {
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            tarazedSessionLogger2.e(TAG, "Received null payload for event " + eventName + ", from RtcscService.");
            return;
        }
        switch (eventName.hashCode()) {
            case -1829768549:
                if (eventName.equals(Keywords.PEERCONNECTION_UPDATED_EVENT)) {
                    this.logger.v(TAG, "Rtcsc service successfully updated the PeerConnection");
                    return;
                }
                break;
            case -896787503:
                if (eventName.equals("SessionDisconnected")) {
                    this.logger.w(TAG, "Rtcsc service informs that session has been disconnected.");
                    this.webRTCManager.handleMediaDisconnected();
                    this.notifier.notifyMediaDisconnected();
                    return;
                }
                break;
            case -688497466:
                if (eventName.equals(Keywords.UPDATE_PEERCONNECTION_FAILED_EVENT)) {
                    this.logger.e(TAG, "Rtcsc service failed to update PeerConnection");
                    this.notifier.notifyMediaFailed();
                    this.metricsHelper.addCountHighPriority(TAG, METRIC_RTCSC_FAILED_UPDATE_PEERCONNECTION, 1.0d);
                    return;
                }
                break;
            case -34936774:
                if (eventName.equals("InitiateSessionFailed")) {
                    this.logger.e(TAG, "Rtcsc service failed to Initiate Session.");
                    this.notifier.notifyMediaFailed();
                    this.metricsHelper.addCountHighPriority(TAG, METRIC_RTCSC_FAILED_INITIATE_SESSION, 1.0d);
                    return;
                }
                break;
            case 484909449:
                if (eventName.equals(Keywords.OFFER_GENERATED_FOR_SESSION_UPDATE_EVENT)) {
                    this.logger.i(TAG, "Offer received from Rtcsc service for UpdateSession request.");
                    this.webRTCManager.handleOffer((OfferEventPayload) payload);
                    return;
                }
                break;
            case 668616714:
                if (eventName.equals(Keywords.UPDATE_SESSION_FAILED_EVENT)) {
                    this.logger.e(TAG, "Rtcsc service failed to update Session.");
                    this.notifier.notifyMediaFailed();
                    this.metricsHelper.addCountHighPriority(TAG, METRIC_RTCSC_FAILED_UPDATE_SESSION, 1.0d);
                    return;
                }
                break;
            case 886725939:
                if (eventName.equals("SessionConnected")) {
                    this.logger.i(TAG, "Rtcsc service confirmed that session has been connected.");
                    this.webRTCManager.handleMediaConnected();
                    this.notifier.notifyMediaConnected();
                    return;
                }
                break;
            case 1533277344:
                if (eventName.equals(Keywords.OFFER_GENERATED_FOR_SESSION_EVENT)) {
                    this.logger.i(TAG, "Offer received from Rtcsc service for InitiateSession request.");
                    this.webRTCManager.handleOffer((OfferEventPayload) payload);
                    return;
                }
                break;
        }
        TarazedSessionLogger tarazedSessionLogger3 = this.logger;
        tarazedSessionLogger3.w(TAG, "Received unhandled event " + eventName + ", from RtcscService. ");
    }
}
