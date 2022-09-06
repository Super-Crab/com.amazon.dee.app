package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class Version {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public Version(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Version version) {
        if (version == null) {
            return 0L;
        }
        return version.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Version(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getFull() {
        return pjsua2JNI.Version_full_get(this.swigCPtr, this);
    }

    public int getMajor() {
        return pjsua2JNI.Version_major_get(this.swigCPtr, this);
    }

    public int getMinor() {
        return pjsua2JNI.Version_minor_get(this.swigCPtr, this);
    }

    public long getNumeric() {
        return pjsua2JNI.Version_numeric_get(this.swigCPtr, this);
    }

    public int getRev() {
        return pjsua2JNI.Version_rev_get(this.swigCPtr, this);
    }

    public String getSuffix() {
        return pjsua2JNI.Version_suffix_get(this.swigCPtr, this);
    }

    public void setFull(String str) {
        pjsua2JNI.Version_full_set(this.swigCPtr, this, str);
    }

    public void setMajor(int i) {
        pjsua2JNI.Version_major_set(this.swigCPtr, this, i);
    }

    public void setMinor(int i) {
        pjsua2JNI.Version_minor_set(this.swigCPtr, this, i);
    }

    public void setNumeric(long j) {
        pjsua2JNI.Version_numeric_set(this.swigCPtr, this, j);
    }

    public void setRev(int i) {
        pjsua2JNI.Version_rev_set(this.swigCPtr, this, i);
    }

    public void setSuffix(String str) {
        pjsua2JNI.Version_suffix_set(this.swigCPtr, this, str);
    }

    public Version() {
        this(pjsua2JNI.new_Version(), true);
    }
}
