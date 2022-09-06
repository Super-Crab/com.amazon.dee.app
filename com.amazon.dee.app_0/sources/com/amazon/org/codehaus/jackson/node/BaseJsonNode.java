package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.JsonSerializableWithType;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import java.io.IOException;
import java.util.List;
/* loaded from: classes13.dex */
public abstract class BaseJsonNode extends JsonNode implements JsonSerializableWithType {
    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    /* renamed from: findParent  reason: collision with other method in class */
    public ObjectNode mo4258findParent(String str) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public final JsonNode findPath(String str) {
        JsonNode findValue = findValue(str);
        return findValue == null ? MissingNode.getInstance() : findValue;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return null;
    }

    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;

    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException;

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonParser traverse() {
        return new TreeTraversingParser(this);
    }
}
