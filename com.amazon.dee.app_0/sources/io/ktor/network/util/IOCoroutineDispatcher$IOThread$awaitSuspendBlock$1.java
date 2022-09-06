package io.ktor.network.util;

import io.ktor.network.util.IOCoroutineDispatcher;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IOCoroutineDispatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "raw", "Lkotlin/coroutines/Continuation;", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class IOCoroutineDispatcher$IOThread$awaitSuspendBlock$1 extends Lambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ IOCoroutineDispatcher.IOThread this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IOCoroutineDispatcher$IOThread$awaitSuspendBlock$1(IOCoroutineDispatcher.IOThread iOThread) {
        super(1);
        this.this$0 = iOThread;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object mo12165invoke(@Nullable Continuation<? super Unit> continuation) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object coroutine_suspended;
        Continuation intercepted = continuation != null ? IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation) : null;
        atomicReferenceFieldUpdater = IOCoroutineDispatcher.IOThread.ThreadCont;
        if (atomicReferenceFieldUpdater.compareAndSet(this.this$0, null, intercepted)) {
            if (this.this$0.tasks.getNext() != this.this$0.tasks && atomicReferenceFieldUpdater.compareAndSet(this.this$0, intercepted, null)) {
                return Unit.INSTANCE;
            }
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended;
        }
        throw new IllegalStateException("Failed to set continuation");
    }
}
