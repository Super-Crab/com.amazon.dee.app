package kotlinx.io.core;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import java.io.Closeable;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Input.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0002\bf\u0018\u00002\u00060\u0001j\u0002`\u0002J\b\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H&J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH&J\u001a\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001aH&J \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020&2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020'2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001aH&J\b\u0010(\u001a\u00020)H&J\b\u0010*\u001a\u00020+H&J\b\u0010,\u001a\u00020-H&J\u001a\u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001aH&J \u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020&2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J \u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020'2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&J\u001a\u0010.\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u001aH&J\b\u0010/\u001a\u00020\u001aH&J\b\u00100\u001a\u00020\u0017H&J\b\u00101\u001a\u000202H&J\b\u00103\u001a\u00020\u001aH&R\"\u0010\u0003\u001a\u00020\u00048&@&X§\u000e¢\u0006\u0012\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u00064"}, d2 = {"Lkotlinx/io/core/Input;", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "byteOrder", "Lkotlinx/io/core/ByteOrder;", "byteOrder$annotations", "()V", "getByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "doNotImplementInputButExtendAbstractInputInstead", "", "doNotImplementInputButExtendAbstractInputInstead$annotations", "getDoNotImplementInputButExtendAbstractInputInstead", "()Ljava/lang/Void;", "endOfInput", "", "getEndOfInput", "()Z", "close", "", "discard", "", JsonReportFormat.COUNT, "peekTo", "", "buffer", "Lkotlinx/io/core/IoBuffer;", "readAvailable", "dst", "Ljava/nio/ByteBuffer;", "length", "", "offset", "", "", "", "", "", "readByte", "", "readDouble", "", "readFloat", "", "readFully", "readInt", "readLong", "readShort", "", "tryPeek", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface Input extends Closeable {

    /* compiled from: Input.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Deprecated(message = "Use readXXXLittleEndian or readXXX then X.reverseByteOrder() instead.")
        public static /* synthetic */ void byteOrder$annotations() {
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Implementing this interface is highly experimental. Extend AbstractInput instead.")
        public static /* synthetic */ void doNotImplementInputButExtendAbstractInputInstead$annotations() {
        }

        public static /* synthetic */ int readAvailable$default(Input input, ByteBuffer byteBuffer, int i, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    i = byteBuffer.remaining();
                }
                return input.readAvailable(byteBuffer, i);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readAvailable");
        }

        public static /* synthetic */ void readFully$default(Input input, ByteBuffer byteBuffer, int i, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    i = byteBuffer.remaining();
                }
                input.readFully(byteBuffer, i);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readFully");
        }

        public static /* synthetic */ void readFully$default(Input input, IoBuffer ioBuffer, int i, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    i = ioBuffer.getWriteRemaining();
                }
                input.readFully(ioBuffer, i);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readFully");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long discard(long j);

    @NotNull
    ByteOrder getByteOrder();

    @NotNull
    /* synthetic */ Void getDoNotImplementInputButExtendAbstractInputInstead();

    boolean getEndOfInput();

    int peekTo(@NotNull IoBuffer ioBuffer);

    int readAvailable(@NotNull ByteBuffer byteBuffer, int i);

    int readAvailable(@NotNull IoBuffer ioBuffer, int i);

    int readAvailable(@NotNull byte[] bArr, int i, int i2);

    int readAvailable(@NotNull double[] dArr, int i, int i2);

    int readAvailable(@NotNull float[] fArr, int i, int i2);

    int readAvailable(@NotNull int[] iArr, int i, int i2);

    int readAvailable(@NotNull long[] jArr, int i, int i2);

    int readAvailable(@NotNull short[] sArr, int i, int i2);

    byte readByte();

    double readDouble();

    float readFloat();

    void readFully(@NotNull ByteBuffer byteBuffer, int i);

    void readFully(@NotNull IoBuffer ioBuffer, int i);

    void readFully(@NotNull byte[] bArr, int i, int i2);

    void readFully(@NotNull double[] dArr, int i, int i2);

    void readFully(@NotNull float[] fArr, int i, int i2);

    void readFully(@NotNull int[] iArr, int i, int i2);

    void readFully(@NotNull long[] jArr, int i, int i2);

    void readFully(@NotNull short[] sArr, int i, int i2);

    int readInt();

    long readLong();

    short readShort();

    void setByteOrder(@NotNull ByteOrder byteOrder);

    int tryPeek();
}
