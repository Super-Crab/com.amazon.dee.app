package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.AmazonClientException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.FileRecordStore;
import com.amazonaws.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public abstract class AbstractKinesisRecorder {
    private static final Log LOGGER = LogFactory.getLog(AbstractKinesisRecorder.class);
    private static final int MAX_BATCH_RECORDS_SIZE_BYTES = 524288;
    private static final int MAX_RECORDS_PER_BATCH = 128;
    private static final int MAX_RETRY_COUNT = 3;
    protected KinesisRecorderConfig config;
    protected FileRecordStore recordStore;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractKinesisRecorder(FileRecordStore fileRecordStore, KinesisRecorderConfig kinesisRecorderConfig) {
        if (fileRecordStore != null) {
            this.recordStore = fileRecordStore;
            this.config = kinesisRecorderConfig;
            return;
        }
        throw new IllegalArgumentException("Record store can't be null");
    }

    public synchronized void deleteAllRecords() {
        try {
            this.recordStore.iterator().removeAllRecords();
        } catch (IOException e) {
            throw new AmazonClientException("Error deleting events", e);
        }
    }

    public long getDiskByteLimit() {
        return this.config.getMaxStorageSize();
    }

    public long getDiskBytesUsed() {
        return this.recordStore.getFileSize();
    }

    public KinesisRecorderConfig getKinesisRecorderConfig() {
        return this.config;
    }

    protected abstract RecordSender getRecordSender();

    protected String nextBatch(FileRecordStore.RecordIterator recordIterator, List<byte[]> list, int i, int i2) {
        list.clear();
        FileRecordParser fileRecordParser = new FileRecordParser();
        int i3 = 0;
        String str = null;
        int i4 = 0;
        while (recordIterator.hasNext() && i3 < i && i4 < i2) {
            String peek = recordIterator.peek();
            if (peek != null && !peek.isEmpty()) {
                try {
                    fileRecordParser.parse(peek);
                    if (str != null && !str.equals(fileRecordParser.streamName)) {
                        break;
                    }
                    list.add(fileRecordParser.bytes);
                    i3++;
                    i4 += fileRecordParser.bytes.length;
                    str = fileRecordParser.streamName;
                    recordIterator.next();
                } catch (Exception e) {
                    LOGGER.warn("Failed to read line. Skip.", e);
                    recordIterator.next();
                }
            } else {
                recordIterator.next();
            }
        }
        return str;
    }

    public void saveRecord(String str, String str2) {
        saveRecord(str.getBytes(StringUtils.UTF8), str2);
    }

    public synchronized void submitAllRecords() {
        String nextBatch;
        List<byte[]> list;
        RecordSender recordSender = getRecordSender();
        FileRecordStore.RecordIterator it2 = this.recordStore.iterator();
        ArrayList arrayList = new ArrayList(128);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (it2.hasNext() && i < 3 && (nextBatch = nextBatch(it2, arrayList, 128, 524288)) != null && !arrayList.isEmpty()) {
            try {
                try {
                    list = recordSender.sendBatch(nextBatch, arrayList);
                } catch (AmazonClientException e) {
                    if (z || e.getMessage() == null || !e.getMessage().contains("Unable to unmarshall error response")) {
                        throw e;
                    }
                    list = arrayList;
                    z = true;
                }
                try {
                    int size = arrayList.size() - list.size();
                    i2 += size;
                    it2.removeReadRecords();
                    if (size == 0) {
                        i++;
                    }
                    if (!list.isEmpty()) {
                        for (byte[] bArr : list) {
                            saveRecord(bArr, nextBatch);
                        }
                    }
                } catch (AmazonClientException e2) {
                    if (recordSender.isRecoverable(e2)) {
                        LOGGER.error("ServiceException in submit all, the values of the data inside the requests appears valid.  The request will be kept", e2);
                    } else {
                        try {
                            this.config.getDeadLetterListener().onRecordsDropped(nextBatch, arrayList);
                        } catch (Exception e3) {
                            LOGGER.error("DeadLetterListener onRecordsDropped has thrown an exception (user code)", e3);
                        }
                        try {
                            it2.removeReadRecords();
                            LOGGER.error("ServiceException in submit all, the last request is presumed to be the cause and will be dropped", e2);
                        } catch (IOException e4) {
                            throw new AmazonClientException("Failed to drop bad records.", e4);
                        }
                    }
                    throw e2;
                }
            } catch (IOException e5) {
                throw new AmazonClientException("Failed to remove read records", e5);
            }
        }
        LOGGER.debug(String.format("submitAllRecords sent %d records", Integer.valueOf(i2)));
        try {
            it2.close();
        } catch (IOException e6) {
            throw new AmazonClientException("Failed to close record file", e6);
        }
    }

    public void saveRecord(byte[] bArr, String str) {
        try {
            this.recordStore.put(FileRecordParser.asString(str, bArr));
        } catch (IOException e) {
            throw new AmazonClientException("Error saving record", e);
        }
    }
}
