package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetProfileResponse;
import com.amazon.clouddrive.extended.model.Profile;
import com.amazon.clouddrive.extended.model.deserializer.ProfileDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetProfileResponseDeserializer extends AbstractDeserializer<Profile, GetProfileResponse> {
    public static final JsonDeserializer<GetProfileResponse> INSTANCE = new GetProfileResponseDeserializer();

    /* loaded from: classes11.dex */
    static class GetProfileResponseFieldDeserializer extends ProfileDeserializer.ProfileFieldDeserializer {
        GetProfileResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.ProfileDeserializer.ProfileFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Profile) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.ProfileDeserializer.ProfileFieldDeserializer
        public <U extends Profile> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            return super.handleField(jsonParser, str, (String) u);
        }
    }

    public GetProfileResponseDeserializer() {
        super(new GetProfileResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetProfileResponse mo3156createValue() {
        return new GetProfileResponse();
    }
}
