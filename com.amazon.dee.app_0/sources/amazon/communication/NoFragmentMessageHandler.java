package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFastException;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes.dex */
public class NoFragmentMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger("TComm.NoFragmentMessageHandler");
    private final MessageHandler mDecoratedMessageHandler;

    public NoFragmentMessageHandler(MessageHandler messageHandler) {
        this.mDecoratedMessageHandler = messageHandler;
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        this.mDecoratedMessageHandler.onMessage(endpointIdentity, message);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        log.error("onMessageFragment", "message fragment encountered.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "message", message, "moreToCome", Boolean.valueOf(z));
        throw new FailFastException();
    }
}
