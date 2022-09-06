package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.calling.sipclient.TurnEndPointInfo;
import com.amazon.comms.ringservice.util.SipUri;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class AmazonCallInfo {
    private static final String DEFAULT_PROVIDER = "A2A";
    private static final Pattern GRUU_PATTERN = Pattern.compile("temp-gruu=\"([^\"]*)");
    private final String authToken;
    private final Call.Side callOrigin;
    private final boolean checkIn;
    private final boolean dropIn;
    private final Map<String, String> extraHeaders;
    private final SipHeaders incomingHeaders;
    private final ParticipantImpl localParticipant;
    private final MediaRelayInfo mediaRelayInfo;
    private Call.Side origin;
    private final SipHeaders outgoingHeaders;
    private final String provider;
    private SipUri remoteGruu;
    private final ParticipantImpl remoteParticipant;
    private String sipCallId;

    private AmazonCallInfo(String str, ParticipantImpl participantImpl, ParticipantImpl participantImpl2, MediaRelayInfo mediaRelayInfo, String str2, boolean z, boolean z2, Call.Side side, SipUri sipUri, String str3, SipHeaders sipHeaders, SipHeaders sipHeaders2, Map<String, String> map, Call.Side side2) {
        this.provider = str;
        this.localParticipant = participantImpl;
        this.remoteParticipant = participantImpl2;
        this.mediaRelayInfo = mediaRelayInfo;
        this.authToken = str2;
        this.dropIn = z;
        this.checkIn = z2;
        this.origin = side;
        this.remoteGruu = sipUri;
        this.sipCallId = str3;
        this.incomingHeaders = sipHeaders;
        this.outgoingHeaders = sipHeaders2;
        this.extraHeaders = map;
        this.callOrigin = side2;
    }

    public static AmazonCallInfo createIncoming(SipHeaders sipHeaders, ParticipantImpl participantImpl, ParticipantImpl participantImpl2) {
        return createIncoming(sipHeaders, participantImpl, participantImpl2, Call.Side.Remote);
    }

    public static AmazonCallInfo createOutgoing(String str, String str2, ParticipantImpl participantImpl, ParticipantImpl participantImpl2, MediaRelayInfo mediaRelayInfo, String str3, boolean z, Map<String, String> map) {
        return createOutgoing(str, str2, participantImpl, participantImpl2, mediaRelayInfo, str3, z, map, Call.Side.Local);
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public Call.Side getCallOrigin() {
        return this.callOrigin;
    }

    public ParticipantImpl getCalleeBasedOnCallOrigin() {
        return this.callOrigin == Call.Side.Local ? this.remoteParticipant : this.localParticipant;
    }

    public ParticipantImpl getCallerBasedOnCallOrigin() {
        return this.callOrigin == Call.Side.Local ? this.localParticipant : this.remoteParticipant;
    }

    public Map<String, String> getExtraHeaders() {
        return this.extraHeaders;
    }

    public SipHeaders getIncomingHeaders() {
        return this.incomingHeaders;
    }

    public ParticipantImpl getLocalParticipant() {
        return this.localParticipant;
    }

    public MediaRelayInfo getMediaRelayInfo() {
        return this.mediaRelayInfo;
    }

    public Call.Side getOrigin() {
        return this.origin;
    }

    public SipHeaders getOutgoingHeaders() {
        return this.outgoingHeaders;
    }

    public String getProvider() {
        return this.provider;
    }

    public SipUri getRemoteGruu() {
        return this.remoteGruu;
    }

    public ParticipantImpl getRemoteParticipant() {
        return this.remoteParticipant;
    }

    public String getSipCallId() {
        return this.sipCallId;
    }

    public boolean isCheckIn() {
        return this.checkIn;
    }

    public boolean isDropIn() {
        return this.dropIn;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOrigin(Call.Side side) {
        this.origin = side;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setRemoteGruu(SipUri sipUri) {
        this.remoteGruu = sipUri;
    }

    public void updateOnAnswer(SipHeaders sipHeaders) {
        String str = sipHeaders.get(SipHeaders.SIP_HEADER_CONTACT, "");
        this.sipCallId = sipHeaders.get(SipHeaders.SIP_HEADER_CALL_ID, "");
        Matcher matcher = GRUU_PATTERN.matcher(str);
        if (matcher.find()) {
            try {
                this.remoteGruu = new SipUri(matcher.group(1));
            } catch (Exception unused) {
                this.remoteGruu = null;
            }
        }
        this.incomingHeaders.putAll(sipHeaders);
    }

    public static AmazonCallInfo createIncoming(SipHeaders sipHeaders, ParticipantImpl participantImpl, ParticipantImpl participantImpl2, Call.Side side) {
        SipUri sipUri;
        String str = sipHeaders.get(SipHeaders.SIP_HEADER_TURN_URL, "");
        String str2 = sipHeaders.get(SipHeaders.SIP_HEADER_TURN_USERNAME, "");
        String str3 = sipHeaders.get(SipHeaders.SIP_HEADER_TURN_CREDENTIAL_OLD, sipHeaders.get(SipHeaders.SIP_HEADER_TURN_CREDENTIAL, ""));
        String str4 = sipHeaders.get(SipHeaders.SIP_HEADER_CALL_ID, "");
        boolean equalsIgnoreCase = sipHeaders.get(SipHeaders.SIP_HEADER_FOR_DROP_IN, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE).equalsIgnoreCase("true");
        String str5 = sipHeaders.get(SipHeaders.SIP_HEADER_CALL_PROVIDER, "A2A");
        String str6 = sipHeaders.get(SipHeaders.SIP_HEADER_CONTACT, "");
        boolean equalsIgnoreCase2 = sipHeaders.get(SipHeaders.SIP_HEADER_FOR_CHECK_IN, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE).equalsIgnoreCase("true");
        Matcher matcher = GRUU_PATTERN.matcher(str6);
        if (matcher.find()) {
            try {
                sipUri = new SipUri(matcher.group(1));
            } catch (Exception unused) {
            }
            return new AmazonCallInfo(str5, participantImpl, participantImpl2, MediaRelayInfo.builder().caller(TurnEndPointInfo.builder().url("").username("").credential("").build()).callee(TurnEndPointInfo.builder().url(str).username(str2).credential(str3).build()).build(), null, equalsIgnoreCase, equalsIgnoreCase2, Call.Side.Remote, sipUri, str4, sipHeaders, new SipHeaders(), null, side);
        }
        sipUri = null;
        return new AmazonCallInfo(str5, participantImpl, participantImpl2, MediaRelayInfo.builder().caller(TurnEndPointInfo.builder().url("").username("").credential("").build()).callee(TurnEndPointInfo.builder().url(str).username(str2).credential(str3).build()).build(), null, equalsIgnoreCase, equalsIgnoreCase2, Call.Side.Remote, sipUri, str4, sipHeaders, new SipHeaders(), null, side);
    }

    public static AmazonCallInfo createOutgoing(String str, String str2, ParticipantImpl participantImpl, ParticipantImpl participantImpl2, MediaRelayInfo mediaRelayInfo, String str3, boolean z, Map<String, String> map, Call.Side side) {
        SipHeaders sipHeaders = new SipHeaders();
        sipHeaders.put(SipHeaders.SIP_HEADER_ALEXA_CALL_ID, str2);
        sipHeaders.put(SipHeaders.SIP_HEADER_TURN_URL, mediaRelayInfo.getCallee().getUrl());
        sipHeaders.put(SipHeaders.SIP_HEADER_TURN_USERNAME, mediaRelayInfo.getCallee().getUsername());
        sipHeaders.put(SipHeaders.SIP_HEADER_TURN_CREDENTIAL_OLD, mediaRelayInfo.getCallee().getCredential());
        sipHeaders.put(SipHeaders.SIP_HEADER_TURN_CREDENTIAL, mediaRelayInfo.getCallee().getCredential());
        sipHeaders.put(SipHeaders.SIP_HEADER_ALEXA_CALLEE_ID, participantImpl2.getProviderSpecifiedId());
        sipHeaders.put(SipHeaders.SIP_HEADER_ALEXA_CALLER_ID, participantImpl.getProviderSpecifiedId());
        sipHeaders.put(SipHeaders.SIP_HEADER_FOR_DROP_IN, String.valueOf(z));
        sipHeaders.put(SipHeaders.SIP_HEADER_AUTH_TOKEN, str3);
        if (map != null) {
            sipHeaders.putAll(map);
        }
        return new AmazonCallInfo(str != null ? str : "A2A", participantImpl, participantImpl2, mediaRelayInfo, str3, z, false, Call.Side.Local, null, null, new SipHeaders(), sipHeaders, map, side);
    }
}
