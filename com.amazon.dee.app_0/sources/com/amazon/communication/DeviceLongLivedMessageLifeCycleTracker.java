package com.amazon.communication;

import android.os.Binder;
import com.amazon.communication.LongLivedMessageLifeCycleTracker;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class DeviceLongLivedMessageLifeCycleTracker extends LongLivedMessageLifeCycleTracker {
    private static final DPLogger log = new DPLogger("TComm.DeviceLongLivedMessageLifeCycleTracker");
    private BandwidthToolByteAccountant mByteAccountant;
    private final int mCallerUid;
    private final AtomicBoolean mIsResendingRejectedChain;

    /* loaded from: classes12.dex */
    private class DeviceMessageTransmittingNotificationSink extends LongLivedMessageLifeCycleTracker.MessageTransmittingNotificationSink {
        private DeviceMessageTransmittingNotificationSink() {
            super();
        }

        @Override // com.amazon.communication.LongLivedMessageLifeCycleTracker.MessageTransmittingNotificationSink, com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainHandled(ByteBufferChain byteBufferChain) {
            super.chainHandled(byteBufferChain);
        }

        @Override // com.amazon.communication.LongLivedMessageLifeCycleTracker.MessageTransmittingNotificationSink, com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainRejected(ByteBufferChain byteBufferChain, boolean z) {
            super.chainRejected(byteBufferChain, z);
        }

        @Override // com.amazon.communication.LongLivedMessageLifeCycleTracker.MessageTransmittingNotificationSink, com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void okToResubmitRejectedChain(ByteBufferChain byteBufferChain) {
            DeviceLongLivedMessageLifeCycleTracker.this.mIsResendingRejectedChain.set(true);
            super.okToResubmitRejectedChain(byteBufferChain);
        }
    }

    public DeviceLongLivedMessageLifeCycleTracker(AlphaProtocolHandlerBase alphaProtocolHandlerBase, ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, int i, InputStream inputStream, String str, int i2, int i3, BandwidthToolByteAccountant bandwidthToolByteAccountant) {
        super(alphaProtocolHandlerBase, byteBufferChainHandler, workExecutor, protocolSocket, i, inputStream, str, i2, i3);
        this.mCallerUid = Binder.getCallingUid();
        this.mIsResendingRejectedChain = new AtomicBoolean(false);
        this.mByteAccountant = bandwidthToolByteAccountant;
    }

    @Override // com.amazon.communication.LongLivedMessageLifeCycleTracker
    protected ByteBufferChainHandlerNotificationSink createNotificationSink() {
        return new DeviceMessageTransmittingNotificationSink();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.communication.LongLivedMessageLifeCycleTracker
    public synchronized boolean transmitMessageFragment(ByteBufferChain byteBufferChain) {
        if (!this.mIsResendingRejectedChain.get()) {
            this.mByteAccountant.accountBytesTransmitted(this.mCallerUid, byteBufferChain.getDataSize());
        } else {
            this.mIsResendingRejectedChain.set(false);
        }
        return super.transmitMessageFragment(byteBufferChain);
    }
}
