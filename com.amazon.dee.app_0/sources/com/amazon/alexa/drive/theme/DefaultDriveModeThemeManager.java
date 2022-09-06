package com.amazon.alexa.drive.theme;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class DefaultDriveModeThemeManager implements DriveModeThemeManager {
    private static final float ALS_THRESHOLD_VALUE = 10.0f;
    private static final String COMMS_INIT_COMPLETE_EVENT = "comms::eventBus:initialized";
    private static final int DELAY_BETWEEN_THEME_SWITCH_MS = 5000;
    private static final String TAG = "DefaultDriveModeThemeManager";
    private SimpleMultiFilterSubscriber mCommsInitCompleteEventSubscriber;
    private Context mContext;
    private float mCurrentALSValue = -1.0f;
    private DriveModeThemeManager.ThemeType mCurrentAlsBasedTheme;
    private DriveModeThemeManager.ThemeType mCurrentDefaultThemeType;
    private Location mCurrentPassiveLocation;
    private DriveModeMetrics mDriveModeMetrics;
    private EventBus mEventBus;
    private Handler mHandler;
    private boolean mIsDriveModeThemeSwitchEnabled;
    private SensorEventListener mSensorEventListener;
    private SensorManager mSensorManager;
    private AlexaServicesConnection mServicesConnection;
    private SunriseTimeProvider mSunriseTimeProvider;
    @VisibleForTesting
    ThemeAlexaServicesConnectionListener mThemeAlexaServicesConnectionListener;

    /* renamed from: com.amazon.alexa.drive.theme.DefaultDriveModeThemeManager$2  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeThemeManager$ThemeType = new int[DriveModeThemeManager.ThemeType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeThemeManager$ThemeType[DriveModeThemeManager.ThemeType.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drivemode$api$DriveModeThemeManager$ThemeType[DriveModeThemeManager.ThemeType.DARK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes7.dex */
    public class ThemeAlexaServicesConnectionListener implements AlexaServicesConnection.ConnectionListener {
        ThemeAlexaServicesConnectionListener() {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            DefaultDriveModeThemeManager defaultDriveModeThemeManager = DefaultDriveModeThemeManager.this;
            defaultDriveModeThemeManager.setDriveModeTheme(defaultDriveModeThemeManager.getCurrentThemeType());
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
        }
    }

    public DefaultDriveModeThemeManager(Context context, AlexaServicesConnection alexaServicesConnection, DriveModeMetrics driveModeMetrics, SunriseTimeProvider sunriseTimeProvider) {
        this.mContext = context;
        this.mDriveModeMetrics = driveModeMetrics;
        this.mServicesConnection = alexaServicesConnection;
        this.mSunriseTimeProvider = sunriseTimeProvider;
        this.mCurrentDefaultThemeType = getDriveModeDefaultThemeType(getPrefsDialogHelper(context));
    }

    @TargetApi(23)
    private boolean checkLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    private DriveModeThemeManager.ThemeType getCurrentDefaultThemeType() {
        return this.mCurrentDefaultThemeType;
    }

    private DriveModeThemeManager.ThemeType getDriveModeDefaultThemeType(PrefsDialogHelper prefsDialogHelper) {
        int preferenceValue = prefsDialogHelper.getPreferenceValue(Constants.DRIVE_MODE_DARK_MODE_SETTING);
        String str = TAG;
        Log.i(str, "getDriveModeDefaultThemeType " + preferenceValue);
        if (preferenceValue != -1) {
            return DriveModeThemeManager.ThemeType.DARK;
        }
        return DriveModeThemeManager.ThemeType.LIGHT;
    }

    private boolean getDriveModeThemeAutoSwitchEnabledValue(PrefsDialogHelper prefsDialogHelper) {
        int preferenceValue = prefsDialogHelper.getPreferenceValue(Constants.DRIVE_MODE_AUTO_SWITCH_SETTING);
        String str = TAG;
        Log.i(str, "isDriveModeThemeAutoSwitchEnabled " + preferenceValue);
        return preferenceValue == 1;
    }

    private void initAmbientLightSensor() {
        Log.i(TAG, "initAmbientLightSensor");
        initSensorListener();
        if (isDriveModeThemeSwitchEnabled()) {
            if (this.mSensorManager.getDefaultSensor(5) != null) {
                Log.i(TAG, "Device has ALS");
                this.mSensorManager.registerListener(this.mSensorEventListener, this.mSensorManager.getDefaultSensor(5), 2);
                return;
            }
            Log.e(TAG, "Device has no ALS");
            return;
        }
        Log.i(TAG, "Day night mode disabled");
    }

    private void initCommsInitCompleteListener() {
        Log.i(TAG, "initCommsInitCompleteListener");
        this.mCommsInitCompleteEventSubscriber = new SimpleMultiFilterSubscriber();
        this.mCommsInitCompleteEventSubscriber.subscribe(new EventTypeMessageFilter(COMMS_INIT_COMPLETE_EVENT), new MessageHandler() { // from class: com.amazon.alexa.drive.theme.-$$Lambda$DefaultDriveModeThemeManager$MJIXWw0Y95NAk1olMRUwIXbyKxo
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultDriveModeThemeManager.this.lambda$initCommsInitCompleteListener$2$DefaultDriveModeThemeManager(message);
            }
        });
        this.mEventBus.subscribe(this.mCommsInitCompleteEventSubscriber);
    }

    private void initSensorListener() {
        this.mSensorEventListener = new SensorEventListener() { // from class: com.amazon.alexa.drive.theme.DefaultDriveModeThemeManager.1
            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                String unused = DefaultDriveModeThemeManager.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onSensorChanged ");
                outline107.append(sensorEvent.values[0]);
                outline107.toString();
                float f = sensorEvent.values[0];
                if (sensorEvent.sensor.getType() == 5) {
                    if (f > DefaultDriveModeThemeManager.ALS_THRESHOLD_VALUE) {
                        if (DefaultDriveModeThemeManager.this.mCurrentALSValue > DefaultDriveModeThemeManager.ALS_THRESHOLD_VALUE) {
                            return;
                        }
                        Log.i(DefaultDriveModeThemeManager.TAG, "setting day theme");
                        DefaultDriveModeThemeManager.this.mCurrentALSValue = f;
                        DefaultDriveModeThemeManager.this.handleAlsUpdate(DriveModeThemeManager.ThemeType.LIGHT);
                    } else if (DefaultDriveModeThemeManager.this.mCurrentALSValue <= DefaultDriveModeThemeManager.ALS_THRESHOLD_VALUE && DefaultDriveModeThemeManager.this.mCurrentALSValue != -1.0f) {
                    } else {
                        Log.i(DefaultDriveModeThemeManager.TAG, "setting night theme");
                        DefaultDriveModeThemeManager.this.mCurrentALSValue = f;
                        DefaultDriveModeThemeManager.this.handleAlsUpdate(DriveModeThemeManager.ThemeType.DARK);
                    }
                }
            }
        };
    }

    private boolean isDriveModeThemeSwitchEnabled() {
        return this.mIsDriveModeThemeSwitchEnabled;
    }

    private void publishThemeChangedEvent(boolean z) {
        Message build;
        GeneratedOutlineSupport1.outline173("publishThemeChangedEvent ", z, TAG);
        DriveModeThemeManager.ThemeType currentThemeType = getCurrentThemeType();
        Message.Builder payload = new Message.Builder().setEventType(DriveModeThemeManager.DRIVE_MODE_THEME_CHANGE_EVENT).setPayload(currentThemeType == DriveModeThemeManager.ThemeType.DARK ? DriveModeThemeManager.MOSAIC_THEME_DARK : DriveModeThemeManager.MOSAIC_THEME_LIGHT);
        if (z) {
            build = payload.build();
        } else {
            build = payload.setSource(Message.Source.TComm).build();
        }
        EventBus eventBus = this.mEventBus;
        if (eventBus != null) {
            eventBus.publish(build);
        }
        setDriveModeTheme(currentThemeType);
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public void destroy() {
        Log.i(TAG, "destroy");
        this.mSensorManager.unregisterListener(this.mSensorEventListener);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mServicesConnection.disconnect();
        this.mServicesConnection.deregisterListener(this.mThemeAlexaServicesConnectionListener);
        this.mEventBus.unsubscribe(this.mCommsInitCompleteEventSubscriber);
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public int getColorFromAttribute(Context context, int i) {
        return ThemeUtil.getColorFromAttribute(context, i);
    }

    Location getCurrentPassiveLocation() {
        if (!checkLocationPermission(this.mContext)) {
            Log.w(TAG, "Location Permission unavailable!");
            return null;
        }
        return ((LocationManager) this.mContext.getSystemService("location")).getLastKnownLocation("passive");
    }

    DriveModeThemeManager.ThemeType getCurrentThemeType() {
        if (isDriveModeThemeSwitchEnabled()) {
            DriveModeThemeManager.ThemeType themeType = this.mCurrentAlsBasedTheme;
            if (themeType != null) {
                return themeType;
            }
            if (this.mCurrentPassiveLocation != null) {
                Log.i(TAG, "ALS based theme null, fetching theme based on sunrise ");
                return getThemeBasedOnSunriseTime(this.mCurrentPassiveLocation);
            }
            Log.i(TAG, "Return theme from default setting");
            return getCurrentDefaultThemeType();
        }
        Log.i(TAG, "Return theme from default setting");
        return getCurrentDefaultThemeType();
    }

    PrefsDialogHelper getPrefsDialogHelper(Context context) {
        return new PrefsDialogHelper(context);
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public int getTheme() {
        Log.i(TAG, "getTheme");
        if (getCurrentThemeType().ordinal() != 0) {
            return R.style.Theme_Mosaic_Dark;
        }
        return R.style.Theme_Mosaic_Light;
    }

    DriveModeThemeManager.ThemeType getThemeBasedOnSunriseTime(@NonNull Location location) {
        Log.i(TAG, "getThemeBasedOnSunriseTime");
        boolean isDay = this.mSunriseTimeProvider.isDay(location.getLatitude(), location.getLongitude());
        GeneratedOutlineSupport1.outline173("isDay ", isDay, TAG);
        if (isDay) {
            return DriveModeThemeManager.ThemeType.LIGHT;
        }
        return DriveModeThemeManager.ThemeType.DARK;
    }

    void handleAlsUpdate(final DriveModeThemeManager.ThemeType themeType) {
        if (isDriveModeThemeSwitchEnabled()) {
            Log.i(TAG, "handleAlsUpdate");
            Handler handler = this.mHandler;
            if (handler == null) {
                return;
            }
            handler.removeCallbacksAndMessages(null);
            this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.drive.theme.-$$Lambda$DefaultDriveModeThemeManager$HHXXHn9E4gzYspIEjOBbKDkozFE
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultDriveModeThemeManager.this.lambda$handleAlsUpdate$0$DefaultDriveModeThemeManager(themeType);
                }
            }, 5000L);
        }
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public void init(Context context) {
        Log.i(TAG, "init");
        PrefsDialogHelper prefsDialogHelper = getPrefsDialogHelper(context);
        initializeThemeManager(context, getDriveModeThemeAutoSwitchEnabledValue(prefsDialogHelper), getDriveModeDefaultThemeType(prefsDialogHelper), (SensorManager) context.getSystemService("sensor"), (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class));
    }

    void initializeThemeManager(Context context, boolean z, DriveModeThemeManager.ThemeType themeType, SensorManager sensorManager, EventBus eventBus) {
        Log.i(TAG, "initializeThemeManager");
        this.mContext = context;
        this.mHandler = new Handler();
        this.mCurrentPassiveLocation = getCurrentPassiveLocation();
        this.mIsDriveModeThemeSwitchEnabled = z;
        this.mCurrentDefaultThemeType = themeType;
        this.mSensorManager = sensorManager;
        initAmbientLightSensor();
        this.mEventBus = eventBus;
        initCommsInitCompleteListener();
        this.mThemeAlexaServicesConnectionListener = new ThemeAlexaServicesConnectionListener();
        this.mServicesConnection.registerListener(this.mThemeAlexaServicesConnectionListener);
        this.mServicesConnection.connect();
        publishThemeChangedEvent(false);
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public boolean isCurrentThemeDark() {
        Log.i(TAG, "isCurrentThemeDark");
        return getCurrentThemeType().ordinal() != 0;
    }

    public /* synthetic */ void lambda$handleAlsUpdate$0$DefaultDriveModeThemeManager(DriveModeThemeManager.ThemeType themeType) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getCurrentThemeType ");
        outline107.append(getCurrentThemeType().name());
        Log.i(str, outline107.toString());
        if (themeType != getCurrentThemeType()) {
            if (themeType == DriveModeThemeManager.ThemeType.DARK) {
                this.mDriveModeMetrics.logNightModeTriggered();
            } else {
                this.mDriveModeMetrics.logDayModeTriggered();
            }
            this.mCurrentAlsBasedTheme = themeType;
            notifyThemeValue();
        }
    }

    public /* synthetic */ void lambda$initCommsInitCompleteListener$2$DefaultDriveModeThemeManager(Message message) {
        Log.i(TAG, "Comms init complete, publishing theme event");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.theme.-$$Lambda$DefaultDriveModeThemeManager$t_I1MFxEZNocfWsH-Y9ZB4fv97o
            @Override // java.lang.Runnable
            public final void run() {
                DefaultDriveModeThemeManager.this.lambda$null$1$DefaultDriveModeThemeManager();
            }
        });
    }

    public /* synthetic */ void lambda$null$1$DefaultDriveModeThemeManager() {
        publishThemeChangedEvent(false);
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public void notifyThemeValue() {
        publishThemeChangedEvent(true);
    }

    @VisibleForTesting
    void setDriveModeTheme(DriveModeThemeManager.ThemeType themeType) {
        String str = TAG;
        Log.i(str, "setDriveModeTheme " + themeType);
        if (this.mServicesConnection.isConnected()) {
            AlexaServicesApis.DriveMode.setDriveModeTheme(this.mServicesConnection, themeType == DriveModeThemeManager.ThemeType.DARK);
        }
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeThemeManager
    public void setSystemBarTheme(Context context) {
        Log.i(TAG, "setSystemBarTheme");
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, getTheme());
            context.getResources().getColor(R.color.DriveMode_Background);
            int i = Build.VERSION.SDK_INT;
            int i2 = 0;
            if (isCurrentThemeDark()) {
                window.getDecorView().setSystemUiVisibility(0);
            } else {
                window.getDecorView().setSystemUiVisibility(8192);
            }
            window.setStatusBarColor(getColorFromAttribute(contextThemeWrapper, R.attr.mosaicBackground));
            context.getResources().getColor(R.color.DriveMode_Background);
            int i3 = Build.VERSION.SDK_INT;
            View decorView = window.getDecorView();
            if (!isCurrentThemeDark()) {
                i2 = 8208;
            }
            decorView.setSystemUiVisibility(i2);
            window.setNavigationBarColor(getColorFromAttribute(contextThemeWrapper, R.attr.mosaicBackground));
        }
    }

    @VisibleForTesting
    DefaultDriveModeThemeManager(Context context, AlexaServicesConnection alexaServicesConnection, DriveModeMetrics driveModeMetrics, SunriseTimeProvider sunriseTimeProvider, PrefsDialogHelper prefsDialogHelper) {
        this.mContext = context;
        this.mDriveModeMetrics = driveModeMetrics;
        this.mServicesConnection = alexaServicesConnection;
        this.mSunriseTimeProvider = sunriseTimeProvider;
        this.mCurrentDefaultThemeType = getDriveModeDefaultThemeType(prefsDialogHelper);
    }
}
