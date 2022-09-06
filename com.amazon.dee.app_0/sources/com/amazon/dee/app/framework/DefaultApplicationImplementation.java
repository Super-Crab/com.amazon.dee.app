package com.amazon.dee.app.framework;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.DefaultCrashReportingServiceFactory;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.voice.ui.VoiceProcess;
import com.amazon.dee.app.dependencies.AccessoriesComponent;
import com.amazon.dee.app.dependencies.AccessoryModule;
import com.amazon.dee.app.dependencies.ApplicationComponent;
import com.amazon.dee.app.dependencies.ApplicationModule;
import com.amazon.dee.app.dependencies.DaggerAccessoriesComponent;
import com.amazon.dee.app.dependencies.DaggerApplicationComponent;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class DefaultApplicationImplementation implements ApplicationImplementation {
    private static final String TAG = Log.tag(DefaultApplicationImplementation.class);
    private CrashReportingService crashReportingService;
    private final String friendlyProcessName;

    /* loaded from: classes12.dex */
    public static class AccessoriesApplicationImplementation extends DefaultApplicationImplementation {
        private AccessoriesComponent accessoriesComponent;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AccessoriesApplicationImplementation(String str) {
            super(str);
        }

        @Override // com.amazon.dee.app.framework.DefaultApplicationImplementation, com.amazon.dee.app.framework.ApplicationImplementation
        public void onCreate(Application application) {
            super.onCreate(application);
            Log.i(DefaultApplicationImplementation.TAG, "Initializing accessories components in accessories' process.");
            this.accessoriesComponent = DaggerAccessoriesComponent.builder().accessoryModule(new AccessoryModule(application)).build();
            this.accessoriesComponent.accessories().bind(application);
        }
    }

    /* loaded from: classes12.dex */
    public static class VoiceApplicationImplementation extends DefaultApplicationImplementation {
        private ApplicationComponent component;
        private final VoiceProcess voiceProcess;

        /* JADX INFO: Access modifiers changed from: package-private */
        public VoiceApplicationImplementation(String str) {
            super(str);
            this.voiceProcess = new VoiceProcess();
        }

        @Override // com.amazon.dee.app.framework.DefaultApplicationImplementation, com.amazon.dee.app.framework.ApplicationImplementation
        public ApplicationComponent getComponent() {
            return this.component;
        }

        @Override // com.amazon.dee.app.framework.DefaultApplicationImplementation
        protected void initializeComponentRegistry(Application application) {
            Log.i(DefaultApplicationImplementation.TAG, "Initializing voice dependencies in Voice process.");
            this.component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(application)).build();
            this.component.componentBinder().registerComponents(application);
            this.component.componentBinder().publishTransitionalObjectsOwnedByDagger();
            this.component.eventBusService().start();
        }

        @Override // com.amazon.dee.app.framework.DefaultApplicationImplementation, com.amazon.dee.app.framework.ApplicationImplementation
        public void onCreate(Application application) {
            super.onCreate(application);
            this.voiceProcess.initialize(application.getApplicationContext(), this.component.eventBus());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultApplicationImplementation(String str) {
        this.friendlyProcessName = getFriendlyProcessName(str);
    }

    private String getFriendlyProcessName(String str) {
        String[] split = str.split(":");
        return split.length > 1 ? split[1] : str;
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public ApplicationComponent getComponent() {
        throw new UnsupportedOperationException();
    }

    protected void initializeComponentRegistry(Application application) {
        ComponentRegistry.init(application);
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public boolean isColdStart() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public boolean isNoActivityVisible() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public boolean isSingleSignOnBuild() {
        throw new UnsupportedOperationException();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void onCreate(Application application) {
        initializeComponentRegistry(application);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Initializing Bugsnag Crash Reporting Service for process: ");
        outline107.append(this.friendlyProcessName);
        Log.i(str, outline107.toString());
        this.crashReportingService = DefaultCrashReportingServiceFactory.newBugsnagInstance(application, this.friendlyProcessName);
        this.crashReportingService.start();
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void onTrimMemory(int i) {
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void reportMemoryStats() {
    }
}
