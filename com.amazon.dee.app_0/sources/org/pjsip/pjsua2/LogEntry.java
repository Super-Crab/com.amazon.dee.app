package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class LogEntry {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public LogEntry(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(LogEntry logEntry) {
        if (logEntry == null) {
            return 0L;
        }
        return logEntry.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogEntry(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getLevel() {
        return pjsua2JNI.LogEntry_level_get(this.swigCPtr, this);
    }

    public String getMsg() {
        return pjsua2JNI.LogEntry_msg_get(this.swigCPtr, this);
    }

    public int getThreadId() {
        return pjsua2JNI.LogEntry_threadId_get(this.swigCPtr, this);
    }

    public String getThreadName() {
        return pjsua2JNI.LogEntry_threadName_get(this.swigCPtr, this);
    }

    public void setLevel(int i) {
        pjsua2JNI.LogEntry_level_set(this.swigCPtr, this, i);
    }

    public void setMsg(String str) {
        pjsua2JNI.LogEntry_msg_set(this.swigCPtr, this, str);
    }

    public void setThreadId(int i) {
        pjsua2JNI.LogEntry_threadId_set(this.swigCPtr, this, i);
    }

    public void setThreadName(String str) {
        pjsua2JNI.LogEntry_threadName_set(this.swigCPtr, this, str);
    }

    public LogEntry() {
        this(pjsua2JNI.new_LogEntry(), true);
    }
}
