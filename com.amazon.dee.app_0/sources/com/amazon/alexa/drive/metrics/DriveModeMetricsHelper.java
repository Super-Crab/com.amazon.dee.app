package com.amazon.alexa.drive.metrics;

import android.text.TextUtils;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.mode.ModeService;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public class DriveModeMetricsHelper {
    private static ModeService modeService;
    private final DriverDistractionLog driverDistractionLog;
    private final Lazy<LandingPageMetrics> landingPageMetrics;
    private final Map<String, String> metricsMap;

    public DriveModeMetricsHelper(Lazy<LandingPageMetrics> lazy, DriverDistractionLog driverDistractionLog, ModeService modeService2) {
        this.landingPageMetrics = lazy;
        this.driverDistractionLog = driverDistractionLog;
        modeService = modeService2;
        this.metricsMap = new HashMap();
        this.metricsMap.put("drive-mode/home/index", "Home");
        this.metricsMap.put("drive-mode/navigation/index", "Navigation");
        this.metricsMap.put("v2/comms/drive-mode-landing-page", "Comms");
        this.metricsMap.put("drive-mode/entertainment/index", "Entertainment");
    }

    public static String getAutoModeType() {
        ModeService modeService2 = modeService;
        return modeService2 != null ? modeService2.isDriveModeAccessoryDeviceConnected().getValue().booleanValue() ? "Accessory" : modeService.isAutoBluetoothDeviceConnected().getValue().booleanValue() ? "AutoBluetooth" : "Default" : "";
    }

    public String getChannel(String str) {
        if (this.metricsMap.containsKey(str)) {
            return this.metricsMap.get(str);
        }
        return null;
    }

    public void logChannelSwitchMetrics(String str, String str2) {
        String channel = getChannel(str);
        String channel2 = getChannel(str2);
        if (channel != null && !TextUtils.isEmpty(channel)) {
            this.landingPageMetrics.mo358get().logSessionTimerEndedWithTimers(channel);
        }
        if (!TextUtils.isEmpty(channel2)) {
            this.landingPageMetrics.mo358get().logSwitchToWithTimers(getAutoModeType(), channel2);
            this.driverDistractionLog.updateUiPage(channel2);
        }
    }

    public void startDriverDistraction() {
        this.driverDistractionLog.start();
    }

    public void stopDriverDistraction() {
        this.driverDistractionLog.stop();
    }
}
