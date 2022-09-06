package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
/* loaded from: classes13.dex */
public abstract class AnnotatedWithParams extends AnnotatedMember {
    protected final AnnotationMap[] _paramAnnotations;

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedWithParams(AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap);
        this._paramAnnotations = annotationMapArr;
    }

    public final void addIfNotPresent(Annotation annotation) {
        this._annotations.addIfNotPresent(annotation);
    }

    public final void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    public final void addOrOverrideParam(int i, Annotation annotation) {
        AnnotationMap annotationMap = this._paramAnnotations[i];
        if (annotationMap == null) {
            annotationMap = new AnnotationMap();
            this._paramAnnotations[i] = annotationMap;
        }
        annotationMap.add(annotation);
    }

    public abstract Object call() throws Exception;

    public abstract Object call(Object[] objArr) throws Exception;

    public abstract Object call1(Object obj) throws Exception;

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._annotations.get(cls);
    }

    public final int getAnnotationCount() {
        return this._annotations.size();
    }

    public final AnnotatedParameter getParameter(int i) {
        return new AnnotatedParameter(this, getParameterType(i), this._paramAnnotations[i], i);
    }

    public final AnnotationMap getParameterAnnotations(int i) {
        AnnotationMap[] annotationMapArr = this._paramAnnotations;
        if (annotationMapArr == null || i < 0 || i > annotationMapArr.length) {
            return null;
        }
        return annotationMapArr[i];
    }

    public abstract Class<?> getParameterClass(int i);

    public abstract int getParameterCount();

    public abstract Type getParameterType(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType getType(TypeBindings typeBindings, TypeVariable<?>[] typeVariableArr) {
        if (typeVariableArr != null && typeVariableArr.length > 0) {
            typeBindings = typeBindings.childInstance();
            for (TypeVariable<?> typeVariable : typeVariableArr) {
                typeBindings._addPlaceholder(typeVariable.getName());
                Type type = typeVariable.getBounds()[0];
                typeBindings.addBinding(typeVariable.getName(), type == null ? TypeFactory.unknownType() : typeBindings.resolveType(type));
            }
        }
        return typeBindings.resolveType(getGenericType());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedParameter replaceParameterAnnotations(int i, AnnotationMap annotationMap) {
        this._paramAnnotations[i] = annotationMap;
        return getParameter(i);
    }

    public final JavaType resolveParameterType(int i, TypeBindings typeBindings) {
        return typeBindings.resolveType(getParameterType(i));
    }
}
