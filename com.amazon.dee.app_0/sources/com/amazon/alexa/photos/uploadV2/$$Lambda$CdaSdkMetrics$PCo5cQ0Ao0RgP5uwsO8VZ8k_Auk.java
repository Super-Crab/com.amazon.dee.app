package com.amazon.alexa.photos.uploadV2;

import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkMetrics$PCo5cQ0Ao0RgP5uwsO8VZ8k_Auk  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$CdaSdkMetrics$PCo5cQ0Ao0RgP5uwsO8VZ8k_Auk implements MetricName {
    public static final /* synthetic */ $$Lambda$CdaSdkMetrics$PCo5cQ0Ao0RgP5uwsO8VZ8k_Auk INSTANCE = new $$Lambda$CdaSdkMetrics$PCo5cQ0Ao0RgP5uwsO8VZ8k_Auk();

    private /* synthetic */ $$Lambda$CdaSdkMetrics$PCo5cQ0Ao0RgP5uwsO8VZ8k_Auk() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        CdaSdkMetrics.lambda$recordCallSuccess$1();
        return "OverallSuccess";
    }
}
