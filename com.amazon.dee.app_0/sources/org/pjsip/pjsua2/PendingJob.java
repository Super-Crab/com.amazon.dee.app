package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class PendingJob {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected PendingJob(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(PendingJob pendingJob) {
        if (pendingJob == null) {
            return 0L;
        }
        return pendingJob.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PendingJob(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    public void execute(boolean z) {
        pjsua2JNI.PendingJob_execute(this.swigCPtr, this, z);
    }

    protected void finalize() {
        delete();
    }
}
