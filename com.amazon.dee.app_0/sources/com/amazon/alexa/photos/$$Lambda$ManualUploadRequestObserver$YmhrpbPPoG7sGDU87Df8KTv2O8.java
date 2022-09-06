package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$Y-mhrpbPPoG7sGDU87Df8KTv2O8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$YmhrpbPPoG7sGDU87Df8KTv2O8 implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$YmhrpbPPoG7sGDU87Df8KTv2O8 INSTANCE = new $$Lambda$ManualUploadRequestObserver$YmhrpbPPoG7sGDU87Df8KTv2O8();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$YmhrpbPPoG7sGDU87Df8KTv2O8() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_BLOCKED_NO_WIFI;
        return PhotosMetricsConstants.UPLOAD_BLOCKED_NO_WIFI;
    }
}
