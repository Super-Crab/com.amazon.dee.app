package com.amazon.tarazed.core.webrtc.rtcsc;

import com.amazon.rtcsc.appclient.screencapturer.RtcscScreenCapturerListener;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.webrtc.WebRTCManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedScreenCapturerListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedScreenCapturerListener;", "Lcom/amazon/rtcsc/appclient/screencapturer/RtcscScreenCapturerListener;", "webRTCManager", "Lcom/amazon/tarazed/core/webrtc/WebRTCManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Lcom/amazon/tarazed/core/webrtc/WebRTCManager;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "onPutScreenCapturerData", "", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedScreenCapturerListener extends RtcscScreenCapturerListener {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TarazedScreenCapListnr";
    private final TarazedSessionLogger logger;
    private final WebRTCManager webRTCManager;

    /* compiled from: TarazedScreenCapturerListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedScreenCapturerListener$Companion;", "", "()V", "TAG", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedScreenCapturerListener(@NotNull WebRTCManager webRTCManager, @NotNull TarazedSessionLogger logger) {
        Intrinsics.checkParameterIsNotNull(webRTCManager, "webRTCManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.webRTCManager = webRTCManager;
        this.logger = logger;
    }

    @Override // com.amazon.rtcsc.appclient.screencapturer.RtcscScreenCapturerListener, com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener
    public void onPutScreenCapturerData() {
        this.logger.i(TAG, "Screen capturer permissions has been successfully set in RTCSCService");
        this.webRTCManager.initiateSession();
    }
}
