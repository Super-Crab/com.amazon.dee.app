package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class UserEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public UserEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(UserEvent userEvent) {
        if (userEvent == null) {
            return 0L;
        }
        return userEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_UserEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getUser1() {
        long UserEvent_user1_get = pjsua2JNI.UserEvent_user1_get(this.swigCPtr, this);
        if (UserEvent_user1_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(UserEvent_user1_get, false);
    }

    public SWIGTYPE_p_void getUser2() {
        long UserEvent_user2_get = pjsua2JNI.UserEvent_user2_get(this.swigCPtr, this);
        if (UserEvent_user2_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(UserEvent_user2_get, false);
    }

    public SWIGTYPE_p_void getUser3() {
        long UserEvent_user3_get = pjsua2JNI.UserEvent_user3_get(this.swigCPtr, this);
        if (UserEvent_user3_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(UserEvent_user3_get, false);
    }

    public SWIGTYPE_p_void getUser4() {
        long UserEvent_user4_get = pjsua2JNI.UserEvent_user4_get(this.swigCPtr, this);
        if (UserEvent_user4_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(UserEvent_user4_get, false);
    }

    public void setUser1(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user1_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setUser2(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user2_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setUser3(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user3_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setUser4(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user4_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public UserEvent() {
        this(pjsua2JNI.new_UserEvent(), true);
    }
}
