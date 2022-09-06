package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountRegConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountRegConfig(long j, boolean z) {
        super(pjsua2JNI.AccountRegConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountRegConfig accountRegConfig) {
        if (accountRegConfig == null) {
            return 0L;
        }
        return accountRegConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountRegConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public long getDelayBeforeRefreshSec() {
        return pjsua2JNI.AccountRegConfig_delayBeforeRefreshSec_get(this.swigCPtr, this);
    }

    public boolean getDropCallsOnFail() {
        return pjsua2JNI.AccountRegConfig_dropCallsOnFail_get(this.swigCPtr, this);
    }

    public long getFirstRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_firstRetryIntervalSec_get(this.swigCPtr, this);
    }

    public SipHeaderVector getHeaders() {
        long AccountRegConfig_headers_get = pjsua2JNI.AccountRegConfig_headers_get(this.swigCPtr, this);
        if (AccountRegConfig_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(AccountRegConfig_headers_get, false);
    }

    public long getProxyUse() {
        return pjsua2JNI.AccountRegConfig_proxyUse_get(this.swigCPtr, this);
    }

    public long getRandomRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_randomRetryIntervalSec_get(this.swigCPtr, this);
    }

    public boolean getRegisterOnAdd() {
        return pjsua2JNI.AccountRegConfig_registerOnAdd_get(this.swigCPtr, this);
    }

    public String getRegistrarUri() {
        return pjsua2JNI.AccountRegConfig_registrarUri_get(this.swigCPtr, this);
    }

    public long getRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_retryIntervalSec_get(this.swigCPtr, this);
    }

    public long getTimeoutSec() {
        return pjsua2JNI.AccountRegConfig_timeoutSec_get(this.swigCPtr, this);
    }

    public long getUnregWaitMsec() {
        return pjsua2JNI.AccountRegConfig_unregWaitMsec_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountRegConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setDelayBeforeRefreshSec(long j) {
        pjsua2JNI.AccountRegConfig_delayBeforeRefreshSec_set(this.swigCPtr, this, j);
    }

    public void setDropCallsOnFail(boolean z) {
        pjsua2JNI.AccountRegConfig_dropCallsOnFail_set(this.swigCPtr, this, z);
    }

    public void setFirstRetryIntervalSec(long j) {
        pjsua2JNI.AccountRegConfig_firstRetryIntervalSec_set(this.swigCPtr, this, j);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.AccountRegConfig_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void setProxyUse(long j) {
        pjsua2JNI.AccountRegConfig_proxyUse_set(this.swigCPtr, this, j);
    }

    public void setRandomRetryIntervalSec(long j) {
        pjsua2JNI.AccountRegConfig_randomRetryIntervalSec_set(this.swigCPtr, this, j);
    }

    public void setRegisterOnAdd(boolean z) {
        pjsua2JNI.AccountRegConfig_registerOnAdd_set(this.swigCPtr, this, z);
    }

    public void setRegistrarUri(String str) {
        pjsua2JNI.AccountRegConfig_registrarUri_set(this.swigCPtr, this, str);
    }

    public void setRetryIntervalSec(long j) {
        pjsua2JNI.AccountRegConfig_retryIntervalSec_set(this.swigCPtr, this, j);
    }

    public void setTimeoutSec(long j) {
        pjsua2JNI.AccountRegConfig_timeoutSec_set(this.swigCPtr, this, j);
    }

    public void setUnregWaitMsec(long j) {
        pjsua2JNI.AccountRegConfig_unregWaitMsec_set(this.swigCPtr, this, j);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountRegConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountRegConfig() {
        this(pjsua2JNI.new_AccountRegConfig(), true);
    }
}
