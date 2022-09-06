package io.ktor.util.pipeline;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PipelineContext.kt */
@ContextDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004J\b\u0010\n\u001a\u00020\u000bH&J\u0011\u0010\f\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0005\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "TSubject", "", "TContext", "Lkotlinx/coroutines/CoroutineScope;", "context", "getContext", "()Ljava/lang/Object;", "subject", "getSubject", "finish", "", "proceed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWith", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface PipelineContext<TSubject, TContext> extends CoroutineScope {
    void finish();

    @NotNull
    TContext getContext();

    @NotNull
    TSubject getSubject();

    @Nullable
    Object proceed(@NotNull Continuation<? super TSubject> continuation);

    @Nullable
    Object proceedWith(@NotNull TSubject tsubject, @NotNull Continuation<? super TSubject> continuation);
}
