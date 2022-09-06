package com.google.android.gms.internal.vision;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Incorrect field signature: TK; */
/* loaded from: classes2.dex */
public final class zzex implements Comparable<zzex>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzeq zzom;
    private final Comparable zzop;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public zzex(zzeq zzeqVar, K k, V v) {
        this.zzom = zzeqVar;
        this.zzop = k;
        this.value = v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzex(zzeq zzeqVar, Map.Entry<K, V> entry) {
        this(zzeqVar, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzex zzexVar) {
        return ((Comparable) getKey()).compareTo((Comparable) zzexVar.getKey());
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzop, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzop;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.zzop;
        int i = 0;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        V v = this.value;
        if (v != 0) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzom.zzdo();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzop);
        String valueOf2 = String.valueOf(this.value);
        return GeneratedOutlineSupport1.outline30(valueOf2.length() + valueOf.length() + 1, valueOf, Config.Compare.EQUAL_TO, valueOf2);
    }
}
