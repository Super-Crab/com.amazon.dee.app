package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes13.dex */
public class PutRecordsRequestMarshaller implements Marshaller<Request<PutRecordsRequest>, PutRecordsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<PutRecordsRequest> marshall(PutRecordsRequest putRecordsRequest) {
        if (putRecordsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(putRecordsRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.PutRecords");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream, 8192);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(gZIPOutputStream, StringUtils.UTF8);
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(outputStreamWriter);
                jsonWriter.beginObject();
                if (putRecordsRequest.getRecords() != null) {
                    List<PutRecordsRequestEntry> records = putRecordsRequest.getRecords();
                    jsonWriter.name("Records");
                    jsonWriter.beginArray();
                    for (PutRecordsRequestEntry putRecordsRequestEntry : records) {
                        if (putRecordsRequestEntry != null) {
                            PutRecordsRequestEntryJsonMarshaller.getInstance().marshall(putRecordsRequestEntry, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (putRecordsRequest.getStreamName() != null) {
                    String streamName = putRecordsRequest.getStreamName();
                    jsonWriter.name("StreamName");
                    jsonWriter.value(streamName);
                }
                jsonWriter.endObject();
                jsonWriter.flush();
                gZIPOutputStream.finish();
                outputStreamWriter.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                defaultRequest.setContent(new ByteArrayInputStream(byteArray));
                defaultRequest.addHeader("Content-Length", Integer.toString(byteArray.length));
                defaultRequest.addHeader("Content-Encoding", "gzip");
                if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.1");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(PutRecordsRequest)");
    }
}
