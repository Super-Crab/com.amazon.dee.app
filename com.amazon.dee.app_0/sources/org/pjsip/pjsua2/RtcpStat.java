package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class RtcpStat {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RtcpStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RtcpStat rtcpStat) {
        if (rtcpStat == null) {
            return 0L;
        }
        return rtcpStat.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RtcpStat(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public RtcpSdes getPeerSdes() {
        long RtcpStat_peerSdes_get = pjsua2JNI.RtcpStat_peerSdes_get(this.swigCPtr, this);
        if (RtcpStat_peerSdes_get == 0) {
            return null;
        }
        return new RtcpSdes(RtcpStat_peerSdes_get, false);
    }

    public int getRtpTxLastSeq() {
        return pjsua2JNI.RtcpStat_rtpTxLastSeq_get(this.swigCPtr, this);
    }

    public long getRtpTxLastTs() {
        return pjsua2JNI.RtcpStat_rtpTxLastTs_get(this.swigCPtr, this);
    }

    public MathStat getRttUsec() {
        long RtcpStat_rttUsec_get = pjsua2JNI.RtcpStat_rttUsec_get(this.swigCPtr, this);
        if (RtcpStat_rttUsec_get == 0) {
            return null;
        }
        return new MathStat(RtcpStat_rttUsec_get, false);
    }

    public MathStat getRxIpdvUsec() {
        long RtcpStat_rxIpdvUsec_get = pjsua2JNI.RtcpStat_rxIpdvUsec_get(this.swigCPtr, this);
        if (RtcpStat_rxIpdvUsec_get == 0) {
            return null;
        }
        return new MathStat(RtcpStat_rxIpdvUsec_get, false);
    }

    public MathStat getRxRawJitterUsec() {
        long RtcpStat_rxRawJitterUsec_get = pjsua2JNI.RtcpStat_rxRawJitterUsec_get(this.swigCPtr, this);
        if (RtcpStat_rxRawJitterUsec_get == 0) {
            return null;
        }
        return new MathStat(RtcpStat_rxRawJitterUsec_get, false);
    }

    public RtcpStreamStat getRxStat() {
        long RtcpStat_rxStat_get = pjsua2JNI.RtcpStat_rxStat_get(this.swigCPtr, this);
        if (RtcpStat_rxStat_get == 0) {
            return null;
        }
        return new RtcpStreamStat(RtcpStat_rxStat_get, false);
    }

    public TimeVal getStart() {
        long RtcpStat_start_get = pjsua2JNI.RtcpStat_start_get(this.swigCPtr, this);
        if (RtcpStat_start_get == 0) {
            return null;
        }
        return new TimeVal(RtcpStat_start_get, false);
    }

    public RtcpStreamStat getTxStat() {
        long RtcpStat_txStat_get = pjsua2JNI.RtcpStat_txStat_get(this.swigCPtr, this);
        if (RtcpStat_txStat_get == 0) {
            return null;
        }
        return new RtcpStreamStat(RtcpStat_txStat_get, false);
    }

    public void setPeerSdes(RtcpSdes rtcpSdes) {
        pjsua2JNI.RtcpStat_peerSdes_set(this.swigCPtr, this, RtcpSdes.getCPtr(rtcpSdes), rtcpSdes);
    }

    public void setRtpTxLastSeq(int i) {
        pjsua2JNI.RtcpStat_rtpTxLastSeq_set(this.swigCPtr, this, i);
    }

    public void setRtpTxLastTs(long j) {
        pjsua2JNI.RtcpStat_rtpTxLastTs_set(this.swigCPtr, this, j);
    }

    public void setRttUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStat_rttUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public void setRxIpdvUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStat_rxIpdvUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public void setRxRawJitterUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStat_rxRawJitterUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public void setRxStat(RtcpStreamStat rtcpStreamStat) {
        pjsua2JNI.RtcpStat_rxStat_set(this.swigCPtr, this, RtcpStreamStat.getCPtr(rtcpStreamStat), rtcpStreamStat);
    }

    public void setStart(TimeVal timeVal) {
        pjsua2JNI.RtcpStat_start_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public void setTxStat(RtcpStreamStat rtcpStreamStat) {
        pjsua2JNI.RtcpStat_txStat_set(this.swigCPtr, this, RtcpStreamStat.getCPtr(rtcpStreamStat), rtcpStreamStat);
    }

    public RtcpStat() {
        this(pjsua2JNI.new_RtcpStat(), true);
    }
}
