package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "", "", "Lcom/amazon/photos/uploader/UploadState;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderOperations$cancelUploads$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<Map<Long, ? extends UploadState>>, Unit> {
    final /* synthetic */ List $requestIds;
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$cancelUploads$1(UploaderOperations uploaderOperations, List list) {
        super(1);
        this.this$0 = uploaderOperations;
        this.$requestIds = list;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(CallbackToFutureAdapter.Completer<Map<Long, ? extends UploadState>> completer) {
        invoke2((CallbackToFutureAdapter.Completer<Map<Long, UploadState>>) completer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final CallbackToFutureAdapter.Completer<Map<Long, UploadState>> completer) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$cancelUploads$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                Map requestStatesByIds;
                try {
                    UploaderOperations$cancelUploads$1.this.this$0.throwDestroyedIfNeeded();
                    UploaderOperations$cancelUploads$1.this.this$0.cancelUploadByIdHelper(UploaderOperations$cancelUploads$1.this.$requestIds);
                    requestStatesByIds = UploaderOperations$cancelUploads$1.this.this$0.getRequestStatesByIds(UploaderOperations$cancelUploads$1.this.$requestIds);
                    UploaderOperations$cancelUploads$1.this.this$0.logMetricWithCount(UploadMetrics.UPLOAD_CANCELLED, UploaderOperations$cancelUploads$1.this.$requestIds.size());
                    completer.set(requestStatesByIds);
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }
        });
    }
}
