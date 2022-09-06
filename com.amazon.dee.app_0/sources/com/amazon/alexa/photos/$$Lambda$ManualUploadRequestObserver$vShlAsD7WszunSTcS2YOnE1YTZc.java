package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$vShlAsD7WszunSTcS2YOnE1YTZc  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$vShlAsD7WszunSTcS2YOnE1YTZc implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$vShlAsD7WszunSTcS2YOnE1YTZc INSTANCE = new $$Lambda$ManualUploadRequestObserver$vShlAsD7WszunSTcS2YOnE1YTZc();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$vShlAsD7WszunSTcS2YOnE1YTZc() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.MAKE_UPLOAD_LONG_RUNNING_FAILED_NO_UPLOAD_BUNDLE;
        return PhotosMetricsConstants.MAKE_UPLOAD_LONG_RUNNING_FAILED_NO_UPLOAD_BUNDLE;
    }
}
