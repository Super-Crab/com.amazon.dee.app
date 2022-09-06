package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class FindBuddyMatch {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected FindBuddyMatch(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(FindBuddyMatch findBuddyMatch) {
        if (findBuddyMatch == null) {
            return 0L;
        }
        return findBuddyMatch.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_FindBuddyMatch(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean match(String str, Buddy buddy) {
        return FindBuddyMatch.class == FindBuddyMatch.class ? pjsua2JNI.FindBuddyMatch_match(this.swigCPtr, this, str, Buddy.getCPtr(buddy), buddy) : pjsua2JNI.FindBuddyMatch_matchSwigExplicitFindBuddyMatch(this.swigCPtr, this, str, Buddy.getCPtr(buddy), buddy);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.FindBuddyMatch_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.FindBuddyMatch_change_ownership(this, this.swigCPtr, true);
    }

    public FindBuddyMatch() {
        this(pjsua2JNI.new_FindBuddyMatch(), true);
        pjsua2JNI.FindBuddyMatch_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
