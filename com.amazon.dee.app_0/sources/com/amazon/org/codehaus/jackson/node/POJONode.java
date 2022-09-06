package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
/* loaded from: classes13.dex */
public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        Object obj = this._value;
        return (obj == null || !(obj instanceof Boolean)) ? z : ((Boolean) obj).booleanValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).doubleValue() : d;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).longValue() : j;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        Object obj = this._value;
        return obj == null ? "null" : obj.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != POJONode.class) {
            return false;
        }
        POJONode pOJONode = (POJONode) obj;
        Object obj2 = this._value;
        if (obj2 != null) {
            return obj2.equals(pOJONode._value);
        }
        return pOJONode._value == null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() throws IOException {
        Object obj = this._value;
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        return super.getBinaryValue();
    }

    public Object getPojo() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isPojo() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        Object obj = this._value;
        if (obj == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(obj);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        return String.valueOf(this._value);
    }
}
