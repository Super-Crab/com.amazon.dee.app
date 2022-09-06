package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class RtcpSdes {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RtcpSdes(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RtcpSdes rtcpSdes) {
        if (rtcpSdes == null) {
            return 0L;
        }
        return rtcpSdes.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RtcpSdes(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getCname() {
        return pjsua2JNI.RtcpSdes_cname_get(this.swigCPtr, this);
    }

    public String getEmail() {
        return pjsua2JNI.RtcpSdes_email_get(this.swigCPtr, this);
    }

    public String getLoc() {
        return pjsua2JNI.RtcpSdes_loc_get(this.swigCPtr, this);
    }

    public String getName() {
        return pjsua2JNI.RtcpSdes_name_get(this.swigCPtr, this);
    }

    public String getNote() {
        return pjsua2JNI.RtcpSdes_note_get(this.swigCPtr, this);
    }

    public String getPhone() {
        return pjsua2JNI.RtcpSdes_phone_get(this.swigCPtr, this);
    }

    public String getTool() {
        return pjsua2JNI.RtcpSdes_tool_get(this.swigCPtr, this);
    }

    public void setCname(String str) {
        pjsua2JNI.RtcpSdes_cname_set(this.swigCPtr, this, str);
    }

    public void setEmail(String str) {
        pjsua2JNI.RtcpSdes_email_set(this.swigCPtr, this, str);
    }

    public void setLoc(String str) {
        pjsua2JNI.RtcpSdes_loc_set(this.swigCPtr, this, str);
    }

    public void setName(String str) {
        pjsua2JNI.RtcpSdes_name_set(this.swigCPtr, this, str);
    }

    public void setNote(String str) {
        pjsua2JNI.RtcpSdes_note_set(this.swigCPtr, this, str);
    }

    public void setPhone(String str) {
        pjsua2JNI.RtcpSdes_phone_set(this.swigCPtr, this, str);
    }

    public void setTool(String str) {
        pjsua2JNI.RtcpSdes_tool_set(this.swigCPtr, this, str);
    }

    public RtcpSdes() {
        this(pjsua2JNI.new_RtcpSdes(), true);
    }
}
