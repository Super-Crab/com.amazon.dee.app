package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class pjmedia_tone_digit_map {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected pjmedia_tone_digit_map(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(pjmedia_tone_digit_map pjmedia_tone_digit_mapVar) {
        if (pjmedia_tone_digit_mapVar == null) {
            return 0L;
        }
        return pjmedia_tone_digit_mapVar.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_pjmedia_tone_digit_map(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getCount() {
        return pjsua2JNI.pjmedia_tone_digit_map_count_get(this.swigCPtr, this);
    }

    public void setCount(long j) {
        pjsua2JNI.pjmedia_tone_digit_map_count_set(this.swigCPtr, this, j);
    }

    public pjmedia_tone_digit_map() {
        this(pjsua2JNI.new_pjmedia_tone_digit_map(), true);
    }
}
