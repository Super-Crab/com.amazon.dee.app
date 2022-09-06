package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$Bh_tmIonwFQY7BFUkV_jfKRYxj8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$Bh_tmIonwFQY7BFUkV_jfKRYxj8 implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$Bh_tmIonwFQY7BFUkV_jfKRYxj8 INSTANCE = new $$Lambda$ManualUploadRequestObserver$Bh_tmIonwFQY7BFUkV_jfKRYxj8();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$Bh_tmIonwFQY7BFUkV_jfKRYxj8() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_BLOCKED_NO_PERMISSIONS;
        return PhotosMetricsConstants.UPLOAD_BLOCKED_NO_PERMISSIONS;
    }
}
