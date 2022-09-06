package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class pj_qos_params {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public pj_qos_params(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(pj_qos_params pj_qos_paramsVar) {
        if (pj_qos_paramsVar == null) {
            return 0L;
        }
        return pj_qos_paramsVar.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_pj_qos_params(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public short getDscp_val() {
        return pjsua2JNI.pj_qos_params_dscp_val_get(this.swigCPtr, this);
    }

    public short getFlags() {
        return pjsua2JNI.pj_qos_params_flags_get(this.swigCPtr, this);
    }

    public short getSo_prio() {
        return pjsua2JNI.pj_qos_params_so_prio_get(this.swigCPtr, this);
    }

    public pj_qos_wmm_prio getWmm_prio() {
        return pj_qos_wmm_prio.swigToEnum(pjsua2JNI.pj_qos_params_wmm_prio_get(this.swigCPtr, this));
    }

    public void setDscp_val(short s) {
        pjsua2JNI.pj_qos_params_dscp_val_set(this.swigCPtr, this, s);
    }

    public void setFlags(short s) {
        pjsua2JNI.pj_qos_params_flags_set(this.swigCPtr, this, s);
    }

    public void setSo_prio(short s) {
        pjsua2JNI.pj_qos_params_so_prio_set(this.swigCPtr, this, s);
    }

    public void setWmm_prio(pj_qos_wmm_prio pj_qos_wmm_prioVar) {
        pjsua2JNI.pj_qos_params_wmm_prio_set(this.swigCPtr, this, pj_qos_wmm_prioVar.swigValue());
    }

    public pj_qos_params() {
        this(pjsua2JNI.new_pj_qos_params(), true);
    }
}
