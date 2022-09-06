package com.amazon.comms.device;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.KnightDeviceFacade;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.util.Size;
import com.amazon.comms.util.SystemProperty;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class HeadlessDeviceFacade implements AvsDeviceFacade {
    private static final String AUX_PLUGGED_IN_STATE = "aux_plugged_in_state";
    private static final String DEFAULT_DEVICE_TYPE = "DEVICE_TYPE";
    private static final String DO_NOT_DISTURB_INTENT = "com.amazon.alexa.doNotDisturb";
    private static final int SCREEN_HEIGHT = 0;
    public static final int SCREEN_ROTATION = -1;
    private static final int SCREEN_WIDTH = 0;
    static final String USER_SETUP_COMPLETE = "user_setup_complete";
    private static final CommsLogger sLog = CommsLogger.getLogger(HeadlessDeviceFacade.class);
    private final Context mContext;
    private final String deviceType = SystemProperty.getSystemProperty("ro.product.config.type", DEFAULT_DEVICE_TYPE);
    private ContentObserver mUserSetupObserver = null;
    private boolean mUserSetupComplete = false;

    public HeadlessDeviceFacade(Context context) {
        this.mContext = context;
        setOOBEContract();
    }

    private boolean getSettings(String str) {
        try {
            return Settings.Secure.getInt(this.mContext.getContentResolver(), str) != 0;
        } catch (Settings.SettingNotFoundException e) {
            CommsLogger commsLogger = sLog;
            commsLogger.e(str + " setting not found! " + e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOOBEContract() {
        if (this.mUserSetupObserver == null) {
            this.mUserSetupObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.amazon.comms.device.HeadlessDeviceFacade.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    HeadlessDeviceFacade.this.setOOBEContract();
                }
            };
            this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(USER_SETUP_COMPLETE), false, this.mUserSetupObserver);
        }
        this.mUserSetupComplete = getSettings(USER_SETUP_COMPLETE);
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("OOBE done: "), this.mUserSetupComplete, sLog);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void destroy() {
        if (this.mUserSetupObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mUserSetupObserver);
        }
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean enableLocalVideoAtCallInitiation() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void forceResetMicToDefault() {
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public AudioAttributes getAudioAttributesForRingtone() {
        return new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getDefaultOutputDeviceForCallAudio() {
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public Size getDisplaySize() {
        return new Size(0, 0);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getHdmiFlag() {
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getInternalSpeakerFlag() {
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getInternalSpeakerVolume(AudioManager audioManager, int i) {
        return audioManager.getStreamVolume(i);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getScreenRotation() {
        sLog.w("getScreenRotation not supported");
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public String getWakeWord() {
        return KnightDeviceFacade.WakeWordObserver.DEFAULT_WAKEWORD;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isAudioTransferSupported() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isAuxPluggedIn() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), AUX_PLUGGED_IN_STATE, 0) != 0;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isBluetoothA2dpOn() {
        return ((AudioManager) this.mContext.getSystemService("audio")).isBluetoothA2dpOn();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isCameraPermitted() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isCameraPresent() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isCameraShutterCovered() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isDoNotDisturbOn() {
        return "true".equals(Settings.Secure.getString(this.mContext.getContentResolver(), DO_NOT_DISTURB_INTENT));
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isDropInDisallowed() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isExternalCameraPresent() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isExternalCameraSupported() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isFireTvDevice() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isFreeTimeModeOn() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isMmSdkApplication() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isMobileWeblabSupported() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isOOBEComplete() {
        return this.mUserSetupComplete;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isPrivacyModeOn() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isPrivilegedTaskInProgress() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void registerCameraAvailabilityListener(AvsDeviceFacade.CameraAvailabilityListener cameraAvailabilityListener) {
        sLog.w("registerCameraAvailabilityListener not supported");
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void registerPrivacyModeListener(AvsDeviceFacade.PrivacyModeListener privacyModeListener) {
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void setForceNoSpeakerVolume(AudioManager audioManager) {
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void setForceSpeakerVolume(AudioManager audioManager) {
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void unregisterCameraAvailabilityListener(AvsDeviceFacade.CameraAvailabilityListener cameraAvailabilityListener) {
        sLog.w("unregisterCameraAvailabilityListener not supported");
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void unregisterPrivacyModeListener(AvsDeviceFacade.PrivacyModeListener privacyModeListener) {
    }
}
