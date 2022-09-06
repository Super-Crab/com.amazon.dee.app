package com.amazon.comms.device;

import amazon.media.AmazonAudioManager;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.Point;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.KnightDeviceFacade;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.CommsLoggerAspect;
import com.amazon.comms.log.annotation.Log;
import com.amazon.comms.util.DeviceProperties;
import com.amazon.comms.util.Size;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
/* loaded from: classes11.dex */
public class FireTVDeviceFacade implements AvsDeviceFacade {
    private static final int AMAZON_AUDIO_MANAGER_DUAL_MODE_FEATURE_VERSION = 14;
    private static final String AMAZON_AUDIO_MANAGER_FEATURE_NAME = "com.fireos.sdk.amazon_audio_manager";
    static final String AMAZON_PHOENIX_SDKMODULE_MAPCLIENT = "com.fireos.sdk.mapclientlib";
    static final int AMAZON_PHOENIX_SDKMODULE_MAPCLIENT_VERSION = 8;
    private static final String AUX_PLUGGED_IN_STATE = "aux_plugged_in_state";
    private static final String CAMERA_SHUTTER_STATE = "camera_shutter_state";
    private static final String DEVICE_MODE_AUTHORITY = "com.amazon.systemui.provider.devicemodeprovider";
    private static final String DEVICE_MODE_PATH = "device_modes";
    private static final String DISABLE_CAMERA = "disable_camera";
    static final String DO_NOT_DISTURB_MODE = "dnd_mode";
    private static final String FIRE_TV_DND_SETTINGS = "com.amazon.alexa.doNotDisturb";
    private static final String FIRE_TV_SETTINGS = "amazon.speech.ALEXA_SETTINGS_UPDATED";
    private static final String FIRE_TV_SETTINGS_PERMISSION = "amazon.speech.permission.SEND_ALEXA_DIRECTIVE";
    private static final int FORCE_OUTPUT_AUDIO_DEVICE_NOT_SUPPORTED = -1;
    private static final int GET_INTERNAL_SPEAKER_VOLUME_LEVEL_NOT_SUPPORTED = -1;
    private static final String PRIVACY_STATUS = "privacy_status";
    static final String USER_SETUP_COMPLETE = "user_setup_complete";
    private static final String WAKEWORD_KEY = "com.amazon.alexa.asr.wakeword";
    private static final CommsLogger sLog = CommsLogger.getLogger(FireTVDeviceFacade.class);
    private final LinkedList<AvsDeviceFacade.CameraAvailabilityListener> cameraAvailabilityListeners;
    private final DisableCameraObserver disableCameraObserver;
    private boolean enableLocalVideoAtCallStartup;
    private CameraManager.AvailabilityCallback mCameraAvailabilityCallback;
    private CameraManager mCameraManager;
    private final boolean mCameraPresent;
    private final Context mContext;
    @VisibleForTesting
    final DndModeBroadcastReceiver mDndModeBroadcastReceiver;
    private final AtomicBoolean mIsDndOn;
    private boolean mUserSetupComplete;
    private ContentObserver mUserSetupObserver;
    private final LinkedList<AvsDeviceFacade.PrivacyModeListener> privacyModeListeners;
    private final PrivacyModeObserver privacyModeObserver;
    private boolean supportsAudioTransfer;
    private boolean supportsCameraShutterSettings;
    private boolean supportsForceSpeakerVolume;
    private final WakeWordObserver wakeWordObserver;

