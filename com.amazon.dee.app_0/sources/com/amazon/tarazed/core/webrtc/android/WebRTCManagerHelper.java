package com.amazon.tarazed.core.webrtc.android;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.webrtc.WebRTCManager;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedAppClient;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedAppClientListener;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedCapabilityAgent;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedCapabilityAgentListener;
import com.amazon.tarazed.core.webrtc.rtcsc.TarazedScreenCapturerListener;
import java.util.logging.Handler;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WebRTCManagerHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 62\u00020\u0001:\u00016BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\r\u0010\u001d\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001eJ\u0011\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0015\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b&J\r\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020(H\u0000¢\u0006\u0002\b-J\r\u0010.\u001a\u00020/H\u0000¢\u0006\u0002\b0J\u001d\u00101\u001a\u0002022\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b3J\u000e\u00104\u001a\u0002052\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;", "", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "mediaProjectionPermissionTimeoutMs", "", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;J)V", "resultReceiver", "Lcom/amazon/tarazed/core/webrtc/android/MediaProjectionResultReceiver;", "resultReceiver$annotations", "()V", "getResultReceiver$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/webrtc/android/MediaProjectionResultReceiver;", "setResultReceiver$TarazedMobileCore_release", "(Lcom/amazon/tarazed/core/webrtc/android/MediaProjectionResultReceiver;)V", "webRTCLogger", "Ljava/util/logging/Logger;", "getWebRTCLogger", "getWebRTCLogger$TarazedMobileCore_release", "provideMediaProjectionPermissions", "Landroid/content/Intent;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "provideScreenCapturerListener", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedScreenCapturerListener;", "webRTCManager", "Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "provideScreenCapturerListener$TarazedMobileCore_release", "provideTarazedAppClient", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClient;", "provideTarazedAppClient$TarazedMobileCore_release", "provideTarazedAppClientListener", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClientListener;", "appClient", "provideTarazedAppClientListener$TarazedMobileCore_release", "provideTarazedCapabilityAgent", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgent;", "provideTarazedCapabilityAgent$TarazedMobileCore_release", "provideTarazedCapabilityAgentListener", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgentListener;", "provideTarazedCapabilityAgentListener$TarazedMobileCore_release", "setWebRTCLogger", "", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WebRTCManagerHelper {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WebRTCManagerHelper";
    private static final String WEBRTC_LOGGER_NAME = "org.webrtc.Logging";
    private final BizMetricsHelper bizMetricsHelper;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final DispatcherProvider dispatchers;
    private final TarazedSessionLogger logger;
    private final long mediaProjectionPermissionTimeoutMs;
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    public MediaProjectionResultReceiver resultReceiver;
    private final TarazedSessionNotifier sessionNotifier;
    private Logger webRTCLogger;

    /* compiled from: WebRTCManagerHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper$Companion;", "", "()V", "TAG", "", "WEBRTC_LOGGER_NAME", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WebRTCManagerHelper(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull TarazedSessionNotifier sessionNotifier, long j) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.bizMetricsHelper = bizMetricsHelper;
        this.coroutineScope = coroutineScope;
        this.dispatchers = dispatchers;
        this.sessionNotifier = sessionNotifier;
        this.mediaProjectionPermissionTimeoutMs = j;
    }

    @VisibleForTesting
    public static /* synthetic */ void resultReceiver$annotations() {
    }

    @NotNull
    public final MediaProjectionResultReceiver getResultReceiver$TarazedMobileCore_release() {
        MediaProjectionResultReceiver mediaProjectionResultReceiver = this.resultReceiver;
        if (mediaProjectionResultReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultReceiver");
        }
        return mediaProjectionResultReceiver;
    }

    @VisibleForTesting
    @NotNull
    public final Logger getWebRTCLogger$TarazedMobileCore_release() {
        Logger logger = Logger.getLogger(WEBRTC_LOGGER_NAME);
        Intrinsics.checkExpressionValueIsNotNull(logger, "Logger.getLogger(WEBRTC_LOGGER_NAME)");
        return logger;
    }

    @Nullable
    public final Object provideMediaProjectionPermissions(@NotNull Continuation<? super Intent> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        TarazedMediaProjectionActivity.Companion.setLogger$TarazedMobileCore_release(this.logger);
        TarazedMediaProjectionActivity.Companion.setMetricsHelper$TarazedMobileCore_release(this.metricsHelper);
        TarazedMediaProjectionActivity.Companion.setBizMetricsHelper$TarazedMobileCore_release(this.bizMetricsHelper);
        TarazedMediaProjectionActivity.Companion.setPermissionTimeoutMs$TarazedMobileCore_release(this.mediaProjectionPermissionTimeoutMs);
        Intent intent = new Intent(this.context, TarazedMediaProjectionActivity.class);
        intent.addFlags(268435456);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        SafeContinuation safeContinuation = new SafeContinuation(intercepted);
        this.resultReceiver = new MediaProjectionResultReceiver(safeContinuation);
        MediaProjectionResultReceiver mediaProjectionResultReceiver = this.resultReceiver;
        if (mediaProjectionResultReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultReceiver");
        }
        intent.putExtra(TarazedMediaProjectionActivity.EXTRA_KEY_SCREEN_CAPTURE_RESULT_RECEIVER, mediaProjectionResultReceiver.getGenericResultReceiver());
        this.context.startActivity(intent);
        Object orThrow = safeContinuation.getOrThrow();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (orThrow == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    @NotNull
    public final TarazedScreenCapturerListener provideScreenCapturerListener$TarazedMobileCore_release(@NotNull WebRTCManager webRTCManager) {
        Intrinsics.checkParameterIsNotNull(webRTCManager, "webRTCManager");
        return new TarazedScreenCapturerListener(webRTCManager, this.logger);
    }

    @NotNull
    public final TarazedAppClient provideTarazedAppClient$TarazedMobileCore_release() {
        return new TarazedAppClient(this.context, this.logger);
    }

    @NotNull
    public final TarazedAppClientListener provideTarazedAppClientListener$TarazedMobileCore_release(@NotNull TarazedAppClient appClient) {
        Intrinsics.checkParameterIsNotNull(appClient, "appClient");
        return new TarazedAppClientListener(this.logger, appClient, this.sessionNotifier, this.metricsHelper);
    }

    @NotNull
    public final TarazedCapabilityAgent provideTarazedCapabilityAgent$TarazedMobileCore_release() {
        return new TarazedCapabilityAgent(this.context, this.logger);
    }

    @NotNull
    public final TarazedCapabilityAgentListener provideTarazedCapabilityAgentListener$TarazedMobileCore_release(@NotNull WebRTCManager webRTCManager, @NotNull TarazedSessionNotifier sessionNotifier) {
        Intrinsics.checkParameterIsNotNull(webRTCManager, "webRTCManager");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        return new TarazedCapabilityAgentListener(webRTCManager, sessionNotifier, this.logger, this.metricsHelper);
    }

    public final void setResultReceiver$TarazedMobileCore_release(@NotNull MediaProjectionResultReceiver mediaProjectionResultReceiver) {
        Intrinsics.checkParameterIsNotNull(mediaProjectionResultReceiver, "<set-?>");
        this.resultReceiver = mediaProjectionResultReceiver;
    }

    public final void setWebRTCLogger(@NotNull TarazedSessionLogger logger) {
        Handler[] handlers;
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.webRTCLogger = getWebRTCLogger$TarazedMobileCore_release();
        Logger logger2 = this.webRTCLogger;
        if (logger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webRTCLogger");
        }
        for (Handler handler : logger2.getHandlers()) {
            if (handler instanceof WebRTCLogger) {
                Logger logger3 = this.webRTCLogger;
                if (logger3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webRTCLogger");
                }
                logger3.removeHandler(handler);
            }
        }
        Logger logger4 = this.webRTCLogger;
        if (logger4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webRTCLogger");
        }
        logger4.setUseParentHandlers(false);
        Logger logger5 = this.webRTCLogger;
        if (logger5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webRTCLogger");
        }
        logger5.addHandler(new WebRTCLogger(logger));
        logger.i(TAG, "WebRTC logger added");
    }
}
