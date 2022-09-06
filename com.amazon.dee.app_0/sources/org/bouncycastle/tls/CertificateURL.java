package org.bouncycastle.tls;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
/* loaded from: classes5.dex */
public class CertificateURL {
    protected short type;
    protected Vector urlAndHashList;

    /* loaded from: classes5.dex */
    class ListBuffer16 extends ByteArrayOutputStream {
        ListBuffer16() throws IOException {
            TlsUtils.writeUint16(0, this);
        }

        void encodeTo(OutputStream outputStream) throws IOException {
            int i = ((ByteArrayOutputStream) this).count - 2;
            TlsUtils.checkUint16(i);
            TlsUtils.writeUint16(i, ((ByteArrayOutputStream) this).buf, 0);
            outputStream.write(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    public CertificateURL(short s, Vector vector) {
        if (CertChainType.isValid(s)) {
            if (vector == null || vector.isEmpty()) {
                throw new IllegalArgumentException("'urlAndHashList' must have length > 0");
            }
            if (s != 1 || vector.size() == 1) {
                this.type = s;
                this.urlAndHashList = vector;
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'urlAndHashList' must contain exactly one entry when type is ");
            outline107.append(CertChainType.getText(s));
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("'type' is not a valid CertChainType value");
    }

    public static CertificateURL parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (CertChainType.isValid(readUint8)) {
            int readUint16 = TlsUtils.readUint16(inputStream);
            if (readUint16 < 1) {
                throw new TlsFatalAlert((short) 50);
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint16, inputStream));
            Vector vector = new Vector();
            while (byteArrayInputStream.available() > 0) {
                vector.addElement(URLAndHash.parse(tlsContext, byteArrayInputStream));
            }
            if (readUint8 == 1 && vector.size() != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            return new CertificateURL(readUint8, vector);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.type, outputStream);
        ListBuffer16 listBuffer16 = new ListBuffer16();
        for (int i = 0; i < this.urlAndHashList.size(); i++) {
            ((URLAndHash) this.urlAndHashList.elementAt(i)).encode(listBuffer16);
        }
        listBuffer16.encodeTo(outputStream);
    }

    public short getType() {
        return this.type;
    }

    public Vector getURLAndHashList() {
        return this.urlAndHashList;
    }
}
