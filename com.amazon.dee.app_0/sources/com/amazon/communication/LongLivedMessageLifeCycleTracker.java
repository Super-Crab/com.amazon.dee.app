package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class LongLivedMessageLifeCycleTracker {
    private static final int DEFAULT_RETRY_READ_DATA_DELAY_IN_MS = 100;
    private static final DPLogger log = new DPLogger("TComm.LongLivedMessageLifeCycleTracker");
    private final AlphaProtocolHandlerBase mAlphaProtocolHandlerBase;
    private final ByteBufferChainHandler mByteBufferChainHandler;
    private final int mChannel;
    private final int mExpectedMessageFragmentSize;
    private final int mMessageId;
    private final InputStream mMessageStream;
    private final String mMessageType;
    private final ProtocolSocket mProtocolSocket;
    private final WorkExecutor mWorkExecutor;
    private int mNextSequenceNumber = 1;
    private final AtomicInteger mNumUnacknowledgedOnByteBufferChainCalls = new AtomicInteger(0);
    private final AtomicInteger mNumRejectedChains = new AtomicInteger(0);
    protected boolean mEndOfStream = false;
    private boolean mHandlerIsDead = false;
    private final ByteBufferChainHandlerNotificationSink mNotificationSink = createNotificationSink();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class MessageTransmittingNotificationSink implements ByteBufferChainHandlerNotificationSink {
        /* JADX INFO: Access modifiers changed from: protected */
        public MessageTransmittingNotificationSink() {
        }

        @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainHandled(ByteBufferChain byteBufferChain) {
            LongLivedMessageLifeCycleTracker.log.verbose("chainHandled", "chain handled on large message", new Object[0]);
            synchronized (LongLivedMessageLifeCycleTracker.this) {
                LongLivedMessageLifeCycleTracker.this.mNumUnacknowledgedOnByteBufferChainCalls.getAndDecrement();
                if (LongLivedMessageLifeCycleTracker.this.mHandlerIsDead) {
                    LongLivedMessageLifeCycleTracker.log.warn("chainHandled", "handler dead; directly return", new Object[0]);
                    return;
                }
                if (LongLivedMessageLifeCycleTracker.this.mEndOfStream && LongLivedMessageLifeCycleTracker.this.mNumUnacknowledgedOnByteBufferChainCalls.get() == 0 && LongLivedMessageLifeCycleTracker.this.mNumRejectedChains.get() == 0) {
                    LongLivedMessageLifeCycleTracker.log.verbose("chainHandled", "all fragments have been accepted", new Object[0]);
                    LongLivedMessageLifeCycleTracker.this.destroy(false);
                } else {
                    LongLivedMessageLifeCycleTracker.this.transmitMessage();
                }
            }
        }

        @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainRejected(ByteBufferChain byteBufferChain, boolean z) {
            LongLivedMessageLifeCycleTracker.log.warn("chainRejected", "chain rejected on large message", new Object[0]);
            synchronized (LongLivedMessageLifeCycleTracker.this) {
                LongLivedMessageLifeCycleTracker.this.mNumUnacknowledgedOnByteBufferChainCalls.getAndDecrement();
                LongLivedMessageLifeCycleTracker.this.mNumRejectedChains.incrementAndGet();
                if (z) {
                    LongLivedMessageLifeCycleTracker.log.info("chainRejected", "notified that ByteBufferChainHandler will never accept a ByteBufferChain; it cannot accept any more fragments of the current message; treating it as dead", new Object[0]);
                    LongLivedMessageLifeCycleTracker.this.mHandlerIsDead = true;
                    LongLivedMessageLifeCycleTracker.this.destroy(true);
                }
            }
        }

        @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void okToResubmitRejectedChain(ByteBufferChain byteBufferChain) {
            LongLivedMessageLifeCycleTracker.log.warn("okToResubmitRejectedChain", "notified to resubmit a rejected chain", new Object[0]);
            synchronized (LongLivedMessageLifeCycleTracker.this) {
                if (LongLivedMessageLifeCycleTracker.this.mHandlerIsDead) {
                    LongLivedMessageLifeCycleTracker.log.warn("okToResubmitRejectedChain", "not resubmitting a rejected chain, because handler is dead", new Object[0]);
                    return;
                }
                LongLivedMessageLifeCycleTracker.this.mNumRejectedChains.decrementAndGet();
                LongLivedMessageLifeCycleTracker.log.verbose("okToResubmitRejectedChain", "attempting to transmit previously rejected fragment", new Object[0]);
                LongLivedMessageLifeCycleTracker.this.transmitMessageFragment(byteBufferChain);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LongLivedMessageLifeCycleTracker(AlphaProtocolHandlerBase alphaProtocolHandlerBase, ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, int i, InputStream inputStream, String str, int i2, int i3) {
        this.mAlphaProtocolHandlerBase = alphaProtocolHandlerBase;
        this.mByteBufferChainHandler = byteBufferChainHandler;
        this.mWorkExecutor = workExecutor;
        this.mProtocolSocket = protocolSocket;
        this.mExpectedMessageFragmentSize = i;
        this.mMessageType = str;
        this.mChannel = i2;
        this.mMessageId = i3;
        this.mMessageStream = inputStream;
        this.mProtocolSocket.largeMessageTransactionBeginning();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroy(boolean z) {
        log.verbose("destroy", "removing tracker from map", "isDueToFailure", Boolean.valueOf(z));
        this.mProtocolSocket.largeMessageTransactionEnding();
        this.mAlphaProtocolHandlerBase.stopTrackingLongLivedMessage(this.mMessageId);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized boolean transmitNextMessageFragment() {
        /*
            Method dump skipped, instructions count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.LongLivedMessageLifeCycleTracker.transmitNextMessageFragment():boolean");
    }

    protected ByteBufferChainHandlerNotificationSink createNotificationSink() {
        return new MessageTransmittingNotificationSink();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void transmitMessage() {
        boolean z = false;
        int i = 0;
        while (true) {
            try {
                if (this.mNumUnacknowledgedOnByteBufferChainCalls.get() >= 5 || this.mNumRejectedChains.get() != 0 || this.mEndOfStream || this.mHandlerIsDead) {
                    break;
                }
                if (log.isVerboseEnabled()) {
                    log.verbose("transmitMessage", "attempting to transmit next message fragment", "mMessageStream.available", Integer.valueOf(this.mMessageStream.available()), "mNumUnacknowledgedOnByteBufferChainCalls", Integer.valueOf(this.mNumUnacknowledgedOnByteBufferChainCalls.get()));
                }
                if (!transmitNextMessageFragment()) {
                    z = true;
                    break;
                } else {
                    i++;
                    z = true;
                }
            } catch (IOException e) {
                log.error("transmitMessage", "encountered IOException when checking how many bytes are available in the stream", e);
                destroy(true);
                return;
            }
        }
        if (log.isVerboseEnabled()) {
            if (!z) {
                log.verbose("transmitMessage", "did not attempt to send any message fragments", "mNumUnacknowledgedOnByteBufferChainCalls", Integer.valueOf(this.mNumUnacknowledgedOnByteBufferChainCalls.get()), "mNextSequenceNumber", Integer.valueOf(this.mNextSequenceNumber), "mNumRejectedChains", Integer.valueOf(this.mNumRejectedChains.get()), "mEndOfStream", Boolean.valueOf(this.mEndOfStream), "mHandlerIsDead", Boolean.valueOf(this.mHandlerIsDead));
            } else {
                log.verbose("transmitMessage", "sent message fragments", "numMessageFragmentsSent", Integer.valueOf(i), "mNumUnacknowledgedOnByteBufferChainCalls", Integer.valueOf(this.mNumUnacknowledgedOnByteBufferChainCalls.get()), "mNextSequenceNumber", Integer.valueOf(this.mNextSequenceNumber), "mNumRejectedChains", Integer.valueOf(this.mNumRejectedChains.get()), "mEndOfStream", Boolean.valueOf(this.mEndOfStream), "mHandlerIsDead", Boolean.valueOf(this.mHandlerIsDead));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized boolean transmitMessageFragment(ByteBufferChain byteBufferChain) {
        try {
            this.mNumUnacknowledgedOnByteBufferChainCalls.getAndIncrement();
            this.mByteBufferChainHandler.onByteBufferChain(byteBufferChain, this.mNotificationSink);
        } catch (IOException e) {
            log.error("transmitMessageFragment", "encountered exception upon transmitting fragment", e);
            destroy(true);
            return false;
        }
        return true;
    }
}
