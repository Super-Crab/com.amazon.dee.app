package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.log.UploadLogger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderOperations$cancelUploadsForQueues$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<Unit>, Unit> {
    final /* synthetic */ List $queueNames;
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$cancelUploadsForQueues$1(UploaderOperations uploaderOperations, List list) {
        super(1);
        this.this$0 = uploaderOperations;
        this.$queueNames = list;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(CallbackToFutureAdapter.Completer<Unit> completer) {
        invoke2(completer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final CallbackToFutureAdapter.Completer<Unit> completer) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$cancelUploadsForQueues$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    UploaderOperations$cancelUploadsForQueues$1.this.this$0.throwDestroyedIfNeeded();
                    UploadLogger logger$AndroidPhotosUploader_release = UploaderOperations$cancelUploadsForQueues$1.this.this$0.getLogger$AndroidPhotosUploader_release();
                    logger$AndroidPhotosUploader_release.i$AndroidPhotosUploader_release(UploaderOperations.TAG, "Cancel " + UploaderOperations$cancelUploadsForQueues$1.this.$queueNames.size() + " queues' requests start.");
                    ArrayList arrayList = new ArrayList();
                    for (String str : UploaderOperations$cancelUploadsForQueues$1.this.$queueNames) {
                        arrayList.addAll(UploaderOperations$cancelUploadsForQueues$1.this.this$0.getRequestDao$AndroidPhotosUploader_release().getRequestsForQueue(str));
                    }
                    UploadLogger logger$AndroidPhotosUploader_release2 = UploaderOperations$cancelUploadsForQueues$1.this.this$0.getLogger$AndroidPhotosUploader_release();
                    logger$AndroidPhotosUploader_release2.i$AndroidPhotosUploader_release(UploaderOperations.TAG, "Cancel " + arrayList.size() + " requests.");
                    UploaderOperations$cancelUploadsForQueues$1.this.this$0.cancelUploadRequestHelper(arrayList);
                    UploaderOperations$cancelUploadsForQueues$1.this.this$0.logMetricWithCount(UploadMetrics.UPLOAD_CANCELLED, arrayList.size());
                    completer.set(Unit.INSTANCE);
                    UploadLogger logger$AndroidPhotosUploader_release3 = UploaderOperations$cancelUploadsForQueues$1.this.this$0.getLogger$AndroidPhotosUploader_release();
                    logger$AndroidPhotosUploader_release3.i$AndroidPhotosUploader_release(UploaderOperations.TAG, "Cancel " + UploaderOperations$cancelUploadsForQueues$1.this.$queueNames.size() + " queues' requests complete.");
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    Boolean.valueOf(completer.setException(th));
                }
            }
        });
    }
}
