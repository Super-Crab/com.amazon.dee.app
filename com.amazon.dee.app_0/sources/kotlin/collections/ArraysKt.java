package kotlin.collections;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/collections/ArraysKt__ArraysJVMKt", "kotlin/collections/ArraysKt__ArraysKt", "kotlin/collections/ArraysKt___ArraysJvmKt", "kotlin/collections/ArraysKt___ArraysKt"}, k = 4, mv = {1, 1, 16}, xi = 1)
/* loaded from: classes.dex */
public final class ArraysKt extends ArraysKt___ArraysKt {
    private ArraysKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> asSequence(@NotNull T[] tArr) {
        return ArraysKt___ArraysKt.asSequence(tArr);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static /* bridge */ /* synthetic */ byte[] copyInto(@NotNull byte[] bArr, @NotNull byte[] bArr2, int i, int i2, int i3) {
        return ArraysKt___ArraysJvmKt.copyInto(bArr, bArr2, i, i2, i3);
    }

    public static /* bridge */ /* synthetic */ Object[] copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        return ArraysKt___ArraysJvmKt.copyInto$default(objArr, objArr2, i, i2, i3, i4, obj);
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static /* bridge */ /* synthetic */ <T> T[] copyOfRange(@NotNull T[] tArr, int i, int i2) {
        return (T[]) ArraysKt___ArraysJvmKt.copyOfRange(tArr, i, i2);
    }

    public static /* bridge */ /* synthetic */ <T> T first(@NotNull T[] tArr) {
        return (T) ArraysKt___ArraysKt.first(tArr);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T firstOrNull(@NotNull T[] tArr) {
        return (T) ArraysKt___ArraysKt.firstOrNull(tArr);
    }

    public static /* bridge */ /* synthetic */ <T> T last(@NotNull T[] tArr) {
        return (T) ArraysKt___ArraysKt.last(tArr);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T lastOrNull(@NotNull T[] tArr) {
        return (T) ArraysKt___ArraysKt.lastOrNull(tArr);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> T[] plus(@NotNull T[] tArr, T t) {
        return (T[]) ArraysKt___ArraysJvmKt.plus(tArr, t);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> T[] plus(@NotNull T[] tArr, @NotNull T[] tArr2) {
        return (T[]) ArraysKt___ArraysJvmKt.plus((Object[]) tArr, (Object[]) tArr2);
    }

    public static /* bridge */ /* synthetic */ <T> T single(@NotNull T[] tArr) {
        return (T) ArraysKt___ArraysKt.single(tArr);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T singleOrNull(@NotNull T[] tArr) {
        return (T) ArraysKt___ArraysKt.singleOrNull(tArr);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T, C extends Collection<? super T>> C toCollection(@NotNull T[] tArr, @NotNull C c) {
        return (C) ArraysKt___ArraysKt.toCollection(tArr, c);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> List<T> toList(@NotNull T[] tArr) {
        return ArraysKt___ArraysKt.toList(tArr);
    }
}
