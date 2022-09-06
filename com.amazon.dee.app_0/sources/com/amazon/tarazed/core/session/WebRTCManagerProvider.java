package com.amazon.tarazed.core.session;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.rtcsc.android.typedapi.types.IceServer;
import com.amazon.tarazed.core.coroutine.scope.MainCoroutineScope;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.RotationListener;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.core.webrtc.VideoParameters;
import com.amazon.tarazed.core.webrtc.WebRTCManager;
import com.amazon.tarazed.core.webrtc.android.WebRTCManagerHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WebRTCManagerProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Jt\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e¨\u0006\u001f"}, d2 = {"Lcom/amazon/tarazed/core/session/WebRTCManagerProvider;", "", "()V", "provideWebRTCManager", "Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "webRTCManagerHelper", "Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "rotationListener", "Lcom/amazon/tarazed/core/rotation/RotationListener;", "iceServers", "", "Lcom/amazon/rtcsc/android/typedapi/types/IceServer;", "videoParameters", "Lcom/amazon/tarazed/core/webrtc/VideoParameters;", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "", "screenHeight", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WebRTCManagerProvider {
    @NotNull
    public final WebRTCManager provideWebRTCManager(@NotNull WebRTCManagerHelper webRTCManagerHelper, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedIoTManager iotManager, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull RotationListener rotationListener, @NotNull List<? extends IceServer> iceServers, @NotNull VideoParameters videoParameters, int i, int i2, @NotNull String sessionId, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(webRTCManagerHelper, "webRTCManagerHelper");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(rotationListener, "rotationListener");
        Intrinsics.checkParameterIsNotNull(iceServers, "iceServers");
        Intrinsics.checkParameterIsNotNull(videoParameters, "videoParameters");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        return new WebRTCManager(webRTCManagerHelper, logger, metricsHelper, iotManager, sessionNotifier, rotationListener, iceServers, videoParameters, i, i2, sessionId, new MainCoroutineScope(null, 1, null), bizMetricsHelper, deviceInfoUtility);
    }
}
