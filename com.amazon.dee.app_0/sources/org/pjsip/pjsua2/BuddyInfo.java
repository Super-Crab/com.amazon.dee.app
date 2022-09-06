package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class BuddyInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public BuddyInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BuddyInfo buddyInfo) {
        if (buddyInfo == null) {
            return 0L;
        }
        return buddyInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContact() {
        return pjsua2JNI.BuddyInfo_contact_get(this.swigCPtr, this);
    }

    public boolean getPresMonitorEnabled() {
        return pjsua2JNI.BuddyInfo_presMonitorEnabled_get(this.swigCPtr, this);
    }

    public PresenceStatus getPresStatus() {
        long BuddyInfo_presStatus_get = pjsua2JNI.BuddyInfo_presStatus_get(this.swigCPtr, this);
        if (BuddyInfo_presStatus_get == 0) {
            return null;
        }
        return new PresenceStatus(BuddyInfo_presStatus_get, false);
    }

    public pjsip_evsub_state getSubState() {
        return pjsip_evsub_state.swigToEnum(pjsua2JNI.BuddyInfo_subState_get(this.swigCPtr, this));
    }

    public String getSubStateName() {
        return pjsua2JNI.BuddyInfo_subStateName_get(this.swigCPtr, this);
    }

    public pjsip_status_code getSubTermCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.BuddyInfo_subTermCode_get(this.swigCPtr, this));
    }

    public String getSubTermReason() {
        return pjsua2JNI.BuddyInfo_subTermReason_get(this.swigCPtr, this);
    }

    public String getUri() {
        return pjsua2JNI.BuddyInfo_uri_get(this.swigCPtr, this);
    }

    public void setContact(String str) {
        pjsua2JNI.BuddyInfo_contact_set(this.swigCPtr, this, str);
    }

    public void setPresMonitorEnabled(boolean z) {
        pjsua2JNI.BuddyInfo_presMonitorEnabled_set(this.swigCPtr, this, z);
    }

    public void setPresStatus(PresenceStatus presenceStatus) {
        pjsua2JNI.BuddyInfo_presStatus_set(this.swigCPtr, this, PresenceStatus.getCPtr(presenceStatus), presenceStatus);
    }

    public void setSubState(pjsip_evsub_state pjsip_evsub_stateVar) {
        pjsua2JNI.BuddyInfo_subState_set(this.swigCPtr, this, pjsip_evsub_stateVar.swigValue());
    }

    public void setSubStateName(String str) {
        pjsua2JNI.BuddyInfo_subStateName_set(this.swigCPtr, this, str);
    }

    public void setSubTermCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.BuddyInfo_subTermCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public void setSubTermReason(String str) {
        pjsua2JNI.BuddyInfo_subTermReason_set(this.swigCPtr, this, str);
    }

    public void setUri(String str) {
        pjsua2JNI.BuddyInfo_uri_set(this.swigCPtr, this, str);
    }

    public BuddyInfo() {
        this(pjsua2JNI.new_BuddyInfo(), true);
    }
}
