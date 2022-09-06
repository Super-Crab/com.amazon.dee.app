package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public final class AnnotatedConstructor extends AnnotatedWithParams {
    protected final Constructor<?> _constructor;

    public AnnotatedConstructor(Constructor<?> constructor, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        if (constructor != null) {
            this._constructor = constructor;
            return;
        }
        throw new IllegalArgumentException("Null constructor not allowed");
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call1(Object obj) throws Exception {
        return this._constructor.newInstance(obj);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class<?> getDeclaringClass() {
        return this._constructor.getDeclaringClass();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return getRawType();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._constructor;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._constructor.getModifiers();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._constructor.getName();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Class<?> getParameterClass(int i) {
        Class<?>[] parameterTypes = this._constructor.getParameterTypes();
        if (i >= parameterTypes.length) {
            return null;
        }
        return parameterTypes[i];
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public int getParameterCount() {
        return this._constructor.getParameterTypes().length;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Type getParameterType(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        return this._constructor.getDeclaringClass();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._constructor.getTypeParameters());
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot call setValue() on constructor of ");
        outline107.append(getDeclaringClass().getName());
        throw new UnsupportedOperationException(outline107.toString());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[constructor for ");
        outline107.append(getName());
        outline107.append(", annotations: ");
        outline107.append(this._annotations);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: getAnnotated  reason: collision with other method in class */
    public Constructor<?> mo4213getAnnotated() {
        return this._constructor;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: withAnnotations  reason: collision with other method in class */
    public AnnotatedConstructor mo4215withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedConstructor(this._constructor, annotationMap, this._paramAnnotations);
    }
}
