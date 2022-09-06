package kotlinx.io.core;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import java.io.Closeable;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Output.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\bf\u0018\u00002\u00060\u0001j\u0002`\u00022\u00060\u0003j\u0002`\u0004J$\u0010\u0012\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H&J\b\u0010\u0018\u001a\u00020\u0019H&J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020\u0019H&J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\"H&J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020$H&J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'H&J \u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H&J \u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u00020,2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H&J \u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u00020-2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H&J \u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u00020.2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H&J \u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u00020/2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H&J \u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u0002002\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H&J\u0018\u0010%\u001a\u00020\u00192\u0006\u0010(\u001a\u0002012\u0006\u0010+\u001a\u00020\u0016H&J\u0010\u00102\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0016H&J\u0010\u00103\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001cH&J\u0010\u00104\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u000205H&R\"\u0010\u0005\u001a\u00020\u00068&@&X§\u000e¢\u0006\u0012\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000e8&X§\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\b\u001a\u0004\b\u0010\u0010\u0011¨\u00066"}, d2 = {"Lkotlinx/io/core/Output;", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "byteOrder", "Lkotlinx/io/core/ByteOrder;", "byteOrder$annotations", "()V", "getByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "doNotImplementOutputButExtendAbstractOutputInstead", "", "doNotImplementOutputButExtendAbstractOutputInstead$annotations", "getDoNotImplementOutputButExtendAbstractOutputInstead", "()Ljava/lang/Void;", "append", "csq", "", "start", "", "end", "close", "", "fill", JsonReportFormat.COUNT, "", "v", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "writeByte", "writeDouble", "", "writeFloat", "", "writeFully", "bb", "Ljava/nio/ByteBuffer;", "src", "", "offset", "length", "", "", "", "", "", "Lkotlinx/io/core/IoBuffer;", "writeInt", "writeLong", "writeShort", "", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface Output extends Closeable, Appendable {

    /* compiled from: Output.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Deprecated(message = "Write with writeXXXLittleEndian or do X.reverseByteOrder() and then writeXXX instead.")
        public static /* synthetic */ void byteOrder$annotations() {
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Implementing this interface is highly experimental. Extend AbstractOutput instead.")
        public static /* synthetic */ void doNotImplementOutputButExtendAbstractOutputInstead$annotations() {
        }
    }

    @NotNull
    Appendable append(@NotNull char[] cArr, int i, int i2);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void fill(long j, byte b);

    void flush();

    @NotNull
    ByteOrder getByteOrder();

    @NotNull
    /* synthetic */ Void getDoNotImplementOutputButExtendAbstractOutputInstead();

    void setByteOrder(@NotNull ByteOrder byteOrder);

    void writeByte(byte b);

    void writeDouble(double d);

    void writeFloat(float f);

    void writeFully(@NotNull ByteBuffer byteBuffer);

    void writeFully(@NotNull IoBuffer ioBuffer, int i);

    void writeFully(@NotNull byte[] bArr, int i, int i2);

    void writeFully(@NotNull double[] dArr, int i, int i2);

    void writeFully(@NotNull float[] fArr, int i, int i2);

    void writeFully(@NotNull int[] iArr, int i, int i2);

    void writeFully(@NotNull long[] jArr, int i, int i2);

    void writeFully(@NotNull short[] sArr, int i, int i2);

    void writeInt(int i);

    void writeLong(long j);

    void writeShort(short s);
}
