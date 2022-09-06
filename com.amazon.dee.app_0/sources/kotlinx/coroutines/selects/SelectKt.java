package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.time.ExperimentalTime;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Select.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u001f\b\u0004\u0010\u000f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001aK\u0010\u0015\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u001c\u0010\u0018\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010H\u0007ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0003\"\u0016\u0010\n\u001a\u00020\u000b8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"ALREADY_SELECTED", "", "ALREADY_SELECTED$annotations", "()V", "getALREADY_SELECTED", "()Ljava/lang/Object;", "RESUMED", "RESUMED$annotations", "UNDECIDED", "UNDECIDED$annotations", "selectOpSequenceNumber", "Lkotlinx/coroutines/selects/SeqNumber;", "selectOpSequenceNumber$annotations", "select", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onTimeout", "timeout", "Lkotlin/time/Duration;", "block", "Lkotlin/coroutines/Continuation;", "onTimeout-0lHKgQg", "(Lkotlinx/coroutines/selects/SelectBuilder;DLkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SelectKt {
    @NotNull
    private static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");
    private static final Object UNDECIDED = new Symbol("UNDECIDED");
    private static final Object RESUMED = new Symbol("RESUMED");
    private static final SeqNumber selectOpSequenceNumber = new SeqNumber();

    public static /* synthetic */ void ALREADY_SELECTED$annotations() {
    }

    private static /* synthetic */ void RESUMED$annotations() {
    }

    private static /* synthetic */ void UNDECIDED$annotations() {
    }

    public static final /* synthetic */ Object access$getRESUMED$p() {
        return RESUMED;
    }

    public static final /* synthetic */ SeqNumber access$getSelectOpSequenceNumber$p() {
        return selectOpSequenceNumber;
    }

    public static final /* synthetic */ Object access$getUNDECIDED$p() {
        return UNDECIDED;
    }

    @NotNull
    public static final Object getALREADY_SELECTED() {
        return ALREADY_SELECTED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ExperimentalTime
    @ExperimentalCoroutinesApi
    /* renamed from: onTimeout-0lHKgQg */
    public static final <R> void m12323onTimeout0lHKgQg(@NotNull SelectBuilder<? super R> selectBuilder, double d, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectBuilder.onTimeout(DelayKt.m12236toDelayMillisLRDsOJo(d), function1);
    }

    @Nullable
    public static final <R> Object select(@NotNull Function1<? super SelectBuilder<? super R>, Unit> function1, @NotNull Continuation<? super R> continuation) {
        Object coroutine_suspended;
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.mo12165invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Nullable
    private static final Object select$$forInline(@NotNull Function1 function1, @NotNull Continuation continuation) {
        Object coroutine_suspended;
        InlineMarker.mark(0);
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.mo12165invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    private static /* synthetic */ void selectOpSequenceNumber$annotations() {
    }
}
