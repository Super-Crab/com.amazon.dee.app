package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.error.PreconditionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningNotGranted;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenFailedValidationException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenNotFoundException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WorkflowFailureException;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class CentralWJErrorMapper implements WJErrorMapper<Throwable> {
    private static final String TAG = "CentralWJErrorMapper";
    private final WJErrorMapper<ConnectionFailureException> mConnectionFailureExceptionWJErrorMapper;
    private final WJErrorMapper<DSSClientError> mDSSClientErrorWJErrorMapper;
    private final WJErrorMapper<PreconditionFailureException> mPreconditionFailureExceptionWJErrorMapper;
    private final WJErrorMapper<ProvisionerCommandError> mProvisionerCommandErrorWJErrorMapper;
    private final WJErrorMapper<ProvisioningFailureException> mProvisioningFailureExceptionWJErrorMapper;
    private final WJErrorMapper<ProvisioningNotGranted> mProvisioningNotGrantedWJErrorMapper;
    private final WJErrorMapper<TrustProviderInitializationFailedException> mTrustProviderInitializationFailedExceptionWJErrorMapper;
    private final WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> mWiFiSyncAuthTokenFailedValidationExceptionWJErrorMapper;
    private final WJErrorMapper<WiFiSyncAuthTokenNotFoundException> mWiFiSyncAuthTokenNotFoundExceptionWJErrorMapper;
    private final WJErrorMapper<WorkflowFailureException> mWorkflowFailureExceptionWJErrorMapper;

    public CentralWJErrorMapper(WJErrorMapper<ProvisionerCommandError> wJErrorMapper, WJErrorMapper<DSSClientError> wJErrorMapper2, WJErrorMapper<ProvisioningFailureException> wJErrorMapper3, WJErrorMapper<PreconditionFailureException> wJErrorMapper4, WJErrorMapper<ConnectionFailureException> wJErrorMapper5, WJErrorMapper<TrustProviderInitializationFailedException> wJErrorMapper6, WJErrorMapper<ProvisioningNotGranted> wJErrorMapper7, WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> wJErrorMapper8, WJErrorMapper<WiFiSyncAuthTokenNotFoundException> wJErrorMapper9, WJErrorMapper<WorkflowFailureException> wJErrorMapper10) {
        this.mProvisionerCommandErrorWJErrorMapper = wJErrorMapper;
        this.mDSSClientErrorWJErrorMapper = wJErrorMapper2;
        this.mProvisioningFailureExceptionWJErrorMapper = wJErrorMapper3;
        this.mPreconditionFailureExceptionWJErrorMapper = wJErrorMapper4;
        this.mConnectionFailureExceptionWJErrorMapper = wJErrorMapper5;
        this.mTrustProviderInitializationFailedExceptionWJErrorMapper = wJErrorMapper6;
        this.mProvisioningNotGrantedWJErrorMapper = wJErrorMapper7;
        this.mWiFiSyncAuthTokenFailedValidationExceptionWJErrorMapper = wJErrorMapper8;
        this.mWiFiSyncAuthTokenNotFoundExceptionWJErrorMapper = wJErrorMapper9;
        this.mWorkflowFailureExceptionWJErrorMapper = wJErrorMapper10;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Mapping throwable: ");
        outline107.append(th.toString());
        WJLog.d(str, outline107.toString());
        if (th instanceof PreconditionFailureException) {
            return this.mPreconditionFailureExceptionWJErrorMapper.map((PreconditionFailureException) th);
        }
        if (th instanceof ConnectionFailureException) {
            return this.mConnectionFailureExceptionWJErrorMapper.map((ConnectionFailureException) th);
        }
        if (th instanceof ProvisionerCommandError) {
            return this.mProvisionerCommandErrorWJErrorMapper.map((ProvisionerCommandError) th);
        }
        if (th instanceof ProvisioningFailureException) {
            return this.mProvisioningFailureExceptionWJErrorMapper.map((ProvisioningFailureException) th);
        }
        if (th instanceof DSSClientError) {
            return this.mDSSClientErrorWJErrorMapper.map((DSSClientError) th);
        }
        if (th instanceof TrustProviderInitializationFailedException) {
            return this.mTrustProviderInitializationFailedExceptionWJErrorMapper.map((TrustProviderInitializationFailedException) th);
        }
        if (th instanceof ProvisioningNotGranted) {
            return this.mProvisioningNotGrantedWJErrorMapper.map((ProvisioningNotGranted) th);
        }
        if (th instanceof WiFiSyncAuthTokenFailedValidationException) {
            return this.mWiFiSyncAuthTokenFailedValidationExceptionWJErrorMapper.map((WiFiSyncAuthTokenFailedValidationException) th);
        }
        if (th instanceof WiFiSyncAuthTokenNotFoundException) {
            return this.mWiFiSyncAuthTokenNotFoundExceptionWJErrorMapper.map((WiFiSyncAuthTokenNotFoundException) th);
        }
        if (th instanceof WorkflowFailureException) {
            return this.mWorkflowFailureExceptionWJErrorMapper.map((WorkflowFailureException) th);
        }
        return WJErrorFactory.Other.unknown(CommonErrorDetailsProvider.getCommonErrorDetails(th));
    }
}
