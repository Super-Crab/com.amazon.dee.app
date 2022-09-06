package com.amazonaws.mobileconnectors.s3.transferutility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.RequestClientOptions;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class TransferUtility {
    static final int MINIMUM_UPLOAD_PART_SIZE = 5242880;
    private static final String TRANSFER_ADD = "add_transfer";
    private static final String TRANSFER_CANCEL = "cancel_transfer";
    private static final String TRANSFER_PAUSE = "pause_transfer";
    private static final String TRANSFER_RESUME = "resume_transfer";
    final ConnectivityManager connManager;
    private TransferDBUtil dbUtil;
    private final String defaultBucket;
    private final AmazonS3 s3;
    private final TransferUtilityOptions transferUtilityOptions;
    private TransferStatusUpdater updater;
    private static final Log LOGGER = LogFactory.getLog(TransferUtility.class);
    private static final Object LOCK = new Object();
    private static String userAgentFromConfig = "";

    /* loaded from: classes13.dex */
    public static class Builder {
        private Context appContext;
        private AWSConfiguration awsConfig;
        private String defaultBucket;
        private AmazonS3 s3;
        private TransferUtilityOptions transferUtilityOptions;

        protected Builder() {
        }

        public Builder awsConfiguration(AWSConfiguration aWSConfiguration) {
            this.awsConfig = aWSConfiguration;
            return this;
        }

        public TransferUtility build() {
            if (this.s3 != null) {
                if (this.appContext != null) {
                    AWSConfiguration aWSConfiguration = this.awsConfig;
                    if (aWSConfiguration != null) {
                        try {
                            JSONObject optJsonObject = aWSConfiguration.optJsonObject("S3TransferUtility");
                            this.s3.setRegion(Region.getRegion(optJsonObject.getString("Region")));
                            this.defaultBucket = optJsonObject.getString("Bucket");
                            TransferUtility.setUserAgentFromConfig(this.awsConfig.getUserAgent());
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Failed to read S3TransferUtility please check your setup or awsconfiguration.json file", e);
                        }
                    }
                    if (this.transferUtilityOptions == null) {
                        this.transferUtilityOptions = new TransferUtilityOptions();
                    }
                    return new TransferUtility(this.s3, this.appContext, this.defaultBucket, this.transferUtilityOptions);
                }
                throw new IllegalArgumentException("Context is required please set using .context(applicationContext)");
            }
            throw new IllegalArgumentException("AmazonS3 client is required please set using .s3Client(yourClient)");
        }

        public Builder context(Context context) {
            this.appContext = context.getApplicationContext();
            return this;
        }

        public Builder defaultBucket(String str) {
            this.defaultBucket = str;
            return this;
        }

        public Builder s3Client(AmazonS3 amazonS3) {
            this.s3 = amazonS3;
            return this;
        }

        public Builder transferUtilityOptions(TransferUtilityOptions transferUtilityOptions) {
            this.transferUtilityOptions = transferUtilityOptions;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <X extends AmazonWebServiceRequest> X appendMultipartTransferServiceUserAgentString(X x) {
        RequestClientOptions requestClientOptions = x.getRequestClientOptions();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransferService_multipart/");
        outline107.append(getUserAgentFromConfig());
        outline107.append(VersionInfoUtils.getVersion());
        requestClientOptions.appendUserAgent(outline107.toString());
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <X extends AmazonWebServiceRequest> X appendTransferServiceUserAgentString(X x) {
        RequestClientOptions requestClientOptions = x.getRequestClientOptions();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransferService/");
        outline107.append(getUserAgentFromConfig());
        outline107.append(VersionInfoUtils.getVersion());
        requestClientOptions.appendUserAgent(outline107.toString());
        return x;
    }

    public static Builder builder() {
        return new Builder();
    }

    private int createMultipartUploadRecords(String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList) {
        long length = file.length();
        double d = length;
        long max = (long) Math.max(Math.ceil(d / 10000.0d), 5242880.0d);
        int ceil = ((int) Math.ceil(d / max)) + 1;
        ContentValues[] contentValuesArr = new ContentValues[ceil];
        contentValuesArr[0] = this.dbUtil.generateContentValuesForMultiPartUpload(str, str2, file, 0L, 0, "", file.length(), 0, objectMetadata, cannedAccessControlList, this.transferUtilityOptions);
        long j = length;
        int i = 1;
        long j2 = 0;
        for (int i2 = 1; i2 < ceil; i2++) {
            long min = Math.min(max, j);
            j -= max;
            contentValuesArr[i2] = this.dbUtil.generateContentValuesForMultiPartUpload(str, str2, file, j2, i, "", min, j <= 0 ? 1 : 0, objectMetadata, cannedAccessControlList, this.transferUtilityOptions);
            j2 += max;
            i++;
        }
        return this.dbUtil.bulkInsertTransferRecords(contentValuesArr);
    }

    private String getDefaultBucketOrThrow() {
        String str = this.defaultBucket;
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("TransferUtility has not been configured with a default bucket. Please use the corresponding method that specifies bucket name or configure the default bucket name in construction of the object. See TransferUtility.builder().defaultBucket() or TransferUtility.builder().awsConfiguration()");
    }

    private List<Integer> getTransferIdsWithTypeAndStates(TransferType transferType, TransferState[] transferStateArr) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.dbUtil.queryTransfersWithTypeAndStates(transferType, transferStateArr);
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM)) == 0) {
                    arrayList.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("_id"))));
                }
            }
            cursor.close();
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static String getUserAgentFromConfig() {
        synchronized (LOCK) {
            if (userAgentFromConfig != null && !userAgentFromConfig.trim().isEmpty()) {
                return userAgentFromConfig.trim() + "/";
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setUserAgentFromConfig(String str) {
        synchronized (LOCK) {
            userAgentFromConfig = str;
        }
    }

    private boolean shouldUploadInMultipart(File file) {
        return file != null && file.length() > CacheDataSink.DEFAULT_FRAGMENT_SIZE;
    }

    private synchronized void submitTransferJob(String str, int i) {
        S3ClientReference.put(Integer.valueOf(i), this.s3);
        TransferRecord transfer = this.updater.getTransfer(i);
        if (transfer == null) {
            transfer = this.dbUtil.getTransferById(i);
            if (transfer == null) {
                Log log = LOGGER;
                log.error("Cannot find transfer with id: " + i);
                return;
            }
            this.updater.addTransfer(transfer);
        } else if (TRANSFER_ADD.equals(str)) {
            Log log2 = LOGGER;
            log2.warn("Transfer has already been added: " + i);
            return;
        }
        if (!TRANSFER_ADD.equals(str) && !TRANSFER_RESUME.equals(str)) {
            if (TRANSFER_PAUSE.equals(str)) {
                transfer.pause(this.s3, this.updater);
            } else if (TRANSFER_CANCEL.equals(str)) {
                transfer.cancel(this.s3, this.updater);
            } else {
                Log log3 = LOGGER;
                log3.error("Unknown action: " + str);
            }
        }
        transfer.start(this.s3, this.dbUtil, this.updater, this.connManager);
    }

    public boolean cancel(int i) {
        submitTransferJob(TRANSFER_CANCEL, i);
        return true;
    }

    public void cancelAllWithType(TransferType transferType) {
        Cursor cursor = null;
        try {
            cursor = this.dbUtil.queryAllTransfersWithType(transferType);
            while (cursor.moveToNext()) {
                cancel(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public boolean deleteTransferRecord(int i) {
        cancel(i);
        return this.dbUtil.deleteTransferRecords(i) > 0;
    }

    public TransferObserver download(String str, String str2, File file) {
        return download(str, str2, file, null);
    }

    TransferDBUtil getDbUtil() {
        return this.dbUtil;
    }

    public TransferObserver getTransferById(int i) {
        Cursor cursor;
        try {
            cursor = this.dbUtil.queryTransferById(i);
        } catch (Throwable th) {
            th = th;
            cursor = null;
        }
        try {
            if (!cursor.moveToNext()) {
                cursor.close();
                return null;
            }
            TransferObserver transferObserver = new TransferObserver(i, this.dbUtil);
            transferObserver.updateFromDB(cursor);
            cursor.close();
            return transferObserver;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public List<TransferObserver> getTransfersWithType(TransferType transferType) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.dbUtil.queryAllTransfersWithType(transferType);
            while (cursor.moveToNext()) {
                TransferObserver transferObserver = new TransferObserver(cursor.getInt(cursor.getColumnIndexOrThrow("_id")), this.dbUtil);
                transferObserver.updateFromDB(cursor);
                arrayList.add(transferObserver);
            }
            cursor.close();
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public List<TransferObserver> getTransfersWithTypeAndState(TransferType transferType, TransferState transferState) {
        return getTransfersWithTypeAndStates(transferType, new TransferState[]{transferState});
    }

    public List<TransferObserver> getTransfersWithTypeAndStates(TransferType transferType, TransferState[] transferStateArr) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.dbUtil.queryTransfersWithTypeAndStates(transferType, transferStateArr);
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM)) == 0) {
                    TransferObserver transferObserver = new TransferObserver(cursor.getInt(cursor.getColumnIndexOrThrow("_id")), this.dbUtil);
                    transferObserver.updateFromDB(cursor);
                    arrayList.add(transferObserver);
                }
            }
            cursor.close();
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public boolean pause(int i) {
        submitTransferJob(TRANSFER_PAUSE, i);
        return true;
    }

    public void pauseAllWithType(TransferType transferType) {
        Cursor cursor = null;
        try {
            cursor = this.dbUtil.queryAllTransfersWithType(transferType);
            while (cursor.moveToNext()) {
                pause(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public TransferObserver resume(int i) {
        submitTransferJob(TRANSFER_RESUME, i);
        return getTransferById(i);
    }

    public List<TransferObserver> resumeAllWithType(TransferType transferType) {
        ArrayList arrayList = new ArrayList();
        for (Integer num : getTransferIdsWithTypeAndStates(transferType, new TransferState[]{TransferState.PAUSED, TransferState.FAILED, TransferState.CANCELED})) {
            arrayList.add(resume(num.intValue()));
        }
        return arrayList;
    }

    public TransferObserver upload(String str, String str2, File file) {
        return upload(str, str2, file, new ObjectMetadata());
    }

    private TransferUtility(AmazonS3 amazonS3, Context context, String str, TransferUtilityOptions transferUtilityOptions) {
        this.s3 = amazonS3;
        this.defaultBucket = str;
        this.transferUtilityOptions = transferUtilityOptions;
        this.dbUtil = new TransferDBUtil(context.getApplicationContext());
        this.updater = TransferStatusUpdater.getInstance(context.getApplicationContext());
        TransferThreadPool.init(this.transferUtilityOptions.getTransferThreadPoolSize());
        this.connManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public TransferObserver download(String str, File file) {
        return download(getDefaultBucketOrThrow(), str, file, null);
    }

    public TransferObserver upload(String str, File file) {
        return upload(getDefaultBucketOrThrow(), str, file, new ObjectMetadata());
    }

    public TransferObserver download(String str, String str2, File file, TransferListener transferListener) {
        if (file != null && !file.isDirectory()) {
            int parseInt = Integer.parseInt(this.dbUtil.insertSingleTransferRecord(TransferType.DOWNLOAD, str, str2, file, this.transferUtilityOptions).getLastPathSegment());
            if (file.isFile()) {
                Log log = LOGGER;
                log.warn("Overwrite existing file: " + file);
                file.delete();
            }
            submitTransferJob(TRANSFER_ADD, parseInt);
            return new TransferObserver(parseInt, this.dbUtil, str, str2, file, transferListener);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline63("Invalid file: ", file));
    }

    public TransferObserver upload(String str, String str2, File file, CannedAccessControlList cannedAccessControlList) {
        return upload(str, str2, file, new ObjectMetadata(), cannedAccessControlList);
    }

    public TransferObserver upload(String str, File file, CannedAccessControlList cannedAccessControlList) {
        return upload(getDefaultBucketOrThrow(), str, file, new ObjectMetadata(), cannedAccessControlList);
    }

    public TransferObserver upload(String str, String str2, File file, ObjectMetadata objectMetadata) {
        return upload(str, str2, file, objectMetadata, (CannedAccessControlList) null);
    }

    public TransferObserver upload(String str, File file, ObjectMetadata objectMetadata) {
        return upload(getDefaultBucketOrThrow(), str, file, objectMetadata, (CannedAccessControlList) null);
    }

    public TransferObserver upload(String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList) {
        return upload(str, str2, file, objectMetadata, cannedAccessControlList, null);
    }

    public TransferObserver upload(String str, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList) {
        return upload(getDefaultBucketOrThrow(), str, file, objectMetadata, cannedAccessControlList, null);
    }

    public TransferObserver upload(String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferListener transferListener) {
        int parseInt;
        if (file != null && !file.isDirectory() && file.exists()) {
            if (shouldUploadInMultipart(file)) {
                parseInt = createMultipartUploadRecords(str, str2, file, objectMetadata, cannedAccessControlList);
            } else {
                parseInt = Integer.parseInt(this.dbUtil.insertSingleTransferRecord(TransferType.UPLOAD, str, str2, file, objectMetadata, cannedAccessControlList, this.transferUtilityOptions).getLastPathSegment());
            }
            int i = parseInt;
            submitTransferJob(TRANSFER_ADD, i);
            return new TransferObserver(i, this.dbUtil, str, str2, file, transferListener);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline63("Invalid file: ", file));
    }

    @Deprecated
    public TransferUtility(AmazonS3 amazonS3, Context context) {
        this.s3 = amazonS3;
        this.defaultBucket = null;
        this.transferUtilityOptions = new TransferUtilityOptions();
        this.dbUtil = new TransferDBUtil(context.getApplicationContext());
        this.updater = TransferStatusUpdater.getInstance(context.getApplicationContext());
        TransferThreadPool.init(this.transferUtilityOptions.getTransferThreadPoolSize());
        this.connManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public TransferObserver download(String str, File file, TransferListener transferListener) {
        return download(getDefaultBucketOrThrow(), str, file, transferListener);
    }

    public TransferObserver upload(String str, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferListener transferListener) {
        return upload(getDefaultBucketOrThrow(), str, file, objectMetadata, cannedAccessControlList, transferListener);
    }
}
