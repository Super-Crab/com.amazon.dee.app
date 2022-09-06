package com.amazon.comms.ringservice.pjsip;

import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.Signaling;
import com.amazon.comms.ringservice.pjsip.PjsipSignaling;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.wav.WavDirectory;
import com.google.common.base.Strings;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.CallSetting;
import org.pjsip.pjsua2.OnCallAnswerParam;
import org.pjsip.pjsua2.OnCallRemoteUpdateParam;
import org.pjsip.pjsua2.OnCallSdpCreatedParam;
import org.pjsip.pjsua2.OnCallSipInfoParam;
import org.pjsip.pjsua2.OnCallSipInfoResponseParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.OnCallTsxStateParam;
import org.pjsip.pjsua2.SipEvent;
import org.pjsip.pjsua2.SipHeader;
import org.pjsip.pjsua2.SipHeaderVector;
import org.pjsip.pjsua2.SipMsg;
import org.pjsip.pjsua2.SipTxOption;
import org.pjsip.pjsua2.TsxStateEvent;
import org.pjsip.pjsua2.pjsip_event_id_e;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_method_e;
import org.pjsip.pjsua2.pjsip_msg_type_e;
import org.pjsip.pjsua2.pjsip_role_e;
import org.pjsip.pjsua2.pjsua_call_flag;
/* loaded from: classes12.dex */
public class PjsipCall extends Call {
    private static final String APPLICATION_CALLERID_RECEIVED_CTYPE = "application/callerid-received";
    private static final String APPLICATION_DTMF_RELAY_CTYPE = "application/dtmf-relay";
    private static final String APPLICATION_SDP_CTYPE = "application/sdp";
    private static final String APPLICATION_TRICKLE_ICE_CTYPE = "application/trickle-ice-sdpfrag";
    private static final String DTMFUPLMETRIC_SENDDTMFSIPINFORESPONSE = "SendDTMFSIPInfoResponse";
    private static final String SIP_HEADER_REASON_KEY = "Reason";
    private String callId;
    private PjsipSignaling.Channel channel;
    private final EventTracer eventTracer;
    private int latestDtmfMsgCseq;
    private PjsipSignaling pjsipSignaling;
    private String previousProvisionalAnswerSdp;
    private boolean reinviteInProgress;
    private AtomicBoolean reportMetricsAndTraceEventsOnHangup;
    private static final CommsLogger log = CommsLogger.getLogger(PjsipCall.class);
    private static final int DEFAULT_STATUS_CODE = SipStatusCode.OK.getCode();
    private static final Pattern REASON_VALUE_PATTERN = Pattern.compile("[;]?[\\s]*cause=([0-9]*)");

    /* JADX INFO: Access modifiers changed from: package-private */
    public PjsipCall(Account account, String str, EventTracer eventTracer, PjsipSignaling pjsipSignaling) {
        super(account);
        this.latestDtmfMsgCseq = 0;
        this.reinviteInProgress = false;
        this.previousProvisionalAnswerSdp = "";
        this.reportMetricsAndTraceEventsOnHangup = new AtomicBoolean(true);
        this.callId = str;
        this.pjsipSignaling = pjsipSignaling;
        this.eventTracer = eventTracer;
    }

    private static HangupReason convertIntReasonCodeToHangupReason(int i) {
        if (i == SipStatusCode.OK.getCode()) {
            return HangupReason.AnsweredElsewhere;
        }
        if (i == SipStatusCode.DECLINE.getCode()) {
            return HangupReason.RejectedElsewhere;
        }
        return HangupReason.Cancelled;
    }

    private static Matcher getMatchedPortionFromHeader(SipHeaderVector sipHeaderVector, String str, Pattern pattern) {
        String str2;
        if (sipHeaderVector == null || Strings.isNullOrEmpty(str) || pattern == null) {
            return null;
        }
        int i = 0;
        while (true) {
            if (i >= sipHeaderVector.size()) {
                str2 = null;
                break;
            }
            SipHeader sipHeader = sipHeaderVector.get(i);
            if (sipHeader != null && str.equals(sipHeader.getHName())) {
                str2 = sipHeader.getHValue();
                break;
            }
            i++;
        }
        if (str2 != null) {
            return pattern.matcher(str2);
        }
        return null;
    }

