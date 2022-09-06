package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.log.UploadLogger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderOperations$scheduleUploads$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<List<? extends Long>>, Unit> {
    final /* synthetic */ String $queue;
    final /* synthetic */ UploadStrategy $strategy;
    final /* synthetic */ List $uploadRequests;
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$scheduleUploads$1(UploaderOperations uploaderOperations, List list, String str, UploadStrategy uploadStrategy) {
        super(1);
        this.this$0 = uploaderOperations;
        this.$uploadRequests = list;
        this.$queue = str;
        this.$strategy = uploadStrategy;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(CallbackToFutureAdapter.Completer<List<? extends Long>> completer) {
        invoke2((CallbackToFutureAdapter.Completer<List<Long>>) completer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final CallbackToFutureAdapter.Completer<List<Long>> completer) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$scheduleUploads$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                List schedulerUploadsHelper;
                int collectionSizeOrDefault;
                try {
                    UploaderOperations$scheduleUploads$1.this.this$0.throwDestroyedIfNeeded();
                    UploadLogger logger$AndroidPhotosUploader_release = UploaderOperations$scheduleUploads$1.this.this$0.getLogger$AndroidPhotosUploader_release();
                    logger$AndroidPhotosUploader_release.d$AndroidPhotosUploader_release(UploaderOperations.TAG, "Scheduled " + UploaderOperations$scheduleUploads$1.this.$uploadRequests.size() + " requests start.");
                    schedulerUploadsHelper = UploaderOperations$scheduleUploads$1.this.this$0.schedulerUploadsHelper(UploaderOperations$scheduleUploads$1.this.$uploadRequests, UploaderOperations$scheduleUploads$1.this.$queue, UploaderOperations$scheduleUploads$1.this.$strategy);
                    List<UploadRequest> list = UploaderOperations$scheduleUploads$1.this.$uploadRequests;
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                    for (UploadRequest uploadRequest : list) {
                        UploaderOperations$scheduleUploads$1.this.this$0.logMetricWithMetadata(UploadMetrics.UPLOAD_ATTEMPT_CATEGORY, uploadRequest.getUploadCategory());
                        arrayList.add(Unit.INSTANCE);
                    }
                    UploaderOperations$scheduleUploads$1.this.this$0.logMetricWithCount(UploadMetrics.UPLOAD_REQUESTED, UploaderOperations$scheduleUploads$1.this.$uploadRequests.size());
                    completer.set(schedulerUploadsHelper);
                    UploadLogger logger$AndroidPhotosUploader_release2 = UploaderOperations$scheduleUploads$1.this.this$0.getLogger$AndroidPhotosUploader_release();
                    logger$AndroidPhotosUploader_release2.d$AndroidPhotosUploader_release(UploaderOperations.TAG, "Scheduled " + UploaderOperations$scheduleUploads$1.this.$uploadRequests.size() + " requests stop.");
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    Boolean.valueOf(completer.setException(th));
                }
            }
        });
    }
}
