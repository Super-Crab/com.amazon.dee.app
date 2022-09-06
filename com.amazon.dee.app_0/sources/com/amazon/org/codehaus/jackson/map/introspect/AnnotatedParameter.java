package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public final class AnnotatedParameter extends AnnotatedMember {
    protected final int _index;
    protected final AnnotatedWithParams _owner;
    protected final Type _type;

    public AnnotatedParameter(AnnotatedWithParams annotatedWithParams, Type type, AnnotationMap annotationMap, int i) {
        super(annotationMap);
        this._owner = annotatedWithParams;
        this._type = type;
        this._index = i;
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: getAnnotated */
    public AnnotatedElement mo4213getAnnotated() {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._annotations.get(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class<?> getDeclaringClass() {
        return this._owner.getDeclaringClass();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._type;
    }

    public int getIndex() {
        return this._index;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._owner.getMember();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._owner.getModifiers();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return "";
    }

    public AnnotatedWithParams getOwner() {
        return this._owner;
    }

    public Type getParameterType() {
        return this._type;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        Type type = this._type;
        if (type instanceof Class) {
            return (Class) type;
        }
        return TypeFactory.defaultInstance().constructType(this._type).getRawClass();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot call setValue() on constructor parameter of ");
        outline107.append(getDeclaringClass().getName());
        throw new UnsupportedOperationException(outline107.toString());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[parameter #");
        outline107.append(getIndex());
        outline107.append(", annotations: ");
        outline107.append(this._annotations);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: withAnnotations  reason: collision with other method in class */
    public AnnotatedParameter mo4215withAnnotations(AnnotationMap annotationMap) {
        return annotationMap == this._annotations ? this : this._owner.replaceParameterAnnotations(this._index, annotationMap);
    }
}
