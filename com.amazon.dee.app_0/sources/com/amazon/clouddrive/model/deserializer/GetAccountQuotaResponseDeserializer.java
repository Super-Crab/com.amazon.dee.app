package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetAccountQuotaResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetAccountQuotaResponseDeserializer implements JsonDeserializer<GetAccountQuotaResponse> {
    public static final JsonDeserializer<GetAccountQuotaResponse> INSTANCE = new GetAccountQuotaResponseDeserializer();
    private final GetAccountQuotaResponseFieldDeserializer mFieldHandler = new GetAccountQuotaResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetAccountQuotaResponseFieldDeserializer implements JsonFieldDeserializer<GetAccountQuotaResponse> {
        GetAccountQuotaResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetAccountQuotaResponse) obj));
        }

        public <U extends GetAccountQuotaResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("quota".equals(str)) {
                u.setQuota(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("lastCalculated".equals(str)) {
                u.setLastCalculated(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("available".equals(str)) {
                u.setAvailable(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("benefits".equals(str)) {
                u.setBenefits(CloudDriveBenefitListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("grants".equals(str)) {
                u.setGrants(CloudDriveGrantListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"plans".equals(str)) {
                return false;
            } else {
                u.setPlans(SimpleListDeserializers.STRING_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GetAccountQuotaResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetAccountQuotaResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetAccountQuotaResponse getAccountQuotaResponse = new GetAccountQuotaResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getAccountQuotaResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getAccountQuotaResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
