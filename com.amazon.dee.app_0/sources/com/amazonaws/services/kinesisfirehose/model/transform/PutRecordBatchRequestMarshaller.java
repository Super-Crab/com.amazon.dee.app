package com.amazonaws.services.kinesisfirehose.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;
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
public class PutRecordBatchRequestMarshaller implements Marshaller<Request<PutRecordBatchRequest>, PutRecordBatchRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<PutRecordBatchRequest> marshall(PutRecordBatchRequest putRecordBatchRequest) {
        if (putRecordBatchRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(putRecordBatchRequest, "AmazonKinesisFirehose");
            defaultRequest.addHeader("X-Amz-Target", "Firehose_20150804.PutRecordBatch");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream, 8192);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(gZIPOutputStream, StringUtils.UTF8);
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(outputStreamWriter);
                jsonWriter.beginObject();
                if (putRecordBatchRequest.getDeliveryStreamName() != null) {
                    String deliveryStreamName = putRecordBatchRequest.getDeliveryStreamName();
                    jsonWriter.name("DeliveryStreamName");
                    jsonWriter.value(deliveryStreamName);
                }
                if (putRecordBatchRequest.getRecords() != null) {
                    List<Record> records = putRecordBatchRequest.getRecords();
                    jsonWriter.name("Records");
                    jsonWriter.beginArray();
                    for (Record record : records) {
                        if (record != null) {
                            RecordJsonMarshaller.getInstance().marshall(record, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
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
        throw new AmazonClientException("Invalid argument passed to marshall(PutRecordBatchRequest)");
    }
}
