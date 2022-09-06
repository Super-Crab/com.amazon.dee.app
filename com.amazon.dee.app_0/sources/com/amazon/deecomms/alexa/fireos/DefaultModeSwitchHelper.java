package com.amazon.deecomms.alexa.fireos;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.android.modeswitch.AlexaModeSwitchManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class DefaultModeSwitchHelper implements ModeSwitchHelper, AlexaModeSwitchManager.AlexaModeSwitchCallback {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DefaultModeSwitchHelper.class);
    private final AlexaModeSwitchManager mAlexaModeSwitchManager;
    private final Context mContext;
    private int previousMode = 0;

    public DefaultModeSwitchHelper(@NonNull Context context) {
        this.mContext = context;
        this.mAlexaModeSwitchManager = (AlexaModeSwitchManager) this.mContext.getSystemService("alexa_modeswitch");
        this.mAlexaModeSwitchManager.registerCallback(this);
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public void detectAndReactToBackgroundModeSwitch() {
        int mode = this.mAlexaModeSwitchManager.getMode();
        if (mode != this.previousMode) {
            this.previousMode = mode;
            if (mode == 1) {
                MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH, Constants.MULTIMODAL_MODE, Constants.MANUAL_DETECTION);
                transitionToMultiModalMode(Constants.MANUAL_DETECTION);
                return;
            }
            MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH, Constants.TABLET_MODE, Constants.MANUAL_DETECTION);
            transitionToTabletMode(Constants.MANUAL_DETECTION);
        }
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public boolean isMultiModalMode() {
        return this.mAlexaModeSwitchManager.getMode() == 1;
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public boolean isTabletMode() {
        return this.mAlexaModeSwitchManager.getMode() == 0;
    }

    public void onModeSwitchComplete(int i) {
        if (i == 1) {
            LOG.i("Mode switched to Multi modal");
            MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH, Constants.MULTIMODAL_MODE, Constants.AUTOMATIC_DETECTION);
            transitionToMultiModalMode(Constants.AUTOMATIC_DETECTION);
        } else if (i != 0) {
        } else {
            LOG.i("Mode switched back to tablet");
            MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH, Constants.TABLET_MODE, Constants.AUTOMATIC_DETECTION);
            transitionToTabletMode(Constants.AUTOMATIC_DETECTION);
        }
    }

    public void onModeSwitchFailure(int i, int i2) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Mode switch failed. Current mode : " + i);
        MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH_FAILURE, String.valueOf(i), String.valueOf(i2));
    }

    public void onModeSwitching(int i) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Starting mode switch with currentMode : " + i);
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public void transitionToMultiModalMode(@NonNull String str) {
        GeneratedOutlineSupport.outline4("Sending broadcast to stop RingService, detected : ", str, LOG);
        MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH_RINGSERVICE_SHUTDOWN, Constants.MULTIMODAL_MODE, str);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.MODE_SWITCH_MULTI_MODAL));
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public void transitionToTabletMode(@NonNull String str) {
        GeneratedOutlineSupport.outline4("Sending broadcast to restart RingService, detected : ", str, LOG);
        MetricsHelper.recordMetricsWithSourceAndEventValue(MetricKeys.MODE_SWITCH_RINGSERVICE_RESTART, Constants.TABLET_MODE, str);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.MODE_SWITCH_TABLET));
    }
}
