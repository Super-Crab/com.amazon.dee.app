package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$mfoj6husJ0cLuUBYsdCp9D8wPSc  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$mfoj6husJ0cLuUBYsdCp9D8wPSc implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$mfoj6husJ0cLuUBYsdCp9D8wPSc INSTANCE = new $$Lambda$ManualUploadRequestObserver$mfoj6husJ0cLuUBYsdCp9D8wPSc();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$mfoj6husJ0cLuUBYsdCp9D8wPSc() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUESTS_WITH_AUTOSAVE_COMPLETED;
        return PhotosMetricsConstants.UPLOAD_V2_REQUESTS_WITH_AUTOSAVE_COMPLETED;
    }
}
