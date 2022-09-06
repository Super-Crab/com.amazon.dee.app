package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListCollectionNodesResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListCollectionNodesResponseDeserializer implements JsonDeserializer<ListCollectionNodesResponse> {
    public static final JsonDeserializer<ListCollectionNodesResponse> INSTANCE = new ListCollectionNodesResponseDeserializer();
    private final ListCollectionNodesFieldDeserializer mFieldHandler = new ListCollectionNodesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ListCollectionNodesFieldDeserializer implements JsonFieldDeserializer<ListCollectionNodesResponse> {
        ListCollectionNodesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ListCollectionNodesResponse) obj));
        }

        public <U extends ListCollectionNodesResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("nextToken".equals(str)) {
                u.setNextToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if (!"groupNodes".equals(str)) {
                return false;
            } else {
                u.setGroupNodes(GroupNodeDeserializer.LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private ListCollectionNodesResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListCollectionNodesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListCollectionNodesResponse listCollectionNodesResponse = new ListCollectionNodesResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) listCollectionNodesResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return listCollectionNodesResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
