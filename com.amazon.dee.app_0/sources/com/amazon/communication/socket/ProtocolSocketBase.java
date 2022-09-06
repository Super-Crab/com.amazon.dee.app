package com.amazon.communication.socket;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.communication.ByteBufferBackedMessage;
import com.amazon.communication.TuningFailedException;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.net.SocketException;
import java.net.URI;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public abstract class ProtocolSocketBase implements ProtocolSocket {
    private static final DPLogger log = new DPLogger("TComm.ProtocolSocketBase");
    protected EndpointIdentity mIdentity;
    protected UniqueEndpointIdentifier mUniqueEndpointIdentifier;
    protected WorkExecutor mWorkExecutor;
    private final Set<ProtocolSocket.ProtocolSocketStateListener> mStateTransitionListenerSet = new CopyOnWriteArraySet();
    private final Set<ProtocolSocket.ProtocolSocketTransactionListener> mTransactionListenerSet = new CopyOnWriteArraySet();
    protected Purpose mPurpose = Purpose.REGULAR;
    protected ProtocolSocketStats mProtocolSocketStats = new ProtocolSocketStats();
    protected final Map<ProtocolSocket.EnvironmentProperty, String> mEnvironmentProperties = new ConcurrentHashMap();
    private final AtomicInteger mNumLargeMessageTransactionsInProgess = new AtomicInteger(0);
    private final Set<ProtocolSocket.ProtocolSocketAttribute> mSupportedAttributes = EnumSet.of(ProtocolSocket.ProtocolSocketAttribute.REUSABLE);
    protected final AtomicInteger mRefCount = new AtomicInteger(0);

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void addStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        log.verbose("addStateListener", "adding listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener);
        this.mStateTransitionListenerSet.add(protocolSocketStateListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean addSupportedAttributes(Set<ProtocolSocket.ProtocolSocketAttribute> set) {
        return this.mSupportedAttributes.addAll(set);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void addTransactionListener(ProtocolSocket.ProtocolSocketTransactionListener protocolSocketTransactionListener) {
        this.mTransactionListenerSet.add(protocolSocketTransactionListener);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    @Deprecated
    public void close() {
        log.info("close", "socket closed without details", "this", this, new RuntimeException("Stacktrace for no-details close"));
        close(new CloseDetail(1000, "Client-initiated close without detail"));
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void enableKeepAlive(int i, int i2, int i3) throws SocketException {
        throw new UnsupportedOperationException("No keep alive support");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String formatProtocolSocketString(String str, URI uri, String str2) {
        return formatProtocolSocketString(str, uri, str2, Purpose.REGULAR);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public ConnectReason getConnectReason() {
        throw new UnsupportedOperationException("This ProtocolSocket type does not support getConnectReason");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public EndpointIdentity getEndpointIdentity() {
        return this.mIdentity;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public String getEnvironmentProperty(ProtocolSocket.EnvironmentProperty environmentProperty) {
        return this.mEnvironmentProperties.get(environmentProperty);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public String getFqdn() {
        return "";
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public ProtocolSocketStats getProtocolSocketStats() {
        return this.mProtocolSocketStats;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public Purpose getPurpose() {
        return this.mPurpose;
    }

    public int getRefCount() {
        return this.mRefCount.get();
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public UniqueEndpointIdentifier getUniqueEndpointIdentifier() {
        return this.mUniqueEndpointIdentifier;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public boolean isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute protocolSocketAttribute) {
        return this.mSupportedAttributes.contains(protocolSocketAttribute);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public boolean isConnectReasonSupported() {
        return false;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public boolean isLargeMessageTransactionInProgress() {
        return this.mNumLargeMessageTransactionsInProgess.get() > 0;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void largeMessageTransactionBeginning() {
        this.mNumLargeMessageTransactionsInProgess.incrementAndGet();
        for (ProtocolSocket.ProtocolSocketTransactionListener protocolSocketTransactionListener : this.mTransactionListenerSet) {
            protocolSocketTransactionListener.onLargeMessageTransactionBegin();
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void largeMessageTransactionEnding() {
        this.mNumLargeMessageTransactionsInProgess.decrementAndGet();
        for (ProtocolSocket.ProtocolSocketTransactionListener protocolSocketTransactionListener : this.mTransactionListenerSet) {
            protocolSocketTransactionListener.onLargeMessageTransactionComplete();
        }
    }

    public void notifyStateChanged() {
        log.verbose("notifyStateChanged", "notifying listeners", new Object[0]);
        for (ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener : this.mStateTransitionListenerSet) {
            try {
                protocolSocketStateListener.notifyStateChanged(this);
            } catch (RuntimeException e) {
                log.warn("notifyStateChanged", "Exception in socket state change listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener, e);
            }
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public int release() {
        if (this.mRefCount.get() != 0) {
            return this.mRefCount.decrementAndGet();
        }
        throw new IllegalStateException("release() called, but reference count is already 0.");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void removeStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        log.verbose("removeStateListener", "removing listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener);
        this.mStateTransitionListenerSet.remove(protocolSocketStateListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean removeSupportedAttributes(Set<ProtocolSocket.ProtocolSocketAttribute> set) {
        return this.mSupportedAttributes.removeAll(set);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void removeTransactionListener(ProtocolSocket.ProtocolSocketTransactionListener protocolSocketTransactionListener) {
        this.mTransactionListenerSet.remove(protocolSocketTransactionListener);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public int retain() {
        return this.mRefCount.incrementAndGet();
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void setEnvironmentProperty(ProtocolSocket.EnvironmentProperty environmentProperty, String str) {
        this.mEnvironmentProperties.put(environmentProperty, str);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void setPurpose(Purpose purpose) {
        if (purpose != null) {
            this.mPurpose = purpose;
            return;
        }
        throw new IllegalArgumentException("Purpose cannot be null");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void verifyTuningResult(ByteBufferBackedMessage byteBufferBackedMessage) throws TuningFailedException {
        throw new UnsupportedOperationException("Implement this method if tuning is needed.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String formatProtocolSocketString(String str, URI uri, String str2, Purpose purpose) {
        return formatProtocolSocketString(str, uri.toString(), str2, purpose);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String formatProtocolSocketString(String str, EndpointIdentity endpointIdentity, String str2) {
        return formatProtocolSocketString(str, endpointIdentity, str2, Purpose.REGULAR);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String formatProtocolSocketString(String str, String str2, String str3) {
        return formatProtocolSocketString(str, str2, str3, Purpose.REGULAR);
    }

    protected String formatProtocolSocketString(String str, EndpointIdentity endpointIdentity, String str2, Purpose purpose) {
        return formatProtocolSocketString(str, endpointIdentity.toString(), str2, purpose);
    }

    private String formatProtocolSocketString(String str, String str2, String str3, Purpose purpose) {
        StringBuilder sb = new StringBuilder("Attributes: ");
        for (ProtocolSocket.ProtocolSocketAttribute protocolSocketAttribute : this.mSupportedAttributes) {
            sb.append(protocolSocketAttribute);
            sb.append(";");
        }
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN, str, RealTimeTextConstants.COLON_SPACE, str2, "}(");
        outline116.append(str3);
        outline116.append(") Purpose:");
        outline116.append(purpose);
        outline116.append("; ");
        outline116.append((Object) sb);
        return outline116.toString();
    }
}
