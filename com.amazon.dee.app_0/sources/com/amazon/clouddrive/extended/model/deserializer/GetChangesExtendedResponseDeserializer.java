package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetChangesExtendedResponse;
import com.amazon.clouddrive.extended.model.NodeExtended;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetChangesExtendedResponseDeserializer implements JsonDeserializer<GetChangesExtendedResponse> {
    public static final JsonDeserializer<GetChangesExtendedResponse> INSTANCE = new GetChangesExtendedResponseDeserializer();
    private final GetChangesExtendedResponseFieldDeserializer mFieldHandler = new GetChangesExtendedResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetChangesExtendedResponseFieldDeserializer implements JsonFieldDeserializer<GetChangesExtendedResponse> {
        private final ListDeserializer<NodeExtended> mListDeserializer = new ListDeserializer<>(NodeExtendedDeserializer.INSTANCE);

        GetChangesExtendedResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetChangesExtendedResponse) obj));
        }

        public <U extends GetChangesExtendedResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("reset".equals(str)) {
                u.setReset(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                return true;
            } else if ("checkpoint".equals(str)) {
                u.setCheckpoint(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"nodes".equals(str)) {
                return false;
            } else {
                u.setNodes(this.mListDeserializer.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GetChangesExtendedResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetChangesExtendedResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetChangesExtendedResponse getChangesExtendedResponse = new GetChangesExtendedResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getChangesExtendedResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getChangesExtendedResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
