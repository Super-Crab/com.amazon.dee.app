package kotlin.text;

import androidx.exifinterface.media.ExifInterface;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringNumberConversionsJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0015*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u0017*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0019\u001a\r\u0010\u001a\u001a\u00020\u001b*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001d\u001a\r\u0010\u001e\u001a\u00020\u0010*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001e\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u001f\u001a\u00020 *\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010!\u001a\u00020\"*\u00020\u0003H\u0087\b\u001a\u0015\u0010!\u001a\u00020\"*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020 2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\"2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b¨\u0006$"}, d2 = {"screenFloatValue", ExifInterface.GPS_DIRECTION_TRUE, "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String str, Function1<? super String, ? extends T> function1) {
        try {
            if (!ScreenFloatValueRegEx.value.matches(str)) {
                return null;
            }
            return function1.mo12165invoke(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull String str) {
        return new BigDecimal(str);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String toBigDecimalOrNull) {
        Intrinsics.checkParameterIsNotNull(toBigDecimalOrNull, "$this$toBigDecimalOrNull");
        try {
            if (!ScreenFloatValueRegEx.value.matches(toBigDecimalOrNull)) {
                return null;
            }
            return new BigDecimal(toBigDecimalOrNull);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(@NotNull String str) {
        return new BigInteger(str);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String toBigIntegerOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toBigIntegerOrNull, "$this$toBigIntegerOrNull");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = toBigIntegerOrNull.length();
        if (length != 0) {
            int i2 = 0;
            if (length != 1) {
                if (toBigIntegerOrNull.charAt(0) == '-') {
                    i2 = 1;
                }
                while (i2 < length) {
                    if (CharsKt__CharJVMKt.digitOf(toBigIntegerOrNull.charAt(i2), i) < 0) {
                        return null;
                    }
                    i2++;
                }
            } else if (CharsKt__CharJVMKt.digitOf(toBigIntegerOrNull.charAt(0), i) < 0) {
                return null;
            }
            return new BigInteger(toBigIntegerOrNull, CharsKt__CharJVMKt.checkRadix(i));
        }
        return null;
    }

    @InlineOnly
    private static final boolean toBoolean(@NotNull String str) {
        return Boolean.parseBoolean(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte toByte(@NotNull String str, int i) {
        return Byte.parseByte(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @InlineOnly
    private static final double toDouble(@NotNull String str) {
        return Double.parseDouble(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Double toDoubleOrNull(@NotNull String toDoubleOrNull) {
        Intrinsics.checkParameterIsNotNull(toDoubleOrNull, "$this$toDoubleOrNull");
        try {
            if (!ScreenFloatValueRegEx.value.matches(toDoubleOrNull)) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(toDoubleOrNull));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @InlineOnly
    private static final float toFloat(@NotNull String str) {
        return Float.parseFloat(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Float toFloatOrNull(@NotNull String toFloatOrNull) {
        Intrinsics.checkParameterIsNotNull(toFloatOrNull, "$this$toFloatOrNull");
        try {
            if (!ScreenFloatValueRegEx.value.matches(toFloatOrNull)) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(toFloatOrNull));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int toInt(@NotNull String str, int i) {
        return Integer.parseInt(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long toLong(@NotNull String str, int i) {
        return Long.parseLong(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short toShort(@NotNull String str, int i) {
        return Short.parseShort(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(byte b, int i) {
        String num = Integer.toString(b, CharsKt__CharJVMKt.checkRadix(CharsKt__CharJVMKt.checkRadix(i)));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull String str, MathContext mathContext) {
        return new BigDecimal(str, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(@NotNull String str, int i) {
        return new BigInteger(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String toBigDecimalOrNull, @NotNull MathContext mathContext) {
        Intrinsics.checkParameterIsNotNull(toBigDecimalOrNull, "$this$toBigDecimalOrNull");
        Intrinsics.checkParameterIsNotNull(mathContext, "mathContext");
        try {
            if (!ScreenFloatValueRegEx.value.matches(toBigDecimalOrNull)) {
                return null;
            }
            return new BigDecimal(toBigDecimalOrNull, mathContext);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @InlineOnly
    private static final byte toByte(@NotNull String str) {
        return Byte.parseByte(str);
    }

    @InlineOnly
    private static final int toInt(@NotNull String str) {
        return Integer.parseInt(str);
    }

    @InlineOnly
    private static final long toLong(@NotNull String str) {
        return Long.parseLong(str);
    }

    @InlineOnly
    private static final short toShort(@NotNull String str) {
        return Short.parseShort(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(int i, int i2) {
        String num = Integer.toString(i, CharsKt__CharJVMKt.checkRadix(i2));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(long j, int i) {
        String l = Long.toString(j, CharsKt__CharJVMKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(short s, int i) {
        String num = Integer.toString(s, CharsKt__CharJVMKt.checkRadix(CharsKt__CharJVMKt.checkRadix(i)));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String toBigIntegerOrNull) {
        Intrinsics.checkParameterIsNotNull(toBigIntegerOrNull, "$this$toBigIntegerOrNull");
        return toBigIntegerOrNull(toBigIntegerOrNull, 10);
    }
}
