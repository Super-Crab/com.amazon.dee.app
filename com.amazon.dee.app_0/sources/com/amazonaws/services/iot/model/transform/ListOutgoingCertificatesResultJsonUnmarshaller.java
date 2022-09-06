package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListOutgoingCertificatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListOutgoingCertificatesResultJsonUnmarshaller implements Unmarshaller<ListOutgoingCertificatesResult, JsonUnmarshallerContext> {
    private static ListOutgoingCertificatesResultJsonUnmarshaller instance;

    public static ListOutgoingCertificatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListOutgoingCertificatesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListOutgoingCertificatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListOutgoingCertificatesResult listOutgoingCertificatesResult = new ListOutgoingCertificatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("outgoingCertificates")) {
                listOutgoingCertificatesResult.setOutgoingCertificates(new ListUnmarshaller(OutgoingCertificateJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listOutgoingCertificatesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listOutgoingCertificatesResult;
    }
}
