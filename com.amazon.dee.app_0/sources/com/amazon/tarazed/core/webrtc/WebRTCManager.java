package com.amazon.tarazed.core.webrtc;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.rtcsc.android.typedapi.constants.BundlePolicy;
import com.amazon.rtcsc.android.typedapi.constants.KeyExchange;
import com.amazon.rtcsc.android.typedapi.constants.MediaDirection;
import com.amazon.rtcsc.android.typedapi.constants.RtcpMuxPolicy;
import com.amazon.rtcsc.android.typedapi.constants.VideoSourceType;
import com.amazon.rtcsc.android.typedapi.payloads.directives.DisconnectSessionPayload;
import com.amazon.rtcsc.android.typedapi.payloads.directives.InitiateSessionPayload;
import com.amazon.rtcsc.android.typedapi.payloads.directives.StartPeerConnectionPayload;
import com.amazon.rtcsc.android.typedapi.payloads.directives.UpdatePeerConnectionPayload;
import com.amazon.rtcsc.android.typedapi.payloads.directives.UpdateSessionPayload;
import com.amazon.rtcsc.android.typedapi.payloads.events.OfferEventPayload;
import com.amazon.rtcsc.android.typedapi.types.AudioStream;
import com.amazon.rtcsc.android.typedapi.types.IceServer;
import com.amazon.rtcsc.android.typedapi.types.MediaStreams;
import com.amazon.rtcsc.android.typedapi.types.VideoStream;
import com.amazon.rtcsc.android.typedapi.types.WebRTCHandshake;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.DeviceRotationHandler;
import com.amazon.tarazed.core.rotation.RotationListener;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.core.webrtc.android.WebRTCManagerHelper;
import com.amazon.tarazed.core.webrtc.rtcsc.Constants;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedAppClient;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedAppClientListener;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedCapabilityAgent;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedCapabilityAgentListener;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedScreenCapturerListener;
import com.amazon.tarazed.core.webrtc.signals.MediaSignal;
import com.amazon.tarazed.core.webrtc.signals.MediaSignalSerializer;
import com.amazon.tarazed.core.webrtc.signals.Offer;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalFormats;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalTypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WebRTCManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 f2\u00020\u0001:\u0002fgB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\u0006\u0010P\u001a\u00020QJ\u000e\u0010R\u001a\u00020Q2\u0006\u0010S\u001a\u00020TJ\u0006\u0010U\u001a\u00020QJ\u0006\u0010V\u001a\u00020QJ\u000e\u0010W\u001a\u00020Q2\u0006\u0010X\u001a\u00020YJ\u0006\u0010Z\u001a\u00020QJ\u0006\u0010[\u001a\u00020QJ\u0011\u0010\\\u001a\u00020QH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010]J\u0006\u0010^\u001a\u00020QJ\u0006\u0010_\u001a\u00020QJ\u0010\u0010`\u001a\u00020Q2\u0006\u0010a\u001a\u00020\u0017H\u0002J\u0010\u0010b\u001a\u00020Q2\u0006\u0010S\u001a\u00020TH\u0002J\u0010\u0010c\u001a\u00020Q2\u0006\u0010d\u001a\u00020 H\u0002J\u0018\u0010e\u001a\u00020Q2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001f\u001a\u00020 8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\u00020/8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b0\u0010\"\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u00105\u001a\u00020\u00178\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b6\u0010\"\u001a\u0004\b7\u00108R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082.¢\u0006\u0002\n\u0000R$\u0010F\u001a\u00020\u00148\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bG\u0010\"\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010L\u001a\u00020\u00148\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bM\u0010\"\u001a\u0004\bN\u0010I\"\u0004\bO\u0010KR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006h"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "", "webRTCManagerHelper", "Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "rotationListener", "Lcom/amazon/tarazed/core/rotation/RotationListener;", "iceServers", "", "Lcom/amazon/rtcsc/android/typedapi/types/IceServer;", "videoParameters", "Lcom/amazon/tarazed/core/webrtc/VideoParameters;", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "", "screenHeight", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "mainCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/rotation/RotationListener;Ljava/util/List;Lcom/amazon/tarazed/core/webrtc/VideoParameters;IILjava/lang/String;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "expectingAnswerForUpdate", "", "expectingAnswerForUpdate$annotations", "()V", "getExpectingAnswerForUpdate$TarazedMobileCore_release", "()Z", "setExpectingAnswerForUpdate$TarazedMobileCore_release", "(Z)V", "mediaConnected", "offerJob", "Lkotlinx/coroutines/Job;", "getOfferJob$TarazedMobileCore_release", "()Lkotlinx/coroutines/Job;", "setOfferJob$TarazedMobileCore_release", "(Lkotlinx/coroutines/Job;)V", "rotationHandler", "Lcom/amazon/tarazed/core/rotation/DeviceRotationHandler;", "rotationHandler$annotations", "getRotationHandler$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/rotation/DeviceRotationHandler;", "setRotationHandler$TarazedMobileCore_release", "(Lcom/amazon/tarazed/core/rotation/DeviceRotationHandler;)V", "rtcscSessionDomainForApp", "rtcscSessionDomainForApp$annotations", "getRtcscSessionDomainForApp$TarazedMobileCore_release", "()Ljava/lang/String;", "sdpModifier", "Lcom/amazon/tarazed/core/webrtc/SdpModifier;", "sessionInitialized", "tarazedAppClient", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClient;", "tarazedAppClientListener", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClientListener;", "tarazedCapabilityAgent", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgent;", "tarazedCapabilityAgentListener", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgentListener;", "tarazedScreenCapturerListener", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedScreenCapturerListener;", "videoHeight", "videoHeight$annotations", "getVideoHeight$TarazedMobileCore_release", "()I", "setVideoHeight$TarazedMobileCore_release", "(I)V", "videoWidth", "videoWidth$annotations", "getVideoWidth$TarazedMobileCore_release", "setVideoWidth$TarazedMobileCore_release", Metrics.DISCONNECT, "", "handleAnswer", WebRTCSignalTypes.ANSWER, "Lcom/amazon/rtcsc/android/typedapi/types/WebRTCHandshake;", "handleMediaConnected", "handleMediaDisconnected", "handleOffer", "offerPayload", "Lcom/amazon/rtcsc/android/typedapi/payloads/events/OfferEventPayload;", "initiateSession", "pause", "prepareSession", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerRTCSCListeners", "resume", "sendOffer", WebRTCSignalFormats.SDP, "sendUpdatePeerConnection", "sendUpdateSessionDirective", "isVideoOn", "setVideoDimensions", "Companion", "WebRTCDeviceRotationHandler", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WebRTCManager {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String METRIC_BROWSER_INITIAL_ANSWER_LATENCY = "BrowserInitialAnswerLatency";
    private static final String METRIC_INITIATE_SESSION_REQUEST = "InitiateSessionRequestSent";
    private static final String METRIC_RECEIVE_ANSWER_TIMEOUT = "ReceiveAnswerTimeout";
    @NotNull
    public static final String METRIC_RTCSC_OFFER_LATENCY = "RTCSCOfferLatency";
    private static final String METRIC_UPDATE_PEER_CONNECTION_REQUEST = "UpdatePeerConnectionRequestSent";
    private static final String METRIC_UPDATE_SESSION_REQUEST = "UpdateSessionRequestSent";
    @NotNull
    public static final String METRIC_WEBRTC_START_LATENCY = "WebRTCStartLatency";
    private static final int SEND_OFFER_MAX_ATTEMPTS = 2;
    private static final long SEND_OFFER_RETRY_DELAY_MILLIS = 45000;
    @NotNull
    public static final String TAG = "WebRTCManager";
    private final BizMetricsHelper bizMetricsHelper;
    private boolean expectingAnswerForUpdate;
    private final List<IceServer> iceServers;
    private final TarazedIoTManager iotManager;
    private final TarazedSessionLogger logger;
    private final CoroutineScope mainCoroutineScope;
    private boolean mediaConnected;
    private final TarazedMetricsHelper metricsHelper;
    @Nullable
    private Job offerJob;
    @NotNull
    private DeviceRotationHandler rotationHandler;
    @NotNull
    private final String rtcscSessionDomainForApp;
    private SdpModifier sdpModifier;
    private final String sessionId;
    private boolean sessionInitialized;
    private final TarazedSessionNotifier sessionNotifier;
    private TarazedAppClient tarazedAppClient;
    private TarazedAppClientListener tarazedAppClientListener;
    private TarazedCapabilityAgent tarazedCapabilityAgent;
    private TarazedCapabilityAgentListener tarazedCapabilityAgentListener;
    private TarazedScreenCapturerListener tarazedScreenCapturerListener;
    private int videoHeight;
    private final VideoParameters videoParameters;
    private int videoWidth;
    private final WebRTCManagerHelper webRTCManagerHelper;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WebRTCManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/WebRTCManager$Companion;", "", "()V", "METRIC_BROWSER_INITIAL_ANSWER_LATENCY", "", "METRIC_INITIATE_SESSION_REQUEST", "METRIC_RECEIVE_ANSWER_TIMEOUT", "METRIC_RTCSC_OFFER_LATENCY", "METRIC_UPDATE_PEER_CONNECTION_REQUEST", "METRIC_UPDATE_SESSION_REQUEST", "METRIC_WEBRTC_START_LATENCY", "SEND_OFFER_MAX_ATTEMPTS", "", "SEND_OFFER_RETRY_DELAY_MILLIS", "", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: WebRTCManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/WebRTCManager$WebRTCDeviceRotationHandler;", "Lcom/amazon/tarazed/core/rotation/DeviceRotationHandler;", "(Lcom/amazon/tarazed/core/webrtc/WebRTCManager;)V", "onDeviceRotated", "", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "", "screenHeight", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private final class WebRTCDeviceRotationHandler implements DeviceRotationHandler {
        public WebRTCDeviceRotationHandler() {
        }

        @Override // com.amazon.tarazed.core.rotation.DeviceRotationHandler
        public void onDeviceRotated(int i, int i2) {
            TarazedSessionLogger tarazedSessionLogger = WebRTCManager.this.logger;
            Companion unused = WebRTCManager.Companion;
            tarazedSessionLogger.i(WebRTCManager.TAG, "Device rotated, updating resolution to width=" + i + ", height=" + i2);
            int videoWidth$TarazedMobileCore_release = WebRTCManager.this.getVideoWidth$TarazedMobileCore_release();
            int videoHeight$TarazedMobileCore_release = WebRTCManager.this.getVideoHeight$TarazedMobileCore_release();
            WebRTCManager.this.setVideoDimensions(i, i2);
            if (videoWidth$TarazedMobileCore_release != WebRTCManager.this.getVideoWidth$TarazedMobileCore_release() || videoHeight$TarazedMobileCore_release != WebRTCManager.this.getVideoHeight$TarazedMobileCore_release()) {
                TarazedSessionLogger tarazedSessionLogger2 = WebRTCManager.this.logger;
                Companion unused2 = WebRTCManager.Companion;
                tarazedSessionLogger2.i(WebRTCManager.TAG, "Dimensions changed, updating screen capturer dimensions");
                WebRTCManager.this.tarazedAppClient.setScreenCapturerDimensions(WebRTCManager.this.sessionId, WebRTCManager.this.getVideoWidth$TarazedMobileCore_release(), WebRTCManager.this.getVideoHeight$TarazedMobileCore_release());
                return;
            }
            TarazedSessionLogger tarazedSessionLogger3 = WebRTCManager.this.logger;
            Companion unused3 = WebRTCManager.Companion;
            tarazedSessionLogger3.i(WebRTCManager.TAG, "Dimensions have not changed since last update, not updating screen capturer dimensions");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WebRTCManager(@NotNull WebRTCManagerHelper webRTCManagerHelper, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedIoTManager iotManager, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull RotationListener rotationListener, @NotNull List<? extends IceServer> iceServers, @NotNull VideoParameters videoParameters, int i, int i2, @NotNull String sessionId, @NotNull CoroutineScope mainCoroutineScope, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(webRTCManagerHelper, "webRTCManagerHelper");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(rotationListener, "rotationListener");
        Intrinsics.checkParameterIsNotNull(iceServers, "iceServers");
        Intrinsics.checkParameterIsNotNull(videoParameters, "videoParameters");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(mainCoroutineScope, "mainCoroutineScope");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        this.webRTCManagerHelper = webRTCManagerHelper;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.iotManager = iotManager;
        this.sessionNotifier = sessionNotifier;
        this.iceServers = iceServers;
        this.videoParameters = videoParameters;
        this.sessionId = sessionId;
        this.mainCoroutineScope = mainCoroutineScope;
        this.bizMetricsHelper = bizMetricsHelper;
        this.rotationHandler = new WebRTCDeviceRotationHandler();
        this.rtcscSessionDomainForApp = deviceInfoUtility.getApp() + "." + Constants.SESSION_DOMAIN;
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Initializing WebRTCManager with video parameters: ");
        outline107.append(this.videoParameters);
        tarazedSessionLogger.i(TAG, outline107.toString());
        if (i2 >= 0 && i >= 0) {
            setVideoDimensions(i, i2);
            this.webRTCManagerHelper.setWebRTCLogger(this.logger);
            this.tarazedAppClient = this.webRTCManagerHelper.provideTarazedAppClient$TarazedMobileCore_release();
            this.tarazedCapabilityAgent = this.webRTCManagerHelper.provideTarazedCapabilityAgent$TarazedMobileCore_release();
            rotationListener.registerRotationHandler(this.rotationHandler);
            this.logger.i(TAG, "Registered device rotation listener");
            this.sdpModifier = new SdpModifier(this.logger, this.metricsHelper);
            return;
        }
        throw new IllegalArgumentException("Screen dimensions must be non-negative".toString());
    }

    @VisibleForTesting
    public static /* synthetic */ void expectingAnswerForUpdate$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void rotationHandler$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void rtcscSessionDomainForApp$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendOffer(String str) {
        TarazedIoTManager.sendEvent$default(this.iotManager, Json.Default.stringify(TarazedEvent.Companion.serializer(MediaSignalSerializer.INSTANCE), new TarazedEvent(WebRTCSignalTypes.EVENT_TYPE_MEDIA_SIGNAL, new MediaSignal(WebRTCSignalTypes.OFFER, new Offer(str)))), null, 2, null);
    }

    private final void sendUpdatePeerConnection(WebRTCHandshake webRTCHandshake) {
        SdpModifier sdpModifier = this.sdpModifier;
        int maxVideoBitrateKbps = this.videoParameters.getMaxVideoBitrateKbps();
        String value = webRTCHandshake.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "answer.value");
        UpdatePeerConnectionPayload updatePeerConnectionPayload = new UpdatePeerConnectionPayload(this.sessionId, this.rtcscSessionDomainForApp, new WebRTCHandshake(webRTCHandshake.getFormat(), sdpModifier.modifySDP(maxVideoBitrateKbps, value)));
        this.metricsHelper.addCount(TAG, METRIC_UPDATE_PEER_CONNECTION_REQUEST, 1.0d);
        this.logger.i(TAG, "Received answer from peer, sending directive to RTCSC");
        this.tarazedCapabilityAgent.handleDirective("UpdatePeerConnection", updatePeerConnectionPayload.getSerializedPayload());
    }

    private final void sendUpdateSessionDirective(boolean z) {
        VideoStream videoStream;
        if (z) {
            videoStream = new VideoStream(MediaDirection.SEND_ONLY, RtcpMuxPolicy.NEGOTIATE, false, 0, VideoSourceType.SCREEN);
        } else {
            videoStream = new VideoStream(MediaDirection.INACTIVE, RtcpMuxPolicy.NEGOTIATE, false, 0, VideoSourceType.SCREEN);
        }
        UpdateSessionPayload updateSessionPayload = new UpdateSessionPayload(this.sessionId, this.rtcscSessionDomainForApp, new MediaStreams(BundlePolicy.BALANCED, new AudioStream(MediaDirection.OFF, RtcpMuxPolicy.NEGOTIATE), videoStream), this.iceServers);
        this.metricsHelper.addCount(TAG, METRIC_UPDATE_SESSION_REQUEST, 1.0d);
        this.logger.i(TAG, "Updating the session, sending directive to RTCSC");
        this.tarazedCapabilityAgent.handleDirective("UpdateSession", updateSessionPayload.getSerializedPayload());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVideoDimensions(int i, int i2) {
        int max = Math.max(i, i2);
        double min = Math.min(Math.max(max, this.videoParameters.getMinVideoDimensions()), this.videoParameters.getMaxVideoDimension()) / max;
        this.videoWidth = (int) (i * min);
        this.videoHeight = (int) (i2 * min);
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Set video dimensions: ");
        outline107.append(this.videoWidth);
        outline107.append('x');
        outline107.append(this.videoHeight);
        outline107.append(" (screen dimensions ");
        outline107.append(i);
        outline107.append('x');
        outline107.append(i2);
        outline107.append(')');
        tarazedSessionLogger.i(TAG, outline107.toString());
    }

    @VisibleForTesting
    public static /* synthetic */ void videoHeight$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void videoWidth$annotations() {
    }

    public final void disconnect() {
        if (this.sessionInitialized) {
            this.mediaConnected = false;
            this.tarazedAppClient.unregisterScreenCapturerListener(this.sessionId);
            this.tarazedAppClient.unregisterRtcscAppClientListener(new RtcscAppInfo(this.rtcscSessionDomainForApp));
            DisconnectSessionPayload disconnectSessionPayload = new DisconnectSessionPayload(this.sessionId, this.rtcscSessionDomainForApp);
            this.logger.i(TAG, "Disconnecting WebRTC, sending directive to RTCSC");
            this.tarazedCapabilityAgent.handleDirective("DisconnectSession", disconnectSessionPayload.getSerializedPayload());
        }
    }

    public final boolean getExpectingAnswerForUpdate$TarazedMobileCore_release() {
        return this.expectingAnswerForUpdate;
    }

    @Nullable
    public final Job getOfferJob$TarazedMobileCore_release() {
        return this.offerJob;
    }

    @NotNull
    public final DeviceRotationHandler getRotationHandler$TarazedMobileCore_release() {
        return this.rotationHandler;
    }

    @NotNull
    public final String getRtcscSessionDomainForApp$TarazedMobileCore_release() {
        return this.rtcscSessionDomainForApp;
    }

    public final int getVideoHeight$TarazedMobileCore_release() {
        return this.videoHeight;
    }

    public final int getVideoWidth$TarazedMobileCore_release() {
        return this.videoWidth;
    }

    public final void handleAnswer(@NotNull WebRTCHandshake answer) {
        Intrinsics.checkParameterIsNotNull(answer, "answer");
        Job job = this.offerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (this.expectingAnswerForUpdate) {
            sendUpdatePeerConnection(answer);
            this.expectingAnswerForUpdate = false;
            return;
        }
        this.metricsHelper.stopMetricTimer(TAG, METRIC_BROWSER_INITIAL_ANSWER_LATENCY);
        SdpModifier sdpModifier = this.sdpModifier;
        int maxVideoBitrateKbps = this.videoParameters.getMaxVideoBitrateKbps();
        String value = answer.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "answer.value");
        StartPeerConnectionPayload startPeerConnectionPayload = new StartPeerConnectionPayload(this.sessionId, this.rtcscSessionDomainForApp, false, new WebRTCHandshake(answer.getFormat(), sdpModifier.modifySDP(maxVideoBitrateKbps, value)));
        this.logger.i(TAG, "Received answer from peer, sending directive to RTCSC");
        this.tarazedCapabilityAgent.handleDirective("StartPeerConnection", startPeerConnectionPayload.getSerializedPayload());
        this.tarazedAppClient.setScreenCapturerDimensions(this.sessionId, this.videoWidth, this.videoHeight);
    }

    public final void handleMediaConnected() {
        this.metricsHelper.stopMetricTimer(TAG, METRIC_WEBRTC_START_LATENCY);
        this.mediaConnected = true;
        BizMetricsHelper.publishBizMetric$default(this.bizMetricsHelper, BizMetricsConstants.SESSION_CONNECTED_EVENT_NAME, null, 2, null);
    }

    public final void handleMediaDisconnected() {
        this.mediaConnected = false;
    }

    public final void handleOffer(@NotNull OfferEventPayload offerPayload) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(offerPayload, "offerPayload");
        this.metricsHelper.stopMetricTimer(TAG, METRIC_RTCSC_OFFER_LATENCY);
        this.metricsHelper.startMetricTimer(TAG, METRIC_BROWSER_INITIAL_ANSWER_LATENCY);
        WebRTCHandshake offer = offerPayload.getOffer();
        Intrinsics.checkExpressionValueIsNotNull(offer, "offerPayload.offer");
        String value = offer.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "offerPayload.offer.value");
        String modifySDP = this.sdpModifier.modifySDP(this.videoParameters.getMaxVideoBitrateKbps(), value);
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.v(TAG, "Inserted bandwidth limit in SDP " + modifySDP);
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.mainCoroutineScope, null, null, new WebRTCManager$handleOffer$1(this, modifySDP, null), 3, null);
        this.offerJob = launch$default;
    }

    public final void initiateSession() {
        VideoStream videoStream = new VideoStream(MediaDirection.SEND_ONLY, RtcpMuxPolicy.NEGOTIATE, false, 0, VideoSourceType.SCREEN);
        InitiateSessionPayload initiateSessionPayload = new InitiateSessionPayload(this.sessionId, this.rtcscSessionDomainForApp, KeyExchange.DTLS, false, new MediaStreams(BundlePolicy.BALANCED, new AudioStream(MediaDirection.OFF, RtcpMuxPolicy.NEGOTIATE), videoStream), this.iceServers);
        this.logger.i(TAG, "Sending InitiateSession directive to RTCSC");
        this.metricsHelper.startMetricTimer(TAG, METRIC_RTCSC_OFFER_LATENCY);
        this.metricsHelper.addCount(TAG, METRIC_INITIATE_SESSION_REQUEST, 1.0d);
        this.tarazedCapabilityAgent.handleDirective("InitiateSession", initiateSessionPayload.getSerializedPayload());
        this.sessionInitialized = true;
    }

    public final void pause() {
        if (this.mediaConnected) {
            this.logger.i(TAG, "Pausing WebRTC stream");
            this.expectingAnswerForUpdate = true;
            sendUpdateSessionDirective(false);
            return;
        }
        this.logger.i(TAG, "ignoring pause request as media has not been connected yet");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object prepareSession(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.tarazed.core.webrtc.WebRTCManager$prepareSession$1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.tarazed.core.webrtc.WebRTCManager$prepareSession$1 r0 = (com.amazon.tarazed.core.webrtc.WebRTCManager$prepareSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.webrtc.WebRTCManager$prepareSession$1 r0 = new com.amazon.tarazed.core.webrtc.WebRTCManager$prepareSession$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.webrtc.WebRTCManager r0 = (com.amazon.tarazed.core.webrtc.WebRTCManager) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6c
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r7 = r6.metricsHelper
            java.lang.String r2 = "WebRTCManager"
            java.lang.String r4 = "WebRTCStartLatency"
            r7.startMetricTimer(r2, r4)
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r7 = r6.sessionNotifier
            r7.notifyMediaConnecting()
            com.amazon.tarazed.core.webrtc.android.WebRTCManagerHelper r7 = r6.webRTCManagerHelper
            com.amazon.tarazed.core.webrtc.rtcsc.TarazedScreenCapturerListener r7 = r7.provideScreenCapturerListener$TarazedMobileCore_release(r6)
            r6.tarazedScreenCapturerListener = r7
            com.amazon.tarazed.core.webrtc.rtcsc.TarazedAppClient r7 = r6.tarazedAppClient
            java.lang.String r2 = r6.sessionId
            com.amazon.tarazed.core.webrtc.rtcsc.TarazedScreenCapturerListener r4 = r6.tarazedScreenCapturerListener
            if (r4 != 0) goto L5b
            java.lang.String r5 = "tarazedScreenCapturerListener"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L5b:
            r7.registerScreenCapturerListener(r2, r4)
            com.amazon.tarazed.core.webrtc.android.WebRTCManagerHelper r7 = r6.webRTCManagerHelper
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = r7.provideMediaProjectionPermissions(r0)
            if (r7 != r1) goto L6b
            return r1
        L6b:
            r0 = r6
        L6c:
            android.content.Intent r7 = (android.content.Intent) r7
            com.amazon.tarazed.core.webrtc.rtcsc.TarazedAppClient r1 = r0.tarazedAppClient
            java.lang.String r0 = r0.sessionId
            r1.putScreenCapturerData(r0, r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.webrtc.WebRTCManager.prepareSession(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void registerRTCSCListeners() {
        this.tarazedAppClientListener = this.webRTCManagerHelper.provideTarazedAppClientListener$TarazedMobileCore_release(this.tarazedAppClient);
        this.tarazedCapabilityAgentListener = this.webRTCManagerHelper.provideTarazedCapabilityAgentListener$TarazedMobileCore_release(this, this.sessionNotifier);
        TarazedAppClient tarazedAppClient = this.tarazedAppClient;
        RtcscAppInfo rtcscAppInfo = new RtcscAppInfo(this.rtcscSessionDomainForApp);
        TarazedAppClientListener tarazedAppClientListener = this.tarazedAppClientListener;
        if (tarazedAppClientListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tarazedAppClientListener");
        }
        tarazedAppClient.registerRtcscAppClientListener(rtcscAppInfo, tarazedAppClientListener);
        TarazedCapabilityAgent tarazedCapabilityAgent = this.tarazedCapabilityAgent;
        RtcscAppInfo rtcscAppInfo2 = new RtcscAppInfo(this.rtcscSessionDomainForApp);
        TarazedCapabilityAgentListener tarazedCapabilityAgentListener = this.tarazedCapabilityAgentListener;
        if (tarazedCapabilityAgentListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tarazedCapabilityAgentListener");
        }
        tarazedCapabilityAgent.registerCAEventListener(rtcscAppInfo2, tarazedCapabilityAgentListener);
    }

    public final void resume() {
        if (this.mediaConnected) {
            this.logger.i(TAG, "Resuming WebRTC stream");
            this.expectingAnswerForUpdate = true;
            sendUpdateSessionDirective(true);
            return;
        }
        this.logger.i(TAG, "ignoring resume request as media has not been connected yet");
    }

    public final void setExpectingAnswerForUpdate$TarazedMobileCore_release(boolean z) {
        this.expectingAnswerForUpdate = z;
    }

    public final void setOfferJob$TarazedMobileCore_release(@Nullable Job job) {
        this.offerJob = job;
    }

    public final void setRotationHandler$TarazedMobileCore_release(@NotNull DeviceRotationHandler deviceRotationHandler) {
        Intrinsics.checkParameterIsNotNull(deviceRotationHandler, "<set-?>");
        this.rotationHandler = deviceRotationHandler;
    }

    public final void setVideoHeight$TarazedMobileCore_release(int i) {
        this.videoHeight = i;
    }

    public final void setVideoWidth$TarazedMobileCore_release(int i) {
        this.videoWidth = i;
    }
}
