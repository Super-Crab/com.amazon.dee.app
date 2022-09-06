package com.amazon.alexa.mode.statemachine;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.alexa.mode.bluetooth.AutoBluetoothObserver;
import com.amazon.alexa.mode.drive.HomeChannelInteractor;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.util.DriveModeFTUEHelper;
import com.amazon.alexa.mode.util.IdentityServiceHelper;
import com.amazon.alexa.mode.util.NotificationHelper;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
/* loaded from: classes9.dex */
public class StateDependencies {
    private final AutoBluetoothObserver mAutoBluetoothObserver;
    private final Context mContext;
    private final DriveModeFTUEHelper mDriveModeFTUEHelper;
    private final Lazy<DriveModeMetrics> mDriveModeMetrics;
    private final HomeChannelInteractor mHomeChannelInteractor;
    private final IdentityServiceHelper mIdentityServiceHelper;
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private final Lazy<NotificationHelper> mNotificationHelper;
    private final PrefsDialogHelper mPrefsDialogHelper;
    private final RoutingService mRoutingService;
    private final StateTransitionHelper mStateTransitionHelper;

    public StateDependencies(@NonNull PrefsDialogHelper prefsDialogHelper, @NonNull RoutingService routingService, @NonNull DriveModeFTUEHelper driveModeFTUEHelper, @NonNull Lazy<NotificationHelper> lazy, @NonNull Lazy<DriveModeMetrics> lazy2, @NonNull StateTransitionHelper stateTransitionHelper, @NonNull HomeChannelInteractor homeChannelInteractor, @NonNull Context context, @NonNull IdentityServiceHelper identityServiceHelper, @NonNull AutoBluetoothObserver autoBluetoothObserver) {
        this.mPrefsDialogHelper = prefsDialogHelper;
        this.mRoutingService = routingService;
        this.mDriveModeFTUEHelper = driveModeFTUEHelper;
        this.mNotificationHelper = lazy;
        this.mDriveModeMetrics = lazy2;
        this.mStateTransitionHelper = stateTransitionHelper;
        this.mHomeChannelInteractor = homeChannelInteractor;
        this.mContext = context;
        this.mIdentityServiceHelper = identityServiceHelper;
        this.mAutoBluetoothObserver = autoBluetoothObserver;
    }

    public AutoBluetoothObserver getAutoBluetoothObserver() {
        return this.mAutoBluetoothObserver;
    }

    public Context getContext() {
        return this.mContext;
    }

    public DriveModeFTUEHelper getDriveModeFTUEHelper() {
        return this.mDriveModeFTUEHelper;
    }

    public Lazy<DriveModeMetrics> getDriveModeMetrics() {
        return this.mDriveModeMetrics;
    }

    public HomeChannelInteractor getHomeChannelInteractor() {
        return this.mHomeChannelInteractor;
    }

    public IdentityServiceHelper getIdentityServiceHelper() {
        return this.mIdentityServiceHelper;
    }

    public Handler getMainThreadHandler() {
        return this.mMainThreadHandler;
    }

    public Lazy<NotificationHelper> getNotificationHelper() {
        return this.mNotificationHelper;
    }

    public PrefsDialogHelper getPrefsDialogHelper() {
        return this.mPrefsDialogHelper;
    }

    public RoutingService getRoutingService() {
        return this.mRoutingService;
    }

    public StateTransitionHelper getStateTransitionHelper() {
        return this.mStateTransitionHelper;
    }
}
