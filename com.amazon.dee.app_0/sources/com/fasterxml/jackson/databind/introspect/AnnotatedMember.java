package com.fasterxml.jackson.databind.introspect;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Collections;
/* loaded from: classes2.dex */
public abstract class AnnotatedMember extends Annotated implements Serializable {
    private static final long serialVersionUID = 1;
    protected final transient AnnotationMap _annotations;
    protected final transient TypeResolutionContext _typeContext;

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedMember(TypeResolutionContext typeResolutionContext, AnnotationMap annotationMap) {
        this._typeContext = typeResolutionContext;
        this._annotations = annotationMap;
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    @Deprecated
    public Iterable<Annotation> annotations() {
        AnnotationMap annotationMap = this._annotations;
        if (annotationMap == null) {
            return Collections.emptyList();
        }
        return annotationMap.annotations();
    }

    public final void fixAccess(boolean z) {
        Member mo7118getMember = mo7118getMember();
        if (mo7118getMember != null) {
            ClassUtil.checkAndFixAccess(mo7118getMember, z);
        }
    }

    public AnnotationMap getAllAnnotations() {
        return this._annotations;
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        AnnotationMap annotationMap = this._annotations;
        if (annotationMap == null) {
            return null;
        }
        return (A) annotationMap.get(cls);
    }

    public abstract Class<?> getDeclaringClass();

    public String getFullName() {
        return getDeclaringClass().getName() + MqttTopic.MULTI_LEVEL_WILDCARD + getName();
    }

    /* renamed from: getMember */
    public abstract Member mo7118getMember();

    @Deprecated
    public TypeResolutionContext getTypeContext() {
        return this._typeContext;
    }

    public abstract Object getValue(Object obj) throws UnsupportedOperationException, IllegalArgumentException;

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public final boolean hasAnnotation(Class<?> cls) {
        AnnotationMap annotationMap = this._annotations;
        if (annotationMap == null) {
            return false;
        }
        return annotationMap.has(cls);
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
        AnnotationMap annotationMap = this._annotations;
        if (annotationMap == null) {
            return false;
        }
        return annotationMap.hasOneOf(clsArr);
    }

    public abstract void setValue(Object obj, Object obj2) throws UnsupportedOperationException, IllegalArgumentException;

    /* renamed from: withAnnotations */
    public abstract Annotated mo7120withAnnotations(AnnotationMap annotationMap);

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedMember(AnnotatedMember annotatedMember) {
        this._typeContext = annotatedMember._typeContext;
        this._annotations = annotatedMember._annotations;
    }
}
