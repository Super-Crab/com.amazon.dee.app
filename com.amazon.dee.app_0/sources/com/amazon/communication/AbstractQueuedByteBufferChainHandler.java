package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public abstract class AbstractQueuedByteBufferChainHandler implements ByteBufferChainHandler {
    private static final long BUFFER_FULL_DELAY_START = 100;
    private static final long BUFFER_FULL_RETRY_TIMES = 25;
    private static final DPLogger log = new DPLogger("TComm.AbstractQueuedByteBufferChainHandler");
    private final BlockingQueue<SendMessageParameters> mMessageQueue;
    private final int mMessageQueueCapacity;
    protected final ProtocolSocket mProtocolSocket;
    private final WorkExecutor mWorkExecutor;
    private final AtomicBoolean mIsMessageSendingTaskQueued = new AtomicBoolean(false);
    protected final SendMessageCallable mSendMessageCallable = new SendMessageCallable();
    private List<SendMessageParameters> mRejectedChainMessageParameters = Collections.synchronizedList(new LinkedList());
    private volatile int mRetryTimes = 0;

    /* loaded from: classes12.dex */
    protected class SendMessageCallable implements Callable<Void> {
        private static final String SEND_MESSAGE_FAILED = "SendMessageFailed";
        private static final String TIME_CHAIN_HANDLED_IN_NOTIFICATION_SINK_ON_SUCCESS = "TimeChainHandledInNotificationSinkInSuccess";
        private static final String TIME_COPYING_REJECTED_MESSAGE_PARAMETERS = "TimeCopyingRejectedMessageParameters";
        private static final String TIME_IN_MESSAGE_QUEUE = "TimeInMessageQueue";
        private static final String TIME_IN_MESSAGE_QUEUE_ON_EXCEPTION = "TimeInMessageQueueOnException";
        private static final String TIME_OK_TO_RESUBMIT_REJECTED_CHAIN = "TimeOkToResubmitRejectedChain";
        private static final String TIME_RECORD_TIME_LAST_MESSAGE_SENT = "TimeRecordTimeLastMessageSent";
        private static final String TIME_REJECTING_BYTE_BUFFER_CHAIN = "TimeRejectingByteBufferChain";
        private SendMessageParameters mReadiedMessageParameters;

        protected SendMessageCallable() {
        }

        private void clearMessages(String str) {
            AbstractQueuedByteBufferChainHandler.log.warn("SendMessageCallable.clearMessages", "clearing message queue", "mProtocolSocket", AbstractQueuedByteBufferChainHandler.this.mProtocolSocket, "size", Integer.valueOf(AbstractQueuedByteBufferChainHandler.this.mMessageQueue.size()));
            long nanoTime = System.nanoTime();
            SendMessageParameters sendMessageParameters = this.mReadiedMessageParameters;
            if (sendMessageParameters != null) {
                sendMessageParameters.notificationSink.chainRejected(sendMessageParameters.byteBufferChain, true);
                AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_REJECTING_BYTE_BUFFER_CHAIN, System.nanoTime() - nanoTime);
                this.mReadiedMessageParameters = null;
            }
            while (true) {
                SendMessageParameters sendMessageParameters2 = (SendMessageParameters) AbstractQueuedByteBufferChainHandler.this.mMessageQueue.poll();
                this.mReadiedMessageParameters = sendMessageParameters2;
                if (sendMessageParameters2 != null) {
                    long nanoTime2 = System.nanoTime();
                    SendMessageParameters sendMessageParameters3 = this.mReadiedMessageParameters;
                    sendMessageParameters3.notificationSink.chainRejected(sendMessageParameters3.byteBufferChain, true);
                    AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_REJECTING_BYTE_BUFFER_CHAIN, System.nanoTime() - nanoTime2);
                    AbstractQueuedByteBufferChainHandler.this.recordTime(GeneratedOutlineSupport1.outline72("TimeInMessageQueueOnException.Reason.", str), nanoTime2 - this.mReadiedMessageParameters.enqueueTimeNanos);
                } else {
                    return;
                }
            }
        }

        private long computeRetryTime() {
            return (AbstractQueuedByteBufferChainHandler.this.mRetryTimes + 1) * AbstractQueuedByteBufferChainHandler.BUFFER_FULL_DELAY_START;
        }

        private void engageWorkExecutor() {
            if (AbstractQueuedByteBufferChainHandler.this.mMessageQueue.peek() == null || !AbstractQueuedByteBufferChainHandler.this.mIsMessageSendingTaskQueued.compareAndSet(false, true)) {
                return;
            }
            AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.engageWorkExecutor", "message queue not empty, scheduling task", "mMessageQueue.size", Integer.valueOf(AbstractQueuedByteBufferChainHandler.this.mMessageQueue.size()));
            AbstractQueuedByteBufferChainHandler.this.mWorkExecutor.enqueueWork(AbstractQueuedByteBufferChainHandler.this.mProtocolSocket, this);
        }

        private void notifyReadyToResubmitRejectedChains() {
            List<SendMessageParameters> list;
            long nanoTime = System.nanoTime();
            synchronized (AbstractQueuedByteBufferChainHandler.this) {
                list = AbstractQueuedByteBufferChainHandler.this.mRejectedChainMessageParameters;
                AbstractQueuedByteBufferChainHandler.this.mRejectedChainMessageParameters = Collections.synchronizedList(new LinkedList());
            }
            AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_COPYING_REJECTED_MESSAGE_PARAMETERS, System.nanoTime() - nanoTime);
            for (SendMessageParameters sendMessageParameters : list) {
                long nanoTime2 = System.nanoTime();
                sendMessageParameters.notificationSink.okToResubmitRejectedChain(sendMessageParameters.byteBufferChain);
                AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_OK_TO_RESUBMIT_REJECTED_CHAIN, System.nanoTime() - nanoTime2);
            }
            AbstractQueuedByteBufferChainHandler.this.recordTime("SendMessageCallable.notifyReadyToResubmitRejectedChains", System.nanoTime() - nanoTime);
        }

        private void onSendMessageFailure(Exception exc) {
            AbstractQueuedByteBufferChainHandler.log.error("SendMessageCallable.onSendMessageFailure", "exception thrown", "mProtocolSocket", AbstractQueuedByteBufferChainHandler.this.mProtocolSocket, exc);
            AbstractQueuedByteBufferChainHandler.this.recordFailure(SEND_MESSAGE_FAILED, exc);
        }

        private void onSendMessageSuccess() {
            AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.onSendMessageSuccess", "message sent successfully", new Object[0]);
            long nanoTime = System.nanoTime();
            SendMessageParameters sendMessageParameters = this.mReadiedMessageParameters;
            sendMessageParameters.notificationSink.chainHandled(sendMessageParameters.byteBufferChain);
            AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_CHAIN_HANDLED_IN_NOTIFICATION_SINK_ON_SUCCESS, System.nanoTime() - nanoTime);
            notifyReadyToResubmitRejectedChains();
            this.mReadiedMessageParameters = null;
            long nanoTime2 = System.nanoTime();
            AbstractQueuedByteBufferChainHandler.this.mProtocolSocket.getProtocolSocketStats().recordTimeLastMessageSent();
            AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_RECORD_TIME_LAST_MESSAGE_SENT, System.nanoTime() - nanoTime2);
            engageWorkExecutor();
            AbstractQueuedByteBufferChainHandler.this.recordTime("SendMessageCallable.onSendMessageSuccess", System.nanoTime() - nanoTime);
        }

        private boolean readyMessageForDelivery() {
            if (this.mReadiedMessageParameters == null) {
                this.mReadiedMessageParameters = (SendMessageParameters) AbstractQueuedByteBufferChainHandler.this.mMessageQueue.poll();
                long nanoTime = System.nanoTime();
                if (this.mReadiedMessageParameters == null) {
                    AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.readyMessageForDelivery", "no messages to send", new Object[0]);
                    return false;
                }
                AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.readyMessageForDelivery", "sending new message", new Object[0]);
                AbstractQueuedByteBufferChainHandler.this.recordTime(TIME_IN_MESSAGE_QUEUE, nanoTime - this.mReadiedMessageParameters.enqueueTimeNanos);
                return true;
            }
            AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.readyMessageForDelivery", "continuing to send same message", new Object[0]);
            return true;
        }

        private void resendCurrentMessageAfterDelay() throws ByteBufferChainConsumptionException {
            long computeRetryTime = computeRetryTime();
            AbstractQueuedByteBufferChainHandler.log.info("SendMessageCallable.resendCurrentMessageAfterDelay", "WebSocketClient not accepting any bytes, will re-enqueue with delay", "mRetryTimes", Integer.valueOf(AbstractQueuedByteBufferChainHandler.this.mRetryTimes), "retryTime", Long.valueOf(computeRetryTime));
            if (AbstractQueuedByteBufferChainHandler.this.mIsMessageSendingTaskQueued.compareAndSet(false, true)) {
                AbstractQueuedByteBufferChainHandler.access$308(AbstractQueuedByteBufferChainHandler.this);
                if (AbstractQueuedByteBufferChainHandler.this.mRetryTimes == AbstractQueuedByteBufferChainHandler.BUFFER_FULL_RETRY_TIMES) {
                    AbstractQueuedByteBufferChainHandler.log.warn("SendMessageCallable.resendCurrentMessageAfterDelay", "We have retried sending this too many times already, informing downstream component and giving up", "mRetryTimes", Integer.valueOf(AbstractQueuedByteBufferChainHandler.this.mRetryTimes), "BUFFER_FULL_RETRY_TIMES", Long.valueOf((long) AbstractQueuedByteBufferChainHandler.BUFFER_FULL_RETRY_TIMES));
                    AbstractQueuedByteBufferChainHandler.this.retriedTooManyTimes();
                    return;
                }
                AbstractQueuedByteBufferChainHandler.this.mWorkExecutor.enqueueWorkAfter(AbstractQueuedByteBufferChainHandler.this.mProtocolSocket, this, computeRetryTime);
            }
        }

        private boolean sendReadiedMessage() {
            long nanoTime = System.nanoTime();
            try {
                int dataSize = this.mReadiedMessageParameters.byteBufferChain.getDataSize();
                while (dataSize > 0) {
                    AbstractQueuedByteBufferChainHandler.log.debug("SendMessageCallable.sendReadiedMessage", "sending byte buffer chain", "bytesToSend", Integer.valueOf(dataSize));
                    int sendByteBufferChain = AbstractQueuedByteBufferChainHandler.this.sendByteBufferChain(this.mReadiedMessageParameters.byteBufferChain);
                    dataSize -= sendByteBufferChain;
                    if (sendByteBufferChain == 0) {
                        resendCurrentMessageAfterDelay();
                        return false;
                    }
                }
                AbstractQueuedByteBufferChainHandler.this.mRetryTimes = 0;
                return true;
            } catch (ByteBufferChainConsumptionException unused) {
                clearMessages("ByteBufferChainConsumptionException");
                return false;
            } finally {
                AbstractQueuedByteBufferChainHandler.this.recordTime("SendMessageCallable.sendReadiedMessage", System.nanoTime() - nanoTime);
            }
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.call", "starting sendMessageCallable.", "mProtocolSocket", AbstractQueuedByteBufferChainHandler.this.mProtocolSocket);
            AbstractQueuedByteBufferChainHandler.this.mIsMessageSendingTaskQueued.set(false);
            if (AbstractQueuedByteBufferChainHandler.this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTING || AbstractQueuedByteBufferChainHandler.this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
                AbstractQueuedByteBufferChainHandler.log.verbose("SendMessageCallable.call", "clearing messages", "mProtocolSocket.socketState", AbstractQueuedByteBufferChainHandler.this.mProtocolSocket.socketState());
                clearMessages("FoundDisconnectingOrDisconnected");
                return null;
            }
            try {
                if (!readyMessageForDelivery() || !sendReadiedMessage()) {
                    return null;
                }
                onSendMessageSuccess();
                return null;
            } catch (Exception e) {
                onSendMessageFailure(e);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UnmodeledException.");
                outline107.append(e.getClass().getSimpleName());
                clearMessages(outline107.toString());
                return null;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class SendMessageParameters {
        public ByteBufferChain byteBufferChain;
        public long enqueueTimeNanos;
        public ByteBufferChainHandlerNotificationSink notificationSink;
    }

    public AbstractQueuedByteBufferChainHandler(WorkExecutor workExecutor, ProtocolSocket protocolSocket, int i) {
        this.mWorkExecutor = workExecutor;
        this.mProtocolSocket = protocolSocket;
        this.mMessageQueueCapacity = i;
        this.mMessageQueue = new ArrayBlockingQueue(this.mMessageQueueCapacity);
    }

    static /* synthetic */ int access$308(AbstractQueuedByteBufferChainHandler abstractQueuedByteBufferChainHandler) {
        int i = abstractQueuedByteBufferChainHandler.mRetryTimes;
        abstractQueuedByteBufferChainHandler.mRetryTimes = i + 1;
        return i;
    }

    protected void logTooManyMessagesEnqueued(int i, int i2, ByteBufferChainHandlerNotificationSink byteBufferChainHandlerNotificationSink) {
        log.error("onByteBufferChain", "Too many messages enqueued", "mMessageQueue.size", Integer.valueOf(i), "mMessageQueueCapacity", Integer.valueOf(i2));
    }

    @Override // com.amazon.communication.ByteBufferChainHandler
    public synchronized void onByteBufferChain(ByteBufferChain byteBufferChain, ByteBufferChainHandlerNotificationSink byteBufferChainHandlerNotificationSink) throws IOException {
        SendMessageParameters sendMessageParameters = new SendMessageParameters();
        sendMessageParameters.byteBufferChain = byteBufferChain;
        sendMessageParameters.notificationSink = byteBufferChainHandlerNotificationSink;
        sendMessageParameters.enqueueTimeNanos = System.nanoTime();
        if (this.mMessageQueue.offer(sendMessageParameters)) {
            log.verbose("onByteBufferChain", "message accepted and placed on the queue.", "mMessageQueue.size", Integer.valueOf(this.mMessageQueue.size()));
            if (this.mIsMessageSendingTaskQueued.compareAndSet(false, true)) {
                log.verbose("onByteBufferChain", "enqueue message sending task.", "mMessageQueue.size", Integer.valueOf(this.mMessageQueue.size()));
                this.mWorkExecutor.enqueueWork(this.mProtocolSocket, this.mSendMessageCallable);
            }
        } else {
            logTooManyMessagesEnqueued(this.mMessageQueue.size(), this.mMessageQueueCapacity, byteBufferChainHandlerNotificationSink);
            this.mRejectedChainMessageParameters.add(sendMessageParameters);
            byteBufferChainHandlerNotificationSink.chainRejected(byteBufferChain, false);
        }
    }

    protected void recordFailure(String str, Exception exc) {
    }

    protected void recordTime(String str, long j) {
    }

    protected abstract void retriedTooManyTimes();

    protected abstract int sendByteBufferChain(ByteBufferChain byteBufferChain) throws ByteBufferChainConsumptionException;
}
