package com.amazon.device.crashmanager;

import android.os.Build;
import android.system.ErrnoException;
import android.system.OsConstants;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.processor.ArtifactProcessor;
import com.amazon.device.crashmanager.source.ArtifactSource;
import com.amazon.device.crashmanager.source.DropBoxArtifactSource;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.utils.det.DetEndpointConfig;
import com.amazon.device.utils.det.Domain;
import com.amazon.device.utils.det.NetworkManager;
import com.amazon.device.utils.det.StatusNotifier;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class ArtifactUploader {
    private static final int BACK_OFF_MULTIPLIER = 2;
    private static final int HTTP_CONNECT_TIMEOUT = 10000;
    private static final int HTTP_READ_TIMEOUT = 60000;
    private static final long INITIAL_BACK_OFF_MS = 500;
    private static final int MAX_RETRIES = 5;
    private static final DPLogger log = new DPLogger("ArtifactUploader");
    private final String TAG = ArtifactUploader.class.getSimpleName();
    private final CrashDescriptorDedupeUtil crashDedupeUtil;
    private final boolean mAllowWANUpload;
    private final List<ArtifactProcessor> mArtifactProcessors;
    private final List<ArtifactSource> mArtifactSources;
    private final String mDeviceSerialNumber;
    private final String mDeviceType;
    private final Domain mDomain;
    private final NetworkManager mNetworkManager;
    private final StatusNotifier mStatusNotifier;

    /* renamed from: com.amazon.device.crashmanager.ArtifactUploader$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus = new int[ArtifactUploadStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.DE_DUPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.SERVER_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum ArtifactUploadStatus {
        SUCCESS,
        DE_DUPED,
        SKIP,
        FAILED,
        SERVER_ERROR
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ArtifactUploaderResult {
        private final ArtifactUploadStatus mArtifactUploadStatus;
        private final String mCrashDescriptor;
        private final String mUploadErrorMessage;
        private final String mUploadErrorTag;

        public ArtifactUploaderResult(ArtifactUploader artifactUploader, ArtifactUploadStatus artifactUploadStatus, String str) {
            this(artifactUploadStatus, str, null, null);
        }

        public ArtifactUploadStatus getArtifactUploadStatus() {
            return this.mArtifactUploadStatus;
        }

        public String getCrashDescriptor() {
            return this.mCrashDescriptor;
        }

        public String getUploadErrorTag() {
            return this.mUploadErrorTag;
        }

        public ArtifactUploaderResult(ArtifactUploadStatus artifactUploadStatus, String str, String str2, String str3) {
            if (artifactUploadStatus != null) {
                this.mArtifactUploadStatus = artifactUploadStatus;
                this.mCrashDescriptor = str;
                this.mUploadErrorTag = str2;
                this.mUploadErrorMessage = str3;
                return;
            }
            throw new IllegalArgumentException("artifact upload status cannot be null");
        }
    }

    /* loaded from: classes12.dex */
    public class CrashUploadStatistics {
        private final CrashUploadStatus mCrashUploadStatus;
        private final int mNumArtifactsFailed;
        private final int mNumArtifactsSkipped;
        private final int mNumArtifactsUploaded;

        public CrashUploadStatistics(CrashUploadStatus crashUploadStatus, int i, int i2, int i3) {
            this.mCrashUploadStatus = crashUploadStatus;
            this.mNumArtifactsUploaded = i;
            this.mNumArtifactsFailed = i2;
            this.mNumArtifactsSkipped = i3;
        }

        public CrashUploadStatus getCrashUploadStatus() {
            return this.mCrashUploadStatus;
        }

        public int getNumArtifactsFailed() {
            return this.mNumArtifactsFailed;
        }

        public int getNumArtifactsSkipped() {
            return this.mNumArtifactsSkipped;
        }

        public int getNumArtifactsUploaded() {
            return this.mNumArtifactsUploaded;
        }
    }

    /* loaded from: classes12.dex */
    public enum CrashUploadStatus {
        SUCCESS,
        FAILURE,
        SERVER_ERROR
    }

    public ArtifactUploader(Domain domain, String str, String str2, List<ArtifactSource> list, List<ArtifactProcessor> list2, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil, StatusNotifier statusNotifier, NetworkManager networkManager, boolean z) throws IllegalArgumentException {
        if (domain != null) {
            if (str != null && str.length() != 0) {
                if (str2 == null || str2.length() == 0) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Device type: ", str2, ", is invalid"));
                }
                if (list == null) {
                    throw new IllegalArgumentException("Artifact sources must not be null.");
                }
                if (list2 == null) {
                    throw new IllegalArgumentException("Artifact processors must not be null.");
                }
                if (crashDescriptorDedupeUtil == null) {
                    throw new IllegalArgumentException("CrashDescriptorStorageUtil must not be null.");
                }
                if (statusNotifier == null) {
                    throw new IllegalArgumentException("StatusNotifier must not be null.");
                }
                if (networkManager != null) {
                    this.mDomain = domain;
                    this.mDeviceType = str;
                    this.mDeviceSerialNumber = str2;
                    this.mArtifactSources = list;
                    this.mArtifactProcessors = list2;
                    this.crashDedupeUtil = crashDescriptorDedupeUtil;
                    this.mStatusNotifier = statusNotifier;
                    this.mNetworkManager = networkManager;
                    this.mAllowWANUpload = z;
                    return;
                }
                throw new IllegalArgumentException("NetworkManager must not be null.");
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Device type: ", str, ", is invalid"));
        }
        throw new IllegalArgumentException("Domain must not be null.");
    }

    private void addRequestHeaders(HttpURLConnection httpURLConnection, String str, String str2) {
        httpURLConnection.addRequestProperty("Accept-Encoding", "utf-8");
        httpURLConnection.addRequestProperty("Content-Type", "Application/Raw");
        httpURLConnection.addRequestProperty("X-Anonymous-Tag", this.mDeviceSerialNumber);
        httpURLConnection.addRequestProperty(SipHeaders.SIP_HEADER_DEVICE_TYPE, this.mDeviceType);
        httpURLConnection.addRequestProperty("X-DeviceFirmwareVersion", Build.DISPLAY);
        httpURLConnection.setRequestProperty("X-Content-Type", "CrashReport");
        log.debug(this.TAG, "Key for the file sent to DET", str);
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        httpURLConnection.setRequestProperty("X-Upload-Tag", str2);
    }

    private ArtifactUploadStatus attemptUpload(MetricEvent metricEvent, Artifact artifact, String str, boolean z, InputStream inputStream) throws Exception {
        long currentTimeMillis;
        String format = String.format("%s-%d", artifact.getTag(), Long.valueOf(artifact.getCreationTimeUTCMillis()));
        long currentTimeMillis2 = System.currentTimeMillis();
        HttpURLConnection httpURLConnection = null;
        try {
            String crashDescriptor = artifact.getCrashDescriptor();
            if (crashDescriptor != null && z && !this.crashDedupeUtil.contains(artifact.getTag(), crashDescriptor)) {
                log.debug(this.TAG, "CrashDescriptor: " + crashDescriptor + " not found in SharedPreferences, skipping.", new Object[0]);
                return ArtifactUploadStatus.DE_DUPED;
            }
            metricEvent.addCounter(MetricsConstants.COUNTER_DET_END_POINT + DetEndpointConfig.getServiceEndpoint(this.mDomain), 1.0d);
            HttpURLConnection httpUrlConnection = getHttpUrlConnection(format);
            addRequestHeaders(httpUrlConnection, format, str);
            httpUrlConnection.setConnectTimeout(10000);
            httpUrlConnection.setReadTimeout(60000);
            httpUrlConnection.setFixedLengthStreamingMode(inputStream.available());
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            long available = inputStream.available();
            log.debug(this.TAG, "Upload Size: " + available, new Object[0]);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_CRASH_SIZE, (double) available);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpUrlConnection.getOutputStream());
            copyBytesToOutputStream(inputStream, bufferedOutputStream);
            bufferedOutputStream.flush();
            ArtifactUploadStatus handleResponse = handleResponse(httpUrlConnection);
            metricEvent.addTimer(MetricsConstants.TIMER_INDIVIDUAL_CRASH_FILE_UPLOAD + artifact.getTag(), System.currentTimeMillis() - currentTimeMillis2);
            metricEvent.addTimer(MetricsConstants.BYTES_PER_SECOND_UPLOAD + artifact.getTag(), (available * 1000) / currentTimeMillis);
            httpUrlConnection.disconnect();
            return handleResponse;
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private void buildDedupeMapFromDropbox(DropBoxArtifactSource dropBoxArtifactSource, MetricEvent metricEvent) {
        log.info(this.TAG, "Starting to build descriptor dedupe map", new Object[0]);
        metricEvent.startTimer(MetricsConstants.TIMER_BUILD_COUNTER_TIME);
        while (true) {
            Artifact nextArtifact = dropBoxArtifactSource.getNextArtifact(metricEvent, CrashManagerActions.BUILD_MAP);
            if (nextArtifact != null) {
                try {
                    processArtifact(nextArtifact, CrashManagerActions.BUILD_MAP);
                    nextArtifact.close();
                } catch (IOException e) {
                    log.warn(this.TAG, "Failed to close artifact.", e);
                } catch (Exception e2) {
                    log.warn(this.TAG, "Exception thrown when process artifact.", e2);
                }
                this.crashDedupeUtil.increaseCounter(nextArtifact.getTag(), nextArtifact.getCrashDescriptor());
            } else {
                dropBoxArtifactSource.saveBuildMapIndex();
                this.crashDedupeUtil.persistCrashDescriptors();
                metricEvent.stopTimer(MetricsConstants.TIMER_BUILD_COUNTER_TIME);
                log.info(this.TAG, "Finish building descriptor dedupe map", new Object[0]);
                return;
            }
        }
    }

    private ArtifactProcessor chooseArtifactProcessor(Artifact artifact) {
        for (ArtifactProcessor artifactProcessor : this.mArtifactProcessors) {
            if (artifactProcessor != null && artifactProcessor.canProcessTag(artifact.getTag())) {
                return artifactProcessor;
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No artifact processor available for artifact. Tag:");
        outline107.append(artifact.getTag());
        throw new IllegalStateException(outline107.toString());
    }

    private void copyBytesToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private String getExceptionMessage(Exception exc) {
        if (exc.getCause() == null) {
            return exc.getMessage();
        }
        return exc.getMessage() + " " + exc.getCause().getMessage();
    }

    private HttpURLConnection getHttpUrlConnection(String str) throws IOException {
        return (HttpURLConnection) new URL(DetEndpointConfig.getServiceEndpoint(this.mDomain) + "?key=" + str).openConnection();
    }

    private ArtifactUploadStatus handleResponse(HttpURLConnection httpURLConnection) throws IOException, TimeoutException {
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseCode >= 200 && responseCode < 300) {
            log.info("handleResponse", String.format("Successfully uploaded crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            return ArtifactUploadStatus.SUCCESS;
        } else if (responseCode == 408) {
            log.error("handleResponse", String.format("Http client timeout while uploading crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            throw new TimeoutException(GeneratedOutlineSupport1.outline72("Http client timeout while uploading crash; message: ", responseMessage));
        } else if (responseCode >= 400 && responseCode < 500) {
            log.error("handleResponse", String.format("Client error while uploading crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            return ArtifactUploadStatus.FAILED;
        } else if (responseCode >= 500 && responseCode < 600) {
            return ArtifactUploadStatus.SERVER_ERROR;
        } else {
            log.error("handleResponse", String.format("Unexpected response code while uploading crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            return ArtifactUploadStatus.FAILED;
        }
    }

    private InputStream processArtifact(Artifact artifact, String str) throws Exception {
        return chooseArtifactProcessor(artifact).processArtifact(artifact, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:94:0x0232 A[Catch: all -> 0x0318, TryCatch #22 {all -> 0x0318, blocks: (B:4:0x0011, B:6:0x003a, B:13:0x008a, B:16:0x0095, B:33:0x00c8, B:35:0x00f5, B:36:0x00fd, B:42:0x010f, B:47:0x014c, B:92:0x021e, B:94:0x0232, B:95:0x024d, B:49:0x0174, B:51:0x019c, B:55:0x01ac, B:59:0x01d8, B:101:0x0294, B:108:0x02b7, B:113:0x02d9, B:30:0x00b9), top: B:130:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x024d A[Catch: all -> 0x0318, TryCatch #22 {all -> 0x0318, blocks: (B:4:0x0011, B:6:0x003a, B:13:0x008a, B:16:0x0095, B:33:0x00c8, B:35:0x00f5, B:36:0x00fd, B:42:0x010f, B:47:0x014c, B:92:0x021e, B:94:0x0232, B:95:0x024d, B:49:0x0174, B:51:0x019c, B:55:0x01ac, B:59:0x01d8, B:101:0x0294, B:108:0x02b7, B:113:0x02d9, B:30:0x00b9), top: B:130:0x0011 }] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.device.crashmanager.ArtifactUploader.ArtifactUploaderResult uploadArtifactWithRetries(com.amazon.client.metrics.common.MetricEvent r28, com.amazon.device.crashmanager.Artifact r29, java.lang.String r30, boolean r31) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 804
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.crashmanager.ArtifactUploader.uploadArtifactWithRetries(com.amazon.client.metrics.common.MetricEvent, com.amazon.device.crashmanager.Artifact, java.lang.String, boolean):com.amazon.device.crashmanager.ArtifactUploader$ArtifactUploaderResult");
    }

    protected boolean isConnectionRefusedError(Exception exc) {
        if (!(exc instanceof ConnectException)) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        Throwable cause = exc.getCause();
        if ((cause instanceof ErrnoException) && OsConstants.ECONNREFUSED == ((ErrnoException) cause).errno) {
            return true;
        }
        String message = exc.getMessage();
        return message != null && message.contains("ECONNREFUSED");
    }

    protected ArtifactUploaderResult uploadArtifact(MetricEvent metricEvent, Artifact artifact, String str, boolean z) {
        try {
            log.debug(this.TAG, "About to upload artifact", "Tag", artifact.getTag(), "Creation time UTC", Long.valueOf(artifact.getCreationTimeUTCMillis()), "DeviceType", this.mDeviceType, RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, this.mDeviceSerialNumber);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_UPLOAD_ATTEMPT, 1.0d);
            metricEvent.startTimer(MetricsConstants.TIMER_UPLOAD_TIME);
            return uploadArtifactWithRetries(metricEvent, artifact, str, z);
        } catch (Exception e) {
            log.error(this.TAG, "Exception while uploading crashes", e);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_UPLOAD_FAILED, 1.0d);
            metricEvent.addCounter(MetricsConstants.COUNTER_UPLOAD_UNKNOWN_EXCEPTION, 1.0d);
            metricEvent.addCounter("uploadUnknowException." + e.getClass().getName(), 1.0d);
            return new ArtifactUploaderResult(ArtifactUploadStatus.SKIP, artifact.getCrashDescriptor(), MetricsConstants.STRING_UPLOAD_UNKNOWN_EXCEPTION, getExceptionMessage(e));
        } finally {
            metricEvent.stopTimer(MetricsConstants.TIMER_UPLOAD_TIME);
        }
    }

    public CrashUploadStatistics uploadArtifacts(MetricEvent metricEvent) {
        return uploadArtifacts(metricEvent, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0215, code lost:
        r4 = r16;
        r5 = r17;
        r6 = r18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.device.crashmanager.ArtifactUploader.CrashUploadStatistics uploadArtifacts(com.amazon.client.metrics.common.MetricEvent r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 792
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.crashmanager.ArtifactUploader.uploadArtifacts(com.amazon.client.metrics.common.MetricEvent, java.lang.String):com.amazon.device.crashmanager.ArtifactUploader$CrashUploadStatistics");
    }
}
