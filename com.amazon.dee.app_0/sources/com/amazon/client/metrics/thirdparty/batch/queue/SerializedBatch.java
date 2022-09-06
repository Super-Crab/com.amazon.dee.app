package com.amazon.client.metrics.thirdparty.batch.queue;
/* loaded from: classes11.dex */
public class SerializedBatch {
    private byte[] mBatchContent;
    private long mTimestamp;

    public SerializedBatch(byte[] bArr, long j) {
        this.mBatchContent = bArr;
        this.mTimestamp = j;
    }

    public long getLength() {
        byte[] bArr = this.mBatchContent;
        if (bArr == null) {
            return 0L;
        }
        return bArr.length;
    }

    public byte[] getSerializedBytes() {
        return this.mBatchContent;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public SerializedBatch(byte[] bArr) {
        this.mBatchContent = bArr;
        this.mTimestamp = System.currentTimeMillis();
    }
}
