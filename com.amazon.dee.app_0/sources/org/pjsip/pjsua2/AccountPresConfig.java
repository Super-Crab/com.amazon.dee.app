package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountPresConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountPresConfig(long j, boolean z) {
        super(pjsua2JNI.AccountPresConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountPresConfig accountPresConfig) {
        if (accountPresConfig == null) {
            return 0L;
        }
        return accountPresConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountPresConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public SipHeaderVector getHeaders() {
        long AccountPresConfig_headers_get = pjsua2JNI.AccountPresConfig_headers_get(this.swigCPtr, this);
        if (AccountPresConfig_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(AccountPresConfig_headers_get, false);
    }

    public String getPidfTupleId() {
        return pjsua2JNI.AccountPresConfig_pidfTupleId_get(this.swigCPtr, this);
    }

    public boolean getPublishEnabled() {
        return pjsua2JNI.AccountPresConfig_publishEnabled_get(this.swigCPtr, this);
    }

    public boolean getPublishQueue() {
        return pjsua2JNI.AccountPresConfig_publishQueue_get(this.swigCPtr, this);
    }

    public long getPublishShutdownWaitMsec() {
        return pjsua2JNI.AccountPresConfig_publishShutdownWaitMsec_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountPresConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.AccountPresConfig_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void setPidfTupleId(String str) {
        pjsua2JNI.AccountPresConfig_pidfTupleId_set(this.swigCPtr, this, str);
    }

    public void setPublishEnabled(boolean z) {
        pjsua2JNI.AccountPresConfig_publishEnabled_set(this.swigCPtr, this, z);
    }

    public void setPublishQueue(boolean z) {
        pjsua2JNI.AccountPresConfig_publishQueue_set(this.swigCPtr, this, z);
    }

    public void setPublishShutdownWaitMsec(long j) {
        pjsua2JNI.AccountPresConfig_publishShutdownWaitMsec_set(this.swigCPtr, this, j);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountPresConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountPresConfig() {
        this(pjsua2JNI.new_AccountPresConfig(), true);
    }
}
