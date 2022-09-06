package com.amazon.photos.discovery.internal.worker;

import androidx.work.ListenableWorker;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: DedupeWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"noStage", "Landroidx/work/ListenableWorker$Result;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class DedupeWorker$mainTask$1 extends Lambda implements Function0<ListenableWorker.Result> {
    final /* synthetic */ DedupeWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DedupeWorker$mainTask$1(DedupeWorker dedupeWorker) {
        super(0);
        this.this$0 = dedupeWorker;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final ListenableWorker.Result mo12560invoke() {
        int i;
        Logger logger = this.this$0.getLogger();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Specified de-duplication stage not found: ");
        i = this.this$0.paramStageId;
        outline107.append(i);
        logger.e("DedupeWorker", outline107.toString());
        this.this$0.getMetrics().recordSimpleEvent("DedupeWorker", DiscoveryMetrics.DedupeStageNotFound, MetricRecordingType.STANDARD);
        ListenableWorker.Result failure = ListenableWorker.Result.failure();
        Intrinsics.checkExpressionValueIsNotNull(failure, "Result.failure()");
        return failure;
    }
}
