package com.fasterxml.jackson.databind.type;

import java.io.Serializable;
/* loaded from: classes2.dex */
public final class ClassKey implements Comparable<ClassKey>, Serializable {
    private static final long serialVersionUID = 1;
    private Class<?> _class;
    private String _className;
    private int _hashCode;

    public ClassKey() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == ClassKey.class && ((ClassKey) obj)._class == this._class;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public void reset(Class<?> cls) {
        this._class = cls;
        this._className = cls.getName();
        this._hashCode = this._className.hashCode();
    }

    public String toString() {
        return this._className;
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassKey classKey) {
        return this._className.compareTo(classKey._className);
    }

    public ClassKey(Class<?> cls) {
        this._class = cls;
        this._className = cls.getName();
        this._hashCode = this._className.hashCode();
    }
}
