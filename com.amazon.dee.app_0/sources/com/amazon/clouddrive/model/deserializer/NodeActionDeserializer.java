package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.NodeAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class NodeActionDeserializer implements JsonDeserializer<NodeAction> {
    public static NodeActionDeserializer INSTANCE = new NodeActionDeserializer();

    private NodeActionDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public NodeAction mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            return NodeAction.valueOf(jsonParser.getText());
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected string, got ", currentToken), jsonParser.getTokenLocation());
    }
}
