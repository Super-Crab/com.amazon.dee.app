package com.amazon.tarazed.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import androidx.work.WorkManager;
import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleObserver;
import com.amazon.tarazed.arcus.ArcusConstants;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.android.DisplayRotationListener;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import com.amazon.tarazed.dagger.subcomponents.DebugSubcomponent;
import com.amazon.tarazed.sessionmanager.HeadsUpNotificationManager;
import com.amazon.tarazed.sessionmanager.TarazedController;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
import com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.amazon.tarazed.worker.logging.upload.TarazedLogUploadScheduler;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.Module;
import dagger.Provides;
import kotlinx.coroutines.CoroutineScope;
@Module(subcomponents = {DebugSubcomponent.class})
/* loaded from: classes13.dex */
public class LibraryModule {
    private static final String TARAZED_PREFERENCES_FILE_NAME = "tarazedPreferences";
    private final Context context;

    public LibraryModule(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public ArcusHelper provideArcusHelper(WorkManager workManager, RemoteConfigurationManager remoteConfigurationManager, TarazedLogger tarazedLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return new ArcusHelper(workManager, remoteConfigurationManager, tarazedLogger, tarazedMetricsHelper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public Context provideContext() {
        return this.context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public DisplayRotationListener provideDisplayRotationListener(TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return new DisplayRotationListener(this.context, tarazedSessionLogger, tarazedMetricsHelper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public Handler provideMainLooperHandler() {
        return new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public RemoteConfigurationManager provideRemoteConfigurationManager(DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        return RemoteConfigurationManager.forAppId(this.context, ArcusConstants.APP_ID).withDefaultConfiguration(deviceInfoUtilityAndroid.isFireTVEdition() ^ true ? ArcusConstants.DEFAULT_SUPPORTED_CONFIGURATION : ArcusConstants.DEFAULT_UNSUPPORTED_CONFIGURATION).createOrGet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public SharedPreferences provideSharedPreferences() {
        return this.context.getSharedPreferences(TARAZED_PREFERENCES_FILE_NAME, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public TarazedAppLifeCycleObserver provideTarazedAppLifeCycleObserver(TarazedLogger tarazedLogger, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, ArcusHelper arcusHelper, TarazedSessionNotifier tarazedSessionNotifier, BizMetricsHelper bizMetricsHelper) {
        return new TarazedAppLifeCycleObserver(tarazedLogger, this.context, coroutineScope, dispatcherProvider, arcusHelper, tarazedSessionNotifier, bizMetricsHelper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public TarazedController provideTarazedController(TarazedResourceManager tarazedResourceManager, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid, BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetectorToResumeSuspendedSession, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, HeadsUpNotificationManager headsUpNotificationManager) {
        return new TarazedController(tarazedResourceManager, tarazedMetricsHelper, tarazedSessionLogger, deviceInfoUtilityAndroid, browserPresenceDetectorToResumeSuspendedSession, coroutineScope, dispatcherProvider, headsUpNotificationManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public TarazedEventDispatcher provideTarazedEventDispatcher(TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return new TarazedEventDispatcher(tarazedSessionLogger, tarazedMetricsHelper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public TarazedIoTManager provideTarazedIoTManager(TarazedEventDispatcher tarazedEventDispatcher, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionNotifier tarazedSessionNotifier) {
        return new TarazedIoTManager(tarazedEventDispatcher, tarazedSessionLogger, tarazedMetricsHelper, tarazedSessionNotifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public TarazedLogUploadScheduler provideTarazedLogUploadScheduler(WorkManager workManager, TarazedLogger tarazedLogger) {
        return new TarazedLogUploadScheduler(workManager, tarazedLogger);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @LibraryScope
    public WorkManager provideWorkManager() {
        return WorkManager.getInstance(this.context);
    }
}
