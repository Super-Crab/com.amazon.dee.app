package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$mjbZ-_9FMqLfNVbyXD7Sk4QSc9Q  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$mjbZ_9FMqLfNVbyXD7Sk4QSc9Q implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$mjbZ_9FMqLfNVbyXD7Sk4QSc9Q INSTANCE = new $$Lambda$ManualUploadRequestObserver$mjbZ_9FMqLfNVbyXD7Sk4QSc9Q();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$mjbZ_9FMqLfNVbyXD7Sk4QSc9Q() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.LIST_NODES_TIME;
        return PhotosMetricsConstants.LIST_NODES_TIME;
    }
}
