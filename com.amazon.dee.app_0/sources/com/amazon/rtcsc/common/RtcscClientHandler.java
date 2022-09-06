package com.amazon.rtcsc.common;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;
import com.amazon.identity.auth.device.api.MAPAccountManager;
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
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.utils.Util;
import java.util.Locale;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class RtcscClientHandler extends BaseClient<IRtcscServiceHandler> {
    private static RtcscLogger mLog = RtcscLogger.getLogger(RtcscClientHandler.class);

    public RtcscClientHandler(@NonNull Context context) {
        this(context, new AsyncServiceConnectionManager());
    }

    public RtcscErrorCode acceptSession(@NonNull String str) {
        if (str != null) {
            mLog.i(String.format("acceptSession called. Forwarding call to the service. SessionId: %s.", str));
            try {
                getService().acceptSession(str);
                mLog.i("Service request for acceptSession sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in acceptSession", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode clearImage(String str, String str2) {
        mLog.i(String.format("clearImage called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().clearImage(str, str2);
            mLog.i("Service request for clearImage returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in clearImage", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    @Override // com.amazon.rtcsc.common.BaseClient
    protected BaseClient<IRtcscServiceHandler>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<IRtcscServiceHandler>.BaseClientServiceConnection() { // from class: com.amazon.rtcsc.common.RtcscClientHandler.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.rtcsc.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService */
            public IRtcscServiceHandler mo4497convertIBinderToService(IBinder iBinder) {
                return IRtcscServiceHandler.Stub.asInterface(iBinder);
            }
        };
    }

    public RtcscErrorCode disconnectSession(@NonNull String str, @NonNull RtcscAppDisconnectCode rtcscAppDisconnectCode) {
        if (str != null) {
            if (rtcscAppDisconnectCode != null) {
                mLog.i(String.format("disconnectSession called. Forwarding call to the service. SessionId: %s.", str));
                try {
                    getService().disconnectSession(str, rtcscAppDisconnectCode);
                    mLog.i("Service request for disconnectSession sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in disconnectSession", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("appDisconnectCode is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode handleDirective(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 != null) {
                mLog.i(String.format("handleDirective called. Forwarding call to the service. DirectiveName: %s", str));
                try {
                    getService().handleDirective(str, str2);
                    mLog.i("Service request for handleDirective sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in handleDirective", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("directivePayload is null");
        }
        throw new IllegalArgumentException("directiveName is null");
    }

    public RtcscErrorCode initRenderer(String str, String str2, IRtcscViewRendererListener iRtcscViewRendererListener, RtcscViewDirection rtcscViewDirection, String str3) {
        mLog.i(String.format("initRenderer called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().initRenderer(str, str2, iRtcscViewRendererListener, rtcscViewDirection, str3);
            mLog.i("Service request for initRenderer returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in initRenderer", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode onRendererLayout(String str, String str2, boolean z, int i, int i2, int i3, int i4) {
        mLog.i(String.format("onRendererLayout called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().onRendererLayout(str, str2, z, i, i2, i3, i4);
            mLog.i("Service request for onRendererLayout returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in onRendererLayout", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode onRendererMeasure(String str, String str2, int i, int i2) {
        mLog.i(String.format("onRendererMeasure called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().onRendererMeasure(str, str2, i, i2);
            mLog.i("Service request for onRendererMeasure returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in onRendererMeasure", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode putScreenCapturerData(String str, Intent intent) {
        mLog.i(String.format(Locale.US, "putScreenCapturerData called. Forwarding call to the service. SessionId: %s", str));
        try {
            getService().putScreenCapturerData(str, intent);
            mLog.i("Service request for putScreenCapturerData sent successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in putScreenCapturerData", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode registerCAEventListener(@NonNull RtcscAppInfo rtcscAppInfo, @NonNull IRtcscEventListener iRtcscEventListener) {
        if (rtcscAppInfo != null) {
            if (iRtcscEventListener != null) {
                mLog.i("registerCAEventListener called. Forwarding call to the service");
                try {
                    getService().registerCAEventListener(rtcscAppInfo, iRtcscEventListener);
                    mLog.i("Service request for registerCAEventListener sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in registerCAEventListener", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("listener is null");
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public void registerClientDeathListener(@NonNull RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo != null) {
            mLog.i(String.format(Locale.US, "registerClientDeathListener called. Forwarding call to the service. SessionDomain: %s", rtcscAppInfo.getAppIdentifier()));
            try {
                getService().registerClientDeathListener(rtcscAppInfo, new Binder());
                mLog.i("Service request for registerClientDeathListener sent successfully");
                return;
            } catch (RemoteException e) {
                mLog.e("Exception in registerClientDeathListener", e);
                return;
            }
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public boolean registerDataChannelListener(@NonNull String str, @NonNull IRtcscDataChannelListener iRtcscDataChannelListener) {
        if (str != null) {
            if (iRtcscDataChannelListener != null) {
                boolean z = false;
                mLog.i(String.format("registerDataChannelListener called. Forwarding call to RtcscService. SessionId: %s", str));
                try {
                    z = getService().registerDataChannelListener(str, iRtcscDataChannelListener);
                    mLog.i("Service request for registerDataChannelListener returned successfully");
                    return z;
                } catch (RemoteException e) {
                    mLog.e("Exception in registerDataChannelListener", e);
                    return z;
                }
            }
            throw new IllegalArgumentException("listener is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode registerRtcscAppClientListener(@NonNull RtcscAppInfo rtcscAppInfo, @NonNull IRtcscAppClientListener iRtcscAppClientListener) {
        if (rtcscAppInfo != null) {
            if (iRtcscAppClientListener != null) {
                mLog.i(String.format("registerRtcscAppClientListener called for SessionDomain - %s. Forwarding call to the service", rtcscAppInfo.getAppIdentifier()));
                try {
                    getService().registerRtcscAppClientListener(rtcscAppInfo, iRtcscAppClientListener);
                    mLog.i("Service request for registerRtcscAppClientListener sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in registerRtcscAppClientListener", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("rtcAppClientListener is null");
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode registerRtcscCustomMetricsPublisherAdapter(@NonNull RtcscAppInfo rtcscAppInfo, @NonNull IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) {
        if (rtcscAppInfo != null) {
            if (iRtcscCustomMetricsPublisherAdapter != null) {
                mLog.i(String.format("registerRtcscCustomMetricsPublisherAdapter called for app client: %s. Forwarding call to the service.", rtcscAppInfo.getAppIdentifier()));
                try {
                    getService().registerRtcscCustomMetricsPublisherAdapter(rtcscAppInfo, iRtcscCustomMetricsPublisherAdapter);
                    mLog.i("Service request for registerRtcscCustomMetricsPublisherAdapter sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in registerRtcscCustomMetricsPublisherAdapter", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("rtcCustomMetricsPublisherAdapter is null");
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode registerScreenCapturerListener(String str, IRtcscScreenCapturerListener iRtcscScreenCapturerListener) {
        mLog.i(String.format(Locale.US, "registerScreenCapturerListener called. Forwarding call to the service. SessionId: %s", str));
        try {
            getService().registerScreenCapturerListener(str, iRtcscScreenCapturerListener);
            mLog.i("Service request for registerScreenCapturerListener sent successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in registerScreenCapturerListener", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode releaseRenderer(String str, String str2) {
        mLog.i(String.format("releaseRenderer called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().releaseRenderer(str, str2);
            mLog.i("Service request for releaseRenderer returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in releaseRenderer", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public boolean sendData(@NonNull String str, @NonNull String str2, @NonNull byte[] bArr, boolean z) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("label is null");
            }
            if (bArr != null) {
                boolean z2 = false;
                mLog.i(String.format("sendData called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
                try {
                    z2 = getService().sendData(str, str2, bArr, z);
                    mLog.i("Service request for sendData returned successfully");
                    return z2;
                } catch (RemoteException e) {
                    mLog.e("Exception in sendData", e);
                    return z2;
                }
            }
            throw new IllegalArgumentException("data is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setEnableHardwareScaler(String str, String str2, boolean z) {
        mLog.i(String.format("setEnableHardwareScaler called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().setEnableHardwareScaler(str, str2, z);
            mLog.i("Service request for setEnableHardwareScaler returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in setEnableHardwareScaler", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode setLocalAudioState(@NonNull String str, boolean z) {
        if (str != null) {
            mLog.i(String.format("setLocalAudioState called. Forwarding call to the service. SessionId: %s. AudioState: %b", str, Boolean.valueOf(z)));
            try {
                getService().setLocalAudioState(str, z);
                mLog.i("Service request for setLocalAudioState sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in setLocalAudioState", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setLocalVideoState(@NonNull String str, boolean z) {
        if (str != null) {
            mLog.i(String.format("setLocalVideoState called. Forwarding call to the service. SessionId: %s. videoEnabled: %b", str, Boolean.valueOf(z)));
            try {
                getService().setLocalVideoState(str, z);
                mLog.i("Service request for setLocalVideoState sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in setLocalVideoState", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setMirror(String str, String str2, boolean z) {
        mLog.i(String.format("setMirror called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().setMirror(str, str2, z);
            mLog.i("Service request for setMirror returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in setMirror", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode setRemoteAudioState(@NonNull String str, boolean z) {
        if (str != null) {
            mLog.i(String.format("setRemoteAudioState called. Forwarding call to the service. SessionId: %s. AudioState: %b", str, Boolean.valueOf(z)));
            try {
                getService().setRemoteAudioState(str, z);
                mLog.i("Service request for setRemoteAudioState sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in setRemoteAudioState", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode setScalingType(String str, String str2, RtcscScalingType rtcscScalingType) {
        mLog.i(String.format("setScalingType called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().setScalingType(str, str2, rtcscScalingType);
            mLog.i("Service request for setScalingType returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in setScalingType", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode setScreenCapturerDimensions(String str, int i, int i2) {
        mLog.i(String.format(Locale.US, "setScreenCapturerDimensions called. Forwarding call to the service. SessionId: %s", str));
        try {
            getService().setScreenCapturerDimensions(str, i, i2);
            mLog.i("Service request for setScreenCapturerDimensions sent successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in setScreenCapturerDimensions", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode setVideoEffect(@NonNull String str, @NonNull RtcscVideoEffect rtcscVideoEffect, int i) {
        if (str != null) {
            if (rtcscVideoEffect != null) {
                mLog.i(String.format(Locale.US, "setVideoEffect called. Forwarding call to the service. SessionId: %s.", str));
                try {
                    getService().setVideoEffect(str, rtcscVideoEffect, i);
                    mLog.i("Service request for setVideoEffect sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in setVideoEffect", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("videoEffect is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode signalReadyForSession(@NonNull String str) {
        if (str != null) {
            mLog.i(String.format("signalReadyForSession called. Forwarding call to the service. SessionId: %s.", str));
            try {
                getService().signalReadyForSession(str);
                mLog.i("Service request for signalReadyForSession sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in signalReadyForSession", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode surfaceChanged(String str, String str2, Surface surface, int i, int i2, int i3) {
        mLog.i(String.format("surfaceChanged called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().surfaceChanged(str, str2, surface, i, i2, i3);
            mLog.i("Service request for surfaceChanged returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in surfaceChanged", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode surfaceCreated(String str, String str2, Surface surface) {
        mLog.i(String.format("surfaceCreated called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().surfaceCreated(str, str2, surface);
            mLog.i("Service request for surfaceCreated returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in surfaceCreated", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode surfaceDestroyed(String str, String str2, Surface surface) {
        mLog.i(String.format("surfaceDestroyed called. Forwarding call to the service. SessionId: %s. Label: %s", str, str2));
        try {
            getService().surfaceDestroyed(str, str2, surface);
            mLog.i("Service request for surfaceDestroyed returned successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in surfaceDestroyed", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscErrorCode switchCamera(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 != null) {
                mLog.i(String.format("switchCamera called. Forwarding call to the service. SessionId: %s. CameraName: %s", str, str2));
                try {
                    getService().switchCamera(str, str2);
                    mLog.i("Service request for switchCamera sent successfully");
                    return RtcscErrorCode.SUCCESS;
                } catch (RemoteException e) {
                    mLog.e("Exception in switchCamera", e);
                    return RtcscErrorCode.REMOTE_ERROR;
                }
            }
            throw new IllegalArgumentException("cameraName is null");
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode unregisterCAEventListener(@NonNull RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo != null) {
            mLog.i("unregisterCAEventListener called. Forwarding call to the service");
            try {
                getService().unregisterCAEventListener(rtcscAppInfo);
                mLog.i("Service request for unregisterCAEventListener sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in unregisterCAEventListener", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public boolean unregisterDataChannelListener(@NonNull String str) {
        if (str != null) {
            boolean z = false;
            mLog.i(String.format("unregisterDataChannelListener called. Forwarding call to RtcscService. SessionId: %s", str));
            try {
                z = getService().unregisterDataChannelListener(str);
                mLog.i("Service request for unregisterDataChannelListener returned successfully");
                return z;
            } catch (RemoteException e) {
                mLog.e("Exception in unregisterDataChannelListener", e);
                return z;
            }
        }
        throw new IllegalArgumentException("sessionId is null");
    }

    public RtcscErrorCode unregisterRtcscAppClientListener(@NonNull RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo != null) {
            mLog.i(String.format("unregisterRtcscAppClientListener called for SessionDomain - %s. Forwarding call to the service", rtcscAppInfo.getAppIdentifier()));
            try {
                getService().unregisterRtcscAppClientListener(rtcscAppInfo);
                mLog.i("Service request for unregisterRtcscAppClientListener sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in unregisterRtcscAppClientListener", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode unregisterRtcscCustomMetricsPublisherAdapter(@NonNull RtcscAppInfo rtcscAppInfo) {
        if (rtcscAppInfo != null) {
            mLog.i(String.format("unregisterRtcscCustomMetricsPublisherAdapter called for app client: %s. Forwarding call to the service", rtcscAppInfo.getAppIdentifier()));
            try {
                getService().unregisterRtcscCustomMetricsPublisherAdapter(rtcscAppInfo);
                mLog.i("Service request for unregisterRtcscCustomMetricsPublisherAdapter sent successfully");
                return RtcscErrorCode.SUCCESS;
            } catch (RemoteException e) {
                mLog.e("Exception in unregisterRtcscCustomMetricsPublisherAdapter", e);
                return RtcscErrorCode.REMOTE_ERROR;
            }
        }
        throw new IllegalArgumentException("rtcscAppInfo is null");
    }

    public RtcscErrorCode unregisterScreenCapturerListener(String str) {
        mLog.i(String.format(Locale.US, "unregisterScreenCapturerListener called. Forwarding call to the service. SessionId: %s", str));
        try {
            getService().unregisterScreenCapturerListener(str);
            mLog.i("Service request for unregisterScreenCapturerListener sent successfully");
            return RtcscErrorCode.SUCCESS;
        } catch (RemoteException e) {
            mLog.e("Exception in unregisterScreenCapturerListener", e);
            return RtcscErrorCode.REMOTE_ERROR;
        }
    }

    public RtcscClientHandler(@NonNull Context context, @NonNull ServiceConnectionManager serviceConnectionManager) {
        this(context, serviceConnectionManager, new Intent(RtcscServiceInfo.createRtcscServiceBindIntent(context)));
    }

    public RtcscClientHandler(@NonNull Context context, @NonNull ServiceConnectionManager serviceConnectionManager, @NonNull Intent intent) {
        super(IRtcscServiceHandler.class, (Context) Util.nullCheck(context, "context"), (Intent) Util.nullCheck(intent, MAPAccountManager.KEY_INTENT), "com.amazon.rtcsc.permission.RTCSC", (ServiceConnectionManager) Util.nullCheck(serviceConnectionManager, "serviceConnectionManager"));
    }
}
