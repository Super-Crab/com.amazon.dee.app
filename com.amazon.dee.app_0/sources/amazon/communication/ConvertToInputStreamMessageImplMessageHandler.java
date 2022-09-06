package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.KnownSizeInputStreamMessage;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes.dex */
public class ConvertToInputStreamMessageImplMessageHandler implements MessageHandler {
    public static final int SIZE_LIMIT_BEFORE_USING_INPUT_STREAM = 102400;
    private static final DPLogger log = new DPLogger("TComm.ConvertToInputStreamMessageImplMessageHandler");
    private final MessageHandler mDecoratedMessageHandler;

    public ConvertToInputStreamMessageImplMessageHandler(MessageHandler messageHandler) {
        this.mDecoratedMessageHandler = messageHandler;
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        int payloadSize = message.getPayloadSize();
        if (payloadSize > 102400) {
            log.verbose("onMessage", "converting message to KnownSizeInputStreamMessage", "payloadSize", Integer.valueOf(payloadSize), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
            this.mDecoratedMessageHandler.onMessage(endpointIdentity, new KnownSizeInputStreamMessage(message.getPayload(), payloadSize));
            return;
        }
        log.verbose("onMessage", "not converting message to KnownSizeInputStreamMessage", "payloadSize", Integer.valueOf(payloadSize), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
        this.mDecoratedMessageHandler.onMessage(endpointIdentity, message);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("Fragments not expected here");
    }
}
