package com.amazon.alexa.smarthomecameras.session;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.model.SessionId;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import com.amazon.alexa.smarthomecameras.service.ALVUIService;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.rtcsc.appclient.RtcscAppClientListener;
import com.amazon.rtcsc.interfaces.RtcscAppDisconnectCode;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.interfaces.RtcscMediaConnectionState;
import com.amazon.rtcsc.interfaces.RtcscMediaType;
import com.amazon.rtcsc.interfaces.RtcscSessionState;
import com.amazon.rtcsc.interfaces.RtcscSide;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes10.dex */
public class RtcscCameraSessionManager extends RtcscAppClientListener implements CameraSessionManager {
    private static final String LIVE_VIEW_DATA_CHANNEL_LABEL = "live-view-data-channel";
    private static final String TAG = RtcscCameraSessionManager.class.getSimpleName();
    private SessionId activeSessionId;
    private RtcscAppInfo appInfo;
    private CameraSessionState cameraSessionState;
    private Context context;
    private LiveViewEventSender eventSender;
    private ScheduledExecutorService executorService;
    private boolean isEstablishingSession;
    private MediaConnectionState mediaConnectionState;
    private CamerasMobilyticsService mobilyticsService;
    private CamerasAppClient rtcscAppClient;
    private Set<CameraSessionListener> sessionListeners = new HashSet();

    /* renamed from: com.amazon.alexa.smarthomecameras.session.RtcscCameraSessionManager$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$rtcsc$interfaces$RtcscMediaConnectionState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSide = new int[RtcscSide.values().length];

        static {
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSide[RtcscSide.LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSide[RtcscSide.REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$rtcsc$interfaces$RtcscMediaConnectionState = new int[RtcscMediaConnectionState.values().length];
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscMediaConnectionState[RtcscMediaConnectionState.MEDIA_CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscMediaConnectionState[RtcscMediaConnectionState.MEDIA_DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscMediaConnectionState[RtcscMediaConnectionState.MEDIA_CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscMediaConnectionState[RtcscMediaConnectionState.UNKNOWN_MEDIA_CONNECTION_STATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState = new int[RtcscSessionState.values().length];
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState[RtcscSessionState.SESSION_STATE_UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState[RtcscSessionState.SESSION_PREPARING.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState[RtcscSessionState.SESSION_ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState[RtcscSessionState.SESSION_PAUSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$interfaces$RtcscSessionState[RtcscSessionState.SESSION_FINISHING.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public RtcscCameraSessionManager(Context context, RtcscAppInfo rtcscAppInfo, CamerasAppClient camerasAppClient, LiveViewEventSender liveViewEventSender, ScheduledExecutorService scheduledExecutorService, CamerasMobilyticsService camerasMobilyticsService) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(rtcscAppInfo, "AppInfo cannot be null");
        Preconditions.checkNotNull(camerasAppClient, "RtcscAppClient cannot be null");
        Preconditions.checkNotNull(liveViewEventSender, "LiveViewEventSender cannot be null");
        Preconditions.checkNotNull(scheduledExecutorService, "ExecutorService cannot be null");
        Preconditions.checkNotNull(camerasMobilyticsService, "CamerasMobilyticsService cannot be null");
        this.context = context;
        this.appInfo = rtcscAppInfo;
        this.rtcscAppClient = camerasAppClient;
        this.eventSender = liveViewEventSender;
        this.executorService = scheduledExecutorService;
        this.mobilyticsService = camerasMobilyticsService;
        this.isEstablishingSession = true;
        initialize();
    }

    private MediaConnectionState getMediaStateFromRtcscsMediaState(RtcscMediaConnectionState rtcscMediaConnectionState) {
        int ordinal = rtcscMediaConnectionState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return MediaConnectionState.MEDIA_CONNECTED;
            }
            if (ordinal == 2) {
                return MediaConnectionState.MEDIA_DISCONNECTED;
            }
            if (ordinal != 3) {
                return MediaConnectionState.UNKNOWN_MEDIA_CONNECTION_STATE;
            }
            return MediaConnectionState.UNKNOWN_MEDIA_CONNECTION_STATE;
        }
        return MediaConnectionState.MEDIA_CONNECTING;
    }

    private CameraSessionState getStateFromRtcscSessionState(RtcscSessionState rtcscSessionState) {
        int ordinal = rtcscSessionState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return CameraSessionState.SESSION_PREPARING;
            }
            if (ordinal == 2) {
                return CameraSessionState.SESSION_ACTIVE;
            }
            if (ordinal == 3) {
                return CameraSessionState.SESSION_PAUSED;
            }
            if (ordinal != 4) {
                return CameraSessionState.SESSION_STATE_UNKNOWN;
            }
            return CameraSessionState.SESSION_FINISHING;
        }
        return CameraSessionState.SESSION_STATE_UNKNOWN;
    }

    private VideoRendererSide getVideoRendererSideFromRtcscSide(RtcscSide rtcscSide) {
        int ordinal = rtcscSide.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                return VideoRendererSide.REMOTE;
            }
            return VideoRendererSide.REMOTE;
        }
        return VideoRendererSide.LOCAL;
    }

    private void initialize() {
        this.eventSender.initialize();
    }

    private void stopForegroundService() {
        Intent intent = new Intent(this.context, ALVUIService.class);
        intent.setAction(ALVUIService.ACTION_STOP);
        this.context.startService(intent);
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public synchronized void deregisterSessionListener(EntityId entityId, CameraSessionListener cameraSessionListener) {
        this.sessionListeners.remove(cameraSessionListener);
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public void didBackground() {
        this.isEstablishingSession = true;
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public synchronized void disconnectSession(EntityId entityId) {
        if (this.activeSessionId != null) {
            final String value = this.activeSessionId.getValue();
            this.eventSender.sendRequestStopLiveViewEvent(value, entityId.getValue());
            this.executorService.execute(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.session.-$$Lambda$RtcscCameraSessionManager$3FZykTfKVWiV2hJCZO96r15pllY
                @Override // java.lang.Runnable
                public final void run() {
                    RtcscCameraSessionManager.this.lambda$disconnectSession$1$RtcscCameraSessionManager(value);
                }
            });
        }
        this.executorService.execute(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.session.-$$Lambda$RtcscCameraSessionManager$-kGMWbrRWrcgBEUolHmo4_2GGD8
            @Override // java.lang.Runnable
            public final void run() {
                RtcscCameraSessionManager.this.lambda$disconnectSession$2$RtcscCameraSessionManager();
            }
        });
        this.activeSessionId = null;
        this.cameraSessionState = null;
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    @Nullable
    public synchronized SessionId getActiveSessionId() {
        return this.activeSessionId;
    }

    public /* synthetic */ void lambda$disconnectSession$1$RtcscCameraSessionManager(String str) {
        GeneratedOutlineSupport1.outline158("Disconnecting session: ", str);
        this.rtcscAppClient.disconnectSession(str, RtcscAppDisconnectCode.USER_TERMINATED_SESSION);
    }

