package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n¨\u0006\f"}, d2 = {"ByteChannel", "Lkotlinx/coroutines/io/ByteChannel;", "autoFlush", "", "ByteReadChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "content", "Ljava/nio/ByteBuffer;", "", "offset", "", "length", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteChannelKt {
    @NotNull
    public static final ByteChannel ByteChannel(boolean z) {
        return new ByteBufferChannel(z, null, 0, 6, null);
    }

    @NotNull
    public static /* synthetic */ ByteChannel ByteChannel$default(boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return ByteChannel(z);
    }

    @NotNull
    public static final ByteReadChannel ByteReadChannel(@NotNull ByteBuffer content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new ByteBufferChannel(content);
    }

    @NotNull
    public static /* synthetic */ ByteReadChannel ByteReadChannel$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ByteReadChannel(bArr, i, i2);
    }

    @NotNull
    public static final ByteReadChannel ByteReadChannel(@NotNull byte[] content, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        ByteBuffer wrap = ByteBuffer.wrap(content, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(wrap, "ByteBuffer.wrap(content, offset, length)");
        return new ByteBufferChannel(wrap);
    }
}
