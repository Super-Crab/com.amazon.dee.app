package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$UploadOperation$WcLarJRBN9rMH8IqH1DNK73xubw  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$UploadOperation$WcLarJRBN9rMH8IqH1DNK73xubw implements MetricName {
    public static final /* synthetic */ $$Lambda$UploadOperation$WcLarJRBN9rMH8IqH1DNK73xubw INSTANCE = new $$Lambda$UploadOperation$WcLarJRBN9rMH8IqH1DNK73xubw();

    private /* synthetic */ $$Lambda$UploadOperation$WcLarJRBN9rMH8IqH1DNK73xubw() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        return PhotosMetricsConstants.INVALID_FILE_EXCEPTION;
    }
}
