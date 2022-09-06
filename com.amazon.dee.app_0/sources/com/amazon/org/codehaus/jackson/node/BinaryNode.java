package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.Base64Variants;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes13.dex */
public final class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    final byte[] _data;

    public BinaryNode(byte[] bArr) {
        this._data = bArr;
    }

    public static BinaryNode valueOf(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BINARY_NODE;
        }
        return new BinaryNode(bArr);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
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
        if (obj == null || obj.getClass() != BinaryNode.class) {
            return false;
        }
        return Arrays.equals(((BinaryNode) obj)._data, this._data);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() {
        return this._data;
    }

    public int hashCode() {
        byte[] bArr = this._data;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isBinary() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeBinary(this._data);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }

    public BinaryNode(byte[] bArr, int i, int i2) {
        if (i == 0 && i2 == bArr.length) {
            this._data = bArr;
            return;
        }
        this._data = new byte[i2];
        System.arraycopy(bArr, i, this._data, 0, i2);
    }

    public static BinaryNode valueOf(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return null;
        }
        if (i2 == 0) {
            return EMPTY_BINARY_NODE;
        }
        return new BinaryNode(bArr, i, i2);
    }
}
