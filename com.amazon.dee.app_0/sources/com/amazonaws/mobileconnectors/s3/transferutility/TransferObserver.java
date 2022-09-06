package com.amazonaws.mobileconnectors.s3.transferutility;

import android.database.Cursor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class TransferObserver {
    private String bucket;
    private long bytesTotal;
    private long bytesTransferred;
    private final TransferDBUtil dbUtil;
    private String filePath;
    private final int id;
    private String key;
    private TransferStatusListener statusListener;
    private TransferListener transferListener;
    private TransferState transferState;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class TransferStatusListener implements TransferListener {
        private TransferStatusListener() {
        }

        @Override // com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
        public void onError(int i, Exception exc) {
        }

        @Override // com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
        public void onProgressChanged(int i, long j, long j2) {
            TransferObserver.this.bytesTransferred = j;
            TransferObserver.this.bytesTotal = j2;
        }

        @Override // com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
        public void onStateChanged(int i, TransferState transferState) {
            TransferObserver.this.transferState = transferState;
        }
    }

    TransferObserver(int i, TransferDBUtil transferDBUtil, String str, String str2, File file) {
        this.id = i;
        this.dbUtil = transferDBUtil;
        this.bucket = str;
        this.key = str2;
        this.filePath = file.getAbsolutePath();
        this.bytesTotal = file.length();
        this.transferState = TransferState.WAITING;
    }

    public void cleanTransferListener() {
        synchronized (this) {
            if (this.transferListener != null) {
                TransferStatusUpdater.unregisterListener(this.id, this.transferListener);
                this.transferListener = null;
            }
            if (this.statusListener != null) {
                TransferStatusUpdater.unregisterListener(this.id, this.statusListener);
                this.statusListener = null;
            }
        }
    }

    public String getAbsoluteFilePath() {
        return this.filePath;
    }

    public String getBucket() {
        return this.bucket;
    }

    public long getBytesTotal() {
        return this.bytesTotal;
    }

    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public int getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public TransferState getState() {
        return this.transferState;
    }

    public void refresh() {
        Cursor cursor = null;
        try {
            cursor = this.dbUtil.queryTransferById(this.id);
            if (cursor.moveToFirst()) {
                updateFromDB(cursor);
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void setTransferListener(TransferListener transferListener) {
        if (transferListener != null) {
            synchronized (this) {
                cleanTransferListener();
                this.statusListener = new TransferStatusListener();
                TransferStatusUpdater.registerListener(this.id, this.statusListener);
                this.transferListener = transferListener;
                TransferStatusUpdater.registerListener(this.id, this.transferListener);
            }
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransferObserver{id=");
        outline107.append(this.id);
        outline107.append(", bucket='");
        GeneratedOutlineSupport1.outline176(outline107, this.bucket, Chars.QUOTE, ", key='");
        GeneratedOutlineSupport1.outline176(outline107, this.key, Chars.QUOTE, ", bytesTotal=");
        outline107.append(this.bytesTotal);
        outline107.append(", bytesTransferred=");
        outline107.append(this.bytesTransferred);
        outline107.append(", transferState=");
        outline107.append(this.transferState);
        outline107.append(", filePath='");
        return GeneratedOutlineSupport1.outline90(outline107, this.filePath, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateFromDB(Cursor cursor) {
        this.bucket = cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BUCKET_NAME));
        this.key = cursor.getString(cursor.getColumnIndexOrThrow("key"));
        this.bytesTotal = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL));
        this.bytesTransferred = cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_CURRENT));
        this.transferState = TransferState.getState(cursor.getString(cursor.getColumnIndexOrThrow("state")));
        this.filePath = cursor.getString(cursor.getColumnIndexOrThrow("file"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransferObserver(int i, TransferDBUtil transferDBUtil, String str, String str2, File file, TransferListener transferListener) {
        this(i, transferDBUtil, str, str2, file);
        setTransferListener(transferListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransferObserver(int i, TransferDBUtil transferDBUtil) {
        this.id = i;
        this.dbUtil = transferDBUtil;
    }
}
