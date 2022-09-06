package io.ktor.util;

import com.amazon.alexa.location.utils.MetricsUtil;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: NIO.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0003H\u0007\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u0001H\u0007¨\u0006\u000f"}, d2 = {"copy", "Ljava/nio/ByteBuffer;", "size", "", "pool", "Lkotlinx/io/pool/ObjectPool;", "decodeString", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "moveTo", "destination", MetricsUtil.LegacyMetricTypes.LIMIT, "moveToByteArray", "", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class NIOKt {
    @InternalAPI
    @NotNull
    public static final ByteBuffer copy(@NotNull ByteBuffer receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ByteBuffer allocate = ByteBuffer.allocate(i);
        ByteBuffer slice = receiver$0.slice();
        Intrinsics.checkExpressionValueIsNotNull(slice, "this@copy.slice()");
        Intrinsics.checkExpressionValueIsNotNull(allocate, "this@apply");
        moveTo$default(slice, allocate, 0, 2, null);
        allocate.clear();
        Intrinsics.checkExpressionValueIsNotNull(allocate, "ByteBuffer.allocate(size…ly)\n        clear()\n    }");
        return allocate;
    }

    @InternalAPI
    @NotNull
    public static /* synthetic */ ByteBuffer copy$default(ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = byteBuffer.remaining();
        }
        return copy(byteBuffer, i);
    }

    @InternalAPI
    @NotNull
    public static final String decodeString(@NotNull ByteBuffer receiver$0, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        String charBuffer = charset.decode(receiver$0).toString();
        Intrinsics.checkExpressionValueIsNotNull(charBuffer, "charset.decode(this).toString()");
        return charBuffer;
    }

    @InternalAPI
    @NotNull
    public static /* synthetic */ String decodeString$default(ByteBuffer byteBuffer, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeString(byteBuffer, charset);
    }

    @InternalAPI
    public static final int moveTo(@NotNull ByteBuffer receiver$0, @NotNull ByteBuffer destination, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        int min = Math.min(i, Math.min(receiver$0.remaining(), destination.remaining()));
        if (min == receiver$0.remaining()) {
            destination.put(receiver$0);
        } else {
            int limit = receiver$0.limit();
            receiver$0.limit(receiver$0.position() + min);
            destination.put(receiver$0);
            receiver$0.limit(limit);
        }
        return min;
    }

    @InternalAPI
    public static /* synthetic */ int moveTo$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return moveTo(byteBuffer, byteBuffer2, i);
    }

    @InternalAPI
    @NotNull
    public static final byte[] moveToByteArray(@NotNull ByteBuffer receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        byte[] bArr = new byte[receiver$0.remaining()];
        receiver$0.get(bArr);
        return bArr;
    }

    @InternalAPI
    @NotNull
    public static /* synthetic */ ByteBuffer copy$default(ByteBuffer byteBuffer, ObjectPool objectPool, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        return copy(byteBuffer, objectPool, i);
    }

    @InternalAPI
    @NotNull
    public static final ByteBuffer copy(@NotNull ByteBuffer receiver$0, @NotNull ObjectPool<ByteBuffer> pool, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        ByteBuffer mo12351borrow = pool.mo12351borrow();
        mo12351borrow.limit(i);
        ByteBuffer slice = receiver$0.slice();
        Intrinsics.checkExpressionValueIsNotNull(slice, "this@copy.slice()");
        moveTo$default(slice, mo12351borrow, 0, 2, null);
        mo12351borrow.flip();
        return mo12351borrow;
    }
}
