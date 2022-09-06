package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public abstract class BufferedMessageManagerBase {
    public static final long DEFAULT_MESSAGE_TRACKING_TIMEOUT_IN_MILLIS = 1800000;
    public static final int MAX_MESSAGE_BUFFER_SIZE = 10485760;
    protected static final int NO_CHANNEL_SPECIFIED = -1;
    private static final DPLogger log = new DPLogger("TComm.BufferedMessageManagerBase");
    private static AtomicInteger sDeadMessageRemoverNumber = new AtomicInteger(0);
    private final PeriodicTimeoutInstanceRemover<MessageIdentityKey> mDeadMessageRemover;
    protected Map<MessageIdentityKey, ByteBufferChainMessageEntry> mMessageFragmentMap;
    private final InstanceTracker<MessageIdentityKey> mMessageInstanceTracker;
    protected final long mMessageTrackingTimeoutInMillis;
    private final Map<MessageIdentityKey, Long> mThrownAwayMessages;

    /* loaded from: classes12.dex */
    public static class ByteBufferChainMessageEntry extends MessageEntry {
        protected int mFragmentCount;
        protected long mLastFragmentArrivalTimeInMillis;

        public ByteBufferChainMessageEntry(ByteBufferChainMessageImpl byteBufferChainMessageImpl, int i, int i2) {
            super(byteBufferChainMessageImpl, i, i2);
            this.mFragmentCount = 1;
            this.mLastFragmentArrivalTimeInMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
        }

        public void appendFragment(Message message) {
            ((ByteBufferChainMessageImpl) this.mMessage).appendPayload(message);
            this.mFragmentCount++;
            this.mLastFragmentArrivalTimeInMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
        }

        public long getLastFragmentArrivalTimeInMillis() {
            return this.mLastFragmentArrivalTimeInMillis;
        }

        @Override // com.amazon.communication.BufferedMessageManagerBase.MessageEntry
        public String toString() {
            Object[] objArr = new Object[12];
            objArr[0] = "message";
            objArr[1] = this.mMessage;
            objArr[2] = "messageId";
            objArr[3] = Integer.valueOf(this.mMessageId);
            objArr[4] = "message size";
            objArr[5] = Integer.valueOf(this.mMessage.getPayloadSize());
            objArr[6] = "channel";
            int i = this.mChannel;
            objArr[7] = i == -1 ? "NO_CHANNEL_SPECIFIED" : Integer.valueOf(i);
            objArr[8] = "fragmentCount";
            objArr[9] = Integer.valueOf(this.mFragmentCount);
            objArr[10] = "lastFragmentArrivalTimeMillis";
            objArr[11] = Long.valueOf(this.mLastFragmentArrivalTimeInMillis);
            return DPFormattedMessage.toDPFormat("MessageEntry", "message details", objArr);
        }
    }

    /* loaded from: classes12.dex */
    public static class MessageEntry {
        protected final int mChannel;
        protected final Message mMessage;
        protected final int mMessageId;

        public MessageEntry(Message message, int i, int i2) {
            this.mMessage = message;
            this.mMessageId = i;
            this.mChannel = i2;
        }

        public int getChannel() {
            return this.mChannel;
        }

        public Message getMessage() {
            return this.mMessage;
        }

        public int getMessageId() {
            return this.mMessageId;
        }

        public String toString() {
            Object[] objArr = new Object[8];
            objArr[0] = "message";
            objArr[1] = this.mMessage;
            objArr[2] = "messageId";
            objArr[3] = Integer.valueOf(getMessageId());
            objArr[4] = "message size";
            objArr[5] = Integer.valueOf(this.mMessage.getPayloadSize());
            objArr[6] = "channel";
            int i = this.mChannel;
            objArr[7] = i == -1 ? "NO_CHANNEL_SPECIFIED" : Integer.valueOf(i);
            return DPFormattedMessage.toDPFormat("MessageEntry", "message details", objArr);
        }
    }

    /* loaded from: classes12.dex */
    public interface MessageIdentityKey {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static class MessageIdentityWithMessageId extends MessageIdentity implements MessageIdentityKey {
        public MessageIdentityWithMessageId(EndpointIdentity endpointIdentity, int i) {
            super(endpointIdentity, i);
        }
    }

    /* loaded from: classes12.dex */
    protected class MessageInstanceTracker implements InstanceTracker<MessageIdentityKey> {
        protected MessageInstanceTracker() {
        }

        @Override // com.amazon.communication.InstanceTracker
        public Set<MessageIdentityKey> getTrackedInstances() {
            HashSet hashSet = new HashSet(BufferedMessageManagerBase.this.mMessageFragmentMap.keySet());
            hashSet.addAll(BufferedMessageManagerBase.this.mThrownAwayMessages.keySet());
            return hashSet;
        }

        @Override // com.amazon.communication.InstanceTracker
        public boolean isTimedOut(MessageIdentityKey messageIdentityKey) {
            long longValue;
            ByteBufferChainMessageEntry byteBufferChainMessageEntry = BufferedMessageManagerBase.this.mMessageFragmentMap.get(messageIdentityKey);
            if (byteBufferChainMessageEntry == null) {
                Long l = (Long) BufferedMessageManagerBase.this.mThrownAwayMessages.get(messageIdentityKey);
                longValue = l != null ? l.longValue() : 0L;
            } else {
                longValue = byteBufferChainMessageEntry.getLastFragmentArrivalTimeInMillis();
            }
            return BufferedMessageManagerBase.this.mMessageTrackingTimeoutInMillis < GlobalTimeSource.INSTANCE.currentTimeMillis() - longValue;
        }

        @Override // com.amazon.communication.InstanceTracker
        public boolean stopTrackingInstance(MessageIdentityKey messageIdentityKey) {
            if (BufferedMessageManagerBase.this.mMessageFragmentMap.remove(messageIdentityKey) != null) {
                BufferedMessageManagerBase.log.debug("stopTrackingInstance", "stop tracking buffered message", "instance", messageIdentityKey);
            } else if (BufferedMessageManagerBase.this.mThrownAwayMessages.remove(messageIdentityKey) != null) {
                BufferedMessageManagerBase.log.debug("stopTrackingInstance", "stop tracking thrown away message", "instance", messageIdentityKey);
            } else {
                BufferedMessageManagerBase.log.warn("stopTrackingInstance", "message already processed", "instance", messageIdentityKey);
                return false;
            }
            return true;
        }
    }

    public BufferedMessageManagerBase() {
        this(1800000L);
    }

    protected PeriodicTimeoutInstanceRemover<MessageIdentityKey> createDeadMessageRemover(InstanceTracker<MessageIdentityKey> instanceTracker, long j) {
        return new PeriodicTimeoutInstanceRemover<>(instanceTracker, j);
    }

    public MessageIdentityKey createMessageIdentityKey(EndpointIdentity endpointIdentity, int i) {
        return new MessageIdentityWithMessageId(endpointIdentity, i);
    }

    public Map<MessageIdentityKey, ByteBufferChainMessageEntry> getMessageFragmentMap() {
        return this.mMessageFragmentMap;
    }

    protected abstract void handleCompletedMessage(EndpointIdentity endpointIdentity, MessageEntry messageEntry);

    public void handleMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        handleMessageFragment(endpointIdentity, i, message, z, -1);
    }

    public void setMessageFragmentMap(Map<MessageIdentityKey, ByteBufferChainMessageEntry> map) {
        this.mMessageFragmentMap = map;
    }

    public void verifyOnMessageFragmentParams(EndpointIdentity endpointIdentity, Message message) {
        if (endpointIdentity != null) {
            if (message == null) {
                throw new IllegalArgumentException("Message cannot be null");
            }
            return;
        }
        throw new IllegalArgumentException("EndpointIdentity cannot be null");
    }

    public BufferedMessageManagerBase(long j) {
        this.mThrownAwayMessages = new ConcurrentHashMap();
        this.mMessageFragmentMap = new ConcurrentHashMap();
        this.mMessageTrackingTimeoutInMillis = j;
        this.mMessageInstanceTracker = new MessageInstanceTracker();
        this.mDeadMessageRemover = createDeadMessageRemover(this.mMessageInstanceTracker, this.mMessageTrackingTimeoutInMillis);
    }

    public void handleMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z, int i2) {
        int payloadSize;
        verifyOnMessageFragmentParams(endpointIdentity, message);
        log.debug("handleMessageFragment", "beginning execution", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, endpointIdentity, "messageId", Integer.valueOf(i), "message", message, "moreToCome", Boolean.valueOf(z));
        this.mDeadMessageRemover.cleanIfTimesUp();
        MessageIdentityKey createMessageIdentityKey = createMessageIdentityKey(endpointIdentity, i);
        ByteBufferChainMessageEntry byteBufferChainMessageEntry = this.mMessageFragmentMap.get(createMessageIdentityKey);
        if (this.mThrownAwayMessages.containsKey(createMessageIdentityKey)) {
            if (!z) {
                log.warn("handleMessageFragment", "last fragment of a thrown away message.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i));
                this.mThrownAwayMessages.remove(createMessageIdentityKey);
                return;
            }
            log.debug("handleMessageFragment", "non-last fragment of a thrown away message.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i));
            this.mThrownAwayMessages.put(createMessageIdentityKey, Long.valueOf(GlobalTimeSource.INSTANCE.currentTimeMillis()));
        } else if (!z) {
            if (byteBufferChainMessageEntry != null) {
                log.info("handleMessageFragment", "last fragment of a buffered message.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "receivedMessageFragments", byteBufferChainMessageEntry);
                this.mMessageFragmentMap.remove(createMessageIdentityKey);
                byteBufferChainMessageEntry.appendFragment(message);
                handleCompletedMessage(endpointIdentity, byteBufferChainMessageEntry);
                return;
            }
            log.warn("handleMessageFragment", "last and first fragment of a message, possibly bug", new Object[0]);
            handleCompletedMessage(endpointIdentity, new MessageEntry(message, i, i2));
        } else {
            FailFast.expectTrue(message.getPayloadSize() > 0);
            if (byteBufferChainMessageEntry == null) {
                payloadSize = message.getPayloadSize();
            } else {
                payloadSize = message.getPayloadSize() + byteBufferChainMessageEntry.getMessage().getPayloadSize();
            }
            if (payloadSize > 10485760) {
                log.error("handleMessageFragment", "can't buffer a too large message, throw it away.", "receivedMessageFragments", byteBufferChainMessageEntry);
                if (byteBufferChainMessageEntry != null) {
                    this.mMessageFragmentMap.remove(createMessageIdentityKey);
                }
                this.mThrownAwayMessages.put(createMessageIdentityKey, Long.valueOf(GlobalTimeSource.INSTANCE.currentTimeMillis()));
            } else if (byteBufferChainMessageEntry == null) {
                log.info("handleMessageFragment", "new fragmented message.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "moreToCome", Boolean.valueOf(z));
                this.mMessageFragmentMap.put(createMessageIdentityKey, new ByteBufferChainMessageEntry(new ByteBufferChainMessageImpl(message), i, i2));
                log.info("handleMessageFragment", "added message entry to message fragment map", "mMessageFragmentMap.size", Integer.valueOf(this.mMessageFragmentMap.size()));
            } else {
                log.info("handleMessageFragment", "append to buffered fragments.", "receivedMessageFragments", byteBufferChainMessageEntry);
                byteBufferChainMessageEntry.appendFragment(message);
            }
        }
    }
}
