package com.amazon.alexa.crashreporting;

import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.CrashReportingComponent;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DefaultCrashReportingService implements CrashReportingService {
    private static final String AMAZON_DOMAIN = "@amazon.";
    private static final String FEATURES_UPDATED_EVENT = "featureServiceV2:internal:featuresUpdated";
    private static final Object INIT_LOCK = new Object();
    private static final String TAG = "DefaultCrashReportingService";
    private static final String WEBLAB_TO_EMIT_EMAIL = "ALEXA_MOBILE_PERFORMANCE_V1";
    private static volatile DefaultCrashReportingService singleton;
    private final Provider<IdentityService> checkedIdentityService;
    private final List<CrashReportingComponent> crashReportingComponents;
    private final Provider<EventBus> eventBus;
    private final Provider<FeatureServiceV2> featureServiceV2;
    private MultiFilterSubscriber featureV2Subscription;
    MultiFilterSubscriber.FilterUuid userFilterUuid;
    @GuardedBy("INIT_LOCK")
    private boolean isStarted = false;
    private MultiFilterSubscriber userIdentitySubscription = null;
    private final Set<CrashReportingService.CrashObserver> externalObservers = new CopyOnWriteArraySet();
    private final Thread.UncaughtExceptionHandler systemExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    @VisibleForTesting
    DefaultCrashReportingService(List<CrashReportingComponent> list, Provider<IdentityService> provider, Provider<EventBus> provider2, Provider<FeatureServiceV2> provider3) {
        this.crashReportingComponents = Collections.unmodifiableList(new ArrayList(list));
        this.checkedIdentityService = provider;
        this.eventBus = provider2;
        this.featureServiceV2 = provider3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCrash(Thread thread, Throwable th) {
        for (CrashReportingService.CrashObserver crashObserver : this.externalObservers) {
            try {
                crashObserver.onCrash();
            } catch (Throwable unused) {
            }
        }
        for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
            try {
                if (crashReportingComponent.isInitialized()) {
                    crashReportingComponent.handleCrash(thread, th);
                }
            } catch (Throwable unused2) {
            }
        }
        this.systemExceptionHandler.uncaughtException(thread, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static DefaultCrashReportingService init(@NonNull List<CrashReportingComponent> list, @NonNull Provider<IdentityService> provider, @NonNull Provider<EventBus> provider2, @NonNull Provider<FeatureServiceV2> provider3) {
        synchronized (INIT_LOCK) {
            if (singleton == null) {
                singleton = new DefaultCrashReportingService(list, provider, provider2, provider3);
            } else {
                throw new IllegalStateException("Already initialized.");
            }
        }
        return singleton;
    }

    private void initializeComponents() {
        synchronized (INIT_LOCK) {
            for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
                maybeInitializeComponent(crashReportingComponent);
            }
        }
    }

    static boolean isInitialized() {
        boolean z;
        synchronized (INIT_LOCK) {
            z = singleton != null;
        }
        return z;
    }

    private void maybeInitializeComponent(CrashReportingComponent crashReportingComponent) {
        synchronized (INIT_LOCK) {
            if (!crashReportingComponent.isInitialized()) {
                try {
                    crashReportingComponent.initialize();
                } catch (CrashReportingComponent.InitializationException e) {
                    String str = TAG;
                    Log.w(str, "Failed to initialize " + crashReportingComponent.getFriendlyName(), e);
                }
            }
        }
    }

    @VisibleForTesting
    static DefaultCrashReportingService singleton() {
        DefaultCrashReportingService defaultCrashReportingService;
        synchronized (INIT_LOCK) {
            if (singleton != null) {
                defaultCrashReportingService = singleton;
            } else {
                throw new IllegalStateException("Must call init() before requesting singleton.");
            }
        }
        return defaultCrashReportingService;
    }

    private void startObservingFeatureV2Availability() {
        if (this.featureV2Subscription == null && this.eventBus.mo10268get() != null) {
            this.featureV2Subscription = this.eventBus.mo10268get().getNewSubscriber();
        }
        MultiFilterSubscriber multiFilterSubscriber = this.featureV2Subscription;
        if (multiFilterSubscriber != null) {
            multiFilterSubscriber.subscribeFilter($$Lambda$DefaultCrashReportingService$yOLmhSTcJPKdsRSx03QEyAlE.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingService$SERDbnSy27WwOxV0I3MI_s7mwSU
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    DefaultCrashReportingService.this.lambda$startObservingFeatureV2Availability$3$DefaultCrashReportingService(message);
                }
            });
        }
    }

    private void startObservingIdentityAvailability() {
        if (this.checkedIdentityService.mo10268get() == null) {
            return;
        }
        if (this.userIdentitySubscription == null && this.eventBus.mo10268get() != null) {
            this.userIdentitySubscription = this.eventBus.mo10268get().getSubscriber();
        }
        MultiFilterSubscriber multiFilterSubscriber = this.userIdentitySubscription;
        if (multiFilterSubscriber == null) {
            return;
        }
        this.userFilterUuid = multiFilterSubscriber.subscribeFilter($$Lambda$DefaultCrashReportingService$zW0s17Amz90pJptH9RIioJUVykE.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingService$p3YjVrUbYi7G7oZx5YHBavoadkU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultCrashReportingService.this.lambda$startObservingIdentityAvailability$1$DefaultCrashReportingService(message);
            }
        });
    }

    private void stopObservingFeatureAvailability() {
        MultiFilterSubscriber multiFilterSubscriber = this.userIdentitySubscription;
        if (multiFilterSubscriber != null) {
            multiFilterSubscriber.unsubscribeFilter(this.userFilterUuid);
            this.userFilterUuid = null;
        }
        if (this.featureV2Subscription == null || this.eventBus.mo10268get() == null) {
            return;
        }
        this.eventBus.mo10268get().unsubscribe(this.featureV2Subscription);
        this.featureV2Subscription = null;
    }

    private void syncFeatureV2ToBugsnag() {
        if (this.featureServiceV2.mo10268get() == null || !(this.featureServiceV2.mo10268get() instanceof PlatformFeatureServiceV2)) {
            return;
        }
        Map<String, String> allFeatures = ((PlatformFeatureServiceV2) this.featureServiceV2.mo10268get()).getAllFeatures();
        for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
            if (crashReportingComponent instanceof BugsnagIntegration) {
                ((BugsnagIntegration) crashReportingComponent).syncFeaturesV2(allFeatures);
            }
        }
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingService
    public void addObserver(CrashReportingService.CrashObserver crashObserver) {
        this.externalObservers.add(crashObserver);
    }

    public /* synthetic */ void lambda$startObservingFeatureV2Availability$3$DefaultCrashReportingService(Message message) {
        syncFeatureV2ToBugsnag();
    }

    public /* synthetic */ void lambda$startObservingIdentityAvailability$1$DefaultCrashReportingService(Message message) {
        syncUserToBugsnag(this.checkedIdentityService.mo10268get().getUser(TAG));
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingService
    public void putMetadata(String str, String str2) {
        for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
            crashReportingComponent.putMetadata(str, str2);
        }
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingService
    public void reportNonFatal(Throwable th) {
        for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
            crashReportingComponent.reportNonFatal(th);
        }
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingService
    public void setAccount(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
            crashReportingComponent.setAccount(str, str2, str3);
        }
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void start() {
        synchronized (INIT_LOCK) {
            if (!this.isStarted) {
                Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingService$gR5Rl9ciVaZML8ZI0Pd1Ydb8wyE
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public final void uncaughtException(Thread thread, Throwable th) {
                        DefaultCrashReportingService.this.handleCrash(thread, th);
                    }
                });
                for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
                    if (crashReportingComponent instanceof BugsnagIntegration) {
                        maybeInitializeComponent(crashReportingComponent);
                    }
                }
                startObservingIdentityAvailability();
                startObservingFeatureV2Availability();
                initializeComponents();
                this.isStarted = true;
            }
        }
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void stop() {
        synchronized (INIT_LOCK) {
            if (this.isStarted) {
                stopObservingFeatureAvailability();
                Thread.setDefaultUncaughtExceptionHandler(this.systemExceptionHandler);
                this.isStarted = false;
            }
        }
    }

    @VisibleForTesting
    void syncUserToBugsnag(UserIdentity userIdentity) {
        String email = userIdentity == null ? "" : userIdentity.getEmail();
        boolean z = false;
        if (this.featureServiceV2.mo10268get() != null && (this.featureServiceV2.mo10268get() instanceof PlatformFeatureServiceV2)) {
            z = this.featureServiceV2.mo10268get().hasAccess(WEBLAB_TO_EMIT_EMAIL, false);
        }
        if (!z || !email.contains(AMAZON_DOMAIN)) {
            return;
        }
        syncFeatureV2ToBugsnag();
        for (CrashReportingComponent crashReportingComponent : this.crashReportingComponents) {
            if (crashReportingComponent instanceof BugsnagIntegration) {
                ((BugsnagIntegration) crashReportingComponent).syncUser(email);
            }
        }
    }
}
