package com.amazon.comms.ringservice.webrtc;

import com.amazon.comms.log.CommsLogger;
import org.webrtc.LocalAudioVideoShim;
/* loaded from: classes12.dex */
public class WebrtcVideoCodecHwSupportCheckImpl implements VideoCodecHwSupportChecker {
    private static final CommsLogger log = CommsLogger.getLogger(WebrtcVideoCodecHwSupportCheckImpl.class);
    private boolean doFreshVideoCodecQueryNextTime = true;
    private boolean isH264HwEncodeSupported = true;
    private boolean isH264HwDecodeSupported = true;
    private boolean isVp8HwEncodeSupported = true;
    private boolean isVp8HwDecodeSupported = true;

    private synchronized void queryVideoHwCodecSupport() {
        this.doFreshVideoCodecQueryNextTime = false;
        this.isH264HwEncodeSupported = true;
        try {
            this.isH264HwEncodeSupported = LocalAudioVideoShim.isH264HwEncodeSupported();
            CommsLogger commsLogger = log;
            commsLogger.v("isH264HwEncodeSupported= " + this.isH264HwEncodeSupported);
        } catch (RuntimeException e) {
            log.w("There was an unexpected error while attempting to determine H264 encode support. ", e);
            this.doFreshVideoCodecQueryNextTime = true;
        }
        this.isH264HwDecodeSupported = true;
        try {
            this.isH264HwDecodeSupported = LocalAudioVideoShim.isH264HwDecodeSupported();
            CommsLogger commsLogger2 = log;
            commsLogger2.v("isH264HwDecodeSupported= " + this.isH264HwDecodeSupported);
        } catch (RuntimeException e2) {
            log.e("There was an unexpected error while attempting to determine H264 support. ", e2);
            this.doFreshVideoCodecQueryNextTime = true;
        }
        this.isVp8HwEncodeSupported = true;
        try {
            this.isVp8HwEncodeSupported = LocalAudioVideoShim.isVP8HwEncodeSupported();
            CommsLogger commsLogger3 = log;
            commsLogger3.v("isVp8HwEncodeSupported= " + this.isVp8HwEncodeSupported);
        } catch (RuntimeException e3) {
            log.e("There was an unexpected error while attempting to determine Vp8 support. ", e3);
            this.doFreshVideoCodecQueryNextTime = true;
        }
        this.isVp8HwDecodeSupported = true;
        try {
            this.isVp8HwDecodeSupported = LocalAudioVideoShim.isVP8HwDecodeSupported();
            CommsLogger commsLogger4 = log;
            commsLogger4.v("isVp8HwDecodeSupported= " + this.isVp8HwDecodeSupported);
        } catch (RuntimeException e4) {
            log.e("There was an unexpected error while attempting to determine Vp8 support. ", e4);
            this.doFreshVideoCodecQueryNextTime = true;
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoCodecHwSupportChecker
    public synchronized boolean isH264HwDecodeSupported() {
        if (this.doFreshVideoCodecQueryNextTime) {
            queryVideoHwCodecSupport();
        }
        return this.isH264HwDecodeSupported;
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoCodecHwSupportChecker
    public synchronized boolean isH264HwEncodeSupported() {
        if (this.doFreshVideoCodecQueryNextTime) {
            queryVideoHwCodecSupport();
        }
        return this.isH264HwEncodeSupported;
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoCodecHwSupportChecker
    public synchronized boolean isH264OrVp8HwDecodeSupported() {
        boolean z;
        if (this.doFreshVideoCodecQueryNextTime) {
            queryVideoHwCodecSupport();
        }
        if (!this.isH264HwDecodeSupported) {
            if (!this.isVp8HwDecodeSupported) {
                z = false;
            }
        }
        z = true;
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0016, code lost:
        if (r1.isVp8HwDecodeSupported != false) goto L10;
     */
    @Override // com.amazon.comms.ringservice.webrtc.VideoCodecHwSupportChecker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isH264OrVp8HwEncodeAndDecodeSupported() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.doFreshVideoCodecQueryNextTime     // Catch: java.lang.Throwable -> L1d
            if (r0 == 0) goto L8
            r1.queryVideoHwCodecSupport()     // Catch: java.lang.Throwable -> L1d
        L8:
            boolean r0 = r1.isH264HwEncodeSupported     // Catch: java.lang.Throwable -> L1d
            if (r0 == 0) goto L10
            boolean r0 = r1.isH264HwDecodeSupported     // Catch: java.lang.Throwable -> L1d
            if (r0 != 0) goto L18
        L10:
            boolean r0 = r1.isVp8HwEncodeSupported     // Catch: java.lang.Throwable -> L1d
            if (r0 == 0) goto L1a
            boolean r0 = r1.isVp8HwDecodeSupported     // Catch: java.lang.Throwable -> L1d
            if (r0 == 0) goto L1a
        L18:
            r0 = 1
            goto L1b
        L1a:
            r0 = 0
        L1b:
            monitor-exit(r1)
            return r0
        L1d:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.ringservice.webrtc.WebrtcVideoCodecHwSupportCheckImpl.isH264OrVp8HwEncodeAndDecodeSupported():boolean");
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoCodecHwSupportChecker
    public synchronized boolean isVp8HwDecodeSupported() {
        if (this.doFreshVideoCodecQueryNextTime) {
            queryVideoHwCodecSupport();
        }
        return this.isVp8HwDecodeSupported;
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoCodecHwSupportChecker
    public synchronized boolean isVp8HwEncodeSupported() {
        if (this.doFreshVideoCodecQueryNextTime) {
            queryVideoHwCodecSupport();
        }
        return this.isVp8HwEncodeSupported;
    }

    public synchronized void webrtcFactoryInitialized() {
        queryVideoHwCodecSupport();
    }

    public synchronized void webrtcFactoryShutdown() {
        this.isH264HwEncodeSupported = true;
        this.isH264HwDecodeSupported = true;
        this.isVp8HwEncodeSupported = true;
        this.isVp8HwDecodeSupported = true;
        this.doFreshVideoCodecQueryNextTime = true;
    }
}
