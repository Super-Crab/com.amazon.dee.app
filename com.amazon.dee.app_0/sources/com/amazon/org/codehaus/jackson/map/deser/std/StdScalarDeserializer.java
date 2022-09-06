package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public StdScalarDeserializer(Class<?> cls) {
        super(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
    }

    protected StdScalarDeserializer(JavaType javaType) {
        super(javaType);
    }
}
