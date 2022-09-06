package com.amazon.org.codehaus.jackson.node;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import java.io.IOException;
/* loaded from: classes13.dex */
public final class BooleanNode extends ValueNode {
    public static final BooleanNode TRUE = new BooleanNode();
    public static final BooleanNode FALSE = new BooleanNode();

    private BooleanNode() {
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean asBoolean() {
        return this == TRUE;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        if (this == TRUE) {
            return 1.0d;
        }
        return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return this == TRUE ? 1 : 0;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return this == TRUE ? 1L : 0L;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String asText() {
        return this == TRUE ? "true" : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ValueNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return this == TRUE ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean getBooleanValue() {
        return this == TRUE;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isBoolean() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeBoolean(this == TRUE);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return this == TRUE;
    }
}
