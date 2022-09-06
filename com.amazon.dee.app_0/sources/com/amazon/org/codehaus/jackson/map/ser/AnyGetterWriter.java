package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Method;
import java.util.Map;
/* loaded from: classes13.dex */
public class AnyGetterWriter {
    protected final Method _anyGetter;
    protected final com.amazon.org.codehaus.jackson.map.ser.std.MapSerializer _serializer;

    public AnyGetterWriter(AnnotatedMethod annotatedMethod, com.amazon.org.codehaus.jackson.map.ser.std.MapSerializer mapSerializer) {
        this._anyGetter = annotatedMethod.mo4213getAnnotated();
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object invoke = this._anyGetter.invoke(obj, new Object[0]);
        if (invoke == null) {
            return;
        }
        if (invoke instanceof Map) {
            this._serializer.serializeFields((Map) invoke, jsonGenerator, serializerProvider);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value returned by 'any-getter' (");
        outline107.append(this._anyGetter.getName());
        outline107.append("()) not java.util.Map but ");
        outline107.append(invoke.getClass().getName());
        throw new JsonMappingException(outline107.toString());
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        this._serializer.resolve(serializerProvider);
    }
}
