package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$47bgw6_5uRFc4Fazy-wIvvoWDeg  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$47bgw6_5uRFc4FazywIvvoWDeg implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$47bgw6_5uRFc4FazywIvvoWDeg INSTANCE = new $$Lambda$ManualUploadRequestObserver$47bgw6_5uRFc4FazywIvvoWDeg();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$47bgw6_5uRFc4FazywIvvoWDeg() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_BLOCKED_LOW_BATTERY;
        return PhotosMetricsConstants.UPLOAD_BLOCKED_LOW_BATTERY;
    }
}
