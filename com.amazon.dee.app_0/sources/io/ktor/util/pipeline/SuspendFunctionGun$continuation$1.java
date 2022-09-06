package io.ktor.util.pipeline;

import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PipelineContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0010\u0010\u000e\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010H\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001H\u0002J\u001e\u0010\u0012\u001a\u00020\u00022\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u001c\u0010\u0005\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"io/ktor/util/pipeline/SuspendFunctionGun$continuation$1", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lio/ktor/util/CoroutineStackFrame;", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "context$annotations", "()V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lio/ktor/util/StackTraceElement;", "peekContinuation", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class SuspendFunctionGun$continuation$1 implements Continuation<Unit>, CoroutineStackFrame {
    final /* synthetic */ SuspendFunctionGun this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SuspendFunctionGun$continuation$1(SuspendFunctionGun suspendFunctionGun) {
        this.this$0 = suspendFunctionGun;
    }

    public static /* synthetic */ void context$annotations() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000a, code lost:
        r0 = r4.this$0.rootContinuation;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final kotlin.coroutines.Continuation<?> peekContinuation() {
        /*
            r4 = this;
            io.ktor.util.pipeline.SuspendFunctionGun r0 = r4.this$0
            int r0 = io.ktor.util.pipeline.SuspendFunctionGun.access$getLastPeekedIndex$p(r0)
            r1 = 0
            if (r0 >= 0) goto La
            return r1
        La:
            io.ktor.util.pipeline.SuspendFunctionGun r0 = r4.this$0
            java.lang.Object r0 = io.ktor.util.pipeline.SuspendFunctionGun.access$getRootContinuation$p(r0)
            if (r0 != 0) goto L13
            return r1
        L13:
            boolean r2 = r0 instanceof kotlin.coroutines.Continuation
            if (r2 == 0) goto L28
            io.ktor.util.pipeline.SuspendFunctionGun r1 = r4.this$0
            int r2 = io.ktor.util.pipeline.SuspendFunctionGun.access$getLastPeekedIndex$p(r1)
            int r2 = r2 + (-1)
            io.ktor.util.pipeline.SuspendFunctionGun.access$setLastPeekedIndex$p(r1, r2)
            io.ktor.util.pipeline.SuspendFunctionGun.access$getLastPeekedIndex$p(r1)
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            return r0
        L28:
            boolean r2 = r0 instanceof java.util.ArrayList
            if (r2 == 0) goto L51
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L35
            return r1
        L35:
            io.ktor.util.pipeline.SuspendFunctionGun r1 = r4.this$0
            int r2 = io.ktor.util.pipeline.SuspendFunctionGun.access$getLastPeekedIndex$p(r1)
            int r3 = r2 + (-1)
            io.ktor.util.pipeline.SuspendFunctionGun.access$setLastPeekedIndex$p(r1, r3)
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L49
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            return r0
        L49:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.coroutines.Continuation<*>"
            r0.<init>(r1)
            throw r0
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.pipeline.SuspendFunctionGun$continuation$1.peekContinuation():kotlin.coroutines.Continuation");
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<?> peekContinuation = peekContinuation();
        if (!(peekContinuation instanceof CoroutineStackFrame)) {
            peekContinuation = null;
        }
        return (CoroutineStackFrame) peekContinuation;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        Object obj;
        obj = this.this$0.rootContinuation;
        if (obj != null) {
            if (obj instanceof Continuation) {
                return ((Continuation) obj).getContext();
            }
            if (!(obj instanceof List)) {
                throw new IllegalStateException("Unexpected rootContinuation value");
            }
            return ((Continuation) CollectionsKt.last((List<? extends Object>) obj)).getContext();
        }
        throw new IllegalStateException("Not started");
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        if (!Result.m10384isFailureimpl(obj)) {
            this.this$0.loop(false);
            return;
        }
        SuspendFunctionGun suspendFunctionGun = this.this$0;
        Result.Companion companion = Result.Companion;
        Throwable m10381exceptionOrNullimpl = Result.m10381exceptionOrNullimpl(obj);
        if (m10381exceptionOrNullimpl == null) {
            Intrinsics.throwNpe();
        }
        suspendFunctionGun.resumeRootWith(Result.m10378constructorimpl(ResultKt.createFailure(m10381exceptionOrNullimpl)));
    }
}
