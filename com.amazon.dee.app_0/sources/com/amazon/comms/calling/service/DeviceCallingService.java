package com.amazon.comms.calling.service;

import android.content.Context;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.AuthenticationInfo;
import com.amazon.comms.calling.sipclient.MediaRelayInfo;
import com.amazon.comms.calling.sipclient.RegistrarConfiguration;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public interface DeviceCallingService {

    /* loaded from: classes11.dex */
    public static class OutgoingCallParams {
        private final AuthenticationInfo authInfo;
        private final BundlePolicy bundlePolicy;
        private final String callId;
        private final boolean callReconnectInitiation;
        private final String calleeCommsId;
        private final String calleeEndpointDescription;
        private final String calleeName;
        private final String calleeUri;
        private final String callerCommsId;
        private final String callerEndpointDescription;
        private final String callerName;
        private final String callerUri;
        private List<DataChannelConfiguration> dataChannelParams;
        private final boolean dropIn;
        private final boolean dropInPermissionGrantedForCaller;
        private final DynamicAcousticParams dynamicAcousticParams;
        private final Map<String, String> headers;
        private final String join;
        private final MediaRelayInfo mediaRelayInfo;
        private final String provider;
        private final RealTimeText realTimeText;
        private final RtcpMuxPolicy rtcpMuxPolicy;
        private final String srtpKeying;
        private final Map<Call.State, TimeoutRule> stateTransitionTimeouts;
        private final boolean trickleIceEnabled;
        private final Call.VideoEffect videoEffect;
        private final boolean videoEnabled;

        /* loaded from: classes11.dex */
        public static class OutgoingCallParamsBuilder {
            private AuthenticationInfo authInfo;
            private BundlePolicy bundlePolicy;
            private String callId;
            private boolean callReconnectInitiation;
            private boolean callReconnectInitiation$set;
            private String calleeCommsId;
            private String calleeEndpointDescription;
            private String calleeName;
            private String calleeUri;
            private String callerCommsId;
            private String callerEndpointDescription;
            private String callerName;
            private String callerUri;
            private List<DataChannelConfiguration> dataChannelParams;
            private boolean dropIn;
            private boolean dropInPermissionGrantedForCaller;
            private DynamicAcousticParams dynamicAcousticParams;
            private Map<String, String> headers;
            private String join;
            private MediaRelayInfo mediaRelayInfo;
            private String provider;
            private RealTimeText realTimeText;
            private RtcpMuxPolicy rtcpMuxPolicy;
            private String srtpKeying;
            private Map<Call.State, TimeoutRule> stateTransitionTimeouts;
            private boolean trickleIceEnabled;
            private boolean trickleIceEnabled$set;
            private Call.VideoEffect videoEffect;
            private boolean videoEnabled;

            OutgoingCallParamsBuilder() {
            }

            public OutgoingCallParamsBuilder authInfo(AuthenticationInfo authenticationInfo) {
                this.authInfo = authenticationInfo;
                return this;
            }

            public OutgoingCallParams build() {
                return new OutgoingCallParams(this.provider, this.callId, this.authInfo, this.mediaRelayInfo, this.dropIn, this.videoEffect, this.videoEnabled, this.calleeName, this.calleeEndpointDescription, this.calleeCommsId, this.calleeUri, this.callerName, this.callerEndpointDescription, this.callerCommsId, this.callerUri, this.dropInPermissionGrantedForCaller, this.join, this.headers, this.srtpKeying, this.stateTransitionTimeouts, this.bundlePolicy, this.dynamicAcousticParams, this.realTimeText, this.dataChannelParams, this.callReconnectInitiation$set ? this.callReconnectInitiation : OutgoingCallParams.$default$callReconnectInitiation(), this.trickleIceEnabled$set ? this.trickleIceEnabled : OutgoingCallParams.$default$trickleIceEnabled(), this.rtcpMuxPolicy);
            }

            public OutgoingCallParamsBuilder bundlePolicy(BundlePolicy bundlePolicy) {
                this.bundlePolicy = bundlePolicy;
                return this;
            }

            public OutgoingCallParamsBuilder callId(String str) {
                this.callId = str;
                return this;
            }

            public OutgoingCallParamsBuilder callReconnectInitiation(boolean z) {
                this.callReconnectInitiation = z;
                this.callReconnectInitiation$set = true;
                return this;
            }

            public OutgoingCallParamsBuilder calleeCommsId(String str) {
                this.calleeCommsId = str;
                return this;
            }

            public OutgoingCallParamsBuilder calleeEndpointDescription(String str) {
                this.calleeEndpointDescription = str;
                return this;
            }

            public OutgoingCallParamsBuilder calleeName(String str) {
                this.calleeName = str;
                return this;
            }

            public OutgoingCallParamsBuilder calleeUri(String str) {
                this.calleeUri = str;
                return this;
            }

            public OutgoingCallParamsBuilder callerCommsId(String str) {
                this.callerCommsId = str;
                return this;
            }

            public OutgoingCallParamsBuilder callerEndpointDescription(String str) {
                this.callerEndpointDescription = str;
                return this;
            }

            public OutgoingCallParamsBuilder callerName(String str) {
                this.callerName = str;
                return this;
            }

            public OutgoingCallParamsBuilder callerUri(String str) {
                this.callerUri = str;
                return this;
            }

            public OutgoingCallParamsBuilder dataChannelParams(List<DataChannelConfiguration> list) {
                this.dataChannelParams = list;
                return this;
            }

            public OutgoingCallParamsBuilder dropIn(boolean z) {
                this.dropIn = z;
                return this;
            }

            public OutgoingCallParamsBuilder dropInPermissionGrantedForCaller(boolean z) {
                this.dropInPermissionGrantedForCaller = z;
                return this;
            }

            public OutgoingCallParamsBuilder dynamicAcousticParams(DynamicAcousticParams dynamicAcousticParams) {
                this.dynamicAcousticParams = dynamicAcousticParams;
                return this;
            }

            public OutgoingCallParamsBuilder headers(Map<String, String> map) {
                this.headers = map;
                return this;
            }

            public OutgoingCallParamsBuilder join(String str) {
                this.join = str;
                return this;
            }

            public OutgoingCallParamsBuilder mediaRelayInfo(MediaRelayInfo mediaRelayInfo) {
                this.mediaRelayInfo = mediaRelayInfo;
                return this;
            }

            public OutgoingCallParamsBuilder provider(String str) {
                this.provider = str;
                return this;
            }

            public OutgoingCallParamsBuilder realTimeText(RealTimeText realTimeText) {
                this.realTimeText = realTimeText;
                return this;
            }

            public OutgoingCallParamsBuilder rtcpMuxPolicy(RtcpMuxPolicy rtcpMuxPolicy) {
                this.rtcpMuxPolicy = rtcpMuxPolicy;
                return this;
            }

            public OutgoingCallParamsBuilder srtpKeying(String str) {
                this.srtpKeying = str;
                return this;
            }

            public OutgoingCallParamsBuilder stateTransitionTimeouts(Map<Call.State, TimeoutRule> map) {
                this.stateTransitionTimeouts = map;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceCallingService.OutgoingCallParams.OutgoingCallParamsBuilder(provider=");
                outline107.append(this.provider);
                outline107.append(", callId=");
                outline107.append(this.callId);
                outline107.append(", authInfo=");
                outline107.append(this.authInfo);
                outline107.append(", mediaRelayInfo=");
                outline107.append(this.mediaRelayInfo);
                outline107.append(", dropIn=");
                outline107.append(this.dropIn);
                outline107.append(", videoEffect=");
                outline107.append(this.videoEffect);
                outline107.append(", videoEnabled=");
                outline107.append(this.videoEnabled);
                outline107.append(", calleeName=");
                outline107.append(this.calleeName);
                outline107.append(", calleeEndpointDescription=");
                outline107.append(this.calleeEndpointDescription);
                outline107.append(", calleeCommsId=");
                outline107.append(this.calleeCommsId);
                outline107.append(", calleeUri=");
                outline107.append(this.calleeUri);
                outline107.append(", callerName=");
                outline107.append(this.callerName);
                outline107.append(", callerEndpointDescription=");
                outline107.append(this.callerEndpointDescription);
                outline107.append(", callerCommsId=");
                outline107.append(this.callerCommsId);
                outline107.append(", callerUri=");
                outline107.append(this.callerUri);
                outline107.append(", dropInPermissionGrantedForCaller=");
                outline107.append(this.dropInPermissionGrantedForCaller);
                outline107.append(", join=");
                outline107.append(this.join);
                outline107.append(", headers=");
                outline107.append(this.headers);
                outline107.append(", srtpKeying=");
                outline107.append(this.srtpKeying);
                outline107.append(", stateTransitionTimeouts=");
                outline107.append(this.stateTransitionTimeouts);
                outline107.append(", bundlePolicy=");
                outline107.append(this.bundlePolicy);
                outline107.append(", dynamicAcousticParams=");
                outline107.append(this.dynamicAcousticParams);
                outline107.append(", realTimeText=");
                outline107.append(this.realTimeText);
                outline107.append(", dataChannelParams=");
                outline107.append(this.dataChannelParams);
                outline107.append(", callReconnectInitiation=");
                outline107.append(this.callReconnectInitiation);
                outline107.append(", trickleIceEnabled=");
                outline107.append(this.trickleIceEnabled);
                outline107.append(", rtcpMuxPolicy=");
                outline107.append(this.rtcpMuxPolicy);
                outline107.append(")");
                return outline107.toString();
            }

            public OutgoingCallParamsBuilder trickleIceEnabled(boolean z) {
                this.trickleIceEnabled = z;
                this.trickleIceEnabled$set = true;
                return this;
            }

            public OutgoingCallParamsBuilder videoEffect(Call.VideoEffect videoEffect) {
                this.videoEffect = videoEffect;
                return this;
            }

            public OutgoingCallParamsBuilder videoEnabled(boolean z) {
                this.videoEnabled = z;
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean $default$callReconnectInitiation() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean $default$trickleIceEnabled() {
            return false;
        }

        OutgoingCallParams(String str, String str2, AuthenticationInfo authenticationInfo, MediaRelayInfo mediaRelayInfo, boolean z, Call.VideoEffect videoEffect, boolean z2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z3, String str11, Map<String, String> map, String str12, Map<Call.State, TimeoutRule> map2, BundlePolicy bundlePolicy, DynamicAcousticParams dynamicAcousticParams, RealTimeText realTimeText, List<DataChannelConfiguration> list, boolean z4, boolean z5, RtcpMuxPolicy rtcpMuxPolicy) {
            this.provider = str;
            this.callId = str2;
            this.authInfo = authenticationInfo;
            this.mediaRelayInfo = mediaRelayInfo;
            this.dropIn = z;
            this.videoEffect = videoEffect;
            this.videoEnabled = z2;
            this.calleeName = str3;
            this.calleeEndpointDescription = str4;
            this.calleeCommsId = str5;
            this.calleeUri = str6;
            this.callerName = str7;
            this.callerEndpointDescription = str8;
            this.callerCommsId = str9;
            this.callerUri = str10;
            this.dropInPermissionGrantedForCaller = z3;
            this.join = str11;
            this.headers = map;
            this.srtpKeying = str12;
            this.stateTransitionTimeouts = map2;
            this.bundlePolicy = bundlePolicy;
            this.dynamicAcousticParams = dynamicAcousticParams;
            this.realTimeText = realTimeText;
            this.dataChannelParams = list;
            this.callReconnectInitiation = z4;
            this.trickleIceEnabled = z5;
            this.rtcpMuxPolicy = rtcpMuxPolicy;
        }

        public static OutgoingCallParamsBuilder builder() {
            return new OutgoingCallParamsBuilder();
        }

        public AuthenticationInfo getAuthInfo() {
            return this.authInfo;
        }

        public BundlePolicy getBundlePolicy() {
            return this.bundlePolicy;
        }

        public String getCallId() {
            return this.callId;
        }

        public String getCalleeCommsId() {
            return this.calleeCommsId;
        }

        public String getCalleeEndpointDescription() {
            return this.calleeEndpointDescription;
        }

        public String getCalleeName() {
            return this.calleeName;
        }

        public String getCalleeUri() {
            return this.calleeUri;
        }

        public String getCallerCommsId() {
            return this.callerCommsId;
        }

        public String getCallerEndpointDescription() {
            return this.callerEndpointDescription;
        }

        public String getCallerName() {
            return this.callerName;
        }

        public String getCallerUri() {
            return this.callerUri;
        }

        public List<DataChannelConfiguration> getDataChannelParams() {
            return this.dataChannelParams;
        }

        public DynamicAcousticParams getDynamicAcousticParams() {
            return this.dynamicAcousticParams;
        }

        public Map<String, String> getHeaders() {
            return this.headers;
        }

        public String getJoin() {
            return this.join;
        }

        public MediaRelayInfo getMediaRelayInfo() {
            return this.mediaRelayInfo;
        }

        public String getProvider() {
            return this.provider;
        }

        public RealTimeText getRealTimeText() {
            return this.realTimeText;
        }

        public RtcpMuxPolicy getRtcpMuxPolicy() {
            return this.rtcpMuxPolicy;
        }

        public String getSrtpKeying() {
            return this.srtpKeying;
        }

        public Map<Call.State, TimeoutRule> getStateTransitionTimeouts() {
            return this.stateTransitionTimeouts;
        }

        public Call.VideoEffect getVideoEffect() {
            return this.videoEffect;
        }

        public boolean isCallReconnectInitiation() {
            return this.callReconnectInitiation;
        }

        public boolean isDropIn() {
            return this.dropIn;
        }

        public boolean isDropInPermissionGrantedForCaller() {
            return this.dropInPermissionGrantedForCaller;
        }

        public boolean isTrickleIceEnabled() {
            return this.trickleIceEnabled;
        }

        public boolean isVideoEnabled() {
            return this.videoEnabled;
        }
    }

    /* loaded from: classes11.dex */
    public enum State {
        Uninitialized,
        Initialized,
        Unregistered,
        Registered
    }

    void beginCall(OutgoingCallParams outgoingCallParams, EventTracer eventTracer);

    void configurePresence(boolean z);

    void configureRegistrar(RegistrarConfiguration registrarConfiguration);

    Call getCallByCallId(String str);

    List<Call> getCalls(Predicate<Call> predicate);

    HistoricalCall getHistoricalCallByCallId(String str);

    Iterable<HistoricalCall> getHistoricalCalls();

    WebRTCViewRenderer getLocalViewRenderer();

    Call getMostRecentCall();

    RegistrarConfiguration getRegistrarConfiguration();

    WebRTCViewRenderer getRemoteViewRenderer();

    State getState();

    VolumeController getVolumeController();

    void hangup();

    void initialize(Context context, EventTracerFactory eventTracerFactory, DeviceCallingServiceParams deviceCallingServiceParams);

    boolean isSystemMediaEnabled();

    void reconnectCall(AuthenticationInfo authenticationInfo, MediaRelayInfo mediaRelayInfo);

    void registerListener(DeviceCallingServiceListener deviceCallingServiceListener);

    void setMediaConstraints(Map<String, Integer> map);

    void setSystemMediaState(boolean z);

    void shutdown();

    long timeLeftInAuthToken();

    void unregisterListener(DeviceCallingServiceListener deviceCallingServiceListener);

    void updateAudioBitrateForCalls(int i);

    void updateAuthToken(String str, int i, boolean z);

    void updateConnectivityState(boolean z);

    void updateDeviceCapabilities(boolean z, boolean z2);

    void updatePresenceInfo(String str);

    void updateSystemCameraState(boolean z);

    void warmup(int i);
}
