package com.amazon.alexa.wakeword.pryon;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.wakeword.davs.WakeWordModelArtifactInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
/* loaded from: classes11.dex */
public class WakeWordModelAuthority {
    private static final String TAG = "WakeWordModelAuthority";
    private final String backupModelAssetName;
    private boolean isBackupModelSet;
    private final WakeWordDetectionMetricsListener metricsListener;
    private final WakeWordDownloadManager wakeWordDownloadManager;
    private final WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper;

    @Deprecated
    public WakeWordModelAuthority(WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, WakeWordDownloadManager wakeWordDownloadManager, boolean z) {
        this(wakeWordModelContentProviderHelper, wakeWordDetectionMetricsListener, wakeWordDownloadManager, (String) null);
    }

    private boolean hasPersistedDataForExistingModel() {
        return !TextUtils.isEmpty(this.wakeWordModelContentProviderHelper.readExistingModelArtifactId()) && !TextUtils.isEmpty(this.wakeWordModelContentProviderHelper.readLastUsedLocale()) && !TextUtils.isEmpty(this.wakeWordModelContentProviderHelper.readExistingModelEngineCompatibilityId()) && this.wakeWordModelContentProviderHelper.readWakeWords() != null;
    }

    private boolean isExistingModelCompatible() {
        return WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId().equals(this.wakeWordModelContentProviderHelper.readExistingModelEngineCompatibilityId());
    }

    private byte[] readWakeWordModel() throws IOException {
        InputStream openWakeWordModelInputStream = this.wakeWordModelContentProviderHelper.openWakeWordModelInputStream();
        try {
            byte[] convertStreamToByteArray = convertStreamToByteArray(openWakeWordModelInputStream);
            if (openWakeWordModelInputStream != null) {
                openWakeWordModelInputStream.close();
            }
            return convertStreamToByteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (openWakeWordModelInputStream != null) {
                    try {
                        openWakeWordModelInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private void setupBackupModel() {
        String str = TAG;
        String str2 = this.backupModelAssetName;
        if (str2 != null) {
            this.wakeWordModelContentProviderHelper.setBackupModel(str2);
        } else {
            Log.w(str, "no backup model in the host app assets");
        }
    }

    @VisibleForTesting
    byte[] convertStreamToByteArray(InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getWakeWordModel(String str) throws IOException {
        if (!this.isBackupModelSet) {
            setupBackupModel();
            this.isBackupModelSet = true;
        }
        if (str == null) {
            return readWakeWordModel();
        }
        List<String> list = null;
        if (hasPersistedDataForExistingModel()) {
            if (str.equals(this.wakeWordModelContentProviderHelper.readLastUsedLocale())) {
                if (isExistingModelCompatible()) {
                    return readWakeWordModel();
                }
                list = this.wakeWordModelContentProviderHelper.readWakeWords();
            } else {
                Log.i(TAG, "locale of existing model doesn't match request.");
                this.metricsListener.onLocaleMismatch();
            }
        }
        WakeWordModelUserParams wakeWordModelUserParams = list != null ? new WakeWordModelUserParams(str, list) : new WakeWordModelUserParams(str);
        String str2 = TAG;
        Log.w(str2, "starting to download a model " + wakeWordModelUserParams);
        this.wakeWordDownloadManager.downloadWakeWordModelAsync(wakeWordModelUserParams);
        return readWakeWordModel();
    }

    public WakeWordModelAuthority(WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, WakeWordDownloadManager wakeWordDownloadManager, String str) {
        this.isBackupModelSet = false;
        this.wakeWordModelContentProviderHelper = wakeWordModelContentProviderHelper;
        this.metricsListener = wakeWordDetectionMetricsListener;
        this.wakeWordDownloadManager = wakeWordDownloadManager;
        this.backupModelAssetName = str;
    }
}
