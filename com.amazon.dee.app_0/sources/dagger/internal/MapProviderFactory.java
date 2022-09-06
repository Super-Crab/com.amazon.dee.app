package dagger.internal;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import dagger.Lazy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes3.dex */
public final class MapProviderFactory<K, V> implements Factory<Map<K, Provider<V>>>, Lazy<Map<K, Provider<V>>> {
    private final Map<K, Provider<V>> contributingMap;

    /* loaded from: classes3.dex */
    public static final class Builder<K, V> {
        private final LinkedHashMap<K, Provider<V>> map;

        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V> put(K key, Provider<V> providerOfValue) {
            this.map.put(Preconditions.checkNotNull(key, "key"), Preconditions.checkNotNull(providerOfValue, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER));
            return this;
        }

        public Builder<K, V> putAll(Provider<Map<K, Provider<V>>> mapProviderFactory) {
            this.map.putAll(((MapProviderFactory) mapProviderFactory).contributingMap);
            return this;
        }

        private Builder(int size) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(size);
        }
    }

    public static <K, V> Builder<K, V> builder(int size) {
        return new Builder<>(size);
    }

    private MapProviderFactory(Map<K, Provider<V>> contributingMap) {
        this.contributingMap = Collections.unmodifiableMap(contributingMap);
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Map<K, Provider<V>> mo10268get() {
        return this.contributingMap;
    }
}
