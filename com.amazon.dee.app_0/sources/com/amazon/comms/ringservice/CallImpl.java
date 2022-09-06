package com.amazon.comms.ringservice;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallListener;
import com.amazon.comms.calling.service.CameraEventsListener;
import com.amazon.comms.calling.service.CameraPreSwitchListener;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.DataChannelEvent;
import com.amazon.comms.calling.service.DataChannelEventListener;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.calling.service.MediaListener;
import com.amazon.comms.calling.service.MediaParams;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.service.Participant;
import com.amazon.comms.calling.service.RealTimeTextListener;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.calling.sipclient.CallDetails;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler;
import com.amazon.comms.ringservice.webrtc.WebRTCViewRendererImpl;
import com.amazon.comms.util.ListenerSet;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import java.util.Map;
import org.webrtc.EglBase;
import org.webrtc.SurfaceViewRendererShim;
import org.webrtc.TextureViewRendererShim;
import org.webrtc.WebRTCAudioUtilsShim;
import org.webrtc.WebRTCRendererShim;
/* loaded from: classes12.dex */
public class CallImpl implements Call, VideoRendererEventsHandler.VideoRendererEventsListener {
    private static final String SENDDTMF_ERROR_CODE_CALL_NOT_IN_PROGRESS = "CALL_NOT_IN_PROGRESS";
    private static final String SENDDTMF_ERROR_CODE_DTMF_FAILED = "DTMF_FAILED";
    private static final CommsLogger log = CommsLogger.getLogger(CallImpl.class);
    private boolean callAccepted;
    private final CallDeathTimer callDeathTimer;
    private final String callId;
    private Call.State callStateFromUIView;
    private final boolean checkInCall;
    private final boolean dropInCall;
    private final DropInControllerImpl dropInController;
    private final EventTracer eventTracer;
    private final CallImplInternal internalCallOrchestrator;
    private VideoRendererEventsHandler localRendererEventsHandler;
    private Call.VideoViewDimensions localVideoViewDimensions;
    private WebRTCViewRenderer localViewRenderer;
    private WebRTCRendererShim<?> mLocalRenderer;
    private WebRTCRendererShim<?> mRemoteRenderer;
    private MediaStats mediaStats;
    private final MetricsSession metricsSession;
    private final float pipSurfaceViewBorderRadius;
    private RealTimeTextListener realTimeTextListener;
    private VideoRendererEventsHandler remoteRendererEventsHandler;
    private Call.VideoViewDimensions remoteVideoViewDimensions;
    private WebRTCViewRenderer remoteViewRenderer;
    private final boolean shouldSimulateFirstFrameReceived;
    private final boolean translucentVideoRendererSupported;
    private final boolean useTextureViewForRendering;
    private boolean videoCapable;
    private boolean videoRequested;
    private final ListenerSet<CallListener> callListeners = new ListenerSet<>();
    private final ListenerSet<MediaListener> mediaListeners = new ListenerSet<>();
    private final ListenerSet<DataChannelEventListener> dataChannelListeners = new ListenerSet<>();
    private final ListenerSet<CameraPreSwitchListener> cameraPreSwitchListenerSet = new ListenerSet<>();
    private final ListenerSet<CameraEventsListener> cameraEventsListenerSet = new ListenerSet<>();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private MediaStateChangeTrigger lastMediaStateChangeTrigger = MediaStateChangeTrigger.USER_REQUEST;
    private Call.VideoEffect lastRequestVideoEffect = Call.VideoEffect.None;

