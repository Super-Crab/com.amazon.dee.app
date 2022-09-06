package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$UploadOperation$Nq6VkiAg3CE4BmjPdSl73Fb10Nw  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$UploadOperation$Nq6VkiAg3CE4BmjPdSl73Fb10Nw implements MetricName {
    public static final /* synthetic */ $$Lambda$UploadOperation$Nq6VkiAg3CE4BmjPdSl73Fb10Nw INSTANCE = new $$Lambda$UploadOperation$Nq6VkiAg3CE4BmjPdSl73Fb10Nw();

    private /* synthetic */ $$Lambda$UploadOperation$Nq6VkiAg3CE4BmjPdSl73Fb10Nw() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        return PhotosMetricsConstants.UPLOAD_FATAL_ERROR;
    }
}
