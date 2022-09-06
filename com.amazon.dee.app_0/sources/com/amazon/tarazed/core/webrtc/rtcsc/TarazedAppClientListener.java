package com.amazon.tarazed.core.webrtc.rtcsc;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcsc.appclient.RtcscAppClientListener;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedAppClientListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\u0011\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClientListener;", "Lcom/amazon/rtcsc/appclient/RtcscAppClientListener;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "appClient", "Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClient;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClient;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "getAppClient", "()Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClient;", "onSessionAvailable", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "onSessionError", "errorCode", "Lcom/amazon/rtcsc/interfaces/RtcscErrorCode;", "errorMessage", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedAppClientListener extends RtcscAppClientListener {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_RTCSC_ERROR = "RtcscErrorReceived";
    private static final String METRIC_RTCSC_MEDIA_CONNECTION_LOST = "RtcscMediaConnectionLost";
    private static final String TAG = "TarazedAppClientListr";
    @NotNull
    private final TarazedAppClient appClient;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final TarazedSessionNotifier sessionNotifier;

    /* compiled from: TarazedAppClientListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedAppClientListener$Companion;", "", "()V", "METRIC_RTCSC_ERROR", "", "METRIC_RTCSC_MEDIA_CONNECTION_LOST", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedAppClientListener(@NotNull TarazedSessionLogger logger, @NotNull TarazedAppClient appClient, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(appClient, "appClient");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.logger = logger;
        this.appClient = appClient;
        this.sessionNotifier = sessionNotifier;
        this.metricsHelper = metricsHelper;
    }

    @NotNull
    public final TarazedAppClient getAppClient() {
        return this.appClient;
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onSessionAvailable(@NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Received onSessionAvailable from RtcscService. sessionId: " + sessionId);
        this.appClient.signalReadyForSession(sessionId);
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onSessionError(@Nullable String str, @NotNull RtcscErrorCode errorCode, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.e(TAG, "Received onSessionError from RtcscService. sessionId: " + str + ", errorCode: " + errorCode + ", errorMessage: " + str2);
        if (errorCode == RtcscErrorCode.MEDIA_CONNECTION_LOST) {
            this.metricsHelper.addCountHighPriority(TAG, METRIC_RTCSC_MEDIA_CONNECTION_LOST, 1.0d);
            this.sessionNotifier.notifyMediaDisconnected();
        } else if (errorCode == RtcscErrorCode.SUCCESS) {
        } else {
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            tarazedSessionLogger2.e(TAG, "Received Error from Rtcsc: " + errorCode + ", ending the session ");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_RTCSC_ERROR, 1.0d);
            this.sessionNotifier.notifyMediaFailed();
        }
    }
}
