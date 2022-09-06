package com.amazon.photos.autosave.internal.workers;

import android.content.SharedPreferences;
import androidx.work.ListenableWorker;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dagger.AutosaveManagerMap;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.AutosaveMetricEvents;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import com.amazon.photos.autosave.internal.upload.AutosaveUploadConfigurationProvider;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.autosave.internal.utils.FetchResult;
import com.amazon.photos.autosave.internal.utils.FetchSuccess;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.autosave.internal.utils.TraverseOperations;
import com.amazon.photos.autosave.internal.workers.AutosaveWorker;
import com.amazon.photos.autosave.model.AutosaveItem;
import com.amazon.photos.autosave.model.AutosaveState;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.autosave.model.ModelExtensionsKt;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadStrategy;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u0083\u00012\u00020\u0001:\u0004\u0083\u0001\u0084\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010r\u001a\u00020s2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\bH\u0002J\u001c\u0010v\u001a\b\u0012\u0004\u0012\u00020u0\b2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020u0\bH\u0002J\b\u0010x\u001a\u00020BH\u0014J\u0006\u0010y\u001a\u00020\tJ\b\u0010z\u001a\u00020:H\u0014J\b\u0010{\u001a\u00020sH\u0014J\u0011\u0010|\u001a\u00020}H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010~J!\u0010\u007f\u001a\u00030\u0080\u00012\f\u0010w\u001a\b\u0012\u0004\u0012\u00020u0\b2\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0015@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u001b@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\"\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020!@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010(\u001a\u00020'2\u0006\u0010\u000e\u001a\u00020'@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010/\u001a\u00020.2\u0006\u0010\u000e\u001a\u00020.@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001b\u00104\u001a\u0002058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u0010\r\u001a\u0004\b6\u00107R\u000e\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010=\u001a\u00020<2\u0006\u0010\u000e\u001a\u00020<@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010C\u001a\u00020B2\u0006\u0010\u000e\u001a\u00020B@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR$\u0010I\u001a\u00020H2\u0006\u0010\u000e\u001a\u00020H@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR!\u0010N\u001a\b\u0012\u0004\u0012\u00020P0O8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bS\u0010\r\u001a\u0004\bQ\u0010RR$\u0010U\u001a\u00020T2\u0006\u0010\u000e\u001a\u00020T@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR$\u0010[\u001a\u00020Z2\u0006\u0010\u000e\u001a\u00020Z@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R$\u0010a\u001a\u00020`2\u0006\u0010\u000e\u001a\u00020`@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR$\u0010g\u001a\u00020f2\u0006\u0010\u000e\u001a\u00020f@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR$\u0010m\u001a\u00020l2\u0006\u0010\u000e\u001a\u00020l@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010o\"\u0004\bp\u0010q\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0085\u0001"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/AutosaveWorker;", "Lcom/amazon/photos/autosave/internal/workers/BaseWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "autoSaveEnabledFolderIds", "", "", "getAutoSaveEnabledFolderIds", "()Ljava/util/List;", "autoSaveEnabledFolderIds$delegate", "Lkotlin/Lazy;", "<set-?>", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "autosaveBucketDao", "getAutosaveBucketDao", "()Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "setAutosaveBucketDao", "(Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;)V", "Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;", "autosaveEventNotifier", "getAutosaveEventNotifier", "()Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;", "setAutosaveEventNotifier", "(Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;)V", "Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "autosaveItemDao", "getAutosaveItemDao", "()Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "setAutosaveItemDao", "(Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;)V", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "autosaveOperations", "getAutosaveOperations", "()Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "setAutosaveOperations", "(Lcom/amazon/photos/autosave/internal/AutosaveOperations;)V", "Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "autosavePreferences", "getAutosavePreferences", "()Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "setAutosavePreferences", "(Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;)V", "dedupedLocalItems", "Lcom/amazon/photos/discovery/Discovery;", "discovery", "getDiscovery", "()Lcom/amazon/photos/discovery/Discovery;", "setDiscovery", "(Lcom/amazon/photos/discovery/Discovery;)V", "handleMediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "getHandleMediaType", "()Lcom/amazon/photos/autosave/model/MediaType;", "handleMediaType$delegate", "handleMediaTypeStr", "", "hashedDirectedId", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "logger", "getLogger", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;", "metricsHelper", "getMetricsHelper", "()Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;", "setMetricsHelper", "(Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;)V", "requestedItemType", "", "Lcom/amazon/photos/discovery/model/ItemType;", "getRequestedItemType", "()Ljava/util/Set;", "requestedItemType$delegate", "Landroid/content/SharedPreferences;", "sharedPreferences", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "setSharedPreferences", "(Landroid/content/SharedPreferences;)V", "Lcom/amazon/photos/autosave/internal/utils/SystemUtil;", "systemUtil", "getSystemUtil", "()Lcom/amazon/photos/autosave/internal/utils/SystemUtil;", "setSystemUtil", "(Lcom/amazon/photos/autosave/internal/utils/SystemUtil;)V", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "transactionRunner", "getTransactionRunner", "()Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "setTransactionRunner", "(Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;)V", "Lcom/amazon/photos/autosave/internal/upload/UploadHelper;", "uploadHelper", "getUploadHelper", "()Lcom/amazon/photos/autosave/internal/upload/UploadHelper;", "setUploadHelper", "(Lcom/amazon/photos/autosave/internal/upload/UploadHelper;)V", "Lcom/amazon/photos/uploader/UploadManager;", "uploadManager", "getUploadManager", "()Lcom/amazon/photos/uploader/UploadManager;", "setUploadManager", "(Lcom/amazon/photos/uploader/UploadManager;)V", "enqueueLocalItems", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "Lcom/amazon/photos/discovery/model/LocalItem;", "getLocalItemsNewToAutosave", "localItems", "getMetricsObj", "getNow", "getTag", "injectMethod", "mainTask", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadLocalItemIfNeeded", "", "isFullScan", "", "Companion", "ScanLocalOperations", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveWorker extends BaseWorker {
    public static final Companion Companion = new Companion(null);
    private static final int DEFAULT_DEDUPE_STAGE = 0;
    @NotNull
    public static final String TAG = "AutosaveWorker";
    private final Lazy autoSaveEnabledFolderIds$delegate;
    @NotNull
    public AutosaveBucketDao autosaveBucketDao;
    @NotNull
    public AutosaveEventNotifier autosaveEventNotifier;
    @NotNull
    public AutosaveItemDao autosaveItemDao;
    @NotNull
    public AutosaveOperations autosaveOperations;
    @NotNull
    public InternalPreferences autosavePreferences;
    private final List<Long> dedupedLocalItems;
    @NotNull
    public Discovery discovery;
    private final Lazy handleMediaType$delegate;
    private final String handleMediaTypeStr;
    private final String hashedDirectedId;
    @NotNull
    public Logger logger;
    @NotNull
    public Metrics metrics;
    @NotNull
    public MetricsHelper metricsHelper;
    private final Lazy requestedItemType$delegate;
    @NotNull
    public SharedPreferences sharedPreferences;
    @NotNull
    public SystemUtil systemUtil;
    @NotNull
    public AutosaveTransactionRunner transactionRunner;
    @NotNull
    public UploadHelper uploadHelper;
    @NotNull
    public UploadManager uploadManager;

    /* compiled from: AutosaveWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/AutosaveWorker$Companion;", "", "()V", "DEFAULT_DEDUPE_STAGE", "", "TAG", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AutosaveWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010)\u001a\u0004\u0018\u00010\u00022\f\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u0004H\u0016J\b\u0010,\u001a\u00020\u0002H\u0016J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00020.H\u0016J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020.H\u0002J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020\u00020.H\u0002J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020\u0002H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u0015R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0018\"\u0004\b(\u0010\u001a¨\u00064"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/AutosaveWorker$ScanLocalOperations;", "Lcom/amazon/photos/autosave/internal/utils/TraverseOperations;", "Landroidx/work/ListenableWorker$Result;", "dedupedLocalItems", "", "", "(Lcom/amazon/photos/autosave/internal/workers/AutosaveWorker;Ljava/util/List;)V", "getDedupedLocalItems", "()Ljava/util/List;", "finalDedupeStageId", "", "getFinalDedupeStageId", "()I", "inputLocalItems", "getInputLocalItems", "isFullScan", "", "()Z", "lastInputItemListIndex", "getLastInputItemListIndex", "setLastInputItemListIndex", "(I)V", "lastProcessedId", "getLastProcessedId", "()J", "setLastProcessedId", "(J)V", "localItemDao", "Lcom/amazon/photos/discovery/dao/LocalItemDao;", "getLocalItemDao", "()Lcom/amazon/photos/discovery/dao/LocalItemDao;", "newItemCount", "getNewItemCount", "setNewItemCount", "unifiedItemDao", "Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "getUnifiedItemDao", "()Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "workStartTimeMs", "getWorkStartTimeMs", "setWorkStartTimeMs", "batchOperation", "unifiedItems", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "completed", "fetchOneBatch", "Lcom/amazon/photos/autosave/internal/utils/FetchResult;", "fetchOneBatchInFullScan", "fetchOneBatchInGivenItems", "init", "", "stopped", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class ScanLocalOperations implements TraverseOperations<ListenableWorker.Result> {
        @NotNull
        private final List<Long> dedupedLocalItems;
        private final int finalDedupeStageId;
        @NotNull
        private final List<List<Long>> inputLocalItems;
        private final boolean isFullScan;
        private int lastInputItemListIndex;
        private long lastProcessedId;
        @NotNull
        private final LocalItemDao localItemDao;
        private int newItemCount;
        final /* synthetic */ AutosaveWorker this$0;
        @NotNull
        private final UnifiedItemDao unifiedItemDao;
        private long workStartTimeMs;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes13.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MediaType.values().length];

            static {
                $EnumSwitchMapping$0[MediaType.PHOTO.ordinal()] = 1;
                $EnumSwitchMapping$0[MediaType.VIDEO.ordinal()] = 2;
            }
        }

        public ScanLocalOperations(@NotNull AutosaveWorker autosaveWorker, List<Long> dedupedLocalItems) {
            Intrinsics.checkParameterIsNotNull(dedupedLocalItems, "dedupedLocalItems");
            this.this$0 = autosaveWorker;
            this.dedupedLocalItems = dedupedLocalItems;
            this.isFullScan = this.dedupedLocalItems.isEmpty();
            List<List<Long>> partition = Lists.partition(this.dedupedLocalItems, 200);
            Intrinsics.checkExpressionValueIsNotNull(partition, "Lists.partition(dedupedLocalItems, BATCH_SIZE)");
            Intrinsics.checkExpressionValueIsNotNull(partition, "dedupedLocalItems.let {\n…ms, BATCH_SIZE)\n        }");
            this.inputLocalItems = partition;
            this.localItemDao = autosaveWorker.getDiscovery().getDaos().getLocalItemDao();
            this.unifiedItemDao = autosaveWorker.getDiscovery().getDaos().getUnifiedItemDao();
            DedupeStage dedupeStage = (DedupeStage) CollectionsKt.lastOrNull((List<? extends Object>) autosaveWorker.getDiscovery().getConfiguration().getDedupeStages());
            this.finalDedupeStageId = dedupeStage != null ? dedupeStage.getStageId() : 0;
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List, T] */
        private final FetchResult<ListenableWorker.Result> fetchOneBatchInFullScan() {
            List emptyList;
            ?? emptyList2;
            if (!this.this$0.getAutoSaveEnabledFolderIds().isEmpty() && !this.this$0.getRequestedItemType().isEmpty()) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                emptyList2 = CollectionsKt__CollectionsKt.emptyList();
                objectRef.element = emptyList2;
                if (this.this$0.isStopped()) {
                    Logger logger = this.this$0.getLogger();
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutosaveWorker ");
                    outline107.append(this.this$0.handleMediaTypeStr);
                    outline107.append(" cancelled");
                    logger.i(AutosaveWorker.TAG, outline107.toString());
                    return new FetchSuccess((List) objectRef.element);
                }
                Logger logger2 = this.this$0.getLogger();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Folders enabled for Autosave count: ");
                outline1072.append(this.this$0.getAutoSaveEnabledFolderIds().size());
                logger2.i(AutosaveWorker.TAG, outline1072.toString());
                this.this$0.getMetricsHelper().logMetricWithCount$AndroidPhotosAutosave_release(AutosaveWorker.TAG, AutosaveMetricEvents.COUNT_FOLDERS_ENABLED, this.this$0.getAutoSaveEnabledFolderIds().size());
                this.this$0.getTransactionRunner().runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.workers.AutosaveWorker$ScanLocalOperations$fetchOneBatchInFullScan$1
                    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.List, T] */
                    @Override // java.lang.Runnable
                    public final void run() {
                        objectRef.element = AutosaveWorker.ScanLocalOperations.this.getUnifiedItemDao().getAllUnifiedItemsInFoldersByBatch(AutosaveWorker.ScanLocalOperations.this.getLastProcessedId(), AutosaveWorker.ScanLocalOperations.this.this$0.getAutoSaveEnabledFolderIds(), AutosaveWorker.ScanLocalOperations.this.getFinalDedupeStageId(), 200, AutosaveWorker.ScanLocalOperations.this.this$0.getRequestedItemType());
                    }
                });
                if (!((List) objectRef.element).isEmpty()) {
                    this.lastProcessedId = ((UnifiedItem) CollectionsKt.last((List<? extends Object>) objectRef.element)).getId();
                }
                Logger logger3 = this.this$0.getLogger();
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Autosave");
                outline1073.append(this.this$0.handleMediaTypeStr);
                outline1073.append(" Discovered new items batch size: ");
                outline1073.append(((List) objectRef.element).size());
                outline1073.append('.');
                logger3.i(AutosaveWorker.TAG, outline1073.toString());
                this.this$0.getMetricsHelper().logMetricWithCount$AndroidPhotosAutosave_release(AutosaveWorker.TAG, AutosaveMetricEvents.AUTOSAVE_FULL_SCAN_NEW_BATCH_SIZE, ((List) objectRef.element).size());
                return new FetchSuccess((List) objectRef.element);
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return new FetchSuccess(emptyList);
        }

        private final FetchResult<ListenableWorker.Result> fetchOneBatchInGivenItems() {
            List emptyList;
            int collectionSizeOrDefault;
            List<UnifiedItem> emptyList2;
            List emptyList3;
            if (!this.this$0.getAutoSaveEnabledFolderIds().isEmpty() && !this.this$0.getRequestedItemType().isEmpty()) {
                if (this.lastInputItemListIndex >= this.inputLocalItems.size()) {
                    emptyList3 = CollectionsKt__CollectionsKt.emptyList();
                    return new FetchSuccess(emptyList3);
                }
                List<Long> list = this.inputLocalItems.get(this.lastInputItemListIndex);
                List<LocalItem> localItemByIds = list.isEmpty() ^ true ? this.localItemDao.getLocalItemByIds(list) : CollectionsKt__CollectionsKt.emptyList();
                Logger logger = this.this$0.getLogger();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave");
                outline107.append(this.this$0.handleMediaTypeStr);
                outline107.append(" input local items for Upload batch size: ");
                outline107.append(localItemByIds.size());
                logger.i(AutosaveWorker.TAG, outline107.toString());
                this.this$0.getMetricsHelper().logMetricWithCount$AndroidPhotosAutosave_release(AutosaveWorker.TAG, AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_BATCH_SIZE, localItemByIds.size());
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(localItemByIds, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (LocalItem localItem : localItemByIds) {
                    arrayList.add(Long.valueOf(localItem.getUnifiedId()));
                }
                if (!arrayList.isEmpty()) {
                    emptyList2 = this.unifiedItemDao.getUnifiedItemsByIdsAndFolders(this.this$0.getAutoSaveEnabledFolderIds(), arrayList, this.finalDedupeStageId, this.this$0.getRequestedItemType());
                } else {
                    emptyList2 = CollectionsKt__CollectionsKt.emptyList();
                }
                this.lastInputItemListIndex++;
                return new FetchSuccess(emptyList2);
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return new FetchSuccess(emptyList);
        }

        @Override // com.amazon.photos.autosave.internal.utils.TraverseOperations
        /* renamed from: batchOperation  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ListenableWorker.Result mo4293batchOperation(List list) {
            return mo4293batchOperation((List<UnifiedItem>) list);
        }

        @Override // com.amazon.photos.autosave.internal.utils.TraverseOperations
        @NotNull
        public FetchResult<ListenableWorker.Result> fetchOneBatch() {
            return this.isFullScan ? fetchOneBatchInFullScan() : fetchOneBatchInGivenItems();
        }

        @NotNull
        public final List<Long> getDedupedLocalItems() {
            return this.dedupedLocalItems;
        }

        public final int getFinalDedupeStageId() {
            return this.finalDedupeStageId;
        }

        @NotNull
        public final List<List<Long>> getInputLocalItems() {
            return this.inputLocalItems;
        }

        public final int getLastInputItemListIndex() {
            return this.lastInputItemListIndex;
        }

        public final long getLastProcessedId() {
            return this.lastProcessedId;
        }

        @NotNull
        public final LocalItemDao getLocalItemDao() {
            return this.localItemDao;
        }

        public final int getNewItemCount() {
            return this.newItemCount;
        }

        @NotNull
        public final UnifiedItemDao getUnifiedItemDao() {
            return this.unifiedItemDao;
        }

        public final long getWorkStartTimeMs() {
            return this.workStartTimeMs;
        }

        @Override // com.amazon.photos.autosave.internal.utils.TraverseOperations
        public void init() {
            Logger logger = this.this$0.getLogger();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave");
            outline107.append(this.this$0.handleMediaTypeStr);
            outline107.append(" started.");
            logger.i(AutosaveWorker.TAG, outline107.toString());
            this.workStartTimeMs = this.this$0.getNow();
        }

        public final boolean isFullScan() {
            return this.isFullScan;
        }

        public final void setLastInputItemListIndex(int i) {
            this.lastInputItemListIndex = i;
        }

        public final void setLastProcessedId(long j) {
            this.lastProcessedId = j;
        }

        public final void setNewItemCount(int i) {
            this.newItemCount = i;
        }

        public final void setWorkStartTimeMs(long j) {
            this.workStartTimeMs = j;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.autosave.internal.utils.TraverseOperations
        @Nullable
        /* renamed from: batchOperation */
        public ListenableWorker.Result mo4293batchOperation(@NotNull List<UnifiedItem> unifiedItems) {
            Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
            if (this.this$0.isStopped()) {
                Logger logger = this.this$0.getLogger();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutosaveWorker ");
                outline107.append(this.this$0.handleMediaTypeStr);
                outline107.append(" cancelled");
                logger.i(AutosaveWorker.TAG, outline107.toString());
                return ListenableWorker.Result.success();
            }
            ArrayList arrayList = new ArrayList();
            for (UnifiedItem unifiedItem : unifiedItems) {
                LocalItem localItem = (LocalItem) CollectionsKt.firstOrNull((List<? extends Object>) unifiedItem.getLocalItems());
                if (localItem != null) {
                    arrayList.add(localItem);
                }
            }
            Logger logger2 = this.this$0.getLogger();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Autosave");
            outline1072.append(this.this$0.handleMediaTypeStr);
            outline1072.append(" eligible Discovery items ");
            outline1072.append("for Upload batch size: ");
            outline1072.append(arrayList.size());
            logger2.i(AutosaveWorker.TAG, outline1072.toString());
            this.this$0.getMetricsHelper().logMetricWithCount$AndroidPhotosAutosave_release(AutosaveWorker.TAG, AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_TO_UPLOAD_BATCH_SIZE, arrayList.size());
            this.newItemCount = this.this$0.uploadLocalItemIfNeeded(arrayList, this.isFullScan) + this.newItemCount;
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.autosave.internal.utils.TraverseOperations
        @NotNull
        /* renamed from: completed */
        public ListenableWorker.Result mo4294completed() {
            String str;
            Logger logger = this.this$0.getLogger();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave");
            outline107.append(this.this$0.handleMediaTypeStr);
            outline107.append(" completed. isFullScan: ");
            outline107.append(this.isFullScan);
            outline107.append('.');
            logger.i(AutosaveWorker.TAG, outline107.toString());
            this.this$0.getMetricsHelper().logMetricWithCountAndTimer$AndroidPhotosAutosave_release(AutosaveWorker.TAG, this.isFullScan ? AutosaveMetricEvents.AUTOSAVE_FULL_SCAN_UPLOAD_COMPLETE : AutosaveMetricEvents.AUTOSAVE_INPUT_ITEMS_UPLOAD_COMPLETE, this.newItemCount, this.this$0.getNow() - this.workStartTimeMs);
            if (this.isFullScan && !this.this$0.isStopped()) {
                SharedPreferences.Editor edit = this.this$0.getSharedPreferences().edit();
                int i = WhenMappings.$EnumSwitchMapping$0[this.this$0.getHandleMediaType().ordinal()];
                if (i == 1) {
                    str = PreferenceUploadQueueHelper.FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_PHOTOS;
                } else if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                } else {
                    str = PreferenceUploadQueueHelper.FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_VIDEOS;
                }
                edit.putBoolean(str, true).apply();
                if (this.this$0.getAutosaveItemDao().getCountForState(AutosaveState.QUEUED) == 0) {
                    this.this$0.getAutosaveEventNotifier().onNoUploadsScanComplete$AndroidPhotosAutosave_release();
                }
            }
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
            return success;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.photos.autosave.internal.utils.TraverseOperations
        @NotNull
        /* renamed from: stopped */
        public ListenableWorker.Result mo4295stopped() {
            Logger logger = this.this$0.getLogger();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave");
            outline107.append(this.this$0.handleMediaTypeStr);
            outline107.append(" stopped. isFullScan: ");
            outline107.append(this.isFullScan);
            logger.i(AutosaveWorker.TAG, outline107.toString());
            this.this$0.getMetricsHelper().logMetricWithCountAndTimer$AndroidPhotosAutosave_release(AutosaveWorker.TAG, this.isFullScan ? AutosaveMetricEvents.AUTOSAVE_FULL_SCAN_UPLOAD_STOP : AutosaveMetricEvents.AUTOSAVE_INPUT_ITEMS_UPLOAD_STOP, this.newItemCount, this.this$0.getNow() - this.workStartTimeMs);
            ListenableWorker.Result retry = ListenableWorker.Result.retry();
            Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
            return retry;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0040, code lost:
        r2 = kotlin.collections.ArraysKt___ArraysJvmKt.asList(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AutosaveWorker(@org.jetbrains.annotations.NotNull android.content.Context r2, @org.jetbrains.annotations.NotNull androidx.work.WorkerParameters r3) {
        /*
            r1 = this;
            java.lang.String r0 = "appContext"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "workerParams"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            r1.<init>(r2, r3)
            androidx.work.Data r2 = r3.getInputData()
            java.lang.String r0 = "HASHED_DIRECTED_ID_KEY"
            java.lang.String r2 = r2.getString(r0)
            if (r2 == 0) goto L6c
            r1.hashedDirectedId = r2
            androidx.work.Data r2 = r3.getInputData()
            java.lang.String r0 = "HANDLE_MEDIA_TYPE"
            java.lang.String r2 = r2.getString(r0)
            if (r2 == 0) goto L64
            r1.handleMediaTypeStr = r2
            com.amazon.photos.autosave.internal.workers.AutosaveWorker$handleMediaType$2 r2 = new com.amazon.photos.autosave.internal.workers.AutosaveWorker$handleMediaType$2
            r2.<init>(r1)
            kotlin.Lazy r2 = kotlin.LazyKt.lazy(r2)
            r1.handleMediaType$delegate = r2
            androidx.work.Data r2 = r3.getInputData()
            java.lang.String r3 = "DEDUPED_ITEM_IDS"
            long[] r2 = r2.getLongArray(r3)
            if (r2 == 0) goto L47
            java.util.List r2 = kotlin.collections.ArraysKt.asList(r2)
            if (r2 == 0) goto L47
            goto L4b
        L47:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L4b:
            r1.dedupedLocalItems = r2
            com.amazon.photos.autosave.internal.workers.AutosaveWorker$requestedItemType$2 r2 = new com.amazon.photos.autosave.internal.workers.AutosaveWorker$requestedItemType$2
            r2.<init>(r1)
            kotlin.Lazy r2 = kotlin.LazyKt.lazy(r2)
            r1.requestedItemType$delegate = r2
            com.amazon.photos.autosave.internal.workers.AutosaveWorker$autoSaveEnabledFolderIds$2 r2 = new com.amazon.photos.autosave.internal.workers.AutosaveWorker$autoSaveEnabledFolderIds$2
            r2.<init>(r1)
            kotlin.Lazy r2 = kotlin.LazyKt.lazy(r2)
            r1.autoSaveEnabledFolderIds$delegate = r2
            return
        L64:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "No handle MediaType associated with worker."
            r2.<init>(r3)
            throw r2
        L6c:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "No hashed directed id associated with worker."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.autosave.internal.workers.AutosaveWorker.<init>(android.content.Context, androidx.work.WorkerParameters):void");
    }

    private final void enqueueLocalItems(List<LocalItem> list) {
        if (list.isEmpty()) {
            return;
        }
        InternalPreferences internalPreferences = this.autosavePreferences;
        if (internalPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosavePreferences");
        }
        boolean isAddToFamilyEnabled = internalPreferences.isAddToFamilyEnabled();
        if (isAddToFamilyEnabled) {
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics.recordSimpleEvent(TAG, AutosaveWorker$enqueueLocalItems$1.INSTANCE, new MetricRecordingType[0]);
        }
        UploadHelper uploadHelper = this.uploadHelper;
        if (uploadHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadHelper");
        }
        List<UploadRequest> createUploadRequests$AndroidPhotosAutosave_release = uploadHelper.createUploadRequests$AndroidPhotosAutosave_release(list, MediaType.PHOTO, isAddToFamilyEnabled);
        UploadHelper uploadHelper2 = this.uploadHelper;
        if (uploadHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadHelper");
        }
        List<UploadRequest> createUploadRequests$AndroidPhotosAutosave_release2 = uploadHelper2.createUploadRequests$AndroidPhotosAutosave_release(list, MediaType.VIDEO, isAddToFamilyEnabled);
        if (isStopped()) {
            return;
        }
        if (!createUploadRequests$AndroidPhotosAutosave_release.isEmpty()) {
            Logger logger = this.logger;
            if (logger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave Upload Photos Requests scheduled: ");
            outline107.append(createUploadRequests$AndroidPhotosAutosave_release.size());
            logger.i(TAG, outline107.toString());
            MetricsHelper metricsHelper = this.metricsHelper;
            if (metricsHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
            }
            metricsHelper.logMetricWithCount$AndroidPhotosAutosave_release(TAG, AutosaveMetricEvents.COUNT_PHOTO_REQUESTS_SCHEDULED, createUploadRequests$AndroidPhotosAutosave_release.size());
            UploadManager uploadManager = this.uploadManager;
            if (uploadManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uploadManager");
            }
            uploadManager.getOperations().scheduleUploads(createUploadRequests$AndroidPhotosAutosave_release, AutosaveUploadConfigurationProvider.PHOTOS_QUEUE, UploadStrategy.KEEP_EXISTING).waitForResult();
        }
        if (!(!createUploadRequests$AndroidPhotosAutosave_release2.isEmpty())) {
            return;
        }
        Logger logger2 = this.logger;
        if (logger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Autosave Upload Videos Requests scheduled: ");
        outline1072.append(createUploadRequests$AndroidPhotosAutosave_release2.size());
        logger2.i(TAG, outline1072.toString());
        MetricsHelper metricsHelper2 = this.metricsHelper;
        if (metricsHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
        }
        metricsHelper2.logMetricWithCount$AndroidPhotosAutosave_release(TAG, AutosaveMetricEvents.COUNT_VIDEO_REQUESTS_SCHEDULED, createUploadRequests$AndroidPhotosAutosave_release2.size());
        UploadManager uploadManager2 = this.uploadManager;
        if (uploadManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadManager");
        }
        uploadManager2.getOperations().scheduleUploads(createUploadRequests$AndroidPhotosAutosave_release2, AutosaveUploadConfigurationProvider.VIDEOS_QUEUE, UploadStrategy.KEEP_EXISTING).waitForResult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Long> getAutoSaveEnabledFolderIds() {
        return (List) this.autoSaveEnabledFolderIds$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaType getHandleMediaType() {
        return (MediaType) this.handleMediaType$delegate.getValue();
    }

    private final List<LocalItem> getLocalItemsNewToAutosave(List<LocalItem> list) {
        int collectionSizeOrDefault;
        final ArrayList arrayList = new ArrayList();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        final ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (LocalItem localItem : list) {
            arrayList2.add(Long.valueOf(localItem.getId()));
        }
        if (!arrayList2.isEmpty()) {
            AutosaveTransactionRunner autosaveTransactionRunner = this.transactionRunner;
            if (autosaveTransactionRunner == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
            }
            autosaveTransactionRunner.runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.workers.AutosaveWorker$getLocalItemsNewToAutosave$1
                @Override // java.lang.Runnable
                public final void run() {
                    int collectionSizeOrDefault2;
                    List list2 = arrayList;
                    List<AutosaveItem> itemsByLocalIds = AutosaveWorker.this.getAutosaveItemDao().getItemsByLocalIds(arrayList2);
                    collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(itemsByLocalIds, 10);
                    ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault2);
                    for (AutosaveItem autosaveItem : itemsByLocalIds) {
                        arrayList3.add(Long.valueOf(autosaveItem.getLocalItemId()));
                    }
                    list2.addAll(arrayList3);
                }
            });
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : list) {
            if (!arrayList.contains(Long.valueOf(((LocalItem) obj).getId()))) {
                arrayList3.add(obj);
            }
        }
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<ItemType> getRequestedItemType() {
        return (Set) this.requestedItemType$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int uploadLocalItemIfNeeded(List<LocalItem> list, boolean z) {
        if (!z) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                LocalItem localItem = (LocalItem) obj;
                if (getRequestedItemType().contains(localItem.getType()) && getAutoSaveEnabledFolderIds().contains(Long.valueOf(localItem.getParentId()))) {
                    arrayList.add(obj);
                }
            }
            list = arrayList;
        }
        final List<LocalItem> localItemsNewToAutosave = getLocalItemsNewToAutosave(list);
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave local items batch size eligible for upload: ");
        outline107.append(localItemsNewToAutosave.size());
        outline107.append('.');
        logger.i(TAG, outline107.toString());
        MetricsHelper metricsHelper = this.metricsHelper;
        if (metricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
        }
        metricsHelper.logMetricWithCount$AndroidPhotosAutosave_release(TAG, z ? AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_UPLOAD_NEW_BATCH_SIZE : AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_NEW_BATCH_SIZE, localItemsNewToAutosave.size());
        if (!localItemsNewToAutosave.isEmpty()) {
            AutosaveTransactionRunner autosaveTransactionRunner = this.transactionRunner;
            if (autosaveTransactionRunner == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
            }
            autosaveTransactionRunner.runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.workers.AutosaveWorker$uploadLocalItemIfNeeded$1
                @Override // java.lang.Runnable
                public final void run() {
                    for (LocalItem localItem2 : localItemsNewToAutosave) {
                        AutosaveItem autosaveItem$default = ModelExtensionsKt.toAutosaveItem$default(localItem2, null, 1, null);
                        if (autosaveItem$default != null) {
                            AutosaveWorker.this.getAutosaveItemDao().insertItem(autosaveItem$default);
                        }
                    }
                }
            });
            enqueueLocalItems(localItemsNewToAutosave);
        }
        return localItemsNewToAutosave.size();
    }

    @NotNull
    public final AutosaveBucketDao getAutosaveBucketDao() {
        AutosaveBucketDao autosaveBucketDao = this.autosaveBucketDao;
        if (autosaveBucketDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveBucketDao");
        }
        return autosaveBucketDao;
    }

    @NotNull
    public final AutosaveEventNotifier getAutosaveEventNotifier() {
        AutosaveEventNotifier autosaveEventNotifier = this.autosaveEventNotifier;
        if (autosaveEventNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveEventNotifier");
        }
        return autosaveEventNotifier;
    }

    @NotNull
    public final AutosaveItemDao getAutosaveItemDao() {
        AutosaveItemDao autosaveItemDao = this.autosaveItemDao;
        if (autosaveItemDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveItemDao");
        }
        return autosaveItemDao;
    }

    @NotNull
    public final AutosaveOperations getAutosaveOperations() {
        AutosaveOperations autosaveOperations = this.autosaveOperations;
        if (autosaveOperations == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveOperations");
        }
        return autosaveOperations;
    }

    @NotNull
    public final InternalPreferences getAutosavePreferences() {
        InternalPreferences internalPreferences = this.autosavePreferences;
        if (internalPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosavePreferences");
        }
        return internalPreferences;
    }

    @NotNull
    public final Discovery getDiscovery() {
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        return discovery;
    }

    @NotNull
    public final Logger getLogger() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return logger;
    }

    @NotNull
    public final Metrics getMetrics() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @NotNull
    public final MetricsHelper getMetricsHelper() {
        MetricsHelper metricsHelper = this.metricsHelper;
        if (metricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
        }
        return metricsHelper;
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
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
        return systemUtil.elapsedRealTimeMillis$AndroidPhotosAutosave_release();
    }

    @NotNull
    public final SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences;
    }

    @NotNull
    public final SystemUtil getSystemUtil() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    @NotNull
    protected String getTag() {
        return TAG;
    }

    @NotNull
    public final AutosaveTransactionRunner getTransactionRunner() {
        AutosaveTransactionRunner autosaveTransactionRunner = this.transactionRunner;
        if (autosaveTransactionRunner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionRunner");
        }
        return autosaveTransactionRunner;
    }

    @NotNull
    public final UploadHelper getUploadHelper() {
        UploadHelper uploadHelper = this.uploadHelper;
        if (uploadHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadHelper");
        }
        return uploadHelper;
    }

    @NotNull
    public final UploadManager getUploadManager() {
        UploadManager uploadManager = this.uploadManager;
        if (uploadManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadManager");
        }
        return uploadManager;
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    protected void injectMethod() {
        AutosaveManagerMap.INSTANCE.getAutosaveManagerForAccount$AndroidPhotosAutosave_release(this.hashedDirectedId).getComponent$AndroidPhotosAutosave_release().inject(this);
    }

    @Override // com.amazon.photos.autosave.internal.workers.BaseWorker
    @Nullable
    protected Object mainTask(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        return CoroutineScopeKt.coroutineScope(new AutosaveWorker$mainTask$2(this, null), continuation);
    }

    @Inject
    public final void setAutosaveBucketDao(@NotNull AutosaveBucketDao autosaveBucketDao) {
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "<set-?>");
        this.autosaveBucketDao = autosaveBucketDao;
    }

    @Inject
    public final void setAutosaveEventNotifier(@NotNull AutosaveEventNotifier autosaveEventNotifier) {
        Intrinsics.checkParameterIsNotNull(autosaveEventNotifier, "<set-?>");
        this.autosaveEventNotifier = autosaveEventNotifier;
    }

    @Inject
    public final void setAutosaveItemDao(@NotNull AutosaveItemDao autosaveItemDao) {
        Intrinsics.checkParameterIsNotNull(autosaveItemDao, "<set-?>");
        this.autosaveItemDao = autosaveItemDao;
    }

    @Inject
    public final void setAutosaveOperations(@NotNull AutosaveOperations autosaveOperations) {
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "<set-?>");
        this.autosaveOperations = autosaveOperations;
    }

    @Inject
    public final void setAutosavePreferences(@NotNull InternalPreferences internalPreferences) {
        Intrinsics.checkParameterIsNotNull(internalPreferences, "<set-?>");
        this.autosavePreferences = internalPreferences;
    }

    @Inject
    public final void setDiscovery(@NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(discovery, "<set-?>");
        this.discovery = discovery;
    }

    @Inject
    public final void setLogger(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    @Inject
    public final void setMetrics(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    @Inject
    public final void setMetricsHelper(@NotNull MetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(metricsHelper, "<set-?>");
        this.metricsHelper = metricsHelper;
    }

    @Inject
    public final void setSharedPreferences(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "<set-?>");
        this.sharedPreferences = sharedPreferences;
    }

    @Inject
    public final void setSystemUtil(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
    }

    @Inject
    public final void setTransactionRunner(@NotNull AutosaveTransactionRunner autosaveTransactionRunner) {
        Intrinsics.checkParameterIsNotNull(autosaveTransactionRunner, "<set-?>");
        this.transactionRunner = autosaveTransactionRunner;
    }

    @Inject
    public final void setUploadHelper(@NotNull UploadHelper uploadHelper) {
        Intrinsics.checkParameterIsNotNull(uploadHelper, "<set-?>");
        this.uploadHelper = uploadHelper;
    }

    @Inject
    public final void setUploadManager(@NotNull UploadManager uploadManager) {
        Intrinsics.checkParameterIsNotNull(uploadManager, "<set-?>");
        this.uploadManager = uploadManager;
    }
}
