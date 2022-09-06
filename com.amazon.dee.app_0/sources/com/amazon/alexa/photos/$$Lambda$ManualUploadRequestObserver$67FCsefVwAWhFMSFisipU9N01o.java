package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$-67FCsefVwAWhFMSFisipU9N01o  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$67FCsefVwAWhFMSFisipU9N01o implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$67FCsefVwAWhFMSFisipU9N01o INSTANCE = new $$Lambda$ManualUploadRequestObserver$67FCsefVwAWhFMSFisipU9N01o();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$67FCsefVwAWhFMSFisipU9N01o() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.MD5_CONFLICT_SUCCESSFULLY_ADDED_TO_ALBUM;
        return PhotosMetricsConstants.MD5_CONFLICT_SUCCESSFULLY_ADDED_TO_ALBUM;
    }
}
