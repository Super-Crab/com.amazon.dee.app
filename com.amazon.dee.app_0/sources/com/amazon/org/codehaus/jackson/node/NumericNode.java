package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public abstract class NumericNode extends ValueNode {
    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble() {
        return getDoubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt() {
        return getIntValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong() {
        return getLongValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract String asText();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract BigInteger getBigIntegerValue();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract BigDecimal getDecimalValue();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract double getDoubleValue();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract int getIntValue();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract long getLongValue();

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public abstract JsonParser.NumberType getNumberType();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public abstract Number getNumberValue();

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public final boolean isNumber() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return getDoubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return getIntValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return getLongValue();
    }
}
