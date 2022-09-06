package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public abstract class ScalarSerializerBase<T> extends SerializerBase<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public ScalarSerializerBase(Class<T> cls) {
        super(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return createSchemaNode("string", true);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForScalar(t, jsonGenerator);
        serialize(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(t, jsonGenerator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ScalarSerializerBase(Class<?> cls, boolean z) {
        super(cls);
    }
}
