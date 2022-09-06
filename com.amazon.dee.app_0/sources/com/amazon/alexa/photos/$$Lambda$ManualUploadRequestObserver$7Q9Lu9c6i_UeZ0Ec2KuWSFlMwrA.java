package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$7Q9Lu9c6i_UeZ0Ec2KuWSFlMwrA  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$ManualUploadRequestObserver$7Q9Lu9c6i_UeZ0Ec2KuWSFlMwrA implements MetricName {
    public static final /* synthetic */ $$Lambda$ManualUploadRequestObserver$7Q9Lu9c6i_UeZ0Ec2KuWSFlMwrA INSTANCE = new $$Lambda$ManualUploadRequestObserver$7Q9Lu9c6i_UeZ0Ec2KuWSFlMwrA();

    private /* synthetic */ $$Lambda$ManualUploadRequestObserver$7Q9Lu9c6i_UeZ0Ec2KuWSFlMwrA() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        String unused;
        unused = PhotosMetricsConstants.MAKE_UPLOAD_LONG_RUNNING_FAILED_NO_NOTIFICATION_MANAGER;
        return PhotosMetricsConstants.MAKE_UPLOAD_LONG_RUNNING_FAILED_NO_NOTIFICATION_MANAGER;
    }
}
