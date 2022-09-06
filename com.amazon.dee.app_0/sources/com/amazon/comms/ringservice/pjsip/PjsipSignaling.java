package com.amazon.comms.ringservice.pjsip;

import android.os.Handler;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.AmazonCallInfo;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.Signaling;
import com.amazon.comms.ringservice.SignalingChannelListener;
import com.amazon.comms.ringservice.SignalingListener;
import com.amazon.comms.ringservice.pjsip.PjsipAccount;
import com.amazon.comms.ringservice.util.Capability;
import com.amazon.comms.ringservice.util.SipUri;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AccountSipConfig;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.AuthCredInfoVector;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.LogConfig;
import org.pjsip.pjsua2.LogWriter;
import org.pjsip.pjsua2.PresenceStatus;
import org.pjsip.pjsua2.SipHeaderVector;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.UaConfig;
import org.pjsip.pjsua2.pj_log_decoration;
import org.pjsip.pjsua2.pjrpid_activity;
import org.pjsip.pjsua2.pjsip_status_code;
import org.pjsip.pjsua2.pjsip_transport_type_e;
import org.pjsip.pjsua2.pjsua_buddy_status;
import org.pjsip.pjsua2.pjsua_destroy_flag;
/* loaded from: classes12.dex */
public class PjsipSignaling implements Signaling {
    static final String APPLICATION_TRICKLE_ICE_CTYPE = "application/trickle-ice-sdpfrag";
    private static final String DEFAULT_REALM = "localhost";
    private static final int PJSIP_CALL_DELETE_DELAY_MS = 250;
    private static final int SESSION_TIMER_EXPIRATION_PERIOD_S = 1800;
    private static final int SESSION_TIMER_MINIMUM_EXPIRATION_PERIOD_S = 100;
    private static final int SIP_TLS_PORT = 5061;
    private static final int SIP_UDP_TCP_PORT = 5060;
    private Endpoint endpoint;
    private EpConfig epConfig;
    private LogWriter logWriter;
    private final Handler orchestratorHandler;
    private SipHeaders registrationHeaders;
    private SignalingListener signalingListener;
    private TransportConfig sipTcpTpConfig;
    private TransportConfig sipTlsTpConfig;
    private TransportConfig sipUdpTpConfig;
    private static final CommsLogger sLog = CommsLogger.getLogger(PjsipSignaling.class);
    private static final String DEFAULT_AOR = String.format(Locale.US, "sip:anonymous@%s", "localhost");
    private boolean initialized = false;
    private AccountConfig accountConfig = null;
    private PjsipAccount account = null;
    private boolean accountCreated = false;
    private final int[] transportIds = {-1, -1, -1};
    private final ConcurrentLinkedQueue<PjsipCall> pjsipCalls = new ConcurrentLinkedQueue<>();
    private final List<Channel> channels = new ArrayList();
    private final boolean serverMode = false;
    private boolean isPresencePublishCapable = true;

    /* loaded from: classes12.dex */
    public static class AnswerMessage implements Signaling.Message {
        private SipHeaders headers;
        private Sdp sdp;

        public AnswerMessage(SipHeaders sipHeaders, Sdp sdp) {
            this.headers = sipHeaders;
            this.sdp = sdp;
        }

        public SipHeaders getHeaders() {
            return this.headers;
        }

        public Sdp getSdp() {
            return this.sdp;
        }
    }

    /* loaded from: classes12.dex */
    public static class CallMessage implements Signaling.Message {
        private AmazonCallInfo callInfo;
        private Sdp sdp;

        public CallMessage(AmazonCallInfo amazonCallInfo, Sdp sdp) {
            this.callInfo = amazonCallInfo;
            this.sdp = sdp;
        }

        public AmazonCallInfo getCallInfo() {
            return this.callInfo;
        }

        public Sdp getSdp() {
            return this.sdp;
        }
    }

    /* loaded from: classes12.dex */
    public static class CallerIdInfoMessage implements Signaling.Message {
        private String callerId;
        private String name;
        private boolean secondCall;

        public CallerIdInfoMessage(String str, String str2, boolean z) {
            this.name = str;
            this.callerId = str2;
            this.secondCall = z;
        }

        public String getCallerId() {
            return this.callerId;
        }

        public String getName() {
            return this.name;
        }

        public boolean isSecondCall() {
            return this.secondCall;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class Channel implements Signaling.Channel {
        private final PjsipCall call;
        private final String callId;
        private final EventTracer eventTracer;
        private final PjsipSignaling signaling;
        private SignalingChannelListener signalingChannelListener;
        private final SipUri targetUri;
        private final Queue<Signaling.Message> pendingMessages = new ArrayDeque();
        private Signaling.Channel.State state = Signaling.Channel.State.Open;

        public Channel(PjsipSignaling pjsipSignaling, SipUri sipUri, String str, PjsipCall pjsipCall, EventTracer eventTracer) {
            this.signaling = pjsipSignaling;
            this.targetUri = sipUri;
            this.callId = str;
            this.call = pjsipCall;
            this.call.setChannel(this);
            this.eventTracer = eventTracer;
        }

        protected synchronized void activate() {
            this.state = Signaling.Channel.State.Active;
        }

        public synchronized void close() {
            this.state = Signaling.Channel.State.Closed;
            this.signaling.channels.remove(this);
        }

        void drainMessages() {
            for (final Signaling.Message message : this.pendingMessages) {
                this.signaling.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.Channel.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PjsipSignaling.sLog.i("Sending Pending Message");
                        Channel.this.sendMessage(message);
                    }
                });
            }
        }

