package com.amazon.deecomms.calling.telecom.audiomanagement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.media.AudioManager;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class TelecomCallAudioManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomCallAudioManager.class);
    private final AudioManager audioManager;
    private Connection currentConnection;
    private final TelecomBridge telecomBridge;

    @Inject
    public TelecomCallAudioManager(@NonNull TelecomBridge telecomBridge, @NonNull AudioManager audioManager) {
        this.telecomBridge = telecomBridge;
        this.audioManager = audioManager;
    }

    @TargetApi(28)
    private List<BluetoothDevice> getBluetoothDevices() {
        if (Utils.isPieAndAbove()) {
            this.currentConnection = this.telecomBridge.getCurrentConnection();
            Connection connection = this.currentConnection;
            if (connection == null) {
                return Collections.emptyList();
            }
            CallAudioState callAudioState = connection.getCallAudioState();
            if (callAudioState == null) {
                return Collections.emptyList();
            }
            return (List) callAudioState.getSupportedBluetoothDevices();
        }
        return Collections.emptyList();
    }

    @TargetApi(28)
    public String getActiveBluetoothDevice() {
        CallAudioState callAudioState;
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        if (connection == null || (callAudioState = connection.getCallAudioState()) == null) {
            return "";
        }
        if (callAudioState.getActiveBluetoothDevice() == null) {
            return AudioRoutes.BLUETOOTH.toString();
        }
        return callAudioState.getActiveBluetoothDevice().getName();
    }

    @TargetApi(26)
    public String getActiveRoute() {
        CallAudioState callAudioState;
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        return (connection == null || (callAudioState = connection.getCallAudioState()) == null) ? "" : CallAudioState.audioRouteToString(callAudioState.getRoute());
    }

    @TargetApi(26)
    public List<String> getSupportedAudioRoutes() {
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        if (connection == null) {
            LOG.e("CurrentConnection was null");
            return Collections.emptyList();
        }
        CallAudioState callAudioState = connection.getCallAudioState();
        if (callAudioState == null) {
            LOG.e("CallAudioState was null");
            return Collections.emptyList();
        }
        String audioRouteToString = CallAudioState.audioRouteToString(callAudioState.getSupportedRouteMask());
        CommsLogger commsLogger = LOG;
        commsLogger.i(" List of supported routes : " + audioRouteToString);
        ArrayList newArrayList = Lists.newArrayList(Splitter.on((char) JsonReaderKt.COMMA).trimResults().omitEmptyStrings().split(audioRouteToString));
        if (newArrayList.remove(AudioRoutes.WIRED_HEADSET)) {
            newArrayList.add(AudioRoutes.WIREDHEADSET.toString());
        }
        if (Utils.isPieAndAbove() && newArrayList.remove(AudioRoutes.BLUETOOTH.toString().toUpperCase())) {
            newArrayList.addAll(Lists.transform(getBluetoothDevices(), $$Lambda$baz_ihVg47DUbdRkUwFZmpwm_yQ.INSTANCE));
        }
        return Lists.transform(newArrayList, $$Lambda$PSHtEO4PPN1cPnIe8MFPeU5QIdY.INSTANCE);
    }

    @TargetApi(28)
    @VisibleForTesting
    public void setActiveBluetoothDevice(@NonNull BluetoothDevice bluetoothDevice) {
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        if (connection != null) {
            connection.requestBluetoothAudio(bluetoothDevice);
        }
    }

    @TargetApi(26)
    @VisibleForTesting
    public void setAudioRoute(int i) {
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        if (connection != null) {
            connection.setAudioRoute(i);
        }
    }

    @TargetApi(26)
    public void setDefaultAudioRoute(boolean z) {
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        if (connection == null) {
            LOG.e("Unable to set default audio route. There is no telecom connection");
            return;
        }
        CallAudioState callAudioState = connection.getCallAudioState();
        if (callAudioState == null) {
            LOG.e("CallAudioState was null");
            return;
        }
        String audioRouteToString = CallAudioState.audioRouteToString(callAudioState.getSupportedRouteMask());
        CommsLogger commsLogger = LOG;
        commsLogger.i(" List of supported routes : " + audioRouteToString);
        if (audioRouteToString.contains("BLUETOOTH")) {
            setAudioRoute(2);
        } else if (audioRouteToString.contains(AudioRoutes.WIRED_HEADSET)) {
            setAudioRoute(5);
        } else if (!z) {
        } else {
            setAudioRoute(8);
        }
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(26)
    public void toggleSpeaker() {
        CallAudioState callAudioState;
        this.currentConnection = this.telecomBridge.getCurrentConnection();
        Connection connection = this.currentConnection;
        if (connection == null || (callAudioState = connection.getCallAudioState()) == null) {
            return;
        }
        if (callAudioState.getRoute() != 1 && callAudioState.getRoute() != 4 && callAudioState.getRoute() != 5) {
            setAudioRoute(5);
        } else {
            setAudioRoute(8);
        }
    }

    @TargetApi(28)
    public void setActiveBluetoothDevice(@NonNull String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Setting active bluetooth audio route to : " + str);
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.ROUTE_SELECTED);
        generateClickstream.getMetadata().put("EventValue", "BluetoothDevice");
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(1.0d));
        for (BluetoothDevice bluetoothDevice : getBluetoothDevices()) {
            if (str.equalsIgnoreCase(bluetoothDevice.getName())) {
                setActiveBluetoothDevice(bluetoothDevice);
                return;
            }
        }
        GeneratedOutlineSupport.outline3("There was no bluetooth device found with the name : ", str, LOG);
    }

    @TargetApi(26)
    public void setAudioRoute(@NonNull String str) {
        char c;
        LOG.i("Changing audio route to : " + str);
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.ROUTE_SELECTED);
        generateClickstream.getMetadata().put("EventValue", str);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(1.0d));
        String lowerCase = str.toLowerCase();
        int hashCode = lowerCase.hashCode();
        if (hashCode == -2008522753) {
            if (lowerCase.equals("speaker")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == -874598431) {
            if (lowerCase.equals("wired headset")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != -805885608) {
            if (hashCode == 1968882350 && lowerCase.equals("bluetooth")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (lowerCase.equals("earpiece")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            setAudioRoute(1);
        } else if (c == 1) {
            setAudioRoute(8);
        } else if (c == 2) {
            setAudioRoute(4);
        } else if (c != 3) {
            setAudioRoute(1);
        } else {
            setAudioRoute(2);
        }
        TelecomAudioUtils.verifyRouteSelected(this.audioManager, str);
    }
}
