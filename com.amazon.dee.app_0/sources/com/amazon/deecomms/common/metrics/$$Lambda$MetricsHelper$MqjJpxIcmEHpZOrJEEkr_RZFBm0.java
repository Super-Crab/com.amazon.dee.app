package com.amazon.deecomms.common.metrics;

import com.amazon.deecomms.common.metrics.MetricsHelper;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.metrics.-$$Lambda$MetricsHelper$MqjJpxIcmEHpZOrJEEkr_RZFBm0  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MetricsHelper$MqjJpxIcmEHpZOrJEEkr_RZFBm0 implements MetricsHelper.NetworkExceptionClassifier {
    public static final /* synthetic */ $$Lambda$MetricsHelper$MqjJpxIcmEHpZOrJEEkr_RZFBm0 INSTANCE = new $$Lambda$MetricsHelper$MqjJpxIcmEHpZOrJEEkr_RZFBm0();

    private /* synthetic */ $$Lambda$MetricsHelper$MqjJpxIcmEHpZOrJEEkr_RZFBm0() {
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsHelper.NetworkExceptionClassifier
    public final boolean isNetworkException(Throwable th) {
        return MetricsHelper.lambda$static$1(th);
    }
}
