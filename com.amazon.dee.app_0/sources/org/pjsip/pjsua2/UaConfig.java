package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class UaConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public UaConfig(long j, boolean z) {
        super(pjsua2JNI.UaConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(UaConfig uaConfig) {
        if (uaConfig == null) {
            return 0L;
        }
        return uaConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_UaConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public boolean getMainThreadOnly() {
        return pjsua2JNI.UaConfig_mainThreadOnly_get(this.swigCPtr, this);
    }

    public long getMaxCalls() {
        return pjsua2JNI.UaConfig_maxCalls_get(this.swigCPtr, this);
    }

    public boolean getMwiUnsolicitedEnabled() {
        return pjsua2JNI.UaConfig_mwiUnsolicitedEnabled_get(this.swigCPtr, this);
    }

    public StringVector getNameserver() {
        long UaConfig_nameserver_get = pjsua2JNI.UaConfig_nameserver_get(this.swigCPtr, this);
        if (UaConfig_nameserver_get == 0) {
            return null;
        }
        return new StringVector(UaConfig_nameserver_get, false);
    }

    public int getNatTypeInSdp() {
        return pjsua2JNI.UaConfig_natTypeInSdp_get(this.swigCPtr, this);
    }

    public boolean getStunIgnoreFailure() {
        return pjsua2JNI.UaConfig_stunIgnoreFailure_get(this.swigCPtr, this);
    }

    public StringVector getStunServer() {
        long UaConfig_stunServer_get = pjsua2JNI.UaConfig_stunServer_get(this.swigCPtr, this);
        if (UaConfig_stunServer_get == 0) {
            return null;
        }
        return new StringVector(UaConfig_stunServer_get, false);
    }

    public long getThreadCnt() {
        return pjsua2JNI.UaConfig_threadCnt_get(this.swigCPtr, this);
    }

    public String getUserAgent() {
        return pjsua2JNI.UaConfig_userAgent_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.UaConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setMainThreadOnly(boolean z) {
        pjsua2JNI.UaConfig_mainThreadOnly_set(this.swigCPtr, this, z);
    }

    public void setMaxCalls(long j) {
        pjsua2JNI.UaConfig_maxCalls_set(this.swigCPtr, this, j);
    }

    public void setMwiUnsolicitedEnabled(boolean z) {
        pjsua2JNI.UaConfig_mwiUnsolicitedEnabled_set(this.swigCPtr, this, z);
    }

    public void setNameserver(StringVector stringVector) {
        pjsua2JNI.UaConfig_nameserver_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setNatTypeInSdp(int i) {
        pjsua2JNI.UaConfig_natTypeInSdp_set(this.swigCPtr, this, i);
    }

    public void setStunIgnoreFailure(boolean z) {
        pjsua2JNI.UaConfig_stunIgnoreFailure_set(this.swigCPtr, this, z);
    }

    public void setStunServer(StringVector stringVector) {
        pjsua2JNI.UaConfig_stunServer_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setThreadCnt(long j) {
        pjsua2JNI.UaConfig_threadCnt_set(this.swigCPtr, this, j);
    }

    public void setUserAgent(String str) {
        pjsua2JNI.UaConfig_userAgent_set(this.swigCPtr, this, str);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.UaConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public UaConfig() {
        this(pjsua2JNI.new_UaConfig(), true);
    }
}
