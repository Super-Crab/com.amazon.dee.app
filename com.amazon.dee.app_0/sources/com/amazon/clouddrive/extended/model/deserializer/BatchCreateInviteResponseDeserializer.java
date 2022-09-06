package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class BatchCreateInviteResponseDeserializer implements JsonDeserializer<BatchCreateInviteResponse> {
    public static final JsonDeserializer<BatchCreateInviteResponse> INSTANCE = new BatchCreateInviteResponseDeserializer();
    private final JsonFieldDeserializer<BatchCreateInviteResponse.BatchCreateInviteResponseBuilder> FIELD_DESERIALIZER = new BatchCreateInviteResponseFieldDeserializer();

    /* loaded from: classes11.dex */
    static class BatchCreateInviteResponseFieldDeserializer implements JsonFieldDeserializer<BatchCreateInviteResponse.BatchCreateInviteResponseBuilder> {
        BatchCreateInviteResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public boolean handleField(JsonParser jsonParser, String str, BatchCreateInviteResponse.BatchCreateInviteResponseBuilder batchCreateInviteResponseBuilder) throws IOException {
            if (MAPWebViewEventHelper.KEY_ERRORS.equals(str)) {
                batchCreateInviteResponseBuilder.errors(BatchCreateInviteResponseErrorListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"invites".equals(str)) {
                return false;
            } else {
                batchCreateInviteResponseBuilder.invites(BatchCreateInviteResponseInviteListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private BatchCreateInviteResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public BatchCreateInviteResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            BatchCreateInviteResponse.BatchCreateInviteResponseBuilder builder = BatchCreateInviteResponse.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.FIELD_DESERIALIZER.handleField(jsonParser, currentName, builder)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return builder.build();
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
