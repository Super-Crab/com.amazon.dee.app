package com.amazon.identity.auth.device;

import java.util.Date;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gj<T> implements ix<gj<T>> {
    private final T mValue;
    private final Date nW;
    private boolean nX;
    private boolean nY;

    public gj(T t, Date date, boolean z, boolean z2) {
        in.a(date, "dateTime");
        this.mValue = t;
        this.nW = (Date) date.clone();
        this.nX = z;
        this.nY = z2;
    }

    public void a(Date date) {
        if (!this.nW.equals(date)) {
            return;
        }
        this.nX = false;
    }

    public void b(Date date) {
        if (this.nW.after(date)) {
            return;
        }
        this.nX = false;
    }

    public boolean c(Date date) {
        if (date == null) {
            return true;
        }
        return this.nW.after(date);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && gj.class == obj.getClass()) {
            gj gjVar = (gj) obj;
            if (this.nY == gjVar.nY && this.nX == gjVar.nX && fh().equals(fh()) && Cif.equals(getValue(), gjVar.getValue())) {
                return true;
            }
        }
        return false;
    }

    public Date fh() {
        return (Date) this.nW.clone();
    }

    public boolean fi() {
        return this.nY;
    }

    @Override // com.amazon.identity.auth.device.ix
    /* renamed from: fj */
    public gj<T> ek() {
        try {
            return new gj<>(Cif.g(this.mValue), (Date) this.nW.clone(), this.nX, this.nY);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public T getValue() {
        return this.mValue;
    }

    public int hashCode() {
        Date date = this.nW;
        int i = 0;
        int i2 = 1231;
        int hashCode = ((((date == null ? 0 : date.hashCode()) + 31) * 31) + (this.nY ? 1231 : 1237)) * 31;
        if (!this.nX) {
            i2 = 1237;
        }
        int i3 = (hashCode + i2) * 31;
        T t = this.mValue;
        if (t != null) {
            i = t.hashCode();
        }
        return i3 + i;
    }

    public boolean isDirty() {
        return this.nX;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        T t = this.mValue;
        objArr[0] = t != null ? t.toString() : "None";
        objArr[1] = Long.valueOf(this.nW.getTime());
        objArr[2] = Boolean.toString(this.nY);
        objArr[3] = Boolean.toString(this.nX);
        return String.format(locale, "Value: %s, TimeStamp: %d, Deleted: %s, Dirty: %s", objArr);
    }
}
