package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$ULdW_G8WhGI-Mymtc23hbVbOhF8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$ULdW_G8WhGIMymtc23hbVbOhF8 implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$ULdW_G8WhGIMymtc23hbVbOhF8 INSTANCE = new $$Lambda$ManualUploadRequestObserver$ULdW_G8WhGIMymtc23hbVbOhF8();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$ULdW_G8WhGIMymtc23hbVbOhF8() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.UPLOAD_BLOCKED_NO_NETWORK;
        return PhotosMetricsConstants.UPLOAD_BLOCKED_NO_NETWORK;
    }
}
