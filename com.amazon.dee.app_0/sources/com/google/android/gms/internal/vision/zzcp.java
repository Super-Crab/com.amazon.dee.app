package com.google.android.gms.internal.vision;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzcp extends zzbi<Float> implements zzcw<Float>, zzej, RandomAccess {
    private static final zzcp zzko;
    private int size;
    private float[] zzkp;

    static {
        zzcp zzcpVar = new zzcp();
        zzko = zzcpVar;
        zzcpVar.zzao();
    }

    zzcp() {
        this(new float[10], 0);
    }

    private zzcp(float[] fArr, int i) {
        this.zzkp = fArr;
        this.size = i;
    }

    private final void zzc(int i, float f) {
        int i2;
        zzap();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
        float[] fArr = this.zzkp;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzkp, i, fArr2, i + 1, this.size - i);
            this.zzkp = fArr2;
        }
        this.zzkp[i] = f;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    private final void zzi(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
    }

    private final String zzj(int i) {
        return GeneratedOutlineSupport1.outline28(35, "Index:", i, ", Size:", this.size);
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzap();
        zzct.checkNotNull(collection);
        if (!(collection instanceof zzcp)) {
            return super.addAll(collection);
        }
        zzcp zzcpVar = (zzcp) collection;
        int i = zzcpVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        float[] fArr = this.zzkp;
        if (i3 > fArr.length) {
            this.zzkp = Arrays.copyOf(fArr, i3);
        }
        System.arraycopy(zzcpVar.zzkp, 0, this.zzkp, this.size, zzcpVar.size);
        this.size = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcp)) {
            return super.equals(obj);
        }
        zzcp zzcpVar = (zzcp) obj;
        if (this.size != zzcpVar.size) {
            return false;
        }
        float[] fArr = zzcpVar.zzkp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkp[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzi(i);
        return Float.valueOf(this.zzkp[i]);
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzkp[i2]);
        }
        return i;
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzap();
        zzi(i);
        float[] fArr = this.zzkp;
        float f = fArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, i2 - i);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzap();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzkp[i]))) {
                float[] fArr = this.zzkp;
                System.arraycopy(fArr, i + 1, fArr, i, this.size - i);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzap();
        if (i2 >= i) {
            float[] fArr = this.zzkp;
            System.arraycopy(fArr, i2, fArr, i, this.size - i2);
            this.size -= i2 - i;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.vision.zzbi, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzap();
        zzi(i);
        float[] fArr = this.zzkp;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zze(float f) {
        zzc(this.size, f);
    }

    @Override // com.google.android.gms.internal.vision.zzcw
    public final /* synthetic */ zzcw<Float> zzk(int i) {
        if (i >= this.size) {
            return new zzcp(Arrays.copyOf(this.zzkp, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
