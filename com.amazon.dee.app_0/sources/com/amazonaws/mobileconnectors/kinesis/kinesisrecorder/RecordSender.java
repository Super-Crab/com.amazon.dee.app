package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.AmazonClientException;
import java.util.List;
/* loaded from: classes13.dex */
interface RecordSender {
    boolean isRecoverable(AmazonClientException amazonClientException);

    List<byte[]> sendBatch(String str, List<byte[]> list);
}
