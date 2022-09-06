package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountMwiConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountMwiConfig(long j, boolean z) {
        super(pjsua2JNI.AccountMwiConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountMwiConfig accountMwiConfig) {
        if (accountMwiConfig == null) {
            return 0L;
        }
        return accountMwiConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountMwiConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public boolean getEnabled() {
        return pjsua2JNI.AccountMwiConfig_enabled_get(this.swigCPtr, this);
    }

    public long getExpirationSec() {
        return pjsua2JNI.AccountMwiConfig_expirationSec_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountMwiConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setEnabled(boolean z) {
        pjsua2JNI.AccountMwiConfig_enabled_set(this.swigCPtr, this, z);
    }

    public void setExpirationSec(long j) {
        pjsua2JNI.AccountMwiConfig_expirationSec_set(this.swigCPtr, this, j);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountMwiConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountMwiConfig() {
        this(pjsua2JNI.new_AccountMwiConfig(), true);
    }
}
