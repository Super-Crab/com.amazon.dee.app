package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CloudDriveBenefit;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CloudDriveBenefitDeserializer implements JsonDeserializer<CloudDriveBenefit> {
    public static final JsonDeserializer<CloudDriveBenefit> INSTANCE = new CloudDriveBenefitDeserializer();
    private final CloudDriveBenefitFieldDeserializer mFieldHandler = new CloudDriveBenefitFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CloudDriveBenefitFieldDeserializer implements JsonFieldDeserializer<CloudDriveBenefit> {
        CloudDriveBenefitFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CloudDriveBenefit) obj));
        }

        public <U extends CloudDriveBenefit> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("expiration".equals(str)) {
                u.setExpiration(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"benefit".equals(str)) {
                return false;
            } else {
                u.setBenefit(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private CloudDriveBenefitDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CloudDriveBenefit mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CloudDriveBenefit cloudDriveBenefit = new CloudDriveBenefit();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) cloudDriveBenefit)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return cloudDriveBenefit;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
