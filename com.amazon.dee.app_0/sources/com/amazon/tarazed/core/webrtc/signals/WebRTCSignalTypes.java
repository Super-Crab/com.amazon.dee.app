package com.amazon.tarazed.core.webrtc.signals;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: WebRTCSignalTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/signals/WebRTCSignalTypes;", "", "()V", "ANSWER", "", "EVENT_TYPE_MEDIA_SIGNAL", "OFFER", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WebRTCSignalTypes {
    @NotNull
    public static final String ANSWER = "answer";
    @NotNull
    public static final String EVENT_TYPE_MEDIA_SIGNAL = "mediaSignal";
    public static final WebRTCSignalTypes INSTANCE = new WebRTCSignalTypes();
    @NotNull
    public static final String OFFER = "offer";

    private WebRTCSignalTypes() {
    }
}
