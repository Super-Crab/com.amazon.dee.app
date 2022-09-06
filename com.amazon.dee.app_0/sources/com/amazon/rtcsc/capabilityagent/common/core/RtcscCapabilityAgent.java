package com.amazon.rtcsc.capabilityagent.common.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger;
import com.amazon.rtcsc.capabilityagentclient.RtcscCAClient;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes13.dex */
public class RtcscCapabilityAgent extends RtcscCAClient {
    private static final String TAG = "RtcscCapabilityAgent";
    private Context mContext;
    private RtcscCapabilityAgentEventListener mEventListener;
    private RtcscAppInfo mRtcAppInfo;

    @Inject
    public RtcscCapabilityAgent(Context context, RtcscCapabilityAgentEventListener rtcscCapabilityAgentEventListener) {
        super(context);
        this.mRtcAppInfo = new RtcscAppInfo(RtcscConstants.RTCSC_CAPABILITY_AGENT_APP_IDENTIFIER);
        RtcscLogger.i(TAG, "Constructor -- RtcscCapabilityAgent", new Object[0]);
        this.mContext = context;
        this.mEventListener = rtcscCapabilityAgentEventListener;
    }

    private void registerEventListenerIfSessionStart(String str) {
        RtcscLogger.i(TAG, "registerListenerIfSessionStart", new Object[0]);
        if (str.equals("InitiateSession") || str.equals(RtcscConstants.RTCSC_DIRECTIVE_NAME_INITIATE_SESSION_WITH_OFFER)) {
            RtcscLogger.i(TAG, "Session initiating directive received. Registering RtcscCapabilityAgentEventListener.", new Object[0]);
            super.registerCAEventListener(this.mRtcAppInfo, this.mEventListener);
        }
    }

    public void disconnectFromService() {
        RtcscLogger.i(TAG, "disconnectFromService", new Object[0]);
        super.disconnectFromRtcscService();
    }

    public Set<AlexaCapability> getRtcscCapabilities() {
        RtcscLogger.i(TAG, "getRtcscCapabilities ", new Object[0]);
        HashSet hashSet = new HashSet();
        hashSet.add(AlexaCapability.create(RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_NAME, RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_VERSION));
        RtcscLogger.i(TAG, "returning capability set of count %d", Integer.valueOf(hashSet.size()));
        return hashSet;
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAClient
    protected void onRtcscServiceConnected() {
        RtcscLogger.i(TAG, "onRtcscServiceConnected received.", new Object[0]);
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAClient
    protected void onRtcscServiceDisconnected() {
        RtcscLogger.i(TAG, "onRtcscServiceDisconnected received.", new Object[0]);
    }

    public boolean processRTCDirective(@NonNull AlexaDirective alexaDirective) {
        RtcscLogger.i(TAG, "processRTCDirective -- Process directives from Alexa", new Object[0]);
        if (alexaDirective.getAlexaPayload() == null) {
            RtcscLogger.e(TAG, "processRTCDirective -- Alexa Payload is null. Cannot proceed", new Object[0]);
            return false;
        } else if (alexaDirective.getAlexaHeader() == null) {
            RtcscLogger.e(TAG, "processRTCDirective -- Alexa Header is null. Cannot proceed", new Object[0]);
            return false;
        } else {
            String namespace = alexaDirective.getNamespace();
            String name = alexaDirective.getName();
            String payload = alexaDirective.getAlexaPayload().getPayload();
            String correlationToken = alexaDirective.getAlexaHeader().getCorrelationToken();
            RtcscLogger.i(TAG, "processRTCDirective: Namespace : %s, Name : %s.", namespace, name);
            RtcscLogger.d(TAG, "processRTCDirective: Directive Payload: %s", payload);
            if (!RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_NAME.equals(namespace)) {
                RtcscLogger.w(TAG, "processRTCDirective -- Not an Rtcsc namespace directive. Can't handle it", new Object[0]);
                return false;
            }
            RtcscLogger.i(TAG, GeneratedOutlineSupport1.outline72("processRTCDirective -- Received directive: ", name), new Object[0]);
            registerEventListenerIfSessionStart(name);
            super.connectToRtcscService();
            char c = 65535;
            switch (name.hashCode()) {
                case -1558326883:
                    if (name.equals("InitiateSession")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1461238880:
                    if (name.equals(RtcscConstants.RTCSC_DIRECTIVE_NAME_GENERATE_ANSWER_FOR_SESSION)) {
                        c = 2;
                        break;
                    }
                    break;
                case -912589118:
                    if (name.equals("StartPeerConnection")) {
                        c = 3;
                        break;
                    }
                    break;
                case -896787503:
                    if (name.equals("SessionDisconnected")) {
                        c = 6;
                        break;
                    }
                    break;
                case -21734854:
                    if (name.equals("DisconnectSession")) {
                        c = 5;
                        break;
                    }
                    break;
                case 14210073:
                    if (name.equals(RtcscConstants.RTCSC_DIRECTIVE_NAME_INITIATE_SESSION_WITH_OFFER)) {
                        c = 1;
                        break;
                    }
                    break;
                case 886725939:
                    if (name.equals("SessionConnected")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    RtcscLogger.i(TAG, GeneratedOutlineSupport1.outline72("processRTCDirective -- handle directive ", name), new Object[0]);
                    this.mEventListener.addCorrelationToken(new PayloadObject(payload).getSessionId(), correlationToken);
                    RtcscErrorCode handleDirective = super.handleDirective(name, payload);
                    if (handleDirective != RtcscErrorCode.SUCCESS) {
                        RtcscLogger.e(TAG, "processRTCDirective -- Error handling RTCDirective: " + handleDirective, new Object[0]);
                    } else {
                        RtcscLogger.i(TAG, "processRTCDirective -- Directive successfully sent to Capability Agent.", new Object[0]);
                    }
                    return handleDirective == RtcscErrorCode.SUCCESS;
                default:
                    RtcscLogger.e(TAG, "processRTCDirective -- Not a valid RTCSC Directive. Can't handle it.", new Object[0]);
                    return false;
            }
        }
    }

    public void unregisterEventListener() {
        RtcscLogger.i(TAG, "unregisterEventListener", new Object[0]);
        super.unregisterCAEventListener(this.mRtcAppInfo);
    }
}
