package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetPreferencesResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetPreferencesResponseDeserializer extends AbstractDeserializer<GetPreferencesResponse, GetPreferencesResponse> {
    public static final JsonDeserializer<GetPreferencesResponse> INSTANCE = new GetPreferencesResponseDeserializer();

    /* loaded from: classes11.dex */
    static class GetPreferencesResponseFieldDeserializer implements JsonFieldDeserializer<GetPreferencesResponse> {
        GetPreferencesResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetPreferencesResponse) obj));
        }

        public <U extends GetPreferencesResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("familyName".equals(str)) {
                u.setFamilyName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("name".equals(str)) {
                u.setName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("avatarNodeId".equals(str)) {
                u.setAvatarNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("autoAddContentToFamilyArchive".equals(str)) {
                u.setAutoAddContentToFamilyArchive(SimpleDeserializers.deserializeBoolean(jsonParser).booleanValue());
                return true;
            } else if ("recognitionEnabledFamily".equals(str)) {
                u.setRecognitionEnabledFamily(SimpleDeserializers.deserializeBoolean(jsonParser).booleanValue());
                return true;
            } else if ("recognitionEnabled".equals(str)) {
                u.setRecognitionEnabled(SimpleDeserializers.deserializeBoolean(jsonParser).booleanValue());
                return true;
            } else if (!"recognitionEnabledPersonal".equals(str)) {
                return false;
            } else {
                u.setRecognitionEnabledPersonal(SimpleDeserializers.deserializeBoolean(jsonParser).booleanValue());
                return true;
            }
        }
    }

    private GetPreferencesResponseDeserializer() {
        super(new GetPreferencesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetPreferencesResponse mo3156createValue() {
        return new GetPreferencesResponse();
    }
}
