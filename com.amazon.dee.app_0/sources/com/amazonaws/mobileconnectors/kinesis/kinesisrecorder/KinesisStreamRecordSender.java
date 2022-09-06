package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.util.StringUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class KinesisStreamRecordSender implements RecordSender {
    private final AmazonKinesis client;
    private final String partitionKey;
    private final String userAgent;

    public KinesisStreamRecordSender(AmazonKinesis amazonKinesis, String str) {
        this(amazonKinesis, str, null);
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.RecordSender
    public boolean isRecoverable(AmazonClientException amazonClientException) {
        if (!(amazonClientException instanceof AmazonServiceException)) {
            return amazonClientException.getCause() != null && (amazonClientException.getCause() instanceof IOException);
        }
        String errorCode = ((AmazonServiceException) amazonClientException).getErrorCode();
        return "InternalFailure".equals(errorCode) || "ServiceUnavailable".equals(errorCode) || "Throttling".equals(errorCode) || "ProvisionedThroughputExceededException".equals(errorCode);
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.RecordSender
    public List<byte[]> sendBatch(String str, List<byte[]> list) {
        if (list != null && !list.isEmpty()) {
            PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
            putRecordsRequest.setStreamName(str);
            ArrayList arrayList = new ArrayList(list.size());
            for (byte[] bArr : list) {
                String uuid = StringUtils.isBlank(this.partitionKey) ? UUID.randomUUID().toString() : this.partitionKey;
                PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
                putRecordsRequestEntry.setData(ByteBuffer.wrap(bArr));
                putRecordsRequestEntry.setPartitionKey(uuid);
                arrayList.add(putRecordsRequestEntry);
            }
            putRecordsRequest.setRecords(arrayList);
            putRecordsRequest.getRequestClientOptions().appendUserAgent(this.userAgent);
            PutRecordsResult putRecords = this.client.putRecords(putRecordsRequest);
            int size = putRecords.getRecords().size();
            ArrayList arrayList2 = new ArrayList(putRecords.getFailedRecordCount().intValue());
            for (int i = 0; i < size; i++) {
                if (putRecords.getRecords().get(i).getErrorCode() != null) {
                    arrayList2.add(list.get(i));
                }
            }
            return arrayList2;
        }
        return Collections.emptyList();
    }

    public KinesisStreamRecordSender(AmazonKinesis amazonKinesis, String str, String str2) {
        this.client = amazonKinesis;
        this.userAgent = str;
        this.partitionKey = str2;
    }
}
