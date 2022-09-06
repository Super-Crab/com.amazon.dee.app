package kotlinx.serialization.builtins;

import androidx.exifinterface.media.ExifInterface;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.MapEntrySerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PairSerializer;
import kotlinx.serialization.internal.TripleSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: BuiltinSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a@\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u00070\u0001\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0001\u001a@\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\r0\u0001\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0001\u001aZ\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u000f0\u0001\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0001\"-\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0016"}, d2 = {"nullable", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "getNullable", "(Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "MapEntrySerializer", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "keySerializer", "valueSerializer", "PairSerializer", "Lkotlin/Pair;", "TripleSerializer", "Lkotlin/Triple;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "aSerializer", "bSerializer", "cSerializer", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BuiltinSerializersKt {
    @NotNull
    public static final <K, V> KSerializer<Map.Entry<K, V>> MapEntrySerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        Intrinsics.checkParameterIsNotNull(keySerializer, "keySerializer");
        Intrinsics.checkParameterIsNotNull(valueSerializer, "valueSerializer");
        return new MapEntrySerializer(keySerializer, valueSerializer);
    }

    @NotNull
    public static final <K, V> KSerializer<Pair<K, V>> PairSerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        Intrinsics.checkParameterIsNotNull(keySerializer, "keySerializer");
        Intrinsics.checkParameterIsNotNull(valueSerializer, "valueSerializer");
        return new PairSerializer(keySerializer, valueSerializer);
    }

    @NotNull
    public static final <A, B, C> KSerializer<Triple<A, B, C>> TripleSerializer(@NotNull KSerializer<A> aSerializer, @NotNull KSerializer<B> bSerializer, @NotNull KSerializer<C> cSerializer) {
        Intrinsics.checkParameterIsNotNull(aSerializer, "aSerializer");
        Intrinsics.checkParameterIsNotNull(bSerializer, "bSerializer");
        Intrinsics.checkParameterIsNotNull(cSerializer, "cSerializer");
        return new TripleSerializer(aSerializer, bSerializer, cSerializer);
    }

    @NotNull
    public static final <T> KSerializer<T> getNullable(@NotNull KSerializer<T> nullable) {
        Intrinsics.checkParameterIsNotNull(nullable, "$this$nullable");
        return nullable.getDescriptor().isNullable() ? nullable : new NullableSerializer(nullable);
    }
}
