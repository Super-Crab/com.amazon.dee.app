package com.amazon.alexa.sharing.media;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import java.io.File;
/* loaded from: classes10.dex */
public class MediaFileManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaFileManager.class);

    @VisibleForTesting
    File getFileFromFilePath(@NonNull String str) {
        return new File(str);
    }

    @VisibleForTesting
    String getFilePathFromUri(@NonNull String str) {
        return Uri.parse(str).getPath();
    }

    @VisibleForTesting
    public void removeFilesFromDisk(@NonNull String[] strArr, @NonNull ResponseResolver responseResolver) {
        for (String str : strArr) {
            try {
                String filePathFromUri = getFilePathFromUri(str);
                if (!getFileFromFilePath(filePathFromUri).delete()) {
                    LOG.i("[MediaFileRemoval] File NOT deleted: " + filePathFromUri);
                } else {
                    LOG.i("[MediaFileRemoval] Deleted file: " + filePathFromUri);
                }
            } catch (Exception e) {
                LOG.e("[MediaFileRemoval] Exception while deleting file: " + str, e);
            }
        }
        responseResolver.resolve(null);
    }
}
