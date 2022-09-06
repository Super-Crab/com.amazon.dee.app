package com.google.common.collect;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    @Override // java.util.Map.Entry
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return Objects.equal(mo8108getKey(), entry.getKey()) && Objects.equal(mo7742getValue(), entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    /* renamed from: getKey */
    public abstract K mo8108getKey();

    @Override // java.util.Map.Entry
    /* renamed from: getValue */
    public abstract V mo7742getValue();

    @Override // java.util.Map.Entry
    public int hashCode() {
        K mo8108getKey = mo8108getKey();
        V mo7742getValue = mo7742getValue();
        int i = 0;
        int hashCode = mo8108getKey == null ? 0 : mo8108getKey.hashCode();
        if (mo7742getValue != null) {
            i = mo7742getValue.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        String valueOf = String.valueOf(mo8108getKey());
        String valueOf2 = String.valueOf(mo7742getValue());
        return GeneratedOutlineSupport1.outline30(valueOf2.length() + valueOf.length() + 1, valueOf, Config.Compare.EQUAL_TO, valueOf2);
    }
}
