package com.amazon.deecomms.common.util;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public class DeviceUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceUtils.class);
    @NonNull
    private Context context;
    @NonNull
    private SecuredSharedPreference securedSharedPreference;
    @VisibleForTesting
    AtomicReference<String> clientID = new AtomicReference<>();
    @VisibleForTesting
    AtomicBoolean failedClientIdWasRecorded = new AtomicBoolean();
    private final String MAP_DSN = "MAP_DSN";
    private final String ANDROID_ID = "ANDROID_ID";

    public DeviceUtils(@NonNull Context context, @NonNull SecuredSharedPreference securedSharedPreference) {
        this.context = context;
        this.securedSharedPreference = securedSharedPreference;
    }

    public static boolean isAMPDDevice() {
        boolean isHandsFreeCapable = AMPDInformationProvider.getInstance(CommsDaggerWrapper.getComponent().getContext()).isHandsFreeCapable();
        GeneratedOutlineSupport.outline5("Is AMPD device: ", isHandsFreeCapable, LOG);
        return isHandsFreeCapable;
    }

    public static boolean isCommsNativeDefaulted(@NonNull CapabilitiesManager capabilitiesManager) {
        return isAMPDDevice() && capabilitiesManager.isNativeCallingEnabled();
    }

    @Deprecated
    public String getClientID() {
        return getDeviceSerialNumber();
    }

    public String getDeviceSerialNumber() {
        try {
            return DeviceDataStore.getInstance(this.context).getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
        } catch (DeviceDataStoreException e) {
            LOG.e("Unable to obtain DSN from MAP");
            e.printStackTrace();
            recordInvalidClientIdMetricOnce("MAP_DSN");
            return Constants.UNKNOWN_CLIENT_ID;
        }
    }

    @VisibleForTesting
    void recordInvalidClientIdMetricOnce(@NonNull String str) {
        if (!this.failedClientIdWasRecorded.compareAndSet(false, true)) {
            LOG.d("Invalid client id metric already recorded for this process.");
            return;
        }
        LOG.i("Could not generate a proper client ID.");
        final CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_CACHE_CLIENTID_FAILED);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", String.format("%s: model=%s, api=%d", Build.MANUFACTURER, Build.MODEL, Integer.valueOf(Build.VERSION.SDK_INT)));
        metadata.put("errorSource", str);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.deecomms.common.util.-$$Lambda$DeviceUtils$VD0GWwz2js2v2n0G9FEUydYRWfU
            @Override // java.lang.Runnable
            public final void run() {
                MetricsHelper.recordCounterMetric(CounterMetric.this, Double.valueOf(1.0d));
            }
        });
    }
}
