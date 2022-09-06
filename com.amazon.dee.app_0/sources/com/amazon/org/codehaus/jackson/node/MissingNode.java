package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import java.io.IOException;
/* loaded from: classes13.dex */
public final class MissingNode extends BaseJsonNode {
    private static final MissingNode instance = new MissingNode();

    private MissingNode() {
    }

    public static MissingNode getInstance() {
        return instance;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return z;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return d;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return i;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return j;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return "";
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isMissingNode() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        return "";
    }
}
