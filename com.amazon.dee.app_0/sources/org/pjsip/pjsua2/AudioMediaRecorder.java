package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AudioMediaRecorder extends AudioMedia {
    private transient long swigCPtr;

    protected AudioMediaRecorder(long j, boolean z) {
        super(pjsua2JNI.AudioMediaRecorder_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMediaRecorder audioMediaRecorder) {
        if (audioMediaRecorder == null) {
            return 0L;
        }
        return audioMediaRecorder.swigCPtr;
    }

    public static AudioMediaRecorder typecastFromAudioMedia(AudioMedia audioMedia) {
        long AudioMediaRecorder_typecastFromAudioMedia = pjsua2JNI.AudioMediaRecorder_typecastFromAudioMedia(AudioMedia.getCPtr(audioMedia), audioMedia);
        if (AudioMediaRecorder_typecastFromAudioMedia == 0) {
            return null;
        }
        return new AudioMediaRecorder(AudioMediaRecorder_typecastFromAudioMedia, false);
    }

    public void createRecorder(String str, long j, SWIGTYPE_p_pj_ssize_t sWIGTYPE_p_pj_ssize_t, long j2) throws Exception {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_0(this.swigCPtr, this, str, j, SWIGTYPE_p_pj_ssize_t.getCPtr(sWIGTYPE_p_pj_ssize_t), j2);
    }

    @Override // org.pjsip.pjsua2.AudioMedia, org.pjsip.pjsua2.Media
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaRecorder(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.AudioMedia, org.pjsip.pjsua2.Media
    protected void finalize() {
        delete();
    }

    public void createRecorder(String str, long j, SWIGTYPE_p_pj_ssize_t sWIGTYPE_p_pj_ssize_t) throws Exception {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_1(this.swigCPtr, this, str, j, SWIGTYPE_p_pj_ssize_t.getCPtr(sWIGTYPE_p_pj_ssize_t));
    }

    public AudioMediaRecorder() {
        this(pjsua2JNI.new_AudioMediaRecorder(), true);
    }

    public void createRecorder(String str, long j) throws Exception {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_2(this.swigCPtr, this, str, j);
    }

    public void createRecorder(String str) throws Exception {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_3(this.swigCPtr, this, str);
    }
}
