package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/collections/MapsKt__MapWithDefaultKt", "kotlin/collections/MapsKt__MapsJVMKt", "kotlin/collections/MapsKt__MapsKt", "kotlin/collections/MapsKt___MapsKt"}, k = 4, mv = {1, 1, 16}, xi = 1)
/* loaded from: classes.dex */
public final class MapsKt extends MapsKt___MapsKt {
    private MapsKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <K, V> Map<K, V> emptyMap() {
        return MapsKt__MapsKt.emptyMap();
    }

    @SinceKotlin(version = "1.1")
    public static /* bridge */ /* synthetic */ <K, V> V getValue(@NotNull Map<K, ? extends V> map, K k) {
        return (V) MapsKt__MapsKt.getValue(map, k);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static /* bridge */ /* synthetic */ <K, V> Map<K, V> toMutableMap(@NotNull Map<? extends K, ? extends V> map) {
        return MapsKt__MapsKt.toMutableMap(map);
    }
}
