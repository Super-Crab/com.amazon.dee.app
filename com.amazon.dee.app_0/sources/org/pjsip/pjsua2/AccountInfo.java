package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountInfo accountInfo) {
        if (accountInfo == null) {
            return 0L;
        }
        return accountInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getId() {
        return pjsua2JNI.AccountInfo_id_get(this.swigCPtr, this);
    }

    public boolean getIsDefault() {
        return pjsua2JNI.AccountInfo_isDefault_get(this.swigCPtr, this);
    }

    public boolean getOnlineStatus() {
        return pjsua2JNI.AccountInfo_onlineStatus_get(this.swigCPtr, this);
    }

    public String getOnlineStatusText() {
        return pjsua2JNI.AccountInfo_onlineStatusText_get(this.swigCPtr, this);
    }

    public int getRegExpiresSec() {
        return pjsua2JNI.AccountInfo_regExpiresSec_get(this.swigCPtr, this);
    }

    public boolean getRegIsActive() {
        return pjsua2JNI.AccountInfo_regIsActive_get(this.swigCPtr, this);
    }

    public boolean getRegIsConfigured() {
        return pjsua2JNI.AccountInfo_regIsConfigured_get(this.swigCPtr, this);
    }

    public int getRegLastErr() {
        return pjsua2JNI.AccountInfo_regLastErr_get(this.swigCPtr, this);
    }

    public pjsip_status_code getRegStatus() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.AccountInfo_regStatus_get(this.swigCPtr, this));
    }

    public String getRegStatusText() {
        return pjsua2JNI.AccountInfo_regStatusText_get(this.swigCPtr, this);
    }

    public String getUri() {
        return pjsua2JNI.AccountInfo_uri_get(this.swigCPtr, this);
    }

    public void setId(int i) {
        pjsua2JNI.AccountInfo_id_set(this.swigCPtr, this, i);
    }

    public void setIsDefault(boolean z) {
        pjsua2JNI.AccountInfo_isDefault_set(this.swigCPtr, this, z);
    }

    public void setOnlineStatus(boolean z) {
        pjsua2JNI.AccountInfo_onlineStatus_set(this.swigCPtr, this, z);
    }

    public void setOnlineStatusText(String str) {
        pjsua2JNI.AccountInfo_onlineStatusText_set(this.swigCPtr, this, str);
    }

    public void setRegExpiresSec(int i) {
        pjsua2JNI.AccountInfo_regExpiresSec_set(this.swigCPtr, this, i);
    }

    public void setRegIsActive(boolean z) {
        pjsua2JNI.AccountInfo_regIsActive_set(this.swigCPtr, this, z);
    }

    public void setRegIsConfigured(boolean z) {
        pjsua2JNI.AccountInfo_regIsConfigured_set(this.swigCPtr, this, z);
    }

    public void setRegLastErr(int i) {
        pjsua2JNI.AccountInfo_regLastErr_set(this.swigCPtr, this, i);
    }

    public void setRegStatus(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.AccountInfo_regStatus_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public void setRegStatusText(String str) {
        pjsua2JNI.AccountInfo_regStatusText_set(this.swigCPtr, this, str);
    }

    public void setUri(String str) {
        pjsua2JNI.AccountInfo_uri_set(this.swigCPtr, this, str);
    }

    public AccountInfo() {
        this(pjsua2JNI.new_AccountInfo(), true);
    }
}
