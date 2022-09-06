package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.DeleteThingShadowResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.IOUtils;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class DeleteThingShadowResultJsonUnmarshaller implements Unmarshaller<DeleteThingShadowResult, JsonUnmarshallerContext> {
    private static DeleteThingShadowResultJsonUnmarshaller instance;

    public static DeleteThingShadowResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteThingShadowResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteThingShadowResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DeleteThingShadowResult deleteThingShadowResult = new DeleteThingShadowResult();
        InputStream content = jsonUnmarshallerContext.getHttpResponse().getContent();
        if (content != null) {
            deleteThingShadowResult.setPayload(ByteBuffer.wrap(IOUtils.toByteArray(content)));
        }
        return deleteThingShadowResult;
    }
}
