package com.amazon.alexa.wakeword.pryon;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetectorCompat;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class WakeWordDetectorProvider implements Provider<PryonWakeWordDetectorCompat> {
    @VisibleForTesting
    static final int DEFAULT_INITIALIZATION_FAILURE = 0;
    @VisibleForTesting
    static final String DEFAULT_MODEL_MD5 = "";
    private static final String TAG = "WakeWordDetectorProvider";
    private final Map<PryonLite5000.ClientProperty, Integer> clientPropertiesMap = new HashMap();
    private final Provider<PryonLite5000.Config> configProvider;
    private final LocaleProvider localeProvider;
    private final WakeWordDetectionMetricsListener metricsListener;
    private final TimeProvider timeProvider;
    private PryonWakeWordDetectorCompat wakeWordDetectorCompat;
    private final WakeWordDownloadManager wakeWordDownloadManager;
    private final WakeWordModelAuthority wakeWordModelAuthority;

    public WakeWordDetectorProvider(WakeWordModelAuthority wakeWordModelAuthority, LocaleProvider localeProvider, TimeProvider timeProvider, Provider<PryonLite5000.Config> provider, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, WakeWordDownloadManager wakeWordDownloadManager) {
        this.wakeWordModelAuthority = wakeWordModelAuthority;
        this.localeProvider = localeProvider;
        this.timeProvider = timeProvider;
        this.metricsListener = wakeWordDetectionMetricsListener;
        this.configProvider = provider;
        this.wakeWordDownloadManager = wakeWordDownloadManager;
    }

    private long getCurrentTime() {
        return this.timeProvider.elapsedRealTime();
    }

    private PryonLite5000.Config getPryonConfig(byte[] bArr) {
        PryonLite5000.Config mo2864get = this.configProvider.mo2864get();
        mo2864get.wakewordModel = bArr;
        return mo2864get;
    }

    private byte[] getWakeWordModel(Locale locale) throws IOException {
        return this.wakeWordModelAuthority.getWakeWordModel(locale != null ? locale.toLanguageTag() : null);
    }

    @VisibleForTesting
    PryonWakeWordDetectorCompat createWakeWordDetector(PryonLite5000.Config config) {
        return new PryonWakeWordDetectorCompat(config, false, false);
    }

    public synchronized void downloadWakeWordModel(Locale locale) {
        this.wakeWordDownloadManager.downloadWakeWordModelAsync(new WakeWordModelUserParams(locale.toLanguageTag(), Arrays.asList("alexa")));
    }

    public synchronized void initWakeWordModelUpdates() {
        this.wakeWordDownloadManager.initUpdatesCycle();
    }

    public synchronized void release() {
        this.wakeWordDownloadManager.release();
        if (this.wakeWordDetectorCompat == null) {
            return;
        }
        this.wakeWordDetectorCompat.release();
        this.wakeWordDetectorCompat = null;
    }

    public synchronized void resetPryon() {
        long currentTime;
        int initialize;
        Log.i(TAG, "reset");
        if (this.wakeWordDetectorCompat == null) {
            return;
        }
        PryonLite5000 pryonLite = this.wakeWordDetectorCompat.getPryonLite();
        if (pryonLite == null) {
            return;
        }
        try {
            currentTime = getCurrentTime();
            byte[] wakeWordModel = getWakeWordModel(this.localeProvider.getLocale());
            Log.i(TAG, "Calling PryonLite destroy() from WakeWordDetectorProvider.resetPryon()...");
            int destroy = pryonLite.destroy();
            if (destroy == 0) {
                Log.i(TAG, "PryonLite destroy() reports initialized object destroyed successfully.");
            } else if (destroy == -1) {
                Log.i(TAG, "PryonLite destroy() reports object is not initialized.");
            } else {
                throw new IllegalStateException("Failed to destroy PryonWakeWordDetector. ErrorCode " + destroy);
            }
            Log.i(TAG, "Calling PryonLite initialize() from WakeWordDetectorProvider.resetPryon()...");
            initialize = pryonLite.initialize(getPryonConfig(wakeWordModel));
        } catch (Exception e) {
            Log.e(TAG, "failed to reinitialize wakeword engine", e);
            this.metricsListener.onPryonInitializationFailure(0, CheckSumUtils.getMD5(null));
            release();
        }
        if (initialize == 0) {
            this.metricsListener.onPryonReset(getCurrentTime() - currentTime);
            for (Map.Entry<PryonLite5000.ClientProperty, Integer> entry : this.clientPropertiesMap.entrySet()) {
                String str = TAG;
                Log.i(str, "resetPryon | entry | key property id: " + entry.getKey().propertyId + " key group id: " + entry.getKey().groupId + " | value: " + entry.getValue());
                pryonLite.setClientProperty(entry.getKey(), entry.getValue().intValue());
            }
            return;
        }
        String str2 = TAG;
        Log.e(str2, "PryonLite initialize() failed, error = " + initialize);
        throw new IllegalStateException("Failed to init PryonWakeWordDetector. ErrorCode " + initialize);
    }

    public synchronized void setClientProperty(PryonLite5000.ClientProperty clientProperty, int i) {
        String str = TAG;
        Log.i(str, "setClientProperty | clientProperty group id: " + clientProperty.groupId + " property id: " + clientProperty.propertyId + " | clientPropertyState: " + i);
        this.clientPropertiesMap.put(clientProperty, Integer.valueOf(i));
        if (this.wakeWordDetectorCompat != null && this.wakeWordDetectorCompat.getPryonLite() != null) {
            this.wakeWordDetectorCompat.getPryonLite().setClientProperty(clientProperty, i);
            return;
        }
        Log.e(TAG, "setClientProperty | unable to set property when pryon isn't up and running");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.utils.Provider
    /* renamed from: get */
    public synchronized PryonWakeWordDetectorCompat mo2864get() {
        if (this.wakeWordDetectorCompat == null) {
            long currentTime = getCurrentTime();
            try {
                byte[] wakeWordModel = getWakeWordModel(this.localeProvider.getLocale());
                this.wakeWordDetectorCompat = createWakeWordDetector(getPryonConfig(wakeWordModel));
                if (this.wakeWordDetectorCompat.getPryonLite() == null) {
                    this.metricsListener.onPryonInitializationFailure(this.wakeWordDetectorCompat.getPryonErrorCode(), CheckSumUtils.getMD5(wakeWordModel));
                } else {
                    this.metricsListener.onPryonInitializationSuccess(getCurrentTime() - currentTime, CheckSumUtils.getMD5(wakeWordModel));
                }
            } catch (Exception e) {
                Log.e(TAG, "failed to create and initialize wakeword engine", e);
                this.metricsListener.onPryonInitializationFailure(0, "");
                release();
            }
        }
        return this.wakeWordDetectorCompat;
    }
}
