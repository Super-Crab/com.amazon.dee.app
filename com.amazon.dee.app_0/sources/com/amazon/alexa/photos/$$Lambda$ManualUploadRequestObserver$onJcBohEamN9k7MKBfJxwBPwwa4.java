package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$onJcBohEamN9k7MKBfJxwBPwwa4  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$onJcBohEamN9k7MKBfJxwBPwwa4 implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$onJcBohEamN9k7MKBfJxwBPwwa4 INSTANCE = new $$Lambda$ManualUploadRequestObserver$onJcBohEamN9k7MKBfJxwBPwwa4();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$onJcBohEamN9k7MKBfJxwBPwwa4() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_NOTIFICATION_WITHOUT_AUTOSAVE;
        return PhotosMetricsConstants.UPLOAD_NOTIFICATION_WITHOUT_AUTOSAVE;
    }
}
