package com.amazon.photos.uploader.cds.multipart;

import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.photos.uploader.cds.CdsMetrics;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: PartUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class PartUploader$handlePartFailure$clientMetric$1 implements MetricName {
    public static final PartUploader$handlePartFailure$clientMetric$1 INSTANCE = new PartUploader$handlePartFailure$clientMetric$1();

    PartUploader$handlePartFailure$clientMetric$1() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    @NotNull
    public final String getEventName() {
        return CdsMetrics.PART_UPLOAD_EXCEPTION;
    }
}
