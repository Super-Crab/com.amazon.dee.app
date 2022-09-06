package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import androidx.work.ListenableWorker;
import androidx.work.WorkInfo;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.DiscoveryOperations;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.model.ItemTypeCount;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.ItemType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MonitorWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010]\u001a\u00020^H\u0002J\b\u0010_\u001a\u00020>H\u0014J\u0016\u0010`\u001a\u00020\b2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\b0DH\u0002J\b\u0010b\u001a\u00020\bH\u0014J\b\u0010c\u001a\u00020dH\u0014J\b\u0010e\u001a\u00020.H\u0002J\u0010\u0010f\u001a\u00020.2\u0006\u0010g\u001a\u00020^H\u0002J!\u0010h\u001a\u00020.2\b\u0010i\u001a\u0004\u0018\u00010^2\b\u0010j\u001a\u0004\u0018\u00010^H\u0002¢\u0006\u0002\u0010kJ\u0017\u0010l\u001a\u00020.2\b\u0010m\u001a\u0004\u0018\u00010^H\u0002¢\u0006\u0002\u0010nJ\u0010\u0010o\u001a\u00020.2\u0006\u0010p\u001a\u00020qH\u0002J\u0010\u0010r\u001a\u00020.2\u0006\u0010g\u001a\u00020^H\u0002J\b\u0010s\u001a\u00020.H\u0002R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u00020!8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001e\u0010&\u001a\u00020'8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001e\u00101\u001a\u0002028\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u00107\u001a\u0002088\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001e\u0010=\u001a\u00020>8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00020\b0DX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010E\u001a\u00020F8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001e\u0010K\u001a\u00020L8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001e\u0010Q\u001a\u00020R8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001e\u0010W\u001a\u00020X8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\¨\u0006u"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/MonitorWorker;", "Lcom/amazon/photos/discovery/internal/worker/BaseWorker;", "context", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "cameraDirectoryPathQueryParameter", "", "getCameraDirectoryPathQueryParameter", "()Ljava/lang/String;", "cameraDirectoryPathQueryParameter$delegate", "Lkotlin/Lazy;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "getConfiguration$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "setConfiguration$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver$AndroidPhotosDiscovery_release", "()Landroid/content/ContentResolver;", "setContentResolver$AndroidPhotosDiscovery_release", "(Landroid/content/ContentResolver;)V", "dedupeStages", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "getDedupeStages$AndroidPhotosDiscovery_release", "()Ljava/util/List;", "setDedupeStages$AndroidPhotosDiscovery_release", "(Ljava/util/List;)V", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "getDiscovery$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/Discovery;", "setDiscovery$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/Discovery;)V", "fileUtils", "Lcom/amazon/photos/discovery/internal/util/FileUtils;", "getFileUtils$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/FileUtils;", "setFileUtils$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/FileUtils;)V", "injectMethod", "Lkotlin/Function0;", "", "getInjectMethod", "()Lkotlin/jvm/functions/Function0;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "getMediaStoreUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "setMediaStoreUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "properWorkers", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences$AndroidPhotosDiscovery_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$AndroidPhotosDiscovery_release", "(Landroid/content/SharedPreferences;)V", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "getSystemUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "setSystemUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;)V", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "getWorkerDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "setWorkerDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dao/WorkerDao;)V", "workerHelper", "Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "getWorkerHelper$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "setWorkerHelper$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/WorkerHelper;)V", "getMediaStoreCameraRollCount", "", "getMetricsObj", "getProperWorkerTag", "tags", "getTag", "mainTask", "Landroidx/work/ListenableWorker$Result;", "recordCameraDirectoryComparison", "recordCloudComparison", "unifiedItemCount", "recordLocalItemDataAnalysis", "imageCount", "videoCount", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "recordLocalMediaStoreComparison", "mediaStoreCount", "(Ljava/lang/Integer;)V", "recordMissingMediaStoreItem", "mediaStoreItemSource", "Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "recordStageComparison", "recordWorkInfoMetrics", "ScanMissingOperations", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MonitorWorker extends BaseWorker {
    private final Lazy cameraDirectoryPathQueryParameter$delegate;
    @Inject
    @NotNull
    public DiscoveryConfiguration configuration;
    @Inject
    @NotNull
    public ContentResolver contentResolver;
    private final Context context;
    @Inject
    @NotNull
    public List<DedupeStage> dedupeStages;
    @Inject
    @NotNull
    public Discovery discovery;
    @Inject
    @NotNull
    public FileUtils fileUtils;
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
    private final Set<String> properWorkers;
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
    /* compiled from: MonitorWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001d\u0010\r\u001a\u0004\u0018\u00010\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016¢\u0006\u0002\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\r\u0010\u0018\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0013R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/MonitorWorker$ScanMissingOperations;", "Lcom/amazon/photos/discovery/internal/worker/TraverseOperations;", "", "itemSource", "Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "(Lcom/amazon/photos/discovery/internal/worker/MonitorWorker;Lcom/amazon/photos/discovery/internal/worker/ItemSource;)V", "getItemSource", "()Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "mediaStoreMissingInDbCount", "getMediaStoreMissingInDbCount", "()I", "setMediaStoreMissingInDbCount", "(I)V", "batchOperation", "batch", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "(Ljava/util/List;)Ljava/lang/Integer;", "completed", "()Ljava/lang/Integer;", "fetchOneBatch", "Lcom/amazon/photos/discovery/internal/worker/FetchResult;", "init", "", "stopped", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class ScanMissingOperations implements TraverseOperations<Integer> {
        @NotNull
        private final ItemSource itemSource;
        private int mediaStoreMissingInDbCount;
        final /* synthetic */ MonitorWorker this$0;

        public ScanMissingOperations(@NotNull MonitorWorker monitorWorker, ItemSource itemSource) {
            Intrinsics.checkParameterIsNotNull(itemSource, "itemSource");
            this.this$0 = monitorWorker;
            this.itemSource = itemSource;
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        /* renamed from: batchOperation  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Integer mo4358batchOperation(List list) {
            return mo4358batchOperation((List<MutableLocalItem>) list);
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        public FetchResult<Integer> fetchOneBatch() {
            try {
                return new FetchSuccess(ScanAddedWorkerUtilKt.fillBatch(this.itemSource, this.this$0.getConfiguration$AndroidPhotosDiscovery_release().getScanBatchSize()));
            } catch (Exception e) {
                this.this$0.getLogger$AndroidPhotosDiscovery_release().e(ConstantsKt.MONITOR_WORKER, "Failure scanning media store for additions", e);
                this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleErrorEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MediaStoreScanError, e);
                return new FetchFailure(-1);
            }
        }

        @NotNull
        public final ItemSource getItemSource() {
            return this.itemSource;
        }

        public final int getMediaStoreMissingInDbCount() {
            return this.mediaStoreMissingInDbCount;
        }

        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        public void init() {
            this.mediaStoreMissingInDbCount = 0;
        }

        public final void setMediaStoreMissingInDbCount(int i) {
            this.mediaStoreMissingInDbCount = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @Nullable
        /* renamed from: batchOperation */
        public Integer mo4358batchOperation(@NotNull List<MutableLocalItem> batch) {
            int collectionSizeOrDefault;
            List<MutableLocalItem> list;
            boolean contains$default;
            Intrinsics.checkParameterIsNotNull(batch, "batch");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(batch, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (MutableLocalItem mutableLocalItem : batch) {
                arrayList.add(mutableLocalItem.getFilePath());
            }
            Metrics metrics$AndroidPhotosDiscovery_release = this.this$0.getMetrics$AndroidPhotosDiscovery_release();
            try {
                list = this.this$0.getWorkerDao$AndroidPhotosDiscovery_release().getLocalInGivenPaths(arrayList);
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_LOCAL_ADDED_AFTER, metrics$AndroidPhotosDiscovery_release, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
                list = null;
            }
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : batch) {
                    MutableLocalItem mutableLocalItem2 = (MutableLocalItem) obj;
                    boolean z = false;
                    if (!list.isEmpty()) {
                        Iterator<T> it2 = list.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            contains$default = StringsKt__StringsKt.contains$default((CharSequence) ((MutableLocalItem) it2.next()).getFilePath(), (CharSequence) mutableLocalItem2.getFilePath(), false, 2, (Object) null);
                            if (contains$default) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (!z) {
                        arrayList2.add(obj);
                    }
                }
                this.mediaStoreMissingInDbCount = arrayList2.size() + this.mediaStoreMissingInDbCount;
                return null;
            }
            return -1;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        /* renamed from: completed */
        public Integer mo4359completed() {
            return Integer.valueOf(this.mediaStoreMissingInDbCount);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.discovery.internal.worker.TraverseOperations
        @NotNull
        /* renamed from: stopped */
        public Integer mo4360stopped() {
            return Integer.valueOf(this.mediaStoreMissingInDbCount);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MonitorWorker(@NotNull Context context, @NotNull WorkerParameters workerParameters) {
        super(context, workerParameters);
        Set<String> of;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParameters, "workerParameters");
        this.context = context;
        this.cameraDirectoryPathQueryParameter$delegate = LazyKt.lazy(new MonitorWorker$cameraDirectoryPathQueryParameter$2(this));
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{ConstantsKt.SCAN_ADDED_WORKER, ConstantsKt.SCAN_DELETED_WORKER, ConstantsKt.MDD_STAGE, ConstantsKt.DIGEST_CALCULATOR_STAGE, ConstantsKt.DIGEST_BREAK_UP_STAGE, ConstantsKt.DIGEST_DEDUPLICATOR_STAGE, ConstantsKt.MONITOR_WORKER, ConstantsKt.MEDIA_STORE_CHANGE});
        this.properWorkers = of;
        this.injectMethod = new MonitorWorker$injectMethod$1(this);
    }

    private final String getCameraDirectoryPathQueryParameter() {
        return (String) this.cameraDirectoryPathQueryParameter$delegate.getValue();
    }

    private final int getMediaStoreCameraRollCount() {
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
        }
        MediaStoreUtil mediaStoreUtil = this.mediaStoreUtil;
        if (mediaStoreUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
        }
        int i = 0;
        Cursor query = contentResolver.query(mediaStoreUtil.getFilesUri(), new String[]{"_data"}, "_data LIKE ?", new String[]{getCameraDirectoryPathQueryParameter()}, null);
        if (query != null) {
            try {
                i = query.getCount();
            } finally {
            }
        }
        CloseableKt.closeFinally(query, null);
        return i;
    }

    private final String getProperWorkerTag(Set<String> set) {
        Set intersect;
        intersect = CollectionsKt___CollectionsKt.intersect(set, this.properWorkers);
        if (intersect.size() > 1) {
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics.recordSimpleEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MonitorWorkInfoStatusTagUnset, MetricRecordingType.STANDARD);
        }
        String str = (String) CollectionsKt.firstOrNull(intersect);
        return str != null ? str : "Unknown";
    }

    private final void recordCameraDirectoryComparison() {
        if (getCameraDirectoryPathQueryParameter().length() == 0) {
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics.recordSimpleEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MonitorCameraDirectoryMissing, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            return;
        }
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            int countOfLocalItemsInDirectory = workerDao.getCountOfLocalItemsInDirectory(getCameraDirectoryPathQueryParameter());
            int mediaStoreCameraRollCount = getMediaStoreCameraRollCount();
            ClientMetric addCounter = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER).addCounter(DiscoveryMetrics.MonitorLocalCameraItems, countOfLocalItemsInDirectory).addCounter(DiscoveryMetrics.MonitorMediaStoreCameraItems, mediaStoreCameraRollCount).addCounter(DiscoveryMetrics.MonitorCameraItemsDiscrepancy, Math.abs(mediaStoreCameraRollCount - countOfLocalItemsInDirectory));
            Metrics metrics3 = this.metrics;
            if (metrics3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics3.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_CAMERA_ROLL_LOCAL, metrics2, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
    }

    private final void recordCloudComparison(int i) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            int countOfUnifiedItemsWithMappedCloudItems = workerDao.getCountOfUnifiedItemsWithMappedCloudItems();
            ClientMetric addCounter = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER).addCounter(DiscoveryMetrics.MonitorUnifiedWithoutCloud, Math.abs(i - countOfUnifiedItemsWithMappedCloudItems)).addCounter(DiscoveryMetrics.MonitorUnifiedWithCloud, countOfUnifiedItemsWithMappedCloudItems);
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_CLOUD_MAPPING_COUNT, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
    }

    private final void recordLocalItemDataAnalysis(Integer num, Integer num2) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            for (final ItemTypeCount itemTypeCount : workerDao.getLocalItemTypeCounts()) {
                ClientMetric addCounter = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER).withStatus(itemTypeCount.getItemType().name()).addCounter(new MetricName() { // from class: com.amazon.photos.discovery.internal.worker.MonitorWorker$recordLocalItemDataAnalysis$1$1$localItemTypeCount$1
                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                    @NotNull
                    public final String getEventName() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryMetricsKt.MONITOR_WORKER_LOCAL_ITEM_TYPE_PREFIX);
                        outline107.append(ItemTypeCount.this.getItemType().name());
                        return outline107.toString();
                    }
                }, itemTypeCount.getCount());
                Metrics metrics2 = this.metrics;
                if (metrics2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics2.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            }
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_LOCAL_ITEM_TYPES, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
        Metrics metrics3 = this.metrics;
        if (metrics3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao2 = this.workerDao;
            if (workerDao2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            List<DedupeStage> list = this.dedupeStages;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dedupeStages");
            }
            int countOfLocalItemsWithMissingMd5Values = workerDao2.getCountOfLocalItemsWithMissingMd5Values(list.size());
            WorkerDao workerDao3 = this.workerDao;
            if (workerDao3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            int countOfZeroDurationLocalItemByType = workerDao3.getCountOfZeroDurationLocalItemByType(ItemType.VIDEO);
            WorkerDao workerDao4 = this.workerDao;
            if (workerDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            int countOfLocalItemsWithInvalidFileSize = workerDao4.getCountOfLocalItemsWithInvalidFileSize();
            WorkerDao workerDao5 = this.workerDao;
            if (workerDao5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            ClientMetric addCounter2 = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER).addCounter(DiscoveryMetrics.MonitorLocalInvalidDateTaken, workerDao5.getCountOfLocalItemsWithInvalidDateTaken()).addCounter(DiscoveryMetrics.MonitorMediaStoreImages, num != null ? num.intValue() : 0).addCounter(DiscoveryMetrics.MonitorMediaStoreVideos, num2 != null ? num2.intValue() : 0).addCounter(DiscoveryMetrics.MonitorLocalItemsMissingMd5, countOfLocalItemsWithMissingMd5Values).addCounter(DiscoveryMetrics.MonitorLocalVideosZeroDuration, countOfZeroDurationLocalItemByType).addCounter(DiscoveryMetrics.MonitorLocalItemsInvalidSize, countOfLocalItemsWithInvalidFileSize);
            Metrics metrics4 = this.metrics;
            if (metrics4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics4.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter2, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            Unit unit2 = Unit.INSTANCE;
        } catch (Exception e2) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_BAD_ITEM_COUNTS, metrics3, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e2);
        }
    }

    private final void recordLocalMediaStoreComparison(Integer num) {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            int localItemCount = workerDao.getLocalItemCount();
            WorkerDao workerDao2 = this.workerDao;
            if (workerDao2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            List<String> duplicatedLocalItems = workerDao2.getDuplicatedLocalItems();
            if (num != null) {
                num.intValue();
                ClientMetric addCounter = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER).addCounter(DiscoveryMetrics.MonitorLocalItemDiscrepancy, Math.abs(num.intValue() - localItemCount)).addCounter(DiscoveryMetrics.MonitorDiscoveryLocalItems, localItemCount).addCounter(DiscoveryMetrics.MonitorMediaStoreItems, num.intValue());
                if (num.intValue() == localItemCount) {
                    addCounter.addCounter(DiscoveryMetrics.MonitorConsistentDevice, 1);
                } else {
                    addCounter.addCounter(DiscoveryMetrics.MonitorInconsistentDevice, 1);
                }
                if (num.intValue() < localItemCount) {
                    addCounter.addCounter(DiscoveryMetrics.MonitorMoreItemsInDbEvent, 1).addCounter(DiscoveryMetrics.MonitorMoreItemsInDbCount, localItemCount - num.intValue());
                }
                if (!duplicatedLocalItems.isEmpty()) {
                    addCounter.addCounter(DiscoveryMetrics.MonitorDupItemsInDbEvent, 1);
                    addCounter.addCounter(DiscoveryMetrics.MonitorDupItemsInDbCount, duplicatedLocalItems.size());
                } else {
                    addCounter.addCounter(DiscoveryMetrics.MonitorNoDupItemsInDb, 1);
                }
                Metrics metrics2 = this.metrics;
                if (metrics2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics2.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            } else {
                Metrics metrics3 = this.metrics;
                if (metrics3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics3.recordSimpleEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MonitorMediaStoreCountInvalid, MetricRecordingType.STANDARD);
            }
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_LOCAL_ITEM_COUNT, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
    }

    private final void recordMissingMediaStoreItem(ItemSource itemSource) {
        int intValue = ((Number) ScanAddedWorkerUtilKt.traverseAllByBatch(new MonitorWorker$recordMissingMediaStoreItem$mediaStoreMissingInDbCount$1(this), new ScanMissingOperations(this, itemSource))).intValue();
        ClientMetric withTagName = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER);
        if (intValue > 0) {
            withTagName.addCounter(DiscoveryMetrics.MonitorMissItemEvent, 1);
            withTagName.addCounter(DiscoveryMetrics.MonitorMissItemCount, intValue);
        } else if (intValue == 0) {
            withTagName.addCounter(DiscoveryMetrics.MonitorNoMissItem, 1);
        } else {
            withTagName.addCounter(DiscoveryMetrics.MonitorMissItemError, 1);
        }
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(ConstantsKt.MONITOR_WORKER, withTagName, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
    }

    private final void recordStageComparison(int i) {
        String str;
        Object obj;
        boolean z;
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            List<DedupeStage> list = this.dedupeStages;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dedupeStages");
            }
            int unifiedItemCountOnDedupeStage = workerDao.getUnifiedItemCountOnDedupeStage(list.size());
            ClientMetric addCounter = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER).addCounter(DiscoveryMetrics.MonitorDedupeComplete, unifiedItemCountOnDedupeStage).addCounter(DiscoveryMetrics.MonitorDedupeIncomplete, Math.abs(i - unifiedItemCountOnDedupeStage));
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_LAST_STAGE_COUNT, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
        Metrics metrics3 = this.metrics;
        if (metrics3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao2 = this.workerDao;
            if (workerDao2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            for (Number number : workerDao2.getDistinctDedupeStages()) {
                final int intValue = number.intValue();
                List<DedupeStage> list2 = this.dedupeStages;
                if (list2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dedupeStages");
                }
                Iterator<T> it2 = list2.iterator();
                while (true) {
                    str = null;
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it2.next();
                    if (((DedupeStage) obj).getStageId() == intValue) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                DedupeStage dedupeStage = (DedupeStage) obj;
                if (dedupeStage != null) {
                    str = dedupeStage.getName();
                }
                ClientMetric withTagName = new ClientMetric().withTagName(ConstantsKt.MONITOR_WORKER);
                if (str == null) {
                    str = "Unknown";
                }
                ClientMetric withStatus = withTagName.withStatus(str);
                MetricName metricName = new MetricName() { // from class: com.amazon.photos.discovery.internal.worker.MonitorWorker$recordStageComparison$2$1$stageLevelMetric$1
                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                    @NotNull
                    public final String getEventName() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryMetricsKt.MONITOR_WORKER_STAGE_COUNT_PREFIX);
                        outline107.append(intValue);
                        return outline107.toString();
                    }
                };
                WorkerDao workerDao3 = this.workerDao;
                if (workerDao3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("workerDao");
                }
                ClientMetric addCounter2 = withStatus.addCounter(metricName, workerDao3.getUnifiedItemCountOnDedupeStage(intValue));
                Metrics metrics4 = this.metrics;
                if (metrics4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics4.recordCustomMetric(ConstantsKt.MONITOR_WORKER, addCounter2, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
            }
            Unit unit2 = Unit.INSTANCE;
        } catch (Exception e2) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_DISTINCT_DEDUPE_STAGES, metrics3, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e2);
        }
    }

    private final void recordWorkInfoMetrics() {
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        List<WorkInfo> discoveryWorkInfo = discovery.getOperations().getWorkInfo().get();
        Intrinsics.checkExpressionValueIsNotNull(discoveryWorkInfo, "discoveryWorkInfo");
        for (final WorkInfo workInfo : discoveryWorkInfo) {
            Set<String> tags = workInfo.getTags();
            Intrinsics.checkExpressionValueIsNotNull(tags, "it.tags");
            String properWorkerTag = getProperWorkerTag(tags);
            ClientMetric addCounter = new ClientMetric().withTagName(properWorkerTag).withStatus(workInfo.getState().name()).addCounter(new MetricName() { // from class: com.amazon.photos.discovery.internal.worker.MonitorWorker$recordWorkInfoMetrics$1$workInfoMetric$1
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryMetricsKt.MONITOR_WORKER_STATUS_PREFIX);
                    outline107.append(WorkInfo.this.getState().name());
                    return outline107.toString();
                }
            }, workInfo.getRunAttemptCount());
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics.recordCustomMetric(properWorkerTag, addCounter, MetricRecordingType.CUSTOMER, MetricRecordingType.STANDARD);
        }
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
    public final ContentResolver getContentResolver$AndroidPhotosDiscovery_release() {
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
        }
        return contentResolver;
    }

    @NotNull
    public final List<DedupeStage> getDedupeStages$AndroidPhotosDiscovery_release() {
        List<DedupeStage> list = this.dedupeStages;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dedupeStages");
        }
        return list;
    }

    @NotNull
    public final Discovery getDiscovery$AndroidPhotosDiscovery_release() {
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        return discovery;
    }

    @NotNull
    public final FileUtils getFileUtils$AndroidPhotosDiscovery_release() {
        FileUtils fileUtils = this.fileUtils;
        if (fileUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileUtils");
        }
        return fileUtils;
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
        return ConstantsKt.MONITOR_WORKER;
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
        List listOf;
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger.i(ConstantsKt.MONITOR_WORKER, "Worker started.");
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MonitorWorkerStarted, MetricRecordingType.STANDARD);
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        if (discovery.getOperations().scanWorkPlanned()) {
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordSimpleEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MonitorWorkRetryAfterDiscovery, MetricRecordingType.STANDARD);
            ListenableWorker.Result retry = ListenableWorker.Result.retry();
            Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
            return retry;
        }
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        if (!workerHelper.isPermissionGranted$AndroidPhotosDiscovery_release(this.context, "android.permission.READ_EXTERNAL_STORAGE")) {
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger2.e(ConstantsKt.MONITOR_WORKER, "No file read permission.");
            Metrics metrics3 = this.metrics;
            if (metrics3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            return WorkerUtilKt.recordAbortErrorAndFailWorker(metrics3, ConstantsKt.MONITOR_WORKER, DiscoveryMetricsKt.STORAGE_PERMISSION_FAILURE);
        }
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        if (!sharedPreferences.contains(ScanAddedWorkerKt.PREFERENCE_PHOTO_LAST_ADDED_ROW_ID)) {
            SharedPreferences sharedPreferences2 = this.sharedPreferences;
            if (sharedPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            }
            if (!sharedPreferences2.contains(ScanAddedWorkerKt.PREFERENCE_VIDEO_LAST_ADDED_ROW_ID)) {
                Metrics metrics4 = this.metrics;
                if (metrics4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics4.recordSimpleEvent(ConstantsKt.MONITOR_WORKER, DiscoveryMetrics.MonitorWorkRetryAfterScanned, MetricRecordingType.STANDARD);
                ListenableWorker.Result retry2 = ListenableWorker.Result.retry();
                Intrinsics.checkExpressionValueIsNotNull(retry2, "Result.retry()");
                return retry2;
            }
        }
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        long elapsedRealTimeMillis = systemUtil.elapsedRealTimeMillis();
        Metrics metrics5 = this.metrics;
        if (metrics5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            int unifiedItemCount = workerDao.getUnifiedItemCount();
            recordCloudComparison(unifiedItemCount);
            recordStageComparison(unifiedItemCount);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_UNIFIED_ITEM_COUNT, metrics5, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
        }
        MediaStoreUtil mediaStoreUtil = this.mediaStoreUtil;
        if (mediaStoreUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
        }
        Metrics metrics6 = this.metrics;
        if (metrics6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        ImageSource imageSource = new ImageSource(0L, contentResolver, mediaStoreUtil, metrics6);
        try {
            ContentResolver contentResolver2 = this.contentResolver;
            if (contentResolver2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
            }
            MediaStoreUtil mediaStoreUtil2 = this.mediaStoreUtil;
            if (mediaStoreUtil2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
            }
            Metrics metrics7 = this.metrics;
            if (metrics7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            DiscoveryConfiguration discoveryConfiguration = this.configuration;
            if (discoveryConfiguration == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            }
            VideoSource videoSource = new VideoSource(0L, contentResolver2, mediaStoreUtil2, metrics7, discoveryConfiguration.getCrashReporter());
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new CursorItemSource[]{imageSource, videoSource});
            CompositeItemSource compositeItemSource = new CompositeItemSource(listOf);
            recordLocalMediaStoreComparison(compositeItemSource.getItemCount());
            recordCameraDirectoryComparison();
            recordWorkInfoMetrics();
            recordLocalItemDataAnalysis(imageSource.getItemCount(), videoSource.getItemCount());
            recordMissingMediaStoreItem(compositeItemSource);
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(videoSource, null);
            Unit unit3 = Unit.INSTANCE;
            CloseableKt.closeFinally(imageSource, null);
            Discovery discovery2 = this.discovery;
            if (discovery2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("discovery");
            }
            DiscoveryOperations.enqueueMonitorWorkIfNeeded$default(discovery2.getOperations(), false, 1, null);
            Metrics metrics8 = this.metrics;
            if (metrics8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            DiscoveryMetrics discoveryMetrics = DiscoveryMetrics.MonitorWorkerComplete;
            SystemUtil systemUtil2 = this.systemUtil;
            if (systemUtil2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
            }
            metrics8.recordSimpleDuration(ConstantsKt.MONITOR_WORKER, discoveryMetrics, systemUtil2.elapsedRealTimeMillis() - elapsedRealTimeMillis);
            Logger logger3 = this.logger;
            if (logger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger3.i(ConstantsKt.MONITOR_WORKER, "Worker stopped.");
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
            return success;
        } finally {
        }
    }

    public final void setConfiguration$AndroidPhotosDiscovery_release(@NotNull DiscoveryConfiguration discoveryConfiguration) {
        Intrinsics.checkParameterIsNotNull(discoveryConfiguration, "<set-?>");
        this.configuration = discoveryConfiguration;
    }

    public final void setContentResolver$AndroidPhotosDiscovery_release(@NotNull ContentResolver contentResolver) {
        Intrinsics.checkParameterIsNotNull(contentResolver, "<set-?>");
        this.contentResolver = contentResolver;
    }

    public final void setDedupeStages$AndroidPhotosDiscovery_release(@NotNull List<DedupeStage> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.dedupeStages = list;
    }

    public final void setDiscovery$AndroidPhotosDiscovery_release(@NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(discovery, "<set-?>");
        this.discovery = discovery;
    }

    public final void setFileUtils$AndroidPhotosDiscovery_release(@NotNull FileUtils fileUtils) {
        Intrinsics.checkParameterIsNotNull(fileUtils, "<set-?>");
        this.fileUtils = fileUtils;
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
