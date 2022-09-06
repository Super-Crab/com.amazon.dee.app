package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.Operation;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploaderOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class UploaderOperations$clearBackoff$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<Unit>, Unit> {
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$clearBackoff$1(UploaderOperations uploaderOperations) {
        super(1);
        this.this$0 = uploaderOperations;
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
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$clearBackoff$1$task$1

            /* compiled from: UploaderOperations.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.photos.uploader.UploaderOperations$clearBackoff$1$task$1$1  reason: invalid class name */
            /* loaded from: classes13.dex */
            static final class AnonymousClass1 implements MetricName {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                AnonymousClass1() {
                }

                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return UploadMetrics.BACKOFF_CLEARED;
                }
            }

            @Override // java.lang.Runnable
            public final void run() {
                ListenableFuture<Operation.State.SUCCESS> result;
                try {
                    UploaderOperations$clearBackoff$1.this.this$0.throwDestroyedIfNeeded();
                    UploaderOperations$clearBackoff$1.this.this$0.getMetrics$AndroidPhotosUploader_release().recordSimpleEvent(UploaderOperations.TAG, AnonymousClass1.INSTANCE, new MetricRecordingType[0]);
                    Operation clearBackoffPolicy = UploaderOperations$clearBackoff$1.this.this$0.getBackoffBlockerEvaluator$AndroidPhotosUploader_release().clearBackoffPolicy();
                    if (clearBackoffPolicy != null && (result = clearBackoffPolicy.getResult()) != null) {
                        result.get();
                    }
                    completer.set(Unit.INSTANCE);
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }
        });
    }
}
