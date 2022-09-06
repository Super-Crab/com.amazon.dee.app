package com.amazonaws.mobileconnectors.s3.transferutility;

import android.database.Cursor;
import android.net.ConnectivityManager;
import com.amazonaws.AmazonClientException;
import com.amazonaws.com.google.gson.Gson;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class TransferRecord {
    private static final Log LOGGER = LogFactory.getLog(TransferRecord.class);
    public String bucketName;
    public long bytesCurrent;
    public long bytesTotal;
    public String cannedAcl;
    public String eTag;
    public String expirationTimeRuleId;
    public String file;
    public long fileOffset;
    private Gson gson = new Gson();
    public String headerCacheControl;
    public String headerContentDisposition;
    public String headerContentEncoding;
    public String headerContentLanguage;
    public String headerContentType;
    public String headerExpire;
    public String headerStorageClass;
    public String httpExpires;
    public int id;
    public int isEncrypted;
    public int isLastPart;
    public int isMultipart;
    public int isRequesterPays;
    public String key;
    public int mainUploadId;
    public String md5;
    public String multipartId;
    public int partNumber;
    public long rangeLast;
    public long rangeStart;
    public long speed;
    public String sseAlgorithm;
    public String sseKMSKey;
    public TransferState state;
    private Future<?> submittedTask;
    public TransferUtilityOptions transferUtilityOptions;
    public TransferType type;
    public Map<String, String> userMetadata;
    public String versionId;

    public TransferRecord(int i) {
        this.id = i;
    }

    private boolean checkIsReadyToRun() {
        return this.partNumber == 0 && !TransferState.COMPLETED.equals(this.state);
    }

    private boolean checkPreferredNetworkAvailability(TransferStatusUpdater transferStatusUpdater, ConnectivityManager connectivityManager) {
        TransferUtilityOptions transferUtilityOptions;
        if (connectivityManager == null || (transferUtilityOptions = this.transferUtilityOptions) == null || transferUtilityOptions.getTransferNetworkConnectionType().isConnected(connectivityManager)) {
            return true;
        }
        Log log = LOGGER;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network Connection ");
        outline107.append(this.transferUtilityOptions.getTransferNetworkConnectionType());
        outline107.append(" is not available.");
        log.info(outline107.toString());
        transferStatusUpdater.updateState(this.id, TransferState.WAITING_FOR_NETWORK);
        return false;
    }

    private boolean isFinalState(TransferState transferState) {
        return TransferState.COMPLETED.equals(transferState) || TransferState.FAILED.equals(transferState) || TransferState.CANCELED.equals(transferState);
    }

    public boolean cancel(final AmazonS3 amazonS3, TransferStatusUpdater transferStatusUpdater) {
        if (!isFinalState(this.state)) {
            transferStatusUpdater.updateState(this.id, TransferState.CANCELED);
            if (isRunning()) {
                this.submittedTask.cancel(true);
            }
            if (TransferType.UPLOAD.equals(this.type) && this.isMultipart == 1) {
                new Thread(new Runnable() { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferRecord.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            amazonS3.abortMultipartUpload(new AbortMultipartUploadRequest(TransferRecord.this.bucketName, TransferRecord.this.key, TransferRecord.this.multipartId));
                            Log log = TransferRecord.LOGGER;
                            log.debug("Successfully clean up multipart upload: " + TransferRecord.this.id);
                        } catch (AmazonClientException e) {
                            Log log2 = TransferRecord.LOGGER;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to abort multiplart upload: ");
                            outline107.append(TransferRecord.this.id);
                            log2.debug(outline107.toString(), e);
                        }
                    }
                }).start();
            } else if (TransferType.DOWNLOAD.equals(this.type)) {
                new File(this.file).delete();
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRunning() {
        Future<?> future = this.submittedTask;
        return future != null && !future.isDone();
    }

    public boolean pause(AmazonS3 amazonS3, TransferStatusUpdater transferStatusUpdater) {
        if (isFinalState(this.state) || TransferState.PAUSED.equals(this.state)) {
            return false;
        }
        transferStatusUpdater.updateState(this.id, TransferState.PAUSED);
        if (isRunning()) {
            this.submittedTask.cancel(true);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean pauseIfRequiredForNetworkInterruption(AmazonS3 amazonS3, TransferStatusUpdater transferStatusUpdater, ConnectivityManager connectivityManager) {
        boolean checkPreferredNetworkAvailability = checkPreferredNetworkAvailability(transferStatusUpdater, connectivityManager);
        boolean z = false;
        if (!checkPreferredNetworkAvailability && !isFinalState(this.state)) {
            z = true;
            if (isRunning()) {
                this.submittedTask.cancel(true);
            }
        }
        return z;
    }

    public boolean start(AmazonS3 amazonS3, TransferDBUtil transferDBUtil, TransferStatusUpdater transferStatusUpdater, ConnectivityManager connectivityManager) {
        if (isRunning() || !checkIsReadyToRun() || !checkPreferredNetworkAvailability(transferStatusUpdater, connectivityManager)) {
            return false;
        }
        if (this.type.equals(TransferType.DOWNLOAD)) {
            this.submittedTask = TransferThreadPool.submitTask(new DownloadTask(this, amazonS3, transferStatusUpdater));
            return true;
        }
        this.submittedTask = TransferThreadPool.submitTask(new UploadTask(this, amazonS3, transferDBUtil, transferStatusUpdater));
        return true;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("[", "id:");
        outline113.append(this.id);
        outline113.append(",");
        outline113.append("bucketName:");
        GeneratedOutlineSupport1.outline180(outline113, this.bucketName, ",", "key:");
        GeneratedOutlineSupport1.outline180(outline113, this.key, ",", "file:");
        GeneratedOutlineSupport1.outline180(outline113, this.file, ",", "type:");
        outline113.append(this.type);
        outline113.append(",");
        outline113.append("bytesTotal:");
        outline113.append(this.bytesTotal);
        outline113.append(",");
        outline113.append("bytesCurrent:");
        outline113.append(this.bytesCurrent);
        outline113.append(",");
        outline113.append("fileOffset:");
        outline113.append(this.fileOffset);
        outline113.append(",");
        outline113.append("state:");
        outline113.append(this.state);
        outline113.append(",");
        outline113.append("cannedAcl:");
        GeneratedOutlineSupport1.outline180(outline113, this.cannedAcl, ",", "mainUploadId:");
        outline113.append(this.mainUploadId);
        outline113.append(",");
        outline113.append("isMultipart:");
        outline113.append(this.isMultipart);
        outline113.append(",");
        outline113.append("isLastPart:");
        outline113.append(this.isLastPart);
        outline113.append(",");
        outline113.append("partNumber:");
        outline113.append(this.partNumber);
        outline113.append(",");
        outline113.append("multipartId:");
        GeneratedOutlineSupport1.outline180(outline113, this.multipartId, ",", "eTag:");
        GeneratedOutlineSupport1.outline180(outline113, this.eTag, ",", "storageClass:");
        GeneratedOutlineSupport1.outline180(outline113, this.headerStorageClass, ",", "userMetadata:");
        outline113.append(this.userMetadata.toString());
        outline113.append(",");
        outline113.append("transferUtilityOptions:");
        outline113.append(this.gson.toJson(this.transferUtilityOptions));
        outline113.append("]");
        return outline113.toString();
    }

    public void updateFromDB(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        this.mainUploadId = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_MAIN_UPLOAD_ID));
        this.type = TransferType.getType(cursor.getString(cursor.getColumnIndexOrThrow("type")));
        this.state = TransferState.getState(cursor.getString(cursor.getColumnIndexOrThrow("state")));
        this.bucketName = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BUCKET_NAME));
        this.key = cursor.getString(cursor.getColumnIndexOrThrow("key"));
        this.versionId = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_VERSION_ID));
        this.bytesTotal = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL));
        this.bytesCurrent = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_CURRENT));
        this.speed = cursor.getLong(cursor.getColumnIndexOrThrow("speed"));
        this.isRequesterPays = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_IS_REQUESTER_PAYS));
        this.isMultipart = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_IS_MULTIPART));
        this.isLastPart = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_IS_LAST_PART));
        this.isEncrypted = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_IS_ENCRYPTED));
        this.partNumber = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM));
        this.eTag = cursor.getString(cursor.getColumnIndexOrThrow("etag"));
        this.file = cursor.getString(cursor.getColumnIndexOrThrow("file"));
        this.multipartId = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_MULTIPART_ID));
        this.rangeStart = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_DATA_RANGE_START));
        this.rangeLast = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_DATA_RANGE_LAST));
        this.fileOffset = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_FILE_OFFSET));
        this.headerContentType = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_CONTENT_TYPE));
        this.headerContentLanguage = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_CONTENT_LANGUAGE));
        this.headerContentDisposition = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_CONTENT_DISPOSITION));
        this.headerContentEncoding = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_CONTENT_ENCODING));
        this.headerCacheControl = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_CACHE_CONTROL));
        this.headerExpire = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_EXPIRE));
        this.userMetadata = JsonUtils.jsonToMap(cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_USER_METADATA)));
        this.expirationTimeRuleId = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_EXPIRATION_TIME_RULE_ID));
        this.httpExpires = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HTTP_EXPIRES_DATE));
        this.sseAlgorithm = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_SSE_ALGORITHM));
        this.sseKMSKey = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_SSE_KMS_KEY));
        this.md5 = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_CONTENT_MD5));
        this.cannedAcl = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_CANNED_ACL));
        this.headerStorageClass = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_HEADER_STORAGE_CLASS));
        this.transferUtilityOptions = (TransferUtilityOptions) this.gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_TRANSFER_UTILITY_OPTIONS)), (Class<Object>) TransferUtilityOptions.class);
    }

    void waitTillFinish(long j) throws InterruptedException, ExecutionException, TimeoutException {
        if (isRunning()) {
            this.submittedTask.get(j, TimeUnit.MILLISECONDS);
        }
    }
}
