package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.location.utils.MetricsUtil;
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
import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import com.amazon.photos.discovery.internal.util.RetryException;
import com.amazon.photos.discovery.internal.util.RetryableOperationUtil;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.observers.LocalContentType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanDeletedWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002_`B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010P\u001a\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020T2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020R0QH\u0002J.\u0010V\u001a\u0012\u0012\u0004\u0012\u00020X0Wj\b\u0012\u0004\u0012\u00020X`Y2\u0006\u0010S\u001a\u00020T2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020X0QH\u0002J\b\u0010[\u001a\u00020-H\u0014J\b\u0010\\\u001a\u00020XH\u0014J\b\u0010]\u001a\u00020^H\u0014R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u00020!8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001e\u0010&\u001a\u00020'8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010,\u001a\u00020-8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001e\u00102\u001a\u0002038\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001e\u00108\u001a\u0002098\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001e\u0010>\u001a\u00020?8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001e\u0010D\u001a\u00020E8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001e\u0010J\u001a\u00020K8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010O¨\u0006a"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ScanDeletedWorker;", "Lcom/amazon/photos/discovery/internal/worker/BaseWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "getConfiguration$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "setConfiguration$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "contentChangeNotifier", "Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "getContentChangeNotifier$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "setContentChangeNotifier$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;)V", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver$AndroidPhotosDiscovery_release", "()Landroid/content/ContentResolver;", "setContentResolver$AndroidPhotosDiscovery_release", "(Landroid/content/ContentResolver;)V", "enqueuedTime", "", "injectMethod", "Lkotlin/Function0;", "", "getInjectMethod", "()Lkotlin/jvm/functions/Function0;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "getMediaStoreUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "setMediaStoreUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "orphanRemover", "Lcom/amazon/photos/discovery/internal/util/OrphanRemover;", "getOrphanRemover$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/OrphanRemover;", "setOrphanRemover$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/OrphanRemover;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences$AndroidPhotosDiscovery_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$AndroidPhotosDiscovery_release", "(Landroid/content/SharedPreferences;)V", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "getSystemUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "setSystemUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;)V", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "getWorkerDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "setWorkerDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dao/WorkerDao;)V", "workerHelper", "Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "getWorkerHelper$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "setWorkerHelper$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/WorkerHelper;)V", "checkItemsHaveDeleted", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "uri", "Landroid/net/Uri;", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "getExistingPathsWithRetry", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "paths", "getMetricsObj", "getTag", "mainTask", "Landroidx/work/ListenableWorker$Result;", "DeleteItemTransaction", "ScanDeletedOperations", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ScanDeletedWorker extends BaseWorker {
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
    public OrphanRemover orphanRemover;
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
    /* compiled from: ScanDeletedWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0011\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0096\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ScanDeletedWorker$DeleteItemTransaction;", "Lkotlin/Function1;", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "", "folderIds", "", "", "deletedItems", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "contentChangeNotifier", "Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "(Ljava/util/Set;Ljava/util/List;Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;)V", "invoke", "workerDao", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DeleteItemTransaction implements Function1<WorkerDao, Unit> {
        private final ContentChangeNotifier contentChangeNotifier;
        private final List<MutableLocalItem> deletedItems;
        private final Set<Long> folderIds;

        public DeleteItemTransaction(@NotNull Set<Long> folderIds, @NotNull List<MutableLocalItem> deletedItems, @NotNull ContentChangeNotifier contentChangeNotifier) {
            Intrinsics.checkParameterIsNotNull(folderIds, "folderIds");
            Intrinsics.checkParameterIsNotNull(deletedItems, "deletedItems");
            Intrinsics.checkParameterIsNotNull(contentChangeNotifier, "contentChangeNotifier");
            this.folderIds = folderIds;
            this.deletedItems = deletedItems;
            this.contentChangeNotifier = contentChangeNotifier;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(WorkerDao workerDao) {
            invoke2(workerDao);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@NotNull WorkerDao workerDao) {
            int collectionSizeOrDefault;
            Intrinsics.checkParameterIsNotNull(workerDao, "workerDao");
            for (MutableLocalItem mutableLocalItem : this.deletedItems) {
                workerDao.deleteLocal(mutableLocalItem);
                if (workerDao.getLocalCountForUnifiedId(mutableLocalItem.getUnifiedId()) == 0) {
                    workerDao.deleteCloudItemsByUnifiedId(mutableLocalItem.getUnifiedId());
                    workerDao.deleteUnifiedById(mutableLocalItem.getUnifiedId());
                }
            }
            ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
            LocalContentType localContentType = LocalContentType.LOCAL_ITEM;
            List<MutableLocalItem> list = this.deletedItems;
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (MutableLocalItem mutableLocalItem2 : list) {
                arrayList.add(Long.valueOf(mutableLocalItem2.getId()));
            }
            contentChangeNotifier.onContentDeleted$AndroidPhotosDiscovery_release(localContentType, arrayList);
            ArrayList arrayList2 = new ArrayList();
            for (Number number : this.folderIds) {
                long longValue = number.longValue();
                if (workerDao.getItemCountByFolderId(longValue) == 0) {
                    workerDao.deleteLocalFolderById(longValue);
                    arrayList2.add(Long.valueOf(longValue));
                }
            }
            this.contentChangeNotifier.onContentDeleted$AndroidPhotosDiscovery_release(LocalContentType.FOLDER, arrayList2);
        }
    }

    /* compiled from: ScanDeletedWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\b\u0010 \u001a\u00020\u0002H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\"H\u0016J \u0010#\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006H\u0002J\u0006\u0010&\u001a\u00020\u0006J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\u0002H\u0016R-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001a\u0010\u0019\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000f¨\u0006-"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ScanDeletedWorker$ScanDeletedOperations;", "Lcom/amazon/photos/discovery/internal/worker/TraverseOperations;", "Landroidx/work/ListenableWorker$Result;", "(Lcom/amazon/photos/discovery/internal/worker/ScanDeletedWorker;)V", "folders", "Ljava/util/HashMap;", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "Lkotlin/collections/HashMap;", "getFolders", "()Ljava/util/HashMap;", "lastProcessedId", "getLastProcessedId", "()J", "setLastProcessedId", "(J)V", "totalItemsDeleted", "", "getTotalItemsDeleted", "()I", "setTotalItemsDeleted", "(I)V", "totalProcessedItems", "getTotalProcessedItems", "setTotalProcessedItems", "workStartTimeMs", "getWorkStartTimeMs", "setWorkStartTimeMs", "batchOperation", "batch", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "completed", "fetchOneBatch", "Lcom/amazon/photos/discovery/internal/worker/FetchResult;", "getLocalAfterIdWithRetry", "id", MetricsUtil.LegacyMetricTypes.LIMIT, "getNow", "init", "", "recordMetrics", "resultMetrics", "Lcom/amazon/photos/discovery/metrics/DiscoveryMetrics;", "stopped", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private final class ScanDeletedOperations implements TraverseOperations<ListenableWorker.Result> {
        @NotNull
        private final HashMap<Long, MutableLocalFolder> folders = new HashMap<>();
        private long lastProcessedId;
        private int totalItemsDeleted;
        private int totalProcessedItems;
        private long workStartTimeMs;

        public ScanDeletedOperations() {
        }

        private final List<MutableLocalItem> getLocalAfterIdWithRetry(final long j, final long j2) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = null;
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = null;
            RetryableOperationUtil.INSTANCE.runWithRetry(3, new RetryableOperationUtil.RetryableOperation() { // from class: com.amazon.photos.discovery.internal.worker.ScanDeletedWorker$ScanDeletedOperations$getLocalAfterIdWithRetry$1
                @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
                public void finalOperation(boolean z, int i) {
                    if (z) {
                        if (i >= 2) {
                            return;
                        }
                        ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanGetLocalAfterIdRetried, MetricRecordingType.STANDARD);
                        return;
                    }
                    Exception exc = (Exception) objectRef2.element;
                    if (exc != null) {
                        ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanGetLocalAfterIdError, exc);
                    } else {
                        ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanGetLocalAfterIdError, MetricRecordingType.STANDARD);
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
                    objectRef.element = ScanDeletedWorker.this.getWorkerDao$AndroidPhotosDiscovery_release().getLocalAfterId(j, j2);
                }
            });
            return (List) objectRef.element;
        }

        private final void recordMetrics(DiscoveryMetrics discoveryMetrics) {
            if (this.totalItemsDeleted > 0) {
                ScanDeletedWorker.this.getOrphanRemover$AndroidPhotosDiscovery_release().removeOrphanedEntries();
            }
            ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordCustomMetric(ConstantsKt.SCAN_DELETED_WORKER, new ClientMetric().addCounter(DiscoveryMetrics.DeletedScanTotalDeleted, this.totalItemsDeleted).addCounter(DiscoveryMetrics.DeletedScanTotalProcessed, this.totalProcessedItems).addTimer(discoveryMetrics, ScanDeletedWorker.this.getSystemUtil$AndroidPhotosDiscovery_release().elapsedRealTimeMillis() - this.workStartTimeMs), MetricRecordingType.STANDARD);
            ScanDeletedWorker.this.getWorkerHelper$AndroidPhotosDiscovery_release().recordWorkerLifetimeMetric(ScanDeletedWorker.this.enqueuedTime, ConstantsKt.SCAN_DELETED_WORKER);
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        /* renamed from: batchOperation  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ListenableWorker.Result mo4358batchOperation(List list) {
            return mo4358batchOperation((List<MutableLocalItem>) list);
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        public FetchResult<ListenableWorker.Result> fetchOneBatch() {
            long now = getNow();
            List<MutableLocalItem> localAfterIdWithRetry = getLocalAfterIdWithRetry(this.lastProcessedId, ScanDeletedWorker.this.getConfiguration$AndroidPhotosDiscovery_release().getScanBatchSize());
            if (localAfterIdWithRetry == null) {
                ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.WorkerRetryOnError, MetricRecordingType.STANDARD);
                ListenableWorker.Result retry = ListenableWorker.Result.retry();
                Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
                return new FetchFailure(retry);
            }
            ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleDuration(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanFetchOneBatchTime, getNow() - now);
            return new FetchSuccess(localAfterIdWithRetry);
        }

        @NotNull
        public final HashMap<Long, MutableLocalFolder> getFolders() {
            return this.folders;
        }

        public final long getLastProcessedId() {
            return this.lastProcessedId;
        }

        public final long getNow() {
            return ScanDeletedWorker.this.getSystemUtil$AndroidPhotosDiscovery_release().elapsedRealTimeMillis();
        }

        public final int getTotalItemsDeleted() {
            return this.totalItemsDeleted;
        }

        public final int getTotalProcessedItems() {
            return this.totalProcessedItems;
        }

        public final long getWorkStartTimeMs() {
            return this.workStartTimeMs;
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        public void init() {
            ScanDeletedWorker.this.getLogger$AndroidPhotosDiscovery_release().i(ConstantsKt.SCAN_DELETED_WORKER, "Scan start.");
            this.workStartTimeMs = ScanDeletedWorker.this.getSystemUtil$AndroidPhotosDiscovery_release().elapsedRealTimeMillis();
            ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanStarted, MetricRecordingType.STANDARD);
            this.lastProcessedId = ScanDeletedWorker.this.getSharedPreferences$AndroidPhotosDiscovery_release().getLong(ScanDeletedWorkerKt.PREF_LAST_PROCESSED_ID, 0L);
            this.totalItemsDeleted = 0;
            this.totalProcessedItems = 0;
        }

        public final void setLastProcessedId(long j) {
            this.lastProcessedId = j;
        }

        public final void setTotalItemsDeleted(int i) {
            this.totalItemsDeleted = i;
        }

        public final void setTotalProcessedItems(int i) {
            this.totalProcessedItems = i;
        }

        public final void setWorkStartTimeMs(long j) {
            this.workStartTimeMs = j;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @Nullable
        /* renamed from: batchOperation */
        public ListenableWorker.Result mo4358batchOperation(@NotNull List<MutableLocalItem> batch) {
            List<MutableLocalItem> plus;
            int collectionSizeOrDefault;
            Intrinsics.checkParameterIsNotNull(batch, "batch");
            long now = getNow();
            Logger logger$AndroidPhotosDiscovery_release = ScanDeletedWorker.this.getLogger$AndroidPhotosDiscovery_release();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Processing batch starting at ");
            outline107.append(this.lastProcessedId);
            outline107.append(" of ");
            outline107.append(batch.size());
            outline107.append(" items");
            logger$AndroidPhotosDiscovery_release.i(ConstantsKt.SCAN_DELETED_WORKER, outline107.toString());
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (MutableLocalItem mutableLocalItem : batch) {
                if (mutableLocalItem.getItemType() == ItemType.PHOTO) {
                    arrayList.add(mutableLocalItem);
                } else {
                    arrayList2.add(mutableLocalItem);
                }
                if (mutableLocalItem.getId() > this.lastProcessedId) {
                    this.lastProcessedId = mutableLocalItem.getId();
                }
                this.totalProcessedItems++;
            }
            try {
                plus = CollectionsKt___CollectionsKt.plus((Collection) ScanDeletedWorker.this.checkItemsHaveDeleted(ScanDeletedWorker.this.getMediaStoreUtil$AndroidPhotosDiscovery_release().getImagesUri(), arrayList), (Iterable) ScanDeletedWorker.this.checkItemsHaveDeleted(ScanDeletedWorker.this.getMediaStoreUtil$AndroidPhotosDiscovery_release().getVideosUri(), arrayList2));
                this.totalItemsDeleted = plus.size() + this.totalItemsDeleted;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10);
                ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
                for (MutableLocalItem mutableLocalItem2 : plus) {
                    arrayList3.add(Long.valueOf(mutableLocalItem2.getParentId()));
                }
                linkedHashSet.addAll(arrayList3);
                ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleDuration(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanCheckDeletedTime, getNow() - now);
                long now2 = getNow();
                if (!plus.isEmpty()) {
                    try {
                        ScanDeletedWorker.this.getWorkerDao$AndroidPhotosDiscovery_release().executeTransaction(new DeleteItemTransaction(linkedHashSet, plus, ScanDeletedWorker.this.getContentChangeNotifier$AndroidPhotosDiscovery_release()));
                    } catch (Exception e) {
                        ScanDeletedWorker.this.getLogger$AndroidPhotosDiscovery_release().e(ConstantsKt.SCAN_DELETED_WORKER, "Failed to apply transaction", e);
                        ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanTransactionFailed, e);
                        return WorkerUtilKt.recordAndRetry(ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release(), ConstantsKt.SCAN_DELETED_WORKER);
                    }
                }
                ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleDuration(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanDeleteTransactionTime, getNow() - now2);
                ScanDeletedWorker.this.getSharedPreferences$AndroidPhotosDiscovery_release().edit().putLong(ScanDeletedWorkerKt.PREF_LAST_PROCESSED_ID, this.lastProcessedId).apply();
                return null;
            } catch (Exception e2) {
                ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.DeletedScanCheckDeletedError, e2);
                return WorkerUtilKt.recordAndRetry(ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release(), ConstantsKt.SCAN_DELETED_WORKER);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        /* renamed from: completed */
        public ListenableWorker.Result mo4359completed() {
            Logger logger$AndroidPhotosDiscovery_release = ScanDeletedWorker.this.getLogger$AndroidPhotosDiscovery_release();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scan completed, deleted ");
            outline107.append(this.totalItemsDeleted);
            outline107.append(" items.");
            logger$AndroidPhotosDiscovery_release.i(ConstantsKt.SCAN_DELETED_WORKER, outline107.toString());
            ScanDeletedWorker.this.getSharedPreferences$AndroidPhotosDiscovery_release().edit().putLong(ScanDeletedWorkerKt.PREF_LAST_PROCESSED_ID, 0L).apply();
            recordMetrics(DiscoveryMetrics.DeletedScanComplete);
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
            return success;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        /* renamed from: stopped */
        public ListenableWorker.Result mo4360stopped() {
            Logger logger$AndroidPhotosDiscovery_release = ScanDeletedWorker.this.getLogger$AndroidPhotosDiscovery_release();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scan stopped, deleted ");
            outline107.append(this.totalItemsDeleted);
            outline107.append(" items.");
            logger$AndroidPhotosDiscovery_release.i(ConstantsKt.SCAN_DELETED_WORKER, outline107.toString());
            recordMetrics(DiscoveryMetrics.DeletedScanStopped);
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
            return success;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanDeletedWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.context = context;
        this.enqueuedTime = workerParams.getInputData().getLong(DiscoveryOperationsKt.ENQUEUED_TIME, 0L);
        this.injectMethod = new ScanDeletedWorker$injectMethod$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<MutableLocalItem> checkItemsHaveDeleted(Uri uri, List<MutableLocalItem> list) {
        int collectionSizeOrDefault;
        if (list.isEmpty()) {
            return new ArrayList();
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalItem mutableLocalItem : list) {
            arrayList.add(mutableLocalItem.getFilePath());
        }
        ArrayList<String> existingPathsWithRetry = getExistingPathsWithRetry(uri, arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (!existingPathsWithRetry.contains(((MutableLocalItem) obj).getFilePath())) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    private final ArrayList<String> getExistingPathsWithRetry(final Uri uri, final List<String> list) {
        int collectionSizeOrDefault;
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : list) {
            arrayList.add(WebConstants.UriConstants.QUESTIONMARK_KEY);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("_data IN (");
        outline107.append(Joiner.on((char) JsonReaderKt.COMMA).join(arrayList));
        outline107.append(")");
        final String sb = outline107.toString();
        final ArrayList<String> arrayList2 = new ArrayList<>();
        RetryableOperationUtil.INSTANCE.runWithRetry(3, new RetryableOperationUtil.RetryableOperation() { // from class: com.amazon.photos.discovery.internal.worker.ScanDeletedWorker$getExistingPathsWithRetry$1
            @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
            public void finalOperation(boolean z, int i) {
                if (!z) {
                    ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.QueryExistedPathFailed, MetricRecordingType.STANDARD);
                    throw new RetryException(null, 1, null);
                } else if (i >= 2) {
                } else {
                    ScanDeletedWorker.this.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetrics.QueryExistedPathSucceedAfterRetry, MetricRecordingType.STANDARD);
                }
            }

            @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
            public void run() {
                arrayList2.clear();
                ContentResolver contentResolver$AndroidPhotosDiscovery_release = ScanDeletedWorker.this.getContentResolver$AndroidPhotosDiscovery_release();
                Uri uri2 = uri;
                String[] strArr = {"_data"};
                String str2 = sb;
                Object[] array = list.toArray(new String[0]);
                if (array != null) {
                    Cursor query = contentResolver$AndroidPhotosDiscovery_release.query(uri2, strArr, str2, (String[]) array, null);
                    try {
                        if (query != null) {
                            query.moveToFirst();
                            while (!query.isAfterLast()) {
                                arrayList2.add(query.getString(0));
                                query.moveToNext();
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(query, null);
                            return;
                        }
                        throw new IllegalStateException();
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(query, th);
                            throw th2;
                        }
                    }
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        return arrayList2;
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

    @NotNull
    public final OrphanRemover getOrphanRemover$AndroidPhotosDiscovery_release() {
        OrphanRemover orphanRemover = this.orphanRemover;
        if (orphanRemover == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orphanRemover");
        }
        return orphanRemover;
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
        return ConstantsKt.SCAN_DELETED_WORKER;
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

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected ListenableWorker.Result mainTask() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger.i(ConstantsKt.SCAN_DELETED_WORKER, "Worker started.");
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        if (!workerHelper.isPermissionGranted$AndroidPhotosDiscovery_release(this.context, "android.permission.READ_EXTERNAL_STORAGE")) {
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger2.e(ConstantsKt.SCAN_DELETED_WORKER, "No file read permission.");
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            return WorkerUtilKt.recordAbortErrorAndFailWorker(metrics, ConstantsKt.SCAN_DELETED_WORKER, DiscoveryMetricsKt.STORAGE_PERMISSION_FAILURE);
        }
        ListenableWorker.Result result = (ListenableWorker.Result) ScanAddedWorkerUtilKt.traverseAllByBatch(new ScanDeletedWorker$mainTask$result$1(this), new ScanDeletedOperations());
        Logger logger3 = this.logger;
        if (logger3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger3.i(ConstantsKt.SCAN_DELETED_WORKER, "Worker stopped.");
        return result;
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

    public final void setOrphanRemover$AndroidPhotosDiscovery_release(@NotNull OrphanRemover orphanRemover) {
        Intrinsics.checkParameterIsNotNull(orphanRemover, "<set-?>");
        this.orphanRemover = orphanRemover;
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
