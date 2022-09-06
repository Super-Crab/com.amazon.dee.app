package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class StreamingMessageHandler implements MessageHandler {
    public static final long DEFAULT_MESSAGE_TRACKING_TIMEOUT_IN_MILLIS = 120000;
    public static final long MESSAGE_TRACKING_PERIOD_IN_MILLIS = 1800000;
    private static final DPLogger log = new DPLogger("TComm.StreamingMessageHandler");
    private final PeriodicTimeoutInstanceRemover<MessageIdentity> mDeadMessageRemover;
    private final MessageHandler mDecoratedMessageHandler;
    private final long mMessageFragmentTrackingTimeoutInMs;
    private final Map<MessageIdentity, MessageFragmentInputStream> mMessageFragments;

    /* loaded from: classes12.dex */
    private class FragmentInputStreamInstanceTracker implements InstanceTracker<MessageIdentity> {
        private FragmentInputStreamInstanceTracker() {
        }

        @Override // com.amazon.communication.InstanceTracker
        public Set<MessageIdentity> getTrackedInstances() {
            return new HashSet(StreamingMessageHandler.this.mMessageFragments.keySet());
        }

        @Override // com.amazon.communication.InstanceTracker
        public boolean isTimedOut(MessageIdentity messageIdentity) {
            MessageFragmentInputStream messageFragmentInputStream = (MessageFragmentInputStream) StreamingMessageHandler.this.mMessageFragments.get(messageIdentity);
            if (messageFragmentInputStream != null) {
                return StreamingMessageHandler.this.mMessageFragmentTrackingTimeoutInMs < GlobalTimeSource.INSTANCE.currentTimeMillis() - messageFragmentInputStream.getLastFragmentArrivalTimeInMillis();
            }
            return false;
        }

        @Override // com.amazon.communication.InstanceTracker
        public boolean stopTrackingInstance(MessageIdentity messageIdentity) {
            MessageFragmentInputStream messageFragmentInputStream = (MessageFragmentInputStream) StreamingMessageHandler.this.mMessageFragments.remove(messageIdentity);
            if (messageFragmentInputStream == null) {
                StreamingMessageHandler.log.warn("stopTrackingInstance", "Message already processed", "message", messageIdentity);
                return false;
            }
            messageFragmentInputStream.setFailedToReceiveAllFragments(true);
            StreamingMessageHandler.log.debug("stopTrackingInstance", "Stop tracking message", "message", messageIdentity);
            return true;
        }
    }

    public StreamingMessageHandler(MessageHandler messageHandler) {
        this(messageHandler, 120000L);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        this.mDecoratedMessageHandler.onMessage(endpointIdentity, message);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        Message message2 = message;
        log.debug("onMessageFragment", "Message fragment received", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "message", message2, "moreToCome", Boolean.valueOf(z));
        this.mDeadMessageRemover.cleanIfTimesUp();
        MessageIdentity messageIdentity = new MessageIdentity(endpointIdentity, i);
        MessageFragmentInputStream messageFragmentInputStream = this.mMessageFragments.get(messageIdentity);
        if (messageFragmentInputStream == null) {
            if (z) {
                log.debug("onMessageFragment", "first fragment of a message", "messageId", Integer.valueOf(i));
                MessageFragmentInputStream messageFragmentInputStream2 = new MessageFragmentInputStream(message2);
                this.mMessageFragments.put(messageIdentity, messageFragmentInputStream2);
                message2 = MessageFactory.createMessage(messageFragmentInputStream2);
            } else {
                log.warn("onMessageFragment", "first and last fragment of a message", "Endpoint", EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "message", message2);
            }
            onMessage(endpointIdentity, message2);
            return;
        }
        log.debug("onMessageFragment", "not first fragment of a message", "messageId", Integer.valueOf(i));
        messageFragmentInputStream.newFragmentArrived(message2, z);
        if (z) {
            return;
        }
        this.mMessageFragments.remove(messageIdentity);
    }

    public StreamingMessageHandler(MessageHandler messageHandler, long j) {
        this.mDecoratedMessageHandler = messageHandler;
        this.mMessageFragmentTrackingTimeoutInMs = j;
        this.mMessageFragments = new HashMap();
        this.mDeadMessageRemover = new PeriodicTimeoutInstanceRemover<>(new FragmentInputStreamInstanceTracker(), 1800000L);
    }
}
