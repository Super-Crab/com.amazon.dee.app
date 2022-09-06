package com.amazon.device.crashmanager;

import android.os.Environment;
import com.amazon.device.crashmanager.source.ArtifactSource;
import com.amazon.device.utils.det.MediaScannerHelper;
import com.amazon.device.utils.det.StatusNotifier;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipOutputStream;
/* loaded from: classes12.dex */
class ArtifactOffloader {
    private static final String LOG_FILE_PREFIX = "crashes_";
    private static final String LOG_FILE_SUFFIX = ".zip";
    private final List<ArtifactSource> mArtifactSources;
    private final String mDeviceSerialNumber;
    private final MediaScannerHelper mMediaScannerHelper;
    private final StatusNotifier mStatusNotifier;
    private static final DPLogger log = new DPLogger("CrashManager.ArtifactOffloader");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.hhmmss.SSS", Locale.US);
    private static final File LOGS_DIR = new File(Environment.getExternalStorageDirectory(), "KindleLogs");
    private static final File TEMP_LOGS_DIR = new File(Environment.getExternalStorageDirectory(), "tmp/crashes");

    public ArtifactOffloader(List<ArtifactSource> list, StatusNotifier statusNotifier, MediaScannerHelper mediaScannerHelper, String str) {
        if (list != null) {
            if (statusNotifier == null) {
                throw new IllegalArgumentException("StatusNotifier must not be null.");
            }
            if (mediaScannerHelper != null) {
                this.mArtifactSources = list;
                this.mStatusNotifier = statusNotifier;
                this.mMediaScannerHelper = mediaScannerHelper;
                this.mDeviceSerialNumber = (str == null || str.trim().isEmpty()) ? "UNKNOWN_DSN" : str;
                return;
            }
            throw new IllegalArgumentException("MediaScannerHelper must not be null.");
        }
        throw new IllegalArgumentException("Artifact sources must not be null.");
    }

    private File createTempFile() {
        File file = TEMP_LOGS_DIR;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(LOG_FILE_PREFIX);
        outline107.append(this.mDeviceSerialNumber);
        outline107.append("_");
        outline107.append(DATE_FORMAT.format(new Date()));
        outline107.append(LOG_FILE_SUFFIX);
        File file2 = new File(file, outline107.toString());
        log.debug("createTempFile", "Creating temp file", file2.getAbsolutePath());
        file2.getParentFile().mkdirs();
        return file2;
    }

    private ZipOutputStream getZipStream(File file) throws IOException {
        log.debug("getNextZipStream", "Creating stream for", file.getAbsolutePath());
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        zipOutputStream.setLevel(9);
        zipOutputStream.setMethod(8);
        return zipOutputStream;
    }

    private boolean renameFile(File file) {
        File file2 = LOGS_DIR;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(LOG_FILE_PREFIX);
        outline107.append(this.mDeviceSerialNumber);
        outline107.append("_");
        outline107.append(DATE_FORMAT.format(new Date()));
        outline107.append(LOG_FILE_SUFFIX);
        File file3 = new File(file2, outline107.toString());
        file3.getParentFile().mkdirs();
        boolean renameTo = file.renameTo(file3);
        if (!renameTo) {
            log.info("RenameLogFile", "Unable to rename logfile", "OldFile", file.getAbsolutePath(), "NewFile", file3.getAbsolutePath());
        }
        return renameTo;
    }

    public String generateOffloadFileName(String str, long j) {
        Date date = new Date(j);
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "_");
        outline113.append(DATE_FORMAT.format(date));
        return outline113.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e3, code lost:
        if (r7 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e5, code lost:
        r7.close();
        renameFile(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00eb, code lost:
        r17.mStatusNotifier.fireNotification("Finished offloading Crashes", r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x010f, code lost:
        if (r7 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0112, code lost:
        com.amazon.device.crashmanager.ArtifactOffloader.log.debug(com.amazon.device.crashmanager.metrics.MetricsConstants.SOURCE_NAME_OFFLOAD_CRASH, "Done offloading crashes", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x011c, code lost:
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00d6, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int offloadArtifacts(com.amazon.client.metrics.common.MetricEvent r18, int r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.crashmanager.ArtifactOffloader.offloadArtifacts(com.amazon.client.metrics.common.MetricEvent, int):int");
    }
}
