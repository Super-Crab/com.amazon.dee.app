package com.google.common.collect;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Map.Entry<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract Map.Entry<K, V> mo8280delegate();

    @Override // java.util.Map.Entry
    public boolean equals(@NullableDecl Object obj) {
        return mo8280delegate().equals(obj);
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return mo8280delegate().getKey();
    }

    @Override // java.util.Map.Entry
    /* renamed from: getValue */
    public V mo8055getValue() {
        return mo8280delegate().getValue();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return mo8280delegate().hashCode();
    }

    public V setValue(V v) {
        return mo8280delegate().setValue(v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean standardEquals(@NullableDecl Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return Objects.equal(getKey(), entry.getKey()) && Objects.equal(mo8055getValue(), entry.getValue());
        }
        return false;
    }

    protected int standardHashCode() {
        K key = getKey();
        V mo8055getValue = mo8055getValue();
        int i = 0;
        int hashCode = key == null ? 0 : key.hashCode();
        if (mo8055getValue != null) {
            i = mo8055getValue.hashCode();
        }
        return hashCode ^ i;
    }

    @Beta
    protected String standardToString() {
        String valueOf = String.valueOf(getKey());
        String valueOf2 = String.valueOf(mo8055getValue());
        return GeneratedOutlineSupport1.outline30(valueOf2.length() + valueOf.length() + 1, valueOf, Config.Compare.EQUAL_TO, valueOf2);
    }
}