    protected CallImpl(CallImplInternal callImplInternal, String str, boolean z, boolean z2, boolean z3, boolean z4, EventTracer eventTracer, MetricsSession metricsSession, boolean z5, boolean z6, float f, boolean z7, boolean z8) {
        this.internalCallOrchestrator = callImplInternal;
        this.shouldSimulateFirstFrameReceived = z6;
        this.pipSurfaceViewBorderRadius = f;
        this.eventTracer = eventTracer;
        this.metricsSession = metricsSession;
        this.callDeathTimer = new CallDeathTimer(metricsSession, this, z5);
        this.dropInCall = z3;
        this.checkInCall = z4;
        this.callId = str;
        this.videoCapable = z;
        this.videoRequested = z && z2;
        if (z3 && getOrigin() == Call.Side.Remote) {
            this.dropInController = new DropInControllerImpl(this);
        } else {
            this.dropInController = null;
        }
        this.callStateFromUIView = Call.State.Created;
        this.callAccepted = false;
        this.realTimeTextListener = null;
        this.useTextureViewForRendering = z7;
        this.translucentVideoRendererSupported = z8;
    }

    public static CallImpl beginCall(CallImplInternal callImplInternal, boolean z, boolean z2, String str, AmazonCallInfo amazonCallInfo, boolean z3, EventTracer eventTracer, MetricsSession metricsSession, boolean z4, boolean z5, float f, boolean z6, boolean z7) {
        String uri = amazonCallInfo.getRemoteParticipant().getUri();
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("begin call: calleeUri=");
        outline107.append(log.sensitive(uri));
        commsLogger.i(outline107.toString());
        return new CallImpl(callImplInternal, str, z, z2, z3, false, eventTracer, metricsSession, z4, z5, f, z6, z7);
    }

    private void clearRendererAndListener() {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("clearRendererAndListener. local Renderer= ");
        outline107.append(this.mLocalRenderer);
        outline107.append(" remote renderer= ");
        outline107.append(this.mRemoteRenderer);
        commsLogger.i(outline107.toString());
        WebRTCRendererShim<?> webRTCRendererShim = this.mLocalRenderer;
        if (webRTCRendererShim != null) {
            webRTCRendererShim.release();
            this.mLocalRenderer = null;
        }
        WebRTCRendererShim<?> webRTCRendererShim2 = this.mRemoteRenderer;
        if (webRTCRendererShim2 != null) {
            webRTCRendererShim2.release();
            this.mRemoteRenderer = null;
        }
        this.localViewRenderer = null;
        this.remoteViewRenderer = null;
        this.localRendererEventsHandler = null;
        this.remoteRendererEventsHandler = null;
        this.remoteVideoViewDimensions = null;
        this.localVideoViewDimensions = null;
        this.callListeners.clear();
        this.mediaListeners.clear();
        this.dataChannelListeners.clear();
        this.cameraPreSwitchListenerSet.clear();
        this.cameraEventsListenerSet.clear();
    }

    private void setState(Call.State state) {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setState. previous= ");
        outline107.append(this.callStateFromUIView);
        outline107.append(" new= ");
        outline107.append(state);
        commsLogger.i(outline107.toString());
        this.callStateFromUIView = state;
    }

