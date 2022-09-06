package amazon.communication;

import amazon.communication.connection.ConnectionClosedDetails;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class ConnectionInterruptedException extends CommunicationBaseException {
    private ConnectionClosedDetails mConnectionTerminationDetails;

    @FireOsSdk
    public ConnectionInterruptedException(Exception exc) {
        super(exc);
    }

    @FireOsSdk
    public ConnectionClosedDetails getConnectionClosedDetails() {
        return this.mConnectionTerminationDetails;
    }

    @FireOsSdk
    public ConnectionInterruptedException(String str, Exception exc) {
        super(str, exc);
    }

    @FireOsSdk
    public ConnectionInterruptedException(String str) {
        super(str);
    }

    @FireOsSdk
    public ConnectionInterruptedException(ConnectionClosedDetails connectionClosedDetails) {
        this(connectionClosedDetails.toString());
        this.mConnectionTerminationDetails = connectionClosedDetails;
    }

    @FireOsSdk
    public ConnectionInterruptedException(ConnectionClosedDetails connectionClosedDetails, Exception exc) {
        this(connectionClosedDetails.toString(), exc);
        this.mConnectionTerminationDetails = connectionClosedDetails;
    }
}
