package kotlinx.io.core;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.SingleInstancePool;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteReadPacketExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lkotlinx/io/core/SingleByteBufferPool;", "Lkotlinx/io/pool/SingleInstancePool;", "Lkotlinx/io/core/IoBuffer;", "instance", "Ljava/nio/ByteBuffer;", "release", "Lkotlin/Function1;", "", "(Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function1;)V", "getInstance", "()Ljava/nio/ByteBuffer;", "getRelease", "()Lkotlin/jvm/functions/Function1;", "disposeInstance", "produceInstance", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class SingleByteBufferPool extends SingleInstancePool<IoBuffer> {
    @NotNull
    private final ByteBuffer instance;
    @NotNull
    private final Function1<ByteBuffer, Unit> release;

    /* JADX WARN: Multi-variable type inference failed */
    public SingleByteBufferPool(@NotNull ByteBuffer instance, @NotNull Function1<? super ByteBuffer, Unit> release) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
        Intrinsics.checkParameterIsNotNull(release, "release");
        this.instance = instance;
        this.release = release;
    }

    @NotNull
    public final ByteBuffer getInstance() {
        return this.instance;
    }

    @NotNull
    public final Function1<ByteBuffer, Unit> getRelease() {
        return this.release;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.io.pool.SingleInstancePool
    public void disposeInstance(@NotNull IoBuffer instance) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
        this.release.mo12165invoke(this.instance);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.io.pool.SingleInstancePool
    @NotNull
    public IoBuffer produceInstance() {
        return new IoBuffer(this.instance);
    }
}
