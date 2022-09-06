package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VidCodecParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VidCodecParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(VidCodecParam vidCodecParam) {
        if (vidCodecParam == null) {
            return 0L;
        }
        return vidCodecParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VidCodecParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaFormatVideo getDecFmt() {
        long VidCodecParam_decFmt_get = pjsua2JNI.VidCodecParam_decFmt_get(this.swigCPtr, this);
        if (VidCodecParam_decFmt_get == 0) {
            return null;
        }
        return new MediaFormatVideo(VidCodecParam_decFmt_get, false);
    }

    public CodecFmtpVector getDecFmtp() {
        long VidCodecParam_decFmtp_get = pjsua2JNI.VidCodecParam_decFmtp_get(this.swigCPtr, this);
        if (VidCodecParam_decFmtp_get == 0) {
            return null;
        }
        return new CodecFmtpVector(VidCodecParam_decFmtp_get, false);
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.VidCodecParam_dir_get(this.swigCPtr, this));
    }

    public MediaFormatVideo getEncFmt() {
        long VidCodecParam_encFmt_get = pjsua2JNI.VidCodecParam_encFmt_get(this.swigCPtr, this);
        if (VidCodecParam_encFmt_get == 0) {
            return null;
        }
        return new MediaFormatVideo(VidCodecParam_encFmt_get, false);
    }

    public CodecFmtpVector getEncFmtp() {
        long VidCodecParam_encFmtp_get = pjsua2JNI.VidCodecParam_encFmtp_get(this.swigCPtr, this);
        if (VidCodecParam_encFmtp_get == 0) {
            return null;
        }
        return new CodecFmtpVector(VidCodecParam_encFmtp_get, false);
    }

    public long getEncMtu() {
        return pjsua2JNI.VidCodecParam_encMtu_get(this.swigCPtr, this);
    }

    public boolean getIgnoreFmtp() {
        return pjsua2JNI.VidCodecParam_ignoreFmtp_get(this.swigCPtr, this);
    }

    public pjmedia_vid_packing getPacking() {
        return pjmedia_vid_packing.swigToEnum(pjsua2JNI.VidCodecParam_packing_get(this.swigCPtr, this));
    }

    public void setDecFmt(MediaFormatVideo mediaFormatVideo) {
        pjsua2JNI.VidCodecParam_decFmt_set(this.swigCPtr, this, MediaFormatVideo.getCPtr(mediaFormatVideo), mediaFormatVideo);
    }

    public void setDecFmtp(CodecFmtpVector codecFmtpVector) {
        pjsua2JNI.VidCodecParam_decFmtp_set(this.swigCPtr, this, CodecFmtpVector.getCPtr(codecFmtpVector), codecFmtpVector);
    }

    public void setDir(pjmedia_dir pjmedia_dirVar) {
        pjsua2JNI.VidCodecParam_dir_set(this.swigCPtr, this, pjmedia_dirVar.swigValue());
    }

    public void setEncFmt(MediaFormatVideo mediaFormatVideo) {
        pjsua2JNI.VidCodecParam_encFmt_set(this.swigCPtr, this, MediaFormatVideo.getCPtr(mediaFormatVideo), mediaFormatVideo);
    }

    public void setEncFmtp(CodecFmtpVector codecFmtpVector) {
        pjsua2JNI.VidCodecParam_encFmtp_set(this.swigCPtr, this, CodecFmtpVector.getCPtr(codecFmtpVector), codecFmtpVector);
    }

    public void setEncMtu(long j) {
        pjsua2JNI.VidCodecParam_encMtu_set(this.swigCPtr, this, j);
    }

    public void setIgnoreFmtp(boolean z) {
        pjsua2JNI.VidCodecParam_ignoreFmtp_set(this.swigCPtr, this, z);
    }

    public void setPacking(pjmedia_vid_packing pjmedia_vid_packingVar) {
        pjsua2JNI.VidCodecParam_packing_set(this.swigCPtr, this, pjmedia_vid_packingVar.swigValue());
    }

    public VidCodecParam() {
        this(pjsua2JNI.new_VidCodecParam(), true);
    }
}
