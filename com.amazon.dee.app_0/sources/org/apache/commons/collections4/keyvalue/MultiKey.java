package org.apache.commons.collections4.keyvalue;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes4.dex */
public class MultiKey<K> implements Serializable {
    private static final long serialVersionUID = 4465448607415788805L;
    private transient int hashCode;
    private final K[] keys;

    public MultiKey(K k, K k2) {
        this(new Object[]{k, k2}, false);
    }

    private void calculateHashCode(Object[] objArr) {
        int i = 0;
        for (Object obj : objArr) {
            if (obj != null) {
                i ^= obj.hashCode();
            }
        }
        this.hashCode = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiKey)) {
            return false;
        }
        return Arrays.equals(this.keys, ((MultiKey) obj).keys);
    }

    public K getKey(int i) {
        return this.keys[i];
    }

    public K[] getKeys() {
        return (K[]) ((Object[]) this.keys.clone());
    }

    public int hashCode() {
        return this.hashCode;
    }

    protected Object readResolve() {
        calculateHashCode(this.keys);
        return this;
    }

    public int size() {
        return this.keys.length;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MultiKey");
        outline107.append(Arrays.toString(this.keys));
        return outline107.toString();
    }

    public MultiKey(K k, K k2, K k3) {
        this(new Object[]{k, k2, k3}, false);
    }

    public MultiKey(K k, K k2, K k3, K k4) {
        this(new Object[]{k, k2, k3, k4}, false);
    }

    public MultiKey(K k, K k2, K k3, K k4, K k5) {
        this(new Object[]{k, k2, k3, k4, k5}, false);
    }

    public MultiKey(K[] kArr) {
        this((Object[]) kArr, true);
    }

    public MultiKey(K[] kArr, boolean z) {
        if (kArr != null) {
            if (z) {
                this.keys = (K[]) ((Object[]) kArr.clone());
            } else {
                this.keys = kArr;
            }
            calculateHashCode(kArr);
            return;
        }
        throw new IllegalArgumentException("The array of keys must not be null");
    }
}
