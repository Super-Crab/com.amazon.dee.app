package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class BuddyConfig extends PersistentObject {
    private transient long swigCPtr;

    protected BuddyConfig(long j, boolean z) {
        super(pjsua2JNI.BuddyConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(BuddyConfig buddyConfig) {
        if (buddyConfig == null) {
            return 0L;
        }
        return buddyConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public boolean getSubscribe() {
        return pjsua2JNI.BuddyConfig_subscribe_get(this.swigCPtr, this);
    }

    public String getUri() {
        return pjsua2JNI.BuddyConfig_uri_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.BuddyConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setSubscribe(boolean z) {
        pjsua2JNI.BuddyConfig_subscribe_set(this.swigCPtr, this, z);
    }

    public void setUri(String str) {
        pjsua2JNI.BuddyConfig_uri_set(this.swigCPtr, this, str);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.BuddyConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public BuddyConfig() {
        this(pjsua2JNI.new_BuddyConfig(), true);
    }
}
