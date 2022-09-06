package kotlinx.io.streams;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.DefaultPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteArrays.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"ByteArrayPool", "Lkotlinx/io/pool/DefaultPool;", "", "getByteArrayPool", "()Lkotlinx/io/pool/DefaultPool;", "ByteArrayPoolBufferSize", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteArraysKt {
    @NotNull
    private static final DefaultPool<byte[]> ByteArrayPool = new DefaultPool<byte[]>(128) { // from class: kotlinx.io.streams.ByteArraysKt$ByteArrayPool$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.io.pool.DefaultPool
        @NotNull
        public final byte[] produceInstance() {
            return new byte[4096];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.io.pool.DefaultPool
        public final void validateInstance(@NotNull byte[] instance) {
            Intrinsics.checkParameterIsNotNull(instance, "instance");
            if (instance.length == 4096) {
                super.validateInstance((ByteArraysKt$ByteArrayPool$1) instance);
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Unable to recycle buffer of wrong size: "), instance.length, " != 4096").toString());
        }
    };
    public static final int ByteArrayPoolBufferSize = 4096;

    @NotNull
    public static final DefaultPool<byte[]> getByteArrayPool() {
        return ByteArrayPool;
    }
}
