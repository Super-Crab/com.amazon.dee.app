package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaCoordinate {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaCoordinate(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaCoordinate mediaCoordinate) {
        if (mediaCoordinate == null) {
            return 0L;
        }
        return mediaCoordinate.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaCoordinate(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getX() {
        return pjsua2JNI.MediaCoordinate_x_get(this.swigCPtr, this);
    }

    public int getY() {
        return pjsua2JNI.MediaCoordinate_y_get(this.swigCPtr, this);
    }

    public void setX(int i) {
        pjsua2JNI.MediaCoordinate_x_set(this.swigCPtr, this, i);
    }

    public void setY(int i) {
        pjsua2JNI.MediaCoordinate_y_set(this.swigCPtr, this, i);
    }

    public MediaCoordinate() {
        this(pjsua2JNI.new_MediaCoordinate(), true);
    }
}
