package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.PreconditionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningNotGranted;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenFailedValidationException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenNotFoundException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WorkflowFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.BLETransportOperationErrorDetailsProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.CentralWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ConnectionFailureExceptionWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.PreconditionFailureWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ProvisionerBLECommandErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ProvisioningFailureWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ProvisioningNotGrantedWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.TrustProviderInitializationExceptionWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WebServiceErrorCodeMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WiFiSyncAuthTokenFailedValidationWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WiFiSyncAuthTokenNotFoundWJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WorkflowFailureWJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ErrorCodeMapperModule {
    @Provides
    public BLETransportOperationErrorDetailsProvider providesBLETransportOperationErrorDetailsProvider() {
        return new BLETransportOperationErrorDetailsProvider();
    }

    @Provides
    public WJErrorMapper<ConnectionFailureException> providesConnectionFailureMapper(BLETransportOperationErrorDetailsProvider bLETransportOperationErrorDetailsProvider) {
        return new ConnectionFailureExceptionWJErrorMapper(bLETransportOperationErrorDetailsProvider);
    }

    @Provides
    public WJErrorMapper<DSSClientError> providesDSSClientErrorMapper() {
        return new WebServiceErrorCodeMapper();
    }

    @Provides
    public WJErrorMapper<Throwable> providesErrorMapper(WJErrorMapper<ProvisionerCommandError> wJErrorMapper, WJErrorMapper<DSSClientError> wJErrorMapper2, WJErrorMapper<ProvisioningFailureException> wJErrorMapper3, WJErrorMapper<PreconditionFailureException> wJErrorMapper4, WJErrorMapper<ConnectionFailureException> wJErrorMapper5, WJErrorMapper<TrustProviderInitializationFailedException> wJErrorMapper6, WJErrorMapper<ProvisioningNotGranted> wJErrorMapper7, WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> wJErrorMapper8, WJErrorMapper<WiFiSyncAuthTokenNotFoundException> wJErrorMapper9, WJErrorMapper<WorkflowFailureException> wJErrorMapper10) {
        return new CentralWJErrorMapper(wJErrorMapper, wJErrorMapper2, wJErrorMapper3, wJErrorMapper4, wJErrorMapper5, wJErrorMapper6, wJErrorMapper7, wJErrorMapper8, wJErrorMapper9, wJErrorMapper10);
    }

    @Provides
    public WJErrorMapper<PreconditionFailureException> providesPreconditionalFailureMapper() {
        return new PreconditionFailureWJErrorMapper();
    }

    @Provides
    public WJErrorMapper<ProvisionerCommandError> providesProvisionerCommandErrorMapper(BLETransportOperationErrorDetailsProvider bLETransportOperationErrorDetailsProvider) {
        return new ProvisionerBLECommandErrorMapper(bLETransportOperationErrorDetailsProvider);
    }

    @Provides
    public WJErrorMapper<ProvisioningNotGranted> providesProvisionignNotGrantedMapper() {
        return new ProvisioningNotGrantedWJErrorMapper();
    }

    @Provides
    public WJErrorMapper<ProvisioningFailureException> providesProvisioningFailureMapper() {
        return new ProvisioningFailureWJErrorMapper();
    }

    @Provides
    public WJErrorMapper<TrustProviderInitializationFailedException> providesTrustProviderInitializationErrorMapper(WJErrorMapper<ProvisionerCommandError> wJErrorMapper, WJErrorMapper<DSSClientError> wJErrorMapper2) {
        return new TrustProviderInitializationExceptionWJErrorMapper(wJErrorMapper, wJErrorMapper2);
    }

    @Provides
    public WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> providesWiFiSyncAuthTokenFailedValidationMapper() {
        return new WiFiSyncAuthTokenFailedValidationWJErrorMapper();
    }

    @Provides
    public WJErrorMapper<WiFiSyncAuthTokenNotFoundException> providesWiFiSyncAuthTokenNotFoundMapper() {
        return new WiFiSyncAuthTokenNotFoundWJErrorMapper();
    }

    @Provides
    public WJErrorMapper<WorkflowFailureException> providesWorkflowFailureExceptionErrorMapper() {
        return new WorkflowFailureWJErrorMapper();
    }
}
