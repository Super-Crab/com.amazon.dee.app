package io.ktor.util.pipeline;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PipelineContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00020\u0006BY\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00028\u0001\u0012?\u0010\t\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000e0\nø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u001f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0002J\b\u0010 \u001a\u00020\rH\u0002J\u0019\u0010!\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\rH\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002J\u0011\u0010'\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0019\u0010)\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\"J\u001e\u0010*\u001a\u00020\r2\f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0002ø\u0001\u0000¢\u0006\u0002\u0010-J\u0012\u0010.\u001a\u00020/2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u0002RJ\u0010\t\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000e0\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00028\u0000@RX\u0096\u000e¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001e\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lio/ktor/util/pipeline/SuspendFunctionGun;", "TSubject", "", "TContext", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/util/pipeline/PipelineExecutor;", "Lkotlinx/coroutines/CoroutineScope;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "context", "blocks", "", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/List;)V", "getContext", "()Ljava/lang/Object;", "Ljava/lang/Object;", "continuation", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "index", "", "lastPeekedIndex", "rootContinuation", "<set-?>", "subject", "getSubject", "addContinuation", "discardLastRootContinuation", "execute", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finish", "loop", "", "direct", "proceed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWith", "resumeRootWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "unexpectedRootContinuationValue", "", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class SuspendFunctionGun<TSubject, TContext> implements PipelineContext<TSubject, TContext>, PipelineExecutor<TSubject>, CoroutineScope {
    private final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> blocks;
    @NotNull
    private final TContext context;
    private final Continuation<Unit> continuation;
    private int index;
    private int lastPeekedIndex;
    private Object rootContinuation;
    @NotNull
    private TSubject subject;

    /* JADX WARN: Multi-variable type inference failed */
    public SuspendFunctionGun(@NotNull TSubject initial, @NotNull TContext context, @NotNull List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> blocks) {
        Intrinsics.checkParameterIsNotNull(initial, "initial");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(blocks, "blocks");
        this.context = context;
        this.blocks = blocks;
        this.lastPeekedIndex = -1;
        this.continuation = new SuspendFunctionGun$continuation$1(this);
        this.subject = initial;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addContinuation(Continuation<? super TSubject> continuation) {
        int lastIndex;
        Object obj = this.rootContinuation;
        if (obj == null) {
            this.lastPeekedIndex = 0;
            this.rootContinuation = continuation;
        } else if (obj instanceof Continuation) {
            ArrayList arrayList = new ArrayList(this.blocks.size());
            arrayList.add(obj);
            arrayList.add(continuation);
            this.lastPeekedIndex = 1;
            this.rootContinuation = arrayList;
        } else if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(continuation);
            lastIndex = CollectionsKt__CollectionsKt.getLastIndex((List) obj);
            this.lastPeekedIndex = lastIndex;
        } else {
            unexpectedRootContinuationValue(obj);
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void discardLastRootContinuation() {
        int lastIndex;
        int lastIndex2;
        Object obj = this.rootContinuation;
        if (obj != null) {
            if (obj instanceof Continuation) {
                this.lastPeekedIndex = -1;
                this.rootContinuation = null;
                return;
            } else if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                if (!arrayList.isEmpty()) {
                    List list = (List) obj;
                    lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
                    arrayList.remove(lastIndex);
                    lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
                    this.lastPeekedIndex = lastIndex2;
                    return;
                }
                throw new IllegalStateException("No more continuations to resume");
            } else {
                unexpectedRootContinuationValue(obj);
                throw null;
            }
        }
        throw new IllegalStateException("No more continuations to resume");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loop(boolean z) {
        Object invoke;
        Object coroutine_suspended;
        do {
            int i = this.index;
            if (i == this.blocks.size()) {
                if (z) {
                    return true;
                }
                Result.Companion companion = Result.Companion;
                resumeRootWith(Result.m10378constructorimpl(getSubject()));
                return false;
            }
            this.index = i + 1;
            Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object> function3 = this.blocks.get(i);
            try {
                TSubject subject = getSubject();
                Continuation<Unit> continuation = this.continuation;
                if (function3 != null) {
                    invoke = ((Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function3, 3)).invoke(this, subject, continuation);
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type (R, A, kotlin.coroutines.Continuation<kotlin.Unit>) -> kotlin.Any?");
                }
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                resumeRootWith(Result.m10378constructorimpl(ResultKt.createFailure(th)));
                return false;
            }
        } while (invoke != coroutine_suspended);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeRootWith(Object obj) {
        int lastIndex;
        int lastIndex2;
        Object obj2 = this.rootContinuation;
        if (obj2 != null) {
            if (obj2 instanceof Continuation) {
                this.rootContinuation = null;
                this.lastPeekedIndex = -1;
            } else if (obj2 instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj2;
                if (!arrayList.isEmpty()) {
                    List list = (List) obj2;
                    lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
                    this.lastPeekedIndex = lastIndex - 1;
                    lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
                    obj2 = arrayList.remove(lastIndex2);
                } else {
                    throw new IllegalStateException("No more continuations to resume");
                }
            } else {
                unexpectedRootContinuationValue(obj2);
                throw null;
            }
            if (obj2 != null) {
                ((Continuation) obj2).resumeWith(obj);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.Continuation<TSubject>");
        }
        throw new IllegalStateException("No more continuations to resume");
    }

    private final Void unexpectedRootContinuationValue(Object obj) {
        throw new IllegalStateException(GeneratedOutlineSupport1.outline70("Unexpected rootContinuation content: ", obj));
    }

    @Override // io.ktor.util.pipeline.PipelineExecutor
    @Nullable
    public Object execute(@NotNull TSubject tsubject, @NotNull Continuation<? super TSubject> continuation) {
        this.index = 0;
        if (this.index == this.blocks.size()) {
            return tsubject;
        }
        this.subject = tsubject;
        if (this.rootContinuation == null) {
            return proceed(continuation);
        }
        throw new IllegalStateException("Already started");
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void finish() {
        this.index = this.blocks.size();
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    @NotNull
    public TContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.continuation.getContext();
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    @NotNull
    public TSubject getSubject() {
        return this.subject;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    @Nullable
    public Object proceed(@NotNull Continuation<? super TSubject> continuation) {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        if (this.index == this.blocks.size()) {
            coroutine_suspended = getSubject();
        } else {
            addContinuation(continuation);
            if (loop(true)) {
                discardLastRootContinuation();
                coroutine_suspended = getSubject();
            } else {
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
        }
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == coroutine_suspended2) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    @Nullable
    public Object proceedWith(@NotNull TSubject tsubject, @NotNull Continuation<? super TSubject> continuation) {
        this.subject = tsubject;
        return proceed(continuation);
    }
}
