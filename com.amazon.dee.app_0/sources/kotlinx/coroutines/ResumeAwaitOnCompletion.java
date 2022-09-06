package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JobSupport.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/JobSupport;", "job", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ResumeAwaitOnCompletion<T> extends JobNode<JobSupport> {
    private final CancellableContinuationImpl<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeAwaitOnCompletion(@NotNull JobSupport jobSupport, @NotNull CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        super(jobSupport);
        this.continuation = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResumeAwaitOnCompletion[");
        outline107.append(this.continuation);
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        Object state$kotlinx_coroutines_core = ((JobSupport) this.job).getState$kotlinx_coroutines_core();
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                CancellableContinuationImpl<T> cancellableContinuationImpl = this.continuation;
                Throwable th2 = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(th2)));
                return;
            }
            CancellableContinuationImpl<T> cancellableContinuationImpl2 = this.continuation;
            Object unboxState = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl2.resumeWith(Result.m10378constructorimpl(unboxState));
            return;
        }
        throw new AssertionError();
    }
}
