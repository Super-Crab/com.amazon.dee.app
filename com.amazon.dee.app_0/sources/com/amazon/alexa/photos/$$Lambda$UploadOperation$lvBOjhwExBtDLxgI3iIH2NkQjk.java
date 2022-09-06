package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$UploadOperation$lvBOjh-wExBtDLxgI3iIH2NkQjk  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$UploadOperation$lvBOjhwExBtDLxgI3iIH2NkQjk implements MetricName {
    public static final /* synthetic */ $$Lambda$UploadOperation$lvBOjhwExBtDLxgI3iIH2NkQjk INSTANCE = new $$Lambda$UploadOperation$lvBOjhwExBtDLxgI3iIH2NkQjk();

    private /* synthetic */ $$Lambda$UploadOperation$lvBOjhwExBtDLxgI3iIH2NkQjk() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        return PhotosMetricsConstants.FILE_NOT_FOUND_ERROR;
    }
}
