package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public final class AnnotatedMethod extends AnnotatedWithParams {
    protected final Method _method;
    protected Class<?>[] _paramTypes;

    public AnnotatedMethod(Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        this._method = method;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call() throws Exception {
        return this._method.invoke(null, new Object[0]);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call1(Object obj) throws Exception {
        return this._method.invoke(null, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public String getFullName() {
        return getDeclaringClass().getName() + MqttTopic.MULTI_LEVEL_WILDCARD + getName() + "(" + getParameterCount() + " params)";
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._method;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._method.getModifiers();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._method.getName();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Class<?> getParameterClass(int i) {
        Class<?>[] parameterTypes = this._method.getParameterTypes();
        if (i >= parameterTypes.length) {
            return null;
        }
        return parameterTypes[i];
    }

    public Class<?>[] getParameterClasses() {
        if (this._paramTypes == null) {
            this._paramTypes = this._method.getParameterTypes();
        }
        return this._paramTypes;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public int getParameterCount() {
        return getParameterTypes().length;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Type getParameterType(int i) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Type[] getParameterTypes() {
        return this._method.getGenericParameterTypes();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._method.getTypeParameters());
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._method.invoke(obj, obj2);
        } catch (IllegalAccessException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to setValue() with method ");
            outline107.append(getFullName());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.getMessage());
            throw new IllegalArgumentException(outline107.toString(), e);
        } catch (InvocationTargetException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed to setValue() with method ");
            outline1072.append(getFullName());
            outline1072.append(RealTimeTextConstants.COLON_SPACE);
            outline1072.append(e2.getMessage());
            throw new IllegalArgumentException(outline1072.toString(), e2);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[method ");
        outline107.append(getName());
        outline107.append(", annotations: ");
        outline107.append(this._annotations);
        outline107.append("]");
        return outline107.toString();
    }

    public AnnotatedMethod withMethod(Method method) {
        return new AnnotatedMethod(method, this._annotations, this._paramAnnotations);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call(Object[] objArr) throws Exception {
        return this._method.invoke(null, objArr);
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: getAnnotated  reason: collision with other method in class */
    public Method mo4213getAnnotated() {
        return this._method;
    }

    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    /* renamed from: withAnnotations  reason: collision with other method in class */
    public AnnotatedMethod mo4215withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedMethod(this._method, annotationMap, this._paramAnnotations);
    }
}
