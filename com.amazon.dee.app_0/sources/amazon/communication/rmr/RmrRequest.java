package amazon.communication.rmr;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Deprecated
/* loaded from: classes.dex */
public interface RmrRequest {
    @FireOsSdk
    @Deprecated
    void cancelRequest();

    @FireOsSdk
    @Deprecated
    void cancelRequest(RmrResponseException rmrResponseException);

    @FireOsSdk
    @Deprecated
    void startRequest() throws ConnectionAcquisitionFailedException, MissingCredentialsException, TransmissionFailedException, IllegalConnectionStateException;
}
