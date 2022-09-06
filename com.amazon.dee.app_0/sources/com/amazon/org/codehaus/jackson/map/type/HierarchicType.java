package com.amazon.org.codehaus.jackson.map.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public class HierarchicType {
    protected final Type _actualType;
    protected final ParameterizedType _genericType;
    protected final Class<?> _rawClass;
    protected HierarchicType _subType;
    protected HierarchicType _superType;

    public HierarchicType(Type type) {
        this._actualType = type;
        if (type instanceof Class) {
            this._rawClass = (Class) type;
            this._genericType = null;
        } else if (type instanceof ParameterizedType) {
            this._genericType = (ParameterizedType) type;
            this._rawClass = (Class) this._genericType.getRawType();
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Type ");
            outline107.append(type.getClass().getName());
            outline107.append(" can not be used to construct HierarchicType");
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    public final ParameterizedType asGeneric() {
        return this._genericType;
    }

    public HierarchicType deepCloneWithoutSubtype() {
        HierarchicType hierarchicType = this._superType;
        HierarchicType deepCloneWithoutSubtype = hierarchicType == null ? null : hierarchicType.deepCloneWithoutSubtype();
        HierarchicType hierarchicType2 = new HierarchicType(this._actualType, this._rawClass, this._genericType, deepCloneWithoutSubtype, null);
        if (deepCloneWithoutSubtype != null) {
            deepCloneWithoutSubtype.setSubType(hierarchicType2);
        }
        return hierarchicType2;
    }

    public final Class<?> getRawClass() {
        return this._rawClass;
    }

    public final HierarchicType getSubType() {
        return this._subType;
    }

    public final HierarchicType getSuperType() {
        return this._superType;
    }

    public final boolean isGeneric() {
        return this._genericType != null;
    }

    public void setSubType(HierarchicType hierarchicType) {
        this._subType = hierarchicType;
    }

    public void setSuperType(HierarchicType hierarchicType) {
        this._superType = hierarchicType;
    }

    public String toString() {
        ParameterizedType parameterizedType = this._genericType;
        if (parameterizedType != null) {
            return parameterizedType.toString();
        }
        return this._rawClass.getName();
    }

    private HierarchicType(Type type, Class<?> cls, ParameterizedType parameterizedType, HierarchicType hierarchicType, HierarchicType hierarchicType2) {
        this._actualType = type;
        this._rawClass = cls;
        this._genericType = parameterizedType;
        this._superType = hierarchicType;
        this._subType = hierarchicType2;
    }
}
