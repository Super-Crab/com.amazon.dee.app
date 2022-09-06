package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.ClientConfiguration;
/* loaded from: classes13.dex */
public class KinesisRecorderConfig {
    private static final long DEFAUT_MAX_STORAGE_SIZE = 5242880;
    private final ClientConfiguration clientConfiguration;
    private DeadLetterListener mDeadLetterListener;
    private long maxStorageSize;
    private String partitionKey;

    public KinesisRecorderConfig() {
        this(new ClientConfiguration());
    }

    public ClientConfiguration getClientConfiguration() {
        return this.clientConfiguration;
    }

    public DeadLetterListener getDeadLetterListener() {
        return this.mDeadLetterListener;
    }

    public long getMaxStorageSize() {
        return this.maxStorageSize;
    }

    public String getPartitionKey() {
        return this.partitionKey;
    }

    public KinesisRecorderConfig withDeadLetterListener(DeadLetterListener deadLetterListener) {
        this.mDeadLetterListener = deadLetterListener;
        return this;
    }

    public KinesisRecorderConfig withMaxStorageSize(long j) {
        this.maxStorageSize = j;
        return this;
    }

    public KinesisRecorderConfig withPartitionKey(String str) {
        this.partitionKey = str;
        return this;
    }

    public KinesisRecorderConfig(ClientConfiguration clientConfiguration) {
        this.maxStorageSize = 5242880L;
        if (clientConfiguration != null) {
            this.clientConfiguration = new ClientConfiguration(clientConfiguration);
            return;
        }
        throw new IllegalArgumentException();
    }

    public KinesisRecorderConfig(KinesisRecorderConfig kinesisRecorderConfig) {
        this.maxStorageSize = 5242880L;
        this.maxStorageSize = kinesisRecorderConfig.getMaxStorageSize();
        this.clientConfiguration = new ClientConfiguration(kinesisRecorderConfig.getClientConfiguration());
        this.partitionKey = kinesisRecorderConfig.partitionKey;
    }
}
