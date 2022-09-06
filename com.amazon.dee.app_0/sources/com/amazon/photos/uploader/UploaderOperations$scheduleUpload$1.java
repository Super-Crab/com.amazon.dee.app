package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderOperations$scheduleUpload$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<Long>, Unit> {
    final /* synthetic */ String $queue;
    final /* synthetic */ UploadStrategy $strategy;
    final /* synthetic */ UploadRequest $uploadRequest;
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$scheduleUpload$1(UploaderOperations uploaderOperations, UploadRequest uploadRequest, String str, UploadStrategy uploadStrategy) {
        super(1);
        this.this$0 = uploaderOperations;
        this.$uploadRequest = uploadRequest;
        this.$queue = str;
        this.$strategy = uploadStrategy;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(CallbackToFutureAdapter.Completer<Long> completer) {
        invoke2(completer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final CallbackToFutureAdapter.Completer<Long> completer) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$scheduleUpload$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                List listOf;
                List schedulerUploadsHelper;
                try {
                    UploaderOperations$scheduleUpload$1.this.this$0.throwDestroyedIfNeeded();
                    UploaderOperations uploaderOperations = UploaderOperations$scheduleUpload$1.this.this$0;
                    listOf = CollectionsKt__CollectionsJVMKt.listOf(UploaderOperations$scheduleUpload$1.this.$uploadRequest);
                    schedulerUploadsHelper = uploaderOperations.schedulerUploadsHelper(listOf, UploaderOperations$scheduleUpload$1.this.$queue, UploaderOperations$scheduleUpload$1.this.$strategy);
                    long longValue = ((Number) CollectionsKt.first((List<? extends Object>) schedulerUploadsHelper)).longValue();
                    UploaderOperations$scheduleUpload$1.this.this$0.logMetricWithMetadata(UploadMetrics.UPLOAD_ATTEMPT_CATEGORY, UploaderOperations$scheduleUpload$1.this.$uploadRequest.getUploadCategory());
                    UploaderOperations$scheduleUpload$1.this.this$0.logMetricWithCount(UploadMetrics.UPLOAD_REQUESTED, 1);
                    completer.set(Long.valueOf(longValue));
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }
        });
    }
}
