package com.amazon.tarazed.core.webrtc;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalFormats;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: SdpModifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0002J&\u0010\u000e\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0016\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/SdpModifier;", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "insertBandwidthLimit", "", "sdpLines", "", "", "maxBitrate", "", "insertSdpLine", "lineToInsertAfter", "lineToInsert", "modifySDP", WebRTCSignalFormats.SDP, "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SdpModifier {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DELIMITER = "\r\n";
    @NotNull
    public static final String METRIC_SDP_MODIFICATION_FAILED = "SDPModificationFailed";
    @NotNull
    public static final String TAG = "SdpModifier";
    @NotNull
    public static final String VIDEO_MIDLINE = "a=mid:video";
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: SdpModifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/SdpModifier$Companion;", "", "()V", "DELIMITER", "", "METRIC_SDP_MODIFICATION_FAILED", "TAG", "VIDEO_MIDLINE", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SdpModifier(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.logger = logger;
        this.metricsHelper = metricsHelper;
    }

    private final void insertBandwidthLimit(List<String> list, int i) {
        insertSdpLine(list, VIDEO_MIDLINE, GeneratedOutlineSupport1.outline49("b=AS:", i));
    }

    private final void insertSdpLine(List<String> list, String str, String str2) {
        boolean contains$default;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str3 = list.get(i);
            if (str3.length() > 0) {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str3, (CharSequence) str, false, 2, (Object) null);
                if (contains$default) {
                    list.add(i + 1, str2);
                }
            }
        }
    }

    @NotNull
    public final String modifySDP(int i, @NotNull String sdp) {
        List split$default;
        List<String> mutableList;
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(sdp, "sdp");
        split$default = StringsKt__StringsKt.split$default((CharSequence) sdp, new String[]{"\r\n"}, false, 0, 6, (Object) null);
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) split$default);
        try {
            insertBandwidthLimit(mutableList, i);
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(mutableList, "\r\n", null, null, 0, null, null, 62, null);
            return joinToString$default;
        } catch (Exception e) {
            this.logger.e(TAG, "Error modifying SDP, using unmodified", e);
            this.metricsHelper.addCount(TAG, METRIC_SDP_MODIFICATION_FAILED, 1.0d);
            return sdp;
        }
    }
}
