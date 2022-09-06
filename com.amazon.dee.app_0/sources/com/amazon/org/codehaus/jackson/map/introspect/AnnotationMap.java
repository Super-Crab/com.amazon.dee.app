package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.HashMap;
/* loaded from: classes13.dex */
public final class AnnotationMap implements Annotations {
    protected HashMap<Class<? extends Annotation>, Annotation> _annotations;

    public AnnotationMap() {
    }

    public static AnnotationMap merge(AnnotationMap annotationMap, AnnotationMap annotationMap2) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        HashMap<Class<? extends Annotation>, Annotation> hashMap2;
        if (annotationMap == null || (hashMap = annotationMap._annotations) == null || hashMap.isEmpty()) {
            return annotationMap2;
        }
        if (annotationMap2 == null || (hashMap2 = annotationMap2._annotations) == null || hashMap2.isEmpty()) {
            return annotationMap;
        }
        HashMap hashMap3 = new HashMap();
        for (Annotation annotation : annotationMap2._annotations.values()) {
            hashMap3.put(annotation.annotationType(), annotation);
        }
        for (Annotation annotation2 : annotationMap._annotations.values()) {
            hashMap3.put(annotation2.annotationType(), annotation2);
        }
        return new AnnotationMap(hashMap3);
    }

    protected final void _add(Annotation annotation) {
        if (this._annotations == null) {
            this._annotations = new HashMap<>();
        }
        this._annotations.put(annotation.annotationType(), annotation);
    }

    public void add(Annotation annotation) {
        _add(annotation);
    }

    public void addIfNotPresent(Annotation annotation) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this._annotations;
        if (hashMap == null || !hashMap.containsKey(annotation.annotationType())) {
            _add(annotation);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.util.Annotations
    public <A extends Annotation> A get(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this._annotations;
        if (hashMap == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.util.Annotations
    public int size() {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this._annotations;
        if (hashMap == null) {
            return 0;
        }
        return hashMap.size();
    }

    public String toString() {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this._annotations;
        return hashMap == null ? "[null]" : hashMap.toString();
    }

    private AnnotationMap(HashMap<Class<? extends Annotation>, Annotation> hashMap) {
        this._annotations = hashMap;
    }
}
