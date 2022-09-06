package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class en<T> implements ix<en<T>> {
    private final T mValue;

    public en(T t) {
        this.mValue = t;
    }

    @Override // com.amazon.identity.auth.device.ix
    /* renamed from: ej */
    public en<T> ek() {
        try {
            return new en<>(Cif.g(this.mValue));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public T getValue() {
        return this.mValue;
    }
}
