package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$k4Ve8Zixuya8lwL8wkwqoFUy3-U  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$k4Ve8Zixuya8lwL8wkwqoFUy3U implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$k4Ve8Zixuya8lwL8wkwqoFUy3U INSTANCE = new $$Lambda$ManualUploadRequestObserver$k4Ve8Zixuya8lwL8wkwqoFUy3U();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$k4Ve8Zixuya8lwL8wkwqoFUy3U() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.ADD_CHILD_TO_ALBUM;
        return PhotosMetricsConstants.ADD_CHILD_TO_ALBUM;
    }
}
