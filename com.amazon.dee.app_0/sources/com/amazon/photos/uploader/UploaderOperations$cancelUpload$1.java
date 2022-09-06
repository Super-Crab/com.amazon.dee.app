package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "Lcom/amazon/photos/uploader/UploadState;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class UploaderOperations$cancelUpload$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<UploadState>, Unit> {
    final /* synthetic */ long $requestId;
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$cancelUpload$1(UploaderOperations uploaderOperations, long j) {
        super(1);
        this.this$0 = uploaderOperations;
        this.$requestId = j;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(CallbackToFutureAdapter.Completer<UploadState> completer) {
        invoke2(completer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final CallbackToFutureAdapter.Completer<UploadState> completer) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$cancelUpload$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                List listOf;
                try {
                    UploaderOperations$cancelUpload$1.this.this$0.throwDestroyedIfNeeded();
                    UploaderOperations uploaderOperations = UploaderOperations$cancelUpload$1.this.this$0;
                    listOf = CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(UploaderOperations$cancelUpload$1.this.$requestId));
                    uploaderOperations.cancelUploadByIdHelper(listOf);
                    UploaderOperations$cancelUpload$1.this.this$0.logMetricWithCount(UploadMetrics.UPLOAD_CANCELLED, 1);
                    completer.set(UploaderOperations$cancelUpload$1.this.this$0.getRequestDao$AndroidPhotosUploader_release().getRequestById(UploaderOperations$cancelUpload$1.this.$requestId).getState());
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }
        });
    }
}
