package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class BuddyVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public BuddyVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BuddyVector buddyVector) {
        if (buddyVector == null) {
            return 0L;
        }
        return buddyVector.swigCPtr;
    }

    public void add(Buddy buddy) {
        pjsua2JNI.BuddyVector_add(this.swigCPtr, this, Buddy.getCPtr(buddy), buddy);
    }

    public long capacity() {
        return pjsua2JNI.BuddyVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.BuddyVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public Buddy get(int i) {
        long BuddyVector_get = pjsua2JNI.BuddyVector_get(this.swigCPtr, this, i);
        if (BuddyVector_get == 0) {
            return null;
        }
        return new Buddy(BuddyVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.BuddyVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.BuddyVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, Buddy buddy) {
        pjsua2JNI.BuddyVector_set(this.swigCPtr, this, i, Buddy.getCPtr(buddy), buddy);
    }

    public long size() {
        return pjsua2JNI.BuddyVector_size(this.swigCPtr, this);
    }

    public BuddyVector() {
        this(pjsua2JNI.new_BuddyVector__SWIG_0(), true);
    }

    public BuddyVector(long j) {
        this(pjsua2JNI.new_BuddyVector__SWIG_1(j), true);
    }
}
