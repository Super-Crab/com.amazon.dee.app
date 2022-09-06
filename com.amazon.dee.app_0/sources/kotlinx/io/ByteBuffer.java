package kotlinx.io;

import com.dee.app.metrics.MetricsConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Buffers.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = WritersKt.message)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0000J\u0006\u0010\n\u001a\u00020\u0000J\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\u000b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\fJ\u0016\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\bJ\u001e\u0010 \u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0014J\u0016\u0010#\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0016J\u0016\u0010$\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0018J\u0016\u0010%\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010J\u0016\u0010&\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u001bJ\u0016\u0010'\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u001dJ\u0016\u0010(\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\u0010R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lkotlinx/io/ByteBuffer;", "", "dw", "Ljava/nio/ByteBuffer;", "Lkotlinx/io/JByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "()V", "array", "", MetricsConstants.Method.CACHE_CLEAR, "flip", MetricsConstants.Method.CACHE_GET, "", "", "dst", "offset", "", "cnt", "index", "getChar", "", "getDouble", "", "getFloat", "", "getInt", "getLong", "", "getShort", "", "order", "Lkotlinx/io/ByteOrder;", MetricsConstants.Method.CACHE_PUT, "value", "src", "putChar", "putDouble", "putFloat", "putInt", "putLong", "putShort", "Companion", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ByteBuffer {
    public static final Companion Companion = new Companion(null);
    private java.nio.ByteBuffer dw;

    /* compiled from: Buffers.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/io/ByteBuffer$Companion;", "", "()V", "allocate", "Lkotlinx/io/ByteBuffer;", "capacity", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ByteBuffer allocate(int i) {
            java.nio.ByteBuffer allocate = java.nio.ByteBuffer.allocate(i);
            Intrinsics.checkExpressionValueIsNotNull(allocate, "java.nio.ByteBuffer.allocate(capacity)");
            return new ByteBuffer(allocate);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private ByteBuffer() {
    }

    @NotNull
    public final byte[] array() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byte[] array = byteBuffer.array();
        Intrinsics.checkExpressionValueIsNotNull(array, "dw.array()");
        return array;
    }

    @NotNull
    public final ByteBuffer clear() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.clear();
        return this;
    }

    @NotNull
    public final ByteBuffer flip() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.flip();
        return this;
    }

    public final byte get() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.get();
    }

    public final char getChar() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getChar();
    }

    public final double getDouble() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getDouble();
    }

    public final float getFloat() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getFloat();
    }

    public final int getInt() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getInt();
    }

    public final long getLong() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getLong();
    }

    public final short getShort() {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getShort();
    }

    @NotNull
    public final ByteBuffer order(@NotNull ByteOrder order) {
        Intrinsics.checkParameterIsNotNull(order, "order");
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.order(order == ByteOrder.LITTLE_ENDIAN ? java.nio.ByteOrder.LITTLE_ENDIAN : java.nio.ByteOrder.BIG_ENDIAN);
        return this;
    }

    @NotNull
    public final ByteBuffer put(byte b) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.put(b);
        return this;
    }

    @NotNull
    public final ByteBuffer putChar(char c) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putChar(c);
        return this;
    }

    @NotNull
    public final ByteBuffer putDouble(double d) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putDouble(d);
        return this;
    }

    @NotNull
    public final ByteBuffer putFloat(float f) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putFloat(f);
        return this;
    }

    @NotNull
    public final ByteBuffer putInt(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putInt(i);
        return this;
    }

    @NotNull
    public final ByteBuffer putLong(long j) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putLong(j);
        return this;
    }

    @NotNull
    public final ByteBuffer putShort(short s) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putShort(s);
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteBuffer(@NotNull java.nio.ByteBuffer dw) {
        this();
        Intrinsics.checkParameterIsNotNull(dw, "dw");
        this.dw = dw;
    }

    public final byte get(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.get(i);
    }

    public final char getChar(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getChar(i);
    }

    public final double getDouble(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getDouble(i);
    }

    public final float getFloat(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getFloat(i);
    }

    public final int getInt(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getInt(i);
    }

    public final long getLong(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getLong(i);
    }

    public final short getShort(int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        return byteBuffer.getShort(i);
    }

    @NotNull
    public final ByteBuffer put(byte b, int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.put(i, b);
        return this;
    }

    @NotNull
    public final ByteBuffer putChar(char c, int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putChar(i, c);
        return this;
    }

    @NotNull
    public final ByteBuffer putDouble(double d, int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putDouble(i, d);
        return this;
    }

    @NotNull
    public final ByteBuffer putFloat(float f, int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putFloat(i, f);
        return this;
    }

    @NotNull
    public final ByteBuffer putInt(int i, int i2) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putInt(i2, i);
        return this;
    }

    @NotNull
    public final ByteBuffer putLong(long j, int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putLong(i, j);
        return this;
    }

    @NotNull
    public final ByteBuffer putShort(short s, int i) {
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.putShort(i, s);
        return this;
    }

    public final void get(@NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.get(dst, i, i2);
    }

    @NotNull
    public final ByteBuffer put(@NotNull byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.put(src);
        return this;
    }

    @NotNull
    public final ByteBuffer put(@NotNull byte[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        java.nio.ByteBuffer byteBuffer = this.dw;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dw");
        }
        byteBuffer.put(src, i, i2);
        return this;
    }
}
