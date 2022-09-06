package com.amazon.alexa.mode.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mode.drive.HomeChannelInteractor;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.userstudy.ModeStatusLog;
import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import com.amazon.alexa.mode.util.AutomotiveDeviceRegistry;
import com.amazon.alexa.mode.util.CatapultTtsDeviceMonitor;
import com.amazon.alexa.mode.util.DriveModePreferences;
import com.amazon.alexa.mode.util.NotificationHelper;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes9.dex */
public class ModeModule {
    Context context;

    public ModeModule(Context context) {
        this.context = context;
    }

    @ModeScope
    @Provides
    public AutomotiveAccessoryConnectivityObserver provideAutomotiveAccessoryConnectivityObserver() {
        return new AutomotiveAccessoryConnectivityObserver();
    }

    @ModeScope
    @Provides
    public AutomotiveDeviceRegistry provideAutomotiveDeviceRegistry() {
        return new AutomotiveDeviceRegistry();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ModeScope
    @Provides
    public DriveModeMetrics provideDriveModeMetrics() {
        return new DriveModeMetrics();
    }

    @ModeScope
    @Provides
    public DriveModePreferences provideDriveModePreferences(PrefsDialogHelper prefsDialogHelper) {
        return new DriveModePreferences((EventBus) GeneratedOutlineSupport1.outline21(EventBus.class), prefsDialogHelper);
    }

    @ModeScope
    @Provides
    public HomeChannelInteractor provideHomeChannelInteractor() {
        return new HomeChannelInteractor();
    }

    @ModeScope
    @Provides
    public ModeStatusLog provideModeStatusLog() {
        return new ModeStatusLog(this.context);
    }

    @ModeScope
    @Provides
    public CatapultTtsDeviceMonitor provideMuffinOobeMonitor() {
        return new CatapultTtsDeviceMonitor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ModeScope
    @Provides
    public NotificationHelper provideNotificationHelper(Lazy<DriveModeMetrics> lazy) {
        return new NotificationHelper(this.context, lazy);
    }

    @ModeScope
    @Provides
    public PrefsDialogHelper providePrefsDialogHelper() {
        return new PrefsDialogHelper(this.context);
    }
}
