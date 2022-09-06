package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AudDevManager {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AudDevManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudDevManager audDevManager) {
        if (audDevManager == null) {
            return 0L;
        }
        return audDevManager.swigCPtr;
    }

    public String capName(pjmedia_aud_dev_cap pjmedia_aud_dev_capVar) {
        return pjsua2JNI.AudDevManager_capName(this.swigCPtr, this, pjmedia_aud_dev_capVar.swigValue());
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

    public AudioDevInfoVector enumDev() throws Exception {
        return new AudioDevInfoVector(pjsua2JNI.AudDevManager_enumDev(this.swigCPtr, this), false);
    }

    public int getCaptureDev() throws Exception {
        return pjsua2JNI.AudDevManager_getCaptureDev(this.swigCPtr, this);
    }

    public AudioMedia getCaptureDevMedia() throws Exception {
        return new AudioMedia(pjsua2JNI.AudDevManager_getCaptureDevMedia(this.swigCPtr, this), false);
    }

    public boolean getCng() throws Exception {
        return pjsua2JNI.AudDevManager_getCng(this.swigCPtr, this);
    }

    public long getDevCount() {
        return pjsua2JNI.AudDevManager_getDevCount(this.swigCPtr, this);
    }

    public AudioDevInfo getDevInfo(int i) throws Exception {
        return new AudioDevInfo(pjsua2JNI.AudDevManager_getDevInfo(this.swigCPtr, this, i), true);
    }

    public long getEcTail() throws Exception {
        return pjsua2JNI.AudDevManager_getEcTail(this.swigCPtr, this);
    }

    public MediaFormatAudio getExtFormat() throws Exception {
        return new MediaFormatAudio(pjsua2JNI.AudDevManager_getExtFormat(this.swigCPtr, this), true);
    }

    public long getInputLatency() throws Exception {
        return pjsua2JNI.AudDevManager_getInputLatency(this.swigCPtr, this);
    }

    public pjmedia_aud_dev_route getInputRoute() throws Exception {
        return pjmedia_aud_dev_route.swigToEnum(pjsua2JNI.AudDevManager_getInputRoute(this.swigCPtr, this));
    }

    public long getInputSignal() throws Exception {
        return pjsua2JNI.AudDevManager_getInputSignal(this.swigCPtr, this);
    }

    public long getInputVolume() throws Exception {
        return pjsua2JNI.AudDevManager_getInputVolume(this.swigCPtr, this);
    }

    public long getOutputLatency() throws Exception {
        return pjsua2JNI.AudDevManager_getOutputLatency(this.swigCPtr, this);
    }

    public pjmedia_aud_dev_route getOutputRoute() throws Exception {
        return pjmedia_aud_dev_route.swigToEnum(pjsua2JNI.AudDevManager_getOutputRoute(this.swigCPtr, this));
    }

    public long getOutputSignal() throws Exception {
        return pjsua2JNI.AudDevManager_getOutputSignal(this.swigCPtr, this);
    }

    public long getOutputVolume() throws Exception {
        return pjsua2JNI.AudDevManager_getOutputVolume(this.swigCPtr, this);
    }

    public int getPlaybackDev() throws Exception {
        return pjsua2JNI.AudDevManager_getPlaybackDev(this.swigCPtr, this);
    }

    public AudioMedia getPlaybackDevMedia() throws Exception {
        return new AudioMedia(pjsua2JNI.AudDevManager_getPlaybackDevMedia(this.swigCPtr, this), false);
    }

    public boolean getPlc() throws Exception {
        return pjsua2JNI.AudDevManager_getPlc(this.swigCPtr, this);
    }

    public boolean getVad() throws Exception {
        return pjsua2JNI.AudDevManager_getVad(this.swigCPtr, this);
    }

    public int lookupDev(String str, String str2) throws Exception {
        return pjsua2JNI.AudDevManager_lookupDev(this.swigCPtr, this, str, str2);
    }

    public void refreshDevs() throws Exception {
        pjsua2JNI.AudDevManager_refreshDevs(this.swigCPtr, this);
    }

    public void setCaptureDev(int i) throws Exception {
        pjsua2JNI.AudDevManager_setCaptureDev(this.swigCPtr, this, i);
    }

    public void setCng(boolean z, boolean z2) throws Exception {
        pjsua2JNI.AudDevManager_setCng__SWIG_0(this.swigCPtr, this, z, z2);
    }

    public void setEcOptions(long j, long j2) throws Exception {
        pjsua2JNI.AudDevManager_setEcOptions(this.swigCPtr, this, j, j2);
    }

    public void setExtFormat(MediaFormatAudio mediaFormatAudio, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setExtFormat__SWIG_0(this.swigCPtr, this, MediaFormatAudio.getCPtr(mediaFormatAudio), mediaFormatAudio, z);
    }

    public void setInputLatency(long j, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setInputLatency__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setInputRoute(pjmedia_aud_dev_route pjmedia_aud_dev_routeVar, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setInputRoute__SWIG_0(this.swigCPtr, this, pjmedia_aud_dev_routeVar.swigValue(), z);
    }

    public void setInputVolume(long j, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setInputVolume__SWIG_0(this.swigCPtr, this, j, z);
    }

    public SWIGTYPE_p_p_void setNoDev() {
        long AudDevManager_setNoDev = pjsua2JNI.AudDevManager_setNoDev(this.swigCPtr, this);
        if (AudDevManager_setNoDev == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(AudDevManager_setNoDev, false);
    }

    public void setNullDev() throws Exception {
        pjsua2JNI.AudDevManager_setNullDev(this.swigCPtr, this);
    }

    public void setOutputLatency(long j, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setOutputLatency__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setOutputRoute(pjmedia_aud_dev_route pjmedia_aud_dev_routeVar, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setOutputRoute__SWIG_0(this.swigCPtr, this, pjmedia_aud_dev_routeVar.swigValue(), z);
    }

    public void setOutputVolume(long j, boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setOutputVolume__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setPlaybackDev(int i) throws Exception {
        pjsua2JNI.AudDevManager_setPlaybackDev(this.swigCPtr, this, i);
    }

    public void setPlc(boolean z, boolean z2) throws Exception {
        pjsua2JNI.AudDevManager_setPlc__SWIG_0(this.swigCPtr, this, z, z2);
    }

    public void setSndDevMode(long j) throws Exception {
        pjsua2JNI.AudDevManager_setSndDevMode(this.swigCPtr, this, j);
    }

    public void setVad(boolean z, boolean z2) throws Exception {
        pjsua2JNI.AudDevManager_setVad__SWIG_0(this.swigCPtr, this, z, z2);
    }

    public boolean sndIsActive() {
        return pjsua2JNI.AudDevManager_sndIsActive(this.swigCPtr, this);
    }

    public void setCng(boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setCng__SWIG_1(this.swigCPtr, this, z);
    }

    public void setExtFormat(MediaFormatAudio mediaFormatAudio) throws Exception {
        pjsua2JNI.AudDevManager_setExtFormat__SWIG_1(this.swigCPtr, this, MediaFormatAudio.getCPtr(mediaFormatAudio), mediaFormatAudio);
    }

    public void setInputLatency(long j) throws Exception {
        pjsua2JNI.AudDevManager_setInputLatency__SWIG_1(this.swigCPtr, this, j);
    }

    public void setInputRoute(pjmedia_aud_dev_route pjmedia_aud_dev_routeVar) throws Exception {
        pjsua2JNI.AudDevManager_setInputRoute__SWIG_1(this.swigCPtr, this, pjmedia_aud_dev_routeVar.swigValue());
    }

    public void setInputVolume(long j) throws Exception {
        pjsua2JNI.AudDevManager_setInputVolume__SWIG_1(this.swigCPtr, this, j);
    }

    public void setOutputLatency(long j) throws Exception {
        pjsua2JNI.AudDevManager_setOutputLatency__SWIG_1(this.swigCPtr, this, j);
    }

    public void setOutputRoute(pjmedia_aud_dev_route pjmedia_aud_dev_routeVar) throws Exception {
        pjsua2JNI.AudDevManager_setOutputRoute__SWIG_1(this.swigCPtr, this, pjmedia_aud_dev_routeVar.swigValue());
    }

    public void setOutputVolume(long j) throws Exception {
        pjsua2JNI.AudDevManager_setOutputVolume__SWIG_1(this.swigCPtr, this, j);
    }

    public void setPlc(boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setPlc__SWIG_1(this.swigCPtr, this, z);
    }

    public void setVad(boolean z) throws Exception {
        pjsua2JNI.AudDevManager_setVad__SWIG_1(this.swigCPtr, this, z);
    }
}
