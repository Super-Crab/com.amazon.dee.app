package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$UploadOperation$trfoY1y0AbUesIBob21ZXSzbWVY  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$UploadOperation$trfoY1y0AbUesIBob21ZXSzbWVY implements MetricName {
    public static final /* synthetic */ $$Lambda$UploadOperation$trfoY1y0AbUesIBob21ZXSzbWVY INSTANCE = new $$Lambda$UploadOperation$trfoY1y0AbUesIBob21ZXSzbWVY();

    private /* synthetic */ $$Lambda$UploadOperation$trfoY1y0AbUesIBob21ZXSzbWVY() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        return PhotosMetricsConstants.CLOUD_DRIVE_EXCEPTION;
    }
}
