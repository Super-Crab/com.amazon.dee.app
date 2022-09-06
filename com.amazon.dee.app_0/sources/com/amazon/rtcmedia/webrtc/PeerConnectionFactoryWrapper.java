package com.amazon.rtcmedia.webrtc;

import android.content.Context;
import com.amazon.rtcmedia.util.Logger;
import com.google.common.base.Strings;
import java.util.Locale;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.EglBase;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;
/* loaded from: classes13.dex */
public class PeerConnectionFactoryWrapper {
    private static volatile PeerConnectionFactoryWrapper factoryWrapper;
    private static Logger mLog = Logger.getLogger(PeerConnectionFactoryWrapper.class);
    private static int refCount = 0;
    private static PeerConnectionFactory factory = null;
    private static boolean isVideoCapable = false;

    private PeerConnectionFactoryWrapper() {
    }

    public static void dispose() {
        mLog.i("dispose");
        synchronized (PeerConnectionFactoryWrapper.class) {
            mLog.d(String.format(Locale.US, "Refcount is: %d", Integer.valueOf(refCount)));
            if (refCount > 0) {
                refCount--;
            }
            if (refCount == 0) {
                PeerConnectionFactory.stopInternalTracingCapture();
                PeerConnectionFactory.shutdownInternalTracer();
                if (factoryWrapper != null) {
                    if (factory != null) {
                        if (isVideoCapable) {
                            factory.setVideoHwAccelerationOptions(null, null);
                        }
                        factory.dispose();
                        factory = null;
                        mLog.i("Destroyed peer connection factory.");
                    }
                    factoryWrapper = null;
                    mLog.i("Destroyed peer connection factory wrapper.");
                }
            }
        }
    }

    public static PeerConnectionFactoryWrapper getOrCreateInstance(Context context, boolean z, String str, EglBase.Context context2, EglBase.Context context3, boolean z2) {
        mLog.i("getOrCreateInstance");
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factoryWrapper == null) {
                factoryWrapper = new PeerConnectionFactoryWrapper();
                PeerConnectionFactory.InitializationOptions.Builder enableVideoHwAcceleration = PeerConnectionFactory.InitializationOptions.builder(context).setEnableInternalTracer(z2).setEnableVideoHwAcceleration(z);
                if (!Strings.isNullOrEmpty(str)) {
                    enableVideoHwAcceleration.setFieldTrials(str);
                }
                PeerConnectionFactory.initialize(enableVideoHwAcceleration.createInitializationOptions());
                factory = new PeerConnectionFactory(null);
                if (z) {
                    factory.setVideoHwAccelerationOptions(context2, context3);
                }
                isVideoCapable = z;
                mLog.i("Created peer connection factory.");
            }
            refCount++;
            mLog.d(String.format(Locale.US, "Refcount is: %d", Integer.valueOf(refCount)));
        }
        return factoryWrapper;
    }

    public AudioSource createAudioSource(MediaConstraints mediaConstraints) {
        mLog.i("createAudioSource");
        AudioSource audioSource = null;
        if (mediaConstraints == null) {
            mLog.e("Constraints are null. Not creating audio source.");
            return null;
        }
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factory != null) {
                audioSource = factory.createAudioSource(mediaConstraints);
            } else {
                mLog.e("Factory is null. Not creating audio source.");
            }
        }
        return audioSource;
    }

    public AudioTrack createAudioTrack(String str, AudioSource audioSource) {
        mLog.i("createAudioTrack");
        AudioTrack audioTrack = null;
        if (!Strings.isNullOrEmpty(str) && audioSource != null) {
            synchronized (PeerConnectionFactoryWrapper.class) {
                if (factory != null) {
                    audioTrack = factory.createAudioTrack(str, audioSource);
                } else {
                    mLog.e("Factory is null. Not creating audio track.");
                }
            }
            return audioTrack;
        }
        mLog.e("One or more parameters are null. Not creating audio track.");
        return null;
    }

    public MediaStream createLocalMediaStream(String str) {
        mLog.i("createLocalMediaStream");
        MediaStream mediaStream = null;
        if (str.isEmpty()) {
            mLog.e("Label is empty. Not creating local media stream.");
            return null;
        }
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factory != null) {
                mediaStream = factory.createLocalMediaStream(str);
            } else {
                mLog.e("Factory is null. Not creating local media stream.");
            }
        }
        return mediaStream;
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        mLog.i("createPeerConnection");
        PeerConnection peerConnection = null;
        if (rTCConfiguration != null && mediaConstraints != null && observer != null) {
            synchronized (PeerConnectionFactoryWrapper.class) {
                if (factory != null) {
                    peerConnection = factory.createPeerConnection(rTCConfiguration, mediaConstraints, observer);
                } else {
                    mLog.e("Factory is null. Not creating peer connection.");
                }
            }
            return peerConnection;
        }
        mLog.e("One or more parameters are null. Not creating peer connection.");
        return null;
    }

    public VideoSource createVideoSource(VideoCapturer videoCapturer) {
        mLog.i("createVideoSource");
        VideoSource videoSource = null;
        if (videoCapturer == null) {
            mLog.e("Video capturer is null. Not creating video source.");
            return null;
        }
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factory != null) {
                videoSource = factory.createVideoSource(videoCapturer);
            } else {
                mLog.e("Factory is null. Not creating video source.");
            }
        }
        return videoSource;
    }

    public VideoTrack createVideoTrack(String str, VideoSource videoSource) {
        mLog.i("createVideoTrack");
        VideoTrack videoTrack = null;
        if (videoSource == null) {
            mLog.e("Video source is null. Not creating video track.");
            return null;
        }
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factory != null) {
                videoTrack = factory.createVideoTrack(str, videoSource);
            } else {
                mLog.e("Factory is null. Not creating video track.");
            }
        }
        return videoTrack;
    }

    public PeerConnectionFactory getFactory() {
        PeerConnectionFactory peerConnectionFactory;
        mLog.i("getFactory");
        synchronized (PeerConnectionFactoryWrapper.class) {
            peerConnectionFactory = factory;
        }
        return peerConnectionFactory;
    }

    public boolean startAecDump(int i, int i2) {
        boolean z;
        mLog.i("startAecDump");
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factory != null) {
                z = factory.startAecDump(i, i2);
            } else {
                mLog.e("Factory is null. Not starting AEC dump.");
                z = false;
            }
        }
        return z;
    }

    public void stopAecDump() {
        mLog.i("stopAecDump");
        synchronized (PeerConnectionFactoryWrapper.class) {
            if (factory != null) {
                factory.stopAecDump();
            } else {
                mLog.e("Factory is null. Not stopping AEC dump.");
            }
        }
    }
}
