package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListCACertificatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListCACertificatesResultJsonUnmarshaller implements Unmarshaller<ListCACertificatesResult, JsonUnmarshallerContext> {
    private static ListCACertificatesResultJsonUnmarshaller instance;

    public static ListCACertificatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListCACertificatesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListCACertificatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListCACertificatesResult listCACertificatesResult = new ListCACertificatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificates")) {
                listCACertificatesResult.setCertificates(new ListUnmarshaller(CACertificateJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listCACertificatesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listCACertificatesResult;
    }
}
