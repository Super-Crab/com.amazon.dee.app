package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "", "", "Lcom/amazon/photos/uploader/UploadState;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderOperations$cancelUploadsByFilePath$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<Map<Long, ? extends UploadState>>, Unit> {
    final /* synthetic */ List $filepaths;
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$cancelUploadsByFilePath$1(UploaderOperations uploaderOperations, List list) {
        super(1);
        this.this$0 = uploaderOperations;
        this.$filepaths = list;
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
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$cancelUploadsByFilePath$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                List list;
                Map requestStates;
                try {
                    UploaderOperations$cancelUploadsByFilePath$1.this.this$0.throwDestroyedIfNeeded();
                    List<String> list2 = UploaderOperations$cancelUploadsByFilePath$1.this.$filepaths;
                    ArrayList arrayList = new ArrayList();
                    for (String str : list2) {
                        UploadRequest pendingRequestByFilepath = UploaderOperations$cancelUploadsByFilePath$1.this.this$0.getRequestDao$AndroidPhotosUploader_release().getPendingRequestByFilepath(str);
                        if (pendingRequestByFilepath != null) {
                            arrayList.add(pendingRequestByFilepath);
                        }
                    }
                    list = CollectionsKt___CollectionsKt.toList(arrayList);
                    UploaderOperations$cancelUploadsByFilePath$1.this.this$0.cancelUploadRequestHelper(list);
                    requestStates = UploaderOperations$cancelUploadsByFilePath$1.this.this$0.getRequestStates(list);
                    UploaderOperations$cancelUploadsByFilePath$1.this.this$0.logMetricWithCount(UploadMetrics.UPLOAD_CANCELLED, list.size());
                    completer.set(requestStates);
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }
        });
    }
}
