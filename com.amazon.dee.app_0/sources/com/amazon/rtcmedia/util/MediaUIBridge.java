package com.amazon.rtcmedia.util;

import android.content.Intent;
import com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer;
import com.amazon.rtcmedia.webrtc.WebRTCAndroidSession;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.webrtc.DataChannel;
/* loaded from: classes13.dex */
public class MediaUIBridge {
    private static volatile MediaUIBridge bridge;
    private static RtcscLogger mLog = RtcscLogger.getLogger(MediaUIBridge.class);
    private Map<String, RTCSurfaceRenderer> localRendererMap = new HashMap();
    private Map<String, RTCSurfaceRenderer> remoteRendererMap = new HashMap();
    private Map<String, Intent> screenCapturerPermissionMap = new HashMap();
    private Map<String, WebRTCAndroidSession.RTCDataChannelEventListener> dataChannelListenerMap = new HashMap();
    private Map<String, Map<String, DataChannel>> dataChannelSessionMap = new HashMap();
    private Set<MediaUIBridgeListener> listenerSet = Collections.synchronizedSet(new HashSet());

    /* loaded from: classes13.dex */
    public interface MediaUIBridgeListener {
        void onDataChannelListenerAdded(String str, WebRTCAndroidSession.RTCDataChannelEventListener rTCDataChannelEventListener);

        void onDataChannelListenerRemoved(String str, WebRTCAndroidSession.RTCDataChannelEventListener rTCDataChannelEventListener);

        void onLocalRendererAdded(String str, RTCSurfaceRenderer rTCSurfaceRenderer);

        void onLocalRendererRemoved(String str, RTCSurfaceRenderer rTCSurfaceRenderer);

        void onRemoteRendererAdded(String str, RTCSurfaceRenderer rTCSurfaceRenderer);

        void onRemoteRendererRemoved(String str, RTCSurfaceRenderer rTCSurfaceRenderer);

        void onScreenCapturerDimensionsSet(String str, int i, int i2);
    }

    private MediaUIBridge() {
    }

    public static MediaUIBridge getInstance() {
        mLog.i("getInstance");
        if (bridge == null) {
            synchronized (MediaUIBridge.class) {
                if (bridge == null) {
                    bridge = new MediaUIBridge();
                }
            }
        }
        return bridge;
    }

    public void addDataChannel(String str, String str2, DataChannel dataChannel) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("addDataChannel, sessionId = " + str + ", label = " + str2);
        synchronized (this.dataChannelSessionMap) {
            Map<String, DataChannel> map = this.dataChannelSessionMap.get(str);
            if (map != null) {
                if (map.get(str2) != null) {
                    mLog.e("Already have a data channel with this label");
                    return;
                } else {
                    map.put(str2, dataChannel);
                    return;
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.put(str2, dataChannel);
            this.dataChannelSessionMap.put(str, hashMap);
        }
    }

    public void addDataChannelListener(String str, WebRTCAndroidSession.RTCDataChannelEventListener rTCDataChannelEventListener) {
        GeneratedOutlineSupport1.outline160("addDataChannelListener, sessionId = ", str, mLog);
        synchronized (this.dataChannelListenerMap) {
            if (this.dataChannelListenerMap.containsKey(str)) {
                mLog.w("Already have a data channel listener with this sessionId");
                return;
            }
            this.dataChannelListenerMap.put(str, rTCDataChannelEventListener);
            synchronized (this.listenerSet) {
                for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                    mediaUIBridgeListener.onDataChannelListenerAdded(str, rTCDataChannelEventListener);
                }
            }
        }
    }

    public void addListener(MediaUIBridgeListener mediaUIBridgeListener) {
        this.listenerSet.add(mediaUIBridgeListener);
    }