    private void shutdownDeathTimer() {
        this.callDeathTimer.cancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallImpl startIncomingCall(CallImplInternal callImplInternal, boolean z, boolean z2, String str, AmazonCallInfo amazonCallInfo, EventTracer eventTracer, MetricsSession metricsSession, boolean z3, boolean z4, float f, boolean z5, boolean z6, boolean z7) {
        CallImpl callImpl = new CallImpl(callImplInternal, str, z, z2, amazonCallInfo.isDropIn(), amazonCallInfo.isCheckIn(), eventTracer, metricsSession, z3, z4, f, z6, z7);
        callImpl.videoRequested = z && z2 && z5;
        return callImpl;
    }

    @Override // com.amazon.comms.calling.service.Call
    public void accept(Call.AcceptParams acceptParams) {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ACCEPT Call: ");
        outline107.append(log.sensitiveCallId(this.callId));
        outline107.append(" this= ");
        outline107.append(this);
        commsLogger.i(outline107.toString());
        this.internalCallOrchestrator.acceptCall(acceptParams);
        DropInControllerImpl dropInControllerImpl = this.dropInController;
        if (dropInControllerImpl != null) {
            dropInControllerImpl.initializeWithAcceptParams(acceptParams);
        }
    }

    @Override // com.amazon.comms.calling.service.Call
    public void acknowledgeCall() {
        this.callDeathTimer.cancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLastFrame(Call.Side side, EglBase.Context context, MediaParams.LastFrameClearOption lastFrameClearOption) {
        WebRTCRendererShim<?> webRTCRendererShim;
        VideoRendererEventsHandler videoRendererEventsHandler;
        CommsLogger commsLogger = log;
        commsLogger.i("clearLastFrame. side= " + side + " frameClearOption= " + lastFrameClearOption);
        if (side == Call.Side.Remote) {
            webRTCRendererShim = this.mRemoteRenderer;
            videoRendererEventsHandler = this.remoteRendererEventsHandler;
        } else {
            webRTCRendererShim = this.mLocalRenderer;
            videoRendererEventsHandler = this.localRendererEventsHandler;
        }
        CommsLogger commsLogger2 = log;
        commsLogger2.d("clearLastFrame, side:" + side + " lastFrameClearOption:" + lastFrameClearOption);
        if (webRTCRendererShim != null) {
            if (lastFrameClearOption == MediaParams.LastFrameClearOption.REINITIALIZE_SURFACE) {
                webRTCRendererShim.release();
                webRTCRendererShim.init(context, videoRendererEventsHandler);
            } else if (lastFrameClearOption != MediaParams.LastFrameClearOption.RENDER_BLACK) {
            } else {
                webRTCRendererShim.enableRenderBlack(true);
                webRTCRendererShim.enableRenderBlack(false);
            }
        }
    }

    @Override // com.amazon.comms.calling.service.Call
    public CallDetails getCallDetails() {
        return this.internalCallOrchestrator.getCallDetails();
    }

    @Override // com.amazon.comms.calling.service.Call
    public String getCallId() {
        return this.callId;
    }

    public ListenerSet<CallListener> getCallListeners() {
        return this.callListeners;
    }

    @Override // com.amazon.comms.calling.service.Call
    public EventTracer getEventTracer() {
        return this.eventTracer;
    }

    @Override // com.amazon.comms.calling.service.Call
    public SipHeaders getIncomingHeaders() {
        return this.internalCallOrchestrator.getIncomingHeaders();
    }

    public CallImplInternal getInternalCallOrchestrator() {
        return this.internalCallOrchestrator;
    }

    @Override // com.amazon.comms.calling.service.Call
    public MediaStateChangeTrigger getLastMediaStateChangeTrigger() {
        return this.lastMediaStateChangeTrigger;
    }

    @Override // com.amazon.comms.calling.service.Call
    public Participant getLocalParticipant() {
        log.d("getLocalParticipart ");
        return this.internalCallOrchestrator.getLocalParticipant();
    }

    @Override // com.amazon.comms.calling.service.Call
    public Call.VideoViewDimensions getLocalVideoViewDimensions() {
        return this.localVideoViewDimensions;
    }

    public WebRTCViewRenderer getLocalViewRenderer() {
        WebRTCRendererShim<?> webRTCRendererShim;
        if (this.localViewRenderer == null && (webRTCRendererShim = this.mLocalRenderer) != null) {
            this.localViewRenderer = new WebRTCViewRendererImpl(webRTCRendererShim);
        }
        return this.localViewRenderer;
    }

    @Override // com.amazon.comms.calling.service.Call
    public MediaStats getMediaStats() {
        return this.mediaStats;
    }

    @Override // com.amazon.comms.calling.service.Call
    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus = this.internalCallOrchestrator.getMediaStatus();
        CommsLogger commsLogger = log;
        commsLogger.i("getMediaStatus. status= " + mediaStatus);
        return mediaStatus;
    }

    @Override // com.amazon.comms.calling.service.Call
    public Call.Side getOrigin() {
        return this.internalCallOrchestrator.getCallOrigin();
    }

    @Override // com.amazon.comms.calling.service.Call
    public SipHeaders getOutgoingHeaders() {
        return this.internalCallOrchestrator.getOutgoingHeaders();
    }

    @Override // com.amazon.comms.calling.service.Call
    public String getProvider() {
        log.d("getProvider ");
        return this.internalCallOrchestrator.getProvider();
    }

    @Override // com.amazon.comms.calling.service.Call
    public Participant getRemoteParticipant() {
        log.d("getRemoteParticipant ");
        return this.internalCallOrchestrator.getRemoteParticipant();
    }

    @Override // com.amazon.comms.calling.service.Call
    public Call.VideoViewDimensions getRemoteVideoViewDimensions() {
        return this.remoteVideoViewDimensions;
    }

    public WebRTCViewRenderer getRemoteViewRenderer() {
        WebRTCRendererShim<?> webRTCRendererShim;
        if (this.remoteViewRenderer == null && (webRTCRendererShim = this.mRemoteRenderer) != null) {
            this.remoteViewRenderer = new WebRTCViewRendererImpl(webRTCRendererShim);
        }
        return this.remoteViewRenderer;
    }

    @Override // com.amazon.comms.calling.service.Call
    public Call.State getState() {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getState ");
        outline107.append(this.callStateFromUIView);
        commsLogger.d(outline107.toString());
        return this.callStateFromUIView;
    }

    @Override // com.amazon.comms.calling.service.Call
    public Call.VideoEffect getVideoEffect() {
        log.d("getVideoEffect ");
        return this.lastRequestVideoEffect;
    }

    @Override // com.amazon.comms.calling.service.Call
    public void hangup(Call.HangupRequest hangupRequest) {
        CommsLogger commsLogger = log;
        commsLogger.i("hangup " + hangupRequest);
        this.internalCallOrchestrator.hangupThisCall(hangupRequest);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hangupProcessingComplete(final HangupReason hangupReason) {
        log.i("hangupProcessingComplete ");
        setState(Call.State.Complete);
        shutdownDeathTimer();
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.4
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onHangup(CallImpl.this, hangupReason);
            }
        });
        clearRendererAndListener();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initializeRenderersIfNeeded(EglBase.Context context) {
        CommsLogger commsLogger = log;
        commsLogger.i("initializeRenderersIfNeeded. eglContext= " + context + " localRenderer= " + this.mLocalRenderer + " remoteRenderer= " + this.mRemoteRenderer);
        WebRTCRendererShim<?> webRTCRendererShim = this.mLocalRenderer;
        if (webRTCRendererShim != null) {
            webRTCRendererShim.init(context, this.localRendererEventsHandler);
        }
        WebRTCRendererShim<?> webRTCRendererShim2 = this.mRemoteRenderer;
        if (webRTCRendererShim2 != null) {
            webRTCRendererShim2.init(context, this.remoteRendererEventsHandler);
        }
    }

