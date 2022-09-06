package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SrtpCrypto {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected SrtpCrypto(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SrtpCrypto srtpCrypto) {
        if (srtpCrypto == null) {
            return 0L;
        }
        return srtpCrypto.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SrtpCrypto(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getFlags() {
        return pjsua2JNI.SrtpCrypto_flags_get(this.swigCPtr, this);
    }

    public String getKey() {
        return pjsua2JNI.SrtpCrypto_key_get(this.swigCPtr, this);
    }

    public String getName() {
        return pjsua2JNI.SrtpCrypto_name_get(this.swigCPtr, this);
    }

    public void setFlags(long j) {
        pjsua2JNI.SrtpCrypto_flags_set(this.swigCPtr, this, j);
    }

    public void setKey(String str) {
        pjsua2JNI.SrtpCrypto_key_set(this.swigCPtr, this, str);
    }

    public void setName(String str) {
        pjsua2JNI.SrtpCrypto_name_set(this.swigCPtr, this, str);
    }

    public SrtpCrypto() {
        this(pjsua2JNI.new_SrtpCrypto(), true);
    }
}
