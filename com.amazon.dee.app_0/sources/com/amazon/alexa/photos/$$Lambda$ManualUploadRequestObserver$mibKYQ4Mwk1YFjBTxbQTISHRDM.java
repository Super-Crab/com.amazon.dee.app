package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$mibKYQ4M-wk1YFjBTxbQTISHRDM  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$mibKYQ4Mwk1YFjBTxbQTISHRDM implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$mibKYQ4Mwk1YFjBTxbQTISHRDM INSTANCE = new $$Lambda$ManualUploadRequestObserver$mibKYQ4Mwk1YFjBTxbQTISHRDM();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$mibKYQ4Mwk1YFjBTxbQTISHRDM() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_V2_REQUEST_SUCCESS;
        return PhotosMetricsConstants.UPLOAD_V2_REQUEST_SUCCESS;
    }
}