    /* loaded from: classes11.dex */
    private class CameraAvailabilityCallback extends CameraManager.AvailabilityCallback {
        private CameraAvailabilityCallback() {
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(String str) {
            super.onCameraAvailable(str);
            CommsLogger commsLogger = FireTVDeviceFacade.sLog;
            commsLogger.i("onCameraAvailable, cameraId is: " + str);
            for (int i = 0; i < FireTVDeviceFacade.this.cameraAvailabilityListeners.size(); i++) {
                ((AvsDeviceFacade.CameraAvailabilityListener) FireTVDeviceFacade.this.cameraAvailabilityListeners.get(i)).onCameraAvailable(str);
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(String str) {
            super.onCameraUnavailable(str);
            CommsLogger commsLogger = FireTVDeviceFacade.sLog;
            commsLogger.i("onCameraUnavailable, cameraId is: " + str);
            for (int i = 0; i < FireTVDeviceFacade.this.cameraAvailabilityListeners.size(); i++) {
                ((AvsDeviceFacade.CameraAvailabilityListener) FireTVDeviceFacade.this.cameraAvailabilityListeners.get(i)).onCameraUnavailable(str);
            }
        }
    }

    /* loaded from: classes11.dex */
    interface ContentResolverUriProvider {
        Uri provide();
    }

    /* loaded from: classes11.dex */
    private static class DeviceModeContentResolverUriProviderImpl implements ContentResolverUriProvider {
        private DeviceModeContentResolverUriProviderImpl() {
        }

        @Override // com.amazon.comms.device.FireTVDeviceFacade.ContentResolverUriProvider
        public Uri provide() {
            return new Uri.Builder().scheme("content").authority(FireTVDeviceFacade.DEVICE_MODE_AUTHORITY).path(FireTVDeviceFacade.DEVICE_MODE_PATH).build();
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class DisableCameraObserver extends ContentObserver {
        private static final int CAMERA_DISABLED = 1;
        private static final int CAMERA_NOT_DISABLED = 0;
        private int cameraDisableStatus;

        public DisableCameraObserver(Handler handler, Context context) {
            super(handler);
            this.cameraDisableStatus = 0;
            this.cameraDisableStatus = getCameraDisabledStatus();
        }

        private int getCameraDisabledStatus() {
            try {
                this.cameraDisableStatus = Settings.Secure.getInt(FireTVDeviceFacade.this.mContext.getContentResolver(), FireTVDeviceFacade.DISABLE_CAMERA);
            } catch (Settings.SettingNotFoundException unused) {
                this.cameraDisableStatus = 0;
            }
            return this.cameraDisableStatus;
        }

        public boolean isCameraPermitted() {
            return this.cameraDisableStatus != 1;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            this.cameraDisableStatus = getCameraDisabledStatus();
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class DndModeBroadcastReceiver extends BroadcastReceiver {
        DndModeBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(FireTVDeviceFacade.FIRE_TV_DND_SETTINGS);
            if ("true".equals(stringExtra)) {
                FireTVDeviceFacade.this.setIsDoNotDisturbOn(true);
            } else if (!PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(stringExtra)) {
            } else {
                FireTVDeviceFacade.this.setIsDoNotDisturbOn(false);
            }
        }
    }

    /* loaded from: classes11.dex */
    private class PrivacyModeObserver extends ContentObserver {
        private static final boolean DEFAULT_PRIVACY_STATUS = false;
        private static final int PRIVACY_MODE_ON = 1;
        private static /* synthetic */ Annotation ajc$anno$0;
        private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
        private final AtomicBoolean privacyStatus;

        /* loaded from: classes11.dex */
        public class AjcClosure1 extends AroundClosure {
            public AjcClosure1(Object[] objArr) {
                super(objArr);
            }

            @Override // org.aspectj.runtime.internal.AroundClosure
            public Object run(Object[] objArr) {
                Object[] objArr2 = this.state;
                PrivacyModeObserver.onChange_aroundBody0((PrivacyModeObserver) objArr2[0], Conversions.booleanValue(objArr2[1]), (Uri) objArr2[2], (JoinPoint) objArr2[3]);
                return null;
            }
        }

        static {
            ajc$preClinit();
        }

        public PrivacyModeObserver(Handler handler, Context context) {
            super(handler);
            this.privacyStatus = new AtomicBoolean(getPrivacyModeStatus(context));
        }

        private static /* synthetic */ void ajc$preClinit() {
            Factory factory = new Factory("FireTVDeviceFacade.java", PrivacyModeObserver.class);
            ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "onChange", "com.amazon.comms.device.FireTVDeviceFacade$PrivacyModeObserver", "boolean:android.net.Uri", "selfChange:uri", "", "void"), 434);
        }

        private boolean getPrivacyModeStatus(Context context) {
            try {
                return 1 == Settings.Global.getInt(context.getContentResolver(), "privacy_status");
            } catch (Settings.SettingNotFoundException unused) {
                return false;
            }
        }

        static final /* synthetic */ void onChange_aroundBody0(PrivacyModeObserver privacyModeObserver, boolean z, Uri uri, JoinPoint joinPoint) {
            AtomicBoolean atomicBoolean = privacyModeObserver.privacyStatus;
            atomicBoolean.set(!atomicBoolean.get());
            for (int i = 0; i < FireTVDeviceFacade.this.privacyModeListeners.size(); i++) {
                ((AvsDeviceFacade.PrivacyModeListener) FireTVDeviceFacade.this.privacyModeListeners.get(i)).onChange(privacyModeObserver.privacyStatus.get());
            }
        }

        public boolean isPrivacyModeOn() {
            return this.privacyStatus.get();
        }

        @Override // android.database.ContentObserver
        @Log
        public void onChange(boolean z, Uri uri) {
            JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this, Conversions.booleanObject(z), uri);
            CommsLoggerAspect aspectOf = CommsLoggerAspect.aspectOf();
            ProceedingJoinPoint linkClosureAndJoinPoint = new AjcClosure1(new Object[]{this, Conversions.booleanObject(z), uri, makeJP}).linkClosureAndJoinPoint(69648);
            Annotation annotation = ajc$anno$0;
            if (annotation == null) {
                annotation = PrivacyModeObserver.class.getDeclaredMethod("onChange", Boolean.TYPE, Uri.class).getAnnotation(Log.class);
                ajc$anno$0 = annotation;
            }
            aspectOf.log(linkClosureAndJoinPoint, (Log) annotation);
        }
    }

    /* loaded from: classes11.dex */
    private class WakeWordObserver extends ContentObserver {
        private static final String DEFAULT_WAKEWORD = "ALEXA";
        private static /* synthetic */ Annotation ajc$anno$0;
        private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
        private final Context mContext;
        private String mWakeWord;

        /* loaded from: classes11.dex */
        public class AjcClosure1 extends AroundClosure {
            public AjcClosure1(Object[] objArr) {
                super(objArr);
            }

            @Override // org.aspectj.runtime.internal.AroundClosure
            public Object run(Object[] objArr) {
                Object[] objArr2 = this.state;
                WakeWordObserver.onChange_aroundBody0((WakeWordObserver) objArr2[0], Conversions.booleanValue(objArr2[1]), (Uri) objArr2[2], (JoinPoint) objArr2[3]);
                return null;
            }
        }

        static {
            ajc$preClinit();
        }

        public WakeWordObserver(Handler handler, Context context) {
            super(handler);
            this.mWakeWord = Settings.System.getString(context.getContentResolver(), FireTVDeviceFacade.WAKEWORD_KEY);
            this.mContext = context;
        }

        private static /* synthetic */ void ajc$preClinit() {
            Factory factory = new Factory("FireTVDeviceFacade.java", WakeWordObserver.class);
            ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "onChange", "com.amazon.comms.device.FireTVDeviceFacade$WakeWordObserver", "boolean:android.net.Uri", "selfChange:uri", "", "void"), 494);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getCurrentWakeWord() {
            String str = this.mWakeWord;
            return (str == null || str.isEmpty()) ? "ALEXA" : this.mWakeWord;
        }

        static final /* synthetic */ void onChange_aroundBody0(WakeWordObserver wakeWordObserver, boolean z, Uri uri, JoinPoint joinPoint) {
            wakeWordObserver.mWakeWord = Settings.System.getString(wakeWordObserver.mContext.getContentResolver(), FireTVDeviceFacade.WAKEWORD_KEY);
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Wake Word changed to: "), wakeWordObserver.mWakeWord, FireTVDeviceFacade.sLog);
        }

        @Override // android.database.ContentObserver
        @Log
        public void onChange(boolean z, Uri uri) {
            JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this, Conversions.booleanObject(z), uri);
            CommsLoggerAspect aspectOf = CommsLoggerAspect.aspectOf();
            ProceedingJoinPoint linkClosureAndJoinPoint = new AjcClosure1(new Object[]{this, Conversions.booleanObject(z), uri, makeJP}).linkClosureAndJoinPoint(69648);
            Annotation annotation = ajc$anno$0;
            if (annotation == null) {
                annotation = WakeWordObserver.class.getDeclaredMethod("onChange", Boolean.TYPE, Uri.class).getAnnotation(Log.class);
                ajc$anno$0 = annotation;
            }
            aspectOf.log(linkClosureAndJoinPoint, (Log) annotation);
        }
    }

