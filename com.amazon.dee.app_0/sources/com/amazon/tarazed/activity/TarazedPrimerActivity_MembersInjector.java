package com.amazon.tarazed.activity;

import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class TarazedPrimerActivity_MembersInjector implements MembersInjector<TarazedPrimerActivity> {
    private final Provider<ArcusHelper> arcusHelperProvider;
    private final Provider<BizMetricsHelper> bizMetricsHelperProvider;
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;

    public TarazedPrimerActivity_MembersInjector(Provider<ArcusHelper> provider, Provider<BizMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<CoroutineScope> provider4, Provider<TarazedMetricsHelper> provider5) {
        this.arcusHelperProvider = provider;
        this.bizMetricsHelperProvider = provider2;
        this.loggerProvider = provider3;
        this.coroutineScopeProvider = provider4;
        this.metricsHelperProvider = provider5;
    }

    public static MembersInjector<TarazedPrimerActivity> create(Provider<ArcusHelper> provider, Provider<BizMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<CoroutineScope> provider4, Provider<TarazedMetricsHelper> provider5) {
        return new TarazedPrimerActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectArcusHelper(TarazedPrimerActivity tarazedPrimerActivity, ArcusHelper arcusHelper) {
        tarazedPrimerActivity.arcusHelper = arcusHelper;
    }

    public static void injectBizMetricsHelper(TarazedPrimerActivity tarazedPrimerActivity, BizMetricsHelper bizMetricsHelper) {
        tarazedPrimerActivity.bizMetricsHelper = bizMetricsHelper;
    }

    public static void injectCoroutineScope(TarazedPrimerActivity tarazedPrimerActivity, CoroutineScope coroutineScope) {
        tarazedPrimerActivity.coroutineScope = coroutineScope;
    }

    public static void injectLogger(TarazedPrimerActivity tarazedPrimerActivity, TarazedSessionLogger tarazedSessionLogger) {
        tarazedPrimerActivity.logger = tarazedSessionLogger;
    }

    public static void injectMetricsHelper(TarazedPrimerActivity tarazedPrimerActivity, TarazedMetricsHelper tarazedMetricsHelper) {
        tarazedPrimerActivity.metricsHelper = tarazedMetricsHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TarazedPrimerActivity tarazedPrimerActivity) {
        injectArcusHelper(tarazedPrimerActivity, this.arcusHelperProvider.mo10268get());
        injectBizMetricsHelper(tarazedPrimerActivity, this.bizMetricsHelperProvider.mo10268get());
        injectLogger(tarazedPrimerActivity, this.loggerProvider.mo10268get());
        injectCoroutineScope(tarazedPrimerActivity, this.coroutineScopeProvider.mo10268get());
        injectMetricsHelper(tarazedPrimerActivity, this.metricsHelperProvider.mo10268get());
    }
}
