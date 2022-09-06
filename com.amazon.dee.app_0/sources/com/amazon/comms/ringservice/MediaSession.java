package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.comms.ringservice.util.ThermalMitigationDetails;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import java.util.Map;
import lombok.NonNull;
import org.webrtc.VideoRenderer;
/* loaded from: classes12.dex */
public interface MediaSession {
    void addMediaRelayInfo(String str, String str2, String str3);

    void addRemoteIceCandidates();

    void addRemoteIceCandidates(Sdp sdp);

    void attachCall(String str, boolean z, MediaSessionListener mediaSessionListener, EventTracer eventTracer, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, DataChannelListener dataChannelListener, RealTimeTextDataChannelListener realTimeTextDataChannelListener, boolean z2);

    void clearIceServers();

    boolean didMediaConnectionEverEstablish();

    MediaStatus getMediaStatus();

    ThermalMitigationDetails getThermalMitigationDetails();

    void insertDtmf(String str, int i, int i2);

    boolean isDtmfInsertable();

    boolean isMediaConnectionActive();

    boolean isUsingFrontFacingCamera();

    void postCallEstablished();

    void processAcceptParams(boolean z);

    boolean processRemoteDescription(Sdp sdp, Sdp.Type type);

    void refreshSession();

    void resetRemoteDescription(boolean z, boolean z2);

    void restartIce(Sdp sdp, boolean z);

    void retryMedia();

    void sendData(@NonNull DataChannelDTO dataChannelDTO);

    void setLocalAudioState(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger);

    void setLocalVideoPermitted(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger);

    void setLocalVideoState(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger);

    void setMediaConstraints(Map<String, Integer> map);

    void setSystemCameraState(boolean z);

    void setSystemMediaState(boolean z);

    void setVideoEffect(Call.VideoEffect videoEffect, double d);

    void setVolume(float f);

    void startIceTrickling();

    void startMedia(Sdp sdp, VideoRenderer.Callbacks callbacks, VideoRenderer.Callbacks callbacks2);

    void stopIceTrickling();

    void stopMedia();

    void switchCamera();

    void switchCamera(String str);

    void warmupMedia();
}