    public FireTVDeviceFacade(Context context, boolean z, boolean z2, boolean z3) {
        this(context);
        this.supportsAudioTransfer = z;
        this.supportsForceSpeakerVolume = z2;
        this.enableLocalVideoAtCallStartup = z3;
    }

    private boolean checkBuiltinCameraPresent() {
        boolean hasSystemFeature = this.mContext.getPackageManager().hasSystemFeature("android.hardware.camera");
        CommsLogger commsLogger = sLog;
        commsLogger.d("Is builtin Camera Present = " + hasSystemFeature);
        return hasSystemFeature;
    }

    private IntentFilter createFireTvSettingsIntentFilter() {
        return GeneratedOutlineSupport1.outline10(FIRE_TV_SETTINGS);
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

    private boolean isForceSpeakerVolumeSupported() {
        return this.supportsForceSpeakerVolume && supportsAmazonAudioManagerFeature();
    }

    private boolean isPhoenixMapClientSupported() {
        int i = Build.VERSION.SDK_INT;
        boolean isPhoenixMapClientSupportedPost24 = isPhoenixMapClientSupportedPost24();
        if (isPhoenixMapClientSupportedPost24) {
            sLog.i("Phoenix-SDKModule-mapclient is supported on the divice.");
        } else {
            sLog.i("Phoenix-SDKModule-mapclient is not supported on the divice.");
        }
        return isPhoenixMapClientSupportedPost24;
    }

    @TargetApi(24)
    private boolean isPhoenixMapClientSupportedPost24() {
        return this.mContext.getPackageManager().hasSystemFeature("com.fireos.sdk.mapclientlib", 8);
    }

    @TargetApi(24)
    private boolean isPhoenixMapClientSupportedPre24() {
        return this.mContext.getPackageManager().hasSystemFeature("com.fireos.sdk.mapclientlib") && this.mContext.getPackageManager().hasSystemFeature("com.fireos.sdk.mapclientlib", 8);
    }

    private void setForceSpeakerVolumeHelper(AudioManager audioManager, int i) {
        ((AmazonAudioManager) audioManager).setForceUse(AmazonAudioManager.FOR_MEDIA, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsDoNotDisturbOn(boolean z) {
        this.mIsDndOn.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOOBEContract() {
        if (this.mUserSetupObserver == null) {
            this.mUserSetupObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.amazon.comms.device.FireTVDeviceFacade.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    FireTVDeviceFacade.this.setOOBEContract();
                }
            };
            this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(USER_SETUP_COMPLETE), false, this.mUserSetupObserver);
        }
        this.mUserSetupComplete = getSettings(USER_SETUP_COMPLETE);
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("OOBE done: "), this.mUserSetupComplete, sLog);
    }

