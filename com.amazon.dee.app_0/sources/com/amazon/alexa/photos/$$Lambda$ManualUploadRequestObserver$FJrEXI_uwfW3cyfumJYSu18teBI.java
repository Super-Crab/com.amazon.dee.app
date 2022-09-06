package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$FJrEXI_uwfW3cyfumJYSu18teBI  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$FJrEXI_uwfW3cyfumJYSu18teBI implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$FJrEXI_uwfW3cyfumJYSu18teBI INSTANCE = new $$Lambda$ManualUploadRequestObserver$FJrEXI_uwfW3cyfumJYSu18teBI();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$FJrEXI_uwfW3cyfumJYSu18teBI() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_BLOCKED_PAUSED;
        return PhotosMetricsConstants.UPLOAD_BLOCKED_PAUSED;
    }
}
