package com.amazon.whisperjoin.credentiallocker;

import com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics;
import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.amazon.whisperjoin.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.wifi.WifiNetwork;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes13.dex */
class SetupAttemptMetricHelper {
    static final String NONE_LOCKER_NETWORK_CREATED_METRIC = "NoneLockerNetworkCreated";
    static final String NONE_LOCKER_NETWORK_FETCHED_METRIC = "NoneLockerNetworkFetched";
    static final String NONE_LOCKER_NETWORK_UPDATED_METRIC = "NoneLockerNetworkUpdated";
    static final String WEP_LOCKER_NETWORK_CREATED_METRIC = "WepLockerNetworkCreated";
    static final String WEP_LOCKER_NETWORK_FETCHED_METRIC = "WepLockerNetworkFetched";
    static final String WEP_LOCKER_NETWORK_UPDATED_METRIC = "WepLockerNetworkUpdated";
    static final String WPA_LOCKER_NETWORK_CREATED_METRIC = "WpaLockerNetworkCreated";
    static final String WPA_LOCKER_NETWORK_FETCHED_METRIC = "WpaLockerNetworkFetched";
    static final String WPA_LOCKER_NETWORK_UPDATED_METRIC = "WpaLockerNetworkUpdated";
    final Set<WifiNetwork> mFetchedWifiConfigurations = new HashSet();

    /* renamed from: com.amazon.whisperjoin.credentiallocker.SetupAttemptMetricHelper$1  reason: invalid class name */
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

    private SetupAttemptMetricHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized SetupAttemptMetricHelper getInstance() {
        SetupAttemptMetricHelper setupAttemptMetricHelper;
        synchronized (SetupAttemptMetricHelper.class) {
            setupAttemptMetricHelper = new SetupAttemptMetricHelper();
        }
        return setupAttemptMetricHelper;
    }

    private void onLockerNetworkCreated(SetupAttemptMetrics setupAttemptMetrics, WifiKeyManagement wifiKeyManagement) {
        if (wifiKeyManagement == null) {
            return;
        }
        int ordinal = wifiKeyManagement.ordinal();
        if (ordinal == 0) {
            setupAttemptMetrics.customMetrics.addCounter(NONE_LOCKER_NETWORK_CREATED_METRIC, 1.0d);
        } else if (ordinal == 1) {
            setupAttemptMetrics.customMetrics.addCounter(WPA_LOCKER_NETWORK_CREATED_METRIC, 1.0d);
        } else if (ordinal != 2) {
        } else {
            setupAttemptMetrics.customMetrics.addCounter(WEP_LOCKER_NETWORK_CREATED_METRIC, 1.0d);
        }
    }

    private static void onLockerNetworkUpdated(SetupAttemptMetrics setupAttemptMetrics, WifiKeyManagement wifiKeyManagement) {
        if (wifiKeyManagement == null) {
            return;
        }
        int ordinal = wifiKeyManagement.ordinal();
        if (ordinal == 0) {
            setupAttemptMetrics.customMetrics.addCounter(NONE_LOCKER_NETWORK_UPDATED_METRIC, 1.0d);
        } else if (ordinal == 1) {
            setupAttemptMetrics.customMetrics.addCounter(WPA_LOCKER_NETWORK_UPDATED_METRIC, 1.0d);
        } else if (ordinal != 2) {
        } else {
            setupAttemptMetrics.customMetrics.addCounter(WEP_LOCKER_NETWORK_UPDATED_METRIC, 1.0d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onLockerNetworksDeleted() {
        this.mFetchedWifiConfigurations.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onLockerNetworksFetched(SetupAttemptMetrics setupAttemptMetrics, List<WifiConfiguration> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.mFetchedWifiConfigurations.clear();
        for (WifiConfiguration wifiConfiguration : list) {
            this.mFetchedWifiConfigurations.add(new WifiNetwork(wifiConfiguration));
        }
        for (WifiConfiguration wifiConfiguration2 : list) {
            int ordinal = wifiConfiguration2.getKeyMgmt().ordinal();
            if (ordinal == 0) {
                setupAttemptMetrics.customMetrics.addCounter(NONE_LOCKER_NETWORK_FETCHED_METRIC, 1.0d);
            } else if (ordinal == 1) {
                setupAttemptMetrics.customMetrics.addCounter(WPA_LOCKER_NETWORK_FETCHED_METRIC, 1.0d);
            } else if (ordinal == 2) {
                setupAttemptMetrics.customMetrics.addCounter(WEP_LOCKER_NETWORK_FETCHED_METRIC, 1.0d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onLockerNetworksSaved(SetupAttemptMetrics setupAttemptMetrics, List<WifiConfiguration> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (WifiConfiguration wifiConfiguration : list) {
            if (this.mFetchedWifiConfigurations.contains(new WifiNetwork(wifiConfiguration))) {
                onLockerNetworkUpdated(setupAttemptMetrics, wifiConfiguration.getKeyMgmt());
            } else {
                onLockerNetworkCreated(setupAttemptMetrics, wifiConfiguration.getKeyMgmt());
            }
        }
        for (WifiConfiguration wifiConfiguration2 : list) {
            this.mFetchedWifiConfigurations.add(new WifiNetwork(wifiConfiguration2));
        }
    }
}
