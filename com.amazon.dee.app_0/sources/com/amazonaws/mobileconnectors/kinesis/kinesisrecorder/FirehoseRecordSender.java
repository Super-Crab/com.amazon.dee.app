package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchResult;
import com.amazonaws.services.kinesisfirehose.model.Record;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
class FirehoseRecordSender implements RecordSender {
    private final AmazonKinesisFirehose client;
    private final String userAgent;

    public FirehoseRecordSender(AmazonKinesisFirehose amazonKinesisFirehose, String str) {
        this.client = amazonKinesisFirehose;
        this.userAgent = str;
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.RecordSender
    public boolean isRecoverable(AmazonClientException amazonClientException) {
        if (!(amazonClientException instanceof AmazonServiceException)) {
            return amazonClientException.getCause() != null && (amazonClientException.getCause() instanceof IOException);
        }
        String errorCode = ((AmazonServiceException) amazonClientException).getErrorCode();
        return "InternalFailure".equals(errorCode) || "ServiceUnavailable".equals(errorCode) || "Throttling".equals(errorCode) || "ServiceUnavailableException".equals(errorCode);
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.RecordSender
    public List<byte[]> sendBatch(String str, List<byte[]> list) {
        if (list != null && !list.isEmpty()) {
            PutRecordBatchRequest putRecordBatchRequest = new PutRecordBatchRequest();
            putRecordBatchRequest.setDeliveryStreamName(str);
            ArrayList arrayList = new ArrayList(list.size());
            for (byte[] bArr : list) {
                Record record = new Record();
                record.setData(ByteBuffer.wrap(bArr));
                arrayList.add(record);
            }
            putRecordBatchRequest.setRecords(arrayList);
            putRecordBatchRequest.getRequestClientOptions().appendUserAgent(this.userAgent);
            PutRecordBatchResult putRecordBatch = this.client.putRecordBatch(putRecordBatchRequest);
            int size = putRecordBatch.getRequestResponses().size();
            ArrayList arrayList2 = new ArrayList(putRecordBatch.getFailedPutCount().intValue());
            for (int i = 0; i < size; i++) {
                if (putRecordBatch.getRequestResponses().get(i).getErrorCode() != null) {
                    arrayList2.add(list.get(i));
                }
            }
            return arrayList2;
        }
        return Collections.emptyList();
    }
}
