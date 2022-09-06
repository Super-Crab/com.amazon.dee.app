package com.amazon.comms.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.FeatureInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.log.annotation.Log;
import com.amazon.comms.util.DeviceProperties;
import com.amazon.comms.util.Size;
import com.amazon.comms.util.modeswitch.ModeSwitchHelper;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.iptc.IptcDirectory;
import com.google.common.annotations.VisibleForTesting;
import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
/* loaded from: classes11.dex */
public class KnightDeviceFacade implements AvsDeviceFacade {
    static final String AMAZON_MANUFACTURER = "Amazon";
    private static final String AUX_PLUGGED_IN_STATE = "aux_plugged_in_state";
    private static final String BROADCASTER_PERMISSION = "permission.ALLOW_BROADCAST";
    private static final String CAMERA_SHUTTER_STATE = "camera_shutter_state";
    private static final String DEFAULT_WAKEWORD = "ALEXA";
    private static final String DEVICE_MODE_AUTHORITY = "com.amazon.systemui.provider.devicemodeprovider";
    private static final String DEVICE_MODE_PATH = "device_modes";
    private static final String DEVICE_OP_MODE = "device_op_mode";
    static final String DEVICE_OP_MODE_URI = "content://com.amazon.settings.provider.settingsproxy/settings/device_op_mode";
    private static final String DISABLE_CAMERA = "disable_camera";
    static final String DO_NOT_DISTURB_MODE = "dnd_mode";
    private static final String DO_NOT_DISTURB_OFF = "amazon.intent.action.DO_NOT_DISTURB_MODE_OFF";
    private static final String DO_NOT_DISTURB_ON = "amazon.intent.action.DO_NOT_DISTURB_MODE_ON";
    private static final String DO_NOT_DISTURB_PERMISSION = "com.amazon.settings.SET_DND";
    private static final String DROP_IN_ACCESS = "comms_dropin_access";
    private static final String MMSDK_FEATURE_NAME = "com.amazon.alexa.multimodal";
    static final String MODE_ENABLED = "true";
    static final String PRIVACY_MODE = "privacy_mode";
    private static final String PRIVACY_MODE_OFF = "amazon.intent.action.PRIVACY_MODE_OFF";
    private static final String PRIVACY_MODE_ON = "amazon.intent.action.PRIVACY_MODE_ON";
    private static final String PRIVACY_MODE_PERMISSION = "amazon.permission.PRIVACY_MODE";
    private static final String PRIVACY_STATUS = "privacy_status";
    private static final String PRIVILEGED_TASK_IN_PROGRESS = "privilegedTaskInProgress";
    private static final String SETTINGS_PROVIDER_URI = "content://com.amazon.settings.provider.settingsproxy/settings";
    static final String USER_SETUP_COMPLETE = "user_setup_complete";
    static final String VESTA_MODEL = "CRGVS";
    private static final String WAKEWORD_KEY = "com.amazon.alexa.asr.wakeword";
    private final DisableCameraObserver disableCameraObserver;
    private final boolean mCameraPresent;
    private final Context mContext;
    private final ContentResolverUriProvider mDeviceModeUriProvider;
    private DeviceOpModeObserver mDeviceOpModeObserver;
    @VisibleForTesting
    final DndModeBroadcastReceiver mDndModeBroadcastReceiver;
    private final DropInAccessObserver mDropInAccessObserver;
    private final AtomicBoolean mIsDndOn;
    private final AtomicBoolean mIsFreeTimeModeOn;
    private boolean mIsMmSdkApplication;
    private final AtomicBoolean mIsPrivacyOn;
    @VisibleForTesting
    final PrivacyModeBroadcastReceiver mPrivacyModeBroadcastReceiver;
    private boolean mUserSetupComplete;
    private ContentObserver mUserSetupObserver;
    private final PrivacyModeObserver privacyModeObserver;
    private final PrivilegedTaskObserver privilegedTaskObserver;
    private boolean supportsCameraShutterSettings;
    private final WakeWordObserver wakeWordObserver;
    private static final CommsLogger sLog = CommsLogger.getLogger(KnightDeviceFacade.class);
    private static final LinkedList<AvsDeviceFacade.PrivacyModeListener> privacyModeListeners = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public interface ContentResolverUriProvider {
        Uri provide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface CursorQuery<T> {
        /* renamed from: cursorNotFound */
        T mo3239cursorNotFound();

        /* renamed from: query */
        T mo3240query(Cursor cursor);
    }

    /* loaded from: classes11.dex */
    private static class DeviceModeContentResolverUriProviderImpl implements ContentResolverUriProvider {
        private DeviceModeContentResolverUriProviderImpl() {
        }

        @Override // com.amazon.comms.device.KnightDeviceFacade.ContentResolverUriProvider
        public Uri provide() {
            return new Uri.Builder().scheme("content").authority(KnightDeviceFacade.DEVICE_MODE_AUTHORITY).path(KnightDeviceFacade.DEVICE_MODE_PATH).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public class DeviceOpModeObserver extends ContentObserver {
        DeviceOpModeObserver(Handler handler) {
            super(handler);
        }

        public /* synthetic */ void lambda$onChange$0$KnightDeviceFacade$DeviceOpModeObserver() {
            KnightDeviceFacade knightDeviceFacade = KnightDeviceFacade.this;
            knightDeviceFacade.setIsFreeTimeModeOn(knightDeviceFacade.getFreeTimeMode());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            AsyncTask.execute(new Runnable() { // from class: com.amazon.comms.device.-$$Lambda$KnightDeviceFacade$DeviceOpModeObserver$i9o-bkfZXUJDEW-q0MSA-12ss88
                @Override // java.lang.Runnable
                public final void run() {
                    KnightDeviceFacade.DeviceOpModeObserver.this.lambda$onChange$0$KnightDeviceFacade$DeviceOpModeObserver();
                }
            });
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
                this.cameraDisableStatus = Settings.Secure.getInt(KnightDeviceFacade.this.mContext.getContentResolver(), KnightDeviceFacade.DISABLE_CAMERA);
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
            if (KnightDeviceFacade.DO_NOT_DISTURB_ON.equals(intent.getAction())) {
                KnightDeviceFacade.this.setIsDoNotDisturbOn(true);
            } else if (!KnightDeviceFacade.DO_NOT_DISTURB_OFF.equals(intent.getAction())) {
            } else {
                KnightDeviceFacade.this.setIsDoNotDisturbOn(false);
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class DropInAccessObserver extends ContentObserver {
        private static final int DROP_IN_DISALLOWED = 0;
        private static final int DROP_IN_PERMITTED = 1;
        private AtomicInteger dropInAccessStatus;

        DropInAccessObserver(Handler handler) {
            super(handler);
            this.dropInAccessStatus = new AtomicInteger(1);
            this.dropInAccessStatus.set(getDropInAccessStatus());
            CommsLogger commsLogger = KnightDeviceFacade.sLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The initial value of dropInAccessStatus: ");
            outline107.append(this.dropInAccessStatus.get());
            commsLogger.d(outline107.toString());
        }

        private synchronized int getDropInAccessStatus() {
            int i;
            i = 1;
            try {
                i = Settings.Secure.getInt(KnightDeviceFacade.this.mContext.getContentResolver(), KnightDeviceFacade.DROP_IN_ACCESS);
            } catch (Settings.SettingNotFoundException unused) {
                KnightDeviceFacade.sLog.d("DropIn access setting is not found. Defaulting to dropInAccessStatus=1");
            }
            return i;
        }

        boolean isDropInDisallowed() {
            return this.dropInAccessStatus.get() == 0;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            this.dropInAccessStatus.set(getDropInAccessStatus());
            CommsLogger commsLogger = KnightDeviceFacade.sLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting dropInAccessStatus to ");
            outline107.append(this.dropInAccessStatus.get());
            commsLogger.d(outline107.toString());
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class PrivacyModeBroadcastReceiver extends BroadcastReceiver {
        PrivacyModeBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (KnightDeviceFacade.PRIVACY_MODE_ON.equals(intent.getAction())) {
                KnightDeviceFacade.this.setIsPrivacyModeOn(true);
            } else if (!KnightDeviceFacade.PRIVACY_MODE_OFF.equals(intent.getAction())) {
            } else {
                KnightDeviceFacade.this.setIsPrivacyModeOn(false);
            }
        }
    }

    /* loaded from: classes11.dex */
    static class PrivacyModeObserver extends ContentObserver {
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
            Factory factory = new Factory("KnightDeviceFacade.java", PrivacyModeObserver.class);
            ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "onChange", "com.amazon.comms.device.KnightDeviceFacade$PrivacyModeObserver", "boolean:android.net.Uri", "selfChange:uri", "", "void"), IptcDirectory.TAG_COUNTRY_OR_PRIMARY_LOCATION_CODE);
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
            for (int i = 0; i < KnightDeviceFacade.privacyModeListeners.size(); i++) {
                ((AvsDeviceFacade.PrivacyModeListener) KnightDeviceFacade.privacyModeListeners.get(i)).onChange(privacyModeObserver.privacyStatus.get());
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

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class PrivilegedTaskObserver extends ContentObserver {
        private boolean isPrivilegedTaskInProgress;

        public PrivilegedTaskObserver(Handler handler) {
            super(handler);
            this.isPrivilegedTaskInProgress = checkifPrivilegedTaskInProgress();
        }

        private boolean checkifPrivilegedTaskInProgress() {
            return Settings.Global.getInt(KnightDeviceFacade.this.mContext.getContentResolver(), KnightDeviceFacade.PRIVILEGED_TASK_IN_PROGRESS, 0) > 0;
        }

        public boolean isPrivilegedTaskInProgress() {
            return this.isPrivilegedTaskInProgress;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            this.isPrivilegedTaskInProgress = checkifPrivilegedTaskInProgress();
        }
    }

    /* loaded from: classes11.dex */
    static class WakeWordObserver extends ContentObserver {
        public static final String DEFAULT_WAKEWORD = "ALEXA";
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
            this.mWakeWord = Settings.System.getString(context.getContentResolver(), KnightDeviceFacade.WAKEWORD_KEY);
            this.mContext = context;
        }

        private static /* synthetic */ void ajc$preClinit() {
            Factory factory = new Factory("KnightDeviceFacade.java", WakeWordObserver.class);
            ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "onChange", "com.amazon.comms.device.KnightDeviceFacade$WakeWordObserver", "boolean:android.net.Uri", "selfChange:uri", "", "void"), 649);
        }

        static final /* synthetic */ void onChange_aroundBody0(WakeWordObserver wakeWordObserver, boolean z, Uri uri, JoinPoint joinPoint) {
            wakeWordObserver.mWakeWord = Settings.System.getString(wakeWordObserver.mContext.getContentResolver(), KnightDeviceFacade.WAKEWORD_KEY);
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("Wake Word changed to: "), wakeWordObserver.mWakeWord, KnightDeviceFacade.sLog);
        }

        public String getCurrentWakeWord() {
            String str = this.mWakeWord;
            return (str == null || str.isEmpty()) ? "ALEXA" : this.mWakeWord;
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

    public KnightDeviceFacade(Context context) {
        this(context, new DeviceModeContentResolverUriProviderImpl());
    }

    private boolean containsSystemFeature(String str) {
        FeatureInfo[] systemAvailableFeatures = this.mContext.getPackageManager().getSystemAvailableFeatures();
        if (systemAvailableFeatures != null) {
            for (FeatureInfo featureInfo : systemAvailableFeatures) {
                String str2 = featureInfo.name;
                if (str2 != null && str2.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private IntentFilter createDndIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DO_NOT_DISTURB_ON);
        intentFilter.addAction(DO_NOT_DISTURB_OFF);
        return intentFilter;
    }

    private IntentFilter createPrivacyModeIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PRIVACY_MODE_ON);
        intentFilter.addAction(PRIVACY_MODE_OFF);
        return intentFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getDoNotDisturb() {
        return ((Boolean) queryDeviceMode(new CursorQuery<Boolean>() { // from class: com.amazon.comms.device.KnightDeviceFacade.2
            private static /* synthetic */ Annotation ajc$anno$0;
            private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;

            /* renamed from: com.amazon.comms.device.KnightDeviceFacade$2$AjcClosure1 */
            /* loaded from: classes11.dex */
            public class AjcClosure1 extends AroundClosure {
                public AjcClosure1(Object[] objArr) {
                    super(objArr);
                }

                @Override // org.aspectj.runtime.internal.AroundClosure
                public Object run(Object[] objArr) {
                    Object[] objArr2 = this.state;
                    AnonymousClass2 anonymousClass2 = (AnonymousClass2) objArr2[0];
                    JoinPoint joinPoint = (JoinPoint) objArr2[1];
                    return false;
                }
            }

            static {
                ajc$preClinit();
            }

            private static /* synthetic */ void ajc$preClinit() {
                Factory factory = new Factory("KnightDeviceFacade.java", AnonymousClass2.class);
                ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "cursorNotFound", "com.amazon.comms.device.KnightDeviceFacade$2", "", "", "", "java.lang.Boolean"), 408);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.comms.device.KnightDeviceFacade.CursorQuery
            @Log(level = LogLevel.Error, message = "Not able to query DND mode, assuming DND OFF")
            /* renamed from: cursorNotFound */
            public Boolean mo3239cursorNotFound() {
                JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this);
                CommsLoggerAspect aspectOf = CommsLoggerAspect.aspectOf();
                ProceedingJoinPoint linkClosureAndJoinPoint = new AjcClosure1(new Object[]{this, makeJP}).linkClosureAndJoinPoint(69648);
                Annotation annotation = ajc$anno$0;
                if (annotation == null) {
                    annotation = AnonymousClass2.class.getDeclaredMethod("cursorNotFound", new Class[0]).getAnnotation(Log.class);
                    ajc$anno$0 = annotation;
                }
                return (Boolean) aspectOf.log(linkClosureAndJoinPoint, (Log) annotation);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.comms.device.KnightDeviceFacade.CursorQuery
            /* renamed from: query */
            public Boolean mo3240query(Cursor cursor) {
                return Boolean.valueOf("true".equals(cursor.getString(cursor.getColumnIndex("dnd_mode"))));
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getFreeTimeMode() {
        return ((Boolean) queryDeviceOpMode(new CursorQuery<Boolean>() { // from class: com.amazon.comms.device.KnightDeviceFacade.4
            private static final String CHILD_DIRECTED = "CHILD_DIRECTED";
            private static /* synthetic */ Annotation ajc$anno$0;
            private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;

            /* renamed from: com.amazon.comms.device.KnightDeviceFacade$4$AjcClosure1 */
            /* loaded from: classes11.dex */
            public class AjcClosure1 extends AroundClosure {
                public AjcClosure1(Object[] objArr) {
                    super(objArr);
                }

                @Override // org.aspectj.runtime.internal.AroundClosure
                public Object run(Object[] objArr) {
                    Object[] objArr2 = this.state;
                    AnonymousClass4 anonymousClass4 = (AnonymousClass4) objArr2[0];
                    JoinPoint joinPoint = (JoinPoint) objArr2[1];
                    return false;
                }
            }

            static {
                ajc$preClinit();
            }

            private static /* synthetic */ void ajc$preClinit() {
                Factory factory = new Factory("KnightDeviceFacade.java", AnonymousClass4.class);
                ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "cursorNotFound", "com.amazon.comms.device.KnightDeviceFacade$4", "", "", "", "java.lang.Boolean"), 443);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.comms.device.KnightDeviceFacade.CursorQuery
            @Log(level = LogLevel.Debug, message = "Not able to query device op mode, assuming FreeTime mode off")
            /* renamed from: cursorNotFound */
            public Boolean mo3239cursorNotFound() {
                JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this);
                CommsLoggerAspect aspectOf = CommsLoggerAspect.aspectOf();
                ProceedingJoinPoint linkClosureAndJoinPoint = new AjcClosure1(new Object[]{this, makeJP}).linkClosureAndJoinPoint(69648);
                Annotation annotation = ajc$anno$0;
                if (annotation == null) {
                    annotation = AnonymousClass4.class.getDeclaredMethod("cursorNotFound", new Class[0]).getAnnotation(Log.class);
                    ajc$anno$0 = annotation;
                }
                return (Boolean) aspectOf.log(linkClosureAndJoinPoint, (Log) annotation);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.comms.device.KnightDeviceFacade.CursorQuery
            /* renamed from: query */
            public Boolean mo3240query(Cursor cursor) {
                String string = cursor.getString(0);
                CommsLogger commsLogger = KnightDeviceFacade.sLog;
                commsLogger.d("FreeTimeMode value: " + string);
                return Boolean.valueOf(CHILD_DIRECTED.equals(string));
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getPrivacyMode() {
        return ((Boolean) queryDeviceMode(new CursorQuery<Boolean>() { // from class: com.amazon.comms.device.KnightDeviceFacade.3
            private static /* synthetic */ Annotation ajc$anno$0;
            private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;

            /* renamed from: com.amazon.comms.device.KnightDeviceFacade$3$AjcClosure1 */
            /* loaded from: classes11.dex */
            public class AjcClosure1 extends AroundClosure {
                public AjcClosure1(Object[] objArr) {
                    super(objArr);
                }

                @Override // org.aspectj.runtime.internal.AroundClosure
                public Object run(Object[] objArr) {
                    Object[] objArr2 = this.state;
                    AnonymousClass3 anonymousClass3 = (AnonymousClass3) objArr2[0];
                    JoinPoint joinPoint = (JoinPoint) objArr2[1];
                    return false;
                }
            }

            static {
                ajc$preClinit();
            }

            private static /* synthetic */ void ajc$preClinit() {
                Factory factory = new Factory("KnightDeviceFacade.java", AnonymousClass3.class);
                ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "cursorNotFound", "com.amazon.comms.device.KnightDeviceFacade$3", "", "", "", "java.lang.Boolean"), 424);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.comms.device.KnightDeviceFacade.CursorQuery
            @Log(level = LogLevel.Error, message = "Not able to query privacy mode, assuming not-private")
            /* renamed from: cursorNotFound */
            public Boolean mo3239cursorNotFound() {
                JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this);
                CommsLoggerAspect aspectOf = CommsLoggerAspect.aspectOf();
                ProceedingJoinPoint linkClosureAndJoinPoint = new AjcClosure1(new Object[]{this, makeJP}).linkClosureAndJoinPoint(69648);
                Annotation annotation = ajc$anno$0;
                if (annotation == null) {
                    annotation = AnonymousClass3.class.getDeclaredMethod("cursorNotFound", new Class[0]).getAnnotation(Log.class);
                    ajc$anno$0 = annotation;
                }
                return (Boolean) aspectOf.log(linkClosureAndJoinPoint, (Log) annotation);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.comms.device.KnightDeviceFacade.CursorQuery
            /* renamed from: query */
            public Boolean mo3240query(Cursor cursor) {
                return Boolean.valueOf("true".equals(cursor.getString(cursor.getColumnIndex(KnightDeviceFacade.PRIVACY_MODE))));
            }
        })).booleanValue();
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

    private void initializeValues() {
        AsyncTask.execute(new Runnable() { // from class: com.amazon.comms.device.KnightDeviceFacade.1
            @Override // java.lang.Runnable
            public void run() {
                KnightDeviceFacade knightDeviceFacade = KnightDeviceFacade.this;
                knightDeviceFacade.setIsDoNotDisturbOn(knightDeviceFacade.getDoNotDisturb());
                if (ModeSwitchHelper.getInstance().deviceSupportsModeSwitch(KnightDeviceFacade.this.mContext)) {
                    boolean z = false;
                    int i = Settings.Secure.getInt(KnightDeviceFacade.this.mContext.getContentResolver(), "alexa_ww_privacy_mode_enabled", 0);
                    KnightDeviceFacade knightDeviceFacade2 = KnightDeviceFacade.this;
                    if (i == 1) {
                        z = true;
                    }
                    knightDeviceFacade2.setIsPrivacyModeOn(z);
                } else {
                    KnightDeviceFacade knightDeviceFacade3 = KnightDeviceFacade.this;
                    knightDeviceFacade3.setIsPrivacyModeOn(knightDeviceFacade3.getPrivacyMode());
                }
                if (KnightDeviceFacade.this.isVestaDevice()) {
                    KnightDeviceFacade knightDeviceFacade4 = KnightDeviceFacade.this;
                    knightDeviceFacade4.setIsFreeTimeModeOn(knightDeviceFacade4.getFreeTimeMode());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVestaDevice() {
        return "Amazon".equalsIgnoreCase(Build.MANUFACTURER) && VESTA_MODEL.equals(Build.MODEL);
    }

    private <T> T queryDeviceMode(CursorQuery<T> cursorQuery) {
        Cursor query = this.mContext.getContentResolver().query(this.mDeviceModeUriProvider.provide(), null, null, null, null);
        try {
            if (query == null) {
                return cursorQuery.mo3239cursorNotFound();
            }
            query.moveToNext();
            T mo3240query = cursorQuery.mo3240query(query);
            query.close();
            return mo3240query;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    private <T> T queryDeviceOpMode(CursorQuery<T> cursorQuery) {
        Cursor query = this.mContext.getContentResolver().query(Uri.parse(SETTINGS_PROVIDER_URI), null, DEVICE_OP_MODE, null, null);
        if (query != null) {
            try {
                if (query.moveToLast()) {
                    T mo3240query = cursorQuery.mo3240query(query);
                    query.close();
                    return mo3240query;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        T mo3239cursorNotFound = cursorQuery.mo3239cursorNotFound();
        if (query != null) {
            query.close();
        }
        return mo3239cursorNotFound;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsDoNotDisturbOn(boolean z) {
        this.mIsDndOn.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsFreeTimeModeOn(boolean z) {
        this.mIsFreeTimeModeOn.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPrivacyModeOn(boolean z) {
        this.mIsPrivacyOn.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOOBEContract() {
        if (this.mUserSetupObserver == null) {
            this.mUserSetupObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.amazon.comms.device.KnightDeviceFacade.5
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    KnightDeviceFacade.this.setOOBEContract();
                }
            };
            this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(USER_SETUP_COMPLETE), false, this.mUserSetupObserver);
        }
        this.mUserSetupComplete = getSettings(USER_SETUP_COMPLETE);
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport1.outline107("OOBE done: "), this.mUserSetupComplete, sLog);
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
        try {
            this.mContext.unregisterReceiver(this.mPrivacyModeBroadcastReceiver);
        } catch (Exception unused2) {
            sLog.d("mPrivacyModeBroadcastReceiver is already unregistered");
        }
        this.mContext.getContentResolver().unregisterContentObserver(this.disableCameraObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.privilegedTaskObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mDropInAccessObserver);
        if (this.mDeviceOpModeObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mDeviceOpModeObserver);
            this.mDeviceOpModeObserver = null;
        }
        if (this.mUserSetupObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mUserSetupObserver);
        }
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean enableLocalVideoAtCallInitiation() {
        return true;
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
        return DeviceProperties.getScreenRotation(this.mContext);
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public String getWakeWord() {
        return "ALEXA";
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
        return this.mDropInAccessObserver.isDropInDisallowed();
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
        return this.mIsFreeTimeModeOn.get();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isMmSdkApplication() {
        return this.mIsMmSdkApplication;
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
        return this.mIsPrivacyOn.get();
    }

    @Override // com.amazon.comms.device.AvsDeviceFacade
    public boolean isPrivilegedTaskInProgress() {
        return this.privilegedTaskObserver.isPrivilegedTaskInProgress();
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

    KnightDeviceFacade(Context context, ContentResolverUriProvider contentResolverUriProvider) {
        this.mIsDndOn = new AtomicBoolean(false);
        this.mIsPrivacyOn = new AtomicBoolean(false);
        this.mIsFreeTimeModeOn = new AtomicBoolean(false);
        this.mDndModeBroadcastReceiver = new DndModeBroadcastReceiver();
        this.mPrivacyModeBroadcastReceiver = new PrivacyModeBroadcastReceiver();
        this.mUserSetupObserver = null;
        this.mDeviceOpModeObserver = null;
        this.mUserSetupComplete = false;
        this.supportsCameraShutterSettings = false;
        this.mIsMmSdkApplication = false;
        this.mContext = context;
        this.mCameraPresent = this.mContext.getPackageManager().hasSystemFeature("android.hardware.camera");
        this.mDeviceModeUriProvider = contentResolverUriProvider;
        initializeValues();
        context.registerReceiver(this.mDndModeBroadcastReceiver, createDndIntentFilter(), DO_NOT_DISTURB_PERMISSION, null);
        this.mIsMmSdkApplication = containsSystemFeature(MMSDK_FEATURE_NAME);
        this.privacyModeObserver = null;
        this.wakeWordObserver = null;
        if (this.mIsMmSdkApplication) {
            context.registerReceiver(this.mPrivacyModeBroadcastReceiver, createPrivacyModeIntentFilter(), PRIVACY_MODE_PERMISSION, null);
        } else if (ModeSwitchHelper.getInstance().deviceSupportsModeSwitch(context)) {
            context.registerReceiver(this.mPrivacyModeBroadcastReceiver, createPrivacyModeIntentFilter());
        } else {
            context.registerReceiver(this.mPrivacyModeBroadcastReceiver, createPrivacyModeIntentFilter(), BROADCASTER_PERMISSION, null);
        }
        setOOBEContract();
        this.disableCameraObserver = new DisableCameraObserver(new Handler(Looper.getMainLooper()), this.mContext);
        this.privilegedTaskObserver = new PrivilegedTaskObserver(new Handler(Looper.getMainLooper()));
        this.mDropInAccessObserver = new DropInAccessObserver(new Handler(Looper.getMainLooper()));
        this.supportsCameraShutterSettings = supportsSettings(CAMERA_SHUTTER_STATE);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(DISABLE_CAMERA), false, this.disableCameraObserver);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(PRIVILEGED_TASK_IN_PROGRESS), false, this.privilegedTaskObserver);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(DROP_IN_ACCESS), false, this.mDropInAccessObserver);
        if (isVestaDevice()) {
            this.mDeviceOpModeObserver = new DeviceOpModeObserver(new Handler(Looper.getMainLooper()));
            context.getContentResolver().registerContentObserver(Uri.parse(DEVICE_OP_MODE_URI), false, this.mDeviceOpModeObserver);
        }
    }
}