    private static HangupReason getReasonCodeForCallCancel(SipHeaderVector sipHeaderVector) {
        Matcher matchedPortionFromHeader = getMatchedPortionFromHeader(sipHeaderVector, SIP_HEADER_REASON_KEY, REASON_VALUE_PATTERN);
        if (matchedPortionFromHeader == null) {
            return HangupReason.Cancelled;
        }
        if (matchedPortionFromHeader.find() && matchedPortionFromHeader.groupCount() < 1) {
            return HangupReason.Cancelled;
        }
        String group = matchedPortionFromHeader.group(1);
        CommsLogger commsLogger = log;
        commsLogger.d("matchedReason string: " + group);
        if (Strings.isNullOrEmpty(group)) {
            return HangupReason.Cancelled;
        }
        try {
            return convertIntReasonCodeToHangupReason(Integer.parseInt(group));
        } catch (NumberFormatException e) {
            log.e("Cancel cause not available. ", e);
            return HangupReason.Cancelled;
        }
    }

    private boolean isUserAgentServer(pjsip_role_e pjsip_role_eVar) {
        return pjsip_role_eVar == pjsip_role_e.PJSIP_ROLE_UAS || pjsip_role_eVar == pjsip_role_e.PJSIP_UAS_ROLE;
    }

    public void close() {
        this.channel.close();
    }

    public void handleSdpUpdateMessage(PjsipSignaling.SdpUpdateMessage sdpUpdateMessage) {
        if (sdpUpdateMessage.getType() == Sdp.Type.OFFER) {
            updateSdp(sdpUpdateMessage.getSdp().toString());
        } else if (this.reinviteInProgress) {
            reinviteResponse(getId(), sdpUpdateMessage.getSdp().toString());
            this.reinviteInProgress = false;
        } else {
            updateResponse(getId(), sdpUpdateMessage.getSdp().toString());
        }
    }

    public void hangup(CallOpParam callOpParam, boolean z) throws Exception {
        this.reportMetricsAndTraceEventsOnHangup.set(z);
        super.hangup(callOpParam);
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallAnswer(OnCallAnswerParam onCallAnswerParam) {
        log.i("PjsipCall onCallAnswer");
        this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.AnswerMessage(PjsipHeaders.transform(onCallAnswerParam.getRdata().getSipMsg().getHeaders()), !Strings.isNullOrEmpty(onCallAnswerParam.getRemSdp().getWholeSdp()) ? new Sdp(onCallAnswerParam.getRemSdp().getWholeSdp()) : null));
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallEarlyMedia(OnCallSdpCreatedParam onCallSdpCreatedParam) {
        log.i("PjsipCall onCallEarlyMedia");
        Sdp sdp = new Sdp(onCallSdpCreatedParam.getRemSdp().getWholeSdp());
        if (sdp.toString().equals(this.previousProvisionalAnswerSdp)) {
            log.w("Not processing SDP since found same cached one");
            return;
        }
        this.previousProvisionalAnswerSdp = sdp.toString();
        this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.EarlyMediaMessage(sdp));
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallRemoteReinvite(OnCallRemoteUpdateParam onCallRemoteUpdateParam) {
        log.i(String.format("PjsipCall onCallRxReInvite ctype=%s", onCallRemoteUpdateParam.getCType()));
        if (APPLICATION_SDP_CTYPE.equals(onCallRemoteUpdateParam.getCType())) {
            this.reinviteInProgress = true;
            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.SdpUpdateMessage(new Sdp(onCallRemoteUpdateParam.getMsgBody()), onCallRemoteUpdateParam.getIsOffer() ? Sdp.Type.OFFER : Sdp.Type.ANSWER, true));
            return;
        }
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected CType passed. Please review: ");
        outline107.append(onCallRemoteUpdateParam.getCType());
        commsLogger.w(outline107.toString());
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallRemoteUpdate(OnCallRemoteUpdateParam onCallRemoteUpdateParam) {
        log.i(String.format("PjsipCall onCallRemoteUpdate ctype=%s", onCallRemoteUpdateParam.getCType()));
        if (APPLICATION_SDP_CTYPE.equals(onCallRemoteUpdateParam.getCType())) {
            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.SdpUpdateMessage(new Sdp(onCallRemoteUpdateParam.getMsgBody()), onCallRemoteUpdateParam.getIsOffer() ? Sdp.Type.OFFER : Sdp.Type.ANSWER, true));
            return;
        }
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected CType passed. Please review: ");
        outline107.append(onCallRemoteUpdateParam.getCType());
        commsLogger.w(outline107.toString());
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallSessionRefresh() {
        log.i(String.format("Received onCallSessionRefresh", new Object[0]));
        this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.SessionRefreshMessage());
    }

