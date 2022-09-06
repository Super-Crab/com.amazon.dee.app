package kotlinx.coroutines.io.internal;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.internal.ReadWriteBufferState;
import kotlinx.io.pool.DefaultPool;
import kotlinx.io.pool.NoPoolImpl;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: ObjectPool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n\"\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u0010"}, d2 = {"BUFFER_OBJECT_POOL_SIZE", "", "BUFFER_POOL_SIZE", "BUFFER_SIZE", "getBUFFER_SIZE", "()I", "BufferObjectNoPool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "getBufferObjectNoPool", "()Lkotlinx/io/pool/ObjectPool;", "BufferObjectPool", "getBufferObjectPool", "BufferPool", "Ljava/nio/ByteBuffer;", "getBufferPool", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ObjectPoolKt {
    @NotNull
    private static final ObjectPool<ReadWriteBufferState.Initial> BufferObjectNoPool;
    @NotNull
    private static final ObjectPool<ReadWriteBufferState.Initial> BufferObjectPool;
    @NotNull
    private static final ObjectPool<ByteBuffer> BufferPool;
    private static final int BUFFER_SIZE = UtilsKt.getIOIntProperty("BufferSize", 4096);
    private static final int BUFFER_POOL_SIZE = UtilsKt.getIOIntProperty("BufferPoolSize", 2048);
    private static final int BUFFER_OBJECT_POOL_SIZE = UtilsKt.getIOIntProperty("BufferObjectPoolSize", 1024);

    static {
        final int i = BUFFER_POOL_SIZE;
        BufferPool = new DefaultPool<ByteBuffer>(i) { // from class: kotlinx.coroutines.io.internal.ObjectPoolKt$BufferPool$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            @NotNull
            public ByteBuffer clearInstance(@NotNull ByteBuffer instance) {
                Intrinsics.checkParameterIsNotNull(instance, "instance");
                instance.clear();
                return instance;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            @NotNull
            public ByteBuffer produceInstance() {
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(ObjectPoolKt.getBUFFER_SIZE());
                Intrinsics.checkExpressionValueIsNotNull(allocateDirect, "ByteBuffer.allocateDirect(BUFFER_SIZE)");
                return allocateDirect;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            public void validateInstance(@NotNull ByteBuffer instance) {
                Intrinsics.checkParameterIsNotNull(instance, "instance");
                if (instance.capacity() == ObjectPoolKt.getBUFFER_SIZE()) {
                    return;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        };
        final int i2 = BUFFER_OBJECT_POOL_SIZE;
        BufferObjectPool = new DefaultPool<ReadWriteBufferState.Initial>(i2) { // from class: kotlinx.coroutines.io.internal.ObjectPoolKt$BufferObjectPool$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            public void disposeInstance(@NotNull ReadWriteBufferState.Initial instance) {
                Intrinsics.checkParameterIsNotNull(instance, "instance");
                ObjectPoolKt.getBufferPool().recycle(instance.backingBuffer);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            @NotNull
            public ReadWriteBufferState.Initial produceInstance() {
                return new ReadWriteBufferState.Initial(ObjectPoolKt.getBufferPool().mo12351borrow(), 0, 2, null);
            }
        };
        BufferObjectNoPool = new NoPoolImpl<ReadWriteBufferState.Initial>() { // from class: kotlinx.coroutines.io.internal.ObjectPoolKt$BufferObjectNoPool$1
            @Override // kotlinx.io.pool.ObjectPool
            @NotNull
            /* renamed from: borrow  reason: collision with other method in class */
            public ReadWriteBufferState.Initial mo12351borrow() {
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(ObjectPoolKt.getBUFFER_SIZE());
                Intrinsics.checkExpressionValueIsNotNull(allocateDirect, "ByteBuffer.allocateDirect(BUFFER_SIZE)");
                return new ReadWriteBufferState.Initial(allocateDirect, 0, 2, null);
            }
        };
    }

    public static final int getBUFFER_SIZE() {
        return BUFFER_SIZE;
    }

    @NotNull
    public static final ObjectPool<ReadWriteBufferState.Initial> getBufferObjectNoPool() {
        return BufferObjectNoPool;
    }

    @NotNull
    public static final ObjectPool<ReadWriteBufferState.Initial> getBufferObjectPool() {
        return BufferObjectPool;
    }

    @NotNull
    public static final ObjectPool<ByteBuffer> getBufferPool() {
        return BufferPool;
    }
}
