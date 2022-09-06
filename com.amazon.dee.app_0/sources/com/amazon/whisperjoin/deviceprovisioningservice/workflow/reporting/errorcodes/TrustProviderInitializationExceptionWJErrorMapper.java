package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
/* loaded from: classes13.dex */
public class TrustProviderInitializationExceptionWJErrorMapper implements WJErrorMapper<TrustProviderInitializationFailedException> {
    private final WJErrorMapper<DSSClientError> mDSSClientErrorWJErrorMapper;
    private final WJErrorMapper<ProvisionerCommandError> mProvisionerCommandErrorWJErrorMapper;

    public TrustProviderInitializationExceptionWJErrorMapper(WJErrorMapper<ProvisionerCommandError> wJErrorMapper, WJErrorMapper<DSSClientError> wJErrorMapper2) {
        this.mProvisionerCommandErrorWJErrorMapper = wJErrorMapper;
        this.mDSSClientErrorWJErrorMapper = wJErrorMapper2;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(TrustProviderInitializationFailedException trustProviderInitializationFailedException) {
        Throwable rootCause = WJErrorUtils.getRootCause(trustProviderInitializationFailedException.getCause());
        if (rootCause instanceof ProvisionerCommandError) {
            return this.mProvisionerCommandErrorWJErrorMapper.map((ProvisionerCommandError) rootCause);
        }
        if (rootCause instanceof DSSClientError) {
            return this.mDSSClientErrorWJErrorMapper.map((DSSClientError) rootCause);
        }
        return WJErrorFactory.Connection.unableToEstablishSecureChannel();
    }
}
