package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.DropInController;
import com.amazon.comms.calling.sipclient.CallDetails;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.instrumentation.EventTracer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import lombok.NonNull;
/* loaded from: classes11.dex */
public interface Call {

    /* loaded from: classes11.dex */
    public static class AcceptParams {
        private final boolean callReconnectInitiation;
        private final boolean calleeDropInPermission;
        private final String calleeName;
        private final boolean callerDropInPermission;
        private final String callerName;
        private final DropInController.UserPreference dropInUserPreference;
        private final double frostedTransitionTime;
        private final double totalFrostTime;
        private final boolean videoEnabled;

        /* loaded from: classes11.dex */
        public static class AcceptParamsBuilder {
            private boolean callReconnectInitiation;
            private boolean callReconnectInitiation$set;
            private boolean calleeDropInPermission;
            private String calleeName;
            private boolean callerDropInPermission;
            private String callerName;
            private DropInController.UserPreference dropInUserPreference;
            private double frostedTransitionTime;
            private double totalFrostTime;
            private boolean videoEnabled = true;

            AcceptParamsBuilder() {
            }

            public AcceptParams build() {
                return new AcceptParams(this.dropInUserPreference, this.totalFrostTime, this.frostedTransitionTime, this.videoEnabled, this.callerName, this.calleeName, this.callerDropInPermission, this.calleeDropInPermission, this.callReconnectInitiation$set ? this.callReconnectInitiation : AcceptParams.$default$callReconnectInitiation());
            }

            public AcceptParamsBuilder callReconnectInitiation(boolean z) {
                this.callReconnectInitiation = z;
                this.callReconnectInitiation$set = true;
                return this;
            }

            public AcceptParamsBuilder calleeDropInPermission(boolean z) {
                this.calleeDropInPermission = z;
                return this;
            }

            public AcceptParamsBuilder calleeName(String str) {
                this.calleeName = str;
                return this;
            }

            public AcceptParamsBuilder callerDropInPermission(boolean z) {
                this.callerDropInPermission = z;
                return this;
            }

            public AcceptParamsBuilder callerName(String str) {
                this.callerName = str;
                return this;
            }

            public AcceptParamsBuilder dropInUserPreference(DropInController.UserPreference userPreference) {
                this.dropInUserPreference = userPreference;
                return this;
            }

