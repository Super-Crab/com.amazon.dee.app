package com.amazon.tarazed.core.session;

import com.amazon.tarazed.core.session.sessionEvents.SessionEvents;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedStateChangeChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0086@"}, d2 = {"processFirst", "", SessionEvents.STATE_CHANGE, "Lkotlin/Function0;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedStateChangeChannel", f = "TarazedStateChangeChannel.kt", i = {0, 0, 0}, l = {101}, m = "processFirst", n = {"this", SessionEvents.STATE_CHANGE, "$this$withLock$iv"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes13.dex */
public final class TarazedStateChangeChannel$processFirst$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedStateChangeChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedStateChangeChannel$processFirst$1(TarazedStateChangeChannel tarazedStateChangeChannel, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedStateChangeChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.processFirst(null, this);
    }
}
