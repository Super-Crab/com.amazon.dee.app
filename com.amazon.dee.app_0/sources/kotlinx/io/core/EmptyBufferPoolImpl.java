package kotlinx.io.core;

import kotlin.Metadata;
import kotlinx.io.pool.NoPoolImpl;
import org.jetbrains.annotations.NotNull;
/* compiled from: Buffers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u0005"}, d2 = {"Lkotlinx/io/core/EmptyBufferPoolImpl;", "Lkotlinx/io/pool/NoPoolImpl;", "Lkotlinx/io/core/IoBuffer;", "()V", "borrow", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class EmptyBufferPoolImpl extends NoPoolImpl<IoBuffer> {
    public static final EmptyBufferPoolImpl INSTANCE = new EmptyBufferPoolImpl();

    private EmptyBufferPoolImpl() {
    }

    @Override // kotlinx.io.pool.ObjectPool
    @NotNull
    /* renamed from: borrow  reason: collision with other method in class */
    public IoBuffer mo12351borrow() {
        return IoBuffer.Companion.getEmpty();
    }
}
