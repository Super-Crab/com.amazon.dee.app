package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.PreconditionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningNotGranted;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenFailedValidationException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenNotFoundException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WorkflowFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesErrorMapperFactory implements Factory<WJErrorMapper<Throwable>> {
    private final Provider<WJErrorMapper<ConnectionFailureException>> connectionFailureExceptionWJErrorMapperProvider;
    private final Provider<WJErrorMapper<DSSClientError>> dssClientErrorWJErrorMapperProvider;
    private final ErrorCodeMapperModule module;
    private final Provider<WJErrorMapper<PreconditionFailureException>> preconditionFailureExceptionWJErrorMapperProvider;
    private final Provider<WJErrorMapper<ProvisionerCommandError>> provisionerCommandErrorWJErrorMapperProvider;
    private final Provider<WJErrorMapper<ProvisioningFailureException>> provisioningFailureExceptionWJErrorMapperProvider;
    private final Provider<WJErrorMapper<ProvisioningNotGranted>> provisioningNotGrantedWJErrorMapperProvider;
    private final Provider<WJErrorMapper<TrustProviderInitializationFailedException>> trustProviderInitializationFailedExceptionWJErrorMapperProvider;
    private final Provider<WJErrorMapper<WiFiSyncAuthTokenFailedValidationException>> wiFiSyncAuthTokenFailedValidationWJErrorMapperProvider;
    private final Provider<WJErrorMapper<WiFiSyncAuthTokenNotFoundException>> wiFiSyncAuthTokenNotFoundWJErrorMapperProvider;
    private final Provider<WJErrorMapper<WorkflowFailureException>> workflowFailureExceptionWJErrorMapperProvider;

    public ErrorCodeMapperModule_ProvidesErrorMapperFactory(ErrorCodeMapperModule errorCodeMapperModule, Provider<WJErrorMapper<ProvisionerCommandError>> provider, Provider<WJErrorMapper<DSSClientError>> provider2, Provider<WJErrorMapper<ProvisioningFailureException>> provider3, Provider<WJErrorMapper<PreconditionFailureException>> provider4, Provider<WJErrorMapper<ConnectionFailureException>> provider5, Provider<WJErrorMapper<TrustProviderInitializationFailedException>> provider6, Provider<WJErrorMapper<ProvisioningNotGranted>> provider7, Provider<WJErrorMapper<WiFiSyncAuthTokenFailedValidationException>> provider8, Provider<WJErrorMapper<WiFiSyncAuthTokenNotFoundException>> provider9, Provider<WJErrorMapper<WorkflowFailureException>> provider10) {
        this.module = errorCodeMapperModule;
        this.provisionerCommandErrorWJErrorMapperProvider = provider;
        this.dssClientErrorWJErrorMapperProvider = provider2;
        this.provisioningFailureExceptionWJErrorMapperProvider = provider3;
        this.preconditionFailureExceptionWJErrorMapperProvider = provider4;
        this.connectionFailureExceptionWJErrorMapperProvider = provider5;
        this.trustProviderInitializationFailedExceptionWJErrorMapperProvider = provider6;
        this.provisioningNotGrantedWJErrorMapperProvider = provider7;
        this.wiFiSyncAuthTokenFailedValidationWJErrorMapperProvider = provider8;
        this.wiFiSyncAuthTokenNotFoundWJErrorMapperProvider = provider9;
        this.workflowFailureExceptionWJErrorMapperProvider = provider10;
    }

    public static ErrorCodeMapperModule_ProvidesErrorMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule, Provider<WJErrorMapper<ProvisionerCommandError>> provider, Provider<WJErrorMapper<DSSClientError>> provider2, Provider<WJErrorMapper<ProvisioningFailureException>> provider3, Provider<WJErrorMapper<PreconditionFailureException>> provider4, Provider<WJErrorMapper<ConnectionFailureException>> provider5, Provider<WJErrorMapper<TrustProviderInitializationFailedException>> provider6, Provider<WJErrorMapper<ProvisioningNotGranted>> provider7, Provider<WJErrorMapper<WiFiSyncAuthTokenFailedValidationException>> provider8, Provider<WJErrorMapper<WiFiSyncAuthTokenNotFoundException>> provider9, Provider<WJErrorMapper<WorkflowFailureException>> provider10) {
        return new ErrorCodeMapperModule_ProvidesErrorMapperFactory(errorCodeMapperModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static WJErrorMapper<Throwable> provideInstance(ErrorCodeMapperModule errorCodeMapperModule, Provider<WJErrorMapper<ProvisionerCommandError>> provider, Provider<WJErrorMapper<DSSClientError>> provider2, Provider<WJErrorMapper<ProvisioningFailureException>> provider3, Provider<WJErrorMapper<PreconditionFailureException>> provider4, Provider<WJErrorMapper<ConnectionFailureException>> provider5, Provider<WJErrorMapper<TrustProviderInitializationFailedException>> provider6, Provider<WJErrorMapper<ProvisioningNotGranted>> provider7, Provider<WJErrorMapper<WiFiSyncAuthTokenFailedValidationException>> provider8, Provider<WJErrorMapper<WiFiSyncAuthTokenNotFoundException>> provider9, Provider<WJErrorMapper<WorkflowFailureException>> provider10) {
        return proxyProvidesErrorMapper(errorCodeMapperModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get());
    }

    public static WJErrorMapper<Throwable> proxyProvidesErrorMapper(ErrorCodeMapperModule errorCodeMapperModule, WJErrorMapper<ProvisionerCommandError> wJErrorMapper, WJErrorMapper<DSSClientError> wJErrorMapper2, WJErrorMapper<ProvisioningFailureException> wJErrorMapper3, WJErrorMapper<PreconditionFailureException> wJErrorMapper4, WJErrorMapper<ConnectionFailureException> wJErrorMapper5, WJErrorMapper<TrustProviderInitializationFailedException> wJErrorMapper6, WJErrorMapper<ProvisioningNotGranted> wJErrorMapper7, WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> wJErrorMapper8, WJErrorMapper<WiFiSyncAuthTokenNotFoundException> wJErrorMapper9, WJErrorMapper<WorkflowFailureException> wJErrorMapper10) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesErrorMapper(wJErrorMapper, wJErrorMapper2, wJErrorMapper3, wJErrorMapper4, wJErrorMapper5, wJErrorMapper6, wJErrorMapper7, wJErrorMapper8, wJErrorMapper9, wJErrorMapper10), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<Throwable> mo10268get() {
        return provideInstance(this.module, this.provisionerCommandErrorWJErrorMapperProvider, this.dssClientErrorWJErrorMapperProvider, this.provisioningFailureExceptionWJErrorMapperProvider, this.preconditionFailureExceptionWJErrorMapperProvider, this.connectionFailureExceptionWJErrorMapperProvider, this.trustProviderInitializationFailedExceptionWJErrorMapperProvider, this.provisioningNotGrantedWJErrorMapperProvider, this.wiFiSyncAuthTokenFailedValidationWJErrorMapperProvider, this.wiFiSyncAuthTokenNotFoundWJErrorMapperProvider, this.workflowFailureExceptionWJErrorMapperProvider);
    }
}
