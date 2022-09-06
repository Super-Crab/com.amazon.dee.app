package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$qtIysqRVI7ZUGg7NtEGA_NTyp3s  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$qtIysqRVI7ZUGg7NtEGA_NTyp3s implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$qtIysqRVI7ZUGg7NtEGA_NTyp3s INSTANCE = new $$Lambda$ManualUploadRequestObserver$qtIysqRVI7ZUGg7NtEGA_NTyp3s();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$qtIysqRVI7ZUGg7NtEGA_NTyp3s() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUESTS_COMPLETED;
        return PhotosMetricsConstants.UPLOAD_V2_REQUESTS_COMPLETED;
    }
}
