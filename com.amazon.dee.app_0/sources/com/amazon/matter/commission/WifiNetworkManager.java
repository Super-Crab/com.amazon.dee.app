package com.amazon.matter.commission;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import chip.devicecontroller.ChipClusters;
import chip.devicecontroller.GetConnectedDeviceCallbackJni;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.matter.MatterClient;
import com.amazon.matter.commission.WifiNetworkManager;
import com.amazon.matter.data.AddAndEnableNetworkResponse;
import com.amazon.matter.data.AddAndEnableNetworkStatus;
import com.amazon.matter.data.MatterError;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.amazon.matter.metrics.MatterMetricsService;
import com.google.gson.Gson;
import java.nio.charset.Charset;
/* loaded from: classes8.dex */
public class WifiNetworkManager {
    private static final long ENABLE_NETWORK_TIMEOUT = 10000;
    private static final Gson GSON = new Gson();
    private static final String TAG = "WifiNetworkManager";
    private Context context;
    private EventBusHelper eventBusHelper;
    private MatterMetricsService metricsService;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.matter.commission.WifiNetworkManager$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    public class AnonymousClass1 implements GetConnectedDeviceCallbackJni.GetConnectedDeviceCallback {
        final /* synthetic */ String val$pwd;
        final /* synthetic */ String val$ssid;
        final /* synthetic */ MobilyticsMetricsTimer val$timer;

        AnonymousClass1(MobilyticsMetricsTimer mobilyticsMetricsTimer, String str, String str2) {
            this.val$timer = mobilyticsMetricsTimer;
            this.val$ssid = str;
            this.val$pwd = str2;
        }

        public /* synthetic */ void lambda$onDeviceConnected$0$WifiNetworkManager$1(long j, final MobilyticsMetricsTimer mobilyticsMetricsTimer, final String str, String str2) {
            final ChipClusters.NetworkCommissioningCluster networkCommissioningCluster = new ChipClusters.NetworkCommissioningCluster(j, 0);
            ChipClusters.NetworkCommissioningCluster.AddWiFiNetworkResponseCallback addWiFiNetworkResponseCallback = new ChipClusters.NetworkCommissioningCluster.AddWiFiNetworkResponseCallback() { // from class: com.amazon.matter.commission.WifiNetworkManager.1.1
                @Override // chip.devicecontroller.ChipClusters.NetworkCommissioningCluster.AddWiFiNetworkResponseCallback
                public void onError(Exception exc) {
                    Log.e(WifiNetworkManager.TAG, "addWifiNetwork failed.", exc);
                    WifiNetworkManager.this.metricsService.recordErrorMetric(MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST, MatterErrorType.ADD_AND_ENABLE_NETWORK_ERROR_ADD_FAILURE);
                    MatterError matterError = new MatterError(MatterErrorType.ADD_AND_ENABLE_NETWORK_ERROR_ADD_FAILURE, "");
                    WifiNetworkManager.this.metricsService.recordEventTime(mobilyticsMetricsTimer);
                    WifiNetworkManager.this.eventBusHelper.sendMessageToEventBus(MatterEventType.ADD_AND_ENABLE_NETWORK_RESPONSE_ERROR, WifiNetworkManager.GSON.toJson(matterError));
                }

                @Override // chip.devicecontroller.ChipClusters.NetworkCommissioningCluster.AddWiFiNetworkResponseCallback
                public void onSuccess(int i, String str3) {
                    String str4 = WifiNetworkManager.TAG;
                    Log.i(str4, "addWifiNetwork succeeded:" + str3);
                    networkCommissioningCluster.enableNetwork(new ChipClusters.NetworkCommissioningCluster.EnableNetworkResponseCallback() { // from class: com.amazon.matter.commission.WifiNetworkManager.1.1.1
                        @Override // chip.devicecontroller.ChipClusters.NetworkCommissioningCluster.EnableNetworkResponseCallback
                        public void onError(Exception exc) {
                            Log.e(WifiNetworkManager.TAG, "enableNetwork failed.");
                            WifiNetworkManager.this.metricsService.recordErrorMetric(MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST, MatterErrorType.ADD_AND_ENABLE_NETWORK_ERROR_ENABLE_FAILURE);
                            AddAndEnableNetworkResponse addAndEnableNetworkResponse = new AddAndEnableNetworkResponse(AddAndEnableNetworkStatus.ADD_AND_ENABLE_NETWORK_SUCCESS);
                            WifiNetworkManager.this.metricsService.recordEventTime(mobilyticsMetricsTimer);
                            WifiNetworkManager.this.eventBusHelper.sendMessageToEventBus(MatterEventType.ADD_AND_ENABLE_NETWORK_RESPONSE_SUCCESS, WifiNetworkManager.GSON.toJson(addAndEnableNetworkResponse));
                        }

                        @Override // chip.devicecontroller.ChipClusters.NetworkCommissioningCluster.EnableNetworkResponseCallback
                        public void onSuccess(int i2, String str5) {
                            Log.i(WifiNetworkManager.TAG, "enableNetwork succeeded.");
                            WifiNetworkManager.this.metricsService.recordSuccessMetric(MatterEventType.ADD_AND_ENABLE_NETWORK_RESPONSE_SUCCESS);
                            AddAndEnableNetworkResponse addAndEnableNetworkResponse = new AddAndEnableNetworkResponse(AddAndEnableNetworkStatus.ADD_AND_ENABLE_NETWORK_SUCCESS);
                            WifiNetworkManager.this.metricsService.recordEventTime(mobilyticsMetricsTimer);
                            WifiNetworkManager.this.eventBusHelper.sendMessageToEventBus(MatterEventType.ADD_AND_ENABLE_NETWORK_RESPONSE_SUCCESS, WifiNetworkManager.GSON.toJson(addAndEnableNetworkResponse));
                        }
                    }, str.getBytes(Charset.forName("UTF-8")), 0L, 10000L);
                }
            };
            String unused = WifiNetworkManager.TAG;
            networkCommissioningCluster.addWiFiNetwork(addWiFiNetworkResponseCallback, str.getBytes(Charset.forName("UTF-8")), str2.getBytes(Charset.forName("UTF-8")), 0L, 10000L);
        }

        @Override // chip.devicecontroller.GetConnectedDeviceCallbackJni.GetConnectedDeviceCallback
        public void onConnectionFailure(long j, Exception exc) {
            Log.e(WifiNetworkManager.TAG, "GetConnectedDevice.onConnectionFailure called.");
            WifiNetworkManager.this.metricsService.recordErrorMetric(MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST, MatterErrorType.ADD_AND_ENABLE_NETWORK_ERROR_CONNECTION_FAILURE);
            WifiNetworkManager.this.metricsService.recordEventTime(this.val$timer);
            WifiNetworkManager.this.eventBusHelper.sendMessageToEventBus(MatterEventType.ADD_AND_ENABLE_NETWORK_RESPONSE_ERROR, WifiNetworkManager.GSON.toJson(new MatterError(MatterErrorType.ADD_AND_ENABLE_NETWORK_ERROR_CONNECTION_FAILURE, "")));
        }

        @Override // chip.devicecontroller.GetConnectedDeviceCallbackJni.GetConnectedDeviceCallback
        public void onDeviceConnected(final long j) {
            Handler handler = new Handler(Looper.getMainLooper());
            final MobilyticsMetricsTimer mobilyticsMetricsTimer = this.val$timer;
            final String str = this.val$ssid;
            final String str2 = this.val$pwd;
            handler.post(new Runnable() { // from class: com.amazon.matter.commission.-$$Lambda$WifiNetworkManager$1$90PIWnjGiAISGA9G73ibVa6AnBc
                @Override // java.lang.Runnable
                public final void run() {
                    WifiNetworkManager.AnonymousClass1.this.lambda$onDeviceConnected$0$WifiNetworkManager$1(j, mobilyticsMetricsTimer, str, str2);
                }
            });
        }
    }

    public WifiNetworkManager(Context context, EventBusHelper eventBusHelper, MatterMetricsService matterMetricsService) {
        this.context = context;
        this.eventBusHelper = eventBusHelper;
        this.metricsService = matterMetricsService;
    }

    public void addAndEnableNetwork(long j, String str, String str2, MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        String str3 = "addAndEnableNetwork called with nodeId:" + j;
        MatterClient.getMatterClient(this.context).getConnectedDevicePointer(j, new AnonymousClass1(mobilyticsMetricsTimer, str, str2));
    }
}
