package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/collections/CollectionsKt__CollectionsJVMKt", "kotlin/collections/CollectionsKt__CollectionsKt", "kotlin/collections/CollectionsKt__IterablesKt", "kotlin/collections/CollectionsKt__IteratorsJVMKt", "kotlin/collections/CollectionsKt__IteratorsKt", "kotlin/collections/CollectionsKt__MutableCollectionsJVMKt", "kotlin/collections/CollectionsKt__MutableCollectionsKt", "kotlin/collections/CollectionsKt__ReversedViewsKt", "kotlin/collections/CollectionsKt___CollectionsJvmKt", "kotlin/collections/CollectionsKt___CollectionsKt"}, k = 4, mv = {1, 1, 16}, xi = 1)
/* loaded from: classes.dex */
public final class CollectionsKt extends CollectionsKt___CollectionsKt {
    private CollectionsKt() {
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T elementAtOrNull(@NotNull Iterable<? extends T> iterable, int i) {
        return (T) CollectionsKt___CollectionsKt.elementAtOrNull(iterable, i);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> List<T> emptyList() {
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public static /* bridge */ /* synthetic */ <T> T first(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.first(iterable);
    }

    public static /* bridge */ /* synthetic */ <T> T first(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.first((List<? extends Object>) list);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T firstOrNull(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.firstOrNull(iterable);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T firstOrNull(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) list);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T getOrNull(@NotNull List<? extends T> list, int i) {
        return (T) CollectionsKt___CollectionsKt.getOrNull(list, i);
    }

    public static /* bridge */ /* synthetic */ Appendable joinTo$default(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        return CollectionsKt___CollectionsKt.joinTo$default(iterable, appendable, charSequence, charSequence2, charSequence3, i, charSequence4, function1, i2, obj);
    }

    public static /* bridge */ /* synthetic */ <T> T last(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.last(iterable);
    }

    public static /* bridge */ /* synthetic */ <T> T last(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.last((List<? extends Object>) list);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T lastOrNull(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.lastOrNull(iterable);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T lastOrNull(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) list);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T extends Comparable<? super T>> T max(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.max(iterable);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T extends Comparable<? super T>> T min(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.min(iterable);
    }

    public static /* bridge */ /* synthetic */ <T> T single(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.single(iterable);
    }

    public static /* bridge */ /* synthetic */ <T> T single(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.single((List<? extends Object>) list);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T singleOrNull(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.singleOrNull(iterable);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T singleOrNull(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.singleOrNull((List<? extends Object>) list);
    }
}
