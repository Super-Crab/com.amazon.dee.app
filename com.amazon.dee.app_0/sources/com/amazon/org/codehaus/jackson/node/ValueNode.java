package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class ValueNode extends BaseJsonNode {
    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isValueNode() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        typeSerializer.writeTypePrefixForScalar(this, jsonGenerator);
        serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(this, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        return asText();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return MissingNode.getInstance();
    }
}
