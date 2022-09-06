package org.bouncycastle.tls.crypto;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
/* loaded from: classes5.dex */
public interface TlsCrypto {
    TlsSecret adoptSecret(TlsSecret tlsSecret);

    TlsCertificate createCertificate(byte[] bArr) throws IOException;

    TlsCipher createCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException;

    TlsDHDomain createDHDomain(TlsDHConfig tlsDHConfig);

    TlsECDomain createECDomain(TlsECConfig tlsECConfig);

    TlsHMAC createHMAC(int i);

    TlsHMAC createHMAC(short s);

    TlsHash createHash(short s);

    TlsNonceGenerator createNonceGenerator(byte[] bArr);

    TlsSRP6Client createSRP6Client(TlsSRPConfig tlsSRPConfig);

    TlsSRP6Server createSRP6Server(TlsSRPConfig tlsSRPConfig, BigInteger bigInteger);

    TlsSRP6VerifierGenerator createSRP6VerifierGenerator(TlsSRPConfig tlsSRPConfig);

    TlsSecret createSecret(byte[] bArr);

    TlsSecret generateRSAPreMasterSecret(ProtocolVersion protocolVersion);

    SecureRandom getSecureRandom();

    boolean hasAllRawSignatureAlgorithms();

    boolean hasDHAgreement();

    boolean hasECDHAgreement();

    boolean hasEncryptionAlgorithm(int i);

    boolean hasHashAlgorithm(short s);

    boolean hasMacAlgorithm(int i);

    boolean hasNamedGroup(int i);

    boolean hasRSAEncryption();

    boolean hasSRPAuthentication();

    boolean hasSignatureAlgorithm(short s);

    boolean hasSignatureAndHashAlgorithm(SignatureAndHashAlgorithm signatureAndHashAlgorithm);

    boolean hasSignatureScheme(int i);

    TlsSecret hkdfInit(short s);
}
