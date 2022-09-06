package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public final class DecimalNode extends NumericNode {
    protected final BigDecimal _value;

    public DecimalNode(BigDecimal bigDecimal) {
        this._value = bigDecimal;
    }

    public static DecimalNode valueOf(BigDecimal bigDecimal) {
        return new DecimalNode(bigDecimal);
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value.toString();
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
        if (obj == null || obj.getClass() != DecimalNode.class) {
            return false;
        }
        return ((DecimalNode) obj)._value.equals(this._value);
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return this._value.toBigInteger();
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public double getDoubleValue() {
        return this._value.doubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public int getIntValue() {
        return this._value.intValue();
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public long getLongValue() {
        return this._value.longValue();
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.BIG_DECIMAL;
    }

    @Override // com.amazon.org.codehaus.jackson.node.NumericNode, com.amazon.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isBigDecimal() {
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
