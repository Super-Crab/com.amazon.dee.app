package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListCertificatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListCertificatesResultJsonUnmarshaller implements Unmarshaller<ListCertificatesResult, JsonUnmarshallerContext> {
    private static ListCertificatesResultJsonUnmarshaller instance;

    public static ListCertificatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListCertificatesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListCertificatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListCertificatesResult listCertificatesResult = new ListCertificatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificates")) {
                listCertificatesResult.setCertificates(new ListUnmarshaller(CertificateJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listCertificatesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listCertificatesResult;
    }
}
