package com.amazon.rtcsc.appclient;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.amazon.rtcsc.common.AsyncServiceConnectionManager;
import com.amazon.rtcsc.common.RtcscClientHandler;
import com.amazon.rtcsc.common.ServiceConnectionManager;
import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter;
import com.amazon.rtcsc.interfaces.IRtcscDataChannelListener;
import com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener;
import com.amazon.rtcsc.interfaces.RtcscAppDisconnectCode;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.interfaces.RtcscVideoEffect;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.util.List;
import java.util.Locale;
import lombok.NonNull;
/* loaded from: classes13.dex */
public abstract class RtcscAppClient {
    private static final String AVSCA_SERVICE_CLASS_NAME = "com.amazon.rtcsc.capabilityagent.avs.RtcscCapabilityAgentServiceAVS";
    private static final String AVSCA_SERVICE_PACKAGE_NAME = "com.amazon.dee.app";
    private static final int SERVICE_VERSION_2 = 2;
    private static final int SERVICE_VERSION_3 = 3;
    private static RtcscClientHandler mRtcscClientHandler;
    private final Context mContext;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscAppClient.class);
    private RtcscAppInfo mRtcscAppInfo = null;
    private int mRtcscServiceVersion = 1;

    public RtcscAppClient(@NonNull Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("context is null");
    }

    public static RtcscClientHandler getClientHandler() {
        RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            return rtcscClientHandler;
        }
        throw new IllegalStateException("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
    }

    private boolean isServicePresent(Intent intent) {
        this.mLog.i("isServicePresent");
        List<ResolveInfo> queryIntentServices = this.mContext.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            this.mLog.w("No service found that matches the provided Intent");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerClientDeathListener() {
        if (this.mRtcscServiceVersion >= 2) {
            mRtcscClientHandler.registerClientDeathListener(this.mRtcscAppInfo);
        }
    }

    public RtcscErrorCode acceptSession(@NonNull String str) {
        if (str != null) {
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("acceptSession called for session with id: " + str);
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            }
            return rtcscClientHandler.acceptSession(str);
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode disconnectSession(@NonNull String str, @NonNull RtcscAppDisconnectCode rtcscAppDisconnectCode) {
        if (str != null) {
            if (rtcscAppDisconnectCode != null) {
                RtcscLogger rtcscLogger = this.mLog;
                rtcscLogger.i("disconnectSession called for session with id: " + str);
                RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
                if (rtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return RtcscErrorCode.NULL_CLIENT_HANDLER;
                }
                return rtcscClientHandler.disconnectSession(str, rtcscAppDisconnectCode);
            }
            throw new IllegalArgumentException("appDisconnectCode is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    protected abstract void onRtcscServiceConnected();

    protected abstract void onRtcscServiceDisconnected();

    public RtcscErrorCode putScreenCapturerData(@NonNull String str, @NonNull Intent intent) {
        if (str != null) {
            if (intent != null) {
                this.mLog.i(String.format(Locale.US, "putScreenCapturerData called for sessionId: %s", str));
                if (mRtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return RtcscErrorCode.NULL_CLIENT_HANDLER;
                } else if (str.trim().isEmpty()) {
                    this.mLog.e("sessionId should be a valid non-empty string");
                    return RtcscErrorCode.EMPTY_SESSION_ID;
                } else {
                    return mRtcscClientHandler.putScreenCapturerData(str, intent);
                }
            }
            throw new IllegalArgumentException("mediaProjectionPermissionData is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public boolean registerDataChannelListener(@NonNull String str, @NonNull IRtcscDataChannelListener iRtcscDataChannelListener) {
        if (str != null) {
            if (iRtcscDataChannelListener != null) {
                this.mLog.i(String.format("registerDataChannelListener called for sessionId: %s", str));
                RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
                if (rtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return false;
                }
                return rtcscClientHandler.registerDataChannelListener(str, iRtcscDataChannelListener);
            }
            throw new IllegalArgumentException("listener is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode registerRtcscAppClientListener(@NonNull RtcscAppInfo rtcscAppInfo, @NonNull IRtcscAppClientListener iRtcscAppClientListener) {
        if (rtcscAppInfo != null) {
            if (iRtcscAppClientListener != null) {
                this.mLog.i(String.format("registerRtcscAppClientListener called with SessionDomain : %s", rtcscAppInfo.getAppIdentifier()));
                this.mRtcscAppInfo = rtcscAppInfo;
                Intent className = new Intent().setClassName("com.amazon.dee.app", AVSCA_SERVICE_CLASS_NAME);
                if (isServicePresent(className)) {
                    this.mLog.i("Starting AVS CapabilityAgent service");
                    this.mContext.startService(className);
                }
                if (mRtcscClientHandler == null) {
                    mRtcscClientHandler = setupRtcscClientHandler();
                }
                return mRtcscClientHandler.registerRtcscAppClientListener(rtcscAppInfo, iRtcscAppClientListener);
            }
            throw new IllegalArgumentException("rtcAppClientListener is null");
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode registerRtcscCustomMetricsPublisherAdapter(@NonNull RtcscAppInfo rtcscAppInfo, @NonNull IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) {
        if (rtcscAppInfo != null) {
            if (iRtcscCustomMetricsPublisherAdapter != null) {
                this.mLog.i("registerRtcscCustomMetricsPublisherAdapter called");
                RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
                if (rtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return RtcscErrorCode.NULL_CLIENT_HANDLER;
                }
                return rtcscClientHandler.registerRtcscCustomMetricsPublisherAdapter(rtcscAppInfo, iRtcscCustomMetricsPublisherAdapter);
            }
            throw new IllegalArgumentException("rtcCustomMetricsPublisherAdapter is null");
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode registerScreenCapturerListener(@NonNull String str, @NonNull IRtcscScreenCapturerListener iRtcscScreenCapturerListener) {
        if (str != null) {
            if (iRtcscScreenCapturerListener != null) {
                this.mLog.i(String.format(Locale.US, "registerScreenCapturerListener called for sessionId: %s", str));
                if (mRtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return RtcscErrorCode.NULL_CLIENT_HANDLER;
                } else if (str.trim().isEmpty()) {
                    this.mLog.e("sessionId should be a valid non-empty string");
                    return RtcscErrorCode.EMPTY_SESSION_ID;
                } else {
                    return mRtcscClientHandler.registerScreenCapturerListener(str, iRtcscScreenCapturerListener);
                }
            }
            throw new IllegalArgumentException("listener is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public boolean sendData(@NonNull String str, @NonNull String str2, @NonNull byte[] bArr, boolean z) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("label is null");
            }
            if (bArr != null) {
                this.mLog.i(String.format("sendData called for sessionId: %s, Label: %s", str, str2));
                this.mLog.d(String.format("Data Length: %d, isBinary: %s", Integer.valueOf(bArr.length), Boolean.valueOf(z)));
                RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
                if (rtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return false;
                }
                return rtcscClientHandler.sendData(str, str2, bArr, z);
            }
            throw new IllegalArgumentException("data is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setLocalAudioState(@NonNull String str, boolean z) {
        if (str != null) {
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("setLocalAudioState called for session with id: " + str + " with audioEnabled: " + z);
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            }
            return rtcscClientHandler.setLocalAudioState(str, z);
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setLocalVideoState(@NonNull String str, boolean z) {
        if (str != null) {
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("setLocalVideoState called for session with id: " + str + " with videoEnabled: " + z);
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            }
            return rtcscClientHandler.setLocalVideoState(str, z);
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setRemoteAudioState(@NonNull String str, boolean z) {
        if (str != null) {
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("setRemoteAudioState called for session with id: " + str + " with audioEnabled: " + z);
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            } else if (this.mRtcscServiceVersion < 3) {
                this.mLog.w("setRemoteAudioState API isn't available in current RTCSC Service.");
                return RtcscErrorCode.API_NOT_AVAILABLE;
            } else {
                return rtcscClientHandler.setRemoteAudioState(str, z);
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setScreenCapturerDimensions(@NonNull String str, int i, int i2) {
        if (str != null) {
            this.mLog.i(String.format(Locale.US, "setScreenCapturerDimensions called for sessionId: %s with width: %d and height: %d", str, Integer.valueOf(i), Integer.valueOf(i2)));
            if (mRtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            } else if (str.trim().isEmpty()) {
                this.mLog.e("sessionId should be a valid non-empty string");
                return RtcscErrorCode.EMPTY_SESSION_ID;
            } else {
                return mRtcscClientHandler.setScreenCapturerDimensions(str, i, i2);
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setVideoEffect(@NonNull String str, @NonNull RtcscVideoEffect rtcscVideoEffect, int i) {
        if (str != null) {
            if (rtcscVideoEffect != null) {
                this.mLog.i(String.format(Locale.US, "setVideoEffect called for session with id: %s", str));
                if (rtcscVideoEffect != RtcscVideoEffect.FROST_EFFECT && rtcscVideoEffect != RtcscVideoEffect.UNKNOWN_EFFECT) {
                    RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
                    if (rtcscClientHandler == null) {
                        this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                        return RtcscErrorCode.NULL_CLIENT_HANDLER;
                    }
                    return rtcscClientHandler.setVideoEffect(str, rtcscVideoEffect, i);
                }
                throw new IllegalArgumentException("AppClients are not expected to call setVideoEffect() with effect FROST_EFFECT OR UNKNOWN_EFFECT.");
            }
            throw new IllegalArgumentException("videoEffect is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    RtcscClientHandler setupRtcscClientHandler() {
        AsyncServiceConnectionManager asyncServiceConnectionManager = new AsyncServiceConnectionManager();
        asyncServiceConnectionManager.addCallback(new ServiceConnectionManager.Callback() { // from class: com.amazon.rtcsc.appclient.RtcscAppClient.1
            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onBind(boolean z) {
                RtcscLogger rtcscLogger = RtcscAppClient.this.mLog;
                rtcscLogger.i("onBind callback received. bindSuccess value: " + z);
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            @Deprecated
            public void onServiceConnected() {
                RtcscAppClient.this.mLog.i("onServiceConnected callback received");
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onServiceDisconnected() {
                RtcscAppClient.this.mLog.i("onServiceDisconnected callback received");
                RtcscAppClient.this.onRtcscServiceDisconnected();
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onUnbind() {
                RtcscAppClient.this.mLog.i("onUnbind callback received");
            }

            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onServiceConnected(int i) {
                RtcscLogger rtcscLogger = RtcscAppClient.this.mLog;
                rtcscLogger.i("onServiceConnected callback received with Service Version : " + i);
                RtcscAppClient.this.mRtcscServiceVersion = i;
                RtcscAppClient.this.registerClientDeathListener();
                RtcscAppClient.this.onRtcscServiceConnected();
            }
        });
        return new RtcscClientHandler(this.mContext, asyncServiceConnectionManager);
    }

    public RtcscErrorCode signalReadyForSession(@NonNull String str) {
        if (str != null) {
            RtcscLogger rtcscLogger = this.mLog;
            rtcscLogger.i("signalReadyForSession called for session with id: " + str);
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            }
            return rtcscClientHandler.signalReadyForSession(str);
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode switchCamera(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 != null) {
                RtcscLogger rtcscLogger = this.mLog;
                rtcscLogger.i("switchCamera called for session with id: " + str + " with cameraName: " + str2);
                RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
                if (rtcscClientHandler == null) {
                    this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                    return RtcscErrorCode.NULL_CLIENT_HANDLER;
                }
                return rtcscClientHandler.switchCamera(str, str2);
            }
            throw new IllegalArgumentException("cameraName is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public boolean unregisterDataChannelListener(@NonNull String str) {
        if (str != null) {
            this.mLog.i(String.format("unregisterDataChannelListener called for sessionId: %s", str));
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return false;
            }
            return rtcscClientHandler.unregisterDataChannelListener(str);
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode unregisterRtcscAppClientListener(@NonNull RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo != null) {
            this.mLog.i(String.format("unregisterRtcscAppClientListener called with SessionDomain : %s", rtcscAppInfo.getAppIdentifier()));
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            }
            RtcscErrorCode unregisterRtcscAppClientListener = rtcscClientHandler.unregisterRtcscAppClientListener(rtcscAppInfo);
            mRtcscClientHandler.teardown();
            mRtcscClientHandler = null;
            return unregisterRtcscAppClientListener;
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode unregisterRtcscCustomMetricsPublisherAdapter(@NonNull RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo != null) {
            this.mLog.i("unregisterRtcscCustomMetricsPublisherAdapter called");
            RtcscClientHandler rtcscClientHandler = mRtcscClientHandler;
            if (rtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            }
            return rtcscClientHandler.unregisterRtcscCustomMetricsPublisherAdapter(rtcscAppInfo);
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode unregisterScreenCapturerListener(@NonNull String str) {
        if (str != null) {
            this.mLog.i(String.format(Locale.US, "unregisterScreenCapturerListener called for sessionId: %s", str));
            if (mRtcscClientHandler == null) {
                this.mLog.e("RtcscClientHandler is null. Call registerRtcscAppClientListener() first.");
                return RtcscErrorCode.NULL_CLIENT_HANDLER;
            } else if (str.trim().isEmpty()) {
                this.mLog.e("sessionId should be a valid non-empty string");
                return RtcscErrorCode.EMPTY_SESSION_ID;
            } else {
                return mRtcscClientHandler.unregisterScreenCapturerListener(str);
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    RtcscAppClient(@NonNull Context context, RtcscClientHandler rtcscClientHandler) {
        if (context != null) {
            this.mContext = context;
            mRtcscClientHandler = rtcscClientHandler;
            return;
        }
        throw new IllegalArgumentException("context is null");
    }
}
