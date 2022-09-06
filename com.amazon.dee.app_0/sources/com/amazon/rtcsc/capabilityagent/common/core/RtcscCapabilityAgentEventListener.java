package com.amazon.rtcsc.capabilityagent.common.core;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.rtcsc.capabilityagent.avs.RtcscCapabilityAgentServiceAVS;
import com.amazon.rtcsc.capabilityagent.avs.RtcscContextProvider;
import com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection;
import com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger;
import com.amazon.rtcsc.capabilityagentclient.RtcscCAEventListener;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes13.dex */
public class RtcscCapabilityAgentEventListener extends RtcscCAEventListener {
    private static final String TAG = "RtcscCpAgntEventListener";
    private final Context mContext;
    private final RtcscContextProvider mRtcscContextProvider;
    private ConcurrentHashMap<String, String> mSessionIdToCorrelationMap;
    private final WrappedAlexaConnection mWrappedAlexaConnection;

    @Inject
    public RtcscCapabilityAgentEventListener(Context context, WrappedAlexaConnection wrappedAlexaConnection, RtcscContextProvider rtcscContextProvider) {
        RtcscLogger.i(TAG, "Constructor -- RtcscCapabilityAgentEventListener", new Object[0]);
        this.mContext = context;
        this.mWrappedAlexaConnection = wrappedAlexaConnection;
        this.mRtcscContextProvider = rtcscContextProvider;
        this.mSessionIdToCorrelationMap = new ConcurrentHashMap<>();
    }

    private void stopCapabilityAgentService() {
        RtcscLogger.i(TAG, "Stopping AVS CapabilityAgent service", new Object[0]);
        this.mContext.stopService(new Intent().setClass(this.mContext, RtcscCapabilityAgentServiceAVS.class));
    }

    public void addCorrelationToken(String str, String str2) {
        RtcscLogger.i(TAG, GeneratedOutlineSupport1.outline72("addCorrelationToken. sessionId: ", str), new Object[0]);
        RtcscLogger.d(TAG, "correlationToken: " + str2, new Object[0]);
        if (str2 != null) {
            this.mSessionIdToCorrelationMap.put(str, str2);
        }
    }

    @VisibleForTesting
    String getCorrelationToken(String str) {
        return this.mSessionIdToCorrelationMap.get(str);
    }

    @VisibleForTesting
    String getValue(String str) {
        String str2 = this.mSessionIdToCorrelationMap.get(str);
        return str2 == null ? "" : str2;
    }

    @VisibleForTesting
    boolean hasKey(String str) {
        return this.mSessionIdToCorrelationMap.get(str) != null;
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAEventListener, com.amazon.rtcsc.interfaces.IRtcscEventListener
    public void onError(RtcscErrorCode rtcscErrorCode) {
        RtcscLogger.i(TAG, "Received onError from RtcscService. errorCode : " + rtcscErrorCode, new Object[0]);
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAEventListener, com.amazon.rtcsc.interfaces.IRtcscEventListener
    public void onRTCSessionContextUpdated(String str) {
        RtcscLogger.i(TAG, GeneratedOutlineSupport1.outline72("Updated Session Context Received from RtcscService. Context: ", str), new Object[0]);
        this.mRtcscContextProvider.setRtcscSessionContext(str);
    }

    @Override // com.amazon.rtcsc.capabilityagentclient.RtcscCAEventListener, com.amazon.rtcsc.interfaces.IRtcscEventListener
    public void onSendEvent(String str, String str2, String str3, String str4) {
        RtcscLogger.i(TAG, String.format(Locale.US, "Received event from RtcscService. sessionId: %s. eventName: %s, sessionContext: %s", str, str2, str4), new Object[0]);
        RtcscLogger.d(TAG, "Payload: %s", str3);
        String correlationToken = getCorrelationToken(str);
        if (correlationToken != null) {
            RtcscLogger.d(TAG, GeneratedOutlineSupport1.outline76("Using correlationToken: ", correlationToken, " for event : ", str2), new Object[0]);
            this.mWrappedAlexaConnection.sendEvent(new AlexaEvent(AlexaHeader.builder().setNamespace(RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_NAME).setName(str2).setCorrelationToken(correlationToken).setPayloadVersion(RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_VERSION).build(), new AlexaPayload(str3)));
        } else {
            RtcscLogger.w(TAG, "correlationToken not present. Not forwarding event to cloud", new Object[0]);
        }
        removeCorrelationTokenOnSessionEnd(str, str2);
    }

    public void removeCorrelationTokenOnSessionEnd(String str, String str2) {
        char c;
        int hashCode = str2.hashCode();
        if (hashCode == -896787503) {
            if (str2.equals("SessionDisconnected")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != -34936774) {
            if (hashCode == 1331764662 && str2.equals(RtcscConstants.RTCSC_EVENT_NAME_INITIATE_SESSION_WITH_OFFER_FAILED)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str2.equals("InitiateSessionFailed")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0 && c != 1 && c != 2) {
            RtcscLogger.i(TAG, "Not an event that disconnects session. Not removing mapping", new Object[0]);
            return;
        }
        this.mSessionIdToCorrelationMap.remove(str);
        if (this.mSessionIdToCorrelationMap.isEmpty()) {
            stopCapabilityAgentService();
        }
        RtcscLogger.i(TAG, GeneratedOutlineSupport1.outline76("Removed mapping for sessionId: ", str, " eventName: ", str2), new Object[0]);
    }
}