    @Override // org.pjsip.pjsua2.Call
    public boolean onCallSipInfo(OnCallSipInfoParam onCallSipInfoParam) {
        String cType = onCallSipInfoParam.getCType();
        String msgBody = onCallSipInfoParam.getMsgBody();
        log.i(String.format("PjsipCall onCallSipInfo ctype=%s", cType));
        if (APPLICATION_CALLERID_RECEIVED_CTYPE.equals(cType)) {
            try {
                JSONObject jSONObject = new JSONObject(msgBody);
                String string = jSONObject.getString("display_name");
                String string2 = jSONObject.getString("caller_id");
                boolean z = jSONObject.getBoolean("second_call");
                if (Strings.isNullOrEmpty(string) || Strings.isNullOrEmpty(string2)) {
                    return false;
                }
                this.eventTracer.mark(EventTracerConfig.Event.Callee_Sip_Info_Received);
                this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.CallerIdInfoMessage(string, string2, z));
                return true;
            } catch (Exception unused) {
                log.e("Error parsing INFO Json object");
                return false;
            }
        } else if (APPLICATION_TRICKLE_ICE_CTYPE.equals(cType)) {
            if (Strings.isNullOrEmpty(onCallSipInfoParam.getMsgBody())) {
                log.w("PjsipCall onCallSipInfo, null sdp");
                return false;
            }
            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.IceCandidateMessage(new Sdp(onCallSipInfoParam.getMsgBody())));
            return true;
        } else {
            CommsLogger commsLogger = log;
            commsLogger.w("PjsipCall onCallSipInfo, unexpected Sip Info CType passed. cType: " + cType);
            return false;
        }
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallSipInfoResponse(OnCallSipInfoResponseParam onCallSipInfoResponseParam) {
        String contentType = onCallSipInfoResponseParam.getContentType();
        CommsLogger commsLogger = log;
        commsLogger.i("PjsipCall onCallSipInfoResponse content type - " + contentType);
        if (APPLICATION_TRICKLE_ICE_CTYPE.equals(contentType)) {
            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.IceCandidateResponseMessage(onCallSipInfoResponseParam.getStatusCode(), onCallSipInfoResponseParam.getReason()));
        } else if (!APPLICATION_DTMF_RELAY_CTYPE.equals(contentType)) {
        } else {
            if (onCallSipInfoResponseParam.getStatusCode() == SipStatusCode.OK.getCode() && onCallSipInfoResponseParam.getCseq() == this.latestDtmfMsgCseq) {
                this.eventTracer.mark(EventTracerConfig.Event.Sip_Info_Response_Received, DTMFUPLMETRIC_SENDDTMFSIPINFORESPONSE);
            }
            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.DtmfResponseMessage(onCallSipInfoResponseParam.getStatusCode(), onCallSipInfoResponseParam.getReason()));
        }
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallState(OnCallStateParam onCallStateParam) {
        log.d("onCallState called");
        try {
            CallInfo info = getInfo();
            pjsip_inv_state state = info.getState();
            pjsip_role_e role = info.getRole();
            if (state == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                if (this.reportMetricsAndTraceEventsOnHangup.get()) {
                    this.eventTracer.mark(EventTracerConfig.Event.Call_Disconnected);
                }
                this.channel.close();
                this.pjsipSignaling.removeCall(this);
            } else if (state == pjsip_inv_state.PJSIP_INV_STATE_CALLING) {
                this.pjsipSignaling.addCall(this);
                this.eventTracer.mark(EventTracerConfig.Event.Caller_INVITE_Sent);
            } else if (state == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                this.eventTracer.mark(EventTracerConfig.Event.Call_Confirmed);
            } else if (state != pjsip_inv_state.PJSIP_INV_STATE_CONNECTING) {
            } else {
                if (isUserAgentServer(role)) {
                    this.eventTracer.mark(EventTracerConfig.Event.Callee_Send_200OK);
                } else {
                    this.eventTracer.mark(EventTracerConfig.Event.Caller_Received_200OK);
                }
            }
        } catch (Exception e) {
            log.d(String.format("Exception handling onCallState: %s", e.getMessage()));
        }
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallTsxState(OnCallTsxStateParam onCallTsxStateParam) {
        String str;
        HangupReason hangupReason;
        SipEvent e = onCallTsxStateParam.getE();
        if (e.getType() == pjsip_event_id_e.PJSIP_EVENT_TSX_STATE) {
            TsxStateEvent tsxState = e.getBody().getTsxState();
            if (tsxState.getType() == pjsip_event_id_e.PJSIP_EVENT_TX_MSG) {
                SipMsg sipMsg = tsxState.getSrc().getTdata().getSipMsg();
                if (sipMsg.getType() == pjsip_msg_type_e.PJSIP_REQUEST_MSG) {
                    pjsip_method_e method = sipMsg.getRequest().getMethod();
                    String name = sipMsg.getRequest().getName();
                    if (method == pjsip_method_e.PJSIP_OTHER_METHOD && name.equals(WavDirectory.LIST_INFO) && APPLICATION_DTMF_RELAY_CTYPE.equals(sipMsg.getContentType())) {
                        this.latestDtmfMsgCseq = tsxState.getTsx().getCseq();
                    }
                }
            }
            if (tsxState.getType() == pjsip_event_id_e.PJSIP_EVENT_RX_MSG) {
                SipMsg sipMsg2 = tsxState.getSrc().getRdata().getSipMsg();
                pjsip_msg_type_e type = sipMsg2.getType();
                int i = DEFAULT_STATUS_CODE;
                pjsip_method_e pjsip_method_eVar = pjsip_method_e.PJSIP_OTHER_METHOD;
                String str2 = "";
                if (type == pjsip_msg_type_e.PJSIP_RESPONSE_MSG) {
                    i = sipMsg2.getStatus().getCode();
                    str2 = sipMsg2.getStatus().getReason();
                    str = tsxState.getSrc().getRdata().getInfo();
                } else {
                    if (type == pjsip_msg_type_e.PJSIP_REQUEST_MSG) {
                        pjsip_method_eVar = sipMsg2.getRequest().getMethod();
                    }
                    str = str2;
                }
                boolean z = false;
                log.i(String.format(Locale.US, "onCallTsxState: %s: %d (%s), %s", type.toString(), Integer.valueOf(i), str2, pjsip_method_eVar.toString()));
                if (pjsip_method_eVar != pjsip_method_e.PJSIP_BYE_METHOD && pjsip_method_eVar != pjsip_method_e.PJSIP_CANCEL_METHOD && (!str.contains("INVITE") || (i != SipStatusCode.DECLINE.getCode() && i != SipStatusCode.BUSY_HERE.getCode() && i != SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()))) {
                    int i2 = i / 100;
                    if (i2 != 4) {
                        if (i2 == 5) {
                            this.channel.close();
                            this.pjsipSignaling.postMessageError(this.channel, Signaling.MessageError.SEND_ERROR, new PjsipSignaling.MessageErrorInfo(this.callId, ErrorCode.ServerError, i, str2));
                            return;
                        } else if (i != SipStatusCode.RINGING.getCode()) {
                            return;
                        } else {
                            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.RingingMessage());
                            this.eventTracer.mark(EventTracerConfig.Event.Caller_Received_180Ringing);
                            return;
                        }
                    } else if (str.contains("UPDATE")) {
                        if (SipStatusCode.CSMS_TOKEN_REFRESH_ERROR.getCode() == i) {
                            this.channel.close();
                            this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.HangupMessage(HangupReason.InvalidCSMSToken));
                            return;
                        }
                        PjsipSignaling pjsipSignaling = this.pjsipSignaling;
                        PjsipSignaling.Channel channel = this.channel;
                        Sdp.Type type2 = Sdp.Type.ANSWER;
                        if (i == SipStatusCode.REQUEST_PENDING.getCode()) {
                            z = true;
                        }
                        pjsipSignaling.postRecvMessage(channel, new PjsipSignaling.SdpUpdateMessage(null, type2, z));
                        return;
                    } else if (i == SipStatusCode.NOT_FOUND.getCode()) {
                        this.channel.close();
                        this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.HangupMessage(HangupReason.NotProvisioned));
                        return;
                    } else if (i == SipStatusCode.CALL_TSX_DOES_NOT_EXIST.getCode() && str.contains("BYE")) {
                        log.i("BYE on old call");
                        return;
                    } else {
                        this.channel.close();
                        this.pjsipSignaling.postMessageError(this.channel, Signaling.MessageError.SEND_ERROR, new PjsipSignaling.MessageErrorInfo(this.callId, ErrorCode.ClientFailed, i, str2));
                        return;
                    }
                }
                CommsLogger commsLogger = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notifying call listeners of hangup: ");
                outline107.append(log.sensitiveCallId(this.callId));
                commsLogger.i(outline107.toString());
                this.channel.close();
                if (pjsip_method_eVar == pjsip_method_e.PJSIP_CANCEL_METHOD) {
                    hangupReason = getReasonCodeForCallCancel(sipMsg2.getHeaders());
                } else if (i == SipStatusCode.DECLINE.getCode()) {
                    hangupReason = HangupReason.Rejected;
                } else if (i == SipStatusCode.BUSY_HERE.getCode()) {
                    hangupReason = HangupReason.Busy;
                } else if (i == SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()) {
                    hangupReason = HangupReason.Offline;
                } else {
                    hangupReason = HangupReason.RemoteHangup;
                }
                this.pjsipSignaling.postRecvMessage(this.channel, new PjsipSignaling.HangupMessage(hangupReason));
            } else if (tsxState.getType() != pjsip_event_id_e.PJSIP_EVENT_TRANSPORT_ERROR) {
            } else {
                PjsipSignaling pjsipSignaling2 = this.pjsipSignaling;
                PjsipSignaling.Channel channel2 = this.channel;
                Signaling.MessageError messageError = Signaling.MessageError.SEND_ERROR;
                String str3 = this.callId;
                ErrorCode errorCode = ErrorCode.SIPInternalError;
                pjsipSignaling2.postMessageError(channel2, messageError, new PjsipSignaling.MessageErrorInfo(str3, errorCode, errorCode.getValue(), "Transport Error"));
            }
        }
    }

    public void setChannel(PjsipSignaling.Channel channel) {
        this.channel = channel;
    }

    public void updateSdp(String str) {
        log.d("PjsipCall updateSdp sending SDP UPDATE");
        SipTxOption sipTxOption = new SipTxOption();
        sipTxOption.setContentType(APPLICATION_SDP_CTYPE);
        sipTxOption.setMsgBody(str);
        CallOpParam callOpParam = new CallOpParam();
        try {
            CallSetting setting = getInfo().getSetting();
            setting.setFlag(setting.getFlag() | pjsua_call_flag.PJSUA_CALL_NO_SDP_OFFER.swigValue());
            callOpParam.setOpt(setting);
            callOpParam.setTxOption(sipTxOption);
            update(callOpParam);
        } catch (Exception e) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateSdp Exception getting call info or updating call: ");
            outline107.append(e.getMessage());
            commsLogger.e(outline107.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PjsipCall(Account account, String str, int i, EventTracer eventTracer, PjsipSignaling pjsipSignaling) {
        super(account, i);
        this.latestDtmfMsgCseq = 0;
        this.reinviteInProgress = false;
        this.previousProvisionalAnswerSdp = "";
        this.reportMetricsAndTraceEventsOnHangup = new AtomicBoolean(true);
        this.callId = str;
        this.pjsipSignaling = pjsipSignaling;
        this.eventTracer = eventTracer;
    }
}
