package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY INSTANCE = new $$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUESTS_COMPLETED_WITHOUT_NODE;
        return PhotosMetricsConstants.UPLOAD_V2_REQUESTS_COMPLETED_WITHOUT_NODE;
    }
}
