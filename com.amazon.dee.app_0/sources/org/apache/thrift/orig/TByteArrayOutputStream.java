package org.apache.thrift.orig;

import java.io.ByteArrayOutputStream;
/* loaded from: classes4.dex */
public class TByteArrayOutputStream extends ByteArrayOutputStream {
    public TByteArrayOutputStream(int i) {
        super(i);
    }

    public byte[] get() {
        return ((ByteArrayOutputStream) this).buf;
    }

    public int len() {
        return ((ByteArrayOutputStream) this).count;
    }

    public TByteArrayOutputStream() {
    }
}
