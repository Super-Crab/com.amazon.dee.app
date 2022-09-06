package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
import com.amazon.whisperjoin.provisioning.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.amazon.whisperjoin.wifi.WifiNetwork;
import com.amazon.whisperjoin.wifi.WifiScanResult;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
/* loaded from: classes13.dex */
public class WifiSetupMetrics {
    static final String CONNECTION_FAILED_ASSOCIATION_TIMED_OUT_METRIC = "WifiFailureCauseAssociationTimedOut";
    static final String CONNECTION_FAILED_CAPTIVE_PORTAL_METRIC = "WifiFailureCauseCaptivePortal";
    static final String CONNECTION_FAILED_DHCP_LEASE_FAILED_METRIC = "WifiFailureCauseDhcpLeaseFailed";
    static final String CONNECTION_FAILED_ERROR_MESSAGE = "WifiFailureCauseErrorMessage";
    static final String CONNECTION_FAILED_INTERNET_UNREACHABLE_METRIC = "WifiFailureCauseInternetUnreachable";
    static final String CONNECTION_FAILED_IP_CONFLICT_METRIC = "WifiFailureCauseIpConflict";
    static final String CONNECTION_FAILED_LAST_FAILURE_CAUSE = "WifiFailureLastCause";
    static final String CONNECTION_FAILED_LOW_SIGNAL_METRIC = "WifiFailureCauseLowSignal";
    static final String CONNECTION_FAILED_NO_AP_RESPONSE_METRIC = "WifiFailureCauseNoApResponse";
    static final String CONNECTION_FAILED_NO_DHCP_SERVER_METRIC = "WifiFailureCauseNoDhcpServer";
    static final String CONNECTION_FAILED_NO_DNS_SERVER_METRIC = "WifiFailureCauseNoDnsServer";
    static final String CONNECTION_FAILED_NO_NETWORK_AVAILABLE_METRIC = "WifiFailureCauseNoNetworkAvailable";
    static final String CONNECTION_FAILED_OTHER_METRIC = "WifiFailureCauseOther";
    static final String CONNECTION_FAILED_PSK_AUTHENTICATION_METRIC = "WifiFailureCausePskAuthentication";
    static final String CONNECTION_FAILED_UNKNOWN_CODE_METRIC = "WifiFailureCauseUnknown";
    static final String WIFI_NETWORK_CONFIGURED_SUCCESS = "WifiNetworkConfiguredSuccess";
    static final String WIFI_NETWORK_FOUND = "WifiNetworksFound";
    private final MetricHelper mMetricHelper;
    volatile Stack<WifiNetwork> mSavedWifiNetworks = new Stack<>();

    public WifiSetupMetrics(MetricHelper metricHelper) {
        this.mMetricHelper = metricHelper;
    }

    public void onConfiguredNetworkConnected() {
        try {
            this.mMetricHelper.recordCounter(WIFI_NETWORK_CONFIGURED_SUCCESS, 1.0d, this.mSavedWifiNetworks.pop().getKeyMgmt());
        } catch (EmptyStackException unused) {
        }
    }

    public void onListWifiNetworks(List<WifiScanResult> list) {
        if (list == null) {
            return;
        }
        Iterator<WifiScanResult> it2 = list.iterator();
        while (it2.hasNext()) {
            this.mMetricHelper.recordCounter(WIFI_NETWORK_FOUND, 1.0d, it2.next().getKeyMgmt());
        }
    }

    public void onNetworkConnected(WifiNetwork wifiNetwork) {
        WifiNetwork wifiNetwork2 = new WifiNetwork(wifiNetwork);
        if (this.mSavedWifiNetworks.contains(wifiNetwork2)) {
            this.mSavedWifiNetworks.remove(wifiNetwork2);
            this.mMetricHelper.recordCounter(WIFI_NETWORK_CONFIGURED_SUCCESS, 1.0d, wifiNetwork.getKeyMgmt());
        }
    }

    public void onNetworkConnectionFailed(WifiConnectionDetails wifiConnectionDetails) {
        if (wifiConnectionDetails.getSsid() == null || wifiConnectionDetails.getKeyMgmt() == null) {
            return;
        }
        WifiNetwork wifiNetwork = new WifiNetwork(wifiConnectionDetails.getSsid(), wifiConnectionDetails.getKeyMgmt());
        if (!this.mSavedWifiNetworks.contains(wifiNetwork)) {
            return;
        }
        this.mSavedWifiNetworks.remove(wifiNetwork);
        this.mMetricHelper.recordCounter(WIFI_NETWORK_CONFIGURED_SUCCESS, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, wifiConnectionDetails.getKeyMgmt());
        this.mMetricHelper.recordString(CONNECTION_FAILED_LAST_FAILURE_CAUSE, wifiConnectionDetails.getState(), new Object[0]);
        switch (wifiConnectionDetails.getState().intValue()) {
            case -12:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_ASSOCIATION_TIMED_OUT_METRIC, 1.0d, new Object[0]);
                break;
            case -11:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_CAPTIVE_PORTAL_METRIC, 1.0d, new Object[0]);
                break;
            case -10:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_INTERNET_UNREACHABLE_METRIC, 1.0d, new Object[0]);
                break;
            case -9:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_IP_CONFLICT_METRIC, 1.0d, new Object[0]);
                break;
            case -8:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_NO_DNS_SERVER_METRIC, 1.0d, new Object[0]);
                break;
            case -7:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_DHCP_LEASE_FAILED_METRIC, 1.0d, new Object[0]);
                break;
            case -6:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_NO_DHCP_SERVER_METRIC, 1.0d, new Object[0]);
                break;
            case -5:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_NO_AP_RESPONSE_METRIC, 1.0d, new Object[0]);
                break;
            case -4:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_PSK_AUTHENTICATION_METRIC, 1.0d, new Object[0]);
                break;
            case -3:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_LOW_SIGNAL_METRIC, 1.0d, new Object[0]);
                break;
            case -2:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_NO_NETWORK_AVAILABLE_METRIC, 1.0d, new Object[0]);
                break;
            case -1:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_OTHER_METRIC, 1.0d, new Object[0]);
                break;
            default:
                this.mMetricHelper.recordCounter(CONNECTION_FAILED_UNKNOWN_CODE_METRIC, 1.0d, new Object[0]);
                break;
        }
        String message = wifiConnectionDetails.getMessage();
        if (message == null || message.length() <= 0) {
            return;
        }
        this.mMetricHelper.recordString(CONNECTION_FAILED_ERROR_MESSAGE, message, new Object[0]);
    }

    public void onNetworkSaved(WifiConfiguration wifiConfiguration) {
        this.mSavedWifiNetworks.push(new WifiNetwork(wifiConfiguration));
    }
}
