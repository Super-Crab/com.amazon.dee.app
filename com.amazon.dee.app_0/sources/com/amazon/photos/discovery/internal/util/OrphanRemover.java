package com.amazon.photos.discovery.internal.util;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: OrphanRemover.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/OrphanRemover;", "", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/photos/discovery/internal/dao/WorkerDao;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "fixOrphanedLocalItems", "", "removeOrphanedEntries", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class OrphanRemover {
    private final Metrics metrics;
    private final WorkerDao workerDao;

    public OrphanRemover(@NotNull WorkerDao workerDao, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(workerDao, "workerDao");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.workerDao = workerDao;
        this.metrics = metrics;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fixOrphanedLocalItems() {
        Metrics metrics = this.metrics;
        try {
            List<MutableLocalItem> orphanedLocalItems = this.workerDao.getOrphanedLocalItems();
            if (orphanedLocalItems.isEmpty()) {
                return;
            }
            LocalItemUtils.INSTANCE.updateUnifiedIds(orphanedLocalItems, this.workerDao.insertUnified(UnifiedEntryUtils.INSTANCE.createUnifiedEntries(orphanedLocalItems)));
            Integer.valueOf(this.workerDao.updateLocal(orphanedLocalItems));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_FIX_ORPHANS, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
    }

    public final void removeOrphanedEntries() {
        Metrics metrics = this.metrics;
        try {
            this.workerDao.executeTransaction(new OrphanRemover$removeOrphanedEntries$$inlined$catchDb$lambda$1(this));
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_REMOVE_ORPHANS, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
    }
}
