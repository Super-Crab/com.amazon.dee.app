package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetNodeResponse;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.deserializer.NodeDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetNodeResponseDeserializer implements JsonDeserializer<GetNodeResponse> {
    public static final JsonDeserializer<GetNodeResponse> INSTANCE = new GetNodeResponseDeserializer();
    private final GetNodeResponseFieldDeserializer mFieldHandler = new GetNodeResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetNodeResponseFieldDeserializer extends NodeDeserializer.NodeFieldDeserializer {
        GetNodeResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.NodeDeserializer.NodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((IEditableNode) obj));
        }

        @Override // com.amazon.clouddrive.model.deserializer.NodeDeserializer.NodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer
        public <U extends IEditableNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof GetNodeResponse) || !"location".equals(str)) {
                return false;
            }
            ((GetNodeResponse) u).setLocation(SimpleDeserializers.deserializeString(jsonParser));
            return true;
        }
    }

    private GetNodeResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetNodeResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetNodeResponse getNodeResponse = new GetNodeResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getNodeResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getNodeResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
