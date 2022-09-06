package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class StreamStat {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(StreamStat streamStat) {
        if (streamStat == null) {
            return 0L;
        }
        return streamStat.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_StreamStat(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public JbufState getJbuf() {
        long StreamStat_jbuf_get = pjsua2JNI.StreamStat_jbuf_get(this.swigCPtr, this);
        if (StreamStat_jbuf_get == 0) {
            return null;
        }
        return new JbufState(StreamStat_jbuf_get, false);
    }

    public RtcpStat getRtcp() {
        long StreamStat_rtcp_get = pjsua2JNI.StreamStat_rtcp_get(this.swigCPtr, this);
        if (StreamStat_rtcp_get == 0) {
            return null;
        }
        return new RtcpStat(StreamStat_rtcp_get, false);
    }

    public void setJbuf(JbufState jbufState) {
        pjsua2JNI.StreamStat_jbuf_set(this.swigCPtr, this, JbufState.getCPtr(jbufState), jbufState);
    }

    public void setRtcp(RtcpStat rtcpStat) {
        pjsua2JNI.StreamStat_rtcp_set(this.swigCPtr, this, RtcpStat.getCPtr(rtcpStat), rtcpStat);
    }

    public StreamStat() {
        this(pjsua2JNI.new_StreamStat(), true);
    }
}
