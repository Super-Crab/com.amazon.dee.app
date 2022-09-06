package com.amazonaws.mobileconnectors.s3.transferutility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazonaws.com.google.gson.Gson;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class TransferDBUtil {
    private static final String QUERY_PLACE_HOLDER_STRING = ",?";
    private static TransferDBBase transferDBBase;
    private Gson gson = new Gson();
    private static final Log LOGGER = LogFactory.getLog(TransferDBUtil.class);
    private static final Object LOCK = new Object();

    public TransferDBUtil(Context context) {
        synchronized (LOCK) {
            if (transferDBBase == null) {
                transferDBBase = new TransferDBBase(context);
            }
        }
    }

    private String createPlaceholders(int i) {
        if (i <= 0) {
            LOGGER.error("Cannot create a string of 0 or less placeholders.");
            return null;
        }
        StringBuilder sb = new StringBuilder((i * 2) - 1);
        sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(QUERY_PLACE_HOLDER_STRING);
        }
        return sb.toString();
    }

    private ContentValues generateContentValuesForObjectMetadata(ObjectMetadata objectMetadata) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_USER_METADATA, JsonUtils.mapToString(objectMetadata.getUserMetadata()));
        contentValues.put(TransferTable.COLUMN_HEADER_CONTENT_TYPE, objectMetadata.getContentType());
        contentValues.put(TransferTable.COLUMN_HEADER_CONTENT_ENCODING, objectMetadata.getContentEncoding());
        contentValues.put(TransferTable.COLUMN_HEADER_CACHE_CONTROL, objectMetadata.getCacheControl());
        contentValues.put(TransferTable.COLUMN_CONTENT_MD5, objectMetadata.getContentMD5());
        contentValues.put(TransferTable.COLUMN_HEADER_CONTENT_DISPOSITION, objectMetadata.getContentDisposition());
        contentValues.put(TransferTable.COLUMN_SSE_ALGORITHM, objectMetadata.getSSEAlgorithm());
        contentValues.put(TransferTable.COLUMN_SSE_KMS_KEY, objectMetadata.getSSEAwsKmsKeyId());
        contentValues.put(TransferTable.COLUMN_EXPIRATION_TIME_RULE_ID, objectMetadata.getExpirationTimeRuleId());
        if (objectMetadata.getHttpExpiresDate() != null) {
            contentValues.put(TransferTable.COLUMN_HTTP_EXPIRES_DATE, String.valueOf(objectMetadata.getHttpExpiresDate().getTime()));
        }
        if (objectMetadata.getStorageClass() != null) {
            contentValues.put(TransferTable.COLUMN_HEADER_STORAGE_CLASS, objectMetadata.getStorageClass());
        }
        return contentValues;
    }

    private ContentValues generateContentValuesForSinglePartTransfer(TransferType transferType, String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferUtilityOptions transferUtilityOptions) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", transferType.toString());
        contentValues.put("state", TransferState.WAITING.toString());
        contentValues.put(TransferTable.COLUMN_BUCKET_NAME, str);
        contentValues.put("key", str2);
        contentValues.put("file", file.getAbsolutePath());
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, (Long) 0L);
        if (transferType.equals(TransferType.UPLOAD)) {
            contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(file.length()));
        }
        contentValues.put(TransferTable.COLUMN_IS_MULTIPART, (Integer) 0);
        contentValues.put(TransferTable.COLUMN_PART_NUM, (Integer) 0);
        contentValues.put(TransferTable.COLUMN_IS_ENCRYPTED, (Integer) 0);
        contentValues.putAll(generateContentValuesForObjectMetadata(objectMetadata));
        if (cannedAccessControlList != null) {
            contentValues.put(TransferTable.COLUMN_CANNED_ACL, cannedAccessControlList.toString());
        }
        if (transferUtilityOptions != null) {
            contentValues.put(TransferTable.COLUMN_TRANSFER_UTILITY_OPTIONS, this.gson.toJson(transferUtilityOptions));
        }
        return contentValues;
    }

    static TransferDBBase getTransferDBBase(Context context) {
        TransferDBBase transferDBBase2;
        synchronized (LOCK) {
            if (transferDBBase == null) {
                transferDBBase = new TransferDBBase(context);
            }
            transferDBBase2 = transferDBBase;
        }
        return transferDBBase2;
    }

    public int bulkInsertTransferRecords(ContentValues[] contentValuesArr) {
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.bulkInsert(transferDBBase2.getContentUri(), contentValuesArr);
    }

    public int cancelAllWithType(TransferType transferType) {
        String[] strArr;
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PENDING_CANCEL.toString());
        if (transferType == TransferType.ANY) {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString(), TransferState.PAUSED.toString(), TransferState.WAITING_FOR_NETWORK.toString()};
            str = "state in (?,?,?,?,?)";
        } else {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString(), TransferState.PAUSED.toString(), TransferState.WAITING_FOR_NETWORK.toString(), transferType.toString()};
            str = "state in (?,?,?,?,?) and type=?";
        }
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, str, strArr);
    }

    public boolean checkWaitingForNetworkPartRequestsFromDB(int i) {
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, "state=?", new String[]{TransferState.WAITING_FOR_NETWORK.toString()}, null);
            boolean moveToNext = cursor.moveToNext();
            cursor.close();
            return moveToNext;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void closeDB() {
        synchronized (LOCK) {
            if (transferDBBase != null) {
                transferDBBase.closeDBHelper();
            }
        }
    }

    public int deleteTransferRecords(int i) {
        return transferDBBase.delete(getRecordUri(i), null, null);
    }

    public ContentValues generateContentValuesForMultiPartUpload(String str, String str2, File file, long j, int i, String str3, long j2, int i2, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferUtilityOptions transferUtilityOptions) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", TransferType.UPLOAD.toString());
        contentValues.put("state", TransferState.WAITING.toString());
        contentValues.put(TransferTable.COLUMN_BUCKET_NAME, str);
        contentValues.put("key", str2);
        contentValues.put("file", file.getAbsolutePath());
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, (Long) 0L);
        contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(j2));
        contentValues.put(TransferTable.COLUMN_IS_MULTIPART, (Integer) 1);
        contentValues.put(TransferTable.COLUMN_PART_NUM, Integer.valueOf(i));
        contentValues.put(TransferTable.COLUMN_FILE_OFFSET, Long.valueOf(j));
        contentValues.put(TransferTable.COLUMN_MULTIPART_ID, str3);
        contentValues.put(TransferTable.COLUMN_IS_LAST_PART, Integer.valueOf(i2));
        contentValues.put(TransferTable.COLUMN_IS_ENCRYPTED, (Integer) 0);
        contentValues.putAll(generateContentValuesForObjectMetadata(objectMetadata));
        if (cannedAccessControlList != null) {
            contentValues.put(TransferTable.COLUMN_CANNED_ACL, cannedAccessControlList.toString());
        }
        if (transferUtilityOptions != null) {
            contentValues.put(TransferTable.COLUMN_TRANSFER_UTILITY_OPTIONS, this.gson.toJson(transferUtilityOptions));
        }
        return contentValues;
    }

    public Uri getContentUri() {
        return transferDBBase.getContentUri();
    }

    public List<UploadPartRequest> getNonCompletedPartRequestsFromDB(int i, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            while (cursor.moveToNext()) {
                if (!TransferState.PART_COMPLETED.equals(TransferState.getState(cursor.getString(cursor.getColumnIndexOrThrow("state"))))) {
                    UploadPartRequest withPartSize = new UploadPartRequest().withId(cursor.getInt(cursor.getColumnIndexOrThrow("_id"))).withMainUploadId(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_MAIN_UPLOAD_ID))).withBucketName(cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BUCKET_NAME))).withKey(cursor.getString(cursor.getColumnIndexOrThrow("key"))).withUploadId(str).withFile(new File(cursor.getString(cursor.getColumnIndexOrThrow("file")))).withFileOffset(cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_FILE_OFFSET))).withPartNumber(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM))).withPartSize(cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL)));
                    boolean z = true;
                    if (1 != cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_IS_LAST_PART))) {
                        z = false;
                    }
                    arrayList.add(withPartSize.withLastPart(z));
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

    public Uri getPartUri(int i) {
        return Uri.parse(transferDBBase.getContentUri() + "/part/" + i);
    }

    public Uri getRecordUri(int i) {
        return Uri.parse(transferDBBase.getContentUri() + "/" + i);
    }

    public Uri getStateUri(TransferState transferState) {
        return Uri.parse(transferDBBase.getContentUri() + "/state/" + transferState.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransferRecord getTransferById(int i) {
        Cursor cursor;
        TransferRecord transferRecord = null;
        try {
            cursor = queryTransferById(i);
            try {
                if (cursor.moveToFirst()) {
                    transferRecord = new TransferRecord(i);
                    transferRecord.updateFromDB(cursor);
                }
                cursor.close();
                return transferRecord;
            } catch (Throwable th) {
                th = th;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public Uri insertMultipartUploadRecord(String str, String str2, File file, long j, int i, String str3, long j2, int i2, TransferUtilityOptions transferUtilityOptions) {
        ContentValues generateContentValuesForMultiPartUpload = generateContentValuesForMultiPartUpload(str, str2, file, j, i, str3, j2, i2, new ObjectMetadata(), null, transferUtilityOptions);
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.insert(transferDBBase2.getContentUri(), generateContentValuesForMultiPartUpload);
    }

    public Uri insertSingleTransferRecord(TransferType transferType, String str, String str2, File file, ObjectMetadata objectMetadata, TransferUtilityOptions transferUtilityOptions) {
        return insertSingleTransferRecord(transferType, str, str2, file, objectMetadata, null, transferUtilityOptions);
    }

    public int pauseAllWithType(TransferType transferType) {
        String[] strArr;
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PENDING_PAUSE.toString());
        if (transferType == TransferType.ANY) {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString()};
            str = "state in (?,?,?)";
        } else {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString(), transferType.toString()};
            str = "state in (?,?,?) and type=?";
        }
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, str, strArr);
    }

    public Cursor queryAllTransfersWithType(TransferType transferType) {
        if (transferType == TransferType.ANY) {
            TransferDBBase transferDBBase2 = transferDBBase;
            return transferDBBase2.query(transferDBBase2.getContentUri(), null, null, null, null);
        }
        TransferDBBase transferDBBase3 = transferDBBase;
        return transferDBBase3.query(transferDBBase3.getContentUri(), null, "type=?", new String[]{transferType.toString()}, null);
    }

    public long queryBytesTransferredByMainUploadId(int i) {
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            long j = 0;
            while (cursor.moveToNext()) {
                if (TransferState.PART_COMPLETED.equals(TransferState.getState(cursor.getString(cursor.getColumnIndexOrThrow("state"))))) {
                    j += cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL));
                }
            }
            cursor.close();
            return j;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0037, code lost:
        r8 = r0.getLong(r0.getColumnIndexOrThrow(com.amazonaws.mobileconnectors.s3.transferutility.TransferTable.COLUMN_BYTES_CURRENT));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long queryBytesTransferredOfPartNum(int r8, int r9) {
        /*
            r7 = this;
            r0 = 0
            com.amazonaws.mobileconnectors.s3.transferutility.TransferDBBase r1 = com.amazonaws.mobileconnectors.s3.transferutility.TransferDBUtil.transferDBBase     // Catch: java.lang.Throwable -> L48
            android.net.Uri r2 = r7.getPartUri(r8)     // Catch: java.lang.Throwable -> L48
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L48
        Lf:
            boolean r8 = r0.moveToNext()     // Catch: java.lang.Throwable -> L48
            if (r8 == 0) goto L42
            java.lang.String r8 = "part_num"
            int r8 = r0.getColumnIndexOrThrow(r8)     // Catch: java.lang.Throwable -> L48
            int r8 = r0.getInt(r8)     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = "state"
            int r1 = r0.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r0.getString(r1)     // Catch: java.lang.Throwable -> L48
            if (r8 != r9) goto Lf
            com.amazonaws.mobileconnectors.s3.transferutility.TransferState r8 = com.amazonaws.mobileconnectors.s3.transferutility.TransferState.PART_COMPLETED     // Catch: java.lang.Throwable -> L48
            com.amazonaws.mobileconnectors.s3.transferutility.TransferState r1 = com.amazonaws.mobileconnectors.s3.transferutility.TransferState.getState(r1)     // Catch: java.lang.Throwable -> L48
            boolean r8 = r8.equals(r1)     // Catch: java.lang.Throwable -> L48
            if (r8 != 0) goto Lf
            java.lang.String r8 = "bytes_current"
            int r8 = r0.getColumnIndexOrThrow(r8)     // Catch: java.lang.Throwable -> L48
            long r8 = r0.getLong(r8)     // Catch: java.lang.Throwable -> L48
            goto L44
        L42:
            r8 = 0
        L44:
            r0.close()
            return r8
        L48:
            r8 = move-exception
            if (r0 == 0) goto L4e
            r0.close()
        L4e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.mobileconnectors.s3.transferutility.TransferDBUtil.queryBytesTransferredOfPartNum(int, int):long");
    }

    public List<PartETag> queryPartETagsOfUpload(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            while (cursor.moveToNext()) {
                arrayList.add(new PartETag(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM)), cursor.getString(cursor.getColumnIndexOrThrow("etag"))));
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

    public Cursor queryTransferById(int i) {
        return transferDBBase.query(getRecordUri(i), null, null, null, null);
    }

    public Cursor queryTransfersWithTypeAndState(TransferType transferType, TransferState transferState) {
        if (transferType == TransferType.ANY) {
            return transferDBBase.query(getStateUri(transferState), null, null, null, null);
        }
        return transferDBBase.query(getStateUri(transferState), null, "type=?", new String[]{transferType.toString()}, null);
    }

    public Cursor queryTransfersWithTypeAndStates(TransferType transferType, TransferState[] transferStateArr) {
        String str;
        String[] strArr;
        int length = transferStateArr.length;
        String createPlaceholders = createPlaceholders(length);
        int i = 0;
        if (transferType == TransferType.ANY) {
            String outline75 = GeneratedOutlineSupport1.outline75("state in (", createPlaceholders, ")");
            String[] strArr2 = new String[length];
            while (i < length) {
                strArr2[i] = transferStateArr[i].toString();
                i++;
            }
            str = outline75;
            strArr = strArr2;
        } else {
            String outline77 = GeneratedOutlineSupport1.outline77("state in (", createPlaceholders, ") and ", "type", "=?");
            String[] strArr3 = new String[length + 1];
            while (i < length) {
                strArr3[i] = transferStateArr[i].toString();
                i++;
            }
            strArr3[i] = transferType.toString();
            str = outline77;
            strArr = strArr3;
        }
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.query(transferDBBase2.getContentUri(), null, str, strArr, null);
    }

    public int setAllRunningRecordsToPausedBeforeShutdownService() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PAUSED.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "state in (?,?,?,?)", new String[]{TransferState.IN_PROGRESS.toString(), TransferState.PENDING_PAUSE.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString()});
    }

    public int updateBytesTotalForDownload(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(j));
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateBytesTransferred(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, Long.valueOf(j));
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateETag(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("etag", str);
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateMultipartId(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_MULTIPART_ID, str);
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateNetworkConnected() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.RESUMED_WAITING.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "state in (?,?)", new String[]{TransferState.PENDING_NETWORK_DISCONNECT.toString(), TransferState.WAITING_FOR_NETWORK.toString()});
    }

    public int updateNetworkDisconnected() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PENDING_NETWORK_DISCONNECT.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "state in (?,?,?)", new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString()});
    }

    public int updateState(int i, TransferState transferState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", transferState.toString());
        if (TransferState.FAILED.equals(transferState)) {
            return transferDBBase.update(getRecordUri(i), contentValues, "state not in (?,?,?,?,?) ", new String[]{TransferState.COMPLETED.toString(), TransferState.PENDING_NETWORK_DISCONNECT.toString(), TransferState.PAUSED.toString(), TransferState.CANCELED.toString(), TransferState.WAITING_FOR_NETWORK.toString()});
        }
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateStateAndNotifyUpdate(int i, TransferState transferState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", transferState.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, GeneratedOutlineSupport1.outline49("_id=", i), null);
    }

    public int updateTransferRecord(TransferRecord transferRecord) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(transferRecord.id));
        contentValues.put("state", transferRecord.state.toString());
        contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(transferRecord.bytesTotal));
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, Long.valueOf(transferRecord.bytesCurrent));
        return transferDBBase.update(getRecordUri(transferRecord.id), contentValues, null, null);
    }

    public Uri insertSingleTransferRecord(TransferType transferType, String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferUtilityOptions transferUtilityOptions) {
        ContentValues generateContentValuesForSinglePartTransfer = generateContentValuesForSinglePartTransfer(transferType, str, str2, file, objectMetadata, cannedAccessControlList, transferUtilityOptions);
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.insert(transferDBBase2.getContentUri(), generateContentValuesForSinglePartTransfer);
    }

    public Uri insertSingleTransferRecord(TransferType transferType, String str, String str2, File file, TransferUtilityOptions transferUtilityOptions) {
        return insertSingleTransferRecord(transferType, str, str2, file, new ObjectMetadata(), transferUtilityOptions);
    }
}
