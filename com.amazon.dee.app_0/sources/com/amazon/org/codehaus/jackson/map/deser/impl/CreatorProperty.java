package com.amazon.org.codehaus.jackson.map.deser.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.annotation.Annotation;
/* loaded from: classes13.dex */
public class CreatorProperty extends SettableBeanProperty {
    protected final AnnotatedParameter _annotated;
    protected final Object _injectableValueId;

    public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj) {
        super(str, javaType, typeDeserializer, annotations);
        this._annotated = annotatedParameter;
        this._propertyIndex = i;
        this._injectableValueId = obj;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object findInjectableValue(DeserializationContext deserializationContext, Object obj) {
        Object obj2 = this._injectableValueId;
        if (obj2 != null) {
            return deserializationContext.findInjectableValue(obj2, this, obj);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Property '");
        outline107.append(getName());
        outline107.append("' (type ");
        outline107.append(CreatorProperty.class.getName());
        outline107.append(") has no injectable value id configured");
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        AnnotatedParameter annotatedParameter = this._annotated;
        if (annotatedParameter == null) {
            return null;
        }
        return (A) annotatedParameter.getAnnotation(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
    public Object getInjectableValueId() {
        return this._injectableValueId;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty, com.amazon.org.codehaus.jackson.map.BeanProperty
    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        set(obj, findInjectableValue(deserializationContext, obj));
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
    public void set(Object obj, Object obj2) throws IOException {
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
    /* renamed from: withValueDeserializer */
    public /* bridge */ /* synthetic */ SettableBeanProperty mo4140withValueDeserializer(JsonDeserializer jsonDeserializer) {
        return mo4140withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty
    /* renamed from: withValueDeserializer  reason: collision with other method in class */
    public CreatorProperty mo4140withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        return new CreatorProperty(this, jsonDeserializer);
    }

    protected CreatorProperty(CreatorProperty creatorProperty, JsonDeserializer<Object> jsonDeserializer) {
        super(creatorProperty, jsonDeserializer);
        this._annotated = creatorProperty._annotated;
        this._injectableValueId = creatorProperty._injectableValueId;
    }
}
