package com.amazon.org.codehaus.jackson.map.deser.impl;

import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
/* loaded from: classes13.dex */
public class ValueInjector extends BeanProperty.Std {
    protected final Object _valueId;

    public ValueInjector(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember, Object obj) {
        super(str, javaType, annotations, annotatedMember);
        this._valueId = obj;
    }

    public Object findValue(DeserializationContext deserializationContext, Object obj) {
        return deserializationContext.findInjectableValue(this._valueId, this, obj);
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        this._member.setValue(obj, findValue(deserializationContext, obj));
    }
}
