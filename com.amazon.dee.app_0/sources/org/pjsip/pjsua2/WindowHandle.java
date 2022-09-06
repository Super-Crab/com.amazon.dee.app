package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class WindowHandle {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public WindowHandle(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(WindowHandle windowHandle) {
        if (windowHandle == null) {
            return 0L;
        }
        return windowHandle.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_WindowHandle(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public void setWindow(Object obj) {
        pjsua2JNI.WindowHandle_setWindow(this.swigCPtr, this, obj);
    }

    public WindowHandle() {
        this(pjsua2JNI.new_WindowHandle(), true);
    }
}
