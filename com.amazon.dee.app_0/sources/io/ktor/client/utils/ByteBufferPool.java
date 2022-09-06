package io.ktor.client.utils;

import io.ktor.util.InternalAPI;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.DefaultPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: CIOJvm.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0014J\b\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lio/ktor/client/utils/ByteBufferPool;", "Lkotlinx/io/pool/DefaultPool;", "Ljava/nio/ByteBuffer;", "()V", "clearInstance", "instance", "produceInstance", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ByteBufferPool extends DefaultPool<ByteBuffer> {
    public ByteBufferPool() {
        super(1000);
    }

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
        ByteBuffer allocate = ByteBuffer.allocate(4096);
        if (allocate == null) {
            Intrinsics.throwNpe();
        }
        return allocate;
    }
}
