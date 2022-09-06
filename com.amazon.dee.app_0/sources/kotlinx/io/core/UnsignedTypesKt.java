package kotlinx.io.core;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UnsignedTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\n2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00102\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u0015\u0010\u0016\u001a\u00020\u0017*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a\u0015\u0010\u001c\u001a\u00020\u001d*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a3\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a3\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\n2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a3\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a3\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u001f\u0010*\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a\u001f\u0010.\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u0017H\u0087\bø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u001f\u00101\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u001aH\u0087\bø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u001f\u00104\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u001dH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, d2 = {"readFully", "", "Lkotlinx/io/core/Input;", "dst", "Lkotlin/UByteArray;", "offset", "", "length", "readFully-Yjx-SNk", "(Lkotlinx/io/core/Input;[BII)V", "Lkotlin/UIntArray;", "readFully-wOR3gME", "(Lkotlinx/io/core/Input;[III)V", "Lkotlin/ULongArray;", "readFully-Hn3XiNM", "(Lkotlinx/io/core/Input;[JII)V", "Lkotlin/UShortArray;", "readFully-_44sl1A", "(Lkotlinx/io/core/Input;[SII)V", "readUByte", "Lkotlin/UByte;", "(Lkotlinx/io/core/Input;)B", "readUInt", "Lkotlin/UInt;", "(Lkotlinx/io/core/Input;)I", "readULong", "Lkotlin/ULong;", "(Lkotlinx/io/core/Input;)J", "readUShort", "Lkotlin/UShort;", "(Lkotlinx/io/core/Input;)S", "writeFully", "Lkotlinx/io/core/Output;", "array", "writeFully-HSHaXEY", "(Lkotlinx/io/core/Output;[BII)V", "writeFully-5YrZhAk", "(Lkotlinx/io/core/Output;[III)V", "writeFully-OjWY3pY", "(Lkotlinx/io/core/Output;[JII)V", "writeFully-EgFVB10", "(Lkotlinx/io/core/Output;[SII)V", "writeUByte", "v", "writeUByte-DNbBI8I", "(Lkotlinx/io/core/Output;B)V", "writeUInt", "writeUInt-MK4Q_rY", "(Lkotlinx/io/core/Output;I)V", "writeULong", "writeULong-lectD24", "(Lkotlinx/io/core/Output;J)V", "writeUShort", "writeUShort-ONiEYYE", "(Lkotlinx/io/core/Output;S)V", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UnsignedTypesKt {
    @ExperimentalUnsignedTypes
    /* renamed from: readFully-Hn3XiNM  reason: not valid java name */
    public static final void m12358readFullyHn3XiNM(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-Hn3XiNM$default  reason: not valid java name */
    public static /* synthetic */ void m12359readFullyHn3XiNM$default(Input receiver$0, long[] dst, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m10586getSizeimpl(dst) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-Yjx-SNk  reason: not valid java name */
    public static final void m12360readFullyYjxSNk(@NotNull Input receiver$0, @NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-Yjx-SNk$default  reason: not valid java name */
    public static /* synthetic */ void m12361readFullyYjxSNk$default(Input receiver$0, byte[] dst, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m10446getSizeimpl(dst) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-_44sl1A  reason: not valid java name */
    public static final void m12362readFully_44sl1A(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-_44sl1A$default  reason: not valid java name */
    public static /* synthetic */ void m12363readFully_44sl1A$default(Input receiver$0, short[] dst, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m10682getSizeimpl(dst) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-wOR3gME  reason: not valid java name */
    public static final void m12364readFullywOR3gME(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: readFully-wOR3gME$default  reason: not valid java name */
    public static /* synthetic */ void m12365readFullywOR3gME$default(Input receiver$0, int[] dst, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m10516getSizeimpl(dst) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    @ExperimentalUnsignedTypes
    public static final byte readUByte(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return UByte.m10396constructorimpl(receiver$0.readByte());
    }

    @ExperimentalUnsignedTypes
    public static final int readUInt(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return UInt.m10464constructorimpl(receiver$0.readInt());
    }

    @ExperimentalUnsignedTypes
    public static final long readULong(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return ULong.m10534constructorimpl(receiver$0.readLong());
    }

    @ExperimentalUnsignedTypes
    public static final short readUShort(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return UShort.m10632constructorimpl(receiver$0.readShort());
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-5YrZhAk  reason: not valid java name */
    public static final void m12366writeFully5YrZhAk(@NotNull Output receiver$0, @NotNull int[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-5YrZhAk$default  reason: not valid java name */
    public static /* synthetic */ void m12367writeFully5YrZhAk$default(Output receiver$0, int[] array, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m10516getSizeimpl(array) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-EgFVB10  reason: not valid java name */
    public static final void m12368writeFullyEgFVB10(@NotNull Output receiver$0, @NotNull short[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-EgFVB10$default  reason: not valid java name */
    public static /* synthetic */ void m12369writeFullyEgFVB10$default(Output receiver$0, short[] array, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m10682getSizeimpl(array) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-HSHaXEY  reason: not valid java name */
    public static final void m12370writeFullyHSHaXEY(@NotNull Output receiver$0, @NotNull byte[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-HSHaXEY$default  reason: not valid java name */
    public static /* synthetic */ void m12371writeFullyHSHaXEY$default(Output receiver$0, byte[] array, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m10446getSizeimpl(array) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-OjWY3pY  reason: not valid java name */
    public static final void m12372writeFullyOjWY3pY(@NotNull Output receiver$0, @NotNull long[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeFully-OjWY3pY$default  reason: not valid java name */
    public static /* synthetic */ void m12373writeFullyOjWY3pY$default(Output receiver$0, long[] array, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m10586getSizeimpl(array) - i;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(array, "array");
        receiver$0.writeFully(array, i, i2);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeUByte-DNbBI8I  reason: not valid java name */
    public static final void m12374writeUByteDNbBI8I(@NotNull Output receiver$0, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.writeByte(b);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeUInt-MK4Q_rY  reason: not valid java name */
    public static final void m12375writeUIntMK4Q_rY(@NotNull Output receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.writeInt(i);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeULong-lectD24  reason: not valid java name */
    public static final void m12376writeULonglectD24(@NotNull Output receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.writeLong(j);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: writeUShort-ONiEYYE  reason: not valid java name */
    public static final void m12377writeUShortONiEYYE(@NotNull Output receiver$0, short s) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.writeShort(s);
    }
}
