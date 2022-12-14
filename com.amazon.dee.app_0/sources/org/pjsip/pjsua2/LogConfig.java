package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class LogConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public LogConfig(long j, boolean z) {
        super(pjsua2JNI.LogConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(LogConfig logConfig) {
        if (logConfig == null) {
            return 0L;
        }
        return logConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public long getConsoleLevel() {
        return pjsua2JNI.LogConfig_consoleLevel_get(this.swigCPtr, this);
    }

    public long getDecor() {
        return pjsua2JNI.LogConfig_decor_get(this.swigCPtr, this);
    }

    public long getFileFlags() {
        return pjsua2JNI.LogConfig_fileFlags_get(this.swigCPtr, this);
    }

    public String getFilename() {
        return pjsua2JNI.LogConfig_filename_get(this.swigCPtr, this);
    }

    public long getLevel() {
        return pjsua2JNI.LogConfig_level_get(this.swigCPtr, this);
    }

    public long getMsgLogging() {
        return pjsua2JNI.LogConfig_msgLogging_get(this.swigCPtr, this);
    }

    public LogWriter getWriter() {
        long LogConfig_writer_get = pjsua2JNI.LogConfig_writer_get(this.swigCPtr, this);
        if (LogConfig_writer_get == 0) {
            return null;
        }
        return new LogWriter(LogConfig_writer_get, false);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.LogConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setConsoleLevel(long j) {
        pjsua2JNI.LogConfig_consoleLevel_set(this.swigCPtr, this, j);
    }

    public void setDecor(long j) {
        pjsua2JNI.LogConfig_decor_set(this.swigCPtr, this, j);
    }

    public void setFileFlags(long j) {
        pjsua2JNI.LogConfig_fileFlags_set(this.swigCPtr, this, j);
    }

    public void setFilename(String str) {
        pjsua2JNI.LogConfig_filename_set(this.swigCPtr, this, str);
    }

    public void setLevel(long j) {
        pjsua2JNI.LogConfig_level_set(this.swigCPtr, this, j);
    }

    public void setMsgLogging(long j) {
        pjsua2JNI.LogConfig_msgLogging_set(this.swigCPtr, this, j);
    }

    public void setWriter(LogWriter logWriter) {
        pjsua2JNI.LogConfig_writer_set(this.swigCPtr, this, LogWriter.getCPtr(logWriter), logWriter);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.LogConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public LogConfig() {
        this(pjsua2JNI.new_LogConfig(), true);
    }
}
