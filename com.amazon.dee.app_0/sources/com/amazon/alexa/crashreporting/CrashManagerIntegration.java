package com.amazon.alexa.crashreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.CrashReportingMetrics;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.client.metrics.common.NullMetricsFactory;
import com.amazon.device.crashmanager.CrashDetailsCollectable;
import com.amazon.device.crashmanager.CrashDetectionHelper;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class CrashManagerIntegration extends CrashReportingComponent {
    private static final String TAG = "CrashManagerIntegration";
    @SuppressLint({"StaticFieldLeak"})
    private static volatile CrashManagerIntegration singleton;
    private final Context context;
    private final Provider<DeviceInformation> deviceInformation;
    private final Map<String, String> metadata = new HashMap();
    private final Provider<MetricsService> metricsService;

    @VisibleForTesting
    CrashManagerIntegration(Context context, Provider<MetricsService> provider, Provider<DeviceInformation> provider2) {
        this.context = context;
        this.metricsService = provider;
        this.deviceInformation = provider2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void init(Context context, Provider<MetricsService> provider, Provider<DeviceInformation> provider2) {
        synchronized (CrashManagerIntegration.class) {
            if (singleton == null) {
                singleton = new CrashManagerIntegration(context, provider, provider2);
            } else {
                throw new IllegalStateException("Already initialized.");
            }
        }
    }

    private void reportCrashMetrics(Throwable th) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorSource", th.getLocalizedMessage());
        hashMap.put("sendPriority", 1);
        this.metricsService.mo10268get().recordOccurrence("APP_CRASH", CrashReportingMetrics.Component.CRASH_HANDLER, true, hashMap);
        this.metricsService.mo10268get().recordEvent("APP_CRASH_COUNT", CrashReportingMetrics.Component.CRASH_HANDLER, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized CrashManagerIntegration singleton() {
        CrashManagerIntegration crashManagerIntegration;
        synchronized (CrashManagerIntegration.class) {
            if (singleton != null) {
                crashManagerIntegration = singleton;
            } else {
                throw new IllegalStateException("Must call init() before requesting instance().");
            }
        }
        return crashManagerIntegration;
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    public String getFriendlyName() {
        return MetricsConstants.PROGRAM_NAME;
    }

    public /* synthetic */ Map lambda$registerDefaultUncaughtExceptionHandler$0$CrashManagerIntegration(Throwable th) {
        GeneratedOutlineSupport1.outline158("CrashManager is processing ", String.format("%s: %s", th.getClass().getName(), th.getMessage()));
        reportCrashMetrics(th);
        return this.metadata;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    public void putMetadata(@NonNull String str, @NonNull String str2) {
        this.metadata.put(str, str2);
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    void registerDefaultUncaughtExceptionHandler() {
        String str;
        String str2 = "Unknown";
        if (!this.deviceInformation.mo10268get().isFireOS()) {
            try {
                String deviceType = this.deviceInformation.mo10268get().getDeviceType();
                str = this.deviceInformation.mo10268get().getDeviceSerialNumber();
                str2 = deviceType;
            } catch (DeviceInformationException unused) {
                str = str2;
            }
            CrashDetectionHelper.setUpCrashDetection(str2, str, new NullMetricsFactory(), this.context).appendCrashDetailsCollector(new CrashDetailsCollectable() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$CrashManagerIntegration$mYuA2e6EIMl0pgwoq7_Q1ARuSl0
                @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
                public final Map collect(Throwable th) {
                    return CrashManagerIntegration.this.lambda$registerDefaultUncaughtExceptionHandler$0$CrashManagerIntegration(th);
                }
            });
            return;
        }
        throw new IllegalStateException("CrashManager won't run on FireOS.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    public void reportNonFatal(Throwable th) {
        if (!isInitialized()) {
            Log.w(TAG, "CrashManager integration is not initialized yet, so it won't report non-fatal.", th);
        } else {
            CrashDetectionHelper.getInstance().caughtException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    public void setAccount(String str, String str2, String str3) {
    }
}
