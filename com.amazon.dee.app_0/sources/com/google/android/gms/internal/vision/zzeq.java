package com.google.android.gms.internal.vision;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzeq<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzhv;
    private final int zzof;
    private List<zzex> zzog;
    private Map<K, V> zzoh;
    private volatile zzez zzoi;
    private Map<K, V> zzoj;
    private volatile zzet zzok;

    private zzeq(int i) {
        this.zzof = i;
        this.zzog = Collections.emptyList();
        this.zzoh = Collections.emptyMap();
        this.zzoj = Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeq(int i, zzer zzerVar) {
        this(i);
    }

    private final int zza(K k) {
        int size = this.zzog.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzog.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzog.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <FieldDescriptorType extends zzcl<FieldDescriptorType>> zzeq<FieldDescriptorType, Object> zzam(int i) {
        return new zzer(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzao(int i) {
        zzdo();
        V v = (V) this.zzog.remove(i).getValue();
        if (!this.zzoh.isEmpty()) {
            Iterator<Map.Entry<K, V>> it2 = zzdp().entrySet().iterator();
            this.zzog.add(new zzex(this, it2.next()));
            it2.remove();
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzdo() {
        if (!this.zzhv) {
            return;
        }
        throw new UnsupportedOperationException();
    }

    private final SortedMap<K, V> zzdp() {
        zzdo();
        if (this.zzoh.isEmpty() && !(this.zzoh instanceof TreeMap)) {
            this.zzoh = new TreeMap();
            this.zzoj = ((TreeMap) this.zzoh).descendingMap();
        }
        return (SortedMap) this.zzoh;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzdo();
        if (!this.zzog.isEmpty()) {
            this.zzog.clear();
        }
        if (!this.zzoh.isEmpty()) {
            this.zzoh.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zzeq<K, V>) comparable) >= 0 || this.zzoh.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzoi == null) {
            this.zzoi = new zzez(this, null);
        }
        return this.zzoi;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeq)) {
            return super.equals(obj);
        }
        zzeq zzeqVar = (zzeq) obj;
        int size = size();
        if (size != zzeqVar.size()) {
            return false;
        }
        int zzdl = zzdl();
        if (zzdl != zzeqVar.zzdl()) {
            return entrySet().equals(zzeqVar.entrySet());
        }
        for (int i = 0; i < zzdl; i++) {
            if (!zzan(i).equals(zzeqVar.zzan(i))) {
                return false;
            }
        }
        if (zzdl == size) {
            return true;
        }
        return this.zzoh.equals(zzeqVar.zzoh);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((zzeq<K, V>) comparable);
        return zza >= 0 ? (V) this.zzog.get(zza).getValue() : this.zzoh.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int zzdl = zzdl();
        int i = 0;
        for (int i2 = 0; i2 < zzdl; i2++) {
            i += this.zzog.get(i2).hashCode();
        }
        return this.zzoh.size() > 0 ? i + this.zzoh.hashCode() : i;
    }

    public final boolean isImmutable() {
        return this.zzhv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zzeq<K, V>) ((Comparable) obj), (Comparable) obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzdo();
        Comparable comparable = (Comparable) obj;
        int zza = zza((zzeq<K, V>) comparable);
        if (zza >= 0) {
            return (V) zzao(zza);
        }
        if (!this.zzoh.isEmpty()) {
            return this.zzoh.remove(comparable);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzoh.size() + this.zzog.size();
    }

    public final V zza(K k, V v) {
        zzdo();
        int zza = zza((zzeq<K, V>) k);
        if (zza >= 0) {
            return (V) this.zzog.get(zza).setValue(v);
        }
        zzdo();
        if (this.zzog.isEmpty() && !(this.zzog instanceof ArrayList)) {
            this.zzog = new ArrayList(this.zzof);
        }
        int i = -(zza + 1);
        if (i >= this.zzof) {
            return zzdp().put(k, v);
        }
        int size = this.zzog.size();
        int i2 = this.zzof;
        if (size == i2) {
            zzex remove = this.zzog.remove(i2 - 1);
            zzdp().put((K) remove.getKey(), (V) remove.getValue());
        }
        this.zzog.add(i, new zzex(this, k, v));
        return null;
    }

    public final Map.Entry<K, V> zzan(int i) {
        return this.zzog.get(i);
    }

    public void zzao() {
        if (!this.zzhv) {
            this.zzoh = this.zzoh.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzoh);
            this.zzoj = this.zzoj.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzoj);
            this.zzhv = true;
        }
    }

    public final int zzdl() {
        return this.zzog.size();
    }

    public final Iterable<Map.Entry<K, V>> zzdm() {
        return this.zzoh.isEmpty() ? zzeu.zzdr() : this.zzoh.entrySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzdn() {
        if (this.zzok == null) {
            this.zzok = new zzet(this, null);
        }
        return this.zzok;
    }
}
