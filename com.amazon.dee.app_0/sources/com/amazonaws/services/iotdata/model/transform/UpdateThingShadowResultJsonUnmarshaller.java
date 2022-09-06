package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.UpdateThingShadowResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.IOUtils;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class UpdateThingShadowResultJsonUnmarshaller implements Unmarshaller<UpdateThingShadowResult, JsonUnmarshallerContext> {
    private static UpdateThingShadowResultJsonUnmarshaller instance;

    public static UpdateThingShadowResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateThingShadowResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateThingShadowResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateThingShadowResult updateThingShadowResult = new UpdateThingShadowResult();
        InputStream content = jsonUnmarshallerContext.getHttpResponse().getContent();
        if (content != null) {
            updateThingShadowResult.setPayload(ByteBuffer.wrap(IOUtils.toByteArray(content)));
        }
        return updateThingShadowResult;
    }
}
