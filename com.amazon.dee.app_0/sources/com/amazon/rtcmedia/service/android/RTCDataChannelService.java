package com.amazon.rtcmedia.service.android;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface;
import com.amazon.rtcmedia.util.MediaUIBridge;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.amazon.rtcmedia.webrtc.RTCDataChannelDTO;
import com.amazon.rtcmedia.webrtc.RTCDataChannelEvent;
import com.amazon.rtcmedia.webrtc.WebRTCAndroidSession;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.webrtc.DataChannel;
/* loaded from: classes13.dex */
public class RTCDataChannelService implements WebRTCAndroidSession.RTCDataChannelEventListener, RTCDataChannelServiceInterface {
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCDataChannelService.class);
    private static volatile RTCDataChannelService rtcDataChannelService;
    private Map<String, RTCDataChannelServiceInterface.RTCDataChannelListenerInterface> dataChannelListenerMap = new HashMap();

    /* renamed from: com.amazon.rtcmedia.service.android.RTCDataChannelService$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$rtcmedia$webrtc$RTCDataChannelEvent$Type = new int[RTCDataChannelEvent.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$rtcmedia$webrtc$RTCDataChannelEvent$Type[RTCDataChannelEvent.Type.STATE_CHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$rtcmedia$webrtc$RTCDataChannelEvent$Type[RTCDataChannelEvent.Type.RECEIVED_MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private RTCDataChannelService() {
    }

    public static RTCDataChannelService getInstance() {
        mLog.i("createInstance");
        if (rtcDataChannelService == null) {
            synchronized (RTCDataChannelService.class) {
                if (rtcDataChannelService == null) {
                    rtcDataChannelService = new RTCDataChannelService();
                }
            }
        }
        return rtcDataChannelService;
    }

    private boolean isValidData(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            mLog.e("data is null or empty");
            return false;
        }
        return true;
    }

    private boolean isValidDataChannel(DataChannel dataChannel) {
        if (dataChannel == null) {
            mLog.e("dataChannel is null");
            return false;
        }
        return true;
    }

    private boolean isValidLabel(String str) {
        if (str == null || str.isEmpty()) {
            mLog.e("label is null or empty");
            return false;
        }
        return true;
    }

    private boolean isValidListener(RTCDataChannelServiceInterface.RTCDataChannelListenerInterface rTCDataChannelListenerInterface) {
        if (rTCDataChannelListenerInterface == null) {
            mLog.e("DataChannelListener is null");
            return false;
        }
        return true;
    }

    private boolean isValidSessionId(String str) {
        if (str == null || str.isEmpty()) {
            RtcscLogger rtcscLogger = mLog;
            rtcscLogger.e("sessionId is null or empty " + str);
            return false;
        }
        return true;
    }

    private final RTCDataChannelServiceInterface.RTCDataChannelListenerInterface.State stringToDataChannelState(String str) {
        if (str != null && !str.isEmpty()) {
            return RTCDataChannelServiceInterface.RTCDataChannelListenerInterface.State.valueOf(str);
        }
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.e("Invalid DataChannel state " + str);
        return null;
    }

    @Override // com.amazon.rtcmedia.webrtc.WebRTCAndroidSession.RTCDataChannelEventListener
    public void onDataChannelEvent(RTCDataChannelEvent rTCDataChannelEvent) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("Received onDataChannelEvent with event " + rTCDataChannelEvent);
        String obj = rTCDataChannelEvent.getParams().get(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY).toString();
        GeneratedOutlineSupport1.outline160("Received onDataChannelEvent with sessionId ", obj, mLog);
        synchronized (this.dataChannelListenerMap) {
            RTCDataChannelServiceInterface.RTCDataChannelListenerInterface rTCDataChannelListenerInterface = this.dataChannelListenerMap.get(obj);
            if (!isValidListener(rTCDataChannelListenerInterface)) {
                return;
            }
            String obj2 = rTCDataChannelEvent.getParams().get("label").toString();
            RtcscLogger rtcscLogger2 = mLog;
            rtcscLogger2.i("Received onDataChannelEvent with label " + obj2);
            int ordinal = rTCDataChannelEvent.getEventType().ordinal();
            if (ordinal == 0) {
                mLog.i("Received event type RECEIVED_MESSAGE");
                RTCDataChannelDTO rTCDataChannelDTO = (RTCDataChannelDTO) rTCDataChannelEvent.getParams().get("data");
                rTCDataChannelListenerInterface.onMessageReceived(obj, obj2, rTCDataChannelDTO.getData(), rTCDataChannelDTO.isBinary());
            } else if (ordinal != 1) {
                RtcscLogger rtcscLogger3 = mLog;
                rtcscLogger3.e("Received unexpected event type " + rTCDataChannelEvent.getEventType());
            } else {
                mLog.i("Received event type STATE_CHANGED");
                String upperCase = rTCDataChannelEvent.getParams().get("state").toString().toUpperCase(Locale.ENGLISH);
                RtcscLogger rtcscLogger4 = mLog;
                rtcscLogger4.i(" Received state = " + upperCase);
                rTCDataChannelListenerInterface.onStateChange(obj, obj2, stringToDataChannelState(upperCase));
            }
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface
    public boolean registerDataChannelListener(String str, RTCDataChannelServiceInterface.RTCDataChannelListenerInterface rTCDataChannelListenerInterface) {
        GeneratedOutlineSupport1.outline160("registerDataChannelListener, sessionId = ", str, mLog);
        if (!isValidSessionId(str) || !isValidListener(rTCDataChannelListenerInterface)) {
            return false;
        }
        synchronized (this.dataChannelListenerMap) {
            if (this.dataChannelListenerMap.get(str) != null) {
                mLog.e("Already have a dataChannelListener with this sessionId");
                return false;
            }
            this.dataChannelListenerMap.put(str, rTCDataChannelListenerInterface);
            MediaUIBridge.getInstance().addDataChannelListener(str, this);
            return true;
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface
    public synchronized boolean sendData(String str, String str2, byte[] bArr, boolean z) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("sendData, sessionId = " + str + "label = " + str2);
        if (isValidSessionId(str) && isValidLabel(str2) && isValidData(bArr)) {
            DataChannel dataChannel = MediaUIBridge.getInstance().getDataChannel(str, str2);
            if (!isValidDataChannel(dataChannel)) {
                return false;
            }
            if (dataChannel.state() != DataChannel.State.OPEN) {
                RtcscLogger rtcscLogger2 = mLog;
                rtcscLogger2.w("DataChannel is not ready for sending, state " + dataChannel.state());
                return false;
            }
            return dataChannel.send(new DataChannel.Buffer(ByteBuffer.wrap(bArr), z));
        }
        return false;
    }

    @Override // com.amazon.rtcmedia.service.android.RTCDataChannelServiceInterface
    public boolean unregisterDataChannelListener(String str) {
        GeneratedOutlineSupport1.outline160("unregisterDataChannelListener, sessionId = ", str, mLog);
        if (!isValidSessionId(str)) {
            return false;
        }
        synchronized (this.dataChannelListenerMap) {
            if (this.dataChannelListenerMap.remove(str) == null) {
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.e("No listener found for sessionId " + str);
                return false;
            }
            MediaUIBridge.getInstance().removeDataChannelListener(str, this);
            return true;
        }
    }
}
