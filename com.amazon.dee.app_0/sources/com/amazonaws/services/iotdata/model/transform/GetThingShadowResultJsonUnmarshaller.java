package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.GetThingShadowResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.IOUtils;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class GetThingShadowResultJsonUnmarshaller implements Unmarshaller<GetThingShadowResult, JsonUnmarshallerContext> {
    private static GetThingShadowResultJsonUnmarshaller instance;

    public static GetThingShadowResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetThingShadowResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetThingShadowResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetThingShadowResult getThingShadowResult = new GetThingShadowResult();
        InputStream content = jsonUnmarshallerContext.getHttpResponse().getContent();
        if (content != null) {
            getThingShadowResult.setPayload(ByteBuffer.wrap(IOUtils.toByteArray(content)));
        }
        return getThingShadowResult;
    }
}
