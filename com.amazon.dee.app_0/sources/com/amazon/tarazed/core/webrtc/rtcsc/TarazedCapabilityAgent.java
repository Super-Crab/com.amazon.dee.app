package com.amazon.tarazed.core.webrtc.rtcsc;

import android.content.Context;
import com.amazon.rtcsc.capabilityagentclient.RtcscCAClient;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedCapabilityAgent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgent;", "Lcom/amazon/rtcsc/capabilityagentclient/RtcscCAClient;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "onRtcscServiceConnected", "", "onRtcscServiceDisconnected", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedCapabilityAgent extends RtcscCAClient {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TarazedCapabilityAgent";
    private final TarazedSessionLogger logger;

    /* compiled from: TarazedCapabilityAgent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/rtcsc/TarazedCapabilityAgent$Companion;", "", "()V", "TAG", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedCapabilityAgent(@NotNull Context context, @NotNull TarazedSessionLogger logger) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAClient
    protected void onRtcscServiceConnected() {
        this.logger.i(TAG, "onRtcscServiceConnected received.");
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAClient
    protected void onRtcscServiceDisconnected() {
        this.logger.i(TAG, "onRtcscServiceDisconnected received.");
    }
}
