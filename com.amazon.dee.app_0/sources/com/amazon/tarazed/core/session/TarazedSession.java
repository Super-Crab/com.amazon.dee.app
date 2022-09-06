package com.amazon.tarazed.core.session;

import androidx.databinding.ObservableField;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.rtcsc.android.typedapi.types.IceServer;
import com.amazon.tarazed.core.appInfo.TarazedAppInfoRequester;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.RotationListener;
import com.amazon.tarazed.core.session.dialog.SessionDialogManager;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import com.amazon.tarazed.core.sessionclient.TarazedSessionClient;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.LoggingCredentials;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.BrowserPresenceDetectorAtSessionStart;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.core.webrtc.VideoParameters;
import com.amazon.tarazed.core.webrtc.WebRTCManager;
import com.amazon.tarazed.core.webrtc.WebRTCMessageHandler;
import com.amazon.tarazed.core.webrtc.android.WebRTCManagerHelper;
import com.amazonaws.services.s3.internal.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u0093\u00012\u00020\u0001:\u0002\u0093\u0001B£\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u0010'J\b\u0010j\u001a\u00020VH\u0002J\u0011\u0010k\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\b\u0010m\u001a\u00020VH\u0002J\u0013\u0010n\u001a\u00020VH\u0080@ø\u0001\u0000¢\u0006\u0004\bo\u0010lJ\u0019\u0010p\u001a\u00020V2\u0006\u0010q\u001a\u00020:H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010rJ\u0010\u0010s\u001a\u00020V2\u0006\u0010q\u001a\u00020:H\u0002J\u0019\u0010t\u001a\u00020V2\u0006\u0010u\u001a\u00020vH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010wJ\u0010\u0010x\u001a\u00020 2\u0006\u0010u\u001a\u00020vH\u0002J\b\u0010y\u001a\u00020 H\u0002J\u0019\u0010z\u001a\u00020V2\u0006\u0010q\u001a\u00020:H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010rJ\u0011\u0010{\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0011\u0010|\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0011\u0010}\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0010\u0010~\u001a\u00020V2\u0006\u0010q\u001a\u00020:H\u0002J\u0011\u0010\u007f\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\t\u0010\u0080\u0001\u001a\u00020VH\u0002J\u0011\u0010\u0081\u0001\u001a\u00020V2\u0006\u0010u\u001a\u00020vH\u0002J\u000f\u0010\u0082\u0001\u001a\u00020V2\u0006\u0010=\u001a\u00020vJ&\u0010\u0083\u0001\u001a\u00020V2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020 H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0087\u0001J\u0011\u0010\u0088\u0001\u001a\u00020V2\u0006\u0010q\u001a\u00020:H\u0002J\t\u0010\u0089\u0001\u001a\u00020VH\u0002J\u0017\u0010\u008a\u0001\u001a\u00020V2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dJ\u0012\u0010\u008b\u0001\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0012\u0010\u008c\u0001\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0012\u0010\u008d\u0001\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010lJ\t\u0010\u008e\u0001\u001a\u00020VH\u0002J\t\u0010\u008f\u0001\u001a\u00020VH\u0002J\t\u0010\u0090\u0001\u001a\u00020VH\u0002J\u0011\u0010\u0091\u0001\u001a\u00020V2\u0006\u0010q\u001a\u00020:H\u0002J\t\u0010\u0092\u0001\u001a\u00020VH\u0002R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010-\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020605X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010>\u001a\u00020\u00192\u0006\u0010=\u001a\u00020\u00198F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010@\"\u0004\bE\u0010BR\u001a\u0010F\u001a\u00020/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u00101\"\u0004\bH\u00103R\u0014\u0010I\u001a\u00020/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u00101R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010L\"\u0004\bP\u0010NR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010@R2\u0010R\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020T\u0012\n\u0012\b\u0012\u0004\u0012\u00020V0U\u0012\u0006\u0012\u0004\u0018\u00010\u00010Sø\u0001\u0000¢\u0006\n\n\u0002\u0010Y\u001a\u0004\bW\u0010XR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010[\u001a\u00020\\X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u001a\u0010_\u001a\u00020/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u00101\"\u0004\ba\u00103R\u000e\u0010b\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010c\u001a\u00020/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u00101\"\u0004\be\u00103R\u0010\u0010f\u001a\u0004\u0018\u00010gX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020iX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0094\u0001"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedSession;", "", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "sessionClient", "Lcom/amazon/tarazed/core/sessionclient/TarazedSessionClient;", "webRTCManagerHelper", "Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;", "playbackStateCache", "Lcom/amazon/tarazed/core/session/PlaybackStateCache;", "eventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "rotationListener", "Lcom/amazon/tarazed/core/rotation/RotationListener;", Constants.REQUESTER_PAYS, "Lcom/amazon/tarazed/core/appInfo/TarazedAppInfoRequester;", "sessionDialogManager", "Lcom/amazon/tarazed/core/session/dialog/SessionDialogManager;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "videoParameters", "Lcom/amazon/tarazed/core/webrtc/VideoParameters;", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "", "screenHeight", "isBeta", "", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/sessionclient/TarazedSessionClient;Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;Lcom/amazon/tarazed/core/session/PlaybackStateCache;Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;Lcom/amazon/tarazed/core/rotation/RotationListener;Lcom/amazon/tarazed/core/appInfo/TarazedAppInfoRequester;Lcom/amazon/tarazed/core/session/dialog/SessionDialogManager;Ljava/lang/String;Lcom/amazon/tarazed/core/webrtc/VideoParameters;IIZLandroidx/databinding/ObservableField;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "browserPresenceDetector", "Lcom/amazon/tarazed/core/utility/BrowserPresenceDetectorAtSessionStart;", "durationMS", "", "Ljava/lang/Long;", "endSessionDialogEnabled", "endSessionTimer", "Lkotlinx/coroutines/Job;", "getEndSessionTimer$TarazedMobileCore_release", "()Lkotlinx/coroutines/Job;", "setEndSessionTimer$TarazedMobileCore_release", "(Lkotlinx/coroutines/Job;)V", "iceServers", "", "Lcom/amazon/rtcsc/android/typedapi/types/IceServer;", "iotMessenger", "Lcom/amazon/tarazed/core/session/TarazedSessionIoTMessenger;", "lastPauseEventSource", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "loggingCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "state", "playbackState", "getPlaybackState", "()Ljava/lang/String;", "setPlaybackState", "(Ljava/lang/String;)V", "previousPlaybackState", "getPreviousPlaybackState", "setPreviousPlaybackState", "primerNotShownBizMetricTimer", "getPrimerNotShownBizMetricTimer$TarazedMobileCore_release", "setPrimerNotShownBizMetricTimer$TarazedMobileCore_release", "refreshCredentialsJob", "getRefreshCredentialsJob$TarazedMobileCore_release", "getScreenHeight", "()I", "setScreenHeight", "(I)V", "getScreenWidth", "setScreenWidth", "getSessionId", "sessionNotificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "getSessionNotificationHandler", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "sessionPausedTimeStamp", "stateChangeChannel", "Lcom/amazon/tarazed/core/session/TarazedStateChangeChannel;", "getStateChangeChannel$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/session/TarazedStateChangeChannel;", "stateChangeRequestReceiverJob", "getStateChangeRequestReceiverJob$TarazedMobileCore_release", "setStateChangeRequestReceiverJob$TarazedMobileCore_release", "suspendSessionTimer", "webRTCConnectionTimer", "getWebRTCConnectionTimer$TarazedMobileCore_release", "setWebRTCConnectionTimer$TarazedMobileCore_release", "webRTCManager", "Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "webRTCManagerProvider", "Lcom/amazon/tarazed/core/session/WebRTCManagerProvider;", "confirmEndSession", "connectWebRTC", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", Metrics.DISCONNECT, "endImmediately", "endImmediately$TarazedMobileCore_release", "endSession", "source", "(Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endSessionOverIoT", "handleStateChangeEvent", "event", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeRequest;", "(Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isValidStateChange", "isWebRTCInitialized", "pauseSession", "requestResume", "requestStartSession", "requestStartSessionDialog", "resumeSession", "sendEndSessionRequest", "sendPrimerNotShownBizMetric", "sendStateChangeBizMetricEvent", "sendStateChangeEventRequest", "setCredentials", "credentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "isRefresh", "(Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDialogEndSessionSource", "setPermissionDialogCallbacks", "setScreenDimensions", "startSession", "startSessionOverIot", "startSessionOverWebRTC", "stopTimerMetrics", "subscribeToNotifier", "suspendSession", "suspendSessionOverIoT", "webRTCConnected", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSession {
    private static final long BROWSER_PING_TIMEOUT_MS = 5000;
    private static final String METRIC_DURATION_FROM_ACCEPT = "SessionDurationFromAccept";
    private static final String METRIC_DURATION_FROM_START = "SessionDurationFromStart";
    private static final String METRIC_END_SESSION_TIMEOUT = "EndSessionTimeout";
    private static final String METRIC_INVALID_STATE_CHANGE_ATTEMPT = "InvalidStateChangeAttempt";
    private static final String METRIC_IOT_CONNECTION_TIMEOUT = "IoTConnectionTimeout";
    private static final String METRIC_IOT_UNKNOWN_FAILURE = "IoTUnknownFailure";
    @NotNull
    public static final String METRIC_LAUNCH_PERMISSION_DELAY = "LaunchPermissionDelay";
    private static final String METRIC_STOP_CAPTURE_INTERRUPTED = "StopCaptureInterrupted";
    private static final String METRIC_VIDEO_CAPTURE_FAILED = "VideoCaptureFailed";
    private static final String METRIC_WEBRTC_ATTEMPT_CONNECTION_TIMEOUT = "WebRTCAttemptConnectionTimeout";
    private static final long PRIMER_NOT_SHOWN_TIMEOUT = 15000;
    private static final int SEND_MESSAGE_ATTEMPTS = 5;
    private static final String STATE_REASON_APP_IN_BACKGROUND = "appInBackground";
    private static final String TAG = "TarazedSession";
    private final BizMetricsHelper bizMetricsHelper;
    private final BrowserPresenceDetectorAtSessionStart browserPresenceDetector;
    private final DeviceInfoUtility deviceInfo;
    private Long durationMS;
    private boolean endSessionDialogEnabled;
    @NotNull
    private Job endSessionTimer;
    private final TarazedEventDispatcher eventDispatcher;
    private List<? extends IceServer> iceServers;
    private final TarazedIoTManager iotManager;
    private final TarazedSessionIoTMessenger iotMessenger;
    private final boolean isBeta;
    private TarazedSessionStateChangeSource lastPauseEventSource;
    private final TarazedSessionLogger logger;
    private LoggingCredentials loggingCredentials;
    private final TarazedMetricsHelper metricsHelper;
    private final PlaybackStateCache playbackStateCache;
    private final ObservableField<String> playbackStateObservable;
    @NotNull
    private String previousPlaybackState;
    @NotNull
    private Job primerNotShownBizMetricTimer;
    @NotNull
    private final Job refreshCredentialsJob;
    private final TarazedAppInfoRequester requester;
    private final RotationListener rotationListener;
    private int screenHeight;
    private int screenWidth;
    private final TarazedSessionClient sessionClient;
    private final SessionDialogManager sessionDialogManager;
    @NotNull
    private final String sessionId;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler;
    private final TarazedSessionNotifier sessionNotifier;
    private long sessionPausedTimeStamp;
    @NotNull
    private final TarazedStateChangeChannel stateChangeChannel;
    @NotNull
    private Job stateChangeRequestReceiverJob;
    private Job suspendSessionTimer;
    private final VideoParameters videoParameters;
    @NotNull
    private Job webRTCConnectionTimer;
    private WebRTCManager webRTCManager;
    private final WebRTCManagerHelper webRTCManagerHelper;
    private final WebRTCManagerProvider webRTCManagerProvider;
    public static final Companion Companion = new Companion(null);
    private static long WEBRTC_START_CONNECTION_TIMEOUT_MS = 70000;
    private static long IOT_INITIAL_CONNECTION_TIMEOUT_MS = 10000;
    private static long MESSAGE_RESPONSE_TIMEOUT_MS = 10000;
    private static long MESSAGE_ATTEMPT_RESPONSE_PERIOD_MS = MESSAGE_RESPONSE_TIMEOUT_MS / 5;
    private static long REFRESH_TIME_BEFORE_EXPIRATION_MS = 60000;

    /* compiled from: TarazedSession.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession$1", f = "TarazedSession.kt", i = {0}, l = {187}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.amazon.tarazed.core.session.TarazedSession$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = this.p$;
                this.label = 1;
                if (DelayKt.delay(TarazedSession.PRIMER_NOT_SHOWN_TIMEOUT, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            TarazedSession.this.sendPrimerNotShownBizMetric();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: TarazedSession.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u000e\u0010\u001f\u001a\u00020 X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0007\"\u0004\b%\u0010\t¨\u0006&"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedSession$Companion;", "", "()V", "BROWSER_PING_TIMEOUT_MS", "", "IOT_INITIAL_CONNECTION_TIMEOUT_MS", "getIOT_INITIAL_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release", "()J", "setIOT_INITIAL_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release", "(J)V", "MESSAGE_ATTEMPT_RESPONSE_PERIOD_MS", "getMESSAGE_ATTEMPT_RESPONSE_PERIOD_MS$TarazedMobileCore_release", "setMESSAGE_ATTEMPT_RESPONSE_PERIOD_MS$TarazedMobileCore_release", "MESSAGE_RESPONSE_TIMEOUT_MS", "getMESSAGE_RESPONSE_TIMEOUT_MS$TarazedMobileCore_release", "setMESSAGE_RESPONSE_TIMEOUT_MS$TarazedMobileCore_release", "METRIC_DURATION_FROM_ACCEPT", "", "METRIC_DURATION_FROM_START", "METRIC_END_SESSION_TIMEOUT", "METRIC_INVALID_STATE_CHANGE_ATTEMPT", "METRIC_IOT_CONNECTION_TIMEOUT", "METRIC_IOT_UNKNOWN_FAILURE", "METRIC_LAUNCH_PERMISSION_DELAY", "METRIC_STOP_CAPTURE_INTERRUPTED", "METRIC_VIDEO_CAPTURE_FAILED", "METRIC_WEBRTC_ATTEMPT_CONNECTION_TIMEOUT", "PRIMER_NOT_SHOWN_TIMEOUT", "REFRESH_TIME_BEFORE_EXPIRATION_MS", "getREFRESH_TIME_BEFORE_EXPIRATION_MS$TarazedMobileCore_release", "setREFRESH_TIME_BEFORE_EXPIRATION_MS$TarazedMobileCore_release", "SEND_MESSAGE_ATTEMPTS", "", "STATE_REASON_APP_IN_BACKGROUND", "TAG", "WEBRTC_START_CONNECTION_TIMEOUT_MS", "getWEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release", "setWEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public final long getIOT_INITIAL_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release() {
            return TarazedSession.IOT_INITIAL_CONNECTION_TIMEOUT_MS;
        }

        public final long getMESSAGE_ATTEMPT_RESPONSE_PERIOD_MS$TarazedMobileCore_release() {
            return TarazedSession.MESSAGE_ATTEMPT_RESPONSE_PERIOD_MS;
        }

        public final long getMESSAGE_RESPONSE_TIMEOUT_MS$TarazedMobileCore_release() {
            return TarazedSession.MESSAGE_RESPONSE_TIMEOUT_MS;
        }

        public final long getREFRESH_TIME_BEFORE_EXPIRATION_MS$TarazedMobileCore_release() {
            return TarazedSession.REFRESH_TIME_BEFORE_EXPIRATION_MS;
        }

        public final long getWEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release() {
            return TarazedSession.WEBRTC_START_CONNECTION_TIMEOUT_MS;
        }

        public final void setIOT_INITIAL_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release(long j) {
            TarazedSession.IOT_INITIAL_CONNECTION_TIMEOUT_MS = j;
        }

        public final void setMESSAGE_ATTEMPT_RESPONSE_PERIOD_MS$TarazedMobileCore_release(long j) {
            TarazedSession.MESSAGE_ATTEMPT_RESPONSE_PERIOD_MS = j;
        }

        public final void setMESSAGE_RESPONSE_TIMEOUT_MS$TarazedMobileCore_release(long j) {
            TarazedSession.MESSAGE_RESPONSE_TIMEOUT_MS = j;
        }

        public final void setREFRESH_TIME_BEFORE_EXPIRATION_MS$TarazedMobileCore_release(long j) {
            TarazedSession.REFRESH_TIME_BEFORE_EXPIRATION_MS = j;
        }

        public final void setWEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release(long j) {
            TarazedSession.WEBRTC_START_CONNECTION_TIMEOUT_MS = j;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_DISCONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_STOPPED.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_FAILED.ordinal()] = 4;
            $EnumSwitchMapping$0[TarazedNotificationEvent.IOT_DISCONNECTED.ordinal()] = 5;
            $EnumSwitchMapping$0[TarazedNotificationEvent.PROFILE_SWITCH.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[TarazedSessionStateChangeType.values().length];
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.CONNECT_IOT.ordinal()] = 1;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.REQUEST_START_SESSION.ordinal()] = 2;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.START_SESSION.ordinal()] = 3;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.RESUME_SESSION.ordinal()] = 4;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.REQUEST_RESUME_SESSION.ordinal()] = 5;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.PAUSE_SESSION.ordinal()] = 6;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.BEGIN_SUSPENDING_SESSION.ordinal()] = 7;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.SUSPEND_SESSION.ordinal()] = 8;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.CONFIRM_END_SESSION.ordinal()] = 9;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.BEGIN_ENDING_SESSION.ordinal()] = 10;
            $EnumSwitchMapping$1[TarazedSessionStateChangeType.END_SESSION.ordinal()] = 11;
            $EnumSwitchMapping$2 = new int[TarazedSessionStateChangeType.values().length];
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.CONNECT_IOT.ordinal()] = 1;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.REQUEST_START_SESSION.ordinal()] = 2;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.START_SESSION.ordinal()] = 3;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.RESUME_SESSION.ordinal()] = 4;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.REQUEST_RESUME_SESSION.ordinal()] = 5;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.PAUSE_SESSION.ordinal()] = 6;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.BEGIN_SUSPENDING_SESSION.ordinal()] = 7;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.SUSPEND_SESSION.ordinal()] = 8;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.CONFIRM_END_SESSION.ordinal()] = 9;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.BEGIN_ENDING_SESSION.ordinal()] = 10;
            $EnumSwitchMapping$2[TarazedSessionStateChangeType.END_SESSION.ordinal()] = 11;
            $EnumSwitchMapping$3 = new int[TarazedSessionStateChangeType.values().length];
            $EnumSwitchMapping$3[TarazedSessionStateChangeType.PAUSE_SESSION.ordinal()] = 1;
            $EnumSwitchMapping$3[TarazedSessionStateChangeType.RESUME_SESSION.ordinal()] = 2;
            $EnumSwitchMapping$3[TarazedSessionStateChangeType.BEGIN_ENDING_SESSION.ordinal()] = 3;
        }
    }

    public TarazedSession(@NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSessionLogger logger, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionClient sessionClient, @NotNull WebRTCManagerHelper webRTCManagerHelper, @NotNull PlaybackStateCache playbackStateCache, @NotNull TarazedEventDispatcher eventDispatcher, @NotNull TarazedIoTManager iotManager, @NotNull RotationListener rotationListener, @NotNull TarazedAppInfoRequester requester, @NotNull SessionDialogManager sessionDialogManager, @NotNull String sessionId, @NotNull VideoParameters videoParameters, int i, int i2, boolean z, @NotNull ObservableField<String> playbackStateObservable, @NotNull DeviceInfoUtility deviceInfo, @NotNull BizMetricsHelper bizMetricsHelper) {
        Job launch$default;
        Job launch$default2;
        Job launch$default3;
        Job launch$default4;
        Job launch$default5;
        Job launch$default6;
        Job launch$default7;
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(sessionClient, "sessionClient");
        Intrinsics.checkParameterIsNotNull(webRTCManagerHelper, "webRTCManagerHelper");
        Intrinsics.checkParameterIsNotNull(playbackStateCache, "playbackStateCache");
        Intrinsics.checkParameterIsNotNull(eventDispatcher, "eventDispatcher");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(rotationListener, "rotationListener");
        Intrinsics.checkParameterIsNotNull(requester, "requester");
        Intrinsics.checkParameterIsNotNull(sessionDialogManager, "sessionDialogManager");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(videoParameters, "videoParameters");
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.metricsHelper = metricsHelper;
        this.logger = logger;
        this.sessionNotifier = sessionNotifier;
        this.sessionClient = sessionClient;
        this.webRTCManagerHelper = webRTCManagerHelper;
        this.playbackStateCache = playbackStateCache;
        this.eventDispatcher = eventDispatcher;
        this.iotManager = iotManager;
        this.rotationListener = rotationListener;
        this.requester = requester;
        this.sessionDialogManager = sessionDialogManager;
        this.sessionId = sessionId;
        this.videoParameters = videoParameters;
        this.screenWidth = i;
        this.screenHeight = i2;
        this.isBeta = z;
        this.playbackStateObservable = playbackStateObservable;
        this.deviceInfo = deviceInfo;
        this.bizMetricsHelper = bizMetricsHelper;
        this.iotMessenger = new TarazedSessionIoTMessenger(this.iotManager);
        this.browserPresenceDetector = new BrowserPresenceDetectorAtSessionStart(this.logger, this.eventDispatcher, this.iotManager);
        this.webRTCManagerProvider = new WebRTCManagerProvider();
        this.lastPauseEventSource = TarazedSessionStateChangeSource.SOURCE_OTHER;
        this.endSessionDialogEnabled = true;
        this.stateChangeChannel = new TarazedStateChangeChannel();
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedSession$endSessionTimer$1(null), 1, null);
        this.endSessionTimer = launch$default;
        launch$default2 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedSession$suspendSessionTimer$1(null), 1, null);
        this.suspendSessionTimer = launch$default2;
        launch$default3 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedSession$webRTCConnectionTimer$1(null), 1, null);
        this.webRTCConnectionTimer = launch$default3;
        launch$default4 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedSession$primerNotShownBizMetricTimer$1(null), 1, null);
        this.primerNotShownBizMetricTimer = launch$default4;
        this.previousPlaybackState = "inactive";
        this.sessionNotificationHandler = new TarazedSession$sessionNotificationHandler$1(this, null);
        subscribeToNotifier();
        if (!this.deviceInfo.is1PDevice()) {
            launch$default7 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
            this.primerNotShownBizMetricTimer = launch$default7;
        }
        launch$default5 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedSession$stateChangeRequestReceiverJob$1(this, null), 3, null);
        this.stateChangeRequestReceiverJob = launch$default5;
        launch$default6 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedSession$refreshCredentialsJob$1(this, null), 1, null);
        this.refreshCredentialsJob = launch$default6;
    }

    private final void confirmEndSession() {
        this.logger.i(TAG, "Confirming end session");
        this.sessionDialogManager.mo4638getConfirmEndSessionDialog().start();
        this.sessionDialogManager.mo4638getConfirmEndSessionDialog().show();
    }

    private final void disconnect() {
        this.iotMessenger.disconnect();
        Job.DefaultImpls.cancel$default(this.webRTCConnectionTimer, (CancellationException) null, 1, (Object) null);
        WebRTCManager webRTCManager = this.webRTCManager;
        if (webRTCManager != null) {
            webRTCManager.disconnect();
        }
    }

    private final void endSessionOverIoT(TarazedSessionStateChangeSource tarazedSessionStateChangeSource) {
        Job launch$default;
        setDialogEndSessionSource(tarazedSessionStateChangeSource);
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedSession$endSessionOverIoT$1(this, tarazedSessionStateChangeSource, null), 3, null);
        this.endSessionTimer = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isValidStateChange(TarazedSessionStateChangeRequest tarazedSessionStateChangeRequest) {
        Set of;
        Set of2;
        Set of3;
        Set of4;
        Set of5;
        Set of6;
        Set of7;
        Set of8;
        Set of9;
        Set of10;
        Set of11;
        Set of12;
        switch (WhenMappings.$EnumSwitchMapping$1[tarazedSessionStateChangeRequest.getType().ordinal()]) {
            case 1:
                if (tarazedSessionStateChangeRequest.getSource() == TarazedSessionStateChangeSource.SOURCE_3P_APP_EVENT) {
                    of = SetsKt__SetsJVMKt.setOf("inactive");
                    return of.contains(getPlaybackState());
                }
                break;
            case 2:
                of2 = SetsKt__SetsKt.setOf((Object[]) new String[]{"inactive", PlaybackStates.STARTING_BACKGROUND, PlaybackStates.SUSPENDED});
                return of2.contains(getPlaybackState());
            case 3:
                of3 = SetsKt__SetsJVMKt.setOf(PlaybackStates.STARTING);
                return of3.contains(getPlaybackState());
            case 4:
                if (isWebRTCInitialized()) {
                    of4 = SetsKt__SetsKt.setOf((Object[]) new String[]{"paused", PlaybackStates.REQUESTING_RESUME, PlaybackStates.CONFIRMING_END});
                    if (of4.contains(getPlaybackState())) {
                        return true;
                    }
                }
                break;
            case 5:
                if (isWebRTCInitialized()) {
                    of5 = SetsKt__SetsKt.setOf((Object[]) new String[]{"paused", PlaybackStates.CONFIRMING_END});
                    if (of5.contains(getPlaybackState())) {
                        return true;
                    }
                }
                break;
            case 6:
                if (tarazedSessionStateChangeRequest.getSource() == TarazedSessionStateChangeSource.SOURCE_3P_APP_EVENT) {
                    if (isWebRTCInitialized()) {
                        of7 = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.PLAYING, PlaybackStates.CONFIRMING_END, "paused", PlaybackStates.STARTING});
                        if (of7.contains(getPlaybackState())) {
                            return true;
                        }
                    }
                } else if (isWebRTCInitialized()) {
                    of6 = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.PLAYING, PlaybackStates.CONFIRMING_END});
                    if (of6.contains(getPlaybackState())) {
                        return true;
                    }
                }
                break;
            case 7:
                of8 = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.PLAYING, "paused"});
                return of8.contains(getPlaybackState());
            case 8:
                of9 = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.PLAYING, "paused", PlaybackStates.REQUESTING_RESUME, PlaybackStates.SUSPENDING, PlaybackStates.CONFIRMING_END});
                return of9.contains(getPlaybackState());
            case 9:
                of10 = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.PLAYING, "paused", PlaybackStates.REQUESTING_RESUME, PlaybackStates.SUSPENDING, PlaybackStates.SUSPENDED});
                return of10.contains(getPlaybackState());
            case 10:
                of11 = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.PLAYING, "paused", PlaybackStates.REQUESTING_RESUME, PlaybackStates.SUSPENDING, PlaybackStates.SUSPENDED, PlaybackStates.REQUESTING_RESUME, PlaybackStates.CONFIRMING_END, PlaybackStates.STARTING, PlaybackStates.STARTING_BACKGROUND});
                return of11.contains(getPlaybackState());
            case 11:
                of12 = SetsKt__SetsKt.setOf((Object[]) new String[]{"inactive", PlaybackStates.ENDED});
                if (!of12.contains(getPlaybackState())) {
                    return true;
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return false;
    }

    private final boolean isWebRTCInitialized() {
        return this.webRTCManager != null;
    }

    private final void resumeSession(TarazedSessionStateChangeSource tarazedSessionStateChangeSource) {
        this.logger.i(TAG, "Resuming Session");
        WebRTCManager webRTCManager = this.webRTCManager;
        if (webRTCManager != null) {
            webRTCManager.resume();
        }
        if (this.sessionPausedTimeStamp > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put(BizMetricsConstants.DURATION_METADATA_NAME, String.valueOf((System.currentTimeMillis() - this.sessionPausedTimeStamp) / 1000));
            this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.SESSION_PAUSED_DURATION_EVENT_NAME, hashMap);
            this.sessionPausedTimeStamp = 0L;
        }
        if (tarazedSessionStateChangeSource == TarazedSessionStateChangeSource.SOURCE_CUSTOMER) {
            this.sessionNotifier.notifySessionResumedByCustomer();
        } else if (tarazedSessionStateChangeSource == TarazedSessionStateChangeSource.SOURCE_AGENT) {
            this.sessionNotifier.notifySessionResumedByAgent();
        }
        this.sessionNotifier.notifySessionActive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendPrimerNotShownBizMetric() {
        this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.DISPLAY_PRIMER_EVENT_NAME, GeneratedOutlineSupport1.outline133(BizMetricsConstants.DISPLAYED_MSS_PRIMER_METADATA_NAME, BizMetricsConstants.DISPLAYED_MSS_PRIMER_FALSE_VALUE));
    }

    private final void sendStateChangeBizMetricEvent(TarazedSessionStateChangeRequest tarazedSessionStateChangeRequest) {
        if (tarazedSessionStateChangeRequest.getSource() == TarazedSessionStateChangeSource.SOURCE_CUSTOMER || tarazedSessionStateChangeRequest.getSource() == TarazedSessionStateChangeSource.SOURCE_AGENT) {
            HashMap hashMap = new HashMap();
            int i = WhenMappings.$EnumSwitchMapping$3[tarazedSessionStateChangeRequest.getType().ordinal()];
            if (i == 1) {
                hashMap.put("source", tarazedSessionStateChangeRequest.getSource().name());
                this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.SESSION_PAUSED_EVENT_NAME, hashMap);
            } else if (i != 2) {
                if (i != 3 || tarazedSessionStateChangeRequest.getSource() != TarazedSessionStateChangeSource.SOURCE_CUSTOMER) {
                    return;
                }
                hashMap.put(BizMetricsConstants.SESSION_END_REASON_METADATA_NAME, BizMetricsConstants.SESSION_END_SOURCE_CUSTOMER);
                this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.SESSION_ENDED_EVENT_NAME, hashMap);
            } else {
                hashMap.put("source", tarazedSessionStateChangeRequest.getSource().name());
                this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.SESSION_RESUMED_EVENT_NAME, hashMap);
            }
        }
    }

    private final void setDialogEndSessionSource(TarazedSessionStateChangeSource tarazedSessionStateChangeSource) {
        if (this.sessionDialogManager.getEndSessionSource() == null) {
            this.sessionDialogManager.setEndSessionSource(tarazedSessionStateChangeSource);
        }
    }

    private final void setPermissionDialogCallbacks() {
        this.sessionDialogManager.getStartSessionPermissionDialog().setOnAcceptSessionCallback(new TarazedSession$setPermissionDialogCallbacks$1(this));
        this.sessionDialogManager.getStartSessionPermissionDialog().setOnDeclineSessionCallback(new TarazedSession$setPermissionDialogCallbacks$2(this));
        this.sessionDialogManager.getStartSessionPermissionDialog().setOnTimeoutCallback(new TarazedSession$setPermissionDialogCallbacks$3(this));
        this.sessionDialogManager.mo4643getResumeSessionPermissionDialog().setOnAcceptSessionCallback(new TarazedSession$setPermissionDialogCallbacks$4(this));
        this.sessionDialogManager.mo4643getResumeSessionPermissionDialog().setOnDeclineSessionCallback(new TarazedSession$setPermissionDialogCallbacks$5(this));
        this.sessionDialogManager.mo4638getConfirmEndSessionDialog().setOnAcceptSessionCallback(new TarazedSession$setPermissionDialogCallbacks$6(this));
        this.sessionDialogManager.mo4638getConfirmEndSessionDialog().setOnDeclineSessionCallback(new TarazedSession$setPermissionDialogCallbacks$7(this));
    }

    private final void stopTimerMetrics() {
        this.metricsHelper.stopMetricTimer(TAG, METRIC_DURATION_FROM_START);
        this.metricsHelper.stopMetricTimer(TAG, METRIC_DURATION_FROM_ACCEPT);
    }

    private final void subscribeToNotifier() {
        TarazedSessionNotifier.subscribe$default(this.sessionNotifier, this.sessionNotificationHandler, ListenerPriority.HIGH, false, 4, null);
    }

    private final void suspendSession() {
        this.logger.i(TAG, "Suspending Session");
        stopTimerMetrics();
        Job.DefaultImpls.cancel$default(this.refreshCredentialsJob, (CancellationException) null, 1, (Object) null);
        disconnect();
        this.sessionNotifier.notifySessionSuspended();
    }

    private final void suspendSessionOverIoT(TarazedSessionStateChangeSource tarazedSessionStateChangeSource) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedSession$suspendSessionOverIoT$1(this, tarazedSessionStateChangeSource, null), 3, null);
        this.suspendSessionTimer = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void webRTCConnected() {
        Job.DefaultImpls.cancel$default(this.webRTCConnectionTimer, (CancellationException) null, 1, (Object) null);
        if (Intrinsics.areEqual(getPlaybackState(), PlaybackStates.STARTING)) {
            this.logger.i(TAG, "WebRTC is connected, changing state to PLAYING");
            setPlaybackState(PlaybackStates.PLAYING);
            TarazedSessionIoTMessenger.sendPlaybackStateChange$default(this.iotMessenger, getPlaybackState(), null, 2, null);
            return;
        }
        this.logger.i(TAG, "WebRTC is connected but session is in paused state, continue to pause WebRTC");
        WebRTCManager webRTCManager = this.webRTCManager;
        if (webRTCManager == null) {
            return;
        }
        webRTCManager.pause();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object connectWebRTC(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            boolean r1 = r0.Z$0
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6b
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L37:
            kotlin.ResultKt.throwOnFailure(r8)
            com.amazon.tarazed.core.logging.TarazedSessionLogger r8 = r7.logger
            java.lang.String r2 = "TarazedSession"
            java.lang.String r4 = "Connecting to WebRTC"
            r8.i(r2, r4)
            java.lang.String r8 = "ended"
            java.lang.String r2 = "ending"
            java.lang.String[] r8 = new java.lang.String[]{r8, r2}
            java.util.Set r8 = kotlin.collections.SetsKt.setOf(r8)
            java.lang.String r2 = r7.getPlaybackState()
            boolean r8 = r8.contains(r2)
            if (r8 != 0) goto L91
            com.amazon.tarazed.core.webrtc.WebRTCManager r2 = r7.webRTCManager
            if (r2 == 0) goto L6a
            r0.L$0 = r7
            r0.Z$0 = r8
            r0.label = r3
            java.lang.Object r8 = r2.prepareSession(r0)
            if (r8 != r1) goto L6a
            return r1
        L6a:
            r0 = r7
        L6b:
            com.amazon.tarazed.core.session.TarazedSessionIoTMessenger r8 = r0.iotMessenger
            java.lang.String r1 = "permissionAccepted"
            r8.sendPermissionStateChange(r1)
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r8 = r0.sessionNotifier
            r8.notifyMediaPermissionAccepted()
            kotlinx.coroutines.Job r8 = r0.webRTCConnectionTimer
            boolean r8 = r8.isActive()
            if (r8 != 0) goto L91
            kotlinx.coroutines.GlobalScope r1 = kotlinx.coroutines.GlobalScope.INSTANCE
            r2 = 0
            r3 = 0
            com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$2 r4 = new com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$2
            r8 = 0
            r4.<init>(r0, r8)
            r5 = 3
            r6 = 0
            kotlinx.coroutines.Job r8 = kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
            r0.webRTCConnectionTimer = r8
        L91:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.connectWebRTC(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0064 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object endImmediately$TarazedMobileCore_release(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.tarazed.core.session.TarazedSession$endImmediately$1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.tarazed.core.session.TarazedSession$endImmediately$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$endImmediately$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$endImmediately$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$endImmediately$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L65
        L30:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L38:
            java.lang.Object r2 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r2 = (com.amazon.tarazed.core.session.TarazedSession) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L51
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r7 = r6.stateChangeChannel
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r7.cancel(r0)
            if (r7 != r1) goto L50
            return r1
        L50:
            r2 = r6
        L51:
            com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r7 = new com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest
            com.amazon.tarazed.core.session.TarazedSessionStateChangeType r4 = com.amazon.tarazed.core.session.TarazedSessionStateChangeType.END_SESSION
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r5 = com.amazon.tarazed.core.session.TarazedSessionStateChangeSource.SOURCE_OTHER
            r7.<init>(r4, r5)
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r2.handleStateChangeEvent(r7, r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.endImmediately$TarazedMobileCore_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b4, code lost:
        if (r2.contains(r12) != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00db  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object endSession(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.endSession(com.amazon.tarazed.core.session.TarazedSessionStateChangeSource, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Job getEndSessionTimer$TarazedMobileCore_release() {
        return this.endSessionTimer;
    }

    @NotNull
    public final String getPlaybackState() {
        return this.playbackStateCache.getPlaybackState(this.sessionId);
    }

    @NotNull
    public final String getPreviousPlaybackState() {
        return this.previousPlaybackState;
    }

    @NotNull
    public final Job getPrimerNotShownBizMetricTimer$TarazedMobileCore_release() {
        return this.primerNotShownBizMetricTimer;
    }

    @NotNull
    public final Job getRefreshCredentialsJob$TarazedMobileCore_release() {
        return this.refreshCredentialsJob;
    }

    public final int getScreenHeight() {
        return this.screenHeight;
    }

    public final int getScreenWidth() {
        return this.screenWidth;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getSessionNotificationHandler() {
        return this.sessionNotificationHandler;
    }

    @NotNull
    public final TarazedStateChangeChannel getStateChangeChannel$TarazedMobileCore_release() {
        return this.stateChangeChannel;
    }

    @NotNull
    public final Job getStateChangeRequestReceiverJob$TarazedMobileCore_release() {
        return this.stateChangeRequestReceiverJob;
    }

    @NotNull
    public final Job getWebRTCConnectionTimer$TarazedMobileCore_release() {
        return this.webRTCConnectionTimer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0206  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object handleStateChangeEvent(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.handleStateChangeEvent(com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object pauseSession(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.amazon.tarazed.core.session.TarazedSession$pauseSession$1
            if (r0 == 0) goto L13
            r0 = r10
            com.amazon.tarazed.core.session.TarazedSession$pauseSession$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$pauseSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$pauseSession$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$pauseSession$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r9 = r0.L$2
            java.lang.InterruptedException r9 = (java.lang.InterruptedException) r9
            java.lang.Object r9 = r0.L$1
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r9 = (com.amazon.tarazed.core.session.TarazedSessionStateChangeSource) r9
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L72
        L35:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3d:
            kotlin.ResultKt.throwOnFailure(r10)
            com.amazon.tarazed.core.logging.TarazedSessionLogger r10 = r8.logger
            java.lang.String r2 = "TarazedSession"
            java.lang.String r4 = "Pausing Session"
            r10.i(r2, r4)
            com.amazon.tarazed.core.webrtc.WebRTCManager r10 = r8.webRTCManager     // Catch: java.lang.InterruptedException -> L52
            if (r10 == 0) goto L50
            r10.pause()     // Catch: java.lang.InterruptedException -> L52
        L50:
            r0 = r8
            goto L72
        L52:
            r10 = move-exception
            com.amazon.tarazed.core.logging.TarazedSessionLogger r4 = r8.logger
            java.lang.String r5 = "Interrupted when stopping screen capture, ending session."
            r4.e(r2, r5, r10)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r4 = r8.metricsHelper
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r7 = "StopCaptureInterrupted"
            r4.addCount(r2, r7, r5)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r10 = r8.endImmediately$TarazedMobileCore_release(r0)
            if (r10 != r1) goto L50
            return r1
        L72:
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r10 = com.amazon.tarazed.core.session.TarazedSessionStateChangeSource.SOURCE_CUSTOMER
            if (r9 != r10) goto L82
            long r9 = java.lang.System.currentTimeMillis()
            r0.sessionPausedTimeStamp = r9
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r9 = r0.sessionNotifier
            r9.notifySessionPausedByCustomer()
            goto L9b
        L82:
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r10 = com.amazon.tarazed.core.session.TarazedSessionStateChangeSource.SOURCE_AGENT
            if (r9 != r10) goto L92
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r9 = r0.sessionNotifier
            r9.notifySessionPausedByAgent()
            long r9 = java.lang.System.currentTimeMillis()
            r0.sessionPausedTimeStamp = r9
            goto L9b
        L92:
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r10 = com.amazon.tarazed.core.session.TarazedSessionStateChangeSource.SOURCE_3P_APP_EVENT
            if (r9 != r10) goto L9b
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r9 = r0.sessionNotifier
            r9.notifySessionPausedBy3pAppEvent()
        L9b:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.pauseSession(com.amazon.tarazed.core.session.TarazedSessionStateChangeSource, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    final /* synthetic */ Object requestResume(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (this.lastPauseEventSource == TarazedSessionStateChangeSource.SOURCE_AGENT && (this.deviceInfo.is1PDevice() || AppBackgroundChecker.INSTANCE.isAppInForeground())) {
            this.logger.i(TAG, "Agent paused the session, immediately resuming session");
            Object handleStateChangeEvent = handleStateChangeEvent(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.RESUME_SESSION, TarazedSessionStateChangeSource.SOURCE_AGENT), continuation);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return handleStateChangeEvent == coroutine_suspended ? handleStateChangeEvent : Unit.INSTANCE;
        }
        this.logger.i(TAG, "Requesting to Resume Session");
        this.sessionDialogManager.mo4643getResumeSessionPermissionDialog().start();
        this.sessionDialogManager.mo4643getResumeSessionPermissionDialog().show();
        this.sessionNotifier.notifyResumeRequested();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object requestStartSession(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.core.session.TarazedSession$requestStartSession$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.core.session.TarazedSession$requestStartSession$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$requestStartSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$requestStartSession$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$requestStartSession$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5a
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L38:
            java.lang.Object r2 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r2 = (com.amazon.tarazed.core.session.TarazedSession) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L40:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.startSessionOverIot(r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            r2 = r5
        L4f:
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.requestStartSessionDialog(r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.requestStartSession(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object requestStartSessionDialog(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.core.session.TarazedSession$requestStartSessionDialog$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.core.session.TarazedSession$requestStartSessionDialog$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$requestStartSessionDialog$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$requestStartSessionDialog$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$requestStartSessionDialog$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            boolean r1 = r0.Z$0
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L96
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L3a:
            java.lang.Object r2 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r2 = (com.amazon.tarazed.core.session.TarazedSession) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6c
        L42:
            kotlin.ResultKt.throwOnFailure(r8)
            com.amazon.tarazed.core.logging.TarazedSessionLogger r8 = r7.logger
            java.lang.String r2 = "TarazedSession"
            java.lang.String r5 = "Requesting to Start Session"
            r8.i(r2, r5)
            com.amazon.tarazed.core.utility.DeviceInfoUtility r8 = r7.deviceInfo
            boolean r8 = r8.is1PDevice()
            if (r8 != 0) goto L5c
            kotlinx.coroutines.Job r8 = r7.primerNotShownBizMetricTimer
            r2 = 0
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r8, r2, r4, r2)
        L5c:
            com.amazon.tarazed.core.utility.BrowserPresenceDetectorAtSessionStart r8 = r7.browserPresenceDetector
            r5 = 5000(0x1388, double:2.4703E-320)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r8.isBrowserPresent(r5, r0)
            if (r8 != r1) goto L6b
            return r1
        L6b:
            r2 = r7
        L6c:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L87
            com.amazon.tarazed.core.session.dialog.SessionDialogManager r8 = r2.sessionDialogManager
            com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog r8 = r8.getStartSessionPermissionDialog()
            r8.start()
            com.amazon.tarazed.core.session.dialog.SessionDialogManager r8 = r2.sessionDialogManager
            com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog r8 = r8.getStartSessionPermissionDialog()
            r8.show()
            goto L96
        L87:
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r4 = com.amazon.tarazed.core.session.TarazedSessionStateChangeSource.SOURCE_OTHER
            r0.L$0 = r2
            r0.Z$0 = r8
            r0.label = r3
            java.lang.Object r8 = r2.endSession(r4, r0)
            if (r8 != r1) goto L96
            return r1
        L96:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.requestStartSessionDialog(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object sendEndSessionRequest(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.core.session.TarazedSession$sendEndSessionRequest$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.core.session.TarazedSession$sendEndSessionRequest$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$sendEndSessionRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$sendEndSessionRequest$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$sendEndSessionRequest$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            java.lang.String r4 = "TarazedSession"
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r1 = r0.L$1
            com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionRequest r1 = (com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionRequest) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L33 io.ktor.client.features.BadResponseStatusException -> L35 java.io.IOException -> L37 java.net.UnknownHostException -> L39
            goto L83
        L33:
            r6 = move-exception
            goto L5e
        L35:
            r6 = move-exception
            goto L68
        L37:
            r6 = move-exception
            goto L72
        L39:
            r6 = move-exception
            goto L7c
        L3b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L43:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionRequest r6 = new com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionRequest     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            java.lang.String r2 = r5.sessionId     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            r6.<init>(r2)     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            com.amazon.tarazed.core.sessionclient.TarazedSessionClient r2 = r5.sessionClient     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            r0.L$0 = r5     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            r0.L$1 = r6     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            r0.label = r3     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            java.lang.Object r6 = r2.endSession(r6, r0)     // Catch: com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException -> L5c io.ktor.client.features.BadResponseStatusException -> L66 java.io.IOException -> L70 java.net.UnknownHostException -> L7a
            if (r6 != r1) goto L83
            return r1
        L5c:
            r6 = move-exception
            r0 = r5
        L5e:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = r0.logger
            java.lang.String r1 = "Authentication failed when ending session."
            r0.e(r4, r1, r6)
            goto L83
        L66:
            r6 = move-exception
            r0 = r5
        L68:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = r0.logger
            java.lang.String r1 = "Received bad response when ending session."
            r0.e(r4, r1, r6)
            goto L83
        L70:
            r6 = move-exception
            r0 = r5
        L72:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = r0.logger
            java.lang.String r1 = "Unable to connect to remote host due to network issue."
            r0.e(r4, r1, r6)
            goto L83
        L7a:
            r6 = move-exception
            r0 = r5
        L7c:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = r0.logger
            java.lang.String r1 = "Unable to resolve session service host."
            r0.e(r4, r1, r6)
        L83:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.sendEndSessionRequest(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void sendStateChangeEventRequest(@NotNull TarazedSessionStateChangeRequest state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        BuildersKt__BuildersKt.runBlocking$default(null, new TarazedSession$sendStateChangeEventRequest$1(this, state, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object setCredentials(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse r7, boolean r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.amazon.tarazed.core.session.TarazedSession$setCredentials$1
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.tarazed.core.session.TarazedSession$setCredentials$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$setCredentials$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$setCredentials$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$setCredentials$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            boolean r7 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse r7 = (com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse) r7
            java.lang.Object r8 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r8 = (com.amazon.tarazed.core.session.TarazedSession) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8d
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.List r9 = r7.getMediaCredentials()
            java.util.ArrayList r2 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r4)
            r2.<init>(r4)
            java.util.Iterator r9 = r9.iterator()
        L51:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L67
            java.lang.Object r4 = r9.next()
            com.amazon.tarazed.core.sessionclient.model.createcredentials.MediaCredential r4 = (com.amazon.tarazed.core.sessionclient.model.createcredentials.MediaCredential) r4
            boolean r5 = r6.isBeta
            com.amazon.rtcsc.android.typedapi.types.IceServer r4 = r4.toIceServer(r5)
            r2.add(r4)
            goto L51
        L67:
            r6.iceServers = r2
            if (r8 == 0) goto L76
            com.amazon.tarazed.core.session.TarazedSessionIoTMessenger r8 = r6.iotMessenger
            com.amazon.tarazed.core.sessionclient.model.createcredentials.SignalingCredentials r9 = r7.getSignalingCredentials()
            r8.refreshCredentials(r9)
        L74:
            r8 = r6
            goto L8d
        L76:
            long r4 = com.amazon.tarazed.core.session.TarazedSession.IOT_INITIAL_CONNECTION_TIMEOUT_MS
            com.amazon.tarazed.core.session.TarazedSession$setCredentials$3 r9 = new com.amazon.tarazed.core.session.TarazedSession$setCredentials$3
            r2 = 0
            r9.<init>(r6, r7, r2)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.Z$0 = r8
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r9, r0)
            if (r8 != r1) goto L74
            return r1
        L8d:
            com.amazon.tarazed.core.sessionclient.model.createcredentials.LoggingCredentials r9 = r7.getLoggingCredentials()
            r8.loggingCredentials = r9
            int r7 = r7.getDurationSeconds()
            long r0 = (long) r7
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r2
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)
            r8.durationMS = r7
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.setCredentials(com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setEndSessionTimer$TarazedMobileCore_release(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "<set-?>");
        this.endSessionTimer = job;
    }

    public final void setPlaybackState(@NotNull String state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.previousPlaybackState = this.playbackStateCache.getPlaybackState(this.sessionId);
        this.playbackStateCache.setPlaybackState(this.sessionId, state);
        this.playbackStateObservable.set(state);
    }

    public final void setPreviousPlaybackState(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.previousPlaybackState = str;
    }

    public final void setPrimerNotShownBizMetricTimer$TarazedMobileCore_release(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "<set-?>");
        this.primerNotShownBizMetricTimer = job;
    }

    public final void setScreenDimensions(int i, int i2) {
        this.screenWidth = i;
        this.screenHeight = i2;
    }

    public final void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public final void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public final void setStateChangeRequestReceiverJob$TarazedMobileCore_release(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "<set-?>");
        this.stateChangeRequestReceiverJob = job;
    }

    public final void setWebRTCConnectionTimer$TarazedMobileCore_release(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "<set-?>");
        this.webRTCConnectionTimer = job;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object startSession(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.amazon.tarazed.core.session.TarazedSession$startSession$1
            if (r0 == 0) goto L13
            r0 = r10
            com.amazon.tarazed.core.session.TarazedSession$startSession$1 r0 = (com.amazon.tarazed.core.session.TarazedSession$startSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedSession$startSession$1 r0 = new com.amazon.tarazed.core.session.TarazedSession$startSession$1
            r0.<init>(r9, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            java.lang.String r5 = "TarazedSession"
            if (r2 == 0) goto L49
            if (r2 == r3) goto L3f
            if (r2 != r4) goto L37
            java.lang.Object r1 = r0.L$1
            com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException r1 = (com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r0 = (com.amazon.tarazed.core.session.TarazedSession) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto Laa
        L37:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L3f:
            java.lang.Object r2 = r0.L$0
            com.amazon.tarazed.core.session.TarazedSession r2 = (com.amazon.tarazed.core.session.TarazedSession) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L47
            goto Laa
        L47:
            r10 = move-exception
            goto L78
        L49:
            kotlin.ResultKt.throwOnFailure(r10)
            com.amazon.tarazed.core.logging.TarazedSessionLogger r10 = r9.logger     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            r2.<init>()     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            java.lang.String r6 = "Starting Session: "
            r2.append(r6)     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            java.lang.String r6 = r9.sessionId     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            r2.append(r6)     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            java.lang.String r2 = r2.toString()     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            r10.i(r5, r2)     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r10 = r9.metricsHelper     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            java.lang.String r2 = "SessionDurationFromStart"
            r10.startMetricTimer(r5, r2)     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            r0.L$0 = r9     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            r0.label = r3     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            java.lang.Object r10 = r9.startSessionOverWebRTC(r0)     // Catch: com.amazon.tarazed.core.webrtc.android.TarazedMediaProjectionException -> L76
            if (r10 != r1) goto Laa
            return r1
        L76:
            r10 = move-exception
            r2 = r9
        L78:
            int r3 = r10.getErrorCode()
            if (r3 == r4) goto L8f
            com.amazon.tarazed.core.logging.TarazedSessionLogger r3 = r2.logger
            java.lang.String r6 = "Video capture failed, ending session"
            r3.e(r5, r6, r10)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r3 = r2.metricsHelper
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r8 = "VideoCaptureFailed"
            r3.addCountHighPriority(r5, r8, r6)
            goto L96
        L8f:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r3 = r2.logger
            java.lang.String r6 = "Video capture permission was rejected by customer, ending session"
            r3.w(r5, r6)
        L96:
            com.amazon.tarazed.core.session.TarazedSessionIoTMessenger r3 = r2.iotMessenger
            java.lang.String r5 = "permissionRejected"
            r3.sendPermissionStateChange(r5)
            r0.L$0 = r2
            r0.L$1 = r10
            r0.label = r4
            java.lang.Object r10 = r2.endImmediately$TarazedMobileCore_release(r0)
            if (r10 != r1) goto Laa
            return r1
        Laa:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.startSession(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(7:1|(2:3|(4:5|6|7|8))|95|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b1, code lost:
        r10 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b4, code lost:
        r10 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b7, code lost:
        r10 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ba, code lost:
        r10 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bd, code lost:
        r10 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c0, code lost:
        r10 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fe A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011c  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsRequest, java.lang.Object] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object startSessionOverIot(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession.startSessionOverIot(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    final /* synthetic */ Object startSessionOverWebRTC(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        WebRTCManagerProvider webRTCManagerProvider = this.webRTCManagerProvider;
        WebRTCManagerHelper webRTCManagerHelper = this.webRTCManagerHelper;
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
        TarazedIoTManager tarazedIoTManager = this.iotManager;
        TarazedSessionNotifier tarazedSessionNotifier = this.sessionNotifier;
        RotationListener rotationListener = this.rotationListener;
        List<? extends IceServer> list = this.iceServers;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iceServers");
        }
        this.webRTCManager = webRTCManagerProvider.provideWebRTCManager(webRTCManagerHelper, tarazedSessionLogger, tarazedMetricsHelper, tarazedIoTManager, tarazedSessionNotifier, rotationListener, list, this.videoParameters, this.screenWidth, this.screenHeight, this.sessionId, this.bizMetricsHelper, this.deviceInfo);
        WebRTCManager webRTCManager = this.webRTCManager;
        if (webRTCManager != null) {
            this.eventDispatcher.registerHandler(new WebRTCMessageHandler(webRTCManager, this.logger, this.metricsHelper));
        }
        WebRTCManager webRTCManager2 = this.webRTCManager;
        if (webRTCManager2 != null) {
            webRTCManager2.registerRTCSCListeners();
        }
        this.requester.requestAppInfo();
        this.sessionNotifier.notifySessionActive();
        Object connectWebRTC = connectWebRTC(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return connectWebRTC == coroutine_suspended ? connectWebRTC : Unit.INSTANCE;
    }
}
