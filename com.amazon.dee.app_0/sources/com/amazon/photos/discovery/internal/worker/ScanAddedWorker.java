package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.DiscoveryOperationsKt;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.LocalItemUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.RetryException;
import com.amazon.photos.discovery.internal.util.RetryableOperationUtil;
import com.amazon.photos.discovery.internal.util.UnifiedEntryUtils;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import com.amazon.photos.discovery.internal.worker.ScanAddedWorker;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.FolderType;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.observers.LocalContentType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.audio.WavUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002jkB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010R\u001a\b\u0012\u0004\u0012\u00020T0S2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0SH\u0002J\u0010\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020YH\u0002J&\u0010Z\u001a\u00020\u001a2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020V0\\2\u0006\u0010X\u001a\u00020Y2\u0006\u0010]\u001a\u00020\u001aH\u0002J\b\u0010^\u001a\u000205H\u0014J*\u0010_\u001a\b\u0012\u0004\u0012\u00020T0S2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0S2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020T0SH\u0002J*\u0010a\u001a\b\u0012\u0004\u0012\u00020V0S2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020V0\\2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0SH\u0002J\u0006\u0010c\u001a\u00020\u001aJ\b\u0010d\u001a\u00020eH\u0014J\b\u0010f\u001a\u00020gH\u0014J$\u0010h\u001a\u00020i2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020V0\\2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0SH\u0002R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u001c8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001e\u0010(\u001a\u00020)8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001e\u0010.\u001a\u00020/8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u00104\u001a\u0002058\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010:\u001a\u00020;8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001e\u0010@\u001a\u00020A8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001e\u0010F\u001a\u00020G8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001e\u0010L\u001a\u00020M8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010Q¨\u0006l"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ScanAddedWorker;", "Lcom/amazon/photos/discovery/internal/worker/BaseWorker;", "context", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "getConfiguration$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "setConfiguration$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "contentChangeNotifier", "Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "getContentChangeNotifier$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "setContentChangeNotifier$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;)V", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver$AndroidPhotosDiscovery_release", "()Landroid/content/ContentResolver;", "setContentResolver$AndroidPhotosDiscovery_release", "(Landroid/content/ContentResolver;)V", "enqueuedTime", "", "filterEvents", "Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;", "getFilterEvents$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;", "setFilterEvents$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;)V", "fullScan", "", "injectMethod", "Lkotlin/Function0;", "", "getInjectMethod", "()Lkotlin/jvm/functions/Function0;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "getMediaStoreUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "setMediaStoreUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences$AndroidPhotosDiscovery_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$AndroidPhotosDiscovery_release", "(Landroid/content/SharedPreferences;)V", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "getSystemUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "setSystemUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;)V", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "getWorkerDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "setWorkerDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dao/WorkerDao;)V", "workerHelper", "Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "getWorkerHelper$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "setWorkerHelper$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/WorkerHelper;)V", "getExistingFolders", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "msItems", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "getLastAddedRowIdFromType", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "getLatestRowIdAddedByType", "batch", "", "originalValue", "getMetricsObj", "getNewFolders", "existingFolders", "getNewItems", "dbItems", "getNow", "getTag", "", "mainTask", "Landroidx/work/ListenableWorker$Result;", "processBatch", "", "PersistItemsTransaction", "ScanAddedOperations", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ScanAddedWorker extends BaseWorker {
    @Inject
    @NotNull
    public DiscoveryConfiguration configuration;
    @Inject
    @NotNull
    public ContentChangeNotifier contentChangeNotifier;
    @Inject
    @NotNull
    public ContentResolver contentResolver;
    private final Context context;
    private final long enqueuedTime;
    @Inject
    @NotNull
    public FilterEvents filterEvents;
    private final boolean fullScan;
    @NotNull
    private final Function0<Unit> injectMethod;
    @Inject
    @NotNull
    public Logger logger;
    @Inject
    @NotNull
    public MediaStoreUtil mediaStoreUtil;
    @Inject
    @NotNull
    public Metrics metrics;
    @Inject
    @NotNull
    public SharedPreferences sharedPreferences;
    @Inject
    @NotNull
    public SystemUtil systemUtil;
    @Inject
    @NotNull
    public WorkerDao workerDao;
    @Inject
    @NotNull
    public WorkerHelper workerHelper;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScanAddedWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BK\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0002H\u0096\u0002R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ScanAddedWorker$PersistItemsTransaction;", "Lkotlin/Function1;", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "folders", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "addedFolderIds", "", "", "addedLocalIds", "addedUnifiedIds", "(Lcom/amazon/photos/discovery/internal/worker/ScanAddedWorker;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getAddedFolderIds", "()Ljava/util/List;", "getAddedLocalIds", "getAddedUnifiedIds", "getFolders", "getItems", "numItemsAdded", "", "getNumItemsAdded", "()I", "setNumItemsAdded", "(I)V", "invoke", "workerDao", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class PersistItemsTransaction implements Function1<WorkerDao, Unit> {
        @NotNull
        private final List<Long> addedFolderIds;
        @NotNull
        private final List<Long> addedLocalIds;
        @NotNull
        private final List<Long> addedUnifiedIds;
        @NotNull
        private final List<MutableLocalFolder> folders;
        @NotNull
        private final List<MutableLocalItem> items;
        private int numItemsAdded;
        final /* synthetic */ ScanAddedWorker this$0;

        public PersistItemsTransaction(@NotNull ScanAddedWorker scanAddedWorker, @NotNull List<MutableLocalItem> items, @NotNull List<MutableLocalFolder> folders, @NotNull List<Long> addedFolderIds, @NotNull List<Long> addedLocalIds, List<Long> addedUnifiedIds) {
            Intrinsics.checkParameterIsNotNull(items, "items");
            Intrinsics.checkParameterIsNotNull(folders, "folders");
            Intrinsics.checkParameterIsNotNull(addedFolderIds, "addedFolderIds");
            Intrinsics.checkParameterIsNotNull(addedLocalIds, "addedLocalIds");
            Intrinsics.checkParameterIsNotNull(addedUnifiedIds, "addedUnifiedIds");
            this.this$0 = scanAddedWorker;
            this.items = items;
            this.folders = folders;
            this.addedFolderIds = addedFolderIds;
            this.addedLocalIds = addedLocalIds;
            this.addedUnifiedIds = addedUnifiedIds;
        }

        @NotNull
        public final List<Long> getAddedFolderIds() {
            return this.addedFolderIds;
        }

        @NotNull
        public final List<Long> getAddedLocalIds() {
            return this.addedLocalIds;
        }

        @NotNull
        public final List<Long> getAddedUnifiedIds() {
            return this.addedUnifiedIds;
        }

        @NotNull
        public final List<MutableLocalFolder> getFolders() {
            return this.folders;
        }

        @NotNull
        public final List<MutableLocalItem> getItems() {
            return this.items;
        }

        public final int getNumItemsAdded() {
            return this.numItemsAdded;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(WorkerDao workerDao) {
            invoke2(workerDao);
            return Unit.INSTANCE;
        }

        public final void setNumItemsAdded(int i) {
            this.numItemsAdded = i;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@NotNull WorkerDao workerDao) {
            List<Long> list;
            List<Long> list2;
            List<Pair> zip;
            List<Long> list3;
            Integer num;
            List<Long> list4;
            List<Long> list5;
            Intrinsics.checkParameterIsNotNull(workerDao, "workerDao");
            Metrics metrics$AndroidPhotosDiscovery_release = this.this$0.getMetrics$AndroidPhotosDiscovery_release();
            try {
                list = workerDao.insertFoldersOrIgnore(this.folders);
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_INSERT_FOLDERS, metrics$AndroidPhotosDiscovery_release, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
                list = null;
            }
            if (list != null) {
                this.addedFolderIds.addAll(list);
                Metrics metrics$AndroidPhotosDiscovery_release2 = this.this$0.getMetrics$AndroidPhotosDiscovery_release();
                try {
                    list5 = ArraysKt___ArraysKt.toList(workerDao.insertLocalOrIgnore(this.items));
                    list2 = list5;
                } catch (Exception e2) {
                    GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_INSERT_LOCAL, metrics$AndroidPhotosDiscovery_release2, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e2);
                    list2 = null;
                }
                if (list2 != null) {
                    this.addedLocalIds.addAll(list2);
                    zip = CollectionsKt___CollectionsKt.zip(list2, this.items);
                    ArrayList arrayList = new ArrayList();
                    for (Pair pair : zip) {
                        MutableLocalItem copy$default = ((Number) pair.getFirst()).longValue() > 0 ? MutableLocalItem.copy$default((MutableLocalItem) pair.getSecond(), ((Number) pair.getFirst()).longValue(), 0L, null, null, null, null, null, null, 0L, 0L, 0L, 0L, 0L, null, null, 0L, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE, null) : null;
                        if (copy$default != null) {
                            arrayList.add(copy$default);
                        }
                    }
                    Metrics metrics$AndroidPhotosDiscovery_release3 = this.this$0.getMetrics$AndroidPhotosDiscovery_release();
                    try {
                        list4 = ArraysKt___ArraysKt.toList(workerDao.insertUnified(UnifiedEntryUtils.INSTANCE.createUnifiedEntries(arrayList)));
                        list3 = list4;
                    } catch (Exception e3) {
                        GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_INSERT_UNIFIED, metrics$AndroidPhotosDiscovery_release3, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e3);
                        list3 = null;
                    }
                    if (list3 != null) {
                        LocalItemUtils.INSTANCE.updateUnifiedIds(arrayList, list3);
                        Metrics metrics$AndroidPhotosDiscovery_release4 = this.this$0.getMetrics$AndroidPhotosDiscovery_release();
                        try {
                            num = Integer.valueOf(workerDao.updateLocal(arrayList));
                        } catch (Exception e4) {
                            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_UPDATE_LOCAL, metrics$AndroidPhotosDiscovery_release4, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e4);
                            num = null;
                        }
                        if (num != null) {
                            num.intValue();
                            this.addedUnifiedIds.addAll(list3);
                            this.numItemsAdded = list2.size();
                            return;
                        }
                        throw new RetryException(null, 1, null);
                    }
                    throw new RetryException(null, 1, null);
                }
                throw new RetryException(null, 1, null);
            }
            throw new RetryException(null, 1, null);
        }
    }

    /* compiled from: ScanAddedWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00022\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J\b\u0010!\u001a\u00020\u0002H\u0016J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#H\u0016J\u001e\u0010$\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001fH\u0002J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\u0002H\u0016J\b\u0010-\u001a\u00020\u0002H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u000e¨\u0006."}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ScanAddedWorker$ScanAddedOperations;", "Lcom/amazon/photos/discovery/internal/worker/TraverseOperations;", "Landroidx/work/ListenableWorker$Result;", "lastSeenPhotoDateAddedRowId", "", "lastSeenVideoDateAddedRowId", "itemSource", "Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "(Lcom/amazon/photos/discovery/internal/worker/ScanAddedWorker;JJLcom/amazon/photos/discovery/internal/worker/ItemSource;)V", "getItemSource", "()Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "getLastSeenPhotoDateAddedRowId", "()J", "setLastSeenPhotoDateAddedRowId", "(J)V", "getLastSeenVideoDateAddedRowId", "setLastSeenVideoDateAddedRowId", "numItemsAdded", "", "getNumItemsAdded", "()I", "setNumItemsAdded", "(I)V", "numItemsProcessed", "getNumItemsProcessed", "setNumItemsProcessed", "workStartTimeMs", "getWorkStartTimeMs", "setWorkStartTimeMs", "batchOperation", "batch", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "completed", "fetchOneBatch", "Lcom/amazon/photos/discovery/internal/worker/FetchResult;", "getLocalInGivenPathsWithRetry", "filePaths", "", "init", "", "recordMetrics", "executionTimeMetric", "Lcom/amazon/photos/discovery/metrics/DiscoveryMetrics;", "stopped", "successResult", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private final class ScanAddedOperations implements TraverseOperations<ListenableWorker.Result> {
        @NotNull
        private final ItemSource itemSource;
        private long lastSeenPhotoDateAddedRowId;
        private long lastSeenVideoDateAddedRowId;
        private int numItemsAdded;
        private int numItemsProcessed;
        final /* synthetic */ ScanAddedWorker this$0;
        private long workStartTimeMs;

        public ScanAddedOperations(ScanAddedWorker scanAddedWorker, long j, @NotNull long j2, ItemSource itemSource) {
            Intrinsics.checkParameterIsNotNull(itemSource, "itemSource");
            this.this$0 = scanAddedWorker;
            this.lastSeenPhotoDateAddedRowId = j;
            this.lastSeenVideoDateAddedRowId = j2;
            this.itemSource = itemSource;
        }

        private final List<MutableLocalItem> getLocalInGivenPathsWithRetry(final List<String> list) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = null;
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = null;
            RetryableOperationUtil.INSTANCE.runWithRetry(3, new RetryableOperationUtil.RetryableOperation() { // from class: com.amazon.photos.discovery.internal.worker.ScanAddedWorker$ScanAddedOperations$getLocalInGivenPathsWithRetry$1
                @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
                public void finalOperation(boolean z, int i) {
                    if (z) {
                        if (i >= 2) {
                            return;
                        }
                        ScanAddedWorker.ScanAddedOperations.this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddGetLocalByPathsDbRetried, MetricRecordingType.STANDARD);
                        return;
                    }
                    Exception exc = (Exception) objectRef2.element;
                    if (exc != null) {
                        ScanAddedWorker.ScanAddedOperations.this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddGetLocalByPathsDbError, exc);
                    } else {
                        ScanAddedWorker.ScanAddedOperations.this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddGetLocalByPathsDbError, MetricRecordingType.STANDARD);
                    }
                    objectRef.element = null;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
                public void handleException(@Nullable Exception exc, int i) {
                    objectRef2.element = exc;
                }

                /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List, T] */
                @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
                public void run() {
                    objectRef.element = ScanAddedWorker.ScanAddedOperations.this.this$0.getWorkerDao$AndroidPhotosDiscovery_release().getLocalInGivenPaths(list);
                }
            });
            return (List) objectRef.element;
        }

        private final void recordMetrics(DiscoveryMetrics discoveryMetrics) {
            ClientMetric withTagName = new ClientMetric().withTagName(ConstantsKt.SCAN_ADDED_WORKER);
            withTagName.addCounter(DiscoveryMetrics.ScanAddedWorkerProcessedItems, this.numItemsProcessed).addCounter(DiscoveryMetrics.ScanAddedWorkerDiscoveredItems, this.numItemsAdded).addTimer(discoveryMetrics, this.this$0.getSystemUtil$AndroidPhotosDiscovery_release().elapsedRealTimeMillis() - this.workStartTimeMs);
            this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordCustomMetric(ConstantsKt.SCAN_ADDED_WORKER, withTagName, MetricRecordingType.STANDARD);
            this.this$0.getWorkerHelper$AndroidPhotosDiscovery_release().recordWorkerLifetimeMetric(this.this$0.enqueuedTime, ConstantsKt.SCAN_ADDED_WORKER);
        }

        private final ListenableWorker.Result successResult() {
            ListenableWorker.Result success = ListenableWorker.Result.success(new Data.Builder().putInt(ScanAddedWorkerKt.RESULT_ADDED_ITEM_COUNT, this.numItemsAdded).build());
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success(Data.Buil…, numItemsAdded).build())");
            return success;
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        /* renamed from: batchOperation  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ListenableWorker.Result mo4358batchOperation(List list) {
            return mo4358batchOperation((List<MutableLocalItem>) list);
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        public FetchResult<ListenableWorker.Result> fetchOneBatch() {
            try {
                long now = this.this$0.getNow();
                FetchSuccess fetchSuccess = new FetchSuccess(ScanAddedWorkerUtilKt.fillBatch(this.itemSource, this.this$0.getConfiguration$AndroidPhotosDiscovery_release().getScanBatchSize()));
                this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleDuration(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.AddedScanFillBatchTime, this.this$0.getNow() - now);
                return fetchSuccess;
            } catch (Exception e) {
                this.this$0.getLogger$AndroidPhotosDiscovery_release().e(ConstantsKt.SCAN_ADDED_WORKER, "Failure scanning media store for additions.", e);
                this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddFetchBatchError, e);
                return new FetchFailure(ListenableWorker.Result.retry());
            }
        }

        @NotNull
        public final ItemSource getItemSource() {
            return this.itemSource;
        }

        public final long getLastSeenPhotoDateAddedRowId() {
            return this.lastSeenPhotoDateAddedRowId;
        }

        public final long getLastSeenVideoDateAddedRowId() {
            return this.lastSeenVideoDateAddedRowId;
        }

        public final int getNumItemsAdded() {
            return this.numItemsAdded;
        }

        public final int getNumItemsProcessed() {
            return this.numItemsProcessed;
        }

        public final long getWorkStartTimeMs() {
            return this.workStartTimeMs;
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        public void init() {
            this.this$0.getLogger$AndroidPhotosDiscovery_release().i(ConstantsKt.SCAN_ADDED_WORKER, "Scan start.");
            this.workStartTimeMs = this.this$0.getSystemUtil$AndroidPhotosDiscovery_release().elapsedRealTimeMillis();
            this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddedWorkerStarted, MetricRecordingType.STANDARD);
            this.numItemsAdded = 0;
            this.numItemsProcessed = 0;
        }

        public final void setLastSeenPhotoDateAddedRowId(long j) {
            this.lastSeenPhotoDateAddedRowId = j;
        }

        public final void setLastSeenVideoDateAddedRowId(long j) {
            this.lastSeenVideoDateAddedRowId = j;
        }

        public final void setNumItemsAdded(int i) {
            this.numItemsAdded = i;
        }

        public final void setNumItemsProcessed(int i) {
            this.numItemsProcessed = i;
        }

        public final void setWorkStartTimeMs(long j) {
            this.workStartTimeMs = j;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @Nullable
        /* renamed from: batchOperation */
        public ListenableWorker.Result mo4358batchOperation(@NotNull List<MutableLocalItem> batch) {
            int collectionSizeOrDefault;
            Intrinsics.checkParameterIsNotNull(batch, "batch");
            long now = this.this$0.getNow();
            Logger logger$AndroidPhotosDiscovery_release = this.this$0.getLogger$AndroidPhotosDiscovery_release();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Processing batch of ");
            outline107.append(batch.size());
            outline107.append(" items");
            logger$AndroidPhotosDiscovery_release.i(ConstantsKt.SCAN_ADDED_WORKER, outline107.toString());
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(batch, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (MutableLocalItem mutableLocalItem : batch) {
                arrayList.add(mutableLocalItem.getFilePath());
            }
            this.numItemsProcessed = arrayList.size() + this.numItemsProcessed;
            List<MutableLocalItem> localInGivenPathsWithRetry = getLocalInGivenPathsWithRetry(arrayList);
            if (localInGivenPathsWithRetry != null) {
                this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleDuration(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.AddedScanBatchGetLocalTime, this.this$0.getNow() - now);
                long now2 = this.this$0.getNow();
                try {
                    this.numItemsAdded += this.this$0.processBatch(localInGivenPathsWithRetry, batch);
                    this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleDuration(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.AddedScanBatchProcessTime, this.this$0.getNow() - now2);
                    this.lastSeenPhotoDateAddedRowId = this.this$0.getLatestRowIdAddedByType(batch, ItemType.PHOTO, this.lastSeenPhotoDateAddedRowId);
                    this.lastSeenVideoDateAddedRowId = this.this$0.getLatestRowIdAddedByType(batch, ItemType.VIDEO, this.lastSeenVideoDateAddedRowId);
                    this.this$0.getSharedPreferences$AndroidPhotosDiscovery_release().edit().putLong(ScanAddedWorkerKt.PREFERENCE_PHOTO_LAST_ADDED_ROW_ID, this.lastSeenPhotoDateAddedRowId).putLong(ScanAddedWorkerKt.PREFERENCE_VIDEO_LAST_ADDED_ROW_ID, this.lastSeenVideoDateAddedRowId).apply();
                    return null;
                } catch (RetryException e) {
                    this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddProcessBatchError, e);
                    return WorkerUtilKt.recordAndRetry(this.this$0.getMetrics$AndroidPhotosDiscovery_release(), ConstantsKt.SCAN_ADDED_WORKER);
                }
            }
            return WorkerUtilKt.recordAndRetry(this.this$0.getMetrics$AndroidPhotosDiscovery_release(), ConstantsKt.SCAN_ADDED_WORKER);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        /* renamed from: completed */
        public ListenableWorker.Result mo4359completed() {
            Logger logger$AndroidPhotosDiscovery_release = this.this$0.getLogger$AndroidPhotosDiscovery_release();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scan completed, added ");
            outline107.append(this.numItemsAdded);
            outline107.append(" new items.");
            logger$AndroidPhotosDiscovery_release.i(ConstantsKt.SCAN_ADDED_WORKER, outline107.toString());
            this.this$0.getFilterEvents$AndroidPhotosDiscovery_release().onMediaStoreScanComplete(this.lastSeenPhotoDateAddedRowId, this.lastSeenVideoDateAddedRowId);
            recordMetrics(DiscoveryMetrics.ScanAddedWorkerComplete);
            return successResult();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        /* renamed from: stopped */
        public ListenableWorker.Result mo4360stopped() {
            Logger logger$AndroidPhotosDiscovery_release = this.this$0.getLogger$AndroidPhotosDiscovery_release();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scan stopped, added ");
            outline107.append(this.numItemsAdded);
            outline107.append(" new items.");
            logger$AndroidPhotosDiscovery_release.i(ConstantsKt.SCAN_ADDED_WORKER, outline107.toString());
            recordMetrics(DiscoveryMetrics.ScanAddedWorkerStopped);
            return successResult();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanAddedWorker(@NotNull Context context, @NotNull WorkerParameters workerParameters) {
        super(context, workerParameters);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParameters, "workerParameters");
        this.context = context;
        this.enqueuedTime = workerParameters.getInputData().getLong(DiscoveryOperationsKt.ENQUEUED_TIME, 0L);
        this.fullScan = workerParameters.getInputData().getBoolean(DiscoveryOperationsKt.FULL_SCAN, false);
        this.injectMethod = new ScanAddedWorker$injectMethod$1(this);
    }

    private final List<MutableLocalFolder> getExistingFolders(List<MutableLocalItem> list) {
        int collectionSizeOrDefault;
        List<MutableLocalFolder> list2;
        List<MutableLocalFolder> emptyList;
        List list3;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalItem mutableLocalItem : list) {
            arrayList.add(Long.valueOf(mutableLocalItem.getParentId()));
        }
        linkedHashSet.addAll(arrayList);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            list3 = CollectionsKt___CollectionsKt.toList(linkedHashSet);
            list2 = workerDao.getFoldersByIds(list3);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_CHECK_FOLDERS_EXISTING, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
            list2 = null;
        }
        if (list2 != null) {
            return list2;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    private final long getLastAddedRowIdFromType(ItemType itemType) {
        long j;
        if (this.fullScan) {
            return 0L;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[itemType.ordinal()];
        if (i == 1) {
            SharedPreferences sharedPreferences = this.sharedPreferences;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            }
            j = sharedPreferences.getLong(ScanAddedWorkerKt.PREFERENCE_PHOTO_LAST_ADDED_ROW_ID, 0L);
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            SharedPreferences sharedPreferences2 = this.sharedPreferences;
            if (sharedPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            }
            j = sharedPreferences2.getLong(ScanAddedWorkerKt.PREFERENCE_VIDEO_LAST_ADDED_ROW_ID, 0L);
        }
        if (j <= 500) {
            return 0L;
        }
        return j - 500;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getLatestRowIdAddedByType(Collection<MutableLocalItem> collection, ItemType itemType, long j) {
        Object next;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (((MutableLocalItem) obj).getItemType() == itemType) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            next = null;
        } else {
            next = it2.next();
            if (it2.hasNext()) {
                long id = ((MutableLocalItem) next).getId();
                do {
                    Object next2 = it2.next();
                    long id2 = ((MutableLocalItem) next2).getId();
                    if (id < id2) {
                        next = next2;
                        id = id2;
                    }
                } while (it2.hasNext());
            }
        }
        MutableLocalItem mutableLocalItem = (MutableLocalItem) next;
        return Math.max(mutableLocalItem != null ? mutableLocalItem.getId() : 0L, j);
    }

    private final List<MutableLocalFolder> getNewFolders(List<MutableLocalItem> list, List<MutableLocalFolder> list2) {
        int collectionSizeOrDefault;
        List<MutableLocalFolder> emptyList;
        List<MutableLocalFolder> list3;
        int collectionSizeOrDefault2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalItem mutableLocalItem : list) {
            arrayList.add(Long.valueOf(mutableLocalItem.getParentId()));
        }
        linkedHashSet.addAll(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : linkedHashSet) {
            long longValue = ((Number) obj).longValue();
            collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10);
            ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault2);
            for (MutableLocalFolder mutableLocalFolder : list2) {
                arrayList3.add(Long.valueOf(mutableLocalFolder.getId()));
            }
            if (!arrayList3.contains(Long.valueOf(longValue))) {
                arrayList2.add(obj);
            }
        }
        try {
            ContentResolver contentResolver = this.contentResolver;
            if (contentResolver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
            }
            MediaStoreUtil mediaStoreUtil = this.mediaStoreUtil;
            if (mediaStoreUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
            }
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            list3 = CollectionsKt___CollectionsKt.toList(new LocalFoldersSource(contentResolver, mediaStoreUtil, metrics, arrayList2).getAllFolders());
            return list3;
        } catch (Exception e) {
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddFetchFolderError, e);
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
    }

    private final List<MutableLocalItem> getNewItems(Collection<MutableLocalItem> collection, List<MutableLocalItem> list) {
        boolean contains$default;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            MutableLocalItem mutableLocalItem = (MutableLocalItem) obj;
            boolean z = false;
            if (!(collection instanceof Collection) || !collection.isEmpty()) {
                Iterator<T> it2 = collection.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    contains$default = StringsKt__StringsKt.contains$default((CharSequence) ((MutableLocalItem) it2.next()).getFilePath(), (CharSequence) mutableLocalItem.getFilePath(), false, 2, (Object) null);
                    if (contains$default) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int processBatch(Collection<MutableLocalItem> collection, List<MutableLocalItem> list) throws RetryException {
        Set union;
        int i;
        int collectionSizeOrDefault;
        ArrayList arrayList;
        SystemUtil systemUtil;
        SystemUtil systemUtil2 = this.systemUtil;
        if (systemUtil2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        long elapsedRealTimeMillis = systemUtil2.elapsedRealTimeMillis();
        List<MutableLocalFolder> existingFolders = getExistingFolders(list);
        List<MutableLocalFolder> newFolders = getNewFolders(list, existingFolders);
        union = CollectionsKt___CollectionsKt.union(existingFolders, newFolders);
        ArrayList<MutableLocalFolder> arrayList2 = new ArrayList();
        Iterator it2 = union.iterator();
        while (true) {
            i = 0;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((MutableLocalFolder) next).getFolderType() == FolderType.CAMERA) {
                i = 1;
            }
            if (i != 0) {
                arrayList2.add(next);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalFolder mutableLocalFolder : arrayList2) {
            arrayList3.add(Long.valueOf(mutableLocalFolder.getId()));
        }
        List<MutableLocalItem> newItems = getNewItems(collection, list);
        DiscoveryConfiguration discoveryConfiguration = this.configuration;
        if (discoveryConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        }
        if (discoveryConfiguration.getOnlyScanCamera()) {
            ArrayList arrayList4 = new ArrayList();
            for (Object obj : newItems) {
                if (arrayList3.contains(Long.valueOf(((MutableLocalItem) obj).getParentId()))) {
                    arrayList4.add(obj);
                }
            }
            arrayList = arrayList4;
        } else {
            arrayList = newItems;
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            PersistItemsTransaction persistItemsTransaction = new PersistItemsTransaction(this, arrayList, newFolders, arrayList5, arrayList6, arrayList7);
            try {
                WorkerDao workerDao = this.workerDao;
                if (workerDao == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("workerDao");
                }
                workerDao.executeTransaction(persistItemsTransaction);
                i = persistItemsTransaction.getNumItemsAdded();
                if (!arrayList5.isEmpty()) {
                    ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
                    if (contentChangeNotifier == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
                    }
                    contentChangeNotifier.onContentAdded$AndroidPhotosDiscovery_release(LocalContentType.FOLDER, arrayList5);
                }
                if (!arrayList6.isEmpty()) {
                    ContentChangeNotifier contentChangeNotifier2 = this.contentChangeNotifier;
                    if (contentChangeNotifier2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
                    }
                    contentChangeNotifier2.onContentAdded$AndroidPhotosDiscovery_release(LocalContentType.LOCAL_ITEM, arrayList6);
                }
                if (!arrayList7.isEmpty()) {
                    ContentChangeNotifier contentChangeNotifier3 = this.contentChangeNotifier;
                    if (contentChangeNotifier3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
                    }
                    contentChangeNotifier3.onContentAdded$AndroidPhotosDiscovery_release(LocalContentType.UNIFIED_ITEM, arrayList7);
                }
            } catch (Exception e) {
                Metrics metrics = this.metrics;
                if (metrics == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics.recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetrics.ScanAddPersistItemsError, e);
                throw new RetryException(null, 1, null);
            }
        }
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        DiscoveryMetrics discoveryMetrics = DiscoveryMetrics.ScanAddedBatchComplete;
        if (this.systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        metrics2.recordSimpleDuration(ConstantsKt.SCAN_ADDED_WORKER, discoveryMetrics, systemUtil.elapsedRealTimeMillis() - elapsedRealTimeMillis);
        return i;
    }

    @NotNull
    public final DiscoveryConfiguration getConfiguration$AndroidPhotosDiscovery_release() {
        DiscoveryConfiguration discoveryConfiguration = this.configuration;
        if (discoveryConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        }
        return discoveryConfiguration;
    }

    @NotNull
    public final ContentChangeNotifier getContentChangeNotifier$AndroidPhotosDiscovery_release() {
        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
        if (contentChangeNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        return contentChangeNotifier;
    }

    @NotNull
    public final ContentResolver getContentResolver$AndroidPhotosDiscovery_release() {
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
        }
        return contentResolver;
    }

    @NotNull
    public final FilterEvents getFilterEvents$AndroidPhotosDiscovery_release() {
        FilterEvents filterEvents = this.filterEvents;
        if (filterEvents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterEvents");
        }
        return filterEvents;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected Function0<Unit> getInjectMethod() {
        return this.injectMethod;
    }

    @NotNull
    public final Logger getLogger$AndroidPhotosDiscovery_release() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return logger;
    }

    @NotNull
    public final MediaStoreUtil getMediaStoreUtil$AndroidPhotosDiscovery_release() {
        MediaStoreUtil mediaStoreUtil = this.mediaStoreUtil;
        if (mediaStoreUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
        }
        return mediaStoreUtil;
    }

    @NotNull
    public final Metrics getMetrics$AndroidPhotosDiscovery_release() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected Metrics getMetricsObj() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    public final long getNow() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil.elapsedRealTimeMillis();
    }

    @NotNull
    public final SharedPreferences getSharedPreferences$AndroidPhotosDiscovery_release() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences;
    }

    @NotNull
    public final SystemUtil getSystemUtil$AndroidPhotosDiscovery_release() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected String getTag() {
        return ConstantsKt.SCAN_ADDED_WORKER;
    }

    @NotNull
    public final WorkerDao getWorkerDao$AndroidPhotosDiscovery_release() {
        WorkerDao workerDao = this.workerDao;
        if (workerDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerDao");
        }
        return workerDao;
    }

    @NotNull
    public final WorkerHelper getWorkerHelper$AndroidPhotosDiscovery_release() {
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        return workerHelper;
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [long] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v5 */
    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected ListenableWorker.Result mainTask() {
        Throwable th;
        ImageSource imageSource;
        ContentResolver contentResolver;
        MediaStoreUtil mediaStoreUtil;
        Metrics metrics;
        DiscoveryConfiguration discoveryConfiguration;
        List listOf;
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger.i(ConstantsKt.SCAN_ADDED_WORKER, "Worker started.");
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        if (!workerHelper.isPermissionGranted$AndroidPhotosDiscovery_release(this.context, "android.permission.READ_EXTERNAL_STORAGE")) {
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger2.e(ConstantsKt.SCAN_ADDED_WORKER, "No file read permission.");
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            return WorkerUtilKt.recordAbortErrorAndFailWorker(metrics2, ConstantsKt.SCAN_ADDED_WORKER, DiscoveryMetricsKt.STORAGE_PERMISSION_FAILURE);
        }
        ?? lastAddedRowIdFromType = getLastAddedRowIdFromType(ItemType.PHOTO);
        long lastAddedRowIdFromType2 = getLastAddedRowIdFromType(ItemType.VIDEO);
        Intrinsics.checkExpressionValueIsNotNull(ListenableWorker.Result.failure(), "Result.failure()");
        ContentResolver contentResolver2 = this.contentResolver;
        if (contentResolver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
        }
        MediaStoreUtil mediaStoreUtil2 = this.mediaStoreUtil;
        if (mediaStoreUtil2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
        }
        Metrics metrics3 = this.metrics;
        if (metrics3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        ImageSource imageSource2 = new ImageSource(lastAddedRowIdFromType, contentResolver2, mediaStoreUtil2, metrics3);
        try {
            contentResolver = this.contentResolver;
            if (contentResolver == null) {
                try {
                    Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
                } catch (Throwable th2) {
                    th = th2;
                    imageSource = imageSource2;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(imageSource, th);
                        throw th3;
                    }
                }
            }
            mediaStoreUtil = this.mediaStoreUtil;
            if (mediaStoreUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
            }
            metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            discoveryConfiguration = this.configuration;
            if (discoveryConfiguration == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            }
        } catch (Throwable th4) {
            th = th4;
            lastAddedRowIdFromType = imageSource2;
        }
        try {
            try {
                VideoSource videoSource = new VideoSource(lastAddedRowIdFromType2, contentResolver, mediaStoreUtil, metrics, discoveryConfiguration.getCrashReporter());
                try {
                    listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new CursorItemSource[]{imageSource2, videoSource});
                    try {
                        ListenableWorker.Result result = (ListenableWorker.Result) ScanAddedWorkerUtilKt.traverseAllByBatch(new ScanAddedWorker$mainTask$1$1$1(this), new ScanAddedOperations(this, lastAddedRowIdFromType, lastAddedRowIdFromType2, new CompositeItemSource(listOf)));
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(videoSource, null);
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(imageSource2, null);
                        Logger logger3 = this.logger;
                        if (logger3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("logger");
                        }
                        logger3.i(ConstantsKt.SCAN_ADDED_WORKER, "Worker stopped.");
                        return result;
                    } catch (Throwable th5) {
                        th = th5;
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
                th = th;
                imageSource = lastAddedRowIdFromType;
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            lastAddedRowIdFromType = imageSource2;
            th = th;
            imageSource = lastAddedRowIdFromType;
            throw th;
        }
    }

    public final void setConfiguration$AndroidPhotosDiscovery_release(@NotNull DiscoveryConfiguration discoveryConfiguration) {
        Intrinsics.checkParameterIsNotNull(discoveryConfiguration, "<set-?>");
        this.configuration = discoveryConfiguration;
    }

    public final void setContentChangeNotifier$AndroidPhotosDiscovery_release(@NotNull ContentChangeNotifier contentChangeNotifier) {
        Intrinsics.checkParameterIsNotNull(contentChangeNotifier, "<set-?>");
        this.contentChangeNotifier = contentChangeNotifier;
    }

    public final void setContentResolver$AndroidPhotosDiscovery_release(@NotNull ContentResolver contentResolver) {
        Intrinsics.checkParameterIsNotNull(contentResolver, "<set-?>");
        this.contentResolver = contentResolver;
    }

    public final void setFilterEvents$AndroidPhotosDiscovery_release(@NotNull FilterEvents filterEvents) {
        Intrinsics.checkParameterIsNotNull(filterEvents, "<set-?>");
        this.filterEvents = filterEvents;
    }

    public final void setLogger$AndroidPhotosDiscovery_release(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    public final void setMediaStoreUtil$AndroidPhotosDiscovery_release(@NotNull MediaStoreUtil mediaStoreUtil) {
        Intrinsics.checkParameterIsNotNull(mediaStoreUtil, "<set-?>");
        this.mediaStoreUtil = mediaStoreUtil;
    }

    public final void setMetrics$AndroidPhotosDiscovery_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    public final void setSharedPreferences$AndroidPhotosDiscovery_release(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "<set-?>");
        this.sharedPreferences = sharedPreferences;
    }

    public final void setSystemUtil$AndroidPhotosDiscovery_release(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
    }

    public final void setWorkerDao$AndroidPhotosDiscovery_release(@NotNull WorkerDao workerDao) {
        Intrinsics.checkParameterIsNotNull(workerDao, "<set-?>");
        this.workerDao = workerDao;
    }

    public final void setWorkerHelper$AndroidPhotosDiscovery_release(@NotNull WorkerHelper workerHelper) {
        Intrinsics.checkParameterIsNotNull(workerHelper, "<set-?>");
        this.workerHelper = workerHelper;
    }
}
