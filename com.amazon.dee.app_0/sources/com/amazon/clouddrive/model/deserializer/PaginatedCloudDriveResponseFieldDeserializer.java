package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class PaginatedCloudDriveResponseFieldDeserializer implements JsonFieldDeserializer<PaginatedCloudDriveResponse> {
    @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
    public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
        return handleField(jsonParser, str, (String) ((PaginatedCloudDriveResponse) obj));
    }

    public <U extends PaginatedCloudDriveResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
        if ("nextToken".equals(str)) {
            u.setNextToken(SimpleDeserializers.deserializeString(jsonParser));
            return true;
        } else if (!"count".equals(str)) {
            return false;
        } else {
            u.setCount(SimpleDeserializers.deserializeInteger(jsonParser).intValue());
            return true;
        }
    }
}
