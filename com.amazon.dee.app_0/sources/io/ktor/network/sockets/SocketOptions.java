package io.ktor.network.sockets;

import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SocketOptions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 #2\u00020\u0001:\u0006\"#$%&'B\u001d\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0000H ¢\u0006\u0002\b\u001bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0000H\u0014J\r\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!R\"\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001f\u0010\u0010\u001a\u00020\u0011X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u0082\u0001\u0003(\u0018 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lio/ktor/network/sockets/SocketOptions;", "", "customOptions", "", "(Ljava/util/Map;)V", "getCustomOptions", "()Ljava/util/Map;", "reuseAddress", "", "getReuseAddress", "()Z", "setReuseAddress", "(Z)V", "reusePort", "getReusePort", "setReusePort", "typeOfService", "Lio/ktor/network/sockets/TypeOfService;", "getTypeOfService", "()B", "setTypeOfService-SNCuOGA", "(B)V", "B", "acceptor", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "acceptor$ktor_network", "copy", "copy$ktor_network", "copyCommon", "", ADMConstants.EXTRA_FROM, "peer", "Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "peer$ktor_network", "AcceptorOptions", "Companion", "GeneralSocketOptions", "PeerSocketOptions", "TCPClientSocketOptions", "UDPSocketOptions", "Lio/ktor/network/sockets/SocketOptions$GeneralSocketOptions;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class SocketOptions {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<Object, Object> customOptions;
    private boolean reuseAddress;
    private boolean reusePort;
    private byte typeOfService;

    /* compiled from: SocketOptions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lio/ktor/network/sockets/SocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "copy", "copy$ktor_network", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class AcceptorOptions extends SocketOptions {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AcceptorOptions(@NotNull Map<Object, Object> customOptions) {
            super(customOptions, null);
            Intrinsics.checkParameterIsNotNull(customOptions, "customOptions");
        }

        @Override // io.ktor.network.sockets.SocketOptions
        @NotNull
        /* renamed from: copy$ktor_network */
        public AcceptorOptions mo10312copy$ktor_network() {
            AcceptorOptions acceptorOptions = new AcceptorOptions(new HashMap(getCustomOptions()));
            acceptorOptions.copyCommon(this);
            return acceptorOptions;
        }
    }

    /* compiled from: SocketOptions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\tR\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$Companion;", "", "()V", "Empty", "Lio/ktor/network/sockets/SocketOptions;", "Empty$annotations", "getEmpty", "()Lio/ktor/network/sockets/SocketOptions;", "create", "create$ktor_network", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Not supported anymore", replaceWith = @ReplaceWith(expression = "TODO(\"Not supported anymore\")", imports = {}))
        public static /* synthetic */ void Empty$annotations() {
        }

        @NotNull
        public final SocketOptions create$ktor_network() {
            return new GeneralSocketOptions(new HashMap());
        }

        @NotNull
        public final SocketOptions getEmpty() {
            throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", "Not supported anymore"));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: SocketOptions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$GeneralSocketOptions;", "Lio/ktor/network/sockets/SocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "copy", "copy$ktor_network", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    private static final class GeneralSocketOptions extends SocketOptions {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GeneralSocketOptions(@NotNull Map<Object, Object> customOptions) {
            super(customOptions, null);
            Intrinsics.checkParameterIsNotNull(customOptions, "customOptions");
        }

        @Override // io.ktor.network.sockets.SocketOptions
        @NotNull
        /* renamed from: copy$ktor_network */
        public GeneralSocketOptions mo10312copy$ktor_network() {
            GeneralSocketOptions generalSocketOptions = new GeneralSocketOptions(new HashMap(getCustomOptions()));
            generalSocketOptions.copyCommon(this);
            return generalSocketOptions;
        }
    }

    /* compiled from: SocketOptions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u000f\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0001H\u0014J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u001a"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "Lio/ktor/network/sockets/SocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "receiveBufferSize", "", "getReceiveBufferSize", "()I", "setReceiveBufferSize", "(I)V", "sendBufferSize", "getSendBufferSize", "setSendBufferSize", "copy", "copy$ktor_network", "copyCommon", "", ADMConstants.EXTRA_FROM, "tcp", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "tcp$ktor_network", "udp", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "udp$ktor_network", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static class PeerSocketOptions extends SocketOptions {
        private int receiveBufferSize;
        private int sendBufferSize;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PeerSocketOptions(@NotNull Map<Object, Object> customOptions) {
            super(customOptions, null);
            Intrinsics.checkParameterIsNotNull(customOptions, "customOptions");
            this.sendBufferSize = -1;
            this.receiveBufferSize = -1;
        }

        @Override // io.ktor.network.sockets.SocketOptions
        protected void copyCommon(@NotNull SocketOptions from) {
            Intrinsics.checkParameterIsNotNull(from, "from");
            super.copyCommon(from);
            if (from instanceof PeerSocketOptions) {
                PeerSocketOptions peerSocketOptions = (PeerSocketOptions) from;
                this.sendBufferSize = peerSocketOptions.sendBufferSize;
                this.receiveBufferSize = peerSocketOptions.receiveBufferSize;
            }
        }

        public final int getReceiveBufferSize() {
            return this.receiveBufferSize;
        }

        public final int getSendBufferSize() {
            return this.sendBufferSize;
        }

        public final void setReceiveBufferSize(int i) {
            this.receiveBufferSize = i;
        }

        public final void setSendBufferSize(int i) {
            this.sendBufferSize = i;
        }

        @NotNull
        public final TCPClientSocketOptions tcp$ktor_network() {
            TCPClientSocketOptions tCPClientSocketOptions = new TCPClientSocketOptions(new HashMap(getCustomOptions()));
            copyCommon(this);
            return tCPClientSocketOptions;
        }

        @NotNull
        public final UDPSocketOptions udp$ktor_network() {
            UDPSocketOptions uDPSocketOptions = new UDPSocketOptions(new HashMap(getCustomOptions()));
            uDPSocketOptions.copyCommon(this);
            return uDPSocketOptions;
        }

        @Override // io.ktor.network.sockets.SocketOptions
        @NotNull
        /* renamed from: copy$ktor_network */
        public PeerSocketOptions mo10312copy$ktor_network() {
            PeerSocketOptions peerSocketOptions = new PeerSocketOptions(new HashMap(getCustomOptions()));
            peerSocketOptions.copyCommon(this);
            return peerSocketOptions;
        }
    }

    private SocketOptions(Map<Object, Object> map) {
        this.customOptions = map;
        this.typeOfService = TypeOfService.Companion.getUNDEFINED();
    }

    @NotNull
    public final AcceptorOptions acceptor$ktor_network() {
        AcceptorOptions acceptorOptions = new AcceptorOptions(new HashMap(this.customOptions));
        acceptorOptions.copyCommon(this);
        return acceptorOptions;
    }

    @NotNull
    /* renamed from: copy$ktor_network */
    public abstract SocketOptions mo10312copy$ktor_network();

    protected void copyCommon(@NotNull SocketOptions from) {
        Intrinsics.checkParameterIsNotNull(from, "from");
        this.typeOfService = from.typeOfService;
        this.reuseAddress = from.reuseAddress;
        this.reusePort = from.reusePort;
    }

    @NotNull
    protected final Map<Object, Object> getCustomOptions() {
        return this.customOptions;
    }

    public final boolean getReuseAddress() {
        return this.reuseAddress;
    }

    public final boolean getReusePort() {
        return this.reusePort;
    }

    public final byte getTypeOfService() {
        return this.typeOfService;
    }

    @NotNull
    public final PeerSocketOptions peer$ktor_network() {
        PeerSocketOptions peerSocketOptions = new PeerSocketOptions(new HashMap(this.customOptions));
        copyCommon(this);
        return peerSocketOptions;
    }

    public final void setReuseAddress(boolean z) {
        this.reuseAddress = z;
    }

    public final void setReusePort(boolean z) {
        this.reusePort = z;
    }

    /* renamed from: setTypeOfService-SNCuOGA  reason: not valid java name */
    public final void m10307setTypeOfServiceSNCuOGA(byte b) {
        this.typeOfService = b;
    }

    /* compiled from: SocketOptions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u0018\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "keepAlive", "", "getKeepAlive", "()Ljava/lang/Boolean;", "setKeepAlive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "lingerSeconds", "", "getLingerSeconds", "()I", "setLingerSeconds", "(I)V", "noDelay", "getNoDelay", "()Z", "setNoDelay", "(Z)V", "copy", "copy$ktor_network", "copyCommon", "", ADMConstants.EXTRA_FROM, "Lio/ktor/network/sockets/SocketOptions;", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class TCPClientSocketOptions extends PeerSocketOptions {
        @Nullable
        private Boolean keepAlive;
        private int lingerSeconds;
        private boolean noDelay;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TCPClientSocketOptions(@NotNull Map<Object, Object> customOptions) {
            super(customOptions);
            Intrinsics.checkParameterIsNotNull(customOptions, "customOptions");
            this.noDelay = true;
            this.lingerSeconds = -1;
        }

        @Override // io.ktor.network.sockets.SocketOptions.PeerSocketOptions, io.ktor.network.sockets.SocketOptions
        protected void copyCommon(@NotNull SocketOptions from) {
            Intrinsics.checkParameterIsNotNull(from, "from");
            super.copyCommon(from);
            if (from instanceof TCPClientSocketOptions) {
                TCPClientSocketOptions tCPClientSocketOptions = (TCPClientSocketOptions) from;
                this.noDelay = tCPClientSocketOptions.noDelay;
                this.lingerSeconds = tCPClientSocketOptions.lingerSeconds;
                this.keepAlive = tCPClientSocketOptions.keepAlive;
            }
        }

        @Nullable
        public final Boolean getKeepAlive() {
            return this.keepAlive;
        }

        public final int getLingerSeconds() {
            return this.lingerSeconds;
        }

        public final boolean getNoDelay() {
            return this.noDelay;
        }

        public final void setKeepAlive(@Nullable Boolean bool) {
            this.keepAlive = bool;
        }

        public final void setLingerSeconds(int i) {
            this.lingerSeconds = i;
        }

        public final void setNoDelay(boolean z) {
            this.noDelay = z;
        }

        @Override // io.ktor.network.sockets.SocketOptions.PeerSocketOptions, io.ktor.network.sockets.SocketOptions
        @NotNull
        /* renamed from: copy$ktor_network  reason: collision with other method in class */
        public TCPClientSocketOptions mo10312copy$ktor_network() {
            TCPClientSocketOptions tCPClientSocketOptions = new TCPClientSocketOptions(new HashMap(getCustomOptions()));
            tCPClientSocketOptions.copyCommon(this);
            return tCPClientSocketOptions;
        }
    }

    /* compiled from: SocketOptions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "copy", "copy$ktor_network", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class UDPSocketOptions extends PeerSocketOptions {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UDPSocketOptions(@NotNull Map<Object, Object> customOptions) {
            super(customOptions);
            Intrinsics.checkParameterIsNotNull(customOptions, "customOptions");
        }

        @Override // io.ktor.network.sockets.SocketOptions.PeerSocketOptions, io.ktor.network.sockets.SocketOptions
        @NotNull
        /* renamed from: copy$ktor_network  reason: collision with other method in class */
        public UDPSocketOptions mo10312copy$ktor_network() {
            UDPSocketOptions uDPSocketOptions = new UDPSocketOptions(new HashMap(getCustomOptions()));
            uDPSocketOptions.copyCommon(this);
            return uDPSocketOptions;
        }
    }

    public /* synthetic */ SocketOptions(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }
}
