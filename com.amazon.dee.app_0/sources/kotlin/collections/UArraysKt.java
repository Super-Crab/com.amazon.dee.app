package kotlin.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
/* compiled from: UArraysKt.kt */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "Provided for binary compatibility")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u001e\u0010&\u001a\u00020'*\u00020\u00052\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u001e\u0010&\u001a\u00020+*\u00020\t2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u001e\u0010&\u001a\u00020.*\u00020\f2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u001e\u0010&\u001a\u000201*\u00020\u000f2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020'05*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020+05*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020.05*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020105*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, d2 = {"Lkotlin/collections/UArraysKt;", "", "()V", "contentEquals", "", "Lkotlin/UByteArray;", "other", "contentEquals-kdPth3s", "([B[B)Z", "Lkotlin/UIntArray;", "contentEquals-ctEhBpI", "([I[I)Z", "Lkotlin/ULongArray;", "contentEquals-us8wMrg", "([J[J)Z", "Lkotlin/UShortArray;", "contentEquals-mazbYpA", "([S[S)Z", "contentHashCode", "", "contentHashCode-GBYM_sE", "([B)I", "contentHashCode--ajY-9A", "([I)I", "contentHashCode-QwZRm1k", "([J)I", "contentHashCode-rL5Bavg", "([S)I", "contentToString", "", "contentToString-GBYM_sE", "([B)Ljava/lang/String;", "contentToString--ajY-9A", "([I)Ljava/lang/String;", "contentToString-QwZRm1k", "([J)Ljava/lang/String;", "contentToString-rL5Bavg", "([S)Ljava/lang/String;", "random", "Lkotlin/UByte;", "Lkotlin/random/Random;", "random-oSF2wD8", "([BLkotlin/random/Random;)B", "Lkotlin/UInt;", "random-2D5oskM", "([ILkotlin/random/Random;)I", "Lkotlin/ULong;", "random-JzugnMA", "([JLkotlin/random/Random;)J", "Lkotlin/UShort;", "random-s5X_as8", "([SLkotlin/random/Random;)S", "toTypedArray", "", "toTypedArray-GBYM_sE", "([B)[Lkotlin/UByte;", "toTypedArray--ajY-9A", "([I)[Lkotlin/UInt;", "toTypedArray-QwZRm1k", "([J)[Lkotlin/ULong;", "toTypedArray-rL5Bavg", "([S)[Lkotlin/UShort;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UArraysKt {
    public static final UArraysKt INSTANCE = new UArraysKt();

    private UArraysKt() {
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-ctEhBpI  reason: not valid java name */
    public static final boolean m10747contentEqualsctEhBpI(@NotNull int[] contentEquals, @NotNull int[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-kdPth3s  reason: not valid java name */
    public static final boolean m10748contentEqualskdPth3s(@NotNull byte[] contentEquals, @NotNull byte[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-mazbYpA  reason: not valid java name */
    public static final boolean m10749contentEqualsmazbYpA(@NotNull short[] contentEquals, @NotNull short[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-us8wMrg  reason: not valid java name */
    public static final boolean m10750contentEqualsus8wMrg(@NotNull long[] contentEquals, @NotNull long[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode--ajY-9A  reason: not valid java name */
    public static final int m10751contentHashCodeajY9A(@NotNull int[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-GBYM_sE  reason: not valid java name */
    public static final int m10752contentHashCodeGBYM_sE(@NotNull byte[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-QwZRm1k  reason: not valid java name */
    public static final int m10753contentHashCodeQwZRm1k(@NotNull long[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-rL5Bavg  reason: not valid java name */
    public static final int m10754contentHashCoderL5Bavg(@NotNull short[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString--ajY-9A  reason: not valid java name */
    public static final String m10755contentToStringajY9A(@NotNull int[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(contentToString, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-GBYM_sE  reason: not valid java name */
    public static final String m10756contentToStringGBYM_sE(@NotNull byte[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(contentToString, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-QwZRm1k  reason: not valid java name */
    public static final String m10757contentToStringQwZRm1k(@NotNull long[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(contentToString, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-rL5Bavg  reason: not valid java name */
    public static final String m10758contentToStringrL5Bavg(@NotNull short[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(contentToString, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-2D5oskM  reason: not valid java name */
    public static final int m10759random2D5oskM(@NotNull int[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (!UIntArray.m10518isEmptyimpl(random)) {
            return UIntArray.m10515getimpl(random, random2.nextInt(UIntArray.m10516getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-JzugnMA  reason: not valid java name */
    public static final long m10760randomJzugnMA(@NotNull long[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (!ULongArray.m10588isEmptyimpl(random)) {
            return ULongArray.m10585getimpl(random, random2.nextInt(ULongArray.m10586getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-oSF2wD8  reason: not valid java name */
    public static final byte m10761randomoSF2wD8(@NotNull byte[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (!UByteArray.m10448isEmptyimpl(random)) {
            return UByteArray.m10445getimpl(random, random2.nextInt(UByteArray.m10446getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-s5X_as8  reason: not valid java name */
    public static final short m10762randoms5X_as8(@NotNull short[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (!UShortArray.m10684isEmptyimpl(random)) {
            return UShortArray.m10681getimpl(random, random2.nextInt(UShortArray.m10682getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray--ajY-9A  reason: not valid java name */
    public static final UInt[] m10763toTypedArrayajY9A(@NotNull int[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m10516getSizeimpl = UIntArray.m10516getSizeimpl(toTypedArray);
        UInt[] uIntArr = new UInt[m10516getSizeimpl];
        for (int i = 0; i < m10516getSizeimpl; i++) {
            uIntArr[i] = UInt.m10458boximpl(UIntArray.m10515getimpl(toTypedArray, i));
        }
        return uIntArr;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-GBYM_sE  reason: not valid java name */
    public static final UByte[] m10764toTypedArrayGBYM_sE(@NotNull byte[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m10446getSizeimpl = UByteArray.m10446getSizeimpl(toTypedArray);
        UByte[] uByteArr = new UByte[m10446getSizeimpl];
        for (int i = 0; i < m10446getSizeimpl; i++) {
            uByteArr[i] = UByte.m10390boximpl(UByteArray.m10445getimpl(toTypedArray, i));
        }
        return uByteArr;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-QwZRm1k  reason: not valid java name */
    public static final ULong[] m10765toTypedArrayQwZRm1k(@NotNull long[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m10586getSizeimpl = ULongArray.m10586getSizeimpl(toTypedArray);
        ULong[] uLongArr = new ULong[m10586getSizeimpl];
        for (int i = 0; i < m10586getSizeimpl; i++) {
            uLongArr[i] = ULong.m10528boximpl(ULongArray.m10585getimpl(toTypedArray, i));
        }
        return uLongArr;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-rL5Bavg  reason: not valid java name */
    public static final UShort[] m10766toTypedArrayrL5Bavg(@NotNull short[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m10682getSizeimpl = UShortArray.m10682getSizeimpl(toTypedArray);
        UShort[] uShortArr = new UShort[m10682getSizeimpl];
        for (int i = 0; i < m10682getSizeimpl; i++) {
            uShortArr[i] = UShort.m10626boximpl(UShortArray.m10681getimpl(toTypedArray, i));
        }
        return uShortArr;
    }
}
