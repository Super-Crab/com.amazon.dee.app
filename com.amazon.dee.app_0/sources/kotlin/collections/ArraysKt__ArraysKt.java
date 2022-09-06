package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt___UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Arrays.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a8\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0087\b¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"contentDeepEqualsImpl", "", ExifInterface.GPS_DIRECTION_TRUE, "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/collections/ArraysKt")
/* loaded from: classes.dex */
public class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "contentDeepEquals")
    public static final <T> boolean contentDeepEquals(@NotNull T[] contentDeepEqualsImpl, @NotNull T[] other) {
        boolean m10846contentEqualsus8wMrg;
        boolean m10843contentEqualsctEhBpI;
        boolean m10845contentEqualsmazbYpA;
        boolean m10844contentEqualskdPth3s;
        Intrinsics.checkParameterIsNotNull(contentDeepEqualsImpl, "$this$contentDeepEqualsImpl");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (contentDeepEqualsImpl == other) {
            return true;
        }
        if (contentDeepEqualsImpl.length != other.length) {
            return false;
        }
        int length = contentDeepEqualsImpl.length;
        for (int i = 0; i < length; i++) {
            T t = contentDeepEqualsImpl[i];
            T t2 = other[i];
            if (t != t2) {
                if (t == null || t2 == null) {
                    return false;
                }
                if (!(t instanceof Object[]) || !(t2 instanceof Object[])) {
                    if (!(t instanceof byte[]) || !(t2 instanceof byte[])) {
                        if (!(t instanceof short[]) || !(t2 instanceof short[])) {
                            if (!(t instanceof int[]) || !(t2 instanceof int[])) {
                                if (!(t instanceof long[]) || !(t2 instanceof long[])) {
                                    if (!(t instanceof float[]) || !(t2 instanceof float[])) {
                                        if (!(t instanceof double[]) || !(t2 instanceof double[])) {
                                            if (!(t instanceof char[]) || !(t2 instanceof char[])) {
                                                if (!(t instanceof boolean[]) || !(t2 instanceof boolean[])) {
                                                    if ((t instanceof UByteArray) && (t2 instanceof UByteArray)) {
                                                        m10844contentEqualskdPth3s = UArraysKt___UArraysKt.m10844contentEqualskdPth3s(((UByteArray) t).m10454unboximpl(), ((UByteArray) t2).m10454unboximpl());
                                                        if (!m10844contentEqualskdPth3s) {
                                                            return false;
                                                        }
                                                    } else if ((t instanceof UShortArray) && (t2 instanceof UShortArray)) {
                                                        m10845contentEqualsmazbYpA = UArraysKt___UArraysKt.m10845contentEqualsmazbYpA(((UShortArray) t).m10690unboximpl(), ((UShortArray) t2).m10690unboximpl());
                                                        if (!m10845contentEqualsmazbYpA) {
                                                            return false;
                                                        }
                                                    } else if ((t instanceof UIntArray) && (t2 instanceof UIntArray)) {
                                                        m10843contentEqualsctEhBpI = UArraysKt___UArraysKt.m10843contentEqualsctEhBpI(((UIntArray) t).m10524unboximpl(), ((UIntArray) t2).m10524unboximpl());
                                                        if (!m10843contentEqualsctEhBpI) {
                                                            return false;
                                                        }
                                                    } else if ((t instanceof ULongArray) && (t2 instanceof ULongArray)) {
                                                        m10846contentEqualsus8wMrg = UArraysKt___UArraysKt.m10846contentEqualsus8wMrg(((ULongArray) t).m10594unboximpl(), ((ULongArray) t2).m10594unboximpl());
                                                        if (!m10846contentEqualsus8wMrg) {
                                                            return false;
                                                        }
                                                    } else if (!Intrinsics.areEqual(t, t2)) {
                                                        return false;
                                                    }
                                                } else if (!Arrays.equals((boolean[]) t, (boolean[]) t2)) {
                                                    return false;
                                                }
                                            } else if (!Arrays.equals((char[]) t, (char[]) t2)) {
                                                return false;
                                            }
                                        } else if (!Arrays.equals((double[]) t, (double[]) t2)) {
                                            return false;
                                        }
                                    } else if (!Arrays.equals((float[]) t, (float[]) t2)) {
                                        return false;
                                    }
                                } else if (!Arrays.equals((long[]) t, (long[]) t2)) {
                                    return false;
                                }
                            } else if (!Arrays.equals((int[]) t, (int[]) t2)) {
                                return false;
                            }
                        } else if (!Arrays.equals((short[]) t, (short[]) t2)) {
                            return false;
                        }
                    } else if (!Arrays.equals((byte[]) t, (byte[]) t2)) {
                        return false;
                    }
                } else if (!contentDeepEquals((Object[]) t, (Object[]) t2)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "contentDeepToString")
    @NotNull
    @PublishedApi
    public static final <T> String contentDeepToString(@NotNull T[] contentDeepToStringImpl) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(contentDeepToStringImpl, "$this$contentDeepToStringImpl");
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(contentDeepToStringImpl.length, 429496729);
        StringBuilder sb = new StringBuilder((coerceAtMost * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(contentDeepToStringImpl, sb, new ArrayList());
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(@NotNull T[] tArr, StringBuilder sb, List<Object[]> list) {
        String m10853contentToStringQwZRm1k;
        String m10851contentToStringajY9A;
        String m10854contentToStringrL5Bavg;
        String m10852contentToStringGBYM_sE;
        if (list.contains(tArr)) {
            sb.append("[...]");
            return;
        }
        list.add(tArr);
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            T t = tArr[i];
            if (t == null) {
                sb.append("null");
            } else if (t instanceof Object[]) {
                contentDeepToStringInternal$ArraysKt__ArraysKt((Object[]) t, sb, list);
            } else if (t instanceof byte[]) {
                String arrays = Arrays.toString((byte[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
                sb.append(arrays);
            } else if (t instanceof short[]) {
                String arrays2 = Arrays.toString((short[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
                sb.append(arrays2);
            } else if (t instanceof int[]) {
                String arrays3 = Arrays.toString((int[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays3, "java.util.Arrays.toString(this)");
                sb.append(arrays3);
            } else if (t instanceof long[]) {
                String arrays4 = Arrays.toString((long[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays4, "java.util.Arrays.toString(this)");
                sb.append(arrays4);
            } else if (t instanceof float[]) {
                String arrays5 = Arrays.toString((float[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays5, "java.util.Arrays.toString(this)");
                sb.append(arrays5);
            } else if (t instanceof double[]) {
                String arrays6 = Arrays.toString((double[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays6, "java.util.Arrays.toString(this)");
                sb.append(arrays6);
            } else if (t instanceof char[]) {
                String arrays7 = Arrays.toString((char[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays7, "java.util.Arrays.toString(this)");
                sb.append(arrays7);
            } else if (t instanceof boolean[]) {
                String arrays8 = Arrays.toString((boolean[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays8, "java.util.Arrays.toString(this)");
                sb.append(arrays8);
            } else if (t instanceof UByteArray) {
                m10852contentToStringGBYM_sE = UArraysKt___UArraysKt.m10852contentToStringGBYM_sE(((UByteArray) t).m10454unboximpl());
                sb.append(m10852contentToStringGBYM_sE);
            } else if (t instanceof UShortArray) {
                m10854contentToStringrL5Bavg = UArraysKt___UArraysKt.m10854contentToStringrL5Bavg(((UShortArray) t).m10690unboximpl());
                sb.append(m10854contentToStringrL5Bavg);
            } else if (t instanceof UIntArray) {
                m10851contentToStringajY9A = UArraysKt___UArraysKt.m10851contentToStringajY9A(((UIntArray) t).m10524unboximpl());
                sb.append(m10851contentToStringajY9A);
            } else if (t instanceof ULongArray) {
                m10853contentToStringQwZRm1k = UArraysKt___UArraysKt.m10853contentToStringQwZRm1k(((ULongArray) t).m10594unboximpl());
                sb.append(m10853contentToStringQwZRm1k);
            } else {
                sb.append(t.toString());
            }
        }
        sb.append(JsonReaderKt.END_LIST);
        list.remove(CollectionsKt__CollectionsKt.getLastIndex(list));
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull T[][] flatten) {
        Intrinsics.checkParameterIsNotNull(flatten, "$this$flatten");
        int i = 0;
        for (T[] tArr : flatten) {
            i += tArr.length;
        }
        ArrayList arrayList = new ArrayList(i);
        for (T[] tArr2 : flatten) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, tArr2);
        }
        return arrayList;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Object[] objArr, Function0 function0) {
        return objArr.length == 0 ? function0.mo12560invoke() : objArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable Object[] objArr) {
        if (objArr != null) {
            if (!(objArr.length == 0)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] unzip) {
        Intrinsics.checkParameterIsNotNull(unzip, "$this$unzip");
        ArrayList arrayList = new ArrayList(unzip.length);
        ArrayList arrayList2 = new ArrayList(unzip.length);
        for (Pair<? extends T, ? extends R> pair : unzip) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }
}
