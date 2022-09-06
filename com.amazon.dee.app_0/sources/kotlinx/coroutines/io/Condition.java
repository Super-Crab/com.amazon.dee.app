package kotlinx.coroutines.io;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConditionJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000b\u001a\u00020\bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\fJ!\u0010\u000b\u001a\u00020\b2\u000e\b\u0004\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/io/Condition;", "", "predicate", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "cond", "Lkotlin/coroutines/Continuation;", "", "getPredicate", "()Lkotlin/jvm/functions/Function0;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "block", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "check", "signal", "toString", "", "Companion", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class Condition {
    public static final Companion Companion = new Companion(null);
    private static final AtomicReferenceFieldUpdater<Condition, Continuation<Unit>> updater;
    private volatile Continuation<? super Unit> cond;
    @NotNull
    private final Function0<Boolean> predicate;

    /* compiled from: ConditionJVM.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R(\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u0004X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/io/Condition$Companion;", "", "()V", "updater", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlinx/coroutines/io/Condition;", "Lkotlin/coroutines/Continuation;", "", "updater$annotations", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        private static /* synthetic */ void updater$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        AtomicReferenceFieldUpdater<Condition, Continuation<Unit>> newUpdater = AtomicReferenceFieldUpdater.newUpdater(Condition.class, Continuation.class, "cond");
        if (newUpdater != null) {
            updater = newUpdater;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.concurrent.atomic.AtomicReferenceFieldUpdater<kotlinx.coroutines.io.Condition, kotlin.coroutines.Continuation<kotlin.Unit>?>");
    }

    public Condition(@NotNull Function0<Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        this.predicate = predicate;
    }

    public static final /* synthetic */ AtomicReferenceFieldUpdater access$getUpdater$cp() {
        return updater;
    }

    private final Object await(Function0<Unit> function0, Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        if (getPredicate().mo12560invoke().booleanValue()) {
            return Unit.INSTANCE;
        }
        InlineMarker.mark(0);
        if (updater.compareAndSet(this, null, continuation)) {
            if (!getPredicate().mo12560invoke().booleanValue() || !updater.compareAndSet(this, continuation, null)) {
                function0.mo12560invoke();
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            } else {
                coroutine_suspended = Unit.INSTANCE;
            }
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (coroutine_suspended == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return coroutine_suspended;
        }
        throw new IllegalStateException();
    }

    @Nullable
    private final Object await$$forInline(@NotNull Continuation continuation) {
        Object coroutine_suspended;
        if (getPredicate().mo12560invoke().booleanValue()) {
            return Unit.INSTANCE;
        }
        InlineMarker.mark(0);
        if (updater.compareAndSet(this, null, continuation)) {
            Object coroutine_suspended2 = (!getPredicate().mo12560invoke().booleanValue() || !updater.compareAndSet(this, continuation, null)) ? IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() : Unit.INSTANCE;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (coroutine_suspended2 == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return coroutine_suspended2;
        }
        throw new IllegalStateException();
    }

    public final boolean check() {
        return this.predicate.mo12560invoke().booleanValue();
    }

    @NotNull
    public final Function0<Boolean> getPredicate() {
        return this.predicate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void signal() {
        Continuation intercepted;
        Continuation<? super Unit> continuation = this.cond;
        if (continuation == null || !this.predicate.mo12560invoke().booleanValue() || !updater.compareAndSet(this, continuation, null)) {
            return;
        }
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        Unit unit = Unit.INSTANCE;
        Result.Companion companion = Result.Companion;
        intercepted.resumeWith(Result.m10378constructorimpl(unit));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Condition(cond=");
        outline107.append(this.cond);
        outline107.append(')');
        return outline107.toString();
    }

    @Nullable
    public final Object await(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (getPredicate().mo12560invoke().booleanValue()) {
            return Unit.INSTANCE;
        }
        if (updater.compareAndSet(this, null, continuation)) {
            Object coroutine_suspended2 = (!getPredicate().mo12560invoke().booleanValue() || !updater.compareAndSet(this, continuation, null)) ? IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() : Unit.INSTANCE;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (coroutine_suspended2 == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return coroutine_suspended2;
        }
        throw new IllegalStateException();
    }
}
