package com.amazon.alexa;

import java.io.InputStream;
import java.io.OutputStream;
/* compiled from: Attachment.java */
/* loaded from: classes.dex */
public abstract class Aml {
    public final cIy attachmentID;
    public volatile boolean isFinished = false;

    public Aml(cIy ciy) {
        this.attachmentID = ciy;
    }

    public abstract void close();

    public void finish() {
        this.isFinished = true;
    }

    public cIy getAttachmentIdentifier() {
        return this.attachmentID;
    }

    public abstract ZVp getDataFormat();

    public abstract InputStream getInputStream();

    public abstract OutputStream getOutputStream();

    public abstract InputStream getRetryInputStream();

    public abstract boolean isClosed();

    public boolean isFinished() {
        return this.isFinished;
    }
}
