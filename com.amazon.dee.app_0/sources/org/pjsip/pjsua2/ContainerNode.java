package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class ContainerNode {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerNode(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ContainerNode containerNode) {
        if (containerNode == null) {
            return 0L;
        }
        return containerNode.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ContainerNode(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean hasUnread() {
        return pjsua2JNI.ContainerNode_hasUnread(this.swigCPtr, this);
    }

    public ContainerNode readArray(String str) throws Exception {
        return new ContainerNode(pjsua2JNI.ContainerNode_readArray__SWIG_0(this.swigCPtr, this, str), true);
    }

    public boolean readBool(String str) throws Exception {
        return pjsua2JNI.ContainerNode_readBool__SWIG_0(this.swigCPtr, this, str);
    }

    public ContainerNode readContainer(String str) throws Exception {
        return new ContainerNode(pjsua2JNI.ContainerNode_readContainer__SWIG_0(this.swigCPtr, this, str), true);
    }

    public int readInt(String str) throws Exception {
        return pjsua2JNI.ContainerNode_readInt__SWIG_0(this.swigCPtr, this, str);
    }

    public float readNumber(String str) throws Exception {
        return pjsua2JNI.ContainerNode_readNumber__SWIG_0(this.swigCPtr, this, str);
    }

    public void readObject(PersistentObject persistentObject) throws Exception {
        pjsua2JNI.ContainerNode_readObject(this.swigCPtr, this, PersistentObject.getCPtr(persistentObject), persistentObject);
    }

    public String readString(String str) throws Exception {
        return pjsua2JNI.ContainerNode_readString__SWIG_0(this.swigCPtr, this, str);
    }

    public StringVector readStringVector(String str) throws Exception {
        return new StringVector(pjsua2JNI.ContainerNode_readStringVector__SWIG_0(this.swigCPtr, this, str), true);
    }

    public String unreadName() throws Exception {
        return pjsua2JNI.ContainerNode_unreadName(this.swigCPtr, this);
    }

    public void writeBool(String str, boolean z) throws Exception {
        pjsua2JNI.ContainerNode_writeBool(this.swigCPtr, this, str, z);
    }

    public void writeInt(String str, int i) throws Exception {
        pjsua2JNI.ContainerNode_writeInt(this.swigCPtr, this, str, i);
    }

    public ContainerNode writeNewArray(String str) throws Exception {
        return new ContainerNode(pjsua2JNI.ContainerNode_writeNewArray(this.swigCPtr, this, str), true);
    }

    public ContainerNode writeNewContainer(String str) throws Exception {
        return new ContainerNode(pjsua2JNI.ContainerNode_writeNewContainer(this.swigCPtr, this, str), true);
    }

    public void writeNumber(String str, float f) throws Exception {
        pjsua2JNI.ContainerNode_writeNumber(this.swigCPtr, this, str, f);
    }

    public void writeObject(PersistentObject persistentObject) throws Exception {
        pjsua2JNI.ContainerNode_writeObject(this.swigCPtr, this, PersistentObject.getCPtr(persistentObject), persistentObject);
    }

    public void writeString(String str, String str2) throws Exception {
        pjsua2JNI.ContainerNode_writeString(this.swigCPtr, this, str, str2);
    }

    public void writeStringVector(String str, StringVector stringVector) throws Exception {
        pjsua2JNI.ContainerNode_writeStringVector(this.swigCPtr, this, str, StringVector.getCPtr(stringVector), stringVector);
    }

    public ContainerNode readArray() throws Exception {
        return new ContainerNode(pjsua2JNI.ContainerNode_readArray__SWIG_1(this.swigCPtr, this), true);
    }

    public boolean readBool() throws Exception {
        return pjsua2JNI.ContainerNode_readBool__SWIG_1(this.swigCPtr, this);
    }

    public ContainerNode readContainer() throws Exception {
        return new ContainerNode(pjsua2JNI.ContainerNode_readContainer__SWIG_1(this.swigCPtr, this), true);
    }

    public int readInt() throws Exception {
        return pjsua2JNI.ContainerNode_readInt__SWIG_1(this.swigCPtr, this);
    }

    public float readNumber() throws Exception {
        return pjsua2JNI.ContainerNode_readNumber__SWIG_1(this.swigCPtr, this);
    }

    public String readString() throws Exception {
        return pjsua2JNI.ContainerNode_readString__SWIG_1(this.swigCPtr, this);
    }

    public StringVector readStringVector() throws Exception {
        return new StringVector(pjsua2JNI.ContainerNode_readStringVector__SWIG_1(this.swigCPtr, this), true);
    }

    public ContainerNode() {
        this(pjsua2JNI.new_ContainerNode(), true);
    }
}
