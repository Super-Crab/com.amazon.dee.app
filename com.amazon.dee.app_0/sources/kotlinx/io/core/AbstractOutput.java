package kotlinx.io.core;

import com.amazon.alexa.presence.service.PresenceJobService;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Builder.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0007\b'\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH$J\u0006\u0010\u0011\u001a\u00020\u000fJ\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0004H$J\u0015\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0014J\u0006\u0010\u0015\u001a\u00020\u000fR*\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00048D@DX\u0085\u000e¢\u0006\u0012\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lkotlinx/io/core/AbstractOutput;", "Lkotlinx/io/core/BytePacketBuilderPlatformBase;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "(Lkotlinx/io/pool/ObjectPool;)V", "newValue", "currentTail", "currentTail$annotations", "()V", "getCurrentTail", "()Lkotlinx/io/core/IoBuffer;", "setCurrentTail", "(Lkotlinx/io/core/IoBuffer;)V", "close", "", "closeDestination", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "buffer", "last", "last$kotlinx_io", "release", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class AbstractOutput extends BytePacketBuilderPlatformBase {
    public AbstractOutput() {
        this(null, 1, null);
    }

    public /* synthetic */ AbstractOutput(ObjectPool<IoBuffer> objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? IoBuffer.Companion.getPool() : objectPool);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Will be removed. Override flush(buffer) properly.")
    protected static /* synthetic */ void currentTail$annotations() {
    }

    @Override // kotlinx.io.core.Output, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            flush();
        } finally {
            release();
        }
    }

    protected abstract void closeDestination();

    @Override // kotlinx.io.core.Output
    public final void flush() {
        last$kotlinx_io(IoBuffer.Companion.getEmpty());
    }

    protected abstract void flush(@NotNull IoBuffer ioBuffer);

    @NotNull
    protected final IoBuffer getCurrentTail() {
        return getTail();
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase
    public final void last$kotlinx_io(@NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        IoBuffer tail = getTail();
        setTail(buffer);
        if (tail == IoBuffer.Companion.getEmpty()) {
            return;
        }
        while (true) {
            IoBuffer next = tail.getNext();
            tail.setNext(null);
            try {
                flush(tail);
                tail.release(getPool());
                if (next == null) {
                    return;
                }
                tail = next;
            } finally {
            }
        }
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase
    public final void release() {
        IoBuffer tail = getTail();
        setTail(IoBuffer.Companion.getEmpty());
        if (tail != IoBuffer.Companion.getEmpty()) {
            tail.release(getPool());
        }
        closeDestination();
    }

    protected final void setCurrentTail(@NotNull IoBuffer newValue) {
        Intrinsics.checkParameterIsNotNull(newValue, "newValue");
        setTail(newValue);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractOutput(@NotNull ObjectPool<IoBuffer> pool) {
        super(pool);
        Intrinsics.checkParameterIsNotNull(pool, "pool");
    }
}
