package com.amazon.org.codehaus.jackson.schema;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public interface SchemaAware {
    JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException;
}
