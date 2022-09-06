package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Tuples.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022 \u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u0003:\u0001\u0014B!\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\bJ)\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0013R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00028\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00028\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0015"}, d2 = {"Lkotlinx/serialization/internal/MapEntrySerializer;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlinx/serialization/internal/KeyValueSerializer;", "", "keySerializer", "Lkotlinx/serialization/KSerializer;", "valueSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "key", "getKey", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "value", "getValue", "toResult", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map$Entry;", "MapEntry", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This class is used only by the plugin in generated code and should not be used directly. Use corresponding factory functions instead", replaceWith = @ReplaceWith(expression = "MapEntrySerializer(keySerializer, valueSerializer, cSerializer)", imports = {"kotlinx.serialization.builtins.MapEntrySerializer"}))
/* loaded from: classes4.dex */
public final class MapEntrySerializer<K, V> extends KeyValueSerializer<K, V, Map.Entry<? extends K, ? extends V>> {
    @NotNull
    private final SerialDescriptor descriptor;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Tuples.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ.\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00022\b\b\u0002\u0010\u0005\u001a\u00028\u0003HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0016\u0010\u0004\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001a\u00028\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0017"}, d2 = {"Lkotlinx/serialization/internal/MapEntrySerializer$MapEntry;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlinx/serialization/internal/MapEntrySerializer$MapEntry;", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class MapEntry<K, V> implements Map.Entry<K, V>, KMappedMarker {
        private final K key;
        private final V value;

        public MapEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MapEntry copy$default(MapEntry mapEntry, Object obj, Object obj2, int i, Object obj3) {
            if ((i & 1) != 0) {
                obj = mapEntry.getKey();
            }
            if ((i & 2) != 0) {
                obj2 = mapEntry.getValue();
            }
            return mapEntry.copy(obj, obj2);
        }

        public final K component1() {
            return getKey();
        }

        public final V component2() {
            return getValue();
        }

        @NotNull
        public final MapEntry<K, V> copy(K k, V v) {
            return new MapEntry<>(k, v);
        }

        @Override // java.util.Map.Entry
        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof MapEntry)) {
                    return false;
                }
                MapEntry mapEntry = (MapEntry) obj;
                return Intrinsics.areEqual(getKey(), mapEntry.getKey()) && Intrinsics.areEqual(getValue(), mapEntry.getValue());
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K key = getKey();
            int i = 0;
            int hashCode = (key != null ? key.hashCode() : 0) * 31;
            V value = getValue();
            if (value != null) {
                i = value.hashCode();
            }
            return hashCode + i;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MapEntry(key=");
            outline107.append(getKey());
            outline107.append(", value=");
            return GeneratedOutlineSupport1.outline88(outline107, getValue(), ")");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapEntrySerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        super(keySerializer, valueSerializer, null);
        Intrinsics.checkParameterIsNotNull(keySerializer, "keySerializer");
        Intrinsics.checkParameterIsNotNull(valueSerializer, "valueSerializer");
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor("kotlin.collections.Map.Entry", StructureKind.MAP.INSTANCE, new MapEntrySerializer$descriptor$1(keySerializer, valueSerializer));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public /* bridge */ /* synthetic */ Object getKey(Object obj) {
        return getKey((Map.Entry) ((Map.Entry) obj));
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public /* bridge */ /* synthetic */ Object getValue(Object obj) {
        return getValue((Map.Entry) ((Map.Entry) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.KeyValueSerializer
    /* renamed from: toResult */
    public /* bridge */ /* synthetic */ Object mo12394toResult(Object obj, Object obj2) {
        return mo12394toResult((MapEntrySerializer<K, V>) obj, obj2);
    }

    public K getKey(@NotNull Map.Entry<? extends K, ? extends V> key) {
        Intrinsics.checkParameterIsNotNull(key, "$this$key");
        return key.getKey();
    }

    public V getValue(@NotNull Map.Entry<? extends K, ? extends V> value) {
        Intrinsics.checkParameterIsNotNull(value, "$this$value");
        return value.getValue();
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    @NotNull
    /* renamed from: toResult  reason: collision with other method in class */
    public Map.Entry<K, V> mo12394toResult(K k, V v) {
        return new MapEntry(k, v);
    }
}
