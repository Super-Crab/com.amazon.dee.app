package com.amazon.photos.discovery.internal.worker;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryOperationsKt;
import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.dedupe.DeduplicationRequest;
import com.amazon.photos.discovery.dedupe.DeduplicatorResult;
import com.amazon.photos.discovery.dedupe.NoRetryDedupeException;
import com.amazon.photos.discovery.dedupe.RetryDedupeException;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import com.amazon.photos.discovery.internal.util.RetryException;
import com.amazon.photos.discovery.internal.util.UnifiedItemUtils;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* compiled from: DedupeWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010[\u001a\u00020+2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u00162\u0006\u0010^\u001a\u00020\u0010H\u0002J\u0010\u0010_\u001a\u00020`2\u0006\u0010^\u001a\u00020\u0010H\u0002J\u001e\u0010a\u001a\u00020+2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020]0\u00162\u0006\u0010^\u001a\u00020\u0010H\u0002J\u001e\u0010c\u001a\u00020+2\u0006\u0010^\u001a\u00020\u00102\f\u0010b\u001a\b\u0012\u0004\u0012\u00020]0\u0016H\u0002J\b\u0010d\u001a\u000205H\u0014J\b\u0010e\u001a\u00020fH\u0014J\b\u0010g\u001a\u00020hH\u0014J\u0016\u0010i\u001a\u00020+2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u0016H\u0002J$\u0010j\u001a\u00020+2\u0006\u0010^\u001a\u00020\u00102\u0006\u0010k\u001a\u00020l2\n\u0010m\u001a\u00060nj\u0002`oH\u0002J\u0018\u0010p\u001a\u00020+2\u0006\u0010^\u001a\u00020\u00102\u0006\u0010q\u001a\u00020(H\u0002R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00168\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001e\u0010.\u001a\u00020/8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u00104\u001a\u0002058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R*\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0012\"\u0004\b<\u0010\u0014R\u001e\u0010=\u001a\u00020>8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010C\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010D\u001a\u00020E8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001b\u0010J\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bK\u0010LR\u001e\u0010O\u001a\u00020P8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001e\u0010U\u001a\u00020V8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010Z¨\u0006r"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/DedupeWorker;", "Lcom/amazon/photos/discovery/internal/worker/BaseWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "contentChangeNotifier", "Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "getContentChangeNotifier", "()Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "setContentChangeNotifier", "(Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;)V", "dedupeIdMap", "", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "getDedupeIdMap", "()Ljava/util/Map;", "setDedupeIdMap", "(Ljava/util/Map;)V", "dedupeStages", "", "getDedupeStages", "()Ljava/util/List;", "setDedupeStages", "(Ljava/util/List;)V", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "getDiscovery", "()Lcom/amazon/photos/discovery/Discovery;", "setDiscovery", "(Lcom/amazon/photos/discovery/Discovery;)V", "editDao", "Lcom/amazon/photos/discovery/dao/EditDao;", "getEditDao", "()Lcom/amazon/photos/discovery/dao/EditDao;", "setEditDao", "(Lcom/amazon/photos/discovery/dao/EditDao;)V", "enqueuedTime", "", "injectMethod", "Lkotlin/Function0;", "", "getInjectMethod", "()Lkotlin/jvm/functions/Function0;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "nextDedupeStageIdMap", "getNextDedupeStageIdMap", "setNextDedupeStageIdMap", "orphanRemover", "Lcom/amazon/photos/discovery/internal/util/OrphanRemover;", "getOrphanRemover", "()Lcom/amazon/photos/discovery/internal/util/OrphanRemover;", "setOrphanRemover", "(Lcom/amazon/photos/discovery/internal/util/OrphanRemover;)V", "paramStageId", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "getSystemUtil", "()Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "setSystemUtil", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;)V", "terminatingStageId", "getTerminatingStageId", "()I", "terminatingStageId$delegate", "Lkotlin/Lazy;", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "getWorkerDao", "()Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "setWorkerDao", "(Lcom/amazon/photos/discovery/internal/dao/WorkerDao;)V", "workerHelper", "Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "getWorkerHelper", "()Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "setWorkerHelper", "(Lcom/amazon/photos/discovery/internal/util/WorkerHelper;)V", "cleanupAndComplete", "processedItems", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "dedupeStage", "createDedupeDao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "dedupeBatch", "queriedItems", "executeBatchedDeduplication", "getMetricsObj", "getTag", "", "mainTask", "Landroidx/work/ListenableWorker$Result;", "markLocalItemsComplete", "recordWorkerAndStageFailureMetric", "metricName", "Lcom/amazon/clouddrive/android/core/interfaces/MetricName;", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "scheduleIfNeeded", "processedItemCount", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DedupeWorker extends BaseWorker {
    @Inject
    @NotNull
    public ContentChangeNotifier contentChangeNotifier;
    private final Context context;
    @Inject
    @Named("DedupeIdMap")
    @NotNull
    public Map<Integer, DedupeStage> dedupeIdMap;
    @Inject
    @NotNull
    public List<DedupeStage> dedupeStages;
    @Inject
    @NotNull
    public Discovery discovery;
    @Inject
    @NotNull
    public EditDao editDao;
    private final long enqueuedTime;
    @NotNull
    private final Function0<Unit> injectMethod;
    @Inject
    @NotNull
    public Logger logger;
    @Inject
    @NotNull
    public Metrics metrics;
    @Inject
    @Named("NextDedupeStageIdMap")
    @NotNull
    public Map<Integer, Integer> nextDedupeStageIdMap;
    @Inject
    @NotNull
    public OrphanRemover orphanRemover;
    private final int paramStageId;
    @Inject
    @NotNull
    public SystemUtil systemUtil;
    private final Lazy terminatingStageId$delegate;
    @Inject
    @NotNull
    public WorkerDao workerDao;
    @Inject
    @NotNull
    public WorkerHelper workerHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DedupeWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.context = context;
        this.paramStageId = workerParams.getInputData().getInt(DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID, 0);
        this.enqueuedTime = workerParams.getInputData().getLong(DiscoveryOperationsKt.ENQUEUED_TIME, 0L);
        this.terminatingStageId$delegate = LazyKt.lazy(new DedupeWorker$terminatingStageId$2(this));
        this.injectMethod = new DedupeWorker$injectMethod$1(this);
    }

    private final void cleanupAndComplete(List<UnifiedItem> list, DedupeStage dedupeStage) {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] objArr = {Integer.valueOf(list.size()), dedupeStage.getName()};
        String format = String.format(locale, "Batch of %d items complete for stage %s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        logger.i("DedupeWorker", format);
        OrphanRemover orphanRemover = this.orphanRemover;
        if (orphanRemover == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orphanRemover");
        }
        orphanRemover.removeOrphanedEntries();
        int stageId = dedupeStage.getStageId();
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        try {
            WorkerDao workerDao = this.workerDao;
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException("workerDao");
            }
            workerDao.updateUnifiedEntryDedupeStage(UnifiedItemUtils.INSTANCE.getUnifiedIds(list), stageId);
            if (stageId == getTerminatingStageId()) {
                markLocalItemsComplete(list);
            }
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_UPDATE_DEDUPE_STAGE, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
        }
    }

    private final DedupeDao createDedupeDao(DedupeStage dedupeStage) {
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(dedupeStage.getOperatorStageId()));
        if (dedupeStage.getStageId() == getTerminatingStageId()) {
            hashSet.add(Integer.valueOf(dedupeStage.getStageId()));
        }
        EditDao editDao = this.editDao;
        if (editDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editDao");
        }
        return new DedupeDao(editDao, hashSet);
    }

    private final void dedupeBatch(List<UnifiedItem> list, DedupeStage dedupeStage) throws InterruptedException, RetryDedupeException, NoRetryDedupeException {
        DedupeWorker$dedupeBatch$1 dedupeWorker$dedupeBatch$1 = new DedupeWorker$dedupeBatch$1(this);
        if (!list.isEmpty()) {
            try {
                DeduplicationRequest deduplicationRequest = new DeduplicationRequest(list, createDedupeDao(dedupeStage), dedupeStage.getStageId());
                SystemUtil systemUtil = this.systemUtil;
                if (systemUtil == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
                }
                long elapsedRealTimeMillis = systemUtil.elapsedRealTimeMillis();
                DeduplicatorResult deduplicate = dedupeStage.getDeduplicator().deduplicate(deduplicationRequest);
                int component1 = deduplicate.component1();
                String component2 = deduplicate.component2();
                SystemUtil systemUtil2 = this.systemUtil;
                if (systemUtil2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
                }
                ClientMetric withTagName = new ClientMetric().addCounter(DiscoveryMetrics.DedupeStageItemsModified, component1).addCounter(DiscoveryMetrics.DedupeStageItemsProcessed, list.size()).addTimer(DiscoveryMetrics.DedupeStageCompletion, systemUtil2.elapsedRealTimeMillis() - elapsedRealTimeMillis).withTagName(component2);
                Metrics metrics = this.metrics;
                if (metrics == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics.recordCustomMetric(component2, withTagName, MetricRecordingType.STANDARD);
                cleanupAndComplete(list, dedupeStage);
            } catch (RetryDedupeException e) {
                dedupeWorker$dedupeBatch$1.invoke2((Exception) e);
            } catch (InterruptedException e2) {
                dedupeWorker$dedupeBatch$1.invoke2((Exception) e2);
            } catch (Exception e3) {
                Logger logger = this.logger;
                if (logger == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                logger.e("DedupeWorker", "Deduplication encountered a no retry exception.", e3);
                cleanupAndComplete(list, dedupeStage);
                throw new NoRetryDedupeException(e3);
            }
        }
    }

    private final void executeBatchedDeduplication(DedupeStage dedupeStage, List<UnifiedItem> list) throws RetryException {
        try {
            dedupeBatch(list, dedupeStage);
            scheduleIfNeeded(dedupeStage, list.size());
        } catch (NoRetryDedupeException e) {
            recordWorkerAndStageFailureMetric(dedupeStage, DiscoveryMetrics.DedupeNoRetryError, e);
            scheduleIfNeeded(dedupeStage, list.size());
        } catch (RetryDedupeException e2) {
            recordWorkerAndStageFailureMetric(dedupeStage, DiscoveryMetrics.DedupeRetryError, e2);
            Logger logger = this.logger;
            if (logger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scheduling another de-dupe run for stage ");
            outline107.append(dedupeStage.getStageId());
            logger.e("DedupeWorker", outline107.toString(), e2);
            throw new RetryException(e2);
        } catch (InterruptedException e3) {
            recordWorkerAndStageFailureMetric(dedupeStage, DiscoveryMetrics.DedupeRetryError, e3);
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Scheduling another de-dupe run for stage ");
            outline1072.append(dedupeStage.getStageId());
            logger2.e("DedupeWorker", outline1072.toString(), e3);
            throw new RetryException(e3);
        }
    }

    private final int getTerminatingStageId() {
        return ((Number) this.terminatingStageId$delegate.getValue()).intValue();
    }

    private final void markLocalItemsComplete(List<UnifiedItem> list) {
        int collectionSizeOrDefault;
        List<Long> distinct;
        String str;
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        long elapsedRealTimeMillis = systemUtil.elapsedRealTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (UnifiedItem unifiedItem : list) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, unifiedItem.getLocalItems());
        }
        ArrayList<LocalItem> arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (true) {
            boolean z = false;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((LocalItem) next).getEndProcessing() <= 0) {
                z = true;
            }
            if (z) {
                arrayList2.add(next);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
        for (LocalItem localItem : arrayList2) {
            arrayList3.add(Long.valueOf(localItem.getId()));
        }
        distinct = CollectionsKt___CollectionsKt.distinct(arrayList3);
        if (!distinct.isEmpty()) {
            WorkerDao workerDao = this.workerDao;
            String str2 = "workerDao";
            if (workerDao == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            List<MutableLocalItem> localById = workerDao.getLocalById(distinct);
            for (MutableLocalItem mutableLocalItem : localById) {
                mutableLocalItem.setEndProcessing(elapsedRealTimeMillis);
                if (mutableLocalItem.getEndProcessing() > mutableLocalItem.getStartProcessing()) {
                    Metrics metrics = this.metrics;
                    if (metrics == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    str = str2;
                    metrics.recordSimpleDuration("DedupeWorker", DiscoveryMetrics.DiscoveryItemCompletion, mutableLocalItem.getEndProcessing() - mutableLocalItem.getStartProcessing());
                } else {
                    str = str2;
                    Metrics metrics2 = this.metrics;
                    if (metrics2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    metrics2.recordSimpleEvent("DedupeWorker", DiscoveryMetrics.DiscoveryItemDoneAcrossReboot, new MetricRecordingType[0]);
                }
                str2 = str;
            }
            String str3 = str2;
            WorkerDao workerDao2 = this.workerDao;
            if (workerDao2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str3);
            }
            workerDao2.updateLocal(localById);
            ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
            if (contentChangeNotifier == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
            }
            contentChangeNotifier.onContentDeduped$AndroidPhotosDiscovery_release(distinct);
        }
        ContentChangeNotifier contentChangeNotifier2 = this.contentChangeNotifier;
        if (contentChangeNotifier2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        contentChangeNotifier2.onContentDedupeComplete$AndroidPhotosDiscovery_release(true);
    }

    private final void recordWorkerAndStageFailureMetric(DedupeStage dedupeStage, MetricName metricName, Exception exc) {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger.e("DedupeWorker", "Failed to run de-duplication work", exc);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleErrorEvent("DedupeWorker", metricName, exc);
        Metrics metrics2 = this.metrics;
        if (metrics2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics2.recordSimpleErrorEvent(dedupeStage.getName(), metricName, exc);
    }

    private final void scheduleIfNeeded(DedupeStage dedupeStage, long j) {
        int stageId = dedupeStage.getStageId();
        if (j == dedupeStage.getBatchSize()) {
            Discovery discovery = this.discovery;
            if (discovery == null) {
                Intrinsics.throwUninitializedPropertyAccessException("discovery");
            }
            discovery.getOperations().dedupeOperation(this.paramStageId);
            Logger logger = this.logger;
            if (logger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger.i("DedupeWorker", "Scheduling another de-dupe run for stage " + stageId);
        }
        Map<Integer, Integer> map = this.nextDedupeStageIdMap;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextDedupeStageIdMap");
        }
        if (map.containsKey(Integer.valueOf(stageId))) {
            Map<Integer, Integer> map2 = this.nextDedupeStageIdMap;
            if (map2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nextDedupeStageIdMap");
            }
            Integer num = map2.get(Integer.valueOf(stageId));
            if (num == null) {
                Metrics metrics = this.metrics;
                if (metrics == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics.recordSimpleEvent("DedupeWorker", DiscoveryMetrics.DedupeNoNextStage, MetricRecordingType.STANDARD);
                Logger logger2 = this.logger;
                if (logger2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                logger2.e("DedupeWorker", "Next stage id is null when attempting to run.");
                return;
            }
            Logger logger3 = this.logger;
            if (logger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger3.i("DedupeWorker", "Scheduling next de-dupe stage " + num);
            Discovery discovery2 = this.discovery;
            if (discovery2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("discovery");
            }
            discovery2.getOperations().dedupeOperation(num.intValue());
        }
    }

    @NotNull
    public final ContentChangeNotifier getContentChangeNotifier() {
        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
        if (contentChangeNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
        }
        return contentChangeNotifier;
    }

    @NotNull
    public final Map<Integer, DedupeStage> getDedupeIdMap() {
        Map<Integer, DedupeStage> map = this.dedupeIdMap;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dedupeIdMap");
        }
        return map;
    }

    @NotNull
    public final List<DedupeStage> getDedupeStages() {
        List<DedupeStage> list = this.dedupeStages;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dedupeStages");
        }
        return list;
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
    public final EditDao getEditDao() {
        EditDao editDao = this.editDao;
        if (editDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editDao");
        }
        return editDao;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected Function0<Unit> getInjectMethod() {
        return this.injectMethod;
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
    public final Map<Integer, Integer> getNextDedupeStageIdMap() {
        Map<Integer, Integer> map = this.nextDedupeStageIdMap;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextDedupeStageIdMap");
        }
        return map;
    }

    @NotNull
    public final OrphanRemover getOrphanRemover() {
        OrphanRemover orphanRemover = this.orphanRemover;
        if (orphanRemover == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orphanRemover");
        }
        return orphanRemover;
    }

    @NotNull
    public final SystemUtil getSystemUtil() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected String getTag() {
        return "DedupeWorker";
    }

    @NotNull
    public final WorkerDao getWorkerDao() {
        WorkerDao workerDao = this.workerDao;
        if (workerDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerDao");
        }
        return workerDao;
    }

    @NotNull
    public final WorkerHelper getWorkerHelper() {
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        return workerHelper;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected ListenableWorker.Result mainTask() {
        List<UnifiedItem> list;
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Worker for dedupe stage ");
        outline107.append(this.paramStageId);
        outline107.append(" started.");
        logger.i("DedupeWorker", outline107.toString());
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        long elapsedRealTimeMillis = systemUtil.elapsedRealTimeMillis();
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent("DedupeWorker", DiscoveryMetrics.DedupeWorkerStarted, MetricRecordingType.STANDARD);
        List<DedupeStage> list2 = this.dedupeStages;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dedupeStages");
        }
        if (list2.isEmpty()) {
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger2.w("DedupeWorker", "No de-duplication stages configured.");
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordSimpleEvent("DedupeWorker", DiscoveryMetrics.DedupeNoStagesProvided, MetricRecordingType.STANDARD);
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
            return success;
        }
        WorkerHelper workerHelper = this.workerHelper;
        if (workerHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
        }
        if (!workerHelper.isPermissionGranted$AndroidPhotosDiscovery_release(this.context, "android.permission.READ_EXTERNAL_STORAGE")) {
            Logger logger3 = this.logger;
            if (logger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger3.e("DedupeWorker", "No file read permission.");
            Metrics metrics3 = this.metrics;
            if (metrics3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            return WorkerUtilKt.recordAbortErrorAndFailWorker(metrics3, "DedupeWorker", DiscoveryMetricsKt.STORAGE_PERMISSION_FAILURE);
        }
        DedupeWorker$mainTask$1 dedupeWorker$mainTask$1 = new DedupeWorker$mainTask$1(this);
        Map<Integer, DedupeStage> map = this.dedupeIdMap;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dedupeIdMap");
        }
        DedupeStage dedupeStage = map.get(Integer.valueOf(this.paramStageId));
        if (dedupeStage != null) {
            Logger logger4 = this.logger;
            if (logger4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Beginning work for ");
            outline1072.append(dedupeStage.getName());
            logger4.d("DedupeWorker", outline1072.toString());
            Metrics metrics4 = this.metrics;
            if (metrics4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            try {
                WorkerDao workerDao = this.workerDao;
                if (workerDao == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("workerDao");
                }
                list = workerDao.getUnifiedByDedupeStage(dedupeStage.getOperatorStageId(), dedupeStage.getBatchSize());
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_UNIFIED_BY_STAGE, metrics4, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
                list = null;
            }
            if (list != null) {
                if (list.isEmpty()) {
                    Logger logger5 = this.logger;
                    if (logger5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("No items to process on stage: ");
                    outline1073.append(dedupeStage.getName());
                    logger5.i("DedupeWorker", outline1073.toString());
                    Metrics metrics5 = this.metrics;
                    if (metrics5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    metrics5.recordSimpleEvent("DedupeWorker", DiscoveryMetrics.DedupeWorkerEmptyBatch, MetricRecordingType.STANDARD);
                    scheduleIfNeeded(dedupeStage, list.size());
                    if (dedupeStage.getStageId() == getTerminatingStageId()) {
                        ContentChangeNotifier contentChangeNotifier = this.contentChangeNotifier;
                        if (contentChangeNotifier == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("contentChangeNotifier");
                        }
                        contentChangeNotifier.onContentDedupeComplete$AndroidPhotosDiscovery_release(false);
                    }
                    ListenableWorker.Result success2 = ListenableWorker.Result.success();
                    Intrinsics.checkExpressionValueIsNotNull(success2, "Result.success()");
                    return success2;
                }
                try {
                    executeBatchedDeduplication(dedupeStage, list);
                    Metrics metrics6 = this.metrics;
                    if (metrics6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    DiscoveryMetrics discoveryMetrics = DiscoveryMetrics.DedupeWorkerComplete;
                    SystemUtil systemUtil2 = this.systemUtil;
                    if (systemUtil2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
                    }
                    metrics6.recordSimpleDuration("DedupeWorker", discoveryMetrics, systemUtil2.elapsedRealTimeMillis() - elapsedRealTimeMillis);
                    WorkerHelper workerHelper2 = this.workerHelper;
                    if (workerHelper2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("workerHelper");
                    }
                    workerHelper2.recordWorkerLifetimeMetric(this.enqueuedTime, dedupeStage.getName());
                    Logger logger6 = this.logger;
                    if (logger6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Worker for dedupe stage ");
                    outline1074.append(this.paramStageId);
                    outline1074.append(" stopped.");
                    logger6.i("DedupeWorker", outline1074.toString());
                    ListenableWorker.Result success3 = ListenableWorker.Result.success();
                    Intrinsics.checkExpressionValueIsNotNull(success3, "Result.success()");
                    return success3;
                } catch (RetryException unused) {
                    Metrics metrics7 = this.metrics;
                    if (metrics7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metrics");
                    }
                    return WorkerUtilKt.recordAndRetry(metrics7, "DedupeWorker");
                }
            }
            Metrics metrics8 = this.metrics;
            if (metrics8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            return WorkerUtilKt.recordAndRetry(metrics8, "DedupeWorker");
        }
        return dedupeWorker$mainTask$1.mo12560invoke();
    }

    public final void setContentChangeNotifier(@NotNull ContentChangeNotifier contentChangeNotifier) {
        Intrinsics.checkParameterIsNotNull(contentChangeNotifier, "<set-?>");
        this.contentChangeNotifier = contentChangeNotifier;
    }

    public final void setDedupeIdMap(@NotNull Map<Integer, DedupeStage> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.dedupeIdMap = map;
    }

    public final void setDedupeStages(@NotNull List<DedupeStage> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.dedupeStages = list;
    }

    public final void setDiscovery(@NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(discovery, "<set-?>");
        this.discovery = discovery;
    }

    public final void setEditDao(@NotNull EditDao editDao) {
        Intrinsics.checkParameterIsNotNull(editDao, "<set-?>");
        this.editDao = editDao;
    }

    public final void setLogger(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    public final void setMetrics(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    public final void setNextDedupeStageIdMap(@NotNull Map<Integer, Integer> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.nextDedupeStageIdMap = map;
    }

    public final void setOrphanRemover(@NotNull OrphanRemover orphanRemover) {
        Intrinsics.checkParameterIsNotNull(orphanRemover, "<set-?>");
        this.orphanRemover = orphanRemover;
    }

    public final void setSystemUtil(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
    }

    public final void setWorkerDao(@NotNull WorkerDao workerDao) {
        Intrinsics.checkParameterIsNotNull(workerDao, "<set-?>");
        this.workerDao = workerDao;
    }

    public final void setWorkerHelper(@NotNull WorkerHelper workerHelper) {
        Intrinsics.checkParameterIsNotNull(workerHelper, "<set-?>");
        this.workerHelper = workerHelper;
    }
}
