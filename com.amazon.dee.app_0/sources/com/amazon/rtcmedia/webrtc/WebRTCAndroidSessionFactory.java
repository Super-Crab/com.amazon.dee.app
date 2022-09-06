package com.amazon.rtcmedia.webrtc;

import android.content.Context;
import com.amazon.rtcmedia.util.EglBaseProvider;
import com.amazon.rtcmedia.util.LooperExecutor;
import com.amazon.rtcmedia.util.RTCContextUtils;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import org.webrtc.EglBase;
import org.webrtc.voiceengine.WebRtcAudioUtils;
/* loaded from: classes13.dex */
public class WebRTCAndroidSessionFactory {
    private static final String VIDEO_CODEC_H264 = "H264";
    private static final String VIDEO_H264_AMLOGIC_HW_FIELDTRIAL = "WebRTC-AMLOGICH264/Enabled/";
    private static final String VIDEO_H264_BRCM_HW_FIELDTRIAL = "WebRTC-BRCMH264/Enabled/";
    private static final String VIDEO_H264_INTEL_HW_ENCODER_FIELDTRIAL = "WebRTC-IntelH264/Enabled/";
    private static final String VIDEO_H264_MS_HW_FIELDTRIAL = "WebRTC-MSH264/Enabled/";
    private static final String VIDEO_H264_MTK_HW_FIELDTRIAL = "WebRTC-MTKH264/Enabled/";
    private static final String VIDEO_H264_MTK_LOW_LATENCY_FIELDTRIAL = "WebRTC-MTKH264-LowLatency/Enabled/";
    private static final int WEBRTC_DEFAULT_SAMPLE_RATE_16K = 16000;
    private static volatile WebRTCAndroidSessionFactory androidSessionFactory;
    private static RtcscLogger mLog = RtcscLogger.getLogger(WebRTCAndroidSessionFactory.class);
    private EglBase.Context eglContext;
    private LooperExecutor executor;
    private PeerConnectionFactoryWrapper factoryWrapper = null;

    private WebRTCAndroidSessionFactory() {
    }

    public static WebRTCAndroidSessionFactory createInstance() {
        mLog.i("createInstance");
        if (androidSessionFactory == null) {
            synchronized (WebRTCAndroidSessionFactory.class) {
                if (androidSessionFactory == null) {
                    androidSessionFactory = new WebRTCAndroidSessionFactory();
                }
            }
        }
        return androidSessionFactory;
    }

    private void disposeInternal() {
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionFactory.2
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionFactoryWrapper.dispose();
                WebRTCAndroidSessionFactory.this.factoryWrapper = null;
                EglBaseProvider.release();
                WebRTCAndroidSessionFactory.this.eglContext = null;
                WebRTCAndroidSessionFactory.mLog.d("Destroyed peer connection factory.");
            }
        });
    }

    public static WebRTCAndroidSessionFactory getInstance() {
        return createInstance();
    }

    private void initializeInternal(final boolean z, final String str) {
        this.executor.execute(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.WebRTCAndroidSessionFactory.1
            @Override // java.lang.Runnable
            public void run() {
                if (WebRTCAndroidSessionFactory.this.factoryWrapper != null) {
                    WebRTCAndroidSessionFactory.mLog.i("Already initialized, and PeerConnectionFactory created!!");
                    return;
                }
                RtcscLogger rtcscLogger = WebRTCAndroidSessionFactory.mLog;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating PeerConnectionFactory, initialize WebRTC. Field trials: ");
                outline107.append(str);
                outline107.append(" Enable video HW acceleration: ");
                GeneratedOutlineSupport1.outline185(outline107, z, rtcscLogger);
                String str2 = str;
                if (!str2.contains("H264") && !str2.contains("H264".toLowerCase(Locale.ENGLISH))) {
                    WebRTCAndroidSessionFactory.mLog.i("H264 Codec is not part of field trials, adding default ones");
                    str2 = GeneratedOutlineSupport1.outline72(GeneratedOutlineSupport1.outline72(GeneratedOutlineSupport1.outline72(GeneratedOutlineSupport1.outline72(str2 + WebRTCAndroidSessionFactory.VIDEO_H264_INTEL_HW_ENCODER_FIELDTRIAL, "WebRTC-MSH264/Enabled/"), "WebRTC-AMLOGICH264/Enabled/"), WebRTCAndroidSessionFactory.VIDEO_H264_BRCM_HW_FIELDTRIAL), "WebRTC-MTKH264/Enabled/");
                }
                String str3 = str2;
                RtcscLogger rtcscLogger2 = WebRTCAndroidSessionFactory.mLog;
                rtcscLogger2.i("localFieldTrials += : " + str3);
                WebRtcAudioUtils.setDefaultSampleRateHz(16000);
                Context applicationContext = RTCContextUtils.getApplicationContext();
                if (applicationContext != null) {
                    WebRTCAndroidSessionFactory webRTCAndroidSessionFactory = WebRTCAndroidSessionFactory.this;
                    webRTCAndroidSessionFactory.factoryWrapper = PeerConnectionFactoryWrapper.getOrCreateInstance(applicationContext, z, str3, webRTCAndroidSessionFactory.eglContext, WebRTCAndroidSessionFactory.this.eglContext, false);
                    WebRTCAndroidSessionFactory.mLog.i("PeerConnectionFactory created");
                    return;
                }
                throw new IllegalArgumentException("Application context cannot be null for PeerConnectionFactory Initialization");
            }
        });
    }

    public synchronized void dispose() {
        mLog.i("disposing PeerConnectionFactory");
        if (this.executor != null) {
            disposeInternal();
            this.executor.requestStop();
            this.executor = null;
        }
    }

    public LooperExecutor getExecutor() {
        return this.executor;
    }

    public PeerConnectionFactoryWrapper getPeerConnectionFactoryWrapper() {
        LooperExecutor looperExecutor = this.executor;
        if (looperExecutor != null && looperExecutor.checkOnLooperThread()) {
            if (this.factoryWrapper == null) {
                mLog.e("WebRTCAndroidSessionFactory haven't been created yet");
            }
            return this.factoryWrapper;
        }
        throw new IllegalStateException("Wrong thread to call getPeerConnectionFactory");
    }

    public synchronized void initialize(boolean z, String str) {
        mLog.i("initialize");
        if (this.executor == null) {
            this.executor = new LooperExecutor("WebRTCLooperExec");
            this.executor.requestStart();
            if (z && this.eglContext == null) {
                this.eglContext = EglBaseProvider.getEglBaseContext();
            }
            initializeInternal(z, str);
            return;
        }
        mLog.w("initialize has been called twice without dispose, do nothing");
    }
}
