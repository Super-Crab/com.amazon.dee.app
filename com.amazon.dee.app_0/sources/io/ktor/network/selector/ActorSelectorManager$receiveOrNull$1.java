package io.ktor.network.selector;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ActorSelectorManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005H\u0082@ø\u0001\u0000"}, d2 = {"receiveOrNull", "", "Lio/ktor/network/selector/LockFreeMPSCQueue;", "Lio/ktor/network/selector/Selectable;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.selector.ActorSelectorManager", f = "ActorSelectorManager.kt", i = {0, 0}, l = {150}, m = "receiveOrNull", n = {"this", "$receiver"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class ActorSelectorManager$receiveOrNull$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ActorSelectorManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActorSelectorManager$receiveOrNull$1(ActorSelectorManager actorSelectorManager, Continuation continuation) {
        super(continuation);
        this.this$0 = actorSelectorManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.receiveOrNull(null, this);
    }
}
