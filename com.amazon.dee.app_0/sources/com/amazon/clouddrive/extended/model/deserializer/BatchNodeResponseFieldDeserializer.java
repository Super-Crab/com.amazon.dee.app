package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchNodeResponse;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
class BatchNodeResponseFieldDeserializer implements JsonFieldDeserializer<BatchNodeResponse> {
    @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
    public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
        return handleField(jsonParser, str, (String) ((BatchNodeResponse) obj));
    }

    public <U extends BatchNodeResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
        if (MAPWebViewEventHelper.KEY_ERRORS.equals(str)) {
            u.setErrors(BatchNodeErrorListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
            return true;
        } else if ("nodes".equals(str)) {
            u.setNodes(ExtendedDeserializers.LIST_NODE_EXTENDED_DESERIALIZER.mo3229deserialize(jsonParser));
            return true;
        } else if (!"groupCollections".equals(str)) {
            return false;
        } else {
            u.setGroupCollections(GroupCollectionListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
            return false;
        }
    }
}