        public PjsipCall getCall() {
            return this.call;
        }

        public String getCallId() {
            return this.callId;
        }

        public EventTracer getEventTracer() {
            return this.eventTracer;
        }

        @Override // com.amazon.comms.ringservice.Signaling.Channel
        public Signaling.ChannelInfo getInfo() {
            return new ChannelInfo(this.callId, this.eventTracer);
        }

        public Queue<Signaling.Message> getPendingMessages() {
            return this.pendingMessages;
        }

        public PjsipSignaling getSignaling() {
            return this.signaling;
        }

        public SignalingChannelListener getSignalingChannelListener() {
            return this.signalingChannelListener;
        }

        @Override // com.amazon.comms.ringservice.Signaling.Channel
        public Signaling.Channel.State getState() {
            return this.state;
        }

        public SipUri getTargetUri() {
            return this.targetUri;
        }

        @Override // com.amazon.comms.ringservice.Signaling.Channel
        public void registerListener(SignalingChannelListener signalingChannelListener) {
            this.signalingChannelListener = signalingChannelListener;
        }

        @Override // com.amazon.comms.ringservice.Signaling.Channel
        public boolean sendMessage(Signaling.Message message) {
            if (this.state == Signaling.Channel.State.Closed || !this.signaling.isActive()) {
                return false;
            }
            if (!this.signaling.isRegistered()) {
                this.pendingMessages.add(message);
                return true;
            }
            PjsipCall pjsipCall = this.call;
            if (message instanceof CallMessage) {
                activate();
                CallMessage callMessage = (CallMessage) message;
                CallOpParam callOpParam = new CallOpParam();
                callOpParam.getTxOption().setHeaders(PjsipHeaders.transform(callMessage.getCallInfo().getOutgoingHeaders()));
                String uri = callMessage.getCallInfo().getRemoteParticipant().getUri();
                if (!uri.equals(this.targetUri.toString())) {
                    callOpParam.getTxOption().setTargetUri(this.targetUri.toString());
                }
                try {
                    PjsipSignaling.sLog.i("Pjsip is making a call");
                    pjsipCall.makeCall(uri, callOpParam);
                    PjsipSignaling.sLog.d("PjsipCall trying to sendOffer");
                    pjsipCall.sendOffer(pjsipCall.getId(), callMessage.getSdp().toString());
                    return true;
                } catch (Exception e) {
                    PjsipSignaling.sLog.e(String.format("Pjsip is unable to make a call: %s", e));
                    this.state = Signaling.Channel.State.Closed;
                    return false;
                }
            } else if (this.state != Signaling.Channel.State.Active) {
                return false;
            } else {
                if (message instanceof AnswerMessage) {
                    AnswerMessage answerMessage = (AnswerMessage) message;
                    CallOpParam callOpParam2 = new CallOpParam();
                    callOpParam2.getTxOption().setHeaders(PjsipHeaders.transform(answerMessage.getHeaders()));
                    callOpParam2.setStatusCode(pjsip_status_code.PJSIP_SC_OK);
                    PjsipSignaling.sLog.d("PjsipCall trying to sendAnswer");
                    pjsipCall.sendAnswer(pjsipCall.getId(), answerMessage.getSdp().toString());
                    try {
                        pjsipCall.answer(callOpParam2);
                    } catch (Exception e2) {
                        PjsipSignaling.sLog.e(String.format("Pjsip is unable to answer a call: %s", e2));
                        this.state = Signaling.Channel.State.Closed;
                        return false;
                    }
                } else if (message instanceof RingingMessage) {
                    CallOpParam callOpParam3 = new CallOpParam();
                    callOpParam3.setStatusCode(pjsip_status_code.PJSIP_SC_RINGING);
                    PjsipSignaling.sLog.d("PjsipCall trying to send ringing");
                    try {
                        pjsipCall.answer(callOpParam3);
                    } catch (Exception e3) {
                        PjsipSignaling.sLog.e(String.format("Pjsip is unable to send Ringing message: %s", e3));
                        this.state = Signaling.Channel.State.Closed;
                        return false;
                    }
                } else if (message instanceof HangupMessage) {
                    HangupMessage hangupMessage = (HangupMessage) message;
                    CallOpParam callOpParam4 = new CallOpParam();
                    if (hangupMessage.getReason() == HangupReason.Busy) {
                        callOpParam4.setStatusCode(pjsip_status_code.PJSIP_SC_BUSY_HERE);
                    } else if (hangupMessage.getReason() == HangupReason.UnsupportedCodec) {
                        callOpParam4.setStatusCode(pjsip_status_code.PJSIP_SC_NOT_ACCEPTABLE_HERE);
                    } else {
                        callOpParam4.setStatusCode(pjsip_status_code.PJSIP_SC_DECLINE);
                    }
                    try {
                        CommsLogger commsLogger = PjsipSignaling.sLog;
                        commsLogger.i("Initiating PJSIP call hangup. params= " + callOpParam4);
                        pjsipCall.hangup(callOpParam4, hangupMessage.isReportMetricsAndTraceEventsOnHangup());
                    } catch (Exception e4) {
                        PjsipSignaling.sLog.e(String.format("Pjsip is unable to hang up a call: %s", e4));
                        this.state = Signaling.Channel.State.Closed;
                        return false;
                    }
                } else if (message instanceof SdpUpdateMessage) {
                    PjsipSignaling.sLog.d("pjsip call handleSdpUpdateMessage");
                    pjsipCall.handleSdpUpdateMessage((SdpUpdateMessage) message);
                } else if (message instanceof DtmfMessage) {
                    try {
                        DtmfMessage dtmfMessage = (DtmfMessage) message;
                        pjsipCall.sendDTMFinInfo(pjsipCall.getId(), dtmfMessage.getDtmfString(), dtmfMessage.getDuration());
                    } catch (Exception e5) {
                        PjsipSignaling.sLog.e(String.format("Pjsip is unable to send dtmf message: %s", e5));
                        this.state = Signaling.Channel.State.Closed;
                        return false;
                    }
                } else if (message instanceof IceCandidateMessage) {
                    IceCandidateMessage iceCandidateMessage = (IceCandidateMessage) message;
                    PjsipSignaling.sLog.d("pjsip call IceCandidateMessage");
                    try {
                        pjsipCall.sendInfoMessage(pjsipCall.getId(), PjsipSignaling.APPLICATION_TRICKLE_ICE_CTYPE, iceCandidateMessage.getPseudoSdp().toString());
                    } catch (Exception e6) {
                        PjsipSignaling.sLog.e(String.format("Pjsip is unable to send sip info message: %s", e6));
                        this.state = Signaling.Channel.State.Closed;
                        return false;
                    }
                }
                return true;
            }
        }

