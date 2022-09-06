package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetFamilyResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetFamilyResponseDeserializer extends AbstractDeserializer<GetFamilyResponse, GetFamilyResponse> {
    public static final JsonDeserializer<GetFamilyResponse> INSTANCE = new GetFamilyResponseDeserializer();

    /* loaded from: classes11.dex */
    static class GetFamilyResponseFieldDeserializer implements JsonFieldDeserializer<GetFamilyResponse> {
        GetFamilyResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetFamilyResponse) obj));
        }

        public <U extends GetFamilyResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("familyId".equals(str)) {
                u.setFamilyId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("adminId".equals(str)) {
                u.setAdminId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("callerId".equals(str)) {
                u.setCallerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("members".equals(str)) {
                u.setMembers(CustomerInfoDeserializer.LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            } else if (!"familyName".equals(str)) {
                return false;
            } else {
                u.setFamilyName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetFamilyResponseDeserializer() {
        super(new GetFamilyResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetFamilyResponse mo3156createValue() {
        return new GetFamilyResponse();
    }
}
