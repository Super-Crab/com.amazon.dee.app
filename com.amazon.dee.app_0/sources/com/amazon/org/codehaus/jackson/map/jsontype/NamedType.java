package com.amazon.org.codehaus.jackson.map.jsontype;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class NamedType {
    protected final Class<?> _class;
    protected final int _hashCode;
    protected String _name;

    public NamedType(Class<?> cls) {
        this(cls, null);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == NamedType.class && this._class == ((NamedType) obj)._class;
    }

    public String getName() {
        return this._name;
    }

    public Class<?> getType() {
        return this._class;
    }

    public boolean hasName() {
        return this._name != null;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public void setName(String str) {
        if (str == null || str.length() == 0) {
            str = null;
        }
        this._name = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[NamedType, class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", name: ");
        return GeneratedOutlineSupport1.outline91(outline107, this._name == null ? "null" : GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'"), this._name, "'"), "]");
    }

    public NamedType(Class<?> cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode();
        setName(str);
    }
}
