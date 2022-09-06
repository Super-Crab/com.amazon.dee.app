package org.codehaus.jackson.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Modifier;
/* loaded from: classes5.dex */
public abstract class JavaType {
    protected final Class<?> _class;
    protected final int _hashCode;
    protected Object _valueHandler = null;
    protected Object _typeHandler = null;

    protected JavaType(Class<?> cls, int i) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
    }

    protected void _assertSubclass(Class<?> cls, Class<?> cls2) {
        if (this._class.isAssignableFrom(cls)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
        GeneratedOutlineSupport1.outline146(cls, outline107, " is not assignable to ");
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(this._class, outline107));
    }

    protected abstract JavaType _narrow(Class<?> cls);

    protected JavaType _widen(Class<?> cls) {
        return _narrow(cls);
    }

    public JavaType containedType(int i) {
        return null;
    }

    public int containedTypeCount() {
        return 0;
    }

    public String containedTypeName(int i) {
        return null;
    }

    public abstract boolean equals(Object obj);

    public JavaType forcedNarrowBy(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        return this._typeHandler != _narrow.getTypeHandler() ? _narrow.withTypeHandler(this._typeHandler) : _narrow;
    }

    public JavaType getContentType() {
        return null;
    }

    public String getErasedSignature() {
        StringBuilder sb = new StringBuilder(40);
        getErasedSignature(sb);
        return sb.toString();
    }

    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    public String getGenericSignature() {
        StringBuilder sb = new StringBuilder(40);
        getGenericSignature(sb);
        return sb.toString();
    }

    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    public JavaType getKeyType() {
        return null;
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }

    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean isArrayType() {
        return false;
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public boolean isConcrete() {
        return (this._class.getModifiers() & 1536) == 0 || this._class.isPrimitive();
    }

    public abstract boolean isContainerType();

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public boolean isMapLikeType() {
        return false;
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public JavaType narrowBy(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        _assertSubclass(cls, cls2);
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        return this._typeHandler != _narrow.getTypeHandler() ? _narrow.withTypeHandler(this._typeHandler) : _narrow;
    }

    public abstract JavaType narrowContentsBy(Class<?> cls);

    @Deprecated
    public void setValueHandler(Object obj) {
        if (obj != null && this._valueHandler != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trying to reset value handler for type [");
            outline107.append(toString());
            outline107.append("]; old handler of type ");
            outline107.append(this._valueHandler.getClass().getName());
            outline107.append(", new handler of type ");
            outline107.append(obj.getClass().getName());
            throw new IllegalStateException(outline107.toString());
        }
        this._valueHandler = obj;
    }

    public abstract String toCanonical();

    public abstract String toString();

    public JavaType widenBy(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        _assertSubclass(cls2, cls);
        return _widen(cls);
    }

    public abstract JavaType widenContentsBy(Class<?> cls);

    public abstract JavaType withContentTypeHandler(Object obj);

    public JavaType withContentValueHandler(Object obj) {
        getContentType().setValueHandler(obj);
        return this;
    }

    public abstract JavaType withTypeHandler(Object obj);

    public JavaType withValueHandler(Object obj) {
        setValueHandler(obj);
        return this;
    }
}
