package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ResubscribeResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ResubscribeResponseDeserializer implements JsonDeserializer<ResubscribeResponse> {
    public static final JsonDeserializer<ResubscribeResponse> INSTANCE = new ResubscribeResponseDeserializer();
    private final ResubscribeResponseFieldDeserializer mFieldHandler = new ResubscribeResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ResubscribeResponseFieldDeserializer implements JsonFieldDeserializer<ResubscribeResponse> {
        ResubscribeResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ResubscribeResponse) obj));
        }

        public <U extends ResubscribeResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("subscription".equals(str)) {
                u.setSubscription(CloudDriveSubscriptionDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
            return false;
        }
    }

    private ResubscribeResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ResubscribeResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ResubscribeResponse resubscribeResponse = new ResubscribeResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) resubscribeResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return resubscribeResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
