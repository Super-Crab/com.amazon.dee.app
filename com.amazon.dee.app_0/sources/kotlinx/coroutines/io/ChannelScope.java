package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: Coroutines.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/io/ChannelScope;", "Lkotlinx/coroutines/io/ReaderScope;", "Lkotlinx/coroutines/io/WriterScope;", "Lkotlinx/coroutines/CoroutineScope;", "delegate", "channel", "Lkotlinx/coroutines/io/ByteChannel;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/io/ByteChannel;)V", "getChannel", "()Lkotlinx/coroutines/io/ByteChannel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class ChannelScope implements ReaderScope, WriterScope, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0;
    @NotNull
    private final ByteChannel channel;

    public ChannelScope(@NotNull CoroutineScope delegate, @NotNull ByteChannel channel) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.$$delegate_0 = delegate;
        this.channel = channel;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // kotlinx.coroutines.io.ReaderScope, kotlinx.coroutines.io.WriterScope
    @NotNull
    /* renamed from: getChannel */
    public ByteChannel mo12311getChannel() {
        return this.channel;
    }
}
