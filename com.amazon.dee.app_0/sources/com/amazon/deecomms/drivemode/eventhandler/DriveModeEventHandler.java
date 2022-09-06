package com.amazon.deecomms.drivemode.eventhandler;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mode.ModeConstants;
import com.amazon.alexa.mode.ModeName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DriveModeEventHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DriveModeEventHandler.class);
    private DriveModeSharedPreferencesUseCase mDriveModeSharedPreferencesUseCase;
    private MetricsService mMetricsService;

    @Inject
    public DriveModeEventHandler(DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase, MetricsService metricsService) {
        this.mDriveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
        this.mMetricsService = metricsService;
    }

    public void handleModeSwitch(Message message) {
        CounterMetric counterMetric = new CounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0("comms.eventbus.event.received.", ModeConstants.MODE_SWITCHED_EVENT));
        counterMetric.setCounter(Double.valueOf(1.0d));
        this.mMetricsService.recordCounterMetric(counterMetric);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Mode changed to ");
        outline1.append(message.getPayloadAsString());
        commsLogger.d(outline1.toString());
        if (ModeName.DRIVE_MODE.equals(message.getPayloadAsString())) {
            this.mDriveModeSharedPreferencesUseCase.persistMode(true);
        } else {
            this.mDriveModeSharedPreferencesUseCase.persistMode(false);
        }
    }
}
