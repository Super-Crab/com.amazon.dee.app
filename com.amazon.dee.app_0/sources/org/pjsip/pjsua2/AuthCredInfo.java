package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AuthCredInfo extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthCredInfo(long j, boolean z) {
        super(pjsua2JNI.AuthCredInfo_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AuthCredInfo authCredInfo) {
        if (authCredInfo == null) {
            return 0L;
        }
        return authCredInfo.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AuthCredInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public String getAkaAmf() {
        return pjsua2JNI.AuthCredInfo_akaAmf_get(this.swigCPtr, this);
    }

    public String getAkaK() {
        return pjsua2JNI.AuthCredInfo_akaK_get(this.swigCPtr, this);
    }

    public String getAkaOp() {
        return pjsua2JNI.AuthCredInfo_akaOp_get(this.swigCPtr, this);
    }

    public String getData() {
        return pjsua2JNI.AuthCredInfo_data_get(this.swigCPtr, this);
    }

    public int getDataType() {
        return pjsua2JNI.AuthCredInfo_dataType_get(this.swigCPtr, this);
    }

    public String getRealm() {
        return pjsua2JNI.AuthCredInfo_realm_get(this.swigCPtr, this);
    }

    public String getScheme() {
        return pjsua2JNI.AuthCredInfo_scheme_get(this.swigCPtr, this);
    }

    public String getUsername() {
        return pjsua2JNI.AuthCredInfo_username_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AuthCredInfo_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setAkaAmf(String str) {
        pjsua2JNI.AuthCredInfo_akaAmf_set(this.swigCPtr, this, str);
    }

    public void setAkaK(String str) {
        pjsua2JNI.AuthCredInfo_akaK_set(this.swigCPtr, this, str);
    }

    public void setAkaOp(String str) {
        pjsua2JNI.AuthCredInfo_akaOp_set(this.swigCPtr, this, str);
    }

    public void setData(String str) {
        pjsua2JNI.AuthCredInfo_data_set(this.swigCPtr, this, str);
    }

    public void setDataType(int i) {
        pjsua2JNI.AuthCredInfo_dataType_set(this.swigCPtr, this, i);
    }

    public void setRealm(String str) {
        pjsua2JNI.AuthCredInfo_realm_set(this.swigCPtr, this, str);
    }

    public void setScheme(String str) {
        pjsua2JNI.AuthCredInfo_scheme_set(this.swigCPtr, this, str);
    }

    public void setUsername(String str) {
        pjsua2JNI.AuthCredInfo_username_set(this.swigCPtr, this, str);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AuthCredInfo_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AuthCredInfo() {
        this(pjsua2JNI.new_AuthCredInfo__SWIG_0(), true);
    }

    public AuthCredInfo(String str, String str2, String str3, int i, String str4) {
        this(pjsua2JNI.new_AuthCredInfo__SWIG_1(str, str2, str3, i, str4), true);
    }
}
