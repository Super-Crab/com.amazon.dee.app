package com.amazon.rtcsc.capabilityagent.common.dependencies;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.rtcsc.capabilityagent.avs.RtcscCapabilityAgentServiceAVS;
import com.amazon.rtcsc.capabilityagent.avs.RtcscCapabilityAgentServiceAVS_MembersInjector;
import com.amazon.rtcsc.capabilityagent.avs.RtcscContextProvider;
import com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection;
import com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection_Factory;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscCapabilityAgent;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscCapabilityAgentEventListener;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscCapabilityAgentEventListener_Factory;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscCapabilityAgent_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerRtcscComponent implements RtcscComponent {
    private Provider<Context> providesApplicationContextProvider;
    private Provider<ScheduledExecutorService> providesConnectionExecutorProvider;
    private Provider<AlexaServicesConnection> providesRtcscAlexaServicesConnectionProvider;
    private Provider<RtcscContextProvider> providesRtcscContextProvider;
    private Provider<RtcscCapabilityAgentEventListener> rtcscCapabilityAgentEventListenerProvider;
    private Provider<RtcscCapabilityAgent> rtcscCapabilityAgentProvider;
    private Provider<WrappedAlexaConnection> wrappedAlexaConnectionProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;
        private ExecutorModule executorModule;

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public RtcscComponent build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.executorModule == null) {
                this.executorModule = new ExecutorModule();
            }
            return new DaggerRtcscComponent(this);
        }

        public Builder executorModule(ExecutorModule executorModule) {
            this.executorModule = (ExecutorModule) Preconditions.checkNotNull(executorModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesRtcscContextProvider = DoubleCheck.provider(ApplicationModule_ProvidesRtcscContextProviderFactory.create(builder.applicationModule));
        this.providesApplicationContextProvider = DoubleCheck.provider(ApplicationModule_ProvidesApplicationContextFactory.create(builder.applicationModule));
        this.providesRtcscAlexaServicesConnectionProvider = DoubleCheck.provider(ApplicationModule_ProvidesRtcscAlexaServicesConnectionFactory.create(builder.applicationModule, this.providesApplicationContextProvider));
        this.providesConnectionExecutorProvider = DoubleCheck.provider(ExecutorModule_ProvidesConnectionExecutorFactory.create(builder.executorModule));
        this.wrappedAlexaConnectionProvider = DoubleCheck.provider(WrappedAlexaConnection_Factory.create(this.providesRtcscAlexaServicesConnectionProvider, this.providesConnectionExecutorProvider));
        this.rtcscCapabilityAgentEventListenerProvider = DoubleCheck.provider(RtcscCapabilityAgentEventListener_Factory.create(this.providesApplicationContextProvider, this.wrappedAlexaConnectionProvider, this.providesRtcscContextProvider));
        this.rtcscCapabilityAgentProvider = DoubleCheck.provider(RtcscCapabilityAgent_Factory.create(this.providesApplicationContextProvider, this.rtcscCapabilityAgentEventListenerProvider));
    }

    private RtcscCapabilityAgentServiceAVS injectRtcscCapabilityAgentServiceAVS(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS) {
        RtcscCapabilityAgentServiceAVS_MembersInjector.injectMWrappedAlexaConnection(rtcscCapabilityAgentServiceAVS, this.wrappedAlexaConnectionProvider.mo10268get());
        RtcscCapabilityAgentServiceAVS_MembersInjector.injectMRtcscCapabilityAgent(rtcscCapabilityAgentServiceAVS, this.rtcscCapabilityAgentProvider.mo10268get());
        RtcscCapabilityAgentServiceAVS_MembersInjector.injectMRtcscContextProvider(rtcscCapabilityAgentServiceAVS, this.providesRtcscContextProvider.mo10268get());
        return rtcscCapabilityAgentServiceAVS;
    }

    @Override // com.amazon.rtcsc.capabilityagent.common.dependencies.RtcscComponent
    public RtcscContextProvider getRtcscContextProvider() {
        return this.providesRtcscContextProvider.mo10268get();
    }

    @Override // com.amazon.rtcsc.capabilityagent.common.dependencies.RtcscComponent
    public void inject(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS) {
        injectRtcscCapabilityAgentServiceAVS(rtcscCapabilityAgentServiceAVS);
    }

    @Override // com.amazon.rtcsc.capabilityagent.common.dependencies.RtcscComponent
    public void inject(RtcscContextProvider rtcscContextProvider) {
    }

    private DaggerRtcscComponent(Builder builder) {
        initialize(builder);
    }
}
