package com.amazon.client.metrics.thirdparty.batch.queue;

import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
/* loaded from: classes11.dex */
public class NonVolatileBoundedByteArrayQueue extends BoundedByteArrayQueue {
    private static final String METRIC_NAME_ADD_BATCHES_IO_FAILURE = "addBatchesIOFailure";
    private static final String METRIC_NAME_REMOVE_BATCHES_IO_FAILURE = "removeBatchesIOFailure";
    private static final DPLogger log = new DPLogger("Metrics:NonVolatileBoundedByteArrayQueue");
    private final File mDirOfBatchFiles;
    private final Deque<String> mFileNameQueue;

    public NonVolatileBoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, File file) throws IllegalArgumentException, IOException {
        super(batchPipelineConfiguration, periodicMetricReporter);
        this.mFileNameQueue = new LinkedList();
        if (batchPipelineConfiguration.getMaxBatchQueueEntries() > 0) {
            if (file != null && file.isDirectory()) {
                this.mDirOfBatchFiles = file;
                readPersistedData();
                trimQueueToFit();
                return;
            }
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queue.dirError", 1.0d);
            throw new IllegalArgumentException("dirOfBatchFiles must not be null and should be a valid directory.");
        }
        this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queue.capacityError", 1.0d);
        throw new IllegalArgumentException("Capacity of queue must be greater than 0 entry");
    }

    private String getAbsolutePath(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDirOfBatchFiles.getAbsolutePath());
        return GeneratedOutlineSupport1.outline91(sb, File.separator, str);
    }

    private void readPersistedData() {
        File[] listFiles = this.mDirOfBatchFiles.listFiles();
        Arrays.sort(listFiles, new Comparator<File>() { // from class: com.amazon.client.metrics.thirdparty.batch.queue.NonVolatileBoundedByteArrayQueue.1
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                Long l;
                long j = 0L;
                try {
                    l = Long.valueOf(file.getName());
                } catch (NumberFormatException unused) {
                    NonVolatileBoundedByteArrayQueue.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queueRead.NumberFormatException", 1.0d);
                    DPLogger dPLogger = NonVolatileBoundedByteArrayQueue.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported file name format: ");
                    outline107.append(file.getName());
                    dPLogger.error("readPersistedData", outline107.toString(), new Object[0]);
                    l = j;
                }
                try {
                    j = Long.valueOf(file2.getName());
                } catch (NumberFormatException unused2) {
                    NonVolatileBoundedByteArrayQueue.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queueRead.NumberFormatException", 1.0d);
                    DPLogger dPLogger2 = NonVolatileBoundedByteArrayQueue.log;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unsupported file name format: ");
                    outline1072.append(file2.getName());
                    dPLogger2.error("readPersistedData", outline1072.toString(), new Object[0]);
                }
                return l.compareTo(j);
            }
        });
        int length = listFiles.length;
        for (int i = 0; i < length; i++) {
            this.mBytesUsed = listFiles[i].length() + this.mBytesUsed;
            this.mFileNameQueue.add(listFiles[i].getName());
            this.mNumEntries++;
        }
    }

    private void trimQueueToFit() throws IOException {
        while (true) {
            if (this.mBytesUsed > this.mBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes() || this.mNumEntries > this.mBatchPipelineConfiguration.getMaxBatchQueueEntries()) {
                log.debug("trimQueueToFit", "Queue is full. Dropping an element", new Object[0]);
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("droppedBatches", 1.0d);
                String poll = this.mFileNameQueue.poll();
                if (poll != null) {
                    File file = new File(getAbsolutePath(poll));
                    this.mBytesUsed -= file.length();
                    file.delete();
                    this.mNumEntries--;
                } else {
                    this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queueSizeError", 1.0d);
                    throw new IllegalArgumentException("All items removed and the queue is still full.");
                }
            } else {
                return;
            }
        }
    }

    private String writeBatchToFile(SerializedBatch serializedBatch) throws IOException {
        String valueOf;
        FileOutputStream fileOutputStream;
        this.mBytesUsed = serializedBatch.getLength() + this.mBytesUsed;
        this.mNumEntries++;
        trimQueueToFit();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                valueOf = String.valueOf(serializedBatch.getTimestamp());
                fileOutputStream = new FileOutputStream(getAbsolutePath(valueOf));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(serializedBatch.getSerializedBytes());
            fileOutputStream.close();
            return valueOf;
        } catch (IOException e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            this.mBytesUsed -= serializedBatch.getLength();
            this.mNumEntries--;
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter(METRIC_NAME_ADD_BATCHES_IO_FAILURE, 1.0d);
            log.error(BulkOperationType.add, "Unable to persist the serializedObject to internal Storage." + e.getMessage(), new Object[0]);
            throw e;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void add(SerializedBatch serializedBatch, boolean z) throws IllegalArgumentException, IOException {
        validateInput(serializedBatch);
        this.mFileNameQueue.add(writeBatchToFile(serializedBatch));
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addFirst(SerializedBatch serializedBatch, boolean z) throws IOException {
        validateInput(serializedBatch);
        String writeBatchToFile = writeBatchToFile(serializedBatch);
        if (writeBatchToFile != null) {
            this.mFileNameQueue.addFirst(writeBatchToFile);
        }
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void persistBatches() {
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.BoundedByteArrayQueue
    public synchronized void purgeExpiredBatches() {
        long currentTimeMillis = System.currentTimeMillis() - this.mBatchPipelineConfiguration.getExpiryTimeMillis();
        long j = this.mNumEntries;
        while (this.mFileNameQueue.peek() != null && Long.parseLong(this.mFileNameQueue.peek()) < currentTimeMillis) {
            try {
                remove();
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("expiredBatches", 1.0d);
            } catch (IOException e) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter(METRIC_NAME_REMOVE_BATCHES_IO_FAILURE, 1.0d);
                DPLogger dPLogger = log;
                dPLogger.error("purgeExpiredBatches", "Unabled to purge batch." + e.getMessage(), new Object[0]);
            }
        }
        log.debug("purgeExpiredBatches", "Number of batches purged: ", Long.valueOf(j - this.mNumEntries));
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized SerializedBatch remove() throws IOException {
        String poll = this.mFileNameQueue.poll();
        FileInputStream fileInputStream = null;
        if (poll != null) {
            try {
                try {
                    File file = new File(getAbsolutePath(poll));
                    long length = file.length();
                    if (length <= 2147483647L) {
                        byte[] bArr = new byte[(int) length];
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        try {
                            fileInputStream2.read(bArr);
                            this.mBytesUsed -= file.length();
                            file.delete();
                            this.mNumEntries--;
                            SerializedBatch serializedBatch = new SerializedBatch(bArr, Long.parseLong(poll));
                            fileInputStream2.close();
                            return serializedBatch;
                        } catch (IOException e) {
                            e = e;
                            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter(METRIC_NAME_REMOVE_BATCHES_IO_FAILURE, 1.0d);
                            log.error(BulkOperationType.remove, "Unable to delete the file." + e.getMessage(), new Object[0]);
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw th;
                        }
                    }
                    log.error(BulkOperationType.remove, "size of metrics batch file should not be greater than Integer.MAX_VALUE", "file size", Long.valueOf(length));
                    this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("removeBatchMAXLengthError", 1.0d);
                    throw new IOException("Size of metrics batch file greater than Integer.MAX_VALUE");
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            return null;
        }
    }
}
