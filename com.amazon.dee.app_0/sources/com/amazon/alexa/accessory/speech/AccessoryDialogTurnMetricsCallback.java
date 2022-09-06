package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnUserPerceivedLatencyData;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class AccessoryDialogTurnMetricsCallback implements DialogTurnMetricsCallback {
    private static final String AMA = ".AMA.";
    private static final String TAG = "AccessoryDialogTurnMetricsCallback";
    private final String dialogTurnId;
    private final SpeechSettings speechSettings;

    public AccessoryDialogTurnMetricsCallback(SpeechSettings speechSettings, String str) {
        Preconditions.notNull(speechSettings, "speechSettings");
        Preconditions.notEmpty(str, AccessoryMetricsConstants.DIALOG_TURN_ID);
        this.dialogTurnId = str;
        this.speechSettings = speechSettings;
    }

    private Map<String, Object> createCustomAttributes(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType_accessory", this.speechSettings.getDeviceType());
        hashMap.put("deviceId_accessory", this.speechSettings.getDeviceSerialNumber());
        String deviceFirmwareVersion = this.speechSettings.getDeviceFirmwareVersion();
        if (deviceFirmwareVersion != null) {
            hashMap.put("firmware_accessory", deviceFirmwareVersion);
        }
        hashMap.put("dialogId", str);
        hashMap.put(AccessoryMetricsConstants.DIALOG_TURN_ID, this.dialogTurnId);
        return hashMap;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback
    public void onDialogTurnUserPerceivedLatencyData(DialogTurnUserPerceivedLatencyData dialogTurnUserPerceivedLatencyData) {
        Logger.d("AccessoryDialogTurnMetricsCallback: onDialogTurnUserPerceivedLatencyData for accessory: %s with value: %d", this.speechSettings.getAccessoryIdentifierProvider().getIdentifier(), Long.valueOf(dialogTurnUserPerceivedLatencyData.getActualUpl()));
        Logger.i("%s: onDialogTurnUserPerceivedLatencyData for accessory.", TAG);
        if (dialogTurnUserPerceivedLatencyData.getActualUpl() <= 0) {
            return;
        }
        Map<String, Object> createCustomAttributes = createCustomAttributes(dialogTurnUserPerceivedLatencyData.getDialogRequestId());
        AccessoryMetricsServiceHolder.getInstance().get().recordTime(AccessoryMetricsConstants.USER_PERCEIVED_LATENCY_AUDIO, "alexa_accessories", dialogTurnUserPerceivedLatencyData.getActualUpl(), createCustomAttributes);
        AccessoryMetricsServiceHolder.getInstance().get().recordTime("UserPerceivedLatency.Audio.AMA." + this.speechSettings.getDeviceType(), "alexa_accessories", dialogTurnUserPerceivedLatencyData.getActualUpl(), createCustomAttributes);
    }
}
