package com.amazon.alexa.location.phase3.sensor.wifidetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.location.phase3.SensorController;
import com.amazon.alexa.location.phase3.sensor.AbstractSensor;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
/* loaded from: classes9.dex */
public class WifiDetectionSensor extends AbstractSensor {
    private static final String NETWORK_STATE_CHANGE_WIFI_MANAGER = "android.net.wifi.STATE_CHANGE";
    private static final String SENSOR_NAME = "wifi_detection_sensor";
    private Context context;
    private final WifiManager wifiManager;
    private BroadcastReceiver wifiManagerNetworkStateReceiver;
    public static final String WIFI_DETECTION_ACTION = "com.amazon.alexa.location.ACTION_WIFI_DETECTOR";
    private static final Set<String> TARGET_ACTIONS = Collections.singleton(WIFI_DETECTION_ACTION);

    public WifiDetectionSensor(@NonNull Context context, @NonNull LazyComponent<Mobilytics> lazyComponent) {
        super(lazyComponent);
        this.context = context;
        this.wifiManager = (WifiManager) context.getSystemService("wifi");
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @NonNull
    public Set<String> getCorrespondingIntentActions() {
        return TARGET_ACTIONS;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @NonNull
    protected String getSensorName() {
        return SENSOR_NAME;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected List<WifiDataInput> processInternal(@NonNull Intent intent) {
        WifiDataInput wifiDataInput;
        ArrayList arrayList = new ArrayList();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
        if (connectionInfo != null) {
            wifiDataInput = new WifiDataInput(timeInMillis, connectionInfo);
        } else {
            wifiDataInput = new WifiDataInput(timeInMillis);
        }
        arrayList.add(wifiDataInput);
        return arrayList;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected void startInternal(SensorController.SensorState sensorState) {
        if (SensorController.SensorState.NORMAL == sensorState) {
            this.wifiManagerNetworkStateReceiver = new WifiStateBroadcastReceiver();
            this.context.registerReceiver(this.wifiManagerNetworkStateReceiver, new IntentFilter(NETWORK_STATE_CHANGE_WIFI_MANAGER));
        }
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected void stopInternal() {
        BroadcastReceiver broadcastReceiver = this.wifiManagerNetworkStateReceiver;
        if (broadcastReceiver != null) {
            this.context.unregisterReceiver(broadcastReceiver);
            this.wifiManagerNetworkStateReceiver = null;
        }
    }
}
