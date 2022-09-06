package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCertificate;
/* loaded from: classes5.dex */
public class Certificate {
    private static final TlsCertificate[] EMPTY_CERTS = new TlsCertificate[0];
    private static final CertificateEntry[] EMPTY_CERT_ENTRIES = new CertificateEntry[0];
    public static final Certificate EMPTY_CHAIN = new Certificate(EMPTY_CERTS);
    public static final Certificate EMPTY_CHAIN_TLS13 = new Certificate(TlsUtils.EMPTY_BYTES, EMPTY_CERT_ENTRIES);
    protected final CertificateEntry[] certificateEntryList;
    protected final byte[] certificateRequestContext;

    public Certificate(byte[] bArr, CertificateEntry[] certificateEntryArr) {
        if (bArr == null || TlsUtils.isValidUint8(bArr.length)) {
            if (TlsUtils.isNullOrContainsNull(certificateEntryArr)) {
                throw new NullPointerException("'certificateEntryList' cannot be null or contain any nulls");
            }
            this.certificateRequestContext = TlsUtils.clone(bArr);
            this.certificateEntryList = certificateEntryArr;
            return;
        }
        throw new IllegalArgumentException("'certificateRequestContext' cannot be longer than 255");
    }

    public Certificate(TlsCertificate[] tlsCertificateArr) {
        this(null, convert(tlsCertificateArr));
    }

    protected static void calculateEndPointHash(TlsContext tlsContext, TlsCertificate tlsCertificate, byte[] bArr, OutputStream outputStream) throws IOException {
        byte[] calculateEndPointHash = TlsUtils.calculateEndPointHash(tlsContext, tlsCertificate, bArr);
        if (calculateEndPointHash == null || calculateEndPointHash.length <= 0) {
            return;
        }
        outputStream.write(calculateEndPointHash);
    }

    private static CertificateEntry[] convert(TlsCertificate[] tlsCertificateArr) {
        if (!TlsUtils.isNullOrContainsNull(tlsCertificateArr)) {
            int length = tlsCertificateArr.length;
            CertificateEntry[] certificateEntryArr = new CertificateEntry[length];
            for (int i = 0; i < length; i++) {
                certificateEntryArr[i] = new CertificateEntry(tlsCertificateArr[i], null);
            }
            return certificateEntryArr;
        }
        throw new NullPointerException("'certificateList' cannot be null or contain any nulls");
    }

    public static Certificate parse(TlsContext tlsContext, InputStream inputStream, OutputStream outputStream) throws IOException {
        boolean isTLSv13 = TlsUtils.isTLSv13(tlsContext);
        byte[] readOpaque8 = isTLSv13 ? TlsUtils.readOpaque8(inputStream) : null;
        int readUint24 = TlsUtils.readUint24(inputStream);
        if (readUint24 == 0) {
            return !isTLSv13 ? EMPTY_CHAIN : readOpaque8.length < 1 ? EMPTY_CHAIN_TLS13 : new Certificate(readOpaque8, EMPTY_CERT_ENTRIES);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint24, inputStream));
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream, 1);
            TlsCertificate createCertificate = tlsContext.getCrypto().createCertificate(readOpaque24);
            if (vector.isEmpty() && outputStream != null) {
                calculateEndPointHash(tlsContext, createCertificate, readOpaque24, outputStream);
            }
            vector.addElement(new CertificateEntry(createCertificate, isTLSv13 ? TlsProtocol.readExtensionsData(TlsUtils.readOpaque16(byteArrayInputStream)) : null));
        }
        CertificateEntry[] certificateEntryArr = new CertificateEntry[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            certificateEntryArr[i] = (CertificateEntry) vector.elementAt(i);
        }
        return new Certificate(readOpaque8, certificateEntryArr);
    }

    protected CertificateEntry[] cloneCertificateEntryList() {
        CertificateEntry[] certificateEntryArr = this.certificateEntryList;
        int length = certificateEntryArr.length;
        if (length == 0) {
            return EMPTY_CERT_ENTRIES;
        }
        CertificateEntry[] certificateEntryArr2 = new CertificateEntry[length];
        System.arraycopy(certificateEntryArr, 0, certificateEntryArr2, 0, length);
        return certificateEntryArr2;
    }

    protected TlsCertificate[] cloneCertificateList() {
        int length = this.certificateEntryList.length;
        if (length == 0) {
            return EMPTY_CERTS;
        }
        TlsCertificate[] tlsCertificateArr = new TlsCertificate[length];
        for (int i = 0; i < length; i++) {
            tlsCertificateArr[i] = this.certificateEntryList[i].getCertificate();
        }
        return tlsCertificateArr;
    }

    public void encode(TlsContext tlsContext, OutputStream outputStream, OutputStream outputStream2) throws IOException {
        byte[] writeExtensionsData;
        boolean isTLSv13 = TlsUtils.isTLSv13(tlsContext);
        if ((this.certificateRequestContext != null) == isTLSv13) {
            if (isTLSv13) {
                TlsUtils.writeOpaque8(this.certificateRequestContext, outputStream);
            }
            int length = this.certificateEntryList.length;
            Vector vector = new Vector(length);
            Vector vector2 = isTLSv13 ? new Vector(length) : null;
            long j = 0;
            for (int i = 0; i < length; i++) {
                CertificateEntry certificateEntry = this.certificateEntryList[i];
                TlsCertificate certificate = certificateEntry.getCertificate();
                byte[] encoded = certificate.getEncoded();
                if (i == 0 && outputStream2 != null) {
                    calculateEndPointHash(tlsContext, certificate, encoded, outputStream2);
                }
                vector.addElement(encoded);
                j = j + encoded.length + 3;
                if (isTLSv13) {
                    Hashtable extensions = certificateEntry.getExtensions();
                    vector2.addElement(extensions == null ? TlsUtils.EMPTY_BYTES : TlsProtocol.writeExtensionsData(extensions));
                    j = j + writeExtensionsData.length + 2;
                }
            }
            TlsUtils.checkUint24(j);
            TlsUtils.writeUint24((int) j, outputStream);
            for (int i2 = 0; i2 < length; i2++) {
                TlsUtils.writeOpaque24((byte[]) vector.elementAt(i2), outputStream);
                if (isTLSv13) {
                    TlsUtils.writeOpaque16((byte[]) vector2.elementAt(i2), outputStream);
                }
            }
            return;
        }
        throw new IllegalStateException();
    }

    public TlsCertificate getCertificateAt(int i) {
        return this.certificateEntryList[i].getCertificate();
    }

    public CertificateEntry getCertificateEntryAt(int i) {
        return this.certificateEntryList[i];
    }

    public CertificateEntry[] getCertificateEntryList() {
        return cloneCertificateEntryList();
    }

    public TlsCertificate[] getCertificateList() {
        return cloneCertificateList();
    }

    public byte[] getCertificateRequestContext() {
        return TlsUtils.clone(this.certificateRequestContext);
    }

    public short getCertificateType() {
        return (short) 0;
    }

    public int getLength() {
        return this.certificateEntryList.length;
    }

    public boolean isEmpty() {
        return this.certificateEntryList.length == 0;
    }
}
