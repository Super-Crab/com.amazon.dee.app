package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class JsonDocument extends PersistentDocument {
    private transient long swigCPtr;

    protected JsonDocument(long j, boolean z) {
        super(pjsua2JNI.JsonDocument_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(JsonDocument jsonDocument) {
        if (jsonDocument == null) {
            return 0L;
        }
        return jsonDocument.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_JsonDocument(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    protected void finalize() {
        delete();
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    public ContainerNode getRootContainer() {
        return new ContainerNode(pjsua2JNI.JsonDocument_getRootContainer(this.swigCPtr, this), false);
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    public void loadFile(String str) throws Exception {
        pjsua2JNI.JsonDocument_loadFile(this.swigCPtr, this, str);
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    public void loadString(String str) throws Exception {
        pjsua2JNI.JsonDocument_loadString(this.swigCPtr, this, str);
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    public void saveFile(String str) throws Exception {
        pjsua2JNI.JsonDocument_saveFile(this.swigCPtr, this, str);
    }

    @Override // org.pjsip.pjsua2.PersistentDocument
    public String saveString() throws Exception {
        return pjsua2JNI.JsonDocument_saveString(this.swigCPtr, this);
    }

    public JsonDocument() {
        this(pjsua2JNI.new_JsonDocument(), true);
    }
}
