package com.amazon.photos.discovery.internal.util;

import com.amazon.photos.discovery.internal.dao.WorkerDao;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OrphanRemover.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "dao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "invoke", "com/amazon/photos/discovery/internal/util/OrphanRemover$removeOrphanedEntries$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class OrphanRemover$removeOrphanedEntries$$inlined$catchDb$lambda$1 extends Lambda implements Function1<WorkerDao, Unit> {
    final /* synthetic */ OrphanRemover this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OrphanRemover$removeOrphanedEntries$$inlined$catchDb$lambda$1(OrphanRemover orphanRemover) {
        super(1);
        this.this$0 = orphanRemover;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(WorkerDao workerDao) {
        invoke2(workerDao);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull WorkerDao dao) {
        Intrinsics.checkParameterIsNotNull(dao, "dao");
        this.this$0.fixOrphanedLocalItems();
        dao.deleteOrphanedUnifiedItems();
        dao.deleteOrphanedCloudItems();
    }
}
