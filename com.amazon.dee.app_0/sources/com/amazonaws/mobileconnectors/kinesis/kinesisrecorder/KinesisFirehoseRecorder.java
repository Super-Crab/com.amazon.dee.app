package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClient;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.regex.Pattern;
/* loaded from: classes13.dex */
public class KinesisFirehoseRecorder extends AbstractKinesisRecorder {
    private static final int MAX_RECORD_SIZE_BYTES = 1024000;
    private static final String RECORD_FILE_NAME = "kinesis_firehose_records";
    private FirehoseRecordSender sender;
    private static final String USER_AGENT = KinesisFirehoseRecorder.class.getName() + "/" + VersionInfoUtils.getVersion();
    private static final Pattern STREAM_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_.-]{1,64}");

    public KinesisFirehoseRecorder(File file, Regions regions, AWSCredentialsProvider aWSCredentialsProvider) {
        this(file, regions, aWSCredentialsProvider, new KinesisRecorderConfig());
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.AbstractKinesisRecorder
    protected RecordSender getRecordSender() {
        return this.sender;
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.AbstractKinesisRecorder
    public void saveRecord(byte[] bArr, String str) {
        if (str != null && STREAM_NAME_PATTERN.matcher(str).matches()) {
            if (bArr != null && bArr.length != 0 && bArr.length <= MAX_RECORD_SIZE_BYTES) {
                super.saveRecord(bArr, str);
                return;
            }
            throw new IllegalArgumentException("Invalid data size.");
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid stream name: ", str));
    }

    public KinesisFirehoseRecorder(File file, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, KinesisRecorderConfig kinesisRecorderConfig) {
        super(new FileRecordStore(file, RECORD_FILE_NAME, kinesisRecorderConfig.getMaxStorageSize()), kinesisRecorderConfig);
        AmazonKinesisFirehoseClient amazonKinesisFirehoseClient = new AmazonKinesisFirehoseClient(aWSCredentialsProvider, kinesisRecorderConfig.getClientConfiguration());
        amazonKinesisFirehoseClient.setRegion(Region.getRegion(regions));
        this.sender = new FirehoseRecordSender(amazonKinesisFirehoseClient, USER_AGENT);
    }

    KinesisFirehoseRecorder(FirehoseRecordSender firehoseRecordSender, FileRecordStore fileRecordStore, KinesisRecorderConfig kinesisRecorderConfig) {
        super(fileRecordStore, kinesisRecorderConfig);
        this.sender = firehoseRecordSender;
    }
}
