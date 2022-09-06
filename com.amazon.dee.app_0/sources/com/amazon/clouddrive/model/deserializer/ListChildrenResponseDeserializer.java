package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.ListChildrenResponse;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListChildrenResponseDeserializer implements JsonDeserializer<ListChildrenResponse> {
    public static final JsonDeserializer<ListChildrenResponse> INSTANCE = new ListChildrenResponseDeserializer();
    private final ListChildrenResponseFieldDeserializer mFieldHandler = new ListChildrenResponseFieldDeserializer();

    /* loaded from: classes11.dex */
    public static class ListChildrenResponseFieldDeserializer extends PaginatedCloudDriveResponseFieldDeserializer {
        @Override // com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((PaginatedCloudDriveResponse) obj));
        }

        @Override // com.amazon.clouddrive.model.deserializer.PaginatedCloudDriveResponseFieldDeserializer
        public <U extends PaginatedCloudDriveResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof ListChildrenResponse) || !"data".equals(str)) {
                return false;
            }
            ((ListChildrenResponse) u).setData(NodeListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
            return true;
        }
    }

    private ListChildrenResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListChildrenResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListChildrenResponse listChildrenResponse = new ListChildrenResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) listChildrenResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return listChildrenResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
