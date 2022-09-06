package com.amazon.photos.discovery.dao;

import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DedupeDao.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000bJ\u0014\u0010\u000f\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bJ)\u0010\u0010\u001a\u00020\r2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r0\u0012J\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u0017J\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u0017J\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0017J\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0017J\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0017J\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0017J\u0014\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0017J\u0014\u0010'\u001a\u00020$2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0017J\u001c\u0010*\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010+\u001a\u00020\tJ\"\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\t2\b\u0010.\u001a\u0004\u0018\u00010\u001d2\b\u0010/\u001a\u0004\u0018\u00010\u001dJ\u0018\u00100\u001a\u00020\r2\u0006\u0010-\u001a\u00020\t2\b\u0010.\u001a\u0004\u0018\u00010\u001dJ\u001c\u00101\u001a\u00020\r2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010+\u001a\u00020\tJ\u0018\u00102\u001a\u00020\r2\u0006\u0010-\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u00010\u001dJ\u000e\u00103\u001a\u00020\r2\u0006\u00104\u001a\u00020&J\u0014\u00105\u001a\u00020\r2\f\u00106\u001a\b\u0012\u0004\u0012\u00020&0\u000bJ\u0014\u00107\u001a\u00020\r2\f\u00108\u001a\b\u0012\u0004\u0012\u00020)0\u0017J\u000e\u00109\u001a\u00020\r2\u0006\u00108\u001a\u00020)R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/amazon/photos/discovery/dao/DedupeDao;", "", "editDao", "Lcom/amazon/photos/discovery/dao/EditDao;", "dedupeStages", "", "", "(Lcom/amazon/photos/discovery/dao/EditDao;Ljava/util/Set;)V", "combineByUnifiedIds", "", "unifiedIds", "", "deleteCloudByCloudIds", "", "cloudIds", "deleteUnifiedByUnifiedIds", "executeTransaction", "transaction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "dedupeDao", "getUnifiedByCloudIds", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "getUnifiedByLocalIds", "localIds", "getUnifiedByMd5s", "md5s", "", "getUnifiedByNodeIds", "nodeIds", "getUnifiedByUnifiedIds", "getUnifiedByVisualDigests", "visualDigests", "insertCloudOrIgnore", "", "mutableCloudItems", "Lcom/amazon/photos/discovery/internal/model/MutableCloudItem;", "insertUnified", "mutableUnifiedEntries", "Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;", "updateCloudUnifiedId", "unifiedId", "updateLocalDigest", "localId", SierraContentProviderContract.MD5_VALUE, "visualDigest", "updateLocalMd5", "updateLocalUnifiedId", "updateLocalVisualDigest", "updateMutableCloudItem", "cloudItem", "updateMutableCloudItems", "cloudItems", "updateMutableUnifiedEntries", "unifiedEntry", "updateMutableUnifiedEntry", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public final class DedupeDao {
    private final Set<Integer> dedupeStages;
    private final EditDao editDao;

    public DedupeDao(@NotNull EditDao editDao, @NotNull Set<Integer> dedupeStages) {
        Intrinsics.checkParameterIsNotNull(editDao, "editDao");
        Intrinsics.checkParameterIsNotNull(dedupeStages, "dedupeStages");
        this.editDao = editDao;
        this.dedupeStages = dedupeStages;
    }

    public final long combineByUnifiedIds(@NotNull Collection<Long> unifiedIds) {
        Intrinsics.checkParameterIsNotNull(unifiedIds, "unifiedIds");
        return this.editDao.combineByUnifiedIds(unifiedIds);
    }

    public final void deleteCloudByCloudIds(@NotNull Collection<Long> cloudIds) {
        Intrinsics.checkParameterIsNotNull(cloudIds, "cloudIds");
        this.editDao.deleteCloudByCloudIds(cloudIds);
    }

    public final void deleteUnifiedByUnifiedIds(@NotNull Collection<Long> unifiedIds) {
        Intrinsics.checkParameterIsNotNull(unifiedIds, "unifiedIds");
        this.editDao.deleteUnifiedByUnifiedIds(unifiedIds);
    }

    public final void executeTransaction(@NotNull Function1<? super DedupeDao, Unit> transaction) {
        Intrinsics.checkParameterIsNotNull(transaction, "transaction");
        this.editDao.executeTransaction(new DedupeDao$executeTransaction$1(this, transaction));
    }

    @NotNull
    public final List<UnifiedItem> getUnifiedByCloudIds(@NotNull List<Long> cloudIds) {
        Intrinsics.checkParameterIsNotNull(cloudIds, "cloudIds");
        return this.editDao.getUnifiedByCloudIds(cloudIds, this.dedupeStages);
    }

    @NotNull
    public final List<UnifiedItem> getUnifiedByLocalIds(@NotNull List<Long> localIds) {
        Intrinsics.checkParameterIsNotNull(localIds, "localIds");
        return this.editDao.getUnifiedByLocalIds(localIds, this.dedupeStages);
    }

    @NotNull
    public final List<UnifiedItem> getUnifiedByMd5s(@NotNull List<String> md5s) {
        Intrinsics.checkParameterIsNotNull(md5s, "md5s");
        return this.editDao.getUnifiedByMd5s(md5s, this.dedupeStages);
    }

    @NotNull
    public final List<UnifiedItem> getUnifiedByNodeIds(@NotNull List<String> nodeIds) {
        Intrinsics.checkParameterIsNotNull(nodeIds, "nodeIds");
        return this.editDao.getUnifiedByNodeIds(nodeIds, this.dedupeStages);
    }

    @NotNull
    public final List<UnifiedItem> getUnifiedByUnifiedIds(@NotNull List<Long> unifiedIds) {
        Intrinsics.checkParameterIsNotNull(unifiedIds, "unifiedIds");
        return this.editDao.getUnifiedByUnifiedIds(unifiedIds, this.dedupeStages);
    }

    @NotNull
    public final List<UnifiedItem> getUnifiedByVisualDigests(@NotNull List<String> visualDigests) {
        Intrinsics.checkParameterIsNotNull(visualDigests, "visualDigests");
        return this.editDao.getUnifiedByVisualDigests(visualDigests, this.dedupeStages);
    }

    @NotNull
    public final long[] insertCloudOrIgnore(@NotNull List<MutableCloudItem> mutableCloudItems) {
        Intrinsics.checkParameterIsNotNull(mutableCloudItems, "mutableCloudItems");
        return this.editDao.insertCloudOrIgnore(mutableCloudItems);
    }

    @NotNull
    public final long[] insertUnified(@NotNull List<MutableUnifiedEntry> mutableUnifiedEntries) {
        Intrinsics.checkParameterIsNotNull(mutableUnifiedEntries, "mutableUnifiedEntries");
        return this.editDao.insertUnified$AndroidPhotosDiscovery_release(mutableUnifiedEntries);
    }

    public final void updateCloudUnifiedId(@NotNull Collection<Long> cloudIds, long j) {
        Intrinsics.checkParameterIsNotNull(cloudIds, "cloudIds");
        this.editDao.updateCloudUnifiedId(cloudIds, j);
    }

    public final void updateLocalDigest(long j, @Nullable String str, @Nullable String str2) {
        this.editDao.updateLocalDigest$AndroidPhotosDiscovery_release(j, str, str2);
    }

    public final void updateLocalMd5(long j, @Nullable String str) {
        this.editDao.updateLocalMd5$AndroidPhotosDiscovery_release(j, str);
    }

    public final void updateLocalUnifiedId(@NotNull Collection<Long> localIds, long j) {
        Intrinsics.checkParameterIsNotNull(localIds, "localIds");
        this.editDao.updateLocalUnifiedId$AndroidPhotosDiscovery_release(localIds, j);
    }

    public final void updateLocalVisualDigest(long j, @Nullable String str) {
        this.editDao.updateLocalVisualDigest$AndroidPhotosDiscovery_release(j, str);
    }

    public final void updateMutableCloudItem(@NotNull MutableCloudItem cloudItem) {
        Intrinsics.checkParameterIsNotNull(cloudItem, "cloudItem");
        this.editDao.updateMutableCloudItem(cloudItem);
    }

    public final void updateMutableCloudItems(@NotNull Collection<MutableCloudItem> cloudItems) {
        Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
        this.editDao.updateMutableCloudItems(cloudItems);
    }

    public final void updateMutableUnifiedEntries(@NotNull List<MutableUnifiedEntry> unifiedEntry) {
        Intrinsics.checkParameterIsNotNull(unifiedEntry, "unifiedEntry");
        this.editDao.updateMutableUnifiedEntries$AndroidPhotosDiscovery_release(unifiedEntry);
    }

    public final void updateMutableUnifiedEntry(@NotNull MutableUnifiedEntry unifiedEntry) {
        Intrinsics.checkParameterIsNotNull(unifiedEntry, "unifiedEntry");
        this.editDao.updateMutableUnifiedEntry$AndroidPhotosDiscovery_release(unifiedEntry);
    }
}
