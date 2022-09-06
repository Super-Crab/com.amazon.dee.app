package com.amazon.photos.uploader.internal;

import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueConstraint;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QueueManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0007\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001e\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/photos/uploader/internal/QueueManagerImpl;", "Lcom/amazon/photos/uploader/QueueManager;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "queues", "", "Lcom/amazon/photos/uploader/Queue;", "(Lcom/amazon/photos/uploader/SchedulingCallback;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Ljava/util/Set;)V", "nameToQueue", "", "", "addNewQueue", "", "queue", "addQueue", "addQueueConstraint", "queueName", "constraint", "Lcom/amazon/photos/uploader/QueueConstraint;", "getQueue", "getQueueOrThrow", "getQueues", "", "onQueuePropertiesUpdateFailed", "onQueuePropertiesUpdated", "removeQueueConstraint", "setQueueConstraints", "constraints", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class QueueManagerImpl implements QueueManager {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "QueueManager";
    private final UploadLogger logger;
    private final Metrics metrics;
    private Map<String, Queue> nameToQueue;
    private final SchedulingCallback schedulingCallback;

    /* compiled from: QueueManagerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/QueueManagerImpl$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public QueueManagerImpl(@NotNull SchedulingCallback schedulingCallback, @NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull Set<Queue> queues) {
        int collectionSizeOrDefault;
        Map map;
        Map<String, Queue> mutableMap;
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        this.schedulingCallback = schedulingCallback;
        this.logger = logger;
        this.metrics = metrics;
        this.nameToQueue = new LinkedHashMap();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(queues, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Queue queue : queues) {
            arrayList.add(TuplesKt.to(queue.getName(), queue));
        }
        map = MapsKt__MapsKt.toMap(arrayList);
        mutableMap = MapsKt__MapsKt.toMutableMap(map);
        this.nameToQueue = mutableMap;
    }

    private final void addQueue(Queue queue) {
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Adding Queue [" + queue + JsonReaderKt.END_LIST);
        this.nameToQueue.put(queue.getName(), queue);
    }

    private final Queue getQueueOrThrow(String str) {
        Queue queue = getQueue(str);
        if (queue != null) {
            return queue;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("No Queue with ", str).toString());
    }

    private final void onQueuePropertiesUpdateFailed(String str) {
        this.metrics.recordSimpleEvent(TAG, QueueManagerImpl$onQueuePropertiesUpdateFailed$1.INSTANCE, new MetricRecordingType[0]);
        UploadLogger uploadLogger = this.logger;
        uploadLogger.w$AndroidPhotosUploader_release(TAG, "Properties of Queue [" + str + "] not updated");
    }

    private final void onQueuePropertiesUpdated() {
        this.metrics.recordSimpleEvent(TAG, QueueManagerImpl$onQueuePropertiesUpdated$1.INSTANCE, new MetricRecordingType[0]);
        this.schedulingCallback.reevaluateBlockers();
    }

    @Override // com.amazon.photos.uploader.QueueManager
    public void addNewQueue(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Adding New Queue [" + queue + JsonReaderKt.END_LIST);
        if (!this.nameToQueue.containsKey(queue.getName())) {
            this.metrics.recordSimpleEvent(TAG, QueueManagerImpl$addNewQueue$2.INSTANCE, new MetricRecordingType[0]);
            this.nameToQueue.put(queue.getName(), queue);
            this.schedulingCallback.reevaluateBlockers();
            return;
        }
        this.metrics.recordSimpleEvent(TAG, QueueManagerImpl$addNewQueue$1.INSTANCE, new MetricRecordingType[0]);
        throw new IllegalArgumentException("Attempted to add a queue that already exists.");
    }

    @Override // com.amazon.photos.uploader.QueueManager
    public void addQueueConstraint(@NotNull String queueName, @NotNull QueueConstraint constraint) {
        Set<? extends QueueConstraint> plus;
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        Intrinsics.checkParameterIsNotNull(constraint, "constraint");
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Adding constraint [" + constraint + "] on Queue [" + queueName + JsonReaderKt.END_LIST);
        if (!getQueueOrThrow(queueName).getConstraints().contains(constraint)) {
            plus = SetsKt___SetsKt.plus(getQueueOrThrow(queueName).getConstraints(), constraint);
            setQueueConstraints(queueName, plus);
        }
    }

    @Override // com.amazon.photos.uploader.QueueManager
    @Nullable
    public Queue getQueue(@NotNull String queueName) {
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        return this.nameToQueue.get(queueName);
    }

    @Override // com.amazon.photos.uploader.QueueManager
    @NotNull
    public Collection<Queue> getQueues() {
        return this.nameToQueue.values();
    }

    @Override // com.amazon.photos.uploader.QueueManager
    public void removeQueueConstraint(@NotNull String queueName, @NotNull QueueConstraint constraint) {
        Set<? extends QueueConstraint> minus;
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        Intrinsics.checkParameterIsNotNull(constraint, "constraint");
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Removing constraint [" + constraint + "] from Queue [" + queueName + JsonReaderKt.END_LIST);
        if (getQueueOrThrow(queueName).getConstraints().contains(constraint)) {
            minus = SetsKt___SetsKt.minus(getQueueOrThrow(queueName).getConstraints(), constraint);
            setQueueConstraints(queueName, minus);
        }
    }

    @Override // com.amazon.photos.uploader.QueueManager
    public void setQueueConstraints(@NotNull String queueName, @NotNull Set<? extends QueueConstraint> constraints) {
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Setting constraints [" + constraints + "] on Queue [" + queueName + JsonReaderKt.END_LIST);
        Queue queue = getQueue(queueName);
        if (!Intrinsics.areEqual(queue != null ? queue.getConstraints() : null, constraints)) {
            addQueue(Queue.copy$default(getQueueOrThrow(queueName), null, null, constraints, 3, null));
            if (getQueue(queueName) != null) {
                onQueuePropertiesUpdated();
            } else {
                onQueuePropertiesUpdateFailed(queueName);
            }
        }
    }
}
