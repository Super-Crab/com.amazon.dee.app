package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LookAheadSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u0087Hø\u0001\u0000"}, d2 = {"consumeEachRemaining", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "visitor", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lkotlin/coroutines/Continuation;", "", "continuation", ""}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.LookAheadSessionKt", f = "LookAheadSession.kt", i = {0, 0, 0}, l = {55}, m = "consumeEachRemaining", n = {"$receiver", "visitor", "buffer"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class LookAheadSessionKt$consumeEachRemaining$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    public LookAheadSessionKt$consumeEachRemaining$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LookAheadSessionKt.consumeEachRemaining(null, null, this);
    }
}
