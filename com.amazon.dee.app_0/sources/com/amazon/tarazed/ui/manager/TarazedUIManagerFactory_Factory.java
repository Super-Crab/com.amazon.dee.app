package com.amazon.tarazed.ui.manager;

import android.content.Context;
import android.os.Handler;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class TarazedUIManagerFactory_Factory implements Factory<TarazedUIManagerFactory> {
    private final Provider<ActivityTracker> activityTrackerProvider;
    private final Provider<ArcusHelper> arcusHelperProvider;
    private final Provider<BizMetricsHelper> bizMetricsHelperProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<Handler> mainLooperHandlerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final Provider<TarazedSessionNotifier> notifierProvider;
    private final Provider<TVUIManager> tvManagerProvider;
    private final Provider<ViewGroupManager> viewGroupManagerProvider;

    public TarazedUIManagerFactory_Factory(Provider<Context> provider, Provider<ViewGroupManager> provider2, Provider<TarazedSessionLogger> provider3, Provider<TarazedMetricsHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<TVUIManager> provider6, Provider<Handler> provider7, Provider<ActivityTracker> provider8, Provider<CoroutineScope> provider9, Provider<DispatcherProvider> provider10, Provider<ArcusHelper> provider11, Provider<BizMetricsHelper> provider12) {
        this.contextProvider = provider;
        this.viewGroupManagerProvider = provider2;
        this.loggerProvider = provider3;
        this.metricsHelperProvider = provider4;
        this.notifierProvider = provider5;
        this.tvManagerProvider = provider6;
        this.mainLooperHandlerProvider = provider7;
        this.activityTrackerProvider = provider8;
        this.coroutineScopeProvider = provider9;
        this.dispatchersProvider = provider10;
        this.arcusHelperProvider = provider11;
        this.bizMetricsHelperProvider = provider12;
    }

    public static TarazedUIManagerFactory_Factory create(Provider<Context> provider, Provider<ViewGroupManager> provider2, Provider<TarazedSessionLogger> provider3, Provider<TarazedMetricsHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<TVUIManager> provider6, Provider<Handler> provider7, Provider<ActivityTracker> provider8, Provider<CoroutineScope> provider9, Provider<DispatcherProvider> provider10, Provider<ArcusHelper> provider11, Provider<BizMetricsHelper> provider12) {
        return new TarazedUIManagerFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static TarazedUIManagerFactory newTarazedUIManagerFactory(Context context, Provider<ViewGroupManager> provider, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionNotifier tarazedSessionNotifier, Provider<TVUIManager> provider2, Handler handler, ActivityTracker activityTracker, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, ArcusHelper arcusHelper, BizMetricsHelper bizMetricsHelper) {
        return new TarazedUIManagerFactory(context, provider, tarazedSessionLogger, tarazedMetricsHelper, tarazedSessionNotifier, provider2, handler, activityTracker, coroutineScope, dispatcherProvider, arcusHelper, bizMetricsHelper);
    }

    public static TarazedUIManagerFactory provideInstance(Provider<Context> provider, Provider<ViewGroupManager> provider2, Provider<TarazedSessionLogger> provider3, Provider<TarazedMetricsHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<TVUIManager> provider6, Provider<Handler> provider7, Provider<ActivityTracker> provider8, Provider<CoroutineScope> provider9, Provider<DispatcherProvider> provider10, Provider<ArcusHelper> provider11, Provider<BizMetricsHelper> provider12) {
        return new TarazedUIManagerFactory(provider.mo10268get(), provider2, provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6, provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedUIManagerFactory mo10268get() {
        return provideInstance(this.contextProvider, this.viewGroupManagerProvider, this.loggerProvider, this.metricsHelperProvider, this.notifierProvider, this.tvManagerProvider, this.mainLooperHandlerProvider, this.activityTrackerProvider, this.coroutineScopeProvider, this.dispatchersProvider, this.arcusHelperProvider, this.bizMetricsHelperProvider);
    }
}