    public void addLocalRenderer(String str, RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("addLocalRenderer, sessionId = ", str, mLog);
        synchronized (this.localRendererMap) {
            if (this.localRendererMap.containsKey(str)) {
                mLog.w("Already have a local renderer with this sessionId, will replace");
            }
            this.localRendererMap.put(str, rTCSurfaceRenderer);
        }
        synchronized (this.listenerSet) {
            for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                mediaUIBridgeListener.onLocalRendererAdded(str, rTCSurfaceRenderer);
            }
        }
    }

    public void addRemoteRenderer(String str, RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("addRemoteRenderer, sessionId = ", str, mLog);
        synchronized (this.remoteRendererMap) {
            if (this.remoteRendererMap.containsKey(str)) {
                mLog.w("Already have a remote renderer with this sessionId, will replace");
            }
            this.remoteRendererMap.put(str, rTCSurfaceRenderer);
        }
        synchronized (this.listenerSet) {
            for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                mediaUIBridgeListener.onRemoteRendererAdded(str, rTCSurfaceRenderer);
            }
        }
    }

    public DataChannel getDataChannel(String str, String str2) {
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("getDataChannel, sessionId = " + str + ", label = " + str2);
        synchronized (this.dataChannelSessionMap) {
            Map<String, DataChannel> map = this.dataChannelSessionMap.get(str);
            if (map != null) {
                DataChannel dataChannel = map.get(str2);
                if (dataChannel == null) {
                    RtcscLogger rtcscLogger2 = mLog;
                    rtcscLogger2.e("datachannel not found for sessionId = " + str + ", label = " + str2);
                }
                return dataChannel;
            }
            RtcscLogger rtcscLogger3 = mLog;
            rtcscLogger3.e("No datachannels exist for sessionId = " + str);
            return null;
        }
    }

    public WebRTCAndroidSession.RTCDataChannelEventListener getDataChannelListener(String str) {
        WebRTCAndroidSession.RTCDataChannelEventListener rTCDataChannelEventListener;
        GeneratedOutlineSupport1.outline160("getDataChannelListener, sessionId = ", str, mLog);
        synchronized (this.dataChannelListenerMap) {
            rTCDataChannelEventListener = this.dataChannelListenerMap.get(str);
        }
        return rTCDataChannelEventListener;
    }

    public RTCSurfaceRenderer getLocalRenderer(String str) {
        RTCSurfaceRenderer rTCSurfaceRenderer;
        GeneratedOutlineSupport1.outline160("getLocalRenderer, sessionId = ", str, mLog);
        synchronized (this.localRendererMap) {
            rTCSurfaceRenderer = this.localRendererMap.get(str);
        }
        return rTCSurfaceRenderer;
    }

    public RTCSurfaceRenderer getRemoteRenderer(String str) {
        RTCSurfaceRenderer rTCSurfaceRenderer;
        GeneratedOutlineSupport1.outline160("getRemoteRenderer, sessionId = ", str, mLog);
        synchronized (this.remoteRendererMap) {
            rTCSurfaceRenderer = this.remoteRendererMap.get(str);
        }
        return rTCSurfaceRenderer;
    }

    public Intent getScreenCapturerPermission(String str) {
        Intent intent;
        synchronized (this.screenCapturerPermissionMap) {
            intent = this.screenCapturerPermissionMap.get(str);
        }
        return intent;
    }

    public void putScreenCapturerData(String str, Intent intent) {
        GeneratedOutlineSupport1.outline160("putScreenCapturerData, sessionId = ", str, mLog);
        synchronized (this.screenCapturerPermissionMap) {
            if (this.screenCapturerPermissionMap.containsKey(str)) {
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.e("Already have a permission registered for the sessionId:" + str);
                return;
            }
            this.screenCapturerPermissionMap.put(str, intent);
        }
    }

    public void removeDataChannelListener(String str, WebRTCAndroidSession.RTCDataChannelEventListener rTCDataChannelEventListener) {
        GeneratedOutlineSupport1.outline160("removeDataChannelListener, sessionId = ", str, mLog);
        synchronized (this.dataChannelListenerMap) {
            if (this.dataChannelListenerMap.get(str) != rTCDataChannelEventListener) {
                mLog.w("trying to remove data channel listener which is not in the map");
                return;
            }
            this.dataChannelListenerMap.remove(str);
            synchronized (this.listenerSet) {
                for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                    mediaUIBridgeListener.onDataChannelListenerRemoved(str, rTCDataChannelEventListener);
                }
            }
        }
    }

    public void removeDataChannels(String str) {
        GeneratedOutlineSupport1.outline160("disposeDataChannels, sessionId = ", str, mLog);
        synchronized (this.dataChannelSessionMap) {
            Map<String, DataChannel> remove = this.dataChannelSessionMap.remove(str);
            if (remove != null) {
                remove.clear();
            }
        }
    }

    public void removeListener(MediaUIBridgeListener mediaUIBridgeListener) {
        this.listenerSet.remove(mediaUIBridgeListener);
    }

    public void removeLocalRenderer(String str, RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("removeLocalRenderer, sessionId = ", str, mLog);
        synchronized (this.localRendererMap) {
            if (this.localRendererMap.get(str) != rTCSurfaceRenderer) {
                mLog.w("trying to remove local renderer which is not in the map");
                return;
            }
            this.localRendererMap.remove(str);
            synchronized (this.listenerSet) {
                for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                    mediaUIBridgeListener.onLocalRendererRemoved(str, rTCSurfaceRenderer);
                }
            }
        }
    }

    public void removeRemoteRenderer(String str, RTCSurfaceRenderer rTCSurfaceRenderer) {
        GeneratedOutlineSupport1.outline160("removeRemoteRenderer, sessionId = ", str, mLog);
        synchronized (this.remoteRendererMap) {
            if (this.remoteRendererMap.get(str) != rTCSurfaceRenderer) {
                mLog.w("trying to remove remote renderer which is not in the map");
                return;
            }
            this.remoteRendererMap.remove(str);
            synchronized (this.listenerSet) {
                for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                    mediaUIBridgeListener.onRemoteRendererRemoved(str, rTCSurfaceRenderer);
                }
            }
        }
    }

    public void removeScreenCapturerData(String str) {
        GeneratedOutlineSupport1.outline160("removeScreenCapturerData, sessionId = ", str, mLog);
        synchronized (this.screenCapturerPermissionMap) {
            if (!this.screenCapturerPermissionMap.containsKey(str)) {
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.w("No screen capturer permissions available to remove for sessionId:" + str);
                return;
            }
            this.screenCapturerPermissionMap.remove(str);
        }
    }

    public void setScreenCapturerDimensions(String str, int i, int i2) {
        GeneratedOutlineSupport1.outline160("setScreenCapturerDimensions, sessionId = ", str, mLog);
        synchronized (this.listenerSet) {
            for (MediaUIBridgeListener mediaUIBridgeListener : this.listenerSet) {
                mediaUIBridgeListener.onScreenCapturerDimensionsSet(str, i, i2);
            }
        }
    }
}
