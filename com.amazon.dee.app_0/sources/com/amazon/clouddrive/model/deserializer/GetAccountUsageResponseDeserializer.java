package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetAccountUsageResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetAccountUsageResponseDeserializer implements JsonDeserializer<GetAccountUsageResponse> {
    public static final JsonDeserializer<GetAccountUsageResponse> INSTANCE = new GetAccountUsageResponseDeserializer();
    private final GetAccountUsageResponseFieldDeserializer mFieldHandler = new GetAccountUsageResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetAccountUsageResponseFieldDeserializer implements JsonFieldDeserializer<GetAccountUsageResponse> {
        GetAccountUsageResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetAccountUsageResponse) obj));
        }

        public <U extends GetAccountUsageResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("video".equals(str)) {
                u.setVideo(UsageSummaryDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("other".equals(str)) {
                u.setOther(UsageSummaryDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("lastCalculated".equals(str)) {
                u.setLastCalculated(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("doc".equals(str)) {
                u.setDoc(UsageSummaryDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"photo".equals(str)) {
                return false;
            } else {
                u.setPhoto(UsageSummaryDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GetAccountUsageResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetAccountUsageResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetAccountUsageResponse getAccountUsageResponse = new GetAccountUsageResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getAccountUsageResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getAccountUsageResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