    public /* synthetic */ void lambda$disconnectSession$2$RtcscCameraSessionManager() {
        String str = "unregisterRtcscAppClientListenerreturned with code: " + this.rtcscAppClient.unregisterRtcscAppClientListener(this.appInfo);
        stopForegroundService();
    }

    public /* synthetic */ void lambda$onSessionRemoved$3$RtcscCameraSessionManager() {
        String str = "unregisterRtcscAppClientListenerreturned with code: " + this.rtcscAppClient.unregisterRtcscAppClientListener(this.appInfo);
    }

    public /* synthetic */ void lambda$requestSessionForDevice$0$RtcscCameraSessionManager() {
        String str = "registerRtcscAppClientListener returned with code: " + this.rtcscAppClient.registerRtcscAppClientListener(this.appInfo, this);
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onFirstFrameRendered(String str, RtcscSide rtcscSide) {
        super.onFirstFrameRendered(str, rtcscSide);
        String str2 = "On first frame rendered for " + str + " on " + rtcscSide + " side renderer";
        VideoRendererSide videoRendererSideFromRtcscSide = getVideoRendererSideFromRtcscSide(rtcscSide);
        for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
            cameraSessionListener.onFirstFrameRendered(this.activeSessionId, videoRendererSideFromRtcscSide);
        }
        if (this.isEstablishingSession) {
            this.rtcscAppClient.setLocalAudioState(str, false);
            this.rtcscAppClient.setRemoteAudioState(str, false);
            this.isEstablishingSession = false;
        }
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onMediaConnectionStateChanged(String str, RtcscMediaConnectionState rtcscMediaConnectionState) {
        super.onMediaConnectionStateChanged(str, rtcscMediaConnectionState);
        String str2 = "Media connection state state changed for: " + str + " to" + rtcscMediaConnectionState;
        this.mediaConnectionState = getMediaStateFromRtcscsMediaState(rtcscMediaConnectionState);
        for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
            cameraSessionListener.onMediaConnectionStateChanged(this.activeSessionId, this.mediaConnectionState);
        }
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z) {
        super.onMediaStatusChanged(str, rtcscSide, rtcscMediaType, z);
        for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
            cameraSessionListener.onMediaStatusChanged(str, rtcscSide, rtcscMediaType, z);
        }
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public synchronized void onSessionAvailable(String str) {
        super.onSessionAvailable(str);
        String str2 = "Session available: " + str;
        if (!TextUtils.isEmpty(str)) {
            this.activeSessionId = SessionId.create(str);
            for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
                cameraSessionListener.onSessionConnected(this.activeSessionId);
            }
            this.rtcscAppClient.signalReadyForSession(str);
        } else {
            this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.NULL_SESSION_ID);
        }
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public void onSessionError(String str, RtcscErrorCode rtcscErrorCode, String str2) {
        super.onSessionError(str, rtcscErrorCode, str2);
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "RTCSC on session error");
        this.mobilyticsService.recordRtcError(rtcscErrorCode.toString());
        for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
            cameraSessionListener.onError(rtcscErrorCode.toString(), "");
        }
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public synchronized void onSessionRemoved(String str) {
        super.onSessionRemoved(str);
        String str2 = "Session removed: " + str;
        if (!TextUtils.isEmpty(str)) {
            for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
                cameraSessionListener.onSessionDisconnected(SessionId.create(str));
            }
        }
        this.executorService.execute(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.session.-$$Lambda$RtcscCameraSessionManager$f7SxCgclOe7g18pssz7tPZ0sceI
            @Override // java.lang.Runnable
            public final void run() {
                RtcscCameraSessionManager.this.lambda$onSessionRemoved$3$RtcscCameraSessionManager();
            }
        });
        this.activeSessionId = null;
        this.cameraSessionState = null;
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClientListener, com.amazon.rtcsc.interfaces.IRtcscAppClientListener
    public synchronized void onSessionStateChanged(String str, RtcscSessionState rtcscSessionState) {
        super.onSessionStateChanged(str, rtcscSessionState);
        String str2 = "Session state changed for: " + str + " to" + rtcscSessionState;
        this.cameraSessionState = getStateFromRtcscSessionState(rtcscSessionState);
        for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
            cameraSessionListener.onSessionStateChanged(this.activeSessionId, this.cameraSessionState);
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public synchronized void registerSessionListener(EntityId entityId, CameraSessionListener cameraSessionListener) {
        this.sessionListeners.add(cameraSessionListener);
        if (this.activeSessionId != null && this.cameraSessionState != null) {
            cameraSessionListener.onSessionStateChanged(this.activeSessionId, this.cameraSessionState);
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public synchronized void requestSessionForDevice(EntityId entityId) {
        String str = TAG;
        Log.i(str, "Requesting session for device: " + entityId.getValue());
        this.executorService.execute(new Runnable() { // from class: com.amazon.alexa.smarthomecameras.session.-$$Lambda$RtcscCameraSessionManager$NLaC6xpiZv1pHFt80us1QLWYv_Q
            @Override // java.lang.Runnable
            public final void run() {
                RtcscCameraSessionManager.this.lambda$requestSessionForDevice$0$RtcscCameraSessionManager();
            }
        });
        this.eventSender.sendRequestStartLiveViewEvent(entityId.getValue());
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public void sendData(EntityId entityId, String str) {
        SessionId sessionId = this.activeSessionId;
        if (sessionId != null) {
            this.rtcscAppClient.sendData(sessionId.getValue(), LIVE_VIEW_DATA_CHANNEL_LABEL, str.getBytes(), false);
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Physical gesture data sent");
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public void sessionEnded(String str, String str2) {
        for (CameraSessionListener cameraSessionListener : this.sessionListeners) {
            cameraSessionListener.onError(str, str2);
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionManager
    public synchronized void teardown() {
        this.eventSender.teardown();
        this.sessionListeners.clear();
        this.activeSessionId = null;
        this.executorService.shutdown();
    }
}
