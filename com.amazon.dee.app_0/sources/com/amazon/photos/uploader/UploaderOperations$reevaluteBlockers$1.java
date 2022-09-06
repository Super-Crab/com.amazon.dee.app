package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
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
public final class UploaderOperations$reevaluteBlockers$1 extends Lambda implements Function1<CallbackToFutureAdapter.Completer<Unit>, Unit> {
    final /* synthetic */ UploaderOperations this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploaderOperations$reevaluteBlockers$1(UploaderOperations uploaderOperations) {
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
        this.this$0.submitToExecutorIfNotShutdown(new Runnable() { // from class: com.amazon.photos.uploader.UploaderOperations$reevaluteBlockers$1$task$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    UploaderOperations$reevaluteBlockers$1.this.this$0.throwDestroyedIfNeeded();
                    UploaderOperations$reevaluteBlockers$1.this.this$0.getLogger$AndroidPhotosUploader_release().i$AndroidPhotosUploader_release(UploaderOperations.TAG, "Reevaluting blockers.");
                    UploaderOperations$reevaluteBlockers$1.this.this$0.getSchedulingCallback$AndroidPhotosUploader_release().reevaluateBlockers().getResult().get();
                    completer.set(Unit.INSTANCE);
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }
        });
    }
}
