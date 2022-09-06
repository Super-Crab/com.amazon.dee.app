package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$v7bTiiF6AJIal24cLiSERvVg7Hg  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$v7bTiiF6AJIal24cLiSERvVg7Hg implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$v7bTiiF6AJIal24cLiSERvVg7Hg INSTANCE = new $$Lambda$ManualUploadRequestObserver$v7bTiiF6AJIal24cLiSERvVg7Hg();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$v7bTiiF6AJIal24cLiSERvVg7Hg() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUEST_OVER_QUOTA;
        return PhotosMetricsConstants.UPLOAD_V2_REQUEST_OVER_QUOTA;
    }
}
