package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteV2LoggingLevelRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteV2LoggingLevelRequestMarshaller implements Marshaller<Request<DeleteV2LoggingLevelRequest>, DeleteV2LoggingLevelRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteV2LoggingLevelRequest> marshall(DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest) {
        if (deleteV2LoggingLevelRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteV2LoggingLevelRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (deleteV2LoggingLevelRequest.getTargetType() != null) {
                defaultRequest.addParameter(MobilyticsCustomEntries.CommsMetadata.TARGET_TYPE, StringUtils.fromString(deleteV2LoggingLevelRequest.getTargetType()));
            }
            if (deleteV2LoggingLevelRequest.getTargetName() != null) {
                defaultRequest.addParameter(MetricsConstants.Firmware.TARGET_NAME, StringUtils.fromString(deleteV2LoggingLevelRequest.getTargetName()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/v2LoggingLevel", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteV2LoggingLevelRequest)");
    }
}
