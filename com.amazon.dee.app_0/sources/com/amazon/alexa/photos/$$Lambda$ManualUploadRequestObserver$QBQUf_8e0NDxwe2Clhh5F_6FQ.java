package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$QBQU-f_8e0NDxwe2Clh-h5F_6FQ  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$QBQUf_8e0NDxwe2Clhh5F_6FQ implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$QBQUf_8e0NDxwe2Clhh5F_6FQ INSTANCE = new $$Lambda$ManualUploadRequestObserver$QBQUf_8e0NDxwe2Clhh5F_6FQ();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$QBQUf_8e0NDxwe2Clhh5F_6FQ() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.LIST_NODES_RESPONSE_COUNT_V2;
        return PhotosMetricsConstants.LIST_NODES_RESPONSE_COUNT_V2;
    }
}
