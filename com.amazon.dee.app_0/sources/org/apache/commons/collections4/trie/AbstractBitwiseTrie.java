package org.apache.commons.collections4.trie;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import org.apache.commons.collections4.Trie;
/* loaded from: classes4.dex */
public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V> implements Trie<K, V>, Serializable {
    private static final long serialVersionUID = 5826987063535505652L;
    private final KeyAnalyzer<? super K> keyAnalyzer;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBitwiseTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        if (keyAnalyzer != null) {
            this.keyAnalyzer = keyAnalyzer;
            return;
        }
        throw new NullPointerException("keyAnalyzer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean compare(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int bitIndex(K k, K k2) {
        return this.keyAnalyzer.bitIndex(k, 0, lengthInBits(k), k2, 0, lengthInBits(k2));
    }

    final int bitsPerElement() {
        return this.keyAnalyzer.bitsPerElement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public final K castKey(Object obj) {
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean compareKeys(K k, K k2) {
        return k == null ? k2 == null : k2 != null && this.keyAnalyzer.compare(k, k2) == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyAnalyzer<? super K> getKeyAnalyzer() {
        return this.keyAnalyzer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isBitSet(K k, int i, int i2) {
        if (k == null) {
            return false;
        }
        return this.keyAnalyzer.isBitSet(k, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int lengthInBits(K k) {
        if (k == null) {
            return 0;
        }
        return this.keyAnalyzer.lengthInBits(k);
    }

    @Override // java.util.AbstractMap
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trie[");
        outline107.append(size());
        outline107.append("]={\n");
        for (Map.Entry<K, V> entry : entrySet()) {
            outline107.append("  ");
            outline107.append(entry);
            outline107.append("\n");
        }
        outline107.append("}\n");
        return outline107.toString();
    }

    /* loaded from: classes4.dex */
    static abstract class BasicEntry<K, V> implements Map.Entry<K, V>, Serializable {
        private static final long serialVersionUID = -944364551314110330L;
        protected K key;
        protected V value;

        public BasicEntry(K k) {
            this.key = k;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return AbstractBitwiseTrie.compare(this.key, entry.getKey()) && AbstractBitwiseTrie.compare(this.value, entry.getValue());
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
            int i = 0;
            int hashCode = getKey() == null ? 0 : getKey().hashCode();
            if (getValue() != null) {
                i = getValue().hashCode();
            }
            return hashCode ^ i;
        }

        public V setKeyValue(K k, V v) {
            this.key = k;
            return setValue(v);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public String toString() {
            return this.key + Config.Compare.EQUAL_TO + this.value;
        }

        public BasicEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }
}
