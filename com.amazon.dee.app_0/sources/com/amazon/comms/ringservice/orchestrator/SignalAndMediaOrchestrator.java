package com.amazon.comms.ringservice.orchestrator;

import android.content.Context;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HistoricalCall;
import com.amazon.comms.calling.sipclient.AuthenticationInfo;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.ringservice.CallImpl;
import com.amazon.comms.ringservice.MetricsSession;
import com.amazon.comms.ringservice.ParticipantImpl;
import com.amazon.comms.ringservice.util.Capability;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public interface SignalAndMediaOrchestrator {

    /* loaded from: classes12.dex */
    public interface Callbacks {
        void onAuthTokenExpiry(long j);

        void onCallAdded(Call call);

        void onCallConfigure(CallImpl callImpl);

        void onCallRemoved(Call call);

        void onSignalOrMediaError(String str, ErrorCode errorCode, int i, String str2);

        void onStateReport(DeviceCallingService.State state);
    }

    /* loaded from: classes12.dex */
    public enum RegistrationRequestStatus {
        None,
        InProgress,
        Succeeded,
        Failed
    }

    void beginOutgoingCall(ParticipantImpl participantImpl, ParticipantImpl participantImpl2, DeviceCallingService.OutgoingCallParams outgoingCallParams, EventTracer eventTracer);

    void configurePresence(boolean z);

    void configureRegistrar(RegistrarConfiguration registrarConfiguration, long j);

    CallImpl getActiveCall();

    Call getCallByCallId(String str);

    List<Call> getCalls(Predicate<Call> predicate);

    HistoricalCall getHistoricalCallByCallId(String str);

    Iterable<HistoricalCall> getHistoricalCalls();

    Call getMostRecentCall();

    DeviceCallingService.State getOrchestratorState();

    void hangupAll();

    void notifyOfCallRemovedReceived(Call call);

    void performInitialization(Context context, EventTracerFactory eventTracerFactory, DeviceCallingServiceParams deviceCallingServiceParams, MetricsSession metricsSession, boolean z, String str, boolean z2);

    void reconnectCall(AuthenticationInfo authenticationInfo, MediaRelayInfo mediaRelayInfo);

    void setAllCallVolume(float f);

    void setMediaConstraints(Map<String, Integer> map);

    void setSystemCameraState(boolean z);

    void setSystemMediaState(boolean z);

    void shutdownInstance(boolean z);

    void updateAudioBitrateForCalls(int i);

    void updateAuthToken(AuthTokenConfig authTokenConfig, long j);

    void updateConnectivityState(boolean z);

    void updateDeviceCapabilities(List<Capability> list);

    void updatePresenceInfo(String str);

    void warmupMediaPipeline(int i);
}
