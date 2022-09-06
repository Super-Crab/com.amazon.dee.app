package kotlinx.io.core;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: InputLittleEndian.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\r2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u000e2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u000f2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a0\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00112\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a0\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00142\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a0\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00172\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\n\u0010\u001a\u001a\u00020\u001b*\u00020\u0002\u001a\n\u0010\u001c\u001a\u00020\u001d*\u00020\u0002\u001a&\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\r2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\u000e2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\u000f2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a&\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b\u001a0\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00112\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a0\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00142\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a0\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00172\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a\n\u0010&\u001a\u00020\b*\u00020\u0002\u001a\n\u0010'\u001a\u00020(*\u00020\u0002\u001aC\u0010)\u001a\u0002H*\"\b\b\u0000\u0010**\u00020+*\u00020\u00022\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H*0-2\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H*0/¢\u0006\u0002\b0H\u0082\b¢\u0006\u0002\u00101\u001a\n\u00102\u001a\u000203*\u00020\u0002\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Â\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"byteOrderDeprecated", "Lkotlinx/io/core/ByteOrder;", "Lkotlinx/io/core/Input;", "byteOrderDeprecated$annotations", "(Lkotlinx/io/core/Input;)V", "getByteOrderDeprecated", "(Lkotlinx/io/core/Input;)Lkotlinx/io/core/ByteOrder;", "readAvailableLittleEndian", "", "dst", "", "offset", "length", "", "", "", "", "Lkotlin/UIntArray;", "readAvailableLittleEndian-wOR3gME", "(Lkotlinx/io/core/Input;[III)I", "Lkotlin/ULongArray;", "readAvailableLittleEndian-Hn3XiNM", "(Lkotlinx/io/core/Input;[JII)I", "Lkotlin/UShortArray;", "readAvailableLittleEndian-_44sl1A", "(Lkotlinx/io/core/Input;[SII)I", "readDoubleLittleEndian", "", "readFloatLittleEndian", "", "readFullyLittleEndian", "", "readFullyLittleEndian-wOR3gME", "(Lkotlinx/io/core/Input;[III)V", "readFullyLittleEndian-Hn3XiNM", "(Lkotlinx/io/core/Input;[JII)V", "readFullyLittleEndian-_44sl1A", "(Lkotlinx/io/core/Input;[SII)V", "readIntLittleEndian", "readLongLittleEndian", "", "readPrimitiveTemplate", ExifInterface.GPS_DIRECTION_TRUE, "", "read", "Lkotlin/Function0;", "reverse", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/io/core/Input;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "readShortLittleEndian", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class InputLittleEndianKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ByteOrder.values().length];

        static {
            $EnumSwitchMapping$0[ByteOrder.LITTLE_ENDIAN.ordinal()] = 1;
        }
    }

    private static /* synthetic */ void byteOrderDeprecated$annotations(Input input) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteOrder getByteOrderDeprecated(@NotNull Input input) {
        return input.getByteOrder();
    }

    public static final int readAvailableLittleEndian(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAvailable = receiver$0.readAvailable(dst, i, i2);
        if (readAvailable > 0 && receiver$0.getByteOrder() != ByteOrder.LITTLE_ENDIAN && i <= (i + readAvailable) - 1) {
            while (true) {
                dst[i] = Short.reverseBytes(dst[i]);
                if (i == i3) {
                    break;
                }
                i++;
            }
        }
        return readAvailable;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailableLittleEndian(input, sArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-Hn3XiNM  reason: not valid java name */
    public static final int m12339readAvailableLittleEndianHn3XiNM(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return readAvailableLittleEndian(receiver$0, dst, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-Hn3XiNM$default  reason: not valid java name */
    public static /* synthetic */ int m12340readAvailableLittleEndianHn3XiNM$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m10586getSizeimpl(jArr) - i;
        }
        return m12339readAvailableLittleEndianHn3XiNM(input, jArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-_44sl1A  reason: not valid java name */
    public static final int m12341readAvailableLittleEndian_44sl1A(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return readAvailableLittleEndian(receiver$0, dst, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-_44sl1A$default  reason: not valid java name */
    public static /* synthetic */ int m12342readAvailableLittleEndian_44sl1A$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m10682getSizeimpl(sArr) - i;
        }
        return m12341readAvailableLittleEndian_44sl1A(input, sArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-wOR3gME  reason: not valid java name */
    public static final int m12343readAvailableLittleEndianwOR3gME(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return readAvailableLittleEndian(receiver$0, dst, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-wOR3gME$default  reason: not valid java name */
    public static /* synthetic */ int m12344readAvailableLittleEndianwOR3gME$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m10516getSizeimpl(iArr) - i;
        }
        return m12343readAvailableLittleEndianwOR3gME(input, iArr, i, i2);
    }

    public static final double readDoubleLittleEndian(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (WhenMappings.$EnumSwitchMapping$0[getByteOrderDeprecated(receiver$0).ordinal()] != 1) {
            return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(receiver$0.readDouble())));
        }
        return receiver$0.readDouble();
    }

    public static final float readFloatLittleEndian(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (WhenMappings.$EnumSwitchMapping$0[getByteOrderDeprecated(receiver$0).ordinal()] != 1) {
            return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(receiver$0.readFloat())));
        }
        return receiver$0.readFloat();
    }

    public static final void readFullyLittleEndian(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
        if (receiver$0.getByteOrder() == ByteOrder.LITTLE_ENDIAN || i > (i2 + i) - 1) {
            return;
        }
        while (true) {
            dst[i] = Short.reverseBytes(dst[i]);
            if (i == i3) {
                return;
            }
            i++;
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFullyLittleEndian(input, sArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-Hn3XiNM  reason: not valid java name */
    public static final void m12345readFullyLittleEndianHn3XiNM(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        readFullyLittleEndian(receiver$0, dst, i, i2);
    }

    /* renamed from: readFullyLittleEndian-Hn3XiNM$default  reason: not valid java name */
    public static /* synthetic */ void m12346readFullyLittleEndianHn3XiNM$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m10586getSizeimpl(jArr) - i;
        }
        m12345readFullyLittleEndianHn3XiNM(input, jArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-_44sl1A  reason: not valid java name */
    public static final void m12347readFullyLittleEndian_44sl1A(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        readFullyLittleEndian(receiver$0, dst, i, i2);
    }

    /* renamed from: readFullyLittleEndian-_44sl1A$default  reason: not valid java name */
    public static /* synthetic */ void m12348readFullyLittleEndian_44sl1A$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m10682getSizeimpl(sArr) - i;
        }
        m12347readFullyLittleEndian_44sl1A(input, sArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-wOR3gME  reason: not valid java name */
    public static final void m12349readFullyLittleEndianwOR3gME(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        readFullyLittleEndian(receiver$0, dst, i, i2);
    }

    /* renamed from: readFullyLittleEndian-wOR3gME$default  reason: not valid java name */
    public static /* synthetic */ void m12350readFullyLittleEndianwOR3gME$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m10516getSizeimpl(iArr) - i;
        }
        m12349readFullyLittleEndianwOR3gME(input, iArr, i, i2);
    }

    public static final int readIntLittleEndian(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (WhenMappings.$EnumSwitchMapping$0[getByteOrderDeprecated(receiver$0).ordinal()] != 1) {
            return Integer.reverseBytes(receiver$0.readInt());
        }
        return receiver$0.readInt();
    }

    public static final long readLongLittleEndian(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (WhenMappings.$EnumSwitchMapping$0[getByteOrderDeprecated(receiver$0).ordinal()] != 1) {
            return Long.reverseBytes(receiver$0.readLong());
        }
        return receiver$0.readLong();
    }

    private static final <T> T readPrimitiveTemplate(@NotNull Input input, Function0<? extends T> function0, Function1<? super T, ? extends T> function1) {
        if (WhenMappings.$EnumSwitchMapping$0[getByteOrderDeprecated(input).ordinal()] != 1) {
            return function1.mo12165invoke((T) function0.mo12560invoke());
        }
        return function0.mo12560invoke();
    }

    public static final short readShortLittleEndian(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (WhenMappings.$EnumSwitchMapping$0[getByteOrderDeprecated(receiver$0).ordinal()] != 1) {
            return Short.reverseBytes(receiver$0.readShort());
        }
        return receiver$0.readShort();
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailableLittleEndian(input, iArr, i, i2);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFullyLittleEndian(input, iArr, i, i2);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailableLittleEndian(input, jArr, i, i2);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFullyLittleEndian(input, jArr, i, i2);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailableLittleEndian(input, fArr, i, i2);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFullyLittleEndian(input, fArr, i, i2);
    }

    public static final int readAvailableLittleEndian(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAvailable = receiver$0.readAvailable(dst, i, i2);
        if (readAvailable > 0 && receiver$0.getByteOrder() != ByteOrder.LITTLE_ENDIAN && i <= (i + readAvailable) - 1) {
            while (true) {
                dst[i] = Integer.reverseBytes(dst[i]);
                if (i == i3) {
                    break;
                }
                i++;
            }
        }
        return readAvailable;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailableLittleEndian(input, dArr, i, i2);
    }

    public static final void readFullyLittleEndian(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
        if (receiver$0.getByteOrder() == ByteOrder.LITTLE_ENDIAN || i > (i2 + i) - 1) {
            return;
        }
        while (true) {
            dst[i] = Integer.reverseBytes(dst[i]);
            if (i == i3) {
                return;
            }
            i++;
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFullyLittleEndian(input, dArr, i, i2);
    }

    public static final int readAvailableLittleEndian(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAvailable = receiver$0.readAvailable(dst, i, i2);
        if (readAvailable > 0 && receiver$0.getByteOrder() != ByteOrder.LITTLE_ENDIAN && i <= (i + readAvailable) - 1) {
            while (true) {
                dst[i] = Long.reverseBytes(dst[i]);
                if (i == i3) {
                    break;
                }
                i++;
            }
        }
        return readAvailable;
    }

    public static final void readFullyLittleEndian(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
        if (receiver$0.getByteOrder() == ByteOrder.LITTLE_ENDIAN || i > (i2 + i) - 1) {
            return;
        }
        while (true) {
            dst[i] = Long.reverseBytes(dst[i]);
            if (i == i3) {
                return;
            }
            i++;
        }
    }

    public static final int readAvailableLittleEndian(@NotNull Input receiver$0, @NotNull float[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAvailable = receiver$0.readAvailable(dst, i, i2);
        if (readAvailable > 0 && receiver$0.getByteOrder() != ByteOrder.LITTLE_ENDIAN && i <= (i + readAvailable) - 1) {
            while (true) {
                dst[i] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(dst[i])));
                if (i == i3) {
                    break;
                }
                i++;
            }
        }
        return readAvailable;
    }

    public static final void readFullyLittleEndian(@NotNull Input receiver$0, @NotNull float[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
        if (receiver$0.getByteOrder() == ByteOrder.LITTLE_ENDIAN || i > (i2 + i) - 1) {
            return;
        }
        while (true) {
            dst[i] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(dst[i])));
            if (i == i3) {
                return;
            }
            i++;
        }
    }

    public static final int readAvailableLittleEndian(@NotNull Input receiver$0, @NotNull double[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAvailable = receiver$0.readAvailable(dst, i, i2);
        if (readAvailable > 0 && receiver$0.getByteOrder() != ByteOrder.LITTLE_ENDIAN && i <= (i + readAvailable) - 1) {
            while (true) {
                dst[i] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dst[i])));
                if (i == i3) {
                    break;
                }
                i++;
            }
        }
        return readAvailable;
    }

    public static final void readFullyLittleEndian(@NotNull Input receiver$0, @NotNull double[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
        if (receiver$0.getByteOrder() == ByteOrder.LITTLE_ENDIAN || i > (i2 + i) - 1) {
            return;
        }
        while (true) {
            dst[i] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dst[i])));
            if (i == i3) {
                return;
            }
            i++;
        }
    }
}
