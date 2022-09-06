package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.UnableToEstablishConnectionException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.UnexpectedConnectionFailure;
import com.amazon.whisperjoin.provisionerSDK.radios.error.GattConnectionError;
/* loaded from: classes13.dex */
public class ConnectionFailureExceptionWJErrorMapper implements WJErrorMapper<ConnectionFailureException> {
    private final BLETransportOperationErrorDetailsProvider mBLETransportOperationErrorDetailsProvider;

    public ConnectionFailureExceptionWJErrorMapper(BLETransportOperationErrorDetailsProvider bLETransportOperationErrorDetailsProvider) {
        this.mBLETransportOperationErrorDetailsProvider = bLETransportOperationErrorDetailsProvider;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(ConnectionFailureException connectionFailureException) {
        int commonErrorDetails;
        if (connectionFailureException instanceof UnableToEstablishConnectionException) {
            Throwable failureCause = ((UnableToEstablishConnectionException) connectionFailureException).getFailureCause();
            if (failureCause instanceof BLETransportOperationError) {
                commonErrorDetails = this.mBLETransportOperationErrorDetailsProvider.getDetails((BLETransportOperationError) failureCause);
            } else if (failureCause instanceof GattConnectionError) {
                commonErrorDetails = ((GattConnectionError) failureCause).getGattStatusCode();
            } else {
                commonErrorDetails = CommonErrorDetailsProvider.getCommonErrorDetails(failureCause);
            }
            return WJErrorFactory.Connection.unableToEstablishConnection(commonErrorDetails);
        } else if (connectionFailureException instanceof UnexpectedConnectionFailure) {
            return WJErrorFactory.Connection.unexpectedConnectionDrop(((UnexpectedConnectionFailure) connectionFailureException).getGattStatus());
        } else {
            throw new IllegalStateException("Unexpected ConnectionFailureException", connectionFailureException);
        }
    }
}