    @Override // com.amazon.comms.calling.service.Call
    public boolean isAccepted() {
        log.d("isAccepted");
        return this.callAccepted;
    }

    @Override // com.amazon.comms.calling.service.Call
    public boolean isCheckInCall() {
        return this.checkInCall;
    }

    @Override // com.amazon.comms.calling.service.Call
    public boolean isDropInCall() {
        return this.dropInCall;
    }

    @Override // com.amazon.comms.calling.service.Call
    public boolean isVideoRequested() {
        return this.videoRequested;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyMediaStatusUpdated(final Call.Side side, final MediaStatus mediaStatus, final MediaStateChangeTrigger mediaStateChangeTrigger) {
        CommsLogger commsLogger = log;
        commsLogger.i("notifyMediaStatusUpdated. Side= " + side);
        this.lastMediaStateChangeTrigger = mediaStateChangeTrigger;
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.6
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onMediaStatusUpdated(CallImpl.this, side, mediaStatus, mediaStateChangeTrigger);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyOnEarlyMedia() {
        log.i("notifyOnEarlyMedia");
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.1
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onEarlyMedia(CallImpl.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyOnRinging() {
        log.i("notify ringing ");
        setState(Call.State.Ringing);
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.2
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onRinging(CallImpl.this);
            }
        });
    }

    public void notifyRealTimeTextMessageReceived(byte[] bArr) {
        RealTimeTextListener realTimeTextListener = this.realTimeTextListener;
        if (realTimeTextListener != null) {
            realTimeTextListener.onRealTimeTextMessageReceived(this, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyReconnectToAppListener(final boolean z, final boolean z2, final String str) {
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.10
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onReconnect(CallImpl.this, z, z2, str);
            }
        });
    }

    @Override // com.amazon.comms.calling.service.Call
    public void notifyRinging() {
        CommsLogger commsLogger = log;
        commsLogger.d("notifyRinging. this= " + this);
        setState(Call.State.Ringing);
        this.internalCallOrchestrator.notifyOfCallRinging();
    }

    public void onBufferedAmountChange(final DataChannelEvent dataChannelEvent) {
        this.dataChannelListeners.notify(new ListenerSet.Notifier<DataChannelEventListener>() { // from class: com.amazon.comms.ringservice.CallImpl.24
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(DataChannelEventListener dataChannelEventListener) {
                dataChannelEventListener.onBufferedAmountChange(CallImpl.this, dataChannelEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCallAcceptProcessingComplete(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("onCallAcceptProcessingComplete. This= " + this + " isReconnecting= " + z);
        this.callAccepted = true;
        setState(Call.State.Active);
        this.callDeathTimer.schedule();
        if (!z) {
            CommsLogger commsLogger2 = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notifying call listeners of acceptance: ");
            outline107.append(log.sensitiveCallId(this.callId));
            commsLogger2.i(outline107.toString());
            this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.3
                @Override // com.amazon.comms.util.ListenerSet.Notifier
                public void notify(CallListener callListener) {
                    callListener.onAccepted(CallImpl.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCameraOpening(final String str) {
        this.cameraEventsListenerSet.notify(new ListenerSet.Notifier<CameraEventsListener>() { // from class: com.amazon.comms.ringservice.CallImpl.21
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CameraEventsListener cameraEventsListener) {
                cameraEventsListener.onCameraOpening(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCameraSwitchDone(final boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("onCameraSwitchDone. isFrontCamera= " + z);
        setMirroringOnSelfView(z);
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.14
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onCameraSwitchDone(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCameraSwitchError(final String str) {
        CommsLogger commsLogger = log;
        commsLogger.i("onCameraSwitchError. reason= " + str);
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.15
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onCameraSwitchError(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDtmfInserted(boolean z, String str, int i, int i2) {
        CommsLogger commsLogger = log;
        commsLogger.i("onDtmfInserted. isDtmfToneSent= " + z);
        if (z) {
            postSendDtmfTones(z, str, i, i2);
            onMediaDTMFResponse(true, "");
            return;
        }
        onMediaDTMFResponse(false, "DTMF_FAILED");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onErrorProcessingComplete(final ErrorCode errorCode, final int i, final String str) {
        log.i("errorProcessingComplete ");
        setState(Call.State.Complete);
        shutdownDeathTimer();
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.5
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onError(CallImpl.this, errorCode, i, str);
            }
        });
        clearRendererAndListener();
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.VideoRendererEventsListener
    public void onFirstFrameReceived(final Call.Side side) {
        CommsLogger commsLogger = log;
        commsLogger.i("onFirstFrameReceived. side= " + side);
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.18
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onFirstFrameReceived(side);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.VideoRendererEventsListener
    public void onFirstFrameRendered(final Call.Side side) {
        CommsLogger commsLogger = log;
        commsLogger.i("onFirstFrameRendered. side= " + side);
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.17
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onFirstFrameRendered(side);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.VideoRendererEventsListener
    public void onLocalFrameResolutionChanged(final int i, final int i2, final int i3) {
        CommsLogger commsLogger = log;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("onLocalFrameResolutionChanged. Width= ", i, " height= ", i2, " rotation= ");
        outline110.append(i3);
        commsLogger.i(outline110.toString());
        this.localVideoViewDimensions = new Call.VideoViewDimensions(i, i2, i3);
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.20
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onLocalFrameResolutionChanged(i, i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onMediaDTMFResponse(final boolean z, final String str) {
        CommsLogger commsLogger = log;
        commsLogger.i("onMediaDTMFResponse. code= " + str + " success= " + z);
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.9
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onMediaDTMFResponse(CallImpl.this, z, str);
            }
        });
    }

    public void onMessageReceived(final DataChannelEvent dataChannelEvent) {
        this.dataChannelListeners.notify(new ListenerSet.Notifier<DataChannelEventListener>() { // from class: com.amazon.comms.ringservice.CallImpl.23
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(DataChannelEventListener dataChannelEventListener) {
                dataChannelEventListener.onMessageReceived(CallImpl.this, dataChannelEvent);
            }
        });
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.VideoRendererEventsListener
    public void onRemoteFrameResolutionChanged(final int i, final int i2, final int i3) {
        CommsLogger commsLogger = log;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("onRemoteFrameResolutionChanged. Width= ", i, " height= ", i2, " rotation= ");
        outline110.append(i3);
        commsLogger.i(outline110.toString());
        this.remoteVideoViewDimensions = new Call.VideoViewDimensions(i, i2, i3);
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.19
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onRemoteFrameResolutionChanged(i, i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onRemoteParticipantUpdated() {
        log.i("onRemoteParticipantUpdated");
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.16
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onRemoteParticipantUpdated(CallImpl.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSignalingDTMFResponse(final int i, final String str) {
        CommsLogger commsLogger = log;
        commsLogger.i("onSignalingDTMFResponse. status= " + i);
        this.callListeners.notify(new ListenerSet.Notifier<CallListener>() { // from class: com.amazon.comms.ringservice.CallImpl.8
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(CallListener callListener) {
                callListener.onSignalingDTMFResponse(CallImpl.this, i, str);
            }
        });
    }

    public void onStateChange(final DataChannelEvent dataChannelEvent) {
        this.dataChannelListeners.notify(new ListenerSet.Notifier<DataChannelEventListener>() { // from class: com.amazon.comms.ringservice.CallImpl.22
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(DataChannelEventListener dataChannelEventListener) {
                dataChannelEventListener.onStateChange(CallImpl.this, dataChannelEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onVideoEffectChanged(Call.VideoEffect videoEffect) {
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.12
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onVideoEffectChanged(CallImpl.this);
            }
        });
    }

    public void postCallConfigured(Context context) {
        prepareRenderersIfNeeded(context);
        this.internalCallOrchestrator.callConfigurationFinished(this.mLocalRenderer, this.mRemoteRenderer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postSendDtmfTones(boolean z, final String str, final int i, final int i2) {
        CommsLogger commsLogger = log;
        commsLogger.i("postSendDtmfTones. isDtmfToneSent= " + z);
        if (!z) {
            return;
        }
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.7
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onDtmfTonesSent(CallImpl.this, str, i, i2);
            }
        });
    }

    public void prepareRenderersIfNeeded(Context context) {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("prepareRenderersIfNeeded. VideoCapable= ");
        outline107.append(this.videoCapable);
        outline107.append(" context= ");
        outline107.append(context);
        commsLogger.i(outline107.toString());
        if (!this.videoCapable) {
            return;
        }
        if (context == null) {
            log.e("Cannot initialize video renderers as application context is null!");
            return;
        }
        if (this.useTextureViewForRendering) {
            this.mLocalRenderer = new TextureViewRendererShim(context, null);
            this.mRemoteRenderer = new TextureViewRendererShim(context, null);
        } else {
            this.mLocalRenderer = new SurfaceViewRendererShim(SurfaceViewRendererShim.ShapeMode.Dynamic, WebRTCRendererShim.Shape.Rectangle, this.pipSurfaceViewBorderRadius, context, (AttributeSet) null, "self_view: ");
            if (this.translucentVideoRendererSupported) {
                this.mRemoteRenderer = new SurfaceViewRendererShim(SurfaceViewRendererShim.ShapeMode.Dynamic, WebRTCRendererShim.Shape.Rectangle, this.pipSurfaceViewBorderRadius, context, (AttributeSet) null, "remote_view: ");
            } else {
                this.mRemoteRenderer = new SurfaceViewRendererShim(SurfaceViewRendererShim.ShapeMode.Static, WebRTCRendererShim.Shape.Rectangle, 0.0f, context, (AttributeSet) null, "remote_view: ");
            }
        }
        this.localRendererEventsHandler = new VideoRendererEventsHandler(Call.Side.Local, this.mLocalRenderer, this, this.mainThreadHandler, this.shouldSimulateFirstFrameReceived);
        this.remoteRendererEventsHandler = new VideoRendererEventsHandler(Call.Side.Remote, this.mRemoteRenderer, this, this.mainThreadHandler, this.shouldSimulateFirstFrameReceived);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void registerCallListener(CallListener callListener) {
        this.callListeners.add(callListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void registerCameraEventsListener(CameraEventsListener cameraEventsListener) {
        this.cameraEventsListenerSet.add(cameraEventsListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void registerCameraPreSwitchListener(CameraPreSwitchListener cameraPreSwitchListener) {
        this.cameraPreSwitchListenerSet.add(cameraPreSwitchListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void registerDataChannelEventListener(DataChannelEventListener dataChannelEventListener) {
        this.dataChannelListeners.add(dataChannelEventListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void registerMediaListener(MediaListener mediaListener) {
        this.mediaListeners.add(mediaListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void registerRealTimeTextListener(RealTimeTextListener realTimeTextListener) {
        if (this.realTimeTextListener != null) {
            log.w("RealTimeText Lisetner is already registered.");
        } else {
            this.realTimeTextListener = realTimeTextListener;
        }
    }

    @Override // com.amazon.comms.calling.service.Call
    public void sendData(DataChannelDTO dataChannelDTO) {
        this.internalCallOrchestrator.sendData(dataChannelDTO);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void sendDtmfTones(String str, int i, int i2) {
        log.i("sendDtmfTones");
        if (this.internalCallOrchestrator.getState() != Call.State.Active) {
            onMediaDTMFResponse(false, "CALL_NOT_IN_PROGRESS");
        } else {
            this.internalCallOrchestrator.sendDtmfTones(str, i, i2);
        }
    }

    @Override // com.amazon.comms.calling.service.Call
    public void sendRealTimeTextData(byte[] bArr) {
        this.internalCallOrchestrator.sendRealTimeTextData(bArr);
    }

    @Override // com.amazon.comms.calling.service.Call
    public synchronized void setLocalAudioState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("setLocalAudioState. audioEnabled= " + z);
        this.internalCallOrchestrator.setLocalAudioState(z);
    }

    @Override // com.amazon.comms.calling.service.Call
    public synchronized void setLocalMediaCapability(boolean z, boolean z2) {
        CommsLogger commsLogger = log;
        commsLogger.i("setLocalMediaCapability: audio= " + z + " video= " + z2);
        this.internalCallOrchestrator.setLocalMediaCapability(z, z2);
    }

    @Override // com.amazon.comms.calling.service.Call
    public synchronized void setLocalVideoPermitted(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("setLocalVideoPermitted. videoPermitted= " + z);
        this.internalCallOrchestrator.setLocalVideoPermitted(z);
    }

    @Override // com.amazon.comms.calling.service.Call
    public synchronized void setLocalVideoState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("setLocalVideoState. videoEnabled= " + z);
        this.internalCallOrchestrator.setLocalVideoState(z);
    }

    public void setMediaConstraints(Map<String, Integer> map) {
        log.i("setMediaConstraints");
        if (map == null || map.isEmpty()) {
            return;
        }
        this.internalCallOrchestrator.setMediaConstraints(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void setMirroringOnSelfView(boolean z) {
        if (this.mLocalRenderer != null) {
            CommsLogger commsLogger = log;
            commsLogger.i("SelfView setMirror:" + z);
            this.mLocalRenderer.setMirror(z);
            return;
        }
        log.w("LocalRenderer not initialized, ignoring mirroring request");
    }

    public void setSystemCameraState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("setSystemCameraState = " + z);
        this.internalCallOrchestrator.setSystemCameraState(z);
    }

    public void setSystemMediaState(boolean z) {
        CommsLogger commsLogger = log;
        commsLogger.i("setSystemMediaState = " + z);
        this.internalCallOrchestrator.setSystemMediaState(z);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void setVideoEffect(Call.VideoEffect videoEffect, double d) {
        CommsLogger commsLogger = log;
        commsLogger.i("setVideoEffect. effect= " + videoEffect + " transitionDuration= " + d);
        this.lastRequestVideoEffect = videoEffect;
        this.internalCallOrchestrator.setVideoEffect(videoEffect, d);
    }

    public void setVolume(float f) {
        this.internalCallOrchestrator.setVolume(f);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void switchCamera() {
        switchCamera(null);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void switchCameraPostSetup(String str) {
        if (this.callStateFromUIView.equals(Call.State.Complete)) {
            return;
        }
        this.internalCallOrchestrator.switchCamera(str);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void transferAudioToOutputDevice(int i) {
        WebRTCAudioUtilsShim.setOutputAudioDevice(i);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void unregisterCallListener(CallListener callListener) {
        this.callListeners.remove(callListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void unregisterCameraEventsListener(CameraEventsListener cameraEventsListener) {
        this.cameraEventsListenerSet.remove(cameraEventsListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void unregisterCameraPreSwitchListener(CameraPreSwitchListener cameraPreSwitchListener) {
        this.cameraPreSwitchListenerSet.remove(cameraPreSwitchListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void unregisterDataChannelEventListener(DataChannelEventListener dataChannelEventListener) {
        this.dataChannelListeners.remove(dataChannelEventListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void unregisterMediaListener(MediaListener mediaListener) {
        this.mediaListeners.remove(mediaListener);
    }

    @Override // com.amazon.comms.calling.service.Call
    public void unregisterRealTimeTextListener(RealTimeTextListener realTimeTextListener) {
        this.realTimeTextListener = null;
    }

    @Override // com.amazon.comms.calling.service.Call
    public void updateCallInfo(Call.CallInfoParams callInfoParams) {
        log.i("updateCallInfo ");
        this.internalCallOrchestrator.updateParticipantInfo(callInfoParams);
        this.internalCallOrchestrator.updateCallStateTransitionTimeouts(callInfoParams.getStateTransitionTimeouts());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateMediaStatsData(MediaStats mediaStats) {
        log.i("Notifying call listeners of media stats update");
        this.mediaStats = mediaStats;
        this.mediaListeners.notify(new ListenerSet.Notifier<MediaListener>() { // from class: com.amazon.comms.ringservice.CallImpl.11
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(MediaListener mediaListener) {
                mediaListener.onMediaStatsUpdated(CallImpl.this);
            }
        });
    }

    @Override // com.amazon.comms.calling.service.Call
    /* renamed from: getDropInController  reason: collision with other method in class */
    public DropInControllerImpl mo3244getDropInController() {
        return this.dropInController;
    }

    @Override // com.amazon.comms.calling.service.Call
    public void switchCamera(String str) {
        if (this.cameraPreSwitchListenerSet.size() > 0) {
            this.cameraPreSwitchListenerSet.notify(new ListenerSet.Notifier<CameraPreSwitchListener>() { // from class: com.amazon.comms.ringservice.CallImpl.13
                @Override // com.amazon.comms.util.ListenerSet.Notifier
                public void notify(CameraPreSwitchListener cameraPreSwitchListener) {
                    cameraPreSwitchListener.onSwitchCamera();
                }
            });
        } else {
            this.internalCallOrchestrator.switchCamera(str);
        }
    }
}