    private boolean supportsAmazonAudioManagerFeature() {
        int i = Build.VERSION.SDK_INT;
        return supportsAmazonAudioManagerFeaturePost24();
    }

    @TargetApi(24)
    private boolean supportsAmazonAudioManagerFeaturePost24() {
        return this.mContext.getPackageManager().hasSystemFeature(AMAZON_AUDIO_MANAGER_FEATURE_NAME, 14);
    }

    @TargetApi(24)
    private boolean supportsAmazonAudioManagerFeaturePre24() {
        return this.mContext.getPackageManager().hasSystemFeature(AMAZON_AUDIO_MANAGER_FEATURE_NAME) && this.mContext.getPackageManager().hasSystemFeature(AMAZON_AUDIO_MANAGER_FEATURE_NAME, 14);
    }

    private boolean supportsSettings(String str) {
        try {
            return Settings.Secure.getInt(this.mContext.getContentResolver(), str) != -1;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void destroy() {
        try {
            this.mContext.unregisterReceiver(this.mDndModeBroadcastReceiver);
        } catch (Exception unused) {
            sLog.d("mDndModeBroadcastReceiver is already unregistered");
        }
        this.mContext.getContentResolver().unregisterContentObserver(this.disableCameraObserver);
        if (this.mUserSetupObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mUserSetupObserver);
        }
        if (this.wakeWordObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.wakeWordObserver);
        }
        if (this.privacyModeObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.privacyModeObserver);
        }
        if (this.mCameraManager == null || this.mCameraAvailabilityCallback == null) {
            return;
        }
        this.cameraAvailabilityListeners.clear();
        this.mCameraManager.unregisterAvailabilityCallback(this.mCameraAvailabilityCallback);
        this.mCameraAvailabilityCallback = null;
        this.mCameraManager = null;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean enableLocalVideoAtCallInitiation() {
        return this.enableLocalVideoAtCallStartup;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void forceResetMicToDefault() {
        AmazonAudioManager amazonAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (amazonAudioManager instanceof AmazonAudioManager) {
            amazonAudioManager.setForceUse(AmazonAudioManager.FOR_COMMUNICATION, AmazonAudioManager.FORCE_NONE);
        }
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public AudioAttributes getAudioAttributesForRingtone() {
        AudioAttributes.Builder contentType = new AudioAttributes.Builder().setUsage(1).setContentType(2);
        int internalSpeakerFlag = getInternalSpeakerFlag();
        if (internalSpeakerFlag != -1) {
            contentType.setFlags(internalSpeakerFlag);
        }
        return contentType.build();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getDefaultOutputDeviceForCallAudio() {
        if (isAudioTransferSupported()) {
            return getInternalSpeakerFlag();
        }
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public Size getDisplaySize() {
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = point.x;
        int i2 = point.y;
        CommsLogger commsLogger = sLog;
        commsLogger.i("Screen size:" + i + ReactProperties.HereMapMarker.X + i2);
        return new Size(i, i2);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getHdmiFlag() {
        if (supportsAmazonAudioManagerFeature()) {
            return AmazonAudioManager.FLAG_HDMI_OUT;
        }
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getInternalSpeakerFlag() {
        if (supportsAmazonAudioManagerFeature()) {
            return AmazonAudioManager.FLAG_SPEAKER_OUT;
        }
        return -1;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getInternalSpeakerVolume(AudioManager audioManager, int i) {
        if (isForceSpeakerVolumeSupported()) {
            if (audioManager instanceof AmazonAudioManager) {
                AmazonAudioManager amazonAudioManager = (AmazonAudioManager) audioManager;
                Class cls = Integer.TYPE;
                try {
                    try {
                        return ((Integer) AmazonAudioManager.class.getMethod("getStreamVolumeOnDevice", cls, cls).invoke(amazonAudioManager, Integer.valueOf(i), 2)).intValue();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return -1;
                    } catch (InvocationTargetException e2) {
                        e2.printStackTrace();
                        return -1;
                    }
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                    return -1;
                }
            }
            sLog.d("audioManager not instanceof AmazonAudioManager");
            return audioManager.getStreamVolume(i);
        }
        sLog.d("Force Volume Feature not supported");
        return audioManager.getStreamVolume(i);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public int getScreenRotation() {
        return DeviceProperties.getScreenRotation(this.mContext);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public String getWakeWord() {
        WakeWordObserver wakeWordObserver = this.wakeWordObserver;
        return wakeWordObserver != null ? wakeWordObserver.getCurrentWakeWord() : KnightDeviceFacade.WakeWordObserver.DEFAULT_WAKEWORD;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isAudioTransferSupported() {
        return this.supportsAudioTransfer && supportsAmazonAudioManagerFeature();
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
        if (this.supportsCameraShutterSettings) {
            sLog.i("Device has a camera shutter. Always permitting the camera during the call.");
            return true;
        }
        return this.disableCameraObserver.isCameraPermitted();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isCameraPresent() {
        return this.mCameraPresent;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isCameraShutterCovered() {
        return !this.disableCameraObserver.isCameraPermitted();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isDoNotDisturbOn() {
        return this.mIsDndOn.get();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isDropInDisallowed() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isExternalCameraPresent() {
        if (!isExternalCameraSupported()) {
            return false;
        }
        String[] strArr = null;
        try {
            if (this.mCameraManager != null) {
                strArr = this.mCameraManager.getCameraIdList();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return strArr != null && strArr.length > 0;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isExternalCameraSupported() {
        boolean hasSystemFeature = this.mContext.getPackageManager().hasSystemFeature("android.hardware.camera.external");
        CommsLogger commsLogger = sLog;
        commsLogger.d("Is External Camera Supported = " + hasSystemFeature);
        return hasSystemFeature;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isFireTvDevice() {
        return true;
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
        return isPhoenixMapClientSupported();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isOOBEComplete() {
        return this.mUserSetupComplete;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isPrivacyModeOn() {
        PrivacyModeObserver privacyModeObserver = this.privacyModeObserver;
        if (privacyModeObserver != null) {
            return privacyModeObserver.isPrivacyModeOn();
        }
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isPrivilegedTaskInProgress() {
        return false;
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void registerCameraAvailabilityListener(AvsDeviceFacade.CameraAvailabilityListener cameraAvailabilityListener) {
        LinkedList<AvsDeviceFacade.CameraAvailabilityListener> linkedList;
        if (this.mCameraAvailabilityCallback == null || (linkedList = this.cameraAvailabilityListeners) == null || linkedList.contains(cameraAvailabilityListener)) {
            return;
        }
        this.cameraAvailabilityListeners.add(cameraAvailabilityListener);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void registerPrivacyModeListener(AvsDeviceFacade.PrivacyModeListener privacyModeListener) {
        if (privacyModeListener == null || this.privacyModeListeners.contains(privacyModeListener)) {
            return;
        }
        this.privacyModeListeners.add(privacyModeListener);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void setForceNoSpeakerVolume(AudioManager audioManager) {
        if (isForceSpeakerVolumeSupported()) {
            if (audioManager instanceof AmazonAudioManager) {
                setForceSpeakerVolumeHelper(audioManager, 1000);
                return;
            } else {
                sLog.d("audioManager not instanceof AmazonAudioManager");
                return;
            }
        }
        sLog.d("Force Volume Feature not supported");
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void setForceSpeakerVolume(AudioManager audioManager) {
        if (isForceSpeakerVolumeSupported()) {
            if (audioManager instanceof AmazonAudioManager) {
                setForceSpeakerVolumeHelper(audioManager, 1001);
                return;
            } else {
                sLog.d("audioManager not instanceof AmazonAudioManager");
                return;
            }
        }
        sLog.d("Force Volume Feature not supported");
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void unregisterCameraAvailabilityListener(AvsDeviceFacade.CameraAvailabilityListener cameraAvailabilityListener) {
        LinkedList<AvsDeviceFacade.CameraAvailabilityListener> linkedList = this.cameraAvailabilityListeners;
        if (linkedList == null || !linkedList.contains(cameraAvailabilityListener)) {
            return;
        }
        this.cameraAvailabilityListeners.remove(cameraAvailabilityListener);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public void unregisterPrivacyModeListener(AvsDeviceFacade.PrivacyModeListener privacyModeListener) {
        if (privacyModeListener == null || !this.privacyModeListeners.contains(privacyModeListener)) {
            return;
        }
        this.privacyModeListeners.remove(privacyModeListener);
    }

    public FireTVDeviceFacade(Context context) {
        this(context, new DeviceModeContentResolverUriProviderImpl());
    }

    public FireTVDeviceFacade(Context context, ContentResolverUriProvider contentResolverUriProvider) {
        this.supportsAudioTransfer = false;
        this.supportsForceSpeakerVolume = false;
        this.enableLocalVideoAtCallStartup = false;
        this.mIsDndOn = new AtomicBoolean(false);
        this.mDndModeBroadcastReceiver = new DndModeBroadcastReceiver();
        this.mUserSetupObserver = null;
        this.mUserSetupComplete = false;
        this.privacyModeListeners = new LinkedList<>();
        this.cameraAvailabilityListeners = new LinkedList<>();
        this.supportsCameraShutterSettings = false;
        this.mCameraManager = null;
        this.mCameraAvailabilityCallback = null;
        this.mContext = context;
        this.mCameraManager = (CameraManager) this.mContext.getSystemService("camera");
        boolean isExternalCameraSupported = isExternalCameraSupported();
        if (isExternalCameraSupported) {
            if (this.mCameraManager != null) {
                this.mCameraAvailabilityCallback = new CameraAvailabilityCallback();
                this.mCameraManager.registerAvailabilityCallback(this.mCameraAvailabilityCallback, (Handler) null);
            } else {
                sLog.e("Failed to retrieve the CameraManager, won't be able to reopen the camera when re-plugged");
            }
        }
        this.mCameraPresent = isExternalCameraSupported || checkBuiltinCameraPresent();
        context.registerReceiver(this.mDndModeBroadcastReceiver, createFireTvSettingsIntentFilter(), FIRE_TV_SETTINGS_PERMISSION, null);
        this.privacyModeObserver = new PrivacyModeObserver(new Handler(Looper.getMainLooper()), this.mContext);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("privacy_status"), false, this.privacyModeObserver);
        this.wakeWordObserver = new WakeWordObserver(new Handler(Looper.getMainLooper()), this.mContext);
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(WAKEWORD_KEY), false, this.wakeWordObserver);
        setOOBEContract();
        this.disableCameraObserver = new DisableCameraObserver(new Handler(Looper.getMainLooper()), this.mContext);
        this.supportsCameraShutterSettings = supportsSettings(CAMERA_SHUTTER_STATE);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(DISABLE_CAMERA), false, this.disableCameraObserver);
    }
}
