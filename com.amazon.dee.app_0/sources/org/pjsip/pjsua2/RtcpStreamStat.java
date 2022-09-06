package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class RtcpStreamStat {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RtcpStreamStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RtcpStreamStat rtcpStreamStat) {
        if (rtcpStreamStat == null) {
            return 0L;
        }
        return rtcpStreamStat.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RtcpStreamStat(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getBytes() {
        return pjsua2JNI.RtcpStreamStat_bytes_get(this.swigCPtr, this);
    }

    public long getDiscard() {
        return pjsua2JNI.RtcpStreamStat_discard_get(this.swigCPtr, this);
    }

    public long getDup() {
        return pjsua2JNI.RtcpStreamStat_dup_get(this.swigCPtr, this);
    }

    public MathStat getJitterUsec() {
        long RtcpStreamStat_jitterUsec_get = pjsua2JNI.RtcpStreamStat_jitterUsec_get(this.swigCPtr, this);
        if (RtcpStreamStat_jitterUsec_get == 0) {
            return null;
        }
        return new MathStat(RtcpStreamStat_jitterUsec_get, false);
    }

    public long getLoss() {
        return pjsua2JNI.RtcpStreamStat_loss_get(this.swigCPtr, this);
    }

    public MathStat getLossPeriodUsec() {
        long RtcpStreamStat_lossPeriodUsec_get = pjsua2JNI.RtcpStreamStat_lossPeriodUsec_get(this.swigCPtr, this);
        if (RtcpStreamStat_lossPeriodUsec_get == 0) {
            return null;
        }
        return new MathStat(RtcpStreamStat_lossPeriodUsec_get, false);
    }

    public LossType getLossType() {
        long RtcpStreamStat_lossType_get = pjsua2JNI.RtcpStreamStat_lossType_get(this.swigCPtr, this);
        if (RtcpStreamStat_lossType_get == 0) {
            return null;
        }
        return new LossType(RtcpStreamStat_lossType_get, false);
    }

    public long getPkt() {
        return pjsua2JNI.RtcpStreamStat_pkt_get(this.swigCPtr, this);
    }

    public long getReorder() {
        return pjsua2JNI.RtcpStreamStat_reorder_get(this.swigCPtr, this);
    }

    public TimeVal getUpdate() {
        long RtcpStreamStat_update_get = pjsua2JNI.RtcpStreamStat_update_get(this.swigCPtr, this);
        if (RtcpStreamStat_update_get == 0) {
            return null;
        }
        return new TimeVal(RtcpStreamStat_update_get, false);
    }

    public long getUpdateCount() {
        return pjsua2JNI.RtcpStreamStat_updateCount_get(this.swigCPtr, this);
    }

    public void setBytes(long j) {
        pjsua2JNI.RtcpStreamStat_bytes_set(this.swigCPtr, this, j);
    }

    public void setDiscard(long j) {
        pjsua2JNI.RtcpStreamStat_discard_set(this.swigCPtr, this, j);
    }

    public void setDup(long j) {
        pjsua2JNI.RtcpStreamStat_dup_set(this.swigCPtr, this, j);
    }

    public void setJitterUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStreamStat_jitterUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public void setLoss(long j) {
        pjsua2JNI.RtcpStreamStat_loss_set(this.swigCPtr, this, j);
    }

    public void setLossPeriodUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStreamStat_lossPeriodUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public void setLossType(LossType lossType) {
        pjsua2JNI.RtcpStreamStat_lossType_set(this.swigCPtr, this, LossType.getCPtr(lossType), lossType);
    }

    public void setPkt(long j) {
        pjsua2JNI.RtcpStreamStat_pkt_set(this.swigCPtr, this, j);
    }

    public void setReorder(long j) {
        pjsua2JNI.RtcpStreamStat_reorder_set(this.swigCPtr, this, j);
    }

    public void setUpdate(TimeVal timeVal) {
        pjsua2JNI.RtcpStreamStat_update_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public void setUpdateCount(long j) {
        pjsua2JNI.RtcpStreamStat_updateCount_set(this.swigCPtr, this, j);
    }

    public RtcpStreamStat() {
        this(pjsua2JNI.new_RtcpStreamStat(), true);
    }
}
