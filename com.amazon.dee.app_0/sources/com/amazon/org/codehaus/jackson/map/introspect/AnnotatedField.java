package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public final class AnnotatedField extends AnnotatedMember {
    protected final Field _field;

    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        super(annotationMap);
        this._field = field;
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._annotations.get(cls);
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    public String getFullName() {
        return getDeclaringClass().getName() + MqttTopic.MULTI_LEVEL_WILDCARD + getName();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._field.getGenericType();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._field;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._field.getModifiers();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._field.getName();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        return this._field.getType();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to setValue() for field ");
            outline107.append(getFullName());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.getMessage());
            throw new IllegalArgumentException(outline107.toString(), e);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[field ");
        outline107.append(getName());
        outline107.append(", annotations: ");
        outline107.append(this._annotations);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: getAnnotated  reason: collision with other method in class */
    public Field mo4213getAnnotated() {
        return this._field;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: withAnnotations  reason: collision with other method in class */
    public AnnotatedField mo4215withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedField(this._field, annotationMap);
    }
}
