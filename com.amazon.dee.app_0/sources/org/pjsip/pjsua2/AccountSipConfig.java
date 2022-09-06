package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountSipConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountSipConfig(long j, boolean z) {
        super(pjsua2JNI.AccountSipConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountSipConfig accountSipConfig) {
        if (accountSipConfig == null) {
            return 0L;
        }
        return accountSipConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountSipConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public AuthCredInfoVector getAuthCreds() {
        long AccountSipConfig_authCreds_get = pjsua2JNI.AccountSipConfig_authCreds_get(this.swigCPtr, this);
        if (AccountSipConfig_authCreds_get == 0) {
            return null;
        }
        return new AuthCredInfoVector(AccountSipConfig_authCreds_get, false);
    }

    public String getAuthInitialAlgorithm() {
        return pjsua2JNI.AccountSipConfig_authInitialAlgorithm_get(this.swigCPtr, this);
    }

    public boolean getAuthInitialEmpty() {
        return pjsua2JNI.AccountSipConfig_authInitialEmpty_get(this.swigCPtr, this);
    }

    public String getContactForced() {
        return pjsua2JNI.AccountSipConfig_contactForced_get(this.swigCPtr, this);
    }

    public String getContactParams() {
        return pjsua2JNI.AccountSipConfig_contactParams_get(this.swigCPtr, this);
    }

    public String getContactUriParams() {
        return pjsua2JNI.AccountSipConfig_contactUriParams_get(this.swigCPtr, this);
    }

    public StringVector getProxies() {
        long AccountSipConfig_proxies_get = pjsua2JNI.AccountSipConfig_proxies_get(this.swigCPtr, this);
        if (AccountSipConfig_proxies_get == 0) {
            return null;
        }
        return new StringVector(AccountSipConfig_proxies_get, false);
    }

    public int getTransportId() {
        return pjsua2JNI.AccountSipConfig_transportId_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountSipConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setAuthCreds(AuthCredInfoVector authCredInfoVector) {
        pjsua2JNI.AccountSipConfig_authCreds_set(this.swigCPtr, this, AuthCredInfoVector.getCPtr(authCredInfoVector), authCredInfoVector);
    }

    public void setAuthInitialAlgorithm(String str) {
        pjsua2JNI.AccountSipConfig_authInitialAlgorithm_set(this.swigCPtr, this, str);
    }

    public void setAuthInitialEmpty(boolean z) {
        pjsua2JNI.AccountSipConfig_authInitialEmpty_set(this.swigCPtr, this, z);
    }

    public void setContactForced(String str) {
        pjsua2JNI.AccountSipConfig_contactForced_set(this.swigCPtr, this, str);
    }

    public void setContactParams(String str) {
        pjsua2JNI.AccountSipConfig_contactParams_set(this.swigCPtr, this, str);
    }

    public void setContactUriParams(String str) {
        pjsua2JNI.AccountSipConfig_contactUriParams_set(this.swigCPtr, this, str);
    }

    public void setProxies(StringVector stringVector) {
        pjsua2JNI.AccountSipConfig_proxies_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setTransportId(int i) {
        pjsua2JNI.AccountSipConfig_transportId_set(this.swigCPtr, this, i);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountSipConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountSipConfig() {
        this(pjsua2JNI.new_AccountSipConfig(), true);
    }
}
