package io.ktor.util.cio;

import io.ktor.util.KtorExperimentalAPI;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteBufferPool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"DEFAULT_BUFFER_SIZE", "", "DEFAULT_KTOR_POOL_SIZE", "KtorDefaultPool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "KtorDefaultPool$annotations", "()V", "getKtorDefaultPool", "()Lkotlinx/io/pool/ObjectPool;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ByteBufferPoolKt {
    public static final int DEFAULT_BUFFER_SIZE = 4098;
    public static final int DEFAULT_KTOR_POOL_SIZE = 2048;
    @NotNull
    private static final ObjectPool<ByteBuffer> KtorDefaultPool = new ByteBufferPool();

    @KtorExperimentalAPI
    public static /* synthetic */ void KtorDefaultPool$annotations() {
    }

    @NotNull
    public static final ObjectPool<ByteBuffer> getKtorDefaultPool() {
        return KtorDefaultPool;
    }
}
