package kotlin.collections;

import java.util.Collection;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: _UCollections.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: classes.dex */
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull Iterable<UByte> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UByte uByte : sum) {
            i = UInt.m10464constructorimpl(UInt.m10464constructorimpl(uByte.m10437unboximpl() & 255) + i);
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Iterable<UInt> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UInt uInt : sum) {
            i = UInt.m10464constructorimpl(uInt.m10507unboximpl() + i);
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull Iterable<ULong> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        long j = 0;
        for (ULong uLong : sum) {
            j = ULong.m10534constructorimpl(uLong.m10577unboximpl() + j);
        }
        return j;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull Iterable<UShort> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UShort uShort : sum) {
            i = UInt.m10464constructorimpl(UInt.m10464constructorimpl(uShort.m10673unboximpl() & UShort.MAX_VALUE) + i);
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull Collection<UByte> toUByteArray) {
        Intrinsics.checkParameterIsNotNull(toUByteArray, "$this$toUByteArray");
        byte[] m10439constructorimpl = UByteArray.m10439constructorimpl(toUByteArray.size());
        int i = 0;
        for (UByte uByte : toUByteArray) {
            UByteArray.m10450setVurrAj0(m10439constructorimpl, i, uByte.m10437unboximpl());
            i++;
        }
        return m10439constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull Collection<UInt> toUIntArray) {
        Intrinsics.checkParameterIsNotNull(toUIntArray, "$this$toUIntArray");
        int[] m10509constructorimpl = UIntArray.m10509constructorimpl(toUIntArray.size());
        int i = 0;
        for (UInt uInt : toUIntArray) {
            UIntArray.m10520setVXSXFK8(m10509constructorimpl, i, uInt.m10507unboximpl());
            i++;
        }
        return m10509constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull Collection<ULong> toULongArray) {
        Intrinsics.checkParameterIsNotNull(toULongArray, "$this$toULongArray");
        long[] m10579constructorimpl = ULongArray.m10579constructorimpl(toULongArray.size());
        int i = 0;
        for (ULong uLong : toULongArray) {
            ULongArray.m10590setk8EXiF4(m10579constructorimpl, i, uLong.m10577unboximpl());
            i++;
        }
        return m10579constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull Collection<UShort> toUShortArray) {
        Intrinsics.checkParameterIsNotNull(toUShortArray, "$this$toUShortArray");
        short[] m10675constructorimpl = UShortArray.m10675constructorimpl(toUShortArray.size());
        int i = 0;
        for (UShort uShort : toUShortArray) {
            UShortArray.m10686set01HTLdE(m10675constructorimpl, i, uShort.m10673unboximpl());
            i++;
        }
        return m10675constructorimpl;
    }
}
