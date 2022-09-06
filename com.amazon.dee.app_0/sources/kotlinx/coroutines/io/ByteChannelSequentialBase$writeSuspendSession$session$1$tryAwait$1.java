package kotlinx.coroutines.io;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@ø\u0001\u0000"}, d2 = {"tryAwait", "", JsonReportFormat.COUNT, "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1", f = "ByteChannelSequential.kt", i = {0, 0, 0}, l = {652}, m = "tryAwait", n = {"this", JsonReportFormat.COUNT, "this_$iv"}, s = {"L$0", "I$0", "L$1"})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase$writeSuspendSession$session$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1(ByteChannelSequentialBase$writeSuspendSession$session$1 byteChannelSequentialBase$writeSuspendSession$session$1, Continuation continuation) {
        super(continuation);
        this.this$0 = byteChannelSequentialBase$writeSuspendSession$session$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.tryAwait(0, this);
    }
}
