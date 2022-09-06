package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TransportInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransportInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TransportInfo transportInfo) {
        if (transportInfo == null) {
            return 0L;
        }
        return transportInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TransportInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getFlags() {
        return pjsua2JNI.TransportInfo_flags_get(this.swigCPtr, this);
    }

    public int getId() {
        return pjsua2JNI.TransportInfo_id_get(this.swigCPtr, this);
    }

    public String getInfo() {
        return pjsua2JNI.TransportInfo_info_get(this.swigCPtr, this);
    }

    public String getLocalAddress() {
        return pjsua2JNI.TransportInfo_localAddress_get(this.swigCPtr, this);
    }

    public String getLocalName() {
        return pjsua2JNI.TransportInfo_localName_get(this.swigCPtr, this);
    }

    public pjsip_transport_type_e getType() {
        return pjsip_transport_type_e.swigToEnum(pjsua2JNI.TransportInfo_type_get(this.swigCPtr, this));
    }

    public String getTypeName() {
        return pjsua2JNI.TransportInfo_typeName_get(this.swigCPtr, this);
    }

    public long getUsageCount() {
        return pjsua2JNI.TransportInfo_usageCount_get(this.swigCPtr, this);
    }

    public void setFlags(long j) {
        pjsua2JNI.TransportInfo_flags_set(this.swigCPtr, this, j);
    }

    public void setId(int i) {
        pjsua2JNI.TransportInfo_id_set(this.swigCPtr, this, i);
    }

    public void setInfo(String str) {
        pjsua2JNI.TransportInfo_info_set(this.swigCPtr, this, str);
    }

    public void setLocalAddress(String str) {
        pjsua2JNI.TransportInfo_localAddress_set(this.swigCPtr, this, str);
    }

    public void setLocalName(String str) {
        pjsua2JNI.TransportInfo_localName_set(this.swigCPtr, this, str);
    }

    public void setType(pjsip_transport_type_e pjsip_transport_type_eVar) {
        pjsua2JNI.TransportInfo_type_set(this.swigCPtr, this, pjsip_transport_type_eVar.swigValue());
    }

    public void setTypeName(String str) {
        pjsua2JNI.TransportInfo_typeName_set(this.swigCPtr, this, str);
    }

    public void setUsageCount(long j) {
        pjsua2JNI.TransportInfo_usageCount_set(this.swigCPtr, this, j);
    }

    public TransportInfo() {
        this(pjsua2JNI.new_TransportInfo(), true);
    }
}
