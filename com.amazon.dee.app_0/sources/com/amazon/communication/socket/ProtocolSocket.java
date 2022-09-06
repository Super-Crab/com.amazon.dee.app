package com.amazon.communication.socket;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ByteBufferBackedMessage;
import com.amazon.communication.TuningFailedException;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import java.io.IOException;
import java.net.SocketException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
/* loaded from: classes12.dex */
public interface ProtocolSocket {

    /* renamed from: com.amazon.communication.socket.ProtocolSocket$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState = new int[ProtocolSocketState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocketState.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocketState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocketState.DISCONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocketState.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocketState.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum EnvironmentProperty {
        NETWORK_TYPE("x-dp-networkType", ".NetworkType."),
        ACCESS_POINT_OUI("x-dp-oui", ".AccessPointOUI."),
        OBFUSCATED_BSSID("x-dp-obfuscatedBssid", ".ObfuscatedBssid."),
        CARRIER_SIM("x-dp-simMccMnc", ".CarrierSim."),
        CARRIER_TOWER("x-dp-towerMccMnc", ".CarrierTower."),
        TCOMM_VERSION_CODE("x-dp-tcomm-versionCode", ".TCommVersionCode."),
        TCOMM_VERSION_NAME("x-dp-tcomm-versionName", ".TCommVersionName."),
        DEVICE_VERSION("x-dp-deviceVersion", ".DeviceVersion.");
        
        private String mHeaderName;
        private String mMetricsInfix;

        EnvironmentProperty(String str, String str2) {
            this.mHeaderName = str;
            this.mMetricsInfix = str2;
        }

        public String getHeaderName() {
            return this.mHeaderName;
        }

        public String getMetricsInfix() {
            return this.mMetricsInfix;
        }
    }

    /* loaded from: classes12.dex */
    public enum ProtocolSocketAttribute {
        SECURE,
        COMPRESSED,
        REQUEST_RESPONSE_ONLY,
        DEDICATED,
        REUSABLE,
        ANONYMOUS;
        
        public static final Set<ProtocolSocketAttribute> EMPTY_ATTRIBUTES = Collections.unmodifiableSet(EnumSet.noneOf(ProtocolSocketAttribute.class));
    }

    /* loaded from: classes12.dex */
    public enum ProtocolSocketState {
        UNKNOWN,
        CONNECTING,
        CONNECTED,
        DISCONNECTING,
        DISCONNECTED,
        ERROR;

        public int toConnectionState() {
            int ordinal = ordinal();
            int i = 1;
            if (ordinal != 1) {
                i = 2;
                if (ordinal != 2) {
                    i = 3;
                    if (ordinal != 3) {
                        i = 4;
                        if (ordinal != 4 && ordinal != 5) {
                            return 0;
                        }
                    }
                }
            }
            return i;
        }
    }

    /* loaded from: classes12.dex */
    public interface ProtocolSocketStateListener {
        void notifyStateChanged(ProtocolSocket protocolSocket);
    }

    /* loaded from: classes12.dex */
    public interface ProtocolSocketTransactionListener {
        void onLargeMessageTransactionBegin();

        void onLargeMessageTransactionComplete();
    }

    void addStateListener(ProtocolSocketStateListener protocolSocketStateListener);

    void addTransactionListener(ProtocolSocketTransactionListener protocolSocketTransactionListener);

    @Deprecated
    void close();

    void close(CloseDetail closeDetail);

    CloseDetail closeDetail();

    CloseReason closeReason();

    void enableKeepAlive(int i, int i2, int i3) throws SocketException;

    ConnectReason getConnectReason();

    EndpointIdentity getEndpointIdentity();

    String getEnvironmentProperty(EnvironmentProperty environmentProperty);

    String getFqdn();

    ProtocolSocketStats getProtocolSocketStats();

    Purpose getPurpose();

    UniqueEndpointIdentifier getUniqueEndpointIdentifier();

    boolean isAttributeSupported(ProtocolSocketAttribute protocolSocketAttribute);

    boolean isConnectReasonSupported();

    boolean isLargeMessageTransactionInProgress();

    void largeMessageTransactionBeginning();

    void largeMessageTransactionEnding();

    int release();

    void removeStateListener(ProtocolSocketStateListener protocolSocketStateListener);

    void removeTransactionListener(ProtocolSocketTransactionListener protocolSocketTransactionListener);

    int retain();

    void sendMessage(Message message, String str, int i) throws IOException, MissingCredentialsException;

    void setEnvironmentProperty(EnvironmentProperty environmentProperty, String str);

    void setPurpose(Purpose purpose);

    ProtocolSocketState socketState();

    void verifyTuningResult(ByteBufferBackedMessage byteBufferBackedMessage) throws TuningFailedException;
}
