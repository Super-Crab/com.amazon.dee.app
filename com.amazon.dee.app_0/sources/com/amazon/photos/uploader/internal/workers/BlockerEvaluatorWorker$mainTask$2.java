package com.amazon.photos.uploader.internal.workers;

import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BlockerEvaluatorWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.internal.workers.BlockerEvaluatorWorker$mainTask$2", f = "BlockerEvaluatorWorker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class BlockerEvaluatorWorker$mainTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ BlockerEvaluatorWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlockerEvaluatorWorker$mainTask$2(BlockerEvaluatorWorker blockerEvaluatorWorker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = blockerEvaluatorWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BlockerEvaluatorWorker$mainTask$2 blockerEvaluatorWorker$mainTask$2 = new BlockerEvaluatorWorker$mainTask$2(this.this$0, completion);
        blockerEvaluatorWorker$mainTask$2.p$ = (CoroutineScope) obj;
        return blockerEvaluatorWorker$mainTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
        return ((BlockerEvaluatorWorker$mainTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        int pendingRequestsCount;
        int collectionSizeOrDefault;
        String[] strArr;
        Collection processRequests;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            long currentTimeMillis = System.currentTimeMillis();
            this.this$0.getLogger().i(BlockerEvaluatorWorker.TAG, "Evaluating blockers.");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            List<Blocker> globalBlockers = this.this$0.getInternalEvaluator().getGlobalBlockers();
            if (globalBlockers.isEmpty()) {
                pendingRequestsCount = 0;
                for (Queue queue : this.this$0.getQueueManager().getQueues()) {
                    List<Blocker> queueBlockers = this.this$0.getInternalEvaluator().getQueueBlockers(queue);
                    if (queueBlockers.isEmpty()) {
                        processRequests = this.this$0.processRequests(queue);
                        if (!processRequests.isEmpty()) {
                            linkedHashMap2.put(queue, processRequests);
                        }
                        pendingRequestsCount += this.this$0.getRequestDao().getCountForState(queue.getName(), UploadState.BLOCKED);
                    } else {
                        linkedHashMap.put(queue, queueBlockers);
                        pendingRequestsCount += this.this$0.getRequestDao().getPendingRequestsCount(queue.getName());
                        WorkManager workManager = this.this$0.getWorkManager();
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(SchedulerWorkerKt.QUEUE_TAG_PREFIX);
                        outline107.append(queue.getName());
                        workManager.cancelAllWorkByTag(outline107.toString());
                        PersistentLogger logger = this.this$0.getLogger();
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Queue[");
                        outline1072.append(queue.getName());
                        outline1072.append("] blocked by ");
                        outline1072.append(queueBlockers);
                        logger.iPersistent(BlockerEvaluatorWorker.TAG, outline1072.toString());
                    }
                }
            } else {
                this.this$0.getWorkManager().cancelAllWorkByTag(SchedulerWorkerKt.UPLOAD_TAG);
                this.this$0.getLogger().iPersistent(BlockerEvaluatorWorker.TAG, "Global blocker detected: " + globalBlockers);
                pendingRequestsCount = this.this$0.getRequestDao().getPendingRequestsCount();
            }
            this.this$0.getUploadSummaryTracker().setBlockerSummary(globalBlockers, linkedHashMap, linkedHashMap2);
            if (!globalBlockers.isEmpty()) {
                strArr = new String[0];
            } else {
                Collection<Queue> queues = this.this$0.getQueueManager().getQueues();
                ArrayList<Queue> arrayList = new ArrayList();
                for (Object obj2 : queues) {
                    Queue queue2 = (Queue) obj2;
                    if (Boxing.boxBoolean(!linkedHashMap.containsKey(queue2) || ((Collection) MapsKt.getValue(linkedHashMap, queue2)).isEmpty()).booleanValue()) {
                        arrayList.add(obj2);
                    }
                }
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                for (Queue queue3 : arrayList) {
                    arrayList2.add(queue3.getName());
                }
                Object[] array = arrayList2.toArray(new String[0]);
                if (array == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                strArr = (String[]) array;
            }
            this.this$0.recordBlockerEvaluationMetric(pendingRequestsCount, currentTimeMillis);
            PersistentLogger logger2 = this.this$0.getLogger();
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Blocker evaluation complete. ");
            outline1073.append(strArr.length);
            outline1073.append(" unblocked queues found.");
            logger2.i(BlockerEvaluatorWorker.TAG, outline1073.toString());
            return this.this$0.generateResult$AndroidPhotosUploader_release(strArr);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
