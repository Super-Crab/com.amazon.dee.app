package com.esotericsoftware.kryo;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
/* loaded from: classes2.dex */
public abstract class Serializer<T> {
    private boolean acceptsNull;
    private boolean immutable;

    public Serializer() {
    }

    public T copy(Kryo kryo, T t) {
        if (this.immutable) {
            return t;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Serializer does not support copy: ");
        outline107.append(getClass().getName());
        throw new KryoException(outline107.toString());
    }

    public boolean getAcceptsNull() {
        return this.acceptsNull;
    }

    public boolean isImmutable() {
        return this.immutable;
    }

    /* renamed from: read */
    public abstract T mo6848read(Kryo kryo, Input input, Class<T> cls);

    public void setAcceptsNull(boolean z) {
        this.acceptsNull = z;
    }

    public void setGenerics(Kryo kryo, Class[] clsArr) {
    }

    public void setImmutable(boolean z) {
        this.immutable = z;
    }

    public abstract void write(Kryo kryo, Output output, T t);

    public Serializer(boolean z) {
        this.acceptsNull = z;
    }

    public Serializer(boolean z, boolean z2) {
        this.acceptsNull = z;
        this.immutable = z2;
    }
}
