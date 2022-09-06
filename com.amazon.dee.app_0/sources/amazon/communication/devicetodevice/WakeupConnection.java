package amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.Connection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface WakeupConnection extends Connection {
    @FireOsSdk
    @Deprecated
    void sendMessageWithWakeup(Message message, int i, MetricEvent metricEvent, String str) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;
}
