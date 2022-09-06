package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuditFindingsRequest;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Date;
/* loaded from: classes13.dex */
public class ListAuditFindingsRequestMarshaller implements Marshaller<Request<ListAuditFindingsRequest>, ListAuditFindingsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuditFindingsRequest> marshall(ListAuditFindingsRequest listAuditFindingsRequest) {
        if (listAuditFindingsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listAuditFindingsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/audit/findings");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (listAuditFindingsRequest.getTaskId() != null) {
                    String taskId = listAuditFindingsRequest.getTaskId();
                    jsonWriter.name("taskId");
                    jsonWriter.value(taskId);
                }
                if (listAuditFindingsRequest.getCheckName() != null) {
                    String checkName = listAuditFindingsRequest.getCheckName();
                    jsonWriter.name("checkName");
                    jsonWriter.value(checkName);
                }
                if (listAuditFindingsRequest.getResourceIdentifier() != null) {
                    ResourceIdentifier resourceIdentifier = listAuditFindingsRequest.getResourceIdentifier();
                    jsonWriter.name("resourceIdentifier");
                    ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, jsonWriter);
                }
                if (listAuditFindingsRequest.getMaxResults() != null) {
                    Integer maxResults = listAuditFindingsRequest.getMaxResults();
                    jsonWriter.name("maxResults");
                    jsonWriter.value(maxResults);
                }
                if (listAuditFindingsRequest.getNextToken() != null) {
                    String nextToken = listAuditFindingsRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (listAuditFindingsRequest.getStartTime() != null) {
                    Date startTime = listAuditFindingsRequest.getStartTime();
                    jsonWriter.name("startTime");
                    jsonWriter.value(startTime);
                }
                if (listAuditFindingsRequest.getEndTime() != null) {
                    Date endTime = listAuditFindingsRequest.getEndTime();
                    jsonWriter.name("endTime");
                    jsonWriter.value(endTime);
                }
                jsonWriter.endObject();
                jsonWriter.close();
                String stringWriter2 = stringWriter.toString();
                byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
                defaultRequest.setContent(new StringInputStream(stringWriter2));
                defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
                if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListAuditFindingsRequest)");
    }
}