            public AcceptParamsBuilder frostedTransitionTime(double d) {
                this.frostedTransitionTime = d;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Call.AcceptParams.AcceptParamsBuilder(dropInUserPreference=");
                outline107.append(this.dropInUserPreference);
                outline107.append(", totalFrostTime=");
                outline107.append(this.totalFrostTime);
                outline107.append(", frostedTransitionTime=");
                outline107.append(this.frostedTransitionTime);
                outline107.append(", videoEnabled=");
                outline107.append(this.videoEnabled);
                outline107.append(", callerName=");
                outline107.append(this.callerName);
                outline107.append(", calleeName=");
                outline107.append(this.calleeName);
                outline107.append(", callerDropInPermission=");
                outline107.append(this.callerDropInPermission);
                outline107.append(", calleeDropInPermission=");
                outline107.append(this.calleeDropInPermission);
                outline107.append(", callReconnectInitiation=");
                return GeneratedOutlineSupport1.outline97(outline107, this.callReconnectInitiation, ")");
            }

            public AcceptParamsBuilder totalFrostTime(double d) {
                this.totalFrostTime = d;
                return this;
            }

            public AcceptParamsBuilder videoEnabled(boolean z) {
                this.videoEnabled = z;
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean $default$callReconnectInitiation() {
            return false;
        }

        AcceptParams(DropInController.UserPreference userPreference, double d, double d2, boolean z, String str, String str2, boolean z2, boolean z3, boolean z4) {
            this.dropInUserPreference = userPreference;
            this.totalFrostTime = d;
            this.frostedTransitionTime = d2;
            this.videoEnabled = z;
            this.callerName = str;
            this.calleeName = str2;
            this.callerDropInPermission = z2;
            this.calleeDropInPermission = z3;
            this.callReconnectInitiation = z4;
        }

        public static AcceptParamsBuilder builder() {
            return new AcceptParamsBuilder();
        }

        public String getCalleeName() {
            return this.calleeName;
        }

        public String getCallerName() {
            return this.callerName;
        }

        public DropInController.UserPreference getDropInUserPreference() {
            return this.dropInUserPreference;
        }

        public double getFrostedTransitionTime() {
            return this.frostedTransitionTime;
        }

        public double getTotalFrostTime() {
            return this.totalFrostTime;
        }

        public boolean isCallReconnectInitiation() {
            return this.callReconnectInitiation;
        }

        public boolean isCalleeDropInPermission() {
            return this.calleeDropInPermission;
        }

        public boolean isCallerDropInPermission() {
            return this.callerDropInPermission;
        }

        public boolean isVideoEnabled() {
            return this.videoEnabled;
        }
    }

    /* loaded from: classes11.dex */
    public static class CallInfoParams {
        private final String calleeEndpointDescription;
        private final String calleeName;
        private final String callerEndpointDescription;
        private final String callerName;
        private final Map<State, TimeoutRule> stateTransitionTimeouts;

        /* loaded from: classes11.dex */
        public static class CallInfoParamsBuilder {
            private String calleeEndpointDescription;
            private String calleeName;
            private String callerEndpointDescription;
            private String callerName;
            private Map<State, TimeoutRule> stateTransitionTimeouts;

            CallInfoParamsBuilder() {
            }

            public CallInfoParams build() {
                return new CallInfoParams(this.callerName, this.callerEndpointDescription, this.calleeName, this.calleeEndpointDescription, this.stateTransitionTimeouts);
            }

            public CallInfoParamsBuilder calleeEndpointDescription(String str) {
                this.calleeEndpointDescription = str;
                return this;
            }

            public CallInfoParamsBuilder calleeName(String str) {
                this.calleeName = str;
                return this;
            }

            public CallInfoParamsBuilder callerEndpointDescription(String str) {
                this.callerEndpointDescription = str;
                return this;
            }

            public CallInfoParamsBuilder callerName(String str) {
                this.callerName = str;
                return this;
            }

            public CallInfoParamsBuilder stateTransitionTimeouts(Map<State, TimeoutRule> map) {
                this.stateTransitionTimeouts = map;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Call.CallInfoParams.CallInfoParamsBuilder(callerName=");
                outline107.append(this.callerName);
                outline107.append(", callerEndpointDescription=");
                outline107.append(this.callerEndpointDescription);
                outline107.append(", calleeName=");
                outline107.append(this.calleeName);
                outline107.append(", calleeEndpointDescription=");
                outline107.append(this.calleeEndpointDescription);
                outline107.append(", stateTransitionTimeouts=");
                outline107.append(this.stateTransitionTimeouts);
                outline107.append(")");
                return outline107.toString();
            }
        }

        CallInfoParams(String str, String str2, String str3, String str4, Map<State, TimeoutRule> map) {
            this.callerName = str;
            this.callerEndpointDescription = str2;
            this.calleeName = str3;
            this.calleeEndpointDescription = str4;
            this.stateTransitionTimeouts = map;
        }

        public static CallInfoParamsBuilder builder() {
            return new CallInfoParamsBuilder();
        }

        public String getCalleeEndpointDescription() {
            return this.calleeEndpointDescription;
        }

        public String getCalleeName() {
            return this.calleeName;
        }

        public String getCallerEndpointDescription() {
            return this.callerEndpointDescription;
        }

        public String getCallerName() {
            return this.callerName;
        }

        public Map<State, TimeoutRule> getStateTransitionTimeouts() {
            return this.stateTransitionTimeouts;
        }
    }

    /* loaded from: classes11.dex */
    public enum HangupRequest {
        Everywhere,
        ThisDevice
    }

    /* loaded from: classes11.dex */
    public enum Side {
        Local,
        Remote
    }

    /* loaded from: classes11.dex */
    public enum State {
        Created,
        Ringing,
        Active,
        OnLocalHold,
        OnRemoteHold,
        Complete
    }

    /* loaded from: classes11.dex */
    public enum VideoEffect {
        None,
        Frosted
    }

    /* loaded from: classes11.dex */
    public static class VideoViewDimensions {
        private final int rotation;
        private final int videoHeight;
        private final int videoWidth;

        public VideoViewDimensions(int i, int i2, int i3) {
            this.videoWidth = i;
            this.videoHeight = i2;
            this.rotation = i3;
        }

        public int getRotation() {
            return this.rotation;
        }

        public int getVideoHeight() {
            return this.videoHeight;
        }

        public int getVideoWidth() {
            return this.videoWidth;
        }
    }

    void accept(AcceptParams acceptParams);

    void acknowledgeCall();

    CallDetails getCallDetails();

    String getCallId();

    /* renamed from: getDropInController */
    DropInController mo3244getDropInController();

    EventTracer getEventTracer();

    SipHeaders getIncomingHeaders();

    MediaStateChangeTrigger getLastMediaStateChangeTrigger();

    Participant getLocalParticipant();

    VideoViewDimensions getLocalVideoViewDimensions();

    MediaStats getMediaStats();

    MediaStatus getMediaStatus();

    Side getOrigin();

    SipHeaders getOutgoingHeaders();

    String getProvider();

    Participant getRemoteParticipant();

    VideoViewDimensions getRemoteVideoViewDimensions();

    State getState();

    VideoEffect getVideoEffect();

    void hangup(HangupRequest hangupRequest);

    boolean isAccepted();

    boolean isCheckInCall();

    boolean isDropInCall();

    boolean isVideoRequested();

    void notifyRinging();

    void registerCallListener(CallListener callListener);

    void registerCameraEventsListener(CameraEventsListener cameraEventsListener);

    void registerCameraPreSwitchListener(CameraPreSwitchListener cameraPreSwitchListener);

    void registerDataChannelEventListener(DataChannelEventListener dataChannelEventListener);

    void registerMediaListener(MediaListener mediaListener);

    void registerRealTimeTextListener(RealTimeTextListener realTimeTextListener);

    void sendData(@NonNull DataChannelDTO dataChannelDTO);

    void sendDtmfTones(String str, int i, int i2);

    void sendRealTimeTextData(byte[] bArr);

    void setLocalAudioState(boolean z);

    void setLocalMediaCapability(boolean z, boolean z2);

    void setLocalVideoPermitted(boolean z);

    void setLocalVideoState(boolean z);

    void setVideoEffect(VideoEffect videoEffect, double d);

    void switchCamera();

    void switchCamera(String str);

    void switchCameraPostSetup(String str);

    void transferAudioToOutputDevice(int i);

    void unregisterCallListener(CallListener callListener);

    void unregisterCameraEventsListener(CameraEventsListener cameraEventsListener);

    void unregisterCameraPreSwitchListener(CameraPreSwitchListener cameraPreSwitchListener);

    void unregisterDataChannelEventListener(DataChannelEventListener dataChannelEventListener);

    void unregisterMediaListener(MediaListener mediaListener);

    void unregisterRealTimeTextListener(RealTimeTextListener realTimeTextListener);

    void updateCallInfo(CallInfoParams callInfoParams);
}
