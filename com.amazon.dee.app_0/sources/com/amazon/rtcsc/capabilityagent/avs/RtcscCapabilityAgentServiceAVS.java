package com.amazon.rtcsc.capabilityagent.avs;

import android.content.Intent;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscCapabilityAgent;
import com.amazon.rtcsc.capabilityagent.common.dependencies.RtcscDaggerWrapper;
import com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes13.dex */
public class RtcscCapabilityAgentServiceAVS extends AlexaCapabilityAgentService implements AlexaServicesConnection.ConnectionListener {
    private static final String TAG = "RtcscCapAgentSvcAVS";
    @Inject
    RtcscCapabilityAgent mRtcscCapabilityAgent;
    @Inject
    RtcscContextProvider mRtcscContextProvider;
    @Inject
    WrappedAlexaConnection mWrappedAlexaConnection;

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        return this.mRtcscCapabilityAgent.getRtcscCapabilities();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        RtcscLogger.i(TAG, "onConnected -- ", new Object[0]);
        this.mWrappedAlexaConnection.setContextCachingEnabled(true);
        this.mWrappedAlexaConnection.registerContextsProvider(this.mRtcscContextProvider);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        RtcscLogger.w(TAG, "onConnectingFailed due to: " + alexaConnectingFailedReason + " : " + str, new Object[0]);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        RtcscLogger.i(TAG, "onCreate -- ", new Object[0]);
        super.onCreate();
        RtcscDaggerWrapper.initialize(this);
        RtcscLogger.d(TAG, "onCreate -- Dagger wrapper initialized", new Object[0]);
        RtcscDaggerWrapper.getComponent().inject(this);
        this.mWrappedAlexaConnection.registerConnectionListener(this);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onDestroy() {
        RtcscLogger.i(TAG, "onDestroy -- ", new Object[0]);
        this.mRtcscCapabilityAgent.unregisterEventListener();
        this.mWrappedAlexaConnection.disconnect();
        this.mWrappedAlexaConnection.deregisterConnectionListener(this);
        this.mWrappedAlexaConnection.teardown();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        RtcscLogger.i(TAG, "onDisconnected -- ", new Object[0]);
        this.mWrappedAlexaConnection.deregisterContextsProvider(this.mRtcscContextProvider);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        RtcscLogger.i(TAG, "onStartCommand -- ", new Object[0]);
        if (intent != null && intent.getAction() != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onStartCommand. Ignoring Intent Action: ");
            outline107.append(intent.getAction());
            RtcscLogger.d(TAG, outline107.toString(), new Object[0]);
            return 2;
        }
        RtcscLogger.w(TAG, "Intent or action is null.", new Object[0]);
        return 2;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(AlexaDirective alexaDirective) {
        return this.mRtcscCapabilityAgent.processRTCDirective(alexaDirective);
    }
}
