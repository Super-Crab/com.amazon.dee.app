package com.amazon.org.codehaus.jackson.node;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
/* loaded from: classes13.dex */
public final class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return 0;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return 0L;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return "null";
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isNull() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }
}
