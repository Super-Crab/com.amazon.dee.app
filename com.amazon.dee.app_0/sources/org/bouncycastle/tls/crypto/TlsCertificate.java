package org.bouncycastle.tls.crypto;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes5.dex */
public interface TlsCertificate {
    TlsVerifier createVerifier(short s) throws IOException;

    byte[] getEncoded() throws IOException;

    byte[] getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws IOException;

    short getLegacySignatureAlgorithm() throws IOException;

    BigInteger getSerialNumber();

    String getSigAlgOID();

    ASN1Encodable getSigAlgParams() throws IOException;

    boolean supportsSignatureAlgorithm(short s) throws IOException;

    boolean supportsSignatureAlgorithmCA(short s) throws IOException;

    TlsCertificate useInRole(int i, int i2) throws IOException;
}
