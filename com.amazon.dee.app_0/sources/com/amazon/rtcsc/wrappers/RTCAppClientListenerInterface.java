package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class RTCAppClientListenerInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected RTCAppClientListenerInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RTCAppClientListenerInterface rTCAppClientListenerInterface) {
        if (rTCAppClientListenerInterface == null) {
            return 0L;
        }
        return rTCAppClientListenerInterface.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (!this.swigCMemOwn) {
                this.swigCPtr = 0L;
            } else {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
        }
    }

    public void onFirstFrameReceived(String str, RTCMediaType rTCMediaType) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onFirstFrameReceived(this.swigCPtr, this, str, rTCMediaType.swigValue());
    }

    public void onFirstFrameRendered(String str, RTCSide rTCSide) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onFirstFrameRendered(this.swigCPtr, this, str, rTCSide.swigValue());
    }

    public void onMediaConnectionStateChanged(String str, RTCMediaConnectionState rTCMediaConnectionState) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onMediaConnectionStateChanged(this.swigCPtr, this, str, rTCMediaConnectionState.swigValue());
    }

    public void onMediaStatusChanged(String str, Side side, MediaType mediaType, boolean z) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onMediaStatusChanged(this.swigCPtr, this, str, side.swigValue(), mediaType.swigValue(), z);
    }

    public void onSessionAvailable(String str) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onSessionAvailable(this.swigCPtr, this, str);
    }

    public void onSessionError(String str, RTCSCErrorCode rTCSCErrorCode, String str2) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onSessionError(this.swigCPtr, this, str, rTCSCErrorCode.swigValue(), str2);
    }

    public void onSessionRemoved(String str) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onSessionRemoved(this.swigCPtr, this, str);
    }

    public void onSessionStateChanged(String str, RTCSessionState rTCSessionState) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onSessionStateChanged(this.swigCPtr, this, str, rTCSessionState.swigValue());
    }

    public void onVideoEffectChanged(String str, RTCVideoEffect rTCVideoEffect, int i) {
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_onVideoEffectChanged(this.swigCPtr, this, str, rTCVideoEffect.swigValue(), i);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_change_ownership(this, this.swigCPtr, true);
    }

    /* loaded from: classes13.dex */
    public static final class MediaType {
        private final String swigName;
        private final int swigValue;
        public static final MediaType AUDIO = new MediaType("AUDIO");
        public static final MediaType VIDEO = new MediaType("VIDEO");
        private static MediaType[] swigValues = {AUDIO, VIDEO};
        private static int swigNext = 0;

        private MediaType(String str) {
            this.swigName = str;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        public static MediaType swigToEnum(int i) {
            MediaType[] mediaTypeArr = swigValues;
            if (i >= mediaTypeArr.length || i < 0 || mediaTypeArr[i].swigValue != i) {
                int i2 = 0;
                while (true) {
                    MediaType[] mediaTypeArr2 = swigValues;
                    if (i2 < mediaTypeArr2.length) {
                        if (mediaTypeArr2[i2].swigValue == i) {
                            return mediaTypeArr2[i2];
                        }
                        i2++;
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", MediaType.class, " with value ", i));
                    }
                }
            } else {
                return mediaTypeArr[i];
            }
        }

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        private MediaType(String str, int i) {
            this.swigName = str;
            this.swigValue = i;
            swigNext = i + 1;
        }

        private MediaType(String str, MediaType mediaType) {
            this.swigName = str;
            this.swigValue = mediaType.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    /* loaded from: classes13.dex */
    public static final class Side {
        private final String swigName;
        private final int swigValue;
        public static final Side LOCAL = new Side("LOCAL");
        public static final Side REMOTE = new Side("REMOTE");
        private static Side[] swigValues = {LOCAL, REMOTE};
        private static int swigNext = 0;

        private Side(String str) {
            this.swigName = str;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        public static Side swigToEnum(int i) {
            Side[] sideArr = swigValues;
            if (i >= sideArr.length || i < 0 || sideArr[i].swigValue != i) {
                int i2 = 0;
                while (true) {
                    Side[] sideArr2 = swigValues;
                    if (i2 < sideArr2.length) {
                        if (sideArr2[i2].swigValue == i) {
                            return sideArr2[i2];
                        }
                        i2++;
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", Side.class, " with value ", i));
                    }
                }
            } else {
                return sideArr[i];
            }
        }

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        private Side(String str, int i) {
            this.swigName = str;
            this.swigValue = i;
            swigNext = i + 1;
        }

        private Side(String str, Side side) {
            this.swigName = str;
            this.swigValue = side.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    public RTCAppClientListenerInterface() {
        this(RTCControllerAndroidJNI.new_RTCAppClientListenerInterface(), true);
        RTCControllerAndroidJNI.RTCAppClientListenerInterface_director_connect(this, this.swigCPtr, true, true);
    }
}
