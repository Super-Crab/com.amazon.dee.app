package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class CertificateRequest {
    protected final Vector certificateAuthorities;
    protected final byte[] certificateRequestContext;
    protected final short[] certificateTypes;
    protected final Vector supportedSignatureAlgorithms;
    protected final Vector supportedSignatureAlgorithmsCert;

    public CertificateRequest(byte[] bArr, Vector vector, Vector vector2, Vector vector3) {
        this(bArr, null, vector, vector2, vector3);
    }

    private CertificateRequest(byte[] bArr, short[] sArr, Vector vector, Vector vector2, Vector vector3) {
        if (bArr == null || TlsUtils.isValidUint8(bArr.length)) {
            if (sArr != null && (sArr.length < 1 || !TlsUtils.isValidUint8(sArr.length))) {
                throw new IllegalArgumentException("'certificateTypes' should have length from 1 to 255");
            }
            this.certificateRequestContext = TlsUtils.clone(bArr);
            this.certificateTypes = sArr;
            this.supportedSignatureAlgorithms = vector;
            this.supportedSignatureAlgorithmsCert = vector2;
            this.certificateAuthorities = vector3;
            return;
        }
        throw new IllegalArgumentException("'certificateRequestContext' cannot be longer than 255");
    }

    public CertificateRequest(short[] sArr, Vector vector, Vector vector2) {
        this(null, sArr, vector, null, vector2);
    }

    public static CertificateRequest parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        ProtocolVersion serverVersion = tlsContext.getServerVersion();
        if (TlsUtils.isTLSv13(serverVersion)) {
            byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream);
            Hashtable readExtensionsData = TlsProtocol.readExtensionsData(TlsUtils.readOpaque16(inputStream));
            return new CertificateRequest(readOpaque8, TlsExtensionsUtils.getSignatureAlgorithmsExtension(readExtensionsData), TlsExtensionsUtils.getSignatureAlgorithmsCertExtension(readExtensionsData), TlsExtensionsUtils.getCertificateAuthoritiesExtension(readExtensionsData));
        }
        boolean isTLSv12 = TlsUtils.isTLSv12(serverVersion);
        short[] readUint8ArrayWithUint8Length = TlsUtils.readUint8ArrayWithUint8Length(inputStream, 1);
        Vector vector = null;
        Vector parseSupportedSignatureAlgorithms = isTLSv12 ? TlsUtils.parseSupportedSignatureAlgorithms(inputStream) : null;
        byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream);
        if (readOpaque16.length > 0) {
            Vector vector2 = new Vector();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readOpaque16);
            do {
                vector2.addElement(X500Name.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque16(byteArrayInputStream, 1))));
            } while (byteArrayInputStream.available() > 0);
            vector = vector2;
        }
        return new CertificateRequest(readUint8ArrayWithUint8Length, parseSupportedSignatureAlgorithms, vector);
    }

    public void encode(TlsContext tlsContext, OutputStream outputStream) throws IOException {
        ProtocolVersion serverVersion = tlsContext.getServerVersion();
        boolean isTLSv12 = TlsUtils.isTLSv12(serverVersion);
        boolean isTLSv13 = TlsUtils.isTLSv13(serverVersion);
        boolean z = true;
        if (isTLSv13 == (this.certificateRequestContext != null)) {
            if (isTLSv13 == (this.certificateTypes == null)) {
                if (this.supportedSignatureAlgorithms == null) {
                    z = false;
                }
                if (isTLSv12 == z && (isTLSv13 || this.supportedSignatureAlgorithmsCert == null)) {
                    if (isTLSv13) {
                        TlsUtils.writeOpaque8(this.certificateRequestContext, outputStream);
                        Hashtable hashtable = new Hashtable();
                        TlsExtensionsUtils.addSignatureAlgorithmsExtension(hashtable, this.supportedSignatureAlgorithms);
                        Vector vector = this.supportedSignatureAlgorithmsCert;
                        if (vector != null) {
                            TlsExtensionsUtils.addSignatureAlgorithmsCertExtension(hashtable, vector);
                        }
                        Vector vector2 = this.certificateAuthorities;
                        if (vector2 != null) {
                            TlsExtensionsUtils.addCertificateAuthoritiesExtension(hashtable, vector2);
                        }
                        TlsUtils.writeOpaque16(TlsProtocol.writeExtensionsData(hashtable), outputStream);
                        return;
                    }
                    TlsUtils.writeUint8ArrayWithUint8Length(this.certificateTypes, outputStream);
                    if (isTLSv12) {
                        TlsUtils.encodeSupportedSignatureAlgorithms(this.supportedSignatureAlgorithms, outputStream);
                    }
                    Vector vector3 = this.certificateAuthorities;
                    if (vector3 == null || vector3.isEmpty()) {
                        TlsUtils.writeUint16(0, outputStream);
                        return;
                    }
                    Vector vector4 = new Vector(this.certificateAuthorities.size());
                    int i = 0;
                    for (int i2 = 0; i2 < this.certificateAuthorities.size(); i2++) {
                        byte[] encoded = ((X500Name) this.certificateAuthorities.elementAt(i2)).getEncoded("DER");
                        vector4.addElement(encoded);
                        i += encoded.length + 2;
                    }
                    TlsUtils.checkUint16(i);
                    TlsUtils.writeUint16(i, outputStream);
                    for (int i3 = 0; i3 < vector4.size(); i3++) {
                        TlsUtils.writeOpaque16((byte[]) vector4.elementAt(i3), outputStream);
                    }
                    return;
                }
            }
        }
        throw new IllegalStateException();
    }

    public Vector getCertificateAuthorities() {
        return this.certificateAuthorities;
    }

    public byte[] getCertificateRequestContext() {
        return TlsUtils.clone(this.certificateRequestContext);
    }

    public short[] getCertificateTypes() {
        return this.certificateTypes;
    }

    public Vector getSupportedSignatureAlgorithms() {
        return this.supportedSignatureAlgorithms;
    }

    public Vector getSupportedSignatureAlgorithmsCert() {
        return this.supportedSignatureAlgorithmsCert;
    }

    public boolean hasCertificateRequestContext(byte[] bArr) {
        return Arrays.areEqual(this.certificateRequestContext, bArr);
    }
}
