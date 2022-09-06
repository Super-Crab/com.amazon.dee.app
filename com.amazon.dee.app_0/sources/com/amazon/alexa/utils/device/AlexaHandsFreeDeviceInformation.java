package com.amazon.alexa.utils.device;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
/* loaded from: classes10.dex */
public class AlexaHandsFreeDeviceInformation {
    private final Context context;

    public AlexaHandsFreeDeviceInformation(Context context) {
        this.context = context;
    }

    public static boolean isCurrentDeviceHandsFree(Context context) {
        return DeviceTypeInformationProvider.getInstance(context).getSupportedDeviceInformation(context) != null;
    }

    public boolean isCurrentDeviceHandsFree() {
        return DeviceTypeInformationProvider.getInstance(this.context).getSupportedDeviceInformation(this.context) != null;
    }

    public boolean isCurrentDeviceMTK() {
        if (DeviceTypeInformationProvider.getInstance(this.context).getSupportedDeviceInformation(this.context) == null) {
            return false;
        }
        return DeviceTypeInformationProvider.getInstance(this.context).getSupportedDeviceInformation(this.context).getVoiceApp().equals(VoiceApp.METRO);
    }

    public boolean isCurrentDeviceTrueTurnkeyAudio() {
        if (!isCurrentDeviceHandsFree()) {
            return false;
        }
        VoiceApp voiceApp = DeviceTypeInformationProvider.getInstance(this.context).getSupportedDeviceInformation(this.context).getVoiceApp();
        int minTrueTurnkeyAudioVersion = voiceApp.getMinTrueTurnkeyAudioVersion();
        PackageInfo packageInfoIfEnabled = AMPDInformationProvider.getInstance(this.context).getPackageInfoIfEnabled(voiceApp.getPackageName());
        return packageInfoIfEnabled != null && packageInfoIfEnabled.versionCode >= minTrueTurnkeyAudioVersion;
    }
}
