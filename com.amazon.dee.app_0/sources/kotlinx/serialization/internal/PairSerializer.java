package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Tuples.kt */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This class is used only by the plugin in generated code and should not be used directly. Use corresponding factory functions instead", replaceWith = @ReplaceWith(expression = "PairSerializer(keySerializer, valueSerializer, cSerializer)", imports = {"kotlinx.serialization.builtins.PairSerializer"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022 \u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u0003B!\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\bJ)\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0013R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00028\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00028\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0014"}, d2 = {"Lkotlinx/serialization/internal/PairSerializer;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlinx/serialization/internal/KeyValueSerializer;", "Lkotlin/Pair;", "keySerializer", "Lkotlinx/serialization/KSerializer;", "valueSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "key", "getKey", "(Lkotlin/Pair;)Ljava/lang/Object;", "value", "getValue", "toResult", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public final class PairSerializer<K, V> extends KeyValueSerializer<K, V, Pair<? extends K, ? extends V>> {
    @NotNull
    private final SerialDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PairSerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        super(keySerializer, valueSerializer, null);
        Intrinsics.checkParameterIsNotNull(keySerializer, "keySerializer");
        Intrinsics.checkParameterIsNotNull(valueSerializer, "valueSerializer");
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor$default("kotlin.Pair", null, new PairSerializer$descriptor$1(keySerializer, valueSerializer), 2, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public /* bridge */ /* synthetic */ Object getKey(Object obj) {
        return getKey((Pair) ((Pair) obj));
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public /* bridge */ /* synthetic */ Object getValue(Object obj) {
        return getValue((Pair) ((Pair) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.KeyValueSerializer
    /* renamed from: toResult */
    public /* bridge */ /* synthetic */ Object mo12394toResult(Object obj, Object obj2) {
        return mo12394toResult((PairSerializer<K, V>) obj, obj2);
    }

    public K getKey(@NotNull Pair<? extends K, ? extends V> key) {
        Intrinsics.checkParameterIsNotNull(key, "$this$key");
        return key.getFirst();
    }

    public V getValue(@NotNull Pair<? extends K, ? extends V> value) {
        Intrinsics.checkParameterIsNotNull(value, "$this$value");
        return value.getSecond();
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    @NotNull
    /* renamed from: toResult  reason: collision with other method in class */
    public Pair<K, V> mo12394toResult(K k, V v) {
        return kotlin.TuplesKt.to(k, v);
    }
}
