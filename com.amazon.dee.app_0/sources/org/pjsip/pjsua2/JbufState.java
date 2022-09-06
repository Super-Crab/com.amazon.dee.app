package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class JbufState {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public JbufState(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(JbufState jbufState) {
        if (jbufState == null) {
            return 0L;
        }
        return jbufState.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_JbufState(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAvgBurst() {
        return pjsua2JNI.JbufState_avgBurst_get(this.swigCPtr, this);
    }

    public long getAvgDelayMsec() {
        return pjsua2JNI.JbufState_avgDelayMsec_get(this.swigCPtr, this);
    }

    public long getBurst() {
        return pjsua2JNI.JbufState_burst_get(this.swigCPtr, this);
    }

    public long getDevDelayMsec() {
        return pjsua2JNI.JbufState_devDelayMsec_get(this.swigCPtr, this);
    }

    public long getDiscard() {
        return pjsua2JNI.JbufState_discard_get(this.swigCPtr, this);
    }

    public long getEmpty() {
        return pjsua2JNI.JbufState_empty_get(this.swigCPtr, this);
    }

    public long getFrameSize() {
        return pjsua2JNI.JbufState_frameSize_get(this.swigCPtr, this);
    }

    public long getLost() {
        return pjsua2JNI.JbufState_lost_get(this.swigCPtr, this);
    }

    public long getMaxDelayMsec() {
        return pjsua2JNI.JbufState_maxDelayMsec_get(this.swigCPtr, this);
    }

    public long getMaxPrefetch() {
        return pjsua2JNI.JbufState_maxPrefetch_get(this.swigCPtr, this);
    }

    public long getMinDelayMsec() {
        return pjsua2JNI.JbufState_minDelayMsec_get(this.swigCPtr, this);
    }

    public long getMinPrefetch() {
        return pjsua2JNI.JbufState_minPrefetch_get(this.swigCPtr, this);
    }

    public long getPrefetch() {
        return pjsua2JNI.JbufState_prefetch_get(this.swigCPtr, this);
    }

    public long getSize() {
        return pjsua2JNI.JbufState_size_get(this.swigCPtr, this);
    }

    public void setAvgBurst(long j) {
        pjsua2JNI.JbufState_avgBurst_set(this.swigCPtr, this, j);
    }

    public void setAvgDelayMsec(long j) {
        pjsua2JNI.JbufState_avgDelayMsec_set(this.swigCPtr, this, j);
    }

    public void setBurst(long j) {
        pjsua2JNI.JbufState_burst_set(this.swigCPtr, this, j);
    }

    public void setDevDelayMsec(long j) {
        pjsua2JNI.JbufState_devDelayMsec_set(this.swigCPtr, this, j);
    }

    public void setDiscard(long j) {
        pjsua2JNI.JbufState_discard_set(this.swigCPtr, this, j);
    }

    public void setEmpty(long j) {
        pjsua2JNI.JbufState_empty_set(this.swigCPtr, this, j);
    }

    public void setFrameSize(long j) {
        pjsua2JNI.JbufState_frameSize_set(this.swigCPtr, this, j);
    }

    public void setLost(long j) {
        pjsua2JNI.JbufState_lost_set(this.swigCPtr, this, j);
    }

    public void setMaxDelayMsec(long j) {
        pjsua2JNI.JbufState_maxDelayMsec_set(this.swigCPtr, this, j);
    }

    public void setMaxPrefetch(long j) {
        pjsua2JNI.JbufState_maxPrefetch_set(this.swigCPtr, this, j);
    }

    public void setMinDelayMsec(long j) {
        pjsua2JNI.JbufState_minDelayMsec_set(this.swigCPtr, this, j);
    }

    public void setMinPrefetch(long j) {
        pjsua2JNI.JbufState_minPrefetch_set(this.swigCPtr, this, j);
    }

    public void setPrefetch(long j) {
        pjsua2JNI.JbufState_prefetch_set(this.swigCPtr, this, j);
    }

    public void setSize(long j) {
        pjsua2JNI.JbufState_size_set(this.swigCPtr, this, j);
    }

    public JbufState() {
        this(pjsua2JNI.new_JbufState(), true);
    }
}
