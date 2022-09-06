package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetChangesResponse;
import com.amazon.clouddrive.model.IGetChangesResponse;
import com.amazon.clouddrive.model.Node;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetChangesResponseDeserializer implements JsonDeserializer<GetChangesResponse> {
    public static final JsonDeserializer<GetChangesResponse> INSTANCE = new GetChangesResponseDeserializer();
    private final GetChangesResponseFieldDeserializer mFieldHandler = new GetChangesResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetChangesResponseFieldDeserializer implements JsonFieldDeserializer<IGetChangesResponse<Node>> {
        GetChangesResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((IGetChangesResponse) obj));
        }

        public <U extends IGetChangesResponse<Node>> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("reset".equals(str)) {
                u.setReset(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                return true;
            } else if ("checkpoint".equals(str)) {
                u.setCheckpoint(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"nodes".equals(str)) {
                return false;
            } else {
                u.setNodes(NodeListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GetChangesResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetChangesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetChangesResponse getChangesResponse = new GetChangesResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getChangesResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getChangesResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
