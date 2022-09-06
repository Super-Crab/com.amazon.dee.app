package com.amazon.alexa.handsfree.protocols.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncIncomingMessageHandler;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncManager;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncService;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import dagger.Lazy;
import dagger.Subcomponent;
@Subcomponent
/* loaded from: classes8.dex */
public interface FalcoProtocolComponent extends AhfComponentProtocol {
    Lazy<AlexaAppSignInContract> alexaAppSignInContractLazy();

    Context applicationContext();

    Lazy<ApplicationInformationProvider> applicationInformationProviderLazy();

    Lazy<CrashReportRecorder> crashReportRecorderLazy();

    DataSyncIncomingMessageHandler dataSyncIncomingMessageHandler();

    DataSyncManager dataSyncManager();

    Lazy<EnrollmentTypeResolver> enrollmentTypeResolverLazy();

    HandsFreeUserIdentityProvider handsFreeUserIdentityProvider();

    void inject(DataSyncService dataSyncService);

    MetricsBuilderProvider metricsBuilderProvider();

    Lazy<MetricsBuilderProvider> metricsBuilderProviderLazy();

    MetricsConfiguration metricsConfiguration();
}
