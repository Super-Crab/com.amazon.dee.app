package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class CodecInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CodecInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CodecInfo codecInfo) {
        if (codecInfo == null) {
            return 0L;
        }
        return codecInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getCodecId() {
        return pjsua2JNI.CodecInfo_codecId_get(this.swigCPtr, this);
    }

    public String getDesc() {
        return pjsua2JNI.CodecInfo_desc_get(this.swigCPtr, this);
    }

    public short getPriority() {
        return pjsua2JNI.CodecInfo_priority_get(this.swigCPtr, this);
    }

    public void setCodecId(String str) {
        pjsua2JNI.CodecInfo_codecId_set(this.swigCPtr, this, str);
    }

    public void setDesc(String str) {
        pjsua2JNI.CodecInfo_desc_set(this.swigCPtr, this, str);
    }

    public void setPriority(short s) {
        pjsua2JNI.CodecInfo_priority_set(this.swigCPtr, this, s);
    }

    public CodecInfo() {
        this(pjsua2JNI.new_CodecInfo(), true);
    }
}
