package com.amazon.photos.discovery.internal.worker;

import com.amazon.photos.discovery.dedupe.DedupeStage;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: DedupeWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class DedupeWorker$terminatingStageId$2 extends Lambda implements Function0<Integer> {
    final /* synthetic */ DedupeWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DedupeWorker$terminatingStageId$2(DedupeWorker dedupeWorker) {
        super(0);
        this.this$0 = dedupeWorker;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Integer mo12560invoke() {
        return Integer.valueOf(mo12560invoke());
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [int, java.lang.Integer] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Integer mo12560invoke() {
        DedupeStage dedupeStage = (DedupeStage) CollectionsKt.lastOrNull((List<? extends Object>) this.this$0.getDedupeStages());
        if (dedupeStage != null) {
            return dedupeStage.getStageId();
        }
        return -1;
    }
}
