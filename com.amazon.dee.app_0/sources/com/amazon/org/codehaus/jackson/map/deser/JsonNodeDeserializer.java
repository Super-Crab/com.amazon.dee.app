package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.node.ArrayNode;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import java.io.IOException;
@Deprecated
/* loaded from: classes13.dex */
public class JsonNodeDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.JsonNodeDeserializer {
    @Deprecated
    public static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    @Deprecated
    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeAny(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    @Deprecated
    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    @Deprecated
    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }
}
