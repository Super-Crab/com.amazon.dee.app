package org.webrtc;

import android.content.Context;
import java.util.List;
import org.webrtc.EglBase;
import org.webrtc.NativeLibrary;
import org.webrtc.PeerConnection;
/* loaded from: classes5.dex */
public class PeerConnectionFactory {
    private static final String TAG = "PeerConnectionFactory";
    public static final String TRIAL_ENABLED = "Enabled";
    private static final String VIDEO_CAPTURER_THREAD_NAME = "VideoCapturerThread";
    public static final String VIDEO_FRAME_EMIT_TRIAL = "VideoFrameEmit";
    private static Context applicationContext;
    private static Thread networkThread;
    private static Thread signalingThread;
    private static Thread workerThread;
    private EglBase localEglbase;
    private final long nativeFactory;
    private EglBase remoteEglbase;

    /* loaded from: classes5.dex */
    public static class InitializationOptions {
        final Context applicationContext;
        final boolean enableInternalTracer;
        final boolean enableVideoHwAcceleration;
        final String fieldTrials;
        final NativeLibraryLoader nativeLibraryLoader;

        /* loaded from: classes5.dex */
        public static class Builder {
            private final Context applicationContext;
            private String fieldTrials = "";
            private boolean enableInternalTracer = true;
            private boolean enableVideoHwAcceleration = true;
            private NativeLibraryLoader nativeLibraryLoader = new NativeLibrary.DefaultLoader();

            Builder(Context context) {
                this.applicationContext = context;
            }

            public InitializationOptions createInitializationOptions() {
                return new InitializationOptions(this.applicationContext, this.fieldTrials, this.enableInternalTracer, this.enableVideoHwAcceleration, this.nativeLibraryLoader);
            }

            public Builder setEnableInternalTracer(boolean z) {
                this.enableInternalTracer = z;
                return this;
            }

            public Builder setEnableVideoHwAcceleration(boolean z) {
                this.enableVideoHwAcceleration = z;
                return this;
            }

            public Builder setFieldTrials(String str) {
                this.fieldTrials = str;
                return this;
            }

            public Builder setNativeLibraryLoader(NativeLibraryLoader nativeLibraryLoader) {
                this.nativeLibraryLoader = nativeLibraryLoader;
                return this;
            }
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        private InitializationOptions(Context context, String str, boolean z, boolean z2, NativeLibraryLoader nativeLibraryLoader) {
            this.applicationContext = context;
            this.fieldTrials = str;
            this.enableInternalTracer = z;
            this.enableVideoHwAcceleration = z2;
            this.nativeLibraryLoader = nativeLibraryLoader;
        }
    }

    /* loaded from: classes5.dex */
    public static class Options {
        static final int ADAPTER_TYPE_CELLULAR = 4;
        static final int ADAPTER_TYPE_ETHERNET = 1;
        static final int ADAPTER_TYPE_LOOPBACK = 16;
        static final int ADAPTER_TYPE_UNKNOWN = 0;
        static final int ADAPTER_TYPE_VPN = 8;
        static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public int networkIgnoreMask;
    }

    static {
        NativeLibrary.initialize(new NativeLibrary.DefaultLoader());
    }

    @Deprecated
    public PeerConnectionFactory() {
        this(null);
    }

    public static String fieldTrialsFindFullName(String str) {
        return NativeLibrary.isLoaded() ? nativeFieldTrialsFindFullName(str) : "";
    }

    public static void initialize(InitializationOptions initializationOptions) {
        ContextUtils.initialize(initializationOptions.applicationContext);
        NativeLibrary.initialize(initializationOptions.nativeLibraryLoader);
        nativeInitializeAndroidGlobals(initializationOptions.applicationContext, initializationOptions.enableVideoHwAcceleration);
        initializeFieldTrials(initializationOptions.fieldTrials);
        if (initializationOptions.enableInternalTracer) {
            initializeInternalTracer();
        }
    }

    @Deprecated
    public static void initializeAndroidGlobals(Context context, boolean z) {
        ContextUtils.initialize(context);
        nativeInitializeAndroidGlobals(context, z);
    }

    @Deprecated
    public static native void initializeFieldTrials(String str);

    @Deprecated
    public static native void initializeInternalTracer();

    private static native long nativeCreateAudioSource(long j, MediaConstraints mediaConstraints);

    private static native long nativeCreateAudioTrack(long j, String str, long j2);

    private static native long nativeCreateLocalMediaStream(long j, String str);

    private static native long nativeCreateObserver(PeerConnection.Observer observer);

    private static native long nativeCreatePeerConnection(long j, PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, long j2);

    private static native long nativeCreatePeerConnectionFactory(Options options, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory);

    private static native long nativeCreateVideoSource(long j, SurfaceTextureHelper surfaceTextureHelper, boolean z);

    private static native long nativeCreateVideoTrack(long j, String str, long j2);

    private static native String nativeFieldTrialsFindFullName(String str);

    private static native void nativeFreeFactory(long j);

    private static native void nativeInitializeAndroidGlobals(Context context, boolean z);

