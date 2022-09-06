package kotlinx.serialization.builtins;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0006\u001a&\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\u001a@\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f0\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0001\u001a&\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00070\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"-\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"-\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00070\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"list", "Lkotlinx/serialization/KSerializer;", "", ExifInterface.GPS_DIRECTION_TRUE, "getList", "(Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "set", "", "getSet", "ListSerializer", "elementSerializer", "MapSerializer", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "keySerializer", "valueSerializer", "SetSerializer", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CollectionSerializersKt {
    @NotNull
    public static final <T> KSerializer<List<T>> ListSerializer(@NotNull KSerializer<T> elementSerializer) {
        Intrinsics.checkParameterIsNotNull(elementSerializer, "elementSerializer");
        return new ArrayListSerializer(elementSerializer);
    }

    @NotNull
    public static final <K, V> KSerializer<Map<K, V>> MapSerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        Intrinsics.checkParameterIsNotNull(keySerializer, "keySerializer");
        Intrinsics.checkParameterIsNotNull(valueSerializer, "valueSerializer");
        return new LinkedHashMapSerializer(keySerializer, valueSerializer);
    }

    @NotNull
    public static final <T> KSerializer<Set<T>> SetSerializer(@NotNull KSerializer<T> elementSerializer) {
        Intrinsics.checkParameterIsNotNull(elementSerializer, "elementSerializer");
        return new LinkedHashSetSerializer(elementSerializer);
    }

    @NotNull
    public static final <T> KSerializer<List<T>> getList(@NotNull KSerializer<T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$this$list");
        return new ArrayListSerializer(list);
    }

    @NotNull
    public static final <T> KSerializer<Set<T>> getSet(@NotNull KSerializer<T> set) {
        Intrinsics.checkParameterIsNotNull(set, "$this$set");
        return new LinkedHashSetSerializer(set);
    }
}
