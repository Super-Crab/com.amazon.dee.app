package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OTAUpdateInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class OTAUpdateInfoJsonUnmarshaller implements Unmarshaller<OTAUpdateInfo, JsonUnmarshallerContext> {
    private static OTAUpdateInfoJsonUnmarshaller instance;

    OTAUpdateInfoJsonUnmarshaller() {
    }

    public static OTAUpdateInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateInfoJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OTAUpdateInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OTAUpdateInfo oTAUpdateInfo = new OTAUpdateInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("otaUpdateId")) {
                oTAUpdateInfo.setOtaUpdateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("otaUpdateArn")) {
                oTAUpdateInfo.setOtaUpdateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("creationDate")) {
                oTAUpdateInfo.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lastModifiedDate")) {
                oTAUpdateInfo.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("description")) {
                oTAUpdateInfo.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("targets")) {
                oTAUpdateInfo.setTargets(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsJobExecutionsRolloutConfig")) {
                oTAUpdateInfo.setAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("targetSelection")) {
                oTAUpdateInfo.setTargetSelection(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("otaUpdateFiles")) {
                oTAUpdateInfo.setOtaUpdateFiles(new ListUnmarshaller(OTAUpdateFileJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("otaUpdateStatus")) {
                oTAUpdateInfo.setOtaUpdateStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsIotJobId")) {
                oTAUpdateInfo.setAwsIotJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsIotJobArn")) {
                oTAUpdateInfo.setAwsIotJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("errorInfo")) {
                oTAUpdateInfo.setErrorInfo(ErrorInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("additionalParameters")) {
                oTAUpdateInfo.setAdditionalParameters(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return oTAUpdateInfo;
    }
}
