package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.mobileconnectors.s3.transfermanager.PauseStatus;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManagerConfiguration;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
/* loaded from: classes13.dex */
public class TransferManagerUtils {
    public static long calculateOptimalPartSize(PutObjectRequest putObjectRequest, TransferManagerConfiguration transferManagerConfiguration) {
        return (long) Math.max(Math.ceil(getContentLength(putObjectRequest) / 10000.0d), transferManagerConfiguration.getMinimumUploadPartSize());
    }

    public static long calculateOptimalPartSizeForCopy(CopyObjectRequest copyObjectRequest, TransferManagerConfiguration transferManagerConfiguration, long j) {
        return (long) Math.max(Math.ceil(j / 10000.0d), transferManagerConfiguration.getMultipartCopyPartSize());
    }

    public static ThreadPoolExecutor createDefaultExecutorService() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(10, new ThreadFactory() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferManagerUtils.1
            private int threadCount = 1;

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("s3-transfer-manager-worker-");
                int i = this.threadCount;
                this.threadCount = i + 1;
                outline107.append(i);
                thread.setName(outline107.toString());
                return thread;
            }
        });
    }

    public static PauseStatus determinePauseStatus(Transfer.TransferState transferState, boolean z) {
        if (z) {
            if (transferState == Transfer.TransferState.Waiting) {
                return PauseStatus.CANCELLED_BEFORE_START;
            }
            if (transferState == Transfer.TransferState.InProgress) {
                return PauseStatus.CANCELLED;
            }
        }
        if (transferState == Transfer.TransferState.Waiting) {
            return PauseStatus.NOT_STARTED;
        }
        return PauseStatus.NO_EFFECT;
    }

    public static long getContentLength(PutObjectRequest putObjectRequest) {
        File requestFile = getRequestFile(putObjectRequest);
        if (requestFile != null) {
            return requestFile.length();
        }
        if (putObjectRequest.getInputStream() != null && putObjectRequest.getMetadata().getContentLength() > 0) {
            return putObjectRequest.getMetadata().getContentLength();
        }
        return -1L;
    }

    public static File getRequestFile(PutObjectRequest putObjectRequest) {
        if (putObjectRequest.getFile() != null) {
            return putObjectRequest.getFile();
        }
        return null;
    }

    public static boolean isUploadParallelizable(PutObjectRequest putObjectRequest, boolean z) {
        return !z && getRequestFile(putObjectRequest) != null;
    }

    public static boolean shouldUseMultipartUpload(PutObjectRequest putObjectRequest, TransferManagerConfiguration transferManagerConfiguration) {
        return getContentLength(putObjectRequest) > transferManagerConfiguration.getMultipartUploadThreshold();
    }
}
