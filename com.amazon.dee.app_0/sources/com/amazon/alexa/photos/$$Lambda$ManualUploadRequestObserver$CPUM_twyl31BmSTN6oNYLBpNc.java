package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$CPUM--_twyl31BmSTN6oNYLBpNc  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$CPUM_twyl31BmSTN6oNYLBpNc implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$CPUM_twyl31BmSTN6oNYLBpNc INSTANCE = new $$Lambda$ManualUploadRequestObserver$CPUM_twyl31BmSTN6oNYLBpNc();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$CPUM_twyl31BmSTN6oNYLBpNc() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_PROGRESS_STARTED;
        return PhotosMetricsConstants.UPLOAD_V2_PROGRESS_STARTED;
    }
}
