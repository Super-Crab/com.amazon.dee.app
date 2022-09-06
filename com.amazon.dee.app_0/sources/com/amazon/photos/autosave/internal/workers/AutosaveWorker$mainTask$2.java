package com.amazon.photos.autosave.internal.workers;

import androidx.work.ListenableWorker;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.autosave.internal.utils.BatchOperationUtilsKt;
import com.amazon.photos.autosave.internal.workers.AutosaveWorker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AutosaveWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.autosave.internal.workers.AutosaveWorker$mainTask$2", f = "AutosaveWorker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class AutosaveWorker$mainTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AutosaveWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AutosaveWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.autosave.internal.workers.AutosaveWorker$mainTask$2$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            if (!(th instanceof CancellationException) || AutosaveWorker$mainTask$2.this.this$0.getAutosavePreferences().isAutosaveEnabled(AutosaveWorker$mainTask$2.this.this$0.getHandleMediaType())) {
                return;
            }
            Logger logger = AutosaveWorker$mainTask$2.this.this$0.getLogger();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave for ");
            outline107.append(AutosaveWorker$mainTask$2.this.this$0.handleMediaTypeStr);
            outline107.append(" disabled and cause worker cancel.");
            logger.i(AutosaveWorker.TAG, outline107.toString());
            AutosaveWorker$mainTask$2.this.this$0.getAutosaveOperations().cancelUploadsOnly$AndroidPhotosAutosave_release(AutosaveWorker$mainTask$2.this.this$0.getHandleMediaType());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutosaveWorker$mainTask$2(AutosaveWorker autosaveWorker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = autosaveWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AutosaveWorker$mainTask$2 autosaveWorker$mainTask$2 = new AutosaveWorker$mainTask$2(this.this$0, completion);
        autosaveWorker$mainTask$2.p$ = (CoroutineScope) obj;
        return autosaveWorker$mainTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
        return ((AutosaveWorker$mainTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        List list;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Job job = (Job) this.p$.getCoroutineContext().get(Job.Key);
            if (job != null) {
                job.invokeOnCompletion(new AnonymousClass1());
            }
            AutosaveWorker autosaveWorker = this.this$0;
            list = autosaveWorker.dedupedLocalItems;
            return BatchOperationUtilsKt.traverseAllByBatch(new AutosaveWorker.ScanLocalOperations(autosaveWorker, list));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