    private static native void nativeSetVideoHwAccelerationOptions(long j, Object obj, Object obj2);

    private static native boolean nativeStartAecDump(long j, int i, int i2);

    private static native void nativeStopAecDump(long j);

    private static native void nativeThreadsCallbacks(long j);

    private static void onNetworkThreadReady() {
        networkThread = Thread.currentThread();
        Logging.d(TAG, "onNetworkThreadReady");
    }

    private static void onSignalingThreadReady() {
        signalingThread = Thread.currentThread();
        Logging.d(TAG, "onSignalingThreadReady");
    }

    private static void onWorkerThreadReady() {
        workerThread = Thread.currentThread();
        Logging.d(TAG, "onWorkerThreadReady");
    }

    private static void printStackTrace(Thread thread, String str) {
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length <= 0) {
                return;
            }
            Logging.d(TAG, str + " stacks trace:");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Logging.d(TAG, stackTraceElement.toString());
            }
        }
    }

    public static void printStackTraces() {
        printStackTrace(networkThread, "Network thread");
        printStackTrace(workerThread, "Worker thread");
        printStackTrace(signalingThread, "Signaling thread");
    }

    public static native void shutdownInternalTracer();

    public static native boolean startInternalTracingCapture(String str);

    public static native void stopInternalTracingCapture();

    public AudioSource createAudioSource(MediaConstraints mediaConstraints) {
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, mediaConstraints));
    }

    public AudioTrack createAudioTrack(String str, AudioSource audioSource) {
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, str, audioSource.nativeSource));
    }

    public MediaStream createLocalMediaStream(String str) {
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, str));
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        long nativeCreateObserver = nativeCreateObserver(observer);
        if (nativeCreateObserver == 0) {
            return null;
        }
        long nativeCreatePeerConnection = nativeCreatePeerConnection(this.nativeFactory, rTCConfiguration, mediaConstraints, nativeCreateObserver);
        if (nativeCreatePeerConnection != 0) {
            return new PeerConnection(nativeCreatePeerConnection, nativeCreateObserver);
        }
        return null;
    }

    public VideoSource createVideoSource(VideoCapturer videoCapturer) {
        EglBase eglBase = this.localEglbase;
        SurfaceTextureHelper create = SurfaceTextureHelper.create(VIDEO_CAPTURER_THREAD_NAME, eglBase == null ? null : eglBase.mo13074getEglBaseContext());
        long nativeCreateVideoSource = nativeCreateVideoSource(this.nativeFactory, create, videoCapturer.isScreencast());
        videoCapturer.initialize(create, ContextUtils.getApplicationContext(), new AndroidVideoTrackSourceObserver(nativeCreateVideoSource));
        return new VideoSource(nativeCreateVideoSource);
    }

    public VideoTrack createVideoTrack(String str, VideoSource videoSource) {
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, str, videoSource.nativeSource));
    }

    public void dispose() {
        nativeFreeFactory(this.nativeFactory);
        networkThread = null;
        workerThread = null;
        signalingThread = null;
        EglBase eglBase = this.localEglbase;
        if (eglBase != null) {
            eglBase.release();
        }
        EglBase eglBase2 = this.remoteEglbase;
        if (eglBase2 != null) {
            eglBase2.release();
        }
    }

    @Deprecated
    public native void nativeSetOptions(long j, Options options);

    @Deprecated
    public void setOptions(Options options) {
        nativeSetOptions(this.nativeFactory, options);
    }

    public void setVideoHwAccelerationOptions(EglBase.Context context, EglBase.Context context2) {
        if (this.localEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.localEglbase.release();
        }
        if (this.remoteEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.remoteEglbase.release();
        }
        this.localEglbase = EglBase.create(context);
        this.remoteEglbase = EglBase.create(context2);
        nativeSetVideoHwAccelerationOptions(this.nativeFactory, this.localEglbase.mo13074getEglBaseContext(), this.remoteEglbase.mo13074getEglBaseContext());
    }

    public boolean startAecDump(int i, int i2) {
        return nativeStartAecDump(this.nativeFactory, i, i2);
    }

    public void stopAecDump() {
        nativeStopAecDump(this.nativeFactory);
    }

    public void threadsCallbacks() {
        nativeThreadsCallbacks(this.nativeFactory);
    }

    public PeerConnectionFactory(Options options) {
        this(options, null, null);
    }

    public PeerConnectionFactory(Options options, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory) {
        if (NativeLibrary.isLoaded() && ContextUtils.getApplicationContext() != null) {
            this.nativeFactory = nativeCreatePeerConnectionFactory(options, videoEncoderFactory, videoDecoderFactory);
            if (this.nativeFactory == 0) {
                throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
            }
            return;
        }
        throw new IllegalStateException("PeerConnectionFactory.initialize was not called before creating a PeerConnectionFactory.");
    }

    @Deprecated
    public static boolean initializeAndroidGlobals(Object obj, boolean z, boolean z2, boolean z3) {
        initializeAndroidGlobals((Context) obj, z3);
        return true;
    }

    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), mediaConstraints, observer);
    }
}
