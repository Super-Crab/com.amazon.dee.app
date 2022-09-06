package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessorykit.interprocess.DefaultCallNotificationStateMonitor;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$CmtfG9hv2E5Cr3Xb-biLaGTt7hg  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterprocessPrivacyModeEmitter$CmtfG9hv2E5Cr3XbbiLaGTt7hg implements Function {
    public static final /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$CmtfG9hv2E5Cr3XbbiLaGTt7hg INSTANCE = new $$Lambda$InterprocessPrivacyModeEmitter$CmtfG9hv2E5Cr3XbbiLaGTt7hg();

    private /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$CmtfG9hv2E5Cr3XbbiLaGTt7hg() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        DefaultCallNotificationStateMonitor.CallNotificationStatus callNotificationStatus = (DefaultCallNotificationStateMonitor.CallNotificationStatus) obj;
        valueOf = Boolean.valueOf(r1 != DefaultCallNotificationStateMonitor.CallNotificationStatus.NO_ACTIVITY);
        return valueOf;
    }
}