        @Override // com.amazon.comms.ringservice.Signaling.Channel
        public void unregisterListener() {
            this.signalingChannelListener = null;
        }
    }

    /* loaded from: classes12.dex */
    public static class ChannelInfo implements Signaling.ChannelInfo {
        private String callId;
        private EventTracer eventTracer;

        public ChannelInfo(String str, EventTracer eventTracer) {
            this.callId = str;
            this.eventTracer = eventTracer;
        }

        public String getCallId() {
            return this.callId;
        }

        public EventTracer getEventTracer() {
            return this.eventTracer;
        }
    }

    /* loaded from: classes12.dex */
    public static class ChannelParams implements Signaling.ChannelParams {
        private String callId;
        private EventTracer eventTracer;
        private String targetUri;

        public ChannelParams(String str, String str2, EventTracer eventTracer) {
            this.callId = str;
            this.targetUri = str2;
            this.eventTracer = eventTracer;
        }

        public String getCallId() {
            return this.callId;
        }

        public EventTracer getEventTracer() {
            return this.eventTracer;
        }

        public String getTargetUri() {
            return this.targetUri;
        }
    }

    /* loaded from: classes12.dex */
    public static class ConfigurationParams implements Signaling.ConfigurationParams {
        private SipHeaders registrationHeaders;

        public ConfigurationParams(SipHeaders sipHeaders) {
            this.registrationHeaders = sipHeaders;
        }

        public SipHeaders getRegistrationHeaders() {
            return this.registrationHeaders;
        }
    }

    /* loaded from: classes12.dex */
    public static class ConnectParams implements Signaling.ConnectParams {
        private List<Capability> capabilities;
        private RegistrarConfiguration registrarConfig;

        public ConnectParams(RegistrarConfiguration registrarConfiguration, List<Capability> list) {
            this.registrarConfig = registrarConfiguration;
            this.capabilities = list;
        }

        public List<Capability> getCapabilities() {
            return this.capabilities;
        }

        public RegistrarConfiguration getRegistrarConfig() {
            return this.registrarConfig;
        }
    }

    /* loaded from: classes12.dex */
    public static class DtmfMessage implements Signaling.Message {
        private String dtmfString;
        private int duration;

        public DtmfMessage(String str, int i) {
            this.dtmfString = str;
            this.duration = i;
        }

        public String getDtmfString() {
            return this.dtmfString;
        }

        public int getDuration() {
            return this.duration;
        }
    }

    /* loaded from: classes12.dex */
    public static class DtmfResponseMessage implements Signaling.Message {
        private String reason;
        private int statusCode;

        public DtmfResponseMessage(int i, String str) {
            this.statusCode = i;
            this.reason = str;
        }

        public String getReason() {
            return this.reason;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }

    /* loaded from: classes12.dex */
    public static class EarlyMediaMessage implements Signaling.Message {
        private Sdp sdp;

        public EarlyMediaMessage(Sdp sdp) {
            this.sdp = sdp;
        }

        public Sdp getSdp() {
            return this.sdp;
        }
    }

    /* loaded from: classes12.dex */
    public static class HangupMessage implements Signaling.Message {
        private HangupReason reason;
        private boolean reportMetricsAndTraceEventsOnHangup;

        public HangupMessage(HangupReason hangupReason, boolean z) {
            this.reason = hangupReason;
            this.reportMetricsAndTraceEventsOnHangup = z;
        }

        public HangupReason getReason() {
            return this.reason;
        }

        public boolean isReportMetricsAndTraceEventsOnHangup() {
            return this.reportMetricsAndTraceEventsOnHangup;
        }

        public HangupMessage(HangupReason hangupReason) {
            this(hangupReason, true);
        }
    }

    /* loaded from: classes12.dex */
    public static class IceCandidateMessage implements Signaling.Message {
        private Sdp pseudoSdp;

        public IceCandidateMessage(Sdp sdp) {
            this.pseudoSdp = sdp;
        }

        public Sdp getPseudoSdp() {
            return this.pseudoSdp;
        }
    }

    /* loaded from: classes12.dex */
    public static class IceCandidateResponseMessage implements Signaling.Message {
        private String reason;
        private int statusCode;

        public IceCandidateResponseMessage(int i, String str) {
            this.statusCode = i;
            this.reason = str;
        }

        public String getReason() {
            return this.reason;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }

    /* loaded from: classes12.dex */
    public static class InitParams implements Signaling.InitParams {
        private EventTracerFactory eventTracerFactory;
        private List<String> nameServers;
        private boolean presencePublishCapable;
        private SipHeaders registrationHeaders;
        private String rootCACertFilePath;
        private String userAgentInfoTemplate;

        public InitParams(String str, List<String> list, String str2, EventTracerFactory eventTracerFactory, SipHeaders sipHeaders, boolean z) {
            this.rootCACertFilePath = str;
            this.nameServers = list;
            this.userAgentInfoTemplate = str2;
            this.eventTracerFactory = eventTracerFactory;
            this.registrationHeaders = sipHeaders;
            this.presencePublishCapable = z;
        }

        public EventTracerFactory getEventTracerFactory() {
            return this.eventTracerFactory;
        }

        public List<String> getNameServers() {
            return this.nameServers;
        }

        public SipHeaders getRegistrationHeaders() {
            return this.registrationHeaders;
        }

        public String getRootCACertFilePath() {
            return this.rootCACertFilePath;
        }

        public String getUserAgentInfoTemplate() {
            return this.userAgentInfoTemplate;
        }

        public boolean isPresencePublishCapable() {
            return this.presencePublishCapable;
        }
    }

    /* loaded from: classes12.dex */
    public static class MessageErrorInfo implements Signaling.MessageErrorInfo {
        private String callId;
        private ErrorCode errorCode;
        private String errorDescription;
        private int internalErrorCode;

        public MessageErrorInfo(String str, ErrorCode errorCode, int i, String str2) {
            this.callId = str;
            this.errorCode = errorCode;
            this.internalErrorCode = i;
            this.errorDescription = str2;
        }

        public String getCallId() {
            return this.callId;
        }

        public ErrorCode getErrorCode() {
            return this.errorCode;
        }

        public String getErrorDescription() {
            return this.errorDescription;
        }

        public int getInternalErrorCode() {
            return this.internalErrorCode;
        }
    }

    /* loaded from: classes12.dex */
    public static class PresenceInfoForRegistrar implements Signaling.ServerMessage {
        private String dataBlobForNote;

        public PresenceInfoForRegistrar(String str) {
            this.dataBlobForNote = str;
        }

        public String getDataBlobForNote() {
            return this.dataBlobForNote;
        }
    }

    /* loaded from: classes12.dex */
    public static class RingingMessage implements Signaling.Message {
    }

    /* loaded from: classes12.dex */
    public static class SdpUpdateMessage implements Signaling.Message {
        private boolean remoteUpdateInProgress;
        private Sdp sdp;
        private Sdp.Type type;

        public SdpUpdateMessage(Sdp sdp, Sdp.Type type, boolean z) {
            this.sdp = sdp;
            this.type = type;
            this.remoteUpdateInProgress = z;
        }

        public Sdp getSdp() {
            return this.sdp;
        }

        public Sdp.Type getType() {
            return this.type;
        }

        public boolean isRemoteUpdateInProgress() {
            return this.remoteUpdateInProgress;
        }
    }

    /* loaded from: classes12.dex */
    public static class SessionRefreshMessage implements Signaling.Message {
    }

    public PjsipSignaling(SignalingListener signalingListener, Handler handler) {
        boolean z = false;
        Preconditions.checkArgument(signalingListener != null, "signaling listener cannot be null.");
        Preconditions.checkArgument(handler != null ? true : z, "orchestratorHandler cannot be null.");
        this.signalingListener = signalingListener;
        this.orchestratorHandler = handler;
    }

    private void closeAllTransports() {
        int i = 0;
        while (true) {
            int[] iArr = this.transportIds;
            if (i < iArr.length) {
                int i2 = iArr[i];
                if (i2 > -1) {
                    try {
                        CommsLogger commsLogger = sLog;
                        commsLogger.i("closing old Transport endpoint: " + i2);
                        this.endpoint.transportClose(i2, true);
                        this.transportIds[i] = -1;
                    } catch (Exception e) {
                        CommsLogger commsLogger2 = sLog;
                        commsLogger2.e("unable to close transport= " + i2, e);
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    private int createTransport(pjsip_transport_type_e pjsip_transport_type_eVar, TransportConfig transportConfig) {
        sLog.i(String.format("Creating transport: %s", pjsip_transport_type_eVar));
        int i = -1;
        try {
            i = this.endpoint.transportCreate(pjsip_transport_type_eVar, transportConfig);
            CommsLogger commsLogger = sLog;
            commsLogger.i("created pjsip transport with id= " + i);
            return i;
        } catch (Exception unused) {
            sLog.e(String.format("Exception creating transport: %s", pjsip_transport_type_eVar));
            return i;
        }
    }

    private void createTransportConfigs(String str) {
        if (this.sipTlsTpConfig == null) {
            this.sipTlsTpConfig = new TransportConfig();
            if (!Strings.isNullOrEmpty(str)) {
                CommsLogger commsLogger = sLog;
                commsLogger.i("Setting CA list file path to: " + str);
                this.sipTlsTpConfig.getTlsConfig().setCaListFile(str);
            } else {
                sLog.e("Unable to get root CA cert file! Connection to registrar may fail!");
            }
            this.sipTlsTpConfig.getTlsConfig().setVerifyServer(true);
        }
    }

    private void deleteAccount() {
        if (this.account == null) {
            return;
        }
        sLog.i("Deleting pjsip account");
        this.account.shutdown();
        this.account.delete();
        this.account = null;
    }

    private void deleteAccountAndConfig() {
        this.accountCreated = false;
        deleteAccount();
        deleteAccountConfig();
    }

    private void deleteAccountConfig() {
        AuthCredInfoVector authCreds;
        if (this.accountConfig == null) {
            return;
        }
        sLog.i("Deleting pjsip account config");
        AccountSipConfig sipConfig = this.accountConfig.getSipConfig();
        if (sipConfig != null && (authCreds = sipConfig.getAuthCreds()) != null) {
            deleteAuthCredInfos(authCreds);
        }
        this.accountConfig.delete();
        this.accountConfig = null;
    }

    private void deleteAllTransportConfigs() {
        TransportConfig transportConfig = this.sipUdpTpConfig;
        if (transportConfig != null) {
            transportConfig.delete();
            this.sipUdpTpConfig = null;
        }
        TransportConfig transportConfig2 = this.sipTcpTpConfig;
        if (transportConfig2 != null) {
            transportConfig2.delete();
            this.sipTcpTpConfig = null;
        }
        TransportConfig transportConfig3 = this.sipTlsTpConfig;
        if (transportConfig3 != null) {
            transportConfig3.delete();
            this.sipTlsTpConfig = null;
        }
    }

    private void deleteAuthCredInfos(AuthCredInfoVector authCredInfoVector) {
        for (int i = 0; i < authCredInfoVector.size(); i++) {
            authCredInfoVector.get(i).delete();
        }
        authCredInfoVector.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteCall(final PjsipCall pjsipCall) {
        if (Thread.currentThread() == this.orchestratorHandler.getLooper().getThread()) {
            sLog.i("deleting PJSIP Call object");
            pjsipCall.delete();
            return;
        }
        sLog.i("scheduling to delete PJSIP call object");
        this.orchestratorHandler.postDelayed(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.8
            @Override // java.lang.Runnable
            public void run() {
                PjsipSignaling.this.deleteCall(pjsipCall);
            }
        }, 250L);
    }

    private void deleteEndpointConfig() {
        if (this.epConfig == null) {
            sLog.d("No endpoint config to delete!");
            return;
        }
        sLog.i("Deleting endpoint config");
        this.epConfig.delete();
        this.epConfig = null;
    }

    private void deletePjsipEndpoint() {
        if (this.endpoint == null) {
            sLog.i("No endpoint to delete.");
            return;
        }
        try {
            sLog.i("Deleting pjsip endpoint");
            this.endpoint.delete();
            this.endpoint = null;
        } catch (Exception e) {
            sLog.e(String.format("Exception destroying endpoint: %s", e.getMessage()), e);
        }
    }

    private void destroyPjsipEndpoint() {
        if (this.endpoint == null) {
            sLog.i("No endpoint to destroy.");
            return;
        }
        try {
            sLog.i("Destroying pjsip endpoint");
            Runtime.getRuntime().gc();
            this.endpoint.libDestroy(pjsua_destroy_flag.PJSUA_DESTROY_NO_NETWORK.swigValue());
        } catch (Exception e) {
            sLog.e(String.format("Exception destroying endpoint: %s", e.getMessage()), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enablePublish() {
        try {
            if (this.account == null || this.accountConfig.getPresConfig().getPublishEnabled() || !this.isPresencePublishCapable) {
                return;
            }
            this.accountConfig.getPresConfig().setPublishEnabled(true);
            this.account.modify(this.accountConfig);
        } catch (Exception e) {
            sLog.e(String.format("enablePublish, exception: %s", e.getMessage()), e);
        }
    }

    private String generateContactParams(List<Capability> list) {
        StringBuilder sb = new StringBuilder();
        for (Capability capability : list) {
            CommsLogger commsLogger = sLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adding capability ");
            outline107.append(capability.toString());
            commsLogger.i(outline107.toString());
            sb.append(";");
            sb.append(capability.toString());
        }
        return sb.toString().toLowerCase(Locale.US);
    }

    private boolean initializeAndStartPjsipEndpointTransport(String str) {
        if (this.endpoint != null) {
            sLog.i("Not making another endpoint, one already present!");
            return true;
        }
        this.endpoint = new Endpoint();
        try {
            sLog.i("Creating endpoint");
            this.endpoint.libCreate();
            this.epConfig.getUaConfig().setUserAgent(str.replace("__PJSIP_VERSION__", this.endpoint.libVersion().getFull()));
            try {
                sLog.i("Initializing endpoint.");
                this.endpoint.libInit(this.epConfig);
                openTransports();
                try {
                    sLog.i("Starting endpoint.");
                    this.endpoint.libStart();
                    return true;
                } catch (Exception e) {
                    sLog.e(String.format("Exception starting endpoint: %s", e.getMessage()), e);
                    destroyPjsipEndpoint();
                    deletePjsipEndpoint();
                    return false;
                }
            } catch (Exception e2) {
                sLog.e(String.format("Exception initializing endpoint: %s", e2.getMessage()), e2);
                deletePjsipEndpoint();
                return false;
            }
        } catch (Exception e3) {
            sLog.e(String.format("Exception creating endpoint: %s", e3.getMessage()), e3);
            deletePjsipEndpoint();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isActive() {
        return this.account != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRegistered() {
        return this.account.getState() == PjsipAccount.State.Registered;
    }

    private void makeAndInitEndpointConfig(List<String> list) {
        if (this.epConfig != null) {
            sLog.i("Not making endpoint config as one already exists.");
            return;
        }
        sLog.i("Instantiating and Initializing endpoint config");
        this.epConfig = new EpConfig();
        this.epConfig.getLogConfig().setLevel(6L);
        this.epConfig.getLogConfig().setConsoleLevel(6L);
        LogConfig logConfig = this.epConfig.getLogConfig();
        this.logWriter = new PjsipLogWriter();
        logConfig.setWriter(this.logWriter);
        logConfig.setDecor(pj_log_decoration.PJ_LOG_HAS_INDENT.swigValue() | pj_log_decoration.PJ_LOG_HAS_SENDER.swigValue() | pj_log_decoration.PJ_LOG_HAS_THREAD_SWC.swigValue());
        UaConfig uaConfig = this.epConfig.getUaConfig();
        uaConfig.setThreadCnt(1L);
        uaConfig.getNameserver().clear();
        for (String str : list) {
            uaConfig.getNameserver().add(str);
        }
    }

    private void makeNewAccount(EventTracerFactory eventTracerFactory) {
        if (this.account != null) {
            sLog.i("Not creating pjsip account, already have one");
            return;
        }
        sLog.i("Instantiating pjsip account and config");
        this.account = new PjsipAccount(this, eventTracerFactory);
        this.accountCreated = false;
    }

    private void makeNewAccountConfig() {
        if (this.accountConfig != null) {
            return;
        }
        sLog.i("Creating new pjsip account config");
        this.accountConfig = new AccountConfig();
        this.accountConfig.setIdUri(DEFAULT_AOR);
        this.accountConfig.getRegConfig().setHeaders(PjsipHeaders.transform(this.registrationHeaders));
        this.accountConfig.getCallConfig().setTimerMinSESec(100L);
        this.accountConfig.getCallConfig().setTimerSessExpiresSec(1800L);
        this.accountConfig.getPresConfig().setPublishEnabled(this.isPresencePublishCapable);
        this.accountConfig.getPresConfig().setPidfTupleId("presence_pidftuple");
    }

    private void openTransports() {
        sLog.i("Creating transports");
        TransportConfig transportConfig = this.sipUdpTpConfig;
        if (transportConfig != null) {
            this.transportIds[0] = createTransport(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, transportConfig);
        }
        TransportConfig transportConfig2 = this.sipTcpTpConfig;
        if (transportConfig2 != null) {
            this.transportIds[1] = createTransport(pjsip_transport_type_e.PJSIP_TRANSPORT_TCP, transportConfig2);
        }
        TransportConfig transportConfig3 = this.sipTlsTpConfig;
        if (transportConfig3 != null) {
            this.transportIds[2] = createTransport(pjsip_transport_type_e.PJSIP_TRANSPORT_TLS, transportConfig3);
        }
    }

    private void updateAuthCredInfos(AuthCredInfoVector authCredInfoVector, String str) {
        deleteAuthCredInfos(authCredInfoVector);
        authCredInfoVector.add(new AuthCredInfo("digest", str, "", 0, ""));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addCall(PjsipCall pjsipCall) {
        return this.pjsipCalls.add(pjsipCall);
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void close() {
        sLog.i("Shutting down transports");
        closeAllTransports();
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public boolean connect(Signaling.ConnectParams connectParams) {
        if (!this.initialized) {
            sLog.e(" Attempting to connect without initializing.. ");
            return false;
        } else if (!(connectParams instanceof ConnectParams)) {
            return false;
        } else {
            ConnectParams connectParams2 = (ConnectParams) connectParams;
            RegistrarConfiguration registrarConfig = connectParams2.getRegistrarConfig();
            sLog.i("Clearing authtoken header for sip registration");
            SipHeaders sipHeaders = new SipHeaders(this.registrationHeaders);
            if (!Strings.isNullOrEmpty(registrarConfig.getAuthToken())) {
                sLog.i("Setting authtoken header for sip registration");
                sipHeaders.put(SipHeaders.SIP_HEADER_AUTH_TOKEN, registrarConfig.getAuthToken());
            }
            if (registrarConfig.getHeaders() != null) {
                sipHeaders.putAll(registrarConfig.getHeaders());
            }
            sLog.i(EmulateConnection.EXTRA_CONNECT);
            String format = String.format(Locale.US, "%s:%s@%s", registrarConfig.getScheme(), registrarConfig.getUser(), registrarConfig.getDomain());
            String format2 = String.format(Locale.US, "%s:%s", registrarConfig.getScheme(), registrarConfig.getDomain());
            this.accountConfig.setIdUri(format);
            this.accountConfig.getRegConfig().setRegistrarUri(format2);
            this.accountConfig.getNatConfig().setSipOutboundInstanceId(registrarConfig.getSipInstance());
            updateAuthCredInfos(this.accountConfig.getSipConfig().getAuthCreds(), registrarConfig.getRealm());
            this.accountConfig.getSipConfig().getProxies().clear();
            if (registrarConfig.getProxyHost() != null) {
                this.accountConfig.getSipConfig().getProxies().add(String.format(Locale.US, "%s:%s:%s", registrarConfig.getScheme(), registrarConfig.getProxyHost(), Integer.valueOf(registrarConfig.getProxyPort())));
            }
            this.accountConfig.getSipConfig().setContactParams(generateContactParams(connectParams2.getCapabilities()));
            this.accountConfig.getRegConfig().setHeaders(PjsipHeaders.transform(sipHeaders));
            this.accountConfig.getRegConfig().setTimeoutSec(registrarConfig.getExpires());
            sLog.i(String.format("pjsip account to use registrar: %s", format2));
            if (this.accountCreated) {
                try {
                    this.account.modify(this.accountConfig);
                    sLog.i("Modified PJSIP account with stored config");
                } catch (Exception e) {
                    sLog.e(String.format("Exception modifying PJSIP account: %s", e.getMessage()), e);
                    postConnectionError(ErrorCode.ClientFailed.getValue(), "Exception modifying PJSIP account");
                    return false;
                }
            } else {
                try {
                    this.account.create(this.accountConfig);
                    this.accountCreated = true;
                    sLog.i("Created new PJSIP account with stored config");
                } catch (Exception e2) {
                    sLog.e(String.format("Exception creating PJSIP account: %s", e2.getMessage()), e2);
                    postConnectionError(ErrorCode.ClientFailed.getValue(), "Exception creating PJSIP account");
                    return false;
                }
            }
            return true;
        }
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public Signaling.Channel createChannel(Signaling.ChannelParams channelParams) {
        if (!(channelParams instanceof ChannelParams)) {
            return null;
        }
        ChannelParams channelParams2 = (ChannelParams) channelParams;
        try {
            sLog.i("creating new PjsipCall object");
            PjsipCall pjsipCall = new PjsipCall(this.account, channelParams2.getCallId(), channelParams2.getEventTracer(), this);
            try {
                Channel channel = new Channel(this, new SipUri(channelParams2.getTargetUri()), channelParams2.getCallId(), pjsipCall, channelParams2.getEventTracer());
                this.channels.add(channel);
                return channel;
            } catch (Exception unused) {
                return null;
            }
        } catch (Exception e) {
            sLog.w("Couldn't create PjsipCall object", e);
            return null;
        }
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void disablePublish() {
        try {
            if (this.account == null || !this.accountConfig.getPresConfig().getPublishEnabled()) {
                return;
            }
            this.accountConfig.getPresConfig().setPublishEnabled(false);
            this.account.modify(this.accountConfig);
        } catch (Exception e) {
            sLog.e(String.format("disablePublish, exception: %s", e.getMessage()), e);
        }
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void disconnect() {
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void exit() {
        sLog.i("Trying to exit signaling.");
        Iterator<PjsipCall> it2 = this.pjsipCalls.iterator();
        while (it2.hasNext()) {
            PjsipCall next = it2.next();
            sLog.i("Calling close on pjsipCall");
            next.close();
            sLog.i("Calling removeCall on pjsipCall");
            removeCall(next);
        }
        this.pjsipCalls.clear();
        this.channels.clear();
        deleteAccountAndConfig();
        closeAllTransports();
        destroyPjsipEndpoint();
        deleteAllTransportConfigs();
        deleteEndpointConfig();
        deletePjsipEndpoint();
        this.initialized = false;
        sLog.i("Signaling exited.");
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public boolean init(Signaling.InitParams initParams) {
        if (this.initialized) {
            sLog.w("Signaling stack already initialized");
            return true;
        } else if (!(initParams instanceof InitParams)) {
            sLog.e("params not instance of InitParams");
            return false;
        } else {
            InitParams initParams2 = (InitParams) initParams;
            this.registrationHeaders = initParams2.getRegistrationHeaders();
            this.isPresencePublishCapable = initParams2.isPresencePublishCapable();
            createTransportConfigs(initParams2.getRootCACertFilePath());
            makeAndInitEndpointConfig(initParams2.getNameServers());
            if (!initializeAndStartPjsipEndpointTransport(initParams2.getUserAgentInfoTemplate())) {
                sLog.w("Initialization failure. Could not init and start pjsip endpoint");
                exit();
                return false;
            }
            makeNewAccountConfig();
            makeNewAccount(initParams2.getEventTracerFactory());
            this.initialized = true;
            return true;
        }
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void open() {
        openTransports();
        try {
            this.account.setRegistration(true);
        } catch (Exception e) {
            sLog.e(String.format("Exception registering PJSIP account: %s", e.getMessage()), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postConnected(final long j) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.3
            @Override // java.lang.Runnable
            public void run() {
                if (PjsipSignaling.this.account == null) {
                    return;
                }
                PjsipSignaling.this.enablePublish();
                PjsipSignaling.this.signalingListener.onConnected(j);
                for (Channel channel : PjsipSignaling.this.channels) {
                    channel.drainMessages();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postConnectionError(final int i, final String str) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.4
            @Override // java.lang.Runnable
            public void run() {
                if (PjsipSignaling.this.account == null) {
                    return;
                }
                PjsipSignaling.this.signalingListener.onConnectionError(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postMessageError(final Channel channel, final Signaling.MessageError messageError, final Signaling.MessageErrorInfo messageErrorInfo) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.1
            @Override // java.lang.Runnable
            public void run() {
                SignalingChannelListener signalingChannelListener = channel.getSignalingChannelListener();
                if (signalingChannelListener != null) {
                    signalingChannelListener.onMessageError(messageError, messageErrorInfo);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postNewChannel(final Channel channel, final Signaling.Message message) {
        channel.activate();
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.5
            @Override // java.lang.Runnable
            public void run() {
                if (PjsipSignaling.this.account == null) {
                    return;
                }
                PjsipSignaling.this.signalingListener.onNewChannel(channel, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postNewChannelError(final Signaling.MessageError messageError, final Signaling.MessageErrorInfo messageErrorInfo) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.6
            @Override // java.lang.Runnable
            public void run() {
                if (PjsipSignaling.this.account == null) {
                    return;
                }
                PjsipSignaling.this.signalingListener.onNewChannelError(messageError, messageErrorInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postPublishError(final int i, final String str) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.7
            @Override // java.lang.Runnable
            public void run() {
                if (PjsipSignaling.this.account == null) {
                    return;
                }
                PjsipSignaling.this.signalingListener.onPublishError(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postRecvMessage(final Channel channel, final Signaling.Message message) {
        this.orchestratorHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.pjsip.PjsipSignaling.2
            @Override // java.lang.Runnable
            public void run() {
                SignalingChannelListener signalingChannelListener = channel.getSignalingChannelListener();
                if (signalingChannelListener != null) {
                    signalingChannelListener.onRecvMessage(message);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean removeCall(PjsipCall pjsipCall) {
        deleteCall(pjsipCall);
        return this.pjsipCalls.remove(pjsipCall);
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void sendServerMessage(Signaling.ServerMessage serverMessage) {
        if ((serverMessage instanceof PresenceInfoForRegistrar) && this.accountConfig.getPresConfig().getPublishEnabled()) {
            PresenceStatus presenceStatus = new PresenceStatus();
            presenceStatus.setStatus(pjsua_buddy_status.PJSUA_BUDDY_STATUS_ONLINE);
            presenceStatus.setActivity(pjrpid_activity.PJRPID_ACTIVITY_BUSY);
            presenceStatus.setNote(((PresenceInfoForRegistrar) serverMessage).getDataBlobForNote());
            presenceStatus.setRpidId("presence_rpid");
            try {
                sLog.i("posting presence message for account.");
                this.account.setOnlineStatus(presenceStatus);
            } catch (Exception e) {
                sLog.w("Could not post status. ", e);
            }
        }
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public void updateCapabilities(List<Capability> list) {
        try {
            if (this.account == null) {
                return;
            }
            this.accountConfig.getSipConfig().setContactParams(generateContactParams(list));
            this.account.modify(this.accountConfig);
        } catch (Exception e) {
            sLog.e(String.format("updateCapabilities, exception: %s", e.getMessage()), e);
        }
    }

    @Override // com.amazon.comms.ringservice.Signaling
    public boolean updateConfigurationParams(Signaling.ConfigurationParams configurationParams, boolean z) {
        if (!this.accountCreated) {
            sLog.e("Account not created");
            return false;
        } else if (!(configurationParams instanceof ConfigurationParams)) {
            return false;
        } else {
            sLog.i("Updating SipHeaders");
            SipHeaders sipHeaders = new SipHeaders(this.registrationHeaders);
            sipHeaders.putAll(((ConfigurationParams) configurationParams).registrationHeaders);
            try {
                SipHeaderVector transform = PjsipHeaders.transform(sipHeaders);
                this.account.updateRegHeaders(transform);
                this.accountConfig.getRegConfig().setHeaders(transform);
                if (!this.accountConfig.getPresConfig().getPublishEnabled()) {
                    enablePublish();
                } else if (z) {
                    this.account.setRegistration(true);
                }
                return true;
            } catch (Exception e) {
                sLog.e(String.format("Exception updating creds for PJSIP account: %s", e.getMessage()), e);
                return false;
            }
        }
    }
}
