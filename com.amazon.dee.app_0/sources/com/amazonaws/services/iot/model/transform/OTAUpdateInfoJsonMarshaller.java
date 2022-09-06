package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.ErrorInfo;
import com.amazonaws.services.iot.model.OTAUpdateFile;
import com.amazonaws.services.iot.model.OTAUpdateInfo;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
class OTAUpdateInfoJsonMarshaller {
    private static OTAUpdateInfoJsonMarshaller instance;

    OTAUpdateInfoJsonMarshaller() {
    }

    public static OTAUpdateInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateInfoJsonMarshaller();
        }
        return instance;
    }

    public void marshall(OTAUpdateInfo oTAUpdateInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (oTAUpdateInfo.getOtaUpdateId() != null) {
            String otaUpdateId = oTAUpdateInfo.getOtaUpdateId();
            awsJsonWriter.name("otaUpdateId");
            awsJsonWriter.value(otaUpdateId);
        }
        if (oTAUpdateInfo.getOtaUpdateArn() != null) {
            String otaUpdateArn = oTAUpdateInfo.getOtaUpdateArn();
            awsJsonWriter.name("otaUpdateArn");
            awsJsonWriter.value(otaUpdateArn);
        }
        if (oTAUpdateInfo.getCreationDate() != null) {
            Date creationDate = oTAUpdateInfo.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        if (oTAUpdateInfo.getLastModifiedDate() != null) {
            Date lastModifiedDate = oTAUpdateInfo.getLastModifiedDate();
            awsJsonWriter.name("lastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (oTAUpdateInfo.getDescription() != null) {
            String description = oTAUpdateInfo.getDescription();
            awsJsonWriter.name("description");
            awsJsonWriter.value(description);
        }
        if (oTAUpdateInfo.getTargets() != null) {
            List<String> targets = oTAUpdateInfo.getTargets();
            awsJsonWriter.name("targets");
            awsJsonWriter.beginArray();
            for (String str : targets) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (oTAUpdateInfo.getAwsJobExecutionsRolloutConfig() != null) {
            AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig = oTAUpdateInfo.getAwsJobExecutionsRolloutConfig();
            awsJsonWriter.name("awsJobExecutionsRolloutConfig");
            AwsJobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(awsJobExecutionsRolloutConfig, awsJsonWriter);
        }
        if (oTAUpdateInfo.getTargetSelection() != null) {
            String targetSelection = oTAUpdateInfo.getTargetSelection();
            awsJsonWriter.name("targetSelection");
            awsJsonWriter.value(targetSelection);
        }
        if (oTAUpdateInfo.getOtaUpdateFiles() != null) {
            List<OTAUpdateFile> otaUpdateFiles = oTAUpdateInfo.getOtaUpdateFiles();
            awsJsonWriter.name("otaUpdateFiles");
            awsJsonWriter.beginArray();
            for (OTAUpdateFile oTAUpdateFile : otaUpdateFiles) {
                if (oTAUpdateFile != null) {
                    OTAUpdateFileJsonMarshaller.getInstance().marshall(oTAUpdateFile, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (oTAUpdateInfo.getOtaUpdateStatus() != null) {
            String otaUpdateStatus = oTAUpdateInfo.getOtaUpdateStatus();
            awsJsonWriter.name("otaUpdateStatus");
            awsJsonWriter.value(otaUpdateStatus);
        }
        if (oTAUpdateInfo.getAwsIotJobId() != null) {
            String awsIotJobId = oTAUpdateInfo.getAwsIotJobId();
            awsJsonWriter.name("awsIotJobId");
            awsJsonWriter.value(awsIotJobId);
        }
        if (oTAUpdateInfo.getAwsIotJobArn() != null) {
            String awsIotJobArn = oTAUpdateInfo.getAwsIotJobArn();
            awsJsonWriter.name("awsIotJobArn");
            awsJsonWriter.value(awsIotJobArn);
        }
        if (oTAUpdateInfo.getErrorInfo() != null) {
            ErrorInfo errorInfo = oTAUpdateInfo.getErrorInfo();
            awsJsonWriter.name("errorInfo");
            ErrorInfoJsonMarshaller.getInstance().marshall(errorInfo, awsJsonWriter);
        }
        if (oTAUpdateInfo.getAdditionalParameters() != null) {
            Map<String, String> additionalParameters = oTAUpdateInfo.getAdditionalParameters();
            awsJsonWriter.name("additionalParameters");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : additionalParameters.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        awsJsonWriter.endObject();
    }
}
