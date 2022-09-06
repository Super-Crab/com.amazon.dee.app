package kotlinx.io.core;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: OutputLittleEndian.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\u001aR\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e2\u001d\u0010\u000f\u001a\u0019\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\u0002\b\u0012H\u0082\b\u001a\u0012\u0010\u0013\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015\u001a\u0012\u0010\u0016\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0017\u001a&\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a&\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a&\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001c2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a&\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001d2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a&\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001e2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a0\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001f2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a0\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\"2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a0\u0010\u0018\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0019\u001a\u00020%2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0012\u0010(\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\n\u001a\u0012\u0010)\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0014\u001a\u00020*\u001aP\u0010+\u001a\u00020\b\"\b\b\u0000\u0010,*\u00020-*\u00020\u00022\u0006\u0010\u0014\u001a\u0002H,2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u00020\b0/2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u0002H,0/¢\u0006\u0002\b\u0012H\u0002¢\u0006\u0002\u00101\u001a\u0012\u00102\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0014\u001a\u000203\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Â\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"byteOrderDeprecated", "Lkotlinx/io/core/ByteOrder;", "Lkotlinx/io/core/Output;", "byteOrderDeprecated$annotations", "(Lkotlinx/io/core/Output;)V", "getByteOrderDeprecated", "(Lkotlinx/io/core/Output;)Lkotlinx/io/core/ByteOrder;", "writeArrayTemplate", "", "offset", "", "length", "componentSize", "writeFullyBE", "Lkotlin/Function0;", "writeComponent", "Lkotlin/Function2;", "Lkotlinx/io/core/IoBuffer;", "Lkotlin/ExtensionFunctionType;", "writeDoubleLittleEndian", "value", "", "writeFloatLittleEndian", "", "writeFullyLittleEndian", "source", "", "", "", "", "", "Lkotlin/UIntArray;", "writeFullyLittleEndian-5YrZhAk", "(Lkotlinx/io/core/Output;[III)V", "Lkotlin/ULongArray;", "writeFullyLittleEndian-OjWY3pY", "(Lkotlinx/io/core/Output;[JII)V", "Lkotlin/UShortArray;", "writeFullyLittleEndian-EgFVB10", "(Lkotlinx/io/core/Output;[SII)V", "writeIntLittleEndian", "writeLongLittleEndian", "", "writePrimitiveTemplate", ExifInterface.GPS_DIRECTION_TRUE, "", "write", "Lkotlin/Function1;", "reverse", "(Lkotlinx/io/core/Output;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "writeShortLittleEndian", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OutputLittleEndianKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ByteOrder.values().length];

        static {
            $EnumSwitchMapping$0[ByteOrder.LITTLE_ENDIAN.ordinal()] = 1;
        }
    }

    private static /* synthetic */ void byteOrderDeprecated$annotations(Output output) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteOrder getByteOrderDeprecated(@NotNull Output output) {
        return output.getByteOrder();
    }

    private static final void writeArrayTemplate(@NotNull Output output, int i, int i2, int i3, Function0<Unit> function0, Function2<? super IoBuffer, ? super Integer, Unit> function2) {
        if (getByteOrderDeprecated(output) != ByteOrder.LITTLE_ENDIAN) {
            int i4 = i2 + i;
            IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, i3, null);
            while (true) {
                try {
                    int min = Math.min(prepareWriteHead.getWriteRemaining() / i3, i4 - i) + i;
                    int i5 = min - 1;
                    if (i <= i5) {
                        while (true) {
                            function2.mo12248invoke(prepareWriteHead, Integer.valueOf(i));
                            if (i == i5) {
                                break;
                            }
                            i++;
                        }
                    }
                    int i6 = min < i4 ? i3 : 0;
                    if (i6 <= 0) {
                        return;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(output, i6, prepareWriteHead);
                    i = min;
                } finally {
                    InlineMarker.finallyStart(1);
                    UnsafeKt.afterHeadWrite(output, prepareWriteHead);
                    InlineMarker.finallyEnd(1);
                }
            }
        } else {
            function0.mo12560invoke();
        }
    }

    public static final void writeDoubleLittleEndian(@NotNull Output receiver$0, double d) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        writePrimitiveTemplate(receiver$0, Double.valueOf(d), new OutputLittleEndianKt$writeDoubleLittleEndian$1(receiver$0), OutputLittleEndianKt$writeDoubleLittleEndian$2.INSTANCE);
    }

    public static final void writeFloatLittleEndian(@NotNull Output receiver$0, float f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        writePrimitiveTemplate(receiver$0, Float.valueOf(f), new OutputLittleEndianKt$writeFloatLittleEndian$1(receiver$0), OutputLittleEndianKt$writeFloatLittleEndian$2.INSTANCE);
    }

    public static final void writeFullyLittleEndian(@NotNull Output receiver$0, @NotNull short[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (getByteOrderDeprecated(receiver$0) != ByteOrder.LITTLE_ENDIAN) {
            int i3 = i2 + i;
            IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 2, null);
            while (true) {
                try {
                    int min = Math.min(prepareWriteHead.getWriteRemaining() / 2, i3 - i) + i;
                    int i4 = min - 1;
                    if (i <= i4) {
                        while (true) {
                            prepareWriteHead.writeShort(Short.reverseBytes(source[i]));
                            if (i == i4) {
                                break;
                            }
                            i++;
                        }
                    }
                    int i5 = min < i3 ? 2 : 0;
                    if (i5 <= 0) {
                        return;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i5, prepareWriteHead);
                    i = min;
                } finally {
                    UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                }
            }
        } else {
            receiver$0.writeFully(source, i, i2);
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        writeFullyLittleEndian(output, sArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-5YrZhAk  reason: not valid java name */
    public static final void m12352writeFullyLittleEndian5YrZhAk(@NotNull Output receiver$0, @NotNull int[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        writeFullyLittleEndian(receiver$0, source, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-5YrZhAk$default  reason: not valid java name */
    public static /* synthetic */ void m12353writeFullyLittleEndian5YrZhAk$default(Output output, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m10516getSizeimpl(iArr) - i;
        }
        m12352writeFullyLittleEndian5YrZhAk(output, iArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-EgFVB10  reason: not valid java name */
    public static final void m12354writeFullyLittleEndianEgFVB10(@NotNull Output receiver$0, @NotNull short[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        writeFullyLittleEndian(receiver$0, source, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-EgFVB10$default  reason: not valid java name */
    public static /* synthetic */ void m12355writeFullyLittleEndianEgFVB10$default(Output output, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m10682getSizeimpl(sArr) - i;
        }
        m12354writeFullyLittleEndianEgFVB10(output, sArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-OjWY3pY  reason: not valid java name */
    public static final void m12356writeFullyLittleEndianOjWY3pY(@NotNull Output receiver$0, @NotNull long[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        writeFullyLittleEndian(receiver$0, source, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-OjWY3pY$default  reason: not valid java name */
    public static /* synthetic */ void m12357writeFullyLittleEndianOjWY3pY$default(Output output, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m10586getSizeimpl(jArr) - i;
        }
        m12356writeFullyLittleEndianOjWY3pY(output, jArr, i, i2);
    }

    public static final void writeIntLittleEndian(@NotNull Output receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        writePrimitiveTemplate(receiver$0, Integer.valueOf(i), new OutputLittleEndianKt$writeIntLittleEndian$1(receiver$0), OutputLittleEndianKt$writeIntLittleEndian$2.INSTANCE);
    }

    public static final void writeLongLittleEndian(@NotNull Output receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        writePrimitiveTemplate(receiver$0, Long.valueOf(j), new OutputLittleEndianKt$writeLongLittleEndian$1(receiver$0), OutputLittleEndianKt$writeLongLittleEndian$2.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void writePrimitiveTemplate(@NotNull Output output, T t, Function1<? super T, Unit> function1, Function1<? super T, ? extends T> function12) {
        Object obj = t;
        if (WhenMappings.$EnumSwitchMapping$0[output.getByteOrder().ordinal()] != 1) {
            obj = (T) function12.mo12165invoke(t);
        }
        function1.mo12165invoke(obj);
    }

    public static final void writeShortLittleEndian(@NotNull Output receiver$0, short s) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        writePrimitiveTemplate(receiver$0, Short.valueOf(s), new OutputLittleEndianKt$writeShortLittleEndian$1(receiver$0), OutputLittleEndianKt$writeShortLittleEndian$2.INSTANCE);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        writeFullyLittleEndian(output, iArr, i, i2);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        writeFullyLittleEndian(output, jArr, i, i2);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        writeFullyLittleEndian(output, fArr, i, i2);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        writeFullyLittleEndian(output, dArr, i, i2);
    }

    public static final void writeFullyLittleEndian(@NotNull Output receiver$0, @NotNull int[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (getByteOrderDeprecated(receiver$0) != ByteOrder.LITTLE_ENDIAN) {
            int i3 = i2 + i;
            IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 4, null);
            while (true) {
                try {
                    int min = Math.min(prepareWriteHead.getWriteRemaining() / 4, i3 - i) + i;
                    int i4 = min - 1;
                    if (i <= i4) {
                        while (true) {
                            prepareWriteHead.writeInt(Integer.reverseBytes(source[i]));
                            if (i == i4) {
                                break;
                            }
                            i++;
                        }
                    }
                    int i5 = min < i3 ? 4 : 0;
                    if (i5 <= 0) {
                        return;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i5, prepareWriteHead);
                    i = min;
                } finally {
                    UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                }
            }
        } else {
            receiver$0.writeFully(source, i, i2);
        }
    }

    public static final void writeFullyLittleEndian(@NotNull Output receiver$0, @NotNull long[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (getByteOrderDeprecated(receiver$0) != ByteOrder.LITTLE_ENDIAN) {
            int i3 = i2 + i;
            IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 8, null);
            while (true) {
                try {
                    int min = Math.min(prepareWriteHead.getWriteRemaining() / 8, i3 - i) + i;
                    int i4 = min - 1;
                    if (i <= i4) {
                        while (true) {
                            prepareWriteHead.writeLong(Long.reverseBytes(source[i]));
                            if (i == i4) {
                                break;
                            }
                            i++;
                        }
                    }
                    int i5 = min < i3 ? 8 : 0;
                    if (i5 <= 0) {
                        return;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i5, prepareWriteHead);
                    i = min;
                } finally {
                    UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                }
            }
        } else {
            receiver$0.writeFully(source, i, i2);
        }
    }

    public static final void writeFullyLittleEndian(@NotNull Output receiver$0, @NotNull float[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (getByteOrderDeprecated(receiver$0) != ByteOrder.LITTLE_ENDIAN) {
            int i3 = i2 + i;
            IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 4, null);
            while (true) {
                try {
                    int min = Math.min(prepareWriteHead.getWriteRemaining() / 4, i3 - i) + i;
                    int i4 = min - 1;
                    if (i <= i4) {
                        while (true) {
                            prepareWriteHead.writeFloat(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(source[i]))));
                            if (i == i4) {
                                break;
                            }
                            i++;
                        }
                    }
                    int i5 = min < i3 ? 4 : 0;
                    if (i5 <= 0) {
                        return;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i5, prepareWriteHead);
                    i = min;
                } finally {
                    UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                }
            }
        } else {
            receiver$0.writeFully(source, i, i2);
        }
    }

    public static final void writeFullyLittleEndian(@NotNull Output receiver$0, @NotNull double[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (getByteOrderDeprecated(receiver$0) != ByteOrder.LITTLE_ENDIAN) {
            int i3 = i2 + i;
            IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, 8, null);
            while (true) {
                try {
                    int min = Math.min(prepareWriteHead.getWriteRemaining() / 8, i3 - i) + i;
                    int i4 = min - 1;
                    if (i <= i4) {
                        while (true) {
                            prepareWriteHead.writeDouble(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(source[i]))));
                            if (i == i4) {
                                break;
                            }
                            i++;
                        }
                    }
                    int i5 = min < i3 ? 8 : 0;
                    if (i5 <= 0) {
                        return;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(receiver$0, i5, prepareWriteHead);
                    i = min;
                } finally {
                    UnsafeKt.afterHeadWrite(receiver$0, prepareWriteHead);
                }
            }
        } else {
            receiver$0.writeFully(source, i, i2);
        }
    }
}
