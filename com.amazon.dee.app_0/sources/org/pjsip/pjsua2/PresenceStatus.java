package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class PresenceStatus {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public PresenceStatus(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(PresenceStatus presenceStatus) {
        if (presenceStatus == null) {
            return 0L;
        }
        return presenceStatus.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PresenceStatus(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjrpid_activity getActivity() {
        return pjrpid_activity.swigToEnum(pjsua2JNI.PresenceStatus_activity_get(this.swigCPtr, this));
    }

    public String getNote() {
        return pjsua2JNI.PresenceStatus_note_get(this.swigCPtr, this);
    }

    public String getRpidId() {
        return pjsua2JNI.PresenceStatus_rpidId_get(this.swigCPtr, this);
    }

    public pjsua_buddy_status getStatus() {
        return pjsua_buddy_status.swigToEnum(pjsua2JNI.PresenceStatus_status_get(this.swigCPtr, this));
    }

    public String getStatusText() {
        return pjsua2JNI.PresenceStatus_statusText_get(this.swigCPtr, this);
    }

    public void setActivity(pjrpid_activity pjrpid_activityVar) {
        pjsua2JNI.PresenceStatus_activity_set(this.swigCPtr, this, pjrpid_activityVar.swigValue());
    }

    public void setNote(String str) {
        pjsua2JNI.PresenceStatus_note_set(this.swigCPtr, this, str);
    }

    public void setRpidId(String str) {
        pjsua2JNI.PresenceStatus_rpidId_set(this.swigCPtr, this, str);
    }

    public void setStatus(pjsua_buddy_status pjsua_buddy_statusVar) {
        pjsua2JNI.PresenceStatus_status_set(this.swigCPtr, this, pjsua_buddy_statusVar.swigValue());
    }

    public void setStatusText(String str) {
        pjsua2JNI.PresenceStatus_statusText_set(this.swigCPtr, this, str);
    }

    public PresenceStatus() {
        this(pjsua2JNI.new_PresenceStatus(), true);
    }
}
