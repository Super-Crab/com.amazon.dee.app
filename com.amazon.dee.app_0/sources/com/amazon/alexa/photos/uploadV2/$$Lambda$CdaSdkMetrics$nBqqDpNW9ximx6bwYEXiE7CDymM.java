package com.amazon.alexa.photos.uploadV2;

import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$nBqqDpNW9ximx6bwYEXiE7CDymM  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$CdaSdkMetrics$nBqqDpNW9ximx6bwYEXiE7CDymM implements MetricName {
    public static final /* synthetic */ $$Lambda$CdaSdkMetrics$nBqqDpNW9ximx6bwYEXiE7CDymM INSTANCE = new $$Lambda$CdaSdkMetrics$nBqqDpNW9ximx6bwYEXiE7CDymM();

    private /* synthetic */ $$Lambda$CdaSdkMetrics$nBqqDpNW9ximx6bwYEXiE7CDymM() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        CdaSdkMetrics.lambda$recordCallSuccess$3();
        return "OverallTime";
    }
}
