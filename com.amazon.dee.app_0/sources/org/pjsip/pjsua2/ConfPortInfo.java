package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class ConfPortInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfPortInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ConfPortInfo confPortInfo) {
        if (confPortInfo == null) {
            return 0L;
        }
        return confPortInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ConfPortInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaFormatAudio getFormat() {
        long ConfPortInfo_format_get = pjsua2JNI.ConfPortInfo_format_get(this.swigCPtr, this);
        if (ConfPortInfo_format_get == 0) {
            return null;
        }
        return new MediaFormatAudio(ConfPortInfo_format_get, false);
    }

    public IntVector getListeners() {
        long ConfPortInfo_listeners_get = pjsua2JNI.ConfPortInfo_listeners_get(this.swigCPtr, this);
        if (ConfPortInfo_listeners_get == 0) {
            return null;
        }
        return new IntVector(ConfPortInfo_listeners_get, false);
    }

    public String getName() {
        return pjsua2JNI.ConfPortInfo_name_get(this.swigCPtr, this);
    }

    public int getPortId() {
        return pjsua2JNI.ConfPortInfo_portId_get(this.swigCPtr, this);
    }

    public float getRxLevelAdj() {
        return pjsua2JNI.ConfPortInfo_rxLevelAdj_get(this.swigCPtr, this);
    }

    public float getTxLevelAdj() {
        return pjsua2JNI.ConfPortInfo_txLevelAdj_get(this.swigCPtr, this);
    }

    public void setFormat(MediaFormatAudio mediaFormatAudio) {
        pjsua2JNI.ConfPortInfo_format_set(this.swigCPtr, this, MediaFormatAudio.getCPtr(mediaFormatAudio), mediaFormatAudio);
    }

    public void setListeners(IntVector intVector) {
        pjsua2JNI.ConfPortInfo_listeners_set(this.swigCPtr, this, IntVector.getCPtr(intVector), intVector);
    }

    public void setName(String str) {
        pjsua2JNI.ConfPortInfo_name_set(this.swigCPtr, this, str);
    }

    public void setPortId(int i) {
        pjsua2JNI.ConfPortInfo_portId_set(this.swigCPtr, this, i);
    }

    public void setRxLevelAdj(float f) {
        pjsua2JNI.ConfPortInfo_rxLevelAdj_set(this.swigCPtr, this, f);
    }

    public void setTxLevelAdj(float f) {
        pjsua2JNI.ConfPortInfo_txLevelAdj_set(this.swigCPtr, this, f);
    }

    public ConfPortInfo() {
        this(pjsua2JNI.new_ConfPortInfo(), true);
    }
}
