package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
/* loaded from: classes13.dex */
public class StdKeySerializer extends SerializerBase<Object> {
    static final StdKeySerializer instace = new StdKeySerializer();

    public StdKeySerializer() {
        super(Object.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return createSchemaNode("string");
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (obj instanceof Date) {
            serializerProvider.defaultSerializeDateKey((Date) obj, jsonGenerator);
        } else {
            jsonGenerator.writeFieldName(obj.toString());
        }
    }
}
