package com.amazon.comms.ringservice.pjsip;

import com.adobe.xmp.XMPConst;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.AmazonCallInfo;
import com.amazon.comms.ringservice.ParticipantImpl;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.Signaling;
import com.amazon.comms.ringservice.pjsip.PjsipSignaling;
import com.amazon.comms.ringservice.util.SipUri;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnIncomingSubscribeParam;
import org.pjsip.pjsua2.OnPublishErrorNotifyParam;
import org.pjsip.pjsua2.OnRegStateParam;
import org.pjsip.pjsua2.pjsip_status_code;
/* loaded from: classes12.dex */
public class PjsipAccount extends Account {
    private static final String CALL_PROVIDER_PIVOT = "CallProvider";
    private static final int INVALID_ACCOUNT_ID = -1;
    private static final String TRICKLE_ICE_PIVOT = "TrickleIceEnabled";
    private static final CommsLogger sLog = CommsLogger.getLogger(PjsipAccount.class);
    private AtomicBoolean created = new AtomicBoolean(false);
    private EventTracerFactory eventTracerFactory;
    private final PjsipSignaling pjsipSignaling;
    private State state;

    /* loaded from: classes12.dex */
    enum State {
        Unregistered,
        Registered
    }

    public PjsipAccount(PjsipSignaling pjsipSignaling, EventTracerFactory eventTracerFactory) {
        Preconditions.checkNotNull(pjsipSignaling);
        this.pjsipSignaling = pjsipSignaling;
        this.eventTracerFactory = eventTracerFactory;
        this.state = State.Unregistered;
    }

    @Override // org.pjsip.pjsua2.Account
    public void create(AccountConfig accountConfig, boolean z) throws Exception {
        CommsLogger commsLogger = sLog;
        commsLogger.i("Creating account with make_default=" + z);
        super.create(accountConfig, z);
        this.created.set(getId() != -1);
    }

    @Override // org.pjsip.pjsua2.Account
    public synchronized void delete() {
        sLog.i("Deleting account");
        this.created.set(false);
        super.delete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State getState() {
        return this.state;
    }

    @Override // org.pjsip.pjsua2.Account
    public void onIncomingCall(OnIncomingCallParam onIncomingCallParam) {
        String str;
        PjsipCall pjsipCall;
        Preconditions.checkArgument(onIncomingCallParam != null, "OnIncomingCallParam must be non-null.");
        SipHeaders transform = PjsipHeaders.transform(onIncomingCallParam.getRdata().getSipMsg().getHeaders());
        String str2 = transform.get(SipHeaders.SIP_HEADER_ALEXA_CALL_ID, "");
        String str3 = transform.get(SipHeaders.SIP_HEADER_ALEXA_CALLER_ID, "");
        String str4 = transform.get(SipHeaders.SIP_HEADER_ALEXA_CALLEE_ID, "");
        boolean equalsIgnoreCase = transform.get(SipHeaders.SIP_HEADER_FOR_DROP_IN, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE).equalsIgnoreCase("true");
        String str5 = transform.get(SipHeaders.SIP_HEADER_CALL_PROVIDER, CallProvider.A2A);
        Sdp sdp = new Sdp(onIncomingCallParam.getRdata().getSdp());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EventTracerFactory.Pivot("CallProvider", str5));
        arrayList.add(new EventTracerFactory.Pivot("TrickleIceEnabled", (!str5.equalsIgnoreCase(CallProvider.A2A) || !sdp.isTrickleIceEnabled()) ? XMPConst.FALSESTR : XMPConst.TRUESTR));
        EventTracer create = this.eventTracerFactory.create(str2, EventTracerFactory.Role.CALLEE, arrayList);
        create.mark(EventTracerConfig.Event.Callee_INVITE_Received);
        if (equalsIgnoreCase) {
            create.mark(EventTracerConfig.Event.Callee_DropIn_INVITE_Received);
        }
        try {
            sLog.i(String.format("Incoming callId %s on account %s", sLog.sensitiveCallId(str2), Integer.valueOf(getId())));
            pjsipCall = new PjsipCall(this, str2, onIncomingCallParam.getCallId(), create, this.pjsipSignaling);
            this.pjsipSignaling.addCall(pjsipCall);
            str = str2;
        } catch (Exception e) {
            e = e;
            str = str2;
        }
        try {
            this.pjsipSignaling.postNewChannel(new PjsipSignaling.Channel(this.pjsipSignaling, new SipUri(pjsipCall.getInfo().getRemoteUri()), str2, pjsipCall, create), new PjsipSignaling.CallMessage(AmazonCallInfo.createIncoming(transform, ParticipantImpl.builder().providerSpecifiedId(str4).uri(pjsipCall.getInfo().getLocalUri()).origin(Call.Side.Remote).name("").build(), ParticipantImpl.builder().providerSpecifiedId(str3).uri(pjsipCall.getInfo().getRemoteUri()).origin(Call.Side.Local).name("").build()), sdp));
        } catch (Exception e2) {
            e = e2;
            PjsipSignaling pjsipSignaling = this.pjsipSignaling;
            Signaling.MessageError messageError = Signaling.MessageError.RECV_ERROR;
            ErrorCode errorCode = ErrorCode.Unknown;
            pjsipSignaling.postNewChannelError(messageError, new PjsipSignaling.MessageErrorInfo(str, errorCode, errorCode.getValue(), e.toString()));
        }
    }

    @Override // org.pjsip.pjsua2.Account
    public void onIncomingSubscribe(OnIncomingSubscribeParam onIncomingSubscribeParam) {
        onIncomingSubscribeParam.setCode(pjsip_status_code.PJSIP_SC_DECLINE);
    }

    @Override // org.pjsip.pjsua2.Account
    public void onPublishErrorNotify(OnPublishErrorNotifyParam onPublishErrorNotifyParam) {
        CommsLogger commsLogger = sLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OnPublishErrorNotify, sipCode:");
        outline107.append(onPublishErrorNotifyParam.getStatusCode());
        outline107.append(" errorString:");
        outline107.append(onPublishErrorNotifyParam.getReason());
        commsLogger.i(outline107.toString());
        this.pjsipSignaling.postPublishError(onPublishErrorNotifyParam.getStatusCode().swigValue(), onPublishErrorNotifyParam.getReason());
    }

    @Override // org.pjsip.pjsua2.Account
    public void onRegState(OnRegStateParam onRegStateParam) {
        if (!this.created.get()) {
            sLog.w("Account is not created, ignoring onRegState");
            return;
        }
        try {
            pjsip_status_code code = onRegStateParam.getCode();
            int expiration = onRegStateParam.getExpiration();
            if (pjsip_status_code.PJSIP_SC_OK.swigValue() == code.swigValue() && expiration > 0) {
                this.state = State.Registered;
                this.pjsipSignaling.postConnected(onRegStateParam.getExpiration());
            } else {
                this.state = State.Unregistered;
                this.pjsipSignaling.postConnectionError(code.swigValue(), onRegStateParam.getReason());
            }
        } catch (Exception e) {
            sLog.e(String.format("Exception getting account info: %s", e.getMessage()), e);
        }
    }

    @Override // org.pjsip.pjsua2.Account
    public void create(AccountConfig accountConfig) throws Exception {
        sLog.i("Creating account");
        super.create(accountConfig);
        this.created.set(getId() != -1);
    }
}
