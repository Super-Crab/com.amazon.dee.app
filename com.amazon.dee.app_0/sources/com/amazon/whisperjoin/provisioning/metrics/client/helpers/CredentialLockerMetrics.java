package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
import com.amazon.whisperjoin.wifi.WifiKeyManagement;
/* loaded from: classes13.dex */
public class CredentialLockerMetrics {
    private static final String NONE_LOCKER_NETWORK_SUCCESSFULLY_USED_METRIC = "NoneLockerNetworkSuccessfullyUsed";
    private static final String NONE_LOCKER_NETWORK_USED_METRIC = "NoneLockerNetworkUsed";
    private static final String WEP_LOCKER_NETWORK_SUCCESSFULLY_USED_METRIC = "WepLockerNetworkSuccessfullyUsed";
    private static final String WEP_LOCKER_NETWORK_USED_METRIC = "WepLockerNetworkUsed";
    private static final String WPA_LOCKER_NETWORK_SUCCESSFULLY_USED_METRIC = "WpaLockerNetworkSuccessfullyUsed";
    private static final String WPA_LOCKER_NETWORK_USED_METRIC = "WpaLockerNetworkUsed";
    private final MetricHelper mMetricHelper;

    /* renamed from: com.amazon.whisperjoin.provisioning.metrics.client.helpers.CredentialLockerMetrics$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public CredentialLockerMetrics(MetricHelper metricHelper) {
        this.mMetricHelper = metricHelper;
    }

    public void onLockerNetworkUsed(WifiKeyManagement wifiKeyManagement, boolean z) {
        if (wifiKeyManagement == null) {
            return;
        }
        int ordinal = wifiKeyManagement.ordinal();
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        if (ordinal == 0) {
            this.mMetricHelper.recordCounter(NONE_LOCKER_NETWORK_USED_METRIC, 1.0d, new Object[0]);
            MetricHelper metricHelper = this.mMetricHelper;
            if (z) {
                d = 1.0d;
            }
            metricHelper.recordCounter(NONE_LOCKER_NETWORK_SUCCESSFULLY_USED_METRIC, d, new Object[0]);
        } else if (ordinal == 1) {
            this.mMetricHelper.recordCounter(WPA_LOCKER_NETWORK_USED_METRIC, 1.0d, new Object[0]);
            MetricHelper metricHelper2 = this.mMetricHelper;
            if (z) {
                d = 1.0d;
            }
            metricHelper2.recordCounter(WPA_LOCKER_NETWORK_SUCCESSFULLY_USED_METRIC, d, new Object[0]);
        } else if (ordinal != 2) {
        } else {
            this.mMetricHelper.recordCounter(WEP_LOCKER_NETWORK_USED_METRIC, 1.0d, new Object[0]);
            MetricHelper metricHelper3 = this.mMetricHelper;
            if (z) {
                d = 1.0d;
            }
            metricHelper3.recordCounter(WEP_LOCKER_NETWORK_SUCCESSFULLY_USED_METRIC, d, new Object[0]);
        }
    }
}
