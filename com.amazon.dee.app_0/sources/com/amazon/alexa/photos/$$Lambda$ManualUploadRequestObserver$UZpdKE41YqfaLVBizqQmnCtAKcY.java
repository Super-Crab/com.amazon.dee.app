package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$UZpdKE41YqfaLVBizqQmnCtAKcY  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$UZpdKE41YqfaLVBizqQmnCtAKcY implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$UZpdKE41YqfaLVBizqQmnCtAKcY INSTANCE = new $$Lambda$ManualUploadRequestObserver$UZpdKE41YqfaLVBizqQmnCtAKcY();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$UZpdKE41YqfaLVBizqQmnCtAKcY() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUEST_OVER_QUOTA;
        return PhotosMetricsConstants.UPLOAD_V2_REQUEST_OVER_QUOTA;
    }
}
