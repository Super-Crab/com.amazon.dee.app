package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$zX6Cg-bc9psBPq-Y2S8iRi_8iMg  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$zX6Cgbc9psBPqY2S8iRi_8iMg implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$zX6Cgbc9psBPqY2S8iRi_8iMg INSTANCE = new $$Lambda$ManualUploadRequestObserver$zX6Cgbc9psBPqY2S8iRi_8iMg();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$zX6Cgbc9psBPqY2S8iRi_8iMg() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUEST_ABANDONED;
        return PhotosMetricsConstants.UPLOAD_V2_REQUEST_ABANDONED;
    }
}
