package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$ZtLu-ZYDGc3T_W7F75pfQaYV00k  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$ZtLuZYDGc3T_W7F75pfQaYV00k implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$ZtLuZYDGc3T_W7F75pfQaYV00k INSTANCE = new $$Lambda$ManualUploadRequestObserver$ZtLuZYDGc3T_W7F75pfQaYV00k();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$ZtLuZYDGc3T_W7F75pfQaYV00k() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.MD5_CONFLICT_FAILED_TO_ADD_TO_ALBUM;
        return PhotosMetricsConstants.MD5_CONFLICT_FAILED_TO_ADD_TO_ALBUM;
    }
}
