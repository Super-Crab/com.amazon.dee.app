package org.bouncycastle.tls;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Shorts;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes5.dex */
public class TlsUtils {
    private static byte[] DOWNGRADE_TLS11 = Hex.decodeStrict("444F574E47524400");
    private static byte[] DOWNGRADE_TLS12 = Hex.decodeStrict("444F574E47524401");
    private static final Hashtable CERT_SIG_ALG_OIDS = createCertSigAlgOIDs();
    private static final Vector DEFAULT_SUPPORTED_SIG_ALGS = createDefaultSupportedSigAlgs();
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    protected static short MINIMUM_HASH_STRICT = 2;
    protected static short MINIMUM_HASH_PREFERRED = 4;

    public static TlsSecret PRF(SecurityParameters securityParameters, TlsSecret tlsSecret, String str, byte[] bArr, int i) {
        return tlsSecret.deriveUsingPRF(securityParameters.getPRFAlgorithm(), str, bArr, i);
    }

    public static TlsSecret PRF(TlsContext tlsContext, TlsSecret tlsSecret, String str, byte[] bArr, int i) {
        return PRF(tlsContext.getSecurityParametersHandshake(), tlsSecret, str, bArr, i);
    }

    private static void addCertSigAlgOID(Hashtable hashtable, ASN1ObjectIdentifier aSN1ObjectIdentifier, short s, short s2) {
        hashtable.put(aSN1ObjectIdentifier.getId(), SignatureAndHashAlgorithm.getInstance(s, s2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Hashtable addEarlyKeySharesToClientHello(TlsClientContext tlsClientContext, TlsClient tlsClient, Hashtable hashtable) throws IOException {
        if (!isTLSv13(tlsClientContext.getClientVersion()) || !hashtable.containsKey(TlsExtensionsUtils.EXT_supported_groups)) {
            return null;
        }
        int[] supportedGroupsExtension = TlsExtensionsUtils.getSupportedGroupsExtension(hashtable);
        Vector earlyKeyShareGroups = tlsClient.getEarlyKeyShareGroups();
        Hashtable hashtable2 = new Hashtable(3);
        Vector vector = new Vector(2);
        collectKeyShares(tlsClientContext.getCrypto(), supportedGroupsExtension, earlyKeyShareGroups, hashtable2, vector);
        TlsExtensionsUtils.addKeyShareClientHello(hashtable, vector);
        return hashtable2;
    }

    public static void addIfSupported(Vector vector, TlsCrypto tlsCrypto, int i) {
        if (tlsCrypto.hasNamedGroup(i)) {
            vector.addElement(Integers.valueOf(i));
        }
    }

    public static void addIfSupported(Vector vector, TlsCrypto tlsCrypto, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (tlsCrypto.hasSignatureAndHashAlgorithm(signatureAndHashAlgorithm)) {
            vector.addElement(signatureAndHashAlgorithm);
        }
    }

    public static void addIfSupported(Vector vector, TlsCrypto tlsCrypto, int[] iArr) {
        for (int i : iArr) {
            addIfSupported(vector, tlsCrypto, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Hashtable addKeyShareToClientHelloRetry(TlsClientContext tlsClientContext, Hashtable hashtable, int i) throws IOException {
        int[] iArr = {i};
        Vector vectorOfOne = vectorOfOne(Integers.valueOf(i));
        Hashtable hashtable2 = new Hashtable(1, 1.0f);
        Vector vector = new Vector(1);
        collectKeyShares(tlsClientContext.getCrypto(), iArr, vectorOfOne, hashtable2, vector);
        TlsExtensionsUtils.addKeyShareClientHello(hashtable, vector);
        if (hashtable2.isEmpty() || vector.isEmpty()) {
            throw new TlsFatalAlert((short) 80);
        }
        return hashtable2;
    }

    public static boolean addToSet(Vector vector, int i) {
        boolean z = !vector.contains(Integers.valueOf(i));
        if (z) {
            vector.add(Integers.valueOf(i));
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void adjustTranscriptForRetry(TlsHandshakeHash tlsHandshakeHash) throws IOException {
        byte[] currentPRFHash = getCurrentPRFHash(tlsHandshakeHash);
        tlsHandshakeHash.reset();
        int length = currentPRFHash.length;
        checkUint8(length);
        byte[] bArr = new byte[length + 4];
        writeUint8((short) HandshakeType.message_hash, bArr, 0);
        writeUint24(length, bArr, 1);
        System.arraycopy(currentPRFHash, 0, bArr, 4, length);
        tlsHandshakeHash.update(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateEndPointHash(TlsContext tlsContext, TlsCertificate tlsCertificate, byte[] bArr) throws IOException {
        return calculateEndPointHash(tlsContext, tlsCertificate, bArr, 0, bArr.length);
    }

    static byte[] calculateEndPointHash(TlsContext tlsContext, TlsCertificate tlsCertificate, byte[] bArr, int i, int i2) throws IOException {
        short s;
        TlsHash createHash;
        String sigAlgOID = tlsCertificate.getSigAlgOID();
        if (sigAlgOID != null) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.getId().equals(sigAlgOID)) {
                RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(tlsCertificate.getSigAlgParams());
                if (rSASSAPSSparams != null) {
                    ASN1ObjectIdentifier algorithm = rSASSAPSSparams.getHashAlgorithm().getAlgorithm();
                    if (NISTObjectIdentifiers.id_sha256.equals((ASN1Primitive) algorithm)) {
                        s = 4;
                    } else if (NISTObjectIdentifiers.id_sha384.equals((ASN1Primitive) algorithm)) {
                        s = 5;
                    } else if (NISTObjectIdentifiers.id_sha512.equals((ASN1Primitive) algorithm)) {
                        s = 6;
                    }
                }
            } else {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) CERT_SIG_ALG_OIDS.get(sigAlgOID);
                if (signatureAndHashAlgorithm != null) {
                    s = signatureAndHashAlgorithm.getHash();
                }
            }
            if (s != 1 || s == 2) {
                s = 4;
            } else if (s == 8) {
                s = 0;
            }
            if (s != 0 || (createHash = tlsContext.getCrypto().createHash(s)) == null) {
                return EMPTY_BYTES;
            }
            createHash.update(bArr, i, i2);
            return createHash.calculateHash();
        }
        s = 0;
        if (s != 1) {
        }
        s = 4;
        if (s != 0) {
        }
        return EMPTY_BYTES;
    }

    public static byte[] calculateExporterSeed(SecurityParameters securityParameters, byte[] bArr) {
        byte[] clientRandom = securityParameters.getClientRandom();
        byte[] serverRandom = securityParameters.getServerRandom();
        if (bArr == null) {
            return Arrays.concatenate(clientRandom, serverRandom);
        }
        if (!isValidUint16(bArr.length)) {
            throw new IllegalArgumentException("'context' must have length less than 2^16 (or be null)");
        }
        byte[] bArr2 = new byte[2];
        writeUint16(bArr.length, bArr2, 0);
        return Arrays.concatenate(clientRandom, serverRandom, bArr2, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsSecret calculateMasterSecret(TlsContext tlsContext, TlsSecret tlsSecret) {
        byte[] concat;
        String str;
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.isExtendedMasterSecret()) {
            concat = securityParametersHandshake.getSessionHash();
            str = ExporterLabel.extended_master_secret;
        } else {
            concat = concat(securityParametersHandshake.getClientRandom(), securityParametersHandshake.getServerRandom());
            str = "master secret";
        }
        return PRF(securityParametersHandshake, tlsSecret, str, concat, 48);
    }

    static byte[] calculateSignatureHash(TlsContext tlsContext, SignatureAndHashAlgorithm signatureAndHashAlgorithm, DigestInputBuffer digestInputBuffer) {
        TlsCrypto crypto = tlsContext.getCrypto();
        TlsHash combinedHash = signatureAndHashAlgorithm == null ? new CombinedHash(crypto) : crypto.createHash(signatureAndHashAlgorithm.getHash());
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        byte[] clientRandom = securityParametersHandshake.getClientRandom();
        byte[] serverRandom = securityParametersHandshake.getServerRandom();
        combinedHash.update(clientRandom, 0, clientRandom.length);
        combinedHash.update(serverRandom, 0, serverRandom.length);
        digestInputBuffer.updateDigest(combinedHash);
        return combinedHash.calculateHash();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateVerifyData(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, boolean z) throws IOException {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (!isTLSv13(negotiatedVersion)) {
            if (negotiatedVersion.isSSL()) {
                return SSL3Utils.calculateVerifyData(tlsHandshakeHash, z);
            }
            return PRF(securityParametersHandshake, securityParametersHandshake.getMasterSecret(), z ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(tlsHandshakeHash), securityParametersHandshake.getVerifyDataLength()).extract();
        }
        TlsSecret deriveSecret = deriveSecret(securityParametersHandshake, z ? securityParametersHandshake.getBaseKeyServer() : securityParametersHandshake.getBaseKeyClient(), "finished", EMPTY_BYTES);
        byte[] currentPRFHash = getCurrentPRFHash(tlsHandshakeHash);
        TlsCrypto crypto = tlsContext.getCrypto();
        byte[] extract = crypto.adoptSecret(deriveSecret).extract();
        TlsHMAC createHMAC = crypto.createHMAC(securityParametersHandshake.getPRFHashAlgorithm());
        createHMAC.setKey(extract, 0, extract.length);
        createHMAC.update(currentPRFHash, 0, currentPRFHash.length);
        return createHMAC.calculateMAC();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkDowngradeMarker(ProtocolVersion protocolVersion, byte[] bArr) throws IOException {
        ProtocolVersion equivalentTLSVersion = protocolVersion.getEquivalentTLSVersion();
        if (equivalentTLSVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv11)) {
            checkDowngradeMarker(bArr, DOWNGRADE_TLS11);
        }
        if (equivalentTLSVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv12)) {
            checkDowngradeMarker(bArr, DOWNGRADE_TLS12);
        }
    }

    private static void checkDowngradeMarker(byte[] bArr, byte[] bArr2) throws IOException {
        int length = bArr2.length;
        if (!constantTimeAreEqual(length, bArr2, 0, bArr, bArr.length - length)) {
            return;
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static void checkPeerSigAlgs(TlsContext tlsContext, TlsCertificate[] tlsCertificateArr) throws IOException {
        if (tlsContext.isServer()) {
            checkSigAlgOfClientCerts(tlsContext, tlsCertificateArr);
        } else {
            checkSigAlgOfServerCerts(tlsContext, tlsCertificateArr);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void checkSigAlgOfClientCerts(org.bouncycastle.tls.TlsContext r9, org.bouncycastle.tls.crypto.TlsCertificate[] r10) throws java.io.IOException {
        /*
            org.bouncycastle.tls.SecurityParameters r9 = r9.getSecurityParametersHandshake()
            short[] r0 = r9.getClientCertTypes()
            java.util.Vector r9 = r9.getServerSigAlgsCert()
            int r1 = r10.length
            r2 = 1
            int r1 = r1 - r2
            r3 = 0
            r4 = r3
        L11:
            if (r4 >= r1) goto L4a
            r5 = r10[r4]
            int r4 = r4 + 1
            r6 = r10[r4]
            org.bouncycastle.tls.SignatureAndHashAlgorithm r5 = getCertSigAndHashAlg(r5, r6)
            if (r5 != 0) goto L20
            goto L39
        L20:
            if (r9 != 0) goto L3b
            if (r0 == 0) goto L39
            r6 = r3
        L25:
            int r7 = r0.length
            if (r6 >= r7) goto L39
            short r7 = r0[r6]
            short r7 = getLegacySignatureAlgorithmClientCert(r7)
            short r8 = r5.getSignature()
            if (r8 != r7) goto L36
            r5 = r2
            goto L3f
        L36:
            int r6 = r6 + 1
            goto L25
        L39:
            r5 = r3
            goto L3f
        L3b:
            boolean r5 = containsSignatureAlgorithm(r9, r5)
        L3f:
            if (r5 == 0) goto L42
            goto L11
        L42:
            org.bouncycastle.tls.TlsFatalAlert r9 = new org.bouncycastle.tls.TlsFatalAlert
            r10 = 42
            r9.<init>(r10)
            throw r9
        L4a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsUtils.checkSigAlgOfClientCerts(org.bouncycastle.tls.TlsContext, org.bouncycastle.tls.crypto.TlsCertificate[]):void");
    }

    private static void checkSigAlgOfServerCerts(TlsContext tlsContext, TlsCertificate[] tlsCertificateArr) throws IOException {
        boolean z;
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        Vector clientSigAlgsCert = securityParametersHandshake.getClientSigAlgsCert();
        Vector clientSigAlgs = securityParametersHandshake.getClientSigAlgs();
        if (clientSigAlgs == clientSigAlgsCert || isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
            clientSigAlgs = null;
        }
        int length = tlsCertificateArr.length - 1;
        int i = 0;
        while (i < length) {
            TlsCertificate tlsCertificate = tlsCertificateArr[i];
            i++;
            SignatureAndHashAlgorithm certSigAndHashAlg = getCertSigAndHashAlg(tlsCertificate, tlsCertificateArr[i]);
            if (certSigAndHashAlg != null && (clientSigAlgsCert != null ? containsSignatureAlgorithm(clientSigAlgsCert, certSigAndHashAlg) || (clientSigAlgs != null && containsSignatureAlgorithm(clientSigAlgs, certSigAndHashAlg)) : getLegacySignatureAlgorithmServerCert(securityParametersHandshake.getKeyExchangeAlgorithm()) == certSigAndHashAlg.getSignature())) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (!z) {
                throw new TlsFatalAlert((short) 42);
            }
        }
    }

    static void checkTlsFeatures(Certificate certificate, Hashtable hashtable, Hashtable hashtable2) throws IOException {
        byte[] extension = certificate.getCertificateAt(0).getExtension(TlsObjectIdentifiers.id_pe_tlsfeature);
        if (extension != null) {
            Enumeration objects = ((ASN1Sequence) readDERObject(extension)).getObjects();
            while (objects.hasMoreElements()) {
                BigInteger positiveValue = ((ASN1Integer) objects.nextElement()).getPositiveValue();
                if (positiveValue.bitLength() <= 16) {
                    Integer valueOf = Integers.valueOf(positiveValue.intValue());
                    if (hashtable.containsKey(valueOf) && !hashtable2.containsKey(valueOf)) {
                        throw new TlsFatalAlert((short) 46);
                    }
                }
            }
        }
    }

    public static void checkUint16(int i) throws IOException {
        if (isValidUint16(i)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint16(long j) throws IOException {
        if (isValidUint16(j)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint24(int i) throws IOException {
        if (isValidUint24(i)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint24(long j) throws IOException {
        if (isValidUint24(j)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint32(long j) throws IOException {
        if (isValidUint32(j)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint48(long j) throws IOException {
        if (isValidUint48(j)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint64(long j) throws IOException {
        if (isValidUint64(j)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint8(int i) throws IOException {
        if (isValidUint8(i)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint8(long j) throws IOException {
        if (isValidUint8(j)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static void checkUint8(short s) throws IOException {
        if (isValidUint8(s)) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static SignatureAndHashAlgorithm chooseSignatureAndHashAlgorithm(TlsContext tlsContext, Vector vector, short s) throws IOException {
        short hash;
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (!isTLSv12(tlsContext)) {
            return null;
        }
        if (vector == null) {
            vector = getDefaultSignatureAlgorithms(s);
        }
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (signatureAndHashAlgorithm2.getSignature() == s && (hash = signatureAndHashAlgorithm2.getHash()) >= MINIMUM_HASH_STRICT) {
                if (signatureAndHashAlgorithm != null) {
                    short hash2 = signatureAndHashAlgorithm.getHash();
                    short s2 = MINIMUM_HASH_PREFERRED;
                    if (hash2 < s2) {
                        if (hash <= hash2) {
                        }
                    } else if (hash >= s2) {
                        if (hash >= hash2) {
                        }
                    }
                }
                signatureAndHashAlgorithm = signatureAndHashAlgorithm2;
            }
        }
        if (signatureAndHashAlgorithm == null) {
            throw new TlsFatalAlert((short) 80);
        }
        return signatureAndHashAlgorithm;
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bArr.length == 0 ? EMPTY_BYTES : (byte[]) bArr.clone();
    }

    private static void collectKeyShares(TlsCrypto tlsCrypto, int[] iArr, Vector vector, Hashtable hashtable, Vector vector2) throws IOException {
        if (iArr == null || iArr.length < 1 || vector == null || vector.isEmpty()) {
            return;
        }
        for (int i : iArr) {
            Integer valueOf = Integers.valueOf(i);
            if (vector.contains(valueOf) && !hashtable.containsKey(valueOf) && tlsCrypto.hasNamedGroup(i)) {
                TlsAgreement tlsAgreement = null;
                if (NamedGroup.refersToASpecificCurve(i)) {
                    if (tlsCrypto.hasECDHAgreement()) {
                        tlsAgreement = tlsCrypto.createECDomain(new TlsECConfig(i)).createECDH();
                    }
                } else if (NamedGroup.refersToASpecificFiniteField(i) && tlsCrypto.hasDHAgreement()) {
                    tlsAgreement = tlsCrypto.createDHDomain(new TlsDHConfig(i, true)).createDH();
                }
                if (tlsAgreement != null) {
                    vector2.addElement(new KeyShareEntry(i, tlsAgreement.generateEphemeral()));
                    hashtable.put(valueOf, tlsAgreement);
                }
            }
        }
    }

    static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static boolean constantTimeAreEqual(int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            i4 |= bArr[i2 + i5] ^ bArr2[i3 + i5];
        }
        return i4 == 0;
    }

    static boolean contains(int[] iArr, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i2; i4++) {
            if (i3 == iArr[i + i4]) {
                return true;
            }
        }
        return false;
    }

    static boolean containsAll(short[] sArr, short[] sArr2) {
        for (short s : sArr2) {
            if (!Arrays.contains(sArr, s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAnySignatureAlgorithm(Vector vector, short s) {
        for (int i = 0; i < vector.size(); i++) {
            if (((SignatureAndHashAlgorithm) vector.elementAt(i)).getSignature() == s) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNonAscii(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 128) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNonAscii(byte[] bArr) {
        for (byte b : bArr) {
            if ((b & 255) >= 128) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (signatureAndHashAlgorithm2.getHash() == signatureAndHashAlgorithm.getHash() && signatureAndHashAlgorithm2.getSignature() == signatureAndHashAlgorithm.getSignature()) {
                return true;
            }
        }
        return false;
    }

    public static byte[] copyOfRangeExact(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }

    private static Hashtable createCertSigAlgOIDs() {
        Hashtable hashtable = new Hashtable();
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha224, (short) 3, (short) 2);
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha256, (short) 4, (short) 2);
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha384, (short) 5, (short) 2);
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha512, (short) 6, (short) 2);
        addCertSigAlgOID(hashtable, OIWObjectIdentifiers.dsaWithSHA1, (short) 2, (short) 2);
        addCertSigAlgOID(hashtable, OIWObjectIdentifiers.sha1WithRSA, (short) 2, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha1WithRSAEncryption, (short) 2, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha224WithRSAEncryption, (short) 3, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha256WithRSAEncryption, (short) 4, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha384WithRSAEncryption, (short) 5, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha512WithRSAEncryption, (short) 6, (short) 1);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA1, (short) 2, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA224, (short) 3, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA256, (short) 4, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA384, (short) 5, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA512, (short) 6, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.id_dsa_with_sha1, (short) 2, (short) 2);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_1, (short) 2, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_224, (short) 3, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_256, (short) 4, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_384, (short) 5, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_512, (short) 6, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, (short) 2, (short) 1);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, (short) 4, (short) 1);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA1, (short) 2, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA224, (short) 3, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA256, (short) 4, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA384, (short) 5, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA512, (short) 6, (short) 3);
        addCertSigAlgOID(hashtable, EdECObjectIdentifiers.id_Ed25519, (short) 8, (short) 7);
        addCertSigAlgOID(hashtable, EdECObjectIdentifiers.id_Ed448, (short) 8, (short) 8);
        return hashtable;
    }

    private static Vector createDefaultSupportedSigAlgs() {
        Vector vector = new Vector();
        vector.addElement(SignatureAndHashAlgorithm.ed25519);
        vector.addElement(SignatureAndHashAlgorithm.ed448);
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 4, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 5, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 6, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_rsae_sha256);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_rsae_sha384);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_rsae_sha512);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_pss_sha256);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_pss_sha384);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_pss_sha512);
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 4, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 5, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 6, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 4, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 5, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 6, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 3, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 3, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 3, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 2));
        return vector;
    }

    private static TlsKeyExchange createKeyExchangeClient(TlsClient tlsClient, int i) throws IOException {
        TlsKeyExchangeFactory keyExchangeFactory = tlsClient.getKeyExchangeFactory();
        if (i != 1) {
            if (i == 3 || i == 5) {
                return keyExchangeFactory.createDHEKeyExchangeClient(i, tlsClient.getDHGroupVerifier());
            }
            if (i == 7 || i == 9) {
                return keyExchangeFactory.createDHKeyExchange(i);
            }
            if (i == 11) {
                return keyExchangeFactory.createDHanonKeyExchangeClient(i, tlsClient.getDHGroupVerifier());
            }
            switch (i) {
                case 13:
                case 15:
                case 24:
                    return keyExchangeFactory.createPSKKeyExchangeClient(i, tlsClient.getPSKIdentity(), null);
                case 14:
                    return keyExchangeFactory.createPSKKeyExchangeClient(i, tlsClient.getPSKIdentity(), tlsClient.getDHGroupVerifier());
                case 16:
                case 18:
                    return keyExchangeFactory.createECDHKeyExchange(i);
                case 17:
                case 19:
                    return keyExchangeFactory.createECDHEKeyExchangeClient(i);
                case 20:
                    return keyExchangeFactory.createECDHanonKeyExchangeClient(i);
                case 21:
                case 22:
                case 23:
                    return keyExchangeFactory.createSRPKeyExchangeClient(i, tlsClient.getSRPIdentity(), tlsClient.getSRPConfigVerifier());
                default:
                    throw new TlsFatalAlert((short) 80);
            }
        }
        return keyExchangeFactory.createRSAKeyExchange(i);
    }

    private static TlsKeyExchange createKeyExchangeServer(TlsServer tlsServer, int i) throws IOException {
        TlsKeyExchangeFactory keyExchangeFactory = tlsServer.getKeyExchangeFactory();
        if (i != 1) {
            if (i == 3 || i == 5) {
                return keyExchangeFactory.createDHEKeyExchangeServer(i, tlsServer.getDHConfig());
            }
            if (i == 7 || i == 9) {
                return keyExchangeFactory.createDHKeyExchange(i);
            }
            if (i == 11) {
                return keyExchangeFactory.createDHanonKeyExchangeServer(i, tlsServer.getDHConfig());
            }
            switch (i) {
                case 13:
                case 15:
                    return keyExchangeFactory.createPSKKeyExchangeServer(i, tlsServer.getPSKIdentityManager(), null, null);
                case 14:
                    return keyExchangeFactory.createPSKKeyExchangeServer(i, tlsServer.getPSKIdentityManager(), tlsServer.getDHConfig(), null);
                case 16:
                case 18:
                    return keyExchangeFactory.createECDHKeyExchange(i);
                case 17:
                case 19:
                    return keyExchangeFactory.createECDHEKeyExchangeServer(i, tlsServer.getECDHConfig());
                case 20:
                    return keyExchangeFactory.createECDHanonKeyExchangeServer(i, tlsServer.getECDHConfig());
                case 21:
                case 22:
                case 23:
                    return keyExchangeFactory.createSRPKeyExchangeServer(i, tlsServer.getSRPLoginParameters());
                case 24:
                    return keyExchangeFactory.createPSKKeyExchangeServer(i, tlsServer.getPSKIdentityManager(), null, tlsServer.getECDHConfig());
                default:
                    throw new TlsFatalAlert((short) 80);
            }
        }
        return keyExchangeFactory.createRSAKeyExchange(i);
    }

    public static byte[] decodeOpaque16(byte[] bArr) throws IOException {
        return decodeOpaque16(bArr, 0);
    }

    public static byte[] decodeOpaque16(byte[] bArr, int i) throws IOException {
        if (bArr != null) {
            if (bArr.length < 2) {
                throw new TlsFatalAlert((short) 50);
            }
            int readUint16 = readUint16(bArr, 0);
            if (bArr.length == readUint16 + 2 && readUint16 >= i) {
                return copyOfRangeExact(bArr, 2, bArr.length);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static byte[] decodeOpaque8(byte[] bArr) throws IOException {
        return decodeOpaque8(bArr, 0);
    }

    public static byte[] decodeOpaque8(byte[] bArr, int i) throws IOException {
        if (bArr != null) {
            if (bArr.length < 1) {
                throw new TlsFatalAlert((short) 50);
            }
            short readUint8 = readUint8(bArr, 0);
            if (bArr.length == readUint8 + 1 && readUint8 >= i) {
                return copyOfRangeExact(bArr, 1, bArr.length);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static int decodeUint16(byte[] bArr) throws IOException {
        if (bArr != null) {
            if (bArr.length != 2) {
                throw new TlsFatalAlert((short) 50);
            }
            return readUint16(bArr, 0);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static long decodeUint32(byte[] bArr) throws IOException {
        if (bArr != null) {
            if (bArr.length != 4) {
                throw new TlsFatalAlert((short) 50);
            }
            return readUint32(bArr, 0);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static short decodeUint8(byte[] bArr) throws IOException {
        if (bArr != null) {
            if (bArr.length != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            return readUint8(bArr, 0);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static short[] decodeUint8ArrayWithUint8Length(byte[] bArr) throws IOException {
        if (bArr != null) {
            int i = 0;
            int readUint8 = readUint8(bArr, 0);
            if (bArr.length != readUint8 + 1) {
                throw new TlsFatalAlert((short) 50);
            }
            short[] sArr = new short[readUint8];
            while (i < readUint8) {
                int i2 = i + 1;
                sArr[i] = readUint8(bArr, i2);
                i = i2;
            }
            return sArr;
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    static TlsSecret deriveSecret(SecurityParameters securityParameters, TlsSecret tlsSecret, String str, byte[] bArr) throws IOException {
        return TlsCryptoUtils.hkdfExpandLabel(tlsSecret, securityParameters.getPRFHashAlgorithm(), str, bArr, securityParameters.getPRFHashLength());
    }

    public static byte[] encodeOpaque16(byte[] bArr) throws IOException {
        return Arrays.concatenate(encodeUint16(bArr.length), bArr);
    }

    public static byte[] encodeOpaque8(byte[] bArr) throws IOException {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, OutputStream outputStream) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (signatureAndHashAlgorithm.getSignature() == 0) {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
            signatureAndHashAlgorithm.encode(outputStream);
        }
    }

    public static byte[] encodeUint16(int i) throws IOException {
        checkUint16(i);
        byte[] bArr = new byte[2];
        writeUint16(i, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) throws IOException {
        byte[] bArr = new byte[(iArr.length * 2) + 2];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint32(long j) throws IOException {
        checkUint32(j);
        byte[] bArr = new byte[4];
        writeUint32(j, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8(short s) throws IOException {
        checkUint8(s);
        byte[] bArr = new byte[1];
        writeUint8(s, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) throws IOException {
        byte[] bArr = new byte[sArr.length + 1];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeVersion(ProtocolVersion protocolVersion) throws IOException {
        return new byte[]{(byte) protocolVersion.getMajorVersion(), (byte) protocolVersion.getMinorVersion()};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner establish13ClientCredentials(TlsAuthentication tlsAuthentication, CertificateRequest certificateRequest) throws IOException {
        return validate13Credentials(tlsAuthentication.getClientCredentials(certificateRequest));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void establish13PhaseApplication(TlsContext tlsContext, byte[] bArr, RecordStream recordStream) throws IOException {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        TlsSecret masterSecret = securityParametersHandshake.getMasterSecret();
        establish13TrafficSecrets(tlsContext, bArr, masterSecret, "c ap traffic", "s ap traffic", recordStream);
        securityParametersHandshake.exporterMasterSecret = deriveSecret(securityParametersHandshake, masterSecret, "exp master", bArr);
    }

    static void establish13PhaseEarly(TlsContext tlsContext, byte[] bArr, RecordStream recordStream) throws IOException {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        TlsSecret earlySecret = securityParametersHandshake.getEarlySecret();
        if (recordStream != null) {
            establish13TrafficSecrets(tlsContext, bArr, earlySecret, "c e traffic", null, recordStream);
        }
        securityParametersHandshake.earlyExporterMasterSecret = deriveSecret(securityParametersHandshake, earlySecret, "e exp master", bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void establish13PhaseHandshake(TlsContext tlsContext, byte[] bArr, RecordStream recordStream) throws IOException {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        establish13TrafficSecrets(tlsContext, bArr, securityParametersHandshake.getHandshakeSecret(), "c hs traffic", "s hs traffic", recordStream);
        securityParametersHandshake.baseKeyClient = securityParametersHandshake.getTrafficSecretClient();
        securityParametersHandshake.baseKeyServer = securityParametersHandshake.getTrafficSecretServer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void establish13PhaseSecrets(TlsContext tlsContext) throws IOException {
        byte[] bArr;
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        short pRFHashAlgorithm = securityParametersHandshake.getPRFHashAlgorithm();
        byte[] bArr2 = new byte[securityParametersHandshake.getPRFHashLength()];
        byte[] psk = securityParametersHandshake.getPSK();
        if (psk == null) {
            psk = bArr2;
        } else {
            securityParametersHandshake.psk = null;
        }
        TlsSecret sharedSecret = securityParametersHandshake.getSharedSecret();
        if (sharedSecret != null) {
            securityParametersHandshake.sharedSecret = null;
            bArr = sharedSecret.extract();
        } else {
            bArr = bArr2;
        }
        TlsCrypto crypto = tlsContext.getCrypto();
        byte[] calculateHash = crypto.createHash(pRFHashAlgorithm).calculateHash();
        TlsSecret hkdfExtract = crypto.hkdfInit(pRFHashAlgorithm).hkdfExtract(pRFHashAlgorithm, psk);
        TlsSecret hkdfExtract2 = deriveSecret(securityParametersHandshake, hkdfExtract, "derived", calculateHash).hkdfExtract(pRFHashAlgorithm, bArr);
        TlsSecret hkdfExtract3 = deriveSecret(securityParametersHandshake, hkdfExtract2, "derived", calculateHash).hkdfExtract(pRFHashAlgorithm, bArr2);
        securityParametersHandshake.earlySecret = hkdfExtract;
        securityParametersHandshake.handshakeSecret = hkdfExtract2;
        securityParametersHandshake.masterSecret = hkdfExtract3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner establish13ServerCredentials(TlsServer tlsServer) throws IOException {
        return validate13Credentials(tlsServer.getCredentials());
    }

    private static void establish13TrafficSecrets(TlsContext tlsContext, byte[] bArr, TlsSecret tlsSecret, String str, String str2, RecordStream recordStream) throws IOException {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        securityParametersHandshake.trafficSecretClient = deriveSecret(securityParametersHandshake, tlsSecret, str, bArr);
        if (str2 != null) {
            securityParametersHandshake.trafficSecretServer = deriveSecret(securityParametersHandshake, tlsSecret, str2, bArr);
        }
        recordStream.setPendingCipher(initCipher(tlsContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentials establishClientCredentials(TlsAuthentication tlsAuthentication, CertificateRequest certificateRequest) throws IOException {
        return validateCredentials(tlsAuthentication.getClientCredentials(certificateRequest));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void establishClientSigAlgs(SecurityParameters securityParameters, Hashtable hashtable) throws IOException {
        securityParameters.clientSigAlgs = TlsExtensionsUtils.getSignatureAlgorithmsExtension(hashtable);
        securityParameters.clientSigAlgsCert = TlsExtensionsUtils.getSignatureAlgorithmsCertExtension(hashtable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentials establishServerCredentials(TlsServer tlsServer) throws IOException {
        return validateCredentials(tlsServer.getCredentials());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void establishServerSigAlgs(SecurityParameters securityParameters, CertificateRequest certificateRequest) throws IOException {
        securityParameters.clientCertTypes = certificateRequest.getCertificateTypes();
        securityParameters.serverSigAlgs = certificateRequest.getSupportedSignatureAlgorithms();
        securityParameters.serverSigAlgsCert = certificateRequest.getSupportedSignatureAlgorithmsCert();
        if (securityParameters.getServerSigAlgsCert() == null) {
            securityParameters.serverSigAlgsCert = securityParameters.getServerSigAlgs();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DigitallySigned generate13CertificateVerify(TlsContext tlsContext, TlsCredentialedSigner tlsCredentialedSigner, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsCredentialedSigner.getSignatureAndHashAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            return new DigitallySigned(signatureAndHashAlgorithm, generate13CertificateVerify(tlsContext.getCrypto(), tlsCredentialedSigner, tlsContext.isServer() ? "TLS 1.3, server CertificateVerify" : "TLS 1.3, client CertificateVerify", tlsHandshakeHash, signatureAndHashAlgorithm.getHash()));
        }
        throw new TlsFatalAlert((short) 80);
    }

    private static byte[] generate13CertificateVerify(TlsCrypto tlsCrypto, TlsCredentialedSigner tlsCredentialedSigner, String str, TlsHandshakeHash tlsHandshakeHash, short s) throws IOException {
        TlsStreamSigner streamSigner = tlsCredentialedSigner.getStreamSigner();
        byte[] certificateVerifyHeader = getCertificateVerifyHeader(str);
        byte[] currentPRFHash = getCurrentPRFHash(tlsHandshakeHash);
        if (streamSigner != null) {
            OutputStream outputStream = streamSigner.getOutputStream();
            outputStream.write(certificateVerifyHeader, 0, certificateVerifyHeader.length);
            outputStream.write(currentPRFHash, 0, currentPRFHash.length);
            return streamSigner.getSignature();
        }
        TlsHash createHash = tlsCrypto.createHash(s);
        createHash.update(certificateVerifyHeader, 0, certificateVerifyHeader.length);
        createHash.update(currentPRFHash, 0, currentPRFHash.length);
        return tlsCredentialedSigner.generateRawSignature(createHash.calculateHash());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DigitallySigned generateCertificateVerifyClient(TlsClientContext tlsClientContext, TlsCredentialedSigner tlsCredentialedSigner, TlsStreamSigner tlsStreamSigner, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        byte[] generateRawSignature;
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (!isTLSv13(negotiatedVersion)) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = getSignatureAndHashAlgorithm(negotiatedVersion, tlsCredentialedSigner);
            if (tlsStreamSigner != null) {
                tlsHandshakeHash.copyBufferTo(tlsStreamSigner.getOutputStream());
                generateRawSignature = tlsStreamSigner.getSignature();
            } else {
                generateRawSignature = tlsCredentialedSigner.generateRawSignature(signatureAndHashAlgorithm == null ? securityParametersHandshake.getSessionHash() : tlsHandshakeHash.getFinalHash(signatureAndHashAlgorithm.getHash()));
            }
            return new DigitallySigned(signatureAndHashAlgorithm, generateRawSignature);
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void generateServerKeyExchangeSignature(TlsContext tlsContext, TlsCredentialedSigner tlsCredentialedSigner, DigestInputBuffer digestInputBuffer) throws IOException {
        byte[] generateRawSignature;
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = getSignatureAndHashAlgorithm(tlsContext, tlsCredentialedSigner);
        TlsStreamSigner streamSigner = tlsCredentialedSigner.getStreamSigner();
        if (streamSigner != null) {
            sendSignatureInput(tlsContext, digestInputBuffer, streamSigner.getOutputStream());
            generateRawSignature = streamSigner.getSignature();
        } else {
            generateRawSignature = tlsCredentialedSigner.generateRawSignature(calculateSignatureHash(tlsContext, signatureAndHashAlgorithm, digestInputBuffer));
        }
        new DigitallySigned(signatureAndHashAlgorithm, generateRawSignature).encode(digestInputBuffer);
    }

    static SignatureAndHashAlgorithm getCertSigAndHashAlg(TlsCertificate tlsCertificate, TlsCertificate tlsCertificate2) throws IOException {
        String sigAlgOID = tlsCertificate.getSigAlgOID();
        if (sigAlgOID != null) {
            if (!PKCSObjectIdentifiers.id_RSASSA_PSS.getId().equals(sigAlgOID)) {
                return (SignatureAndHashAlgorithm) CERT_SIG_ALG_OIDS.get(sigAlgOID);
            }
            RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(tlsCertificate.getSigAlgParams());
            if (rSASSAPSSparams == null) {
                return null;
            }
            ASN1ObjectIdentifier algorithm = rSASSAPSSparams.getHashAlgorithm().getAlgorithm();
            if (NISTObjectIdentifiers.id_sha256.equals((ASN1Primitive) algorithm)) {
                if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 9)) {
                    return SignatureAndHashAlgorithm.rsa_pss_pss_sha256;
                }
                if (!tlsCertificate2.supportsSignatureAlgorithmCA((short) 4)) {
                    return null;
                }
                return SignatureAndHashAlgorithm.rsa_pss_rsae_sha256;
            } else if (NISTObjectIdentifiers.id_sha384.equals((ASN1Primitive) algorithm)) {
                if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 10)) {
                    return SignatureAndHashAlgorithm.rsa_pss_pss_sha384;
                }
                if (!tlsCertificate2.supportsSignatureAlgorithmCA((short) 5)) {
                    return null;
                }
                return SignatureAndHashAlgorithm.rsa_pss_rsae_sha384;
            } else if (!NISTObjectIdentifiers.id_sha512.equals((ASN1Primitive) algorithm)) {
                return null;
            } else {
                if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 11)) {
                    return SignatureAndHashAlgorithm.rsa_pss_pss_sha512;
                }
                if (!tlsCertificate2.supportsSignatureAlgorithmCA((short) 6)) {
                    return null;
                }
                return SignatureAndHashAlgorithm.rsa_pss_rsae_sha512;
            }
        }
        return null;
    }

    private static byte[] getCertificateVerifyHeader(String str) {
        int length = str.length();
        int i = length + 64;
        byte[] bArr = new byte[i + 1];
        for (int i2 = 0; i2 < 64; i2++) {
            bArr[i2] = 32;
        }
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3 + 64] = (byte) str.charAt(i3);
        }
        bArr[i] = 0;
        return bArr;
    }

    public static int getCipherType(int i) {
        return getEncryptionAlgorithmType(getEncryptionAlgorithm(i));
    }

    public static int[] getCommonCipherSuites(int[] iArr, int[] iArr2, boolean z) {
        if (z) {
            iArr2 = iArr;
            iArr = iArr2;
        }
        int min = Math.min(iArr.length, iArr2.length);
        int[] iArr3 = new int[min];
        int i = 0;
        for (int i2 : iArr) {
            if (!contains(iArr3, 0, i, i2) && Arrays.contains(iArr2, i2)) {
                iArr3[i] = i2;
                i++;
            }
        }
        return i < min ? Arrays.copyOf(iArr3, i) : iArr3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getCurrentPRFHash(TlsHandshakeHash tlsHandshakeHash) {
        return tlsHandshakeHash.forkPRFHash().calculateHash();
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return getDefaultSignatureAlgorithms((short) 2);
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return getDefaultSignatureAlgorithms((short) 3);
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return getDefaultSignatureAlgorithms((short) 1);
    }

    public static SignatureAndHashAlgorithm getDefaultSignatureAlgorithm(short s) {
        if (s == 1 || s == 2 || s == 3) {
            return SignatureAndHashAlgorithm.getInstance((short) 2, s);
        }
        return null;
    }

    public static Vector getDefaultSignatureAlgorithms(short s) {
        SignatureAndHashAlgorithm defaultSignatureAlgorithm = getDefaultSignatureAlgorithm(s);
        return defaultSignatureAlgorithm == null ? new Vector() : vectorOfOne(defaultSignatureAlgorithm);
    }

    public static Vector getDefaultSupportedSignatureAlgorithms(TlsContext tlsContext) {
        TlsCrypto crypto = tlsContext.getCrypto();
        int size = DEFAULT_SUPPORTED_SIG_ALGS.size();
        Vector vector = new Vector(size);
        for (int i = 0; i < size; i++) {
            addIfSupported(vector, crypto, (SignatureAndHashAlgorithm) DEFAULT_SUPPORTED_SIG_ALGS.elementAt(i));
        }
        return vector;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x005a A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getEncryptionAlgorithm(int r1) {
        /*
            r0 = 0
            switch(r1) {
                case 2: goto L5a;
                case 10: goto L58;
                case 13: goto L58;
                case 16: goto L58;
                case 19: goto L58;
                case 22: goto L58;
                case 27: goto L58;
                case 147: goto L58;
                case 148: goto L55;
                case 149: goto L52;
                case 150: goto L4f;
                case 151: goto L4f;
                case 152: goto L4f;
                case 153: goto L4f;
                case 154: goto L4f;
                case 155: goto L4f;
                case 156: goto L4c;
                case 157: goto L49;
                case 158: goto L4c;
                case 159: goto L49;
                case 160: goto L4c;
                case 161: goto L49;
                case 162: goto L4c;
                case 163: goto L49;
                case 164: goto L4c;
                case 165: goto L49;
                case 166: goto L4c;
                case 167: goto L49;
                case 168: goto L4c;
                case 169: goto L49;
                case 170: goto L4c;
                case 171: goto L49;
                case 172: goto L4c;
                case 173: goto L49;
                case 174: goto L55;
                case 175: goto L52;
                case 176: goto L48;
                case 177: goto L48;
                case 178: goto L55;
                case 179: goto L52;
                case 180: goto L48;
                case 181: goto L48;
                case 182: goto L55;
                case 183: goto L52;
                case 184: goto L48;
                case 185: goto L48;
                case 186: goto L45;
                case 187: goto L45;
                case 188: goto L45;
                case 189: goto L45;
                case 190: goto L45;
                case 191: goto L45;
                case 192: goto L42;
                case 193: goto L42;
                case 194: goto L42;
                case 195: goto L42;
                case 196: goto L42;
                case 197: goto L42;
                case 49153: goto L5a;
                case 49170: goto L58;
                case 49171: goto L55;
                case 49172: goto L52;
                case 49173: goto L5a;
                case 49175: goto L58;
                case 49176: goto L55;
                case 49177: goto L52;
                case 49178: goto L58;
                case 49179: goto L58;
                case 49180: goto L58;
                case 49181: goto L55;
                case 49182: goto L55;
                case 49183: goto L55;
                case 49184: goto L52;
                case 49185: goto L52;
                case 49186: goto L52;
                case 49187: goto L55;
                case 49188: goto L52;
                case 49189: goto L55;
                case 49190: goto L52;
                case 49191: goto L55;
                case 49192: goto L52;
                case 49193: goto L55;
                case 49194: goto L52;
                case 49195: goto L4c;
                case 49196: goto L49;
                case 49197: goto L4c;
                case 49198: goto L49;
                case 49199: goto L4c;
                case 49200: goto L49;
                case 49201: goto L4c;
                case 49202: goto L49;
                case 49204: goto L58;
                case 49205: goto L55;
                case 49206: goto L52;
                case 49207: goto L55;
                case 49208: goto L52;
                case 49209: goto L5a;
                case 49210: goto L48;
                case 49211: goto L48;
                case 49212: goto L3f;
                case 49213: goto L3c;
                case 49214: goto L3f;
                case 49215: goto L3c;
                case 49216: goto L3f;
                case 49217: goto L3c;
                case 49218: goto L3f;
                case 49219: goto L3c;
                case 49220: goto L3f;
                case 49221: goto L3c;
                case 49222: goto L3f;
                case 49223: goto L3c;
                case 49224: goto L3f;
                case 49225: goto L3c;
                case 49226: goto L3f;
                case 49227: goto L3c;
                case 49228: goto L3f;
                case 49229: goto L3c;
                case 49230: goto L3f;
                case 49231: goto L3c;
                case 49232: goto L39;
                case 49233: goto L36;
                case 49234: goto L39;
                case 49235: goto L36;
                case 49236: goto L39;
                case 49237: goto L36;
                case 49238: goto L39;
                case 49239: goto L36;
                case 49240: goto L39;
                case 49241: goto L36;
                case 49242: goto L39;
                case 49243: goto L36;
                case 49244: goto L39;
                case 49245: goto L36;
                case 49246: goto L39;
                case 49247: goto L36;
                case 49248: goto L39;
                case 49249: goto L36;
                case 49250: goto L39;
                case 49251: goto L36;
                case 49252: goto L3f;
                case 49253: goto L3c;
                case 49254: goto L3f;
                case 49255: goto L3c;
                case 49256: goto L3f;
                case 49257: goto L3c;
                case 49258: goto L39;
                case 49259: goto L36;
                case 49260: goto L39;
                case 49261: goto L36;
                case 49262: goto L39;
                case 49263: goto L36;
                case 49264: goto L3f;
                case 49265: goto L3c;
                case 49266: goto L45;
                case 49267: goto L42;
                case 49268: goto L45;
                case 49269: goto L42;
                case 49270: goto L45;
                case 49271: goto L42;
                case 49272: goto L45;
                case 49273: goto L42;
                case 49274: goto L33;
                case 49275: goto L30;
                case 49276: goto L33;
                case 49277: goto L30;
                case 49278: goto L33;
                case 49279: goto L30;
                case 49280: goto L33;
                case 49281: goto L30;
                case 49282: goto L33;
                case 49283: goto L30;
                case 49284: goto L33;
                case 49285: goto L30;
                case 49286: goto L33;
                case 49287: goto L30;
                case 49288: goto L33;
                case 49289: goto L30;
                case 49290: goto L33;
                case 49291: goto L30;
                case 49292: goto L33;
                case 49293: goto L30;
                case 49294: goto L33;
                case 49295: goto L30;
                case 49296: goto L33;
                case 49297: goto L30;
                case 49298: goto L33;
                case 49299: goto L30;
                case 49300: goto L45;
                case 49301: goto L42;
                case 49302: goto L45;
                case 49303: goto L42;
                case 49304: goto L45;
                case 49305: goto L42;
                case 49306: goto L45;
                case 49307: goto L42;
                case 49308: goto L2d;
                case 49309: goto L2a;
                case 49310: goto L2d;
                case 49311: goto L2a;
                case 49312: goto L27;
                case 49313: goto L24;
                case 49314: goto L27;
                case 49315: goto L24;
                case 49316: goto L2d;
                case 49317: goto L2a;
                case 49318: goto L2d;
                case 49319: goto L2a;
                case 49320: goto L27;
                case 49321: goto L24;
                case 49322: goto L27;
                case 49323: goto L24;
                case 49324: goto L2d;
                case 49325: goto L2a;
                case 49326: goto L27;
                case 49327: goto L24;
                case 52392: goto L21;
                case 52393: goto L21;
                case 52394: goto L21;
                case 52395: goto L21;
                case 52396: goto L21;
                case 52397: goto L21;
                case 52398: goto L21;
                case 53249: goto L4c;
                case 53250: goto L49;
                case 53251: goto L27;
                case 53253: goto L2d;
                default: goto L4;
            }
        L4:
            switch(r1) {
                case 44: goto L5a;
                case 45: goto L5a;
                case 46: goto L5a;
                case 47: goto L55;
                case 48: goto L55;
                case 49: goto L55;
                case 50: goto L55;
                case 51: goto L55;
                case 52: goto L55;
                case 53: goto L52;
                case 54: goto L52;
                case 55: goto L52;
                case 56: goto L52;
                case 57: goto L52;
                case 58: goto L52;
                case 59: goto L48;
                case 60: goto L55;
                case 61: goto L52;
                case 62: goto L55;
                case 63: goto L55;
                case 64: goto L55;
                case 65: goto L45;
                case 66: goto L45;
                case 67: goto L45;
                case 68: goto L45;
                case 69: goto L45;
                case 70: goto L45;
                default: goto L7;
            }
        L7:
            switch(r1) {
                case 103: goto L55;
                case 104: goto L52;
                case 105: goto L52;
                case 106: goto L52;
                case 107: goto L52;
                case 108: goto L55;
                case 109: goto L52;
                default: goto La;
            }
        La:
            switch(r1) {
                case 132: goto L42;
                case 133: goto L42;
                case 134: goto L42;
                case 135: goto L42;
                case 136: goto L42;
                case 137: goto L42;
                default: goto Ld;
            }
        Ld:
            switch(r1) {
                case 139: goto L58;
                case 140: goto L55;
                case 141: goto L52;
                default: goto L10;
            }
        L10:
            switch(r1) {
                case 143: goto L58;
                case 144: goto L55;
                case 145: goto L52;
                default: goto L13;
            }
        L13:
            switch(r1) {
                case 4865: goto L4c;
                case 4866: goto L49;
                case 4867: goto L21;
                case 4868: goto L2d;
                case 4869: goto L27;
                default: goto L16;
            }
        L16:
            switch(r1) {
                case 49155: goto L58;
                case 49156: goto L55;
                case 49157: goto L52;
                case 49158: goto L5a;
                default: goto L19;
            }
        L19:
            switch(r1) {
                case 49160: goto L58;
                case 49161: goto L55;
                case 49162: goto L52;
                case 49163: goto L5a;
                default: goto L1c;
            }
        L1c:
            switch(r1) {
                case 49165: goto L58;
                case 49166: goto L55;
                case 49167: goto L52;
                case 49168: goto L5a;
                default: goto L1f;
            }
        L1f:
            r1 = -1
            return r1
        L21:
            r1 = 21
            return r1
        L24:
            r1 = 18
            return r1
        L27:
            r1 = 16
            return r1
        L2a:
            r1 = 17
            return r1
        L2d:
            r1 = 15
            return r1
        L30:
            r1 = 20
            return r1
        L33:
            r1 = 19
            return r1
        L36:
            r1 = 25
            return r1
        L39:
            r1 = 24
            return r1
        L3c:
            r1 = 23
            return r1
        L3f:
            r1 = 22
            return r1
        L42:
            r1 = 13
            return r1
        L45:
            r1 = 12
            return r1
        L48:
            return r0
        L49:
            r1 = 11
            return r1
        L4c:
            r1 = 10
            return r1
        L4f:
            r1 = 14
            return r1
        L52:
            r1 = 9
            return r1
        L55:
            r1 = 8
            return r1
        L58:
            r1 = 7
            return r1
        L5a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsUtils.getEncryptionAlgorithm(int):int");
    }

    public static int getEncryptionAlgorithmType(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
            case 22:
            case 23:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 24:
            case 25:
                return 2;
            default:
                return -1;
        }
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static short getHashAlgorithmForHMACAlgorithm(int i) {
        short s = 1;
        if (i != 1) {
            s = 2;
            if (i != 2) {
                if (i == 3) {
                    return (short) 4;
                }
                if (i == 4) {
                    return (short) 5;
                }
                if (i == 5) {
                    return (short) 6;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("specified MACAlgorithm not an HMAC: ");
                outline107.append(MACAlgorithm.getText(i));
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        return s;
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i) {
        if (i == 0 || i == 1) {
            throw new IllegalArgumentException("legacy PRF not a valid algorithm");
        }
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown PRFAlgorithm: ");
                        outline107.append(PRFAlgorithm.getText(i));
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }
            }
            return (short) 5;
        }
        return (short) 4;
    }

    public static int getKeyExchangeAlgorithm(int i) {
        switch (i) {
            case 2:
            case 10:
            case 150:
            case 156:
            case 157:
            case 186:
            case 192:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                return 1;
            case 13:
            case 151:
            case 164:
            case 165:
            case 187:
            case 193:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256 /* 49214 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384 /* 49215 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256 /* 49240 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384 /* 49241 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                return 7;
            case 16:
            case 152:
            case 160:
            case 161:
            case 188:
            case 194:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49216 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49217 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49236 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49237 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                return 9;
            case 19:
            case 153:
            case 162:
            case 163:
            case 189:
            case 195:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                return 3;
            case 22:
            case 154:
            case 158:
            case 159:
            case 190:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                return 5;
            case 27:
            case 155:
            case 166:
            case 167:
            case 191:
            case 197:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_CBC_SHA256 /* 49222 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384 /* 49223 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_GCM_SHA256 /* 49242 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_GCM_SHA384 /* 49243 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                return 11;
            case 147:
            case 148:
            case 149:
            case 172:
            case 173:
            case 182:
            case 183:
            case 184:
            case 185:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256 /* 49256 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384 /* 49257 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256 /* 49262 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384 /* 49263 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                return 15;
            case 168:
            case 169:
            case 174:
            case 175:
            case 176:
            case 177:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_CBC_SHA256 /* 49252 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_CBC_SHA384 /* 49253 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_GCM_SHA256 /* 49258 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_GCM_SHA384 /* 49259 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
            case CipherSuite.TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                return 13;
            case 170:
            case 171:
            case 178:
            case 179:
            case 180:
            case 181:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49254 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49255 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256 /* 49260 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384 /* 49261 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                return 14;
            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49226 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49227 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49246 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49247 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                return 16;
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                return 19;
            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
                return 20;
            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
                return 21;
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
                return 23;
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
                return 22;
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                return 17;
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49230 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49231 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49250 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49251 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                return 18;
            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49264 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49265 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256 /* 53249 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA384 /* 53250 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256 /* 53251 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256 /* 53253 */:
                return 24;
            default:
                switch (i) {
                    case 44:
                        return 13;
                    case 45:
                        return 14;
                    case 46:
                        return 15;
                    case 47:
                    case 53:
                    case 59:
                    case 60:
                    case 61:
                    case 65:
                        return 1;
                    case 48:
                    case 54:
                    case 62:
                    case 66:
                        return 7;
                    case 49:
                    case 55:
                    case 63:
                    case 67:
                        return 9;
                    case 50:
                    case 56:
                    case 64:
                    case 68:
                        return 3;
                    case 51:
                    case 57:
                    case 69:
                        return 5;
                    case 52:
                    case 58:
                    case 70:
                        return 11;
                    default:
                        switch (i) {
                            case 103:
                            case 107:
                                return 5;
                            case 104:
                                return 7;
                            case 105:
                                return 9;
                            case 106:
                                return 3;
                            case 108:
                            case 109:
                                return 11;
                            default:
                                switch (i) {
                                    case 132:
                                        return 1;
                                    case 133:
                                        return 7;
                                    case 134:
                                        return 9;
                                    case 135:
                                        return 3;
                                    case 136:
                                        return 5;
                                    case 137:
                                        return 11;
                                    default:
                                        switch (i) {
                                            case 139:
                                            case 140:
                                            case 141:
                                                return 13;
                                            default:
                                                switch (i) {
                                                    case 143:
                                                    case 144:
                                                    case 145:
                                                        return 14;
                                                    default:
                                                        switch (i) {
                                                            case 4865:
                                                            case 4866:
                                                            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
                                                            case 4868:
                                                            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                                                                return 0;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                                                        return 16;
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                                                        return 17;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                                                                return 17;
                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                                                                return 18;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                                                                        return 18;
                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                                                                        return 19;
                                                                                    default:
                                                                                        return -1;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static Vector getKeyExchangeAlgorithms(int[] iArr) {
        Vector vector = new Vector();
        if (iArr != null) {
            for (int i : iArr) {
                addToSet(vector, getKeyExchangeAlgorithm(i));
            }
            vector.removeElement(Integers.valueOf(-1));
        }
        return vector;
    }

    public static short getLegacyClientCertType(short s) {
        short s2 = 1;
        if (s != 1) {
            s2 = 2;
            if (s != 2) {
                return s != 3 ? (short) -1 : (short) 64;
            }
        }
        return s2;
    }

    public static short getLegacySignatureAlgorithmClient(short s) {
        short s2 = 1;
        if (s != 1) {
            s2 = 2;
            if (s != 2) {
                return s != 64 ? (short) -1 : (short) 3;
            }
        }
        return s2;
    }

    public static short getLegacySignatureAlgorithmClientCert(short s) {
        if (s != 1) {
            if (s != 2) {
                if (s != 3) {
                    if (s != 4) {
                        switch (s) {
                            case 64:
                            case 66:
                                return (short) 3;
                            case 65:
                                break;
                            default:
                                return (short) -1;
                        }
                    }
                }
            }
            return (short) 2;
        }
        return (short) 1;
    }

    public static short getLegacySignatureAlgorithmServer(int i) {
        if (i != 3) {
            if (i == 5) {
                return (short) 1;
            }
            if (i == 17) {
                return (short) 3;
            }
            if (i == 19) {
                return (short) 1;
            }
            if (i == 22) {
                return (short) 2;
            }
            return i != 23 ? (short) -1 : (short) 1;
        }
        return (short) 2;
    }

    public static short getLegacySignatureAlgorithmServerCert(int i) {
        if (i != 1) {
            if (i == 3) {
                return (short) 2;
            }
            if (i != 5) {
                if (i == 7) {
                    return (short) 2;
                }
                if (i != 9) {
                    if (i == 22) {
                        return (short) 2;
                    }
                    if (i != 23) {
                        switch (i) {
                            case 15:
                            case 18:
                            case 19:
                                break;
                            case 16:
                            case 17:
                                return (short) 3;
                            default:
                                return (short) -1;
                        }
                    }
                }
            }
        }
        return (short) 1;
    }

    public static Vector getLegacySupportedSignatureAlgorithms() {
        Vector vector = new Vector(3);
        vector.add(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 2));
        vector.add(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 3));
        vector.add(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 1));
        return vector;
    }

    public static int getMACAlgorithm(int i) {
        switch (i) {
            case 2:
            case 10:
            case 13:
            case 16:
            case 19:
            case 22:
            case 27:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
                return 2;
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49236 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49237 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256 /* 49240 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384 /* 49241 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_GCM_SHA256 /* 49242 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_GCM_SHA384 /* 49243 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49246 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49247 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49250 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49251 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_GCM_SHA256 /* 49258 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_GCM_SHA384 /* 49259 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256 /* 49260 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384 /* 49261 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256 /* 49262 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384 /* 49263 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
            case CipherSuite.TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256 /* 53249 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA384 /* 53250 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256 /* 53251 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256 /* 53253 */:
                return 0;
            case 174:
            case 176:
            case 178:
            case 180:
            case 182:
            case 184:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
            case 197:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256 /* 49214 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49216 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_CBC_SHA256 /* 49222 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49226 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49230 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_CBC_SHA256 /* 49252 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49254 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256 /* 49256 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49264 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
                return 3;
            case 175:
            case 177:
            case 179:
            case 181:
            case 183:
            case 185:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384 /* 49215 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49217 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384 /* 49223 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49227 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49231 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_CBC_SHA384 /* 49253 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49255 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384 /* 49257 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49265 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                return 4;
            default:
                switch (i) {
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                        return 2;
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                        return 3;
                    default:
                        switch (i) {
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                                return 3;
                            default:
                                switch (i) {
                                    case 132:
                                    case 133:
                                    case 134:
                                    case 135:
                                    case 136:
                                    case 137:
                                        return 2;
                                    default:
                                        switch (i) {
                                            case 139:
                                            case 140:
                                            case 141:
                                                return 2;
                                            default:
                                                switch (i) {
                                                    case 143:
                                                    case 144:
                                                    case 145:
                                                        return 2;
                                                    default:
                                                        switch (i) {
                                                            case 4865:
                                                            case 4866:
                                                            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
                                                            case 4868:
                                                            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                                                                return 0;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                                                        return 2;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                                                                return 2;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                                                                        return 2;
                                                                                    default:
                                                                                        return -1;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static ProtocolVersion getMinimumVersion(int i) {
        switch (i) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                break;
            default:
                switch (i) {
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                        break;
                    default:
                        switch (i) {
                            case 156:
                            case 157:
                            case 158:
                            case 159:
                            case 160:
                            case 161:
                            case 162:
                            case 163:
                            case 164:
                            case 165:
                            case 166:
                            case 167:
                            case 168:
                            case 169:
                            case 170:
                            case 171:
                            case 172:
                            case 173:
                                break;
                            default:
                                switch (i) {
                                    case 186:
                                    case 187:
                                    case 188:
                                    case 189:
                                    case 190:
                                    case 191:
                                    case 192:
                                    case 193:
                                    case 194:
                                    case 195:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                                    case 197:
                                        break;
                                    default:
                                        switch (i) {
                                            case 4865:
                                            case 4866:
                                            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
                                            case 4868:
                                            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                                                return ProtocolVersion.TLSv13;
                                            default:
                                                switch (i) {
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
                                                            case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256 /* 49214 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384 /* 49215 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49216 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49217 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_CBC_SHA256 /* 49222 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384 /* 49223 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49226 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49227 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49230 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49231 */:
                                                            case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
                                                            case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49236 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49237 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256 /* 49240 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384 /* 49241 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_GCM_SHA256 /* 49242 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_GCM_SHA384 /* 49243 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49246 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49247 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49250 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49251 */:
                                                            case CipherSuite.TLS_PSK_WITH_ARIA_128_CBC_SHA256 /* 49252 */:
                                                            case CipherSuite.TLS_PSK_WITH_ARIA_256_CBC_SHA384 /* 49253 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49254 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49255 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256 /* 49256 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384 /* 49257 */:
                                                            case CipherSuite.TLS_PSK_WITH_ARIA_128_GCM_SHA256 /* 49258 */:
                                                            case CipherSuite.TLS_PSK_WITH_ARIA_256_GCM_SHA384 /* 49259 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256 /* 49260 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384 /* 49261 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256 /* 49262 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384 /* 49263 */:
                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49264 */:
                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49265 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256 /* 53253 */:
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                                            case CipherSuite.TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                                            case CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                                            case CipherSuite.TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256 /* 53249 */:
                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA384 /* 53250 */:
                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256 /* 53251 */:
                                                                                        break;
                                                                                    default:
                                                                                        return ProtocolVersion.SSLv3;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        return ProtocolVersion.TLSv12;
    }

    public static Vector getNamedGroupRoles(Vector vector) {
        Vector vector2 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            int intValue = ((Integer) vector.elementAt(i)).intValue();
            if (intValue != 0) {
                if (intValue != 3 && intValue != 5 && intValue != 7 && intValue != 9 && intValue != 11 && intValue != 14) {
                    if (intValue != 24) {
                        switch (intValue) {
                            case 16:
                            case 17:
                                addToSet(vector2, 2);
                                addToSet(vector2, 3);
                                break;
                        }
                    }
                } else {
                    addToSet(vector2, 1);
                }
            } else {
                addToSet(vector2, 1);
            }
            addToSet(vector2, 2);
        }
        return vector2;
    }

    public static Vector getNamedGroupRoles(int[] iArr) {
        return getNamedGroupRoles(getKeyExchangeAlgorithms(iArr));
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s) {
        switch (s) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid HashAlgorithm: ");
                outline107.append(HashAlgorithm.getText(s));
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static int getPRFAlgorithm(org.bouncycastle.tls.SecurityParameters r7, int r8) throws java.io.IOException {
        /*
            org.bouncycastle.tls.ProtocolVersion r7 = r7.getNegotiatedVersion()
            boolean r0 = isTLSv13(r7)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L14
            boolean r3 = isTLSv12(r7)
            if (r3 == 0) goto L14
            r3 = r1
            goto L15
        L14:
            r3 = r2
        L15:
            boolean r7 = r7.isSSL()
            r4 = 2
            r5 = 47
            switch(r8) {
                case 59: goto L76;
                case 60: goto L76;
                case 61: goto L76;
                case 62: goto L76;
                case 63: goto L76;
                case 64: goto L76;
                default: goto L1f;
            }
        L1f:
            switch(r8) {
                case 103: goto L76;
                case 104: goto L76;
                case 105: goto L76;
                case 106: goto L76;
                case 107: goto L76;
                case 108: goto L76;
                case 109: goto L76;
                default: goto L22;
            }
        L22:
            r6 = 3
            switch(r8) {
                case 156: goto L76;
                case 157: goto L6d;
                case 158: goto L76;
                case 159: goto L6d;
                case 160: goto L76;
                case 161: goto L6d;
                case 162: goto L76;
                case 163: goto L6d;
                case 164: goto L76;
                case 165: goto L6d;
                case 166: goto L76;
                case 167: goto L6d;
                case 168: goto L76;
                case 169: goto L6d;
                case 170: goto L76;
                case 171: goto L6d;
                case 172: goto L76;
                case 173: goto L6d;
                default: goto L26;
            }
        L26:
            switch(r8) {
                case 175: goto L5e;
                case 177: goto L5e;
                case 179: goto L5e;
                case 181: goto L5e;
                case 183: goto L5e;
                case 49208: goto L5e;
                case 49211: goto L5e;
                case 49212: goto L76;
                case 49213: goto L6d;
                case 49214: goto L76;
                case 49215: goto L6d;
                case 49216: goto L76;
                case 49217: goto L6d;
                case 49218: goto L76;
                case 49219: goto L6d;
                case 49220: goto L76;
                case 49221: goto L6d;
                case 49222: goto L76;
                case 49223: goto L6d;
                case 49224: goto L76;
                case 49225: goto L6d;
                case 49226: goto L76;
                case 49227: goto L6d;
                case 49228: goto L76;
                case 49229: goto L6d;
                case 49230: goto L76;
                case 49231: goto L6d;
                case 49232: goto L76;
                case 49233: goto L6d;
                case 49234: goto L76;
                case 49235: goto L6d;
                case 49236: goto L76;
                case 49237: goto L6d;
                case 49238: goto L76;
                case 49239: goto L6d;
                case 49240: goto L76;
                case 49241: goto L6d;
                case 49242: goto L76;
                case 49243: goto L6d;
                case 49244: goto L76;
                case 49245: goto L6d;
                case 49246: goto L76;
                case 49247: goto L6d;
                case 49248: goto L76;
                case 49249: goto L6d;
                case 49250: goto L76;
                case 49251: goto L6d;
                case 49252: goto L76;
                case 49253: goto L6d;
                case 49254: goto L76;
                case 49255: goto L6d;
                case 49256: goto L76;
                case 49257: goto L6d;
                case 49258: goto L76;
                case 49259: goto L6d;
                case 49260: goto L76;
                case 49261: goto L6d;
                case 49262: goto L76;
                case 49263: goto L6d;
                case 49264: goto L76;
                case 49265: goto L6d;
                case 49266: goto L76;
                case 49267: goto L6d;
                case 49268: goto L76;
                case 49269: goto L6d;
                case 49270: goto L76;
                case 49271: goto L6d;
                case 49272: goto L76;
                case 49273: goto L6d;
                case 49274: goto L76;
                case 49275: goto L6d;
                case 49276: goto L76;
                case 49277: goto L6d;
                case 49278: goto L76;
                case 49279: goto L6d;
                case 49280: goto L76;
                case 49281: goto L6d;
                case 49282: goto L76;
                case 49283: goto L6d;
                case 49284: goto L76;
                case 49285: goto L6d;
                case 49286: goto L76;
                case 49287: goto L6d;
                case 49288: goto L76;
                case 49289: goto L6d;
                case 49290: goto L76;
                case 49291: goto L6d;
                case 49292: goto L76;
                case 49293: goto L6d;
                case 49294: goto L76;
                case 49295: goto L6d;
                case 49296: goto L76;
                case 49297: goto L6d;
                case 49298: goto L76;
                case 49299: goto L6d;
                case 49301: goto L5e;
                case 49303: goto L5e;
                case 49305: goto L5e;
                case 53253: goto L76;
                default: goto L29;
            }
        L29:
            switch(r8) {
                case 185: goto L5e;
                case 186: goto L76;
                case 187: goto L76;
                case 188: goto L76;
                case 189: goto L76;
                case 190: goto L76;
                case 191: goto L76;
                case 192: goto L76;
                case 193: goto L76;
                case 194: goto L76;
                case 195: goto L76;
                case 196: goto L76;
                case 197: goto L76;
                default: goto L2c;
            }
        L2c:
            switch(r8) {
                case 4865: goto L54;
                case 4866: goto L4a;
                case 4867: goto L54;
                case 4868: goto L54;
                case 4869: goto L54;
                default: goto L2f;
            }
        L2f:
            switch(r8) {
                case 49187: goto L76;
                case 49188: goto L6d;
                case 49189: goto L76;
                case 49190: goto L6d;
                case 49191: goto L76;
                case 49192: goto L6d;
                case 49193: goto L76;
                case 49194: goto L6d;
                case 49195: goto L76;
                case 49196: goto L6d;
                case 49197: goto L76;
                case 49198: goto L6d;
                case 49199: goto L76;
                case 49200: goto L6d;
                case 49201: goto L76;
                case 49202: goto L6d;
                default: goto L32;
            }
        L32:
            switch(r8) {
                case 49307: goto L5e;
                case 49308: goto L76;
                case 49309: goto L76;
                case 49310: goto L76;
                case 49311: goto L76;
                case 49312: goto L76;
                case 49313: goto L76;
                case 49314: goto L76;
                case 49315: goto L76;
                case 49316: goto L76;
                case 49317: goto L76;
                case 49318: goto L76;
                case 49319: goto L76;
                case 49320: goto L76;
                case 49321: goto L76;
                case 49322: goto L76;
                case 49323: goto L76;
                case 49324: goto L76;
                case 49325: goto L76;
                case 49326: goto L76;
                case 49327: goto L76;
                default: goto L35;
            }
        L35:
            switch(r8) {
                case 52392: goto L76;
                case 52393: goto L76;
                case 52394: goto L76;
                case 52395: goto L76;
                case 52396: goto L76;
                case 52397: goto L76;
                case 52398: goto L76;
                default: goto L38;
            }
        L38:
            switch(r8) {
                case 53249: goto L76;
                case 53250: goto L6d;
                case 53251: goto L76;
                default: goto L3b;
            }
        L3b:
            if (r0 != 0) goto L44
            if (r3 == 0) goto L40
            return r4
        L40:
            if (r7 == 0) goto L43
            return r2
        L43:
            return r1
        L44:
            org.bouncycastle.tls.TlsFatalAlert r7 = new org.bouncycastle.tls.TlsFatalAlert
            r7.<init>(r5)
            throw r7
        L4a:
            if (r0 == 0) goto L4e
            r7 = 5
            return r7
        L4e:
            org.bouncycastle.tls.TlsFatalAlert r7 = new org.bouncycastle.tls.TlsFatalAlert
            r7.<init>(r5)
            throw r7
        L54:
            if (r0 == 0) goto L58
            r7 = 4
            return r7
        L58:
            org.bouncycastle.tls.TlsFatalAlert r7 = new org.bouncycastle.tls.TlsFatalAlert
            r7.<init>(r5)
            throw r7
        L5e:
            if (r0 != 0) goto L67
            if (r3 == 0) goto L63
            return r6
        L63:
            if (r7 == 0) goto L66
            return r2
        L66:
            return r1
        L67:
            org.bouncycastle.tls.TlsFatalAlert r7 = new org.bouncycastle.tls.TlsFatalAlert
            r7.<init>(r5)
            throw r7
        L6d:
            if (r3 == 0) goto L70
            return r6
        L70:
            org.bouncycastle.tls.TlsFatalAlert r7 = new org.bouncycastle.tls.TlsFatalAlert
            r7.<init>(r5)
            throw r7
        L76:
            if (r3 == 0) goto L79
            return r4
        L79:
            org.bouncycastle.tls.TlsFatalAlert r7 = new org.bouncycastle.tls.TlsFatalAlert
            r7.<init>(r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsUtils.getPRFAlgorithm(org.bouncycastle.tls.SecurityParameters, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getSessionID(TlsSession tlsSession) {
        byte[] sessionID;
        return (tlsSession == null || (sessionID = tlsSession.getSessionID()) == null || sessionID.length <= 0 || sessionID.length > 32) ? EMPTY_BYTES : sessionID;
    }

    static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(ProtocolVersion protocolVersion, TlsCredentialedSigner tlsCredentialedSigner) throws IOException {
        if (isTLSv12(protocolVersion)) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsCredentialedSigner.getSignatureAndHashAlgorithm();
            if (signatureAndHashAlgorithm == null) {
                throw new TlsFatalAlert((short) 80);
            }
            return signatureAndHashAlgorithm;
        }
        return null;
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsCredentialedSigner tlsCredentialedSigner) throws IOException {
        return getSignatureAndHashAlgorithm(tlsContext.getServerVersion(), tlsCredentialedSigner);
    }

    public static int[] getSupportedCipherSuites(TlsCrypto tlsCrypto, int[] iArr) {
        return getSupportedCipherSuites(tlsCrypto, iArr, iArr.length);
    }

    public static int[] getSupportedCipherSuites(TlsCrypto tlsCrypto, int[] iArr, int i) {
        int[] iArr2 = new int[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            if (isSupportedCipherSuite(tlsCrypto, i4)) {
                iArr2[i2] = i4;
                i2++;
            }
        }
        return i2 < i ? Arrays.copyOf(iArr2, i2) : iArr2;
    }

    public static Vector getUsableSignatureAlgorithms(Vector vector) {
        if (vector == null) {
            Vector vector2 = new Vector(3);
            vector2.addElement(Shorts.valueOf((short) 1));
            vector2.addElement(Shorts.valueOf((short) 2));
            vector2.addElement(Shorts.valueOf((short) 3));
            return vector2;
        }
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (signatureAndHashAlgorithm.getHash() >= MINIMUM_HASH_STRICT) {
                Short valueOf = Shorts.valueOf(signatureAndHashAlgorithm.getSignature());
                if (!vector3.contains(valueOf)) {
                    vector3.addElement(valueOf);
                }
            }
        }
        return vector3;
    }

    static boolean hasAnyRSASigAlgs(TlsCrypto tlsCrypto) {
        return tlsCrypto.hasSignatureAlgorithm((short) 1) || tlsCrypto.hasSignatureAlgorithm((short) 4) || tlsCrypto.hasSignatureAlgorithm((short) 5) || tlsCrypto.hasSignatureAlgorithm((short) 6) || tlsCrypto.hasSignatureAlgorithm((short) 9) || tlsCrypto.hasSignatureAlgorithm((short) 10) || tlsCrypto.hasSignatureAlgorithm((short) 11);
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length != 0) {
            throw new TlsFatalAlert(s);
        }
        return true;
    }

    public static boolean hasSigningCapability(short s) {
        return s == 1 || s == 2 || s == 64;
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new TlsSessionImpl(bArr, sessionParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCipher initCipher(TlsContext tlsContext) throws IOException {
        int cipherSuite = tlsContext.getSecurityParametersHandshake().getCipherSuite();
        int encryptionAlgorithm = getEncryptionAlgorithm(cipherSuite);
        int mACAlgorithm = getMACAlgorithm(cipherSuite);
        if (encryptionAlgorithm < 0 || mACAlgorithm < 0) {
            throw new TlsFatalAlert((short) 80);
        }
        return tlsContext.getCrypto().createCipher(new TlsCryptoParameters(tlsContext), encryptionAlgorithm, mACAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsKeyExchange initKeyExchangeClient(TlsClientContext tlsClientContext, TlsClient tlsClient) throws IOException {
        TlsKeyExchange createKeyExchangeClient = createKeyExchangeClient(tlsClient, tlsClientContext.getSecurityParametersHandshake().getKeyExchangeAlgorithm());
        createKeyExchangeClient.init(tlsClientContext);
        return createKeyExchangeClient;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsKeyExchange initKeyExchangeServer(TlsServerContext tlsServerContext, TlsServer tlsServer) throws IOException {
        TlsKeyExchange createKeyExchangeServer = createKeyExchangeServer(tlsServer, tlsServerContext.getSecurityParametersHandshake().getKeyExchangeAlgorithm());
        createKeyExchangeServer.init(tlsServerContext);
        return createKeyExchangeServer;
    }

    public static boolean isAEADCipherSuite(int i) throws IOException {
        return 2 == getCipherType(i);
    }

    public static boolean isBlockCipherSuite(int i) throws IOException {
        return 1 == getCipherType(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isExtendedMasterSecretOptionalDTLS(ProtocolVersion[] protocolVersionArr) {
        return ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.DTLSv12) || ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.DTLSv10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isExtendedMasterSecretOptionalTLS(ProtocolVersion[] protocolVersionArr) {
        return ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.TLSv12) || ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.TLSv11) || ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.TLSv10);
    }

    public static boolean isNullOrContainsNull(Object[] objArr) {
        if (objArr == null) {
            return true;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullOrEmpty(byte[] bArr) {
        return bArr == null || bArr.length < 1;
    }

    public static boolean isNullOrEmpty(int[] iArr) {
        return iArr == null || iArr.length < 1;
    }

    public static boolean isNullOrEmpty(Object[] objArr) {
        return objArr == null || objArr.length < 1;
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return protocolVersion != null && ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isStreamCipherSuite(int i) throws IOException {
        return getCipherType(i) == 0;
    }

    public static boolean isSupportedCipherSuite(TlsCrypto tlsCrypto, int i) {
        return isSupportedKeyExchange(tlsCrypto, getKeyExchangeAlgorithm(i)) && tlsCrypto.hasEncryptionAlgorithm(getEncryptionAlgorithm(i)) && tlsCrypto.hasMacAlgorithm(getMACAlgorithm(i));
    }

    public static boolean isSupportedKeyExchange(TlsCrypto tlsCrypto, int i) {
        if (i != 0) {
            if (i != 1) {
                if (i == 3) {
                    return tlsCrypto.hasDHAgreement() && tlsCrypto.hasSignatureAlgorithm((short) 2);
                } else if (i == 5) {
                    return tlsCrypto.hasDHAgreement() && hasAnyRSASigAlgs(tlsCrypto);
                } else {
                    if (i != 7 && i != 9 && i != 11) {
                        switch (i) {
                            case 13:
                                break;
                            case 14:
                                break;
                            case 15:
                                break;
                            case 16:
                            case 18:
                            case 20:
                            case 24:
                                return tlsCrypto.hasECDHAgreement();
                            case 17:
                                return tlsCrypto.hasECDHAgreement() && (tlsCrypto.hasSignatureAlgorithm((short) 3) || tlsCrypto.hasSignatureAlgorithm((short) 7) || tlsCrypto.hasSignatureAlgorithm((short) 8));
                            case 19:
                                return tlsCrypto.hasECDHAgreement() && hasAnyRSASigAlgs(tlsCrypto);
                            case 21:
                                return tlsCrypto.hasSRPAuthentication();
                            case 22:
                                return tlsCrypto.hasSRPAuthentication() && tlsCrypto.hasSignatureAlgorithm((short) 2);
                            case 23:
                                return tlsCrypto.hasSRPAuthentication() && hasAnyRSASigAlgs(tlsCrypto);
                            default:
                                return false;
                        }
                    }
                    return tlsCrypto.hasDHAgreement();
                }
            }
            return tlsCrypto.hasRSAEncryption();
        }
        return true;
    }

    public static boolean isTLSv10(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv10.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv10(TlsContext tlsContext) {
        return isTLSv10(tlsContext.getServerVersion());
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static boolean isTLSv13(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv13(TlsContext tlsContext) {
        return isTLSv13(tlsContext.getServerVersion());
    }

    public static boolean isValidCipherSuiteForSignatureAlgorithms(int i, Vector vector) {
        int keyExchangeAlgorithm = getKeyExchangeAlgorithm(i);
        if (keyExchangeAlgorithm == 0 || keyExchangeAlgorithm == 3 || keyExchangeAlgorithm == 5 || keyExchangeAlgorithm == 17 || keyExchangeAlgorithm == 19 || keyExchangeAlgorithm == 22 || keyExchangeAlgorithm == 23) {
            int size = vector.size();
            for (int i2 = 0; i2 < size; i2++) {
                Short sh = (Short) vector.elementAt(i2);
                if (sh != null && isValidSignatureAlgorithmForServerKeyExchange(sh.shortValue(), keyExchangeAlgorithm)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isValidCipherSuiteForVersion(int i, ProtocolVersion protocolVersion) {
        return isValidVersionForCipherSuite(i, protocolVersion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidCipherSuiteSelection(int[] iArr, int i) {
        return iArr != null && Arrays.contains(iArr, i) && i != 0 && !CipherSuite.isSCSV(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidKeyShareSelection(ProtocolVersion protocolVersion, int[] iArr, Hashtable hashtable, int i) {
        return iArr != null && Arrays.contains(iArr, i) && !hashtable.containsKey(Integers.valueOf(i)) && NamedGroup.canBeNegotiated(i, protocolVersion);
    }

    static boolean isValidSignatureAlgorithmForCertificateVerify(short s, short[] sArr) {
        short clientCertificateType = SignatureAlgorithm.getClientCertificateType(s);
        return clientCertificateType >= 0 && Arrays.contains(sArr, clientCertificateType);
    }

    static boolean isValidSignatureAlgorithmForServerKeyExchange(short s, int i) {
        if (i == 0) {
            return s != 0;
        }
        if (i != 3) {
            if (i != 5) {
                if (i == 17) {
                    return s == 3 || s == 7 || s == 8;
                } else if (i != 19) {
                    if (i != 22) {
                        if (i != 23) {
                            return false;
                        }
                    }
                }
            }
            if (s != 1 && s != 4 && s != 5 && s != 6) {
                switch (s) {
                    case 9:
                    case 10:
                    case 11:
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }
        return 2 == s;
    }

    public static boolean isValidSignatureSchemeForServerKeyExchange(int i, int i2) {
        return isValidSignatureAlgorithmForServerKeyExchange(SignatureScheme.getSignatureAlgorithm(i), i2);
    }

    public static boolean isValidUint16(int i) {
        return (65535 & i) == i;
    }

    public static boolean isValidUint16(long j) {
        return (WebSocketProtocol.PAYLOAD_SHORT_MAX & j) == j;
    }

    public static boolean isValidUint24(int i) {
        return (16777215 & i) == i;
    }

    public static boolean isValidUint24(long j) {
        return (16777215 & j) == j;
    }

    public static boolean isValidUint32(long j) {
        return (BodyPartID.bodyIdMax & j) == j;
    }

    public static boolean isValidUint48(long j) {
        return (281474976710655L & j) == j;
    }

    public static boolean isValidUint64(long j) {
        return true;
    }

    public static boolean isValidUint8(int i) {
        return (i & 255) == i;
    }

    public static boolean isValidUint8(long j) {
        return (255 & j) == j;
    }

    public static boolean isValidUint8(short s) {
        return (s & 255) == s;
    }

    public static boolean isValidVersionForCipherSuite(int i, ProtocolVersion protocolVersion) {
        ProtocolVersion equivalentTLSVersion = protocolVersion.getEquivalentTLSVersion();
        ProtocolVersion minimumVersion = getMinimumVersion(i);
        if (minimumVersion == equivalentTLSVersion) {
            return true;
        }
        if (!minimumVersion.isEarlierVersionOf(equivalentTLSVersion)) {
            return false;
        }
        return ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(minimumVersion) || ProtocolVersion.TLSv13.isLaterVersionOf(equivalentTLSVersion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void negotiatedCipherSuite(SecurityParameters securityParameters, int i) throws IOException {
        int i2;
        securityParameters.cipherSuite = i;
        securityParameters.keyExchangeAlgorithm = getKeyExchangeAlgorithm(i);
        int pRFAlgorithm = getPRFAlgorithm(securityParameters, i);
        securityParameters.prfAlgorithm = pRFAlgorithm;
        if (pRFAlgorithm == 0 || pRFAlgorithm == 1) {
            i2 = -1;
            securityParameters.prfHashAlgorithm = (short) -1;
        } else {
            short hashAlgorithmForPRFAlgorithm = getHashAlgorithmForPRFAlgorithm(pRFAlgorithm);
            securityParameters.prfHashAlgorithm = hashAlgorithmForPRFAlgorithm;
            i2 = HashAlgorithm.getOutputSize(hashAlgorithmForPRFAlgorithm);
        }
        securityParameters.prfHashLength = i2;
        ProtocolVersion negotiatedVersion = securityParameters.getNegotiatedVersion();
        securityParameters.verifyDataLength = isTLSv13(negotiatedVersion) ? securityParameters.getPRFHashLength() : negotiatedVersion.isSSL() ? 36 : 12;
    }

    static void negotiatedVersion(SecurityParameters securityParameters) throws IOException {
        if (!isSignatureAlgorithmsExtensionAllowed(securityParameters.getNegotiatedVersion())) {
            securityParameters.clientSigAlgs = null;
            securityParameters.clientSigAlgsCert = null;
            return;
        }
        if (securityParameters.getClientSigAlgs() == null) {
            securityParameters.clientSigAlgs = getLegacySupportedSignatureAlgorithms();
        }
        if (securityParameters.getClientSigAlgsCert() != null) {
            return;
        }
        securityParameters.clientSigAlgsCert = securityParameters.getClientSigAlgs();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void negotiatedVersionDTLSClient(TlsClientContext tlsClientContext, TlsClient tlsClient) throws IOException {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (ProtocolVersion.isSupportedDTLSVersionClient(negotiatedVersion)) {
            negotiatedVersion(securityParametersHandshake);
            tlsClient.notifyServerVersion(negotiatedVersion);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void negotiatedVersionDTLSServer(TlsServerContext tlsServerContext) throws IOException {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        if (ProtocolVersion.isSupportedDTLSVersionServer(securityParametersHandshake.getNegotiatedVersion())) {
            negotiatedVersion(securityParametersHandshake);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void negotiatedVersionTLSClient(TlsClientContext tlsClientContext, TlsClient tlsClient) throws IOException {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (ProtocolVersion.isSupportedTLSVersionClient(negotiatedVersion)) {
            negotiatedVersion(securityParametersHandshake);
            tlsClient.notifyServerVersion(negotiatedVersion);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void negotiatedVersionTLSServer(TlsServerContext tlsServerContext) throws IOException {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        if (ProtocolVersion.isSupportedTLSVersionServer(securityParametersHandshake.getNegotiatedVersion())) {
            negotiatedVersion(securityParametersHandshake);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    static CertificateRequest normalizeCertificateRequest(CertificateRequest certificateRequest, short[] sArr) {
        if (containsAll(sArr, certificateRequest.getCertificateTypes())) {
            return certificateRequest;
        }
        short[] retainAll = retainAll(certificateRequest.getCertificateTypes(), sArr);
        if (retainAll.length >= 1) {
            return new CertificateRequest(retainAll, certificateRequest.getSupportedSignatureAlgorithms(), certificateRequest.getCertificateAuthorities());
        }
        return null;
    }

    public static Vector parseSupportedSignatureAlgorithms(InputStream inputStream) throws IOException {
        int readUint16 = readUint16(inputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i = readUint16 / 2;
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 < i; i2++) {
            SignatureAndHashAlgorithm parse = SignatureAndHashAlgorithm.parse(inputStream);
            if (parse.getSignature() != 0) {
                vector.addElement(parse);
            }
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processClientCertificate(TlsServerContext tlsServerContext, Certificate certificate, TlsKeyExchange tlsKeyExchange, TlsServer tlsServer) throws IOException {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getPeerCertificate() == null) {
            if (!isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
                if (certificate.isEmpty()) {
                    tlsKeyExchange.skipClientCredentials();
                } else {
                    tlsKeyExchange.processClientCertificate(certificate);
                }
            }
            securityParametersHandshake.peerCertificate = certificate;
            tlsServer.notifyClientCertificate(certificate);
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processServerCertificate(TlsClientContext tlsClientContext, CertificateStatus certificateStatus, TlsKeyExchange tlsKeyExchange, TlsAuthentication tlsAuthentication, Hashtable hashtable, Hashtable hashtable2) throws IOException {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        boolean isTLSv13 = isTLSv13(securityParametersHandshake.getNegotiatedVersion());
        if (tlsAuthentication == null) {
            if (isTLSv13) {
                throw new TlsFatalAlert((short) 80);
            }
            tlsKeyExchange.skipServerCredentials();
            securityParametersHandshake.tlsServerEndPoint = EMPTY_BYTES;
            return;
        }
        Certificate peerCertificate = securityParametersHandshake.getPeerCertificate();
        checkTlsFeatures(peerCertificate, hashtable, hashtable2);
        if (!isTLSv13) {
            tlsKeyExchange.processServerCertificate(peerCertificate);
        }
        tlsAuthentication.notifyServerCertificate(new TlsServerCertificateImpl(peerCertificate, certificateStatus));
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject != null) {
            if (aSN1InputStream.readObject() != null) {
                throw new TlsFatalAlert((short) 50);
            }
            return readObject;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readAllOrNothing(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        int readFully = Streams.readFully(inputStream, bArr);
        if (readFully == 0) {
            return null;
        }
        if (readFully != i) {
            throw new EOFException();
        }
        return bArr;
    }

    public static ASN1Primitive readDERObject(byte[] bArr) throws IOException {
        ASN1Primitive readASN1Object = readASN1Object(bArr);
        if (Arrays.areEqual(readASN1Object.getEncoded("DER"), bArr)) {
            return readASN1Object;
        }
        throw new TlsFatalAlert((short) 50);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] readEncryptedPMS(TlsContext tlsContext, InputStream inputStream) throws IOException {
        return isSSL(tlsContext) ? SSL3Utils.readEncryptedPMS(inputStream) : readOpaque16(inputStream);
    }

    public static void readFully(byte[] bArr, InputStream inputStream) throws IOException {
        int length = bArr.length;
        if (length <= 0 || length == Streams.readFully(inputStream, bArr)) {
            return;
        }
        throw new EOFException();
    }

    public static byte[] readFully(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        if (i != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
        return bArr;
    }

    public static int readInt32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
    }

    public static byte[] readOpaque16(InputStream inputStream) throws IOException {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque16(InputStream inputStream, int i) throws IOException {
        int readUint16 = readUint16(inputStream);
        if (readUint16 >= i) {
            return readFully(readUint16, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readOpaque24(InputStream inputStream) throws IOException {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream, int i) throws IOException {
        int readUint24 = readUint24(inputStream);
        if (readUint24 >= i) {
            return readFully(readUint24, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readOpaque8(InputStream inputStream) throws IOException {
        return readFully(readUint8(inputStream), inputStream);
    }

    public static byte[] readOpaque8(InputStream inputStream, int i) throws IOException {
        short readUint8 = readUint8(inputStream);
        if (readUint8 >= i) {
            return readFully(readUint8, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readOpaque8(InputStream inputStream, int i, int i2) throws IOException {
        short readUint8 = readUint8(inputStream);
        if (readUint8 < i || i2 < readUint8) {
            throw new TlsFatalAlert((short) 50);
        }
        return readFully(readUint8, inputStream);
    }

    public static int readUint16(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readUint16(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public static int[] readUint16Array(int i, InputStream inputStream) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUint16(inputStream);
        }
        return iArr;
    }

    public static int readUint24(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return read3 | (read << 16) | (read2 << 8);
        }
        throw new EOFException();
    }

    public static int readUint24(byte[] bArr, int i) {
        int i2 = i + 1;
        return (bArr[i2 + 1] & 255) | ((bArr[i] & 255) << 16) | ((bArr[i2] & 255) << 8);
    }

    public static long readUint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if (read4 >= 0) {
            return (read4 | (read << 24) | (read2 << 16) | (read3 << 8)) & BodyPartID.bodyIdMax;
        }
        throw new EOFException();
    }

    public static long readUint32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((bArr[i3 + 1] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8)) & BodyPartID.bodyIdMax;
    }

    public static long readUint48(InputStream inputStream) throws IOException {
        return ((readUint24(inputStream) & BodyPartID.bodyIdMax) << 24) | (BodyPartID.bodyIdMax & readUint24(inputStream));
    }

    public static long readUint48(byte[] bArr, int i) {
        int readUint24 = readUint24(bArr, i);
        return (readUint24(bArr, i + 3) & BodyPartID.bodyIdMax) | ((readUint24 & BodyPartID.bodyIdMax) << 24);
    }

    public static short readUint8(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short readUint8(byte[] bArr, int i) {
        return (short) (bArr[i] & 255);
    }

    public static short[] readUint8Array(int i, InputStream inputStream) throws IOException {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readUint8(inputStream);
        }
        return sArr;
    }

    public static short[] readUint8ArrayWithUint8Length(InputStream inputStream, int i) throws IOException {
        short readUint8 = readUint8(inputStream);
        if (readUint8 >= i) {
            return readUint8Array(readUint8, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static ProtocolVersion readVersion(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.get(read, read2);
        }
        throw new EOFException();
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i) {
        return ProtocolVersion.get(bArr[i] & 255, bArr[i + 1] & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsAuthentication receiveServerCertificate(TlsClientContext tlsClientContext, TlsClient tlsClient, ByteArrayInputStream byteArrayInputStream) throws IOException {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getPeerCertificate() == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Certificate parse = Certificate.parse(tlsClientContext, byteArrayInputStream, byteArrayOutputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            if (isTLSv13(securityParametersHandshake.getNegotiatedVersion()) && parse.getCertificateRequestContext().length > 0) {
                throw new TlsFatalAlert((short) 47);
            }
            if (parse.isEmpty()) {
                throw new TlsFatalAlert((short) 50);
            }
            securityParametersHandshake.peerCertificate = parse;
            securityParametersHandshake.tlsServerEndPoint = byteArrayOutputStream.toByteArray();
            TlsAuthentication authentication = tlsClient.getAuthentication();
            if (authentication == null) {
                throw new TlsFatalAlert((short) 80);
            }
            return authentication;
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedAgreement requireAgreementCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsCredentialedAgreement) {
            return (TlsCredentialedAgreement) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedDecryptor requireDecryptorCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsCredentialedDecryptor) {
            return (TlsCredentialedDecryptor) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner requireSignerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsCredentialedSigner) {
            return (TlsCredentialedSigner) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    static short[] retainAll(short[] sArr, short[] sArr2) {
        short[] sArr3 = new short[Math.min(sArr.length, sArr2.length)];
        int i = 0;
        for (int i2 = 0; i2 < sArr2.length; i2++) {
            if (Arrays.contains(sArr, sArr2[i2])) {
                sArr3[i] = sArr2[i2];
                i++;
            }
        }
        return truncate(sArr3, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sealHandshakeHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, boolean z) {
        if (z || !tlsContext.getCrypto().hasAllRawSignatureAlgorithms()) {
            tlsHandshakeHash.forceBuffering();
        }
        tlsHandshakeHash.sealHashAlgorithms();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyShareEntry selectKeyShare(Vector vector, int i) {
        KeyShareEntry keyShareEntry;
        if (vector == null || 1 != vector.size() || (keyShareEntry = (KeyShareEntry) vector.elementAt(0)) == null || keyShareEntry.getNamedGroup() != i) {
            return null;
        }
        return keyShareEntry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyShareEntry selectKeyShare(TlsCrypto tlsCrypto, ProtocolVersion protocolVersion, Vector vector, int[] iArr, int[] iArr2) {
        if (vector == null || isNullOrEmpty(iArr) || isNullOrEmpty(iArr2)) {
            return null;
        }
        for (int i = 0; i < vector.size(); i++) {
            KeyShareEntry keyShareEntry = (KeyShareEntry) vector.elementAt(i);
            int namedGroup = keyShareEntry.getNamedGroup();
            if (NamedGroup.canBeNegotiated(namedGroup, protocolVersion) && Arrays.contains(iArr2, namedGroup) && Arrays.contains(iArr, namedGroup) && tlsCrypto.hasNamedGroup(namedGroup) && ((!NamedGroup.refersToASpecificCurve(namedGroup) || tlsCrypto.hasECDHAgreement()) && (!NamedGroup.refersToASpecificFiniteField(namedGroup) || tlsCrypto.hasDHAgreement()))) {
                return keyShareEntry;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int selectKeyShareGroup(TlsCrypto tlsCrypto, ProtocolVersion protocolVersion, int[] iArr, int[] iArr2) {
        if (isNullOrEmpty(iArr) || isNullOrEmpty(iArr2)) {
            return -1;
        }
        for (int i : iArr) {
            if (NamedGroup.canBeNegotiated(i, protocolVersion) && Arrays.contains(iArr2, i) && tlsCrypto.hasNamedGroup(i) && ((!NamedGroup.refersToASpecificCurve(i) || tlsCrypto.hasECDHAgreement()) && (!NamedGroup.refersToASpecificFiniteField(i) || tlsCrypto.hasDHAgreement()))) {
                return i;
            }
        }
        return -1;
    }

    static void sendSignatureInput(TlsContext tlsContext, DigestInputBuffer digestInputBuffer, OutputStream outputStream) throws IOException {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        outputStream.write(Arrays.concatenate(securityParametersHandshake.getClientRandom(), securityParametersHandshake.getServerRandom()));
        digestInputBuffer.copyTo(outputStream);
        outputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void trackHashAlgorithms(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                short hash = ((SignatureAndHashAlgorithm) vector.elementAt(i)).getHash();
                if (8 == hash) {
                    tlsHandshakeHash.forceBuffering();
                } else if (HashAlgorithm.isRecognized(hash)) {
                    tlsHandshakeHash.trackHashAlgorithm(hash);
                }
            }
        }
    }

    static short[] truncate(short[] sArr, int i) {
        if (i < sArr.length) {
            return sArr;
        }
        short[] sArr2 = new short[i];
        System.arraycopy(sArr, 0, sArr2, 0, i);
        return sArr2;
    }

    static TlsCredentialedSigner validate13Credentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials == null) {
            return null;
        }
        if (!(tlsCredentials instanceof TlsCredentialedSigner)) {
            throw new TlsFatalAlert((short) 80);
        }
        return (TlsCredentialedSigner) tlsCredentials;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CertificateRequest validateCertificateRequest(CertificateRequest certificateRequest, TlsKeyExchange tlsKeyExchange) throws IOException {
        short[] clientCertificateTypes = tlsKeyExchange.getClientCertificateTypes();
        if (clientCertificateTypes == null || clientCertificateTypes.length < 1) {
            throw new TlsFatalAlert((short) 10);
        }
        CertificateRequest normalizeCertificateRequest = normalizeCertificateRequest(certificateRequest, clientCertificateTypes);
        if (normalizeCertificateRequest == null) {
            throw new TlsFatalAlert((short) 47);
        }
        return normalizeCertificateRequest;
    }

    static TlsCredentials validateCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials == null || (tlsCredentials instanceof TlsCredentialedAgreement ? 1 : 0) + 0 + (tlsCredentials instanceof TlsCredentialedDecryptor ? 1 : 0) + (tlsCredentials instanceof TlsCredentialedSigner ? 1 : 0) == 1) {
            return tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static Vector vectorOfOne(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    private static boolean verify13CertificateVerify(TlsCrypto tlsCrypto, DigitallySigned digitallySigned, TlsVerifier tlsVerifier, String str, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        TlsStreamVerifier streamVerifier = tlsVerifier.getStreamVerifier(digitallySigned);
        byte[] certificateVerifyHeader = getCertificateVerifyHeader(str);
        byte[] currentPRFHash = getCurrentPRFHash(tlsHandshakeHash);
        if (streamVerifier != null) {
            OutputStream outputStream = streamVerifier.getOutputStream();
            outputStream.write(certificateVerifyHeader, 0, certificateVerifyHeader.length);
            outputStream.write(currentPRFHash, 0, currentPRFHash.length);
            return streamVerifier.isVerified();
        }
        TlsHash createHash = tlsCrypto.createHash(digitallySigned.getAlgorithm().getHash());
        createHash.update(certificateVerifyHeader, 0, certificateVerifyHeader.length);
        createHash.update(currentPRFHash, 0, currentPRFHash.length);
        return tlsVerifier.verifyRawSignature(digitallySigned, createHash.calculateHash());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void verify13CertificateVerifyClient(TlsServerContext tlsServerContext, CertificateRequest certificateRequest, DigitallySigned digitallySigned, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        TlsCertificate certificateAt = securityParametersHandshake.getPeerCertificate().getCertificateAt(0);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        verifySupportedSignatureAlgorithm(securityParametersHandshake.getServerSigAlgs(), algorithm);
        try {
            if (!verify13CertificateVerify(tlsServerContext.getCrypto(), digitallySigned, certificateAt.createVerifier(algorithm.getSignature()), "TLS 1.3, client CertificateVerify", tlsHandshakeHash)) {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51, (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void verify13CertificateVerifyServer(TlsClientContext tlsClientContext, DigitallySigned digitallySigned, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        TlsCertificate certificateAt = securityParametersHandshake.getPeerCertificate().getCertificateAt(0);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        verifySupportedSignatureAlgorithm(securityParametersHandshake.getClientSigAlgs(), algorithm);
        try {
            if (!verify13CertificateVerify(tlsClientContext.getCrypto(), digitallySigned, certificateAt.createVerifier(algorithm.getSignature()), "TLS 1.3, server CertificateVerify", tlsHandshakeHash)) {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51, (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void verifyCertificateVerifyClient(TlsServerContext tlsServerContext, CertificateRequest certificateRequest, DigitallySigned digitallySigned, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        short signature;
        boolean verifyRawSignature;
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        TlsCertificate certificateAt = securityParametersHandshake.getPeerCertificate().getCertificateAt(0);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm == null) {
            signature = certificateAt.getLegacySignatureAlgorithm();
            short legacyClientCertType = getLegacyClientCertType(signature);
            if (legacyClientCertType < 0 || !Arrays.contains(certificateRequest.getCertificateTypes(), legacyClientCertType)) {
                throw new TlsFatalAlert((short) 43);
            }
        } else {
            signature = algorithm.getSignature();
            if (!isValidSignatureAlgorithmForCertificateVerify(signature, certificateRequest.getCertificateTypes())) {
                throw new TlsFatalAlert((short) 47);
            }
            verifySupportedSignatureAlgorithm(securityParametersHandshake.getServerSigAlgs(), algorithm);
        }
        try {
            TlsVerifier createVerifier = certificateAt.createVerifier(signature);
            if (isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
                verifyRawSignature = verify13CertificateVerify(tlsServerContext.getCrypto(), digitallySigned, createVerifier, "TLS 1.3, client CertificateVerify", tlsHandshakeHash);
            } else {
                TlsStreamVerifier streamVerifier = createVerifier.getStreamVerifier(digitallySigned);
                if (streamVerifier != null) {
                    tlsHandshakeHash.copyBufferTo(streamVerifier.getOutputStream());
                    verifyRawSignature = streamVerifier.isVerified();
                } else {
                    verifyRawSignature = createVerifier.verifyRawSignature(digitallySigned, isTLSv12(tlsServerContext) ? tlsHandshakeHash.getFinalHash(algorithm.getHash()) : securityParametersHandshake.getSessionHash());
                }
            }
            if (!verifyRawSignature) {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51, (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void verifyServerKeyExchangeSignature(TlsContext tlsContext, InputStream inputStream, TlsCertificate tlsCertificate, DigestInputBuffer digestInputBuffer) throws IOException {
        short s;
        boolean verifyRawSignature;
        DigitallySigned parse = DigitallySigned.parse(tlsContext, inputStream);
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        int keyExchangeAlgorithm = securityParametersHandshake.getKeyExchangeAlgorithm();
        SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
        if (algorithm == null) {
            s = getLegacySignatureAlgorithmServer(keyExchangeAlgorithm);
        } else {
            short signature = algorithm.getSignature();
            if (!isValidSignatureAlgorithmForServerKeyExchange(signature, keyExchangeAlgorithm)) {
                throw new TlsFatalAlert((short) 47);
            }
            verifySupportedSignatureAlgorithm(securityParametersHandshake.getClientSigAlgs(), algorithm);
            s = signature;
        }
        TlsVerifier createVerifier = tlsCertificate.createVerifier(s);
        TlsStreamVerifier streamVerifier = createVerifier.getStreamVerifier(parse);
        if (streamVerifier != null) {
            sendSignatureInput(tlsContext, digestInputBuffer, streamVerifier.getOutputStream());
            verifyRawSignature = streamVerifier.isVerified();
        } else {
            verifyRawSignature = createVerifier.verifyRawSignature(parse, calculateSignatureHash(tlsContext, algorithm, digestInputBuffer));
        }
        if (verifyRawSignature) {
            return;
        }
        throw new TlsFatalAlert((short) 51);
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        if (signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
        if (signatureAndHashAlgorithm.getSignature() != 0 && containsSignatureAlgorithm(vector, signatureAndHashAlgorithm)) {
            return;
        }
        throw new TlsFatalAlert((short) 47);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeDowngradeMarker(ProtocolVersion protocolVersion, byte[] bArr) throws IOException {
        byte[] bArr2;
        ProtocolVersion equivalentTLSVersion = protocolVersion.getEquivalentTLSVersion();
        if (ProtocolVersion.TLSv12 == equivalentTLSVersion) {
            bArr2 = DOWNGRADE_TLS12;
        } else if (!equivalentTLSVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv11)) {
            throw new TlsFatalAlert((short) 80);
        } else {
            bArr2 = DOWNGRADE_TLS11;
        }
        System.arraycopy(bArr2, 0, bArr, bArr.length - bArr2.length, bArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeEncryptedPMS(TlsContext tlsContext, byte[] bArr, OutputStream outputStream) throws IOException {
        if (isSSL(tlsContext)) {
            SSL3Utils.writeEncryptedPMS(bArr, outputStream);
        } else {
            writeOpaque16(bArr, outputStream);
        }
    }

    public static void writeGMTUnixTime(byte[] bArr, int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i] = (byte) (currentTimeMillis >>> 24);
        bArr[i + 1] = (byte) (currentTimeMillis >>> 16);
        bArr[i + 2] = (byte) (currentTimeMillis >>> 8);
        bArr[i + 3] = (byte) currentTimeMillis;
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, byte[] bArr2, int i) throws IOException {
        checkUint8(bArr.length);
        writeUint8(bArr.length, bArr2, i);
        System.arraycopy(bArr, 0, bArr2, i + 1, bArr.length);
    }

    public static void writeUint16(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i >>> 8);
        outputStream.write(i);
    }

    public static void writeUint16(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) throws IOException {
        for (int i : iArr) {
            writeUint16(i, outputStream);
        }
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i) throws IOException {
        for (int i2 : iArr) {
            writeUint16(i2, bArr, i);
            i += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i);
        writeUint16Array(iArr, bArr, i + 2);
    }

    public static void writeUint24(int i, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static void writeUint24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void writeUint32(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint32(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 24);
        bArr[i + 1] = (byte) (j >>> 16);
        bArr[i + 2] = (byte) (j >>> 8);
        bArr[i + 3] = (byte) j;
    }

    public static void writeUint48(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint48(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 40);
        bArr[i + 1] = (byte) (j >>> 32);
        bArr[i + 2] = (byte) (j >>> 24);
        bArr[i + 3] = (byte) (j >>> 16);
        bArr[i + 4] = (byte) (j >>> 8);
        bArr[i + 5] = (byte) j;
    }

    public static void writeUint64(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 56));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint64(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 56);
        bArr[i + 1] = (byte) (j >>> 48);
        bArr[i + 2] = (byte) (j >>> 40);
        bArr[i + 3] = (byte) (j >>> 32);
        bArr[i + 4] = (byte) (j >>> 24);
        bArr[i + 5] = (byte) (j >>> 16);
        bArr[i + 6] = (byte) (j >>> 8);
        bArr[i + 7] = (byte) j;
    }

    public static void writeUint8(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i);
    }

    public static void writeUint8(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    public static void writeUint8(short s, OutputStream outputStream) throws IOException {
        outputStream.write(s);
    }

    public static void writeUint8(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) throws IOException {
        for (short s : sArr) {
            writeUint8(s, outputStream);
        }
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i) throws IOException {
        for (short s : sArr) {
            writeUint8(s, bArr, i);
            i++;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i);
        writeUint8Array(sArr, bArr, i + 1);
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) throws IOException {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i) {
        bArr[i] = (byte) protocolVersion.getMajorVersion();
        bArr[i + 1] = (byte) protocolVersion.getMinorVersion();
    }
}
