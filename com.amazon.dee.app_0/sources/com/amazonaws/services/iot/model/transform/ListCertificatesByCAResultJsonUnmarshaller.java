package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListCertificatesByCAResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListCertificatesByCAResultJsonUnmarshaller implements Unmarshaller<ListCertificatesByCAResult, JsonUnmarshallerContext> {
    private static ListCertificatesByCAResultJsonUnmarshaller instance;

    public static ListCertificatesByCAResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListCertificatesByCAResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListCertificatesByCAResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListCertificatesByCAResult listCertificatesByCAResult = new ListCertificatesByCAResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificates")) {
                listCertificatesByCAResult.setCertificates(new ListUnmarshaller(CertificateJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listCertificatesByCAResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listCertificatesByCAResult;
    }
}
