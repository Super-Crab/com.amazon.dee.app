package com.amazon.deecomms.common.metrics;

import com.amazon.deecomms.common.metrics.MetricsHelper;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.metrics.-$$Lambda$MetricsHelper$JkmiF-HOg7NofX-M4Nbok2aM6Aw  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MetricsHelper$JkmiFHOg7NofXM4Nbok2aM6Aw implements MetricsHelper.NetworkExceptionClassifier {
    public static final /* synthetic */ $$Lambda$MetricsHelper$JkmiFHOg7NofXM4Nbok2aM6Aw INSTANCE = new $$Lambda$MetricsHelper$JkmiFHOg7NofXM4Nbok2aM6Aw();

    private /* synthetic */ $$Lambda$MetricsHelper$JkmiFHOg7NofXM4Nbok2aM6Aw() {
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsHelper.NetworkExceptionClassifier
    public final boolean isNetworkException(Throwable th) {
        return MetricsHelper.lambda$static$0(th);
    }
}
