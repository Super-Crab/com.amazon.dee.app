package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CreateBatchLinkResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CreateBatchLinkDeserializer implements JsonDeserializer<CreateBatchLinkResponse> {
    public static final JsonDeserializer<CreateBatchLinkResponse> INSTANCE = new CreateBatchLinkDeserializer();
    private final CreateBatchLinkResponseFieldDeserializer mFieldHandler = new CreateBatchLinkResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CreateBatchLinkResponseFieldDeserializer implements JsonFieldDeserializer<CreateBatchLinkResponse> {
        CreateBatchLinkResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CreateBatchLinkResponse) obj));
        }

        public <U extends CreateBatchLinkResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("status".equals(str)) {
                u.setStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("expires".equals(str)) {
                u.setExpiryTime(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"links".equals(str)) {
                return false;
            } else {
                u.setLink(BatchLinkDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private CreateBatchLinkDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CreateBatchLinkResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CreateBatchLinkResponse createBatchLinkResponse = new CreateBatchLinkResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) createBatchLinkResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return createBatchLinkResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of array, got ", currentToken), jsonParser.getTokenLocation());
    }
}
