package com.amazonaws.services.iot.model.transform;

import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListV2LoggingLevelsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListV2LoggingLevelsRequestMarshaller implements Marshaller<Request<ListV2LoggingLevelsRequest>, ListV2LoggingLevelsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListV2LoggingLevelsRequest> marshall(ListV2LoggingLevelsRequest listV2LoggingLevelsRequest) {
        if (listV2LoggingLevelsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listV2LoggingLevelsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listV2LoggingLevelsRequest.getTargetType() != null) {
                defaultRequest.addParameter(MobilyticsCustomEntries.CommsMetadata.TARGET_TYPE, StringUtils.fromString(listV2LoggingLevelsRequest.getTargetType()));
            }
            if (listV2LoggingLevelsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listV2LoggingLevelsRequest.getNextToken()));
            }
            if (listV2LoggingLevelsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listV2LoggingLevelsRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/v2LoggingLevel", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListV2LoggingLevelsRequest)");
    }
}
