package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.UpdateFamilyArchiveResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class UpdateFamilyArchiveResponseDeserializer extends AbstractDeserializer<UpdateFamilyArchiveResponse, UpdateFamilyArchiveResponse> {
    public static final JsonDeserializer<UpdateFamilyArchiveResponse> INSTANCE = new UpdateFamilyArchiveResponseDeserializer();

    /* loaded from: classes11.dex */
    static class UpdateFamilyArchiveResponseFieldDeserializer implements JsonFieldDeserializer<UpdateFamilyArchiveResponse> {
        UpdateFamilyArchiveResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((UpdateFamilyArchiveResponse) obj));
        }

        public <U extends UpdateFamilyArchiveResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (MAPWebViewEventHelper.KEY_ERRORS.equals(str)) {
                u.setErrors(UpdateFamilyArchiveErrorDeserializer.LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
            return false;
        }
    }

    private UpdateFamilyArchiveResponseDeserializer() {
        super(new UpdateFamilyArchiveResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public UpdateFamilyArchiveResponse mo3156createValue() {
        return new UpdateFamilyArchiveResponse();
    }
}
