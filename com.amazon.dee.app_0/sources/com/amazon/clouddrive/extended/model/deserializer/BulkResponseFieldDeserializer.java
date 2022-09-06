package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BulkResponse;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
class BulkResponseFieldDeserializer implements JsonFieldDeserializer<BulkResponse> {
    @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
    public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
        return handleField(jsonParser, str, (String) ((BulkResponse) obj));
    }

    public <U extends BulkResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
        if ("errorMap".equals(str)) {
            u.setErrorMap(ErrorMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
            return true;
        } else if (!"message".equals(str)) {
            return false;
        } else {
            u.setMessage(SimpleDeserializers.deserializeString(jsonParser));
            return true;
        }
    }
}
