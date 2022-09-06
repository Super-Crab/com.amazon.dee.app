package com.amazon.tarazed.dagger.components;

import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import com.amazon.tarazed.appevent.AppEventSender;
import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner;
import com.amazon.tarazed.arcus.ArcusPeriodicSchedulerWorker;
import com.amazon.tarazed.arcus.ArcusSyncWorker;
import com.amazon.tarazed.dagger.modules.LibraryModule;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import com.amazon.tarazed.dagger.subcomponents.DebugSubcomponent;
import com.amazon.tarazed.init.TarazedInitializer;
import com.amazon.tarazed.receiver.ToggleQAModeReceiver;
import com.amazon.tarazed.sessionmanager.SessionClientCacheService;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import com.amazon.tarazed.ui.WindowParamsHelper;
import com.amazon.tarazed.utility.ContextHelper;
import com.amazon.tarazed.worker.logging.upload.TarazedLogUploadWorker;
import dagger.Component;
@Component(dependencies = {GlobalComponent.class}, modules = {LibraryModule.class})
@LibraryScope
/* loaded from: classes13.dex */
public interface LibraryComponent {
    ActivityTracker activityTracker();

    DebugSubcomponent.Builder debugSubcomponentBuilder();

    TarazedResourceManager getResourceManager();

    void inject(TarazedPrimerActivity tarazedPrimerActivity);

    void inject(AppEventSender appEventSender);

    void inject(TarazedAppLifeCycleOwner tarazedAppLifeCycleOwner);

    void inject(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker);

    void inject(ArcusSyncWorker arcusSyncWorker);

    void inject(TarazedInitializer tarazedInitializer);

    void inject(ToggleQAModeReceiver toggleQAModeReceiver);

    void inject(SessionClientCacheService sessionClientCacheService);

    void inject(TarazedSessionAndroidService tarazedSessionAndroidService);

    void inject(WindowParamsHelper windowParamsHelper);

    void inject(ContextHelper contextHelper);

    void inject(TarazedLogUploadWorker tarazedLogUploadWorker);
}
