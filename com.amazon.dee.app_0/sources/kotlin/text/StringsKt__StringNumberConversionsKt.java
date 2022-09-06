package kotlin.text;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringNumberConversions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    @NotNull
    public static final Void numberFormatError(@NotNull String input) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        throw new NumberFormatException(GeneratedOutlineSupport1.outline73("Invalid number format: '", input, Chars.QUOTE));
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Byte toByteOrNull(@NotNull String toByteOrNull, int i) {
        int intValue;
        Intrinsics.checkParameterIsNotNull(toByteOrNull, "$this$toByteOrNull");
        Integer intOrNull = toIntOrNull(toByteOrNull, i);
        if (intOrNull == null || (intValue = intOrNull.intValue()) < -128 || intValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Integer toIntOrNull(@NotNull String toIntOrNull) {
        Intrinsics.checkParameterIsNotNull(toIntOrNull, "$this$toIntOrNull");
        return toIntOrNull(toIntOrNull, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Long toLongOrNull(@NotNull String toLongOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toLongOrNull, "$this$toLongOrNull");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = toLongOrNull.length();
        if (length == 0) {
            return null;
        }
        boolean z = false;
        char charAt = toLongOrNull.charAt(0);
        long j = C.TIME_UNSET;
        int i2 = 1;
        if (charAt >= '0') {
            i2 = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                j = Long.MIN_VALUE;
                z = true;
            } else if (charAt != '+') {
                return null;
            }
        }
        long j2 = -256204778801521550L;
        long j3 = 0;
        long j4 = -256204778801521550L;
        while (i2 < length) {
            int digitOf = CharsKt__CharJVMKt.digitOf(toLongOrNull.charAt(i2), i);
            if (digitOf < 0) {
                return null;
            }
            if (j3 < j4) {
                if (j4 == j2) {
                    j4 = j / i;
                    if (j3 < j4) {
                    }
                }
                return null;
            }
            long j5 = j3 * i;
            long j6 = digitOf;
            if (j5 < j + j6) {
                return null;
            }
            j3 = j5 - j6;
            i2++;
            j2 = -256204778801521550L;
        }
        return z ? Long.valueOf(j3) : Long.valueOf(-j3);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Short toShortOrNull(@NotNull String toShortOrNull, int i) {
        int intValue;
        Intrinsics.checkParameterIsNotNull(toShortOrNull, "$this$toShortOrNull");
        Integer intOrNull = toIntOrNull(toShortOrNull, i);
        if (intOrNull == null || (intValue = intOrNull.intValue()) < -32768 || intValue > 32767) {
            return null;
        }
        return Short.valueOf((short) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Integer toIntOrNull(@NotNull String toIntOrNull, int i) {
        boolean z;
        int i2;
        Intrinsics.checkParameterIsNotNull(toIntOrNull, "$this$toIntOrNull");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = toIntOrNull.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char charAt = toIntOrNull.charAt(0);
        int i4 = -2147483647;
        int i5 = 1;
        if (charAt >= '0') {
            z = false;
            i5 = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                i4 = Integer.MIN_VALUE;
                z = true;
            } else if (charAt != '+') {
                return null;
            } else {
                z = false;
            }
        }
        int i6 = -59652323;
        while (i5 < length) {
            int digitOf = CharsKt__CharJVMKt.digitOf(toIntOrNull.charAt(i5), i);
            if (digitOf < 0) {
                return null;
            }
            if ((i3 < i6 && (i6 != -59652323 || i3 < (i6 = i4 / i))) || (i2 = i3 * i) < i4 + digitOf) {
                return null;
            }
            i3 = i2 - digitOf;
            i5++;
        }
        return z ? Integer.valueOf(i3) : Integer.valueOf(-i3);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String toByteOrNull) {
        Byte byteOrNull;
        Intrinsics.checkParameterIsNotNull(toByteOrNull, "$this$toByteOrNull");
        byteOrNull = toByteOrNull(toByteOrNull, 10);
        return byteOrNull;
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String toShortOrNull) {
        Short shortOrNull;
        Intrinsics.checkParameterIsNotNull(toShortOrNull, "$this$toShortOrNull");
        shortOrNull = toShortOrNull(toShortOrNull, 10);
        return shortOrNull;
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Long toLongOrNull(@NotNull String toLongOrNull) {
        Long longOrNull;
        Intrinsics.checkParameterIsNotNull(toLongOrNull, "$this$toLongOrNull");
        longOrNull = toLongOrNull(toLongOrNull, 10);
        return longOrNull;
    }
}
