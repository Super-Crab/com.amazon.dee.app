package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.UpdateFamilyArchiveError;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class UpdateFamilyArchiveErrorDeserializer extends AbstractDeserializer<UpdateFamilyArchiveError, UpdateFamilyArchiveError> {
    public static final JsonDeserializer<UpdateFamilyArchiveError> INSTANCE = new UpdateFamilyArchiveErrorDeserializer();
    public static final JsonDeserializer<List<UpdateFamilyArchiveError>> LIST_DESERIALIZER = new ListDeserializer(INSTANCE);

    /* loaded from: classes11.dex */
    static class UpdateFamilyArchiveErrorFieldDeserializer implements JsonFieldDeserializer<UpdateFamilyArchiveError> {
        UpdateFamilyArchiveErrorFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((UpdateFamilyArchiveError) obj));
        }

        public <U extends UpdateFamilyArchiveError> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(str)) {
                u.setNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("errorCode".equals(str)) {
                u.setErrorCode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"errorMessage".equals(str)) {
                return false;
            } else {
                u.setErrorMessage(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private UpdateFamilyArchiveErrorDeserializer() {
        super(new UpdateFamilyArchiveErrorFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public UpdateFamilyArchiveError mo3156createValue() {
        return new UpdateFamilyArchiveError();
    }
}
