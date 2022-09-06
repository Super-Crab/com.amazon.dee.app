package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public abstract class Annotated {
    protected abstract AnnotationMap getAllAnnotations();

    /* renamed from: getAnnotated */
    public abstract AnnotatedElement mo4213getAnnotated();

    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public abstract Type getGenericType();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getModifiers();

    public abstract String getName();

    public abstract Class<?> getRawType();

    public JavaType getType(TypeBindings typeBindings) {
        return typeBindings.resolveType(getGenericType());
    }

    public final <A extends Annotation> boolean hasAnnotation(Class<A> cls) {
        return getAnnotation(cls) != null;
    }

    public final boolean isPublic() {
        return Modifier.isPublic(getModifiers());
    }

    /* renamed from: withAnnotations */
    public abstract Annotated mo4215withAnnotations(AnnotationMap annotationMap);

    public final Annotated withFallBackAnnotationsFrom(Annotated annotated) {
        return mo4215withAnnotations(AnnotationMap.merge(getAllAnnotations(), annotated.getAllAnnotations()));
    }
}
