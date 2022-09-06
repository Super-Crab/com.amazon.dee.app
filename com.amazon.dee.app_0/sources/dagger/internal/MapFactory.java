package dagger.internal;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes3.dex */
public final class MapFactory<K, V> implements Factory<Map<K, V>> {
    private static final Provider<Map<Object, Object>> EMPTY = InstanceFactory.create(Collections.emptyMap());
    private final Map<K, Provider<V>> contributingMap;

    /* loaded from: classes3.dex */
    public static final class Builder<K, V> {
        private final LinkedHashMap<K, Provider<V>> map;

        public MapFactory<K, V> build() {
            return new MapFactory<>(this.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V> put(K key, Provider<V> providerOfValue) {
            this.map.put(Preconditions.checkNotNull(key, "key"), Preconditions.checkNotNull(providerOfValue, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER));
            return this;
        }

        public Builder<K, V> putAll(Provider<Map<K, V>> mapFactory) {
            this.map.putAll(((MapFactory) mapFactory).contributingMap);
            return this;
        }

        private Builder(int size) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(size);
        }
    }

    public static <K, V> Builder<K, V> builder(int size) {
        return new Builder<>(size);
    }

    public static <K, V> Provider<Map<K, V>> emptyMapProvider() {
        return (Provider<Map<K, V>>) EMPTY;
    }

    private MapFactory(Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Map<K, V> mo10268get() {
        LinkedHashMap newLinkedHashMapWithExpectedSize = DaggerCollections.newLinkedHashMapWithExpectedSize(this.contributingMap.size());
        for (Map.Entry<K, Provider<V>> entry : this.contributingMap.entrySet()) {
            newLinkedHashMapWithExpectedSize.put(entry.getKey(), entry.getValue().mo10268get());
        }
        return Collections.unmodifiableMap(newLinkedHashMapWithExpectedSize);
    }
}
