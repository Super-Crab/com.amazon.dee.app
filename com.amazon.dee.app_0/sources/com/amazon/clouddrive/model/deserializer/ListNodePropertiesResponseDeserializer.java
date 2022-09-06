package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.ListNodePropertiesResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListNodePropertiesResponseDeserializer implements JsonDeserializer<ListNodePropertiesResponse> {
    public static final JsonDeserializer<ListNodePropertiesResponse> INSTANCE = new ListNodePropertiesResponseDeserializer();
    private final ListNodePropertiesResponseFieldDeserializer mFieldHandler = new ListNodePropertiesResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ListNodePropertiesResponseFieldDeserializer implements JsonFieldDeserializer<ListNodePropertiesResponse> {
        ListNodePropertiesResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ListNodePropertiesResponse) obj));
        }

        public <U extends ListNodePropertiesResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("data".equals(str)) {
                u.setData(PropertyMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"count".equals(str)) {
                return false;
            } else {
                u.setCount(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            }
        }
    }

    private ListNodePropertiesResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListNodePropertiesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListNodePropertiesResponse listNodePropertiesResponse = new ListNodePropertiesResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) listNodePropertiesResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return listNodePropertiesResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
