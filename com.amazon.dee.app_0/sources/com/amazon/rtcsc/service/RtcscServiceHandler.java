package com.amazon.rtcsc.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.view.Surface;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.rtcmedia.util.RTCContextUtils;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscConstants;
import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter;
import com.amazon.rtcsc.interfaces.IRtcscDataChannelListener;
import com.amazon.rtcsc.interfaces.IRtcscEventListener;
import com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener;
import com.amazon.rtcsc.interfaces.IRtcscServiceHandler;
import com.amazon.rtcsc.interfaces.IRtcscViewRendererListener;
import com.amazon.rtcsc.interfaces.RtcscAppDisconnectCode;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.interfaces.RtcscScalingType;
import com.amazon.rtcsc.interfaces.RtcscVideoEffect;
import com.amazon.rtcsc.interfaces.RtcscViewDirection;
import com.amazon.rtcsc.service.appclient.RtcscAppClientCommon;
import com.amazon.rtcsc.service.appclient.RtcscAppClientJNIWrapper;
import com.amazon.rtcsc.service.appclient.RtcscAppClientNotifier;
import com.amazon.rtcsc.service.capabilityagentclient.RtcscClientJNIWrapper;
import com.amazon.rtcsc.service.datachannel.RtcscDataChannelWrapper;
import com.amazon.rtcsc.service.deviceconfiguration.DeviceConfigMapper;
import com.amazon.rtcsc.service.screencapturer.RtcscScreenCapturerWrapper;
import com.amazon.rtcsc.service.surfaceview.RtcscSurfaceViewWrapper;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.RTCAppInfo;
import com.amazon.rtcsc.wrappers.RTCSCErrorCode;
import com.amazon.rtcsc.wrappers.RTCSCManagerInterface;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes13.dex */
public class RtcscServiceHandler extends IRtcscServiceHandler.Stub {
    private static final String RTCSC_CUSTOM_METRICS_PUBLISHER_ADAPATER_APP_IDENTIFIER = "RTCSessionController";
    private static final String RTC_LIB = "rtcsc-jni";
    private static final List<String> startDirectives = Arrays.asList("InitiateSession", RtcscConstants.RTCSC_DIRECTIVE_NAME_INITIATE_SESSION_WITH_OFFER);
    private RtcscClientDeathWatcher mClientDeathWatcher;
    private RtcscDataChannelWrapper mDataChannelWrapper;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscServiceHandler.class);
    private MetricsFactory mMetricsFactory;
    private RTCAppInfo mRtcCustomMetricsPublisherAdapterAppInfo;
    private RtcscAppClientCommon mRtcscAppClientCommon;
    private RtcscAppClientJNIWrapper mRtcscAppClientJNIWrapper;
    private RtcscAppClientNotifier mRtcscAppClientNotifier;
    private RtcscAudioManager mRtcscAudioManager;
    private RtcscClientJNIWrapper mRtcscClientJNIWrapper;
    private RtcscCustomMetricsPublisherAdapter mRtcscCustomMetricsPublisherAdapter;
    private RtcscScreenCapturerWrapper mScreenCapturerWrapper;
    private RtcscSurfaceViewWrapper mSurfaceViewWrapper;
    private RTCSCManagerInterface rtcManager;

    static {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            try {
                StrictMode.allowThreadDiskReads();
                System.loadLibrary(RTC_LIB);
                Log.i("RtcscServiceHandler", "RTC Native Library loaded..");
            } catch (UnsatisfiedLinkError e) {
                Log.e("RtcscServiceHandler", "UnsatisfiedLinkError loading RTC Native Lib: " + e.getMessage());
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RtcscServiceHandler(Context context) {
        RTCContextUtils.initialize(context);
        this.rtcManager = RTCSCManagerInterface.createAndInitInstance(DeviceConfigMapper.getDeviceConfigInstance(context));
        this.mRtcscAppClientCommon = new RtcscAppClientCommon(this);
        this.mRtcscAudioManager = new RtcscAudioManager(context);
        this.mRtcscClientJNIWrapper = new RtcscClientJNIWrapper(this.rtcManager.getRTCClient(), this.mRtcscAppClientCommon);
        this.mRtcscAppClientJNIWrapper = new RtcscAppClientJNIWrapper(this.rtcManager.getRTCAppClientManager(), this.rtcManager, this.mRtcscAppClientCommon);
        this.mSurfaceViewWrapper = new RtcscSurfaceViewWrapper();
        this.mScreenCapturerWrapper = new RtcscScreenCapturerWrapper();
        this.mDataChannelWrapper = RtcscDataChannelWrapper.createRtcscDataChannelWrapper();
        this.mRtcscAppClientNotifier = new RtcscAppClientNotifier(context, this.mRtcscAppClientJNIWrapper);
        this.mMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
        this.mRtcscCustomMetricsPublisherAdapter = new RtcscCustomMetricsPublisherAdapter(this.mMetricsFactory);
        this.mRtcCustomMetricsPublisherAdapterAppInfo = new RTCAppInfo();
        this.mRtcCustomMetricsPublisherAdapterAppInfo.setAppIdentifier("RTCSessionController");
        RTCSCErrorCode registerRTCCustomMetricsPublisherAdapter = this.rtcManager.registerRTCCustomMetricsPublisherAdapter(this.mRtcCustomMetricsPublisherAdapterAppInfo, this.mRtcscCustomMetricsPublisherAdapter);
        if (registerRTCCustomMetricsPublisherAdapter != RTCSCErrorCode.SUCCESS) {
            this.mLog.i(String.format(Locale.US, "registerRTCCustomMetricsPublisherAdapter returned from Native layer with errorCode : %s.", RtcscErrorCode.valueOf(registerRTCCustomMetricsPublisherAdapter.toString())));
        }
        this.mClientDeathWatcher = new RtcscClientDeathWatcher(this);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void acceptSession(String str) {
        this.mLog.i(String.format(Locale.US, "acceptSession called. SessionId: %s", str));
        this.mRtcscAppClientJNIWrapper.acceptSession(str);
    }

    public void cleanAppClient(RtcscAppInfo rtcscAppInfo) {
        this.mLog.i(String.format(Locale.US, "cleanAppClient: %s domain just died. End any ongoing sessions for domain.", rtcscAppInfo.getAppIdentifier()));
        Iterator<String> it2 = this.mRtcscAppClientCommon.getSessionsForDomain(rtcscAppInfo.getAppIdentifier()).iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            this.mLog.i(String.format(Locale.US, "cleanAppClient: Invoking disconnectSession for sessionId %s", next));
            disconnectSession(next, RtcscAppDisconnectCode.SESSION_DOMAIN_ERROR);
        }
        this.mRtcscAppClientJNIWrapper.unregisterRtcscAppClientListener(rtcscAppInfo);
    }

    public void cleanUp() {
        this.mLog.i("cleanUp called. Proceeding to release native RTCManager instance.");
        this.rtcManager.unregisterRTCCustomMetricsPublisherAdapter(this.mRtcCustomMetricsPublisherAdapterAppInfo);
        RTCSCManagerInterface.releaseInstance();
        this.rtcManager = null;
        this.mRtcscAppClientJNIWrapper = null;
        this.mRtcscClientJNIWrapper = null;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void clearImage(String str, String str2) {
        this.mSurfaceViewWrapper.clearImage(str, str2);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void disconnectSession(String str, RtcscAppDisconnectCode rtcscAppDisconnectCode) {
        this.mLog.i(String.format(Locale.US, "disconnectSession called. SessionId: %s, AppDisconnectCode: %s", str, rtcscAppDisconnectCode));
        this.mRtcscAppClientJNIWrapper.disconnectSession(str, rtcscAppDisconnectCode);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public int getAPIVersion() {
        return 3;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void handleDirective(String str, String str2) {
        this.mLog.i(String.format(Locale.US, "handleDirective: Directive Name: %s", str));
        this.mLog.d(String.format(Locale.US, "handleDirective: Directive Payload: %s", str2));
        PayloadObject payloadObject = new PayloadObject(str2);
        if (startDirectives.contains(str)) {
            this.mRtcscAppClientCommon.putSessionIdToDomainMapping(payloadObject.getSessionId(), payloadObject.getSessionDomain());
        }
        this.mRtcscClientJNIWrapper.handleDirective(str, str2, payloadObject);
        if (startDirectives.contains(str)) {
            this.mRtcscAppClientNotifier.notifyAppClient(str, payloadObject);
        }
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void initRenderer(String str, String str2, IRtcscViewRendererListener iRtcscViewRendererListener, RtcscViewDirection rtcscViewDirection, String str3) {
        this.mSurfaceViewWrapper.init(str, str2, iRtcscViewRendererListener, rtcscViewDirection, str3);
    }

    public void onAudioEnabledForSession() {
        this.mLog.i("onAudioEnabledForSession called.");
        this.mRtcscAudioManager.setCommunicationsMode();
    }

    public void onLastSessionRemoved() {
        this.mLog.i("onLastSessionRemoved called.");
        this.mRtcscAudioManager.restoreAudioMode();
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void onRendererLayout(String str, String str2, boolean z, int i, int i2, int i3, int i4) {
        this.mSurfaceViewWrapper.onLayout(str, str2, z, i, i2, i3, i4);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void onRendererMeasure(String str, String str2, int i, int i2) {
        this.mSurfaceViewWrapper.onMeasure(str, str2, i, i2);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void putScreenCapturerData(String str, Intent intent) {
        this.mScreenCapturerWrapper.putScreenCapturerData(str, intent);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void registerCAEventListener(RtcscAppInfo rtcscAppInfo, IRtcscEventListener iRtcscEventListener) {
        this.mLog.i("registerCAEventListener called.");
        this.mRtcscClientJNIWrapper.registerEventListener(rtcscAppInfo, iRtcscEventListener);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void registerClientDeathListener(RtcscAppInfo rtcscAppInfo, IBinder iBinder) {
        this.mLog.i(String.format(Locale.US, "registerClientDeathListener called for %s domain and clientDeathListener %s.", rtcscAppInfo.getAppIdentifier(), iBinder));
        this.mClientDeathWatcher.register(iBinder, rtcscAppInfo);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public boolean registerDataChannelListener(String str, IRtcscDataChannelListener iRtcscDataChannelListener) {
        this.mLog.i(String.format(Locale.US, "registerDataChannelListener called. SessionId: %s", str));
        return this.mDataChannelWrapper.registerDataChannelListener(str, iRtcscDataChannelListener);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void registerRtcscAppClientListener(RtcscAppInfo rtcscAppInfo, IRtcscAppClientListener iRtcscAppClientListener) {
        this.mLog.i(String.format(Locale.US, "registerRtcscAppClientListener called with sessionDomain as %s.", rtcscAppInfo.getAppIdentifier()));
        this.mRtcscAppClientJNIWrapper.registerRtcscAppClientListener(rtcscAppInfo, iRtcscAppClientListener);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void registerRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo, IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) {
        this.mLog.i(String.format(Locale.US, "registerRtcscCustomMetricsPublisherAdapter called for app client: %s.", rtcscAppInfo.getAppIdentifier()));
        this.mRtcscAppClientJNIWrapper.registerRtcscCustomMetricsPublisherAdapter(rtcscAppInfo, iRtcscCustomMetricsPublisherAdapter);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void registerScreenCapturerListener(String str, IRtcscScreenCapturerListener iRtcscScreenCapturerListener) {
        this.mScreenCapturerWrapper.registerScreenCapturerListener(str, iRtcscScreenCapturerListener);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void releaseRenderer(String str, String str2) {
        this.mSurfaceViewWrapper.release(str, str2);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public boolean sendData(String str, String str2, byte[] bArr, boolean z) {
        this.mLog.i(String.format(Locale.US, "sendData called. SessionId: %s. Label: %s", str, str2));
        return this.mDataChannelWrapper.sendData(str, str2, bArr, z);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setEnableHardwareScaler(String str, String str2, boolean z) {
        this.mSurfaceViewWrapper.setEnableHardwareScaler(str, str2, z);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setLocalAudioState(String str, boolean z) {
        this.mLog.i(String.format(Locale.US, "setLocalAudioState called. SessionId: %s, audioEnabled: %b", str, Boolean.valueOf(z)));
        this.mRtcscAppClientJNIWrapper.setLocalAudioState(str, z);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setLocalVideoState(String str, boolean z) {
        this.mLog.i(String.format(Locale.US, "setLocalVideoState called. SessionId: %s, videoEnabled: %b", str, Boolean.valueOf(z)));
        this.mRtcscAppClientJNIWrapper.setLocalVideoState(str, z);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setMirror(String str, String str2, boolean z) {
        this.mSurfaceViewWrapper.setMirror(str, str2, z);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setRemoteAudioState(String str, boolean z) {
        this.mLog.i(String.format(Locale.US, "setRemoteAudioState called. SessionId: %s, audioEnabled: %b", str, Boolean.valueOf(z)));
        this.mRtcscAppClientJNIWrapper.setRemoteAudioState(str, z);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setScalingType(String str, String str2, RtcscScalingType rtcscScalingType) {
        this.mSurfaceViewWrapper.setScalingType(str, str2, rtcscScalingType);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setScreenCapturerDimensions(String str, int i, int i2) {
        this.mScreenCapturerWrapper.setScreenCapturerDimensions(str, i, i2);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void setVideoEffect(String str, RtcscVideoEffect rtcscVideoEffect, int i) {
        this.mLog.i(String.format(Locale.US, "setVideoEffect called. SessionId: %s, VideoEffect: %s, videoEffectDurationMs: %d", str, rtcscVideoEffect, Integer.valueOf(i)));
        this.mRtcscAppClientJNIWrapper.setVideoEffect(str, rtcscVideoEffect, i);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void signalReadyForSession(String str) {
        this.mLog.i(String.format(Locale.US, "signalReadyForSession called. SessionId: %s", str));
        this.mRtcscAppClientJNIWrapper.signalReadyForSession(str);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void surfaceChanged(String str, String str2, Surface surface, int i, int i2, int i3) {
        this.mSurfaceViewWrapper.surfaceChanged(str, str2, surface, i, i2, i3);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void surfaceCreated(String str, String str2, Surface surface) {
        this.mSurfaceViewWrapper.surfaceCreated(str, str2, surface);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void surfaceDestroyed(String str, String str2, Surface surface) {
        this.mSurfaceViewWrapper.surfaceDestroyed(str, str2, surface);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void switchCamera(String str, String str2) {
        this.mLog.i(String.format(Locale.US, "switchCamera called. SessionId: %s, cameraName: %s", str, str2));
        this.mRtcscAppClientJNIWrapper.switchCamera(str, str2);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void unregisterCAEventListener(RtcscAppInfo rtcscAppInfo) {
        this.mLog.i("unregisterCAEventListener called.");
        this.mRtcscClientJNIWrapper.unregisterEventListener(rtcscAppInfo);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public boolean unregisterDataChannelListener(String str) {
        this.mLog.i(String.format(Locale.US, "unregisterDataChannelListener called. SesssionId: %s", str));
        return this.mDataChannelWrapper.unregisterDataChannelListener(str);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void unregisterRtcscAppClientListener(RtcscAppInfo rtcscAppInfo) {
        this.mLog.i(String.format(Locale.US, "unregisterRtcscAppClientListener called with sessionDomain as %s.", rtcscAppInfo.getAppIdentifier()));
        this.mClientDeathWatcher.deregister(rtcscAppInfo);
        this.mRtcscAppClientJNIWrapper.unregisterRtcscAppClientListener(rtcscAppInfo);
        if (!this.mRtcscAppClientCommon.isAnyDomainRegistered()) {
            this.mRtcscAudioManager.restoreAudioMode();
        }
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void unregisterRtcscCustomMetricsPublisherAdapter(RtcscAppInfo rtcscAppInfo) {
        this.mLog.i(String.format(Locale.US, "unregisterRtcscCustomMetricsPublisherAdapter called for app client: %s.", rtcscAppInfo.getAppIdentifier()));
        this.mRtcscAppClientJNIWrapper.unregisterRtcscCustomMetricsPublisherAdapter(rtcscAppInfo);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscServiceHandler
    public void unregisterScreenCapturerListener(String str) {
        this.mScreenCapturerWrapper.unregisterScreenCapturerListener(str);
    }
}
