package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.io.NumberOutput;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public final class LongNode extends NumericNode {
    final long _value;

    public LongNode(long j) {
        this._value = j;
    }

    public static LongNode valueOf(long j) {
        return new LongNode(j);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == LongNode.class && ((LongNode) obj)._value == this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public double getDoubleValue() {
        return this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public int getIntValue() {
        return (int) this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public long getLongValue() {
        return this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.LONG;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return Long.valueOf(this._value);
    }

    public int hashCode() {
        long j = this._value;
        return ((int) (j >> 32)) ^ ((int) j);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isIntegralNumber() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isLong() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(this._value);
    }
}
