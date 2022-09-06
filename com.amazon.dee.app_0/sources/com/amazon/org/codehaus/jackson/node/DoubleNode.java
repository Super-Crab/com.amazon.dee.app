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
public final class DoubleNode extends NumericNode {
    protected final double _value;

    public DoubleNode(double d) {
        this._value = d;
    }

    public static DoubleNode valueOf(double d) {
        return new DoubleNode(d);
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == DoubleNode.class && ((DoubleNode) obj)._value == this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return getDecimalValue().toBigInteger();
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
        return (long) this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.DOUBLE;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return Double.valueOf(this._value);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this._value);
        return ((int) (doubleToLongBits >> 32)) ^ ((int) doubleToLongBits);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isDouble() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isFloatingPointNumber() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(this._value);
    }
}
