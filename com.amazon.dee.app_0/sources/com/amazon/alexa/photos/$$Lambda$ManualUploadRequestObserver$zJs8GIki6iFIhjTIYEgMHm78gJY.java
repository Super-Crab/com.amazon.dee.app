package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$zJs8GIki6iFIhjTIYEgMHm78gJY  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$zJs8GIki6iFIhjTIYEgMHm78gJY implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$zJs8GIki6iFIhjTIYEgMHm78gJY INSTANCE = new $$Lambda$ManualUploadRequestObserver$zJs8GIki6iFIhjTIYEgMHm78gJY();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$zJs8GIki6iFIhjTIYEgMHm78gJY() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.LIST_ALBUM_TIME;
        return PhotosMetricsConstants.LIST_ALBUM_TIME;
    }
}
