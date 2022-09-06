package com.amazon.deecomms.common.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.FileUtils;
import java.io.File;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class MetricsService {
    private static final String E_FILE_WRITE_ERROR = "E_FILE_WRITE_ERROR";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MetricsService.class);
    private static final String LOG_DIR = "logs";
    private final ApplicationManager applicationManager;
    private final CommsIdentityManager commsIdentityManager;
    private final CommsInternal commsInternal;
    private final Context context;
    private final DeviceUtils deviceUtils;
    private final ReentrantLock metadataUpdateLock = new ReentrantLock();
    private final File metricsLogDir;

    public MetricsService(@NonNull CommsIdentityManager commsIdentityManager, @NonNull ApplicationManager applicationManager, @NonNull DeviceUtils deviceUtils, @NonNull CommsInternal commsInternal, @NonNull Context context) {
        this.commsIdentityManager = commsIdentityManager;
        this.applicationManager = applicationManager;
        this.deviceUtils = deviceUtils;
        this.commsInternal = commsInternal;
        this.context = context;
        this.metricsLogDir = new File(context.getCacheDir(), LOG_DIR);
    }

    private void ensureMetadataPopulated(@NonNull CommsMetric commsMetric) {
        if (this.metadataUpdateLock.tryLock()) {
            try {
                Map<String, Object> metadata = commsMetric.getMetadata();
                if (this.commsIdentityManager.isCommsIdPopulated("MetricsService.ensureMetadataPopulated", true)) {
                    metadata.put(MetricKeys.META_HASHED_COMMS_ID, this.commsIdentityManager.getCommsId("MetricsService.ensureMetadataPopulated", true));
                }
                if (this.commsIdentityManager.getUserPFMInfo(true).isPFMset()) {
                    metadata.put("pfm", this.commsIdentityManager.getPreferredMarketplace(true));
                    metadata.put("locale", this.commsInternal.getLocale());
                }
                metadata.put("deviceAccountId", this.deviceUtils.getClientID());
            } finally {
                this.metadataUpdateLock.unlock();
            }
        }
    }

    private void recordMetricsToFile(String str) {
        try {
            FileUtils.writeToFile(FileUtils.getMetricsLogFileName(), str, this.metricsLogDir);
        } catch (Exception e) {
            LOG.e(E_FILE_WRITE_ERROR, e);
        }
    }

    public void recordCounterMetric(@NonNull CounterMetric counterMetric) {
        ensureMetadataPopulated(counterMetric);
        recordMetricsToFile(counterMetric.formatMetric());
        this.applicationManager.recordCounterMetric(counterMetric);
    }

    public void recordTimerMetric(@NonNull TimerMetric timerMetric) {
        ensureMetadataPopulated(timerMetric);
        recordMetricsToFile(timerMetric.formatMetric());
        this.applicationManager.recordTimerMetric(timerMetric);
    }

    public void startTimerMetric(@NonNull CommsMetric commsMetric) {
        ensureMetadataPopulated(commsMetric);
        this.applicationManager.startTimerMetric(commsMetric);
    }

    public void stopTimerMetric(@NonNull CommsMetric commsMetric) {
        ensureMetadataPopulated(commsMetric);
        this.applicationManager.stopTimerMetric(commsMetric);
    }
}
