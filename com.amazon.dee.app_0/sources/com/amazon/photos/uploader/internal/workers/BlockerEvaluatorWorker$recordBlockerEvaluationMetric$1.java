package com.amazon.photos.uploader.internal.workers;

import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: BlockerEvaluatorWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class BlockerEvaluatorWorker$recordBlockerEvaluationMetric$1 implements MetricName {
    public static final BlockerEvaluatorWorker$recordBlockerEvaluationMetric$1 INSTANCE = new BlockerEvaluatorWorker$recordBlockerEvaluationMetric$1();

    BlockerEvaluatorWorker$recordBlockerEvaluationMetric$1() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    @NotNull
    public final String getEventName() {
        return UploadMetrics.UPLOAD_BLOCKER_STATE_EVALUATION;
    }
}
