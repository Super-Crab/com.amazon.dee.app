package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TransportConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransportConfig(long j, boolean z) {
        super(pjsua2JNI.TransportConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TransportConfig transportConfig) {
        if (transportConfig == null) {
            return 0L;
        }
        return transportConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TransportConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public String getBoundAddress() {
        return pjsua2JNI.TransportConfig_boundAddress_get(this.swigCPtr, this);
    }

    public long getPort() {
        return pjsua2JNI.TransportConfig_port_get(this.swigCPtr, this);
    }

    public long getPortRange() {
        return pjsua2JNI.TransportConfig_portRange_get(this.swigCPtr, this);
    }

    public String getPublicAddress() {
        return pjsua2JNI.TransportConfig_publicAddress_get(this.swigCPtr, this);
    }

    public pj_qos_params getQosParams() {
        long TransportConfig_qosParams_get = pjsua2JNI.TransportConfig_qosParams_get(this.swigCPtr, this);
        if (TransportConfig_qosParams_get == 0) {
            return null;
        }
        return new pj_qos_params(TransportConfig_qosParams_get, false);
    }

    public pj_qos_type getQosType() {
        return pj_qos_type.swigToEnum(pjsua2JNI.TransportConfig_qosType_get(this.swigCPtr, this));
    }

    public TlsConfig getTlsConfig() {
        long TransportConfig_tlsConfig_get = pjsua2JNI.TransportConfig_tlsConfig_get(this.swigCPtr, this);
        if (TransportConfig_tlsConfig_get == 0) {
            return null;
        }
        return new TlsConfig(TransportConfig_tlsConfig_get, false);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.TransportConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setBoundAddress(String str) {
        pjsua2JNI.TransportConfig_boundAddress_set(this.swigCPtr, this, str);
    }

    public void setPort(long j) {
        pjsua2JNI.TransportConfig_port_set(this.swigCPtr, this, j);
    }

    public void setPortRange(long j) {
        pjsua2JNI.TransportConfig_portRange_set(this.swigCPtr, this, j);
    }

    public void setPublicAddress(String str) {
        pjsua2JNI.TransportConfig_publicAddress_set(this.swigCPtr, this, str);
    }

    public void setQosParams(pj_qos_params pj_qos_paramsVar) {
        pjsua2JNI.TransportConfig_qosParams_set(this.swigCPtr, this, pj_qos_params.getCPtr(pj_qos_paramsVar), pj_qos_paramsVar);
    }

    public void setQosType(pj_qos_type pj_qos_typeVar) {
        pjsua2JNI.TransportConfig_qosType_set(this.swigCPtr, this, pj_qos_typeVar.swigValue());
    }

    public void setTlsConfig(TlsConfig tlsConfig) {
        pjsua2JNI.TransportConfig_tlsConfig_set(this.swigCPtr, this, TlsConfig.getCPtr(tlsConfig), tlsConfig);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.TransportConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public TransportConfig() {
        this(pjsua2JNI.new_TransportConfig(), true);
    }
}
