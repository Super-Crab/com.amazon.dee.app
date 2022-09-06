package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class ToneGenerator extends AudioMedia {
    private transient long swigCPtr;

    protected ToneGenerator(long j, boolean z) {
        super(pjsua2JNI.ToneGenerator_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneGenerator toneGenerator) {
        if (toneGenerator == null) {
            return 0L;
        }
        return toneGenerator.swigCPtr;
    }

    public void createToneGenerator(long j, long j2) throws Exception {
        pjsua2JNI.ToneGenerator_createToneGenerator__SWIG_0(this.swigCPtr, this, j, j2);
    }

    @Override // org.pjsip.pjsua2.AudioMedia, org.pjsip.pjsua2.Media
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneGenerator(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.AudioMedia, org.pjsip.pjsua2.Media
    protected void finalize() {
        delete();
    }

    public ToneDigitMapVector getDigitMap() throws Exception {
        return new ToneDigitMapVector(pjsua2JNI.ToneGenerator_getDigitMap(this.swigCPtr, this), true);
    }

    public boolean isBusy() {
        return pjsua2JNI.ToneGenerator_isBusy(this.swigCPtr, this);
    }

    public void play(ToneDescVector toneDescVector, boolean z) throws Exception {
        pjsua2JNI.ToneGenerator_play__SWIG_0(this.swigCPtr, this, ToneDescVector.getCPtr(toneDescVector), toneDescVector, z);
    }

    public void playDigits(ToneDigitVector toneDigitVector, boolean z) throws Exception {
        pjsua2JNI.ToneGenerator_playDigits__SWIG_0(this.swigCPtr, this, ToneDigitVector.getCPtr(toneDigitVector), toneDigitVector, z);
    }

    public void rewind() throws Exception {
        pjsua2JNI.ToneGenerator_rewind(this.swigCPtr, this);
    }

    public void setDigitMap(ToneDigitMapVector toneDigitMapVector) throws Exception {
        pjsua2JNI.ToneGenerator_setDigitMap(this.swigCPtr, this, ToneDigitMapVector.getCPtr(toneDigitMapVector), toneDigitMapVector);
    }

    public void stop() throws Exception {
        pjsua2JNI.ToneGenerator_stop(this.swigCPtr, this);
    }

    public void createToneGenerator(long j) throws Exception {
        pjsua2JNI.ToneGenerator_createToneGenerator__SWIG_1(this.swigCPtr, this, j);
    }

    public void play(ToneDescVector toneDescVector) throws Exception {
        pjsua2JNI.ToneGenerator_play__SWIG_1(this.swigCPtr, this, ToneDescVector.getCPtr(toneDescVector), toneDescVector);
    }

    public void playDigits(ToneDigitVector toneDigitVector) throws Exception {
        pjsua2JNI.ToneGenerator_playDigits__SWIG_1(this.swigCPtr, this, ToneDigitVector.getCPtr(toneDigitVector), toneDigitVector);
    }

    public ToneGenerator() {
        this(pjsua2JNI.new_ToneGenerator(), true);
    }

    public void createToneGenerator() throws Exception {
        pjsua2JNI.ToneGenerator_createToneGenerator__SWIG_2(this.swigCPtr, this);
    }
}
