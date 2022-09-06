package com.amazon.tarazed.dagger.components;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.modules.GlobalModule;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideBizMetricsHelper$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideDeviceInfoUtility$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideMainCoroutineScope$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideSessionClientCache$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideTVIndicatorManager$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideTarazedLogger$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideTarazedMetricsHelper$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideTarazedSessionLogger$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideTarazedSessionNotifier$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.dagger.modules.GlobalModule_ProvideViewGroupManager$TarazedAndroidLibrary_releaseFactory;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class DaggerGlobalComponent implements GlobalComponent {
    private Provider<BizMetricsHelper> provideBizMetricsHelper$TarazedAndroidLibrary_releaseProvider;
    private Provider<DispatcherProvider> provideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseProvider;
    private Provider<DeviceInfoUtility> provideDeviceInfoUtility$TarazedAndroidLibrary_releaseProvider;
    private Provider<DeviceInfoUtilityAndroid> provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseProvider;
    private Provider<CoroutineScope> provideMainCoroutineScope$TarazedAndroidLibrary_releaseProvider;
    private Provider<SessionClientCache> provideSessionClientCache$TarazedAndroidLibrary_releaseProvider;
    private Provider<TVUIManager> provideTVIndicatorManager$TarazedAndroidLibrary_releaseProvider;
    private Provider<TarazedLogger> provideTarazedLogger$TarazedAndroidLibrary_releaseProvider;
    private Provider<TarazedMetricsHelper> provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider;
    private Provider<TarazedSessionLogger> provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider;
    private Provider<TarazedSessionNotifier> provideTarazedSessionNotifier$TarazedAndroidLibrary_releaseProvider;
    private Provider<ViewGroupManager> provideViewGroupManager$TarazedAndroidLibrary_releaseProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private GlobalModule globalModule;

        public GlobalComponent build() {
            Preconditions.checkBuilderRequirement(this.globalModule, GlobalModule.class);
            return new DaggerGlobalComponent(this);
        }

        public Builder globalModule(GlobalModule globalModule) {
            this.globalModule = (GlobalModule) Preconditions.checkNotNull(globalModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule));
        this.provideMainCoroutineScope$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideMainCoroutineScope$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule));
        this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideTarazedSessionLogger$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule));
        this.provideTarazedLogger$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideTarazedLogger$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule));
        this.provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule));
        this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideTarazedMetricsHelper$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule, this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider, this.provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseProvider));
        this.provideDeviceInfoUtility$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideDeviceInfoUtility$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule));
        this.provideBizMetricsHelper$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideBizMetricsHelper$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule, this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider, this.provideDeviceInfoUtility$TarazedAndroidLibrary_releaseProvider, this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider));
        this.provideTarazedSessionNotifier$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideTarazedSessionNotifier$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule, this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider));
        this.provideViewGroupManager$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideViewGroupManager$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule, this.provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseProvider, this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider, this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider));
        this.provideTVIndicatorManager$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideTVIndicatorManager$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule, this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider, this.provideViewGroupManager$TarazedAndroidLibrary_releaseProvider, this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider));
        this.provideSessionClientCache$TarazedAndroidLibrary_releaseProvider = DoubleCheck.provider(GlobalModule_ProvideSessionClientCache$TarazedAndroidLibrary_releaseFactory.create(builder.globalModule, this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider, this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider, this.provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseProvider));
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public BizMetricsHelper getBizMetricsHelper() {
        return this.provideBizMetricsHelper$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public CoroutineScope getCoroutineScope() {
        return this.provideMainCoroutineScope$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public DeviceInfoUtility getDeviceInfoUtility() {
        return this.provideDeviceInfoUtility$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public DeviceInfoUtilityAndroid getDeviceInfoUtilityAndroid() {
        return this.provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public DispatcherProvider getDispatcherProvider() {
        return this.provideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public TarazedLogger getLogger() {
        return this.provideTarazedLogger$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public TarazedMetricsHelper getMetricsHelper() {
        return this.provideTarazedMetricsHelper$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public SessionClientCache getSessionClientCache() {
        return this.provideSessionClientCache$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public TarazedSessionLogger getSessionLogger() {
        return this.provideTarazedSessionLogger$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public TarazedSessionNotifier getSessionNotifier() {
        return this.provideTarazedSessionNotifier$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public TVUIManager getTVIndicatorManager() {
        return this.provideTVIndicatorManager$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.GlobalComponent
    public ViewGroupManager getViewGroupManager() {
        return this.provideViewGroupManager$TarazedAndroidLibrary_releaseProvider.mo10268get();
    }

    private DaggerGlobalComponent(Builder builder) {
        initialize(builder);
    }
}
