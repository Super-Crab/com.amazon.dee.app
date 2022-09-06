package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.FileRecordStore;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class KinesisRecorder extends AbstractKinesisRecorder {
    private static final int MAX_RECORD_SIZE_BYTES = 1048576;
    private static final String RECORD_FILE_NAME = "kinesis_stream_records";
    private final KinesisStreamRecordSender sender;
    private static final Log LOGGER = LogFactory.getLog(KinesisRecorder.class);
    private static final String USER_AGENT = KinesisRecorder.class.getName() + "/" + VersionInfoUtils.getVersion();
    private static final Pattern STREAM_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_.-]{1,128}");

    public KinesisRecorder(File file, Regions regions, AWSCredentialsProvider aWSCredentialsProvider) {
        this(file, regions, aWSCredentialsProvider, new KinesisRecorderConfig());
    }

    private void checkUpgrade(final File file) {
        if (new File(new File(file, Constants.RECORDS_DIRECTORY), Constants.RECORDS_FILE_NAME).isFile()) {
            new Thread(new Runnable() { // from class: com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.KinesisRecorder.1
                @Override // java.lang.Runnable
                public void run() {
                    KinesisRecorder.this.upgrade(file);
                }
            }).start();
        }
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.AbstractKinesisRecorder
    protected RecordSender getRecordSender() {
        return this.sender;
    }

    @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.AbstractKinesisRecorder
    public void saveRecord(byte[] bArr, String str) {
        if (str != null && STREAM_NAME_PATTERN.matcher(str).matches()) {
            if (bArr != null && bArr.length != 0 && bArr.length <= 1048576) {
                super.saveRecord(bArr, str);
                return;
            }
            throw new IllegalArgumentException("Invalid data size.");
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid stream name: ", str));
    }

    void upgrade(File file) {
        synchronized (this) {
            File file2 = new File(new File(file, Constants.RECORDS_DIRECTORY), Constants.RECORDS_FILE_NAME);
            if (!file2.isFile()) {
                return;
            }
            FileRecordStore.RecordIterator it2 = new FileRecordStore(file, Constants.RECORDS_FILE_NAME, Long.MAX_VALUE).iterator();
            while (it2.hasNext()) {
                try {
                    JSONObject jSONObject = new JSONObject(it2.next());
                    saveRecord(JSONRecordAdapter.getData(jSONObject).array(), JSONRecordAdapter.getStreamName(jSONObject));
                } catch (JSONException e) {
                    LOGGER.debug("caught exception", e);
                }
            }
            try {
                it2.close();
            } catch (IOException e2) {
                LOGGER.debug("caught exception", e2);
            }
            file2.delete();
        }
    }

    public KinesisRecorder(File file, Regions regions, AWSCredentialsProvider aWSCredentialsProvider, KinesisRecorderConfig kinesisRecorderConfig) {
        super(new FileRecordStore(file, RECORD_FILE_NAME, kinesisRecorderConfig.getMaxStorageSize()), kinesisRecorderConfig);
        if (file != null && aWSCredentialsProvider != null && regions != null) {
            AmazonKinesisClient amazonKinesisClient = new AmazonKinesisClient(aWSCredentialsProvider, kinesisRecorderConfig.getClientConfiguration());
            amazonKinesisClient.setRegion(Region.getRegion(regions));
            this.sender = new KinesisStreamRecordSender(amazonKinesisClient, USER_AGENT, kinesisRecorderConfig.getPartitionKey());
            checkUpgrade(file);
            return;
        }
        throw new IllegalArgumentException("You must pass a non-null credentialsProvider, region, directory, and config to KinesisRecordStore");
    }

    KinesisRecorder(KinesisStreamRecordSender kinesisStreamRecordSender, FileRecordStore fileRecordStore, KinesisRecorderConfig kinesisRecorderConfig) {
        super(fileRecordStore, kinesisRecorderConfig);
        this.sender = kinesisStreamRecordSender;
    }
}
