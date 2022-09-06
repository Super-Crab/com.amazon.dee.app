package kotlinx.io.core;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Output.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007\u001a*\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007\u001a\u001c\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00152\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00162\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00172\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00182\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a\u001c\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u001a2\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a\u0012\u0010\u001b\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d\u001a!\u0010\u001e\u001a\u00020\u000b*\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020!0 H\u0086\b\u001a+\u0010\"\u001a\u00020\u000b*\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00072\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00070 H\u0086\b¨\u0006$"}, d2 = {"append", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Lkotlinx/io/core/Output;", "csq", "", "start", "", "end", "", "fill", "", JsonReportFormat.COUNT, "", "v", "", "writeFully", "src", "", "offset", "length", "", "", "", "", "", "Lkotlinx/io/core/IoBuffer;", "writePacket", "packet", "Lkotlinx/io/core/ByteReadPacket;", "writeWhile", "block", "Lkotlin/Function1;", "", "writeWhileSize", "initialSize", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OutputKt {
    @NotNull
    public static final Appendable append(@NotNull Output receiver$0, @NotNull CharSequence csq, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(csq, "csq");
        Appendable append = receiver$0.append(csq, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(csq, start, end)");
        return append;
    }

    @NotNull
    public static /* synthetic */ Appendable append$default(Output output, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return append(output, charSequence, i, i2);
    }

    public static final void fill(@NotNull Output receiver$0, long j, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.fill(j, b);
    }

    public static /* synthetic */ void fill$default(Output output, long j, byte b, int i, Object obj) {
        if ((i & 2) != 0) {
            b = 0;
        }
        fill(output, j, b);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull byte[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i, i2);
    }

    public static /* synthetic */ void writeFully$default(Output output, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        writeFully(output, bArr, i, i2);
    }

    public static final void writePacket(@NotNull Output receiver$0, @NotNull ByteReadPacket packet) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(packet, "packet");
        if (receiver$0 instanceof BytePacketBuilderBase) {
            ((BytePacketBuilderBase) receiver$0).writePacket(packet);
            return;
        }
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(packet, 1);
        if (prepareReadFirstHead == null) {
            return;
        }
        do {
            try {
                writeFully$default(receiver$0, prepareReadFirstHead, 0, 2, null);
                try {
                    prepareReadFirstHead = UnsafeKt.prepareReadNextHead(packet, prepareReadFirstHead);
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        UnsafeKt.completeReadHead(packet, prepareReadFirstHead);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } while (prepareReadFirstHead != null);
    }

    public static final void writeWhile(@NotNull Output receiver$0, @NotNull Function1<? super IoBuffer, Boolean> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 1, null);
        while (block.mo12165invoke(prepareWriteHead).booleanValue()) {
            try {
                prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 1, prepareWriteHead);
            } finally {
                InlineMarker.finallyStart(1);
                UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public static final void writeWhileSize(@NotNull Output receiver$0, int i, @NotNull Function1<? super IoBuffer, Integer> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i, null);
        while (true) {
            try {
                int intValue = block.mo12165invoke(prepareWriteHead).intValue();
                if (intValue <= 0) {
                    return;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, intValue, prepareWriteHead);
            } finally {
                InlineMarker.finallyStart(1);
                UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public static /* synthetic */ void writeWhileSize$default(Output receiver$0, int i, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i, null);
        while (true) {
            try {
                int intValue = ((Number) block.mo12165invoke(prepareWriteHead)).intValue();
                if (intValue <= 0) {
                    return;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, intValue, prepareWriteHead);
            } finally {
                InlineMarker.finallyStart(1);
                UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    @NotNull
    public static final Appendable append(@NotNull Output receiver$0, @NotNull char[] csq, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(csq, "csq");
        return receiver$0.append(csq, i, i2);
    }

    @NotNull
    public static /* synthetic */ Appendable append$default(Output output, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return append(output, cArr, i, i2);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull short[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i, i2);
    }

    public static /* synthetic */ void writeFully$default(Output output, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        writeFully(output, sArr, i, i2);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull int[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i, i2);
    }

    public static /* synthetic */ void writeFully$default(Output output, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        writeFully(output, iArr, i, i2);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull long[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i, i2);
    }

    public static /* synthetic */ void writeFully$default(Output output, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        writeFully(output, jArr, i, i2);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull float[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i, i2);
    }

    public static /* synthetic */ void writeFully$default(Output output, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        writeFully(output, fArr, i, i2);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull double[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i, i2);
    }

    public static /* synthetic */ void writeFully$default(Output output, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        writeFully(output, dArr, i, i2);
    }

    public static final void writeFully(@NotNull Output receiver$0, @NotNull IoBuffer src, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src, i);
    }

    public static /* synthetic */ void writeFully$default(Output output, IoBuffer ioBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ioBuffer.getReadRemaining();
        }
        writeFully(output, ioBuffer, i);
    }
}
