package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$UploadOperation$LQop5MnKhabWVRuTP8QHOCarQm0  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$UploadOperation$LQop5MnKhabWVRuTP8QHOCarQm0 implements MetricName {
    public static final /* synthetic */ $$Lambda$UploadOperation$LQop5MnKhabWVRuTP8QHOCarQm0 INSTANCE = new $$Lambda$UploadOperation$LQop5MnKhabWVRuTP8QHOCarQm0();

    private /* synthetic */ $$Lambda$UploadOperation$LQop5MnKhabWVRuTP8QHOCarQm0() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        return PhotosMetricsConstants.INTERRUPTED_EXCEPTION;
    }
}
