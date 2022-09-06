package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import org.apache.thrift.orig.TByteArrayOutputStream;
/* loaded from: classes4.dex */
public class TMemoryBuffer extends TTransport {
    private TByteArrayOutputStream arr_;
    private int pos_;

    public TMemoryBuffer(int i) {
        this.arr_ = new TByteArrayOutputStream(i);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
    }

    public byte[] getArray() {
        return this.arr_.get();
    }

    public String inspect() {
        byte[] byteArray = this.arr_.toByteArray();
        int i = 0;
        String str = "";
        while (i < byteArray.length) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(this.pos_ == i ? "==>" : "");
            str = GeneratedOutlineSupport1.outline33(byteArray[i] & 255, outline107, " ");
            i++;
        }
        return str;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return true;
    }

    public int length() {
        return this.arr_.size();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() {
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.arr_.get();
        if (i2 > this.arr_.len() - this.pos_) {
            i2 = this.arr_.len() - this.pos_;
        }
        if (i2 > 0) {
            System.arraycopy(bArr2, this.pos_, bArr, i, i2);
            this.pos_ += i2;
        }
        return i2;
    }

    public String toString(String str) throws UnsupportedEncodingException {
        return this.arr_.toString(str);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) {
        this.arr_.write(bArr, i, i2);
    }
}
