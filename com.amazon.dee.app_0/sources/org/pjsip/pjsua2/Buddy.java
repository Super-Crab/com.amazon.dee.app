package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class Buddy {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public Buddy(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(Buddy buddy) {
        if (buddy == null) {
            return 0L;
        }
        return buddy.swigCPtr;
    }

    public void create(Account account, BuddyConfig buddyConfig) throws Exception {
        pjsua2JNI.Buddy_create(this.swigCPtr, this, Account.getCPtr(account), account, BuddyConfig.getCPtr(buddyConfig), buddyConfig);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Buddy(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public BuddyInfo getInfo() throws Exception {
        return new BuddyInfo(pjsua2JNI.Buddy_getInfo(this.swigCPtr, this), true);
    }

    public boolean isValid() {
        return pjsua2JNI.Buddy_isValid(this.swigCPtr, this);
    }

    public void onBuddyState() {
        if (Buddy.class == Buddy.class) {
            pjsua2JNI.Buddy_onBuddyState(this.swigCPtr, this);
        } else {
            pjsua2JNI.Buddy_onBuddyStateSwigExplicitBuddy(this.swigCPtr, this);
        }
    }

    public void sendInstantMessage(SendInstantMessageParam sendInstantMessageParam) throws Exception {
        pjsua2JNI.Buddy_sendInstantMessage(this.swigCPtr, this, SendInstantMessageParam.getCPtr(sendInstantMessageParam), sendInstantMessageParam);
    }

    public void sendTypingIndication(SendTypingIndicationParam sendTypingIndicationParam) throws Exception {
        pjsua2JNI.Buddy_sendTypingIndication(this.swigCPtr, this, SendTypingIndicationParam.getCPtr(sendTypingIndicationParam), sendTypingIndicationParam);
    }

    public void subscribePresence(boolean z) throws Exception {
        pjsua2JNI.Buddy_subscribePresence(this.swigCPtr, this, z);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.Buddy_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Buddy_change_ownership(this, this.swigCPtr, true);
    }

    public void updatePresence() throws Exception {
        pjsua2JNI.Buddy_updatePresence(this.swigCPtr, this);
    }

    public Buddy() {
        this(pjsua2JNI.new_Buddy(), true);
        pjsua2JNI.Buddy_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
